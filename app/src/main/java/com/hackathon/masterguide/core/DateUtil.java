package com.hackathon.masterguide.core;

import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

}
