package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.PaymentFileActions;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.PaymentFileModel;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public class PaymentFileActionsImpl implements PaymentFileActions{

	//1
	@Override
	public JSONObject getByDossierId(long dossierId) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		int total = PaymentFileLocalServiceUtil.getPaymentFilesCount();
		
		List<PaymentFile> data = PaymentFileLocalServiceUtil.getByDossierId(dossierId);
		
		result.put("total", total);
		result.put("data", data);
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public PaymentFile createPaymentFile(long groupId, long dossierId, String referenceUid, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdNo, String paymentFee, long paymentAmount,
			String paymentNote, String epaymentProfile, String bankInfo, String feeText, ServiceContext serviceContext)
			throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentFile getDetailByReferenceUid(long dossierId, String referenceUid) {
		// TODO Auto-generated method stub
		return null;
	}

}
