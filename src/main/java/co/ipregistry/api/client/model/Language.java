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
 * Represents language information associated with an IP address location.
 * <p>
 * This class contains language identification data including standard language codes,
 * English names, and native language names. It provides linguistic context for
 * IP address geolocation, useful for localization and internationalization purposes.
 * </p>
 */
@AllArgsConstructor
@Data
public class Language {

    /**
     * Creates a new Language instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Language() {
    }

    /**
     * The ISO 639-1 two-letter language code (e.g., "en" for English, "fr" for French, "de" for German).
     * This follows the standard international language code specification.
     */
    @JsonProperty("code")
    private String code;

    /**
     * The language name in English (e.g., "English", "French", "German").
     * Provides the common English name for the language.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The language name in its own script and language (e.g., "English", "Français", "Deutsch").
     * Shows how speakers of the language would write the language name themselves.
     */
    @JsonProperty("native")
    private String nativeName;

}
