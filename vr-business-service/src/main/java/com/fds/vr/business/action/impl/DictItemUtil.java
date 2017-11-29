package com.fds.vr.business.action.impl;

import java.util.List;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class DictItemUtil {
	
	public long getCollectionId(String code, long groupId) {
		long collectionId = 0l;

		DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);

		if (Validator.isNotNull(collection)) {
			collectionId = collection.getPrimaryKey();
		}

		return collectionId;
	}
	
	public JSONArray getDictItem(List<DictItem> items, String code) {
		JSONArray array = JSONFactoryUtil
				.createJSONArray();
		
		for (DictItem dictItem : items) {
			
			JSONObject input = JSONFactoryUtil.createJSONObject();

			if (dictItem.getItemCode().toLowerCase().contains(
				code.toLowerCase())) {

				input.put("key", dictItem.getItemCode());

				input.put("value", dictItem.getItemName());

				array.put(input);
			}

		}

		return array;
	}
}
