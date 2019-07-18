package co.ipregistry.api.client.samples;

import co.ipregistry.api.client.IpregistryClient;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.RequesterIpInfo;

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
