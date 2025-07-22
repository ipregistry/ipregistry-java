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

import java.util.List;


/**
 * Represents country information associated with an IP address location.
 * <p>
 * This class contains comprehensive country-level data including geographical,
 * demographic, political, and administrative information. It provides details
 * about the country where an IP address is located, including physical characteristics,
 * population statistics, governmental information, and cultural attributes.
 * </p>
 */
@AllArgsConstructor
@Data
public class Country {

    /**
     * Creates a new Country instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Country() {
    }

    /**
     * The total land area of the country in square kilometers.
     * Represents the geographical size of the country's territory.
     */
    @JsonProperty("area")
    private double area;

    /**
     * List of ISO 3166-1 alpha-2 country codes for countries that share land borders.
     * Contains two-letter country codes (e.g., ["US", "MX"]) for neighboring countries.
     */
    @JsonProperty("borders")
    private List<String> borders;

    /**
     * The international dialing code for the country (e.g., "+1", "+44", "+33").
     * Used for making international phone calls to this country.
     */
    @JsonProperty("calling_code")
    private String callingCode;

    /**
     * The name of the country's capital city.
     * May be null for countries without a designated capital.
     */
    @JsonProperty("capital")
    private String capital;

    /**
     * The ISO 3166-1 alpha-2 country code (e.g., "US", "GB", "FR").
     * This is the standard two-letter country code used internationally.
     */
    @JsonProperty("code")
    private String code;

    /**
     * The official English name of the country (e.g., "United States", "United Kingdom").
     * Provides the commonly used English name for the country.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The total population of the country.
     * Represents the estimated number of inhabitants in the country.
     */
    @JsonProperty("population")
    private int population;

    /**
     * The population density in people per square kilometer.
     * Calculated as population divided by total area, indicating how crowded the country is.
     */
    @JsonProperty("population_density")
    private double populationDensity;

    /**
     * Information about the country's flag including colors, design, and emoji representation.
     * Contains visual and symbolic information about the national flag.
     */
    @JsonProperty("flag")
    private Flag flag;

    /**
     * List of languages officially spoken or commonly used in the country.
     * Contains language information including codes, names, and native representations.
     */
    @JsonProperty("languages")
    private List<Language> languages;

    /**
     * The country code top-level domain (ccTLD) for the country (e.g., ".us", ".uk", ".fr").
     * This is the Internet domain extension assigned to the country.
     */
    @JsonProperty("tld")
    private String tld;

}
