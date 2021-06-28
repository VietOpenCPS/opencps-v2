package org.opencps.synctracking.service.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonServiceUtils {
    private static Log _log = LogFactoryUtil.getLog(CommonServiceUtils.class);
    public static Date stringToDate(String dateTime, String formatDate) {
        try {
            DateFormat inputFormatter = new SimpleDateFormat(formatDate);
            return inputFormatter.parse(dateTime);
        } catch (Exception e) {
            _log.error(e);
            return null;
        }
    }
}
