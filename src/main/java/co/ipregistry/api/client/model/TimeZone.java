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
 * Represents time zone information associated with an IP address location.
 * <p>
 * This class contains comprehensive time zone data including identifiers, current time,
 * UTC offset, daylight saving status, and display names. This information is useful
 * for applications that need to display localized times or schedule events based
 * on the user's geographical location.
 * </p>
 */
@AllArgsConstructor
@Data
public class TimeZone {

    /**
     * Creates a new TimeZone instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public TimeZone() {
    }

    /**
     * The IANA time zone identifier (e.g., "America/New_York", "Europe/London", "Asia/Tokyo").
     * This is the standard identifier used by most date/time libraries and operating systems.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The time zone abbreviation (e.g., "EST", "GMT", "JST").
     * Provides a short, commonly recognized abbreviation for the time zone.
     */
    @JsonProperty("abbreviation")
    private String abbreviation;

    /**
     * The current date and time in the time zone, typically in ISO 8601 format.
     * Shows what time it currently is in the location associated with the IP address.
     */
    @JsonProperty("current_time")
    private String currentTime;

    /**
     * The human-readable name of the time zone (e.g., "Eastern Standard Time", "Greenwich Mean Time").
     * Provides a descriptive name for the time zone that users can easily understand.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The UTC offset in seconds for this time zone.
     * Positive values indicate time zones east of UTC, negative values indicate west of UTC.
     * For example, EST is -18000 (UTC-5), JST is +32400 (UTC+9).
     */
    @JsonProperty("offset")
    private int offset;

    /**
     * Indicates whether the location is currently observing daylight saving time.
     * Returns true if daylight saving time is currently in effect, false otherwise.
     */
    @JsonProperty("in_daylight_saving")
    private boolean daylightSaving;

}
