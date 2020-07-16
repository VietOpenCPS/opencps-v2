package org.fds.opencps.paygate.integration.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;

public class PayGateTerm {

	private static Log _log = LogFactoryUtil.getLog(PayGateTerm.class.getName());
	public static final String ATTACHMENT_FILENAME_FORMAT = "attachment; filename=\"%s\"";
	public static final String PNG_TYPE = "image/png";
	public static final String KEYPAY_DVCQG_PROTOCOL = "KEYPAY_DVCQG_PROTOCOL";
	public static final String KEYPAY_DVCQG_CREATE_TRANSACTION_ENDPOINT = "keypay_dvcqg_create_transaction_endpoint";
	public static final String KEYPAY_DVCQG_DETAIL_TRANSACTION_ENDPOINT = "keypay_dvcqg_detail_transaction_endpoint";
	public static final String VTP_PROTOCOL = "VTP_QRCODE";
	public static final String PRIORITY = "priority";
	public static final String KEY = "key";
	public static final String VALUE = "value";
	public static final String VERSION = "version";
	public static final String HASH_KEY_1 = "hash_key_1";
	public static final String HASH_KEY_2 = "hash_key_2";
	public static final String TYPE = "type";
	public static final String BILLCODE = "billcode";
	public static final String ORDER_ID = "order_id";
	public static final String AMOUNT = "amount";
	public static final String MERCHANT_CODE = "merchant_code";
	public static final String DOT_PNG = ".png";
	public static final String PNG_UPPER = "PNG";
	public static final String ERROR_CODE_03 = "03";
	public static final String ERROR_CODE_01 = "01";
	public static final String ERROR_CODE_02 = "02";
	public static final String ERROR_CODE_00 = "00";
	public static final String ACCESS_CODE = "access_code";
	public static final String HASH_KEY = "hash_key";
	public static final String HMAC_SHA1 = "HmacSHA1";
	public static final String CHECK_SUM = "check_sum";
	public static final String TRANS_AMOUNT = "trans_amount";
	public static final String PAYMENT_FEE = "payment_fee";
	public static final String ERROR_CODE = "error_code";
	public static final String HEX_FORMAT = "%02x";
	public static final String RETURN_URL = "return_url";
	public static final String RETURN_BILL_CODE = "return_bill_code";
	public static final String RETURN_OTHER_INFO = "return_other_info";

	public static final String URL_SEARCH = "url_search";
	public static final String CMD_SEARCH = "cmd_search";
	public static final String VERSION_SEARCH = "version_search";
	public static final String ENCODING_CHECKSUM = "version_search";

	public static final String CUST_MSISDN = "cust_msisdn";
	public static final String VT_TRANSACTION_ID = "vt_transaction_id";
	public static final String PAYMENT_STATUS = "payment_status";

	public static final String ENDPOINT_CONFIRM = "o/pgi/vtp/mcpaymentconfirm";
	public static final String ENDPOINT_RECEIVER = "o/pgi/vtp/mcreceiveresult";
	public static final String ENDPOINT_SEARCH = "o/pgi/vtp/mcsearch";
	public static final String ENDPOINT_DVCRECEIVER = "o/pgi/vtp/dvcreceiveresult";

	public static final String ACTION_IS_ONLINE = "actionIsOnline";
	public static final String ACTION_IS_NOT_ONLINE = "actionIsNotOnline";
	public static final String ACTION_CODE = "actionCode";
	public static final String PAYMENT = "payment";
	public static final String URL = "url";
	public static final String URL_DOMAIN = "url_domain";
	public static final String MC_URL = "mcUrl";
	public static final String USERNAME = "userName";
	public static final String PWD = "pwd";
	public static final String ACTION_ENPOINT = "actions";
	
	//add by TrungNT
	public static final String CLIENT_ID = "client_id";
	public static final String TRANSACTION_ID = "transaction_id";
	public static final String TRANSACTION_CODE = "transaction_code";
	public static final String COMMAND = "command";
	public static final String DESCRIPTION = "description";
	public static final String LOCALE = "locale";
	public static final String COUNTRY_CODE = "country_code";
	public static final String CURRENCY_CODE = "currency_code";
	public static final String ENVIRONMENT = "environment";
	public static final String STATUS = "status";
	public static final String BILL_INFO = "bill_info";
	public static final String MADICHVU = "MaDichVu";

	public static final String BANKINFO = "BankInfo";
	public static final String SERVICE_CODE = "ServiceCode";
	public static final String TKTHUHUONG = "TKThuHuong";
	public static final String MANHTHUHUONG = "MaNHThuHuong";
	public static final String TENTKTHUHUONG = "TenTKThuHuong";
	public static final String DEFAULT = "default";

