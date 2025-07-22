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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents continent information associated with an IP address location.
 * <p>
 * This class contains basic continent identification data, including the continent
 * code and name. It provides geographical context at the continental level for
 * IP address geolocation results.
 * </p>
 */
@AllArgsConstructor
@Builder
@Data
public class Continent {

    /**
     * Creates a new Continent instance with default values.
     * This constructor is used for JSON deserialization and the Builder pattern.
     */
    public Continent() {
    }

    /**
     * The two-letter continent code (e.g., "NA" for North America, "EU" for Europe, "AS" for Asia).
     * This follows standard continent abbreviation conventions used in geographical data.
     */
    @JsonProperty("code")
    private String code;

    /**
     * The full English name of the continent (e.g., "North America", "Europe", "Asia").
     * Provides the human-readable name for the continent.
     */
    @JsonProperty("name")
    private String name;

}
