package co.ipregistry.api.client.options;

/**
 * Utility class to ease the construction of {@link IpregistryOption}s.
 */
public final class IpregistryOptions {

    private IpregistryOptions() {

    }

    /**
     * Creates a new {@link FilterOption} using the specified {@code expression}.
     *
     * @param expression the filter expression to use.
     * @return the new filter option instance.
     */
    public static FilterOption filter(final String expression) {
        return new FilterOption(expression);
    }

    /**
     * Creates a new {@link HostnameOption} using the specified {@code hostname}.
     *
     * @param hostname whether to enable hostname lookup.
     * @return the new hostname option instance.
     */
    public static HostnameOption hostname(final boolean hostname) {
        return new HostnameOption(hostname);
    }

    /**
     * Creates a new {@link IpregistryOption} from the specified arguments.
     *
     * @param name  the option name.
     * @param value the option value.
     * @return the new option instance.
     */
    public static IpregistryOption from(final String name, final String value) {
        return new IpregistryOption(name, value);
    }

}
