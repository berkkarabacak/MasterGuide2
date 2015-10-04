package com.hackathon.masterguide.core;

public class SessionManagerFactory {

    private final static SessionManagerFactory INSTANCE = new SessionManagerFactory();
    private final SessionManager manager;

    private SessionManagerFactory() {
        manager = new InMemorySessionManager();
    }

    public SessionManagerFactory instance() {
        return INSTANCE;
    }

    public SessionManager getManager() {
        return manager;
    }

}
