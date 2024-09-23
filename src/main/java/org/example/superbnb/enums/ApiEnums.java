package org.example.superbnb.enums;

public enum ApiEnums {
    SUPERBNB("api/v1/superbnb");

    public static final String SUPERBNB_API = "api/v1/superbnb";

    private final String api;

    ApiEnums(String api) {
        this.api = api;
    }

    public String getApi(){
        return api;
    }
}
