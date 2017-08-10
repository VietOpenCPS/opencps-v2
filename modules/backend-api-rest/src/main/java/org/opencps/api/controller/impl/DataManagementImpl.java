package org.opencps.api.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DataManagement;
import org.opencps.api.dictcollection.model.DictCollectionShortModel;
import org.opencps.api.dictcollection.model.DictGroupModel;
import org.opencps.api.dictcollection.model.DictItemModel;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DataManagementImpl implements DataManagement {

	Log _log = LogFactoryUtil.getLog(DataManagementImpl.class);

	@Override
	public Response getDictCollection(HttpServletRequest request, org.apache.cxf.headers.Header header) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDictCollectionDetail(HttpServletRequest request, org.apache.cxf.headers.Header header,
			String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addDictCollection(HttpServletRequest request, org.apache.cxf.headers.Header header, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateDictCollection(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			DictCollectionShortModel body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteDictCollection(HttpServletRequest request, org.apache.cxf.headers.Header header,
			String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDataForm(HttpServletRequest request, org.apache.cxf.headers.Header header, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addDataForm(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDictGroups(HttpServletRequest request, org.apache.cxf.headers.Header header, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addDictGroups(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			DictGroupModel body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateDictGroups(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String groupCode, DictGroupModel body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response daleteDictGroups(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String groupCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDictItem(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String groupCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addDictItem(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String groupCode, DictItemModel dictItemModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteDictItem(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String groupCode, String dictitemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDictItems(HttpServletRequest request, org.apache.cxf.headers.Header header, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addDictItems(HttpServletRequest request, org.apache.cxf.headers.Header header, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDictItemByItemCode(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateDictItemByItemCode(HttpServletRequest request, org.apache.cxf.headers.Header header,
			String code, String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response dateletDictItemByItemCode(HttpServletRequest request, org.apache.cxf.headers.Header header,
			String code, String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getMetaDataOfDictItem(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addMetaDataOfDictItem(HttpServletRequest request, org.apache.cxf.headers.Header header, String code,
			String itemCode, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateMetaDataOfDictItem(HttpServletRequest request, org.apache.cxf.headers.Header header,
			String code, String itemCode, String key, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteMetaDataOfDictItem(HttpServletRequest request, org.apache.cxf.headers.Header header,
			String code, String itemCode, String key, String body) {
		// TODO Auto-generated method stub
		return null;
	}
}
