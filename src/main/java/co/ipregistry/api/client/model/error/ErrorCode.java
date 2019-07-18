/*
 * Copyright 2019 IpregistryClient (https://ipregistry.co).
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

public enum ErrorCode {

    DISABLED_API_KEY,
    FORBIDDEN_IP,
    FORBIDDEN_ORIGIN,
    INTERNAL,
    INSUFFICIENT_CREDITS,
    INVALID_API_KEY,
    INVALID_FILTER_SYNTAX,
    INVALID_IP_ADDRESS,
    MISSING_API_KEY,
    PRIVATE_IP_ADDRESS,
    TOO_MANY_IPS,
    TOO_MANY_REQUESTS

}
