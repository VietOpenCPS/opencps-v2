package org.opencps.dossiermgt.processor;

import org.opencps.dossiermgt.constants.ActionConfigTerm;
import org.opencps.dossiermgt.model.DossierSync;

public class APIMessageProcessor extends BaseMessageProcessor {
	public APIMessageProcessor(DossierSync ds) {
		super(ds);
	}
	
	@Override
	public void processRequest() {
		if (dossierSync.getActionCode().equals(ActionConfigTerm.ACTION_CODE_1300)) {
			
		}
	}	
}
