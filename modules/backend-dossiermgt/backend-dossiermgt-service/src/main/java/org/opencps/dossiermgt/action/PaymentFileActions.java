package org.opencps.dossiermgt.action;

import java.util.List;

import org.opencps.dossiermgt.model.PaymentFile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public interface PaymentFileActions {
	//1
	/**
	 * Get all PaymentFile of DossierId
	 * 
	 * @param serviceInfoId
	 * @return JSONObject
	 */
	public JSONObject getByDossierId(long dossierId);
	//2
	/**
	 * Add PaymentFile of DossierId
	 * 
	 * @param form data
	 * @return PaymentFile
	 */
	public PaymentFile createPaymentFile(long groupId, long dossierId, String referenceUid, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdNo, String paymentFee, long paymentAmount,
			String paymentNote, String epaymentProfile, String bankInfo, String feeText, ServiceContext serviceContext)
			throws PortalException;
	//3
	public PaymentFile getDetailByReferenceUid(long dossierId, String referenceUid);
	//4
//	public PaymentFile getDetailByReferenceUid(long dossierId, String referenceUid);
}
