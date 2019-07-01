
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

	@Override
	public void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... serviceContexts) {

		if (messageEntry != null && messageEntry.isSendEmail() && messageEntry.getToAddress().length > 0) {
			System.out.println("===SEND_MAIL_TO_ADD=======" + messageEntry.getToAddress()[0].getAddress());
			MailMessage mailMessage = new MailMessage();
			mailMessage.setSubject(messageEntry.getEmailSubject());
			mailMessage.setTo(messageEntry.getToAddress());
			mailMessage.setBody(messageEntry.getEmailBody());
			mailMessage.setHTMLFormat(true);
			mailMessage.setFrom(messageEntry.getFrom());

			MailServiceUtil.sendEmail(mailMessage);

		}


	}
	
	@BeanReference(type = MailService.class)
	protected MailService mailService;

}
