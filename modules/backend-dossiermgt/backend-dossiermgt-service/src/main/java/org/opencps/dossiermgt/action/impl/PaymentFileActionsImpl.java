package org.opencps.dossiermgt.action.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.action.util.PaymentUrlGenerator;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

public class PaymentFileActionsImpl implements PaymentFileActions {

	Log _log = LogFactoryUtil.getLog(ServiceInfoActionsImpl.class);

	/**
	 * Get all PaymentFile of DossierId
	 * 
	 * @param dossierId
	 * @return JSONObject
	 */
	@Override
	public JSONObject getByDossierId(long dossierId, long companyId, long groupId, Sort[] sorts, int start, int end,
			ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));
		params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));

		try {

			hits = PaymentFileLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = PaymentFileLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	/**
	 * Create PaymentFile of DossierId
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	@Override
	public PaymentFile createPaymentFile(long userId, long groupId, long dossierId, String referenceUid,
			String govAgencyCode, String govAgencyName, String applicantName, String applicantIdNo, String paymentFee,
			long paymentAmount, String paymentNote, String epaymentProfile, String bankInfo,
			ServiceContext serviceContext) throws PortalException {
		_log.info("boom boom");
		if (Validator.isNull(referenceUid)) {
			referenceUid = PortalUUIDUtil.generate();
		}

		try {
			
			PaymentFile result = PaymentFileLocalServiceUtil.createPaymentFiles(userId, groupId, dossierId, referenceUid,
					govAgencyCode, govAgencyName, applicantName, applicantIdNo, paymentFee, paymentAmount, paymentNote,
					epaymentProfile, bankInfo, serviceContext);
			
			JSONObject epaymentProfileObject = JSONFactoryUtil.createJSONObject(epaymentProfile);
			
			// genarater url keypay
			// TODO
			// binhth
			if (epaymentProfileObject.has("paymentPattern")) {
				
				String generatorPayURL;
				try {
					
					Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
					
					PaymentConfig paymentConfig = PaymentConfigLocalServiceUtil.getPaymentConfigByGovAgencyCode(groupId,
							dossier.getGovAgencyCode());
					
					JSONObject epaymentConfigJSON = JSONFactoryUtil.createJSONObject(paymentConfig.getEpaymentConfig());
					
					epaymentProfileObject.put("detailUrl", epaymentConfigJSON.getString("paymentResultUrl") + dossierId);

					generatorPayURL = PaymentUrlGenerator.generatorPayURL(groupId,
							result.getPaymentFileId(), epaymentProfileObject.getString("paymentPattern"), dossierId, epaymentProfileObject.getString("keypayMerchantCode"));
					
					epaymentProfileObject.put("keypayUrl", generatorPayURL);
					
					result = updateEProfile(dossierId, referenceUid, epaymentProfileObject.toJSONString(), serviceContext);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
			
			return result;
					
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			_log.info("boom boom");
			_log.info(e);
			e.printStackTrace();
			throw new PortalException();
		}

	}

	/**
	 * Get detail PaymentFile of DossierId and referenceUid
	 * 
	 * @param dossierId
	 * @param referenceUid
	 * @return List<Document>
	 */
	@Override
	public List<Document> getPaymentFileDetail(long dossierId, String referenceUid, long companyId, long groupId,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		List<Document> result = null;

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));
		params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));
		params.put(PaymentFileTerm.REFERENCE_UID, referenceUid);

		try {

			hits = PaymentFileLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result = hits.toList();

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	/**
	 * Get info EpaymentProfile of DossierId and referenceUid
	 * 
	 * @param dossierId
	 * @param referenceUid
	 * @return PaymentFile
	 */
	@Override
	public PaymentFile getEpaymentProfile(long dossierId, String referenceUid) {

		return PaymentFileLocalServiceUtil.getEpaymentProfile(dossierId, referenceUid);
	}

	@Override
	public PaymentFile getPaymentFile(long dossierId, String referenceUid) {

		return PaymentFileLocalServiceUtil.getPaymentFile(dossierId, referenceUid);
	}

	/**
	 * Update info EpaymentProfile of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	@Override
	public PaymentFile updateEProfile(long dossierId, String referenceUid, String strInput,
			ServiceContext serviceContext) throws PortalException {

		return PaymentFileLocalServiceUtil.updateEProfile(dossierId, referenceUid, strInput, serviceContext);
	}

	/**
	 * Update Payment File Confirm of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	@Override
	public PaymentFile updateFileConfirm(long groupId, long id, String referenceUid, String confirmNote,
			String paymentMethod, String confirmPayload, ServiceContext serviceContext)
			throws SystemException, PortalException {

		return PaymentFileLocalServiceUtil.updateFileConfirm(groupId, id, referenceUid, confirmNote, paymentMethod,
				confirmPayload, serviceContext);
	}

	/**
	 * Update Payment File Confirm of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	@Override
	public PaymentFile updateFileConfirm(long groupId, long id, String referenceUid, String confirmNote,
			String paymentMethod, String confirmPayload, String sourceFileName, long fileSize, InputStream inputStream,
			ServiceContext serviceContext) throws SystemException, PortalException {

		return PaymentFileLocalServiceUtil.updateFileConfirm(groupId, id, referenceUid, confirmNote, paymentMethod,
				confirmPayload, sourceFileName, fileSize, inputStream, serviceContext);
	}

	/**
	 * Update Payment File Approval of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	@Override
	public PaymentFile updateFileApproval(long groupId, long id, String referenceUid, String approveDatetime,
			String accountUserName, String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo,
			String invoiceNo, String sourceFileName, long fileSize, InputStream inputStream,
			ServiceContext serviceContext) throws SystemException, PortalException, java.text.ParseException {

		return PaymentFileLocalServiceUtil.updateFileApproval(groupId, id, referenceUid, approveDatetime,
				accountUserName, govAgencyTaxNo, invoiceTemplateNo, invoiceIssueNo, invoiceNo, sourceFileName, fileSize,
				inputStream, serviceContext);
	}

	@Override
	public PaymentFile updateFileApproval(long groupId, long id, String referenceUid, String approveDatetime,
			String accountUserName, String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo,
			String invoiceNo, ServiceContext serviceContext)
			throws SystemException, PortalException, java.text.ParseException {

		PaymentFile paymentFile = PaymentFileLocalServiceUtil.updateFileApproval(groupId, id, referenceUid, approveDatetime,
				accountUserName, govAgencyTaxNo, invoiceTemplateNo, invoiceIssueNo, invoiceNo, serviceContext);
		
		//Add PaymentFileSync
		
		Dossier dossier = DossierLocalServiceUtil.getDossier(paymentFile.getDossierId());
		//TODO review serverNo on this
		DossierSyncLocalServiceUtil.updateDossierSync(groupId, serviceContext.getUserId(), paymentFile.getDossierId(), dossier.getReferenceUid(),
				false, 3, paymentFile.getPrimaryKey(), paymentFile.getReferenceUid(), StringPool.BLANK);
		
		return paymentFile;
	}

	// 8,9
	/**
	 * Download file Confirm by referenceUid
	 * 
	 * @throws PortalException
	 */
	@Override
	public PaymentFile getPaymentFileByReferenceUid(Long id, String referenceUid) throws PortalException {
		return PaymentFileLocalServiceUtil.getPaymentFileByReferenceUid(id, referenceUid);
	}

	/**
	 * Get all Payment File
	 * 
	 * @param
	 * @return PaymentFile
	 */
	@Override
	public JSONObject getPaymentFiles(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = PaymentFileLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = PaymentFileLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}
	
	@Override
	public List<PaymentFile> getPaymentFiles(long dossierId){
		return PaymentFileLocalServiceUtil.getByDossierId(dossierId);
	}

}
