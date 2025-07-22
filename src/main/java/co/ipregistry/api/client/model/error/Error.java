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

package co.ipregistry.api.client.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents error information returned by the Ipregistry API.
 * <p>
 * This class contains structured error data including error codes, human-readable
 * messages, and suggested resolutions. It is primarily used in bulk operations
 * where individual IP address lookups may fail while others succeed.
 * </p>
 */
@Data
public class Error {

    /**
     * Creates a new Error instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Error() {
    }

    /**
     * The error code identifying the specific type of error that occurred.
     * Error codes provide programmatic identification of error conditions for proper handling.
     */
    @JsonProperty("code")
    private String code;

    /**
     * A human-readable error message describing what went wrong.
     * Provides detailed information about the error for debugging and user display.
     */
    @JsonProperty("message")
    private String message;

    /**
     * Suggested resolution or corrective action to fix the error.
     * Provides guidance on how to resolve the issue that caused the error.
     */
    @JsonProperty("resolution")
    private String resolution;

}
