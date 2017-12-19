package org.opencps.dossiermgt.action.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class AutoFillFormData {
	
	public static String sampleDataBinding(String sampleData, long dossierId, ServiceContext serviceContext) {
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
			//process sampleData
			if(Validator.isNull(sampleData)){
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
						DossierFile dossierFile = DossierFileLocalServiceUtil
								.getDossierFileByDID_FTNO(dossierId, paper);
						
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
	

	
	
	public static Map<String, Object> jsonToMap(JSONObject json) {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(Validator.isNotNull(json)) {
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
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = null;
            if(Validator.isNotNull(object.getJSONArray(key))) {
                value = (JSONArray) object.getJSONArray(key);
                map.put(key, value);
            }

            else if(Validator.isNotNull(object.getJSONObject(key))) {
                value = (JSONObject) object.getJSONObject(key);
                map.put(key, value);
            }
            
            else  {
                value = object.getString(key);
                map.put(key, value);
            }
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.getJSONObject(i);

            if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
