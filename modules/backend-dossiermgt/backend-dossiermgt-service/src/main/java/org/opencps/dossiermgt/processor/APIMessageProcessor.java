package org.opencps.dossiermgt.processor;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

public class APIMessageProcessor extends BaseMessageProcessor {
	private OpenCPSRestClient client;
	public APIMessageProcessor(DossierSync ds) {
		super(ds);
		
		ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(ds.getServerNo());
		try {
			client = OpenCPSRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void processRequest() {
		if (dossierSync.getActionCode().equals(ActionConfigTerm.ACTION_CODE_1300)) {
		}
	}	
}
