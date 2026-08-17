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


class ConnectionTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void testDeserializeFullPayload() {
        final Connection connection = MAPPER.readValue("{\n" +
                "    \"asn\" : 15169,\n" +
                "    \"domain\" : \"google.com\",\n" +
                "    \"is_anycast\" : true,\n" +
                "    \"organization\" : \"Google LLC\",\n" +
                "    \"route\" : \"8.8.8.0/24\",\n" +
                "    \"type\" : \"hosting\"\n" +
                "  }", Connection.class);

        Assertions.assertEquals(15169L, connection.getAsn());
        Assertions.assertEquals("google.com", connection.getDomain());
        Assertions.assertTrue(connection.isAnycast());
        Assertions.assertEquals("Google LLC", connection.getOrganization());
        Assertions.assertEquals("8.8.8.0/24", connection.getRoute());
        Assertions.assertEquals(ConnectionType.HOSTING, connection.getType());
    }

    @Test
    void testIsAnycastFalse() {
        final Connection connection = MAPPER.readValue("{\"is_anycast\" : false}", Connection.class);

        Assertions.assertFalse(connection.isAnycast());
    }

    @Test
    void testIsAnycastDefaultsToFalseWhenAbsent() {
        final Connection connection = MAPPER.readValue("{\"asn\" : 15169}", Connection.class);

        Assertions.assertFalse(connection.isAnycast());
    }

}
