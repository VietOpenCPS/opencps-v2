package org.opencps.communication.listener;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.opencps.communication.constants.ServerConfigTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ServerConfigTempListener extends BaseModelListener<ServerConfig> {

	@Override
	public void onAfterCreate(ServerConfig model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(ServerConfig model) throws ModelListenerException {
		try {
			if (ServerConfigTerm.EMAIL_CONFIG_PT.equals(model.getProtocol())) {

				String configs = model.getActive() ? model.getConfigs() : null;
				List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(ServerConfigTerm.EMAIL_CONFIG_PT);
				PortletPreferences preferences = PrefsPropsUtil.getPreferences();
				long activeId = model.getServerConfigId();
				for (ServerConfig serverConfig : serverConfigs) {
					if (serverConfig.getServerConfigId() != model.getServerConfigId() && model.getActive() && serverConfig.getActive()) {
						serverConfig.setActive(false);
						ServerConfigLocalServiceUtil.updateServerConfig(serverConfig);
					} else if (serverConfig.getServerConfigId() != model.getServerConfigId() && !model.getActive() && serverConfig.getActive()) {
						activeId = serverConfig.getServerConfigId();
						configs = serverConfig.getConfigs();
					}
				}
				_log.debug("model Id=" + model.getServerConfigId() + "=active status=" + model.getActive());
				_log.debug(configs);
				JSONObject jConfigs = configs != null ? JSONFactoryUtil.createJSONObject(configs) : null;
				boolean changeActiveId = String.valueOf(activeId).equals(preferences.getValue(ServerConfigTerm.EMAIL_CONFIG_PT, String.valueOf(0)));
				boolean isModelUpdate = model.getActive();
				if (changeActiveId || isModelUpdate || Validator.isNull(configs)) {
					preferences.setValue(ServerConfigTerm.EMAIL_CONFIG_PT, String.valueOf(activeId));
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_ADVANCED_PROPERTIES, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_ADVANCED_PROPERTIES) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_POP3_PORT, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_POP3_PORT) : String.valueOf(0));
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_POP3_PASSWORD, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_POP3_PASSWORD) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_SMTP_HOST, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_SMTP_HOST) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_POP3_USER, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_POP3_USER) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_STORE_PROTOCOL, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_STORE_PROTOCOL) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_POP3_HOST, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_POP3_HOST) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL) : String.valueOf(false));
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_SMTP_USER, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_SMTP_USER) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_SMTP_PASSWORD, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_SMTP_PASSWORD) : StringPool.BLANK);
					preferences.setValue(PropsKeys.MAIL_SESSION_MAIL_SMTP_PORT, configs != null ? jConfigs.getString(PropsKeys.MAIL_SESSION_MAIL_SMTP_PORT) : String.valueOf(0));
				}
				preferences.store();
			}

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeCreate(ServerConfig model) throws ModelListenerException {
//		try {
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setServerNo(HtmlUtil.escape(model.getServerNo()));
//			model.setServerName(HtmlUtil.escape(model.getServerName()));
//			model.setProtocol(HtmlUtil.escape(model.getProtocol()));
//			//model.setConfigs(HtmlUtil.escape(model.getConfigs()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ServerConfig model) throws ModelListenerException {
//		try {
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setServerNo(HtmlUtil.escape(model.getServerNo()));
//			model.setServerName(HtmlUtil.escape(model.getServerName()));
//			model.setProtocol(HtmlUtil.escape(model.getProtocol()));
//			//model.setConfigs(HtmlUtil.escape(model.getConfigs()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ServerConfigTempListener.class.getName());
}
