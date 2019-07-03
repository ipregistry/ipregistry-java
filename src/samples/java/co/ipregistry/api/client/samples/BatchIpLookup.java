package co.ipregistry.api.client.samples;

import co.ipregistry.api.client.Ipregistry;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.exceptions.IpDataException;
import co.ipregistry.api.client.model.IpData;
import co.ipregistry.api.client.model.IpDataList;

import java.util.Arrays;

public class BatchIpLookup {

    public static void main(String[] args) {
        Ipregistry ipregistry = new Ipregistry("tryout");

        try {
            IpDataList ipdataList =
                    ipregistry.lookup(Arrays.asList("73.2.2.2", "8.8.8.8", "2001:67c:2e8:22::c100:68b"));

            for (int i = 0; i < ipdataList.size(); i++) {
                try {
                    IpData ipdata = ipdataList.get(i);
                    // Here is an example to print out the country name associated with each IP address
                    System.out.println(ipdata.getLocation().getCountry().getName());
                } catch (IpDataException e) {
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
