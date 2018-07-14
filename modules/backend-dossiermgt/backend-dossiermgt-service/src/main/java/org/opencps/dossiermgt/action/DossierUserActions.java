package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierUser;

public interface DossierUserActions {
	public DossierUser addDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited);
	public DossierUser updateDossierUser(long groupId, long dossierId, long userId, int moderator, boolean visited);
	public DossierUser deleteDossierUser(long dossierId, long userId);
	public void initDossierUser(long groupId, Dossier dossier);
}
