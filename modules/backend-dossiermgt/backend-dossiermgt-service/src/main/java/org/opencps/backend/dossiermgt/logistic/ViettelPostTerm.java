package org.opencps.backend.dossiermgt.logistic;

public class ViettelPostTerm {
    public static final String SERVER_NO = "VNPOST";
    public static final String PROTOCOL = "VIA_POSTAL";

    public static final String TOKEN = "token";
    public static final String USER_NAME = "USERNAME";
    public static final String PASSWORD  = "PASSWORD";
    public static final String ORDER_NUMBER = "ORDER_NUMBER";
    public static final String GROUPADDRESS_ID = "GROUPADDRESS_ID";
    public static final String CUS_ID = "CUS_ID";
    public static final String DELIVERY_DATE = "DELIVERY_DATE";
    public static final String SENDER_FULLNAME = "SENDER_FULLNAME";
    public static final String SENDER_ADDRESS = "SENDER_ADDRESS";
    public static final String SENDER_PHONE = "SENDER_PHONE";
    public static final String SENDER_EMAIL = "SENDER_EMAIL";
    public static final String SENDER_WARD = "SENDER_WARD";
    public static final String SENDER_DISTRICT = "SENDER_DISTRICT";
    public static final String SENDER_PROVINCE = "SENDER_PROVINCE";
    public static final String SENDER_LATITUDE = "SENDER_LATITUDE";
    public static final String SENDER_LONGITUDE = "SENDER_LONGITUDE";
    public static final String RECEIVER_FULLNAME = "RECEIVER_FULLNAME";
    public static final String RECEIVER_ADDRESS = "RECEIVER_ADDRESS";
    public static final String RECEIVER_PHONE = "RECEIVER_PHONE";
    public static final String RECEIVER_EMAIL = "RECEIVER_EMAIL";
    public static final String RECEIVER_WARD = "RECEIVER_WARD";
    public static final String RECEIVER_DISTRICT = "RECEIVER_DISTRICT";
    public static final String RECEIVER_PROVINCE = "RECEIVER_PROVINCE";
    public static final String RECEIVER_LATITUDE = "RECEIVER_LATITUDE";
    public static final String RECEIVER_LONGITUDE = "RECEIVER_LONGITUDE";
    public static final String PRODUCT_NAME = "PRODUCT_NAME";
    public static final String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    public static final String PRODUCT_QUANTITY = "PRODUCT_QUANTITY";
    public static final String PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String PRODUCT_WEIGHT = "PRODUCT_WEIGHT";
    public static final String PRODUCT_LENGTH = "PRODUCT_LENGTH";
    public static final String PRODUCT_WIDTH = "PRODUCT_WIDTH";
    public static final String PRODUCT_HEIGHT = "PRODUCT_HEIGHT";
    public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final String ORDER_PAYMENT = "ORDER_PAYMENT";
    public static final String ORDER_SERVICE = "ORDER_SERVICE";
    public static final String ORDER_SERVICE_ADD = "ORDER_SERVICE_ADD";
    public static final String ORDER_VOUCHER = "ORDER_VOUCHER";
    public static final String ORDER_NOTE = "ORDER_NOTE";
    public static final String MONEY_COLLECTION = "MONEY_COLLECTION";
    public static final String MONEY_TOTALFEE = "MONEY_TOTALFEE";
    public static final String MONEY_FEECOD = "MONEY_FEECOD";
    public static final String MONEY_FEEVAS = "MONEY_FEEVAS";
    public static final String MONEY_FEEINSURRANCE = "MONEY_FEEINSURRANCE";
    public static final String MONEY_FEE = "MONEY_FEE";
    public static final String MONEY_FEEOTHER = "MONEY_FEEOTHER";
    public static final String MONEY_TOTALVAT = "MONEY_TOTALVAT";
    public static final String MONEY_TOTAL = "MONEY_TOTAL";
    public static final String LIST_ITEM = "LIST_ITEM";
    //config viettle post
    public static final String TYPE_POST_CONFIG = "typePost";
    public static final String ORDER_PAYMENT_CONFIG = "viettelOrderPayment";
    public static final String ORDER_SERVICE_CONFIG = "viettelOrderService";
    public static final String GROUP_ADDRESS_ID_CONFIG = "viettelGroupAddressId";
    public static final String CUS_ID_CONFIG = "viettelCusId";
    public static final String API_POST_ORDER = "viettelApiPostOrder";
    public static final String API_UPDATE_ORDER = "viettelApiUpdateOrder";
    public static final String API_GET_TOKEN = "viettelApiGetToken";
    public static final String USER = "viettelCustomerKey";
    public static final String PASS = "viettelSecretKey";
    public static final String SENDER_PROVINCE_CONFIG = "viettelSenderProvince";
    public static final String SENDER_DISTRICT_CONFIG = "viettelSenderDistrict";
    public static final String SENDER_NAME_CONFIG = "viettelSenderName";
    public static final String SENDER_ADDRESS_CONFIG = "viettelSenderAddress";
    public static final String SENDER_PHONE_CONFIG = "viettelSenderPhone";
    public static final String SENDER_EMAIL_CONFIG = "viettelSenderEmail";
    public static final String SENDER_LATITUDE_CONFIG = "viettelSenderLatitude";
    public static final String SENDER_LONGITUDE_CONFIG = "viettelSenderLongitude";
    public static final String SENDER_WARD_CONFIG = "viettelSenderWard";
    public static final String SENDER_TEL_CONFIG = "viettelSenderTel";

    //Dich vu chuyen phat
    public static final Integer VN_POST = 1;
    public static final Integer VIETTEL_POST = 2;

    //Loai dich vu
    public static final Integer SEND_DOSSIER = 0;
    public static final Integer RECEIVE_DOSSIER = 1;

    //Trang thai don hang
    public static final Integer FIRST_TIME_CREATE = 0;
    public static final Integer CONFIRM_ORDER = 1;
    public static final Integer CONFIRM_RETURN_SHIPPING = 2;
    public static final Integer DELIVERY_AGAIN = 3;
    public static final Integer CANCEL_ORDER = 4;
    public static final Integer GET_BACK_ORDER = 5;
    public static final Integer DELETE_CANCELED_ORDER = 11;

    //Viettel post response

    public static final Integer SUCCESS = 200;
    public static final Integer TOKEN_INVALID = 201;
    public static final Integer TOKEN_ERROR = 202;
    public static final Integer STATUS_HAS_BEEN_CHANGED = 203;
    public static final Integer ACCOUNT_NOT_FOUND = 204;
    public static final Integer SYSTEM_ERROR = 205;
    public static final Integer NO_ORDER_FOUND = 207;
}
