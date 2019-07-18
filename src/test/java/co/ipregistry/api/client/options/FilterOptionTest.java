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

package co.ipregistry.api.client.options;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilterOptionTest {

    @Test
    void testFilterOptionName() {
        Assertions.assertEquals(new FilterOption("test").name, "fields");
    }

    @Test
    void testFilterOption__noEncodedCharacters() {
        FilterOption filterOption = new FilterOption("connection");
        Assertions.assertEquals(filterOption.getValue(), "connection");
    }

    @Test
    void testFilterOption__withEncodedCharacters() {
        FilterOption filterOption = new FilterOption("location.country.[capital,code,name]");
        Assertions.assertEquals(filterOption.getValue(), "location.country.%5Bcapital%2Ccode%2Cname%5D");
    }

}
