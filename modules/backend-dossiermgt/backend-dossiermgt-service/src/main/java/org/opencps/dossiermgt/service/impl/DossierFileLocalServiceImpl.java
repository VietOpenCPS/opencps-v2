/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.dossiermgt.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.action.util.DeliverableNumberGenerator;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.exception.DuplicateDossierFileException;
import org.opencps.dossiermgt.exception.InvalidDossierStatusException;
import org.opencps.dossiermgt.exception.NoSuchDossierFileException;
import org.opencps.dossiermgt.exception.NoSuchDossierPartException;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.base.DossierFileLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierFileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierFileLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierFileLocalServiceUtil
 */
@ProviderType
public class DossierFileLocalServiceImpl extends DossierFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierFileLocalServiceUtil} to access the
	 * dossier file local service.
	 */
	
	Log _log = LogFactoryUtil.getLog(DossierFileLocalServiceImpl.class);
	
	public DossierFile getByRefAndGroupId(long groupId, String referenceUid) throws PortalException {
		return dossierFilePersistence.fetchByGID_REF(groupId, referenceUid);
	}
	
	/**
	 * POST /dossiers/{id|referenceUid}/files
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, String isSync, ServiceContext serviceContext)
			throws PortalException, SystemException {

		long userId = serviceContext.getUserId();
		
		_log.info("****Start add file at:" + new Date());

		validateAddDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo, dossierPartNo, fileTemplateNo);
		
		_log.info("****End validator file at:" + new Date());

		DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId, dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, inputStream, sourceFileName,
					fileType, fileSize, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}
		_log.info("****End uploadFile file at:" + new Date());

		Date now = new Date();

		User userAction = null;

		if (userId != 0) {
			userAction = userLocalService.getUser(userId);
		}

		long dossierFileId = counterLocalService.increment(DossierFile.class.getName());

		DossierFile object = dossierFilePersistence.create(dossierFileId);

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(Validator.isNotNull(userAction) ? userAction.getUserId() : 0l);
		object.setUserName(Validator.isNotNull(userAction) ? userAction.getFullName() : StringPool.BLANK);

		// Add other fields

		object.setDossierId(dossierId);
		if (Validator.isNull(referenceUid)) {
			referenceUid = PortalUUIDUtil.generate();
		}

		object.setReferenceUid(referenceUid);
		object.setDossierTemplateNo(dossierTemplateNo);
		object.setFileEntryId(fileEntryId);
		object.setDossierPartNo(dossierPartNo);
		object.setFileTemplateNo(fileTemplateNo);
		object.setDossierPartType(dossierPart.getPartType());

		if (Validator.isNull(displayName)) {
			displayName = sourceFileName;
		}

		if (Validator.isNotNull(dossierPart.getFormScript())) {
			object.setEForm(true);
			object.setFormScript(dossierPart.getFormScript());
		}

		if (Validator.isNotNull(dossierPart.getFormReport())) {
			object.setFormReport(dossierPart.getFormReport());
		}
		_log.info("****Start autofill file at:" + new Date());

		_log.info("****End autofill file at:" + new Date());

		object.setDisplayName(displayName);
		object.setOriginal(true);
		
		if (Boolean.parseBoolean(isSync)) {
			object.setIsNew(true);
		}
		
//		String deliverableCode = PwdGenerator.getPassword(10);
//		
//		if (Validator.isNotNull(dossierPart.getDeliverableType())) {
//			object.setDeliverableCode(deliverableCode);
//		}
		String deliverableCode = StringPool.BLANK;
		
		if (Validator.isNotNull(dossierPart.getDeliverableType())) {
			DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.getByCode(groupId, dossierPart.getDeliverableType());
			
			deliverableCode = DeliverableNumberGenerator.generateDeliverableNumber(groupId, serviceContext.getCompanyId(), deliverableType.getDeliverableTypeId());
			object.setDeliverableCode(deliverableCode);
		}

		if (Validator.isNotNull(dossierPart.getSampleData())) {
			String formData = AutoFillFormData.sampleDataBinding(dossierPart.getSampleData(), dossierId, serviceContext);
			JSONObject formDataObj = JSONFactoryUtil.createJSONObject(formData);
			formDataObj.put("LicenceNo", deliverableCode);
			formData = formDataObj.toJSONString();
			object.setFormData(
					formData
					);
		}

		return dossierFilePersistence.update(object);
	}

	private String sampleDataBinding(String sampleData, long dossierId, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			result = JSONFactoryUtil.createJSONObject(sampleData);

			String _subjectName = StringPool.BLANK;
			String _subjectId = StringPool.BLANK;
			String _address = StringPool.BLANK;
			String _cityCode = StringPool.BLANK;
			String _cityName = StringPool.BLANK;
			String _districtCode = StringPool.BLANK;
			String _districtName = StringPool.BLANK;
			String _wardCode = StringPool.BLANK;
			String _wardName = StringPool.BLANK;
			String _contactName = StringPool.BLANK;
			String _contactTelNo = StringPool.BLANK;
			String _contactEmail = StringPool.BLANK;

			// TODO
			String _dossierFileNo = StringPool.BLANK;
			String _dossierFileDate = StringPool.BLANK;

			// get data applicant or employee
			ApplicantActions applicantActions = new ApplicantActionsImpl();

			try {
				String applicantStr = applicantActions.getApplicantByUserId(serviceContext);

				JSONObject applicantJSON = JSONFactoryUtil.createJSONObject(applicantStr);

				_subjectName = applicantJSON.getString("applicantName");
				_subjectId = applicantJSON.getString("applicantId");
				_address = applicantJSON.getString("address");
				_cityCode = applicantJSON.getString("cityCode");
				_cityName = applicantJSON.getString("cityName");
				_districtCode = applicantJSON.getString("districtCode");
				_districtName = applicantJSON.getString("districtName");
				_wardCode = applicantJSON.getString("wardCode");
				_wardName = applicantJSON.getString("wardName");
				_contactName = applicantJSON.getString("contactName");
				_contactTelNo = applicantJSON.getString("contactTelNo");
				_contactEmail = applicantJSON.getString("contactEmail");

			} catch (PortalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// process sampleData
			if (Validator.isNull(sampleData)) {
				sampleData = "{}";
			}

			Map<String, Object> jsonMap = jsonToMap(result);

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

				String value = String.valueOf(entry.getValue());

				if (value.startsWith("_") && !value.contains(":")) {

					if (value.equals("_subjectName")) {
						jsonMap.put(entry.getKey(), _subjectName);
					} else if (value.equals("_subjectId")) {
						jsonMap.put(entry.getKey(), _subjectId);
					} else if (value.equals("_address")) {
						jsonMap.put(entry.getKey(), _address);
					} else if (value.equals("_cityCode")) {
						jsonMap.put(entry.getKey(), _cityCode);
					} else if (value.equals("_cityName")) {
						jsonMap.put(entry.getKey(), _cityName);
					} else if (value.equals("_districtCode")) {
						jsonMap.put(entry.getKey(), _districtCode);
					} else if (value.equals("_districtName")) {
						jsonMap.put(entry.getKey(), _districtName);
					} else if (value.equals("_wardCode")) {
						jsonMap.put(entry.getKey(), _wardCode);
					} else if (value.equals("_wardName")) {
						jsonMap.put(entry.getKey(), _wardName);
					} else if (value.equals("_contactName")) {
						jsonMap.put(entry.getKey(), _contactName);
					} else if (value.equals("_contactTelNo")) {
						jsonMap.put(entry.getKey(), _contactTelNo);
					} else if (value.equals("_contactEmail")) {
						jsonMap.put(entry.getKey(), _contactEmail);
					}

				} else if (value.startsWith("_") && value.contains(":")) {
					String resultBinding = StringPool.BLANK;
					String[] valueSplit = value.split(":");
					for (String string : valueSplit) {
						if (string.equals("_subjectName")) {
							resultBinding += ", " + _subjectName;
						} else if (string.equals("_subjectId")) {
							resultBinding += ", " + _subjectId;
						} else if (string.equals("_address")) {
							resultBinding += ", " + _address;
						} else if (string.equals("_wardCode")) {
							resultBinding += ", " + _wardCode;
						} else if (string.equals("_wardName")) {
							resultBinding += ", " + _wardName;
						} else if (string.equals("_districtCode")) {
							resultBinding += ", " + _districtCode;
						} else if (string.equals("_districtName")) {
							resultBinding += ", " + _districtName;
						} else if (string.equals("_cityCode")) {
							resultBinding += ", " + _cityCode;
						} else if (string.equals("_cityName")) {
							resultBinding += ", " + _cityName;
						} else if (string.equals("_contactName")) {
							resultBinding += ", " + _contactName;
						} else if (string.equals("_contactTelNo")) {
							resultBinding += ", " + _contactTelNo;
						} else if (string.equals("_contactEmail")) {
							resultBinding += ", " + _contactEmail;
						}
					}

					jsonMap.put(entry.getKey(), resultBinding.replaceFirst(", ", StringPool.BLANK));

				} else if (value.startsWith("#") && value.contains("@")) {
					String newString = value.substring(1);
					String[] stringSplit = newString.split("@");
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					try {
						DossierFile dossierFile = dossierFileLocalService.getDossierFileByDID_FTNO_First(dossierId,
								paper, false, new DossierFileComparator(false, "createDate", Date.class));

						if (Validator.isNotNull(dossierFile) && Validator.isNotNull(dossierFile.getFormData())) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							String myCHK = StringPool.BLANK;
							try {
								if (variable.contains(":")) {
									String[] variableMuti = variable.split(":");
									for (String string : variableMuti) {
										myCHK += ", " + jsonOtherMap.get(string).toString();
									}
									myCHK = myCHK.replaceFirst(", ", "");
								}
							} catch (Exception e) {
								// TODO: handle exception
							}

							if (myCHK.startsWith("#")) {
								jsonMap.put(entry.getKey(), "");
							} else {
								jsonMap.put(entry.getKey(), myCHK.toString());
							}
						}
					} catch (SystemException e) {
						e.printStackTrace();
					}
				}
			}

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
				if (entry.getValue().getClass().getName().contains("JSONArray")) {
					result.put(entry.getKey(), (JSONArray) entry.getValue());
				} else if (entry.getValue().getClass().getName().contains("JSONObject")) {
					result.put(entry.getKey(), (JSONObject) entry.getValue());
				} else {
					result.put(entry.getKey(), entry.getValue() + "");
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.toJSONString();
	}

	private Map<String, Object> jsonToMap(JSONObject json) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if (Validator.isNotNull(json)) {
			try {
				retMap = toMap(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = null;
			if (Validator.isNotNull(object.getJSONArray(key))) {
				value = (JSONArray) object.getJSONArray(key);
				map.put(key, value);
			}

			else if (Validator.isNotNull(object.getJSONObject(key))) {
				value = (JSONObject) object.getJSONObject(key);
				map.put(key, value);
			}

			else {
				value = object.getString(key);
				map.put(key, value);
			}
		}
		return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.getJSONObject(i);

			if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	/**
	 * POST /dossiers/{id}/files/copyfile
	 * 
	 * @param groupId
	 * @param dossierId
	 * @param dossierFileId
	 * @param dossierTemplateNo
	 * @param dossierPartNo
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile cloneDossierFile(long groupId, long dossierId, long dossierFileId, String dossierTemplateNo,
			String dossierPartNo, ServiceContext serviceContext) throws PortalException, SystemException {

		DossierFile sourceDossierFile = dossierFilePersistence.findByPrimaryKey(dossierFileId);

		User user = userPersistence.findByPrimaryKey(serviceContext.getUserId());

		DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId, dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		if (sourceDossierFile.getFileEntryId() > 0) {
			try {
				FileEntry fileEntry = FileUploadUtils.cloneDossierFile(user.getPrimaryKey(), groupId,
						sourceDossierFile.getFileEntryId(), serviceContext);

				fileEntryId = fileEntry.getFileEntryId();
			} catch (Exception e) {
				throw new SystemException(e);
			}
		}

		long newDossierFileId = counterLocalService.increment(DossierFile.class.getName());

		DossierFile object = dossierFilePersistence.create(newDossierFileId);

		Date now = new Date();

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(user.getPrimaryKey());
		object.setUserName(user.getFullName());

		// Add other fields

		object.setDossierId(dossierId);
		object.setReferenceUid(PortalUUIDUtil.generate());
		object.setDossierTemplateNo(dossierTemplateNo);
		object.setFileEntryId(fileEntryId);
		object.setDossierPartNo(dossierPartNo);
		object.setFileTemplateNo(sourceDossierFile.getFileTemplateNo());
		object.setDossierPartType(dossierPart.getPartType());
		object.setDisplayName(sourceDossierFile.getDisplayName());
		object.setFormData(sourceDossierFile.getFormData());
		object.setOriginal(false);
		object.setIsNew(true);
		object.setFormScript(sourceDossierFile.getFormScript());
		object.setFormReport(sourceDossierFile.getFormReport());

		return dossierFilePersistence.update(object);
	}

	/**
	 * for POST /dossiers/{id}/cloning
	 * 
	 * @param groupId
	 * @param newDossierId
	 * @param oldDossierId
	 * @param dossierPartType
	 * @param serviceContext
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void cloneDossierFilesByDossierId(long groupId, long newDossierId, long oldDossierId, int dossierPartType,
			ServiceContext serviceContext) throws PortalException, SystemException {

		List<DossierFile> dossierFiles = dossierFileLocalService.getDossierFilesByD_DP(oldDossierId, dossierPartType);

		for (DossierFile dossierFile : dossierFiles) {
			cloneDossierFile(groupId, newDossierId, dossierFile.getDossierFileId(), dossierFile.getDossierTemplateNo(),
					dossierFile.getDossierPartNo(), serviceContext);
		}
	}

	/**
	 * POST /dossiers/{id|referenceUid}/files/{referenceUid}
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile updateDossierFile(long groupId, long dossierId, String referenceUid, String displayName,
			String sourceFileName, InputStream inputStream, ServiceContext serviceContext)
			throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, dossierFile.getFileEntryId(),
					inputStream, sourceFileName, null, 0, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		// Add audit fields
		dossierFile.setModifiedDate(now);
		dossierFile.setUserId(userAction.getUserId());
		dossierFile.setUserName(userAction.getFullName());

		// Add other fields

		dossierFile.setDossierId(dossierId);
		if (Validator.isNull(referenceUid)) {
			referenceUid = PortalUUIDUtil.generate();
		}

		dossierFile.setFileEntryId(fileEntryId);
		if (Validator.isNull(displayName)) {
			displayName = sourceFileName;
		}

		dossierFile.setDisplayName(displayName);
		dossierFile.setOriginal(true);
		dossierFile.setIsNew(true);

		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierFile updateFormData(long groupId, long dossierId, String referenceUid, String formData,
			ServiceContext serviceContext) throws PortalException, SystemException {

		// User user =
		// userPersistence.findByPrimaryKey(serviceContext.getUserId());
		DossierFile dossierFile = dossierFilePersistence.findByDID_REF(dossierId, referenceUid);

		// dossierFileLocalService.getDossierFileByReferenceUid(dossierId,
		// referenceUid);

		String jrxmlTemplate = dossierFile.getFormReport();

		if (Validator.isNull(jrxmlTemplate)) {
			DossierPart dossierPart = dossierPartLocalService.fetchByTemplatePartNo(groupId,
					dossierFile.getDossierTemplateNo(), dossierFile.getDossierPartNo());

			if (dossierPart == null) {
				throw new NoSuchDossierPartException();
			}

			jrxmlTemplate = dossierPart.getFormReport();

			dossierFile.setFormReport(jrxmlTemplate);
		}

		dossierFile.setFormData(formData);
		dossierFile.setIsNew(true);

		// Binhth add message bus to processing jasper file
		_log.info("IN DOSSIER FILE UPDATE FORM DATA");
		Message message = new Message();

		JSONObject msgData = JSONFactoryUtil.createJSONObject();
		msgData.put("className", DossierFile.class.getName());
		msgData.put("classPK", dossierFile.getDossierFileId());
		msgData.put("jrxmlTemplate", jrxmlTemplate);
		msgData.put("formData", formData);
		msgData.put("userId", serviceContext.getUserId());

		message.put("msgToEngine", msgData);
		MessageBusUtil.sendMessage("jasper/engine/out/destination", message);

		_log.info("SEND TO CREATED FILE MODEL");
		
		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(long dossierFileId) throws PortalException {
		DossierFile dossierFile = dossierFilePersistence.findByPrimaryKey(dossierFileId);

		return deleteDossierFile(dossierFile);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierFile removeDossierFile(long dossierId, String referenceUid, ServiceContext serviceContext)
			throws PortalException {
		User user = userPersistence.findByPrimaryKey(serviceContext.getUserId());

		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);

		Date now = new Date();

		dossierFile.setUserId(user.getUserId());
		dossierFile.setModifiedDate(now);
		dossierFile.setRemoved(true);
		dossierFile.setUserName(user.getFullName());
		
		//set submitting = true
		dossierFile.setIsNew(true);

		return dossierFileLocalService.updateDossierFile(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(long dossierId, String referenceUid) throws PortalException {
		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);
		return deleteDossierFile(dossierFile);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierFile resetDossierFile(long dossierFileId) {
		DossierFile dossierFile = dossierFilePersistence.fetchByPrimaryKey(dossierFileId);

		dossierFile.setIsNew(false);

		dossierFilePersistence.update(dossierFile);

		return dossierFile;
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(DossierFile dossierFile) throws PortalException {

		// TODO: validate remove delete dossier file
		validateDeleteDossierFile(dossierFile);

		dossierFile.setModifiedDate(new Date());
		dossierFile.setRemoved(true);

		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deletePermanentDossierFile(DossierFile dossierFile) {

		return dossierFilePersistence.remove(dossierFile);
	}

	public List<DossierFile> getDossierFilesByDossierId(long dossierId) {

		return dossierFilePersistence.findByDossierId(dossierId, false);

	}

	// Get all dossierFile by dossierId
	public List<DossierFile> getAllDossierFile(long dossierId) {

		return dossierFilePersistence.findByDID_(dossierId);

	}

	// Get all dossierFile by dossierId
	public List<DossierFile> getByReferenceUid(String referenceUid) {

		return dossierFilePersistence.findByREF_UID(referenceUid);

	}

	public List<DossierFile> getDossierFilesByD_DP(long dossierId, int dossierPartType) {
		return dossierFilePersistence.findByD_DPT(dossierId, dossierPartType, false);
	}

	// TODO: POST /dossiers/{id|referenceUid}/files/{referenceUid}

	public DossierFile getDossierFileByReferenceUid(long dossierId, String referenceUid) {

		return dossierFilePersistence.fetchByDID_REF(dossierId, referenceUid);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fileTemplateNo = GetterUtil.getString(params.get(DossierFileTerm.FILE_TEMPLATE_NO));
		String dossierPartType = GetterUtil.getString(params.get(DossierFileTerm.DOSSIER_PART_TYPE));
		String user_id = GetterUtil.getString(params.get(DossierFileTerm.USER_ID));
		String original = GetterUtil.getString(params.get(DossierFileTerm.ORIGINAL));
		String removed = GetterUtil.getString(params.get(DossierFileTerm.REMOVED));

		if (Validator.isNotNull(fileTemplateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

			query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierPartType)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierPartType);

			query.addFields(DossierFileTerm.DOSSIER_PART_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(user_id)) {
			MultiMatchQuery query = new MultiMatchQuery(user_id);

			query.addFields(DossierFileTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(original)) {
			MultiMatchQuery query = new MultiMatchQuery(original);

			query.addFields(DossierFileTerm.ORIGINAL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(removed)) {
			MultiMatchQuery query = new MultiMatchQuery(removed);

			query.addFields(DossierFileTerm.REMOVED);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fileTemplateNo = GetterUtil.getString(params.get(DossierFileTerm.FILE_TEMPLATE_NO));
		String dossierPartType = GetterUtil.getString(params.get(DossierFileTerm.DOSSIER_PART_TYPE));
		String user_id = GetterUtil.getString(params.get(DossierFileTerm.USER_ID));
		String original = GetterUtil.getString(params.get(DossierFileTerm.ORIGINAL));
		String removed = GetterUtil.getString(params.get(DossierFileTerm.REMOVED));

		if (Validator.isNotNull(fileTemplateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

			query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierPartType)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierPartType);

			query.addFields(DossierFileTerm.DOSSIER_PART_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(user_id)) {
			MultiMatchQuery query = new MultiMatchQuery(user_id);

			query.addFields(DossierFileTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(original)) {
			MultiMatchQuery query = new MultiMatchQuery(original);

			query.addFields(DossierFileTerm.ORIGINAL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(removed)) {
			MultiMatchQuery query = new MultiMatchQuery(removed);

			query.addFields(DossierFileTerm.REMOVED);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public List<DossierFile> getByDossierIdAndIsNew(long dossierId, boolean isNew) {
		return dossierFilePersistence.findByDID_ISN(dossierId, isNew, false);
	}

	/**
	 * Deny if status of dossier not new or waiting
	 * 
	 * @param dossierFile
	 * @throws PortalException
	 */
	private void validateDeleteDossierFile(DossierFile dossierFile) throws PortalException {

		Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierFile.getDossierId());

		if (dossier != null) {
			if (Validator.isNotNull(dossier.getDossierStatus()) && (!dossier.getDossierStatus().equalsIgnoreCase("new")
					|| !dossier.getDossierStatus().equalsIgnoreCase("waiting"))) {

				throw new InvalidDossierStatusException();
			}
		}
	}

	private void validateAddDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo) throws PortalException {

		// TODO add more logic here

		//dossierPersistence.findByPrimaryKey(dossierId);

		if (Validator.isNotNull(referenceUid)) {
			//DossierFile dossierFile = dossierFilePersistence.fetchByD_RUID(dossierId, referenceUid, false);
			DossierFile dossierFile = null;
			if (dossierFile != null) {
				throw new DuplicateDossierFileException("dossierId= " + dossierId + "|referenceUid=" + referenceUid);
			}
		}
	}

	public List<DossierFile> getDossierFileByDID_DPNO(long dossierId, String dossierPartNo, boolean removed) {
		return dossierFilePersistence.findByDID_DPNO(dossierId, dossierPartNo, removed);
	}
	
	public List<DossierFile> getDossierFileByDID_FTN(long dossierId, String fileTemplateNo) {
		return dossierFilePersistence.findByDID_FTN(dossierId, fileTemplateNo);
	}

	public DossierFile getDossierFileByDID_FTNO_DPT_First(long dossierId, String fileTemplateNo, int dossierPartType,
			boolean removed, OrderByComparator orderByComparator) throws NoSuchDossierFileException {
		return dossierFilePersistence.findByDID_FTNO_DPT_First(dossierId, fileTemplateNo, dossierPartType, removed,
				orderByComparator);
	}

	public List<DossierFile> getDossierFileByDID_FTNO(long dossierId, String fileTemplateNo, boolean removed) {
		return dossierFilePersistence.findByDID_FTNO(dossierId, fileTemplateNo, removed);
	}

	public DossierFile getDossierFileByDID_FTNO_First(long dossierId, String fileTemplateNo, boolean removed,
			OrderByComparator orderByComparator) {
		return dossierFilePersistence.fetchByDID_FTNO_First(dossierId, fileTemplateNo, removed, orderByComparator);
	}

	public List<DossierFile> getDossierFileByDID_FTNO_DPT(long dossierId, String fileTemplateNo, int dossierPartType,
			boolean removed) {
		return dossierFilePersistence.findByDID_FTNO_DPT(dossierId, fileTemplateNo, dossierPartType, removed);
	}

	public List<DossierFile> getDossierFileByDID_FTNO_DPT(long dossierId, String fileTemplateNo, int dossierPartType,
			boolean removed, int start, int end, OrderByComparator orderByComparator) {
		return dossierFilePersistence.findByDID_FTNO_DPT(dossierId, fileTemplateNo, dossierPartType, removed, start,
				end, orderByComparator);
	}

	public List<DossierFile> getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(long dossierId, String fileTemplateNo,
			int dossierPartType, long fileEntryId, boolean removed) {
		return dossierFilePersistence.findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo, dossierPartType,
				fileEntryId, removed);
	}

	public List<DossierFile> getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(long dossierId, String fileTemplateNo,
			int dossierPartType, long fileEntryId, boolean removed, int start, int end,
			OrderByComparator orderByComparator) {
		return dossierFilePersistence.findByDID_FTNO_DPT_NOT_NULL_FID(dossierId, fileTemplateNo, dossierPartType,
				fileEntryId, removed, start, end, orderByComparator);
	}

	/**
	 * Get dossierFile by deliverable Code using output DB
	 */
	public DossierFile getByDeliverableCode(String deliverableCode) {
		try {
			return dossierFilePersistence.findByDE_CODE(deliverableCode);
		} catch (Exception e) {
			return null;
		}
	}

	//TODO: get follow PPC
	public DossierFile getByFileTemplateNo(long id, String fileTemplateNo) {
		try {
			return deliverableFinder.findFileTemplateNo(id, fileTemplateNo);
		} catch (Exception e) {
			return null;
		}
	}

	//Get dossierFile follow fileEntryId
	public DossierFile getByFileEntryId(long fileEntryId) {
		return dossierFilePersistence.fetchByFILE_ID(fileEntryId);
	}

	public static final String CLASS_NAME = DossierFile.class.getName();
}