package com.example.shuttleLaunch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChecker {
    private final String year;
    private final String month;
    private final String day;

    public DateChecker(String year, String month, String day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isThisDateValid(String dateToValidate){

        if(dateToValidate == null){
            return false;
        }
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);


        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

}
