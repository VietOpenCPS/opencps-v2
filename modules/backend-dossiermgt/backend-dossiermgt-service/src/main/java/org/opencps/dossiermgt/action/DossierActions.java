package org.opencps.dossiermgt.action;

import java.util.List;


import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessAction;

public interface DossierActions {
	
	public Dossier addDossier();
	
	public Dossier getDossierDetailById();
	
	public Dossier getDossierDetailByRef();
	
	public Dossier updateDossier();
	
	public Dossier removeDossier();
	
	public Dossier cancelDossier();
	
	public Dossier correctDossier();
	
	public Dossier submitDossier();
	
	public Dossier resetDossier();
	
	public List<ProcessAction> getNextActions();
	
	public List<DossierAction> getDossierActions();
	
	public void doAction();
	
	public Dossier markerVisited();
	
	public void doRollback();
	
	public void getContacts();
}
