/**
 * Class define keys: Protocol and fields in column configs
 * to get exactly informations, which helps send sms
 * 
 * Tags: Viettel, SMS, bulksms, soap
 * 
 * Format in ServerConfig:
 *   protocol: VT_SMS_INF
 *   configs: '{\n  \"CcApiPort_address\": \"http://125.235.4.202:8998/bulkapi\",\n  \"user\": \"USER_VT\",\n  \"password\": \"PASS_VT\",\n  \"CPCode\": \"CP_CODE_VT\",\n  \"requestID\": \"1\",\n  \"serviceID\": \"SERVICE_ID_VT\",\n  \"commandCode\": \"bulksms\",\n  \"contentType\": \"F\"\n}'
 */

package org.opencps.communication.constants;

/**
 * @author thanhnv
 */

public class SendSMSTerm {

	/**
	 * Protocol from table serverConfig, result to get information SMS server
	 */
	public static final String SERVER_CONFIG_PROTOCOL_VT_SMS = "VT_SMS_INF";
	public static final String SERVER_CONFIG_PROTOCOL_BCT_SMS = "BCT_SMS_INF";

	/**
	 * Configs fields
	 */
	public static final String CC_API_PORT_ADDRESS = "CcApiPort_address";
	public static final String USER = "user";
	public static final String SECRET_CODE = "password";
	public static final String CP_CODE = "CPCode";
	public static final String REQUEST_ID = "requestID";
	public static final String SERVICE_ID = "serviceID";
	public static final String COMMAND_CODE = "commandCode";
	public static final String CONTENT_TYPE = "contentType";
	public static final String COUNTRY_CODE = "countryCode";
	public static final String VIETTEL_SERVICE_ID = "viettelServiceID";
	public static final String MOBIFONE_SERVICE_ID = "mobifoneServiceID";
	public static final String VINAPHONE_SERVICE_ID = "vinaphoneServiceID";
	public static final String GMOBILE_SERVICE_ID = "gmobileServiceID";
	public static final String VIETNAMOBILE_SERVICE_ID = "vietnamobileServiceID";
	public static final String SFONE_SERVICE_ID = "sfoneServiceID";
	
	/** Key by BCT **/
	/**
	 * Configs fields
	 */
	public static final String BCT_CP_CODE = "cp_code";
	public static final String BCT_ALIAS = "alias";
	public static final String BCT_USER_NAME = "api-username";
	public static final String BCT_SECRET = "api-password";
	public static final String BCT_CONTENT_TYPE = "content_type";
	public static final String BCT_PACKAGE_ID = "package_id";
	
	public static final String KEYWORDS = "keywords";
	public static final String PARAMS = "params";
	public static final String ITEM_LV = "itemLv";
	public static final String PAGINATION_TYPE = "paginationType";
	public static final String EXPANDO_ATTRIBUTES = "expandoAttributes";

	/**
	 * Zalo Preference to send message zalo
	 */
	// public static final String SERVER_CONFIG_PROTOCOL_ZALO_INF = "ZALO_INF";
	public static final String SERVER_CONFIG_SERVERNO_ZALO = "ZALO";
	public static final String OAID_TOKEN_ACCESS = "oaid_token_access";
	public static final String ZALO_GROUPID = "groupId";
	public static final String DATA = "data";
	public static final String TOTAL = "total";
	// public static final String ZALO_UID = "ZALO_UID";
	// public static final String UID = "uid";
	// public static final String ZALO_TEL_NO_EXITS_MES = "telNoExitsMes";
	// public static final String ZALO_TEL_NO_NOT_EXITS_MES =
	// "telNoNotExitsMes";
	// public static final String ZALO_ADD_TEL_NO_SUCCESS_MES =
	// "addTelNoSuccessMes";
	// public static final String ZALO_UPDATE_TEL_NO_SUCCESS_MES =
	// "updateTelNoSuccessMes";
	// public static final String ZALO_REGISTRY_TEL_NO_SUCCESS_MES =
	// "registryTelNoSuccessMes";
	public static final String ZALO_DOSSIER_NOT_FOUND_MES =
		"dossierNotFoundMes";
	public static final String ZALO_DOSSIER_ERROR_SECRET_MES =
		"dossierErrorPasswordMes";
	public static final String ZALO_DOSSIER_NO_REPLACE = "dossierNoReplace";
	public static final String ZALO_DOSSIER_STATUS_REPLACE =
		"dossierStatusReplace";
	public static final String ZALO_DOSSIER_SUCCESS_MES = "successMes";

	// Config call api from MCDT to DVC
	// public static final String SERVER_CONFIG_PROTOCOL_ZALO_URLS =
	// "ZALO_URLS";
	// public static final String ZALO_URLS_URLS = "urls";
	// public static final String ZALO_URLS_PATH_BASE = "pathBase";
	// public static final String ZALO_URLS_END_POINT = "endPoint";
	// public static final String ZALO_URLS_GROUP_ID = "groupId";
	// public static final String ZALO_URLS_USER_NAME = "userName";
	// public static final String ZALO_URLS_PASS_WORD = "password";

	public static final String SERVER_CONFIG_SERVERNO_EPACIFIC =
		"SMS_EPACIFIC_SERVER";
	public static final String EPACIFIC_GROUPID = "groupId";
	public static final String EPACIFIC_MINE = "mine";
	public static final String EPACIFIC_USER = "user";
	public static final String EPACIFIC_SECRET = "password";
	public static final String EPACIFIC_SYNTAX_ERROR_MES = "syntaxErrorMes";
	public static final String EPACIFIC_D_SECRET_ERROR_MES =
		"dPasswordErrorMes";
	public static final String EPACIFIC_D_NOT_FOUND_MES = "dNotFoundMes";
	public static final String EPACIFIC_MINE_ERROR_MES = "mineErrorMes";
	public static final String EPACIFIC_USER_ERROR_MES = "userErrorMes";
	public static final String EPACIFIC_SECRET_ERROR_MES = "passwordErrorMes";
	public static final String EPACIFIC_SUCCESS_MES = "successMes";
	public static final String EPACIFIC_DOSSIER_NO_REPLACE = "dossierNoReplace";
	public static final String EPACIFIC_DOSSIER_STATUS_REPLACE =
		"dossierStatusReplace";

}
