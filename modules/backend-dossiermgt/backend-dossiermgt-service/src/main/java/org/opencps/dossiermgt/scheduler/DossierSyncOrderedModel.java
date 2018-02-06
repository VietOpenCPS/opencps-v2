package org.opencps.dossiermgt.scheduler;

public class DossierSyncOrderedModel {
	public long getDossierSyncId() {
		return dossierSyncId;
	}
	public void setDossierSyncId(long dossierSyncId) {
		this.dossierSyncId = dossierSyncId;
	}
	public long getDossierId() {
		return dossierId;
	}
	public void setDossierId(long dossierId) {
		this.dossierId = dossierId;
	}
	public int getMethodId() {
		return methodId;
	}
	public void setMethodId(int methodId) {
		this.methodId = methodId;
	}

	public long dossierSyncId;
	public long dossierId;
	public int methodId;

	public DossierSyncOrderedModel(long dossierSyncId, long dossierId, int methodId) {
		this.dossierSyncId = dossierSyncId;
		this.dossierId = dossierId;
		this.methodId = methodId;
	}
	
}
