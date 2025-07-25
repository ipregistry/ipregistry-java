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

import co.ipregistry.api.client.exceptions.IpInfoException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a collection of IP address lookup results from batch operations.
 * <p>
 * This class holds the results of batch IP address lookups, where multiple IP addresses
 * are queried in a single API call. Each entry in the list can be either successful
 * {@link IpInfo} data or an exception if the lookup failed for that particular IP address.
 * This allows for partial success in batch operations where some IPs succeed and others fail.
 * </p>
 */
@AllArgsConstructor
@Data
@JsonDeserialize(using = IpInfoListDeserializer.class)
public class IpInfoList {

    /**
     * Creates a new IpInfoList instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public IpInfoList() {
    }

    /**
     * Array containing the results for each IP address lookup.
     * Each element is either an {@link IpInfo} object for successful lookups
     * or an {@link IpInfoException} for failed lookups.
     */
    @JsonProperty("results")
    Object[] ips = null;


    /**
     * Returns data for the IP address at the specified {@code index}.
     * If an exception was thrown while handling the associated request, this exception is rethrown when invoking this method.
     * <p>
     * The order is the one of the requests that have been inputted for dispatching.
     *
     * @param index the index of the list.
     * @return IpInfo data at the specified index.
     * @throws IpInfoException if an exception was thrown while handling the associated request.
     */
    public IpInfo get(final int index) throws IpInfoException {
        final Object object = ips[index];

        if (object instanceof IpInfo) {
            return (IpInfo) object;
        }

        throw (IpInfoException) object;
    }

    /**
     * Returns the list size.
     *
     * @return the number of entries available in the list.
     */
    public int size() {
        if (ips == null) {
            return 0;
        }

        return ips.length;
    }

    /**
     * Returns data for the IP address at the specified {@code index}.
     * If an exception was thrown while handling the associated request, the return value is an exception. Otherwise, this is the IpInfo data.
     * <p>
     * The order is the one of the requests that have been inputted for dispatching.
     *
     * @param index the index of the list.
     * @return IpInfo data at the specified index, or the exception thrown while dispatching the associated request.
     */
    public Object unsafeGet(final int index) {
        return ips[index];
    }

}
