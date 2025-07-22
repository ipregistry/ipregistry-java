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
 * Represents operating system information extracted from a user agent string.
 * <p>
 * This class contains information about the operating system running on the client device.
 * This includes the OS name, category, and version information, which is useful for
 * compatibility checks, feature detection, and analytics.
 * </p>
 */
@AllArgsConstructor
@Builder
@Data
public class UserAgentOperatingSystem {

    /**
     * Creates a new UserAgentOperatingSystem instance with default values.
     * This constructor is used for JSON deserialization and the Builder pattern.
     */
    public UserAgentOperatingSystem() {
    }

    /**
     * The name of the operating system (e.g., "Windows", "macOS", "iOS", "Android", "Linux").
     * Identifies the specific operating system running on the client device.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The type or category of the operating system (e.g., "desktop", "mobile", "server").
     * Classifies the OS into broad categories for easier handling.
     */
    @JsonProperty("type")
    private String type;

    /**
     * The version string of the operating system (e.g., "10.0", "14.4", "11").
     * Provides version information for the operating system, useful for compatibility checks.
     */
    @JsonProperty("version")
    private String version;

}
