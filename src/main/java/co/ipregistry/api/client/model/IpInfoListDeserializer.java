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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class IpInfoListDeserializer extends JsonDeserializer<Object> {

    public IpInfoListDeserializer() {
        super();
    }

    @Override
    public IpInfoList deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        TreeNode treeNode = codec.readTree(p).get("results");

        Object[] objects = new Object[treeNode.size()];

        for (int i = 0; i < treeNode.size(); i++) {
            TreeNode ipInfoOrLookupError = treeNode.get(i);

            if (ipInfoOrLookupError.get("code") == null) {
                objects[i] = codec.treeToValue(ipInfoOrLookupError, IpInfo.class);
            } else {
                LookupError lookupError = codec.treeToValue(ipInfoOrLookupError, LookupError.class);
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