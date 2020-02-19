package org.opencps.dossiermgt.action.util;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;

public class DossierLogUtils {
	private static final Log _log = LogFactoryUtil.getLog(DossierLogUtils.class);
	private static final String DOSSIER_FILE_CREATE = ": DossierFile Create";
	private static final String PAYMENT_FILE_CREATE = ": PaymentFile Create";
	private static final String DOSSIER_CREATE = ": Create Dossier";
	private static final String REQUEST_ADDITION_DOSSIER = ": Request Addition Dossier";
	private static final String REQUEST_DELETE_DOSSIER = ": Request Delete Dossier";
	private static final String REQUEST_CORRECTING_DOSSIER = ": Request Correctting Dossier";
	
	public static String createPayload(DossierFile dossierFile, PaymentFile paymentFile, Dossier dossier) {
		String fileType = StringPool.BLANK;
		String fileUrl = StringPool.BLANK;
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		JSONArray arr = JSONFactoryUtil.createJSONArray();

		if (Validator.isNotNull(dossierFile)) {
			if (dossierFile.getDossierFileId() > 0 && dossierFile.getFileEntryId() > 0) {
				try {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
					DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil
							.getLatestFileVersion(fileEntry.getFileEntryId(), true);

					fileType = dlFileVersion.getExtension();
					fileUrl = dlFileVersion.getTreePath();

				} catch (Exception e) {
//					e.printStackTrace();
					_log.error(e);
				}
			}
			
			JSONObject item = JSONFactoryUtil.createJSONObject();
			obj.put(DossierLogTerm.JOBPOS_TITLE, String.format(DossierLogTerm.LOG_FORMAT, dossierFile.getUserId(), DOSSIER_FILE_CREATE));
			obj.put(DossierLogTerm.BRIEF_NOTE, StringPool.BLANK);

			item.put(DossierLogTerm.REFERENCEUID, dossierFile.getReferenceUid());
			item.put(DossierLogTerm.FILE_TYPE, fileType);
			item.put(DossierLogTerm.FILE_URL, fileUrl);
			item.put(DossierLogTerm.DISPLAY_NAME, dossierFile.getDisplayName());
			item.put(DossierLogTerm.STATUS, StringPool.BLANK);
			arr.put(item);

			obj.put(DossierLogTerm.DOSSIER_FILES, arr);
		}

		if (Validator.isNotNull(paymentFile)) {
			JSONObject item = JSONFactoryUtil.createJSONObject();
			obj.put(DossierLogTerm.JOBPOS_TITLE, String.format(DossierLogTerm.LOG_FORMAT, paymentFile.getUserId(), PAYMENT_FILE_CREATE));
			obj.put(DossierLogTerm.BRIEF_NOTE, StringPool.BLANK);

			item.put(DossierLogTerm.REFERENCEUID, paymentFile.getReferenceUid());
			item.put(DossierLogTerm.FILE_TYPE, fileType);
			item.put(DossierLogTerm.FILE_URL, fileUrl);
			item.put(DossierLogTerm.DISPLAY_NAME, StringPool.BLANK);
			item.put(DossierLogTerm.PAYMENT_REQUEST_DATE, paymentFile.getCreateDate());
			item.put(DossierLogTerm.PAYMENT_STATUS, paymentFile.getPaymentStatus());
			arr.put(item);

			obj.put(DossierLogTerm.PAYMENT_FILE, arr);
		}

		if (Validator.isNotNull(dossier)) {
			if (dossier.getDossierStatus().contentEquals(DossierStatusConstants.WAITING)) {
				obj.put(DossierLogTerm.JOBPOS_TITLE, String.format(DossierLogTerm.LOG_FORMAT, dossier.getUserId(), REQUEST_ADDITION_DOSSIER));
			} else if (dossier.getCancellingDate() != null && dossier.getSubmitting() == true) {
				obj.put(DossierLogTerm.JOBPOS_TITLE, String.format(DossierLogTerm.LOG_FORMAT, dossier.getUserId(), REQUEST_DELETE_DOSSIER));
			} else if (dossier.getCorrecttingDate() != null && dossier.getSubmitting() == true) {
				obj.put(DossierLogTerm.JOBPOS_TITLE, String.format(DossierLogTerm.LOG_FORMAT, dossier.getUserId(), REQUEST_CORRECTING_DOSSIER));
			} else {
				obj.put(DossierLogTerm.JOBPOS_TITLE, String.format(DossierLogTerm.LOG_FORMAT, dossier.getUserId(), DOSSIER_CREATE));
			}
			
			obj.put(DossierLogTerm.BRIEF_NOTE, dossier.getBriefNote());

			JSONObject item = JSONFactoryUtil.createJSONObject();

			item.put(DossierLogTerm.REFERENCEUID, dossier.getReferenceUid());
			arr.put(item);

			obj.put(DossierLogTerm.DOSSIER, arr);
		}
		return obj.toString();
	}

}
