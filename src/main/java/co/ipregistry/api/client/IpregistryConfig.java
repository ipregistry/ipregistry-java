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
     * @param retryMaxAttempts the maximum number of automatic retries
     * @param retryInterval the base backoff interval, in milliseconds
     * @param retryOnServerError whether to retry on 5xx server errors
     * @param retryOnTooManyRequests whether to retry on 429 Too Many Requests
     */
    public IpregistryConfig(String apiKey, String baseUrl, int connectionKeepAlive, int connectionTimeout, int socketTimeout,
                            int retryMaxAttempts, long retryInterval, boolean retryOnServerError, boolean retryOnTooManyRequests) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.connectionKeepAlive = connectionKeepAlive;
        this.connectionTimeout = connectionTimeout;
        this.socketTimeout = socketTimeout;
        this.retryMaxAttempts = retryMaxAttempts;
        this.retryInterval = retryInterval;
        this.retryOnServerError = retryOnServerError;
        this.retryOnTooManyRequests = retryOnTooManyRequests;
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

    /**
     * The maximum number of times a failed request is automatically retried (in addition to the
     * initial attempt). Set to {@code 0} to disable retries entirely. Defaults to 3.
     */
    @Builder.Default
    private final int retryMaxAttempts = 3;

    /**
     * The base backoff interval in milliseconds used between retries. Successive retries use an
     * exponentially increasing delay ({@code retryInterval * 2^(attempt - 1)}). When a 429 response
     * carries a {@code Retry-After} header, that value takes precedence. Defaults to 1000 milliseconds.
     */
    @Builder.Default
    private final long retryInterval = 1000;

    /**
     * Whether requests failing with a 5xx server error (or a transient network error) should be
     * retried automatically. Defaults to {@code true}.
     */
    @Builder.Default
    private final boolean retryOnServerError = true;

    /**
     * Whether requests failing with a 429 Too Many Requests response should be retried automatically.
     * Ipregistry does not rate limit by default (it is opt-in per API key), so a 429 usually reflects
     * a deliberately configured limit; retrying is therefore disabled by default. Defaults to {@code false}.
     */
    @Builder.Default
    private final boolean retryOnTooManyRequests = false;

}
