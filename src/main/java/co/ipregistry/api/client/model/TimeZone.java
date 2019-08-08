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
public class TimeZone {

    @JsonProperty("id")
    private String id;

    @JsonProperty("abbreviation")
    private String abbreviation;

    @JsonProperty("current_time")
    private String currentTime;

    @JsonProperty("name")
    private String name;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("in_daylight_saving")
    private boolean daylightSaving;

}
