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
 * Wraps flag data associated with an IP address.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonPropertyOrder(alphabetic = true)
public class Flag {

    @JsonProperty("emoji")
    private String emoji;

    @JsonProperty("emoji_unicode")
    private String emojiUnicode;

    @JsonProperty("emojitwo")
    private String emojitwo;

    @JsonProperty("noto")
    private String noto;

    @JsonProperty("twemoji")
    private String twemoji;

    @JsonProperty("wikimedia")
    private String wikimedia;

}
