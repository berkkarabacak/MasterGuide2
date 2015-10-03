package com.hackathon.masterguide.core;

public class GuideServiceFactory {

    private static final GuideServiceFactory INSTANCE = new GuideServiceFactory();
    private final GuideService service;

    private GuideServiceFactory() {
        service = new StubGuideService();
    }

    public GuideServiceFactory instance() {
        return INSTANCE;
    }

    public GuideService getService() {
        return service;
    }

}
