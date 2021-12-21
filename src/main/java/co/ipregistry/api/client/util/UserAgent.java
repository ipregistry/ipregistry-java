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

package co.ipregistry.api.client.util;

/**
 * This class consists exclusively of static methods that operate on or with user-agent values.
 */
public final class UserAgent {

    private UserAgent() {

    }

    /**
     * Returns whether the specified user-agent depicts a bot or spider.
     *
     * @param userAgent the user-agent value to analyze. Usually retrieved from the {@code user-agent} header of a request received on a server.
     * @return whether the specified user-agent depicts a bot or spider.
     */
    public static boolean isBot(String userAgent) {
        userAgent = userAgent.toLowerCase();
        return userAgent.contains("bot") || userAgent.contains("spider") || userAgent.contains("slurp");
    }

}
