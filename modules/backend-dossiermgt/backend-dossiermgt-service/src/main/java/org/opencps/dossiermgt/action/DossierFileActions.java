package org.opencps.dossiermgt.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.opencps.dossiermgt.model.DossierFile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierFileActions {

	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, String isSync, ServiceContext serviceContext)
		throws SystemException, PortalException ;

	public DossierFile cloneDossierFile(long groupId, long dossierId, long dossierFileId, String dossierTemplateNo,
			String dossierPartNo, ServiceContext serviceContext) 
		throws SystemException, PortalException;

	public DossierFile updateDossierFile(long groupId, long dossierId, String referenceUid, String sourceFileName,
			InputStream inputStream, ServiceContext serviceContext) 
		throws SystemException, PortalException;

	public DossierFile deleteDossierFile(long groupId, long dossierId, String referenceUid, ServiceContext serviceContext) 
			throws PortalException;

	public void deleteAllDossierFile(long groupId, long dossierId, String fileTemplateNo, ServiceContext serviceContext) 
			throws PortalException;

	public JSONObject getDossierFiles(long groupId, String keyword, String template, Integer type, Boolean owner,
			Boolean original, int start, int end, String sort, String order, ServiceContext serviceContext);

	public DossierFile updateDossierFileFormData(long groupId, long dossierId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException;

	public DossierFile resetDossierFileFormData(long groupId, long dossierId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException;
	
	public void copyFile(String orgFileName, String targetFileName) throws IOException;
	public void zipDirectory(File dir, String zipDirName) throws IOException;

	public DossierFile getDossierFileByDeliverableCode(long groupId, String deliverableCode);

	public DossierFile getDossierFileByFileTemplateNo(long id, String fileTemplateNo);

	public void uploadFileEntry(String name, InputStream inputStream, ServiceContext serviceContext);

	public DossierFile addDossierFileEForm(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, String isSync, ServiceContext serviceContext)
		throws SystemException, PortalException ;

	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, String isSync, String formScript, String formReport,
			boolean eForm, String formData, ServiceContext serviceContext) throws SystemException, PortalException;

}
