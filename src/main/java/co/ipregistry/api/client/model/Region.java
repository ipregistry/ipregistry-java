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
 * Represents regional or state information associated with an IP address location.
 * <p>
 * This class contains administrative region data at the state or province level
 * within a country. It provides more specific geographical context than country-level
 * information but broader than city-level data.
 * </p>
 */
@AllArgsConstructor
@Data
public class Region {

    /**
     * Creates a new Region instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Region() {
    }

    /**
     * The region or state code, typically the ISO 3166-2 subdivision code.
     * For example, "CA" for California in the US or "ON" for Ontario in Canada.
     */
    @JsonProperty("code")
    private String code;

    /**
     * The full English name of the region or state (e.g., "California", "Ontario", "Bavaria").
     * Provides the human-readable name for the administrative region.
     */
    @JsonProperty("name")
    private String name;

}
