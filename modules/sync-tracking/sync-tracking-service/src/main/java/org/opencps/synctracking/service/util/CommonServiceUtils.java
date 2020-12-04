package org.opencps.synctracking.service.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonServiceUtils {
    public static Date stringToDate(String dateTime, String formatDate) {
        try {
            DateFormat inputFormatter = new SimpleDateFormat(formatDate);
            return inputFormatter.parse(dateTime);
        } catch (Exception e) {
            System.out.println("Error when transform string => date");
            return null;
        }
    }
}
