package com.hackathon.masterguide.core;

public interface SessionManager {

    boolean login(String userName, String password);

    boolean isAuthenticated();

}
