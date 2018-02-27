package org.opencps.dossiermgt.action.impl;

import org.opencps.dossiermgt.action.VNPostAction;
import org.opencps.dossiermgt.model.DossierVnpost;
import org.opencps.dossiermgt.service.DossierVnpostLocalServiceUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vn.mitc.ngsp.sdk.VNPost_N_GSP.IDocument;
import vn.mitc.ngsp.sdk.VNPost_N_GSP.IOrder;
import vn.mitc.ngsp.sdk.VNPost_N_GSP.IToken;
import vn.mitc.ngsp.sdk.models.MDocument;
import vn.mitc.ngsp.sdk.models.MOrder;
import vn.mitc.ngsp.sdk.models.MProcedure;
import vn.mitc.ngsp.sdk.models.MResult;
import vn.mitc.ngsp.sdk.models.MStatus;
import vn.mitc.ngsp.sdk.models.MToken;
import vn.mitc.ngsp.sdk.models.MTransceiver;

public class VNPostActionImpl implements VNPostAction {

	public MToken getToken() throws Exception {
		String tokenUrl = "https://api.mitc.vn/token";
		String consumer_key = "ddabyJ69AeQyzhFvlnXNROKQs7Ia";
		String secret_key = "kFqNeYCoLtr4MMSnfRcXQwhIO3Aa";

		return IToken.getToken(tokenUrl, consumer_key, secret_key);
	}

	@Override
	public String postOrder(String fullName, String phoneNumber, String deliverAddress, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName, long dossierId,
			long groupId) throws Exception {
		MToken token = getToken();
		String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/order/post";

		MOrder order = new MOrder("cthh", "OrderNumber_005", 0.0, 10, 0, "11 Duy Tân, Cầu Giấy, Hà Nội", "CMC Soft",
				null, "0982191921", null, "Tài liệu từ bộ văn hóa", fullName, deliverAddress, phoneNumber,
				Integer.parseInt(cityCode), Integer.parseInt(districtCode), null);

		MResult result = IOrder.postOrder(apiUrl, token.getAccessToken(), token.getTokenType(), order);

		DossierVnpostLocalServiceUtil.addDossierVnpost(fullName, phoneNumber, deliverAddress, cityCode, cityName,
				districtCode, districtName, wardCode, wardName, dossierId, groupId);
		return result.getMessage();
	}

	// public static void cancelOrder() throws Exception {
	// String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/order/cancel";
	// String access_token = "ccff5149-0187-33c6-b544-90c7fb8e9615";
	// String token_type = "Bearer";
	//
	// String customerCode = "cthh";
	// String orderNumber = "HP_000108";
	//
	// MResult result = IOrder.cancelOrder(apiUrl, access_token, token_type,
	// customerCode, orderNumber);
	//
	// System.out.println("Status: " + result.getStatus());
	// System.out.println("Message: " + result.getMessage());
	// }
	//
	// public static void getOrderTracking() throws Exception {
	// String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/order/tracking";
	// String access_token = "ccff5149-0187-33c6-b544-90c7fb8e9615";
	// String token_type = "Bearer";
	//
	// int pagesize = 50;
	// String lastId = "000000000000000000000000";
	//
	// MStatus status = IOrder.getOrderTracking(apiUrl, access_token,
	// token_type, pagesize, lastId);
	//
	// Gson gson = new GsonBuilder().serializeNulls().create();
	// String strStatus = gson.toJson(status);
	// System.out.println(strStatus);
	// }
	//
	// public static void postDocument() throws Exception {
	// String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/document/post";
	// String access_token = "ccff5149-0187-33c6-b544-90c7fb8e9615";
	// String token_type = "Bearer";
	// MProcedure procedure = new MProcedure("KSTT", "Thủ tục khai sinh thông
	// thường", "Hộ tịch",
	// "CMT cha, CMT mẹ, Sổ hộ khẩu");
	// MTransceiver sender = new MTransceiver("A", "A phone", 10, "Hà Nội", 0,
	// null, "45 Phương Liệt", "a@gmail.com");
	// MTransceiver receiver = new MTransceiver("B", "B phone", 16, "Hải Dương",
	// 0, null, "TP Hải Dương",
	// "b@gmail.com");
	//
	// MDocument doc = new MDocument("cthh", "HP_TTKSTT_00001", procedure, true,
	// sender, true, receiver, 1);
	//
	// MResult result = IDocument.postDocument(apiUrl, access_token, token_type,
	// doc);
	//
	// System.out.println("Status: " + result.getStatus());
	// System.out.println("Message: " + result.getMessage());
	// }
}