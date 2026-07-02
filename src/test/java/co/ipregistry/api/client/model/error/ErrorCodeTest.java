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

package co.ipregistry.api.client.model.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ErrorCodeTest {

    @Test
    void testFromCodeMapsKnownCodes() {
        Assertions.assertEquals(ErrorCode.TOO_MANY_REQUESTS, ErrorCode.fromCode("TOO_MANY_REQUESTS"));
        Assertions.assertEquals(ErrorCode.INVALID_API_KEY, ErrorCode.fromCode("INVALID_API_KEY"));
        Assertions.assertEquals(ErrorCode.INSUFFICIENT_CREDITS, ErrorCode.fromCode("INSUFFICIENT_CREDITS"));
    }

    @Test
    void testFromCodeIsCaseInsensitiveAndTrimmed() {
        Assertions.assertEquals(ErrorCode.BAD_REQUEST, ErrorCode.fromCode("  bad_request  "));
    }

    @Test
    void testFromCodeReturnsNullForUnknownCode() {
        Assertions.assertNull(ErrorCode.fromCode("A_BRAND_NEW_CODE"));
        Assertions.assertNull(ErrorCode.fromCode(""));
    }

    @Test
    void testFromCodeReturnsNullForNull() {
        Assertions.assertNull(ErrorCode.fromCode(null));
    }

}
