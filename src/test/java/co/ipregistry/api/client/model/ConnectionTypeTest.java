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


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ConnectionTypeTest {

    @Test
    void testFrom_unknownTypeFallbackToBusiness() {
        Assertions.assertEquals(ConnectionType.BUSINESS, ConnectionType.from("unknown"));
    }

    @Test
    void testFromCalledOnJacksonDeserialization() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final Connection connection = mapper.readValue("{\n" +
                "    \"asn\" : 42,\n" +
                "    \"domain\" : \"acme.com\",\n" +
                "    \"organization\" : \"Acme\",\n" +
                "    \"route\" : \"1.2.3.0/24\",\n" +
                "    \"type\" : \"unknown\"\n" +
                "  }", Connection.class);

        Assertions.assertEquals(ConnectionType.BUSINESS, connection.getType());
    }

}
