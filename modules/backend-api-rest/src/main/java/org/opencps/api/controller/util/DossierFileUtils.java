package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.dossierfilemodel.DossierFileModel;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.model.DossierFile;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;


public class DossierFileUtils {

	public static List<DossierFileModel> mappingToDossierFileData(List<DossierFile> dossierFiles) {
		
		List<DossierFileModel> outputs = new ArrayList<DossierFileModel>();

		for (DossierFile dossierFile : dossierFiles) {
			
			DossierFileModel model = mappingToDossierFileModel(dossierFile);
			
			outputs.add(model);
		}

		return outputs;
	}

	public static DossierFileModel mappingToDossierFileModel(DossierFile dossierFile) {
		if(dossierFile == null) {
			return null;
		}
		
		DossierFileModel model = new DossierFileModel();
		
		model.setCreateDate(APIDateTimeUtils.convertDateToString(dossierFile.getCreateDate()));
		model.setModifiedDate(APIDateTimeUtils.convertDateToString(dossierFile.getModifiedDate()));
		model.setReferenceUid(dossierFile.getReferenceUid());
		model.setDossierTemplateNo(dossierFile.getDossierTemplateNo());
		model.setDossierPartNo(dossierFile.getDossierPartNo());
		model.setDossierPartType(dossierFile.getDossierPartType());
		model.setFileTemplateNo(dossierFile.getFileTemplateNo());
		model.setDisplayName(dossierFile.getDisplayName());
		
		String fileType = "";
		long fileSize = 0;
		String fileVersion = "";
		
		if(dossierFile.getDossierFileId() > 0) {
			try {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getDossierFileId());
				DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(
						fileEntry.getFileEntryId(), true);
				
				fileType = dlFileVersion.getExtension();
				fileSize = dlFileVersion.getSize();
				fileVersion = dlFileVersion.getVersion();
			} catch(Exception e) {
				
			}
		}
		
		model.setFileType(fileType);
		model.setFileSize(fileSize);
		model.setFileVersion(fileVersion);
		model.setIsNew(dossierFile.getIsNew());
		model.setSignCheck(dossierFile.getSignCheck());
		model.setSignInfo(dossierFile.getSignInfo());
		model.setRemoved(dossierFile.getRemoved());
		model.setEForm(dossierFile.getEForm());
		
		return model;
	}

}
