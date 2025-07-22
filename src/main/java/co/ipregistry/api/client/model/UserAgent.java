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
import lombok.*;


/**
 * Represents parsed user agent information from HTTP headers.
 * <p>
 * This class contains comprehensive analysis of a user agent string, providing
 * detailed information about the client application, browser, device, rendering engine,
 * and operating system. This data is useful for analytics, compatibility checks,
 * and providing optimized content based on client capabilities.
 * </p>
 */
@AllArgsConstructor
@Builder
@Data
public class UserAgent {

    /**
     * Creates a new UserAgent instance with default values.
     * This constructor is used for JSON deserialization and the Builder pattern.
     */
    public UserAgent() {
    }

    /**
     * The original user agent header string as sent by the client.
     * Contains the raw user agent string that was parsed to extract the other fields.
     */
    @JsonProperty("header")
    private String header;

    /**
     * The name of the client application or browser (e.g., "Chrome", "Firefox", "Safari").
     * Identifies the specific software making the request.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The type or category of the user agent (e.g., "browser", "bot", "mobile app").
     * Classifies the type of client making the request.
     */
    @JsonProperty("type")
    private String type;

    /**
     * The full version string of the client application (e.g., "91.0.4472.124").
     * Provides the complete version information for the client.
     */
    @JsonProperty("version")
    private String version;

    /**
     * The major version number of the client application (e.g., "91").
     * Provides just the major version for simplified version comparisons.
     */
    @JsonProperty("version_major")
    private String versionMajor;

    /**
     * Information about the device hardware used by the client.
     * Contains device brand, model, and type information.
     */
    @JsonProperty("device")
    private UserAgentDevice device;

    /**
     * Information about the rendering engine used by the client.
     * Contains engine name, type, and version details.
     */
    @JsonProperty("engine")
    private UserAgentEngine engine;

    /**
     * Information about the operating system running on the client device.
     * Contains OS name, type, and version information.
     */
    @JsonProperty("os")
    private UserAgentOperatingSystem operatingSystem;

}
