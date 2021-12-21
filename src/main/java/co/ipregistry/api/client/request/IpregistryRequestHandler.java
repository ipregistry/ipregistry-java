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

package co.ipregistry.api.client.request;

import co.ipregistry.api.client.exceptions.IpregistryException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.IpInfoList;
import co.ipregistry.api.client.options.IpregistryOption;


/**
 * Defines methods common to all Ipregistry request handler implementations.
 */
public interface IpregistryRequestHandler {

    /**
     * Lookup IP data such as geolocation data, currency, timezone, threat information and more
     * for the specified {@code ip} address by using the IpregistryClient API.
     *
     * @param ip      The IPv4 or IPv6 address to lookup information for, using respectively the dotted-decimal
     *                or the colon-hexadecimal notation.
     * @param options An optional set of {@link IpregistryOption}.
     * @return the info found for the specified {@code ip}.
     * @throws IpregistryException if an error happens when looking up data.
     *                             It can be an {@link co.ipregistry.api.client.exceptions.ApiException}
     *                             or a {@link co.ipregistry.api.client.exceptions.ClientException}.
     */
    IpInfo lookup(String ip, IpregistryOption... options) throws IpregistryException;

    /**
     * Lookup IP data such as geolocation data, currency, timezone, threat information and more
     * for the specified collection of {@code ips} addresses by using the IpregistryClient API.
     *
     * @param ips     The IPv4 or IPv6 addresses to lookup information for. The IP address format must use
     *                the dotted-decimal or the colon-hexadecimal notation.
     * @param options An optional set of {@link IpregistryOption}.
     * @return the info found for the specified {@code ips}.
     * @throws IpregistryException if an error happens when looking up data.
     *                             It can be an {@link co.ipregistry.api.client.exceptions.ApiException}
     *                             or a {@link co.ipregistry.api.client.exceptions.ClientException}.
     */
    IpInfoList lookup(Iterable<String> ips, IpregistryOption... options) throws IpregistryException;

}
