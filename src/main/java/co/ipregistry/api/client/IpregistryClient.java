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

import co.ipregistry.api.client.cache.IpregistryCache;
import co.ipregistry.api.client.cache.NoCache;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.IpInfoList;
import co.ipregistry.api.client.model.RequesterIpInfo;
import co.ipregistry.api.client.options.IpregistryOption;
import co.ipregistry.api.client.request.DefaultRequestHandler;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class IpregistryClient {

    private final IpregistryConfig config;

    private final IpregistryCache cache;

    private final DefaultRequestHandler requestHandler;


    public IpregistryClient(final String apiKey) {
        this(IpregistryConfig.builder().apiKey(apiKey).build());
    }

    public IpregistryClient(final IpregistryConfig config) {
        this(config, NoCache.getInstance());
    }

    public IpregistryClient(final IpregistryConfig config, final IpregistryCache cache) {
        this(config, cache, new DefaultRequestHandler(config));
    }

    public IpregistryClient(final IpregistryConfig config, final IpregistryCache cache, final DefaultRequestHandler requestHandler) {
        this.config = config;
        this.cache = cache;
        this.requestHandler = requestHandler;
    }

    public IpregistryCache getCache() {
        return cache;
    }

    public RequesterIpInfo lookup(final IpregistryOption... options) throws ApiException, ClientException {
        return (RequesterIpInfo) lookup("", options);
    }

    public IpInfo lookup(final InetAddress ip, final IpregistryOption... options) throws ApiException, ClientException {
        return lookup(ip.getHostAddress(), options);
    }

    public IpInfo lookup(final String ip, final IpregistryOption... options) throws ApiException, ClientException {
        final String cacheKey = buildCacheKey(ip, options);
        IpInfo cacheValue = this.cache.get(cacheKey);

        if (cacheValue != null) {
            return cacheValue;
        }

        cacheValue = requestHandler.lookup(ip, options);

        cache.put(cacheKey, cacheValue);

        return cacheValue;
    }

    private String buildCacheKey(final String ip, final IpregistryOption... options) {
        final StringBuilder buffer = new StringBuilder();
        buffer.append(ip);

        if (options != null) {
            for (final IpregistryOption option : options) {
                buffer.append(';');
                buffer.append(option.getName());
                buffer.append('=');
                buffer.append(option.getValue());
            }
        }

        return buffer.toString();
    }

    public IpInfoList lookup(final List<String> ips, final IpregistryOption... options) throws ApiException, ClientException {
        final IpInfo[] sparseCache = new IpInfo[ips.size()];
        final List<String> cacheMisses = new ArrayList<>(ips.size());

        for (int i = 0; i < ips.size(); i++) {
            final String ip = ips.get(i);
            final String cacheKey = buildCacheKey(ip, options);
            final IpInfo cacheValue = cache.get(cacheKey);

            if (cacheValue != null) {
                sparseCache[i] = cacheValue;
            } else {
                cacheMisses.add(ip);
            }
        }

        final IpInfoList freshIpData = requestHandler.lookup(cacheMisses, options);

        final Object[] result = new Object[ips.size()];

        int i = 0;
        int j = 0;

        for (final IpInfo cachedIpInfo : sparseCache) {
            if (cachedIpInfo == null) {
                result[i] = freshIpData.unsafeGet(j);

                if (result[i] instanceof IpInfo) {
                    final IpInfo ipdata = (IpInfo) result[i];
                    cache.put(buildCacheKey(ipdata.getIp(), options), ipdata);
                }

                j++;
            } else {
                result[i] = cachedIpInfo;
            }

            i++;
        }

        return new IpInfoList(result);
    }

}
