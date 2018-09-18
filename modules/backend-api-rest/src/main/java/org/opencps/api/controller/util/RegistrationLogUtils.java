
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.opencps.api.registrationlog.model.RegistrationLogModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.RegistrationLogTerm;
import org.opencps.dossiermgt.model.RegistrationLog;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationLogUtils {

	public static List<RegistrationLogModel> mappingToRegistrationLoggData(List<RegistrationLog> lstregistrationLogs) {

		List<RegistrationLogModel> outputs = new ArrayList<RegistrationLogModel>();

		for (RegistrationLog registrationLog : lstregistrationLogs) {

			RegistrationLogModel model = mappingToRegistrationLogModel(registrationLog);

			outputs.add(model);
		}

		return outputs;
	}
	
	public static List<RegistrationLogModel> mappingToRegistrationLoggDataListDocument(List<Document> lstregistrationLogs) {

		List<RegistrationLogModel> outputs = new ArrayList<RegistrationLogModel>();

		for (Document document : lstregistrationLogs) {

			RegistrationLogModel model = new RegistrationLogModel();
			long registrationLogId = GetterUtil.getLong(document.get("entryClassPK"));

			
			String strDate = document.get(RegistrationLogTerm.CREATE_DATE);
			
			Date date = null;
			
			if (Validator.isNotNull(strDate)) {
				date = APIDateTimeUtils.convertStringToDate(strDate, APIDateTimeUtils._LUCENE_PATTERN);
			}
			model.setCreateDate(date != null ? APIDateTimeUtils.convertDateToString(date, APIDateTimeUtils._TIMESTAMP): strDate);
			model.setAuthor(document.get(RegistrationLogTerm.AUTHOR));
			model.setContent(document.get(RegistrationLogTerm.CONTENT));
			model.setPayload(document.get(RegistrationLogTerm.PAYLOAD));
			model.setRegistrationLogId(registrationLogId);
			
			outputs.add(model);
		}

		return outputs;
	}

	public static RegistrationLogModel mappingToRegistrationLogModel(RegistrationLog registrationLog) {

		if (registrationLog == null) {
			return null;
		}
		// int notificationType =
		// GetterUtil.getInteger(dossierLog.getNotificationType());
		RegistrationLogModel model = new RegistrationLogModel();

		model.setCreateDate(APIDateTimeUtils.convertDateToString(registrationLog.getCreateDate()));
		model.setAuthor(registrationLog.getAuthor());
		model.setContent(registrationLog.getContent());
		model.setPayload(registrationLog.getPayload());
		model.setRegistrationLogId(registrationLog.getRegistrationLogId());

		return model;
	}
}
