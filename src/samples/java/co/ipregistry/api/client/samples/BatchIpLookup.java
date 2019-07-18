/*
 * Copyright 2019 IpregistryClient (https://ipregistry.co).
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
import co.ipregistry.api.client.exceptions.IpInfoException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.IpInfoList;

import java.util.Arrays;

public class BatchIpLookup {

    public static void main(String[] args) {
        IpregistryClient client = new IpregistryClient("tryout");

        try {
            IpInfoList ipInfoList =
                    client.lookup(Arrays.asList("73.2.2.2", "8.8.8.8", "2001:67c:2e8:22::c100:68b"));

            for (int i = 0; i < ipInfoList.size(); i++) {
                try {
                    IpInfo ipInfo = ipInfoList.get(i);
                    // Here is an example to print out the country name associated with each IP address
                    System.out.println(ipInfo.getLocation().getCountry().getName());
                } catch (IpInfoException e) {
                    // Handle batch lookup error (e.g. invalid IP address) here
                    e.printStackTrace();
                }
            }
        } catch (ApiException e) {
            // Handle API errors (e.g. insufficient credits, throttling) here
            e.printStackTrace();
        } catch(ClientException e) {
            // Handle client errors (e.g. network error) here
            e.printStackTrace();
        }
    }

}
