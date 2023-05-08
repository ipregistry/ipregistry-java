package co.ipregistry.datasets.model;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Response associated to a lookup from a {@link co.ipregistry.datasets.IpregistryGeolocationDataset}.
 */
@Getter
@EqualsAndHashCode
@ToString
public final class GeolocationData {

    /**
     * Creates a new {@code GeolocationData} instance using the given arguments.
     *
     * @param countryCode a country code in ISO 3166-1 format.
     * @param countryName a country name.
     * @param regionCode a region code in ISO 3166-2 format.
     * @param regionName a region name.
     * @param city a city name.
     * @param postal a postal code.
     * @param latitude a latitude.
     * @param longitude a longitude.
     * @param timeZone a time zone.
     */
    @MaxMindDbConstructor
    public GeolocationData(
            @MaxMindDbParameter(name = "country_code") String countryCode,
            @MaxMindDbParameter(name = "country_name") String countryName,
            @MaxMindDbParameter(name = "region_code") String regionCode,
            @MaxMindDbParameter(name = "region_name") String regionName,
            @MaxMindDbParameter(name = "city") String city,
            @MaxMindDbParameter(name = "postal") String postal,
            @MaxMindDbParameter(name = "latitude") Double latitude,
            @MaxMindDbParameter(name = "longitude") Double longitude,
            @MaxMindDbParameter(name = "time_zone") String timeZone
    ) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.city = city;
        this.postal = postal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeZone = timeZone;
    }


    private final String countryCode;

    private final String countryName;

    private final String regionCode;

    private final String regionName;

    private final String city;

    private final String postal;

    private final double latitude;

    private final double longitude;

    private final String timeZone;

}
