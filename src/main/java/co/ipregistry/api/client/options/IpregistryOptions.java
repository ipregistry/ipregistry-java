package co.ipregistry.api.client.options;

public final class IpregistryOptions {

    private IpregistryOptions() {

    }

    public static FilterOption filter(String fields) {
        return new FilterOption(fields);
    }

    public static HostnameOption hostname(boolean hostname) {
        return new HostnameOption(hostname);
    }

    public static IpregistryOption from(String name, String value) {
        return new IpregistryOption(name, value);
    }

}
