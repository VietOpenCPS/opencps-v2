package org.opencps.dossiermgt.listenner;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.opencps.dossiermgt.action.util.DossierContentGenerator;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DossierBriefNoteListenner extends BaseModelListener<DossierFile> {

	@Override
	public void onBeforeUpdate(DossierFile model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DossierFileLocalServiceUtil.fetchDossierFile(model.getPrimaryKey());
		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onAfterUpdate(DossierFile model) throws ModelListenerException {
		_log.debug("Update DossierBriefNote=====-");
		
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		
		try {
			long dossierId = model.getDossierId();
			long groupId = model.getGroupId();
//			_log.info("groupId: " + groupId);
//			_log.info("dossierId: " + dossierId);
			ProcessOption option = null;
			Dossier dossier = null;

			if (Validator.isNotNull(dossierId)) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			}
//			_log.info("dossier.getServiceCode(): " + dossier.getServiceCode());
//			_log.info("dossier.getGovAgencyCode(): " + dossier.getGovAgencyCode());
//			_log.info("dossier.getDossierTemplateNo(): " + dossier.getDossierTemplateNo());
			if (dossier != null) {
				option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
						dossier.getDossierTemplateNo(), groupId);
	
				long serviceProcessId = option.getServiceProcessId();
	//			_log.info("serviceProcessId: " + serviceProcessId);
				
				String briefNote = StringPool.BLANK;
				if (Validator.isNotNull(serviceProcessId)) {
					List<ProcessStep> processStepList = ProcessStepLocalServiceUtil
							.getProcessStepbyServiceProcessId(serviceProcessId);
					if (processStepList != null && processStepList.size() > 0) {
						for (ProcessStep processStep : processStepList) {
							String briefNoteStep = processStep.getBriefNote();
	//						_log.info("briefNoteStep: " + briefNoteStep);
							if (Validator.isNotNull(briefNoteStep)) {
								briefNote = DossierContentGenerator.getBriefNote(groupId, dossierId, briefNoteStep);
	//							_log.info("briefNote: " + briefNote);
								break;
							}
						}
					}
				}
		
				if (Validator.isNotNull(briefNote)) {
					DossierLocalServiceUtil.updateDossierBriefNote(dossierId, briefNote);
				}
			
			}

		} catch (PortalException e) {
//			e.printStackTrace();
			_log.error(e);
		}
	}

	public DossierFile modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierBriefNoteListenner.class.getName());
	
	protected ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);

		return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
				config.getServiceConfigId());
	}
}
