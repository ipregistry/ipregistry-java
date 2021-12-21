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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Enumerate all kinds of connection types.
 */
public enum ConnectionType {

    /**
     * General business purposes.
     */
    BUSINESS("business"),
    /**
     * College, School, or University.
     */
    EDUCATION("education"),
    /**
     * Municipality or larger governmental usage.
     */
    GOVERNMENT("government"),
    /**
     * Content Delivery Network (CDN), Data Center or Web Hosting.
     */
    HOSTING("hosting"),
    /**
     * Inactive in the sense there exists no recent BGP announcements involving this AS.
     */
    INACTIVE("inactive"),
    /**
     * Fixed line or mobile ISP.
     */
    ISP("isp");

    @JsonValue
    String name;

    ConnectionType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns an enum type from its name.
     *
     * @param value an enum name.
     * @return an enum type from its name. If the specified value is unknown, the result fallbacks to {@link ConnectionType#BUSINESS}.
     */
    @JsonCreator
    public static ConnectionType from(final String value) {
        for (final ConnectionType type : ConnectionType.values()) {
            if (type.name.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return ConnectionType.BUSINESS;
    }

}
