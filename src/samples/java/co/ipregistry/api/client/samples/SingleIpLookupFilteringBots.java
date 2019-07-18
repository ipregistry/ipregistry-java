package co.ipregistry.api.client.samples;

import co.ipregistry.api.client.IpregistryClient;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.model.IpData;
import co.ipregistry.api.client.util.UserAgent;

public class SingleIpLookupFilteringBots {

    public static void main(String[] args) {
        IpregistryClient ipregistryClient = new IpregistryClient("tryout");

        // For testing purposes, you can retrieve you current user agent from:
        // https://api.ipregistry.co/user_agent?key=tryout (look at the field named "header")
        if (UserAgent.isBot("TO_REPLACE_BY_USER_AGENT_RETRIEVED_FROM_REQUEST_HEADER")) {
            try {
                IpData ipdata = ipregistryClient.lookup("8.8.8.8");
                // Here is an example to print out the country name associated with the IP address
                System.out.println(ipdata.getLocation().getCountry().getName());
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
