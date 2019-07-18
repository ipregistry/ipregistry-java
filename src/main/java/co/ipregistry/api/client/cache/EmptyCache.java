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

public class EmptyCache implements IpregistryCache {

    private static class Holder {

        static EmptyCache INSTANCE = new EmptyCache();
    }

    public static EmptyCache getInstance() {
        return Holder.INSTANCE;
    }


    EmptyCache() {

    }

    @Override
    public IpInfo get(String ip) {
        return null;
    }

    @Override
    public void put(String ip, IpInfo response) {
        // do nothing
    }

    @Override
    public void invalidate(String ip) {
        // do nothing
    }

    @Override
    public void invalidateAll() {
        // do nothing
    }

}
