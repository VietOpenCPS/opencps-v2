package org.opencps.dossiermgt.action.impl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.liferay.portal.kernel.json.*;
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
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.action.util.DossierActionUtils;
import org.opencps.dossiermgt.action.util.DossierFileUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.input.model.DossierInputModel;
import org.opencps.dossiermgt.input.model.SyncTrackingInfo;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.model.impl.ServiceInfoImpl;
import org.opencps.dossiermgt.service.*;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil;
import org.opencps.synctracking.model.SyncTrackingQuery;
import org.opencps.synctracking.service.SyncTrackingLocalServiceUtil;
import org.opencps.usermgt.model.Answer;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.AnswerLocalServiceUtil;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.QuestionLocalServiceUtil;
import org.opencps.usermgt.service.util.DateTimeUtils;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import backend.auth.api.exception.ErrorMsgModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
	private RestTemplate restTemplate;

	private Log _log = LogFactoryUtil.getLog(DVCQGIntegrationActionImpl.class);
	private static final String LUCENE_DATE_FORMAT = "yyyyMMddHHmmss";
	private static final String HCM_TIMEZONE = "Asia/Ho_Chi_Minh";
	private static final String DVCQG_INTEGRATION = "DVCQG_INTEGRATION";

	private static final Integer TIMEOUT_DVCQG = 6;

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
													String accessToken, HttpServletRequest request) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		String _oServiceCode = dossier.getServiceCode();
		String _mServiceCode;
		ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId,
				_oServiceCode);
		if (serviceInfoMapping != null) {
			_mServiceCode = serviceInfoMapping.getServiceCodeDVCQG();
		} else {
			// danh cho truong hop cau hinh ma ttch tren opencps = ma tthc tren dvcqg
			_mServiceCode = _oServiceCode;
		}

		object.put("MaHoSo", dossier.getDossierNo());

		object.put("MaHoSo", dossier.getDossierNo());
		object.put("MaTTHC", _mServiceCode);
		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put("service", "LayThuTuc");
		body.put("maTTHC", _mServiceCode);

		JSONObject serviceInfo = getSharingData(config, body, accessToken);
		// get TenTTHC, MaLinhVuc, TenLinhVuc
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
		// object.put("SoBienNhan", ""); //ko bb
		object.put("ChuHoSo", dossier.getContactName()); // ko bb
		int LoaiDoiTuong = 1;
		if (Validator.isNotNull(dossier.getApplicantIdType())) {
			if ("business".equals(dossier.getApplicantIdType())) {
				LoaiDoiTuong = 2;
			}
		}
//		_log.info("groupId: "+groupId);
//		_log.info("dossier.getApplicantIdNo(): "+dossier.getApplicantIdNo());
		Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, dossier.getApplicantIdNo());
		if (applicant == null || (applicant != null && !"dvcqg".contentEquals(applicant.getMappingClassName()))) {
			applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, dossier.getDelegateIdNo());
		}
		String madoituong = StringPool.BLANK;
		_log.info("applicant: "+JSONFactoryUtil.looseSerialize(applicant));
//		JSONObject jsonApplicant = getSharingData(config, body, accessToken);
		if (applicant != null && "dvcqg".contentEquals(applicant.getMappingClassName())) {
			_log.info("className: "+applicant.getMappingClassName());
			_log.info("madoituong: "+applicant.getMappingClassPK());
			madoituong = applicant.getMappingClassPK();
		}
		object.put("LoaiDoiTuong", String.valueOf(LoaiDoiTuong));
		object.put("MaDoiTuong", madoituong); // ko bb
		object.put("ThongTinKhac", ""); // ko bb
		object.put("Email", dossier.getContactEmail());
		object.put("Fax", dossier.getContactTelNo()); // ko bb
		object.put("SoDienThoai", dossier.getContactTelNo());
		object.put("TrichYeuHoSo", dossier.getDossierName());
		object.put("NgayTiepNhan", convertDate2String(dossier.getReceiveDate()));
		object.put("NgayHenTra", convertDate2String(dossier.getDueDate()));
		object.put("TrangThaiHoSo", getMappingStatus(groupId, dossier));
		object.put("NgayTra", convertDate2String(dossier.getFinishDate()));// ko bb
		object.put("ThongTinTra", "");// ko bb
		int HinhThuc = 0;
		if (dossier.getViaPostal() != 0 && dossier.getViaPostal() != 1) {
			HinhThuc = 1;
		}
		object.put("HinhThuc", String.valueOf(HinhThuc));
		object.put("NgayKetThucXuLy", convertDate2String(dossier.getReleaseDate()));// ko bb
		object.put("DonViXuLy", dossier.getGovAgencyName());
		//Giao remove this because dossierNote length > 2k
//		object.put("GhiChu", dossier.getDossierNote());// ko bb

		JSONArray DanhSachLePhi = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachLePhi", DanhSachLePhi);// ko bb
		// object.put("TenPhiLePhi", "");
		// object.put("MaPhiLePhi", "");
		// object.put("HinhThucThu", "");
		// object.put("Gia", "");
		// object.put("LoaiPhiLePhi", "");

		JSONArray DanhSachTepDinhKemKhac = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachTepDinhKemKhac", DanhSachTepDinhKemKhac);// ko bb
		// object.put("TenGiayTo", "");
		// object.put("SoLuong", "");
		// object.put("LoaiGiayTo", "");

		JSONArray DanhSachHoSoBoSung = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachHoSoBoSung", DanhSachHoSoBoSung);// ko bb
		// object.put("HoSoBoSungId", "");
		// object.put("NguoiYeuCauBoSung", "");
		// object.put("NoiDungBoSung", "");
		// object.put("NgayBoSung", "");
		// object.put("NguoiTiepNhanBoSung", "");
		// object.put("ThongTinTiepNhan", "");
		// object.put("NgayTiepNhanBoSung", "");
		// object.put("TrangThaiBoSung", "");
		// object.put("DanhSachGiayToBoSung", "");
		// object.put("DanhSachLePhiBoSung", "");
		// object.put("NgayHenTraTruoc", "");
		// object.put("NgayHenTraMoi", "");
		List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getAllDossierFile(dossier.getDossierId());

		if (dossierFiles != null) {
			JSONArray DanhSachGiayToKetQua = JSONFactoryUtil.createJSONArray();
			JSONObject data = null;

			JSONArray TaiLieuNop = JSONFactoryUtil.createJSONArray();

			for (DossierFile dossierFile : dossierFiles) {
				if (!dossierFile.isRemoved() && dossierFile.getFileEntryId() > 0
						&& dossierFile.getDossierPartType() > 0) {
					try {
						FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
						String url;
						if (request != null) {
							url = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(),
									(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY), StringPool.BLANK);
						} else {
							StringBundler sb = new StringBundler(11);
							sb.append(OpenCPSConfigUtil.getPortalDocumentURI());
							sb.append(PortalUtil.getPathContext());
							sb.append("/documents/");
							sb.append(fileEntry.getRepositoryId());
							sb.append(StringPool.SLASH);
							sb.append(fileEntry.getFolderId());
							sb.append(StringPool.SLASH);

							String fileName = fileEntry.getFileName();

							sb.append(URLCodec.encodeURL(HtmlUtil.unescape(fileName)));

							sb.append(StringPool.SLASH);
							sb.append(URLCodec.encodeURL(fileEntry.getUuid()));

							sb.append("?t=" + System.currentTimeMillis() + "&download=true");

							url = sb.toString();
						}

						_log.debug("===> file URL " + url);

						if (Validator.isNotNull(url)) {
							data = JSONFactoryUtil.createJSONObject();
							if (dossierFile.getDossierPartType() == 2) {
								data.put("TenGiayTo", dossierFile.getDisplayName());
								// data.put("MaThanhPhanHoSo", dossierFile.getDossierPartNo());
								data.put("MaThanhPhanHoSo", "");
								data.put("GiayToId", String.valueOf(dossierFile.getDossierFileId()));
								data.put("DuongDanTepTinKetQua", url);
								DanhSachGiayToKetQua.put(data);
							} else {
								data.put("TenTepDinhKem", dossierFile.getDisplayName());
								// data.put("MaThanhPhanHoSo", dossierFile.getDossierPartNo());
								data.put("MaThanhPhanHoSo", "");
								data.put("TepDinhKemId", String.valueOf(dossierFile.getDossierFileId()));
								data.put("DuongDanTaiTepTin", url);
								data.put("IsDeleted", "False");
								TaiLieuNop.put(data);
							}
						}

					} catch (Exception e) {
						_log.debug(e);
					}
				}
			}

			object.put("DanhSachGiayToKetQua", DanhSachGiayToKetQua);// ko bb

			object.put("TaiLieuNop", TaiLieuNop);// ko bb

		}
		_log.info("object: "+JSONFactoryUtil.looseSerialize(object));

		return object;
	}

	private JSONObject createSyncDossierBodyRequest(long groupId, Dossier dossier, ServerConfig serverConfig,
													HttpServletRequest request) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		String _oServiceCode = dossier.getServiceCode();
		String _mServiceCode;
		ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId,
				_oServiceCode);
		if (serviceInfoMapping != null) {
			_mServiceCode = serviceInfoMapping.getServiceCodeDVCQG();
		} else {
			// danh cho truong hop cau hinh ma ttch tren opencps = ma tthc tren dvcqg
			_mServiceCode = _oServiceCode;
		}

		_log.debug("-------------->>>> " + _mServiceCode + StringPool.PIPE + _oServiceCode + StringPool.PIPE + groupId);

		object.put("MaHoSo", dossier.getDossierNo());

		object.put("MaTTHC", _mServiceCode);
		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put("service", "LayThuTuc");
		body.put("maTTHC", _mServiceCode);

		JSONObject serviceInfo = getSharingData(serverConfig, body);
		// get TenTTHC, MaLinhVuc, TenLinhVuc
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
		// object.put("SoBienNhan", ""); //ko bb
		object.put("ChuHoSo", dossier.getContactName()); // ko bb
		int LoaiDoiTuong = 1;
		if (Validator.isNotNull(dossier.getApplicantIdType())) {
			if ("business".equals(dossier.getApplicantIdType())) {
				LoaiDoiTuong = 2;
			}
		}
		object.put("LoaiDoiTuong", String.valueOf(LoaiDoiTuong));
		Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, dossier.getApplicantIdNo());
		String madoituong = StringPool.BLANK;
		if (applicant != null && "dvcqg".contentEquals(applicant.getMappingClassName())) {
			madoituong = applicant.getMappingClassPK();
		}
		object.put("MaDoiTuong", madoituong); // ko bb
		object.put("ThongTinKhac", ""); // ko bb
		object.put("Email", dossier.getContactEmail());
		object.put("Fax", dossier.getContactTelNo()); // ko bb
		object.put("SoDienThoai", dossier.getContactTelNo());
		object.put("TrichYeuHoSo", dossier.getDossierName());
		object.put("NgayTiepNhan", convertDate2String(dossier.getReceiveDate()));
		object.put("NgayHenTra", convertDate2String(dossier.getDueDate()));
		object.put("TrangThaiHoSo", getMappingStatus(groupId, dossier));
		object.put("NgayTra", convertDate2String(dossier.getFinishDate()));// ko bb
		object.put("ThongTinTra", "");// ko bb
		int HinhThuc = 0;
		if (dossier.getViaPostal() != 0 && dossier.getViaPostal() != 1) {
			HinhThuc = 1;
		}
		object.put("HinhThuc", String.valueOf(HinhThuc));
		object.put("NgayKetThucXuLy", convertDate2String(dossier.getReleaseDate()));// ko bb
		object.put("DonViXuLy", dossier.getGovAgencyName());
		//Giao remove this because dossierNote length > 2k
