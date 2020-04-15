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

/**
 * Enum type used by @{@link InMemoryCache} to specify with which type of memory reference values are stored.
 */
public enum ValuesReferenceType {

    /**
     * Wraps values in soft references.
     * <p>
     * Softly referenced objects are garbage-collected in a globally least-recently-used manner,
     * in response to memory demand. Because of the performance implications of using soft references,
     * we generally recommend using the more predictable maximum cache size instead.
     * <p>
     * Use of softValues() will cause values to be compared using identity (==) equality instead of equals().
     */
    SOFT,
    /**
     * Stores values using strong references.
     */
    STRONG,
    /**
     * Stores values using weak references.
     * <p>
     * This allows entries to be garbage-collected if there are no other (strong or soft) references to the values.
     * Since garbage collection depends only on identity equality, this causes the whole cache to use identity (==)
     * equality to compare values, instead of equals().
     */
    WEAK

}