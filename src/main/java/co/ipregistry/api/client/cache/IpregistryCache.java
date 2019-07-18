/*
 * Copyright 2019 IpregistryClient (https://ipregistry.co).
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

package co.ipregistry.api.client.cache;

import co.ipregistry.api.client.model.IpInfo;

public interface IpregistryCache {

    /**
     * Returns the value associated with {@code ip} in this cache, or {@code null} if there is no
     * cached value for {@code ip}.
     *
     * @param ip An IP address using the dotted-decimal or the colon-hexadecimal notation.
     * @return the data found or {@code null} if no entry is present in the cache.
     */
    IpInfo get(String ip);

    /**
     * Adds the specified {@link IpInfo} to the cache using the given {@code ip} as key.
     *
     * @param ip   An IP address using the dotted-decimal or the colon-hexadecimal notation.
     * @param data the data to cache.
     */
    void put(String ip, IpInfo data);

    /**
     * Removes the data cached for the specified {@code ip} from the cache.
     *
     * @param ip An IPv4 address using the dotted-decimal syntax or
     *           an IPv6 address formatted with the colon-hexadecimal notation.
     */
    void invalidate(String ip);

    /**
     * Clears the cache content (i.e. remove all entries).
     */
    void invalidateAll();

}
