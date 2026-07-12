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
import co.ipregistry.api.client.model.UserAgentList;
import co.ipregistry.api.client.model.error.LookupError;
import co.ipregistry.api.client.options.IpregistryOption;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * The default Ipregistry request handler implementation.
 */
public class DefaultRequestHandler implements IpregistryRequestHandler {

    private static final String USER_AGENT = "IpregistryClient/Java/" + getVersion();

    private final IpregistryConfig config;

    private final CloseableHttpClient httpClient;

    private final boolean ownsHttpClient;

    private final ObjectMapper objectMapper;


    /**
     * Creates a {@code DefaultRequestHandler} using the specified {@link IpregistryConfig} instance.
     *
     * @param config the configuration instance to use.
     */
    public DefaultRequestHandler(final IpregistryConfig config) {
        this(config, defaultObjectMapper());
    }

    /**
     * Creates a {@code DefaultRequestHandler} using the specified {@link IpregistryConfig} and {@link ObjectMapper} instances.
     *
     * @param config       the configuration instance.
     * @param objectMapper the object mapper instance used for unmarshalling responses.
     */
    public DefaultRequestHandler(final IpregistryConfig config, final ObjectMapper objectMapper) {
        this(config, objectMapper, buildDefaultHttpClient(config), true);
    }

    /**
     * Creates a {@code DefaultRequestHandler} using a caller-provided {@link CloseableHttpClient}.
     * <p>
     * The caller keeps ownership of the supplied client: it is <em>not</em> closed when this handler
     * (or the enclosing {@link co.ipregistry.api.client.IpregistryClient}) is closed. The client's own
     * request configuration and retry strategy are used; the timeout and retry settings from
     * {@code config} are ignored.
     *
     * @param config     the configuration instance (used for the API key and base URL).
     * @param httpClient the HTTP client to use for dispatching requests.
     */
    public DefaultRequestHandler(final IpregistryConfig config, final CloseableHttpClient httpClient) {
        this(config, defaultObjectMapper(), httpClient, false);
    }

    /**
     * Creates a {@code DefaultRequestHandler} using a caller-provided {@link CloseableHttpClient} and
     * {@link ObjectMapper}.
     * <p>
     * The caller keeps ownership of the supplied client: it is <em>not</em> closed when this handler
     * (or the enclosing {@link co.ipregistry.api.client.IpregistryClient}) is closed.
     *
     * @param config       the configuration instance (used for the API key and base URL).
     * @param objectMapper the object mapper instance used for unmarshalling responses.
     * @param httpClient   the HTTP client to use for dispatching requests.
     */
    public DefaultRequestHandler(final IpregistryConfig config, final ObjectMapper objectMapper,
                                 final CloseableHttpClient httpClient) {
        this(config, objectMapper, httpClient, false);
    }

    private DefaultRequestHandler(final IpregistryConfig config, final ObjectMapper objectMapper,
                                  final CloseableHttpClient httpClient, final boolean ownsHttpClient) {
        this.config = config;
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
        this.ownsHttpClient = ownsHttpClient;
    }

