package org.opencps.dossiermgt.constants;

public class FrequencyOfficeConstants {
    public static final String DOC   = "doc";
    public static final String DOCX  = "docx";
    public static final String CSV   = "csv";
    public static final String JPEG  = "jpeg";
    public static final String JPG   = "jpg";
    public static final String PNG   = "png";
    public static final String PDF   = "pdf";
    public static final String XLS   = "xls";
    public static final String XLSX  = "xlsx";

    //mime type
    public static final String MIME_DOC  = "application/msword";
    public static final String MIME_DOCX  = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MIME_CSV  = "text/csv";
    public static final String MIME_JPEG  = "image/jpeg";
    public static final String MIME_JPG  = "image/jpeg";
    public static final String MIME_PNG  = "image/png";
    public static final String MIME_PDF  = "application/pdf";
    public static final String MIME_XLS  = "application/vnd.ms-excel";
    public static final String MIME_XLSX  = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static final String PROTOCOL = "DOSSIER_BTTTT";
    public static final String SERVER_NO = "DOSSIER_BTTTT";
    //Server config
    public static final String CONFIG_URL = "adapter_url";
    public static final String CONFIG_GET_TOKEN = "getToken";
    public static final String CONFIG_GET_LIST_DOSSIERS = "getListDossier";
    public static final String CONFIG_GET_DETAIL_DOSSIERS = "getDetailDossier";
    public static final String CONFIG_UPDATE_STATUS_RECEIVER = "updateStatusReceiver";
    public static final String CONFIG_SEND_STATUS_RECEIVER = "sendStatusProfile";
    public static final String CONFIG_SYNC_DOSSIER_TO_DVC_BO = "syncDossierToDVCBo";
    public static final String CONFIG_SEND_STATUS_PROFILE_TO_DVC_BO = "sendStatusProfileToDVCBo";
    public static final String CONFIG_SYNC_DOSSIER = "syncDossier";
    public static final String CONFIG_USER_LGSP = "userLGSP";
    public static final String CONFIG_PASS_LGSP = "passLGSP";
    public static final String CONFIG_UNIT_CODE = "madonvi";
    public static final String CONFIG_STATUS = "dossierStatus";
    public static final String CONFIG_STATUS_MCDT = "MCDT";
    public static final String CONFIG_STATUS_LGSP = "LGSP";
    public static final String CONFIG_FROM_UNIT_CODE = "from_unit_code";
    public static final String CONFIG_TO_UNIT_CODE = "to_unit_code";
    public static final String CONFIG_TO_UNIT_CODE_CUCTANSO = "to_unit_code_cuctanso";

    //Dossier status
    public static final String STATUS_RECEIVE = "TIEPNHAN";
    public static final String STATUS_UPDATE = "CAPNHAT";
    public static final String STATUS_PULL_OUT = "RUT";
    public static final String STATUS_DENIED = "TUCHOI";
    public static final String STATUS_RESULT = "KETQUA";
    //Status LGSP
    public static final Integer STATUS_LGSP_NEW = 1;
    public static final Integer STATUS_LGSP_RECEIVED = 2;
    public static final Integer STATUS_LGSP_DENIED = 3;
    public static final Integer STATUS_LGSP_PROCESSING = 4;
    public static final Integer STATUS_LGSP_REQUIRE_PAPER = 5;
    public static final Integer STATUS_LGSP_REQUIRE_MONEY = 6;
    public static final Integer STATUS_LGSP_PEOPLE_REQUIRE_CANCEL = 7;
    public static final Integer STATUS_LGSP_STOP_PROCESS = 8;
    public static final Integer STATUS_LGSP_DONE = 9;
    public static final Integer STATUS_LGSP_RETURNED_RESULT = 10;


    public static final String STATUS_SUCCESS = "done";
    public static final String STATUS_FAIL = "fail";

    //Field body update Dossier
    public static final String TYPE = "type";
    public static final String SOURCE_ID = "source_id";
    public static final String REF_CODE = "ref_code";
    public static final String STATUS_PROFILE = "status_profile";
    public static final String PROCESS_OFFICIALS = "process_officials";
    public static final String TIME_OF_PROCESS = "time_of_process";
    public static final String FROM_UNIT_CODE = "from_unit_code";
    public static final String TO_UNIT_CODE = "to_unit_code";
}
