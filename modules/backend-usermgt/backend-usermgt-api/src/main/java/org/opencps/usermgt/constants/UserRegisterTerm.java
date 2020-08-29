package org.opencps.usermgt.constants;

public class UserRegisterTerm {

	public static final String URL_TOKEN = "https://lgsp.dongthap.gov.vn/token";
	public static final String CLIENT_ID = "client_id";
	public static final String VALUE_CLIENT_ID = "yPTWeD3FuqgYku7SvNJ5VuylmY0a";
	public static final String CLIENT_SECRECT = "client_secret";
	public static final String VALUE_CLIENT_SECRECT = "lEti1opjJtLecmHcdGf9ogat0SUa";
	public static final String GRANT_TYPE = "grant_type";
	public static final String VALUE_GRANT_TYPE = "client_credentials";
	
	public static final String BASE_URL = "https://lgsp.dongthap.gov.vn/taikhoan/1.0.0";
	public static final String ENDPOINT_CITIZEN = "/congdan/dangky";
	public static final String ENDPOINT_BUSINESS = "/doanhnghiep/dangky";
	public static final String ENDPOINT_CHANGE_SECRECT = "/doimatkhau";
	public static final String ENDPOINT_CITIZEN_ACTIVE = "/congdan/kichhoat";
	public static final String ENDPOINT_BUSINESS_ACTIVE = "/doanhnghiep/kichhoat";
	public static final String ENDPOINT_FORGOT = "/quenmatkhau";
	//
	public static final String NEW_BASE_URL = "http://api.dongthap.gov.vn";
	public static final String NEW_ENDPOINT_TOKEN = "/api/v1/Authentication/Token";
	public static final String NEW_ENDPOINT_LOGIN = "/api/v1/congdan/UserLogin";
	public static final String NEW_ENDPOINT_REGISTER = "/api/v1/congdan/UserRegister";
	public static final String NEW_ENDPOINT_CHANGE_SECRECT = "/api/v1/congdan/UserChangePassword";
	public static final String NEW_ENDPOINT_RESET_SECRECT = "/api/v1/congdan/UserResetPasswordAuto";
	public static final String NEW_ENDPOINT_VERIFY_OTP = "/​api​/v1​/congdan/VerifyOpt";
	public static final String NEW_ENDPOINT_GET_OTP = "/​api​/v1​/congdan/GetOtp";
	public static final String NEW_ENDPOINT_SEND_SMS_OTP = "/​api​/v1​/congdan/SendSmsOtp";
	public static final String NEW_ENDPOINT_GET_USER = "/api/v1/congdan/GetUser";
	public static final String NEW_ENDPOINT_VERIFY_OTP_USER = "/​api​/v1​/congdan/VerifyOptUser";
	public static final String NEW_ENDPOINT_VERIFY_USER = "/api/v1/congdan/VerifyUser";
	public static final String NEW_ENDPOINT_SEND_EMAIL_OTP = "/​api​/v1​/congdan/SendEmailOtp";

	//Citizen
	public static final String EMAIL = "email";
	public static final String SO_CMND = "soCMND";
	public static final String NGAY_SINH = "ngaySinh";
	public static final String GIOI_TINH = "gioiTinh";
	public static final String DIA_CHI_THUONG_TRU = "moTaDiaChiThuongTru";
	public static final String TINH_THUONG_TRU = "tinhThuongTruId";
	public static final String HUYEN_THUONG_TRU = "huyenThuongTruId";
	public static final String XA_THUONG_TRU = "xaThuongTruId";
	public static final String DIEN_THOAI = "dienThoai";
	public static final String HO = "ho";
	public static final String TEN = "ten";
	public static final String DEM = "dem";
	//Business
	public static final String MA_SO_THUE = "maSoThue";
	public static final String NGAY_CAP_GIAY_PHEP = "ngayCapGiayPhep";
	public static final String MA_GIAY_PHEP = "maGiayPhep";
	public static final String MO_TA_DIA_DIEM_KINH_DOANH = "moTaDiaDiemKinhDoanh";
	public static final String TINH_DIA_DIEM_KD = "tinhDiaDiemKDId";
	public static final String HUYEN_DIA_DIEM_KD = "huyenDiaDiemKDId";
	public static final String XA_DIA_DIEM_KD = "xaDiaDiemKDId";
			
	public static final String MESSAGE = "message";
	public static final String ERROR_CODE = "error_code";
	public static final String MA_XAC_NHAN = "maXacNhan";
	public static final String MAT_KHAU = "matKhau";
	public static final String TAI_KHOAN = "taiKhoan";
	public static final String OLD_SECRECT = "matKhauCu";
	public static final String NEW_SECRECT = "matKhauMoi";
	//
	public static final String USER_NAME = "userName";
	public static final String OLD_SECRECT_KEY = "oldPassword";
	public static final String SECRECT_KEY = "password";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ORGANIZATION_NAME = "organizationName";

}
