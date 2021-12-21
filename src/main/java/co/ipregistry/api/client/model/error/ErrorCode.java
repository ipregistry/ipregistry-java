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


/**
 * Enumerates error codes.
 */
public enum ErrorCode {

    /**
     * You specified an invalid request payload, for instance with our bulk endpoints. Check examples in our docs.
     *
     * @see <a href="https://ipregistry.co/docs/endpoints">https://ipregistry.co/docs/endpoints</a>
     */
    BAD_REQUEST,
    /**
     * Your API key is disabled. This may be caused by your account email that has not been verified after multiple recalls or if malicious activities are detected.
     */
    DISABLED_API_KEY,
    /**
     * IP filtering is enabled for the API key in use and the user request is from an IP that you did not whitelist.
     */
    FORBIDDEN_IP,
    /**
     * Origin filtering is enabled for the API key in use and the request origin is from an origin that does not match the ones you allowed.
     */
    FORBIDDEN_ORIGIN,
    /**
     * IP and origin filtering are enabled for the API key in use and the user request is not matching what you allowed.
     */
    FORBIDDEN_IP_ORIGIN,
    /**
     * An unexpected error has occurred on Ipregistry servers. Please check your request and retry again. Contact us if the issue persists.
     */
    INTERNAL,
    /**
     * You need to buy credits to continue using the service (we send up to 3 email alerts before you run out of credits).
     */
    INSUFFICIENT_CREDITS,
    /**
     * Your API key is invalid. Make sure you are using the right key or check its value for a typo.
     */
    INVALID_API_KEY,
    /**
     * The filter pattern you entered to select a subset of fields is invalid. Decompose your filter into smaller parts and test them incrementally.
     */
    INVALID_FILTER_SYNTAX,
    /**
     * The IP address that was searched is not a valid IPv4 or IPv6 address.
     */
    INVALID_IP_ADDRESS,
    /**
     * You did not specify an API key. Add your API key as a request parameter: https://api.ipregistry.co/?key=YOUR_API_KEY.
     */
    MISSING_API_KEY,
    /**
     * The IP address that was searched is a reserved address (loopback, link-local, multicast, private, site-local or wildcard IP address). Such IP addresses cannot be used to deduce geolocation information.
     */
    RESERVED_IP_ADDRESS,
    /**
     * You made a batch request with too many IP addresses. Batch requests must not contain more than 256 IP addresses. Input less IP addresses.
     */
    TOO_MANY_IPS,
    /**
     * Rate limiting was enabled for the API key in use and too many requests were made for the window you configured. Decrease your invocation rate or change your settings from the Ipregistry dashboard.
     */
    TOO_MANY_REQUESTS,
    /**
     * You have specified too many user agents with the batch endpoint. Batch requests must not contain more than 256 user agents. Input fewer user agents.
     */
    TOO_MANY_USER_AGENTS

}
