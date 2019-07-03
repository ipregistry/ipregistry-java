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

import co.ipregistry.api.client.cache.EmptyCache;
import co.ipregistry.api.client.cache.IpregistryCache;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpData;
import co.ipregistry.api.client.model.IpDataList;
import co.ipregistry.api.client.model.RequesterIpData;
import co.ipregistry.api.client.options.IpregistryOption;
import co.ipregistry.api.client.request.DefaultRequestHandler;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Ipregistry {

    private final IpregistryConfig config;

    private final IpregistryCache cache;

    private final DefaultRequestHandler requestHandler;


    public Ipregistry(String apiKey) {
        this(IpregistryConfig.builder().apiKey(apiKey).build());
    }

    public Ipregistry(IpregistryConfig config) {
        this(config, EmptyCache.getInstance());
    }

    public Ipregistry(IpregistryConfig config, IpregistryCache cache) {
        this(config, cache, new DefaultRequestHandler(config));
    }

    public Ipregistry(IpregistryConfig config, IpregistryCache cache, DefaultRequestHandler requestHandler) {
        this.config = config;
        this.cache = cache;
        this.requestHandler = requestHandler;
    }

    public IpregistryCache getCache() {
        return cache;
    }

    public RequesterIpData lookup(IpregistryOption... options) throws ApiException, ClientException {
        return (RequesterIpData) lookup((String) null, options);
    }

    public IpData lookup(InetAddress ip, IpregistryOption... options) throws ApiException, ClientException {
        return lookup(ip.toString(), options);
    }

    public IpData lookup(String ip, IpregistryOption... options) throws ApiException, ClientException {
        IpData found = this.cache.get(ip);

        if (found != null) {
            return found;
        }

        found = requestHandler.lookup(ip, options);

        cache.put(found.getIp(), found);

        return found;
    }

    public IpDataList lookup(List<String> ips, IpregistryOption... options) throws ApiException, ClientException {
        IpData[] sparseCache = new IpData[ips.size()];
        List<String> cacheMisses = new ArrayList<>(ips.size());

        for (int i = 0; i < ips.size(); i++) {
            String ip = ips.get(i);
            IpData found = cache.get(ip);

            if (found != null) {
                sparseCache[i] = found;
            } else {
                cacheMisses.add(ip);
            }
        }

        IpDataList freshIpData = requestHandler.lookup(cacheMisses, options);

        Object[] result = new Object[ips.size()];

        int i = 0;
        int j = 0;

        for (IpData cachedIpData : sparseCache) {
            if (cachedIpData == null) {
                result[i] = freshIpData.unsafeGet(j);

                if (result[i] instanceof IpData) {
                    IpData ipdata = (IpData) result[i];
                    cache.put(ipdata.getIp(), ipdata);
                }

                j++;
            } else {
                result[i] = cachedIpData;
            }

            i++;
        }

        return new IpDataList(result);
    }

}
