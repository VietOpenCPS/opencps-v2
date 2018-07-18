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
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierRequestUD;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil;

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
import com.liferay.portal.kernel.util.StringBundler;
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

		try {
			// Indexer of audit fields
			document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
			document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
			document.addDateSortable(Field.CREATE_DATE, object.getCreateDate());
			document.addDateSortable(Field.MODIFIED_DATE, object.getModifiedDate());
			document.addNumberSortable(Field.USER_ID, object.getUserId());
			if (Validator.isNotNull(object.getUserName())) {
				document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
			}
			document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
			document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

			// add number fields
			if (Validator.isNotNull(object.getApplicantIdDate())) {
				document.addTextSortable(DossierTerm.APPLICANT_ID_DATE, APIDateTimeUtils
						.convertDateToString(object.getApplicantIdDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				document.addTextSortable(DossierTerm.APPLICANT_ID_DATE, StringPool.BLANK);
			}
			if (Validator.isNotNull(object.getSubmitDate())) {
				document.addDateSortable(DossierTerm.SUBMIT_DATE, object.getSubmitDate());
			} else {
				document.addTextSortable(DossierTerm.SUBMIT_DATE, StringPool.BLANK);
			}
			// document.addTextSortable(DossierTerm.SUBMIT_DATE,
			// APIDateTimeUtils.convertDateToString(object.getSubmitDate(),
			// APIDateTimeUtils._NORMAL_PARTTERN));
			// document.addTextSortable(DossierTerm.RECEIVE_DATE,
			// APIDateTimeUtils.convertDateToString(object.getReceiveDate(),
			// APIDateTimeUtils._NORMAL_PARTTERN));

			if (Validator.isNotNull(object.getReceiveDate())) {
				document.addDateSortable(DossierTerm.RECEIVE_DATE, object.getReceiveDate());
			} else {
				document.addTextSortable(DossierTerm.RECEIVE_DATE, StringPool.BLANK);
			}

			if (Validator.isNotNull(object.getDueDate())) {
				document.addTextSortable(DossierTerm.DUE_DATE,
						APIDateTimeUtils.convertDateToString(object.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				document.addTextSortable(DossierTerm.DUE_DATE, StringPool.BLANK);
			}
			if (Validator.isNotNull(object.getReleaseDate())) {
				document.addTextSortable(DossierTerm.RELEASE_DATE, APIDateTimeUtils
						.convertDateToString(object.getReleaseDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				document.addTextSortable(DossierTerm.RELEASE_DATE, StringPool.BLANK);
			}
			if (Validator.isNotNull(object.getFinishDate())) {
				document.addTextSortable(DossierTerm.FINISH_DATE, APIDateTimeUtils
						.convertDateToString(object.getFinishDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				document.addTextSortable(DossierTerm.FINISH_DATE, StringPool.BLANK);
			}
			if (Validator.isNotNull(object.getCancellingDate())) {
				document.addTextSortable(DossierTerm.CANCELLING_DATE, APIDateTimeUtils
						.convertDateToString(object.getCancellingDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				document.addTextSortable(DossierTerm.CANCELLING_DATE, StringPool.BLANK);
			}
			if (Validator.isNotNull(object.getCorrecttingDate())) {
				document.addTextSortable(DossierTerm.CORRECTING_DATE, APIDateTimeUtils
						.convertDateToString(object.getCorrecttingDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				document.addTextSortable(DossierTerm.CORRECTING_DATE, StringPool.BLANK);
			}

			// if (Validator.isNotNull(object.getReceiveDate())) {
			document.addNumberSortable(DossierTerm.RECEIVE_DATE_TIMESTAMP,
					Validator.isNotNull(object.getReceiveDate()) ? object.getReceiveDate().getTime() : 0);
			// }

			// if (Validator.isNotNull(object.getDueDate())) {
			document.addNumberSortable(DossierTerm.DUE_DATE_TIMESTAMP,
					Validator.isNotNull(object.getDueDate()) ? object.getDueDate().getTime() : 0);
			// }

			// if (Validator.isNotNull(object.getReleaseDate())) {
			document.addNumberSortable(DossierTerm.RELEASE_DATE_TIMESTAMP,
					Validator.isNotNull(object.getReleaseDate()) ? object.getReleaseDate().getTime() : 0);
			// }

			// if (Validator.isNotNull(object.getCancellingDate())) {
			document.addNumberSortable(DossierTerm.CANCELLING_DATE_TIMESTAMP,
					Validator.isNotNull(object.getCancellingDate()) ? object.getCancellingDate().getTime() : 0);
			// }

			// if (Validator.isNotNull(object.getCorrecttingDate())) {
			document.addNumberSortable(DossierTerm.CORRECTING_DATE_TIMESTAMP,
					Validator.isNotNull(object.getCorrecttingDate()) ? object.getCorrecttingDate().getTime() : 0);
			// }

			// add number fields
			document.addNumberSortable(DossierTerm.COUNTER, object.getCounter());
			document.addNumberSortable(DossierTerm.FOLDER_ID, object.getFolderId());
			document.addNumberSortable(DossierTerm.DOSSIER_ACTION_ID, object.getDossierActionId());
			document.addNumberSortable(DossierTerm.VIA_POSTAL, object.getViaPostal());
			document.addNumberSortable(DossierTerm.COUNTER, object.getCounter());
			document.addNumberSortable(DossierTerm.ORIGINALLITY, object.getOriginality());
			
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
			long dossierObjectActionId = object.getDossierActionId();
			if (dossierObjectActionId != 0) {
				// Date now = new Date();

				DossierAction dossierAction = DossierActionLocalServiceUtil
						.fetchDossierAction(dossierObjectActionId);

				if (dossierAction != null) {
					// if (Validator.isNotNull(dossierAction.getCreateDate())) {
					document.addTextSortable(DossierTerm.LAST_ACTION_DATE, APIDateTimeUtils
							.convertDateToString(dossierAction.getCreateDate(), APIDateTimeUtils._NORMAL_PARTTERN));
					// }
					// if (Validator.isNotNull(dossierAction.getActionCode())) {
					document.addTextSortable(DossierTerm.LAST_ACTION_CODE, dossierAction.getActionCode());
					// }
					// if (Validator.isNotNull(dossierAction.getActionName())) {
					document.addTextSortable(DossierTerm.LAST_ACTION_NAME, dossierAction.getActionName());
					// }
					// if (Validator.isNotNull(dossierAction.getActionUser())) {
					document.addTextSortable(DossierTerm.LAST_ACTION_USER, dossierAction.getActionUser());
					// }
					// if (Validator.isNotNull(dossierAction.getActionNote())) {
					document.addTextSortable(DossierTerm.LAST_ACTION_NOTE, dossierAction.getActionNote());
					// }

					// if (Validator.isNotNull(dossierAction.getStepCode())) {
					document.addTextSortable(DossierTerm.STEP_CODE, dossierAction.getStepCode());
					// }
					// if (Validator.isNotNull(dossierAction.getStepName())) {
					document.addTextSortable(DossierTerm.STEP_NAME, dossierAction.getStepName());
					// }

					if (dossierAction.getActionOverdue() != 0) {
						document.addTextSortable(DossierTerm.STEP_OVER_DUE, StringPool.TRUE);
					} else {
						document.addTextSortable(DossierTerm.STEP_OVER_DUE, StringPool.FALSE);
					}

					Date stepDuedate = DossierOverDueUtils.getStepOverDue(dossierAction.getActionOverdue(), new Date());

					// if (Validator.isNotNull(stepDuedate)) {
					document.addTextSortable(DossierTerm.STEP_DUE_DATE,
							APIDateTimeUtils.convertDateToString(stepDuedate, APIDateTimeUtils._NORMAL_PARTTERN));
					// }
					//Index userNote
					String actionCode = dossierAction.getActionCode();
					_log.info("actionCode: "+actionCode);
					ActionConfig act = ActionConfigLocalServiceUtil.getByCode(object.getGroupId(), actionCode);
					_log.info("act: "+act);
					if (act != null) {
						_log.info("act: "+act.getUserNote());
						document.addNumberSortable(DossierTerm.USER_NOTE, act.getUserNote());
					} else {
						document.addNumberSortable(DossierTerm.USER_NOTE, 0);
					}
				}
			}

			// add text fields

			long dossierId = object.getDossierId();

			document.addNumberSortable(DossierTerm.DOSSIER_ID, dossierId);
			if (Validator.isNotNull(object.getReferenceUid())) {
				document.addTextSortable(DossierTerm.REFERENCE_UID, object.getReferenceUid());
			}
			if (Validator.isNotNull(object.getServiceCode())) {
				document.addTextSortable(DossierTerm.SERVICE_CODE, object.getServiceCode());
			}
			if (Validator.isNotNull(object.getServiceName())) {
				document.addTextSortable(DossierTerm.SERVICE_NAME, object.getServiceName());
			}
			if (Validator.isNotNull(object.getGovAgencyCode())) {
				document.addTextSortable(DossierTerm.GOV_AGENCY_CODE, object.getGovAgencyCode());
			}
			if (Validator.isNotNull(object.getGovAgencyName())) {
				document.addTextSortable(DossierTerm.GOV_AGENCY_NAME, object.getGovAgencyName());
			}
			if (Validator.isNotNull(object.getApplicantName())) {
				document.addTextSortable(DossierTerm.APPLICANT_NAME, object.getApplicantName());
			}

			if (Validator.isNotNull(object.getApplicantIdType())) {
				document.addTextSortable(DossierTerm.APPLICANT_ID_TYPE, object.getApplicantIdType());
			}
			if (Validator.isNotNull(object.getApplicantIdNo())) {
				document.addTextSortable(DossierTerm.APPLICANT_ID_NO, object.getApplicantIdNo());
			}
			if (Validator.isNotNull(object.getAddress())) {
				document.addTextSortable(DossierTerm.ADDRESS, object.getAddress());
			}
			document.addTextSortable(DossierTerm.CITY_CODE, object.getCityCode());
			document.addTextSortable(DossierTerm.CITY_NAME, object.getCityName());
			document.addTextSortable(DossierTerm.DISTRICT_CODE, object.getDistrictCode());
			document.addTextSortable(DossierTerm.DISTRICT_NAME, object.getDistrictName());
			document.addTextSortable(DossierTerm.WARD_CODE, object.getWardCode());
			document.addTextSortable(DossierTerm.WARD_NAME, object.getWardName());
			document.addTextSortable(DossierTerm.CONTACT_NAME, object.getContactName());

			if (Validator.isNull(object.getContactTelNo())) {
				// Get ContactTelNo from Applicant
			}

			document.addTextSortable(DossierTerm.CONTACT_TEL_NO, object.getContactTelNo());
			document.addTextSortable(DossierTerm.CONTACT_EMAIL, object.getContactEmail());
			document.addTextSortable(DossierTerm.DOSSIER_TEMPLATE_NO, object.getDossierTemplateNo());
			document.addTextSortable(DossierTerm.DOSSIER_NOTE, object.getDossierNote());
			document.addTextSortable(DossierTerm.SUBMISSION_NOTE, object.getSubmissionNote());
			document.addTextSortable(DossierTerm.APPLICANT_NOTE, object.getApplicantNote());
			document.addTextSortable(DossierTerm.BRIEF_NOTE, object.getBriefNote());
			// Search follow dossierNo
			String dossierNo = object.getDossierNo();
			String dossierNoSearch = StringPool.BLANK;
			document.addTextSortable(DossierTerm.DOSSIER_NO, dossierNo);
			if (Validator.isNotNull(dossierNo)) {
				dossierNoSearch = SpecialCharacterUtils.splitSpecial(dossierNo);
				document.addTextSortable(DossierTerm.DOSSIER_NO_SEARCH, dossierNoSearch);
			}
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

			// TODO: index dossierAction StepCode
			StringBundler sb = new StringBundler();
			long dossierActionsUserId = object.getDossierActionId();
			if (dossierActionsUserId > 0) {
				List<DossierActionUser> dossierActionUsers = DossierActionUserLocalServiceUtil
						.getListUser(dossierActionsUserId);
				if (dossierActionUsers != null && dossierActionUsers.size() > 0) {
					int length = dossierActionUsers.size();
					for (int i = 0; i < length; i++) {
						DossierActionUser dau = dossierActionUsers.get(i);
						long userId = dau.getUserId();
						if (i == 0) {
							sb.append(userId);
						} else {
							sb.append(StringPool.SPACE);
							sb.append(userId);

						}
					}
				}
			}
			_log.info("Mapping user:" + sb.toString());
			document.addTextSortable(DossierTerm.ACTION_MAPPING_USERID, sb.toString());

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

			_log.info("Action user:" + StringUtil.merge(actionUserIds, StringPool.SPACE));
			document.addTextSortable(DossierTerm.ACTION_USERIDS, StringUtil.merge(actionUserIds, StringPool.SPACE));

			// binhth index dossierId CTN
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

			document.addTextSortable(DossierTerm.DOSSIER_ID + "CTN", dossierIDCTN);

			// Get info cert Number
			List<String> certNoIndexer = certNoIndexer(dossierId, object.getGroupId());
			if (certNoIndexer != null && certNoIndexer.size() > 0) {
				String certNo = certNoIndexer.get(0);
				String certDateStr = certNoIndexer.get(1);
				String certDateTimeStamp = certDateStr + " 00:00:00";
				Date certDate = APIDateTimeUtils.convertStringToDate(certDateTimeStamp,
						APIDateTimeUtils._NORMAL_PARTTERN);
				_log.info("certNo: " + certNo);
				_log.info("certDate: " + certDate);
				if (Validator.isNotNull(certDate)) {
					document.addTextSortable("so_chung_chi", certNo);
					document.addDateSortable("ngay_ky_cc", certDate);
					// Search follow so_chung_chi
					String certNoSearch = SpecialCharacterUtils.splitSpecial(certNo);
					document.addTextSortable(DossierTerm.CERT_NO_SEARCH, certNoSearch);
				}
			}

			document.addTextSortable(DossierTerm.ENDORSEMENT_DATE, APIDateTimeUtils
					.convertDateToString(object.getEndorsementDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			document.addNumberSortable(DossierTerm.ENDORSEMENT_DATE_TIMESTAMP,
					Validator.isNotNull(object.getEndorsementDate()) ? object.getEndorsementDate().getTime() : 0);

			// LamTV: Indexer from dossierRequest to Dossier
			DossierRequestUD dRegUD = DossierRequestUDLocalServiceUtil.getDossierRequestByDossierId(dossierId);
			if (dRegUD != null) {
				_log.info("statusReg: " + dRegUD.getStatusReg());
				document.addNumberSortable(DossierTerm.STATUS_REG, dRegUD.getStatusReg());
			} else {
				document.addNumberSortable(DossierTerm.STATUS_REG, 4);
			}

			if (Validator.isNotNull(object.getLockState())) {
				document.addTextSortable(DossierTerm.LOCK_STATE, object.getLockState());
			} else {
				document.addTextSortable(DossierTerm.LOCK_STATE, StringPool.BLANK);
			}
			//LamTV: Process Assigned dossier
			DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(dossierActionsUserId,
					object.getUserId());
			if (dau != null) {
				document.addNumberSortable(DossierTerm.ASSIGNED, dau.getAssigned());
			} else {
				document.addNumberSortable(DossierTerm.ASSIGNED, ConstantsTerm.NO_ASSINED);
			}
		} catch (Exception e) {
			_log.error(e);
		}
		return document;
	}

	private List<String> certNoIndexer(long dossierId, long groupId) {
		List<String> certIndex = new ArrayList<String>();
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
				DossierPart dossierPart = DossierPartLocalServiceUtil.getByPartTypeEsign (templateNo,
						partNo, partType, eSign);
				if (dossierPart != null) {
					List<DossierFile> dossierFileRefList = DossierFileLocalServiceUtil
							.getByReferenceUid(dossierFile.getReferenceUid());
					if (dossierFileRefList != null && dossierFileRefList.size() > 0) {
						for (DossierFile dof : dossierFileRefList) {
							deliverableCode = dof.getDeliverableCode();
							_log.info("DOssier deliverableCode: "+deliverableCode);
							if (Validator.isNotNull(deliverableCode)) {
								Deliverable deli = DeliverableLocalServiceUtil.getByCodeAndState(deliverableCode, "2");
								if (deli != null) {
									String formData = StringPool.BLANK;
									formData = deli.getFormData();
									try {
										JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
										String certNo = String.valueOf(jsonData.get("so_chung_chi"));
										String certDateStr = String.valueOf(jsonData.get("ngay_ky_cc"));
										if (Validator.isNotNull(certNo) && Validator.isNotNull(certDateStr)) {
											certIndex.add(certNo);
											certIndex.add(certDateStr);
										}
										return certIndex;
									} catch (Exception e) {
										_log.error(e);
									}
								}
							}
						}
					}
				}
			}
		}
		return certIndex;
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
