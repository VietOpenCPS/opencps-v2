package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dictcollection.model.DictCollectionModel;
import org.opencps.datamgt.model.DictCollection;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class DictCollectionUtils {
	public static List<DictCollectionModel> mapperDictCollectionList(List<DictCollection> dictCollectionList) {
		List<DictCollectionModel> dcmList = new ArrayList<>();

		try {
			for (DictCollection dc : dictCollectionList) {

				DictCollectionModel dcm = new DictCollectionModel();
				
				dcm.setCollectionCode(dc.getCollectionCode());
				dcm.setCollectionName(dc.getCollectionName());
				
				dcm.setCollectionNameEN(dc.getCollectionNameEN());
				
				dcm.setDescription(dc.getDescription());
				
				dcmList.add(dcm);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return dcmList;
	}

	static Log _log = LogFactoryUtil.getLog(DictCollectionUtils.class);
}
