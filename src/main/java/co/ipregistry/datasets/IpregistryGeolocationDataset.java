package co.ipregistry.datasets;

import co.ipregistry.datasets.model.GeolocationData;
import com.google.common.net.InetAddresses;
import com.maxmind.db.Reader;
import lombok.Getter;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;

/**
 * Class used to load and query the Ipregistry Geolocation dataset.
 */
@Getter
public final class IpregistryGeolocationDataset extends IpregistryDataset {

    /**
     * Creates a new {@code IpregistryGeolocationDataset} instance using the given arguments.
     *
     * @param datasetFilePath       the path to the dataset file
     * @param downloadDirectoryPath the path to the directory where new version are downloaded.
     * @param loadingMode           the type of loading that depicts how data is loaded.
     * @param secretKey             an Ipregistry Dataset secret key for automatic download of the latest version.
     * @throws IOException if an error occurs while downloading the latest version of the dataset.
     */
    private IpregistryGeolocationDataset(final Path datasetFilePath, final Path downloadDirectoryPath, final LoadingMode loadingMode, final String secretKey) throws IOException {

        super(datasetFilePath, downloadDirectoryPath, loadingMode, "ipregistry-geolocation", secretKey);
    }

    /**
     * Lookups data for the given IP address.
     *
     * @param ip the IP address to lookup.
     * @return data found or {@code null} if no result.
     * @throws IOException if an error happen while looking up the data.
     */
    public GeolocationData lookup(final String ip) throws IOException {
        return lookup(InetAddresses.forString(ip));
    }

    /**
     * Lookups data for the given IP address.
     *
     * @param inetAddress the IP address to lookup.
     * @return data found or {@code null} if no result.
     * @throws IOException if an error happen while looking up the data.
     */
    public GeolocationData lookup(final InetAddress inetAddress) throws IOException {
        final Reader readerInstance = reader.get();
        if (readerInstance != null) {
            return readerInstance.get(inetAddress, GeolocationData.class);
        }
        return null;
    }

    /**
     * Creates a builder that specify loading the dataset file locally from the specified location.
     *
     * @param datasetFilePath the path to the dataset file.
     * @return the builder instance.
     */
    public static Builder builder(final Path datasetFilePath) {
        return new Builder(datasetFilePath);
    }

    /**
     * Creates a builder that specify downloading the latest version of the dataset automatically using the specified secret key..
     * <p>
     * The file that is downloaded is written on the disk on the default temporary folder.
     *
     * @param secretKey the Ipregistry datasets secret key.
     * @return the builder instance.
     */
    public static Builder builder(final String secretKey) {
        return new Builder(secretKey);
    }

    /**
     * Creates a builder that specify downloading the latest version of the dataset automatically using the specified secret key..
     *
     * @param secretKey             the Ipregistry datasets secret key.
     * @param downloadDirectoryPath where to place the downloaded file locally.
     * @return the builder instance.
     */
    public static Builder builder(final String secretKey, final Path downloadDirectoryPath) {
        return new Builder(secretKey, downloadDirectoryPath);
    }

    /**
     * A builder specifies options to create an {@code IpregistryGeolocationDataset}.
     */
    @Getter
    public static final class Builder {

        /**
         * The path to the dataset file.
         */
        private final Path datasetPath;

        /**
         * The folder where downloaded files are placed.
         */
        private final Path downloadPath;

        /**
         * The type of dataset loading in memory.
         */
        private LoadingMode loadingMode;

        /**
         * The Ipregistry datasets secret key to use for automatic downloads.
         */
        private final String secretKey;

        /**
         * Creates a builder with the specified path to a dataset file.
         *
         * @param datasetFilePath The path to the dataset file.
         */
        public Builder(final Path datasetFilePath) {
            this.downloadPath = null;
            this.datasetPath = datasetFilePath;
            this.secretKey = null;
        }

        /**
         * Creates a builder with the specified secret key for automatic downloads.
         *
         * @param secretKey The Ipregistry datasets secret key to use for automatic downloads.
         */
        public Builder(final String secretKey) {
            this.datasetPath = null;
            this.downloadPath = null;
            this.secretKey = secretKey;
        }

        /**
         * reates a builder with the specified secret key for automatic downloads.
         *
         * @param secretKey             The Ipregistry datasets secret key to use for automatic downloads.
         * @param downloadDirectoryPath the folder where downloaded files are placed.
         */
        public Builder(final String secretKey, final Path downloadDirectoryPath) {
            this.datasetPath = null;
            this.downloadPath = downloadDirectoryPath;
            this.secretKey = secretKey;
        }

        /**
         * Configures a new loading mode.
         *
         * @param loadingMode the loading mode to use.
         * @return the builder instance.
         */
        public Builder loadingMode(final LoadingMode loadingMode) {
            this.loadingMode = loadingMode;
            return this;
        }

        /**
         * Creates an {code IpregistryGeolocationDataset} instance with options configured on the builder.
         *
         * @return an {code IpregistryGeolocationDataset} instance with options configured on the builder.
         * @throws IOException if an IO error occurs.
         */
        public IpregistryGeolocationDataset build() throws IOException {
            return new IpregistryGeolocationDataset(datasetPath, downloadPath, loadingMode, secretKey);
        }

    }

}
