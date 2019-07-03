package co.ipregistry.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAgentList {

    @JsonProperty("results")
    Object[] userAgents = null;


    public UserAgentList(int length) {
        this.userAgents = new Object[length];
    }

    public void set(int index, Object o) {
        userAgents[index] = o;
    }

}
