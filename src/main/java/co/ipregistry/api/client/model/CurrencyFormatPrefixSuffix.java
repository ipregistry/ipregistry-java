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
 * Represents prefix and suffix formatting elements for currency display.
 * <p>
 * This class defines what characters or symbols should appear before (prefix)
 * and after (suffix) a currency amount when displaying monetary values.
 * Different locales and currencies have various conventions for symbol placement
 * and negative number formatting.
 * </p>
 */
@AllArgsConstructor
@Data
public class CurrencyFormatPrefixSuffix {

    /**
     * Creates a new CurrencyFormatPrefixSuffix instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public CurrencyFormatPrefixSuffix() {
    }

    /**
     * Text or symbols to display before the currency amount.
     * Examples include currency symbols ("$", "€"), negative indicators ("-", "("),
     * or localized prefixes that appear before the numeric value.
     */
    @JsonProperty("prefix")
    private String prefix;

    /**
     * Text or symbols to display after the currency amount.
     * Examples include currency codes or symbols placed after the number,
     * closing parentheses for negative amounts, or localized suffixes.
     */
    @JsonProperty("suffix")
    private String suffix;

}
