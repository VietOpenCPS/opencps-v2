package org.opencps.communication.utils;

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

		payload.put("toName", queue.getToUsername());
		payload.put("toAddress", queue.getToEmail());

		String toUserId = payLoadData.getString("toUserId");

		String detailURL = template.getGuestUrlPattern();

		if (Validator.isNotNull(toUserId)) {
			detailURL = template.getUserUrlPattern() + queue.getClassPK();
		}
		
		if (queue.getClassName().equals(MailVariables.DOCFILE_CLASS_NAME)) {			
			if (queue.getNotificationType().equals(NotificationTemplateTerm.DOCUMENT_01)) {
				payload.put("subject", StringUtil.replace(template.getEmailSubject(),
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
				payload.put("body", StringUtil.replace(template.getEmailBody(),
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

				payload.put("textMessage", StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT
								 },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));	
			}
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.DOCUMENT_02)) {
				payload.put("subject", StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put("body", StringUtil.replace(template.getEmailBody(),
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

				payload.put("textMessage", StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT
								 },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT)
								 }));	
				
			}
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.DOCUMENT_03)) {
				payload.put("subject", StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put("body", StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
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

				payload.put("textMessage", StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT,
								"[$ACTIONCOMMENT$]", "[$PASSWORD$]" },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));	
				
			}
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.FILEATTACH)) {
				payload.put("subject", StringUtil.replace(template.getEmailSubject(),
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
				payload.put("body", StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
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

				payload.put("textMessage", StringUtil.replace(template.getTextMessage(),
						new String[] { 
								MailVariables.FROMUSER, 
								MailVariables.OBJECT },
						new String[] { 
								payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));																
			}
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.VOTING_01)) {
				payload.put("subject", StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.FROMUSER, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put("body", StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.SUBJECT },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL,
								payLoadData.getString(MailVariables.SUBJECT) }));

				payload.put("textMessage", StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));																
			}			
			else if (queue.getNotificationType().equals(NotificationTemplateTerm.COMMENT)) {
				payload.put("subject", StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));
				payload.put("body", StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL }));

				payload.put("textMessage", StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT) }));																
			}
			else {
				payload.put("subject",
						StringUtil.replace(template.getEmailSubject(),
								new String[] { "[$USERNAME$]", "[$USEREMAIL$]", "[$FORMUSER$]", "[$OBJECT$]",
										"[$DETAILURL$]", "[$ACTIONCOMMENT$]" },
								new String[] { payLoadData.getString("USERNAME"), payLoadData.getString("USEREMAIL"),
										payLoadData.getString("FORMUSER"), payLoadData.getString("OBJECT"), detailURL,
										payLoadData.getString("ACTIONCOMMENT") }));
				payload.put("body",
						StringUtil.replace(template.getEmailBody(),
								new String[] { "[$USERNAME$]", "[$USEREMAIL$]", "[$FORMUSER$]", "[$OBJECT$]",
										"[$DETAILURL$]", "[$ACTIONCOMMENT$]", "[$PASSWORD$]" },
								new String[] { payLoadData.getString("USERNAME"), payLoadData.getString("USEREMAIL"),
										payLoadData.getString("FORMUSER"), payLoadData.getString("OBJECT"), detailURL,
										payLoadData.getString("ACTIONCOMMENT"), payLoadData.getString("PASSWORD") }));

				payload.put("textMessage",
						StringUtil.replace(template.getTextMessage(),
								new String[] { "[$USERNAME$]", "[$USEREMAIL$]", "[$FORMUSER$]", "[$OBJECT$]",
										"[$DETAILURL$]", "[$ACTIONCOMMENT$]" },
								new String[] { payLoadData.getString("USERNAME"), payLoadData.getString("USEREMAIL"),
										payLoadData.getString("FORMUSER"), payLoadData.getString("OBJECT"), detailURL,
										payLoadData.getString("ACTIONCOMMENT") }));				
			}
		}
		else if (queue.getClassName().equals(MailVariables.DOCARCHIVE_CLASS_NAME)) {
			payload.put("subject", StringUtil.replace(template.getEmailSubject(),
					new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
							MailVariables.OBJECT, MailVariables.DETAILURL, "[$ACTIONCOMMENT$]" },
					new String[] { payLoadData.getString(MailVariables.USERNAME),
							payLoadData.getString(MailVariables.USEREMAIL),
							payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
							detailURL, payLoadData.getString("ACTIONCOMMENT") }));
			payload.put("body", StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
					new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
							MailVariables.OBJECT, MailVariables.DETAILURL,
							"[$ACTIONCOMMENT$]", "[$PASSWORD$]" },
					new String[] { payLoadData.getString(MailVariables.USERNAME),
							payLoadData.getString(MailVariables.USEREMAIL),
							payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
							detailURL,
							payLoadData.getString("ACTIONCOMMENT"), payLoadData.getString("PASSWORD") }));

			payload.put("textMessage", StringUtil.replace(template.getTextMessage(),
					new String[] { MailVariables.FROMUSER, MailVariables.OBJECT,
							"[$ACTIONCOMMENT$]", "[$PASSWORD$]" },
					new String[] { payLoadData.getString(MailVariables.FROMUSER),
							payLoadData.getString(MailVariables.OBJECT),
							payLoadData.getString("ACTIONCOMMENT"), payLoadData.getString("PASSWORD") }));															
		}
		else if (queue.getClassName().equals(MailVariables.ACTIVITY_CLASS_NAME)) {			
			if (queue.getNotificationType().equals(NotificationTemplateTerm.ALBUMFILE)) {
				payload.put("subject", StringUtil.replace(template.getEmailSubject(),
						new String[] { MailVariables.FROMUSER, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, "[$ACTIONCOMMENT$]" },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL, payLoadData.getString("ACTIONCOMMENT") }));
				payload.put("body", StringUtil.replace(HtmlUtil.unescape(template.getEmailBody()),
						new String[] { MailVariables.USERNAME, MailVariables.USEREMAIL, MailVariables.FROMUSER,
								MailVariables.OBJECT, MailVariables.DETAILURL, MailVariables.FILENAME,
								"[$ACTIONCOMMENT$]", "[$PASSWORD$]" },
						new String[] { payLoadData.getString(MailVariables.USERNAME),
								payLoadData.getString(MailVariables.USEREMAIL),
								payLoadData.getString(MailVariables.FROMUSER), payLoadData.getString(MailVariables.OBJECT),
								detailURL,
								payLoadData.getString(MailVariables.FILENAME),
								payLoadData.getString("ACTIONCOMMENT"), payLoadData.getString("PASSWORD") }));

				payload.put("textMessage", StringUtil.replace(template.getTextMessage(),
						new String[] { MailVariables.FROMUSER, MailVariables.OBJECT,
								"[$ACTIONCOMMENT$]", "[$PASSWORD$]" },
						new String[] { payLoadData.getString(MailVariables.FROMUSER),
								payLoadData.getString(MailVariables.OBJECT),
								payLoadData.getString("ACTIONCOMMENT"), payLoadData.getString("PASSWORD") }));																
			}
		}
		else {
			payload.put("subject",
					StringUtil.replace(template.getEmailSubject(),
							new String[] { "[$USERNAME$]", "[$USEREMAIL$]", "[$FORMUSER$]", "[$OBJECT$]",
									"[$DETAILURL$]", "[$ACTIONCOMMENT$]" },
							new String[] { payLoadData.getString("USERNAME"), payLoadData.getString("USEREMAIL"),
									payLoadData.getString("FORMUSER"), payLoadData.getString("OBJECT"), detailURL,
									payLoadData.getString("ACTIONCOMMENT") }));
			payload.put("body",
					StringUtil.replace(template.getEmailBody(),
							new String[] { "[$USERNAME$]", "[$USEREMAIL$]", "[$FORMUSER$]", "[$OBJECT$]",
									"[$DETAILURL$]", "[$ACTIONCOMMENT$]", "[$PASSWORD$]" },
							new String[] { payLoadData.getString("USERNAME"), payLoadData.getString("USEREMAIL"),
									payLoadData.getString("FORMUSER"), payLoadData.getString("OBJECT"), detailURL,
									payLoadData.getString("ACTIONCOMMENT"), payLoadData.getString("PASSWORD") }));

			payload.put("textMessage",
					StringUtil.replace(template.getTextMessage(),
							new String[] { "[$USERNAME$]", "[$USEREMAIL$]", "[$FORMUSER$]", "[$OBJECT$]",
									"[$DETAILURL$]", "[$ACTIONCOMMENT$]" },
							new String[] { payLoadData.getString("USERNAME"), payLoadData.getString("USEREMAIL"),
									payLoadData.getString("FORMUSER"), payLoadData.getString("OBJECT"), detailURL,
									payLoadData.getString("ACTIONCOMMENT") }));
		}

		return payload;
	}
}
