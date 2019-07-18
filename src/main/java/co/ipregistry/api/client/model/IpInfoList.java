/*
 * Copyright 2019 IpregistryClient (https://ipregistry.co).
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

import co.ipregistry.api.client.exceptions.IpInfoException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@JsonDeserialize(using = IpInfoListDeserializer.class)
@NoArgsConstructor
@ToString
public class IpInfoList {

    @JsonProperty("results")
    Object[] ips = null;


    public IpInfo get(int index) throws IpInfoException {
        Object object = ips[index];

        if (object instanceof IpInfo) {
            return (IpInfo) object;
        }

        throw (IpInfoException) object;
    }

    public int size() {
        if (ips == null) {
            return 0;
        }

        return ips.length;
    }

    public Object unsafeGet(int index) {
        return ips[index];
    }

}
