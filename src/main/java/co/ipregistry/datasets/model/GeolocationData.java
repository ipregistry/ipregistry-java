package co.ipregistry.datasets.model;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class GeolocationData {

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
