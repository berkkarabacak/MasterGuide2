package com.hackathon.masterguide.core;

import com.hackathon.masterguide.domain.Guide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StubGuideService implements GuideService {

    private final List<Guide> guides = new ArrayList<Guide>();
    private final DateFormat formatter = new SimpleDateFormat("MM/dd/yy");

    public StubGuideService() {
        guides.add(new Guide(1, "Ahmet", "Yılmaz", "Türkiye", "İstanbul", getDate("03-10-2015")));
    }

    private Date getDate(String date) {
        Date result = null;
        try {
            result = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Guide> getGuides(String country, String city, Date startDate, Date endDate) {
        return null;
    }

}
