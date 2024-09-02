package co.ipregistry.datasets;

import co.ipregistry.datasets.model.GeolocationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class IpregistryGeolocationDatasetTest {

    @Test
    public void test() throws IOException {
        try (IpregistryGeolocationDataset dataset =
                     IpregistryGeolocationDataset.builder(
                             System.getenv("IPREGISTRY_DATASETS_SECRET_KEY")).build()) {

            Assertions.assertTrue(
                    dataset.getBuildDate().toInstant().isAfter(
                            Instant.now().minus(2, ChronoUnit.DAYS)));

            GeolocationData data = dataset.lookup("8.8.8.8");
            Assertions.assertEquals("US", data.getCountryCode());
        }
    }

}
