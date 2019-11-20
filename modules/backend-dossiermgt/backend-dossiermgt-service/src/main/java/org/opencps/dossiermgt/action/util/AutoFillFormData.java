package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierActionComparator;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;

public class AutoFillFormData {

	public static Map<String, Object> jsonToMap(JSONObject json) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if (Validator.isNotNull(json)) {
			try {
				retMap = toMap(json);
			} catch (JSONException e) {
				_log.error(e);
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

	public static String sampleDataBinding(String sampleData, Dossier dossier, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			result = JSONFactoryUtil.createJSONObject(sampleData);

			Map<String, Object> jsonMap = jsonToMap(result);

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

				String value = String.valueOf(entry.getValue());

				if (value.startsWith(StringPool.POUND) && value.contains(StringPool.AT)) {
					String newString = value.substring(1);
					String[] stringSplit = newString.split(StringPool.AT);
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					try {
						DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossier.getDossierId(),
								paper, false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));

						if (Validator.isNotNull(dossierFile) && Validator.isNotNull(dossierFile.getFormData())
								&& dossierFile.getFormData().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							// _log.info("JSON other map: " +
							String myCHK = StringPool.BLANK;
							try {
								if (variable.contains(StringPool.COLON)) {
									String[] variableMuti = variable.split(StringPool.COLON);
									for (String string : variableMuti) {
										myCHK += StringPool.COMMA_AND_SPACE + jsonOtherMap.get(string).toString();
									}
									myCHK = myCHK.replaceFirst(StringPool.COMMA_AND_SPACE, StringPool.BLANK);
								} else {
									myCHK = jsonOtherMap.get(variable).toString();
								}
							} catch (Exception e) {
								_log.debug(e);
							}

							if (myCHK.startsWith(StringPool.POUND)) {
								jsonMap.put(entry.getKey(), StringPool.BLANK);
							} else {
								jsonMap.put(entry.getKey(), myCHK.toString());
							}
						} else {
							jsonMap.put(entry.getKey(), StringPool.BLANK);
						}
					} catch (SystemException e) {
						_log.error(e);
					}
				} else if (value.startsWith(StringPool.DOLLAR) && value.contains(StringPool.AT)) {
					//_log.info("START ACTIONCONFIG: "+value);
					String newString = value.substring(1);
					String[] stringSplit = newString.split(StringPool.AT);
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					//_log.info("START paper: "+paper);
					try {
						DossierAction dossierAction = DossierActionLocalServiceUtil.getByDID_CODE_First(dossier.getDossierId(),
								paper, new DossierActionComparator(false, Field.CREATE_DATE, Date.class));
						//_log.info("START dossierAction: "+dossierAction);

						if (Validator.isNotNull(dossierAction) && Validator.isNotNull(dossierAction.getPayload())
								&& dossierAction.getPayload().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierAction.getPayload());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							// _log.info("JSON other map: " +
							// Arrays.toString(jsonOtherMap.entrySet().toArray()));
							String myActConfig = jsonOtherMap.get(variable).toString();
							//
							jsonMap.put(entry.getKey(), myActConfig);
						} else {
							jsonMap.put(entry.getKey(), StringPool.BLANK);
						}
					} catch (SystemException e) {
						_log.error(e);
					}
				}
			}

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
				if (entry.getValue().getClass().getName().contains(ConstantUtils.KEY_JSON_ARRAY)) {
					result.put(entry.getKey(), (JSONArray) entry.getValue());
				} else if (entry.getValue().getClass().getName().contains(ConstantUtils.KEY_JSON_OBJECT)) {
					result.put(entry.getKey(), (JSONObject) entry.getValue());
				} else {
					result.put(entry.getKey(), entry.getValue() + StringPool.BLANK);
				}
			}
		} catch (JSONException e) {
			_log.error(e);
		}

		_log.debug("START result: "+result);
		return result.toJSONString();
	}	
	private static final Log _log = LogFactoryUtil.getLog(AutoFillFormData.class);
}