	public static final String PHILEPHI = "PhiLePhi";
	public static final String LOAIPHILEPHI = "LoaiPhiLePhi";
	
	public static final String MAPHILEPHI = "MaPhiLePhi";
	public static final String TENPHILEPHI = "TenPhiLePhi";
	public static final String SOTIEN = "SoTien";
	public static final String MADONVI = "MaDonVi";
	public static final String TENDONVI = "TenDonVi";
	public static final String MAHOSO = "MaHoSo";
	public static final String MADVC = "MaDVC";
	public static final String MADVCAPPEND = "MADVCAppend";
	public static final String TENDVC = "TenDVC";
	public static final String MATTHC = "MaTTHC";
	public static final String TENTTHC = "TenTTHC";
	public static final String NOIDUNGTHANHTOAN = "NoiDungThanhToan";
	public static final String MALOAIHINHTHUPHAT = "MaLoaiHinhThuPhat";
	
	public static final String TENLOAIHINHTHUPHAT = "TenLoaiHinhThuPhat";
	public static final String HOTENNGUOINOP = "HoTenNguoiNop";
	public static final String SOCMNDNGUOINOP = "SoCMNDNguoiNop";
	public static final String DIACHINGUOINOP = "DiaChiNguoiNop";
	public static final String HUYENNGUOINOP = "HuyenNguoiNop";
	public static final String TINHNGUOINOP = "TinhNguoiNop";
	public static final String MACOQUANQD = "MaCoQuanQD";
	public static final String TENCOQUANQD = "TenCoQuanQD";
	public static final String KHOBAC = "KhoBac";
	public static final String NGAYQD = "NgayQD";
	public static final String SOQD = "SoQD";
	public static final String THOIGIANVIPHAM = "ThoiGianViPham";
	public static final String DIADIEMVIPHAM = "DiaDiemViPham";
	public static final String TENNGUOIVIPHAM = "TenNguoiViPham";
	public static final String TAIKHOANTHUNSNN = "TaiKhoanThuNSNN";
	public static final String DSKHOANNOP = "DSKhoanNop";
	public static final String NOIDUNG = "NoiDung";
	public static final String ADDITION_FEE = "addition_fee";

	public static final String ERROR_RES_SUCCESS = "0";
	public static final String ERROR_RES_KEY = "error";
	public static final String MSG_RES_KEY = "msg";
	public static final String PAYMENT_URL_RES_KEY = "payment_url";
	
	// add by PhucHN
	public static final String LOAIBANTIN = "LoaiBanTin";
	public static final String LOAIBANTIN_INIT = "LoaiBanTinInit";
	public static final String LOAIBANTIN_GET_BILL = "LoaiBanTinGetBill";
	public static final String LOAIBANTIN_CONFIRM = "LoaiBanTinConfirm";
	public static final String PHIENBAN = "PhienBan";
	public static final String MADOITAC = "MaDoiTac";
	public static final String MATHAMCHIEU = "MaThamChieu";
	public static final String LOAIHINHTHANHTOAN = "LoaiHinhThanhToan";
	public static final String MAKENHTHANHTOAN = "MaKenhThanhToan";
	public static final String MATHIETBI = "MaThietBi";
	public static final String NGONNGU = "NgonNgu";
	public static final String MATIENTE = "MaTienTe";
	public static final String MANGANHANG = "MaNganHang";
	public static final String THONGTINGIAODICH = "ThongTinGiaoDich";
	public static final String THOIGIANGD = "ThoiGianGD";
	public static final String IP = "Ip";
	public static final String THONGTINBIENLAI = "ThongTinBienLai";
	public static final String MAXACTHUC = "MaXacThuc";
	
	public static final String MALOI_KEY = "MaLoi";
	public static final String MALOI_SUCCESS = "00";
	public static final String PAYMENTPLATFORM_DVCQG_INIT_TRANSACTION_ENDPOINT = "paymentplatform_dvcqg_init_transaction_endpoint";
	public static final String MAGIAODICH = "MaGiaoDich";
	public static final String PAYMENTPLATFORM_DVCQG_GET_RECEIPT_ENDPOINT = "paymentplatform_dvcgq_get_receipt_endpoint";

	

	public static String buildPathDoAction(String path, String dossierId) {

		return path + "/o/rest/v2/dossiers/" + dossierId + "/actions";
	};

	public static final String getMcUrlByBillCode (String billcode) {
		try {
			String govAgencyCode = billcode.split("___")[0];
			ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode("SERVER_" + govAgencyCode);
			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			return config.getString(MC_URL);
		} catch (Exception e) {
			_log.debug(e);
		}
		return StringPool.BLANK;
	}
}
