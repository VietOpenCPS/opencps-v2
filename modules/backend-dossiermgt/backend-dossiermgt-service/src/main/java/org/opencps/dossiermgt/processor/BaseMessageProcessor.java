package org.opencps.dossiermgt.processor;

import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.model.DossierSync;

public class BaseMessageProcessor implements IMessageProcessor {
	protected DossierSync dossierSync;
	public BaseMessageProcessor() {
		
	}
	
	public BaseMessageProcessor(DossierSync dossierSync) {
		this.dossierSync = dossierSync;
	}
	
	@Override
	public void processRequest() {
	}

	@Override
	public void processInform() {
	}

	@Override
	public void process() {
		if (dossierSync.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST) {
			processRequest();
		}
		else if (dossierSync.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM) {
			processInform();
		}
	}
	
}
