package org.opencps.api.constants;

public class ConstantUtils {

	public static final String ORIGINAL_TODO = "2,3";
	public static final String EXTENTION_JSON = ".json";
	public static final String EXTENTION_XML = ".xml";
	public static final String EXTENTION_TXT = ".txt";
	public static final String EXTENTION_ZIP = ".zip";
	
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
	
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT_FILENAME = "attachment.filename";
	public static final String HTTP_HEADER_BEARER = "http.header.bearer";
	public static final String QUERY_SORT = "query.sort";
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
	public static final String API_USER_NOTHAVEPERMISSION = "api.permission.message.nothavepermission";
	
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
}
