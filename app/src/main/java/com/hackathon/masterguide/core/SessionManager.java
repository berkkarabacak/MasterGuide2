package com.hackathon.masterguide.core;

public interface SessionManager {

    boolean login(String email, String password);

    boolean isAuthenticated();

}
