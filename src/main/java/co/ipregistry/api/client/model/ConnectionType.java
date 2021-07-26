package co.ipregistry.api.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum ConnectionType {

    BUSINESS("business"),
    EDUCATION("education"),
    GOVERNMENT("government"),
    HOSTING("hosting"),
    ISP("isp");

    @JsonValue
    String name;

    ConnectionType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @JsonCreator
    public static ConnectionType from(final String value) {
        switch (value) {
            case "education":
                return ConnectionType.EDUCATION;
            case "government":
                return ConnectionType.GOVERNMENT;
            case "hosting":
                return ConnectionType.HOSTING;
            case "isp":
                return ConnectionType.ISP;
            default:
                return ConnectionType.BUSINESS;
        }
    }

}
