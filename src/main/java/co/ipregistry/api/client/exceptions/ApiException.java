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
 * Base class for all API related exceptions.
 */
@Getter
public class ApiException extends IpregistryException {

    /**
     * The API exception code.
     */
    private final String code;

    /**
     * The API exception message.
     */
    private final String message;

    /**
     * The API exception resolution description.
     */
    private final String resolution;


    /**
     * Creates a new instance.
     *
     * @param errorCode an error code as described in our docs.
     * @param message a message describing the error.
     * @param resolution explanation about how to fix the error.
     *
     * @see <a href="https://ipregistry.co/docs/errors">https://ipregistry.co/docs/errors</a>.
     */
    public ApiException(final String errorCode, final String message, final String resolution) {
        super(message);
        this.code = errorCode;
        this.message = message;
        this.resolution = resolution;
    }

}
