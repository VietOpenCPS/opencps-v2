package org.opencps.dossiermgt.action.util;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MappingDataUtils {

	private static Log _log = LogFactoryUtil.getLog(MappingDataUtils.class);

	public static JSONObject getFormData (String formDataDossier, String mappingData) {
		//
		JSONObject formDataMapping = JSONFactoryUtil.createJSONObject();
		JSONObject formDataDossierJSON = JSONFactoryUtil.createJSONObject();
		JSONObject mappingDataJSON = JSONFactoryUtil.createJSONObject();
		try {
			formDataDossierJSON = JSONFactoryUtil.createJSONObject(formDataDossier);
			mappingDataJSON = JSONFactoryUtil.createJSONObject(mappingData);
		} catch (JSONException e) {
			_log.error(e);
		}

		//Get key mappingData
		Iterator<String> keyMapping = mappingDataJSON.keys();
		List<String> keyMapList = new ArrayList<String>();
		String keys;
		while(keyMapping.hasNext()) {
				keys = keyMapping.next();
				keyMapList.add(keys);
		}

		//Get key FormData
		Iterator<String> keyFormData = formDataDossierJSON.keys();
		List<String> keyFormList = new ArrayList<String>();
		while(keyFormData.hasNext()) {
				keys = keyFormData.next();
				keyFormList.add(keys);
		}
		String valueMap;
		for (String keyMap : keyMapList) {
			valueMap = String.valueOf(mappingDataJSON.get(keyMap));
			String valueMapLower = valueMap.toLowerCase();
			for (String keyForm : keyFormList) {
				String keyFormLower = keyForm.toLowerCase();
				if ( valueMapLower.equalsIgnoreCase(keyFormLower)) {
					formDataMapping.put(keyFormLower, formDataDossierJSON.get(keyForm));
					break;
				}
			}
		}
		return formDataMapping;
	}
}
