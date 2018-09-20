package backend.postal.api.rest.controller.impl;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.vnpost.model.VNPostGetOrderModel;
import org.opencps.api.vnpost.model.VNPostInputModel;
import org.opencps.api.vnpost.model.VNPostServerConfigModel;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import backend.postal.api.rest.controller.VNPostManagement;
import vn.mitc.ngsp.sdk.VNPost_N_GSP.IOrder;
import vn.mitc.ngsp.sdk.VNPost_N_GSP.IToken;
import vn.mitc.ngsp.sdk.models.MOId;
import vn.mitc.ngsp.sdk.models.MOrder;
import vn.mitc.ngsp.sdk.models.MResult;
import vn.mitc.ngsp.sdk.models.MStatus;
import vn.mitc.ngsp.sdk.models.MToken;

public class VNPostManagementImpl implements VNPostManagement {

	private Log _log = LogFactoryUtil.getLog(VNPostManagementImpl.class);

	public VNPostServerConfigModel fromJSONObject(JSONObject configObj) {
		// _log.info("configObj =============== " +
		// JSONFactoryUtil.looseSerialize(configObj));
		if (configObj.has(VnPostTerm.SERVER_APIPOSTORDER) && configObj.has(VnPostTerm.SERVER_APIGETTOKEN)
				&& configObj.has(VnPostTerm.SERVER_APICANCELORDER)
				&& configObj.has(VnPostTerm.SERVER_APIGETORDERTRACKING) && configObj.has(VnPostTerm.SERVER_CUSTOMERKEY)
				&& configObj.has(VnPostTerm.SERVER_SECRETKEY) && configObj.has(VnPostTerm.SERVER_ACTIVE)) {
			return new VNPostServerConfigModel(configObj.getString(VnPostTerm.SERVER_APIPOSTORDER),
					configObj.getString(VnPostTerm.SERVER_APIGETORDERTRACKING),
					configObj.getString(VnPostTerm.SERVER_APIGETTOKEN),
					configObj.getString(VnPostTerm.SERVER_APICANCELORDER),
					configObj.getString(VnPostTerm.SERVER_CUSTOMERKEY),
					configObj.getString(VnPostTerm.SERVER_SECRETKEY), configObj.getBoolean(VnPostTerm.SERVER_ACTIVE));
		} else {
			return null;
		}
	}

	public VNPostServerConfigModel getServerConfig(long groupId, String protocol) {
		VNPostServerConfigModel config = null;

		List<ServerConfig> lstsc = ServerConfigLocalServiceUtil.getByProtocol(groupId, protocol);
		// _log.info("lstsc ========= " + JSONFactoryUtil.looseSerialize(lstsc));
		if (lstsc == null) {
			return null;
		} else {
			try {
				for (ServerConfig sc : lstsc) {
					VNPostServerConfigModel check = fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
					// _log.info("check ============ " + JSONFactoryUtil.looseSerialize(check));
					if (check.getActive()) {
						config = check;
					}
				}
				return config;
			} catch (JSONException e) {
				// e.printStackTrace();
				_log.error(e);
				return null;
			}
		}

	}

	@Override
	public Response sendPostalRequest(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VNPostInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		VNPostServerConfigModel config = getServerConfig(groupId, "url_vnpost");
		// _log.info("config ============= " + JSONFactoryUtil.looseSerialize(config));

		// String tokenUrl = "https://api.mitc.vn/token";
		// String consumer_key = "ddabyJ69AeQyzhFvlnXNROKQs7Ia";
		// String secret_key = "kFqNeYCoLtr4MMSnfRcXQwhIO3Aa";
		if (config == null) {
			return null;
		} else {
			try {
				MToken token = IToken.getToken(config.getApiGetToken(), config.getCustomerKey(), config.getSecretKey());
				// String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/order/post";
				MOrder order = new MOrder(input.getCustomerCode(), input.getOrderNumber(), input.getCodAmount(),
						input.getSenderProvince(), input.getSenderDistrict(), input.getSenderAddress(),
						input.getSenderName(), input.getSenderEmail(), input.getSenderTel(), input.getSenderDesc(),
						input.getDescription(), input.getReceiverName(), input.getReceiverAddress(),
						input.getReceiverTel(), input.getReceiverProvince(), input.getReceiverDistrict(),
						input.getReceiverEmail());
				MResult result = IOrder.postOrder(config.getApiPostOrder(), token.getAccessToken(),
						token.getTokenType(), order);
				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} catch (Exception e) {
				_log.error(e);
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
			}
		}
	}

	@Override
	public Response getOrderTracking(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VNPostGetOrderModel input) {
		// String tokenUrl = "https://api.mitc.vn/token";
		// String consumer_key = "ddabyJ69AeQyzhFvlnXNROKQs7Ia";
		// String secret_key = "kFqNeYCoLtr4MMSnfRcXQwhIO3Aa";
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		// _log.info("groupId = =========" + groupId);
		VNPostServerConfigModel config = getServerConfig(groupId, "url_vnpost");

		try {
			MToken token = IToken.getToken(config.getApiGetToken(), config.getCustomerKey(), config.getSecretKey());
			// String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/order/tracking";

			MStatus status = IOrder.getOrderTracking(config.getApiGetOrderTracking(), token.getAccessToken(),
					token.getTokenType(), input.getPageSize(), input.getLastId());

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(status)).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}

	}

}
