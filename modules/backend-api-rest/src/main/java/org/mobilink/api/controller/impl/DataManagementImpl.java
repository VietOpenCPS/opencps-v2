package org.mobilink.api.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.mobilink.api.controller.DataManagement;
import org.mobilink.api.controller.util.DictCollectionUtils;
import org.mobilink.api.dictcollection.model.DictCollectionModel;
import org.mobilink.api.dictcollection.model.DictCollectionResults;
import org.mobilink.backend.datamgt.model.DictCollection;
import org.mobilink.backend.datamgt.service.DictCollectionLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.Header;

public class DataManagementImpl implements DataManagement {

	@Override
	public Response getDictCollection(HttpServletRequest request, Header header) {
		try {

			DictCollectionResults result = new DictCollectionResults();

			int total = DictCollectionLocalServiceUtil.getDictCollectionsCount();
			
			List<DictCollection> lsDictCollection = DictCollectionLocalServiceUtil.getDictCollections(QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);
			
			List<DictCollectionModel> ls = DictCollectionUtils.mapperDictCollectionList(lsDictCollection);
			
			/*DictCollectionModel dc = new DictCollectionModel();
			
			dc.setCollectionCode("323");
			
			ls.add(dc);*/
			
			result.setTotal(total);
			result.setDictCollectionModel(ls);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			JSONObject errorMgs = JSONFactoryUtil.createJSONObject();

			errorMgs.put("code", 400);
			errorMgs.put("message", "String input is incorrect");
			errorMgs.put("description", "String input is incorrect");

			_log.info(e);

			return Response.status(400).entity(errorMgs).build();
		}
	}

	Log _log = LogFactoryUtil.getLog(DataManagementImpl.class);
}
