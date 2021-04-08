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

package co.ipregistry.api.client.samples;

import co.ipregistry.api.client.IpregistryClient;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.RequesterIpInfo;

public class SingleIpLookup {

    public static void main(final String[] args) {
        final IpregistryClient client = new IpregistryClient("tryout");

        try {
            // Lookup IP data for the current node and network interface used to execute this code
            final RequesterIpInfo requesterIpInfo = client.lookup();
            System.out.println(requesterIpInfo.getLocation().getCountry().getName());
            System.out.println(requesterIpInfo);

            // Here is another example to lookup data for a given IP address
            // You may already have it or you can get the client IP from a request header
            final IpInfo ipInfo = client.lookup("54.85.132.205");
            System.out.println(ipInfo);
        } catch (final ApiException e) {
            // Handle API errors (e.g. insufficient credits, throttling) here
            e.printStackTrace();
        } catch (final ClientException e) {
            // Handle client errors (e.g. network error) here
            e.printStackTrace();
        }
    }

}
