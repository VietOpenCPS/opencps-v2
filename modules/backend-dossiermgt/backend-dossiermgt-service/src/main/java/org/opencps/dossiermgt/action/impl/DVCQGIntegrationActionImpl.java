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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.DVCQGIntegrationAction;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierStatusMapping;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierStatusMappingLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil;

/**
 * @author trungnt
 *
 */
public class DVCQGIntegrationActionImpl implements DVCQGIntegrationAction {

	private Log _log = LogFactoryUtil.getLog(DVCQGIntegrationActionImpl.class);

	private String convertDate2String(Date date) {

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyyMMddHHmmss");

		if (Validator.isNull(date)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

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
		_log.info("-------------->>>> " + _mServiceCode + "|" + _oServiceCode + "|" + groupId);

		object.put("MaHoSo", dossier.getDossierNo());
		object.put("MaTTHC", _mServiceCode);
		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put("service", "LayThuTuc");
		body.put("maTTHC", _mServiceCode);

		JSONObject serviceInfo = getSharingData(config, body, accessToken);
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
		object.put("MaDoiTuong", ""); //ko bb
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
		_log.info("-------------->>>> " + _mServiceCode + "|" + _oServiceCode + "|" + groupId);

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
		object.put("MaDoiTuong", ""); //ko bb
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

	private String getAccessToken(JSONObject config) {

		HttpURLConnection conn = null;

		try {
			String adapter_url = config.getString("adapter_url");
			String auth_endpoint = config.getString("auth_endpoint");
			String username = config.getString("username");
			String password = config.getString("password");

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

		HttpURLConnection conn = null;

		try {
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			return getAccessToken(config);

		} catch (Exception e) {
			_log.error(e);
			return StringPool.BLANK;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	@Override
	public String getAccessToken(User user, ServiceContext serviceContext) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
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
			String adapter_url = config.getString("adapter_url");
			String integration_endpoint = config.getString("integration_endpoint");
			String madonvi = config.getString("madonvi");

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
			String adapter_url = config.getString("adapter_url");
			String integration_endpoint = config.getString("integration_endpoint");
			String madonvi = config.getString("madonvi");
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

			String endpoint = adapter_url + integration_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
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
					body.put("isUpdating", "True");
				} else {
					body.put("isUpdating", "False");
				}
				body.put("data", synsObjects);
				body.put("service", "DongBoHoSoMC");
				result = syncData(serverConfig, body);
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
}
