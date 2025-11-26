package co.ipregistry.datasets;

import com.maxmind.db.Reader;
import lombok.Getter;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This class is an abstraction that defines methods and fields common to all datasets.
 */
@Getter
public class IpregistryDataset implements Closeable {

    /**
     * The reader instance that knows how to lookup data from an IP address.
     */
    protected final AtomicReference<Reader> reader;

    /**
     * The name of the dataset file on local when downloaded.
     */
    protected final String remoteDatasetFileName;

    /**
     * Whether an update is in progress or not.
     */
    protected final AtomicBoolean updating = new AtomicBoolean(false);

    /**
     * Creates an instance with the given parameters.
     *
     * @param datasetPath           the path to the dataset file
     * @param downloadPath          the path to the directory where new version are downloaded.
     * @param loadingMode           the type of loading that depicts how data is loaded.
     * @param secretKey             an Ipregistry Dataset secret key for automatic download of the latest version.
     * @param remoteDatasetFileName the name of the file to download when automatic downloads is used.
     * @throws IOException if an error occurs while downloading the latest version of the dataset.
     */
    protected IpregistryDataset(
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

    /**
     * Returns when the dataset file in use was created.
     *
     * @return when the dataset file in use was created.
     */
    public Instant getBuildDate() {
        Reader readerInstance = reader.get();
        if (readerInstance != null) {
            return readerInstance.getMetadata().buildTime();
        }
        return null;
    }

    /**
     * Creates a reader instance.
     *
     * @param mmdbFile    the MMDB file to load.
     * @param loadingMode the loading mode to use.
     * @return a reader instance.
     * @throws IOException if an IO error occurs.
     */
    protected Reader load(final File mmdbFile, LoadingMode loadingMode) throws IOException {
        return new Reader(
                mmdbFile,
                loadingMode == LoadingMode.MEMORY ?
                        Reader.FileMode.MEMORY :
                        loadingMode == LoadingMode.MEMORY_MAPPED ?
                                Reader.FileMode.MEMORY_MAPPED :
                                Reader.FileMode.MEMORY);
    }

    /**
     * Updates the current dataset by downloading the latest version automatically with the provided secret key.
     * <p>
     * The task is asynchronous. It runs in a dedicated thread. You can wait for the completion using the return value.
     *
     * @param secretKey   the Ipregistry datasets API key for downloading the latest version of the dataset automatically.
     * @param loadingMode the loading mode to use when loading the new version.
     * @return a future that you can use to wait for completion of the update.
     * @throws IOException                      if an IO error occurs while updating.
     * @throws UpdateAlreadyInProgressException if an update is already in progress.
     */
    public CompletableFuture<Void> update(
            final String secretKey,
            final LoadingMode loadingMode) throws IOException, UpdateAlreadyInProgressException {

        return update(secretKey, loadingMode, Paths.get(System.getProperty("java.io.tmpdir")));
    }

    /**
     * Updates the current dataset by downloading the latest version automatically with the provided secret key.
     * <p>
     * The task is asynchronous. It runs in a dedicated thread. You can wait for the completion using the return value.
     *
     * @param secretKey       the Ipregistry datasets API key for downloading the latest version of the dataset automatically.
     * @param loadingMode     the loading mode to use when loading the new version.
     * @param outputDirectory the path where downloaded files are placed.
     * @return a future that you can use to wait for completion of the update.
     * @throws UpdateAlreadyInProgressException if an update is already in progress.
     */
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

    /**
     * Updates the current dataset instance to use the new MMDB file as provided in argument.
     *
     * @param mmdbFile    the new MMDB file to load and use.
     * @param loadingMode the loading mode.
     * @throws IOException if an IO error occurs.
     */
    public void update(final Path mmdbFile, final LoadingMode loadingMode) throws IOException {
        this.reader.set(this.load(mmdbFile.toFile(), loadingMode));
    }

}
