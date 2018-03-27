package org.opencps.dossiermgt.service.indexer;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.keypay.util.HashFunction;
import org.opencps.dossiermgt.action.util.DossierOverDueUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierIndexer extends BaseIndexer<Dossier> {
	public static final String CLASS_NAME = Dossier.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Dossier object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(Dossier object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);

		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.CREATE_DATE, object.getCreateDate());
		document.addDateSortable(Field.MODIFIED_DATE, object.getModifiedDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		// add number fields
		document.addTextSortable(DossierTerm.APPLICANT_ID_DATE,
				APIDateTimeUtils.convertDateToString(object.getApplicantIdDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		document.addTextSortable(DossierTerm.SUBMIT_DATE,
				APIDateTimeUtils.convertDateToString(object.getSubmitDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		document.addTextSortable(DossierTerm.RECEIVE_DATE,
				APIDateTimeUtils.convertDateToString(object.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		document.addTextSortable(DossierTerm.DUE_DATE,
				APIDateTimeUtils.convertDateToString(object.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		document.addTextSortable(DossierTerm.RELEASE_DATE,
				APIDateTimeUtils.convertDateToString(object.getReleaseDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		document.addTextSortable(DossierTerm.FINISH_DATE,
				APIDateTimeUtils.convertDateToString(object.getFinishDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		document.addTextSortable(DossierTerm.CANCELLING_DATE,
				APIDateTimeUtils.convertDateToString(object.getCancellingDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		document.addTextSortable(DossierTerm.CORRECTING_DATE,
				APIDateTimeUtils.convertDateToString(object.getCorrecttingDate(), APIDateTimeUtils._NORMAL_PARTTERN));

		document.addNumberSortable(DossierTerm.RECEIVE_DATE_TIMESTAMP,
				Validator.isNotNull(object.getReceiveDate()) ? object.getReceiveDate().getTime() : 0);

		document.addNumberSortable(DossierTerm.DUE_DATE_TIMESTAMP,
				Validator.isNotNull(object.getDueDate()) ? object.getDueDate().getTime() : 0);

		document.addNumberSortable(DossierTerm.RELEASE_DATE_TIMESTAMP,
				Validator.isNotNull(object.getReleaseDate()) ? object.getReleaseDate().getTime() : 0);

		document.addNumberSortable(DossierTerm.CANCELLING_DATE_TIMESTAMP,
				Validator.isNotNull(object.getCancellingDate()) ? object.getCancellingDate().getTime() : 0);

		document.addNumberSortable(DossierTerm.CORRECTING_DATE_TIMESTAMP,
				Validator.isNotNull(object.getCorrecttingDate()) ? object.getCorrecttingDate().getTime() : 0);

		// add number fields
		document.addNumberSortable(DossierTerm.COUNTER, object.getCounter());
		document.addNumberSortable(DossierTerm.FOLDER_ID, object.getFolderId());
		document.addNumberSortable(DossierTerm.DOSSIER_ACTION_ID, object.getDossierActionId());
		document.addNumberSortable(DossierTerm.VIA_POSTAL, object.getViaPostal());
		document.addNumberSortable(DossierTerm.COUNTER, object.getCounter());

		int yearDossier = 0;

		int monthDossier = 0;

		if (Validator.isNotNull(object.getReceiveDate())) {
			Calendar cal = Calendar.getInstance();

			cal.setTime(object.getReceiveDate());

			yearDossier = cal.get(Calendar.YEAR);
			monthDossier = cal.get(Calendar.MONTH) + 1;

		}

		document.addNumberSortable(DossierTerm.YEAR_DOSSIER, yearDossier);
		document.addNumberSortable(DossierTerm.MONTH_DOSSIER, monthDossier);

		// DossierAction fields

		if (object.getDossierActionId() != 0) {
			// Date now = new Date();

			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(object.getDossierActionId());

			document.addTextSortable(DossierTerm.LAST_ACTION_DATE, APIDateTimeUtils
					.convertDateToString(dossierAction.getCreateDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			document.addTextSortable(DossierTerm.LAST_ACTION_CODE, dossierAction.getActionCode());
			document.addTextSortable(DossierTerm.LAST_ACTION_NAME, dossierAction.getActionName());
			document.addTextSortable(DossierTerm.LAST_ACTION_USER, dossierAction.getActionUser());
			document.addTextSortable(DossierTerm.LAST_ACTION_NOTE, dossierAction.getActionNote());

			document.addTextSortable(DossierTerm.STEP_CODE, dossierAction.getStepCode());
			document.addTextSortable(DossierTerm.STEP_NAME, dossierAction.getStepName());

			if (dossierAction.getActionOverdue() != 0) {
				document.addTextSortable(DossierTerm.STEP_OVER_DUE, StringPool.TRUE);
			} else {
				document.addTextSortable(DossierTerm.STEP_OVER_DUE, StringPool.FALSE);
			}

			Date stepDuedate = DossierOverDueUtils.getStepOverDue(dossierAction.getActionOverdue(), new Date());

			document.addTextSortable(DossierTerm.STEP_DUE_DATE,
					APIDateTimeUtils.convertDateToString(stepDuedate, APIDateTimeUtils._NORMAL_PARTTERN));
		}

		// add text fields

		long dossierId = object.getDossierId();

		document.addNumberSortable(DossierTerm.DOSSIER_ID, dossierId);
		document.addTextSortable(DossierTerm.REFERENCE_UID, object.getReferenceUid());
		document.addTextSortable(DossierTerm.SERVICE_CODE, object.getServiceCode());
		document.addTextSortable(DossierTerm.SERVICE_NAME, object.getServiceName());
		document.addTextSortable(DossierTerm.GOV_AGENCY_CODE, object.getGovAgencyCode());
		document.addTextSortable(DossierTerm.GOV_AGENCY_NAME, object.getGovAgencyName());
		document.addTextSortable(DossierTerm.APPLICANT_NAME, object.getApplicantName());
		document.addTextSortable(DossierTerm.APPLICANT_ID_TYPE, object.getApplicantIdType());
		document.addTextSortable(DossierTerm.APPLICANT_ID_NO, object.getApplicantIdNo());
		document.addTextSortable(DossierTerm.ADDRESS, object.getAddress());
		document.addTextSortable(DossierTerm.CITY_CODE, object.getCityCode());
		document.addTextSortable(DossierTerm.CITY_NAME, object.getCityName());
		document.addTextSortable(DossierTerm.DISTRICT_CODE, object.getDistrictCode());
		document.addTextSortable(DossierTerm.DISTRICT_NAME, object.getDistrictName());
		document.addTextSortable(DossierTerm.WARD_CODE, object.getWardCode());
		document.addTextSortable(DossierTerm.WARD_NAME, object.getWardName());
		document.addTextSortable(DossierTerm.CONTACT_NAME, object.getContactName());
		
		if(Validator.isNull(object.getContactTelNo())){
			//Get ContactTelNo from Applicant
		}
		
		document.addTextSortable(DossierTerm.CONTACT_TEL_NO, object.getContactTelNo());
		document.addTextSortable(DossierTerm.CONTACT_EMAIL, object.getContactEmail());
		document.addTextSortable(DossierTerm.DOSSIER_TEMPLATE_NO, object.getDossierTemplateNo());
		document.addTextSortable(DossierTerm.DOSSIER_NOTE, object.getDossierNote());
		document.addTextSortable(DossierTerm.SUBMISSION_NOTE, object.getSubmissionNote());
		document.addTextSortable(DossierTerm.APPLICANT_NOTE, object.getApplicantNote());
		document.addTextSortable(DossierTerm.BRIEF_NOTE, object.getBriefNote());
		document.addTextSortable(DossierTerm.DOSSIER_NO, object.getDossierNo());
		document.addTextSortable(DossierTerm.SUBMITTING, Boolean.toString(object.getSubmitting()));

		document.addTextSortable(DossierTerm.DOSSIER_STATUS, object.getDossierStatus());
		document.addTextSortable(DossierTerm.DOSSIER_STATUS_TEXT, object.getDossierStatusText());
		document.addTextSortable(DossierTerm.DOSSIER_SUB_STATUS, object.getDossierSubStatus());
		document.addTextSortable(DossierTerm.DOSSIER_SUB_STATUS_TEXT, object.getDossierSubStatusText());
		document.addTextSortable(DossierTerm.POSTAL_ADDRESS, object.getPostalAddress());
		document.addTextSortable(DossierTerm.POSTAL_CITY_CODE, object.getPostalCityCode());
		document.addTextSortable(DossierTerm.POSTAL_CITY_NAME, object.getPostalCityName());
		document.addTextSortable(DossierTerm.POSTAL_TEL_NO, object.getPostalTelNo());
		document.addTextSortable(DossierTerm.PASSWORD, object.getPassword());
		document.addTextSortable(DossierTerm.NOTIFICATION, Boolean.toString(object.getNotification()));
		document.addTextSortable(DossierTerm.ONLINE, Boolean.toString(object.getOnline()));
		document.addTextSortable(DossierTerm.SERVER_NO, object.getServerNo());
		document.addTextSortable(DossierTerm.DOSSIER_OVER_DUE,
				Boolean.toString(getDossierOverDue(object.getPrimaryKey())));

		// Indexing DossierActionUsers
		List<Long> actionUserIds = new ArrayList<>();
		try {
			List<DossierAction> dossierActions = DossierActionLocalServiceUtil.getDossierActionById(dossierId);

			for (DossierAction dossierAction : dossierActions) {
				List<DossierActionUser> dossierActionUsers = DossierActionUserLocalServiceUtil
						.getListUser(dossierAction.getDossierActionId());

				if (dossierActionUsers != null) {
					for (DossierActionUser dossierActionUser : dossierActionUsers) {
						if (!actionUserIds.contains(dossierActionUser.getUserId())) {
							actionUserIds.add(dossierActionUser.getUserId());
						}
					}
				}

			}
		} catch (Exception e) {
			_log.error("Can not get list dossierActions by dossierId " + dossierId, e);
		}

		document.addTextSortable(DossierTerm.ACTION_USERIDS, StringUtil.merge(actionUserIds, StringPool.SPACE));
		
		//binhth index dossierId CTN
		// TODO
		
		MessageDigest md5 = null;
		
		byte[] ba = null;

		try {
			
			md5 = MessageDigest.getInstance("MD5");
			
			ba = md5.digest(object.getReferenceUid().getBytes("UTF-8"));
			
		} catch (Exception e) {
		} 

		DateFormat df = new SimpleDateFormat("yy");
		
		String formattedDate = df.format(Calendar.getInstance().getTime());
		
		String dossierIDCTN = StringPool.BLANK;
		
		dossierIDCTN = formattedDate + HashFunction.hexShort(ba);
		
		document.addTextSortable(DossierTerm.DOSSIER_ID+"CTN", dossierIDCTN);

		// Get info cert Number
		List<DossierFile> dossierFileList = DossierFileLocalServiceUtil.getAllDossierFile(dossierId);
		if (dossierFileList != null && dossierFileList.size() > 0) {
			String templateNo = StringPool.BLANK;
			String partNo = StringPool.BLANK;
			int partType = 2;
			boolean eSign = true;
			String deliverableCode = StringPool.BLANK;
			for (DossierFile dossierFile : dossierFileList) {
				templateNo = dossierFile.getDossierTemplateNo();
				partNo = dossierFile.getDossierPartNo();
				DossierPart dossierPart = DossierPartLocalServiceUtil.getByPartTypeEsign (object.getGroupId(), templateNo,
						partNo, partType, eSign);
				if (dossierPart != null) {
					deliverableCode = dossierFile.getDeliverableCode();
					if (Validator.isNotNull(deliverableCode)) {
						Deliverable deli = DeliverableLocalServiceUtil.getByCodeAndState(deliverableCode, "2");
						if (deli != null) {
							String formData = StringPool.BLANK;
							formData = deli.getFormData();
							try {
								JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
								String certNo = String.valueOf(jsonData.get("so_chung_chi"));
								String certDate = String.valueOf(jsonData.get("ngay_ky_cc"));
								_log.info("certNo: "+certNo);
								_log.info("certDate: "+certDate);
								document.addTextSortable("so_chung_chi", certNo);
								document.addTextSortable("ngay_ky_cc", certDate);
								break;
							} catch (Exception e) {
								// TODO:
							}
						}
					}
				}
			}
		}

		return document;
	}


	private boolean getDossierOverDue(long dossierId) {
		// TODO add logic here

		return false;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Dossier object = DossierLocalServiceUtil.getDossier(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(Dossier object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DossierLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Dossier>() {

					@Override
					public void performAction(Dossier object) {
						try {
							Document document = getDocument(object);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + object.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	Log _log = LogFactoryUtil.getLog(DossierIndexer.class);

}
