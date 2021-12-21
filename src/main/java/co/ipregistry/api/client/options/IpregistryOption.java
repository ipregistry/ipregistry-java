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

import lombok.Getter;


/**
 * An option depicts an optional action, behavior or configuration to apply when invoking an Ipregistry resource.
 */
@Getter
public class IpregistryOption {

    /**
     * The option name.
     */
    protected final String name;

    /**
     * The option value.
     */
    protected final String value;


    /**
     * Creates a new option with the specified {@code name} and {@code value}.
     *
     * @param name  the option name.
     * @param value the option value.
     */
    public IpregistryOption(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

}
