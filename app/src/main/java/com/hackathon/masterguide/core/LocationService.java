package com.hackathon.masterguide.core;

import java.util.List;

public interface LocationService {

    List<String> getCountries();

    List<String> getCities(String country);

}
