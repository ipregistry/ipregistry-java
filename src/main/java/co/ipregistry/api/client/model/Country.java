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

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@ToString
public class Country {

    @JsonProperty("area")
    private double area;

    @JsonProperty("borders")
    private List<String> borders;

    @JsonProperty("calling_code")
    private String callingCode;

    @JsonProperty("capital")
    private String capital;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("population")
    private int population;

    @JsonProperty("population_density")
    private double populationDensity;

    @JsonProperty("flag")
    private Flag flag;

    @JsonProperty("languages")
    private List<Language> languages;

    @JsonProperty("tld")
    private String tld;

}
