package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.opencps.auth.utils.APIDateTimeUtils;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.DVCQGIntegrationAction;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.constants.DVCQGIntegrationActionTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierStatusMapping;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierStatusMappingLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil;
import org.opencps.usermgt.model.Answer;
import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.AnswerLocalServiceUtil;
import org.opencps.usermgt.service.QuestionLocalServiceUtil;

/**
 * @author trungnt
 *
 */
public class DVCQGIntegrationActionImpl implements DVCQGIntegrationAction {

	private static HashMap<String, Map<CharSequence, Integer>> _mapChars = null;

	private static HashMap<String, JSONObject> _mapItems = null;

	private Log _log = LogFactoryUtil.getLog(DVCQGIntegrationActionImpl.class);
	private static final String LUCENE_DATE_FORMAT = "yyyyMMddHHmmss";
	private static final String HCM_TIMEZONE = "Asia/Ho_Chi_Minh";
	
	private String convertDate2String(Date date) {

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(LUCENE_DATE_FORMAT);

		if (Validator.isNull(date)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone(HCM_TIMEZONE));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

	private JSONObject createSyncDossierBodyRequest(long groupId, Dossier dossier, JSONObject config,
			String accessToken) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		String _oServiceCode = dossier.getServiceCode();
		String _mServiceCode = StringPool.BLANK;
		ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId,
				_oServiceCode);
		if (serviceInfoMapping != null) {
			_mServiceCode = serviceInfoMapping.getServiceCodeDVCQG();
		}
		_log.info("-------------->>>> " + _mServiceCode +  StringPool.PIPE + _oServiceCode +  StringPool.PIPE + groupId);

		object.put(DVCQGIntegrationActionTerm.MAHOSO, dossier.getDossierNo());
		object.put(DVCQGIntegrationActionTerm.MATTHC, _mServiceCode);
		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put(DVCQGIntegrationActionTerm.SERVICE, DVCQGIntegrationActionTerm.LAYTHUTUC);
		body.put(DVCQGIntegrationActionTerm.MATTHC, _mServiceCode);

		JSONObject serviceInfo = getSharingData(config, body, accessToken);
		//get TenTTHC, MaLinhVuc, TenLinhVuc
		String TenTTHC = StringPool.BLANK;
		String MaLinhVuc = StringPool.BLANK;
		String TenLinhVuc = StringPool.BLANK;
		if (serviceInfo != null && serviceInfo.has(DVCQGIntegrationActionTerm.RESULT)) {
			JSONArray results = serviceInfo.getJSONArray(DVCQGIntegrationActionTerm.RESULT);
			if (results.length() > 0) {
				JSONObject _tmp = results.getJSONObject(0);
				TenTTHC = _tmp.getString(DVCQGIntegrationActionTerm.TENTTHC);
				MaLinhVuc = _tmp.getJSONArray(DVCQGIntegrationActionTerm.LINHVUCTHUCHIEN).getJSONObject(0).getString(DVCQGIntegrationActionTerm.MALINHVUC);
				TenLinhVuc = _tmp.getJSONArray(DVCQGIntegrationActionTerm.LINHVUCTHUCHIEN).getJSONObject(0).getString(DVCQGIntegrationActionTerm.TENLINHVUC);
			}
		}
		object.put(DVCQGIntegrationActionTerm.TENTTHC, TenTTHC);
		object.put(DVCQGIntegrationActionTerm.MALINHVUC, MaLinhVuc);
		object.put(DVCQGIntegrationActionTerm.TENLINHVUC, TenLinhVuc);
		//object.put("SoBienNhan, StringPool.BLANK); //ko bb
		object.put(DVCQGIntegrationActionTerm.CHUHOSO, dossier.getContactName()); //ko bb
		int LoaiDoiTuong = 1;
		if (Validator.isNotNull(dossier.getApplicantIdType())) {
			if (dossier.getApplicantIdType().equals(DVCQGIntegrationActionTerm.BUSINESS)) {
				LoaiDoiTuong = 2;
			}
		}
		object.put(DVCQGIntegrationActionTerm.LOAIDOITUONG, String.valueOf(LoaiDoiTuong));
		object.put(DVCQGIntegrationActionTerm.MADOITUONG, StringPool.BLANK); //ko bb
		object.put(DVCQGIntegrationActionTerm.THONGTINKHAC, StringPool.BLANK); //ko bb
		object.put(DVCQGIntegrationActionTerm.EMAIL, dossier.getContactEmail());
		object.put(DVCQGIntegrationActionTerm.FAX, dossier.getContactTelNo()); //ko bb
		object.put(DVCQGIntegrationActionTerm.SODIENTHOAI, dossier.getContactTelNo());
		object.put(DVCQGIntegrationActionTerm.TRICHYEUHOSO, dossier.getDossierNote());
		object.put(DVCQGIntegrationActionTerm.NGAYTIEPNHAN, DVCQGIntegrationActionTerm.convertDate2String(dossier.getReceiveDate()));
		object.put(DVCQGIntegrationActionTerm.NGAYHENTRA, DVCQGIntegrationActionTerm.convertDate2String(dossier.getDueDate()));
		object.put(DVCQGIntegrationActionTerm.TRANGTHAIHOSO, getMappingStatus(groupId, dossier));
		object.put(DVCQGIntegrationActionTerm.NGAYTRA, DVCQGIntegrationActionTerm.convertDate2String(dossier.getFinishDate()));//ko bb
		object.put(DVCQGIntegrationActionTerm.THONGTINTRA, StringPool.BLANK);//ko bb
		int HinhThuc = 0;
		if (dossier.getViaPostal() != 0 && dossier.getViaPostal() != 1) {
			HinhThuc = 1;
		}
		object.put(DVCQGIntegrationActionTerm.HINHTHUC, String.valueOf(HinhThuc));
		object.put(DVCQGIntegrationActionTerm.NGAYKETTHUCXULY, DVCQGIntegrationActionTerm.convertDate2String(dossier.getReleaseDate()));//ko bb
		object.put(DVCQGIntegrationActionTerm.DONVIXULY, dossier.getGovAgencyName());
		object.put(DVCQGIntegrationActionTerm.GHICHU, dossier.getDossierNote());//ko bb

