package co.ipregistry.api.client.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ConnectionType {

    BUSINESS("business"),
    EDUCATION("education"),
    HOSTING("hosting"),
    ISP("isp");

    @JsonValue
    String name;

    ConnectionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}