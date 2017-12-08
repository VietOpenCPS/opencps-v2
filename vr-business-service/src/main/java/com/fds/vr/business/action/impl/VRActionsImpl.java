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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class VRActionsImpl implements VRActions {

	@Override
	public JSONObject getTechSpecByVehicleClass(long groupId, String module, long dossierId, long dossierFileId,
			String vehicleClass) {

		JSONObject returnObj = JSONFactoryUtil.createJSONObject();
		JSONArray techSpecArr = JSONFactoryUtil.createJSONArray();

		try {
			if (dossierFileId != 0) {
				techSpecArr = getFormData(dossierFileId);
			} else {
				// get id THONG_SO_KY_THUAT
				DictCollection nhomThongSoKyThuat = DictCollectionLocalServiceUtil
						.fetchByF_dictCollectionCode(VRKeys.NHOM_THONG_SO_KY_THUAT, groupId);

				long ttktId = nhomThongSoKyThuat.getPrimaryKey();

				DictGroup phanLoaiNhomTTSKT = DictGroupLocalServiceUtil.getByGC_GI_DCI(vehicleClass, groupId, ttktId);

				List<DictItemGroup> danhSachNhomThongSoKTChiTiet = DictItemGroupLocalServiceUtil
						.findByDictGroupId(groupId, phanLoaiNhomTTSKT.getPrimaryKey());

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
									getDataSource(vrConfig.getSpecificationDataCollectionId(), groupId));
						}

						items.put(techspec);
					}

					jsonTechSpec.put("items", items);

					techSpecArr.put(jsonTechSpec);
				}

			}

			returnObj.put("status", HttpsURLConnection.HTTP_OK);
			returnObj.put("content", techSpecArr);

		} catch (Exception e) {
			returnObj.put("status", HttpsURLConnection.HTTP_OK);
			returnObj.put("content", techSpecArr);
		}

		return returnObj;
	}

	private JSONArray getFormData(long dossierFileId) {

		JSONArray output = JSONFactoryUtil.createJSONArray();

		org.opencps.dossiermgt.model.DossierFile dossierFile = getDossierFile(dossierFileId);

		JSONObject formData = null;

		JSONArray formSchema = null;

		if (Validator.isNotNull(dossierFile)) {

			try {
				formData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());

				formSchema = JSONFactoryUtil.createJSONArray(dossierFile.getFormSchema());

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

			}

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

			returnObj.put("status", HttpsURLConnection.HTTP_OK);
			returnObj.put("content", getLimitsConfig(vrLimits));

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
						techspec.put("datasource", getDataSource(vrConfig.getSpecificationDataCollectionId(), groupId));
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
	private JSONArray getDataSource(String collectionCode, long groupId) {

		JSONArray datasource = JSONFactoryUtil.createJSONArray();

		long dictCollectionId = getDictCollectionId(collectionCode, groupId);

		try {
			if (dictCollectionId != 0) {
				List<DictItem> lsDictItems = DictItemLocalServiceUtil.findByF_dictCollectionId(dictCollectionId);

				for (DictItem di : lsDictItems) {
					JSONObject diObject = JSONFactoryUtil.createJSONObject();

					diObject.put("value", di.getItemCode());
					diObject.put("text", di.getItemName());

					datasource.put(diObject);
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

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
