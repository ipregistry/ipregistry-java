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

package co.ipregistry.api.client.exceptions;

import lombok.Getter;


/**
 * UserAgent exception.
 */
@Getter
public class UserAgentException extends ApiException {

    /**
     * Creates a new instance with the specified arguments.
     *
     * @param errorCode the error code.
     * @param message the message describing the error.
     * @param resolution a sentence explaining how to resolve the error.
     */
    public UserAgentException(final String errorCode, final String message, final String resolution) {
        super(errorCode, message, resolution);
    }

}
