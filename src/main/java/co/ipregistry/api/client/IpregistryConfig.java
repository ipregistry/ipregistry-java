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

package co.ipregistry.api.client;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class IpregistryConfig {

    /**
     * The IpregistryClient API key used to authenticate calls.
     */
    @NonNull
    private String apiKey;

    /**
     * The IpregistryClient API endpoint URL.
     */
    @Builder.Default
    private String apiUrl = "https://api.ipregistry.co";

    /**
     * The time to wait in milliseconds until a connection is established.
     */
    @Builder.Default
    private int connectionTimeout = 1000;

    /**
     * The time to wait for data in milliseconds.
     * Said differently, the maximum period inactivity of between two consecutive data packets.
     */
    @Builder.Default
    private int socketTimeout = 1000;

}
