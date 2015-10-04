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
        guides.add(new Guide(1, "Corina", "Ahrens", "Germany", "Berlin", getDate("03-10-2015"), 23, "Merhaba, bunlar benim detayım."));
        guides.add(new Guide(2, "Karl", "Kaiser", "Germany", "Berlin", getDate("03-10-2015"), 18, "Merhaba, bunlar benim detayım."));
        guides.add(new Guide(3, "Zanis", "Yılmaz", "Germany", "Berlin", getDate("03-10-2015"), 19, "Merhaba, bunlar benim detayım."));
        guides.add(new Guide(4, "Linnet", "Terry", "Germany", "Magdeburg", getDate("03-10-2015"), 26, "Merhaba, bunlar benim detayım."));
        guides.add(new Guide(5, "Rick", "Granovsky", "Germany", "Hamburg", getDate("03-10-2015"), 21, "Merhaba, bunlar benim detayım."));
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
        List<Guide> guideList = new ArrayList<Guide>();

        for (Guide g : guides) {
            String cntry = g.getCountry();
            String cty = g.getCity();
            Date time = g.getTime();

            if (cntry == null || cty == null || time == null) {
                continue;
            }

            if (cntry.equalsIgnoreCase(country)
                    && cty.equalsIgnoreCase(city)
                    && time.before(endDate)
                    && time.after(startDate)) {

                guideList.add(g);
            }
        }
        return guideList;
    }

    @Override
    public Guide getGuide(int id) {
        for (Guide g : guides) {
            if (g.getId() == id) {
                return g;
            }
        }
        return null;
    }

}
