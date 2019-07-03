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
public class UserAgentDevice {

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

}
