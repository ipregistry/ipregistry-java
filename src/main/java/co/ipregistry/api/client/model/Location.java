/*
 * Copyright 2019 Ipregistry (https://ipregistry.co).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.ipregistry.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents geographical location information associated with an IP address.
 * <p>
 * This class contains comprehensive location data including continent, country, region,
 * city information, geographical coordinates, primary language, and EU membership status.
 * Location data is used for geolocation services and geographic targeting.
 * </p>
 */
@AllArgsConstructor
@Data
public class Location {

    /**
     * Creates a new Location instance with default values.
     * All fields are initialized to their default values (empty objects or null).
     * This constructor is primarily used for JSON deserialization.
     */
    public Location() {
    }

    /**
     * Continent information where the IP address is located.
     * Contains continent code and name details.
     */
    @JsonProperty("continent")
    private Continent continent = new Continent();

    /**
     * Country information where the IP address is located.
     * Contains country code, name, flag, and other country-specific details.
     */
    @JsonProperty("country")
    private Country country = new Country();

    /**
     * Regional/state information where the IP address is located.
     * Contains region code and name within the country.
     */
    @JsonProperty("region")
    private Region region = new Region();

    /**
     * City name where the IP address is located.
     * May be null if city information is not available.
     */
    @JsonProperty("city")
    private String city;

    /**
     * Postal or ZIP code associated with the IP address location.
     * May be null if postal code information is not available.
     */
    @JsonProperty("postal")
    private String postal;

    /**
     * Geographical latitude coordinate of the IP address location.
     * Expressed as a decimal degree value. May be null if coordinates are not available.
     */
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Geographical longitude coordinate of the IP address location.
     * Expressed as a decimal degree value. May be null if coordinates are not available.
     */
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * Primary language spoken in the location where the IP address is situated.
     * Contains language code, name, and native name information.
     */
    @JsonProperty("language")
    private Language language;

    /**
     * Indicates whether the IP address location is within the European Union.
     * Returns true if the location is in an EU member country, false otherwise.
     */
    @JsonProperty("in_eu")
    private boolean eu;

}
