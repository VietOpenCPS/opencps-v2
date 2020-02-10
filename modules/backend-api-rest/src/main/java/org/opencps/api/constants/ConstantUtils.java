package org.opencps.api.constants;

public class ConstantUtils {

	public static final String ORIGINAL_TODO = "2,3";
	public static final String EXTENTION_JSON = ".json";
	public static final String EXTENTION_XML = ".xml";
	public static final String EXTENTION_TXT = ".txt";
	public static final String EXTENTION_ZIP = ".zip";
	
	public static final String ZIP = "zip";
	public static final String XML = "xml";
	public static final String WORD = "word";
	
	//Destination import file
	public static final String DEST_DIRECTORY = "imported";
	public static final String SOURCE_DICTS = "dicts";
	public static final String SOURCE_FILES = "/files";
	public static final String SOURCE_REPORTS = "/reports";
	public static final String SOURCE_FORMS = "/forms";
	public static final String SOURCE_FILE_REPORTS = "/fileReports";
	public static final String SOURCE_FILE_FORMS = "/fileForms";
	public static final String SOURCE_SERVICES = "services";
	public static final String SOURCE_TEMPLATES = "templates";
	public static final String SOURCE_PROCESSES = "processes";
	public static final String SOURCE_VALIDATE = "services, processes, processes, dicts";
	//
	public static final String PREFIX_ACTIONCONFIG = "ACTION_";
	//Declare file xml
	public static final String XML_ACTION_CONFIG = "ActionConfig.xml";
	public static final String XML_STEP_CONFIG = "StepConfig.xml";
	public static final String XML_MENU_CONFIG = "MenuConfig.xml";
	public static final String XML_DOCUMENT_TYPE = "DocumentType.xml";
	public static final String XML_DELIVERABLE_TYPE = "DeliverableType.xml";
	public static final String XML_PAYMENT_CONFIG = "PaymentConfig.xml";
	public static final String XML_SERVER_CONFIG = "ServerConfig.xml";
	public static final String XML_NOTIFICATION_TEMPLATE = "NotificationTemplate.xml";
	public static final String XML_USERS = "Users.xml";
	public static final String XML_USERS_QA_DONGTHAP = "Users_qa_dongthap.xml";
	public static final String XML_DYNAMIC_REPORT = "DynamicReport.xml";
	public static final String XML_HOLIDAY = "Holiday.xml";
	public static final String XML_APPLICANT = "Applicant.xml";
	public static final String XML_WORKING_TIME = "WorkingTime.xml";
	// Element XML of ActionConfig
	public static final String ACTIONCONFIG_LIST = "ActionConfigList";
	public static final String ACTIONCONFIG = "ActionConfig";
	public static final String ACTION_CODE = "actionCode";
	public static final String ACTION_NAME = "actionName";
	public static final String EXTRA_FORM = "extraForm";
	public static final String SAMPLE_DATA = "sampleData";
	public static final String INSIDE_PROCESS = "insideProcess";
	public static final String SYNC_TYPE = "syncType";
	public static final String PENDDING = "pending";
	public static final String ROLL_BACKABLE = "rollbackable";
	public static final String NOTIFICATION_TYPE = "notificationType";
	public static final String DOCUMENT_TYPE = "documentType";
	//
	public static final String STATISTIC = "statistic";
	public static final String OVER_DUE = "overdue";
	public static final String DELAY = "deplay";
	public static final String COMMNG = "coming";

	//Constant export
	public static final String EXPORT_DICT_COLLECTION = "dictCollection";
	public static final String EXPORT_SERVICE_INFO = "serviceInfo";
	public static final String EXPORT_APPLICANT = "applicant";
	public static final String EXPORT_CITIZEN = "citizen";
	public static final String EXPORT_BUSINESS = "business";
	public static final String EXPORT_ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
	public static final String EXPORT_ACTION_CONFIG = "actionConfig";
	public static final String EXPORT_STEP_CONFIG = "stepConfig";
	public static final String EXPORT_MENU_CONFIG = "menuConfig";
	public static final String DEST_DIRECTORY_EXPORT = "exported";
	//
	public static final String HTML_NEW_LINE = "<br/>";
	public static final String HTML_FOUR_SPACE = "&nbsp;&nbsp;&nbsp;&nbsp";
	public static final int DEFAULT_FONT_SIZE = 16;
	
