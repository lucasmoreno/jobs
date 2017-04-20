package br.com.testepaggi.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class DateUtils {

    public static Date parseStringToDate(String dataStr){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return convertedDate;
    }

    public static String parseDateToString(Date date){

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static String getYearFromDate(Date date){

        Format formatter = new SimpleDateFormat("yyyy");
        return formatter.format(date);
    }

    public static String parseDateToQueryString(Date date){

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
