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

package co.ipregistry.api.client.request;

import co.ipregistry.api.client.IpregistryConfig;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.IpInfoList;
import co.ipregistry.api.client.model.RequesterIpInfo;
import co.ipregistry.api.client.model.error.LookupError;
import co.ipregistry.api.client.options.IpregistryOption;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DefaultRequestHandler implements IpregistryRequestHandler {

    private static final String USER_AGENT = "IpregistryClient/Java/" + getVersion();

    private final IpregistryConfig config;

    public DefaultRequestHandler(final IpregistryConfig config) {
        this.config = config;
    }


    public IpInfo lookup(final String ip, final IpregistryOption... options) throws ApiException, ClientException {
        try {
            final Class<? extends IpInfo> type = "".equals(ip) || ip == null ? RequesterIpInfo.class : IpInfo.class;

            final Object result = Request.get(buildApiUrl(ip, options))
                    .addHeader("User-Agent", USER_AGENT)
                    .connectTimeout(Timeout.ofMilliseconds(config.getConnectionTimeout()))
                    .responseTimeout(Timeout.ofMilliseconds(config.getSocketTimeout()))
                    .execute().handleResponse(response -> {
                        try {
                            if (response.getCode() == HttpStatus.SC_OK) {
                                return new ObjectMapper()
                                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                        .readValue(response.getEntity().getContent(), type);
                            } else {
                                return createCustomException(response);
                            }
                        } catch (final IOException e) {
                            return new ClientException(e);
                        }
                    });

            if (result instanceof IpInfo) {
                return (IpInfo) result;
            }

            if (result instanceof ApiException) {
                throw (ApiException) result;
            }

            if (result instanceof ClientException) {
                throw (ClientException) result;
            }

            if (result instanceof Throwable) {
                throw new ClientException((Throwable) result);
            }

            throw new ClientException("Unknown result type (" + result.getClass() + "). Check your input value.");
        } catch (final IOException e) {
            throw new ClientException(e);
        }
    }

    private ApiException createCustomException(final ClassicHttpResponse response) throws IOException {
        final LookupError error = new ObjectMapper().readValue(response.getEntity().getContent(), LookupError.class);
        return new ApiException(error.getCode(), error.getMessage(), error.getResolution());
    }

    protected String buildApiUrl(final String ip, final IpregistryOption... options) {
        final StringBuilder result = new StringBuilder();

        result.append(config.getApiUrl());
        result.append('/');

        if (ip != null) {
            result.append(ip);
        }

        result.append("?key=");
        result.append(config.getApiKey());

        for (final IpregistryOption option : options) {
            result.append('&');
            result.append(option.getName());
            result.append('=');
            try {
                result.append(URLEncoder.encode(option.getValue(), StandardCharsets.UTF_8.name()));
            } catch (final UnsupportedEncodingException e) {
                result.append(option.getValue());
            }
        }

        return result.toString();
    }

    public IpInfoList lookup(final Iterable<String> ips, final IpregistryOption... options) throws ApiException, ClientException {
        try {
            final Object result = Request.post(buildApiUrl("", options))
                    .bodyString(toJsonList(ips), ContentType.APPLICATION_JSON)
                    .addHeader("User-Agent", USER_AGENT)
                    .connectTimeout(Timeout.ofMilliseconds(config.getConnectionTimeout()))
                    .responseTimeout(Timeout.ofMilliseconds(config.getSocketTimeout()))
                    .execute().handleResponse(response -> {
                        try {
                            if (response.getCode() == HttpStatus.SC_OK) {
                                return new ObjectMapper()
                                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                        .readValue(response.getEntity().getContent(), IpInfoList.class);
                            } else {
                                return createCustomException(response);
                            }
                        } catch (final IOException e) {
                            return new ClientException(e);
                        }
                    });

            if (result instanceof IpInfoList) {
                return (IpInfoList) result;
            }

            throw (ApiException) result;
        } catch (final IOException e) {
            throw new ClientException(e);
        }
    }

    private String toJsonList(final Iterable<String> ips) {
        return '[' + StreamSupport
                .stream(ips.spliterator(), false)
                .map(ip -> "\"" + ip + "\"")
                .collect(Collectors.joining(",")) + ']';
    }

    private static String getVersion() {
        final Package classPackage = DefaultRequestHandler.class.getPackage();

        if (classPackage.getSpecificationVersion() == null) {
            return "dev";
        }

        return classPackage.getSpecificationVersion();
    }

}