	public static final String HTTP_REQUEST = "HTTP.REQUEST";
	public static final String TOTAL = "total";
	public static final String DATA= "data";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CONTENT_LENGTH = "Content-Length";
	public static final String CONTENT_TYPE_XXX_FORM_URLENCODED = "application/x-www-form-urlencoded";
	
	public static final String MAIL_AD = "email";
	public static final String TOKEN = "token";
	public static final String CONTENT_LANGUAGE = "content/Language";
	public static final String MONOSPACED = "Monospaced";
	public static final String BARCODE = "barcode";
	public static final String BARCODE_FILENAME = "barcode.filename";
	public static final String PNG_EXTENSION = "png";
	public static final String MEDIA_TYPE_PNG = "image/png";
	public static final String MEDIA_TYPE_XML = "application/xml";
	public static final String MEDIA_TYPE_ZIP = "application/zip";
	public static final String MEDIA_TYPE_PDF = "application/pdf";
	public static final String MEDIA_TYPE_MSWORD = "application/msword";
	
	public static final String MEDIA_TYPE_TEXT_X_SH = "attachment.file.xsh.extension";
	public static final String MEDIA_TYPE_APPLICATION_MAC_BINARY = "attachment.file.macbinary.extension";
	public static final String MEDIA_TYPE_X_MSDOWNLOAD = "attachment.file.msdownload.extension";
	
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT_FILENAME = "attachment.filename";
	public static final String INLINE_FILENAME = "inline.filename";
	public static final String HTTP_HEADER_BEARER = "http.header.bearer";
	public static final String HTTP_HEADER_BASICAUTH = "http.header.basicauth";
	public static final String QUERY_SORT = "query.sort";
	public static final String QUERY_NUMBER_SORT = "query.number.sort";
	public static final String QUERY_STRING_SORT = "query.string.sort";
	public static final String TIMEZONE_ASIA_HOCHIMINH = "Asia/Ho_Chi_Minh";
	
	public static final String KEY = "key";
	public static final String VALUE = "value";
	public static final String ROLE_ADMIN = "Administrator";
	public static final String ROLE_ADMIN_LOWER = "admin";
	
	public static final String NGSP_TOKEN_URL_KEY = "org.opencps.ngsp.token.url";
	public static final String NGSP_SECRET_KEY = "org.opencps.ngsp.secret.key";
	public static final String NGSP_CONSUMER_KEY = "org.opencps.ngsp.consumer.key";
	
	public static final String NSGP_JSON_DATA = "Data";
	public static final String NGSP_JSON_MAIN_INFORMATION = "MainInformation";
	public static final String NGSP_JSON_MAIN_INFORMATION_NAME = "NAME";
	public static final String NGSP_JSON__REPRESENTATIVES = "Representatives";
	public static final String NGSP_JSON__REPRESENTATIVES_FULLNAME = "FULL_NAME";
	
	public static final String API_JSON_ERROR = "error";
	public static final String API_JSON_MESSAGE = "message";
	public static final String API_JSON_WARNING = "warning";
	public static final String API_JSON_EMPTY = "{}";
	public static final String API_JSON_STATUS = "status";
	public static final String API_JSON_NOCONTENT = "No Content";
	public static final String API_JSON_MESSAGE_NONAUTHORATIVE = "api.json.message.nonauthorative";
	public static final String API_JSON_MESSAGE_INTERNAL_SERVER_ERROR = "api.json.message.internalservererror";
	public static final String API_JSON_MESSAGE_NOT_FOUND = "api.json.message.notfound";
	
	public static final String API_USER_NOTHAVEPERMISSION = "api.permission.message.nothavepermission";
	public static final String API_MESSAGE_FILENOTFOUND = "api.message.filenotfound";
	public static final String API_MESSAGE_COMMENT_REMOVESUCCESS = "api.json.comment.message.removesuccess";
	public static final String API_MESSAGE_NOTFOUND = "api.message.notfound";
	
	public static final String NGSP_CTDN_MESSAGE_NOT_FOUND = "ngsp.ctdn.message.notfound";
	public static final String NGSP_CTDN_MESSAGE_NOT_CORRECT = "ngsp.ctdn.message.notcorrect";
	public static final String NGSP_CTDN_MESSAGE_REPRESENTATIVES_NOT_CORRECT = "ngsp.ctdn.message.representatives.notcorrect";
	
	public static final String JCAPTCHA_DIR = "jcaptcha";
	public static final String JCAPTCHA_FILENAME = "jcaptcha.filename";
	public static final String JCAPTCHA_MESSAGE_NOTCORRECT = "jcaptcha.message.notcorrect";
	public static final String JCAPTCHA_DESCRIPTION_NOTCORRECT = "jcaptcha.description.notcorrect";
	
