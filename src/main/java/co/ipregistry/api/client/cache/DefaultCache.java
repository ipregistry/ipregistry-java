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

import co.ipregistry.api.client.model.IpData;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Builder;

import java.util.concurrent.TimeUnit;

public class DefaultCache implements IpregistryCache {

    private final Cache<String, IpData> cache;

    @Builder
    public DefaultCache() {
        this(
                Runtime.getRuntime().availableProcessors(),
                0,
                1024,
                8096,
                ValuesReferenceType.STRONG);
    }

    /**
     * Constructs a new {@code DefaultCache} instance with the specified properties.
     *
     * @param concurrencyLevel Guides the allowed concurrency among update operations.
     *                         The value is used as a hint for internal sizing
     * @param expireAfter      The time (in milliseconds) after which an entry should be automatically removed from
     *                         the cache once the duration has elapsed after the entry's creation,
     *                         or the most recent replacement of its value.
     * @param initialCapacity  Sets the minimum total size for the internal hash tables.
     * @param maximumSize      Specifies the maximum number of entries the cache may contain.
     *                         Note that the cache <b>may evict an entry before this limit is exceeded</b>.
     * @param referenceType    Specifies how cache values should be wrapped.
     *                         A value other than {@link ValuesReferenceType#STRONG} enables eviction based on
     *                         memory consumption.
     */
    @Builder
    public DefaultCache(
            int concurrencyLevel,
            long expireAfter,
            int initialCapacity,
            int maximumSize,
            ValuesReferenceType referenceType) {

        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.concurrencyLevel(concurrencyLevel);
        cacheBuilder.initialCapacity(initialCapacity);

        if (expireAfter > 0) {
            cacheBuilder.expireAfterWrite(expireAfter, TimeUnit.MILLISECONDS);
        }

        if (maximumSize > 0) {
            cacheBuilder.maximumSize(maximumSize);
        }

        switch (referenceType) {
            case SOFT:
                cacheBuilder.softValues();
                break;
            case WEAK:
                cacheBuilder.weakValues();
                break;
        }

        this.cache = cacheBuilder.build();
    }

    public IpData get(String ip) {
        return this.cache.getIfPresent(ip);
    }

    public void invalidate(String ip) {
        this.cache.invalidate(ip);
    }

    public void invalidateAll() {
        this.cache.invalidateAll();
    }

    public void put(String ip, IpData response) {
        this.cache.put(ip, response);
    }

}
