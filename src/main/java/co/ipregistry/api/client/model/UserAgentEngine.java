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
 * Represents browser engine information extracted from a user agent string.
 * <p>
 * This class contains information about the rendering engine used by the client browser
 * or application. The engine is responsible for parsing HTML, CSS, and executing JavaScript.
 * This information is useful for web compatibility checks and feature detection.
 * </p>
 */
@AllArgsConstructor
@Builder
@Data
public class UserAgentEngine {

    /**
     * Creates a new UserAgentEngine instance with default values.
     * This constructor is used for JSON deserialization and the Builder pattern.
     */
    public UserAgentEngine() {
    }

    /**
     * The name of the rendering engine (e.g., "Blink", "WebKit", "Gecko", "Trident").
     * Identifies the specific engine used for rendering web content.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The type or category of the engine (e.g., "browser", "webview").
     * Classifies the engine into broad categories.
     */
    @JsonProperty("type")
    private String type;

    /**
     * The full version string of the rendering engine (e.g., "91.0.4472.124").
     * Provides complete version information for the engine.
     */
    @JsonProperty("version")
    private String version;

    /**
     * The major version number of the rendering engine (e.g., "91").
     * Provides just the major version for simplified compatibility checks.
     */
    @JsonProperty("version_major")
    private String versionMajor;

}
