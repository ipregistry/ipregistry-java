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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultRequestHandlerTest {

    @Test
    void testBuildApiUrl_optionsEncoded() {
        final IpregistryConfig config =
                IpregistryConfig.builder().apiKey("test").build();
        final DefaultRequestHandler requestHandler =
                new DefaultRequestHandler(
                        config);
        final String url =
                requestHandler.buildApiUrl(
                        "8.8.8.8", new IpregistryOption("test", "[test]"));
        Assertions.assertEquals(config.getApiUrl() + "/8.8.8.8?key=test&test=%5Btest%5D", url);
    }

}