		JSONArray TaiLieuNop = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.TAILIEUNOP, TaiLieuNop);//ko bb
		//object.put("TepDinhKemId", StringPool.BLANK);
		//object.put("TenTepDinhKem", StringPool.BLANK);
		//object.put("IsDeleted", StringPool.BLANK);
		//object.put("MaThanhPhanHoSo", StringPool.BLANK);
		//object.put("DuongDanTaiTepTin", StringPool.BLANK);

		JSONArray DanhSachLePhi = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.DANHSACHLEPHI, DanhSachLePhi);//ko bb
		//object.put("TenPhiLePhi", StringPool.BLANK);
		//object.put("MaPhiLePhi", StringPool.BLANK);
		//object.put("HinhThucThu", StringPool.BLANK);
		//object.put("Gia", StringPool.BLANK);
		//object.put("LoaiPhiLePhi", StringPool.BLANK);

		JSONArray DanhSachTepDinhKemKhac = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.DANHSACHTEPDINHKEMKHAC, DanhSachTepDinhKemKhac);//ko bb
		//object.put("TenGiayTo", StringPool.BLANK);
		//object.put("SoLuong", StringPool.BLANK);
		//object.put("LoaiGiayTo", StringPool.BLANK);

		JSONArray DanhSachHoSoBoSung = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.DANHSACHHOSOBOSUNG, DanhSachHoSoBoSung);//ko bb
		//object.put("HoSoBoSungId", StringPool.BLANK);
		//object.put("NguoiYeuCauBoSung", StringPool.BLANK);
		//object.put("NoiDungBoSung", StringPool.BLANK);
		//object.put("NgayBoSung", StringPool.BLANK);
		//object.put("NguoiTiepNhanBoSung", StringPool.BLANK);
		//object.put("ThongTinTiepNhan", StringPool.BLANK);
		//object.put("NgayTiepNhanBoSung", StringPool.BLANK);
		//object.put("TrangThaiBoSung", StringPool.BLANK);
		//object.put("DanhSachGiayToBoSung", StringPool.BLANK);
		//object.put("DanhSachLePhiBoSung", StringPool.BLANK);
		//object.put("NgayHenTraTruoc", StringPool.BLANK);
		//object.put("NgayHenTraMoi", StringPool.BLANK);
		JSONArray DanhSachGiayToKetQua = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.DANHSACHGIAYTOKETQUA, DanhSachGiayToKetQua);//ko bb
		//object.put("TenGiayTo", StringPool.BLANK);
		//object.put("MaThanhPhanHoSo", StringPool.BLANK);
		//object.put("GiayToId", StringPool.BLANK);
		//object.put("DuongDanTepTinKetQua", StringPool.BLANK);

		return object;
	}

	private JSONObject createSyncDossierBodyRequest(long groupId, Dossier dossier, ServerConfig serverConfig) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		String _oServiceCode = dossier.getServiceCode();
		String _mServiceCode = StringPool.BLANK;
		ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId,
				_oServiceCode);
		if (serviceInfoMapping != null) {
			_mServiceCode = serviceInfoMapping.getServiceCodeDVCQG();
		}
		_log.info("-------------->>>> " + _mServiceCode +  StringPool.PIPE + _oServiceCode +  StringPool.PIPE + groupId);

		object.put(DVCQGIntegrationActionTerm.MAHOSO, dossier.getDossierNo());
		object.put(DVCQGIntegrationActionTerm.MATTHC, _mServiceCode);
		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put(DVCQGIntegrationActionTerm.SERVICE, DVCQGIntegrationActionTerm.LAYTHUTUC);
		body.put(DVCQGIntegrationActionTerm.MATTHC, _mServiceCode);

		JSONObject serviceInfo = getSharingData(serverConfig, body);
		//get TenTTHC, MaLinhVuc, TenLinhVuc
		String TenTTHC = StringPool.BLANK;
		String MaLinhVuc = StringPool.BLANK;
		String TenLinhVuc = StringPool.BLANK;
		if (serviceInfo != null && serviceInfo.has(DVCQGIntegrationActionTerm.RESULT)) {
			JSONArray results = serviceInfo.getJSONArray(DVCQGIntegrationActionTerm.RESULT);
			if (results.length() > 0) {
				JSONObject _tmp = results.getJSONObject(0);
				TenTTHC = _tmp.getString(DVCQGIntegrationActionTerm.TENTTHC);
				MaLinhVuc = _tmp.getJSONArray(DVCQGIntegrationActionTerm.LINHVUCTHUCHIEN).getJSONObject(0).getString(DVCQGIntegrationActionTerm.MALINHVUC);
				TenLinhVuc = _tmp.getJSONArray(DVCQGIntegrationActionTerm.LINHVUCTHUCHIEN).getJSONObject(0).getString(DVCQGIntegrationActionTerm.TENLINHVUC);
			}
		}
		object.put(DVCQGIntegrationActionTerm.TENTTHC, TenTTHC);
		object.put(DVCQGIntegrationActionTerm.MALINHVUC, MaLinhVuc);
		object.put(DVCQGIntegrationActionTerm.TENLINHVUC, TenLinhVuc);
		//object.put("SoBienNhan", StringPool.BLANK); //ko bb
		object.put(DVCQGIntegrationActionTerm.CHUHOSO, dossier.getContactName()); //ko bb
		int LoaiDoiTuong = 1;
		if (Validator.isNotNull(dossier.getApplicantIdType())) {
			if (dossier.getApplicantIdType().equals(DVCQGIntegrationActionTerm.BUSINESS)) {
				LoaiDoiTuong = 2;
			}
		}
		object.put(DVCQGIntegrationActionTerm.LOAIDOITUONG, String.valueOf(LoaiDoiTuong));
		object.put(DVCQGIntegrationActionTerm.MADOITUONG, StringPool.BLANK); //ko bb
		object.put(DVCQGIntegrationActionTerm.THONGTINKHAC, StringPool.BLANK); //ko bb
		object.put(DVCQGIntegrationActionTerm.EMAIL, dossier.getContactEmail());
		object.put(DVCQGIntegrationActionTerm.FAX, dossier.getContactTelNo()); //ko bb
		object.put(DVCQGIntegrationActionTerm.SODIENTHOAI, dossier.getContactTelNo());
		object.put(DVCQGIntegrationActionTerm.TRICHYEUHOSO, dossier.getDossierNote());
		object.put(DVCQGIntegrationActionTerm.NGAYTIEPNHAN, DVCQGIntegrationActionTerm.convertDate2String(dossier.getReceiveDate()));
		object.put(DVCQGIntegrationActionTerm.NGAYHENTRA, DVCQGIntegrationActionTerm.convertDate2String(dossier.getDueDate()));
		object.put(DVCQGIntegrationActionTerm.TRANGTHAIHOSO, getMappingStatus(groupId, dossier));
		object.put(DVCQGIntegrationActionTerm.NGAYTRA, DVCQGIntegrationActionTerm.convertDate2String(dossier.getFinishDate()));//ko bb
		object.put(DVCQGIntegrationActionTerm.THONGTINTRA, StringPool.BLANK);//ko bb
		int HinhThuc = 0;
		if (dossier.getViaPostal() != 0 && dossier.getViaPostal() != 1) {
			HinhThuc = 1;
		}
		object.put(DVCQGIntegrationActionTerm.HINHTHUC, String.valueOf(HinhThuc));
		object.put(DVCQGIntegrationActionTerm.NGAYKETTHUCXULY, DVCQGIntegrationActionTerm.convertDate2String(dossier.getReleaseDate()));//ko bb
		object.put(DVCQGIntegrationActionTerm.DONVIXULY, dossier.getGovAgencyName());
		object.put(DVCQGIntegrationActionTerm.GHICHU, dossier.getDossierNote());//ko bb

		JSONArray TaiLieuNop = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.TAILIEUNOP, TaiLieuNop);//ko bb
		//object.put("TepDinhKemId", StringPool.BLANK);
		//object.put("TenTepDinhKem", StringPool.BLANK);
		//object.put("IsDeleted", StringPool.BLANK);
		//object.put("MaThanhPhanHoSo", StringPool.BLANK);
		//object.put("DuongDanTaiTepTin", StringPool.BLANK);

		JSONArray DanhSachLePhi = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.DANHSACHLEPHI, DanhSachLePhi);//ko bb
		//object.put("TenPhiLePhi", StringPool.BLANK);
		//object.put("MaPhiLePhi", StringPool.BLANK);
		//object.put("HinhThucThu", StringPool.BLANK);
		//object.put("Gia", StringPool.BLANK);
		//object.put("LoaiPhiLePhi", StringPool.BLANK);

		JSONArray DanhSachTepDinhKemKhac = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.DANHSACHTEPDINHKEMKHAC, DanhSachTepDinhKemKhac);//ko bb
		//object.put("TenGiayTo", StringPool.BLANK);
		//object.put("SoLuong", StringPool.BLANK);
		//object.put("LoaiGiayTo", StringPool.BLANK);

		JSONArray DanhSachHoSoBoSung = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.DANHSACHHOSOBOSUNG, DanhSachHoSoBoSung);//ko bb
		//object.put("HoSoBoSungId", StringPool.BLANK);
		//object.put("NguoiYeuCauBoSung", StringPool.BLANK);
		//object.put("NoiDungBoSung", StringPool.BLANK);
		//object.put("NgayBoSung", StringPool.BLANK);
		//object.put("NguoiTiepNhanBoSung", StringPool.BLANK);
		//object.put("ThongTinTiepNhan", StringPool.BLANK);
		//object.put("NgayTiepNhanBoSung", StringPool.BLANK);
		//object.put("TrangThaiBoSung", StringPool.BLANK);
		//object.put("DanhSachGiayToBoSung", StringPool.BLANK);
		//object.put("DanhSachLePhiBoSung", StringPool.BLANK);
		//object.put("NgayHenTraTruoc", StringPool.BLANK);
		//object.put("NgayHenTraMoi", StringPool.BLANK);
		JSONArray DanhSachGiayToKetQua = JSONFactoryUtil.createJSONArray();
		object.put(DVCQGIntegrationActionTerm.DANHSACHGIAYTOKETQUA, DanhSachGiayToKetQua);//ko bb
		//object.put("TenGiayTo", StringPool.BLANK);
		//object.put("MaThanhPhanHoSo", StringPool.BLANK);
		//object.put("GiayToId", StringPool.BLANK);
		//object.put("DuongDanTepTinKetQua", StringPool.BLANK);

		return object;
	}

	private JSONObject createSyncDossierStatusBodyRequest(long groupId, Dossier dossier) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		object.put(DVCQGIntegrationActionTerm.MAHOSO, dossier.getDossierNo());

		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		object.put(DVCQGIntegrationActionTerm.NGUOIXULY, dossierAction != null ? dossierAction.getActionUser() : StringPool.BLANK);
		object.put(DVCQGIntegrationActionTerm.CHUCDANH, StringPool.BLANK);//ko bb
		object.put(DVCQGIntegrationActionTerm.THOIDIEMXULY,
				dossierAction != null ? DVCQGIntegrationActionTerm.convertDate2String(dossierAction.getCreateDate()) : StringPool.BLANK);
		object.put(DVCQGIntegrationActionTerm.PHONGBANXULY, StringPool.BLANK);//ko bb
		object.put(DVCQGIntegrationActionTerm.NOIDUNGXULY, dossierAction != null ? dossierAction.getActionNote() : StringPool.BLANK);
		object.put(DVCQGIntegrationActionTerm.TRANGTHAI, getMappingStatus(groupId, dossier));
		object.put(DVCQGIntegrationActionTerm.NGAYBATDAU, StringPool.BLANK);//ko bb
		object.put(DVCQGIntegrationActionTerm.NGAYKETTHUCTHEOQUYDINH, StringPool.BLANK);//ko bb

		return object;
	}

	@Override
	public JSONObject doSyncGovernmentAgency(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject qajson = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> doSyncGovernmentAgency: " + serverConfigs + "|" + serverConfigs.size());
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				if (!data.has("service")) {
					data.put("service", "LayDanhMucCoQuan");
				}
				qajson = getSharingDictCollection(serverConfig, data);
				if (qajson.has("error_code") && qajson.getInt("error_code") == 0 && qajson.has("result")
						&& qajson.getJSONArray("result").length() > 0) {

					JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

					DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
							DataMGTConstants.GOVERNMENT_AGENCY, serviceContext.getScopeGroupId());
					if (collection == null || config == null) {
						return null;
					}

					JSONArray dmlst = qajson.getJSONArray("result");

					JSONArray resultData = JSONFactoryUtil.createJSONArray();

					JSONObject item = null;

					for (int i = 0; i < dmlst.length(); i++) {
						JSONObject dmobj = dmlst.getJSONObject(i);
						String madonvi = dmobj.getString("MADONVI");
						String tendonvi = dmobj.getString("TENDONVI");
						String capdonvi = dmobj.getString("CAPDONVI");

						DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(madonvi,
								collection.getDictCollectionId(), serviceContext.getScopeGroupId());
						if (dictItem != null) {
							dictItem.setItemName(tendonvi);
							dictItem.setModifiedDate(new Date());
							dictItem.setUserId(user.getUserId());
							dictItem.setUserName(user.getFullName());
							dictItem = DictItemLocalServiceUtil.updateDictItem(dictItem);

						} else {
							dictItem = DictItemLocalServiceUtil.addDictItem(user.getUserId(),
									serviceContext.getScopeGroupId(), collection.getDictCollectionId(), madonvi,
									tendonvi, tendonvi, StringPool.BLANK, 0, String.valueOf(1),
									GetterUtil.getInteger(capdonvi), StringPool.BLANK, serviceContext);
						}

						item = JSONFactoryUtil.createJSONObject();

						item.put("dictItemId", dictItem.getDictItemId());
						item.put("createDate",
								Validator.isNotNull(dictItem.getCreateDate())
										? APIDateTimeUtils.convertDateToString(dictItem.getCreateDate(),
												APIDateTimeUtils._TIMESTAMP)
										: StringPool.BLANK);
						item.put("modifiedDate",
								Validator.isNotNull(dictItem.getModifiedDate())
										? APIDateTimeUtils.convertDateToString(dictItem.getModifiedDate(),
												APIDateTimeUtils._TIMESTAMP)
										: StringPool.BLANK);
						item.put("itemCode", dictItem.getItemCode());
						item.put("itemName", dictItem.getItemName());
						item.put("itemNameEN", dictItem.getItemNameEN());
						item.put("itemDescription", dictItem.getItemDescription());
						item.put("level", dictItem.getLevel());
						item.put("sibling", dictItem.getSibling());
						item.put("treeIndex", dictItem.getTreeIndex());
						item.put("parentItem", JSONFactoryUtil.createJSONObject());
						item.put("groups", StringPool.BLANK);

						resultData.put(item);
					}
					result.put("total", resultData.length());
					result.put("data", resultData);
				}

			} catch (Exception e) {
				_log.error(e);
			}
		}

		return result;
	}

	@Override
	public JSONObject doSyncServiceAdministration(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject qajson = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> doSyncGovernmentAgency: " + serverConfigs + "|" + serverConfigs.size());
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				if (!data.has("service")) {
					data.put("service", "LayDanhMucCoQuan");
				}
				qajson = getSharingDictCollection(serverConfig, data);
				if (qajson.has("error_code") && qajson.getInt("error_code") == 0 && qajson.has("result")
						&& qajson.getJSONArray("result").length() > 0) {

					JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

					DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
							DataMGTConstants.SERVICE_ADMINISTRATION, serviceContext.getScopeGroupId());
					if (collection == null || config == null) {
						return null;
					}

					JSONArray dmlst = qajson.getJSONArray("result");

					JSONArray resultData = JSONFactoryUtil.createJSONArray();

					JSONObject item = null;

					for (int i = 0; i < dmlst.length(); i++) {
						JSONObject dmobj = dmlst.getJSONObject(i);
						String madonvi = dmobj.getString("MADONVI");
						String tendonvi = dmobj.getString("TENDONVI");
						String capdonvi = dmobj.getString("CAPDONVI");

						DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(madonvi,
								collection.getDictCollectionId(), serviceContext.getScopeGroupId());
						if (dictItem != null) {
							dictItem.setItemName(tendonvi);
							dictItem.setModifiedDate(new Date());
							dictItem.setUserId(user.getUserId());
							dictItem.setUserName(user.getFullName());
							dictItem = DictItemLocalServiceUtil.updateDictItem(dictItem);

						} else {
							dictItem = DictItemLocalServiceUtil.addDictItem(user.getUserId(),
									serviceContext.getScopeGroupId(), collection.getDictCollectionId(), madonvi,
									tendonvi, tendonvi, StringPool.BLANK, 0, String.valueOf(1),
									GetterUtil.getInteger(capdonvi), StringPool.BLANK, serviceContext);
						}

						item = JSONFactoryUtil.createJSONObject();

						item.put("dictItemId", dictItem.getDictItemId());
						item.put("createDate",
								Validator.isNotNull(dictItem.getCreateDate())
										? APIDateTimeUtils.convertDateToString(dictItem.getCreateDate(),
												APIDateTimeUtils._TIMESTAMP)
										: StringPool.BLANK);
						item.put("modifiedDate",
								Validator.isNotNull(dictItem.getModifiedDate())
										? APIDateTimeUtils.convertDateToString(dictItem.getModifiedDate(),
												APIDateTimeUtils._TIMESTAMP)
										: StringPool.BLANK);
						item.put("itemCode", dictItem.getItemCode());
						item.put("itemName", dictItem.getItemName());
						item.put("itemNameEN", dictItem.getItemNameEN());
						item.put("itemDescription", dictItem.getItemDescription());
						item.put("level", dictItem.getLevel());
						item.put("sibling", dictItem.getSibling());
						item.put("treeIndex", dictItem.getTreeIndex());
						item.put("parentItem", JSONFactoryUtil.createJSONObject());
						item.put("groups", StringPool.BLANK);

						resultData.put(item);
					}
					result.put("total", resultData.length());
					result.put("data", resultData);
				}

			} catch (Exception e) {
				_log.error(e);
			}
		}

		return result;
	}

	@Override
	public JSONObject doSyncServiceDomain(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject qajson = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> doSyncServiceDomain: " + serverConfigs + "|" + serverConfigs.size());
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				if (!data.has("service")) {
					data.put("service", "LayDanhMucLinhVuc");
				}
				qajson = getSharingDictCollection(serverConfig, data);
				//_log.info("-->>>>>>>>>>>>>>>>>>>>> " + qajson);
				if (qajson.has("error_code") && qajson.getInt("error_code") == 0 && qajson.has("result")
						&& qajson.getJSONArray("result").length() > 0) {

					JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

					DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
							DataMGTConstants.SERVICE_DOMAIN, serviceContext.getScopeGroupId());

					if (collection == null || config == null) {

						return null;
					}

					JSONArray dmlst = qajson.getJSONArray("result");

					String madonvi = config.getString("madonvi");

					JSONArray resultData = JSONFactoryUtil.createJSONArray();

					JSONObject item = null;
					int count = 1;
					for (int i = 0; i < dmlst.length(); i++) {
						JSONObject dmobj = dmlst.getJSONObject(i);
						String malinhvuc = dmobj.getString("MALINHVUC");
						String tenlinhvuc = dmobj.getString("TENLINHVUC");
						String manganh = dmobj.getString("MANGANH");
						if (Validator.isNotNull(manganh) && manganh.equalsIgnoreCase(madonvi)) {

							DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(malinhvuc,
									collection.getDictCollectionId(), serviceContext.getScopeGroupId());
							if (dictItem != null) {
								dictItem.setItemName(tenlinhvuc);
								dictItem.setModifiedDate(new Date());
								dictItem.setUserId(user.getUserId());
								dictItem.setUserName(user.getFullName());
								dictItem = DictItemLocalServiceUtil.updateDictItem(dictItem);

							} else {
								dictItem = DictItemLocalServiceUtil.addDictItem(user.getUserId(),
										serviceContext.getScopeGroupId(), collection.getDictCollectionId(), malinhvuc,
										tenlinhvuc, tenlinhvuc, StringPool.BLANK, 0, String.valueOf(count), 0,
										StringPool.BLANK, serviceContext);
							}

							//_log.info("------>>>>> " + serviceContext.getScopeGroupId() + "|" + malinhvuc);

							item = JSONFactoryUtil.createJSONObject();
							item.put("dictItemId", dictItem.getDictItemId());
							item.put("createDate",
									Validator.isNotNull(dictItem.getCreateDate())
											? APIDateTimeUtils.convertDateToString(dictItem.getCreateDate(),
													APIDateTimeUtils._TIMESTAMP)
											: StringPool.BLANK);
							item.put("modifiedDate", Validator.isNotNull(dictItem.getModifiedDate())
									? APIDateTimeUtils.convertDateToString(dictItem.getModifiedDate(),
											APIDateTimeUtils._TIMESTAMP)
									: StringPool.BLANK);
							item.put("itemCode", dictItem.getItemCode());
							item.put("itemName", dictItem.getItemName());
							item.put("itemNameEN", dictItem.getItemNameEN());
							item.put("itemDescription", dictItem.getItemDescription());
							item.put("level", dictItem.getLevel());
							item.put("sibling", dictItem.getSibling());
							item.put("treeIndex", dictItem.getTreeIndex());
							item.put("parentItem", JSONFactoryUtil.createJSONObject());
							item.put("groups", StringPool.BLANK);

							resultData.put(item);
							count++;
						}

					}
					result.put("total", resultData.length());
					result.put("data", resultData);

					//_log.info("---------------->>>>>>>> result " + result.toString());
				}
			} catch (Exception e) {
				_log.error(e);
			}
		}

		return result;
	}

	@Override
	public JSONObject doSyncSharingQA(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");

		JSONArray resultData = JSONFactoryUtil.createJSONArray();
		_log.info("-->>>>>>>> doSyncSharingQA: " + serverConfigs + "|" + serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				boolean autoSyncAllFAQ = false;
				if (!data.has("service")) {
					autoSyncAllFAQ = true;
				}
				JSONObject qajson = JSONFactoryUtil.createJSONObject();
				if (autoSyncAllFAQ) {
					data.put("service", "LayDanhSachHoiDapGuiTuDVCQG");
					qajson = getSharingData(serverConfig, data);
					extractFAQResult(user, resultData, qajson, serviceContext);

					data.put("service", "LayDanhSachHoiDapBoCoQuan");
					qajson = getSharingData(serverConfig, data);
					extractFAQResult(user, resultData, qajson, serviceContext);
				} else {
					qajson = getSharingData(serverConfig, data);
					extractFAQResult(user, resultData, qajson, serviceContext);
				}

			} catch (Exception e) {
				_log.error(e);
			}
		}

		JSONObject results = JSONFactoryUtil.createJSONObject();
		results.put("total", resultData.length());
		results.put("data", resultData);
		return results;
	}

	private JSONArray extractFAQResult(User user, JSONArray resultData, JSONObject qajson,
			ServiceContext serviceContext) {
		if (qajson.has("error_code") && qajson.getInt("error_code") == 0 && qajson.has("result")
				&& qajson.getJSONArray("result").length() > 0) {

			JSONArray qalst = qajson.getJSONArray("result");

			JSONObject item = null;

			for (int i = 0; i < qalst.length(); i++) {
				item = JSONFactoryUtil.createJSONObject();
				JSONObject qaobj = qalst.getJSONObject(i);
				long hoidapid = qaobj.getLong("HOIDAPID");

				if (hoidapid <= 0) {
					continue;
				}

				String malinhvuc = StringPool.BLANK;
				if (qaobj.has("MALINHVUC")) {
					malinhvuc = qaobj.getString("MALINHVUC");
				}

				String tendonvi = StringPool.BLANK;
				if (qaobj.has("TENDONVI")) {
					tendonvi = qaobj.getString("TENDONVI");
				}

				String noidung = StringPool.BLANK;
				if (qaobj.has("NOIDUNG")) {
					noidung = qaobj.getString("NOIDUNG");
				}

				String madonvi = StringPool.BLANK;
				if (qaobj.has("MADONVI")) {
					madonvi = qaobj.getString("MADONVI");
				}

				String noidungtraloi = qaobj.getString("NOIDUNGTRALOI");
				if (qaobj.has("NOIDUNGTRALOI")) {
					noidungtraloi = qaobj.getString("NOIDUNGTRALOI");
				}

				long traloiid = 0;
				if (qaobj.has("TRALOIID")) {
					traloiid = qaobj.getLong("TRALOIID");
				}

				String hovaten = StringPool.BLANK;
				if (qajson.has("HOVATEN")) {
					hovaten = qaobj.getString("HOVATEN");
				}

				/*String diachi = StringPool.BLANK;
				if (qajson.has("DIACHI")) {
					diachi = qaobj.getString("DIACHI");
				}*/

				String thudientu = StringPool.BLANK;
				if (qajson.has("THUDIENTU")) {
					thudientu = qaobj.getString("THUDIENTU");
				}

				/*String sodienthoai = StringPool.BLANK;
				if (qajson.has("SODIENTHOAI")) {
					sodienthoai = qaobj.getString("SODIENTHOAI");
				}*/

				String matthcdp = StringPool.BLANK;
				if (qajson.has("MATTHCDP")) {
					matthcdp = qaobj.getString("MATTHCDP");
				}

				int trangthai = 1;
				if (qajson.has("TRANGTHAI")) {
					trangthai = qaobj.getInt("TRANGTHAI");
					if (trangthai == 0) {
						continue;
					}
				}

				String domainName = StringPool.BLANK;

				String domainCode = StringPool.BLANK;

				if (Validator.isNotNull(malinhvuc)) {
					domainCode = malinhvuc;
				} else {
					domainCode = matthcdp;
				}

				if (Validator.isNotNull(domainCode)) {
					DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
							DataMGTConstants.SERVICE_DOMAIN, serviceContext.getScopeGroupId());

					if (collection != null) {
						DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(domainCode,
								collection.getDictCollectionId(), serviceContext.getScopeGroupId());

						domainName = dictItem != null ? dictItem.getItemName() : StringPool.BLANK;
					}

				}

				Question question = QuestionLocalServiceUtil.fetchByG_CN_CPK(serviceContext.getScopeGroupId(),
						"dvcqg_question", String.valueOf(hoidapid));
				List<Long> traloiids = new ArrayList<Long>();
				if (question == null) {
					question = QuestionLocalServiceUtil.updateQuestion(serviceContext.getCompanyId(),
							serviceContext.getScopeGroupId(), 0,
							Validator.isNotNull(hovaten) ? hovaten : user.getFullName(), thudientu, noidung, trangthai,
							domainCode, domainName, madonvi, tendonvi,
							Validator.isNotNull(hovaten) ? "faq" : StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
							"dvcqg_question", String.valueOf(hoidapid), 1);

				} else {
					List<Answer> answers = AnswerLocalServiceUtil.findByG_Q(serviceContext.getScopeGroupId(),
							question.getQuestionId());

					if (answers != null) {
						for (Answer answer : answers) {
							if (Validator.isNotNull(answer.getClassName())
									&& answer.getClassName().equals("dvcqg_answer")) {
								traloiids.add(GetterUtil.getLong(answer.getClassPK()));
							}
						}
					}

				}

				item.put("questionId", question.getQuestionId());
				item.put("createDate", APIDateTimeUtils.convertDateToString(question.getCreateDate()));
				item.put("fullname", question.getFullname());
				item.put("email", question.getEmail());
				item.put("content", question.getContent());
				item.put("publish", question.getPublish());
				item.put("questionType", question.getQuestionType());
				item.put("domainCode", question.getDomainCode());
				item.put("domainName", question.getDomainName());
				item.put("govAgencyCode", question.getGovAgencyCode());
				item.put("govAgencyName", question.getGovAgencyName());
				item.put("answered", traloiid > 0 ? true : false);
				item.put("subDomainCode", question.getSubDomainCode());
				item.put("subDomainName", question.getSubDomainName());
				resultData.put(item);

				if (traloiid > 0 && Validator.isNotNull(noidungtraloi) && !traloiids.contains(traloiid)) {

					AnswerLocalServiceUtil.updateAnswer(user.getUserId(), serviceContext.getScopeGroupId(), 0,
							question.getQuestionId(), noidungtraloi, 1, "dvcqg_answer", String.valueOf(traloiid), 1);

				} else if (traloiid <= 0) {
					JSONObject body = JSONFactoryUtil.createJSONObject();
					body.put("service", "LayNoiDungTraLoiTheoCauHoi");
					body.put("cauhoiid", hoidapid);
					JSONArray answerArr = getAnswer(user, serviceContext, body);
					if (answerArr != null) {
						for (int j = 0; j < answerArr.length(); j++) {
							JSONObject asobj = answerArr.getJSONObject(i);
							traloiid = asobj.getLong("TRALOIID");
							noidungtraloi = asobj.getString("NOIDUNGTRALOI");

							if (traloiid > 0 && Validator.isNotNull(noidungtraloi) && !traloiids.contains(traloiid)) {
								AnswerLocalServiceUtil.updateAnswer(user.getUserId(), serviceContext.getScopeGroupId(),
										0, question.getQuestionId(), noidungtraloi, 1, "dvcqg_answer",
										String.valueOf(traloiid), 1);
							}

						}
					}

				}

			}
		}
		return resultData;
	}
	
	private String getAccessToken(JSONObject config) {

		HttpURLConnection conn = null;

		try {
			String adapter_url = config.getString(DVCQGIntegrationActionTerm.ADAPTER_URL);
			String auth_endpoint = config.getString(DVCQGIntegrationActionTerm.AUTH_ENDPOINT);
			String username = config.getString(DVCQGIntegrationActionTerm.USERNAME);
			String password = config.getString(DVCQGIntegrationActionTerm.SECRET);
			String dstcode = config.getString(DVCQGIntegrationActionTerm.DSTCODE);
			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put(DVCQGIntegrationActionTerm.USERNAME, username);
			body.put(DVCQGIntegrationActionTerm.SECRET, password);

			String endpoint = adapter_url + auth_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.CHARSET, DVCQGIntegrationActionTerm.UTF_8_LOWER);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes(DVCQGIntegrationActionTerm.UTF_8);
			int postDataLength = postData.length;
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(postDataLength));
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				System.out.println("response: " + sb.toString());

				JSONObject result = JSONFactoryUtil.createJSONObject(sb.toString());

				if (result.has(DVCQGIntegrationActionTerm.SESSION) && result.has(DVCQGIntegrationActionTerm.ERROR_CODE) && result.getInt(DVCQGIntegrationActionTerm.ERROR_CODE) == 0) {
					return result.getString(DVCQGIntegrationActionTerm.SESSION);
				}

				return StringPool.BLANK;

			}

		} catch (Exception e) {
			_log.error(e);
			return StringPool.BLANK;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	private String getAccessToken(ServerConfig serverConfig) {

		//HttpURLConnection conn = null;

		try {
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			return getAccessToken(config);

		} catch (Exception e) {
			_log.error(e);
			return StringPool.BLANK;
		}
//		finally {
//			if (conn != null) {
//				conn.disconnect();
//			}
//		}
	}

	@Override
	public String getAccessToken(User user, ServiceContext serviceContext) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGIntegrationActionTerm.DVCQG_INTEGRATION);
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);

			return getAccessToken(serverConfig);

		}
		return StringPool.BLANK;

	}

	private JSONArray getAnswer(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject qajson = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> syncAnswer: " + serverConfigs + "|" + serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				qajson = getSharingData(serverConfig, data);
				if (qajson.has("error_code") && qajson.getInt("error_code") == 0 && qajson.has("result")
						&& qajson.getJSONArray("result").length() > 0) {
					JSONArray qalst = qajson.getJSONArray("result");

					return qalst;

				}
			} catch (Exception e) {
				_log.error(e);
			}
		}

		return null;
	}
	
	public String getMappingStatus(long groupId, Dossier dossier) {
		if (Validator.isNotNull(dossier.getDossierSubStatus())) {

			DossierStatusMapping statusMapping = DossierStatusMappingLocalServiceUtil.fetchByF_GID_SUBSC(groupId,
					dossier.getDossierSubStatus());

			if (statusMapping != null) {

				return statusMapping.getStatusCodeDVCQG();
			}

		}

		List<DossierStatusMapping> statusMappings = DossierStatusMappingLocalServiceUtil.findByF_GID_SC(groupId,
				dossier.getDossierStatus());

		if (statusMappings != null && !statusMappings.isEmpty()) {

			return statusMappings.get(0).getStatusCodeDVCQG();
		}

		return StringPool.BLANK;

	}

	public HashMap<String, String> getServiceInfoDVCQGMap(User user, ServiceContext serviceContext) {
		HashMap<String, String> serviceInfoDVCQGMap = new HashMap<String, String>();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject data = JSONFactoryUtil.createJSONObject();
			data.put("service", "LayDanhSachTTHC");
			JSONObject responseData = getSharingDictCollection(serverConfig, data);
			if (responseData != null && responseData.has("result")) {
				JSONArray result = responseData.getJSONArray("result");
				JSONObject item;
				for (int i = 0; i < result.length(); i++) {
					item = result.getJSONObject(i);
					serviceInfoDVCQGMap.put(item.getString("MATTHC"), item.getString("TENTTHC"));
				}
			}
		}
		return serviceInfoDVCQGMap;
	}

	public JSONArray getServiceInfoSimilarity(long groupId, String serviceCode, String serviceName,
			HashMap<String, String> map) {
		ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId,
				serviceCode);
		String _tmpServiceName = serviceName.replaceAll("[.,-_:;\\\"\\']", "").toLowerCase();
		JSONArray result = JSONFactoryUtil.createJSONArray();
		if (serviceInfoMapping != null) {
			JSONObject item = JSONFactoryUtil.createJSONObject();
			String serviceNameDVCQG = map.get(serviceInfoMapping.getServiceCodeDVCQG());
			item.put("serviceCodeDVCQG", serviceInfoMapping.getServiceCodeDVCQG());
			item.put("serviceInfoMappingId", serviceInfoMapping.getServiceInfoMappingId());
			item.put("serviceNameDVCQG", serviceNameDVCQG);
			item.put("similarityPercent", 100);
			item.put("mapped", true);
			result.put(item);

			return result;
		}

		Map<CharSequence, Integer> vectorA = Arrays.stream(_tmpServiceName.split(" "))
				.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

		SortedMap<Double, JSONObject> sortedMap = new TreeMap<Double, JSONObject>(Collections.reverseOrder());

		CosineSimilarity documentsSimilarity = new CosineSimilarity();

		DecimalFormat df = new DecimalFormat();

		df.setMaximumFractionDigits(2);

		if (_mapChars != null && !_mapChars.isEmpty()) {
			_log.info("----------------------------->>>>>getServiceInfoSimilarity: get data from store: "
					+ _mapChars.size());
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String name = entry.getValue().replaceAll("[.,-_:;\\\"\\']", "").toLowerCase();
				//_log.info(key + "|" + name);
				Map<CharSequence, Integer> vectorB = null;
				if (_mapChars.containsKey(key)) {
					vectorB = _mapChars.get(key);
				} else {
					vectorB = Arrays.stream(name.split(" "))
							.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

					_mapChars.put(key, vectorB);
				}

				Double weightIndex = documentsSimilarity.cosineSimilarity(vectorA, vectorB);
				//_log.info(weightIndex);
				JSONObject item = null;
				if (_mapItems != null && _mapItems.containsKey(key)) {
					item = _mapItems.get(key);
					item.put("similarityPercent", df.format(weightIndex * 100));
				} else {
					item = JSONFactoryUtil.createJSONObject();
					item.put("serviceCodeDVCQG", key);
					item.put("serviceNameDVCQG", entry.getValue());
					item.put("mapped", false);
					item.put("serviceInfoMappingId", 0);
					_mapItems.put(key, item);
				}

				if (weightIndex >= 0.4) {

					item.put("similarityPercent", df.format(weightIndex * 100));

					if (weightIndex >= 1) {

						result.put(item);

						return result;
					}
					sortedMap.put(weightIndex, item);

				}
			}
		} else {
			_log.info("----------------------------->>>>>getServiceInfoSimilarity: get new data");
			_mapChars = new HashMap<String, Map<CharSequence, Integer>>();
			_mapItems = new HashMap<String, JSONObject>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String name = entry.getValue().replaceAll("[.,-_:;\\\"\\']", "").toLowerCase();

				Map<CharSequence, Integer> vectorB = Arrays.stream(name.split(" "))
						.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

				_mapChars.put(key, vectorB);

				Double weightIndex = documentsSimilarity.cosineSimilarity(vectorA, vectorB);

				JSONObject item = JSONFactoryUtil.createJSONObject();
				item.put("serviceCodeDVCQG", key);
				item.put("serviceNameDVCQG", entry.getValue());
				item.put("serviceInfoMappingId", 0);
				item.put("mapped", false);
				_mapItems.put(key, item);

				if (weightIndex >= 0.4) {

					item.put("similarityPercent", df.format(weightIndex * 100));

					if (weightIndex >= 1) {
						result.put(item);

						return result;
					}
					sortedMap.put(weightIndex, item);

				}
			}
		}

		if (!sortedMap.isEmpty()) {
			for (Map.Entry<Double, JSONObject> entry : sortedMap.entrySet()) {
				result.put(entry.getValue());
			}
		}

		return result;
	}
	
	private JSONObject getSharingData(JSONObject config, JSONObject data, String accessToken) {

		HttpURLConnection conn = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			String adapter_url = config.getString(DVCQGIntegrationActionTerm.ADAPTER_URL);
			String integration_endpoint = config.getString(DVCQGIntegrationActionTerm.INTEGRATION_ENDPOINT);
			String madonvi = config.getString(DVCQGIntegrationActionTerm.MADONVI);
			String dstcode = config.getString(DVCQGIntegrationActionTerm.DSTCODE);
			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put(DVCQGIntegrationActionTerm.SESSION, accessToken);
			body.put(DVCQGIntegrationActionTerm.MADONVI, madonvi);
			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				body.put(key, data.get(key));
			}

			String endpoint = adapter_url + integration_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.CHARSET, DVCQGIntegrationActionTerm.UTF_8_LOWER);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes(DVCQGIntegrationActionTerm.UTF_8);
			int postDataLength = postData.length;
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(postDataLength));
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				//System.out.println("response: " + sb.toString());

				result = JSONFactoryUtil.createJSONObject(sb.toString());

				return result;

			}

		} catch (Exception e) {
			_log.error(e);
			return result;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	private JSONObject getSharingData(ServerConfig serverConfig, JSONObject data) {

		HttpURLConnection conn = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String adapter_url = config.getString(DVCQGIntegrationActionTerm.ADAPTER_URL);
			String integration_endpoint = config.getString(DVCQGIntegrationActionTerm.INTEGRATION_ENDPOINT);
			String madonvi = config.getString(DVCQGIntegrationActionTerm.MADONVI);
			String accessToken = getAccessToken(config);
			String dstcode = config.getString(DVCQGIntegrationActionTerm.DSTCODE);
			if (Validator.isNull(accessToken) || Validator.isNull(data)) {
				return result;
			}

			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put(DVCQGIntegrationActionTerm.SESSION, accessToken);
			body.put(DVCQGIntegrationActionTerm.MADONVI, madonvi);
			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				body.put(key, data.get(key));
			}

			String endpoint = adapter_url + integration_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.CHARSET, DVCQGIntegrationActionTerm.UTF_8_LOWER);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes(DVCQGIntegrationActionTerm.UTF_8);
			int postDataLength = postData.length;
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(postDataLength));
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				//System.out.println("response: " + sb.toString());

				result = JSONFactoryUtil.createJSONObject(sb.toString());

				return result;

			}

		} catch (Exception e) {
			_log.error(e);
			return result;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	@Override
	public JSONObject getSharingData(User user, ServiceContext serviceContext, JSONObject data) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGIntegrationActionTerm.DVCQG_INTEGRATION);
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			return getSharingData(serverConfig, data);
		}
		return result;
	}

	private JSONObject getSharingDictCollection(ServerConfig serverConfig, JSONObject data) {

		HttpURLConnection conn = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String adapter_url = config.getString(DVCQGIntegrationActionTerm.ADAPTER_URL);
			String share_endpoint = config.getString(DVCQGIntegrationActionTerm.SHARE_ENDPOINT);
			String madonvi = config.getString(DVCQGIntegrationActionTerm.MADONVI);
			String dstcode = config.getString(DVCQGIntegrationActionTerm.DSTCODE);
			String accessToken = getAccessToken(config);
			if (Validator.isNull(accessToken) || Validator.isNull(data)) {
				return result;
			}

			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put(DVCQGIntegrationActionTerm.SESSION, accessToken);
			body.put(DVCQGIntegrationActionTerm.MADONVI, madonvi);
			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				body.put(key, data.get(key));
			}

			String endpoint = adapter_url + share_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.CHARSET, DVCQGIntegrationActionTerm.UTF_8_LOWER);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes(DVCQGIntegrationActionTerm.UTF_8);
			int postDataLength = postData.length;
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(postDataLength));
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				//System.out.println("response: " + sb.toString());

				result = JSONFactoryUtil.createJSONObject(sb.toString());

				return result;

			}

		} catch (Exception e) {
			_log.error(e);
			return result;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	@Override
	public JSONObject getSharingDictCollection(User user, ServiceContext serviceContext, JSONObject data) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGIntegrationActionTerm.DVCQG_INTEGRATION);
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			return getSharingDictCollection(serverConfig, data);
		}
		return result;
	}

	private boolean hasSyncDossier(String dossierNo, JSONObject config, String accessToken) {
		JSONObject searchData = searchDossier(dossierNo, config, accessToken);
		if (searchData != null && searchData.has(DVCQGIntegrationActionTerm.RESULT)) {
			JSONArray result = searchData.getJSONArray(DVCQGIntegrationActionTerm.RESULT);
			if (result != null && result.length() > 0) {
				return true;
			}

		}
		return false;
	}

	private JSONObject searchDossier(String dossierNo, JSONObject config, String accessToken) {

		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put(DVCQGIntegrationActionTerm.SERVICE, DVCQGIntegrationActionTerm.TRACUUHOSO);
		body.put(DVCQGIntegrationActionTerm.MAHOSO, dossierNo);

		return getSharingData(config, body, accessToken);
	}

	private JSONObject syncData(ServerConfig serverConfig, JSONObject data) {

		HttpURLConnection conn = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String adapter_url = config.getString(DVCQGIntegrationActionTerm.ADAPTER_URL);
			String integration_endpoint = config.getString(DVCQGIntegrationActionTerm.INTEGRATION_ENDPOINT);
			String madonvi = config.getString(DVCQGIntegrationActionTerm.MADONVI);
			String accessToken = getAccessToken(config);
			String dstcode = config.getString(DVCQGIntegrationActionTerm.DSTCODE);
			if (Validator.isNull(accessToken) || Validator.isNull(data)) {
				return result;
			}

			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put(DVCQGIntegrationActionTerm.SESSION, accessToken);
			body.put(DVCQGIntegrationActionTerm.MADONVI, madonvi);
			Iterator<String> keys = data.keys();

			while (keys.hasNext()) {
				String key = keys.next();

				body.put(key, data.get(key));
			}

			String endpoint = adapter_url + integration_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGIntegrationActionTerm.APPLICATION_JSON);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.CHARSET, DVCQGIntegrationActionTerm.UTF_8_LOWER);
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes(DVCQGIntegrationActionTerm.UTF_8);
			int postDataLength = postData.length;
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(postDataLength));
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				//System.out.println("response: " + sb.toString());

				result = JSONFactoryUtil.createJSONObject(sb.toString());

				return result;

			}

		} catch (Exception e) {
			_log.error(e);
			return result;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	@Override
	public JSONObject syncDossier(User user, long groupId, ServiceContext serviceContext, String strDossierId,
			String isUpdating) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGIntegrationActionTerm.DVCQG_INTEGRATION);
		if (serverConfigs != null && !serverConfigs.isEmpty() && Validator.isNotNull(strDossierId)) {
			ServerConfig serverConfig = serverConfigs.get(0);
			long[] dossierIds = StringUtil.split(strDossierId, 0L);
			JSONArray synsObjects = JSONFactoryUtil.createJSONArray();
			for (long dossierId : dossierIds) {
				if (dossierId > 0) {
					Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
					if (dossier == null) {
						continue;
					}
					JSONObject synsObject = createSyncDossierBodyRequest(groupId, dossier, serverConfig);
					synsObjects.put(synsObject);
				}
			}

			JSONObject body = JSONFactoryUtil.createJSONObject();
			body.put(DVCQGIntegrationActionTerm.ISUPDATING, isUpdating);
			body.put(DVCQGIntegrationActionTerm.SERVICE, DVCQGIntegrationActionTerm.DONGBOHOSOMC);
			body.put(DVCQGIntegrationActionTerm.DATA, synsObjects);

			return syncData(serverConfig, body);
		}
		return result;
	}

	public JSONObject syncDossierAndDossierStatus(long groupId, Dossier dossier) throws JSONException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGIntegrationActionTerm.DVCQG_INTEGRATION);
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String accessToken = getAccessToken(config);

			JSONArray synsObjects = JSONFactoryUtil.createJSONArray();
			if (Validator.isNotNull(accessToken)) {
				boolean hasSync = hasSyncDossier(dossier.getDossierNo(), config, accessToken);
				JSONObject synsObject = createSyncDossierBodyRequest(groupId, dossier, config, accessToken);
				synsObjects.put(synsObject);
				JSONObject body = JSONFactoryUtil.createJSONObject();

				if (hasSync) {
					body.put(DVCQGIntegrationActionTerm.ISUPDATING, DVCQGIntegrationActionTerm.TRUE_KEY);
				} else {
					body.put(DVCQGIntegrationActionTerm.ISUPDATING, DVCQGIntegrationActionTerm.FALSE_KEY);
				}
				body.put(DVCQGIntegrationActionTerm.DATA, synsObjects);
				body.put(DVCQGIntegrationActionTerm.SERVICE, DVCQGIntegrationActionTerm.DONGBOHOSOMC);
				result = syncData(serverConfig, body);
				if (result.has(DVCQGIntegrationActionTerm.ERROR_CODE) && result.getString(DVCQGIntegrationActionTerm.ERROR_CODE).equals(DVCQGIntegrationActionTerm.ERR_CODE_00_KEY)) {
					body = JSONFactoryUtil.createJSONObject();
					synsObjects = JSONFactoryUtil.createJSONArray();
					JSONObject _tmp = createSyncDossierStatusBodyRequest(groupId, dossier);
					synsObjects.put(_tmp);
					body.put(DVCQGIntegrationActionTerm.SERVICE, DVCQGIntegrationActionTerm.CAPNHATTIENDOHOSOMC);
					body.put(DVCQGIntegrationActionTerm.DATA, synsObjects);
					result = syncData(serverConfig, body);
				}
			}

		}
		return result;
	}

	@Override
	public JSONObject syncDossierStatus(User user, long groupId, ServiceContext serviceContext, String strDossierId) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGIntegrationActionTerm.DVCQG_INTEGRATION);
		if (serverConfigs != null && !serverConfigs.isEmpty() && Validator.isNotNull(strDossierId)) {
			ServerConfig serverConfig = serverConfigs.get(0);
			long[] dossierIds = StringUtil.split(strDossierId, 0L);
			JSONArray synsObjects = JSONFactoryUtil.createJSONArray();
			for (long dossierId : dossierIds) {
				if (dossierId > 0) {
					Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
					if (dossier == null) {
						continue;
					}
					JSONObject synsObject = createSyncDossierStatusBodyRequest(groupId, dossier);
					synsObjects.put(synsObject);
				}
			}
			JSONObject body = JSONFactoryUtil.createJSONObject();
			body.put(DVCQGIntegrationActionTerm.SERVICE, DVCQGIntegrationActionTerm.CAPNHATTIENDOHOSOMC);
			body.put(DVCQGIntegrationActionTerm.DATA, synsObjects);

			return syncData(serverConfig, body);
		}
		return result;
	}

	@Override
	public JSONObject mappingServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCode,
			String serviceCodeDVCQG) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (Validator.isNotNull(serviceCode) && Validator.isNotNull(serviceCodeDVCQG)) {
			try {
				ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.addServiceInfoMapping(
						groupId, serviceContext.getCompanyId(), user.getUserId(), serviceCode, serviceCodeDVCQG);
				result.put(DVCQGIntegrationActionTerm.id, serviceInfoMapping.getServiceInfoMappingId());
				result.put(DVCQGIntegrationActionTerm.serviceCode, serviceCode);
				result.put(DVCQGIntegrationActionTerm.SERVICECODEDVCQG, serviceCodeDVCQG);
				result.put(DVCQGIntegrationActionTerm.SERVICEINFOMAPPINGID, serviceInfoMapping.getServiceInfoMappingId());
				result.put(Field.GROUP_ID, serviceInfoMapping.getGroupId());
				result.put(Field.USER_ID, serviceInfoMapping.getUserId());
			} catch (Exception e) {
				_log.error(e);
			}
		}

		return result;
	}

	@Override
	public boolean removeMappingServiceInfo(User user, long groupId, ServiceContext serviceContext,
			long id) {

		try {
			ServiceInfoMappingLocalServiceUtil.deleteServiceInfoMapping(id);
			return true;
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
	}

	@Override
	public JSONObject syncServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCodes) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> syncServiceInfo: " + serverConfigs + "|" + serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String accessToken = getAccessToken(config);

				JSONObject body = JSONFactoryUtil.createJSONObject();

				body.put("service", "LayThuTuc");

				DictCollection collection = DictCollectionLocalServiceUtil
						.fetchByF_dictCollectionCode(DataMGTConstants.SERVICE_ADMINISTRATION, groupId);

				if (Validator.isNotNull(serviceCodes)) {
					String[] arrayServiceCode = StringUtil.split(serviceCodes);

					for (String serviceCode : arrayServiceCode) {

						ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);

						if (serviceInfo == null) {
							continue;
						}

						syncServiceInfo(user, result, config, serviceCodes, serviceInfo, collection, body, accessToken,
								serviceContext);
					}
				} else {
					List<ServiceInfo> serviceInfos = ServiceInfoLocalServiceUtil.getServiceInfosByGroupId(groupId);
					if (serviceInfos != null) {
						for (ServiceInfo serviceInfo : serviceInfos) {
							syncServiceInfo(user, result, config, serviceCodes, serviceInfo, collection, body,
									accessToken, serviceContext);
						}
					}
				}

			} catch (Exception e) {
				_log.error(e);

			}
		}

		return result;
	}
	
	private JSONObject syncServiceInfo(User user, JSONObject result, JSONObject config, String serviceCode,
			ServiceInfo serviceInfo, DictCollection collection, JSONObject body, String accessToken,
			ServiceContext serviceContext) throws Exception {

		body.put("maTTHC", serviceInfo.getServiceCode());

		JSONObject serviceInfoDVCQG = getSharingData(config, body, accessToken);

		//String _oldServiceCode = serviceInfo.getServiceCode();

		if (serviceInfoDVCQG != null && serviceInfoDVCQG.has("result")) {
			JSONArray results = serviceInfoDVCQG.getJSONArray("result");

			if (results.length() > 0) {
				JSONObject _tmp = results.getJSONObject(0);
				StringBuffer sb = null;

				//TENTTHC
				String tentthc = _tmp.getString("TENTTHC");
				serviceInfo.setServiceName(tentthc);

				//MATTHC
				String matthc = _tmp.getString("MATTHC");
				serviceInfo.setServiceCode(matthc);

				//TRINHTUTHUCHIEN
				sb = new StringBuffer();
				if (_tmp.has("TRINHTUTHUCHIEN")) {
					JSONArray trinhtuthuchien_arr = _tmp.getJSONArray("TRINHTUTHUCHIEN");
					if (trinhtuthuchien_arr != null) {
						for (int i = 0; i < trinhtuthuchien_arr.length(); i++) {
							JSONObject trinhtuthuchien_obj = trinhtuthuchien_arr.getJSONObject(i);
							String truonghop = trinhtuthuchien_obj.getString("TRUONGHOP");
							sb.append(truonghop + "<br>");
							JSONArray trinhtu_arr = trinhtuthuchien_obj.getJSONArray("TRINHTU");
							if (trinhtu_arr != null) {
								for (int j = 0; j < trinhtu_arr.length(); j++) {
									String tentrinhtu = trinhtu_arr.getJSONObject(j).getString("TENTRINHTU");
									sb.append(tentrinhtu + "<br>");
								}
							}
						}

					}
				}
				serviceInfo.setProcessText(sb.toString());

				//LINHVUCTHUCHIEN
				String malinhvuc = StringPool.BLANK;
				String tenlinhvuc = StringPool.BLANK;
				if (_tmp.has("LINHVUCTHUCHIEN")) {
					JSONArray linhvucthuchien_arr = _tmp.getJSONArray("LINHVUCTHUCHIEN");
					if (linhvucthuchien_arr != null && linhvucthuchien_arr.length() > 0) {
						JSONObject linhvucthuchien_obj = linhvucthuchien_arr.getJSONObject(0);
						malinhvuc = linhvucthuchien_obj.getString("MALINHVUC");
						tenlinhvuc = linhvucthuchien_obj.getString("TENLINHVUC");
					}
				}

				serviceInfo.setDomainCode(malinhvuc);
				serviceInfo.setDomainName(tenlinhvuc);

				//MACOQUANCONGBO
				String macoquancongbo = _tmp.getString("MACOQUANCONGBO");
				String tencoquancongbo = StringPool.BLANK;

				if (Validator.isNotNull(macoquancongbo) && collection != null) {
					DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(macoquancongbo,
							collection.getDictCollectionId(), serviceContext.getScopeGroupId());
					if (dictItem != null) {
						tencoquancongbo = dictItem.getItemName();
					}
				}
				serviceInfo.setAdministrationName(tencoquancongbo);
				serviceInfo.setAdministrationCode(macoquancongbo);

				//CACHTHUCTHUCHIEN
				sb = new StringBuffer();
				String durationText = StringPool.BLANK;
				String feeText = StringPool.BLANK;
				if (_tmp.has("CACHTHUCTHUCHIEN")) {
					JSONArray cachthucthuchien_arr = _tmp.getJSONArray("CACHTHUCTHUCHIEN");
					if (cachthucthuchien_arr != null) {
						for (int i = 0; i < cachthucthuchien_arr.length(); i++) {
							JSONObject cachthucthuchien_obj = cachthucthuchien_arr.getJSONObject(i);
							int kenh = cachthucthuchien_obj.getInt("KENH");
							String nhankenh = "Trực tiếp";
							if (kenh == 2) {
								nhankenh = "Trực tuyến";
							} else if (kenh == 3) {
								nhankenh = "Nộp qua bưu chính công ích";
							}
							sb.append(nhankenh + ":<br>");

							JSONArray thoigian_arr = cachthucthuchien_obj.getJSONArray("THOIGIAN");

							if (thoigian_arr != null) {
								for (int j = 0; j < thoigian_arr.length(); j++) {
									JSONObject thoigian_obj = thoigian_arr.getJSONObject(j);
									int thoigiangiaiquyet = thoigian_obj.getInt("THOIGIANGIAIQUYET");
									String donvitinh = thoigian_obj.getString("DONVITINH");
									String mota = thoigian_obj.getString("MOTA");
									sb.append("- Thời gian giải quyết: " + thoigiangiaiquyet + " " + donvitinh
											+ (Validator.isNotNull(mota) ? "(" + mota + ")" : StringPool.BLANK)
											+ "<br>");
									durationText += nhankenh + ":" + "- Thời gian giải quyết: " + thoigiangiaiquyet
											+ " " + donvitinh + "<br>";
									JSONArray philephi_arr = thoigian_obj.getJSONArray("PHILEPHI");
									if (philephi_arr != null && philephi_arr.length() > 0) {
										String maphilephi = philephi_arr.getJSONObject(0).getString("MAPHILEPHI");
										double sotien = philephi_arr.getJSONObject(0).getDouble("SOTIEN");
										String donvi = philephi_arr.getJSONObject(0).getString("DONVI");
										String lephimota = philephi_arr.getJSONObject(0).getString("MOTA");
										feeText += nhankenh + ":" + maphilephi + ", " + sotien + " " + donvi
												+ (Validator.isNotNull(lephimota) ? "(" + lephimota + ")"
														: StringPool.BLANK)
												+ "<br>";
									}
								}
							}
						}
					}
				}
				serviceInfo.setMethodText(sb.toString());
				//durationText
				serviceInfo.setDurationText(durationText);
				//feeText
				serviceInfo.setFeeText(feeText);

				//YEUCAU
				String yeucau = StringPool.BLANK;
				if (_tmp.has("YEUCAU")) {
					yeucau = _tmp.getString("YEUCAU");
				}
				serviceInfo.setConditionText(yeucau);

				//MOTADOITUONGTHUCHIEN
				String motadoituongthuchien = StringPool.BLANK;
				if (_tmp.has("MOTADOITUONGTHUCHIEN")) {
					motadoituongthuchien = _tmp.getString("MOTADOITUONGTHUCHIEN");
				}
				serviceInfo.setApplicantText(motadoituongthuchien);

				//KETQUATHUCHIEN
				sb = new StringBuffer();
				if (_tmp.has("KETQUATHUCHIEN")) {
					JSONArray ketquathuchien_arr = _tmp.getJSONArray("KETQUATHUCHIEN");
					if (ketquathuchien_arr != null) {
						for (int i = 0; i < ketquathuchien_arr.length(); i++) {
							JSONObject ketquathuchien_obj = ketquathuchien_arr.getJSONObject(i);
							String maketqua = ketquathuchien_obj.getString("MAKETQUA");
							String tenketqua = ketquathuchien_obj.getString("TENKETQUA");
							sb.append("- Mã kết quả:" + maketqua + "<br>");
							sb.append("- Kết quả:" + tenketqua + "<br>");
						}

					}

				}
				serviceInfo.setResultText(sb.toString());

				//CANCUPHAPLY
				sb = new StringBuffer();
				if (_tmp.has("CANCUPHAPLY")) {
					JSONArray cancuphaply_arr = _tmp.getJSONArray("CANCUPHAPLY");
					if (cancuphaply_arr != null) {
						for (int i = 0; i < cancuphaply_arr.length(); i++) {
							JSONObject cancuphaply_obj = cancuphaply_arr.getJSONObject(i);
							String sovanban = cancuphaply_obj.getString("SOVANBAN");
							String tenvanban = cancuphaply_obj.getString("TENVANBAN");
							sb.append("- Số văn bản: " + sovanban + "<br>");
							sb.append("- Tên văn bản: " + tenvanban + "<br>");
						}

					}

				}
				serviceInfo.setRegularText(sb.toString());

				//TRANGTHAI
				boolean public_ = true;
				if (_tmp.has("TRANGTHAI")) {
					int trangthai = _tmp.getInt("TRANGTHAI");
					if (trangthai != 1) {
						public_ = false;
					}
				}
				serviceInfo.setPublic_(public_);

				//COQUANTHUCHIEN
				sb = new StringBuffer();
				if (_tmp.has("COQUANTHUCHIEN")) {
					JSONArray coquanthuchien_arr = _tmp.getJSONArray("COQUANTHUCHIEN");
					if (coquanthuchien_arr != null) {
						for (int i = 0; i < coquanthuchien_arr.length(); i++) {
							JSONObject coquanthuchien_obj = coquanthuchien_arr.getJSONObject(i);
							String tendonvi = coquanthuchien_obj.getString("TENDONVI");
							String madonvi = coquanthuchien_obj.getString("MADONVI");
							sb.append("- Tên đơn vị: " + tendonvi + "<br>");
							sb.append("- Mã đơn vị: " + madonvi + "<br>");
						}
					}
				}

				serviceInfo.setGovAgencyText(sb.toString());

				ServiceInfoLocalServiceUtil.updateServiceInfo(serviceInfo);
				/*ServiceInfoMappingLocalServiceUtil.addServiceInfoMapping(serviceContext.getScopeGroupId(),
						serviceContext.getCompanyId(), user.getUserId(), serviceCode, matthc);*/

				//THANHPHANHOSO
				List<ServiceFileTemplate> serviceFileTemplates = ServiceFileTemplateLocalServiceUtil
						.getByServiceInfoId(serviceInfo.getServiceInfoId());
				if (serviceFileTemplates != null) {
					for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplates) {
						ServiceFileTemplateLocalServiceUtil.removeServiceFileTemplate(serviceInfo.getServiceInfoId(),
								serviceFileTemplate.getFileTemplateNo());
					}
				}

				if (_tmp.has("THANHPHANHOSO")) {
					JSONArray thanhphanhoso_arr = _tmp.getJSONArray("THANHPHANHOSO");
					ServiceInfoActions actions = new ServiceInfoActionsImpl();
					if (thanhphanhoso_arr != null) {
						for (int i = 0; i < thanhphanhoso_arr.length(); i++) {
							JSONObject thanhphanhoso_obj = thanhphanhoso_arr.getJSONObject(i);
							JSONArray giayto_arr = thanhphanhoso_obj.getJSONArray("GIAYTO");
							if (giayto_arr != null) {
								for (int j = 0; j < giayto_arr.length(); j++) {
									JSONObject giayto_obj = giayto_arr.getJSONObject(j);
									String magiayto = giayto_obj.getString("MAGIAYTO");
									String tenmaudon = giayto_obj.getString("TENMAUDON");
									String link = giayto_obj.getString("URL");
									String tengiayto = giayto_obj.getString("TENGIAYTO");

									if (Validator.isNotNull(link) && Validator.isNotNull(magiayto)) {
										InputStream in = null;
										URLConnection connection = null;
										try {
											URL url = new URL(link);
											connection = url.openConnection();
											in = connection.getInputStream();
											String mimeType = URLConnection.guessContentTypeFromStream(in);
											//String mimeType = MimeTypesUtil.getContentType(tenmaudon);
											actions.addServiceFileTemplate(user.getUserId(),
													serviceContext.getScopeGroupId(), serviceInfo.getServiceInfoId(),
													magiayto, tengiayto, tenmaudon, in, mimeType,
													connection.getContentLength(), serviceContext);

										} catch (Exception e) {
											_log.error(e);
										} finally {
											if (in != null) {
												in.close();
											}
										}

									}

								}
							}
						}
					}
				}
				result.put(serviceCode, true);
			} else {
				result.put(serviceCode, false);
			}

		} else {
			result.put(serviceCode, false);
		}

		return result;
	}
	
	@Override
	public JSONObject getSharingQA(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGIntegrationActionTerm.DVCQG_INTEGRATION);
