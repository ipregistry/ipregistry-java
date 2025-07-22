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
 * Represents currency formatting rules and conventions for displaying monetary amounts.
 * <p>
 * This class defines how currency values should be formatted according to local conventions,
 * including decimal and thousand separators, and different formatting rules for positive
 * and negative amounts. This information is essential for properly displaying prices
 * and monetary values in localized applications.
 * </p>
 */
@AllArgsConstructor
@Data
public class CurrencyFormat {

    /**
     * Creates a new CurrencyFormat instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public CurrencyFormat() {
    }

    /**
     * The character used as the decimal separator in currency amounts.
     * Common examples include "." (dot) in English locales and "," (comma) in many European locales.
     */
    @JsonProperty("decimal_separator")
    private String decimalSeparator;

    /**
     * The character used to separate groups of digits (thousands separator) in large amounts.
     * Common examples include "," (comma) in English locales and "." (dot) or " " (space) in other locales.
     */
    @JsonProperty("group_separator")
    private String groupSeparator;

    /**
     * Formatting rules for negative currency amounts.
     * Contains prefix and suffix information for displaying negative values (e.g., parentheses, minus signs).
     */
    @JsonProperty("negative")
    private CurrencyFormatPrefixSuffix negative;

    /**
     * Formatting rules for positive currency amounts.
     * Contains prefix and suffix information for displaying positive values (e.g., currency symbol placement).
     */
    @JsonProperty("positive")
    private CurrencyFormatPrefixSuffix positive;

}
