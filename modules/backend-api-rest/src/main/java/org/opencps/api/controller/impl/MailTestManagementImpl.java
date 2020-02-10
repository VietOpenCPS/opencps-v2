package org.opencps.api.controller.impl;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.MailTestManagement;

public class MailTestManagementImpl implements MailTestManagement {

//	@Override
//	public Response sendMail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
//			ServiceContext serviceContext, String subject, String to, String body, String from) {
//		MailMessage mailMessage = new MailMessage();
//		mailMessage.setSubject(subject);
//		try {
//			mailMessage.setTo(new InternetAddress(to));
//			mailMessage.setFrom(new InternetAddress(from));
//		} catch (AddressException e) {
//		}
//		mailMessage.setBody(body);
//		mailMessage.setHTMLFormat(true);
//		
//		MailServiceUtil.sendEmail(mailMessage);
//		
//		return Response.ok().entity("{}").build();
//	}
//
//	@Override
//	public Response javaMail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
//			ServiceContext serviceContext, String subject, String to, String body, String from) {
//		final String fromEmail = "hotrodvc@mic.gov.vn"; //requires valid gmail id
//		final String password = "Dvc@2020;;"; // correct password for gmail id
//		final String toEmail = "trungdk@yopmail.com"; // can be any email id 
//		
//		System.out.println("SSLEmail Start");
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "10.100.10.90"); //SMTP Host
//		props.put("mail.smtp.socketFactory.port", "587"); //SSL Port
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
//		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
//		props.put("mail.smtp.port", "465"); //SMTP Port
//		
//		Authenticator auth = new Authenticator() {
//			//override the getPasswordAuthentication method
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(fromEmail, password);
//			}
//		};
//		
//		Session session = Session.getDefaultInstance(props, auth);
//		System.out.println("Session created");
//	    sendEmail(session, toEmail,"SSLEmail Testing Subject", "SSLEmail Testing Body");
//	    
//		return Response.ok().entity("{}").build();
//	}
//
//	public static void sendEmail(Session session, String toEmail, String subject, String body){
//		try
//	    {
//	      MimeMessage msg = new MimeMessage(session);
//	      //set message headers
//	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//	      msg.addHeader("format", "flowed");
//	      msg.addHeader("Content-Transfer-Encoding", "8bit");
//
//	      msg.setFrom(new InternetAddress("hotrodvc@mic.gov.vn", "NoReply-JD"));
//
//	      msg.setReplyTo(InternetAddress.parse("trungdk@yopmail.com", false));
//
//	      msg.setSubject(subject, "UTF-8");
//
//	      msg.setText(body, "UTF-8");
//
//	      msg.setSentDate(new Date());
//
//	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
//	      System.out.println("Message is ready");
//    	  Transport.send(msg);  
//
//	      System.out.println("EMail Sent Successfully!!");
//	    }
//	    catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	}
	
}
