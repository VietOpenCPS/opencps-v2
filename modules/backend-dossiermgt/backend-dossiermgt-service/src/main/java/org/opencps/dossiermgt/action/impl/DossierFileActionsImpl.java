package org.opencps.dossiermgt.action.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierFileActionsImpl implements DossierFileActions {

	private static final Log _log = LogFactoryUtil.getLog(DossierFileActionsImpl.class);

	@Override
	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, String isSync, ServiceContext serviceContext)
			throws SystemException, PortalException {

		return DossierFileLocalServiceUtil.addDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo,
				dossierPartNo, fileTemplateNo, displayName, sourceFileName, fileSize, inputStream, fileType, isSync,
				serviceContext);
	}

	@Override
	public DossierFile cloneDossierFile(long groupId, long dossierId, long dossierFileId, String dossierTemplateNo,
			String dossierPartNo, ServiceContext serviceContext) throws SystemException, PortalException {

		return DossierFileLocalServiceUtil.cloneDossierFile(groupId, dossierId, dossierFileId, dossierTemplateNo,
				dossierPartNo, serviceContext);
	}

	@Override
	public DossierFile updateDossierFile(long groupId, long dossierId, String referenceUid, String sourceFileName,
			InputStream inputStream, ServiceContext serviceContext) throws SystemException, PortalException {

		return DossierFileLocalServiceUtil.updateDossierFile(groupId, dossierId, referenceUid, StringPool.BLANK,
				sourceFileName, inputStream, serviceContext);
	}

	@Override
	public DossierFile deleteDossierFile(long groupId, long dossierId, String referenceUid,
			ServiceContext serviceContext) throws PortalException {

		// TODO
		// Kiem tra trang thai ho so: moi hoac y/c bo sung thi moi xoa

		// Dung co removed
		return DossierFileLocalServiceUtil.removeDossierFile(dossierId, referenceUid, serviceContext);

		// return DossierFileLocalServiceUtil.deleteDossierFile(dossierId,
		// referenceUid);
	}

	@Override
	public JSONObject getDossierFiles(long groupId, String keyword, String template, Integer type, Boolean owner,
			Boolean original, int start, int end, String sort, String order, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(serviceContext.getCompanyId());

		try {

			if (start == 0) {
				start = -1;
				end = -1;
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, keyword);
			params.put(DossierFileTerm.DOSSIER_TEMPLATE_NO, template);
			params.put(DossierFileTerm.DOSSIER_PART_TYPE, type);
			params.put(DossierFileTerm.REMOVED, String.valueOf(false));

			if (owner != null && owner.booleanValue()) {
				params.put(Field.USER_ID, serviceContext.getUserId());
			}

			params.put(DossierFileTerm.ORIGINAL, original);

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(sort + "_sortable", Sort.STRING_TYPE, GetterUtil.getBoolean(order)) };

			Hits hits = DossierFileLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DossierFileLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public DossierFile updateDossierFileFormData(long groupId, long dossierId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return DossierFileLocalServiceUtil.updateFormData(groupId, dossierId, referenceUid, formData, serviceContext);
	}

	@Override
	public void copyFile(String orgFileName, String targetFileName) throws IOException {
		// TODO Auto-generated method stub
		InputStream inStream = null;

		try {
			File afile = new File(orgFileName);
			File bfile = new File(targetFileName);
			if (!bfile.exists()) {
				bfile.createNewFile();
			}
			inStream = new FileInputStream(afile);
			try (OutputStream outStream = new FileOutputStream(bfile)) {
	
			byte[] buffer = new byte[1024];
	
				int length = 0;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {
	
				outStream.write(buffer, 0, length);
	
			}
			}
		} catch (Exception e) {
			_log.info(e);
		} finally{
			if (inStream != null) 
				inStream.close();
		}
//		_log.info("Create file " + targetFileName + " success");
	}

	@Override
	public void zipDirectory(File dir, String zipDirName) throws IOException {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		List<String> filesListInDir = new ArrayList<String>();
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				filesListInDir.add(file.getAbsolutePath());
			}

		}
		// now zip files one by one
		// create ZipOutputStream to write to the zip file
		try {
			fos = new FileOutputStream(zipDirName);
			zos = new ZipOutputStream(fos);
			ZipEntry ze = null;
			try {
				for (String filePath : filesListInDir) {
					// System.out.println("Zipping " + filePath);
					// for ZipEntry we need to keep only relative file path, so we
					// used substring on absolute path
					ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
					zos.putNextEntry(ze);
					// read the file and write to ZipOutputStream
					try (FileInputStream fis = new FileInputStream(filePath)) {
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = fis.read(buffer)) > 0) {
							zos.write(buffer, 0, len);
						}
					}
				}
			} catch (Exception e) {
				_log.error(e);
			} 
			finally{
				if (zos != null)
					zos.closeEntry();
			}
		} catch (Exception e) {
			_log.error(e);
		} finally{
			if (zos != null)
				zos.close();
			if (fos != null)
				fos.close();
		}
		
		_log.info("Zip file Successfull");

	}

	@Override
	public DossierFile resetDossierFileFormData(long groupId, long dossierId, String referenceUid, String formData,
			ServiceContext serviceContext) throws SystemException, PortalException {
		
		DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossierId, referenceUid);
		
			// String dossierTemplateNo = StringPool.BLANK;
	
			String defaultData = StringPool.BLANK;
	
			if (Validator.isNotNull(dossierFile)) {
				DossierPart part = DossierPartLocalServiceUtil.getByFileTemplateNo(groupId,
						dossierFile.getFileTemplateNo());
	
				defaultData = AutoFillFormData.sampleDataBinding(part.getSampleData(), dossierId, serviceContext);
				dossierFile = DossierFileLocalServiceUtil.getByReferenceUid(referenceUid).get(0);
				JSONObject defaultDataObj = JSONFactoryUtil.createJSONObject(defaultData);
				_log.info("Default data obj: " + defaultDataObj.toJSONString());
				defaultDataObj.put("LicenceNo", dossierFile.getDeliverableCode());
				_log.info("Default data obj: " + defaultDataObj.toJSONString());
				defaultData = defaultDataObj.toJSONString();
			}
	
			dossierFile = DossierFileLocalServiceUtil.updateFormData(groupId, dossierId, referenceUid, defaultData,
					serviceContext);
			
			//remove Deliverable
			
			String deliverableCode = dossierFile.getDeliverableCode();
			
			if (Validator.isNotNull(deliverableCode)) {
				
				//_log.info("DELETE_deliverable_" + deliverableCode);
				
				Deliverable deliverable = DeliverableLocalServiceUtil.getByCode(deliverableCode);
				
				DeliverableLocalServiceUtil.deleteDeliverable(deliverable);
			}


		return dossierFile;

	}

	@Override
	public void deleteAllDossierFile(long groupId, long dossierId, String fileTemplateNo,
			ServiceContext serviceContext) throws PortalException {
		
		//List<DossierFile> lsDossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO(dossierId, fileTemplateNo, false);
		_log.info("DOSSIERID_"+dossierId+"_FILETEMPLATENO_"+fileTemplateNo);

		List<DossierFile> lsDossierFile =  DossierFileLocalServiceUtil.getDossierFileByDID_FTN(dossierId, fileTemplateNo);
		
		Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
		
		_log.info("SIZE_DOSSIER_REMOVE_"+lsDossierFile.size());
		
		for (DossierFile file : lsDossierFile) {
			
			//DossierFileLocalServiceUtil.removeDossierFile(dossierId, file.getReferenceUid(), serviceContext);
			
			file.setRemoved(true);
			
			//set isNew = true
			if (!dossier.getDossierStatus().equals(DossierStatusConstants.NEW)) {
				
				file.setIsNew(true);
				
			}
			DossierFileLocalServiceUtil.updateDossierFile(file);
			
		}

	}

	// Get dossierFile by deliverableCode
	@Override
	public DossierFile getDossierFileByDeliverableCode(long groupId, String deliverableCode) {

		return DossierFileLocalServiceUtil.getByDeliverableCode(deliverableCode);
	}

	@Override
	public DossierFile getDossierFileByFileTemplateNo(long id, String fileTemplateNo) {

		return DossierFileLocalServiceUtil.getByFileTemplateNo(id, fileTemplateNo);
	}

	@Override
	public void uploadFileEntry(String name, InputStream inputStream, ServiceContext serviceContext) {
//		long userId = serviceContext.getUserId();

//		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);

//		long fileEntryId = 0;

		try {
//			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, dossierFile.getFileEntryId(),
//					inputStream, sourceFileName, null, 0, serviceContext);
//
//			if (fileEntry != null) {
//				fileEntryId = fileEntry.getFileEntryId();
//			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public DossierFile addDossierFileEForm(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, String isSync, ServiceContext serviceContext)
			throws SystemException, PortalException {

		return DossierFileLocalServiceUtil.addDossierFileEForm(groupId, dossierId, referenceUid, dossierTemplateNo,
				dossierPartNo, fileTemplateNo, displayName, sourceFileName, fileSize, inputStream, fileType, isSync,
				serviceContext);
	}

	@Override
	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, String isSync, String formScript, String formReport,
			boolean eForm, String formData, ServiceContext serviceContext) throws SystemException, PortalException {

		return DossierFileLocalServiceUtil.addDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo,
				dossierPartNo, fileTemplateNo, displayName, sourceFileName, fileSize, inputStream, fileType, isSync,
				formScript, formReport, eForm, formData, serviceContext);
	}

}
