package org.opencps.dossiermgt.action.util;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;

public class DossierLogUtils {
	public static String createPayload(DossierFile dossierFile, PaymentFile paymentFile, Dossier dossier) {
		String fileType = "";
		String fileUrl = "";
		if (dossierFile != null){
			if (dossierFile.getDossierFileId() > 0) {
				try {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
					DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil
							.getLatestFileVersion(fileEntry.getFileEntryId(), true);

					fileType = dlFileVersion.getExtension();
					fileUrl = dlFileVersion.getTreePath();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		JSONArray arr = JSONFactoryUtil.createJSONArray();

		if (dossierFile != null) {
			JSONObject item = JSONFactoryUtil.createJSONObject();
			obj.put("jobposTitle", "");
			obj.put("briefNote", "");

			item.put("referenceUid", dossierFile.getReferenceUid());
			item.put("fileType", fileType);
			item.put("fileUrl", fileUrl);
			item.put("displayName", dossierFile.getDisplayName());
			item.put("status", "");
			arr.put(item);

			obj.put("dossierFiles", arr);
		}

		if (paymentFile != null) {
			JSONObject item = JSONFactoryUtil.createJSONObject();
			obj.put("jobposTitle", "");
			obj.put("briefNote", "");

			item.put("referenceUid", paymentFile.getReferenceUid());
			item.put("fileType", fileType);
			item.put("fileUrl", fileUrl);
			item.put("displayName", "");
			item.put("paymentRequestDate", paymentFile.getCreateDate());
			item.put("paymentStatus", paymentFile.getPaymentStatus());
			arr.put(item);

			obj.put("dossierFiles", arr);
		}

		if (dossier != null) {
			JSONObject item = JSONFactoryUtil.createJSONObject();
			obj.put("jobposTitle", "");
			obj.put("briefNote", "");

			item.put("referenceUid", dossier.getReferenceUid());
			item.put("fileType", fileType);
			item.put("fileUrl", fileUrl);
			item.put("displayName", "");
			arr.put(item);

			obj.put("dossierFiles", arr);
		}
		return obj.toString();
	}

}
