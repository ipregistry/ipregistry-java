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

package co.ipregistry.api.client.exceptions;

import co.ipregistry.api.client.model.error.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ApiExceptionTest {

    @Test
    void testExposesTypedErrorCodeForKnownCode() {
        final ApiException exception =
                new ApiException("INSUFFICIENT_CREDITS", "No credits left", "Buy credits");

        Assertions.assertEquals("INSUFFICIENT_CREDITS", exception.getCode());
        Assertions.assertEquals(ErrorCode.INSUFFICIENT_CREDITS, exception.getErrorCode());
    }

    @Test
    void testKeepsRawCodeButNullTypedCodeForUnknownCode() {
        final ApiException exception =
                new ApiException("A_BRAND_NEW_CODE", "message", "resolution");

        Assertions.assertEquals("A_BRAND_NEW_CODE", exception.getCode());
        Assertions.assertNull(exception.getErrorCode());
    }

    @Test
    void testSubclassesInheritTypedErrorCode() {
        final ApiException exception =
                new IpInfoException("TOO_MANY_REQUESTS", "Slow down", "Retry later");

        Assertions.assertEquals(ErrorCode.TOO_MANY_REQUESTS, exception.getErrorCode());
    }

}
