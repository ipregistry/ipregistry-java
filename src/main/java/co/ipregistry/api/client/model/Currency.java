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

package co.ipregistry.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@ToString
public class Currency {

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("name_native")
    private String nameNative;

    @JsonProperty("plural")
    private String plural;

    @JsonProperty("plural_native")
    private String pluralNative;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("symbol_native")
    private String symbolNative;

    @JsonProperty("format")
    private CurrencyFormat format;

}
