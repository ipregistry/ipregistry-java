/*
 * Copyright 2022 Ipregistry (https://ipregistry.co).
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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;


/**
 * Implementation used to deserialize an {@link UserAgentList}.
 */
public class UserAgentListDeserializer extends JsonDeserializer<Object> {

    /**
     * Creates a new instance.
     */
    public UserAgentListDeserializer() {
        super();
    }

    @Override
    public UserAgentList deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        final ObjectCodec codec = parser.getCodec();
        final TreeNode treeNode = codec.readTree(parser).get("results");

        final Object[] objects = new Object[treeNode.size()];

        for (int i = 0; i < treeNode.size(); i++) {
            final TreeNode userAgentOrLookupError = treeNode.get(i);

            if (userAgentOrLookupError.get("code") == null) {
                objects[i] = codec.treeToValue(userAgentOrLookupError, UserAgent.class);
            } else {
                final LookupError lookupError = codec.treeToValue(userAgentOrLookupError, LookupError.class);
                objects[i] =
                        new IpInfoException(
                                lookupError.getCode(),
                                lookupError.getMessage(),
                                lookupError.getResolution());
            }
        }

        return new UserAgentList(objects);
    }

}
