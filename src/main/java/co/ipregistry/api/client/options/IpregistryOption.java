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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
public class IpregistryOption {

    protected final String name;

    protected final String value;


    public IpregistryOption(String name, String value) {
        this.name = name;
        this.value = encode(value);
    }

    protected static String encode(String expression) {
        try {
            return URLEncoder.encode(expression, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return expression;
        }
    }

}
