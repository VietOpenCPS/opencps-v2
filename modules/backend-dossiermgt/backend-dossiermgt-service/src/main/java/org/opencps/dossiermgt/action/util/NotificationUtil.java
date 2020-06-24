package org.opencps.dossiermgt.action.util;

public class NotificationUtil {
	private static final String SMS_TRUE = "sms=true";
	private static final String EMAIL_TRUE = "email=true";
	private static final String COMMA = ",";
	
	public static boolean isSendSMS(String condition) {
		String[] conditionArr = condition.split(COMMA);
		for (String tempCondition : conditionArr) {
			if (SMS_TRUE.equalsIgnoreCase(tempCondition)) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean isSendEmail(String condition) {
		String[] conditionArr = condition.split(COMMA);
		for (String tempCondition : conditionArr) {
			if (EMAIL_TRUE.equalsIgnoreCase(tempCondition)) {
				return true;
			}
		}
		
		return false;
	}
}
