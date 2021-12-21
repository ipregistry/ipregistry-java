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

package co.ipregistry.api.client.options;

/**
 * An option to filter IP address data retrieved with a lookup.
 */
public class FilterOption extends IpregistryOption {

    /**
     * Creates a filter expression with the specified {@code expression}.
     *
     * @param expression a filter expression as explained in our docs}.
     *
     * @see <a href="https://ipregistry.co/docs/filtering">https://ipregistry.co/docs/filtering</a>
     */
    public FilterOption(final String expression) {
        super("fields", expression);
    }

}
