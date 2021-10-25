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


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class IpInfo {

    @JsonProperty("ip")
    protected String ip;

    @Builder.Default
    @JsonProperty("type")
    protected IpType type = IpType.UNKNOWN;

    @JsonProperty("hostname")
    protected String hostname;

    @Builder.Default
    @JsonProperty("carrier")
    protected Carrier carrier = new Carrier();

    @Builder.Default
    @JsonProperty("company")
    protected Company company = new Company();

    @Builder.Default
    @JsonProperty("connection")
    protected Connection connection = new Connection();

    @Builder.Default
    @JsonProperty("currency")
    protected Currency currency = new Currency();

    @Builder.Default
    @JsonProperty("location")
    protected Location location = new Location();

    @Builder.Default
    @JsonProperty("security")
    protected Security security = new Security();

    @Builder.Default
    @JsonProperty("time_zone")
    protected TimeZone timeZone = new TimeZone();

}
