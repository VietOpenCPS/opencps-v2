package org.opencps.dossiermgt.action;

import java.util.List;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;

public interface DossierUserActions {
	public DossierUser addDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited);
	public DossierUser updateDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited);
	public DossierUser deleteDossierUser(long dossierId, long userId);
	public void initDossierUser(long groupId, Dossier dossier);
	public void initDossierUser(long groupId, Dossier dossier, ServiceProcess serviceProcess, List<ServiceProcessRole> listSprs);
}
