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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
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

import org.apache.commons.text.similarity.CosineSimilarity;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.DVCQGIntegrationAction;
import org.opencps.dossiermgt.action.ServiceInfoActions;
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
			_log.info("----------------------------->>>>>getServiceInfoSimilarity: get data from store: " + _mapChars.size());
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
					_mapItems.put(key, item);
				}

				if (weightIndex >= 0.8) {

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
				item.put("mapped", false);
				_mapItems.put(key, item);

				if (weightIndex >= 0.8) {
			
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

	@Override
	public JSONObject mappingServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCode,
			String serviceCodeDVCQG) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (Validator.isNotNull(serviceCode) && Validator.isNotNull(serviceCodeDVCQG)) {
			try {
				ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.addServiceInfoMapping(
						groupId, serviceContext.getCompanyId(), user.getUserId(), serviceCode, serviceCodeDVCQG);
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
											sb.append(truonghop + "\n");
											JSONArray trinhtu_arr = trinhtuthuchien_obj.getJSONArray("TRINHTU");
											if (trinhtu_arr != null) {
												for (int j = 0; j < trinhtu_arr.length(); j++) {
													String tentrinhtu = trinhtu_arr.getJSONObject(j)
															.getString("TENTRINHTU");
													sb.append(tentrinhtu + "\n");
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
											sb.append(nhankenh + ":\n");

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
															+ "\n");
													durationText += nhankenh + ":" + "- Thời gian giải quyết: "
															+ thoigiangiaiquyet + " " + donvitinh + "\n";
													JSONArray philephi_arr = thoigian_obj.getJSONArray("PHILEPHI");
													if (philephi_arr != null) {
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
																+ "\n";
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
											sb.append("- Mã kết quả:" + maketqua + "\n");
											sb.append("- Kết quả:" + tenketqua + "\n");
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
											sb.append("- Số văn bản: " + sovanban + "\n");
											sb.append("- Tên văn bản: " + tenvanban + "\n");
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
											sb.append("- Tên đơn vị: " + tendonvi + "\n");
											sb.append("- Mã đơn vị: " + madonvi + "\n");
										}
									}
								}

								serviceInfo.setGovAgencyText(sb.toString());

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
	}

	private static HashMap<String, Map<CharSequence, Integer>> _mapChars = null;
	private static HashMap<String, JSONObject> _mapItems = null;
}
