package org.opencps.communication.utils;

import org.opencps.communication.constants.EmailTerm;
import org.opencps.communication.constants.MailVariables;
import org.opencps.communication.constants.NotificationTemplateTerm;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class EmailUtilities {
	private static Log _log = LogFactoryUtil.getLog(EmailUtilities.class);

	public static JSONObject createEmailBody(Notificationtemplate template, NotificationQueue queue) {
		JSONObject payload = JSONFactoryUtil.createJSONObject();
		JSONObject payLoadData = JSONFactoryUtil.createJSONObject();

		try {
			payLoadData = JSONFactoryUtil.createJSONObject(queue.getPayload());

		} catch (JSONException e) {
			_log.error(e);
		}

		payload.put(EmailTerm.TO_NAME, queue.getToUsername());
		payload.put(EmailTerm.TO_ADDRESS, queue.getToEmail());

		String toUserId = payLoadData.getString(EmailTerm.TO_USER_ID);

		String detailURL = template.getGuestUrlPattern();

		if (Validator.isNotNull(toUserId)) {
			detailURL = template.getUserUrlPattern() + queue.getClassPK();
		}
		
		if (queue.getClassName().equals(MailVariables.DOCFILE_CLASS_NAME)) {			
			if (queue.getNotificationType().equals(NotificationTemplateTerm.DOCUMENT_01)) {
				payload.put(EmailTerm.SUBJECT, StringUtil.replace(template.getEmailSubject(),
						new String[] { 
								MailVariables.USERNAME, 
								MailVariables.FROMUSER,
								MailVariables.OBJECT
							},
						new String[] { 
								payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.FROMUSER), 
								payLoadData.getString(MailVariables.OBJECT)
					}));
				payload.put(EmailTerm.BODY, StringUtil.replace(template.getEmailBody(),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.ISSUERNAME,
								MailVariables.CODENO, MailVariables.PROMULGATIONDATE, MailVariables.PROMULGATIONPLACE
								},
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL, payLoadData.getString(MailVariables.ISSUERNAME),
								payLoadData.getString(MailVariables.CODENO),
								payLoadData.getString(MailVariables.PROMULGATIONDATE),
								payLoadData.getString(MailVariables.PROMULGATIONPLACE)
								 }));

				payload.put(EmailTerm.TEXT_MESSAGE, StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT
								 },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));	
			}
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.DOCUMENT_02)) {
				payload.put(EmailTerm.SUBJECT, StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put(EmailTerm.BODY, StringUtil.replace(template.getEmailBody(),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.ISSUERNAME,
								MailVariables.CODENO, MailVariables.PROMULGATIONDATE, MailVariables.PROMULGATIONPLACE,
								MailVariables.STATUS
								 },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL, payLoadData.getString(MailVariables.ISSUERNAME),
								payLoadData.getString(MailVariables.CODENO),
								payLoadData.getString(MailVariables.PROMULGATIONDATE),
								payLoadData.getString(MailVariables.PROMULGATIONPLACE),
								payLoadData.getString(MailVariables.STATUS)
								 }));

				payload.put(EmailTerm.TEXT_MESSAGE, StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT
								 },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT)
								 }));	
				
			}
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.DOCUMENT_03)) {
				payload.put(EmailTerm.SUBJECT, StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put(EmailTerm.BODY, StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.ISSUERNAME,
								MailVariables.CODENO, MailVariables.PROMULGATIONDATE, MailVariables.PROMULGATIONPLACE },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL, payLoadData.getString(MailVariables.ISSUERNAME),
								payLoadData.getString(MailVariables.CODENO),
								payLoadData.getString(MailVariables.PROMULGATIONDATE),
								payLoadData.getString(MailVariables.PROMULGATIONPLACE) }));

				payload.put(EmailTerm.TEXT_MESSAGE, StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT,
								EmailTerm.ACTION_COMMENT_VARIABLE, EmailTerm.SECRET_VARIABLE },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));	
				
			}
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.FILEATTACH)) {
				payload.put(EmailTerm.SUBJECT, StringUtil.replace(template.getEmailSubject(),
						new String[] { 
								MailVariables.FROMUSER, 
								MailVariables.USEREMAIL, 
								MailVariables.USERNAME,
								MailVariables.OBJECT, 
								MailVariables.DETAILURL },
						new String[] { 
								payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.USERNAME), 
								payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put(EmailTerm.BODY, StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
						new String[] { 
								MailVariables.USERNAME, 
								MailVariables.USEREMAIL, 
								MailVariables.FROMUSER,
								MailVariables.OBJECT, 
								MailVariables.DETAILURL, 
								MailVariables.FILENAME },
						new String[] { 
								payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), 
								payLoadData.getString(MailVariables.OBJECT),
								detailURL,
								payLoadData.getString(MailVariables.FILENAME) }));

				payload.put(EmailTerm.TEXT_MESSAGE, StringUtil.replace(template.getTextMessage(),
						new String[] { 
								MailVariables.FROMUSER, 
								MailVariables.OBJECT },
						new String[] { 
								payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));																
			}
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.VOTING_01)) {
				payload.put(EmailTerm.SUBJECT, StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.FROMUSER, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put(EmailTerm.BODY, StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.SUBJECT },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL,
								payLoadData.getString(MailVariables.SUBJECT) }));

				payload.put(EmailTerm.TEXT_MESSAGE, StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));																
			}			
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.COMMENT)) {
				payload.put(EmailTerm.SUBJECT, StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put(EmailTerm.BODY, StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));

				payload.put(EmailTerm.TEXT_MESSAGE, StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));																
			}
			else {
				payload.put(EmailTerm.SUBJECT,
						StringUtil.replace(template.getEmailSubject(),
								new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER, MailVariables.OBJECT,
										MailVariables.DETAILURL, MailVariables.ACTION_COMMENT },
								new String[] { payLoadData.getString(MailVariables.JSON_FROMUSER), payLoadData.getString(MailVariables.JSON_USEREMAIL),
										payLoadData.getString(MailVariables.JSON_FROMUSER), payLoadData.getString(MailVariables.JSON_OBJECT), detailURL,
										payLoadData.getString(MailVariables.JSON_ACTION_COMMENT) }));
				payload.put(EmailTerm.BODY,
						StringUtil.replace(template.getEmailBody(),
								new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER, MailVariables.OBJECT,
										MailVariables.DETAILURL, MailVariables.ACTION_COMMENT, MailVariables.SECRET },
								new String[] { payLoadData.getString(MailVariables.JSON_USERNAME), payLoadData.getString(MailVariables.JSON_USEREMAIL),
										payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.JSON_OBJECT), detailURL,
										payLoadData.getString(MailVariables.JSON_ACTION_COMMENT), payLoadData.getString(MailVariables.JSON_SECRET) }));

				payload.put(EmailTerm.TEXT_MESSAGE,
						StringUtil.replace(template.getTextMessage(),
								new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER, MailVariables.OBJECT,
										MailVariables.DETAILURL, MailVariables.ACTION_COMMENT },
								new String[] { payLoadData.getString(MailVariables.JSON_USERNAME), payLoadData.getString(MailVariables.JSON_USEREMAIL),
										payLoadData.getString(MailVariables.JSON_FROMUSER), payLoadData.getString(MailVariables.JSON_OBJECT), detailURL,
										payLoadData.getString(MailVariables.JSON_ACTION_COMMENT) }));				
			}
		}
		else if (queue.getClassName().equals(MailVariables.DOCARCHIVE_CLASS_NAME)) {
			payload.put(EmailTerm.SUBJECT, StringUtil.replace(template.getEmailSubject(),
					new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
							MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.ACTION_COMMENT },
					new String[] { payLoadData.getString(MailVariables.USERNAME),
							payLoadData.getString(MailVariables.USEREMAIL),
							payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
							detailURL, payLoadData.getString(MailVariables.JSON_ACTION_COMMENT) }));
			payload.put(EmailTerm.BODY, StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
					new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
							MailVariables.OBJECT, MailVariables.DETAILURL,
							MailVariables.ACTION_COMMENT, MailVariables.SECRET },
					new String[] { payLoadData.getString(MailVariables.USERNAME),
							payLoadData.getString(MailVariables.USEREMAIL),
							payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
							detailURL,
							payLoadData.getString(MailVariables.JSON_ACTION_COMMENT), payLoadData.getString(MailVariables.JSON_SECRET) }));

			payload.put(EmailTerm.TEXT_MESSAGE, StringUtil.replace(template.getTextMessage(),
					new String[] { MailVariables.FROMUSER, MailVariables.OBJECT,
							MailVariables.ACTION_COMMENT, MailVariables.SECRET },
					new String[] { payLoadData.getString(MailVariables.FROMUSER),
							payLoadData.getString(MailVariables.OBJECT),
							payLoadData.getString(MailVariables.JSON_ACTION_COMMENT), payLoadData.getString(MailVariables.JSON_SECRET) }));															
		}
		else if (queue.getClassName().equals(MailVariables.ACTIVITY_CLASS_NAME)) {			
			if (queue.getNotificationType().equals(NotificationTemplateTerm.ALBUMFILE)) {
				payload.put(EmailTerm.SUBJECT, StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.FROMUSER, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.ACTION_COMMENT },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL, payLoadData.getString( MailVariables.JSON_ACTION_COMMENT) }));
				payload.put(EmailTerm.BODY, StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.FILENAME,
								MailVariables.ACTION_COMMENT, MailVariables.SECRET },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL,
								payLoadData.getString(MailVariables.FILENAME),
								payLoadData.getString(MailVariables.JSON_ACTION_COMMENT), payLoadData.getString(MailVariables.JSON_SECRET) }));

				payload.put(EmailTerm.TEXT_MESSAGE, StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT,
								MailVariables.ACTION_COMMENT, MailVariables.SECRET },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT),
								payLoadData.getString(MailVariables.JSON_ACTION_COMMENT), payLoadData.getString(MailVariables.JSON_SECRET) }));																
			}
		}
		else {
			payload.put(EmailTerm.SUBJECT,
					StringUtil.replace(template.getEmailSubject(),
							new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER, MailVariables.OBJECT,
									MailVariables.DETAILURL, MailVariables.ACTION_COMMENT },
							new String[] { payLoadData.getString(MailVariables.JSON_USERNAME), payLoadData.getString(MailVariables.JSON_USEREMAIL),
									payLoadData.getString(MailVariables.JSON_FROMUSER), payLoadData.getString(MailVariables.JSON_OBJECT), detailURL,
									payLoadData.getString(MailVariables.JSON_ACTION_COMMENT) }));
			payload.put(EmailTerm.BODY,
					StringUtil.replace(template.getEmailBody(),
							new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER, MailVariables.OBJECT,
									MailVariables.DETAILURL, MailVariables.ACTION_COMMENT, MailVariables.SECRET },
							new String[] { payLoadData.getString(MailVariables.JSON_USERNAME), payLoadData.getString(MailVariables.JSON_USEREMAIL),
									payLoadData.getString(MailVariables.JSON_FROMUSER), payLoadData.getString(MailVariables.JSON_OBJECT), detailURL,
									payLoadData.getString(MailVariables.JSON_ACTION_COMMENT), payLoadData.getString(MailVariables.JSON_SECRET) }));

			payload.put(EmailTerm.TEXT_MESSAGE,
					StringUtil.replace(template.getTextMessage(),
							new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER, MailVariables.OBJECT,
									MailVariables.DETAILURL, MailVariables.ACTION_COMMENT },
							new String[] { payLoadData.getString(MailVariables.JSON_USERNAME), payLoadData.getString(MailVariables.JSON_USEREMAIL),
									payLoadData.getString(MailVariables.JSON_FROMUSER), payLoadData.getString(MailVariables.JSON_OBJECT), detailURL,
									payLoadData.getString(MailVariables.JSON_ACTION_COMMENT) }));
		}

		return payload;
	}
}
