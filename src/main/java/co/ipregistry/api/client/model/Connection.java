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
 * Wraps connection data associated with an IP address.
 * Contains network infrastructure information including ASN, organization,
 * routing details, and connection type classification.
 */
@AllArgsConstructor
@Data
public class Connection {

    /**
     * Creates a new Connection instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Connection() {
    }

    /**
     * The Autonomous System Number (ASN) that manages the IP address.
     */
    @JsonProperty("asn")
    private Long asn;

    /**
     * The primary domain name associated with the organization.
     */
    @JsonProperty("domain")
    private String domain;

    /**
     * The name of the organization or ISP that owns the IP address.
     */
    @JsonProperty("organization")
    private String organization;

    /**
     * The IP route or subnet in CIDR notation.
     */
    @JsonProperty("route")
    private String route;

    /**
     * The classification of the connection type (e.g., ISP, business, hosting).
     */
    @JsonProperty("type")
    private ConnectionType type;

}
