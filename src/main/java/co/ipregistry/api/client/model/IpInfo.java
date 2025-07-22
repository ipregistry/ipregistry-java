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
 * Represents comprehensive information associated with an IP address.
 * <p>
 * This class serves as the primary data container for IP address lookup results,
 * containing detailed information about the IP address including its type, hostname,
 * geographical location, network carrier, company ownership, connection details,
 * currency information, security analysis, and time zone data.
 * </p>
 * <p>
 * The class uses the Builder pattern (via Lombok) for convenient object construction
 * and provides default instances for complex nested objects to avoid null pointer exceptions.
 * </p>
 */
@AllArgsConstructor
@Builder
@Data
public class IpInfo {

    /**
     * Creates a new IpInfo instance with default values.
     * This constructor is used for JSON deserialization and the Builder pattern.
     */
    public IpInfo() {
        this.carrier = new Carrier();
        this.company = new Company();
        this.connection = new Connection();
        this.currency = new Currency();
        this.location = new Location();
        this.security = new Security();
        this.timeZone = new TimeZone();
        this.type = IpType.UNKNOWN;
    }

    /**
     * IP data found for the IP address.
     */
    @JsonProperty("ip")
    protected String ip;

    /**
     * IP type found for the IP address.
     */
    @Builder.Default
    @JsonProperty("type")
    protected IpType type = IpType.UNKNOWN;

    /**
     * Hostname found for the IP address.
     */
    @JsonProperty("hostname")
    protected String hostname;

    /**
     * Carrier data found for the IP address.
     */
    @Builder.Default
    @JsonProperty("carrier")
    protected Carrier carrier = new Carrier();

    /**
     * Company data found for the IP address.
     */
    @Builder.Default
    @JsonProperty("company")
    protected Company company = new Company();

    /**
     * Connection data found for the IP address.
     */
    @Builder.Default
    @JsonProperty("connection")
    protected Connection connection = new Connection();

    /**
     * Currency data found for the IP address.
     */
    @Builder.Default
    @JsonProperty("currency")
    protected Currency currency = new Currency();

    /**
     * Location data found for the IP address.
     */
    @Builder.Default
    @JsonProperty("location")
    protected Location location = new Location();

    /**
     * Security data found for the IP address.
     */
    @Builder.Default
    @JsonProperty("security")
    protected Security security = new Security();

    /**
     * Time zone data found for the IP address.
     */
    @Builder.Default
    @JsonProperty("time_zone")
    protected TimeZone timeZone = new TimeZone();

}
