package org.opencps.dossiermgt.constants;

import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DVCQGIntegrationActionTerm {
	public static final String APPLICATION_JSON = "application/json";
	public static final String CHARSET = "Charset";
	public static final String UTF_8_LOWER = "utf-8";
	public static final String UTF_8 = "UTF-8";
	
	public static final String MAHOSO = "MaHoSo";
	public static final String MATTHC = "MaTTHC";
	public static final String SERVICE = "service";
	public static final String LAYTHUTUC = "LayThuTuc";
	public static final String RESULT = "result";
	public static final String TENTTHC = "TENTTHC";
	public static final String LINHVUCTHUCHIEN = "LINHVUCTHUCHIEN";
	public static final String MALINHVUC = "MALINHVUC";
	public static final String TENLINHVUC = "TENLINHVUC";
	public static final String SOBIENNHAN = "SoBienNhan";
	public static final String CHUHOSO = "ChuHoSo";
	public static final String BUSINESS = "business";
	public static final String LOAIDOITUONG = "LoaiDoiTuong";
	public static final String MADOITUONG = "MaDoiTuong";
	public static final String THONGTINKHAC = "ThongTinKhac";
	public static final String EMAIL = "Email";
	public static final String FAX = "Fax";
	public static final String SODIENTHOAI = "SoDienThoai";
	public static final String TRICHYEUHOSO = "TrichYeuHoSo";
	public static final String NGAYTIEPNHAN = "NgayTiepNhan";
	public static final String NGAYHENTRA = "NgayHenTra";
	public static final String TRANGTHAIHOSO = "TrangThaiHoSo";
	public static final String NGAYTRA = "NgayTra";
	public static final String THONGTINTRA = "ThongTinTra";
	public static final String HINHTHUC = "HinhThuc";
	public static final String NGAYKETTHUCXULY = "NgayKetThucXuLy";
	public static final String DONVIXULY = "DonViXuLy";
	public static final String GHICHU = "GhiChu";
	public static final String TAILIEUNOP = "TaiLieuNop";
	public static final String TEPDINHKEMID = "TepDinhKemId";
	public static final String TENTEPDINHKEM = "TenTepDinhKem";
	public static final String ISDELETED = "IsDeleted";
	public static final String MATHANHPHANHOSO = "MaThanhPhanHoSo";
	public static final String DUONGDANTAITEPTIN = "DuongDanTaiTepTin";
	public static final String DANHSACHLEPHI = "DanhSachLePhi";
	public static final String TENPHILEPHI = "TenPhiLePhi";
	public static final String MAPHILEPHI = "MaPhiLePhi";
	public static final String HINHTHUCTHU = "HinhThucThu";
	public static final String GIA = "Gia";
	public static final String LOAIPHILEPHI = "LoaiPhiLePhi";
	public static final String DANHSACHHOSOBOSUNG = "DanhSachHoSoBoSung";
	public static final String HOSOBOSUNGID = "HoSoBoSungId";
	public static final String NGUOIYEUCAUBOSUNG = "NguoiYeuCauBoSung";
	public static final String NOIDUNGBOSUNG = "NoiDungBoSung";
	public static final String NGAYBOSUNG = "NgayBoSung";
	public static final String NGUOITIEPNHANBOSUNG = "NguoiTiepNhanBoSung";
	public static final String THONGTINTIEPNHAN = "ThongTinTiepNhan";
	public static final String NGAYTIEPNHANBOSUNG = "NgayTiepNhanBoSung";
	public static final String TRANGTHAIBOSUNG = "TrangThaiBoSung";
	public static final String DANHSACHGIAYTOBOSUNG = "DanhSachGiayToBoSung";
	public static final String DANHSACHLEPHIBOSUNG = "DanhSachLePhiBoSung";
	public static final String NGAYHENTRATRUOC = "NgayHenTraTruoc";
	public static final String NGAYHENTRAMOI = "NgayHenTraMoi";
	public static final String DANHSACHGIAYTOKETQUA = "DanhSachGiayToKetQua";
	public static final String TENGIAYTO = "TenGiayTo";
	public static final String GIAYTOID = "GiayToId";
	public static final String DUONGDANTEPTINKETQUA = "DuongDanTepTinKetQua";
	public static final String USERNAME = "username";
	public static final String ADAPTER_URL = "adapter_url";
	public static final String AUTH_ENDPOINT = "auth_endpoint";
	public static final String SECRET = "password";
	public static final String DSTCODE = "dstcode";

	public static final String DANHSACHTEPDINHKEMKHAC = "DanhSachTepDinhKemKhac";
	public static final String NGUOIXULY = "NguoiXuLy";
	public static final String CHUCDANH = "ChucDanh";
	public static final String THOIDIEMXULY = "ThoiDiemXuLy";
	public static final String PHONGBANXULY = "PhongBanXuLy";
	public static final String NOIDUNGXULY = "NoiDungXuLy";
	public static final String TRANGTHAI = "TrangThai";
	public static final String NGAYBATDAU = "NgayBatDau";
	public static final String NGAYKETTHUCTHEOQUYDINH = "NgayKetThucTheoQuyDinh";
	public static final String SESSION = "session";
	public static final String DVCQG_INTEGRATION = "DVCQG_INTEGRATION";
	public static final String INTEGRATION_ENDPOINT = "integration_endpoint";
	public static final String MADONVI = "madonvi";
	public static final String SHARE_ENDPOINT = "share_endpoint";
	public static final String LAYDANHSACHTTHC = "LayDanhSachTTHC";
	public static final String SERVICECODEDVCQG = "serviceCodeDVCQG";
	public static final String SERVICEINFOMAPPINGID = "serviceInfoMappingId";
	public static final String SERVICENAMEDVCQG = "serviceNameDVCQG";
	public static final String SIMILARITYPERCENT = "similarityPercent";
	public static final String MAPPED = "mapped";
	public static final String TRACUUHOSO = "TraCuuHoSo";
	public static final String ISUPDATING = "isUpdating";
	public static final String DONGBOHOSOMC = "DongBoHoSoMC";
	public static final String DATA = "data";
	public static final String ERROR_CODE = "error_code";
	public static final String CAPNHATTIENDOHOSOMC = "CapNhatTienDoHoSoMC";
	public static final String TRINHTUTHUCHIEN = "TRINHTUTHUCHIEN";
	public static final String TRUONGHOP = "TRUONGHOP";
	public static final String TRINHTU = "TRINHTU";
	public static final String TENTRINHTU = "TENTRINHTU";
	public static final String CACHTHUCTHUCHIEN = "CACHTHUCTHUCHIEN";
	public static final String KENH = "KENH";
	public static final String THOIGIAN = "THOIGIAN";
	public static final String THOIGIANGIAIQUYET = "THOIGIANGIAIQUYET";
	public static final String DONVITINH = "DONVITINH";
	public static final String MOTA = "MOTA";
	public static final String PHILEPHI = "PHILEPHI";
	public static final String SOTIEN = "SOTIEN";
	public static final String DONVI = "DONVI";
	public static final String YEUCAU = "YEUCAU";
	public static final String MOTADOITUONGTHUCHIEN = "MOTADOITUONGTHUCHIEN";
	public static final String KETQUATHUCHIEN = "KETQUATHUCHIEN";

	public static final String THANHPHANHOSO = "THANHPHANHOSO";
	public static final String id = "id";
	public static final String serviceCode = "serviceCode";
	public static final String MAKETQUA = "MAKETQUA";
	public static final String TENKETQUA = "TENKETQUA";
	public static final String CANCUPHAPLY = "CANCUPHAPLY";
	public static final String SOVANBAN = "SOVANBAN";
	public static final String TENVANBAN = "TENVANBAN";
	public static final String COQUANTHUCHIEN = "COQUANTHUCHIEN";
	public static final String TENDONVI = "TENDONVI";
	public static final String URL = "URL";
	public static final String TENMAUDON = "TENMAUDON";
	public static final String MAGIAYTO = "MAGIAYTO";
	public static final String GIAYTO = "GIAYTO";
	public static final String TRUE_KEY = "True";
	public static final String FALSE_KEY = "False";
	public static final String ERR_CODE_00_KEY = "00";
	
	public static final String NHAN_KENH_DEFAULT = "Trực tiếp";
	public static final String NHAN_KENH_2 = "Trực tuyến";
	public static final String NHAN_KENH_3 = "Nộp qua bưu chính công ích";
	public static final String LABEL_THOIGIAN_GIAIQUYET = "- Thời gian giải quyết: ";
	public static final String LABEL_MA_KETQUA = "- Mã kết quả:";
	public static final String LABEL_KETQUA = "- Kết quả:";
	public static final String LABEL_SO_VANBAN = "- Số văn bản: ";
	public static final String LABEL_TEN_VANBAN = "- Tên văn bản: ";
	public static final String LABEL_TEN_DONVI = "- Tên đơn vị: ";
	public static final String LABEL_MA_DONVI = "- Mã đơn vị: ";
	
	public static String convertDate2String(Date date) {

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyyMMddHHmmss");

		if (Validator.isNull(date)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}
	
}
