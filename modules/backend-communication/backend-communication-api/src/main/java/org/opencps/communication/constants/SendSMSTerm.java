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

	/**
	 * Configs fields
	 */
	public static final String CC_API_PORT_ADDRESS = "CcApiPort_address";
	public static final String USER = "user";
	public static final String PASSWORD = "password";
	public static final String CP_CODE = "CPCode";
	public static final String REQUEST_ID = "requestID";
	public static final String SERVICE_ID = "serviceID";
	public static final String COMMAND_CODE = "commandCode";
	public static final String CONTENT_TYPE = "contentType";
}
