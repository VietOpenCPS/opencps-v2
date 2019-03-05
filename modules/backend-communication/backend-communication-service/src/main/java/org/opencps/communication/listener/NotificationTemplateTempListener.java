package org.opencps.communication.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.opencps.communication.model.Notificationtemplate;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class NotificationTemplateTempListener extends BaseModelListener<Notificationtemplate> {

	@Override
	public void onAfterCreate(Notificationtemplate model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(Notificationtemplate model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(Notificationtemplate model) throws ModelListenerException {
//		try {
//
//			model.setSendEmail(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getSendEmail()))));
//			model.setNotificationType(HtmlUtil.escape(model.getNotificationType()));
//			model.setEmailSubject(HtmlUtil.escape(model.getEmailSubject()));
//			model.setEmailBody(HtmlUtil.escape(model.getEmailBody()));
//			model.setTextMessage(HtmlUtil.escape(model.getTextMessage()));
//			model.setSendSMS(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getSendSMS()))));
//			model.setExpireDuration(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getExpireDuration()))));
//			model.setUserUrlPattern(HtmlUtil.escape(model.getUserUrlPattern()));
//			model.setGuestUrlPattern(HtmlUtil.escape(model.getGuestUrlPattern()));
//			model.setInterval(HtmlUtil.escape(model.getInterval()));
//			model.setGrouping(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getGrouping()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(Notificationtemplate model) throws ModelListenerException {
//		try {
//
//			model.setSendEmail(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getSendEmail()))));
//			model.setNotificationType(HtmlUtil.escape(model.getNotificationType()));
//			model.setEmailSubject(HtmlUtil.escape(model.getEmailSubject()));
//			model.setEmailBody(HtmlUtil.escape(model.getEmailBody()));
//			model.setTextMessage(HtmlUtil.escape(model.getTextMessage()));
//			model.setSendSMS(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getSendSMS()))));
//			model.setExpireDuration(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getExpireDuration()))));
//			model.setUserUrlPattern(HtmlUtil.escape(model.getUserUrlPattern()));
//			model.setGuestUrlPattern(HtmlUtil.escape(model.getGuestUrlPattern()));
//			model.setInterval(HtmlUtil.escape(model.getInterval()));
//			model.setGrouping(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getGrouping()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	//private Log _log = LogFactoryUtil.getLog(NotificationTemplateTempListener.class.getName());
}
