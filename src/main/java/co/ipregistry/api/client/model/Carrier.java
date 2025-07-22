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
 * Wraps mobile carrier data (name, MCC, MNC) associated with an IP address.
 * This class provides information about the mobile network operator that owns
 * a mobile IP address, including the carrier name and network codes.
 */
@AllArgsConstructor
@Data
public final class Carrier {

    /**
     * Creates a new Carrier instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Carrier() {
    }

    /**
     * The name of the mobile carrier/operator.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Mobile Country Code (MCC) - a 3-digit code identifying the country of the mobile network.
     */
    @JsonProperty("mcc")
    private String mcc;

    /**
     * Mobile Network Code (MNC) - a 2-3 digit code identifying the mobile network within a country.
     */
    @JsonProperty("mnc")
    private String mnc;

}