//		JSONObject result = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> syncServiceInfo: " + serverConfigs +  StringPool.PIPE + serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
//			try {
//				ServerConfig serverConfig = serverConfigs.get(0);
				//JSONObject result = getSharingData(serverConfig, data);
//			} catch (Exception e) {
//				_log.error(e);
//			}
		}
		
		return null;
	}

//	private static HashMap<String, Map<CharSequence, Integer>> _mapChars = null;
//	private static HashMap<String, JSONObject> _mapItems = null;


	public void syncFAQToDVCQG() {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		//JSONObject result = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> syncFAQToDVCQG: " + serverConfigs + "|" + serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			try {
				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				System.out.println(config.getLong("groupId"));
				List<Question> questions = QuestionLocalServiceUtil.findByG_P_SYNC(config.getLong("groupId"), 1, 0);
				_log.info("------------->>>>>>>>>>>>>>>>>>>>> questions " + questions.size());
				if (questions != null) {
					JSONArray data = JSONFactoryUtil.createJSONArray();
					JSONObject body = JSONFactoryUtil.createJSONObject();
					body.put("service", "DongBoHoiDap");
					JSONObject item = null;
					for (Question question : questions) {
						item = JSONFactoryUtil.createJSONObject();
						item.put("HOIDAPID", question.getQuestionId());
						item.put("MaLinhVuc", question.getDomainCode());
						item.put("NOIDUNG", question.getContent());
						item.put("MADONVI", question.getGovAgencyCode());
						item.put("TENDONVI", question.getGovAgencyName());
						//Answer answer = AnswerLocalServiceUtil.fi
						//item.put("TRALOIID", question.getGovAgencyName());
						//item.put("NOIDUNGTRALOI", question.getGovAgencyName());
						data.put(item);
					}

					body.put("data", data);
					JSONObject result = syncData(serverConfig, body);

					if (result.has("error_code") && result.getInt("error_code") == 0) {
						for (int i = 0; i < data.length(); i++) {
							JSONObject _tmp = data.getJSONObject(i);
							long questionId = _tmp.getLong("HOIDAPID");
							Question question = QuestionLocalServiceUtil.fetchQuestion(questionId);
							question.setSynced(1);
							QuestionLocalServiceUtil.updateQuestion(question);
						}
					}
				}

				List<Answer> answers = AnswerLocalServiceUtil.findByG_P_SYNC(config.getLong("groupId"), 1, 0);
				if (answers != null) {
					JSONArray data = JSONFactoryUtil.createJSONArray();
					JSONObject body = JSONFactoryUtil.createJSONObject();
					body.put("service", "DongBoKetQuaHoiDapGuiTuDVCQG");
					JSONObject item = null;
					for (Answer answer : answers) {
						Question question = QuestionLocalServiceUtil.fetchQuestion(answer.getQuestionId());
						if (question != null && question.getPublish() == 1
								&& Validator.isNotNull(question.getClassName())
								&& question.getClassName().equalsIgnoreCase("dvcqg_question")) {
							item = JSONFactoryUtil.createJSONObject();
							item.put("HOIDAPID", question.getClassPK());
							item.put("MADONVI", question.getGovAgencyCode());
							item.put("NGUOITRALOI", answer.getUserName());
							item.put("CHUCVUNGUOITRALOI", "cb");
							item.put("NGAYTRALOI", APIDateTimeUtils.convertDateToString(answer.getCreateDate(),
									APIDateTimeUtils._NORMAL_DATE));
							item.put("NOIDUNGTRALOI", answer.getContent());
							data.put(item);
						}

					}
					body.put("data", data);
					JSONObject result = syncData(serverConfig, body);
					/*if (result.has("error_code") && result.getInt("error_code") == 0) {
						for (int i = 0; i < data.length(); i++) {
							JSONObject _tmp = data.getJSONObject(i);
							long questionId = _tmp.getLong("HOIDAPID");
							Question question = QuestionLocalServiceUtil.fetchQuestion(questionId);
							question.setSynced(1);
							QuestionLocalServiceUtil.updateQuestion(question);
						}
					}*/
				}
			} catch (Exception e) {
				_log.error(e);
			}
		}

	}	
}
