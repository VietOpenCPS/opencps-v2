package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemMapping;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.service.DictItemMappingLocalServiceUtil;
import org.opencps.dossiermgt.action.DVCQGIntegrationAction;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierStatusMapping;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.model.impl.ServiceInfoImpl;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierStatusMappingLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil;
import org.opencps.usermgt.model.Answer;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.AnswerLocalServiceUtil;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.QuestionLocalServiceUtil;

/**
 * @author trungnt
 *
 */
public class DVCQGIntegrationActionImpl implements DVCQGIntegrationAction {

	private static HashMap<String, Map<CharSequence, Integer>> _serviceInfoMapChars = new HashMap<String, Map<CharSequence, Integer>>();;

	private static HashMap<String, JSONObject> _serviceInfoMapItems = new HashMap<String, JSONObject>();

	private static HashMap<String, Map<CharSequence, Integer>> _dictItemMapChars = new HashMap<String, Map<CharSequence, Integer>>();;

	private static HashMap<String, JSONObject> _dictItemMapItems = new HashMap<String, JSONObject>();

	private static JSONArray _serviceInfoDVCQG = JSONFactoryUtil.createJSONArray();

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

		object.put("MaHoSo", dossier.getDossierNo());
		object.put("MaTTHC", _mServiceCode);
		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put("service", "LayThuTuc");
		body.put("maTTHC", _mServiceCode);

