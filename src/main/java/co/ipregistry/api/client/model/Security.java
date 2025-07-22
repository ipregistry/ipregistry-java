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
 * Represents security-related information and threat intelligence associated with an IP address.
 * <p>
 * This class contains various security indicators that help identify potentially malicious,
 * suspicious, or anonymized traffic. These flags are used for fraud prevention, security
 * filtering, and risk assessment purposes.
 * </p>
 */
@AllArgsConstructor
@Data
public class Security {

    /**
     * Creates a new Security instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Security() {
    }

    /**
     * Indicates if the IP address is known to be associated with abusive behavior.
     * Returns true if the IP has been reported for spam, malware distribution, or other abuse.
     */
    @JsonProperty("is_abuser")
    private boolean abuser;

    /**
     * Indicates if the IP address is known to be associated with malicious attacks.
     * Returns true if the IP has been involved in hacking attempts, DDoS attacks, or similar threats.
     */
    @JsonProperty("is_attacker")
    private boolean attacker;

    /**
     * Indicates if the IP address is a bogon (bogus IP address).
     * Returns true if the IP is from an unallocated, reserved, or special-use address space.
     */
    @JsonProperty("is_bogon")
    private boolean bogon;

    /**
     * Indicates if the IP address belongs to a cloud service provider.
     * Returns true if the IP is associated with cloud hosting services like AWS, Google Cloud, Azure, etc.
     */
    @JsonProperty("is_cloud_provider")
    private boolean cloudProvider;

    /**
     * Indicates if the IP address is operating as a proxy server.
     * Returns true if the IP is known to be a proxy that forwards requests on behalf of clients.
     */
    @JsonProperty("is_proxy")
    private boolean proxy;

    /**
     * Indicates if the IP address is operating as a relay server.
     * Returns true if the IP is known to relay network traffic, potentially for anonymization.
     */
    @JsonProperty("is_relay")
    private boolean relay;

    /**
     * Indicates if the IP address is part of the Tor network.
     * Returns true if the IP is associated with Tor infrastructure (relays, bridges, etc.).
     */
    @JsonProperty("is_tor")
    private boolean tor;

    /**
     * Indicates if the IP address is a Tor exit node.
     * Returns true if the IP is specifically a Tor exit node where traffic leaves the Tor network.
     */
    @JsonProperty("is_tor_exit")
    private boolean torExitNode;

    /**
     * Indicates if the IP address provides anonymous browsing capabilities.
     * Returns true if the IP is associated with services that anonymize user traffic.
     */
    @JsonProperty("is_anonymous")
    private boolean anonymous;

    /**
     * Indicates if the IP address is considered a general security threat.
     * Returns true if the IP poses any kind of security risk based on threat intelligence.
     */
    @JsonProperty("is_threat")
    private boolean threat;

    /**
     * Indicates if the IP address is associated with a VPN (Virtual Private Network) service.
     * Returns true if the IP belongs to a VPN provider or VPN exit server.
     */
    @JsonProperty("is_vpn")
    private boolean vpn;

}
