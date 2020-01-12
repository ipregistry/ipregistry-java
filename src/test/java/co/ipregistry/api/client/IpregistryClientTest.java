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


import co.ipregistry.api.client.cache.DefaultCache;
import co.ipregistry.api.client.cache.NoCache;
import co.ipregistry.api.client.cache.IpregistryCache;
import co.ipregistry.api.client.exceptions.IpregistryException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.IpInfoList;
import co.ipregistry.api.client.request.DefaultRequestHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class IpregistryClientTest {

    @Test
    void testCachingDisabledByDefault() {
        IpregistryClient client = new IpregistryClient("test");
        Assertions.assertSame(client.getCache(), NoCache.getInstance());
    }

    @Test
    void testSingleIpRequestUseCacheIfAvailable() throws IpregistryException {
        IpregistryConfig config =
                IpregistryConfig.builder()
                        .apiKey("test").build();

        IpregistryCache cache = Mockito.spy(new DefaultCache());
        DefaultRequestHandler requestHandler = Mockito.spy(new DefaultRequestHandler(config));
        IpregistryClient client = new IpregistryClient(config, cache, requestHandler);

        IpInfo cachedIpdata = new IpInfo();

        cache.put("8.8.8.8", cachedIpdata);

        IpInfo ipdata = client.lookup("8.8.8.8");

        Mockito.verify(cache).get("8.8.8.8");
        Mockito.verifyNoInteractions(requestHandler);

        Assertions.assertSame(cachedIpdata, ipdata);
    }

    @Test
    void testSingleIpRequestCallApiEndpointIfCacheMiss() throws IpregistryException {
        IpregistryConfig config =
                IpregistryConfig.builder()
                        .apiKey("test").build();

        IpregistryCache cache = Mockito.spy(new DefaultCache());
        DefaultRequestHandler requestHandler = Mockito.spy(new DefaultRequestHandler(config));

        IpInfo ipdata = new IpInfo();

        Mockito.doReturn(ipdata).when(requestHandler).lookup("8.8.8.8");

        IpregistryClient client = new IpregistryClient(config, cache, requestHandler);

        IpInfo ipdataLookupResponse = client.lookup("8.8.8.8");

        Mockito.verify(cache).put(Mockito.anyString(), Mockito.eq(ipdata));
        Mockito.verify(cache).get(Mockito.anyString());
        Mockito.verify(requestHandler).lookup("8.8.8.8");

        Assertions.assertSame(ipdata, ipdataLookupResponse);
    }

    @Test
    void testBatchRequestUseCache() throws IpregistryException {
        IpregistryConfig config =
                IpregistryConfig.builder()
                        .apiKey("test").build();

        IpregistryCache cache = Mockito.spy(new DefaultCache());
        DefaultRequestHandler requestHandler = Mockito.spy(new DefaultRequestHandler(config));

        IpInfo ipdata1111 = new IpInfo();
        IpInfo ipdata8888 = new IpInfo();
        IpInfo ipdata8844 = new IpInfo();

        cache.put("1.1.1.1", ipdata1111);

        IpregistryClient client = new IpregistryClient(config, cache, requestHandler);

        List<String> ips = Arrays.asList("8.8.8.8", "1.1.1.1", "8.8.4.4");
        Mockito.doReturn(new IpInfoList(new Object[]{ipdata8888, ipdata8844}))
                .when(requestHandler).lookup(Mockito.anyList());

        Assertions.assertSame(ipdata1111, cache.get("1.1.1.1"));
        Assertions.assertNull(cache.get("8.8.8.8"));
        Assertions.assertNull(cache.get("8.8.4.4"));

        IpInfoList batchLookupResponse = client.lookup(ips);

        Assertions.assertEquals(new IpInfoList(new Object[]{ipdata8888, ipdata1111, ipdata8844}), batchLookupResponse);
        Assertions.assertEquals(3, batchLookupResponse.size());
        Assertions.assertSame(ipdata8888, batchLookupResponse.get(0));
        Assertions.assertSame(ipdata1111, batchLookupResponse.get(1));
        Assertions.assertSame(ipdata8844, batchLookupResponse.get(2));

        Mockito.verify(cache, Mockito.times(6)).get(Mockito.anyString());
        Mockito.verify(cache, Mockito.times(3)).put(Mockito.anyString(), Mockito.any(IpInfo.class));
        Mockito.verify(requestHandler).lookup(Mockito.anyList());
        Mockito.verify(requestHandler, Mockito.never()).lookup(Mockito.anyString());
    }

}
