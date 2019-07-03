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
import co.ipregistry.api.client.cache.EmptyCache;
import co.ipregistry.api.client.cache.IpregistryCache;
import co.ipregistry.api.client.exceptions.IpregistryException;
import co.ipregistry.api.client.model.IpData;
import co.ipregistry.api.client.model.IpDataList;
import co.ipregistry.api.client.request.DefaultRequestHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class IpregistryTest {

    @Test
    void testCachingDisabledByDefault() {
        Ipregistry client = new Ipregistry("test");
        Assertions.assertSame(client.getCache(), EmptyCache.getInstance());
    }

    @Test
    void testSingleIpRequestUseCacheIfAvailable() throws IpregistryException {
        IpregistryConfig config =
                IpregistryConfig.builder()
                        .apiKey("test").build();

        IpregistryCache cache = Mockito.spy(new DefaultCache());
        DefaultRequestHandler requestHandler = Mockito.spy(new DefaultRequestHandler(config));
        Ipregistry client = new Ipregistry(config, cache, requestHandler);

        IpData cachedIpdata = new IpData();

        cache.put("8.8.8.8", cachedIpdata);

        IpData ipdata = client.lookup("8.8.8.8");

        Mockito.verify(cache).get("8.8.8.8");
        Mockito.verifyZeroInteractions(requestHandler);

        Assertions.assertSame(cachedIpdata, ipdata);
    }

    @Test
    void testSingleIpRequestCallApiEndpointIfCacheMiss() throws IpregistryException {
        IpregistryConfig config =
                IpregistryConfig.builder()
                        .apiKey("test").build();

        IpregistryCache cache = Mockito.spy(new DefaultCache());
        DefaultRequestHandler requestHandler = Mockito.spy(new DefaultRequestHandler(config));

        IpData ipdata = new IpData();

        Mockito.doReturn(ipdata).when(requestHandler).lookup("8.8.8.8");

        Ipregistry client = new Ipregistry(config, cache, requestHandler);

        IpData ipdataLookupResponse = client.lookup("8.8.8.8");

        Mockito.verify(cache).put("8.8.8.8", ipdata);
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

        IpData ipdata1111 = new IpData();
        IpData ipdata8888 = new IpData();
        IpData ipdata8844 = new IpData();

        cache.put("1.1.1.1", ipdata1111);

        Ipregistry client = new Ipregistry(config, cache, requestHandler);

        List<String> ips = Arrays.asList("8.8.8.8", "1.1.1.1", "8.8.4.4");
        Mockito.doReturn(new IpDataList(new Object[]{ipdata8888, ipdata8844}))
                .when(requestHandler).lookup(Mockito.anyList());

        Assertions.assertSame(ipdata1111, cache.get("1.1.1.1"));
        Assertions.assertNull(cache.get("8.8.8.8"));
        Assertions.assertNull(cache.get("8.8.4.4"));

        IpDataList batchLookupResponse = client.lookup(ips);

        Assertions.assertEquals(new IpDataList(new Object[]{ipdata8888, ipdata1111, ipdata8844}), batchLookupResponse);
        Assertions.assertEquals(3, batchLookupResponse.size());
        Assertions.assertSame(ipdata8888, batchLookupResponse.get(0));
        Assertions.assertSame(ipdata1111, batchLookupResponse.get(1));
        Assertions.assertSame(ipdata8844, batchLookupResponse.get(2));

        Mockito.verify(cache, Mockito.times(6)).get(Mockito.anyString());
        Mockito.verify(cache, Mockito.times(3)).put(Mockito.anyString(), Mockito.any(IpData.class));
        Mockito.verify(requestHandler).lookup(Mockito.anyList());
        Mockito.verify(requestHandler, Mockito.never()).lookup(Mockito.anyString());
    }

}
