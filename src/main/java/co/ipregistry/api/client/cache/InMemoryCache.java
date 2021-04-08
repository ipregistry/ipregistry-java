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
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Builder;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class InMemoryCache implements IpregistryCache {

    private final Cache<String, IpInfo> cache;

    @Builder
    public InMemoryCache() {
        this(
                Runtime.getRuntime().availableProcessors(),
                Duration.ofMinutes(10).toMillis(),
                1024,
                4096,
                ValuesReferenceType.STRONG);
    }

    /**
     * Constructs a new {@code InMemoryCache} instance with the specified properties.
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
    protected InMemoryCache(
            final int concurrencyLevel,
            final long expireAfter,
            final int initialCapacity,
            final int maximumSize,
            final ValuesReferenceType referenceType) {

        final CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.concurrencyLevel(concurrencyLevel);
        cacheBuilder.initialCapacity(initialCapacity);
        cacheBuilder.expireAfterWrite(expireAfter, TimeUnit.MILLISECONDS);
        cacheBuilder.maximumSize(maximumSize);

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

    public IpInfo get(final String key) {
        return this.cache.getIfPresent(key);
    }

    public void invalidate(final String key) {
        this.cache.invalidate(key);
    }

    public void invalidateAll() {
        this.cache.invalidateAll();
    }

    public void put(final String key, final IpInfo response) {
        this.cache.put(key, response);
    }

}
