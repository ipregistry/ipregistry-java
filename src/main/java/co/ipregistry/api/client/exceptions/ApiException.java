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

import co.ipregistry.api.client.model.error.ErrorCode;
import lombok.Getter;


/**
 * Base class for all API related exceptions.
 */
@Getter
public class ApiException extends IpregistryException {

    /**
     * The raw API exception code as returned by the API.
     */
    private final String code;

    /**
     * The typed API error code, or {@code null} when {@link #code} does not match any known
     * {@link ErrorCode} (for instance a code introduced after this client release).
     */
    private final ErrorCode errorCode;

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
     * @param code an error code as described in our docs.
     * @param message a message describing the error.
     * @param resolution explanation about how to fix the error.
     *
     * @see <a href="https://ipregistry.co/docs/errors">https://ipregistry.co/docs/errors</a>.
     */
    public ApiException(final String code, final String message, final String resolution) {
        super(message);
        this.code = code;
        this.errorCode = ErrorCode.fromCode(code);
        this.message = message;
        this.resolution = resolution;
    }

}
