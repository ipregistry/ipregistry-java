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


import co.ipregistry.api.client.model.IpData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultCacheTest {

    @Test
    void testGetReturnNullIfCacheEmpty() {
        DefaultCache cache = new DefaultCache();
        Assertions.assertNull(cache.get("8.8.8.8"));
    }

    @Test
    void testGetReturnNullIfEntryNotFound() {
        DefaultCache cache = new DefaultCache();
        cache.put("1.1.1.1", new IpData());
        Assertions.assertNull(cache.get("8.8.8.8"));
    }

    @Test
    void testGetReturnEntryIfFound() {
        DefaultCache cache = new DefaultCache();
        IpData ipData = new IpData();
        cache.put("8.8.8.8", ipData);
        Assertions.assertSame(cache.get("8.8.8.8"), ipData);
    }

}
