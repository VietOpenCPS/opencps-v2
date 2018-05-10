package backend.postal.api.rest.controller.impl;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.vnpost.model.VNPostInputModel;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import backend.postal.api.rest.controller.VNPostManagement;
import vn.mitc.ngsp.sdk.VNPost_N_GSP.IOrder;
import vn.mitc.ngsp.sdk.VNPost_N_GSP.IToken;
import vn.mitc.ngsp.sdk.models.MOrder;
import vn.mitc.ngsp.sdk.models.MResult;
import vn.mitc.ngsp.sdk.models.MToken;

public class VNPostManagementImpl implements VNPostManagement {

	@Override
	public Response sendPostalRequest(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VNPostInputModel input) {

		String tokenUrl = "https://api.mitc.vn/token";
		String consumer_key = "ddabyJ69AeQyzhFvlnXNROKQs7Ia";
		String secret_key = "kFqNeYCoLtr4MMSnfRcXQwhIO3Aa";
		try {
			MToken token = IToken.getToken(tokenUrl, consumer_key, secret_key);
			String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/order/post";
			MOrder order = new MOrder(input.getCustomerCode(), input.getOrderNumber(), input.getCodAmount(),
					input.getSenderProvince(), input.getSenderDistrict(), input.getSenderAddress(),
					input.getSenderName(), input.getSenderEmail(), input.getSenderTel(), input.getSenderDesc(),
					input.getDescription(), input.getReceiverName(), input.getReceiverAddress(), input.getReceiverTel(),
					input.getReceiverProvince(), input.getReceiverDistrict(), input.getReceiverEmail());
			MResult result = IOrder.postOrder(apiUrl, token.getAccessToken(), token.getTokenType(), order);
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}
}
