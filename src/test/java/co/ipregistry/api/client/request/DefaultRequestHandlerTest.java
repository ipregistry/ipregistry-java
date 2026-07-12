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

import co.ipregistry.api.client.IpregistryConfig;
import co.ipregistry.api.client.options.IpregistryOption;
import tools.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

class DefaultRequestHandlerTest {

    @Test
    void testBuildApiUrl_optionsEncoded() {
        final IpregistryConfig config =
                IpregistryConfig.builder().apiKey("test").build();
        final DefaultRequestHandler requestHandler =
                new DefaultRequestHandler(config);
        final String url =
                requestHandler.buildIpLookupUrl(
                        "8.8.8.8", new IpregistryOption("test", "[test]"));
        Assertions.assertEquals(config.getBaseUrl() + "/8.8.8.8?test=%5Btest%5D", url);
    }

    @Test
    void testToJsonListEscapesSpecialCharacters() throws Exception {
        final IpregistryConfig config =
                IpregistryConfig.builder().apiKey("test").build();
        final DefaultRequestHandler requestHandler =
                new DefaultRequestHandler(config);

        final String[] values = {
                "Mozilla/5.0 \"quoted\"",
                "back\\slash",
                "line\nbreak",
                "comma,and]bracket"
        };

        final String json = requestHandler.toJsonList(Arrays.asList(values));

        // The produced body must be valid JSON that round-trips to the exact input values.
        // The previous string-concatenation implementation produced malformed JSON for these inputs.
        final String[] parsed = new ObjectMapper().readValue(json, String[].class);
        Assertions.assertArrayEquals(values, parsed);
    }

    @Test
    void testInjectedHttpClientIsNotClosed() throws Exception {
        final IpregistryConfig config =
                IpregistryConfig.builder().apiKey("test").build();
        final CloseableHttpClient httpClient = Mockito.mock(CloseableHttpClient.class);

        final DefaultRequestHandler requestHandler = new DefaultRequestHandler(config, httpClient);
        requestHandler.close();

        // A caller-provided client remains owned by the caller and must not be closed by the handler.
        Mockito.verify(httpClient, Mockito.never()).close();
    }

}
