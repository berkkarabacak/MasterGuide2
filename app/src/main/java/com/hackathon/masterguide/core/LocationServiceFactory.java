package com.hackathon.masterguide.core;

public class LocationServiceFactory {

    private static final LocationServiceFactory INSTANCE = new LocationServiceFactory();
    private final LocationService service;

    private LocationServiceFactory() {
        service = new MockLocationService();
    }

    public static LocationServiceFactory instance() {
        return INSTANCE;
    }

    public LocationService getService() {
        return service;
    }

}

