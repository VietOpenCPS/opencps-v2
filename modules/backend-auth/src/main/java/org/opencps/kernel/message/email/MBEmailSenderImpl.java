
package org.opencps.kernel.message.email;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.kernel.message.MBMessageEntry;
import org.osgi.service.component.annotations.Component;

/**
 * @author trungnt
 */
@Component(immediate = true, service = MBEmailSenderImpl.class)
public class MBEmailSenderImpl implements MBEmailSender {

	private static final Log _log = LogFactoryUtil.getLog(MBEmailSenderImpl.class);
	@Override
	public void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... serviceContexts) {
		_log.info("===========/////////////// Start send mail");
		if (messageEntry != null && messageEntry.isSendEmail()) {
			MailMessage mailMessage = new MailMessage();
			mailMessage.setSubject(messageEntry.getEmailSubject());
			mailMessage.setTo(messageEntry.getToAddress());
			mailMessage.setBody(messageEntry.getEmailBody());
			mailMessage.setHTMLFormat(true);
			mailMessage.setFrom(messageEntry.getFrom());
			System.out.println("mailMessage: "+JSONFactoryUtil.looseSerialize(mailMessage));
			System.out.println("messageEntry.getFrom(): "+JSONFactoryUtil.looseSerialize(messageEntry.getFrom()));
			
			System.out.println("FROM: "+messageEntry.getFrom().getAddress());
			MailServiceUtil.sendEmail(mailMessage);
			_log.info("===========/////////////// Send to " + messageEntry.getToAddress()[0]);
		}
		_log.info("===========/////////////// end send mail");
	}
	
	@BeanReference(type = MailService.class)
	protected MailService mailService;

}
