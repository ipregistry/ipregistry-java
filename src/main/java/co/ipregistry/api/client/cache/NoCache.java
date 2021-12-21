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


/**
 * A no-op implementation that caches nothing.
 */
public class NoCache implements IpregistryCache {

    NoCache() {

    }

    /**
     * Returns the class instance.
     *
     * @return the class instance.
     */
    public static NoCache getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public IpInfo get(final String key) {
        return null;
    }

    @Override
    public void put(final String key, final IpInfo response) {
        // do nothing
    }

    @Override
    public void invalidate(final String key) {
        // do nothing
    }

    @Override
    public void invalidateAll() {
        // do nothing
    }

    private static class Holder {

        static NoCache INSTANCE = new NoCache();
    }

}
