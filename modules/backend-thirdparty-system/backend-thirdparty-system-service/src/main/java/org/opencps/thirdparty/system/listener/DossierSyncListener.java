package org.opencps.thirdparty.system.listener;

import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalService;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.thirdparty.system.constants.SyncServerTerm;
import org.opencps.thirdparty.system.service.ThirdPartyDossierSyncLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = ModelListener.class)
public class DossierSyncListener extends BaseModelListener<DossierSync> {
	@Override
	public void onAfterCreate(DossierSync model) throws ModelListenerException {
		List<ServerConfig> lstServers = _serverConfigLocalService.getServerConfigs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		for (ServerConfig sc : lstServers) {
			String configs = sc.getConfigs();
			if (Validator.isNotNull(configs)) {
				try {
					JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
					if (configObj.has(SyncServerTerm.SERVER_TYPE) 
							&& configObj.getString(SyncServerTerm.SERVER_TYPE).equals(SyncServerTerm.SOAP_SYNC_SERVER_TYPE)) {
						_thirdPartySyncService.updateThirdPartyDossierSync(model.getGroupId(), model.getUserId(), model.getDossierId(), model.getDossierReferenceUid(), model.getCreateDossier(), model.getMethod(), model.getClassPK(), model.getFileReferenceUid(), sc.getServerNo());
					}
				}
				catch (Exception e) {
				}
			}
		}
	}
	
	@Reference
	private ThirdPartyDossierSyncLocalService _thirdPartySyncService;
	
	@Reference
	private ServerConfigLocalService _serverConfigLocalService;
	
}
