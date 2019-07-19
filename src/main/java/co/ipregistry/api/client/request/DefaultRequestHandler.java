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
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DefaultRequestHandler implements IpregistryRequestHandler {

    private static final String USER_AGENT = "IpregistryClient/Java/1.0.0";

    private IpregistryConfig config;

    public DefaultRequestHandler(IpregistryConfig config) {
        this.config = config;
    }


    public IpInfo lookup(String ip, IpregistryOption... options) throws ApiException, ClientException {
        try {
            Class<? extends IpInfo> type = "".equals(ip) || ip == null ? RequesterIpInfo.class : IpInfo.class;

            Object result = Request.Get(buildApiUrl(ip, options))
                    .addHeader("User-Agent", USER_AGENT)
                    .connectTimeout(config.getConnectionTimeout())
                    .socketTimeout(config.getSocketTimeout())
                    .execute().handleResponse(response -> {
                        try {
                            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                                return new ObjectMapper()
                                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                        .readValue(response.getEntity().getContent(), type);
                            } else {
                                return createCustomException(response);
                            }
                        } catch (IOException e) {
                            return new ClientException(e);
                        }
                    });

            if (result instanceof IpInfo) {
                return (IpInfo) result;
            }

            throw (ApiException) result;
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }

    private ApiException createCustomException(HttpResponse response) throws IOException {
        LookupError error = new ObjectMapper().readValue(response.getEntity().getContent(), LookupError.class);
        return new ApiException(error.getCode(), error.getMessage(), error.getResolution());
    }

    protected String buildApiUrl(String ip, IpregistryOption... options) {
        StringBuilder result = new StringBuilder();

        result.append(config.getApiUrl());
        result.append('/');

        if (ip != null) {
            result.append(ip);
        }

        result.append("?key=");
        result.append(config.getApiKey());

        for (IpregistryOption option : options) {
            result.append('&');
            result.append(option.getName());
            result.append('=');
            try {
                result.append(URLEncoder.encode(option.getValue(), StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                result.append(option.getValue());
            }
        }

        return result.toString();
    }

    public IpInfoList lookup(Iterable<String> ips, IpregistryOption... options) throws ApiException, ClientException {
        try {
            Object result = Request.Post(buildApiUrl("", options))
                    .bodyString(toJsonList(ips), ContentType.APPLICATION_JSON)
                    .addHeader("User-Agent", USER_AGENT)
                    .connectTimeout(config.getConnectionTimeout())
                    .socketTimeout(config.getSocketTimeout())
                    .execute().handleResponse(response -> {
                        try {
                            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                                return new ObjectMapper()
                                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                        .readValue(response.getEntity().getContent(), IpInfoList.class);
                            } else {
                                return createCustomException(response);
                            }
                        } catch (IOException e) {
                            return new ClientException(e);
                        }
                    });

            if (result instanceof IpInfoList) {
                return (IpInfoList) result;
            }

            throw (ApiException) result;
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }

    private String toJsonList(Iterable<String> ips) {
        return '[' + StreamSupport
                .stream(ips.spliterator(), false)
                .map(ip -> "\"" + ip + "\"")
                .collect(Collectors.joining(",")) + ']';
    }

}
