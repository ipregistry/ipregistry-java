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

package co.ipregistry.api.client;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;


/**
 * Configuration settings for the Ipregistry API client.
 * <p>
 * This class provides configuration options for customizing the behavior of the
 * {@link IpregistryClient}, including API authentication, endpoint URLs, and
 * network timeout settings. Use the builder pattern to create instances with
 * custom configuration values.
 * </p>
 * <p>
 * Instances are created using the Builder pattern. The class uses Lombok's {@code @Builder} 
 * annotation to generate a builder with proper constructor handling for required and optional fields.
 * </p>
 */
@Builder
@Getter
public class IpregistryConfig {

    /**
     * Creates a new IpregistryConfig with the specified parameters.
     * This constructor is used internally by the Builder pattern.
     *
     * @param apiKey the API key for authentication
     * @param baseUrl the base URL for the API
     * @param connectionKeepAlive connection keep-alive timeout
     * @param connectionTimeout connection timeout
     * @param socketTimeout socket timeout
     */
    public IpregistryConfig(String apiKey, String baseUrl, int connectionKeepAlive, int connectionTimeout, int socketTimeout) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.connectionKeepAlive = connectionKeepAlive;
        this.connectionTimeout = connectionTimeout;
        this.socketTimeout = socketTimeout;
    }

    /**
     * The API key used to authenticate requests to the Ipregistry API.
     * This key is required and must be obtained from your Ipregistry account dashboard.
     */
    @NonNull
    private final String apiKey;

    /**
     * The base URL for the Ipregistry API endpoint.
     * Defaults to "https://api.ipregistry.co" but can be customized for testing or private deployments.
     */
    @Builder.Default
    private String baseUrl = "https://api.ipregistry.co";

    /**
     * The maximum time in milliseconds that HTTP connections should be kept alive for reuse.
     * Keeping connections alive reduces overhead by reusing existing connections for multiple requests.
     * Defaults to 15 minutes (900,000 milliseconds).
     */
    @Builder.Default
    private final int connectionKeepAlive = 15 * 60 * 1000;

    /**
     * The maximum time in milliseconds to wait when establishing a connection to the server.
     * If a connection cannot be established within this time, the request will fail.
     * Defaults to 15 seconds (15,000 milliseconds).
     */
    @Builder.Default
    private final int connectionTimeout = 15000;

    /**
     * The maximum time in milliseconds to wait for data between consecutive data packets.
     * This is the socket read timeout, which determines how long to wait for the server
     * to send data after the connection is established. Defaults to 15 seconds (15,000 milliseconds).
     */
    @Builder.Default
    private final int socketTimeout = 15000;

}
