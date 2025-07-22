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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents flag imagery and representations for a country.
 * <p>
 * This class provides various formats and representations of a country's flag,
 * including emoji versions and URLs to different graphical representations.
 * These resources can be used for displaying country flags in applications,
 * user interfaces, or documentation.
 * </p>
 */
@AllArgsConstructor
@Data
@JsonPropertyOrder(alphabetic = true)
public class Flag {

    /**
     * Creates a new Flag instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Flag() {
    }

    /**
     * The flag represented as an emoji character.
     * Provides a simple text-based representation of the country flag.
     */
    @JsonProperty("emoji")
    private String emoji;

    /**
     * The Unicode code point sequence for the flag emoji.
     * Contains the Unicode representation that can be used to programmatically display the flag emoji.
     */
    @JsonProperty("emoji_unicode")
    private String emojiUnicode;

    /**
     * URL to the EmojiTwo (now JoyPixels) style flag image.
     * Provides a link to a stylized emoji-style flag image from the EmojiTwo collection.
     */
    @JsonProperty("emojitwo")
    private String emojitwo;

    /**
     * URL to the Google Noto Color Emoji style flag image.
     * Provides a link to a flag image in Google's Noto emoji style.
     */
    @JsonProperty("noto")
    private String noto;

    /**
     * URL to the Twemoji (Twitter Emoji) style flag image.
     * Provides a link to a flag image in Twitter's emoji style.
     */
    @JsonProperty("twemoji")
    private String twemoji;

    /**
     * URL to the official flag image from Wikimedia Commons.
     * Provides a link to an official, high-quality flag image from Wikimedia's collection.
     */
    @JsonProperty("wikimedia")
    private String wikimedia;

}
