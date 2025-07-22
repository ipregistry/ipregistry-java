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
 * Represents device information extracted from a user agent string.
 * <p>
 * This class contains hardware-specific information about the device used by the client,
 * including manufacturer, model name, and device category. This information is useful
 * for device-specific optimizations, responsive design, and analytics.
 * </p>
 */
@AllArgsConstructor
@Builder
@Data
public class UserAgentDevice {

    /**
     * Creates a new UserAgentDevice instance with default values.
     * This constructor is used for JSON deserialization and the Builder pattern.
     */
    public UserAgentDevice() {
    }

    /**
     * The manufacturer or brand of the device (e.g., "Apple", "Samsung", "Google").
     * Identifies the company that manufactured the device hardware.
     */
    @JsonProperty("brand")
    private String brand;

    /**
     * The specific model name or identifier of the device (e.g., "iPhone 12", "Galaxy S21", "Pixel 5").
     * Provides the specific device model for detailed identification.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The category or type of device (e.g., "smartphone", "tablet", "desktop", "smart tv").
     * Classifies the device into broad categories for easier handling.
     */
    @JsonProperty("type")
    private String type;

}
