package com.fds.vr.business.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;

import com.fds.vr.business.action.VRActions;
import com.fds.vr.business.constant.VRKeys;
import com.fds.vr.business.model.VRConfigTechSpec;
import com.fds.vr.business.model.VRLimitConfigTechSpec;
import com.fds.vr.business.service.VRConfigTechSpecLocalServiceUtil;
import com.fds.vr.business.service.VRLimitConfigTechSpecLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class VRActionsImpl implements VRActions {

	@Override
	public JSONObject getTechSpecByVehicleClass(long groupId, String module, long dossierId, long dossierFileId,
			String vehicleClass) {

		JSONObject returnObj = JSONFactoryUtil.createJSONObject();
		JSONArray techSpecArr = JSONFactoryUtil.createJSONArray();

		try {

			// get id THONG_SO_KY_THUAT
			DictCollection nhomThongSoKyThuat = DictCollectionLocalServiceUtil
					.fetchByF_dictCollectionCode(VRKeys.NHOM_THONG_SO_KY_THUAT, groupId);

			long ttktId = nhomThongSoKyThuat.getPrimaryKey();

			DictGroup phanLoaiNhomTTSKT = DictGroupLocalServiceUtil.getByGC_GI_DCI(vehicleClass, groupId, ttktId);

			List<DictItemGroup> danhSachNhomThongSoKTChiTiet = DictItemGroupLocalServiceUtil.findByDictGroupId(groupId,
					phanLoaiNhomTTSKT.getPrimaryKey());

			if (Validator.isNull(module)) {
				module = "1";
			}

			for (DictItemGroup dg : danhSachNhomThongSoKTChiTiet) {

				DictItem dictItem = DictItemLocalServiceUtil.getDictItem(dg.getDictItemId());

				List<VRConfigTechSpec> configTechSpecs = VRConfigTechSpecLocalServiceUtil.getByVCSC(vehicleClass,
						dictItem.getItemCode(), module);
				JSONObject jsonTechSpec = JSONFactoryUtil.createJSONObject();

				jsonTechSpec.put("key", dictItem.getItemCode());
				jsonTechSpec.put("type", "label");
				jsonTechSpec.put("title", dictItem.getItemName());
				jsonTechSpec.put("required", false);
				jsonTechSpec.put("Reference", false);
				jsonTechSpec.put("placeholder", dictItem.getItemDescription());
				jsonTechSpec.put("datasource", StringPool.BLANK);
				jsonTechSpec.put("value", StringPool.BLANK);

				JSONArray items = JSONFactoryUtil.createJSONArray();

				for (VRConfigTechSpec vrConfig : configTechSpecs) {
					JSONObject techspec = JSONFactoryUtil.createJSONObject();

					techspec.put("key", vrConfig.getSpecificationCode());

					techspec.put("type", vrConfig.getSpecificationEntryType());

					techspec.put("title", vrConfig.getSpecificationDisplayName());

					techspec.put("required", vrConfig.getSpecificationMandatory());

					techspec.put("Reference", false);

					techspec.put("value", StringPool.BLANK);

					techspec.put("standard", vrConfig.getSpecificationStandard());
					techspec.put("basicunit", vrConfig.getSpecificationBasicUnit());
					techspec.put("placeholder", vrConfig.getSpecificationEntryPlaceholder());
					if (Validator.isNotNull(vrConfig.getSpecificationDataCollectionId())) {
						techspec.put("datasource",
								getDataSource(vrConfig.getSpecificationDataCollectionId(), groupId, vehicleClass));
					}

					items.put(techspec);
				}

				jsonTechSpec.put("items", items);

				techSpecArr.put(jsonTechSpec);
			}

			if (dossierFileId != 0) {
				// DB chua luu formSchema, nen do data vao techSpecArr
				techSpecArr = getFormData(dossierFileId, techSpecArr);
			}

			returnObj.put("status", HttpsURLConnection.HTTP_OK);
			returnObj.put("content", techSpecArr);

		} catch (Exception e) {
			_log.error(e);
			// returnObj.put("status", HttpsURLConnection.HTTP_OK);
			// returnObj.put("content", techSpecArr);
		}

		return returnObj;
	}

	@Override
	public JSONObject getTechSpecByVehicleClassExt(long groupId, String module, long dossierId, long dossierFileId,
			String fileTemplateNo, String vehicleClass) {

		JSONObject returnObj = JSONFactoryUtil.createJSONObject();
		JSONArray techSpecArr = JSONFactoryUtil.createJSONArray();

		try {

			// get id THONG_SO_KY_THUAT
			DictCollection nhomThongSoKyThuat = DictCollectionLocalServiceUtil
					.fetchByF_dictCollectionCode(VRKeys.NHOM_THONG_SO_KY_THUAT, groupId);

			long ttktId = nhomThongSoKyThuat.getPrimaryKey();

			DictGroup phanLoaiNhomTTSKT = DictGroupLocalServiceUtil.getByGC_GI_DCI(vehicleClass, groupId, ttktId);

			List<DictItemGroup> danhSachNhomThongSoKTChiTiet = DictItemGroupLocalServiceUtil.findByDictGroupId(groupId,
					phanLoaiNhomTTSKT.getPrimaryKey());

			if (Validator.isNull(module)) {
				module = "1";
			}

			for (DictItemGroup dg : danhSachNhomThongSoKTChiTiet) {

				DictItem dictItem = DictItemLocalServiceUtil.getDictItem(dg.getDictItemId());

				List<VRConfigTechSpec> configTechSpecs = VRConfigTechSpecLocalServiceUtil.getByVCSC(vehicleClass,
						dictItem.getItemCode(), module);

				JSONObject jsonTechSpec = JSONFactoryUtil.createJSONObject();

				jsonTechSpec.put("key", dictItem.getItemCode());
				jsonTechSpec.put("type", "label");
				jsonTechSpec.put("title", dictItem.getItemName());
				jsonTechSpec.put("required", false);
				jsonTechSpec.put("Reference", false);
				jsonTechSpec.put("placeholder", dictItem.getItemDescription());
				jsonTechSpec.put("datasource", StringPool.BLANK);
				jsonTechSpec.put("value", StringPool.BLANK);

				JSONArray items = JSONFactoryUtil.createJSONArray();

				for (VRConfigTechSpec vrConfig : configTechSpecs) {

					JSONObject techspec = JSONFactoryUtil.createJSONObject();

					String tmpKey = vrConfig.getSpecificationCode();

					String tmpType = vrConfig.getSpecificationEntryType();

					techspec.put("key", tmpKey);

					techspec.put("type", vrConfig.getSpecificationEntryType());

					techspec.put("title", vrConfig.getSpecificationDisplayName());

					techspec.put("required", vrConfig.getSpecificationMandatory());

					techspec.put("Reference", false);

					techspec.put("standard", vrConfig.getSpecificationStandard());
					techspec.put("basicunit", vrConfig.getSpecificationBasicUnit());
					techspec.put("placeholder", vrConfig.getSpecificationEntryPlaceholder());
					if (Validator.isNotNull(vrConfig.getSpecificationDataCollectionId())) {
						techspec.put("datasource",
								getDataSource(vrConfig.getSpecificationDataCollectionId(), groupId, vehicleClass));
					}
					String value = StringPool.BLANK;
					String value_text = StringPool.BLANK;
					String result = StringPool.BLANK;
					String resultTD = StringPool.BLANK;

					DossierFile dossierFile = null;

					String formData = StringPool.BLANK;

					if (dossierFileId > 0) {

						dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(dossierFileId);

					}
					
					//_log.info("========================== dossierFileId = " + dossierFileId);
					
					if (dossierFile != null) {
						// Update online form
						formData = dossierFile.getFormData();
						//_log.info("==========================1 " + dossierFile.getFileEntryId());
					} else {
						// View online form
						//_log.info("==========================2");
						if (Validator.isNotNull(fileTemplateNo)) {
							try {
								dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
										fileTemplateNo, false, OrderByComparatorFactoryUtil
												.create("opencps_dossierFile", "createDate", false));

								formData = dossierFile.getFormData();
								
								//_log.info("==========================3");

							} catch (Exception e) {
								
							}
						}
					}

					JSONObject formDataObject = null;

					if (Validator.isNotNull(formData)) {
						try {
							formDataObject = JSONFactoryUtil.createJSONObject(formData);
						} catch (Exception e) {
							//_log.error("Can not create json object from formData", e);
						}
					}

					if (formDataObject != null) {

						if (formDataObject.has(tmpKey)) {
							value = formDataObject.getString(tmpKey);

						}

						if (formDataObject.has(tmpKey + "_text") && (tmpType.equalsIgnoreCase("select")
								|| tmpType.equalsIgnoreCase("select1") || tmpType.equalsIgnoreCase("select2")
								|| tmpType.equalsIgnoreCase("select3"))) {
							value_text = formDataObject.getString(tmpKey + "_text");

						}

						if (dossierFileId > 0) {
							// Update online form
							if (formDataObject.has("bb_" + tmpKey)) {
								resultTD = formDataObject.getString("bb_" + tmpKey);

							}

							if (formDataObject.has("kl_" + tmpKey)) {
								result = formDataObject.getString("kl_" + tmpKey);
							}

						} else {
							// View online form
							result = StringPool.BLANK;
							resultTD = value;
						}

					}

					techspec.put("value_text", value_text);
					techspec.put("resultTD", resultTD);
					techspec.put("result", result);
					techspec.put("value", value);

					items.put(techspec);
				}

				jsonTechSpec.put("items", items);

				techSpecArr.put(jsonTechSpec);
			}

			if (dossierFileId != 0) {
				// DB chua luu formSchema, nen do data vao techSpecArr
				techSpecArr = getFormData(dossierFileId, techSpecArr);
			}

			returnObj.put("status", HttpsURLConnection.HTTP_OK);
			returnObj.put("content", techSpecArr);

		} catch (Exception e) {
			//_log.error(e);
			// returnObj.put("status", HttpsURLConnection.HTTP_OK);
			// returnObj.put("content", techSpecArr);
		}

		return returnObj;
	}

	private JSONArray getFormData(long dossierFileId, JSONArray formSchema) {

		JSONArray output = JSONFactoryUtil.createJSONArray();

		org.opencps.dossiermgt.model.DossierFile dossierFile = getDossierFile(dossierFileId);

		JSONObject formData = null;

		// JSONArray formSchema = null;

		if (Validator.isNotNull(dossierFile)) {

			try {
				formData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());

				// DB chua luu FormSchema

				// formSchema =
				// JSONFactoryUtil.createJSONArray(dossierFile.getFormSchema());

				int length = formSchema.length();

				for (int i = 0; i < length; i++) {

					JSONObject parent = formSchema.getJSONObject(i);

					String keyObject = parent.getString("key");

					String keyValue = formData.getString(keyObject);

					parent.put("value", keyValue);

					JSONArray items = JSONFactoryUtil.createJSONArray(parent.getString("items"));

					int lengthChild = items.length();

					JSONArray altJSONArray = JSONFactoryUtil.createJSONArray();

					for (int j = 0; j < lengthChild; j++) {
						JSONObject child = items.getJSONObject(j);

						String childKeyObj = child.getString("key");

						String childKeyValue = formData.getString(childKeyObj);

						child.put("value", childKeyValue);

						altJSONArray.put(child);
					}

					parent.put("items", altJSONArray);

					output.put(parent);
				}
			} catch (Exception e) {
				// _log.error(e);
			}

		}

		if (output.length() == 0) {
			output = formSchema;
		}

		return output;
	}

	private org.opencps.dossiermgt.model.DossierFile getDossierFile(long dossierFileId) {

		org.opencps.dossiermgt.model.DossierFile dossierFile = null;

		if (dossierFileId != 0) {
			try {
				dossierFile = DossierFileLocalServiceUtil.getDossierFile(dossierFileId);
			} catch (Exception e) {
				_log.error(e);
			}

		}

		return dossierFile;
	}

	@Override
	public JSONObject getTechSpecLimit(long groupId, String module, long dossierId, long dossierFileId,
			String vehicleClass, String vehicleType, long fomulaType) {

		JSONObject returnObj = JSONFactoryUtil.createJSONObject();

		try {
			DictCollection nhomLoaiPhuongTien = DictCollectionLocalServiceUtil
					.fetchByF_dictCollectionCode(VRKeys.LOAI_PHUONG_TIEN, groupId);

			long loaiPhuongTienId = nhomLoaiPhuongTien.getPrimaryKey();

			DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(vehicleType, loaiPhuongTienId, groupId);

			List<DictItemGroup> lsDictItemGroup = DictItemGroupLocalServiceUtil.findByF_dictItemId(groupId,
					dictItem.getPrimaryKey());

			long dictGroupId = 0;

			if (lsDictItemGroup.size() != 0) {
				dictGroupId = lsDictItemGroup.get(0).getDictGroupId();
			}

			String dictGroupName = StringPool.BLANK;

			if (dictGroupId != 0) {
				dictGroupName = DictGroupLocalServiceUtil.getDictGroup(dictGroupId).getGroupName();
			}

			List<VRLimitConfigTechSpec> vrLimits = VRLimitConfigTechSpecLocalServiceUtil.getLimitConfigs(vehicleClass,
					dictGroupName, fomulaType);

			JSONObject jsContent = JSONFactoryUtil.createJSONObject();

			jsContent.put("TechSpecs", getLimitsConfig(vrLimits));

			returnObj.put("status", HttpsURLConnection.HTTP_OK);
			returnObj.put("content", jsContent);

		} catch (Exception e) {
			returnObj.put("status", HttpsURLConnection.HTTP_FORBIDDEN);

		}

		return returnObj;
	}

	@Override
	public JSONObject getTechSpecByVehicleClassType(long groupId, String module, long dossierId, long dossierFileId,
			String vehicleClass, String vehicleType) {
		JSONObject returnObj = JSONFactoryUtil.createJSONObject();
		JSONArray techSpecArr = JSONFactoryUtil.createJSONArray();

		if (Validator.isNull(module)) {
			module = "1";
		}

		try {
			// get id THONG_SO_KY_THUAT
			DictCollection nhomThongSoKyThuat = DictCollectionLocalServiceUtil
					.fetchByF_dictCollectionCode(VRKeys.NHOM_THONG_SO_KY_THUAT, groupId);

			long ttktId = nhomThongSoKyThuat.getPrimaryKey();

			DictGroup phanLoaiNhomTTSKT = DictGroupLocalServiceUtil.getByGC_GI_DCI(vehicleClass, groupId, ttktId);

			List<DictItemGroup> danhSachNhomThongSoKTChiTiet = DictItemGroupLocalServiceUtil.findByDictGroupId(groupId,
					phanLoaiNhomTTSKT.getPrimaryKey());

			for (DictItemGroup dg : danhSachNhomThongSoKTChiTiet) {

				DictItem dictItem = DictItemLocalServiceUtil.getDictItem(dg.getDictItemId());

				List<VRConfigTechSpec> configTechSpecs = VRConfigTechSpecLocalServiceUtil.getByVCCTSCMD(vehicleClass,
						vehicleClass, dictItem.getItemCode(), module);

				List<VRConfigTechSpec> configTechSpecsType = VRConfigTechSpecLocalServiceUtil
						.getByVCCTSCMD(vehicleClass, vehicleType, dictItem.getItemCode(), module);

				List<VRConfigTechSpec> configTechSpecsAll = new ArrayList<VRConfigTechSpec>();

				configTechSpecsAll.addAll(configTechSpecs);
				configTechSpecsAll.addAll(configTechSpecsType);

				JSONObject jsonTechSpec = JSONFactoryUtil.createJSONObject();

				jsonTechSpec.put("key", dictItem.getItemCode());
				jsonTechSpec.put("type", "label");
				jsonTechSpec.put("title", dictItem.getItemName());
				jsonTechSpec.put("required", false);
				jsonTechSpec.put("Reference", false);
				jsonTechSpec.put("placeholder", dictItem.getItemDescription());
				jsonTechSpec.put("datasource", StringPool.BLANK);
				jsonTechSpec.put("value", StringPool.BLANK);

				JSONArray items = JSONFactoryUtil.createJSONArray();

				for (VRConfigTechSpec vrConfig : configTechSpecsAll) {
					JSONObject techspec = JSONFactoryUtil.createJSONObject();

					techspec.put("key", vrConfig.getSpecificationCode());

					techspec.put("type", vrConfig.getSpecificationEntryType());

					techspec.put("title", vrConfig.getSpecificationDisplayName());

					techspec.put("required", vrConfig.getSpecificationMandatory());

					techspec.put("Reference", false);

					techspec.put("value", StringPool.BLANK);

					techspec.put("standard", vrConfig.getSpecificationStandard());

					techspec.put("basicunit", vrConfig.getSpecificationBasicUnit());

					techspec.put("placeholder", vrConfig.getSpecificationEntryPlaceholder());
					if (Validator.isNotNull(vrConfig.getSpecificationDataCollectionId())) {
						techspec.put("datasource",
								getDataSource(vrConfig.getSpecificationDataCollectionId(), groupId, vehicleClass));
					}

					items.put(techspec);
				}

				jsonTechSpec.put("items", items);

				techSpecArr.put(jsonTechSpec);

			}

			returnObj.put("status", HttpsURLConnection.HTTP_OK);
			returnObj.put("content", techSpecArr);

		} catch (Exception e) {
			returnObj.put("status", HttpsURLConnection.HTTP_FORBIDDEN);
		}

		return returnObj;
	}

	@Override
	public JSONObject getDictItem(long groupId, String dictCollectionCode, String dictCollectionType) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONArray getLimitsConfig(List<VRLimitConfigTechSpec> vrLimitConfigs) {

		JSONArray arrOut = JSONFactoryUtil.createJSONArray();

		for (VRLimitConfigTechSpec vrLimit : vrLimitConfigs) {

			arrOut.put(vrLimit.getSpecificationCode());
		}

		return arrOut;
	}

	/**
	 * @param dataSourceCode
	 * @return
	 */
	private JSONArray getDataSource(String collectionCode, long groupId, String vehicleClass) {

		JSONArray datasource = JSONFactoryUtil.createJSONArray();

		// _log.info("collectionCode" + collectionCode + "groupId" + groupId +
		// "vehicleClass" + vehicleClass);

		// long dictCollectionId = getDictCollectionId(collectionCode, groupId);

		try {
			long dictGroupCollectionId = getDictCollectionId(collectionCode, groupId);

			DictGroup phanLoaiNhomTTSKT = DictGroupLocalServiceUtil.getByGC_GI_DCI(vehicleClass, groupId,
					dictGroupCollectionId);

			List<DictItemGroup> danhSachNhomThongSoKT = DictItemGroupLocalServiceUtil.findByDictGroupId(groupId,
					phanLoaiNhomTTSKT.getPrimaryKey());

			for (DictItemGroup dg : danhSachNhomThongSoKT) {
				JSONObject diObject = JSONFactoryUtil.createJSONObject();

				DictItem dictItem = DictItemLocalServiceUtil.getDictItem(dg.getDictItemId());

				diObject.put("value", dictItem.getItemCode());
				diObject.put("text", dictItem.getItemName());

				datasource.put(diObject);
			}

		} catch (Exception e) {
			// _log.error(e);
		}
		// _log.info("datasource" + datasource.length());

		return datasource;
	}

	private long getDictCollectionId(String collectionCode, long groupId) {
		long dictCollectionId = 0;

		try {
			DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

			dictCollectionId = dc.getDictCollectionId();
		} catch (Exception e) {
			// _log.error(e);
		}

		return dictCollectionId;
	}

	Log _log = LogFactoryUtil.getLog(VRActionsImpl.class);
}