	public static final String EMPLOYEE_VALID_JSON_DUPLICATE = "duplicate";
	public static final String ES_MODIFIED_SORTABLE = "modified_sortable";	
	
	public static final String CITIZEN_DANTOC_DEFAULT = "citizen.dantoc.default";
	public static final String CITIZEN_NHOMMAU_DEFAULT = "citizen.nhommau.default";
	public static final String CITIZEN_QUOCTICH_DEFAULT = "citizen.quoctich.default";
	
	public static final String SERVICES = "services";
	public static final String BACKUPMASTER_FILENAME = "backupmaster.filename";
	public static final String DICT_XML_FILENAME = "dict.filename";
	public static final String REPORT_XML_FILENAME = "report.filename";
	public static final String SERVICEINFO_XML_FILENAME = "serviceinfo.filename";
	public static final String SERVICEFILETEMPLATE_XML_FILENAME = "servicefiletemplate.filename";
	public static final String TEMPLATE_XML_FILENAME = "template.filename";
	public static final String PROCESS_XML_FILENAME = "process.filename";
	
	public static final String FORM_JSON_FILENAME = "form.filename";
	public static final String REPORT_XML_TEMPLATE_PART_FILENAME = "report.template.part.filename";
	public static final String FORM_JSON_TEMPLATE_PART_FILENAME = "form.template.part.filename";
	public static final String BACKUPMASTER_ZIP_FILENAME = "backupmaster.zip";
	public static final String CACHE_GETACTIONCONFIG_KEY = "getActionConfig";
	public static final String CACHE_GROUPID = "cache.groupid";
	
	public static final String CERT_ID = "certId";
	public static final String CERT_PATTERN = "pattern";
	public static final String CERT_GROUPID = "groupId";
	public static final String CERT_INITNUMBER = "initNumber";
	public static final String CERT_YEAR = "year";
	public static final String CERT_MESSAGE_DUPLICATE = "cert.message.duplicate";
	public static final String API_JSON_STATUS_DONE = "api.json.status.done";
	public static final String API_JSON_STATUS_ERROR = "api.json.status.error";

	public static final String DATEFORMAT_YYYY = "yyyy";
	public static final String DATEFORMAT_YY = "yy";
	
	public static final String API_GROUPID_KEY = "groupId";
	public static final String API_KEYWORDS_KEY = "keywords";
	public static final String API_CLASSNAME_KEY = "className";
	public static final String API_CLASSPK_KEY = "classPK";
	public static final String API_STATUS_KEY = "status";
	public static final String API_DEFAULT_EMAIL = "default@liferay.com";
	public static final String API_JSON_DATA_ITEMLV = "itemLv";
	public static final String API_JSON_SUCCESS = "success";
	public static final String API_JSON_URL = "url";
	public static final String API_JSON_FORM_REPORT = "formReport";
	public static final String API_JSON_FORM_DATA = "formData";
	public static final String API_JSON_REPORT_TYPE = "reportType";
	public static final String API_JSON_COUNT = "count";
	
	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_PUT = "PUT";
	
	public static final String API_MESSAGE_GROUPID_NOT_EXISTS = "api.message.groupidnotexists";
	public static final String API_JSON_VALUE = "value";
	public static final String API_JSON_DESCRIPTION = "description";
	public static final String API_MESSAGE_REQUESTBODYINCORRECT = "api.json.message.requestbodyincorrect";
	public static final String API_MESSAGE_CAPTCHA_INCORRECT = "api.json.message.captchaincorrect";
	public static final String API_MESSAGE_ERROR_PROCESS_DATABASE = "api.json.message.errorprocessdatabase";
	public static final String API_MESSAGE_DELETESUCCESS = "api.json.message.deletesuccess";
	public static final String API_MESSAGE_DELETEFAILURE = "api.json.message.deletefailure";
	
	public static final String API_JSON_DEFAULTSIGNATURE_SIGNEDFILE = "signedFile";
	public static final String API_JSON_DEFAULTSIGNATURE_SIGN = "sign";
	public static final String API_JSON_DEFAULTSIGNATURE_SIGNFIELDNAME = "signFieldName";
	public static final String API_JSON_DEFAULTSIGNATURE_FILENAME = "fileName";
	public static final String API_JSON_DEFAULTSIGNATURE_EXPERTSTATE = "expertState";
	
