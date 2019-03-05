package org.opencps.communication.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.opencps.communication.model.ServerConfig;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ServerConfigTempListener extends BaseModelListener<ServerConfig> {

	@Override
	public void onAfterCreate(ServerConfig model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(ServerConfig model) throws ModelListenerException {
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


	//private Log _log = LogFactoryUtil.getLog(NotificationTemplateTempListener.class.getName());
}
