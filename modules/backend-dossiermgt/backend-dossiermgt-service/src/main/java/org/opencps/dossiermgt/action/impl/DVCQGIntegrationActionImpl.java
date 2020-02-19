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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
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

/**
 * @author trungnt
 *
 */
public class DVCQGIntegrationActionImpl implements DVCQGIntegrationAction {

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

			conn.setRequestProperty(HttpHeaders.ACCEPT, "application/json");
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
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
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
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
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
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
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
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
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty(DVCQGIntegrationActionTerm.DSTCODE, dstcode);
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
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGIntegrationActionTerm.DVCQG_INTEGRATION);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		_log.info("-->>>>>>>> syncServiceInfo: " + serverConfigs +  StringPool.PIPE + serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty() && Validator.isNotNull(serviceCodes)) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String accessToken = getAccessToken(config);
				String[] arrayServiceCode = StringUtil.split(serviceCodes);

				JSONObject body = JSONFactoryUtil.createJSONObject();

				body.put(DVCQGIntegrationActionTerm.SERVICE, DVCQGIntegrationActionTerm.LAYTHUTUC);

				for (String serviceCode : arrayServiceCode) {
					ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil
							.fetchDVCQGServiceCode(groupId, serviceCode);
					ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
					if (serviceInfoMapping != null && Validator.isNotNull(serviceInfoMapping.getServiceCodeDVCQG())
							&& serviceInfo != null) {

						body.put(DVCQGIntegrationActionTerm.MATTHC, serviceInfoMapping.getServiceCodeDVCQG());

						JSONObject serviceInfoDVCQG = getSharingData(config, body, accessToken);

						if (serviceInfoDVCQG != null && serviceInfoDVCQG.has(DVCQGIntegrationActionTerm.RESULT)) {
							JSONArray results = serviceInfoDVCQG.getJSONArray(DVCQGIntegrationActionTerm.RESULT);
							if (results.length() > 0) {
								JSONObject _tmp = results.getJSONObject(0);
								StringBuffer sb = null;
								//TENTTHC
								String tentthc = _tmp.getString(DVCQGIntegrationActionTerm.TENTTHC);
								serviceInfo.setServiceName(tentthc);
								//TRINHTUTHUCHIEN
								sb = new StringBuffer();
								if (_tmp.has(DVCQGIntegrationActionTerm.TRINHTUTHUCHIEN)) {
									JSONArray trinhtuthuchien_arr = _tmp.getJSONArray(DVCQGIntegrationActionTerm.TRINHTUTHUCHIEN);
									if (trinhtuthuchien_arr != null) {
										for (int i = 0; i < trinhtuthuchien_arr.length(); i++) {
											JSONObject trinhtuthuchien_obj = trinhtuthuchien_arr.getJSONObject(i);
											String truonghop = trinhtuthuchien_obj.getString(DVCQGIntegrationActionTerm.TRUONGHOP);
											sb.append(truonghop + StringPool.NEW_LINE);
											JSONArray trinhtu_arr = trinhtuthuchien_obj.getJSONArray(DVCQGIntegrationActionTerm.TRINHTU);
											if (trinhtu_arr != null) {
												for (int j = 0; j < trinhtu_arr.length(); j++) {
													String tentrinhtu = trinhtu_arr.getJSONObject(j)
															.getString(DVCQGIntegrationActionTerm.TENTRINHTU);
													sb.append(tentrinhtu + StringPool.NEW_LINE);
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
								if (_tmp.has(DVCQGIntegrationActionTerm.CACHTHUCTHUCHIEN)) {
									JSONArray cachthucthuchien_arr = _tmp.getJSONArray(DVCQGIntegrationActionTerm.CACHTHUCTHUCHIEN);
									if (cachthucthuchien_arr != null) {
										for (int i = 0; i < cachthucthuchien_arr.length(); i++) {
											JSONObject cachthucthuchien_obj = cachthucthuchien_arr.getJSONObject(i);
											int kenh = cachthucthuchien_obj.getInt(DVCQGIntegrationActionTerm.KENH);
											String nhankenh = DVCQGIntegrationActionTerm.NHAN_KENH_DEFAULT;
											if (kenh == 2) {
												nhankenh = DVCQGIntegrationActionTerm.NHAN_KENH_2;
											} else if (kenh == 3) {
												nhankenh = DVCQGIntegrationActionTerm.NHAN_KENH_3;
											}
											sb.append(nhankenh + ":\n");

											JSONArray thoigian_arr = cachthucthuchien_obj.getJSONArray(DVCQGIntegrationActionTerm.THOIGIAN);

											if (thoigian_arr != null) {
												for (int j = 0; j < thoigian_arr.length(); j++) {
													JSONObject thoigian_obj = thoigian_arr.getJSONObject(j);
													int thoigiangiaiquyet = thoigian_obj.getInt(DVCQGIntegrationActionTerm.THOIGIANGIAIQUYET);
													String donvitinh = thoigian_obj.getString(DVCQGIntegrationActionTerm.DONVITINH);
													String mota = thoigian_obj.getString(DVCQGIntegrationActionTerm.MOTA);
													sb.append(DVCQGIntegrationActionTerm.LABEL_THOIGIAN_GIAIQUYET + thoigiangiaiquyet + StringPool.SPACE
															+ donvitinh + (Validator.isNotNull(mota) ?  StringPool.OPEN_PARENTHESIS + mota +  StringPool.CLOSE_PARENTHESIS
																	: StringPool.BLANK)
															+ StringPool.NEW_LINE);
													durationText += nhankenh + StringPool.COLON + DVCQGIntegrationActionTerm.LABEL_THOIGIAN_GIAIQUYET
															+ thoigiangiaiquyet + StringPool.SPACE + donvitinh + StringPool.NEW_LINE;
													JSONArray philephi_arr = thoigian_obj.getJSONArray(DVCQGIntegrationActionTerm.PHILEPHI);
													if (philephi_arr != null && philephi_arr.length() > 0) {
														String maphilephi = philephi_arr.getJSONObject(0)
																.getString(DVCQGIntegrationActionTerm.MAPHILEPHI);
														double sotien = philephi_arr.getJSONObject(0)
																.getDouble(DVCQGIntegrationActionTerm.SOTIEN);
														String donvi = philephi_arr.getJSONObject(0).getString(DVCQGIntegrationActionTerm.DONVI);
														String lephimota = philephi_arr.getJSONObject(0)
																.getString(DVCQGIntegrationActionTerm.MOTA);
														feeText += nhankenh + StringPool.COLON + maphilephi + StringPool.COMMA_AND_SPACE + sotien + StringPool.SPACE
																+ donvi
																+ (Validator.isNotNull(lephimota)
																		?  StringPool.OPEN_PARENTHESIS + lephimota +  StringPool.CLOSE_PARENTHESIS
																		: StringPool.BLANK)
																+ StringPool.NEW_LINE;
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
								if (_tmp.has(DVCQGIntegrationActionTerm.YEUCAU)) {
									yeucau = _tmp.getString(DVCQGIntegrationActionTerm.YEUCAU);
								}
								serviceInfo.setConditionText(yeucau);

								//MOTADOITUONGTHUCHIEN
								String motadoituongthuchien = StringPool.BLANK;
								if (_tmp.has(DVCQGIntegrationActionTerm.MOTADOITUONGTHUCHIEN)) {
									motadoituongthuchien = _tmp.getString(DVCQGIntegrationActionTerm.MOTADOITUONGTHUCHIEN);
								}
								serviceInfo.setApplicantText(motadoituongthuchien);

								//KETQUATHUCHIEN
								sb = new StringBuffer();
								if (_tmp.has(DVCQGIntegrationActionTerm.KETQUATHUCHIEN)) {
									JSONArray ketquathuchien_arr = _tmp.getJSONArray(DVCQGIntegrationActionTerm.KETQUATHUCHIEN);
									if (ketquathuchien_arr != null) {
										for (int i = 0; i < ketquathuchien_arr.length(); i++) {
											JSONObject ketquathuchien_obj = ketquathuchien_arr.getJSONObject(i);
											String maketqua = ketquathuchien_obj.getString(DVCQGIntegrationActionTerm.MAKETQUA);
											String tenketqua = ketquathuchien_obj.getString(DVCQGIntegrationActionTerm.TENKETQUA);
											sb.append(DVCQGIntegrationActionTerm.LABEL_MA_KETQUA + maketqua + StringPool.NEW_LINE);
											sb.append(DVCQGIntegrationActionTerm.LABEL_KETQUA + tenketqua + StringPool.NEW_LINE);
										}

									}

								}
								serviceInfo.setResultText(sb.toString());

								//CANCUPHAPLY
								sb = new StringBuffer();
								if (_tmp.has(DVCQGIntegrationActionTerm.CANCUPHAPLY)) {
									JSONArray cancuphaply_arr = _tmp.getJSONArray(DVCQGIntegrationActionTerm.CANCUPHAPLY);
									if (cancuphaply_arr != null) {
										for (int i = 0; i < cancuphaply_arr.length(); i++) {
											JSONObject cancuphaply_obj = cancuphaply_arr.getJSONObject(i);
											String sovanban = cancuphaply_obj.getString(DVCQGIntegrationActionTerm.SOVANBAN);
											String tenvanban = cancuphaply_obj.getString(DVCQGIntegrationActionTerm.TENVANBAN);
											sb.append(DVCQGIntegrationActionTerm.LABEL_SO_VANBAN + sovanban + StringPool.NEW_LINE);
											sb.append(DVCQGIntegrationActionTerm.LABEL_TEN_VANBAN + tenvanban + StringPool.NEW_LINE);
										}

									}

								}
								serviceInfo.setRegularText(sb.toString());

								//TRANGTHAI
								boolean public_ = true;
								if (_tmp.has(DVCQGIntegrationActionTerm.TRANGTHAI)) {
									int trangthai = _tmp.getInt(DVCQGIntegrationActionTerm.TRANGTHAI);
									if (trangthai != 1) {
										public_ = false;
									}
								}
								serviceInfo.setPublic_(public_);

								//COQUANTHUCHIEN
								sb = new StringBuffer();
								if (_tmp.has(DVCQGIntegrationActionTerm.COQUANTHUCHIEN)) {
									JSONArray coquanthuchien_arr = _tmp.getJSONArray(DVCQGIntegrationActionTerm.COQUANTHUCHIEN);
									if (coquanthuchien_arr != null) {
										for (int i = 0; i < coquanthuchien_arr.length(); i++) {
											JSONObject coquanthuchien_obj = coquanthuchien_arr.getJSONObject(i);
											String tendonvi = coquanthuchien_obj.getString(DVCQGIntegrationActionTerm.TENDONVI);
											String madonvi = coquanthuchien_obj.getString(DVCQGIntegrationActionTerm.MADONVI);
											sb.append(DVCQGIntegrationActionTerm.LABEL_TEN_DONVI + tendonvi + StringPool.NEW_LINE);
											sb.append(DVCQGIntegrationActionTerm.LABEL_MA_DONVI + madonvi + StringPool.NEW_LINE);
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

								if (_tmp.has(DVCQGIntegrationActionTerm.THANHPHANHOSO)) {
									JSONArray thanhphanhoso_arr = _tmp.getJSONArray(DVCQGIntegrationActionTerm.THANHPHANHOSO);
									ServiceInfoActions actions = new ServiceInfoActionsImpl();
									if (thanhphanhoso_arr != null) {
										for (int i = 0; i < thanhphanhoso_arr.length(); i++) {
											JSONObject thanhphanhoso_obj = thanhphanhoso_arr.getJSONObject(i);
											JSONArray giayto_arr = thanhphanhoso_obj.getJSONArray(DVCQGIntegrationActionTerm.GIAYTO);
											if (giayto_arr != null) {
												for (int j = 0; j < giayto_arr.length(); j++) {
													JSONObject giayto_obj = giayto_arr.getJSONObject(j);
													String magiayto = giayto_obj.getString(DVCQGIntegrationActionTerm.MAGIAYTO);
													String tenmaudon = giayto_obj.getString(DVCQGIntegrationActionTerm.TENMAUDON);
													String link = giayto_obj.getString(DVCQGIntegrationActionTerm.URL);
													String tengiayto = giayto_obj.getString(DVCQGIntegrationActionTerm.TENGIAYTO);

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

	
}
