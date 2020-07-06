package org.opencps.constant;

public class Constant {
    public static final String DOMAIN = "http://api.dongthap.gov.vn/api/v1";
    public static final String KEY_AUTHENTICATE = "WVdSdGFXND06WVdSdGFXNUFNZz09";
    public enum ListUrlLGSP {
        GET_TOKEN("/Authentication/Token"),
        GET_SEND_EMAIL("/congdan/SendEmail");

        private final String value;

        ListUrlLGSP(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }
}
