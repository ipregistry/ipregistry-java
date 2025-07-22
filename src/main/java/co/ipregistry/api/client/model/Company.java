/*
 * Copyright 2021 Ipregistry (https://ipregistry.co).
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

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Wraps company data associated with an IP address.
 * Contains information about the organization that owns or operates
 * the IP address, including company name, domain, and business type.
 */
@AllArgsConstructor
@Data
public class Company {

    /**
     * Creates a new Company instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public Company() {
    }

    /**
     * The name of the company or organization.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The primary domain name associated with the company.
     */
    @JsonProperty("domain")
    private String domain;

    /**
     * The type/category of the company (e.g., business, education, government).
     */
    @JsonProperty("type")
    private CompanyType type;

}
