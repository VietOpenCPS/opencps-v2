package org.fds.opencps.paygate.integration.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.fds.opencps.paygate.integration.action.impl.PayGateIntegrationActionImpl;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItemMapping;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemMappingLocalServiceUtil;
import org.opencps.dossiermgt.model.ApplicableInfo;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFeeInfo;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.model.ServiceConfigMapping;
import org.opencps.dossiermgt.service.ApplicableInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFeeInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigMappingLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil;

public class KeypayDVCQGUtils {

	private static Log _log = LogFactoryUtil.getLog(KeypayDVCQGUtils.class.getName());

	public static String buildMaTraCuuTT(String dossierNo) {

		return dossierNo + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static JSONObject dptracuuthanhtoanhs(Dossier dossier, PaymentFile paymentFile,
			JSONObject epaymentProfileDVCQG) {

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
		philephiJ.put(KeypayDVCQGTerm.PHI_LE_PHI_TEN_PHI_LE_PHI, epaymentProfileDVCQG.getString(PayGateTerm.TENPHILEPHI));
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

	public static JSONObject dptracuuthanhtoanhs2(Dossier dossier, PaymentFile paymentFile,
			JSONObject epaymentProfileDVCQG) throws PortalException {

		JSONObject data = JSONFactoryUtil.createJSONObject();
		long groupId = dossier.getGroupId();
		ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil
				.fetchDVCQGServiceCode(dossier.getGroupId(), dossier.getServiceCode());
		
		ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, dossier.getServiceCode());
		
		DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("GOVERNMENT_AGENCY",
				groupId);

		DictItemMapping itemMapping = DictItemMappingLocalServiceUtil.fetchByF_GID_IC_CID(groupId,
				dossier.getGovAgencyCode(), collection.getDictCollectionId());
		
		String serviceCodeDVCQG = serviceInfoMapping != null ? serviceInfoMapping.getServiceCodeDVCQG()
				: StringPool.BLANK;
		ApplicableInfo applicableInfo = ApplicableInfoLocalServiceUtil.fetchByG_SC_GC_SL(0, serviceCodeDVCQG,
				itemMapping.getItemCodeDVCQG(), serviceInfo.getMaxLevel());

		ServiceConfigMapping serviceConfigMapping = ServiceConfigMappingLocalServiceUtil
				.fetchServiceConfigMapping(applicableInfo.getServiceConfigMappingId());
		
		List<PaymentFeeInfo> paymentFeeInfos = PaymentFeeInfoLocalServiceUtil
				.findByServiceConfigMappingId(serviceConfigMapping.getServiceConfigMappingId());
		
		_log.info(groupId + "|" + serviceCodeDVCQG + "|" + itemMapping.getItemCodeDVCQG() + "|" + serviceInfo.getMaxLevel());

		String transactionId = PayGateUtil.decodeTransactionId(paymentFile.getPaymentFileId());
		data.put(PayGateTerm.TRANSACTION_ID, transactionId);
		data.put(PayGateTerm.CLIENT_ID, epaymentProfileDVCQG.getString(PayGateTerm.CLIENT_ID));
		data.put(KeypayDVCQGTerm.MA_TRA_CUU_TT, buildMaTraCuuTT(dossier.getDossierNo()));
		data.put(KeypayDVCQGTerm.MA_HO_SO, dossier.getDossierNo());
		
		int maDV = epaymentProfileDVCQG.getInt(PayGateTerm.MADICHVU);

		data.put(KeypayDVCQGTerm.MA_DICH_VU, maDV);
		
		
		if (maDV == 2) {
			// TODO: can check lai
			data.put(KeypayDVCQGTerm.MA_DON_VI, epaymentProfileDVCQG.getString(KeypayDVCQGTerm.MA_DON_VI));
			data.put(KeypayDVCQGTerm.TEN_DON_VI,epaymentProfileDVCQG.getString(KeypayDVCQGTerm.TEN_DON_VI));
			data.put(KeypayDVCQGTerm.MA_HO_SO, dossier.getDossierNo());
			data.put(KeypayDVCQGTerm.MA_DVC, serviceCodeDVCQG + epaymentProfileDVCQG.getString(PayGateTerm.MADVCAPPEND));
			data.put(KeypayDVCQGTerm.TEN_DVC, dossier.getServiceName());
			data.put(KeypayDVCQGTerm.MA_TTHC, serviceCodeDVCQG);
			data.put(KeypayDVCQGTerm.TEN_TTHC, dossier.getServiceName());
		} else {
			data.put(KeypayDVCQGTerm.MA_DON_VI, StringPool.BLANK);
			data.put(KeypayDVCQGTerm.TEN_DON_VI, StringPool.BLANK);
			data.put(KeypayDVCQGTerm.MA_HO_SO, StringPool.BLANK);
			data.put(KeypayDVCQGTerm.MA_DVC, StringPool.BLANK);
			data.put(KeypayDVCQGTerm.TEN_DVC, StringPool.BLANK);
			data.put(KeypayDVCQGTerm.MA_TTHC, StringPool.BLANK);
			data.put(KeypayDVCQGTerm.TEN_TTHC, StringPool.BLANK);
		}
		
		if (maDV == 1) {
			// TODO: lam gi voi 1
			data.put(PayGateTerm.MALOAIHINHTHUPHAT, StringPool.BLANK);
			data.put(PayGateTerm.TENLOAIHINHTHUPHAT, StringPool.BLANK);
			data.put(PayGateTerm.KHOBAC, StringPool.BLANK);
			data.put(PayGateTerm.NGAYQD, StringPool.BLANK);
			data.put(PayGateTerm.SOQD, StringPool.BLANK);
			data.put(PayGateTerm.THOIGIANVIPHAM, StringPool.BLANK);
			data.put(PayGateTerm.DIADIEMVIPHAM, StringPool.BLANK);
			data.put(PayGateTerm.TENNGUOIVIPHAM, StringPool.BLANK);
		} else {
			data.put(PayGateTerm.MALOAIHINHTHUPHAT, StringPool.BLANK);
			data.put(PayGateTerm.TENLOAIHINHTHUPHAT, StringPool.BLANK);
			data.put(PayGateTerm.KHOBAC, StringPool.BLANK);
			data.put(PayGateTerm.NGAYQD, StringPool.BLANK);
			data.put(PayGateTerm.SOQD, StringPool.BLANK);
			data.put(PayGateTerm.THOIGIANVIPHAM, StringPool.BLANK);
			data.put(PayGateTerm.DIADIEMVIPHAM, StringPool.BLANK);
			data.put(PayGateTerm.TENNGUOIVIPHAM, StringPool.BLANK);
		}

		JSONArray philephi = JSONFactoryUtil.createJSONArray();
		if (paymentFeeInfos != null) {
			for (PaymentFeeInfo paymentFeeInfo : paymentFeeInfos) {
				JSONObject _tm = JSONFactoryUtil.createJSONObject();
				_tm.put(KeypayDVCQGTerm.PHI_LE_PHI_LOAI_PHI_LE_PHI, paymentFeeInfo.getType());
				_tm.put(KeypayDVCQGTerm.PHI_LE_PHI_MA_PHI_LE_PHI, paymentFeeInfo.getPaymentFeeCode());
				_tm.put(KeypayDVCQGTerm.PHI_LE_PHI_TEN_PHI_LE_PHI, paymentFeeInfo.getPaymentFeeName());
				_tm.put(KeypayDVCQGTerm.PHI_LE_PHI_SO_TIEN, paymentFeeInfo.getAmount());
				// philephi.put(_tm);
			}
		}
		data.put(KeypayDVCQGTerm.PHI_LE_PHI, philephi);

		// moi dich vu cong co 1 thong tin ngan hang thu huong khac nhau
		JSONObject banksInfo = epaymentProfileDVCQG.getJSONObject(PayGateTerm.BANKINFO);
		JSONObject bankInfo = JSONFactoryUtil.createJSONObject();
		if (banksInfo.has(dossier.getServiceCode())) {
			bankInfo = banksInfo.getJSONObject(dossier.getServiceCode());
		} else {
			bankInfo = banksInfo.getJSONObject(PayGateTerm.DEFAULT);
		}
		data.put(KeypayDVCQGTerm.TK_THU_HUONG, bankInfo.getString(KeypayDVCQGTerm.TK_THU_HUONG));
		data.put(KeypayDVCQGTerm.MA_NH_THU_HUONG, bankInfo.getString(KeypayDVCQGTerm.MA_NH_THU_HUONG));
		data.put(KeypayDVCQGTerm.TEN_TK_THU_HUONG, bankInfo.getString(KeypayDVCQGTerm.TEN_TK_THU_HUONG));
		data.put(KeypayDVCQGTerm.MA_CO_QUAN_QD, epaymentProfileDVCQG.getString(PayGateTerm.MACOQUANQD));
		data.put(KeypayDVCQGTerm.TEN_CO_QUAN_QD, epaymentProfileDVCQG.getString(PayGateTerm.TENCOQUANQD));
		data.put(KeypayDVCQGTerm.TAI_KHOAN_THU_NSNN, StringPool.BLANK);
		data.put(KeypayDVCQGTerm.NOI_DUNG_THANH_TOAN, epaymentProfileDVCQG.getString(KeypayDVCQGTerm.NOI_DUNG_THANH_TOAN)); // paymentFile.getPaymentNote()
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
