package co.ipregistry.api.client.samples;

import co.ipregistry.api.client.IpregistryClient;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpData;
import co.ipregistry.api.client.model.RequesterIpData;

public class SingleIpLookup {

    public static void main(String[] args) {
        IpregistryClient ipregistryClient = new IpregistryClient("tryout");

        try {
            // Lookup IP data for the current node and network interface used to execute this code
            RequesterIpData requesterIpData = ipregistryClient.lookup();
            System.out.println(requesterIpData.getLocation().getCountry().getName());
            System.out.println(requesterIpData);

            // Here is another example to lookup data for a given IP address
            // You may already have it or you can get the client IP from a request header
            IpData ipData = ipregistryClient.lookup("54.85.132.205");
            System.out.println(ipData);
        } catch (ApiException e) {
            // Handle API errors (e.g. insufficient credits, throttling) here
            e.printStackTrace();
        } catch (ClientException e) {
            // Handle client errors (e.g. network error) here
            e.printStackTrace();
        }
    }

}