		JSONObject serviceInfo = getSharingData(config, body, accessToken);
		_log.info("-------------->>>>results:  " + serviceInfo.getJSONArray("result"));
		//get TenTTHC, MaLinhVuc, TenLinhVuc
		String TenTTHC = StringPool.BLANK;
		String MaLinhVuc = StringPool.BLANK;
		String TenLinhVuc = StringPool.BLANK;
		if (serviceInfo != null && serviceInfo.has("result")) {
			JSONArray results = serviceInfo.getJSONArray("result");
			if (results.length() > 0) {
				JSONObject _tmp = results.getJSONObject(0);
				TenTTHC = _tmp.getString("TENTTHC");
				MaLinhVuc = _tmp.getJSONArray("LINHVUCTHUCHIEN").getJSONObject(0).getString("MALINHVUC");
				TenLinhVuc = _tmp.getJSONArray("LINHVUCTHUCHIEN").getJSONObject(0).getString("TENLINHVUC");
			}
		}
		object.put("TenTTHC", TenTTHC);
		object.put("MaLinhVuc", MaLinhVuc);
		object.put("TenLinhVuc", TenLinhVuc);
		//object.put("SoBienNhan", ""); //ko bb
		object.put("ChuHoSo", dossier.getContactName()); //ko bb
		int LoaiDoiTuong = 1;
		if (Validator.isNotNull(dossier.getApplicantIdType())) {
			if (dossier.getApplicantIdType().equals("business")) {
				LoaiDoiTuong = 2;
			}
		}
		Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, dossier.getApplicantIdNo());
		String madoituong = StringPool.BLANK;
		if(applicant != null && "dvcqg".contentEquals(applicant.getMappingClassName())) {
			madoituong = applicant.getMappingClassPK();
		}
		object.put("LoaiDoiTuong", String.valueOf(LoaiDoiTuong));
		object.put("MaDoiTuong", madoituong); //ko bb
		object.put("ThongTinKhac", ""); //ko bb
		object.put("Email", dossier.getContactEmail());
		object.put("Fax", dossier.getContactTelNo()); //ko bb
		object.put("SoDienThoai", dossier.getContactTelNo());
		object.put("TrichYeuHoSo", dossier.getDossierNote());
		object.put("NgayTiepNhan", convertDate2String(dossier.getReceiveDate()));
		object.put("NgayHenTra", convertDate2String(dossier.getDueDate()));
		object.put("TrangThaiHoSo", getMappingStatus(groupId, dossier));
		object.put("NgayTra", convertDate2String(dossier.getFinishDate()));//ko bb
		object.put("ThongTinTra", "");//ko bb
		int HinhThuc = 0;
		if (dossier.getViaPostal() != 0 && dossier.getViaPostal() != 1) {
			HinhThuc = 1;
		}
		object.put("HinhThuc", String.valueOf(HinhThuc));
		object.put("NgayKetThucXuLy", convertDate2String(dossier.getReleaseDate()));//ko bb
		object.put("DonViXuLy", dossier.getGovAgencyName());
		object.put("GhiChu", dossier.getDossierNote());//ko bb

		JSONArray TaiLieuNop = JSONFactoryUtil.createJSONArray();
		object.put("TaiLieuNop", TaiLieuNop);//ko bb
		//object.put("TepDinhKemId", "");
		//object.put("TenTepDinhKem", "");
		//object.put("IsDeleted", "");
		//object.put("MaThanhPhanHoSo", "");
		//object.put("DuongDanTaiTepTin", "");

		JSONArray DanhSachLePhi = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachLePhi", DanhSachLePhi);//ko bb
		//object.put("TenPhiLePhi", "");
		//object.put("MaPhiLePhi", "");
		//object.put("HinhThucThu", "");
		//object.put("Gia", "");
		//object.put("LoaiPhiLePhi", "");

		JSONArray DanhSachTepDinhKemKhac = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachTepDinhKemKhac", DanhSachTepDinhKemKhac);//ko bb
		//object.put("TenGiayTo", "");
		//object.put("SoLuong", "");
		//object.put("LoaiGiayTo", "");

		JSONArray DanhSachHoSoBoSung = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachHoSoBoSung", DanhSachHoSoBoSung);//ko bb
		//object.put("HoSoBoSungId", "");
		//object.put("NguoiYeuCauBoSung", "");
		//object.put("NoiDungBoSung", "");
		//object.put("NgayBoSung", "");
		//object.put("NguoiTiepNhanBoSung", "");
		//object.put("ThongTinTiepNhan", "");
		//object.put("NgayTiepNhanBoSung", "");
		//object.put("TrangThaiBoSung", "");
		//object.put("DanhSachGiayToBoSung", "");
		//object.put("DanhSachLePhiBoSung", "");
		//object.put("NgayHenTraTruoc", "");
		//object.put("NgayHenTraMoi", "");
		JSONArray DanhSachGiayToKetQua = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachGiayToKetQua", DanhSachGiayToKetQua);//ko bb
		//object.put("TenGiayTo", "");
		//object.put("MaThanhPhanHoSo", "");
		//object.put("GiayToId", "");
		//object.put("DuongDanTepTinKetQua", "");

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

		object.put("MaHoSo", dossier.getDossierNo());
		object.put("MaTTHC", _mServiceCode);
		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put("service", "LayThuTuc");
		body.put("maTTHC", _mServiceCode);

		JSONObject serviceInfo = getSharingData(serverConfig, body);
		//get TenTTHC, MaLinhVuc, TenLinhVuc
		String TenTTHC = StringPool.BLANK;
		String MaLinhVuc = StringPool.BLANK;
		String TenLinhVuc = StringPool.BLANK;
		if (serviceInfo != null && serviceInfo.has("result")) {
			JSONArray results = serviceInfo.getJSONArray("result");
			if (results.length() > 0) {
				JSONObject _tmp = results.getJSONObject(0);
				TenTTHC = _tmp.getString("TENTTHC");
				MaLinhVuc = _tmp.getJSONArray("LINHVUCTHUCHIEN").getJSONObject(0).getString("MALINHVUC");
				TenLinhVuc = _tmp.getJSONArray("LINHVUCTHUCHIEN").getJSONObject(0).getString("TENLINHVUC");
			}
		}
		object.put("TenTTHC", TenTTHC);
		object.put("MaLinhVuc", MaLinhVuc);
		object.put("TenLinhVuc", TenLinhVuc);
		//object.put("SoBienNhan", ""); //ko bb
		object.put("ChuHoSo", dossier.getContactName()); //ko bb
		int LoaiDoiTuong = 1;
		if (Validator.isNotNull(dossier.getApplicantIdType())) {
			if (dossier.getApplicantIdType().equals("business")) {
				LoaiDoiTuong = 2;
			}
		}
		object.put("LoaiDoiTuong", String.valueOf(LoaiDoiTuong));
		Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, dossier.getApplicantIdNo());
		String madoituong = StringPool.BLANK;
		if(applicant != null && "dvcqg".contentEquals(applicant.getMappingClassName())) {
			madoituong = applicant.getMappingClassPK();
		}
		object.put("MaDoiTuong", madoituong); //ko bb
		object.put("ThongTinKhac", ""); //ko bb
		object.put("Email", dossier.getContactEmail());
		object.put("Fax", dossier.getContactTelNo()); //ko bb
		object.put("SoDienThoai", dossier.getContactTelNo());
		object.put("TrichYeuHoSo", dossier.getDossierNote());
		object.put("NgayTiepNhan", convertDate2String(dossier.getReceiveDate()));
		object.put("NgayHenTra", convertDate2String(dossier.getDueDate()));
		object.put("TrangThaiHoSo", getMappingStatus(groupId, dossier));
		object.put("NgayTra", convertDate2String(dossier.getFinishDate()));//ko bb
		object.put("ThongTinTra", "");//ko bb
		int HinhThuc = 0;
		if (dossier.getViaPostal() != 0 && dossier.getViaPostal() != 1) {
			HinhThuc = 1;
		}
		object.put("HinhThuc", String.valueOf(HinhThuc));
		object.put("NgayKetThucXuLy", convertDate2String(dossier.getReleaseDate()));//ko bb
		object.put("DonViXuLy", dossier.getGovAgencyName());
		object.put("GhiChu", dossier.getDossierNote());//ko bb

		JSONArray TaiLieuNop = JSONFactoryUtil.createJSONArray();
		object.put("TaiLieuNop", TaiLieuNop);//ko bb
		//object.put("TepDinhKemId", "");
		//object.put("TenTepDinhKem", "");
		//object.put("IsDeleted", "");
		//object.put("MaThanhPhanHoSo", "");
		//object.put("DuongDanTaiTepTin", "");

		JSONArray DanhSachLePhi = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachLePhi", DanhSachLePhi);//ko bb
		//object.put("TenPhiLePhi", "");
		//object.put("MaPhiLePhi", "");
		//object.put("HinhThucThu", "");
		//object.put("Gia", "");
		//object.put("LoaiPhiLePhi", "");

		JSONArray DanhSachTepDinhKemKhac = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachTepDinhKemKhac", DanhSachTepDinhKemKhac);//ko bb
		//object.put("TenGiayTo", "");
		//object.put("SoLuong", "");
		//object.put("LoaiGiayTo", "");

		JSONArray DanhSachHoSoBoSung = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachHoSoBoSung", DanhSachHoSoBoSung);//ko bb
		//object.put("HoSoBoSungId", "");
		//object.put("NguoiYeuCauBoSung", "");
		//object.put("NoiDungBoSung", "");
		//object.put("NgayBoSung", "");
		//object.put("NguoiTiepNhanBoSung", "");
		//object.put("ThongTinTiepNhan", "");
		//object.put("NgayTiepNhanBoSung", "");
		//object.put("TrangThaiBoSung", "");
		//object.put("DanhSachGiayToBoSung", "");
		//object.put("DanhSachLePhiBoSung", "");
		//object.put("NgayHenTraTruoc", "");
		//object.put("NgayHenTraMoi", "");
		JSONArray DanhSachGiayToKetQua = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachGiayToKetQua", DanhSachGiayToKetQua);//ko bb
		//object.put("TenGiayTo", "");
		//object.put("MaThanhPhanHoSo", "");
		//object.put("GiayToId", "");
		//object.put("DuongDanTepTinKetQua", "");

		return object;
	}

	private JSONObject createSyncDossierStatusBodyRequest(long groupId, Dossier dossier) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		object.put("MaHoSo", dossier.getDossierNo());

		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		object.put("NguoiXuLy", dossierAction != null ? dossierAction.getActionUser() : StringPool.BLANK);
		object.put("ChucDanh", "");//ko bb
		object.put("ThoiDiemXuLy",
				dossierAction != null ? convertDate2String(dossierAction.getCreateDate()) : StringPool.BLANK);
		object.put("PhongBanXuLy", "");//ko bb
		object.put("NoiDungXuLy", dossierAction != null ? dossierAction.getActionNote() : StringPool.BLANK);
		object.put("TrangThai", getMappingStatus(groupId, dossier));
		object.put("NgayBatDau", "");//ko bb
		object.put("NgayKetThucTheoQuyDinh", "");//ko bb

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
				int syncType = -1;
				if (data != null && data.length() > 0) {
					if (data.has("service")) {
						syncType = 1;
					} else {
						syncType = 2;
					}
				} else {
					syncType = 0;
				}

				JSONObject qajson = JSONFactoryUtil.createJSONObject();
				DictCollection domainCollection = DictCollectionLocalServiceUtil
						.fetchByF_dictCollectionCode(DataMGTConstants.SERVICE_DOMAIN, serviceContext.getScopeGroupId());

				DictCollection agencyCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
						DataMGTConstants.GOVERNMENT_AGENCY, serviceContext.getScopeGroupId());

				if (syncType == 0) {
					data.put("service", "LayDanhSachHoiDapGuiTuDVCQG");
					qajson = getSharingData(serverConfig, data);
					extractFAQResult(user, resultData, qajson, serviceContext, domainCollection.getDictCollectionId(),
							agencyCollection.getDictCollectionId());

					data.put("service", "LayDanhSachHoiDapBoCoQuan");
					qajson = getSharingData(serverConfig, data);
					extractFAQResult(user, resultData, qajson, serviceContext, domainCollection.getDictCollectionId(),
							agencyCollection.getDictCollectionId());
				} else if (syncType == 1) {
					qajson = getSharingData(serverConfig, data);
					extractFAQResult(user, resultData, qajson, serviceContext, domainCollection.getDictCollectionId(),
							agencyCollection.getDictCollectionId());
				} else if (syncType == 2) {
					JSONArray result = JSONFactoryUtil.createJSONArray();
					result.put(data);
					qajson.put("result", result);
					extractFAQResult(user, resultData, qajson, serviceContext, domainCollection.getDictCollectionId(),
							agencyCollection.getDictCollectionId());
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
			ServiceContext serviceContext, long domainCollectionId, long agencyCollectionId) {
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

				String domainCodeTemp = StringPool.BLANK;
				//Dung cho phuong an replace
				if (Validator.isNotNull(malinhvuc)) {
					//domainCode = malinhvuc;
					domainCodeTemp = malinhvuc;
				} else {
					//domainCode = matthcdp;
					domainCodeTemp = matthcdp;
				}

				DictItemMapping dictItemMapping1 = DictItemMappingLocalServiceUtil
						.fetchByF_GID_ICDVCQG_CID(serviceContext.getScopeGroupId(), domainCodeTemp, domainCollectionId);
				DictItemMapping dictItemMapping2 = DictItemMappingLocalServiceUtil
						.fetchByF_GID_ICDVCQG_CID(serviceContext.getScopeGroupId(), madonvi, agencyCollectionId);
				if (dictItemMapping1 == null || dictItemMapping2 == null) {

					continue;
				}

				DictItem domainItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(dictItemMapping1.getItemCode(),
						domainCollectionId, serviceContext.getScopeGroupId());

				DictItem govAgencyItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(dictItemMapping2.getItemCode(),
						agencyCollectionId, serviceContext.getScopeGroupId());

				if (domainItem == null || govAgencyItem == null) {
					continue;
					}

				domainCode = dictItemMapping1.getItemCode();
				domainName = domainItem.getItemName();

				Question question = QuestionLocalServiceUtil.fetchByG_CN_CPK(serviceContext.getScopeGroupId(),
						"dvcqg_question", String.valueOf(hoidapid));

				List<Long> traloiids = new ArrayList<Long>();
				//chua dong bo
				if (question == null) {
					question = QuestionLocalServiceUtil.updateQuestion(serviceContext.getCompanyId(),
							serviceContext.getScopeGroupId(), 0,
							Validator.isNotNull(hovaten) ? hovaten : user.getFullName(), thudientu, noidung, trangthai,
							domainCode, domainName, govAgencyItem.getItemCode(), govAgencyItem.getItemName(),
							Validator.isNotNull(hovaten) ? "faq" : StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
							"dvcqg_question", String.valueOf(hoidapid), 1);

				} else {
					//da dong bo ->dong bo lai va lay d/s cau tra loi da dong bo

					QuestionLocalServiceUtil.updateQuestion(serviceContext.getCompanyId(),
							serviceContext.getScopeGroupId(), question.getQuestionId(),
							Validator.isNotNull(hovaten) ? hovaten : user.getFullName(), thudientu, noidung, trangthai,
							domainCode, domainName, govAgencyItem.getItemCode(), govAgencyItem.getItemName(),
							Validator.isNotNull(hovaten) ? "faq" : StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
							"dvcqg_question", String.valueOf(hoidapid), 1);

					List<Answer> answers = AnswerLocalServiceUtil.findByG_Q(serviceContext.getScopeGroupId(),
							question.getQuestionId());

					if (answers != null) {
						for (Answer answer : answers) {
							if (Validator.isNotNull(answer.getClassName())
									&& "dvcqg_answer".contentEquals(answer.getClassName())) {
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

				//dong bo cau tra loi
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
			String adapter_url = config.getString("adapter_url");
			String auth_endpoint = config.getString("auth_endpoint");
			String username = config.getString("username");
			String password = config.getString("password");
			String dstcode = config.getString("dstcode");
			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put("username", username);
			body.put("password", password);

			String endpoint = adapter_url + auth_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("dstcode", dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
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

				if (result.has("session") && result.has("error_code") && result.getInt("error_code") == 0) {
					return result.getString("session");
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
	public String getAccessToken(User user, HttpServletRequest request, HttpServletResponse response, ServiceContext serviceContext) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);

			String accessToken = getAccessToken(serverConfig);

			/*HttpSession session = request.getSession();
					
			String accessToken = (String)session.getAttribute("ACCESS_TOKEN");
			
			if(Validator.isNull(accessToken)) {
			
				accessToken = getAccessToken(serverConfig);
				
				session.setAttribute("ACCESS_TOKEN", accessToken);
			}*/

			return accessToken;

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

	@Deprecated
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

	public JSONObject getServiceInfoDVCQGDetail(User user, long groupId, ServiceContext serviceContext, String code) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject data = JSONFactoryUtil.createJSONObject();
			data.put("service", "LayThuTuc");
			data.put("maTTHC", code);
			result = getSharingData(serverConfig, data);

		}

		return result;
	}

	public JSONArray getServiceInfoDVCQG(User user, long groupId, ServiceContext serviceContext, String serviceCode,
			String serviceName) {
		JSONArray result = JSONFactoryUtil.createJSONArray();

		if (_serviceInfoDVCQG.length() == 0) {
			List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
			if (serverConfigs != null && !serverConfigs.isEmpty()) {
				ServerConfig serverConfig = serverConfigs.get(0);
				JSONObject data = JSONFactoryUtil.createJSONObject();
				data.put("service", "LayDanhSachTTHC");
				JSONObject responseData = getSharingDictCollection(serverConfig, data);

				if (responseData != null && responseData.has("result")) {

					_serviceInfoDVCQG = responseData.getJSONArray("result");
				}
			}
		}

		JSONObject item;
		JSONObject dataRow = null;
		for (int i = 0; i < _serviceInfoDVCQG.length(); i++) {
			item = _serviceInfoDVCQG.getJSONObject(i);
			dataRow = JSONFactoryUtil.createJSONObject();
			String serviceCodeDVCQG = item.getString("MATTHC");
			dataRow.put("serviceCodeDVCQG", serviceCodeDVCQG);
			dataRow.put("serviceNameDVCQG", item.getString("TENTTHC"));
			ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchByGID_SCDVCQG(groupId,
					serviceCodeDVCQG);
			if (serviceInfoMapping == null) {
				dataRow.put("serviceCode", StringPool.BLANK);
				dataRow.put("serviceName", StringPool.BLANK);
				dataRow.put("mapping", String.valueOf(false));
				dataRow.put("synced", 0);
				dataRow.put("mappingClassPK", 0);
			} else {
				String tmpServiceCode = serviceInfoMapping.getServiceCode();
				ServiceInfo serviceInfo = null;
				try {
					serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, tmpServiceCode);
				} catch (Exception e) {
					_log.warn(e);
				}
				String tmpServiceName = serviceInfo != null ? serviceInfo.getServiceName() : StringPool.BLANK;

				dataRow.put("serviceCode", tmpServiceCode);
				dataRow.put("serviceName", tmpServiceName);
				dataRow.put("mapping", String.valueOf(true));
				dataRow.put("synced", serviceInfoMapping.getSynced());
				dataRow.put("mappingClassPK", serviceInfoMapping.getServiceInfoMappingId());
			}
			result.put(dataRow);

		}
		return result;
	}

	public HashMap<String, String> getDictItemDVCQGMap(User user, ServiceContext serviceContext, String service) {
		HashMap<String, String> dictItemDVCQGMap = new HashMap<String, String>();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject data = JSONFactoryUtil.createJSONObject();
			data.put("service", service);
			JSONObject responseData = getSharingDictCollection(serverConfig, data);
			if (responseData != null && responseData.has("result")) {
				JSONArray result = responseData.getJSONArray("result");
				JSONObject item;

				for (int i = 0; i < result.length(); i++) {
					item = result.getJSONObject(i);
					if (service.equals("LayDanhMucLinhVuc")) {
						dictItemDVCQGMap.put(item.getString("MALINHVUC"), item.getString("TENLINHVUC"));
					} else if (service.equals("LayDanhMucCoQuan")) {
						dictItemDVCQGMap.put(item.getString("MADONVI"), item.getString("TENDONVI"));
					}

				}
			}
		}
		return dictItemDVCQGMap;
	}

	public JSONObject getDictItemDVCQG(User user, ServiceContext serviceContext, String service) {
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject body = JSONFactoryUtil.createJSONObject();
			body.put("service", service);
			JSONObject responseData = getSharingDictCollection(serverConfig, body);
			if (responseData != null && responseData.has("result")) {
				JSONArray result = responseData.getJSONArray("result");
				JSONObject item;

				for (int i = 0; i < result.length(); i++) {
					JSONObject dataRow = JSONFactoryUtil.createJSONObject();
					item = result.getJSONObject(i);
					if (service.equals("LayDanhMucLinhVuc")) {
						dataRow.put("itemCodeDVCQG", item.getString("MALINHVUC"));
						dataRow.put("itemNameDVCQG", item.getString("TENLINHVUC"));
						data.put(dataRow);
					} else if (service.equals("LayDanhMucCoQuan")) {

						dataRow.put("itemCodeDVCQG", item.getString("MADONVI"));
						dataRow.put("itemNameDVCQG", item.getString("TENDONVI"));
						data.put(dataRow);
					}

				}
			}
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("total", data.length());
		result.put("data", data);
		return result;
	}

	@Deprecated
	public JSONArray getServiceInfoSimilarity(long groupId, String serviceCode, String serviceName,
			HashMap<String, String> map) {
		ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId,
				serviceCode);
		String _tmpServiceName = serviceName.replaceAll("[.,\\-_:;\\\"\\']", "").toLowerCase();
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

		if (_serviceInfoMapChars != null && !_serviceInfoMapChars.isEmpty()) {
			_log.info("----------------------------->>>>>getServiceInfoSimilarity: get data from store: "
					+ _serviceInfoMapChars.size());
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String name = entry.getValue().replaceAll("[.,\\-_:;\\\"\\']", "").toLowerCase();
				//_log.info(key + "|" + name);
				Map<CharSequence, Integer> vectorB = null;
				if (_serviceInfoMapChars.containsKey(key)) {
					vectorB = _serviceInfoMapChars.get(key);
				} else {
					vectorB = Arrays.stream(name.split(" "))
							.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

					_serviceInfoMapChars.put(key, vectorB);
				}

				Double weightIndex = documentsSimilarity.cosineSimilarity(vectorA, vectorB);
				//_log.info(weightIndex);
				JSONObject item = null;
				if (_serviceInfoMapItems != null && _serviceInfoMapItems.containsKey(key)) {
					item = _serviceInfoMapItems.get(key);
					item.put("similarityPercent", df.format(weightIndex * 100));
				} else {
					item = JSONFactoryUtil.createJSONObject();
					item.put("serviceCodeDVCQG", key);
					item.put("serviceNameDVCQG", entry.getValue());
					item.put("mapped", false);
					item.put("serviceInfoMappingId", 0);
					_serviceInfoMapItems.put(key, item);
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

			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String name = entry.getValue().replaceAll("[.,\\-_:;\\\"\\']", "").toLowerCase();

				Map<CharSequence, Integer> vectorB = Arrays.stream(name.split(" "))
						.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

				_serviceInfoMapChars.put(key, vectorB);

				Double weightIndex = documentsSimilarity.cosineSimilarity(vectorA, vectorB);

				JSONObject item = JSONFactoryUtil.createJSONObject();
				item.put("serviceCodeDVCQG", key);
				item.put("serviceNameDVCQG", entry.getValue());
				item.put("serviceInfoMappingId", 0);
				item.put("mapped", false);
				_serviceInfoMapItems.put(key, item);

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

	public JSONArray getDictItemSimilarity(long groupId, String collectionCode, String dictItemCode,
			String dictItemName, HashMap<String, String> map, List<String> mappedCode) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode,
				groupId);
		if (dictCollection == null) {
			return result;
		}

		DictItemMapping dicItemMapping = DictItemMappingLocalServiceUtil.fetchByF_GID_IC_CID(groupId, dictItemCode,
				dictCollection.getDictCollectionId());

		String _tmpDictItemName = dictItemName.replaceAll("[.,\\-_:;\\\"\\']", "").toLowerCase();

		if (dicItemMapping != null) {
			JSONObject item = JSONFactoryUtil.createJSONObject();
			String itemNameDVCQG = map.get(dicItemMapping.getItemCodeDVCQG());
			item.put("itemCodeDVCQG", dicItemMapping.getItemCodeDVCQG());
			item.put("dictItemMappingId", dicItemMapping.getMappingId());
			item.put("itemNameDVCQG", itemNameDVCQG);
			item.put("similarityPercent", 100);
			item.put("mapped", true);
			result.put(item);
			return result;
		}

		Map<CharSequence, Integer> vectorA = Arrays.stream(_tmpDictItemName.split(" "))
				.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

		SortedMap<Double, JSONObject> sortedMap = new TreeMap<Double, JSONObject>(Collections.reverseOrder());

		CosineSimilarity documentsSimilarity = new CosineSimilarity();

		DecimalFormat df = new DecimalFormat();

		df.setMaximumFractionDigits(2);

		if (_dictItemMapChars != null && !_dictItemMapChars.isEmpty()) {

			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				if (mappedCode.contains(key)) {
					continue;
				}

				String name = entry.getValue().replaceAll("[.,\\-_:;\\\"\\']", "").toLowerCase();

				Map<CharSequence, Integer> vectorB = null;
				if (_dictItemMapChars.containsKey(key)) {
					vectorB = _dictItemMapChars.get(key);
				} else {
					vectorB = Arrays.stream(name.split(" "))
							.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

					_dictItemMapChars.put(key, vectorB);
				}

				Double weightIndex = documentsSimilarity.cosineSimilarity(vectorA, vectorB);

				JSONObject item = null;
				if (_dictItemMapItems != null && _dictItemMapItems.containsKey(key)) {
					item = _dictItemMapItems.get(key);
					item.put("similarityPercent", df.format(weightIndex * 100));
				} else {
					item = JSONFactoryUtil.createJSONObject();
					item.put("itemCodeDVCQG", key);
					item.put("itemNameDVCQG", entry.getValue());
					item.put("mapped", false);
					item.put("dictItemMappingId", 0);
					_dictItemMapItems.put(key, item);
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

			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				if (mappedCode.contains(key)) {
					continue;
				}
				String name = entry.getValue().replaceAll("[.,\\-_:;\\\"\\']", "").toLowerCase();

				Map<CharSequence, Integer> vectorB = Arrays.stream(name.split(" "))
						.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

				_dictItemMapChars.put(key, vectorB);

				Double weightIndex = documentsSimilarity.cosineSimilarity(vectorA, vectorB);

				JSONObject item = JSONFactoryUtil.createJSONObject();
				item.put("itemCodeDVCQG", key);
				item.put("itemNameDVCQG", entry.getValue());
				item.put("dictItemMappingId", 0);
				item.put("mapped", false);
				_dictItemMapItems.put(key, item);

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
			String adapter_url = config.getString("adapter_url");
			String integration_endpoint = config.getString("integration_endpoint");
			String madonvi = config.getString("madonvi");
			String dstcode = config.getString("dstcode");
			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put("session", accessToken);
			body.put("madonvi", madonvi);
			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				body.put(key, data.get(key));
			}

			String endpoint = adapter_url + integration_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("dstcode", dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
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
			String adapter_url = config.getString("adapter_url");
			String integration_endpoint = config.getString("integration_endpoint");
			String madonvi = config.getString("madonvi");
			String accessToken = getAccessToken(config);
			String dstcode = config.getString("dstcode");
			if (Validator.isNull(accessToken) || Validator.isNull(data)) {
				return result;
			}

			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put("session", accessToken);
			body.put("madonvi", madonvi);
			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				body.put(key, data.get(key));
			}

			String endpoint = adapter_url + integration_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("dstcode", dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
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
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
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
			String adapter_url = config.getString("adapter_url");
			String share_endpoint = config.getString("share_endpoint");
			String madonvi = config.getString("madonvi");
			String dstcode = config.getString("dstcode");
			String accessToken = getAccessToken(config);
			if (Validator.isNull(accessToken) || Validator.isNull(data)) {
				return result;
			}

			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put("session", accessToken);
			body.put("madonvi", madonvi);
			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				body.put(key, data.get(key));
			}

			String endpoint = adapter_url + share_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("dstcode", dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
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
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			return getSharingDictCollection(serverConfig, data);
		}
		return result;
	}

	@Override
	public JSONObject getSharingQA(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> getSharingQA: " + serverConfigs + "|" + serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				result = getSharingData(serverConfig, data);
			} catch (Exception e) {
				_log.error(e);
			}
		}

		return result;
	}

	private boolean hasSyncDossier(String dossierNo, JSONObject config, String accessToken) {
		JSONObject searchData = searchDossier(dossierNo, config, accessToken);
		if (searchData != null && searchData.has("result")) {
			JSONArray result = searchData.getJSONArray("result");
			if (result != null && result.length() > 0) {
				return true;
			}

		}
		return false;
	}

	@Override
	public JSONObject mappingServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCode,
			String serviceCodeDVCQG, String serviceNameDVCQG) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (Validator.isNotNull(serviceCode) && Validator.isNotNull(serviceCodeDVCQG)) {
			try {
				ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchByGID_SCDVCQG(groupId,
						serviceCodeDVCQG);
				if (serviceInfoMapping != null) {
					ServiceInfoMappingLocalServiceUtil
							.removeServiceInfoMapping(serviceInfoMapping.getServiceInfoMappingId());
				}

				serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId, serviceCode);

				if (serviceInfoMapping != null) {
					ServiceInfoMappingLocalServiceUtil
							.removeServiceInfoMapping(serviceInfoMapping.getServiceInfoMappingId());
				}

				serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.addServiceInfoMapping(groupId,
						serviceContext.getCompanyId(), user.getUserId(), serviceCode, serviceCodeDVCQG,
						serviceNameDVCQG, 0);
				result.put("id", serviceInfoMapping.getServiceInfoMappingId());
				result.put("serviceCode", serviceCode);
				result.put("serviceCodeDVCQG", serviceCodeDVCQG);
				result.put("serviceInfoMappingId", serviceInfoMapping.getServiceInfoMappingId());
				result.put("groupId", serviceInfoMapping.getGroupId());
				result.put("userId", serviceInfoMapping.getUserId());
			} catch (Exception e) {
				_log.error(e);
			}
		}

		return result;
	}

	@Override
	public boolean removeMappingServiceInfo(User user, long groupId, ServiceContext serviceContext, long id) {

		return ServiceInfoMappingLocalServiceUtil.removeServiceInfoMapping(id);
	}

	private JSONObject searchDossier(String dossierNo, JSONObject config, String accessToken) {

		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put("service", "TraCuuHoSo");
		body.put("mahoso", dossierNo);

		return getSharingData(config, body, accessToken);
	}

	private JSONObject syncData(ServerConfig serverConfig, JSONObject data) {

		HttpURLConnection conn = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			_log.debug("config->>>  " + config.toJSONString());
			String adapter_url = config.getString("adapter_url");
			String integration_endpoint = config.getString("integration_endpoint");
			String madonvi = config.getString("madonvi");
			String accessToken = getAccessToken(config);
			String dstcode = config.getString("dstcode");
			if (Validator.isNull(accessToken) || Validator.isNull(data)) {
				return result;
			}

			JSONObject body = JSONFactoryUtil.createJSONObject();

			body.put("session", accessToken);
			body.put("madonvi", madonvi);
			Iterator<String> keys = data.keys();

			while (keys.hasNext()) {
				String key = keys.next();

				body.put(key, data.get(key));
			}

			String endpoint = adapter_url + integration_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("dstcode", dstcode);
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toJSONString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
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
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
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
			body.put("isUpdating", isUpdating);
			body.put("service", "DongBoHoSoMC");
			body.put("data", synsObjects);

			return syncData(serverConfig, body);
		}
		return result;
	}

	public JSONObject syncDossierAndDossierStatus(long groupId, Dossier dossier) throws JSONException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		_log.info("serverConfigs: "+serverConfigs);
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String accessToken = getAccessToken(config);
			_log.info("accessToken: "+accessToken);

			JSONArray synsObjects = JSONFactoryUtil.createJSONArray();
			if (Validator.isNotNull(accessToken)) {
				boolean hasSync = hasSyncDossier(dossier.getDossierNo(), config, accessToken);
				JSONObject synsObject = createSyncDossierBodyRequest(groupId, dossier, config, accessToken);
				synsObjects.put(synsObject);
				JSONObject body = JSONFactoryUtil.createJSONObject();

				if (hasSync) {
					body.put("isUpdating", "True");
				} else {
					body.put("isUpdating", "False");
				}
				body.put("data", synsObjects);
				body.put("service", "DongBoHoSoMC");
				result = syncData(serverConfig, body);
				_log.debug("syncDossierAndDossierStatus " + result.toJSONString());
				if (result.has("error_code") && result.getString("error_code").equals("00")) {
					body = JSONFactoryUtil.createJSONObject();
					synsObjects = JSONFactoryUtil.createJSONArray();
					JSONObject _tmp = createSyncDossierStatusBodyRequest(groupId, dossier);
					synsObjects.put(_tmp);
					body.put("service", "CapNhatTienDoHoSoMC");
					body.put("data", synsObjects);
					result = syncData(serverConfig, body);
				}
			}

		}
		return result;
	}

	@Override
	public JSONObject syncDossierStatus(User user, long groupId, ServiceContext serviceContext, String strDossierId) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
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
			body.put("service", "CapNhatTienDoHoSoMC");
			body.put("data", synsObjects);

			return syncData(serverConfig, body);
		}
		return result;
	}

	/*@Deprecated
	@Override
	public JSONObject syncServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCodes) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> syncServiceInfo: " + serverConfigs + "|" + serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty() && Validator.isNotNull(serviceCodes)) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String accessToken = getAccessToken(config);
				String[] arrayServiceCode = StringUtil.split(serviceCodes);
	
				JSONObject body = JSONFactoryUtil.createJSONObject();
	
				body.put("service", "LayThuTuc");
	
				for (String serviceCode : arrayServiceCode) {
					ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil
							.fetchDVCQGServiceCode(groupId, serviceCode);
					ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
					if (serviceInfoMapping != null && Validator.isNotNull(serviceInfoMapping.getServiceCodeDVCQG())
							&& serviceInfo != null) {
	
						body.put("maTTHC", serviceInfoMapping.getServiceCodeDVCQG());
	
						JSONObject serviceInfoDVCQG = getSharingData(config, body, accessToken);
	
						if (serviceInfoDVCQG != null && serviceInfoDVCQG.has("result")) {
							JSONArray results = serviceInfoDVCQG.getJSONArray("result");
							if (results.length() > 0) {
								JSONObject _tmp = results.getJSONObject(0);
								StringBuffer sb = null;
								//TENTTHC
								String tentthc = _tmp.getString("TENTTHC");
								serviceInfo.setServiceName(tentthc);
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
													String tentrinhtu = trinhtu_arr.getJSONObject(j)
															.getString("TENTRINHTU");
													sb.append(tentrinhtu + "<br>");
												}
											}
										}
	
									}
								}
								serviceInfo.setProcessText(sb.toString());
	
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
													sb.append("- Thời gian giải quyết: " + thoigiangiaiquyet + " "
															+ donvitinh + (Validator.isNotNull(mota) ? "(" + mota + ")"
																	: StringPool.BLANK)
															+ "<br>");
													durationText += nhankenh + ":" + "- Thời gian giải quyết: "
															+ thoigiangiaiquyet + " " + donvitinh + "<br>";
													JSONArray philephi_arr = thoigian_obj.getJSONArray("PHILEPHI");
													if (philephi_arr != null && philephi_arr.length() > 0) {
														String maphilephi = philephi_arr.getJSONObject(0)
																.getString("MAPHILEPHI");
														double sotien = philephi_arr.getJSONObject(0)
																.getDouble("SOTIEN");
														String donvi = philephi_arr.getJSONObject(0).getString("DONVI");
														String lephimota = philephi_arr.getJSONObject(0)
																.getString("MOTA");
														feeText += nhankenh + ":" + maphilephi + ", " + sotien + " "
																+ donvi
																+ (Validator.isNotNull(lephimota)
																		? "(" + lephimota + ")"
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
	
								//THANHPHANHOSO
								List<ServiceFileTemplate> serviceFileTemplates = ServiceFileTemplateLocalServiceUtil
										.getByServiceInfoId(serviceInfo.getServiceInfoId());
								if (serviceFileTemplates != null) {
									for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplates) {
										ServiceFileTemplateLocalServiceUtil.removeServiceFileTemplate(
												serviceInfo.getServiceInfoId(),
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
															String mimeType = URLConnection
																	.guessContentTypeFromStream(in);
															//String mimeType = MimeTypesUtil.getContentType(tenmaudon);
															actions.addServiceFileTemplate(user.getUserId(), groupId,
																	serviceInfo.getServiceInfoId(), magiayto, tengiayto,
																	tenmaudon, in, mimeType,
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
					}
				}
			} catch (Exception e) {
				_log.error(e);
	
			}
		}
	
		return result;
	}*/

	@Override
	public JSONObject syncServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCodes,
			String type) {
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

				DictCollection collection1 = DictCollectionLocalServiceUtil
						.fetchByF_dictCollectionCode(DataMGTConstants.SERVICE_DOMAIN, groupId);

				DictCollection collection2 = DictCollectionLocalServiceUtil
						.fetchByF_dictCollectionCode(DataMGTConstants.GOVERNMENT_AGENCY, groupId);

				if (Validator.isNotNull(serviceCodes)) {
					String[] arrayServiceCode = StringUtil.split(serviceCodes);

					for (String serviceCode : arrayServiceCode) {

						ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);

						if (serviceInfo != null && type.equalsIgnoreCase("sync")) {
							
							String serviceCodeDVCQG = StringPool.BLANK;
							
							ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId, serviceCode);
							
							if(serviceInfoMapping == null) {
							continue;
						}
							
							serviceCodeDVCQG = serviceInfoMapping.getServiceCodeDVCQG();
							
							syncServiceInfo(user, groupId, result, config, serviceCodeDVCQG, serviceInfo,
									collection1.getDictCollectionId(), collection2.getDictCollectionId(), body, accessToken,
									serviceContext, type);
							
						}

						if (type.equals("create")) {
							serviceInfo = new ServiceInfoImpl();
							
							syncServiceInfo(user, groupId, result, config, serviceCode, serviceInfo,
									collection1.getDictCollectionId(), collection2.getDictCollectionId(), body, accessToken,
									serviceContext, type);
						}

						
					}
				} else {
					if (type.equals("sync")) {
					List<ServiceInfo> serviceInfos = ServiceInfoLocalServiceUtil.getServiceInfosByGroupId(groupId);
					if (serviceInfos != null) {
						for (ServiceInfo serviceInfo : serviceInfos) {
								ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId, serviceInfo.getServiceCode());
								
								if(serviceInfoMapping == null || serviceInfoMapping.getSynced() == 1) {
									continue;
								}
								syncServiceInfo(user, groupId, result, config, serviceInfoMapping.getServiceCodeDVCQG(), serviceInfo,
										collection1.getDictCollectionId(), collection2.getDictCollectionId(), body,
										accessToken, serviceContext, type);
						}
					}
				}

				}

			} catch (Exception e) {
				_log.error(e);

			}
		}

		return result;
	}

	//TODO
	private JSONObject syncServiceInfo(User user, long groupId, JSONObject result, JSONObject config,
			String serviceCodeDVCQG, ServiceInfo serviceInfo, long domainCollectionId, long govAgencyCollectionId,
			JSONObject body, String accessToken, ServiceContext serviceContext, String type) throws Exception {

		body.put("maTTHC", serviceCodeDVCQG);

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

				if (type.equalsIgnoreCase("sync")) {
					ServiceInfoMapping infoMapping = ServiceInfoMappingLocalServiceUtil.fetchByGID_SCDVCQG(groupId,
							matthc);
					if (infoMapping == null || Validator.isNull(infoMapping.getServiceCode())) {
						return result;
					}
					matthc = infoMapping.getServiceCode();
				}
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

				if (type.equalsIgnoreCase("sync")) {
					DictItemMapping dictItemMapping = DictItemMappingLocalServiceUtil.fetchByF_GID_ICDVCQG_CID(groupId,
							malinhvuc, domainCollectionId);
					malinhvuc = dictItemMapping != null ? dictItemMapping.getItemCode() : StringPool.BLANK;
					if (Validator.isNotNull(malinhvuc)) {
						DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(malinhvuc,
								domainCollectionId, groupId);
						tenlinhvuc = dictItem != null ? dictItem.getItemName() : StringPool.BLANK;
					}
				}

				serviceInfo.setDomainCode(malinhvuc);
				serviceInfo.setDomainName(tenlinhvuc);

				//MACOQUANCONGBO
				String macoquancongbo = _tmp.getString("MACOQUANCONGBO");
				String tencoquancongbo = StringPool.BLANK;

				if (type.equalsIgnoreCase("sync")) {
					DictItemMapping dictItemMapping = DictItemMappingLocalServiceUtil.fetchByF_GID_ICDVCQG_CID(groupId,
							macoquancongbo, govAgencyCollectionId);
					macoquancongbo = dictItemMapping != null ? dictItemMapping.getItemCode() : StringPool.BLANK;
					if (Validator.isNotNull(macoquancongbo)) {
					DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(macoquancongbo,
								govAgencyCollectionId, groupId);
						tencoquancongbo = dictItem != null ? dictItem.getItemName() : StringPool.BLANK;
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

				if (type.equalsIgnoreCase("sync")) {
					serviceInfo = ServiceInfoLocalServiceUtil.updateServiceInfo(serviceInfo);
				} else if (type.equalsIgnoreCase("create")) {
					serviceInfo = ServiceInfoLocalServiceUtil.addServiceInfo(user.getUserId(), groupId,
							serviceInfo.getServiceCode(), serviceInfo.getServiceName(), serviceInfo.getProcessText(),
							serviceInfo.getMethodText(), serviceInfo.getDossierText(), serviceInfo.getConditionText(),
							serviceInfo.getDurationText(), serviceInfo.getApplicantText(), serviceInfo.getResultText(),
							serviceInfo.getRegularText(), serviceInfo.getFeeText(), serviceInfo.getAdministrationCode(),
							serviceInfo.getDomainCode(), serviceInfo.getMaxLevel(), false,
							serviceInfo.getGovAgencyText(), serviceContext);
				}

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
				result.put(serviceInfo.getServiceCode(), true);
			} else {
				result.put(serviceInfo.getServiceCode(), false);
			}

		} else {
			result.put(serviceInfo.getServiceCode(), false);
		}

		return result;
	}

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
								&& "dvcqg_question".contentEquals(question.getClassName())) {
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
