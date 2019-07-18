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

import co.ipregistry.api.client.cache.NoCache;
import co.ipregistry.api.client.cache.IpregistryCache;
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


    public IpregistryClient(String apiKey) {
        this(IpregistryConfig.builder().apiKey(apiKey).build());
    }

    public IpregistryClient(IpregistryConfig config) {
        this(config, NoCache.getInstance());
    }

    public IpregistryClient(IpregistryConfig config, IpregistryCache cache) {
        this(config, cache, new DefaultRequestHandler(config));
    }

    public IpregistryClient(IpregistryConfig config, IpregistryCache cache, DefaultRequestHandler requestHandler) {
        this.config = config;
        this.cache = cache;
        this.requestHandler = requestHandler;
    }

    public IpregistryCache getCache() {
        return cache;
    }

    public RequesterIpInfo lookup(IpregistryOption... options) throws ApiException, ClientException {
        return (RequesterIpInfo) lookup("", options);
    }

    public IpInfo lookup(InetAddress ip, IpregistryOption... options) throws ApiException, ClientException {
        return lookup(ip.toString(), options);
    }

    public IpInfo lookup(String ip, IpregistryOption... options) throws ApiException, ClientException {
        IpInfo found = this.cache.get(ip);

        if (found != null) {
            return found;
        }

        found = requestHandler.lookup(ip, options);

        cache.put(found.getIp(), found);

        return found;
    }

    public IpInfoList lookup(List<String> ips, IpregistryOption... options) throws ApiException, ClientException {
        IpInfo[] sparseCache = new IpInfo[ips.size()];
        List<String> cacheMisses = new ArrayList<>(ips.size());

        for (int i = 0; i < ips.size(); i++) {
            String ip = ips.get(i);
            IpInfo found = cache.get(ip);

            if (found != null) {
                sparseCache[i] = found;
            } else {
                cacheMisses.add(ip);
            }
        }

        IpInfoList freshIpData = requestHandler.lookup(cacheMisses, options);

        Object[] result = new Object[ips.size()];

        int i = 0;
        int j = 0;

        for (IpInfo cachedIpInfo : sparseCache) {
            if (cachedIpInfo == null) {
                result[i] = freshIpData.unsafeGet(j);

                if (result[i] instanceof IpInfo) {
                    IpInfo ipdata = (IpInfo) result[i];
                    cache.put(ipdata.getIp(), ipdata);
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
