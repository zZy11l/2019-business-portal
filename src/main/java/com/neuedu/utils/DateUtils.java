package com.neuedu.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by tao on 2019/8/16.
 */
public class DateUtils {

    private static final String STANDARD_FORMAT="yyy-MM-dd HH:mm:ss";

    /*
    * Date->string
    * */

    public static String dateToStr(Date date){
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    /*
    * String->Date
    * */
    public static Date strToDate(String str){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime=dateTimeFormatter.parseDateTime(str);
        return dateTime.toDate();

    }


}
