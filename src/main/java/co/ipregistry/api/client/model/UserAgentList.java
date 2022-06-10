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

import co.ipregistry.api.client.exceptions.UserAgentException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * A list of user-agent data as a result of a bulk parsing.
 */
@AllArgsConstructor
@JsonDeserialize(using = UserAgentListDeserializer.class)
@Setter
@NoArgsConstructor
public class UserAgentList {

    @JsonProperty("results")
    Object[] userAgents = null;


    /**
     * Creates a new list with the specified {@code length} capacity.
     *
     * @param length the initial capacity.
     */
    public UserAgentList(final int length) {
        this.userAgents = new Object[length];
    }

    /**
     * Sets the specified value {@code value} at index {@code index}. It overrides the existing value.
     *
     * @param index the list index where to set the value.
     * @param value the value to set.
     */
    public void set(final int index, final Object value) {
        userAgents[index] = value;
    }

    public UserAgent get(final int index) throws UserAgentException {
        final Object object = userAgents[index];

        if (object instanceof UserAgent) {
            return (UserAgent) object;
        }

        throw (UserAgentException) object;
    }

    /**
     * Returns the list size.
     *
     * @return the number of entries available in the list.
     */
    public int size() {
        if (userAgents == null) {
            return 0;
        }

        return userAgents.length;
    }

    public Object unsafeGet(final int index) {
        return userAgents[index];
    }

}
