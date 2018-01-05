package org.opencps.dossiermgt.listenner;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

@Component(immediate = true, service = ModelListener.class)
public class DossierListenner extends BaseModelListener<Dossier> {

	@Override
	public void onAfterCreate(Dossier model) throws ModelListenerException {
		
		/*
		 * 04/01/2017 ThanhNV: 
		 * add doossier log use DossierListennerUtils
		 */
		DossierListennerUltils.createDossierLog(model, false, false);
	
	}

	@Override
	public void onAfterUpdate(Dossier model) throws ModelListenerException {
		_log.info("Dossiser UpDate.....");
		// The case submitting
		// If submitting = true then update DossierSync
		/*
		 * int method = 0; if (model.getSubmitting()) { if
		 * (model.getDossierStatus().contentEquals(DossierStatusConstants.NEW))
		 * { // Update DossierSync with createDossier = true, method = 0 (update
		 * DossierAction)
		 * DossierSyncLocalServiceUtil.updateDossierSync(model.getGroupId(),
		 * model.getUserId(), model.getDossierId(), model.getReferenceUid(),
		 * true, method, model.getDossierActionId(), StringPool.BLANK,
		 * model.getServerNo()); } else { // TODO Add case for update
		 * dossierFile, paymentFile
		 * 
		 * } }
		 */
		
		/*
		 * 04/01/2017 ThanhNV: 
		 * add doossier log use DossierListennerUtils
		 */
		DossierListennerUltils.createDossierLog(model, true, modelBeforeUpdate.getDossierStatus() == model.getDossierStatus());
	
	}

	@Override
	public void onBeforeUpdate(Dossier model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DossierLocalServiceUtil.getDossier(model.getPrimaryKey());
		} catch (Exception e) {
			_log.error(e);
		}
	}

	public static Dossier modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierListenner.class.getName());
}
