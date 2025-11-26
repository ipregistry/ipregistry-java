package co.ipregistry.datasets;

import co.ipregistry.datasets.model.GeolocationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class IpregistryGeolocationDatasetTest {

    @Test
    public void test() throws IOException {
        String secretKey = System.getenv("IPREGISTRY_DATASETS_SECRET_KEY");
        Assumptions.assumeTrue(secretKey != null && !secretKey.isEmpty(),
                "IPREGISTRY_DATASETS_SECRET_KEY environment variable not set");

        try (IpregistryGeolocationDataset dataset =
                     IpregistryGeolocationDataset.builder(secretKey).build()) {

            Assertions.assertTrue(
                    dataset.getBuildDate().isAfter(
                            Instant.now().minus(2, ChronoUnit.DAYS)));

            GeolocationData data = dataset.lookup("8.8.8.8");
            Assertions.assertEquals("US", data.getCountryCode());
        }
    }

}
