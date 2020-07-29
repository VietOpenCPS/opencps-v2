package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OpenCPSConfigUtil {
	public static final String OPENCPS_DVC_ENABLE = "org.opencps.dvc.enable";
	public static final String OPENCPS_NOTIFICATION_ENABLE = "org.opencps.notification.enable";
	public static final String OPENCPS_CACHE_TTL = "cache.default.ttl.seconds";
	private static final int DEFAULT_TTL = 3600;
	public static final String OPENCPS_DOSSIER_LOG_ENABLE = "org.opencps.dossierlog.enable";
	public static final String OPENCPS_TRACK_USER_ENABLE = "org.opencps.trackuser.enable";
	public static final String OPENCPS_API_HTTP_CACHE_ENABLE = "org.opencps.api.httpcache.enable";
	public static final int DEFAULT_HTTP_CACHE_AGE = 86400;
	public static final String OPENCPS_API_HTTP_CACHE_MAX_AGE = "org.opencps.api.httpcache.maxage";
	public static final int DEFAULT_CONNECT_TIMEOUT = 3 * 60000;
	public static final int DEFAULT_READ_TIMEOUT = 3 * 60000;
	public static final String OPENCPS_REST_CONNECTION_TIMEOUT = "org.opencps.rest.connection.timeout";
	public static final String OPENCPS_REST_READ_TIMEOUT = "org.opencps.rest.read.timeout";
	public static final String OPENCPS_STATISTIC_MUTIPLE_SERVER = "org.opencps.statistic.multiple.server.enable";
	public static final String OPENCPS_AI_MODE = "org.opencps.ai.mode";
	public static final String OPENCPS_PERMISSION_ROLE_MODE = "org.opencps.permission.role.mode";
	public static final String OPENCPS_ALLOW_CORS_IPS = "org.opencps.allow.cors.ips";
	public static final String OPENCPS_AUTO_BETIMES = "org.opencps.auto.betimes";
	public static final String PORTAL_DOMAIN = "portal.domain";
	public static final String PORTAL_DOCUMENT_URI = "portal.document.uri";
	public static final String OPENCPS_DOSSIERDOCUMENT_ENABLE = "org.opencps.dossierdocument.enable";
	public static final String OPENCPS_PUBISHEVENT_ENABLE = "org.opencps.publishevent.enable";
	public static final String OPENCPS_DLFILEENTRY_ENABLE = "org.opencps.dlfileentry.enable";

	public static final String OPENCPS_ADMIN_PROXY_URL = "org.opencps.admin.proxy.ip";
	public static final String DEFAULT_PROXY_URL = "http://127.0.0.1:8080";

	public static final String OPENCPS_DOSSIER_SECRET_LENGTH = "org.opencps.dossier.secret.length";
	public static final int OPENCPS_DEFAULT_DOSSIER_SECRET_LENGTH = 4;

	public static final String OPENCPS_MAIL_TO_APPLICANT_FROM = "org.opencps.mailtoapplicant.from";

	public static String getPortalDocumentURI() {
		return GetterUtil.getString(PropsUtil.get(PORTAL_DOCUMENT_URI));
	}

	public static int getPortalDomain() {
		return GetterUtil.getInteger(PropsUtil.get(PORTAL_DOMAIN));
	}

	public static boolean isNotificationEnable() {
		String notificationEnableProperty = PropsUtil.get(OPENCPS_NOTIFICATION_ENABLE);
		return Validator.isNotNull(notificationEnableProperty) ? Boolean.parseBoolean(notificationEnableProperty)
				: false;
	}

	public static boolean isDVC() {
		String dvcEnableProperty = PropsUtil.get(OPENCPS_DVC_ENABLE);
		return Validator.isNotNull(dvcEnableProperty) ? Boolean.parseBoolean(dvcEnableProperty) : true;
	}

	public static boolean isDossierDocumentEnable() {
		String notificationEnableProperty = PropsUtil.get(OPENCPS_DOSSIERDOCUMENT_ENABLE);
		return Validator.isNotNull(notificationEnableProperty) ? Boolean.parseBoolean(notificationEnableProperty)
				: true;
	}

	public static boolean isPublishEventEnable() {
		String notificationEnableProperty = PropsUtil.get(OPENCPS_PUBISHEVENT_ENABLE);
		return Validator.isNotNull(notificationEnableProperty) ? Boolean.parseBoolean(notificationEnableProperty)
				: true;
	}

	public static boolean isDLFileEntryEnable() {
		String fileEntryProperty = PropsUtil.get(OPENCPS_DLFILEENTRY_ENABLE);
		return Validator.isNotNull(fileEntryProperty) ? Boolean.parseBoolean(fileEntryProperty) : true;
	}

	public static int getCacheTTL() {
		String cacheTTLProperty = PropsUtil.get(OPENCPS_CACHE_TTL);
		return Validator.isNotNull(cacheTTLProperty) ? Integer.parseInt(cacheTTLProperty) : DEFAULT_TTL;

	}

	public static boolean isDossierLogEnable() {
		String dossierLogEnable = PropsUtil.get(OPENCPS_DOSSIER_LOG_ENABLE);
		return Validator.isNotNull(dossierLogEnable) ? Boolean.parseBoolean(dossierLogEnable) : false;
	}

	public static boolean isTrackUserEnable() {
		String trackUserEnable = PropsUtil.get(OPENCPS_TRACK_USER_ENABLE);
		return Validator.isNotNull(trackUserEnable) ? Boolean.parseBoolean(trackUserEnable) : false;
	}

	public static boolean isHttpCacheEnable() {
		String httpCacheEnable = PropsUtil.get(OPENCPS_API_HTTP_CACHE_ENABLE);
		return Validator.isNotNull(httpCacheEnable) ? Boolean.parseBoolean(httpCacheEnable) : true;
	}

	public static int getHttpCacheMaxAge() {
		String httpCacheMaxAgeProperty = PropsUtil.get(OPENCPS_API_HTTP_CACHE_MAX_AGE);
		return Validator.isNotNull(httpCacheMaxAgeProperty) ? Integer.parseInt(httpCacheMaxAgeProperty)
				: DEFAULT_HTTP_CACHE_AGE;

	}

	public static int getRestConnectionTimeout() {
		String connectionTimeoutProperty = PropsUtil.get(OPENCPS_REST_CONNECTION_TIMEOUT);
		return Validator.isNotNull(connectionTimeoutProperty) ? Integer.parseInt(connectionTimeoutProperty)
				: DEFAULT_CONNECT_TIMEOUT;

	}

	public static int getRestReadTimeout() {
		String readTimeoutProperty = PropsUtil.get(OPENCPS_REST_READ_TIMEOUT);
		return Validator.isNotNull(readTimeoutProperty) ? Integer.parseInt(readTimeoutProperty) : DEFAULT_READ_TIMEOUT;

	}

	public static boolean isStatisticMultipleServerEnable() {
		String statisticMultipleServerEnable = PropsUtil.get(OPENCPS_STATISTIC_MUTIPLE_SERVER);
		return Validator.isNotNull(statisticMultipleServerEnable) ? Boolean.parseBoolean(statisticMultipleServerEnable)
				: false;
	}

	public static String getHostAddress() {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			return inetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			_log.debug(e);
		}

		return StringPool.BLANK;
	}

	public static boolean isAiMode() {
		String isAiMode = PropsUtil.get(OPENCPS_AI_MODE);
		return Validator.isNotNull(isAiMode) ? Boolean.parseBoolean(isAiMode) : false;
	}

	public static boolean isPermissionRoleMode() {
		String isPermissionRoleMode = PropsUtil.get(OPENCPS_PERMISSION_ROLE_MODE);
		return Validator.isNotNull(isPermissionRoleMode) ? Boolean.parseBoolean(isPermissionRoleMode) : false;
	}

	public String getAllowCORSIps() {
		return PropsUtil.get(OPENCPS_ALLOW_CORS_IPS);
	}

	public static boolean isAutoBetimes() {
		String autoBetimesEnable = PropsUtil.get(OPENCPS_AUTO_BETIMES);
		return Validator.isNotNull(autoBetimesEnable) ? Boolean.parseBoolean(autoBetimesEnable) : false;
	}

	public static String getAdminProxyUrl() {
		String adminProxyUrl = PropsUtil.get(OPENCPS_ADMIN_PROXY_URL);
		return Validator.isNotNull(adminProxyUrl) ? adminProxyUrl : DEFAULT_PROXY_URL;
	}

	public static Integer getDefaultDossierSecretLength() {
		String secretLength = PropsUtil.get(OPENCPS_DOSSIER_SECRET_LENGTH);
		return Validator.isNotNull(secretLength) ? GetterUtil.getInteger(secretLength)
				: OPENCPS_DEFAULT_DOSSIER_SECRET_LENGTH;
	}

	public static String getMailToApplicantFrom() {
		String mailToApplicantFrom = PropsUtil.get(OPENCPS_MAIL_TO_APPLICANT_FROM);
		return Validator.isNotNull(mailToApplicantFrom) ? mailToApplicantFrom : StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(OpenCPSConfigUtil.class);
}
