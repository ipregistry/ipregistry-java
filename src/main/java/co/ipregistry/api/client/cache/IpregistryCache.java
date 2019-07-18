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

package co.ipregistry.api.client.cache;

import co.ipregistry.api.client.model.IpInfo;

public interface IpregistryCache {

    /**
     * Returns the value associated with {@code key} in this cache, or {@code null} if there is no
     * cached value for {@code key}.
     *
     * @param key a key.
     * @return the data found or {@code null} if no entry is present in the cache.
     */
    IpInfo get(String key);

    /**
     * Adds the specified {@link IpInfo} to the cache using the given {@code key}.
     *
     * @param key  a key.
     * @param data the data to cache.
     */
    void put(String key, IpInfo data);

    /**
     * Removes the data cached for the specified {@code key} from the cache.
     *
     * @param key a key.
     */
    void invalidate(String key);

    /**
     * Clears the cache content (i.e. remove all entries).
     */
    void invalidateAll();

}
