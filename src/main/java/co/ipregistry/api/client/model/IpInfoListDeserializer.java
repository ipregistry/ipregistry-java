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
import co.ipregistry.api.client.model.error.LookupError;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;


/**
 * Implementation used to deserialize an {@link IpInfoList}.
 */
public class IpInfoListDeserializer extends ValueDeserializer<Object> {

    /**
     * Creates a new instance.
     */
    public IpInfoListDeserializer() {
        super();
    }

    @Override
    public IpInfoList deserialize(final JsonParser parser, final DeserializationContext context) {
        final JsonNode results = context.readTree(parser).get("results");

        final Object[] objects = new Object[results.size()];

        for (int i = 0; i < results.size(); i++) {
            final JsonNode ipInfoOrLookupError = results.get(i);

            if (ipInfoOrLookupError.get("code") == null) {
                objects[i] = context.readTreeAsValue(ipInfoOrLookupError, IpInfo.class);
            } else {
                final LookupError lookupError = context.readTreeAsValue(ipInfoOrLookupError, LookupError.class);
                objects[i] =
                        new IpInfoException(
                                lookupError.getCode(),
                                lookupError.getMessage(),
                                lookupError.getResolution());
            }
        }

        return new IpInfoList(objects);
    }

}
