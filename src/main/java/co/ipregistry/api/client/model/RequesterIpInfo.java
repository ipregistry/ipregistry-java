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
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class RequesterIpInfo extends IpInfo {

    @JsonProperty("user_agent")
    private UserAgent userAgent;

    public RequesterIpInfo(IpInfo ip, UserAgent userAgent) {
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
