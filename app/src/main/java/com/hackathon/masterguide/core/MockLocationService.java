package com.hackathon.masterguide.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockLocationService implements LocationService {

    private final List<String> countries = new ArrayList<String>();
    private final Map<String, List<String>> cities = new HashMap<String, List<String>>();

    public MockLocationService() {
        countries.add("Austria");
        countries.add("Czech Republic");
        countries.add("Denmark");
        countries.add("Germany");
        countries.add("United Kingdom");
        countries.add("USA");
        countries.add("Turkey");

        cities.put("Turkey", Arrays.asList("Adana",
                "Afyonkarahisar",
                "Aksaray",
                "Ankara",
                "Antalya",
                "Artvin",
                "Aydın",
                "Balıkesir",
                "Bartın",
                "Batman",
                "Bayburt",
                "Burdur",
                "Bursa",
                "Çanakkale",
                "Çankırı",
                "Denizli",
                "Diyarbakır",
                "Düzce",
                "Elazığ",
                "Erzincan",
                "Erzurum",
                "Eskişehir",
                "Gaziantep",
                "Giresun",
                "Gümüşhane",
                "Hatay",
                "Iğdır",
                "Isparta",
                "İstanbul",
                "Kahramanmaraş",
                "Karabük",
                "Karaman",
                "Kars",
                "Kastamonu",
                "Kayseri",
                "Kırıkkale",
                "Kırklareli",
                "Kırşehir",
                "Kocaeli",
                "Konya",
                "Malatya",
                "Manisa",
                "Mardin",
                "Mersin",
                "Muğla",
                "Muş",
                "Nevşehir",
                "Niğde",
                "Ordu",
                "Rize",
                "Sakarya",
                "Samsun",
                "Siirt",
                "Sinop",
                "Sivas",
                "Şanlıurfa",
                "Şırnak",
                "Tekirdağ",
                "Tokat",
                "Trabzon",
                "Tunceli",
                "Uşak",
                "Van",
                "Yozgat",
                "Zonguldak"));
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getCities(String country) {
        List<String> ct = cities.get(country);
        return ct != null ? ct : new ArrayList<String>();
    }

}
