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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Represents a lookup error that occurred during IP address information retrieval.
 * <p>
 * This class extends {@link IpError} and is used to represent specific errors
 * that occur when attempting to look up information for an IP address. It inherits
 * error details from the parent class and includes the IP address that caused the error.
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LookupError extends IpError {

    /**
     * Creates a new LookupError instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public LookupError() {
    }

}
