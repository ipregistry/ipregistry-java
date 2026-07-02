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

package co.ipregistry.api.client.request;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;


/**
 * Retry strategy used by the Ipregistry HTTP client.
 * <p>
 * Retries are attempted, up to a configurable maximum, in the following cases:
 * <ul>
 *     <li>transient network failures ({@link IOException}s that are not clearly permanent);</li>
 *     <li>5xx server error responses, when enabled;</li>
 *     <li>429 Too Many Requests responses, when enabled.</li>
 * </ul>
 * Retries use an exponential backoff based on a configurable interval. For 429 responses carrying a
 * {@code Retry-After} header expressed in seconds, that value takes precedence over the computed backoff.
 */
public class IpregistryRetryStrategy implements HttpRequestRetryStrategy {

    /**
     * Exceptions that are considered permanent and therefore never retried.
     */
    private static final List<Class<? extends IOException>> NON_RETRIABLE_EXCEPTIONS = Arrays.asList(
            InterruptedIOException.class,
            UnknownHostException.class,
            ConnectException.class,
            NoRouteToHostException.class,
            SSLException.class);

    private final int maxAttempts;

    private final long intervalMillis;

    private final boolean retryOnServerError;

    private final boolean retryOnTooManyRequests;


    /**
     * Creates a new retry strategy.
     *
     * @param maxAttempts            the maximum number of retries (in addition to the initial attempt).
     * @param intervalMillis         the base backoff interval, in milliseconds.
     * @param retryOnServerError     whether to retry on 5xx server errors.
     * @param retryOnTooManyRequests whether to retry on 429 Too Many Requests responses.
     */
    public IpregistryRetryStrategy(final int maxAttempts, final long intervalMillis,
                                   final boolean retryOnServerError, final boolean retryOnTooManyRequests) {
        this.maxAttempts = maxAttempts;
        this.intervalMillis = intervalMillis;
        this.retryOnServerError = retryOnServerError;
        this.retryOnTooManyRequests = retryOnTooManyRequests;
    }

    @Override
    public boolean retryRequest(final HttpRequest request, final IOException exception,
                                final int execCount, final HttpContext context) {
        if (execCount > maxAttempts) {
            return false;
        }

        for (final Class<? extends IOException> nonRetriable : NON_RETRIABLE_EXCEPTIONS) {
            if (nonRetriable.isInstance(exception)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean retryRequest(final HttpResponse response, final int execCount, final HttpContext context) {
        if (execCount > maxAttempts) {
            return false;
        }

        final int code = response.getCode();

        if (code == HttpStatus.SC_TOO_MANY_REQUESTS) {
            return retryOnTooManyRequests;
        }

        if (code >= 500 && code < 600) {
            return retryOnServerError;
        }

        return false;
    }

    @Override
    public TimeValue getRetryInterval(final HttpResponse response, final int execCount, final HttpContext context) {
        final Header retryAfter = response.getFirstHeader(HttpHeaders.RETRY_AFTER);

        if (retryAfter != null && retryAfter.getValue() != null) {
            try {
                final long seconds = Long.parseLong(retryAfter.getValue().trim());
                if (seconds >= 0) {
                    return TimeValue.ofSeconds(seconds);
                }
            } catch (final NumberFormatException ignored) {
                // Retry-After expressed as an HTTP date is not honored; fall back to exponential backoff.
            }
        }

        return exponentialBackoff(execCount);
    }

    private TimeValue exponentialBackoff(final int execCount) {
        final int shift = Math.min(Math.max(execCount - 1, 0), 30);
        return TimeValue.ofMilliseconds(intervalMillis * (1L << shift));
    }

}
