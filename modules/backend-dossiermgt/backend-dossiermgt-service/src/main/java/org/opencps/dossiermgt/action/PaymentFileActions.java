package org.opencps.dossiermgt.action;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.PaymentFile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface PaymentFileActions {

	/**
	 * Get all PaymentFile of DossierId
	 * 
	 * @param serviceInfoId
	 * @return JSONObject
	 */
	public JSONObject getByDossierId(long dossierId, long companyId, long groupId, Sort[] sorts, int start, int end,
			ServiceContext serviceContext);

	/**
	 * Add PaymentFile of DossierId
	 * 
	 * @param form
	 *            data
	 * @return PaymentFile
	 */
	public PaymentFile createPaymentFile(long userId, long groupId, long dossierId, String referenceUid,
			String paymentFee, long advanceAmount, long feeAmount, long serviceAmount, long shipAmount,
			long paymentAmount, String paymentNote, String epaymentProfile, String bankInfo,
			int paymentStatus, String paymentMethod,
			ServiceContext serviceContext) throws PortalException;

	/**
	 * Get detail PaymentFile of DossierId and referenceUid
	 * 
	 * @param dossierId
	 * @param referenceUid
	 * @return List<Document>
	 */
	public List<Document> getPaymentFileDetail(long dossierId, String referenceUid, long companyId, long groupId,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	/**
	 * Get info EpaymentProfile of DossierId and referenceUid
	 * 
	 * @param dossierId
	 * @param referenceUid
	 * @return PaymentFile
	 */
	public PaymentFile getEpaymentProfile(long dossierId, String referenceUid);

	public PaymentFile getPaymentFile(long dossierId, String referenceUid);

	/**
	 * Update info EpaymentProfile of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	public PaymentFile updateEProfile(long dossierId, String referenceUid, String strInput,
			ServiceContext serviceContext) throws PortalException;

	/**
	 * Update Payment File Confirm of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	public PaymentFile updateFileConfirm(long groupId, long id, String referenceUid, String confirmNote,
			String paymentMethod, String confirmPayload, ServiceContext serviceContext)
			throws SystemException, PortalException;

	/**
	 * Update Payment File Confirm of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	public PaymentFile updateFileConfirm(long groupId, long id, String referenceUid, String confirmNote,
			String paymentMethod, String confirmPayload, String sourceFileName, long fileSize, InputStream inputStream,
			ServiceContext serviceContext) throws SystemException, PortalException;

	/**
	 * Update Payment File Approval of DossierId and referenceUid
	 * 
	 * @param form
	 *            params
	 * @return PaymentFile
	 */
	public PaymentFile updateFileApproval(long groupId, long id, String referenceUid, String approveDatetime,
			String accountUserName, String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo,
			String invoiceNo, boolean isSync, ServiceContext serviceContext) throws SystemException, PortalException, java.text.ParseException;
	
	
	public PaymentFile updateFileApproval(long groupId, long id, String referenceUid, String approveDatetime,
			String accountUserName, String govAgencyTaxNo, String invoiceTemplateNo, String invoiceIssueNo,
			String invoiceNo, String sourceFileName, long fileSize, InputStream inputStream,
			ServiceContext serviceContext) throws SystemException, PortalException, java.text.ParseException;

	// 8,9
	/**
	 * Download file confirm
	 * 
	 * @param id
	 * @param referenceUid
	 * @return PaymentFile
	 */
	public PaymentFile getPaymentFileByReferenceUid(Long id, String referenceUid) throws PortalException;

	/**
	 * Get all Payment File
	 * 
	 * @param
	 * @return PaymentFile
	 */
	public JSONObject getPaymentFiles(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	
	public PaymentFile getPaymentFiles(long groupId, long dossierId);

}
