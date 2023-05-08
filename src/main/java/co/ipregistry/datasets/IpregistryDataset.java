package co.ipregistry.datasets;

import com.maxmind.db.Reader;
import lombok.Getter;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class IpregistryDataset implements Closeable {

    protected final AtomicReference<Reader> reader;

    protected final String remoteDatasetFileName;

    protected final AtomicBoolean updating = new AtomicBoolean(false);

    public IpregistryDataset(
            final Path datasetPath,
            final Path downloadPath,
            final LoadingMode loadingMode,
            final String remoteDatasetFileName,
            final String secretKey) throws IOException {

        this.reader = new AtomicReference<>();
        this.remoteDatasetFileName = remoteDatasetFileName;

        if (datasetPath != null && Files.exists(datasetPath)) {
            this.update(datasetPath, loadingMode);
        } else if (secretKey != null) {
            try {
                this.update(
                        secretKey,
                        loadingMode,
                        downloadPath == null ?
                                Paths.get(System.getProperty("java.io.tmpdir")) :
                                downloadPath).get();
            } catch (final ExecutionException | InterruptedException | UpdateAlreadyInProgressException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("You need to pass a valid MMDB file or Secret Key.");
        }
    }

    public void close() throws IOException {
        Reader readerInstance = reader.get();
        if (readerInstance != null) {
            readerInstance.close();
        }
    }

    public Date getBuildDate() {
        Reader readerInstance = reader.get();
        if (readerInstance != null) {
            return readerInstance.getMetadata().getBuildDate();
        }
        return null;
    }

    protected Reader load(final File mmdbFile, LoadingMode loadingMode) throws IOException {
        return new Reader(
                mmdbFile,
                loadingMode == LoadingMode.MEMORY ?
                        Reader.FileMode.MEMORY :
                        loadingMode == LoadingMode.MEMORY_MAPPED ?
                                Reader.FileMode.MEMORY_MAPPED :
                                Reader.FileMode.MEMORY);
    }

    public void update(
            final String secretKey,
            final LoadingMode loadingMode) throws IOException, UpdateAlreadyInProgressException {

        update(secretKey, loadingMode, Paths.get(System.getProperty("java.io.tmpdir")));
    }

    public CompletableFuture<Void> update(
            final String secretKey,
            final LoadingMode loadingMode,
            final Path outputDirectory) throws UpdateAlreadyInProgressException {

        if (!this.updating.compareAndSet(false, true)) {
            throw new UpdateAlreadyInProgressException();
        }

        final Path outputDirectory2 = outputDirectory.resolve("ipregistry-datasets");

        outputDirectory2.toFile().mkdirs();

        return CompletableFuture.supplyAsync(() -> {


            final Path outputPath = outputDirectory2.resolve(remoteDatasetFileName + "_" + UUID.randomUUID());

            try (
                    BufferedInputStream in = new BufferedInputStream(new URL(
                            "https://download.ipregistry.co/" + remoteDatasetFileName + ".mmdb?key=" + secretKey
                    ).openStream());
                    FileOutputStream out = new FileOutputStream(outputPath.toFile())) {

                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    out.write(dataBuffer, 0, bytesRead);
                }

                update(outputPath, loadingMode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                this.updating.set(false);
            }
            return null;
        });

    }

    public void update(final Path mmdbFile, final LoadingMode loadingMode) throws IOException {
        this.reader.set(this.load(mmdbFile.toFile(), loadingMode));
    }

}
