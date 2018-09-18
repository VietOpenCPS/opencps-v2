
package backend.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ObjectConverterUtil {

	public static JSONObject objectToJSON(Class<?> clazz, Object object) {

		JSONObject json = JSONFactoryUtil.createJSONObject();

		
			PropertyDescriptor[] propertyDescriptors = null;
			
			try {
				propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			}
			catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
				
				return json;
			}
			
			if(propertyDescriptors != null && propertyDescriptors.length > 0){
				for (PropertyDescriptor pd : propertyDescriptors) {

				if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
					try {
						// if (pd.getPropertyType().getName().equals(
						// Date.class.getName())) {
						if (Date.class.isAssignableFrom(pd.getPropertyType())) {
							Date date = (Date) pd.getReadMethod().invoke(object);
							if (date != null) {
								json.put(pd.getDisplayName(),
										APIDateTimeUtils.convertDateToString(date, APIDateTimeUtils._TIMESTAMP));
							}
						}
						/*
						 * else if (pd.getPropertyType().getName().equals(
						 * Long.class.getName())) { Long value = (Long)
						 * (pd.getReadMethod().invoke(object)); } else if
						 * (pd.getPropertyType().getName().equals(
						 * long.class.getName())) { long value = (Long)
						 * (pd.getReadMethod().invoke(object)); }
						 */
						else {
							json.put(pd.getDisplayName(), pd.getReadMethod().invoke(object));
						}
					} catch (Exception e) {
						_log.debug(e);
						//_log.error(e);
						//_log.info("==Not check log==" + e);
						// continue;
					}

				}

			}
		}

		return json;
	}

	private static Log _log = LogFactoryUtil.getLog(ObjectConverterUtil.class.getName());
}