    private static ObjectMapper defaultObjectMapper() {
        return JsonMapper.builder().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).build();
    }

    private static CloseableHttpClient buildDefaultHttpClient(final IpregistryConfig config) {
        final RequestConfig requestConfig =
                RequestConfig.custom()
                        .setConnectionKeepAlive(TimeValue.ofMilliseconds(config.getConnectionKeepAlive()))
                        .setConnectionRequestTimeout(config.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                        .setResponseTimeout(config.getSocketTimeout(), TimeUnit.MILLISECONDS)
                        .build();

        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setRetryStrategy(new IpregistryRetryStrategy(
                        config.getRetryMaxAttempts(),
                        config.getRetryInterval(),
                        config.isRetryOnServerError(),
                        config.isRetryOnTooManyRequests()))
                .build();
    }

    private static String getVersion() {
        final Package classPackage = DefaultRequestHandler.class.getPackage();

        if (classPackage.getSpecificationVersion() == null) {
            return "dev";
        }

        return classPackage.getSpecificationVersion();
    }

    public IpInfo lookup(final String ip, final IpregistryOption... options) throws ApiException, ClientException {
        try {
            final Class<? extends IpInfo> type = "".equals(ip) || ip == null ? RequesterIpInfo.class : IpInfo.class;

            final Object result = Request.get(buildIpLookupUrl(ip, options))
                    .addHeader("authorization", "ApiKey " + config.getApiKey())
                    .addHeader("user-agent", USER_AGENT)
                    .execute(httpClient).handleResponse(response -> {
                        try {
                            if (response.getCode() == HttpStatus.SC_OK) {
                                return objectMapper.readValue(response.getEntity().getContent(), type);
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
        final LookupError error = objectMapper.readValue(response.getEntity().getContent(), LookupError.class);
        return new ApiException(error.getCode(), error.getMessage(), error.getResolution());
    }

    /**
     * Crafts a new URL for the specified {@code ip} and {@code options}.
     *
     * @param ip      the IP address to lookup.
     * @param options the options to pass.
     * @return an API URL for the specified input arguments.
     */
    protected String buildIpLookupUrl(final String ip, final IpregistryOption... options) {
        final StringBuilder result = new StringBuilder();

        result.append(config.getBaseUrl());
        result.append('/');

        if (ip != null) {
            result.append(ip);
        }

        boolean firstOptionHandled = false;
        for (final IpregistryOption option : options) {
            result.append(!firstOptionHandled ? '?' : '&');
            result.append(option.getName());
            result.append('=');
            result.append(URLEncoder.encode(option.getValue(), StandardCharsets.UTF_8));
            firstOptionHandled = true;
        }

        return result.toString();
    }

    public IpInfoList lookup(final Iterable<String> ips, final IpregistryOption... options) throws ApiException, ClientException {
        try {
            final Object result = Request.post(buildIpLookupUrl("", options))
                    .bodyString(toJsonList(ips), ContentType.APPLICATION_JSON)
                    .addHeader("authorization", "ApiKey " + config.getApiKey())
                    .addHeader("user-agent", USER_AGENT)
                    .execute(httpClient).handleResponse(response -> {
                        try {
                            if (response.getCode() == HttpStatus.SC_OK) {
                                return objectMapper.readValue(response.getEntity().getContent(), IpInfoList.class);
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

    @Override
    public UserAgentList parse(final String... userAgents) throws ApiException, ClientException {
        try {
            final Object result = Request.post(config.getBaseUrl() + "/user_agent")
                    .bodyString(toJsonList(Arrays.asList(userAgents)), ContentType.APPLICATION_JSON)
                    .addHeader("authorization", "ApiKey " + config.getApiKey())
                    .connectTimeout(Timeout.ofMilliseconds(config.getConnectionTimeout()))
                    .responseTimeout(Timeout.ofMilliseconds(config.getSocketTimeout()))
                    .execute(httpClient).handleResponse(response -> {
                        try {
                            if (response.getCode() == HttpStatus.SC_OK) {
                                return objectMapper.readValue(response.getEntity().getContent(), UserAgentList.class);
                            } else {
                                return createCustomException(response);
                            }
                        } catch (final IOException e) {
                            return new ClientException(e);
                        }
                    });

            if (result instanceof UserAgentList) {
                return (UserAgentList) result;
            }

            throw (ApiException) result;
        } catch (final IOException e) {
            throw new ClientException(e);
        }
    }

    /**
     * Serializes the specified {@code values} into a JSON array, escaping each value so that
     * characters such as double quotes, backslashes, and control characters produce a valid
     * JSON body rather than a malformed or injectable one.
     *
     * @param values the values to serialize.
     * @return a JSON array representation of the specified {@code values}.
     * @throws JacksonException if the values cannot be serialized.
     */
    String toJsonList(final Iterable<String> values) throws JacksonException {
        final List<String> list = new ArrayList<>();
        values.forEach(list::add);
        return objectMapper.writeValueAsString(list);
    }

    @Override
    public void close() throws IOException {
        // Only close the HTTP client if this handler created it; a caller-provided client
        // remains under the caller's ownership.
        if (ownsHttpClient) {
            httpClient.close();
        }
    }

}
