package co.ipregistry.datasets;

import co.ipregistry.datasets.model.GeolocationData;
import com.google.common.net.InetAddresses;
import com.maxmind.db.Reader;
import lombok.Getter;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;

@Getter
public final class IpregistryGeolocationDataset extends IpregistryDataset {

    private IpregistryGeolocationDataset(
            final Path datasetFilePath,
            final Path downloadDirectoryPath,
            final LoadingMode loadingMode,
            final String secretKey) throws IOException {

        super(datasetFilePath, downloadDirectoryPath, loadingMode, "ipregistry-geolocation", secretKey);
    }

    public GeolocationData lookup(final String ip) throws IOException {
        return lookup(InetAddresses.forString(ip));
    }

    public GeolocationData lookup(final InetAddress inetAddress) throws IOException {
        final Reader readerInstance = reader.get();
        if (readerInstance != null) {
            return readerInstance.get(inetAddress, GeolocationData.class);
        }
        return null;
    }

    public static Builder builder(final Path datasetFilePath) {
        return new Builder(datasetFilePath);
    }

    public static Builder builder(final String secretKey) {
        return new Builder(secretKey);
    }

    public static Builder builder(final String secretKey, final Path downloadDirectoryPath) {
        return new Builder(secretKey, downloadDirectoryPath);
    }

    @Getter
    public static final class Builder {

        private final Path datasetPath;

        private final Path downloadPath;

        private LoadingMode loadingMode;

        private final String secretKey;

        public Builder(final Path datasetFilePath) {
            this.downloadPath = null;
            this.datasetPath = datasetFilePath;
            this.secretKey = null;
        }

        public Builder(final String secretKey) {
            this.datasetPath = null;
            this.downloadPath = null;
            this.secretKey = secretKey;
        }

        public Builder(final String secretKey, final Path downloadDirectoryPath) {
            this.datasetPath = null;
            this.downloadPath = downloadDirectoryPath;
            this.secretKey = secretKey;
        }

        public Builder loadingMode(final LoadingMode loadingMode) {
            this.loadingMode = loadingMode;
            return this;
        }

        public IpregistryGeolocationDataset build() throws IOException {
            return new IpregistryGeolocationDataset(datasetPath, downloadPath, loadingMode, secretKey);
        }

    }

}
