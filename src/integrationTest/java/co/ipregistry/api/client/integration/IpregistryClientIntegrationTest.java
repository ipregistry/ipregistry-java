package co.ipregistry.api.client.integration;

import co.ipregistry.api.client.IpregistryClient;
import co.ipregistry.api.client.exceptions.ApiException;
import co.ipregistry.api.client.exceptions.ClientException;
import co.ipregistry.api.client.exceptions.IpInfoException;
import co.ipregistry.api.client.model.ConnectionType;
import co.ipregistry.api.client.model.IpInfo;
import co.ipregistry.api.client.model.IpInfoList;
import co.ipregistry.api.client.model.IpType;
import co.ipregistry.api.client.options.IpregistryOptions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.net.InetAddresses;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class IpregistryClientIntegrationTest {

    private String apiKey;

    IpregistryClientIntegrationTest() {
        this.apiKey = System.getenv("IPREGISTRY_API_KEY");

        if (this.apiKey == null) {
            this.apiKey = "tryout";
        }
    }

    @Test
    void testSingleLookupIpv4() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("8.8.8.8");

        Assertions.assertEquals(ipInfo.getIp(), "8.8.8.8");
        Assertions.assertEquals(ipInfo.getType(), IpType.IPV4);
        Assertions.assertNotNull(ipInfo.getConnection());
        Assertions.assertNotNull(ipInfo.getCurrency());
        Assertions.assertNotNull(ipInfo.getCurrency().getNameNative());
        Assertions.assertNotNull(ipInfo.getCurrency().getPluralNative());
        Assertions.assertNotNull(ipInfo.getCurrency().getSymbolNative());
        Assertions.assertNull(ipInfo.getHostname());
        Assertions.assertNotNull(ipInfo.getLocation());
        Assertions.assertNotNull(ipInfo.getSecurity());
        Assertions.assertNotNull(ipInfo.getTimeZone());
    }

    @Test
    void testSingleLookupIpv6() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("2001:4860:4860::8844");

        Assertions.assertEquals(ipInfo.getIp(), "2001:4860:4860::8844");
        Assertions.assertEquals(ipInfo.getType(), IpType.IPV6);
        Assertions.assertNotNull(ipInfo.getConnection());
        Assertions.assertNotNull(ipInfo.getCurrency());
        Assertions.assertNull(ipInfo.getHostname());
        Assertions.assertNotNull(ipInfo.getLocation());
        Assertions.assertNotNull(ipInfo.getSecurity());
        Assertions.assertNotNull(ipInfo.getTimeZone());
    }

    @Test
    void testSingleLookupWithHostname() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("8.8.8.8", IpregistryOptions.hostname(true));

        Assertions.assertEquals(ipInfo.getIp(), "8.8.8.8");
        Assertions.assertEquals(ipInfo.getType(), IpType.IPV4);
        Assertions.assertNotNull(ipInfo.getConnection());
        Assertions.assertNotNull(ipInfo.getCurrency());
        Assertions.assertNotNull(ipInfo.getHostname());
        Assertions.assertNotNull(ipInfo.getLocation());
        Assertions.assertNotNull(ipInfo.getSecurity());
        Assertions.assertNotNull(ipInfo.getTimeZone());
    }

    @Test
    void testSingleLookupWithFilter() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("8.8.8.8", IpregistryOptions.filter("connection"));

        Assertions.assertNull(ipInfo.getIp(), "8.8.8.8");
        Assertions.assertEquals(ipInfo.getType(), IpType.UNKNOWN);
        Assertions.assertNotNull(ipInfo.getConnection());
        Assertions.assertNotNull(ipInfo.getCurrency());
        Assertions.assertNull(ipInfo.getHostname());
        Assertions.assertNotNull(ipInfo.getLocation());
        Assertions.assertNotNull(ipInfo.getSecurity());
        Assertions.assertNotNull(ipInfo.getTimeZone());
    }

    @Test
    void testSingleLookupFromInetAddress() throws ClientException, ApiException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup(InetAddresses.forString("8.8.8.8"));

        Assertions.assertEquals(ipInfo.getIp(), "8.8.8.8");
        Assertions.assertEquals(ipInfo.getType(), IpType.IPV4);
        Assertions.assertNotNull(ipInfo.getConnection());
        Assertions.assertNotNull(ipInfo.getCurrency());
        Assertions.assertNull(ipInfo.getHostname());
        Assertions.assertNotNull(ipInfo.getLocation());
        Assertions.assertNotNull(ipInfo.getSecurity());
        Assertions.assertNotNull(ipInfo.getTimeZone());
    }

    @Test
    void testSingleLookup_CarrierData() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("37.173.153.166");

        Assertions.assertEquals(ipInfo.getIp(), "37.173.153.166");
        Assertions.assertEquals(ipInfo.getType(), IpType.IPV4);
        Assertions.assertNotNull(ipInfo.getCarrier());
        Assertions.assertNotNull(ipInfo.getCarrier().getName());
        Assertions.assertNotNull(ipInfo.getCarrier().getMcc());
        Assertions.assertNotNull(ipInfo.getCarrier().getMnc());
    }


    @Test
    void testSingleLookup_NoCarrierData() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("1.1.1.1");

        Assertions.assertEquals(ipInfo.getIp(), "1.1.1.1");
        Assertions.assertEquals(ipInfo.getType(), IpType.IPV4);
        Assertions.assertNotNull(ipInfo.getCarrier());
        Assertions.assertNull(ipInfo.getCarrier().getName());
        Assertions.assertNull(ipInfo.getCarrier().getMcc());
        Assertions.assertNull(ipInfo.getCarrier().getMnc());
    }

    @Test
    void testSingleLookup_ConnectionType() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("37.173.153.166");

        Assertions.assertEquals(ipInfo.getConnection().getType(), ConnectionType.ISP);
    }

    @Test
    void testSingleLookup_Domain() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("37.173.153.166");

        Assertions.assertEquals(ipInfo.getConnection().getDomain(), "mobile.free.fr");
    }

    @Test
    void testSingleLookup_Security_IsCloudProvider() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup("34.67.189.189");

        Assertions.assertTrue(ipInfo.getSecurity().isCloudProvider());
    }

    @Test
    void testSingleLookup_InvalidIp() {
        final IpregistryClient client = new IpregistryClient(apiKey);
        Assertions.assertThrows(ApiException.class, () -> client.lookup("oops"));
    }

    @Test
    void testSingleLookup_InvalidIp_ValidResource() {
        final IpregistryClient client = new IpregistryClient(apiKey);
        Assertions.assertThrows(ApiException.class, () -> client.lookup("robots.txt"));
    }

    @Test
    void testBatchLookup() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final List<String> ips = Lists.newArrayList("66.165.2.7", "1.1.1.1", "8.8.4.4");
        final IpInfoList ipInfoList = client.lookup(ips);

        for (int i = 0; i < ipInfoList.size(); i++) {
            final IpInfo ipInfo = ipInfoList.get(i);

            Assertions.assertEquals(ipInfo.getIp(), ips.get(i));
            Assertions.assertEquals(ipInfo.getType(), IpType.IPV4);
            Assertions.assertNotNull(ipInfo.getConnection());
            Assertions.assertNotNull(ipInfo.getCurrency());
            Assertions.assertNull(ipInfo.getHostname());
            Assertions.assertNotNull(ipInfo.getLocation());
            Assertions.assertNotNull(ipInfo.getSecurity());
            Assertions.assertNotNull(ipInfo.getTimeZone());
        }
    }

    @Test
    void testBatchLookupError() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final List<String> ips = Lists.newArrayList("66.165.2.OOPS");
        final IpInfoList ipInfoList = client.lookup(ips);
        Assertions.assertThrows(IpInfoException.class, () -> ipInfoList.get(0));
    }

    @Test
    void testBatchLookup_InvalidIp_ValidResource() throws ClientException, ApiException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfoList ipInfoList = client.lookup(ImmutableList.of("robots.txt"));
        Assertions.assertThrows(IpInfoException.class, () -> ipInfoList.get(0));
    }

    @Test
    void testOriginLookup() throws ApiException, ClientException {
        final IpregistryClient client = new IpregistryClient(apiKey);
        final IpInfo ipInfo = client.lookup();

        Assertions.assertNotNull(ipInfo.getIp());
        Assertions.assertNotNull(ipInfo.getConnection());
        Assertions.assertNotNull(ipInfo.getCurrency());
        Assertions.assertNull(ipInfo.getHostname());
        Assertions.assertNotNull(ipInfo.getLocation());
        Assertions.assertNotNull(ipInfo.getSecurity());
        Assertions.assertNotNull(ipInfo.getTimeZone());
    }

}
