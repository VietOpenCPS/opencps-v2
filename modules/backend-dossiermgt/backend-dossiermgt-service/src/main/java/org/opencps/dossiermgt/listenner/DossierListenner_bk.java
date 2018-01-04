//package org.opencps.dossiermgt.listenner;
//
//import org.opencps.dossiermgt.action.util.DossierLogUtils;
//import org.opencps.dossiermgt.constants.DossierStatusConstants;
//import org.opencps.dossiermgt.model.Dossier;
//import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
//import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
//import org.opencps.usermgt.model.Applicant;
//import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
//import org.osgi.service.component.annotations.Component;
//
//import com.liferay.portal.kernel.exception.ModelListenerException;
//import com.liferay.portal.kernel.exception.PortalException;
//import com.liferay.portal.kernel.exception.SystemException;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.model.BaseModelListener;
//import com.liferay.portal.kernel.model.ModelListener;
//import com.liferay.portal.kernel.service.ServiceContext;
//import com.liferay.portal.kernel.util.Validator;
//
//@Component(immediate = true, service = ModelListener.class)
//public class DossierListenner extends BaseModelListener<Dossier> {
//
//	@Override
//	public void onAfterCreate(Dossier model) throws ModelListenerException {
//		// TODO Auto-generated method stub
//		String content = "On Dossiser Created";
//		String notificationType = "Dossier-00";
//		String payload = DossierLogUtils.createPayload(null, null, model);
//
//		ServiceContext serviceContext = new ServiceContext();
//		serviceContext.setCompanyId(model.getCompanyId());
//		serviceContext.setUserId(model.getUserId());
//
//		Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(model.getUserId());
//
//		try {
//			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
//					(applicant != null && Validator.isNotNull(applicant.getApplicantName()))
//							? applicant.getApplicantName() : model.getUserName(),
//					content, notificationType, payload, serviceContext);
//		} catch (SystemException | PortalException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onAfterUpdate(Dossier model) throws ModelListenerException {
//		_log.info("Dossiser UpDate.....");
//		// The case submitting
//		// If submitting = true then update DossierSync
//		/*
//		 * int method = 0; if (model.getSubmitting()) { if
//		 * (model.getDossierStatus().contentEquals(DossierStatusConstants.NEW))
//		 * { // Update DossierSync with createDossier = true, method = 0 (update
//		 * DossierAction)
//		 * DossierSyncLocalServiceUtil.updateDossierSync(model.getGroupId(),
//		 * model.getUserId(), model.getDossierId(), model.getReferenceUid(),
//		 * true, method, model.getDossierActionId(), StringPool.BLANK,
//		 * model.getServerNo()); } else { // TODO Add case for update
//		 * dossierFile, paymentFile
//		 * 
//		 * } }
//		 */
//		ServiceContext serviceContext = new ServiceContext();
//		serviceContext.setCompanyId(model.getCompanyId());
//		serviceContext.setUserId(model.getUserId());
//		Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(model.getUserId());
//		// The case waiting
//		if (model.getDossierStatus().contentEquals(DossierStatusConstants.WAITING)) {
//			_log.info("Request Dossiser Additional.....");
//			String content = "On Dossiser Additional";
//			String notificationType = "DOSSIER-01";
//			String payload = DossierLogUtils.createPayload(null, null, model);
//			try {
//				DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
//						(applicant != null && Validator.isNotNull(applicant.getApplicantName()))
//								? applicant.getApplicantName() : model.getUserName(),
//						content, notificationType, payload, serviceContext);
//			} catch (PortalException e) {
//				e.printStackTrace();
//			}
//		}
//
//		// The case canceling
//		if (model.getCancellingDate() != null && model.getSubmitting() == true) {
//			_log.info("Request Dossiser Canceling......");
//			String content = "On Dossiser Canceling";
//			String notificationType = "DOSSIER-05";
//			String payload = DossierLogUtils.createPayload(null, null, model);
//			try {
//				DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
//						(applicant != null && Validator.isNotNull(applicant.getApplicantName()))
//								? applicant.getApplicantName() : model.getUserName(),
//						content, notificationType, payload, serviceContext);
//			} catch (PortalException e) {
//				e.printStackTrace();
//			}
//		}
//
//		// The case correcting
//		if (model.getCorrecttingDate() != null) {
//			_log.info("Request Dossiser Correcting......");
//			String content = "On Dossiser Correcting";
//			String notificationType = "DOSSIER-06";
//			String payload = DossierLogUtils.createPayload(null, null, model);
//			try {
//				DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
//						(applicant != null && Validator.isNotNull(applicant.getApplicantName()))
//								? applicant.getApplicantName() : model.getUserName(),
//						content, notificationType, payload, serviceContext);
//			} catch (PortalException e) {
//				e.printStackTrace();
//			}
//		}
//
//		// The case Additional
//		if (modelBeforeUpdate.getDossierStatus().contentEquals(DossierStatusConstants.WAITING)) {
//			_log.info("Dossiser Additional.....");
//			String content = "On Dossiser Additional";
//			String notificationType = "DOSSIER-04";
//			String payload = DossierLogUtils.createPayload(null, null, model);
//			try {
//				DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
//						(applicant != null && Validator.isNotNull(applicant.getApplicantName()))
//								? applicant.getApplicantName() : model.getUserName(),
//						content, notificationType, payload, serviceContext);
//			} catch (PortalException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@Override
//	public void onBeforeUpdate(Dossier model) throws ModelListenerException {
//		try {
//			modelBeforeUpdate = DossierLocalServiceUtil.getDossier(model.getPrimaryKey());
//		} catch (Exception e) {
//			_log.error(e);
//		}
//	}
//
//	public static Dossier modelBeforeUpdate;
//
//	private Log _log = LogFactoryUtil.getLog(DossierListenner.class.getName());
//}
