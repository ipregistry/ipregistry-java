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


import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SecurityTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void testDeserializeFullPayload() {
        final Security security = MAPPER.readValue("{\n" +
                "    \"is_abuser\" : false,\n" +
                "    \"is_attacker\" : false,\n" +
                "    \"is_bogon\" : false,\n" +
                "    \"is_cloud_provider\" : false,\n" +
                "    \"is_proxy\" : true,\n" +
                "    \"is_relay\" : false,\n" +
                "    \"is_residential_proxy\" : true,\n" +
                "    \"is_tor\" : false,\n" +
                "    \"is_tor_exit\" : false,\n" +
                "    \"is_vpn\" : false,\n" +
                "    \"is_anonymous\" : true,\n" +
                "    \"is_threat\" : false\n" +
                "  }", Security.class);

        Assertions.assertFalse(security.isAbuser());
        Assertions.assertFalse(security.isAttacker());
        Assertions.assertFalse(security.isBogon());
        Assertions.assertFalse(security.isCloudProvider());
        Assertions.assertTrue(security.isProxy());
        Assertions.assertFalse(security.isRelay());
        Assertions.assertTrue(security.isResidentialProxy());
        Assertions.assertFalse(security.isTor());
        Assertions.assertFalse(security.isTorExitNode());
        Assertions.assertFalse(security.isVpn());
        Assertions.assertTrue(security.isAnonymous());
        Assertions.assertFalse(security.isThreat());
    }

    @Test
    void testIsResidentialProxyFalse() {
        final Security security = MAPPER.readValue("{\"is_residential_proxy\" : false}", Security.class);

        Assertions.assertFalse(security.isResidentialProxy());
    }

    @Test
    void testIsResidentialProxyDefaultsToFalseWhenAbsent() {
        final Security security = MAPPER.readValue("{\"is_proxy\" : true}", Security.class);

        Assertions.assertFalse(security.isResidentialProxy());
    }

}
