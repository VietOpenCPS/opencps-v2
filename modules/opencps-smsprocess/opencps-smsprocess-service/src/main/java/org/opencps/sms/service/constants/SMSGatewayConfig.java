package org.opencps.sms.service.constants;

public interface SMSGatewayConfig {
     String GW_USER_NAME = "";
     String GQ_PASSWORD = "";
     
     interface MessageTypes{
         String TEXT = "text";
         String TEXT_UCS2 = "text_ucs2";
         String BOOKMARK_URL = "BookmarkURL";
         String SERVICE_INDICATION = "ServiceIndication";
         String MONORING_TONE = "MonoRingtone";
         String OPERATOR_LOGO = "OperatorLogo";
         String PICTURE_MESSAGE = "PictureMessage";
     }
}
