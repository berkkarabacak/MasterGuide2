package com.hackathon.masterguide.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySessionManager implements SessionManager {

    private boolean auth = false;

    @Override
    public boolean login(String email, String password) {
        if ("admin@host.com".equalsIgnoreCase(email) && "admin".equalsIgnoreCase(password)) {
            auth = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isAuthenticated() {
        return auth;
    }

}