	public static final String API_PROTOCOL_MULTIMEDIA = "MULTIMEDIA";
	public static final String API_PROTOCOL_API_CONNECT = "API_CONNECT";
	
	public static final String API_JSON_DEFAULTSIGNATURE_MSG = "msg";
	public static final String API_JSON_DEFAULTSIGNATURE_FILEENTRYID = "fileEntryId";
	public static final String API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS = "api.data.defaultsignature.success";
	public static final String API_JSON_DEFAULTSIGNATURE_MSG_FILEENTRYID = "api.data.defaultsignature.fileEntryId";
	public static final String API_JSON_MESSAGE_AUTHENTICATEDFAILURE = "api.json.message.authenticatedfailure";
	
	public static final String HASHCOMPUTED_MESSAGE_KEY = "message";
	public static final String HASHCOMPUTED_HASHCOMPUTERS_KEY = "hashComputers";
	public static final String HASHCOMPUTED_SIGNFIELDNAMES_KEY = "signFieldNames";
	public static final String HASHCOMPUTED_FILENAMES_KEY = "fileNames";
	public static final String HASHCOMPUTED_FULLPATHSIGNED_KEY = "fullPathSigned";
	public static final String HASHCOMPUTED_EMAILUSER_KEY = "emailUser";
	public static final String HASHCOMPUTED_TYPESIGNATURE_KEY = "typeSignature";
	public static final String HASHCOMPUTED_POSTSTEPCODE_KEY = "postStepCode";
	
	public static final String QUERY_ZERO = "0";
	public static final String DELIVERABLE_DATAFORM_QUERY_KEY = "query";
	public static final String DELIVERABLE_DATAFORM_VALUES_KEY = "values";
	public static final String DELIVERABLE_DATAFORM_TYPE_KEY = "type";
	public static final String DELIVERABLE_DATAFORM_PATTERN_KEY = "pattern";
	public static final String DELIVERABLE_DATAFORM_PARAMVALUES_KEY = "paramValues";
	public static final String DELIVERABLE_DATAFORM_PARAMTYPES_KEY = "paramTypes";
	public static final String DELIVERABLE_DATAFORM_SOCHUNGCHI = "so_chung_chi";
	public static final String DELIVERABLE_DATAFORM_NGUOIKYCC = "nguoi_ky_cc";
	public static final String DELIVERABLE_DATAFORM_NGAYKYCC = "ngay_ky_cc";
	public static final String DELIVERABLE_DATAFORM_TEN_DOANH_NGHIEP = "ten_doanh_nghiep";
	public static final String DELIVERABLE_DATAFORM_MASO_DOANH_NGHIEP = "ma_so_doanh_nghiep";
	public static final String DELIVERABLE_DATAFORM_MA_HOSO = "ma_ho_so";
	public static final String DELIVERABLE_DATAFORM_SO_HOSO = "so_ho_so";
	public static final String DELIVERABLE_DATAFORM_NGAY_TIEP_NHAN = "ngay_tiep_nhan";
	public static final String DELIVERABLE_DATAFORM_NGAY_GUI = "ngay_gui";
	public static final String DELIVERABLE_DATAFORM_LOAI_SAN_PHAM = "loai_san_pham";
	public static final String DELIVERABLE_DATAFORM_NHAN_HIEU = "nhan_hieu";
	public static final String DELIVERABLE_DATAFORM_MA_KIEU_LOAI = "ma_kieu_loai";
	public static final String DELIVERABLE_DATAFORM_TEN_THUONG_MAI = "ten_thuong_mai";
	public static final String DELIVERABLE_DATAFORM_BIEN_BAN = "bien_ban";
	public static final String DELIVERABLE_DATAFORM_BIENBAN_HINHTHUCCAPGIAY = "bien_ban@hinh_thuc_cap_giay_text";
	public static final String DELIVERABLE_DATAFORM_HINHTHUCCAPGIAY_KEY = "hinh_thuc_cap_giay_text";
	public static final String DELIVERABLE_DATAFORM_BIENBAN_SOBIENBAN = "bien_ban@so_bien_ban";	
	public static final String DELIVERABLE_DATAFORM_SOBIENBAN_KEY = "so_bien_ban";	
	public static final String DELIVERABLE_DATAFORM_BIENBAN_DANGKIEMVIENCHINH = "bien_ban@dang_kiem_vien_chinh";	
	public static final String DELIVERABLE_DATAFORM_DANGKIEMVIENCHINH_KEY = "dang_kiem_vien_chinh";	

