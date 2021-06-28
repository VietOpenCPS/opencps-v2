package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.model.Dossier;

public interface TTTTIntegrationAction {
	boolean syncDoActionDossier(Dossier dossier) throws Exception;

	boolean syncDoActionDossierUsingHttpConnection(Dossier dossier) throws Exception;
}
