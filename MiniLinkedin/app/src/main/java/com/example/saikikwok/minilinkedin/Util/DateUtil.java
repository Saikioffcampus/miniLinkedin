package com.example.saikikwok.minilinkedin.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by saikikwok on 8/26/16.
 */

public class DateUtil {

    private static final String DEFAULT_DATE_FORMAT = "MM-yyyy";

    public static Date string2Date(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.format(date);
    }

}
