package backend.api.rest.application.v21.parser;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.util.ArrayList;
import java.util.List;

import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.rest.application.model.DossierDocumentModel;

public class DossierDocumentParser {
	private static Log _log = LogFactoryUtil.getLog(DocumentTypeParser.class);
	public static List<DossierDocumentModel> mappingDocumentResultModel(List<DossierDocument> docList) {
//		Gson gson = new Gson();
		if (docList != null && docList.size() > 0) {
			List<DossierDocumentModel> docModelList = new ArrayList<DossierDocumentModel>();
			for (DossierDocument doc : docList) {
				//String strDoc = JSONFactoryUtil.looseSerialize(doc);
				//_log.info("strDoc: "+strDoc);
				DossierDocumentModel model = mappingDocumentTypeModel(doc);

				docModelList.add(model);
			}
			return docModelList;
		} else{
			return null;
		}

	}

	public static DossierDocumentModel mappingDocumentTypeModel(DossierDocument doc) {
		
//		Gson gson = new Gson();
		String strDoc = JSONFactoryUtil.looseSerialize(doc);
		//_log.info("strDoc: " + strDoc);
		DossierDocumentModel object = JSONFactoryUtil.looseDeserialize(strDoc, DossierDocumentModel.class);
		String fileType = StringPool.BLANK;
		long fileSize = 0;
		if (object != null) {
			long documentFileId = object.getDocumentFileId();
			if (documentFileId > 0) {
				try {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(documentFileId);
					DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil
							.getLatestFileVersion(fileEntry.getFileEntryId(), true);

					fileType = dlFileVersion.getMimeType();
					fileSize = dlFileVersion.getSize();
				} catch (Exception e) {
					_log.error(e);
					_log.info("No file entry Id");
				}
			}
			object.setFileType(fileType);
			object.setFileSize(fileSize);
		}
		return object;
	}
}