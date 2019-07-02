
package org.opencps.kernel.prop;

import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author trungnt
 */
public class PropValues {

	public static final String SYSTEM_USER =
		PropsUtil.get(PropKeys.SYSTEM_USER);

	public static final String SYSTEM_USER_ID =
		PropsUtil.get(PropKeys.SYSTEM_USER_ID);

	public static final String SYSTEM_PASS =
		PropsUtil.get(PropKeys.SYSTEM_PASS);

	public static final String SERVER_BASE_PATH =
		PropsUtil.get(PropKeys.SERVER_BASE_PATH);

	public static final String RESTFUL_BASE_PATH =
		PropsUtil.get(PropKeys.RESTFUL_BASE_PATH);

	public static final String FILE_SYSTEM_TEMP_DIR =
		PropsUtil.get(PropKeys.FILE_SYSTEM_TEMP_DIR);

	public static final String FILE_SYSTEM_REPORT_TEMPLATE_DIR =
		PropsUtil.get(PropKeys.FILE_SYSTEM_REPORT_TEMPLATE_DIR);

	public static final String FILE_SYSTEM_REPORT_TEMPLATE_WEEKCALENDAR_NAME =
		PropsUtil.get(PropKeys.FILE_SYSTEM_REPORT_TEMPLATE_WEEKCALENDAR_NAME);

	public static final String FILE_SYSTEM_ONLINEEDITING_TEMPLATE_DIR =
		PropsUtil.get(PropKeys.FILE_SYSTEM_ONLINEEDITING_TEMPLATE_DIR);

	public static final String FILE_SYSTEM_ONLINEEDITING_TEMPLATE_NAME =
		PropsUtil.get(PropKeys.FILE_SYSTEM_ONLINEEDITING_TEMPLATE_NAME);

	public static final String MAIL_ADMIN_SERVER =
			PropsUtil.get(PropKeys.MAIL_ADMIN_SERVER);

	public static final String NAME_ADMIN_SERVER =
			PropsUtil.get(PropKeys.NAME_ADMIN_SERVER);

	public static final String PORTAL_DOMAIN =
		PropsUtil.get(PropKeys.PORTAL_DOMAIN);

	public static final String PORTAL_DOCUMENT_EDITOR_PATH =
		PropsUtil.get(PropKeys.PORTAL_DOCUMENT_EDITOR_PATH);

	public static final String PORTAL_DOCUMENT_EDITOR_CALLBACK_PATH =
		PropsUtil.get(PropKeys.PORTAL_DOCUMENT_EDITOR_CALLBACK_PATH);

	public static final String ONLINE_EDITOR_DOCUMENT_SERVER_HOST =
		PropsUtil.get(PropKeys.ONLINE_EDITOR_DOCUMENT_SERVER_HOST);

	public static final String ONLINE_EDITOR_DOCUMENT_SERVER_API_PATH =
		PropsUtil.get(PropKeys.ONLINE_EDITOR_DOCUMENT_SERVER_API_PATH);

	public static final String SMS_JAXRS_USERNAME =
		PropsUtil.get(PropKeys.SMS_JAXRS_USERNAME);

	public static final String SMS_JAXRS_SECRETKEY =
		PropsUtil.get(PropKeys.SMS_JAXRS_SECRETKEY);

	public static final String SMS_SWITCH_BOARD =
			PropsUtil.get(PropKeys.SMS_SWITCH_BOARD);

	public static final String SMS_END_POINT =
			PropsUtil.get(PropKeys.SMS_END_POINT);

}
