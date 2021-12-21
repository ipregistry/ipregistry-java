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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * This class is used to enrich IpInfo data with user-agent data when retrieving data for an origin lookup request.
 */
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class RequesterIpInfo extends IpInfo {

    @JsonProperty("user_agent")
    private UserAgent userAgent;


    /**
     * Creates a new {@code RequesterIpInfo} instance with the specified {@code ip} and {@code userAgent} instances.
     *
     * @param ip        the IP info data.
     * @param userAgent the user-agent instance.
     */
    public RequesterIpInfo(final IpInfo ip, final UserAgent userAgent) {
        super();

        super.ip = ip.ip;
        super.type = ip.type;
        super.hostname = ip.hostname;
        super.connection = ip.connection;
        super.currency = ip.currency;
        super.location = ip.location;
        super.security = ip.security;
        super.timeZone = ip.timeZone;

        this.userAgent = userAgent;
    }

}
