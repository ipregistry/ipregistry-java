package co.ipregistry.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserAgent {

    @JsonProperty("header")
    private String header;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("version")
    private String version;

    @JsonProperty("version_major")
    private String versionMajor;

    @JsonProperty("device")
    private UserAgentDevice device;

    @JsonProperty("engine")
    private UserAgentEngine engine;

    @JsonProperty("os")
    private UserAgentOperatingSystem operatingSystem;

}
