package co.ipregistry.api.client.request;

import co.ipregistry.api.client.exceptions.IpregistryException;
import co.ipregistry.api.client.model.IpData;
import co.ipregistry.api.client.model.IpDataList;
import co.ipregistry.api.client.options.IpregistryOption;

public interface IpregistryRequestHandler {

    /**
     * Lookup IP data such as geolocation data, currency, timezone, threat information and more
     * for the specified {@code ip} address by using the Ipregistry API.
     *
     * @param ip      The IPv4 or IPv6 address to lookup information for, using respectively the dotted-decimal
     *                or the colon-hexadecimal notation.
     * @param options An optional set of {@link IpregistryOption}.
     * @return the data found for the specified {@code ip}.
     * @throws IpregistryException if an error happens when looking up data.
     *                             It can be an {@link co.ipregistry.api.client.exceptions.ApiException}
     *                             or a {@link co.ipregistry.api.client.exceptions.ClientException}.
     */
    IpData lookup(String ip, IpregistryOption... options) throws IpregistryException;

    /**
     * Lookup IP data such as geolocation data, currency, timezone, threat information and more
     * for the specified collection of {@code ips} addresses by using the Ipregistry API.
     *
     * @param ips     The IPv4 or IPv6 addresses to lookup information for. The IP address format must use
     *                the dotted-decimal or the colon-hexadecimal notation.
     * @param options An optional set of {@link IpregistryOption}.
     * @return the data found for the specified {@code ips}.
     * @throws IpregistryException if an error happens when looking up data.
     *                             It can be an {@link co.ipregistry.api.client.exceptions.ApiException}
     *                             or a {@link co.ipregistry.api.client.exceptions.ClientException}.
     */
    IpDataList lookup(Iterable<String> ips, IpregistryOption... options) throws IpregistryException;

}