	public static final String DELIVERABLE_DL_FILENAME = "fileName";
	public static final String DELIVERABLE_DL_FILETYPE = "fileType";
	public static final String DELIVERABLE_LIST_DELIVERABLE_TYPE = "lstDeliverableType";
	
	public static final String DOSSIERACTION_OVERDUETYPE = "dossieraction.overdue";
	public static final String DOSSIERACTION_UNDUETYPE = "dossieraction.undue";
	public static final String DOSSIERACTION_STEPOVERDUE = "dossieraction.stepoverdue";
	public static final String DOSSIERACTION_PROCESS_ACTION = "processAction";
	public static final String DOSSIERACTION_LIST_CONTACTS = "ListContacts";
	public static final String DOSSIERACTIONUSER_USERNOTEXISTS = "dossieractionuser.usernotexists";
	public static final String DOSSIERACTIONUSER_DOSSIERNOTEXISTS = "dossieractionuser.dossiernotexists";
	
	public static final String DOSSIERDOCUMENT_JASPER_ENGINE_PREVIEW = "jasper/engine/preview/destination";
	public static final String DOSSIERDOCUMENT_JASPER_ENGINE_PREVIEW_CALLBACK = "jasper/engine/preview/callback";
	
	public static final String DOSSIERDOCUMENT_MESSAGE_PREVIEW_NOT_AVAILABLE = "dossierdocument.message.previewrenderingnotavailable";
	public static final String DOSSIERDOCUMENT_MESSAGE_DOSSIERWASNOTONPROCESS = "dossierdocument.message.dossierwasnotonprocess";
	public static final String DOSSIERDOCUMENT_MESSAGE_CANNOTGETDOSSIERWITHID = "dossierdocument.message.cannotgetdossierwithid";
	public static final String DOSSIERDOCUMENT_PROCESSSEQUENCEARR_KEY = "processSequenceArr";
	
	public static final String DOSSIERFILE_MESSAGE_FILEFORMATERROR = "dossierfile.message.fileformaterror";
	public static final String DOSSIERFILE_MESSAGE_FOLDERISNOTSTRUCTURE = "dossierfile.message.folderisnotstructure";
	public static final String DOSSIERTEMPLATE_MESSAGE_NOTFOUND = "dossiertemplate.message.notfound";
	
	public static final String DVCQG_SSO_GROUP_ID_KEY = "_GROUP_ID";
	public static final String DVCQG_SSO_CURRENT_URL_KEY = "_CURRENT_URL";
	public static final String DVCQG_SSO_CANNOTGETUSERINFO = "api.json.message.cannotgetuserinfo";
	
	public static final String EFORM_MESSAGE_SECRETKEYNOTSUCCESS = "eform.message.secretkeynotsuccess";
	public static final String EFORM_MESSAGE_NOEFORMEXISTS = "eform.message.noeformexists";
	public static final String EMPLOYEE_OFFICESITE_FOLDER = "OfficeSite/";
	public static final String EMPLOYEE_OFFICESITE_DESC = "OfficeSite file upload";
	public static final String EMPLOYEE_WORKINGUNIT_FOLDER = "WorkingUnit/";
	public static final String EMPLOYEE_WORKINGUNIT_DESC = "WorkingUnit file upload";

	public static final String EMPLOYEE_JSON_USERIDLIST = "userIdList";

	public static final String FILEATTACH_FOLDER = "FileAttach/";
	public static final String FILEATTACH_DESC = "FileAttach file upload";
	public static final String FILEATTACH_JSON_FILENAME = "fileName";
	public static final String FILEATTACH_JSON_VERSIONS = "versions";
	
	public static final String EMPLOYEE_PROFILEPATH = "employee.profilepath";
	public static final String DOSSIER_KEY = "Dossier";
	public static final String VIEWROOT_URI_KEY = "viewRootURI";
	public static final String VIEWROOT_URI_MESSAGE = "api.viewrooturi";
	public static final String NOTIFICATION_TYPE_SORTABLE = "notificationType_sortable";
	
	public static final String ONEGATE_PAYMENTFEEREQUEST = "paymentFeeRequest";
	public static final String ONEGATE_PAYMENTFEETOTAL = "paymentFeeTotal";
	
	public static final String PROXY_SERVER_DVC = "SERVER_DVC";
	public static final String PROXY_STATISTICS_ENDPOINT = "/statistics";
	public static final String PROXY_V2_ENDPOINT = "/v2";
	
