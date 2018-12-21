
package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossierfile.model.DossierFileSearchResultModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.model.DossierFile;

public class DossierFileUtils {
	private static final Log _log = LogFactoryUtil.getLog(DossierFileUtils.class);
    public static List<DossierFileModel> mappingToDossierFileData(
        List<DossierFile> dossierFiles) {

        List<DossierFileModel> outputs = new ArrayList<DossierFileModel>();

        for (DossierFile dossierFile : dossierFiles) {

            DossierFileModel model = mappingToDossierFileModel(dossierFile);

            outputs.add(model);
        }

        return outputs;
    }

    public static DossierFileModel mappingToDossierFileModel(
        DossierFile dossierFile) {

        if (dossierFile == null) {
            return null;
        }

        DossierFileModel model = new DossierFileModel();

        model.setCreateDate(
            APIDateTimeUtils.convertDateToString(dossierFile.getCreateDate()));
        model.setModifiedDate(
            APIDateTimeUtils.convertDateToString(
                dossierFile.getModifiedDate()));
        model.setReferenceUid(dossierFile.getReferenceUid());
        model.setDossierTemplateNo(dossierFile.getDossierTemplateNo());
        model.setDossierPartNo(dossierFile.getDossierPartNo());
        model.setDossierPartType(dossierFile.getDossierPartType());
        model.setFileTemplateNo(dossierFile.getFileTemplateNo());
        model.setDisplayName(dossierFile.getDisplayName());

        String fileType = StringPool.BLANK;
        long fileSize = 0;
        String fileVersion = StringPool.BLANK;

        if (dossierFile.getDossierFileId() > 0) {
            try {
                FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
                    dossierFile.getFileEntryId());
                DLFileVersion dlFileVersion =
                    DLFileVersionLocalServiceUtil.getLatestFileVersion(
                        fileEntry.getFileEntryId(), true);

                fileType = dlFileVersion.getExtension();
                fileSize = dlFileVersion.getSize();
                fileVersion = dlFileVersion.getVersion();
            }
            catch (Exception e) {
            	//_log.error(e);
            	_log.debug(e);
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
        model.setFormReport(StringPool.BLANK);
        model.setFormScript(dossierFile.getFormScript());
        model.setFormData(dossierFile.getFormData());
        model.setDossierFileId(dossierFile.getDossierFileId());
        model.setDossierId(dossierFile.getDossierId());
        return model;
    }

    public static List<DossierFileSearchResultModel> mappingToDossierFileSearchResultsModel(
        List<Document> documents) {

        List<DossierFileSearchResultModel> outputs =
            new ArrayList<DossierFileSearchResultModel>();

        for (Document document : documents) {
        
            DossierFileSearchResultModel model =
                new DossierFileSearchResultModel();
            
            long dossierId = GetterUtil.getLong(document.get(DossierFileTerm.DOSSIER_ID));
            long dossierFileId = GetterUtil.getLong(document.get(DossierFileTerm.DOSSIER_FILE_ID));

            model.setDossierId(dossierId);
            model.setReferenceUid(document.get(DossierFileTerm.REFERENCE_UID));
            model.setDossierTemplateNo(document.get(DossierFileTerm.DOSSIER_TEMPLATE_NO));
            model.setDossierPartNo(document.get(DossierFileTerm.DOSSIER_PART_NO));
            model.setDossierPartType(GetterUtil.getInteger(document.get(DossierFileTerm.DOSSIER_PART_TYPE)));
            model.setFileTemplateNo(document.get(DossierFileTerm.FILE_TEMPLATE_NO));
            model.setDisplayName(document.get(DossierFileTerm.DISPLAY_NAME));
            
            String fileType = StringPool.BLANK;
            
            long fileSize = 0;
            
            //String fileVersion = StringPool.BLANK;

            if (dossierFileId > 0) {
                try {
                    FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
                        dossierFileId);
                    DLFileVersion dlFileVersion =
                        DLFileVersionLocalServiceUtil.getLatestFileVersion(
                            fileEntry.getFileEntryId(), true);

                    fileType = dlFileVersion.getExtension();
                    fileSize = dlFileVersion.getSize();
                   // fileVersion = dlFileVersion.getVersion();
                }
                catch (Exception e) {
                	//_log.error(e);
                	_log.debug(e);
                }
            }
            
            model.setFileType(fileType);
            model.setFileSize(fileSize);
            model.setOriginal(GetterUtil.getBoolean(document.get(DossierFileTerm.ORIGINAL)));
            model.setIsNew(GetterUtil.getBoolean(document.get(DossierFileTerm.IS_NEW)));
            model.setSignCheck(GetterUtil.getInteger(document.get(DossierFileTerm.SIGN_CHECK)));
            model.setSignInfo(document.get(DossierFileTerm.SIGN_INFO));
            model.setDossierFileId(dossierFileId);
            model.setDossierId(dossierId);

            outputs.add(model);
        }

        return outputs;

    }
}
