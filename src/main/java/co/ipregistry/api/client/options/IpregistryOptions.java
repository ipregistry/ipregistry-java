package co.ipregistry.api.client.options;

public final class IpregistryOptions {

    private IpregistryOptions() {

    }

    public static FilterOption filter(final String fields) {
        return new FilterOption(fields);
    }

    public static HostnameOption hostname(final boolean hostname) {
        return new HostnameOption(hostname);
    }

    public static IpregistryOption from(final String name, final String value) {
        return new IpregistryOption(name, value);
    }

}
