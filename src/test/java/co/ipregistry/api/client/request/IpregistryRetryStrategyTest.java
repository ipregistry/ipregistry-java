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

import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.message.BasicHttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

class IpregistryRetryStrategyTest {

    private static HttpResponse response(final int code) {
        return new BasicHttpResponse(code);
    }

    @Test
    void testRetriesOnServerErrorWhenEnabled() {
        final IpregistryRetryStrategy strategy = new IpregistryRetryStrategy(3, 1000, true, false);
        Assertions.assertTrue(strategy.retryRequest(response(500), 1, null));
        Assertions.assertTrue(strategy.retryRequest(response(503), 1, null));
        Assertions.assertTrue(strategy.retryRequest(response(599), 1, null));
    }

    @Test
    void testDoesNotRetryServerErrorWhenDisabled() {
        final IpregistryRetryStrategy strategy = new IpregistryRetryStrategy(3, 1000, false, false);
        Assertions.assertFalse(strategy.retryRequest(response(500), 1, null));
        Assertions.assertFalse(strategy.retryRequest(response(503), 1, null));
    }

    @Test
    void testRetriesTooManyRequestsOnlyWhenEnabled() {
        Assertions.assertFalse(new IpregistryRetryStrategy(3, 1000, true, false)
                .retryRequest(response(429), 1, null));
        Assertions.assertTrue(new IpregistryRetryStrategy(3, 1000, true, true)
                .retryRequest(response(429), 1, null));
    }

    @Test
    void testDoesNotRetryClientErrorsOrSuccess() {
        final IpregistryRetryStrategy strategy = new IpregistryRetryStrategy(3, 1000, true, true);
        Assertions.assertFalse(strategy.retryRequest(response(200), 1, null));
        Assertions.assertFalse(strategy.retryRequest(response(400), 1, null));
        Assertions.assertFalse(strategy.retryRequest(response(401), 1, null));
        Assertions.assertFalse(strategy.retryRequest(response(404), 1, null));
    }

    @Test
    void testStopsAfterMaxAttempts() {
        final IpregistryRetryStrategy strategy = new IpregistryRetryStrategy(2, 1000, true, true);
        Assertions.assertTrue(strategy.retryRequest(response(500), 1, null));
        Assertions.assertTrue(strategy.retryRequest(response(500), 2, null));
        Assertions.assertFalse(strategy.retryRequest(response(500), 3, null));
    }

    @Test
    void testRetriesTransientIoExceptionButNotPermanentOnes() {
        final IpregistryRetryStrategy strategy = new IpregistryRetryStrategy(3, 1000, true, false);
        Assertions.assertTrue(strategy.retryRequest(null, new IOException("reset"), 1, null));

        Assertions.assertFalse(strategy.retryRequest(null, new SocketTimeoutException(), 1, null));
        Assertions.assertFalse(strategy.retryRequest(null, new UnknownHostException(), 1, null));
        Assertions.assertFalse(strategy.retryRequest(null, new ConnectException(), 1, null));
        Assertions.assertFalse(strategy.retryRequest(null, new SSLException("bad"), 1, null));

        Assertions.assertFalse(strategy.retryRequest(null, new IOException("reset"), 4, null));
    }

    @Test
    void testExponentialBackoff() {
        final IpregistryRetryStrategy strategy = new IpregistryRetryStrategy(5, 1000, true, true);
        Assertions.assertEquals(1000, strategy.getRetryInterval(response(500), 1, null).toMilliseconds());
        Assertions.assertEquals(2000, strategy.getRetryInterval(response(500), 2, null).toMilliseconds());
        Assertions.assertEquals(4000, strategy.getRetryInterval(response(500), 3, null).toMilliseconds());
    }

    @Test
    void testRetryAfterHeaderTakesPrecedence() {
        final IpregistryRetryStrategy strategy = new IpregistryRetryStrategy(5, 1000, true, true);

        final HttpResponse withRetryAfter = response(429);
        withRetryAfter.setHeader("Retry-After", "5");
        Assertions.assertEquals(5000, strategy.getRetryInterval(withRetryAfter, 1, null).toMilliseconds());

        // A non-numeric (HTTP-date) Retry-After is not honored and falls back to exponential backoff.
        final HttpResponse httpDate = response(429);
        httpDate.setHeader("Retry-After", "Wed, 21 Oct 2026 07:28:00 GMT");
        Assertions.assertEquals(1000, strategy.getRetryInterval(httpDate, 1, null).toMilliseconds());
    }

}
