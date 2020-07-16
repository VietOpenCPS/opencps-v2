package org.fds.opencps.paygate.integration.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil;

public class KeypayDVCQGUtils {
	
	public static String buildMaTraCuuTT (String dossierNo) {

		return dossierNo + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static JSONObject dptracuuthanhtoanhs (Dossier dossier, PaymentFile paymentFile, JSONObject epaymentProfileDVCQG) {
		
		JSONObject data = JSONFactoryUtil.createJSONObject();
		
		String transactionId = PayGateUtil.decodeTransactionId(paymentFile.getPaymentFileId());
		data.put(PayGateTerm.TRANSACTION_ID, transactionId);
		data.put(PayGateTerm.CLIENT_ID, epaymentProfileDVCQG.getString(PayGateTerm.CLIENT_ID));

		data.put(KeypayDVCQGTerm.MA_TRA_CUU_TT, buildMaTraCuuTT(dossier.getDossierNo()));
		data.put(KeypayDVCQGTerm.MA_DICH_VU, epaymentProfileDVCQG.getString(PayGateTerm.MADICHVU));
		
		ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil
				.fetchDVCQGServiceCode(dossier.getGroupId(), dossier.getServiceCode());
		String serviceCodeDVCQG = serviceInfoMapping != null ? serviceInfoMapping.getServiceCodeDVCQG()
				: StringPool.BLANK;
		String serviceNameDVCQG = serviceInfoMapping != null ? serviceInfoMapping.getServiceNameDVCQG()
				: StringPool.BLANK;
		data.put(KeypayDVCQGTerm.MA_DVC, serviceCodeDVCQG + epaymentProfileDVCQG.getString(PayGateTerm.MADVCAPPEND));
		data.put(KeypayDVCQGTerm.TEN_DVC, dossier.getServiceName());
		
		data.put(KeypayDVCQGTerm.MA_TTHC, serviceCodeDVCQG);
		data.put(KeypayDVCQGTerm.TEN_TTHC, dossier.getServiceName());
		data.put(KeypayDVCQGTerm.MA_DON_VI, epaymentProfileDVCQG.getString(PayGateTerm.MADONVI));
		data.put(KeypayDVCQGTerm.TEN_DON_VI, epaymentProfileDVCQG.getString(PayGateTerm.TENDONVI));
		data.put(KeypayDVCQGTerm.MA_HO_SO, dossier.getDossierNo());

		JSONArray philephi = JSONFactoryUtil.createJSONArray();
		JSONObject philephiJ = JSONFactoryUtil.createJSONObject();
		// moi dich vu cong co 1 thong tin ngan hang thu huong khac nhau
		JSONObject banksInfo = epaymentProfileDVCQG.getJSONObject(PayGateTerm.BANKINFO);
		JSONObject bankInfo = JSONFactoryUtil.createJSONObject();
		if (banksInfo.has(dossier.getServiceCode())) {
			bankInfo = banksInfo.getJSONObject(dossier.getServiceCode());
		} else {
			bankInfo = banksInfo.getJSONObject(PayGateTerm.DEFAULT);
		}
		philephiJ.put(KeypayDVCQGTerm.PHI_LE_PHI_LOAI_PHI_LE_PHI, "2");
		philephiJ.put(KeypayDVCQGTerm.PHI_LE_PHI_MA_PHI_LE_PHI, "2");
		philephiJ.put(KeypayDVCQGTerm.PHI_LE_PHI_TEN_PHI_LE_PHI, bankInfo.getString(PayGateTerm.TENPHILEPHI));
		philephiJ.put(KeypayDVCQGTerm.PHI_LE_PHI_SO_TIEN, paymentFile.getPaymentAmount());
		philephi.put(philephiJ);
		data.put(KeypayDVCQGTerm.PHI_LE_PHI, philephi);

		data.put(KeypayDVCQGTerm.TK_THU_HUONG, bankInfo.getString(PayGateTerm.TKTHUHUONG));
		data.put(KeypayDVCQGTerm.MA_NH_THU_HUONG, bankInfo.getString(PayGateTerm.MANHTHUHUONG));
		data.put(KeypayDVCQGTerm.TEN_TK_THU_HUONG, bankInfo.getString(PayGateTerm.TENTKTHUHUONG));
		data.put(KeypayDVCQGTerm.MA_LOAI_HINH_THU_PHAT, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.TEN_LOAI_HINH_THU_PHAT, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.MA_CO_QUAN_QD, epaymentProfileDVCQG.getString(PayGateTerm.MACOQUANQD));
		data.put(KeypayDVCQGTerm.TEN_CO_QUAN_QD, epaymentProfileDVCQG.getString(PayGateTerm.TENCOQUANQD));
		data.put(KeypayDVCQGTerm.KHO_BAC, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.NGAY_QD, new SimpleDateFormat("yyyyMMdd").format(new Date()));
		data.put(KeypayDVCQGTerm.SO_QD, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.THOI_GIAN_VI_PHAM, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.DIA_DIEM_VI_PHAM, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.TEN_NGUOI_VI_PHAM, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.TAI_KHOAN_THU_NSNN, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.NOI_DUNG_THANH_TOAN, paymentFile.getPaymentNote());
		data.put(KeypayDVCQGTerm.HO_TEN_NGUOI_NOP, dossier.getApplicantName());
		data.put(KeypayDVCQGTerm.SO_CMND_NGUOI_NOP, dossier.getApplicantIdNo());
		data.put(KeypayDVCQGTerm.DIA_CHI_NGUOI_NOP, dossier.getAddress());
		data.put(KeypayDVCQGTerm.HUYEN_NGUOI_NOP, dossier.getDistrictName());
		data.put(KeypayDVCQGTerm.TINH_NGUOI_NOP, dossier.getCityName());

		JSONArray dskhoannop = JSONFactoryUtil.createJSONArray();
		JSONObject dskhoannop_obj = JSONFactoryUtil.createJSONObject();

		dskhoannop_obj.put(KeypayDVCQGTerm.DS_KHOAN_NOP_NOI_DUNG, paymentFile.getPaymentNote());
		dskhoannop_obj.put(KeypayDVCQGTerm.DS_KHOAN_NOP_SO_TIEN, String.valueOf(paymentFile.getPaymentAmount()));
		dskhoannop.put(dskhoannop_obj);
		data.put(KeypayDVCQGTerm.DS_KHOAN_NOP, dskhoannop);

		return data;
	}
}
