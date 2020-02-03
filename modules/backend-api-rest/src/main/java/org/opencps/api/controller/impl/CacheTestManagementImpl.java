package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.CacheTestManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.datamgt.model.DataSearchModel;
import org.opencps.cache.actions.CacheActions;
import org.opencps.cache.actions.impl.CacheActionsImpl;

public class CacheTestManagementImpl implements CacheTestManagement{

	//@Reference
	//private CacheLocalService cacheLocalService;
	private static final Log _log = LogFactoryUtil.getLog(CacheTestManagementImpl.class);

	@Override
	public Response getFromCache(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		
		try {
			//ObjectOutputStream aaa = new ObjectOutputStream(new FileOutputStream("haha"));
			//Serializable ttt = cacheLocalService.getFromCache("getDetailActionConfig", null, null);
			//cacheLocalService.clearCache("getDetailActionConfig");
			_log.info("START CLEAR CACHE");
			CacheActions cache = new CacheActionsImpl();
			_log.info("START CLEAR CACHE111");
			//cache.clearCache("getActionConfig");
			_log.info("START CLEAR CACHE2222");
			String groupIdCache = String.format(MessageUtil.getMessage(ConstantUtils.CACHE_GROUPID), 35823);
			
			Serializable ttt = cache.getFromCache(ConstantUtils.CACHE_GETACTIONCONFIG_KEY, groupIdCache);
			if (ttt != null) {
				_log.info("ttt: "+ttt.toString());
			}
		} catch (Exception e) {
			_log.error(e);
		}
		
		return null;
	}

//	public static Serializable serialize(String key, OutputStream os) throws IOException {
//		//ObjectOutputStream oos = new ObjectOutputStream(os);
//		//oos.writeObject(key);
//		//oos.flush();
//		ByteArrayOutputStream bo = new ByteArrayOutputStream();
//		ObjectOutputStream so = new ObjectOutputStream(bo);
//		so.writeObject(key);
//		so.flush();
//		return bo.toString();
//	}
}
