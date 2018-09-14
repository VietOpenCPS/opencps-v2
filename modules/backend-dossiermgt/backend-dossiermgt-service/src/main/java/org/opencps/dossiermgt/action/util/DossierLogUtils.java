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

import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;

public class DossierLogUtils {
	private static final Log _log = LogFactoryUtil.getLog(DossierLogUtils.class);
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
			obj.put("jobposTitle", dossierFile.getUserId() + ": DossierFile Create");
			obj.put("briefNote", "");

			item.put("referenceUid", dossierFile.getReferenceUid());
			item.put("fileType", fileType);
			item.put("fileUrl", fileUrl);
			item.put("displayName", dossierFile.getDisplayName());
			item.put("status", "");
			arr.put(item);

			obj.put("dossierFiles", arr);
		}

		if (Validator.isNotNull(paymentFile)) {
			JSONObject item = JSONFactoryUtil.createJSONObject();
			obj.put("jobposTitle", paymentFile.getUserId()+ ": PaymentFile Create" );
			obj.put("briefNote", "");

			item.put("referenceUid", paymentFile.getReferenceUid());
			item.put("fileType", fileType);
			item.put("fileUrl", fileUrl);
			item.put("displayName", "");
			item.put("paymentRequestDate", paymentFile.getCreateDate());
			item.put("paymentStatus", paymentFile.getPaymentStatus());
			arr.put(item);

			obj.put("paymentFile", arr);
		}

		if (Validator.isNotNull(dossier)) {
			if (dossier.getDossierStatus().contentEquals(DossierStatusConstants.WAITING)) {
				obj.put("jobposTitle", dossier.getUserId() + ": Request Addition Dossier");
			} else if (dossier.getCancellingDate() != null && dossier.getSubmitting() == true) {
				obj.put("jobposTitle", dossier.getUserId() + ": Request Delete Dossier");
			} else if (dossier.getCorrecttingDate() != null && dossier.getSubmitting() == true) {
				obj.put("jobposTitle", dossier.getUserId() + ": Request Correctting Dossier");
			} else {
				obj.put("jobposTitle", dossier.getUserId() + ": Create Dossier");
			}
			
			obj.put("briefNote", dossier.getBriefNote());

			JSONObject item = JSONFactoryUtil.createJSONObject();

			item.put("referenceUid", dossier.getReferenceUid());
			arr.put(item);

			obj.put("dossier", arr);
		}
		return obj.toString();
	}

}
