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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Wraps currency format data associated with an IP address.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CurrencyFormat {

    @JsonProperty("decimal_separator")
    private String decimalSeparator;

    @JsonProperty("group_separator")
    private String groupSeparator;

    @JsonProperty("negative")
    private CurrencyFormatPrefixSuffix negative;

    @JsonProperty("positive")
    private CurrencyFormatPrefixSuffix positive;

}
