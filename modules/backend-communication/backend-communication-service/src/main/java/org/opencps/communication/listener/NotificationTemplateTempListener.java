package org.opencps.communication.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
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
		try {

			model.setSendEmail(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getSendEmail()))));
			model.setNotificationType(StringEscapeUtils.escapeHtml4(model.getNotificationType()));
			model.setEmailSubject(StringEscapeUtils.escapeHtml4(model.getEmailSubject()));
			model.setEmailBody(StringEscapeUtils.escapeHtml4(model.getEmailBody()));
			model.setTextMessage(StringEscapeUtils.escapeHtml4(model.getTextMessage()));
			model.setSendSMS(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getSendSMS()))));
			model.setExpireDuration(
					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getExpireDuration()))));
			model.setUserUrlPattern(StringEscapeUtils.escapeHtml4(model.getUserUrlPattern()));
			model.setGuestUrlPattern(StringEscapeUtils.escapeHtml4(model.getGuestUrlPattern()));
			model.setInterval(StringEscapeUtils.escapeHtml4(model.getInterval()));
			model.setGrouping(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getGrouping()))));

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(Notificationtemplate model) throws ModelListenerException {
		try {

			model.setSendEmail(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getSendEmail()))));
			model.setNotificationType(StringEscapeUtils.escapeHtml4(model.getNotificationType()));
			model.setEmailSubject(StringEscapeUtils.escapeHtml4(model.getEmailSubject()));
			model.setEmailBody(StringEscapeUtils.escapeHtml4(model.getEmailBody()));
			model.setTextMessage(StringEscapeUtils.escapeHtml4(model.getTextMessage()));
			model.setSendSMS(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getSendSMS()))));
			model.setExpireDuration(
					Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getExpireDuration()))));
			model.setUserUrlPattern(StringEscapeUtils.escapeHtml4(model.getUserUrlPattern()));
			model.setGuestUrlPattern(StringEscapeUtils.escapeHtml4(model.getGuestUrlPattern()));
			model.setInterval(StringEscapeUtils.escapeHtml4(model.getInterval()));
			model.setGrouping(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getGrouping()))));

		} catch (Exception e) {
			_log.error(e);
		}
	}


	private Log _log = LogFactoryUtil.getLog(NotificationTemplateTempListener.class.getName());
}
