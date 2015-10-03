package com.hackathon.masterguide.core;

import java.util.Arrays;
import java.util.List;

public class MockLocationService implements LocationService {

    public List<String> getCountries() {
        return Arrays.asList("Türkiye");
    }

    public List<String> getCities(String country) {
        return Arrays.asList("İstanbul");
    }

}
