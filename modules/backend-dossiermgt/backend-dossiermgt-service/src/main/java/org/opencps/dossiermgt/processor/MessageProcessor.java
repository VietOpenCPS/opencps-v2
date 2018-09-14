package org.opencps.dossiermgt.processor;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.model.DossierSync;

public class MessageProcessor {
//	private static Log _log = LogFactoryUtil.getLog(MessageProcessor.class);
	private static volatile IMessageProcessor _processor;
	
	public static IMessageProcessor getProcessor(DossierSync ds) {
		if (ds == null)
			return null;
		ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(ds.getGroupId(), ds.getServerNo());
		if (sc == null)
			return null;
		
		if (DossierSyncTerm.SERVER_CONFIG_PROTOCOL_API.equals(sc.getProtocol())) {
			_processor = new APIMessageProcessor(ds);
		}
		else if (DossierSyncTerm.SERVER_CONFIG_PROTOCOL_MESSAGE.equals(sc.getProtocol())) {
			
		}
		else {
			return null;
		}
		
		return _processor;
	}
}
