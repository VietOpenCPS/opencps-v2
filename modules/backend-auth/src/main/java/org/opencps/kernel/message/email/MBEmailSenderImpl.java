
package org.opencps.kernel.message.email;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.kernel.message.MBMessageEntry;
import org.osgi.service.component.annotations.Component;

/**
 * @author trungnt
 */
@Component(immediate = true, service = MBEmailSenderImpl.class)
public class MBEmailSenderImpl implements MBEmailSender {
	private Log _log = LogFactoryUtil.getLog(MBEmailSenderImpl.class);
	
	@Override
	public void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... serviceContexts) {
		
		try {

			if (messageEntry != null && messageEntry.isSendEmail() && messageEntry.getToAddress().length > 0) {
				_log.debug("===SEND_MAIL_TO_ADD=======" + messageEntry.getToAddress()[0].getAddress());
				MailMessage mailMessage = new MailMessage();
				mailMessage.setSubject(messageEntry.getEmailSubject());
				mailMessage.setTo(messageEntry.getToAddress());
				mailMessage.setBody(messageEntry.getEmailBody());
				mailMessage.setHTMLFormat(true);
				_log.debug("messageEntry FROM1: " + messageEntry.getFrom().getAddress());
	
				String smtpUser = PrefsPropsUtil.getString(
						PropsKeys.MAIL_SESSION_MAIL_SMTP_USER,
						StringPool.BLANK);
				
				_log.debug("smtpUser: " + smtpUser);
				if (Validator.isNotNull(smtpUser)) {
					messageEntry.getFrom().setAddress(smtpUser);				
					mailMessage.setFrom(messageEntry.getFrom());

				}
				
				MailServiceUtil.sendEmail(mailMessage);
		
			}
		
		}
		catch(Exception e) {
			_log.error(e);
		}

	}
	
	@BeanReference(type = MailService.class)
	protected MailService mailService;

}
