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
 * Represents currency information associated with an IP address location.
 * <p>
 * This class contains comprehensive currency data for the country where the IP address
 * is located, including currency codes, names in various languages, symbols, and
 * formatting information. This data is useful for e-commerce applications, payment
 * processing, and localization services.
 * </p>
 */
@AllArgsConstructor
@Data
public class Currency {

    /**
     * Creates a new Currency instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Currency() {
    }

    /**
     * The ISO 4217 three-letter currency code (e.g., "USD", "EUR", "GBP").
     * This is the standard international currency code used in financial transactions.
     */
    @JsonProperty("code")
    private String code;

    /**
     * The currency name in English (e.g., "US Dollar", "Euro", "British Pound").
     * Provides the common English name for the currency.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The currency name in the local language and script.
     * For example, "Доллар США" for US Dollar in Russian or "ユーロ" for Euro in Japanese.
     */
    @JsonProperty("name_native")
    private String nameNative;

    /**
     * The plural form of the currency name in English (e.g., "US Dollars", "Euros").
     * Used when referring to multiple units of the currency.
     */
    @JsonProperty("plural")
    private String plural;

    /**
     * The plural form of the currency name in the local language and script.
     * Used when referring to multiple units of the currency in the native language.
     */
    @JsonProperty("plural_native")
    private String pluralNative;

    /**
     * The currency symbol commonly used in English contexts (e.g., "$", "€", "£").
     * This is the symbol typically used in international contexts.
     */
    @JsonProperty("symbol")
    private String symbol;

    /**
     * The currency symbol in the local format and script.
     * This may be different from the international symbol and is used in local contexts.
     */
    @JsonProperty("symbol_native")
    private String symbolNative;

    /**
     * Formatting information for displaying currency amounts.
     * Contains details about decimal places, thousand separators, and symbol positioning.
     */
    @JsonProperty("format")
    private CurrencyFormat format;

}