	public static final String REGISTRATION_DATAFORM_QUERY_KEY = "query";
	public static final String REGISTRATION_DATAFORM_VALUES_KEY = "values";
	public static final String REGISTRATION_DATAFORM_TYPE_KEY = "type";
	public static final String REGISTRATION_DATAFORM_PATTERN_KEY = "pattern";
	public static final String REGISTRATION_DATAFORM_PARAMVALUES_KEY = "paramValues";
	public static final String REGISTRATION_DATAFORM_PARAMTYPES_KEY = "paramTypes";
	public static final String REGISTRATION_LIST_REGISTRATION_TEMPLATE = "lstRegistrationTemplate";
	
	public static final String PAYMENTFILE_JSON_GOV_ADDRESS_KEY = "govAddress";
	public static final String SERVER_CONFIG_JSON_METHOD_KEY = "method";
	public static final String SERVER_CONFIG_JSON_EFORM_NO_KEY = "{eFormNo}";
	public static final String SERVER_CONFIG_JSON_MACHA_KEY = "{maCha}";
	public static final String SERVER_CONFIG_JSON_NAMEDM_KEY = "{nameDM}";
	public static final String SERVER_CONFIG_JSON_PARENTID_KEY = "{parentId}";
	public static final String SERVER_CONFIG_JSON_GOV_AGENCY_NAME_KEY = "{govAgencyName}";
	public static final String SERVER_CONFIG_JSON_EMPLOYEE_NAME_KEY = "{employeeName}";
	public static final String SERVER_CONFIG_JSON_MA_NG_KY_KEY = "{ma_ng_ky}";
	public static final String SERVER_CONFIG_JSON_MA_CQQL_KEY = "{ma_cqql}";
	
	public static final String SERVER_CONFIG_JSON_PARAMS_KEY = "params";
	public static final String SERVER_CONFIG_JSON_HEADER_KEY = "header";
	public static final String SERVER_CONFIG_JSON_TYPE_KEY = "type";
	public static final String SERVER_CONFIG_JSON_BASE_KEY = "base";
	public static final String SERVER_CONFIG_JSON_USERNAME_KEY = "username";
	public static final String SERVER_CONFIG_JSON_SECRET_KEY = "password";
	
	public static final String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
	public static final String SERVICE_DOMAIN = "SERVICE_DOMAIN";
	
	public static final String SERVICE_CONFIGS = "serviceConfigs";
	public static final String DOMAINS = "domains";
	public static final String GOV_AGENCIES = "govAgencies";
	public static final String GOV_AGENCYS = "govAgencys";
	public static final String SERVICE_CONFIG_JSON_DOMAIN_ID_KEY = "domainId";
	public static final String SERVICE_CONFIG_JSON_DOMAIN_NAME_KEY = "domainName";
	public static final String SERVICE_CONFIG_JSON_TYPE_KEY = "type";
	public static final String SERVICE_CONFIG_COMPLETED = "completed";	
	
	public static final String ENCODING_UTF_8 = "UTF-8";
	public static final String SERVICE_INFO_RECENTLY = "recently";
	
	public static final String SERVICE_PROCESS_DUPLICATE_STEPNO_EXCEPTION = "serviceprocess.message.duplicatestepnoexception";
	public static final String SERVICE_PROCESS_INITDONE = "Init Done";
	public static final String SERVICE_PROCESS_CLONEDONE = "Clone Done";
	public static final String SERVICE_PROCESS_UPDATESUCCESS = "{Update sucess}";
	public static final String SERVICE_PROCESS_UPDATEERROR = "{Update error}";
	
	public static final String SERVICE_CONFIG_EMPLOYEE_NAME = "employeeName";
	public static final String API_TREEINDEX_SORTABLE = "treeIndex_sortable";
	
	public static final String VOTING_HASPERMISSION = "hasPermission";
	
	public static final String VGCA_FILENAME = "FileName";
	public static final String VGCA_FILEENTRYID = "fileEntryId";
	public static final String VGCA_URL = "url";
	public static final String VGCA_FILESERVER = "FileServer";
	public static final String VGCA_DOCUMENTNUMBER = "DocumentNumber";
	public static final String VGCA_STATUS = "Status";
	public static final String VGCA_URL_PATH = "vgca.urlpath";
	
	public static final String API_IMAGE_EXTENSION = "image/";
	public static final String USER_PHOTO_FOLDER = "USERPHOTO/";
	public static final String USER_PHOTO_DESC = "USERPHOTO file upload";
	public static final String DATACER_PATH = "datacer.path";
}
