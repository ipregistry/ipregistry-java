[<img src="https://cdn.ipregistry.co/icons/icon-72x72.png" alt="Ipregistry" width="64"/>](https://ipregistry.co/) 
# Ipregistry Java Client Library

[![License](http://img.shields.io/:license-apache-blue.svg)](LICENSE)
[![Actions Status](https://github.com/ipregistry/ipregistry-java/workflows/Java%20CI/badge.svg)](https://github.com/ipregistry/ipregistry-java/actions)
[![Maven Central](https://img.shields.io/maven-central/v/co.ipregistry/ipregistry-client.svg)](https://search.maven.org/search?q=g:co.ipregistry%20AND%20a:ipregistry-client)
[![Javadocs](https://www.javadoc.io/badge/co.ipregistry/ipregistry-client.svg)](https://www.javadoc.io/doc/co.ipregistry/ipregistry-client)

This is the official Java client library for the [Ipregistry](https://ipregistry.co) IP geolocation and threat data API, 
allowing you to lookup your own IP address or specified ones. Responses return up to 65 data points including 
location, currency, timezone, threat information, and more.

## Getting Started

You'll need an Ipregistry API key, which you can get along with 100,000 free lookups by signing up for a free account at [https://ipregistry.co](https://ipregistry.co).

### Installation

#### Maven

```xml
<dependency>
    <groupId>co.ipregistry</groupId>
    <artifactId>ipregistry-client</artifactId>
    <version>3.1.1</version>
</dependency>
```

#### Gradle

```
implementation 'co.ipregistry:ipregistry-client:3.1.1'
```

### Quick start

#### Single IP Lookup

```java
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpInfo;

public class SingleIpLookup {

    public static void main(String[] args) {
        IpregistryClient client = new IpregistryClient("tryout");

        try {
            // Lookup IP data for the current node and network interface used to execute this code
            RequesterIpInfo requesterIpInfo = client.lookup();
            System.out.println(requesterIpInfo.getLocation().getCountry().getName());
            System.out.println(requesterIpInfo);

            // Here is another example to lookup data for a given IP address
            // You may already have it or you can get the client IP from a request header
            IpInfo ipInfo = client.lookup("54.85.132.205");
            System.out.println(ipInfo);
        } catch (ApiException e) {
            // Handle API errors (e.g. insufficient credits, throttling) here
            e.printStackTrace();
        } catch (ClientException e) {
            // Handle client errors (e.g. network error) here
            e.printStackTrace();
        }
    }
    
}
```

#### Batch IP Lookup

```java
import co.ipregistry.api.client.ipregistrygistry;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.exceptions.IpInfoException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.IpInfoList;

import java.util.Arrays;

public class BatchIpLookup {

    public static void main(String[] args) {
        IpregistryClient client = new IpregistryClient("tryout");

        try {
            IpInfoList ipInfoList =
                    client.lookup(Arrays.asList("73.2.2.2", "8.8.8.8", "2001:67c:2e8:22::c100:68b"));

            for (int i = 0; i < ipInfoList.size(); i++) {
                try {
                    IpInfo ipInfo = ipInfoList.get(i);
                    // Here is an example to print out the country name associated with each IP address
                    System.out.println(ipInfo.getLocation().getCountry().getName());
                } catch (IpInfoException e) {
                    // Handle batch lookup error (e.g. invalid IP address) here
                    e.printStackTrace();
                }
            }
        } catch (ApiException e) {
            // Handle API errors (e.g. insufficient credits, throttling) here
            e.printStackTrace();
        } catch(ClientException e) {
            // Handle client errors (e.g. network error) here
            e.printStackTrace();
        }
    }

}
```

## Caching

Although the Ipregistry client library has built-in support for in-memory caching, it is disabled by default to ensure data freshness.

To enable in-memory caching, pass an instance of _InMemoryCache_ to the Ipregistry client:

```java
IpregistryConfig config =
        IpregistryConfig.builder()
                .apiKey("YOUR_API_KEY").build();

IpregistryClient ipregistry = new IpregistryClient(config, InMemoryCache.builder().build());
```

The _InMemoryCache_ implementation supports multiple eviction policies (e.g. size based, time based):

```java
InMemoryCache cache =
        InMemoryCache.builder()
                .concurrencyLevel(16)
                .expireAfter(600 * 1000)
                .initialCapacity(512)
                .maximumSize(4096)
                .build();
```

You can also provide your own cache implementation by implementing the _IpregistryCache_ interface.

## Errors

All Ipregistry exceptions inherit the _IpregistryException_ class.

Main subtypes are _ApiException_ and _ClientException_.

Exceptions of type _ApiException_ include a code field that maps to the one described in the [Ipregistry documentation](https://ipregistry.co/docs/errors).

## Filtering bots

You might want to prevent Ipregistry API calls for crawlers or bots browsing your pages. 

A manner to proceed is to identify bots from the user agent. To ease this process, 
the library includes a utility method:

```java
import co.ipregistry.api.client.ipregistrygistry;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.util.UserAgent;

public class SingleIpLookupFilteringBots {

    public static void main(String[] args) {
        IpregistryClient client = new IpregistryClient("tryout");

        // For testing purposes, you can retrieve you current user agent from:
        // https://api.ipregistry.co/user_agent?key=tryout (look at the field named "user_agent")
        if (UserAgent.isBot("TO_REPLACE_BY_USER_AGENT_RETRIEVED_FROM_REQUEST_HEADER")) {
            try {
                IpData ipInfo = client.lookup("8.8.8.8");
                // Here is an example to print out the country name associated with the IP address
                System.out.println(ipInfo.getLocation().getCountry().getName());
            } catch (ApiException e) {
                // Handle API errors (e.g. insufficient credits, throttling) here
                e.printStackTrace();
            } catch (ClientException e) {
                // Handle client errors (e.g. network error) here
                e.printStackTrace();
            }
        }
    }

}
```

# Other Libraries

There are official Ipregistry client libraries available for many languages including 
[Javascript](https://github.com/ipregistry/ipregistry-javascript), 
[Python](https://github.com/ipregistry/ipregistry-python), 
[Typescript](https://github.com/ipregistry/ipregistry-javascript) and more.

Are you looking for an official client with a programming language or framework we do not support yet? 
[let us know](mailto:support@ipregistry.co). 
