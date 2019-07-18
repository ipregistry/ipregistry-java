package co.ipregistry.api.client.samples;

import co.ipregistry.api.client.IpregistryClient;
import co.ipregistry.api.client.IpregistryConfig;
import co.ipregistry.api.client.cache.DefaultCache;
import co.ipregistry.api.client.cache.ValuesReferenceType;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpInfo;

public class IpLookupAdvancedCacheConfiguration {

    public static void main(String[] args) {
        IpregistryConfig config =
                IpregistryConfig.builder()
                        .apiKey("tryout").build();

        DefaultCache cache =
                DefaultCache.builder()
                        .concurrencyLevel(16)
                        .expireAfter(3600000)
                        .initialCapacity(512)
                        .maximumSize(4096)
                        .referenceType(ValuesReferenceType.WEAK)
                        .build();

        IpregistryClient client = new IpregistryClient(config, cache);

        try {
            IpInfo ipInfo = client.lookup("8.8.8.8");
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