//		object.put("GhiChu", dossier.getDossierNote());// ko bb

		JSONArray DanhSachLePhi = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachLePhi", DanhSachLePhi);// ko bb
		// object.put("TenPhiLePhi", "");
		// object.put("MaPhiLePhi", "");
		// object.put("HinhThucThu", "");
		// object.put("Gia", "");
		// object.put("LoaiPhiLePhi", "");

		JSONArray DanhSachTepDinhKemKhac = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachTepDinhKemKhac", DanhSachTepDinhKemKhac);// ko bb
		// object.put("TenGiayTo", "");
		// object.put("SoLuong", "");
		// object.put("LoaiGiayTo", "");

		JSONArray DanhSachHoSoBoSung = JSONFactoryUtil.createJSONArray();
		object.put("DanhSachHoSoBoSung", DanhSachHoSoBoSung);// ko bb
		// object.put("HoSoBoSungId", "");
		// object.put("NguoiYeuCauBoSung", "");
		// object.put("NoiDungBoSung", "");
		// object.put("NgayBoSung", "");
		// object.put("NguoiTiepNhanBoSung", "");
		// object.put("ThongTinTiepNhan", "");
		// object.put("NgayTiepNhanBoSung", "");
		// object.put("TrangThaiBoSung", "");
		// object.put("DanhSachGiayToBoSung", "");
		// object.put("DanhSachLePhiBoSung", "");
		// object.put("NgayHenTraTruoc", "");
		// object.put("NgayHenTraMoi", "");

		List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getAllDossierFile(dossier.getDossierId());

		if (dossierFiles != null) {
			JSONArray DanhSachGiayToKetQua = JSONFactoryUtil.createJSONArray();
			JSONObject data = null;

			JSONArray TaiLieuNop = JSONFactoryUtil.createJSONArray();

			for (DossierFile dossierFile : dossierFiles) {
				if (!dossierFile.isRemoved() && dossierFile.getFileEntryId() > 0
						&& dossierFile.getDossierPartType() > 0) {
					try {
						FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
						String url;
						if (request != null) {
							url = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(),
									(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY), StringPool.BLANK);
						} else {
							StringBundler sb = new StringBundler(11);
							sb.append(OpenCPSConfigUtil.getPortalDocumentURI());
							sb.append(PortalUtil.getPathContext());
							sb.append("/documents/");
							sb.append(fileEntry.getRepositoryId());
							sb.append(StringPool.SLASH);
							sb.append(fileEntry.getFolderId());
							sb.append(StringPool.SLASH);

							String fileName = fileEntry.getFileName();

							sb.append(URLCodec.encodeURL(HtmlUtil.unescape(fileName)));

							sb.append(StringPool.SLASH);
							sb.append(URLCodec.encodeURL(fileEntry.getUuid()));

							sb.append("?t=" + System.currentTimeMillis() + "&download=true");

							url = sb.toString();
						}
						_log.debug("===> file URL " + url);
						if (Validator.isNotNull(url)) {
							data = JSONFactoryUtil.createJSONObject();
							if (dossierFile.getDossierPartType() == 2) {
								data.put("TenGiayTo", dossierFile.getDisplayName());
								data.put("MaThanhPhanHoSo", dossierFile.getDossierPartNo());
								data.put("GiayToId", String.valueOf(dossierFile.getDossierFileId()));
								data.put("DuongDanTepTinKetQua", url);
								DanhSachGiayToKetQua.put(data);
							} else {
								data.put("TenTepDinhKem", dossierFile.getDisplayName());
								data.put("MaThanhPhanHoSo", dossierFile.getDossierPartNo());
								data.put("TepDinhKemId", String.valueOf(dossierFile.getDossierFileId()));
								data.put("DuongDanTaiTepTin", url);
								data.put("IsDeleted", "False");
								TaiLieuNop.put(data);
							}
						}

					} catch (Exception e) {
						_log.debug(e);
					}
				}
			}

			object.put("DanhSachGiayToKetQua", DanhSachGiayToKetQua);// ko bb

			object.put("TaiLieuNop", TaiLieuNop);// ko bb

		}

		return object;
	}

	private Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	private JSONObject createSyncTTKMDossierBodyRequest(long groupId, Dossier dossier, ServerConfig serverConfig,
														HttpServletRequest request) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		object.put("MaHoSo", dossier.getReferenceUid());
		object.put("MaHoSoDonVi", dossier.getDossierNo());
		Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, dossier.getApplicantIdNo());
		String madoituong = StringPool.BLANK;
		if (applicant != null && "dvcqg".contentEquals(applicant.getMappingClassName())) {
			madoituong = applicant.getMappingClassPK();
		}
		object.put("MaDoiTuong", madoituong); // ko bb
		object.put("NoiDung", dossier.getDossierNote());// ko bb

		Date ngayXuLy = dossier.getReceiveDate() != null ? dossier.getReceiveDate() : getCurrentDate();
		object.put("NgayXuLy", convertDate2String(ngayXuLy));

		int trangthai = GetterUtil.getInteger(getMappingStatus(groupId, dossier));

		//2 = tiep nhan, 5 = Yeu cau bo sung/ tu choi
		if (trangthai == 4 || trangthai == 6  || trangthai == 9 || trangthai == 10 ) {
			trangthai = 2;
		} else if (trangthai == 3 || trangthai == 7 || trangthai == 8) {
			trangthai = 5;
		}

		object.put("TrangThai", String.valueOf(trangthai));

		List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getAllDossierFile(dossier.getDossierId());

		if (dossierFiles != null) {

			JSONObject data = null;

			JSONArray taiLieuXuLy = JSONFactoryUtil.createJSONArray();

			for (DossierFile dossierFile : dossierFiles) {
				if (!dossierFile.isRemoved() && dossierFile.getFileEntryId() > 0
						&& dossierFile.getDossierPartType() > 0) {
					try {
						FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
						String url;
						if (request != null) {
							url = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(),
									(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY), StringPool.BLANK);
						} else {
							StringBundler sb = new StringBundler(11);
							sb.append(OpenCPSConfigUtil.getPortalDocumentURI());
							sb.append(PortalUtil.getPathContext());
							sb.append("/documents/");
							sb.append(fileEntry.getRepositoryId());
							sb.append(StringPool.SLASH);
							sb.append(fileEntry.getFolderId());
							sb.append(StringPool.SLASH);

							String fileName = fileEntry.getFileName();

							sb.append(URLCodec.encodeURL(HtmlUtil.unescape(fileName)));

							sb.append(StringPool.SLASH);
							sb.append(URLCodec.encodeURL(fileEntry.getUuid()));

							sb.append("?t=" + System.currentTimeMillis() + "&download=true");

							url = sb.toString();
						}
						_log.debug("===> file URL " + url);
						if (Validator.isNotNull(url)) {
							data = JSONFactoryUtil.createJSONObject();
							data.put("TenTepDinhKem", dossierFile.getDisplayName());
							data.put("MaThanhPhanHoSo", dossierFile.getDossierPartNo());
							data.put("TepDinhKemId", String.valueOf(dossierFile.getDossierFileId()));
							data.put("DuongDanTaiTepTin", url);
							data.put("IsDeleted", "False");
							taiLieuXuLy.put(data);

						}

					} catch (Exception e) {
						_log.debug(e);
					}
				}
			}

			object.put("TaiLieuXuLy", taiLieuXuLy);// ko bb

		}

		return object;
	}

	private JSONObject createSyncDossierStatusBodyRequest(long groupId, Dossier dossier) {
		JSONObject object = JSONFactoryUtil.createJSONObject();
		object.put("MaHoSo", dossier.getDossierNo());

		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		if (dossier.getOriginality() == 0) {
			object.put("NguoiXuLy", dossier.getUserName());
		} else {
			object.put("NguoiXuLy", dossierAction != null ? dossierAction.getActionUser() : StringPool.BLANK);
		}

		object.put("ChucDanh", "");// ko bb
		if (dossier.getOriginality() == 0) {
			object.put("ThoiDiemXuLy", convertDate2String(dossier.getModifiedDate()));
		} else {
			object.put("ThoiDiemXuLy",
					dossierAction != null ? convertDate2String(dossierAction.getCreateDate()) : StringPool.BLANK);
		}

		//sequenceRole- PhongBanXuLy
		//
		String sequenceRole      = "";
		String dateReturnDossier = "";
		try {
			String submissionNote = dossier.getSubmissionNote();

			if(Validator.isNull(submissionNote)) {
				throw new Exception("No submissionNote found with dossierId: " + dossier.getDossierId());
			}

			JSONObject subJson = JSONFactoryUtil.createJSONObject(submissionNote);
			if(Validator.isNull(subJson) || !subJson.has("data")) {
				throw new Exception("No json found for submissionNote with dossierId: " + dossier.getDossierId());
			}

			JSONArray data = subJson.getJSONArray("data");
			JSONObject oneData;
			JSONObject oneAction;
			JSONArray actions;
			String actionCode;

			for(int i = 0; i< data.length(); i++) {
				oneData = data.getJSONObject(i);
				if(Validator.isNull(oneData)) {
					continue;
				}
				actions = oneData.getJSONArray("actions");
				if(Validator.isNull(actions) || actions.length() ==0) {
					continue;
				}
				oneAction = actions.getJSONObject(0);
				if(Validator.isNull(oneAction)) {
					continue;
				}
				actionCode = oneAction.getString("actionCode");

				if(dossierAction != null) {
					if(Validator.isNotNull(dossierAction.getActionCode()) &&
							actionCode.equals(dossierAction.getActionCode())) {
						sequenceRole = oneData.getString("sequenceRole");
						if(oneAction.has("dueDate") && !oneAction.getString("dueDate").isEmpty()) {
							Date date = new Date(Long.parseLong(oneAction.getString("dueDate")));
							dateReturnDossier = convertDate2String(date);
						}
					}
				}
			}
		}catch (Exception e) {
			_log.error(e);
			e.printStackTrace();
		}

		object.put("PhongBanXuLy", sequenceRole);// ko bb
		String processContent = "";
		if(Validator.isNotNull(dossierAction)) {
			String actionName = Validator.isNotNull(dossierAction.getActionName())
					? dossierAction.getActionName(): StringPool.BLANK;
			String actionNote = Validator.isNotNull(dossierAction.getActionNote())
					? dossierAction.getActionNote(): StringPool.BLANK;
			if(!actionName.isEmpty()) {
				processContent += actionName;
				if(!actionNote.isEmpty()) {
					processContent += "-" + actionNote;
				}
			}
		}

		object.put("NoiDungXuLy", processContent);
		object.put("TrangThai", getMappingStatus(groupId, dossier));
		object.put("NgayBatDau", "");// ko bb
		object.put("NgayKetThucTheoQuyDinh", dateReturnDossier);// ko bb

		return object;
	}

	@Override
	public JSONObject doSyncGovernmentAgency(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject qajson;
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

					JSONObject item;

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
		JSONObject qajson;
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
		JSONObject qajson;
		_log.info("-->>>>>>>> doSyncServiceDomain: " + serverConfigs + "|" + serverConfigs.size());
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				if (!data.has("service")) {
					data.put("service", "LayDanhMucLinhVuc");
				}
				qajson = getSharingDictCollection(serverConfig, data);
				// _log.info("-->>>>>>>>>>>>>>>>>>>>> " + qajson);
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

							// _log.info("------>>>>> " + serviceContext.getScopeGroupId() + "|" +
							// malinhvuc);

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

					// _log.info("---------------->>>>>>>> result " + result.toString());
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

				/*
				 * String diachi = StringPool.BLANK; if (qajson.has("DIACHI")) { diachi =
				 * qaobj.getString("DIACHI"); }
				 */

				String thudientu = StringPool.BLANK;
				if (qajson.has("THUDIENTU")) {
					thudientu = qaobj.getString("THUDIENTU");
				}

				/*
				 * String sodienthoai = StringPool.BLANK; if (qajson.has("SODIENTHOAI")) {
				 * sodienthoai = qaobj.getString("SODIENTHOAI"); }
				 */

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

				String domainName;

				String domainCode;

				String domainCodeTemp;
				// Dung cho phuong an replace
				if (Validator.isNotNull(malinhvuc)) {
					// domainCode = malinhvuc;
					domainCodeTemp = malinhvuc;
				} else {
					// domainCode = matthcdp;
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
				// chua dong bo
				if (question == null) {
					question = QuestionLocalServiceUtil.updateQuestion(serviceContext.getCompanyId(),
							serviceContext.getScopeGroupId(), 0,
							Validator.isNotNull(hovaten) ? hovaten : user.getFullName(), thudientu, noidung, trangthai,
							domainCode, domainName, govAgencyItem.getItemCode(), govAgencyItem.getItemName(),
							Validator.isNotNull(hovaten) ? "faq" : StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
							"dvcqg_question", String.valueOf(hoidapid), 1);

				} else {
					// da dong bo ->dong bo lai va lay d/s cau tra loi da dong bo

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

				// dong bo cau tra loi
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

	private String getAccessToken(long companyId, long groupId, String className, JSONObject config) {

		AccessToken accessToken = AccessTokenLocalServiceUtil.getAccessToken(groupId, className);

		if (accessToken != null) {
			_log.info("getAccesToken form DB " + accessToken.getToken());
			return accessToken.getToken();
		}

		HttpURLConnection conn = null;

		try {
			_log.debug("config: " + config.toJSONString());

			String adapter_url = config.getString("adapter_url");
			String auth_endpoint = config.getString("auth_endpoint");
			String username = config.getString("username");
			String password = config.getString("password");
			String dstcode = config.getString("dstcode");
			_log.debug("dstcode: " + dstcode);
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

			if(Validator.isNotNull(dstcode)) {
				conn.setRequestProperty("dstcode", dstcode);
			}
			conn.setInstanceFollowRedirects(true);
			HttpURLConnection.setFollowRedirects(true);
			conn.setReadTimeout(TIMEOUT_DVCQG * 1000);

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
					String session = result.getString("session");
					AccessTokenLocalServiceUtil.addAccessToken(companyId, groupId, session, className);
					return session;
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

		// HttpURLConnection conn = null;

		try {
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			return getAccessToken(serverConfig.getCompanyId(), serverConfig.getGroupId(), "dvcqg", config);

		} catch (Exception e) {
			_log.error(e);
			return StringPool.BLANK;
		}
		// finally {
		// if (conn != null) {
		// conn.disconnect();
		// }
		// }
	}

	@Override
	public String getAccessToken(User user, HttpServletRequest request, HttpServletResponse response,
								 ServiceContext serviceContext) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);

			String accessToken = getAccessToken(serverConfig);

			/*
			 * HttpSession session = request.getSession();
			 *
			 * String accessToken = (String)session.getAttribute("ACCESS_TOKEN");
			 *
			 * if(Validator.isNull(accessToken)) {
			 *
			 * accessToken = getAccessToken(serverConfig);
			 *
			 * session.setAttribute("ACCESS_TOKEN", accessToken); }
			 */

			return accessToken;

		}
		return StringPool.BLANK;

	}

	private JSONArray getAnswer(User user, ServiceContext serviceContext, JSONObject data) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject qajson;
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
				dataRow.put("mappingClassPK", 0);
				dataRow.put("serviceInfoId", 0);
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
				dataRow.put("serviceInfoId", serviceInfo != null ? serviceInfo.getServiceInfoId() : 0);
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
					if ("LayDanhMucLinhVuc".equals(service)) {
						dictItemDVCQGMap.put(item.getString("MALINHVUC"), item.getString("TENLINHVUC"));
					} else if ("LayDanhMucCoQuan".equals(service)) {
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
					if ("LayDanhMucLinhVuc".equals(service)) {
						dataRow.put("itemCodeDVCQG", item.getString("MALINHVUC"));
						dataRow.put("itemNameDVCQG", item.getString("TENLINHVUC"));
						data.put(dataRow);
					} else if ("LayDanhMucCoQuan".equals(service)) {

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
				// _log.info(key + "|" + name);
				Map<CharSequence, Integer> vectorB = null;
				if (_serviceInfoMapChars.containsKey(key)) {
					vectorB = _serviceInfoMapChars.get(key);
				} else {
					vectorB = Arrays.stream(name.split(" "))
							.collect(Collectors.toMap(character -> character, character -> 1, Integer::sum));

					_serviceInfoMapChars.put(key, vectorB);
				}

				Double weightIndex = documentsSimilarity.cosineSimilarity(vectorA, vectorB);
				// _log.info(weightIndex);
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

				if(_dictItemMapChars != null) {
					_dictItemMapChars.put(key, vectorB);
				}

				Double weightIndex = documentsSimilarity.cosineSimilarity(vectorA, vectorB);

				JSONObject item = JSONFactoryUtil.createJSONObject();
				item.put("itemCodeDVCQG", key);
				item.put("itemNameDVCQG", entry.getValue());
				item.put("dictItemMappingId", 0);
				item.put("mapped", false);

				if(_dictItemMapItems != null) {
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
			conn.setReadTimeout(TIMEOUT_DVCQG * 1000);

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

				// System.out.println("response: " + sb.toString());

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
			String accessToken = getAccessToken(serverConfig.getCompanyId(), serverConfig.getGroupId(), "dvcqg",
					config);
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
			conn.setReadTimeout(TIMEOUT_DVCQG * 1000);

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
			_log.info("config: "+JSONFactoryUtil.looseSerialize(config));

			String adapter_url = config.getString("adapter_url");
			String share_endpoint = config.getString("share_endpoint");
			String madonvi = config.getString("madonvi");
			String dstcode = config.getString("dstcode");
			_log.info("adapter_url: "+adapter_url);
			_log.info("share_endpoint: "+share_endpoint);
			_log.info("madonvi: "+madonvi);
			_log.info("dstcode: "+dstcode);
			String accessToken = getAccessToken(serverConfig.getCompanyId(), serverConfig.getGroupId(), "dvcqg",
					config);
			_log.info("accessToken: "+accessToken);
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
			_log.info("endpoint: "+endpoint);

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
			conn.setReadTimeout(TIMEOUT_DVCQG * 1000);

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

				_log.info("sb.toString(): "+sb.toString());
				// System.out.println("response: " + sb.toString());

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

	private boolean hasSyncDossier(Dossier dossier, JSONObject config, String accessToken) {
		try {
			String dossierNo = dossier.getDossierNo();
			// duan idea
			List<PublishQueue> publishQueue =  PublishQueueLocalServiceUtil.getByG_DID_SN_NST(dossier.getGroupId(),
					dossier.getDossierId(), ServerConfigTerm.DVCQG_INTEGRATION, PublishQueueTerm.STATE_RECEIVED_ACK);

			if(Validator.isNotNull(publishQueue) && publishQueue.size() > 0) {
				_log.info("Dossier " + dossierNo + " has been created on DVCQG");
				return true;
			} else {
				_log.info("Dossier " + dossierNo + " does not existed on DVCQG or not appear in queue");
			}

			// trung idea
			JSONObject searchData = searchDossier(dossierNo, config, accessToken);
			if (searchData != null && searchData.has("result")) {
				JSONArray result = searchData.getJSONArray("result");
				if (result != null && result.length() > 0) {
					return true;
				}

			}
		} catch (Exception e) {
			_log.error(e);
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
			String accessToken = getAccessToken(serverConfig.getCompanyId(), serverConfig.getGroupId(), "dvcqg",
					config);
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
			conn.setReadTimeout(TIMEOUT_DVCQG * 1000);

			byte[] postData = body.toJSONString().getBytes("UTF-8");
			_log.debug("body:" + body.toJSONString());
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

				_log.debug("response: " + sb.toString());

				result = JSONFactoryUtil.createJSONObject(sb.toString());

				return result;

			}

		} catch (Exception e) {
			_log.error(e);
			//must null to check timeout of calling api
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	@Override
	public JSONObject syncDossier(User user, long groupId, ServiceContext serviceContext, String strDossierId,
								  String isUpdating, HttpServletRequest request) {
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

					if (dossier.getSystemId() == 5) {

						continue;
					}

					JSONObject synsObject = createSyncDossierBodyRequest(groupId, dossier, serverConfig, request);
					_log.debug(synsObject.toJSONString());
					synsObjects.put(synsObject);
				}
			}

			if (synsObjects.length() > 0) {
				JSONObject body = JSONFactoryUtil.createJSONObject();
				body.put("isUpdating", isUpdating);
				body.put("service", "DongBoHoSoMC");
				body.put("data", synsObjects);
				result = syncData(serverConfig, body);
				if(Validator.isNull(result)) {
					return JSONFactoryUtil.createJSONObject();
				}
			}

		}
		return result;
	}

	public JSONObject syncDossierAndDossierStatus(long groupId, Dossier dossier, HttpServletRequest request)
			throws JSONException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String accessToken = getAccessToken(serverConfig.getCompanyId(), serverConfig.getGroupId(), "dvcqg",
					config);

			JSONArray synsObjects = JSONFactoryUtil.createJSONArray();
			if (Validator.isNotNull(accessToken)) {

				if (dossier.getSystemId() == 5) {
					JSONObject synsObject = createSyncTTKMDossierBodyRequest(groupId, dossier, serverConfig, request);
					JSONObject body = JSONFactoryUtil.createJSONObject();
					body.put("service", "DongBoTrangThaiHoSo");
					body.put("type", "2");
					body.put("data", synsObject);
					_log.info("DongBoTrangThaiHoSoTTKM: " + synsObject.toJSONString());
					result = syncData(serverConfig, body);

				} else {
					boolean hasSync = hasSyncDossier(dossier, config, accessToken);
					JSONObject synsObject = createSyncDossierBodyRequest(groupId, dossier, config, accessToken,
							request);

					synsObjects.put(synsObject);
					_log.info("hasSync" + hasSync);
					_log.info("syncDossierAndDossierStatus synsObjects " + synsObjects.toJSONString());
					JSONObject body = JSONFactoryUtil.createJSONObject();

					if (hasSync) {
						body.put("isUpdating", "True");
					} else {
						body.put("isUpdating", "False");
					}
					body.put("data", synsObjects);
					body.put("service", "DongBoHoSoMC");
					result = syncData(serverConfig, body);
					_log.info("syncDossierAndDossierStatus " + result.toJSONString());
					if (result.has("error_code") && GetterUtil.getInteger(result.getString("error_code")) == 0) {
						body = JSONFactoryUtil.createJSONObject();
						synsObjects = JSONFactoryUtil.createJSONArray();
						JSONObject _tmp = createSyncDossierStatusBodyRequest(groupId, dossier);
						synsObjects.put(_tmp);
						_log.debug("syncDossierAndDossierStatus synsObjects " + synsObjects.toJSONString());
						body.put("service", "CapNhatTienDoHoSoMC");
						body.put("data", synsObjects);
						result = syncData(serverConfig, body);
					}
				}
				return result;
			}
			//no access token
			return null;
		}
		_log.info("No server config with dossierId: " + dossier.getDossierId());
		return result;
	}

	@Override
	public JSONObject syncDossierStatus(User user, long groupId, ServiceContext serviceContext, String strDossierId,
										HttpServletRequest request) {
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
			_log.debug("syncDossierStatus " + synsObjects);
			JSONObject body = JSONFactoryUtil.createJSONObject();
			body.put("service", "CapNhatTienDoHoSoMC");
			body.put("data", synsObjects);
			JSONObject resultNew = syncData(serverConfig, body);
			if(Validator.isNull(resultNew)) {
				return JSONFactoryUtil.createJSONObject();
			}
			return resultNew;
		}
		return result;
	}

	/*
	 * @Deprecated
	 *
	 * @Override public JSONObject syncServiceInfo(User user, long groupId,
	 * ServiceContext serviceContext, String serviceCodes) { List<ServerConfig>
	 * serverConfigs =
	 * ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION"); JSONObject
	 * result = JSONFactoryUtil.createJSONObject();
	 * _log.info("-->>>>>>>> syncServiceInfo: " + serverConfigs + "|" +
	 * serverConfigs.size()); if (serverConfigs != null && !serverConfigs.isEmpty()
	 * && Validator.isNotNull(serviceCodes)) { try { ServerConfig serverConfig =
	 * serverConfigs.get(0); JSONObject config =
	 * JSONFactoryUtil.createJSONObject(serverConfig.getConfigs()); String
	 * accessToken = getAccessToken(config); String[] arrayServiceCode =
	 * StringUtil.split(serviceCodes);
	 *
	 * JSONObject body = JSONFactoryUtil.createJSONObject();
	 *
	 * body.put("service", "LayThuTuc");
	 *
	 * for (String serviceCode : arrayServiceCode) { ServiceInfoMapping
	 * serviceInfoMapping = ServiceInfoMappingLocalServiceUtil
	 * .fetchDVCQGServiceCode(groupId, serviceCode); ServiceInfo serviceInfo =
	 * ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode); if
	 * (serviceInfoMapping != null &&
	 * Validator.isNotNull(serviceInfoMapping.getServiceCodeDVCQG()) && serviceInfo
	 * != null) {
	 *
	 * body.put("maTTHC", serviceInfoMapping.getServiceCodeDVCQG());
	 *
	 * JSONObject serviceInfoDVCQG = getSharingData(config, body, accessToken);
	 *
	 * if (serviceInfoDVCQG != null && serviceInfoDVCQG.has("result")) { JSONArray
	 * results = serviceInfoDVCQG.getJSONArray("result"); if (results.length() > 0)
	 * { JSONObject _tmp = results.getJSONObject(0); StringBuffer sb = null;
	 * //TENTTHC String tentthc = _tmp.getString("TENTTHC");
	 * serviceInfo.setServiceName(tentthc); //TRINHTUTHUCHIEN sb = new
	 * StringBuffer(); if (_tmp.has("TRINHTUTHUCHIEN")) { JSONArray
	 * trinhtuthuchien_arr = _tmp.getJSONArray("TRINHTUTHUCHIEN"); if
	 * (trinhtuthuchien_arr != null) { for (int i = 0; i <
	 * trinhtuthuchien_arr.length(); i++) { JSONObject trinhtuthuchien_obj =
	 * trinhtuthuchien_arr.getJSONObject(i); String truonghop =
	 * trinhtuthuchien_obj.getString("TRUONGHOP"); sb.append(truonghop + "<br>");
	 * JSONArray trinhtu_arr = trinhtuthuchien_obj.getJSONArray("TRINHTU"); if
	 * (trinhtu_arr != null) { for (int j = 0; j < trinhtu_arr.length(); j++) {
	 * String tentrinhtu = trinhtu_arr.getJSONObject(j) .getString("TENTRINHTU");
	 * sb.append(tentrinhtu + "<br>"); } } }
	 *
	 * } } serviceInfo.setProcessText(sb.toString());
	 *
	 * //CACHTHUCTHUCHIEN sb = new StringBuffer(); String durationText =
	 * StringPool.BLANK; String feeText = StringPool.BLANK; if
	 * (_tmp.has("CACHTHUCTHUCHIEN")) { JSONArray cachthucthuchien_arr =
	 * _tmp.getJSONArray("CACHTHUCTHUCHIEN"); if (cachthucthuchien_arr != null) {
	 * for (int i = 0; i < cachthucthuchien_arr.length(); i++) { JSONObject
	 * cachthucthuchien_obj = cachthucthuchien_arr.getJSONObject(i); int kenh =
	 * cachthucthuchien_obj.getInt("KENH"); String nhankenh = "Trực tiếp"; if (kenh
	 * == 2) { nhankenh = "Trực tuyến"; } else if (kenh == 3) { nhankenh =
	 * "Nộp qua bưu chính công ích"; } sb.append(nhankenh + ":<br>");
	 *
	 * JSONArray thoigian_arr = cachthucthuchien_obj.getJSONArray("THOIGIAN");
	 *
	 * if (thoigian_arr != null) { for (int j = 0; j < thoigian_arr.length(); j++) {
	 * JSONObject thoigian_obj = thoigian_arr.getJSONObject(j); int
	 * thoigiangiaiquyet = thoigian_obj.getInt("THOIGIANGIAIQUYET"); String
	 * donvitinh = thoigian_obj.getString("DONVITINH"); String mota =
	 * thoigian_obj.getString("MOTA"); sb.append("- Thời gian giải quyết: " +
	 * thoigiangiaiquyet + " " + donvitinh + (Validator.isNotNull(mota) ? "(" + mota
	 * + ")" : StringPool.BLANK) + "<br>"); durationText += nhankenh + ":" +
	 * "- Thời gian giải quyết: " + thoigiangiaiquyet + " " + donvitinh + "<br>";
	 * JSONArray philephi_arr = thoigian_obj.getJSONArray("PHILEPHI"); if
	 * (philephi_arr != null && philephi_arr.length() > 0) { String maphilephi =
	 * philephi_arr.getJSONObject(0) .getString("MAPHILEPHI"); double sotien =
	 * philephi_arr.getJSONObject(0) .getDouble("SOTIEN"); String donvi =
	 * philephi_arr.getJSONObject(0).getString("DONVI"); String lephimota =
	 * philephi_arr.getJSONObject(0) .getString("MOTA"); feeText += nhankenh + ":" +
	 * maphilephi + ", " + sotien + " " + donvi + (Validator.isNotNull(lephimota) ?
	 * "(" + lephimota + ")" : StringPool.BLANK) + "<br>"; } } } } } }
	 * serviceInfo.setMethodText(sb.toString()); //durationText
	 * serviceInfo.setDurationText(durationText); //feeText
	 * serviceInfo.setFeeText(feeText);
	 *
	 * //YEUCAU String yeucau = StringPool.BLANK; if (_tmp.has("YEUCAU")) { yeucau =
	 * _tmp.getString("YEUCAU"); } serviceInfo.setConditionText(yeucau);
	 *
	 * //MOTADOITUONGTHUCHIEN String motadoituongthuchien = StringPool.BLANK; if
	 * (_tmp.has("MOTADOITUONGTHUCHIEN")) { motadoituongthuchien =
	 * _tmp.getString("MOTADOITUONGTHUCHIEN"); }
	 * serviceInfo.setApplicantText(motadoituongthuchien);
	 *
	 * //KETQUATHUCHIEN sb = new StringBuffer(); if (_tmp.has("KETQUATHUCHIEN")) {
	 * JSONArray ketquathuchien_arr = _tmp.getJSONArray("KETQUATHUCHIEN"); if
	 * (ketquathuchien_arr != null) { for (int i = 0; i <
	 * ketquathuchien_arr.length(); i++) { JSONObject ketquathuchien_obj =
	 * ketquathuchien_arr.getJSONObject(i); String maketqua =
	 * ketquathuchien_obj.getString("MAKETQUA"); String tenketqua =
	 * ketquathuchien_obj.getString("TENKETQUA"); sb.append("- Mã kết quả:" +
	 * maketqua + "<br>"); sb.append("- Kết quả:" + tenketqua + "<br>"); }
	 *
	 * }
	 *
	 * } serviceInfo.setResultText(sb.toString());
	 *
	 * //CANCUPHAPLY sb = new StringBuffer(); if (_tmp.has("CANCUPHAPLY")) {
	 * JSONArray cancuphaply_arr = _tmp.getJSONArray("CANCUPHAPLY"); if
	 * (cancuphaply_arr != null) { for (int i = 0; i < cancuphaply_arr.length();
	 * i++) { JSONObject cancuphaply_obj = cancuphaply_arr.getJSONObject(i); String
	 * sovanban = cancuphaply_obj.getString("SOVANBAN"); String tenvanban =
	 * cancuphaply_obj.getString("TENVANBAN"); sb.append("- Số văn bản: " + sovanban
	 * + "<br>"); sb.append("- Tên văn bản: " + tenvanban + "<br>"); }
	 *
	 * }
	 *
	 * } serviceInfo.setRegularText(sb.toString());
	 *
	 * //TRANGTHAI boolean public_ = true; if (_tmp.has("TRANGTHAI")) { int
	 * trangthai = _tmp.getInt("TRANGTHAI"); if (trangthai != 1) { public_ = false;
	 * } } serviceInfo.setPublic_(public_);
	 *
	 * //COQUANTHUCHIEN sb = new StringBuffer(); if (_tmp.has("COQUANTHUCHIEN")) {
	 * JSONArray coquanthuchien_arr = _tmp.getJSONArray("COQUANTHUCHIEN"); if
	 * (coquanthuchien_arr != null) { for (int i = 0; i <
	 * coquanthuchien_arr.length(); i++) { JSONObject coquanthuchien_obj =
	 * coquanthuchien_arr.getJSONObject(i); String tendonvi =
	 * coquanthuchien_obj.getString("TENDONVI"); String madonvi =
	 * coquanthuchien_obj.getString("MADONVI"); sb.append("- Tên đơn vị: " +
	 * tendonvi + "<br>"); sb.append("- Mã đơn vị: " + madonvi + "<br>"); } } }
	 *
	 * serviceInfo.setGovAgencyText(sb.toString());
	 *
	 * ServiceInfoLocalServiceUtil.updateServiceInfo(serviceInfo);
	 *
	 * //THANHPHANHOSO List<ServiceFileTemplate> serviceFileTemplates =
	 * ServiceFileTemplateLocalServiceUtil
	 * .getByServiceInfoId(serviceInfo.getServiceInfoId()); if (serviceFileTemplates
	 * != null) { for (ServiceFileTemplate serviceFileTemplate :
	 * serviceFileTemplates) {
	 * ServiceFileTemplateLocalServiceUtil.removeServiceFileTemplate(
	 * serviceInfo.getServiceInfoId(), serviceFileTemplate.getFileTemplateNo()); } }
	 *
	 * if (_tmp.has("THANHPHANHOSO")) { JSONArray thanhphanhoso_arr =
	 * _tmp.getJSONArray("THANHPHANHOSO"); ServiceInfoActions actions = new
	 * ServiceInfoActionsImpl(); if (thanhphanhoso_arr != null) { for (int i = 0; i
	 * < thanhphanhoso_arr.length(); i++) { JSONObject thanhphanhoso_obj =
	 * thanhphanhoso_arr.getJSONObject(i); JSONArray giayto_arr =
	 * thanhphanhoso_obj.getJSONArray("GIAYTO"); if (giayto_arr != null) { for (int
	 * j = 0; j < giayto_arr.length(); j++) { JSONObject giayto_obj =
	 * giayto_arr.getJSONObject(j); String magiayto =
	 * giayto_obj.getString("MAGIAYTO"); String tenmaudon =
	 * giayto_obj.getString("TENMAUDON"); String link = giayto_obj.getString("URL");
	 * String tengiayto = giayto_obj.getString("TENGIAYTO");
	 *
	 * if (Validator.isNotNull(link) && Validator.isNotNull(magiayto)) { InputStream
	 * in = null; URLConnection connection = null; try { URL url = new URL(link);
	 * connection = url.openConnection(); in = connection.getInputStream(); String
	 * mimeType = URLConnection .guessContentTypeFromStream(in); //String mimeType =
	 * MimeTypesUtil.getContentType(tenmaudon);
	 * actions.addServiceFileTemplate(user.getUserId(), groupId,
	 * serviceInfo.getServiceInfoId(), magiayto, tengiayto, tenmaudon, in, mimeType,
	 * connection.getContentLength(), serviceContext);
	 *
	 * } catch (Exception e) { _log.error(e); } finally { if (in != null) {
	 * in.close(); } }
	 *
	 * }
	 *
	 * } } } } } result.put(serviceCode, true); } else { result.put(serviceCode,
	 * false); }
	 *
	 * } else { result.put(serviceCode, false); } } } } catch (Exception e) {
	 * _log.error(e);
	 *
	 * } }
	 *
	 * return result; }
	 */

	@Override
	public JSONObject syncServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCodes,
									  String type) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray responseData = JSONFactoryUtil.createJSONArray();
		// _log.info("-->>>>>>>> syncServiceInfo: " + serverConfigs + "|" +
		// serverConfigs.size());
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			try {
				ServerConfig serverConfig = serverConfigs.get(0);
				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String accessToken = getAccessToken(serverConfig.getCompanyId(), serverConfig.getGroupId(), "dvcqg",
						config);

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

							String serviceCodeDVCQG;

							ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil
									.fetchDVCQGServiceCode(groupId, serviceCode);

							if (serviceInfoMapping == null) {
								continue;
							}

							serviceCodeDVCQG = serviceInfoMapping.getServiceCodeDVCQG();

							JSONObject response = syncServiceInfo(user, groupId, config, serviceCodeDVCQG, serviceInfo,
									collection1.getDictCollectionId(), collection2.getDictCollectionId(), body,
									accessToken, serviceContext, type);

							responseData.put(response);

						}

						if ("create".equals(type)) {
							serviceInfo = new ServiceInfoImpl();

							JSONObject response = syncServiceInfo(user, groupId, config, serviceCode, serviceInfo,
									collection1.getDictCollectionId(), collection2.getDictCollectionId(), body,
									accessToken, serviceContext, type);

							responseData.put(response);
						}

					}
				} else {
					if ("sync".equals(type)) {
						List<ServiceInfo> serviceInfos = ServiceInfoLocalServiceUtil.getServiceInfosByGroupId(groupId);
						if (serviceInfos != null) {
							for (ServiceInfo serviceInfo : serviceInfos) {
								ServiceInfoMapping serviceInfoMapping = ServiceInfoMappingLocalServiceUtil
										.fetchDVCQGServiceCode(groupId, serviceInfo.getServiceCode());

								if (serviceInfoMapping == null || serviceInfoMapping.getSynced() == 1) {
									continue;
								}
								JSONObject response = syncServiceInfo(user, groupId, config,
										serviceInfoMapping.getServiceCodeDVCQG(), serviceInfo,
										collection1.getDictCollectionId(), collection2.getDictCollectionId(), body,
										accessToken, serviceContext, type);

								responseData.put(response);
							}
						}
					}

				}

			} catch (Exception e) {
				_log.error(e);

			}
		}

		result.put("total", responseData.length());
		result.put("data", responseData);

		return result;
	}

	// TODO
	private JSONObject syncServiceInfo(User user, long groupId, JSONObject config, String serviceCodeDVCQG,
									   ServiceInfo serviceInfo, long domainCollectionId, long govAgencyCollectionId, JSONObject body,
									   String accessToken, ServiceContext serviceContext, String type) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		body.put("maTTHC", serviceCodeDVCQG);

		JSONObject serviceInfoDVCQG = getSharingData(config, body, accessToken);

		// String _oldServiceCode = serviceInfo.getServiceCode();

		if (serviceInfoDVCQG != null && serviceInfoDVCQG.has("result")) {
			JSONArray results = serviceInfoDVCQG.getJSONArray("result");

			if (results.length() > 0) {
				JSONObject _tmp = results.getJSONObject(0);
				StringBuffer sb = null;
				_log.debug("syncServiceInfo " + _tmp.toJSONString());
				// TENTTHC
				String tentthc = _tmp.getString("TENTTHC");
				serviceInfo.setServiceName(tentthc);

				// MATTHC
				String matthc = _tmp.getString("MATTHC");

				if ("sync".equalsIgnoreCase(type)) {
					ServiceInfoMapping infoMapping = ServiceInfoMappingLocalServiceUtil.fetchByGID_SCDVCQG(groupId,
							matthc);
					if (infoMapping == null || Validator.isNull(infoMapping.getServiceCode())) {
						return result;
					}
					matthc = infoMapping.getServiceCode();
				}
				serviceInfo.setServiceCode(matthc);

				// TRINHTUTHUCHIEN
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

				// LINHVUCTHUCHIEN
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

				if ("sync".equalsIgnoreCase(type)) {
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

				// MACOQUANCONGBO
				String macoquancongbo = _tmp.getString("MACOQUANCONGBO");
				String tencoquancongbo = StringPool.BLANK;

				if ("sync".equalsIgnoreCase(type)) {
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

				// CACHTHUCTHUCHIEN
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
				// durationText
				serviceInfo.setDurationText(durationText);
				// feeText
				serviceInfo.setFeeText(feeText);

				// YEUCAU
				String yeucau = StringPool.BLANK;
				if (_tmp.has("YEUCAU")) {
					yeucau = _tmp.getString("YEUCAU");
				}
				serviceInfo.setConditionText(yeucau);

				// MOTADOITUONGTHUCHIEN
				/*
				 * String motadoituongthuchien = StringPool.BLANK; if
				 * (_tmp.has("MOTADOITUONGTHUCHIEN")) {
				 *
				 * motadoituongthuchien = _tmp.getString("MOTADOITUONGTHUCHIEN");
				 * _log.debug("MOTADOITUONGTHUCHIEN " + motadoituongthuchien); }
				 */

				// DOITUONGTHUCHIEN
				sb = new StringBuffer();
				if (_tmp.has("DOITUONGTHUCHIEN")) {
					_log.debug("DOITUONGTHUCHIEN " + _tmp.getJSONArray("DOITUONGTHUCHIEN"));
					JSONArray doituongthuchien_arr = _tmp.getJSONArray("DOITUONGTHUCHIEN");
					if (doituongthuchien_arr != null) {
						for (int i = 0; i < doituongthuchien_arr.length(); i++) {
							JSONObject doituongthuchien_obj = doituongthuchien_arr.getJSONObject(i);
							String doituongthuchien = doituongthuchien_obj.getString("TENDOITUONG");

							sb.append(doituongthuchien + "<br>");

						}
					}
				}
				serviceInfo.setApplicantText(sb.toString());

				// KETQUATHUCHIEN
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

				// CANCUPHAPLY
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

				// TRANGTHAI
				boolean public_ = true;
				if (_tmp.has("TRANGTHAI")) {
					int trangthai = _tmp.getInt("TRANGTHAI");
					if (trangthai != 1) {
						public_ = false;
					}
				}
				serviceInfo.setPublic_(public_);

				// COQUANTHUCHIEN
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

				_log.debug("type " + type);

				try {
					if (type.equalsIgnoreCase("sync")) {
						serviceInfo = ServiceInfoLocalServiceUtil.updateServiceInfo(serviceInfo);
						_log.debug(serviceInfo.getApplicantText());

					} else if (type.equalsIgnoreCase("create")) {
						serviceInfo = ServiceInfoLocalServiceUtil.addServiceInfo(user.getUserId(), groupId,
								serviceInfo.getServiceCode(), serviceInfo.getServiceName(),
								serviceInfo.getProcessText(), serviceInfo.getMethodText(), serviceInfo.getDossierText(),
								serviceInfo.getConditionText(), serviceInfo.getDurationText(),
								serviceInfo.getApplicantText(), serviceInfo.getResultText(),
								serviceInfo.getRegularText(), serviceInfo.getFeeText(),
								serviceInfo.getAdministrationCode(), serviceInfo.getDomainCode(),
								serviceInfo.getMaxLevel(), false, serviceInfo.getGovAgencyText(), serviceInfo.getTagCode(),
								serviceInfo.getTagName(), serviceContext);
					}
				} catch (Exception e) {
					_log.error(e);
					result.put("serviceCode", serviceInfo.getServiceCode());
					result.put("message", "error");
					return result;
				}

				try {
					// THANHPHANHOSO
					List<ServiceFileTemplate> serviceFileTemplates = ServiceFileTemplateLocalServiceUtil
							.getByServiceInfoId(serviceInfo.getServiceInfoId());
					if (serviceFileTemplates != null) {
						for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplates) {
							ServiceFileTemplateLocalServiceUtil.removeServiceFileTemplate(
									serviceInfo.getServiceInfoId(), serviceFileTemplate.getFileTemplateNo());
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
												// String mimeType = MimeTypesUtil.getContentType(tenmaudon);
												actions.addServiceFileTemplate(user.getUserId(),
														serviceContext.getScopeGroupId(),
														serviceInfo.getServiceInfoId(), magiayto, tengiayto, tenmaudon,
														in, mimeType, connection.getContentLength(), serviceContext);

											} catch (Exception e) {
												_log.error(e);
											} finally {
												if (in != null) {
													try {
														in.close();
													} catch (Exception e2) {
														_log.error(e2);
													}
												}
											}

										}

									}
								}
							}
						}
					}
				} catch (Exception e) {
					_log.error(e);
				}

				result.put("serviceCode", serviceInfo.getServiceCode());
				result.put("message", "success");
				return result;
			} else {
				result.put("serviceCode", serviceInfo.getServiceCode());
				result.put("message", "error");
				return result;
			}

		} else {
			result.put("serviceCode", serviceInfo.getServiceCode());
			result.put("message", "error");
			return result;
		}

	}

	public void syncFAQToDVCQG() {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");
		// JSONObject result = JSONFactoryUtil.createJSONObject();
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
						// Answer answer = AnswerLocalServiceUtil.fi
						// item.put("TRALOIID", question.getGovAgencyName());
						// item.put("NOIDUNGTRALOI", question.getGovAgencyName());
						data.put(item);
					}

					body.put("data", data);
					JSONObject result = syncData(serverConfig, body);

					if (Validator.isNotNull(result) && result.has("error_code") && result.getInt("error_code") == 0) {
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
					syncData(serverConfig, body);
					/*
					 * if (result.has("error_code") && result.getInt("error_code") == 0) { for (int
					 * i = 0; i < data.length(); i++) { JSONObject _tmp = data.getJSONObject(i);
					 * long questionId = _tmp.getLong("HOIDAPID"); Question question =
					 * QuestionLocalServiceUtil.fetchQuestion(questionId); question.setSynced(1);
					 * QuestionLocalServiceUtil.updateQuestion(question); } }
					 */
				}
			} catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@Override
	public JSONObject doCreateDossierFromDVCQG(Company company, User user, long groupId, ServiceContext serviceContext,
											   JSONObject data) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		ServerConfig serverConfig = null;

		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");

		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			serverConfig = serverConfigs.get(0);
		}

		if (serverConfig == null) {
			_log.info("ttkm result: " + "Not found server config width protocal: DVCQG_INTEGRATION");
			return createResponseMessage(result, -1, "Not found server config width protocal: DVCQG_INTEGRATION");
		}

		if (data == null) {
			_log.info("ttkm result: " + "Data empty");
			return createResponseMessage(result, -1, "Data empty");
		}

		String MaSoThue = data.getString("MaSoThue");

		if (Validator.isNull(MaSoThue)) {
			data.remove("TepDonDangKy");
			_log.info("ttkm data: " + data.toJSONString());
			_log.info("ttkm result: " + "MaSoThue empty");
			return createResponseMessage(result, -1, "MaSoThue empty");
		}

		String techId;

		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put("service", "TraCuuTechID");
		body.put("provider", "wso2is");
		body.put("mstorcmnd", MaSoThue);

		JSONObject searchObj = getSharingDictCollection(serverConfig, body);

		_log.debug("Find TechId by MST " + searchObj.toJSONString());

		if (searchObj != null && GetterUtil.getInteger(searchObj.getString("error_code")) == 0) {
			JSONArray searchResultObj = searchObj.getJSONArray("result");
			if (searchResultObj == null || searchResultObj.length() == 0) {
				data.remove("TepDonDangKy");
				_log.info("ttkm data: " + data.toJSONString());
				_log.info("ttkm result: " + "Can't get techId");
				return createResponseMessage(result, -1, "Can't get techId");
			}
			techId = searchResultObj.getJSONObject(0).getString("TECHNICALID");
			if (Validator.isNull(techId)) {
				data.remove("TepDonDangKy");
				_log.info("ttkm data: " + data.toJSONString());
				_log.info("ttkm result: " + "Can't get techId");
				return createResponseMessage(result, -1, "Can't get techId");
			}

		} else {
			data.remove("TepDonDangKy");
			_log.info("ttkm data: " + data.toJSONString());
			_log.info("ttkm result: " + "Can't get techId");
			return createResponseMessage(result, -1, "Can't get techId");
		}

		String MaHoSo = data.getString("MaHoSo");// bb

		if (Validator.isNull(MaHoSo)) {
			data.remove("TepDonDangKy");
			_log.info("ttkm data: " + data.toJSONString());
			_log.info("ttkm result: " + "MaHoSo empty");
			return createResponseMessage(result, -1, "MaHoSo empty");
		}

		String MaTTHC = data.getString("MaTTHC");// bb
		try {
			JSONObject tmpConfig = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			if (tmpConfig.has("groupId")) {
				groupId = tmpConfig.getLong("groupId");
			}
			if (tmpConfig.has("userId")) {
				long tmpUerId = tmpConfig.getLong("userId");
				serviceContext.setUserId(tmpUerId);
				_log.debug("tmpUerId: " + tmpUerId);
				// serviceContext.setScopeGroupId(tmpUerId != 0 ? tmpUerId :
				// serverConfig.getUserId());
			}

			serviceContext.setCompanyId(serverConfig.getCompanyId());

			serviceContext.setScopeGroupId(groupId != 0 ? groupId : serverConfig.getGroupId());

		} catch (Exception e) {
			data.remove("TepDonDangKy");
			_log.info("ttkm data: " + data.toJSONString());
			_log.info("ttkm result: " + "Can't parse server config: " + MaHoSo);
			_log.error(e);

			return createResponseMessage(result, -1, MaHoSo + "| Can't parse server config");
		}

		_log.info("GroupId: " + groupId + "|" + serviceContext.getScopeGroupId() + "|" + serviceContext.getCompanyId()
				+ "|" + serviceContext.getUserId());

		ServiceInfoMapping mapping = ServiceInfoMappingLocalServiceUtil.fetchByGID_SCDVCQG(groupId, MaTTHC);

		if (mapping == null) {
			data.remove("TepDonDangKy");
			_log.info("ttkm data: " + data.toJSONString());
			_log.info("ttkm result: " + "Not found serviceInfo| " + MaHoSo);
			return createResponseMessage(result, -1, MaHoSo + "| Not found serviceInfo mapping with: " + MaTTHC);
		}
		// String SoVanBan = data.getString("SoVanBan");
		// String NgayNopHoSo = data.getString("NgayNopHoSo");
		String TenThuongNhan = data.getString("TenThuongNhan");// bb
		JSONObject DiaChiDoanhNghiep_obj = data.getJSONObject("DiaChiDoanhNghiep");
		String MaTinh = StringPool.BLANK;
		String MaHuyen = StringPool.BLANK;
		String MaXa = StringPool.BLANK;
		String DiaChiChiTiet = StringPool.BLANK;
		if (DiaChiDoanhNghiep_obj != null && DiaChiDoanhNghiep_obj.length() > 0) {
			MaTinh = DiaChiDoanhNghiep_obj.getString("MaTinh");
			MaHuyen = DiaChiDoanhNghiep_obj.getString("MaHuyen");
			MaXa = DiaChiDoanhNghiep_obj.getString("MaXa");
			DiaChiChiTiet = DiaChiDoanhNghiep_obj.getString("DiaChiChiTiet");
		}

		String DienThoai = data.getString("DienThoai");
		// String Fax = data.getString("Fax");
		String Email = data.getString("Email");
		String NguoiLienHe = data.getString("NguoiLienHe");
		// String SoDienThoaiNguoiLienHe = data.getString("SoDienThoaiNguoiLienHe");
		String TenChuongTrinhKhuyenMai = data.getString("TenChuongTrinhKhuyenMai");
		// String HinhThucKhuyenMai = data.getString("HinhThucKhuyenMai");
		// String ThoiGianKhuyenMaiTu = data.getString("ThoiGianKhuyenMaiTu");
		// String ThoiGianKhuyenMaiDen = data.getString("ThoiGianKhuyenMaiDen");
		// String HangHoaDichVuKhuyenMai = data.getString("HangHoaDichVuKhuyenMai");
		// String SoLuongHangHoaDichVu = data.getString("SoLuongHangHoaDichVu");
		// String KhachHang = data.getString("KhachHang");
		// String CoCauGiaiThuong = data.getString("CoCauGiaiThuong");
		// String TongGiaTriHangHoa = data.getString("TongGiaTriHangHoa");
		// String NoiDungChiTiet = data.getString("NoiDungChiTiet");
		// String TenCacThuongNhanCungThucHien =
		// data.getString("TenCacThuongNhanCungThucHien");
		String TenTepDonDangKy = data.getString("TenTepDonDangKy");
		String TepDonDangKy = data.getString("TepDonDangKy");// base64

		// find applicant
		Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, MaSoThue);

		if (applicant != null && Validator.isNull(applicant.getMappingClassPK())) {
			try {
				applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId,
						applicant.getApplicantId(), _DEFAULT_CLASS_NAME, techId);
			} catch (Exception e) {
				data.remove("TepDonDangKy");
				_log.info("ttkm data: " + data.toJSONString());
				_log.info("ttkm result: " + "Can't update applicant mappingClassPK |" + MaHoSo);
				_log.error(e);
				return createResponseMessage(result, -1, MaHoSo + "| Can't update applicant mappingClassPK");
			}
		} else if (applicant == null) {
			String tmpEmail = Email;
			try {
				String cityName = StringPool.BLANK;
				String districtName = StringPool.BLANK;
				String wardName = StringPool.BLANK;

				// create
				boolean autoEmail = false;
				if (Validator.isNull(Email)) {
					autoEmail = true;
				} else {
					User tmpUser = UserLocalServiceUtil.fetchUserByEmailAddress(serverConfig.getCompanyId(), Email);
					if (tmpUser != null) {
						// duplicate email
						autoEmail = true;
					}
				}

				if (autoEmail) {
					tmpEmail = techId + "-ttkm@dvcqg.gov.vn";
				}
				_log.info("Email: " + Email);
				_log.info("tmpEmail: " + tmpEmail);

				applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId, 0L, TenThuongNhan,
						"business", MaSoThue, DateTimeUtils.dateToString(new Date(), DateTimeUtils._TYPEDATE),
						DiaChiChiTiet, MaTinh, cityName, MaHuyen, districtName, MaXa, wardName, NguoiLienHe, DienThoai,
						tmpEmail, StringPool.BLANK, StringPool.BLANK, true);

				applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId,
						applicant.getApplicantId(), _DEFAULT_CLASS_NAME, techId);

				if (Validator.isNotNull(Email)) {
					// keep original email for applicant
					applicant.setContactEmail(Email);
					applicant = ApplicantLocalServiceUtil.updateApplicant(applicant);
				}

			} catch (Exception e) {
				data.remove("TepDonDangKy");
				_log.info("ttkm data: " + data.toJSONString());
				_log.info("ttkm result: " + "Can't create applicant|" + MaHoSo);
				_log.error(e);
				return createResponseMessage(result, -1, MaHoSo + "| Can't create applicant");
			}
		}

		// create dossier

		try {
			serverConfig = null;
			serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_TTKM");

			if (serverConfigs != null && !serverConfigs.isEmpty()) {
				serverConfig = serverConfigs.get(0);
				_log.debug("serverConfig " + serverConfig);
			}

			if (serverConfig == null) {
				data.remove("TepDonDangKy");
				_log.info("ttkm data: " + data.toJSONString());
				_log.info("ttkm result: " + "Not found server config width protocal: DVCQG_TTKM|" + MaHoSo);
				return createResponseMessage(result, -1,
						MaHoSo + "| Not found server config width protocal: DVCQG_TTKM");
			}

			user = UserLocalServiceUtil.getUser(applicant.getMappingUserId());

			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			// String serverNo = config.getString("serverNo");
			String serviceCode = config.getString("serviceCode");
			String govAgencyCode = config.getString("govAgencyCode");
			String dossierTemplateNo = config.getString("dossierTemplateNo");
			String actionCode = config.getString("actionCode");

			ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);

			if (actConfig == null) {
				data.remove("TepDonDangKy");
				_log.info("ttkm data: " + data.toJSONString());
				_log.info("ttkm result: " + "Not found ActionConfig|" + MaHoSo);
				return createResponseMessage(result, -1, MaHoSo + "| Not found ActionConfig");
			}

			DossierInputModel inputModel = new DossierInputModel();
			inputModel.setServiceCode(serviceCode);
			inputModel.setGovAgencyCode(govAgencyCode);
			inputModel.setDossierTemplateNo(dossierTemplateNo);
			inputModel.setOriginality(String.valueOf(1));

			inputModel.setApplicantName(applicant.getApplicantName());
			inputModel.setDossierNo(StringPool.BLANK);
			inputModel.setApplicantIdType(applicant.getApplicantIdType());
			inputModel.setApplicantIdNo(applicant.getApplicantIdNo());
			inputModel.setAddress(applicant.getAddress());
			inputModel.setCityCode(applicant.getCityCode());
			inputModel.setDistrictCode(applicant.getDistrictCode());
			inputModel.setWardCode(applicant.getWardCode());
			inputModel.setContactTelNo(applicant.getContactTelNo());
			inputModel.setContactEmail(applicant.getContactEmail());
			inputModel.setDelegateName(applicant.getApplicantName());
			inputModel.setDelegateIdNo(applicant.getApplicantIdNo());
			inputModel.setDelegateTelNo(applicant.getContactTelNo());
			inputModel.setDelegateEmail(applicant.getContactEmail());
			inputModel.setDelegateAddress(applicant.getAddress());

			inputModel.setDelegateCityCode(applicant.getCityCode());
			inputModel.setDelegateDistrictCode(applicant.getDistrictCode());
			inputModel.setDelegateWardCode(applicant.getWardCode());
			inputModel.setApplicantNote(StringPool.BLANK);
			inputModel.setSameAsApplicant(false);
			inputModel.setSampleCount(0L);

			inputModel.setDossierNo(MaHoSo);

			inputModel.setDossierName(TenChuongTrinhKhuyenMai);

			// inputModel.setMetaData(metaData);

			serviceContext.setUserId(applicant.getMappingUserId());

			serviceContext.setSignedIn(true);

			_log.debug("applicant.getMappingUserId() " + serviceContext.getUserId());

			_log.debug("applicant " + JSONFactoryUtil.looseSerialize(applicant));
			try {
				Dossier tmp = DossierLocalServiceUtil.getByRef(groupId, MaHoSo);
				if (tmp != null) {
					data.remove("TepDonDangKy");
					_log.info("ttkm data: " + data.toJSONString());
					_log.info("ttkm result: " + "duplicate refId|" + MaHoSo);
					return createResponseMessage(result, 1, MaHoSo + "| duplicate refId");
				}
			} catch (Exception e) {
				_log.error(e);
			}

			Dossier dossier = CPSDossierBusinessLocalServiceUtil.addDossier(groupId, company, user, serviceContext,
					inputModel);

			_log.debug(JSONFactoryUtil.looseSerialize(dossier));

			// update file
			addDossierFileAttachment(groupId, dossier.getDossierId(), dossierTemplateNo, TenTepDonDangKy, TepDonDangKy,
					serviceContext);

			JSONArray TaiLieuNop_arr = data.getJSONArray("TaiLieuNop");
			if (TaiLieuNop_arr != null && TaiLieuNop_arr.length() > 0) {
				for (int i = 0; i < TaiLieuNop_arr.length(); i++) {
					JSONObject TaiLieuNop_obj = TaiLieuNop_arr.getJSONObject(i);
					String TenTepDinhKem = TaiLieuNop_obj.getString("TenTepDinhKem");
					String Base64 = TaiLieuNop_obj.getString("Base64");

					addDossierFileAttachment(groupId, dossier.getDossierId(), dossierTemplateNo, TenTepDinhKem, Base64,
							serviceContext);
				}
			}

			dossier.setReferenceUid(MaHoSo);
			// 5: tu he thong dvcqg
			dossier.setSystemId(5);
			dossier.setSubmitDate(new Date());

			dossier = DossierLocalServiceUtil.updateDossier(dossier);

			// post action

			DossierActions actions = new DossierActionsImpl();
			ProcessOption option = getProcessOption(serviceCode, govAgencyCode, dossierTemplateNo, groupId);
			ErrorMsgModel errorModel = new ErrorMsgModel();

			ProcessAction processAction = DossierActionUtils.getProcessAction(groupId, dossier, actionCode,
					option.getServiceProcessId());

			actions.doAction(groupId, applicant.getMappingUserId(), dossier, option, processAction, actionCode,
					applicant.getApplicantName(), StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, actConfig.getSyncType(), serviceContext, errorModel);
			_log.info("ttkm result: " + "create dossier success|" + MaHoSo);
			return createResponseMessage(result, 0, MaHoSo + "| create dossier success");

		} catch (Exception e) {
			data.remove("TepDonDangKy");
			_log.info("ttkm data: " + data.toJSONString());
			_log.info("ttkm result: " + "create dossier error|" + MaHoSo);
			_log.error(e);
			return createResponseMessage(result, -1, MaHoSo + "| create dossier error");
		}

	}

	@Override
	public JSONObject doCreateDossierSuaDoiBoSungFromDVCQG(Company company, User user, long groupId,
														   ServiceContext serviceContext,
														   JSONObject data, boolean isUpdating) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		ServerConfig serverConfig = null;
		try {
			List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");

			if (serverConfigs != null && !serverConfigs.isEmpty()) {
				serverConfig = serverConfigs.get(0);
			}

			if (serverConfig == null) {
				_log.error("HSKMBS result: " + "Not found server config width protocal: DVCQG_INTEGRATION");
				return createResponseMessage(result, -1, "Not found server config with protocol: DVCQG_INTEGRATION");
			}

			if (data == null) {
				_log.error("HSKMBS result: " + "Data empty");
				return createResponseMessage(result, -1, "Data empty");
			}

			String MaSoThue = data.getString("MaSoThue");
			if (Validator.isNull(MaSoThue)) {
				data.remove("TepDonDangKy");
				_log.info("HSKMBS data: " + data.toJSONString());
				_log.info("HSKMBS result: " + "MaSoThue empty");
				return createResponseMessage(result, -1, "MaSoThue empty");
			}

			String techId;
			JSONObject body = JSONFactoryUtil.createJSONObject();
			body.put("service", "TraCuuTechID");
			body.put("provider", "wso2is");
			body.put("mstorcmnd", MaSoThue);

			JSONObject searchObj = getSharingDictCollection(serverConfig, body);
			_log.debug("Find TechId by MST " + searchObj.toJSONString());

			if (searchObj != null && GetterUtil.getInteger(searchObj.getString("error_code")) == 0) {
				JSONArray searchResultObj = searchObj.getJSONArray("result");
				if (searchResultObj == null || searchResultObj.length() == 0) {
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "Can't get techId");
					return createResponseMessage(result, -1, "Can't get techId");
				}
				techId = searchResultObj.getJSONObject(0).getString("TECHNICALID");
				if (Validator.isNull(techId)) {
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "Can't get techId");
					return createResponseMessage(result, -1, "Can't get techId");
				}
			} else {
				_log.info("HSKMBS data: " + data.toJSONString());
				_log.info("HSKMBS result: " + "Can't get techId");
				return createResponseMessage(result, -1, "Can't get techId");
			}

			String MaHoSo = data.getString("MaHoSoQG");// bb
			if (Validator.isNull(MaHoSo)) {
				data.remove("TepDonDangKy");
				_log.info("HSKMBS data: " + data.toJSONString());
				_log.info("HSKMBS result: " + "MaHoSo empty");
				return createResponseMessage(result, -1, "MaHoSo empty");
			}

			String MaTTHC = data.getString("MaTTHC");// bb
			try {
				JSONObject tmpConfig = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				if (tmpConfig.has("groupId")) {
					groupId = tmpConfig.getLong("groupId");
				}
				if (tmpConfig.has("userId")) {
					long tmpUerId = tmpConfig.getLong("userId");
					serviceContext.setUserId(tmpUerId);
					_log.debug("tmpUerId: " + tmpUerId);
					// serviceContext.setScopeGroupId(tmpUerId != 0 ? tmpUerId :
					// serverConfig.getUserId());
				}

				serviceContext.setCompanyId(serverConfig.getCompanyId());

				serviceContext.setScopeGroupId(groupId != 0 ? groupId : serverConfig.getGroupId());

			} catch (Exception e) {
				data.remove("TepDonDangKy");
				_log.info("HSKMBS data: " + data.toJSONString());
				_log.info("HSKMBS result: " + "Can't parse server config: " + MaHoSo);
				_log.error(e);

				return createResponseMessage(result, -1, MaHoSo + "| Can't parse server config");
			}

			_log.info("GroupId: " + groupId + "|" + serviceContext.getScopeGroupId() + "|" + serviceContext.getCompanyId()
					+ "|" + serviceContext.getUserId());

			ServiceInfoMapping mapping = ServiceInfoMappingLocalServiceUtil.fetchByGID_SCDVCQG(groupId, MaTTHC);
			if (mapping == null) {
				_log.info("HSKMBS data: " + data.toJSONString());
				_log.info("HSKMBS result: " + "Not found serviceInfo| " + MaHoSo);
				return createResponseMessage(result, -1, MaHoSo + "| Not found serviceInfo mapping with: " + MaTTHC);
			}

			String TenThuongNhan = data.getString("TenThuongNhan");// bb
			JSONObject DiaChiDoanhNghiep_obj = data.getJSONObject("DiaChiDoanhNghiep");
			String MaTinh = StringPool.BLANK;
			String MaHuyen = StringPool.BLANK;
			String MaXa = StringPool.BLANK;
			String DiaChiChiTiet = StringPool.BLANK;
			if (DiaChiDoanhNghiep_obj != null && DiaChiDoanhNghiep_obj.length() > 0) {
				MaTinh = DiaChiDoanhNghiep_obj.getString("MaTinh");
				MaHuyen = DiaChiDoanhNghiep_obj.getString("MaHuyen");
				MaXa = DiaChiDoanhNghiep_obj.getString("MaXa");
				DiaChiChiTiet = DiaChiDoanhNghiep_obj.getString("DiaChiChiTiet");
			}
			String DienThoai = data.getString("DienThoai");
			String Email = data.getString("Email");
			String NguoiLienHe = data.getString("NguoiLienHe");

			JSONObject thongTinSuaDoi = data.getJSONObject("ThongTinSuaDoi");
			if(Validator.isNull(thongTinSuaDoi)) {
				_log.info("HSKMBS data: " + data.toJSONString());
				_log.info("HSKMBS result: " + "Not found thongTinSuaDoi| " + MaHoSo);
				return createResponseMessage(result, -1, MaHoSo + "| Not found thongTinSuaDoi with: " + MaTTHC);
			}
			String TenChuongTrinhKhuyenMai = thongTinSuaDoi.getString("TenChuongTrinhKM");
			String TenTepDonDangKy = data.getString("TenTepDangKy");
			String URLTepDangKy = data.getString("URLTepDangKy");

			// find applicant
			Applicant applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, MaSoThue);

			if (applicant != null && Validator.isNull(applicant.getMappingClassPK())) {
				try {
					applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId,
							applicant.getApplicantId(), _DEFAULT_CLASS_NAME, techId);
				} catch (Exception e) {
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "Can't update applicant mappingClassPK |" + MaHoSo);
					_log.error(e);
					return createResponseMessage(result, -1, MaHoSo + "| Can't update applicant mappingClassPK");
				}
			} else if (applicant == null) {
				String tmpEmail = Email;
				try {
					String cityName = StringPool.BLANK;
					String districtName = StringPool.BLANK;
					String wardName = StringPool.BLANK;

					// create
					boolean autoEmail = false;
					if (Validator.isNull(Email)) {
						autoEmail = true;
					} else {
						User tmpUser = UserLocalServiceUtil.fetchUserByEmailAddress(serverConfig.getCompanyId(), Email);
						if (tmpUser != null) {
							// duplicate email
							autoEmail = true;
						}
					}

					if (autoEmail) {
						tmpEmail = techId + "-ttkm@dvcqg.gov.vn";
					}
					_log.info("Email: " + Email);
					_log.info("tmpEmail: " + tmpEmail);

					applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId, 0L, TenThuongNhan,
							"business", MaSoThue, DateTimeUtils.dateToString(new Date(), DateTimeUtils._TYPEDATE),
							DiaChiChiTiet, MaTinh, cityName, MaHuyen, districtName, MaXa, wardName, NguoiLienHe, DienThoai,
							tmpEmail, StringPool.BLANK, StringPool.BLANK, true);

					applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId,
							applicant.getApplicantId(), _DEFAULT_CLASS_NAME, techId);

					if (Validator.isNotNull(Email)) {
						// keep original email for applicant
						applicant.setContactEmail(Email);
						applicant = ApplicantLocalServiceUtil.updateApplicant(applicant);
					}

				} catch (Exception e) {
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "Can't create applicant|" + MaHoSo);
					_log.error(e);
					return createResponseMessage(result, -1, MaHoSo + "| Can't create applicant");
				}
			}

			// create dossier
			try {
				serverConfig = null;
				serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_TTKM");

				if (serverConfigs != null && !serverConfigs.isEmpty()) {
					serverConfig = serverConfigs.get(0);
					_log.debug("serverConfig " + serverConfig);
				}

				if (serverConfig == null || serverConfig.getConfigs() == null) {
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "Not found server config width protocal: DVCQG_TTKM|" + MaHoSo);
					return createResponseMessage(result, -1,
							MaHoSo + "| Not found server config with protocol: DVCQG_TTKM");
				}

				user = UserLocalServiceUtil.getUser(applicant.getMappingUserId());
				JSONObject configTTKM = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				JSONArray listServiceCodeMapping = configTTKM.getJSONArray("configTBKM");
				if(Validator.isNull(listServiceCodeMapping) || listServiceCodeMapping.length() == 0) {
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "Not found configTBKM width protocal: DVCQG_TTKM|" + MaHoSo);
					return createResponseMessage(result, -1,
							MaHoSo + "| Not found server config with protocol: DVCQG_TTKM");
				}

				int lengthArr = listServiceCodeMapping.length();
				String serviceCode = "";
				String govAgencyCode = "";
				String dossierTemplateNo = "";
				String actionCode = "";
				JSONObject tempJsonObj;
				for(int i=0; i<lengthArr; i++) {
					tempJsonObj = listServiceCodeMapping.getJSONObject(i);
					if(MaTTHC.equals(tempJsonObj.getString("serviceCodeDVCQG"))) {
						serviceCode = tempJsonObj.getString("serviceCode");
						govAgencyCode = tempJsonObj.getString("govAgencyCode");
						dossierTemplateNo = tempJsonObj.getString("dossierTemplateNo");
						actionCode = tempJsonObj.getString("actionCode");
						break;
					}
				}

				if(serviceCode.isEmpty()) {
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "service code is empty with protocal: DVCQG_TTKM|" + MaHoSo);
					return createResponseMessage(result, -1,
							MaHoSo + "| Not found service code with protocal: DVCQG_TTKM");
				}

				ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);

				if (actConfig == null) {
					data.remove("TepDonDangKy");
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "Not found ActionConfig|" + MaHoSo);
					return createResponseMessage(result, -1, MaHoSo + "| Not found ActionConfig");
				}

				DossierInputModel inputModel = new DossierInputModel();
				inputModel.setServiceCode(serviceCode);
				inputModel.setGovAgencyCode(govAgencyCode);
				inputModel.setDossierTemplateNo(dossierTemplateNo);
				inputModel.setOriginality(String.valueOf(1));

				inputModel.setApplicantName(applicant.getApplicantName());
				inputModel.setDossierNo(StringPool.BLANK);
				inputModel.setApplicantIdType(applicant.getApplicantIdType());
				inputModel.setApplicantIdNo(applicant.getApplicantIdNo());
				inputModel.setAddress(applicant.getAddress());
				inputModel.setCityCode(applicant.getCityCode());
				inputModel.setDistrictCode(applicant.getDistrictCode());
				inputModel.setWardCode(applicant.getWardCode());
				inputModel.setContactTelNo(applicant.getContactTelNo());
				inputModel.setContactEmail(applicant.getContactEmail());
				inputModel.setDelegateName(applicant.getApplicantName());
				inputModel.setDelegateIdNo(applicant.getApplicantIdNo());
				inputModel.setDelegateTelNo(applicant.getContactTelNo());
				inputModel.setDelegateEmail(applicant.getContactEmail());
				inputModel.setDelegateAddress(applicant.getAddress());

				inputModel.setDelegateCityCode(applicant.getCityCode());
				inputModel.setDelegateDistrictCode(applicant.getDistrictCode());
				inputModel.setDelegateWardCode(applicant.getWardCode());
				inputModel.setApplicantNote(StringPool.BLANK);
				inputModel.setSameAsApplicant(false);
				inputModel.setSampleCount(0L);

				inputModel.setDossierNo(MaHoSo);

				inputModel.setDossierName(TenChuongTrinhKhuyenMai);

				// inputModel.setMetaData(metaData);

				serviceContext.setUserId(applicant.getMappingUserId());

				serviceContext.setSignedIn(true);

				_log.debug("applicant.getMappingUserId() " + serviceContext.getUserId());

				_log.debug("applicant " + JSONFactoryUtil.looseSerialize(applicant));

				Dossier tmpDossier = DossierLocalServiceUtil.getByRef(groupId, MaHoSo);
				if (Validator.isNotNull(tmpDossier)) {
					_log.info("HSKMBS data: " + data.toJSONString());
					_log.info("HSKMBS result: " + "Duplicate refId|" + MaHoSo);
					if(isUpdating) {
						tmpDossier.setServiceCode(serviceCode);
						tmpDossier.setGovAgencyCode(govAgencyCode);
						tmpDossier.setDossierTemplateNo(dossierTemplateNo);

						tmpDossier.setApplicantName(applicant.getApplicantName());
						tmpDossier.setApplicantIdType(applicant.getApplicantIdType());
						tmpDossier.setApplicantIdNo(applicant.getApplicantIdNo());
						tmpDossier.setAddress(applicant.getAddress());
						tmpDossier.setCityCode(applicant.getCityCode());
						tmpDossier.setDistrictCode(applicant.getDistrictCode());
						tmpDossier.setWardCode(applicant.getWardCode());
						tmpDossier.setContactTelNo(applicant.getContactTelNo());
						tmpDossier.setContactEmail(applicant.getContactEmail());
						tmpDossier.setDelegateName(applicant.getApplicantName());
						tmpDossier.setDelegateIdNo(applicant.getApplicantIdNo());
						tmpDossier.setDelegateTelNo(applicant.getContactTelNo());
						tmpDossier.setDelegateEmail(applicant.getContactEmail());
						tmpDossier.setDelegateAddress(applicant.getAddress());

						tmpDossier.setDelegateCityCode(applicant.getCityCode());
						tmpDossier.setDelegateDistrictCode(applicant.getDistrictCode());
						tmpDossier.setDelegateWardCode(applicant.getWardCode());
						tmpDossier.setDossierName(TenChuongTrinhKhuyenMai);

						DossierLocalServiceUtil.updateDossier(tmpDossier);

						addDossierFileHSKMBSAttachment(groupId, tmpDossier.getDossierId(), dossierTemplateNo, TenTepDonDangKy, URLTepDangKy,
								serviceContext);

						return createResponseMessage(result, 0, MaHoSo + "| update dossier success");
					}
					return createResponseMessage(result, 1, MaHoSo + "| Duplicate refId");
				}

				Dossier dossier = CPSDossierBusinessLocalServiceUtil.addDossier(groupId, company, user, serviceContext,
						inputModel);

				_log.debug(JSONFactoryUtil.looseSerialize(dossier));
				// update file
				addDossierFileHSKMBSAttachment(groupId, dossier.getDossierId(), dossierTemplateNo, TenTepDonDangKy, URLTepDangKy,
						serviceContext);

				dossier.setReferenceUid(MaHoSo);
				// 5: tu he thong dvcqg
				dossier.setSystemId(5);

				dossier = DossierLocalServiceUtil.updateDossier(dossier);

				// post action

				DossierActions actions = new DossierActionsImpl();
				ProcessOption option = getProcessOption(serviceCode, govAgencyCode, dossierTemplateNo, groupId);
				ErrorMsgModel errorModel = new ErrorMsgModel();

				ProcessAction processAction = DossierActionUtils.getProcessAction(groupId, dossier, actionCode,
						option.getServiceProcessId());

				actions.doAction(groupId, applicant.getMappingUserId(), dossier, option, processAction, actionCode,
						applicant.getApplicantName(), StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
						StringPool.BLANK, actConfig.getSyncType(), serviceContext, errorModel);
				_log.info("HSKMBS result: " + "create dossier success|" + MaHoSo);
				return createResponseMessage(result, 0, MaHoSo + "| create dossier success");

			} catch (Exception e) {
				_log.info("HSKMBS data: " + data.toJSONString());
				_log.info("HSKMBS result: " + "create dossier error|" + MaHoSo);
				_log.error(e);
				return createResponseMessage(result, -1, MaHoSo + "| create dossier error");
			}
		} catch (Exception e) {
			_log.error(e);
			return createResponseMessage(result, -1, "System error");
		}
	}

	private ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
										   long groupId) throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);
		if (config != null) {
			return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
					config.getServiceConfigId());
		} else {
			return null;
		}
	}

	private JSONObject payloadSummaryVote(JSONObject config) {
		JSONObject payload = JSONFactoryUtil.createJSONObject();
		payload.put("service", "DongBoDanhGia");
		payload.put("data", this.getListSummaryVote(config));
		return payload;
	}

	private JSONArray getListSummaryVote(JSONObject config) {
		JSONArray data = JSONFactoryUtil.createJSONArray();
		List<OpencpsVotingStatistic> votes = new ArrayList<>(OpencpsVotingStatisticLocalServiceUtil.getOpencpsVotingStatistics(0, 100));

		int currentMonth = this.getCurrentTime().get("Month");
		int currentYear  = this.getCurrentTime().get("Year");

		//get only data of current month
		votes.removeIf(vote -> vote.getMonth() != currentMonth || vote.getYear() != currentYear
				|| vote.getVotingCode() == null || vote.getVotingCode().equals(""));

		List<VoteTransform> voteTransformList = new ArrayList<>();
		VoteTransform newVote;
		//Transform opencpsVotingStatistic model to Transform model to easy handle
		for(OpencpsVotingStatistic oldVote: votes) {
			newVote = new VoteTransform(oldVote.getTotalVoted(), oldVote.getGoodCount(), oldVote.getVeryGoodCount(), oldVote.getBadCount(),
					oldVote.getPercentGood(), oldVote.getPercentVeryGood(), oldVote.getPercentBad(),
					oldVote.getVotingCode(), oldVote.getVotingSubject());

			voteTransformList.add(newVote);
		}

		if(voteTransformList.isEmpty()) {
			return data;
		}
		//Sum count group by votingCode
		List<VoteTransform> listVoteAfterCounting = voteTransformList.stream().collect(Collectors.groupingBy(VoteTransform -> VoteTransform.votingCode)).entrySet().stream()
				.map(e -> e.getValue().stream().reduce(this::countingVoteData))
				.map(f -> f.get()).collect(Collectors.toList());

		//Calculate percent
		JSONObject item;
		int percentVeryGood;
		int percentGood;
		int percentBad;
		for(VoteTransform vote: listVoteAfterCounting) {
			item = JSONFactoryUtil.createJSONObject();
			percentVeryGood = calculatePercentage(vote.countVeryGoodVote, vote.totalVote);
			percentGood = calculatePercentage(vote.countGoodVote, vote.totalVote);
			percentBad = percentVeryGood != 0 || percentGood != 0 ? 100 - (percentVeryGood + percentGood): 0;

			item.put("NgayTongHop", currentMonth + "-" + currentYear);
			item.put("TongSoNguoiDanhGia", vote.totalVote);
			item.put("PhanTramDanhGiaRatHaiLong", percentVeryGood + "%");
			item.put("PhanTramDanhGiaHaiLong", percentGood +"%");
			item.put("PhanTramDanhGiaChuaHaiLong", percentBad + "%");
			item.put("DanhSachNoiDungYKien", "");
			item.put("MaThuTuc", "");
			item.put("NoiDung", vote.votingSubject != null ?vote.votingSubject : "");
			item.put("PhanTramDanhGiaDichVuRatHaiLong", percentVeryGood + "%");
			item.put("PhanTramDanhGiaDichVuHaiLong", percentGood +"%");
			item.put("PhanTramDanhGiaDichVuChuaHaiLong", percentBad + "%");
			item.put("Madonvi", config.getString("madonvi", ""));
			item.put("TenDonVi", config.getString("tendonvi", ""));
			data.put(item);
		}

		System.out.println(data);

		return data;
	}

	private int calculatePercentage(int obtained, int total) {
		if(total > 0) {
			return Math.round(obtained * 100 / total);
		}
		return 0;
	}

	private Map<String, Integer> getCurrentTime() {
		Map<String, Integer> monthAndYear = new HashMap<>();
		monthAndYear.put("Month", Calendar.getInstance().get(Calendar.MONTH)+1);
		monthAndYear.put("Year", Calendar.getInstance().get(Calendar.YEAR));
		return monthAndYear;
	}

	class VoteTransform {
		public Integer totalVote;
		public Integer countGoodVote;
		public Integer countVeryGoodVote;
		public Integer countBadVote;
		public Integer percentGoodVote;
		public Integer percentVeryGoodVote;
		public Integer percentBadVote;
		public String votingCode;
		public String votingSubject;

		public VoteTransform(Integer totalVote, Integer countGoodVote, Integer countVeryGoodVote,
							 Integer countBadVote, Integer percentGoodVote, Integer percentVeryGoodVote,
							 Integer percentBadVote, String votingCode, String votingSubject) {
			this.totalVote = totalVote;
			this.countGoodVote = countGoodVote;
			this.countVeryGoodVote = countVeryGoodVote;
			this.countBadVote = countBadVote;
			this.percentGoodVote = percentGoodVote;
			this.percentVeryGoodVote = percentVeryGoodVote;
			this.percentBadVote = percentBadVote;
			this.votingCode = votingCode;
			this.votingSubject = votingSubject;
		}
	}

	private VoteTransform countingVoteData(VoteTransform f1, VoteTransform f2) {
		VoteTransform voteTransform = new VoteTransform(f1.totalVote + f2.totalVote, f1.countGoodVote + f2.countGoodVote,
				f1.countVeryGoodVote + f2.countVeryGoodVote, f1.countBadVote + f2.countBadVote,
				0, 0, 0,
				f1.votingCode, f1.votingSubject);

		return voteTransform;
	}

	public void syncSummaryVote() throws Exception{
		try {
			_log.info("Start getting summary vote...");
			List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQG_INTEGRATION);
			if (serverConfigs == null ||serverConfigs.isEmpty()) {
				throw new Exception("Server config not found");
			}
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			JSONObject payload = this.payloadSummaryVote(config);
			syncData(serverConfig, payload);
			_log.info("End get summary vote.");
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	private void addDossierFileHSKMBSAttachment(long groupId, long dossierId, String dossierTemplateNo, String fileName,
												String url, ServiceContext serviceContext) {
		try {
			if (Validator.isNotNull(fileName) && Validator.isNotNull(url)) {
				DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossierTemplateNo,
						"TP99");
				String fileTemplateNo = StringPool.BLANK;
				if (dossierPart != null) {
					fileTemplateNo = dossierPart.getFileTemplateNo();
				}
				String referenceUid = StringPool.BLANK;
				File file = DossierFileUtils.urlDownloadToFile(url);

				InputStream inputStream = DossierFileUtils.fileToInputStream(file);
				JSONObject fileMetadata = DossierFileUtils.getFileMetadata(file);
				if (inputStream != null) {
					DossierFile dossierFile = DossierFileLocalServiceUtil.addDossierFile(groupId, dossierId, referenceUid,
							dossierTemplateNo, "TP99", fileTemplateNo, fileName, fileName, fileMetadata.getLong("fileSize"),
							inputStream, fileMetadata.getString("mimeType"), String.valueOf(false), serviceContext);

					_log.debug("Attachment dossierFile " + fileName + " " + JSONFactoryUtil.looseSerialize(dossierFile));
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private void addDossierFileAttachment(long groupId, long dossierId, String dossierTemplateNo, String fileName,
										  String base64, ServiceContext serviceContext) throws PortalException, IOException {
		if (Validator.isNotNull(fileName) && Validator.isNotNull(base64)) {
			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossierTemplateNo,
					"TP99");
			String fileTemplateNo = StringPool.BLANK;
			if (dossierPart != null) {
				fileTemplateNo = dossierPart.getFileTemplateNo();
			}
			String referenceUid = StringPool.BLANK;
			File file = DossierFileUtils.base64ToFile(fileName, base64);
			InputStream inputStream = DossierFileUtils.fileToInputStream(file);
			JSONObject fileMetadata = DossierFileUtils.getFileMetadata(file);
			if (inputStream != null) {
				DossierFile dossierFile = DossierFileLocalServiceUtil.addDossierFile(groupId, dossierId, referenceUid,
						dossierTemplateNo, "TP99", fileTemplateNo, fileName, fileName, fileMetadata.getLong("fileSize"),
						inputStream, fileMetadata.getString("mimeType"), String.valueOf(false), serviceContext);

				_log.debug("Attachment dossierFile " + fileName + " " + JSONFactoryUtil.looseSerialize(dossierFile));
			}

		}
	}

	private JSONObject createResponseMessage(JSONObject object, int errorCode, String message) {
		object.put("error_code", errorCode);
		object.put("message", message);
		return object;
	}


	@Override
	public JSONObject doSyncServiceConfig(User user, long groupId, String requestBody, ServiceContext context) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		ServerConfig serverConfig = null;
		try {

			List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG_INTEGRATION");

			if (serverConfigs != null && !serverConfigs.isEmpty()) {
				serverConfig = serverConfigs.get(0);
			}

			if (serverConfig == null) {
				return createResponseMessage(result, 404, "error",
						"Not found server config width protocal: DVCQG_INTEGRATION");
			}

			JSONObject body = JSONFactoryUtil.createJSONObject(requestBody);

			body.put("service", "LayDVC");

			JSONObject serviceConfigData = getSharingData(serverConfig, body);

			_log.info("serviceConfigData: " + serviceConfigData.toJSONString());

			if (serviceConfigData == null || serviceConfigData.length() == 0) {
				return createResponseMessage(result, 404, "error", "can't get data");
			}

			JSONArray data = serviceConfigData.getJSONObject("result").getJSONArray("data");

			for (int i = 0; i < data.length(); i++) {

				JSONObject obj = data.getJSONObject(i);
				String serviceConfigCode = obj.getString("MADVC");
				String serviceConfigName = obj.getString("TENDVC");
				String serviceCode = obj.getString("MATTHC");
				String serviceName = obj.getString("TENTTHC");
				String govAgencyName = obj.getString("TENCOQUANBANHANH");
				String domainName = obj.getString("TENLINHVUC");
				JSONArray applicableInfoObj = obj.getJSONArray("APDUNGDVC");

				JSONObject serviceInfoDetail = getServiceInfoDVCQGDetail(user, groupId, context, serviceCode);

				_log.info("serviceInfoDetail " + serviceInfoDetail.toJSONString());

				JSONArray paymentFeeInfoObj = JSONFactoryUtil.createJSONArray();

				if (serviceInfoDetail.has("error_code")
						&& GetterUtil.getInteger(serviceInfoDetail.getString("error_code")) == 0) {

					JSONArray resultTmp = serviceInfoDetail.getJSONArray("result");

					if (resultTmp != null) {

						JSONObject _tmp = resultTmp.getJSONObject(0);

						if (_tmp.has("CACHTHUCTHUCHIEN")) {

							JSONArray cachthucthuchien_arr = _tmp.getJSONArray("CACHTHUCTHUCHIEN");

							if (cachthucthuchien_arr != null) {
								for (int j = 0; j < cachthucthuchien_arr.length(); j++) {
									JSONObject cachthucthuchien_obj = cachthucthuchien_arr.getJSONObject(j);

									JSONArray thoigian_arr = cachthucthuchien_obj.getJSONArray("THOIGIAN");

									if (thoigian_arr != null) {

										for (int t = 0; t < thoigian_arr.length(); t++) {

											JSONObject thoigian_obj = thoigian_arr.getJSONObject(t);

											JSONArray philephi_arr = thoigian_obj.getJSONArray("PHILEPHI");

											if (philephi_arr != null && philephi_arr.length() > 0) {
												for (int p = 0; p < philephi_arr.length(); p++) {
													paymentFeeInfoObj.put(philephi_arr.getJSONObject(p));
												}
											}
										}
									}
								}
							}
						}

					}
				}

				ServiceConfigMappingLocalServiceUtil.addServiceConfigMaping(groupId, serviceConfigCode,
						serviceConfigName, serviceCode, serviceName, govAgencyName, domainName, applicableInfoObj,
						paymentFeeInfoObj, context);
			}

			return createResponseMessage(result, 200, "success", "sync serverconfig success");

		} catch (Exception e) {
			_log.error(e);
			return createResponseMessage(result, 500, "error", "system error");
		}

	}
//	private static final String DOSSIER_BTTTT = "DOSSIER_BTTTT";
	@Override
	public JSONObject doCreateUpdateDossierFromDVCQG(Company company, User user, long groupId, ServiceContext serviceContext, JSONObject data, boolean isUpdating) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			if (data == null) {
				_log.error("HSKMBS result: " + "Data empty");
				return createResponseMessage(result, -1, "Data empty");
			}
			_log.info("------------ Hồ Sơ Chứng Từ Thuế Đất -------------");
			String MaHoSo = data.getString("MaHoSo");
			if (Validator.isNull(MaHoSo)) {
				_log.info("HSCTTD data: " + data.toJSONString());
				_log.info("HSCTTD result: " + "MaHoSo empty");
				return createResponseMessage(result, -1, "MaHoSo empty");
			}

			String HoTenNguoiNopTien = data.getString("HoTenNguoiNopTien");
			if (Validator.isNull(HoTenNguoiNopTien)) {
				_log.info("HSCTTD data: " + data.toJSONString());
				_log.info("HSCTTD result: " + "HoTenNguoiNopTien empty");
				return createResponseMessage(result, -1, "HoTenNguoiNopTien empty");
			}


			String MaSoThue = data.getString("MaSoThue");
			if (Validator.isNull(MaSoThue)) {
				_log.info("HSCTTD data: " + data.toJSONString());
				_log.info("HSCTTD result: " + "MaSoThue empty");
				return createResponseMessage(result, -1, "MaSoThue empty");
			}

			String ThongTinThanhToan = data.getString("ThongTinThanhToan");
			if (Validator.isNull(ThongTinThanhToan)) {
				_log.info("HSCTTD data: " + data.toJSONString());
				_log.info("HSCTTD result: " + "ThongTinThanhToan empty");
				return createResponseMessage(result, -1, "ThongTinThanhToan empty");
			}

			String UrlFileChungTu = data.getString("UrlFileChungTu");
			if (Validator.isNull(UrlFileChungTu)) {
				_log.info("HSCTTD data: " + data.toJSONString());
				_log.info("HSCTTD result: " + "UrlFileChungTu empty");
				return createResponseMessage(result, -1, "UrlFileChungTu empty");
			}
			// Thông tin thanh toán
			JSONObject TtttChitiet = data.getJSONObject("ThongTinThanhToan");
			if (Validator.isNull(TtttChitiet)) {
				_log.info("HSCTTD data: " + data.toJSONString());
				_log.info("HSCTTD result: " + "TtttChitiet empty");
				return createResponseMessage(result, -1, "Thong tin thanh toán chi tiết empty");
			}

			String MaThongBaoThue = TtttChitiet.getString("MaThongBaoThue");
			if (Validator.isNull(MaThongBaoThue)) {
				_log.info("HSCTTD data: " + TtttChitiet.toJSONString());
				_log.info("HSCTTD result: " + "MaThongBaoThue empty");
				return createResponseMessage(result, -1, "MaThongBaoThue empty");
			}

			String ThoiGianThanhToan = TtttChitiet.getString("ThoiGianThanhToan");
			if (Validator.isNull(ThoiGianThanhToan)) {
				_log.info("HSCTTD data: " + TtttChitiet.toJSONString());
				_log.info("HSCTTD result: " + "UrlFileChungTu empty");
				return createResponseMessage(result, -1, "ThoiGianThanhToan empty");
			}

			String SoTien = TtttChitiet.getString("SoTien");
			if (Validator.isNull(SoTien)) {
				_log.info("HSCTTD data: " + TtttChitiet.toJSONString());
				_log.info("HSCTTD result: " + "SoTien empty");
				return createResponseMessage(result, -1, "SoTien empty");
			}

			String NoiDungThanhToan = TtttChitiet.getString("NoiDungThanhToan");
			if (Validator.isNull(NoiDungThanhToan)) {
				_log.info("HSCTTD data: " + TtttChitiet.toJSONString());
				_log.info("HSCTTD result: " + "NoiDungThanhToan empty");
				return createResponseMessage(result, -1, "NoiDungThanhToan empty");
			}

			int TrangThaiThanhToan = TtttChitiet.getInt("TrangThaiThanhToan");
			if (Validator.isNull(TrangThaiThanhToan)) {
				_log.info("HSCTTD data: " + TtttChitiet.toJSONString());
				_log.info("HSCTTD result: " + "TrangThaiThanhToan empty");
				return createResponseMessage(result, -1, "TrangThaiThanhToan empty");
			}
			Dossier dossier = DossierLocalServiceUtil.fetchByDO_NO(MaHoSo);
			if(Validator.isNotNull(dossier)) {
				SyncTrackingQuery syncTrackingQuery = new SyncTrackingQuery();
				syncTrackingQuery.groupId = groupId;
				syncTrackingQuery.bodyRequest = data.toString();
				syncTrackingQuery.dossierNo = MaHoSo;
				syncTrackingQuery.userId = user.getUserId();
				syncTrackingQuery.referenceUid = dossier.getReferenceUid();
				SyncTrackingLocalServiceUtil.createSyncTrackingManual(syncTrackingQuery);
			}
			return createResponseMessage(result, 0, MaHoSo + "| create dossier success");
		} catch (Exception e) {
			e.getMessage();
			_log.error(e);
		}
		return result;
	}

	@Override
	public JSONObject doCrUpDossierThongBaoThueDatDVCQG(Company company, User user, long groupId,
														ServiceContext serviceContext, JSONObject data, boolean isUpdating) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		String msHS = StringPool.BLANK;
		try {
			if (data == null) {
				_log.error("HSKMBS result: " + "Data empty");
				return createResponseMessage(result, -1, "Data empty");
			}
			_log.info("------------ Hồ Sơ nhận thông báo Thuế Đất -------------");
			String FileThongBaoThue = data.getString("FileThongBaoThue");
			if (Validator.isNull(FileThongBaoThue)) {
				_log.info("HSCTTD data: " + data.toJSONString());
				_log.info("HSCTTD result: " + "FileThongBaoThue empty");
				return createResponseMessage(result, -1, "FileThongBaoThue empty");
			}else{
				byte[] decodedBytes = Base64.getDecoder().decode(FileThongBaoThue);
				String xmlFileThongBaoThue = new String(decodedBytes);
				Document xml = convertStringToXMLDocument(xmlFileThongBaoThue);

				NodeList childs = xml.getElementsByTagName("MA_HSO");
				Node child;

				for (int i = 0; i < childs.getLength(); i++) {
					child = childs.item(i);
					child.getFirstChild();
					System.out.println(child.getNodeName());
					_log.info("MA_HS: " + child.getTextContent());
					Dossier dossier = DossierLocalServiceUtil.fetchByDO_NO(child.getTextContent());
					if(Validator.isNotNull(dossier)) {
						msHS += child.getTextContent() + ",";
						SyncTrackingQuery syncTrackingQuery = new SyncTrackingQuery();
						syncTrackingQuery.groupId = groupId;
						syncTrackingQuery.bodyRequest = xmlFileThongBaoThue;
						syncTrackingQuery.dossierNo = child.getTextContent();
						syncTrackingQuery.userId = user.getUserId();
						syncTrackingQuery.referenceUid = dossier.getReferenceUid();
						SyncTrackingLocalServiceUtil.createSyncTrackingManual(syncTrackingQuery);
					}
				}
			}
			return createResponseMessage(result, 0,  msHS+"| create dossier success");
		}catch (Exception e){
			e.getMessage();
			_log.error(e);
		}

		return result;
	}

	private JSONObject createResponseMessage(JSONObject object, int status, String message, String desc) {
		object.put("status", status);
		object.put("message", message);
		object.put("description", desc);
		return object;
	}

	private static Document convertStringToXMLDocument(String xmlString) throws ParserConfigurationException {
		//Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		//API to obtain DOM Document instance
		DocumentBuilder builder = factory.newDocumentBuilder();
		try
		{
			//Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			//Parse the content to Document object
			InputSource is = new InputSource(new StringReader(xmlString));

			Document doc = builder.parse(is);
			return doc;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private String _DEFAULT_CLASS_NAME = "dvcqg";
}