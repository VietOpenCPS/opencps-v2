
package org.opencps.kernel.message.email;

import org.opencps.kernel.message.MBMessageEntry;
import org.osgi.service.component.annotations.Component;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
@Component(immediate = true, service = MBEmailSenderImpl.class)
public class MBEmailSenderImpl implements MBEmailSender {

	@Override
	public void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... serviceContexts) {
		System.out.println("===========/////////////// Start send mail");
		if (messageEntry != null && messageEntry.isSendEmail()) {
			MailMessage mailMessage = new MailMessage();
			mailMessage.setSubject(messageEntry.getEmailSubject());
			mailMessage.setTo(messageEntry.getToAddress());
			mailMessage.setBody(messageEntry.getEmailBody());
			mailMessage.setHTMLFormat(true);
			mailMessage.setFrom(messageEntry.getFrom());
			//mailService.sendEmail(mailMessage);
			MailServiceUtil.sendEmail(mailMessage);
			System.out.println("===========/////////////// Send to " + messageEntry.getToAddress()[0]);
		}
		System.out.println("===========/////////////// end send mail");
	}
	
	@BeanReference(type = MailService.class)
	protected MailService mailService;

}
