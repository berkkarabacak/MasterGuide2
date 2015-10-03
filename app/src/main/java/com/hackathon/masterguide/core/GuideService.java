package com.hackathon.masterguide.core;

import com.hackathon.masterguide.domain.Guide;

import java.util.Date;
import java.util.List;

public interface GuideService {

    List<Guide> getGuides(String country, String city, Date startDate, Date endDate);

    Guide getGuide(int id);

}
