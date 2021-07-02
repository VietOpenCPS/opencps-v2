package org.opencps.api.controller.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static final String _YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String convertDatetoDateTimeString(Date date) {
        if (date == null) {
            return null;
        }
        date.setSeconds(0);
        DateFormat dateFormat = new SimpleDateFormat(_YYYY_MM_DD_HH_MM_SS);
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
