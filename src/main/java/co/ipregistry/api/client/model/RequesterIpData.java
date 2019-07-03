package co.ipregistry.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class RequesterIpData extends IpData {

    @JsonProperty("user_agent")
    private UserAgent userAgent;

    public RequesterIpData(IpData ip, UserAgent userAgent) {
        super();

        super.ip = ip.ip;
        super.type = ip.type;
        super.hostname = ip.hostname;
        super.connection = ip.connection;
        super.currency = ip.currency;
        super.location = ip.location;
        super.security = ip.security;
        super.timeZone = ip.timeZone;

        this.userAgent = userAgent;
    }

}
