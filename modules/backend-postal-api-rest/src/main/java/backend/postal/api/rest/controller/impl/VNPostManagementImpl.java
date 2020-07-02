package backend.postal.api.rest.controller.impl;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.service.util.ConfigConstants;
import org.opencps.api.service.util.ConfigProps;
import org.opencps.api.vnpost.model.VNPostCancelOrderModel;
import org.opencps.api.vnpost.model.VNPostGetOrderModel;
import org.opencps.api.vnpost.model.VNPostInputModel;
import org.opencps.api.vnpost.model.VNPostServerConfigModel;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;

import backend.postal.api.rest.controller.VNPostManagement;
import vn.mitc.ngsp.sdk.VNPost_N_GSP.IOrder;
import vn.mitc.ngsp.sdk.VNPost_N_GSP.IToken;
import vn.mitc.ngsp.sdk.models.MOrder;
import vn.mitc.ngsp.sdk.models.MResult;
import vn.mitc.ngsp.sdk.models.MRhDoc;
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
					configObj.getString(VnPostTerm.SERVER_SECRETKEY), 
					configObj.getBoolean(VnPostTerm.SERVER_ACTIVE),
					configObj.getString(VnPostTerm.SERVER_CUSTOMERCODE),
					configObj.getInt(VnPostTerm.SERVER_SENDERPROVINCE),
					configObj.getInt(VnPostTerm.SERVER_SENDERDISTRICT),
					configObj.getString(VnPostTerm.SERVER_SENDERADDRESS),
					configObj.getString(VnPostTerm.SERVER_SENDEREMAIL),
					configObj.getString(VnPostTerm.SERVER_SENDERTEL),
					configObj.getString(VnPostTerm.SERVER_SENDERNAME));
		} else {
			return null;
		}
	}

	public VNPostServerConfigModel getServerConfig(long groupId, String serverNo, String protocol) {
		VNPostServerConfigModel config = null;

		try {
			ServerConfig sc = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId, serverNo, protocol);
			VNPostServerConfigModel check = fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
			// _log.info("check ============ " + JSONFactoryUtil.looseSerialize(check));
			if (check.getActive()) {
				config = check;
			}
			return config;
		} catch (JSONException e) {
			// e.printStackTrace();
			_log.error(e);
			return null;
		}

	}

	@Override
	public Response sendPostalRequest(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VNPostInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		String serverNo = VnPostTerm.getVNPostServerNo(input.getGovAgencyCode());
		VNPostServerConfigModel config = getServerConfig(groupId, serverNo, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
		if (config == null) {
			config = getServerConfig(groupId, VnPostTerm.SERVER_CONFIG_VIA_POSTAL_SERVERNO_D, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
		}
		//_log.info("sendPostalRequest groupId ============= " + groupId);
		//_log.info("sendPostalRequest lstsc ============= " + JSONFactoryUtil.looseSerialize(config));

		// String tokenUrl = "https://api.mitc.vn/token";
		// String consumer_key = "ddabyJ69AeQyzhFvlnXNROKQs7Ia";
		// String secret_key = "kFqNeYCoLtr4MMSnfRcXQwhIO3Aa";
		if (config == null) {
			return null;
		} else {
			try {
				MToken token = IToken.getToken(config.getApiGetToken(), config.getCustomerKey(), config.getSecretKey());
				// String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/order/post";

				// _log.info("token ============= " + JSONFactoryUtil.looseSerialize(token));

				String senderName = Validator.isNotNull(config.getSenderName()) ? config.getSenderName()
						: input.getSenderName();
				MOrder order = new MOrder(config.getCustomerCode(), input.getOrderNumber(), input.getCodAmount(),
						config.getSenderProvince(), config.getSenderDistrict(), config.getSenderAddress(), senderName,
						config.getSenderEmail(), config.getSenderTel(), input.getSenderDesc(), input.getDescription(),
						input.getReceiverName(), input.getReceiverAddress(), input.getReceiverTel(),
						input.getReceiverProvince(), input.getReceiverDistrict(), input.getReceiverEmail());
				MResult result = IOrder.postOrder(config.getApiPostOrder(), token.getAccessToken(),
						token.getTokenType(), order);
				_log.info(config.getCustomerCode() + "-----" + input.getOrderNumber() + "-----" + input.getCodAmount()
						+ "-----" + config.getSenderProvince() + "-----" + config.getSenderDistrict() + "-----"
						+ config.getSenderAddress() + "-----" + senderName + "-----" + config.getSenderEmail() + "-----"
						+ config.getSenderTel() + "-----" + input.getSenderDesc() + "-----" + input.getDescription()
						+ "-----" + input.getReceiverName() + "-----" + input.getReceiverAddress() + "-----"
						+ input.getReceiverTel() + "-----" + input.getReceiverProvince() + "-----"
						+ input.getReceiverDistrict() + "-----" + input.getReceiverEmail());
				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} catch (Exception e) {
				_log.error(e);
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
			}
		}
	}
	
	@Override
	public Response sendCollectionVNPostalRequest(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VNPostInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		String serverNo = VnPostTerm.getVNPostServerNo(input.getGovAgencyCode());
		VNPostServerConfigModel config = getServerConfig(groupId, serverNo, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
		if (config == null) {
			config = getServerConfig(groupId, VnPostTerm.SERVER_CONFIG_VIA_POSTAL_SERVERNO_D, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
		}
		if (config == null) {
			return null;
		} else {
			try {
				MToken token = IToken.getToken(config.getApiGetToken(), config.getCustomerKey(), config.getSecretKey());

				_log.info(token);
				_log.info("input============" + input);
				_log.info("config============" + config);
				String receiverName = Validator.isNotNull(config.getSenderName()) ? config.getSenderName()
						: input.getGovAgencyName();
				String receiverAddress = config.getSenderAddress();
				String receiverTel = config.getSenderTel();
				int receiverProvince = config.getSenderProvince();
				int receiverDistrict = config.getSenderDistrict();
				String receiverEmail = config.getSenderEmail();

				MOrder order = new MOrder(config.getCustomerCode(), input.getOrderNumber(), input.getCodAmount(),
						input.getSenderProvince(), input.getSenderDistrict(), input.getSenderAddress(),
						input.getSenderName(), input.getSenderEmail(), input.getSenderTel(), input.getSenderDesc(),
						input.getDescription(), receiverName, receiverAddress, receiverTel, receiverProvince,
						receiverDistrict, receiverEmail);
				
				MResult result = IOrder.postOrder(config.getApiPostOrder(), token.getAccessToken(),
						token.getTokenType(), order);
				_log.info(config.getCustomerCode() + "-----" + input.getOrderNumber() + "-----" + input.getCodAmount()
						+ "-----" + input.getSenderProvince() + "-----" + input.getSenderDistrict() + "-----"
						+ input.getSenderAddress() + "-----" + input.getSenderName() + "-----" + input.getSenderEmail()
						+ "-----" + input.getSenderTel() + "-----" + input.getSenderDesc() + "-----"
						+ input.getDescription() + "-----" + receiverName + "-----" + receiverAddress + "-----"
						+ receiverTel + "-----" + receiverProvince + "-----" + receiverDistrict + "-----"
						+ receiverEmail);
				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} catch (Exception e) {
				_log.error(e);
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
			}
		}
	}

	private JSONObject getOrder(String apiGetToken, String customerKey, String secretKey, String apiGetOrderTracking,
			int pagesize, String lastId, String orderNumber) {
		JSONObject objreturn = JSONFactoryUtil.createJSONObject();
		String oId = "";
		try {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			MToken token = IToken.getToken(apiGetToken, customerKey, secretKey);

			MStatus status = IOrder.getOrderTracking(apiGetOrderTracking, token.getAccessToken(), token.getTokenType(),
					pagesize, lastId);

			if (status.getEmbedded() != null) {
				List<MRhDoc> lstDoc = status.getEmbedded().getRhDoc();

				//_log.info("lstDoc ====== " + ToStringBuilder.reflectionToString(lstDoc));
				int i = 0;
				for (MRhDoc rHdoc : lstDoc) {
					// _log.info("rHdoc ====== " + ToStringBuilder.reflectionToString(rHdoc));
					// String strRhDoc = rHdoc.getOrderNumber();
					// _log.info("orderNumber ====== " + rHdoc.getOrderNumber());
					if (rHdoc.getOrderNumber().equals(orderNumber)) {
						obj.put(VnPostTerm.STATUS_CODE, rHdoc.getStatusCode());
						obj.put(VnPostTerm.STATUS_MESSAGE, rHdoc.getStatusMessage());
						// _log.info("obj length ====== " + obj.length());
					}
					int j = i++;
					// _log.info("lstDoc length ====== " + lstDoc.size());
					if (j == lstDoc.size() - 1) {
						if (obj.length() != 0) {
							//_log.info("obj1 ====== " + obj.toJSONString());
							objreturn = obj;
							break;
						} else {
							oId = rHdoc.getId().getoid();
							//_log.info("oId ====== " + oId);
						}
					}
				}
				//_log.info("objreturn length  ====== " + objreturn.length());
				if (objreturn.length() == 0) {
					//_log.info("check   ====== ");
					objreturn = getOrder(apiGetToken, customerKey, secretKey, apiGetOrderTracking, pagesize, oId, orderNumber);
				}
			}
			//_log.info("OrderTrackingReturn ====== " + objreturn.toJSONString());
		} catch (Exception e) {
			_log.error(e);
			// return (JSONObject)
			// Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
		return objreturn;
	}

	@Override
	public Response getOrderTracking(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VNPostGetOrderModel input) {
		// String tokenUrl = "https://api.mitc.vn/token";
		// String consumer_key = "ddabyJ69AeQyzhFvlnXNROKQs7Ia";
		// String secret_key = "kFqNeYCoLtr4MMSnfRcXQwhIO3Aa";
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// _log.info("groupId = =========" + groupId);
		String serverNo = VnPostTerm.getVNPostServerNo(input.getGovAgencyCode());
		VNPostServerConfigModel config = getServerConfig(groupId, serverNo, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);

		try {

			JSONObject jsondata = getOrder(config.getApiGetToken(), config.getCustomerKey(), config.getSecretKey(),
					config.getApiGetOrderTracking(), input.getPageSize(), input.getLastId(), input.getOrderNumber());

			//_log.info("OrderTrackingReturn VNPOST====== " + jsondata.toJSONString());

			if (jsondata.length() == 0) {
				return Response.status(204).entity(ConfigProps.get(ConfigConstants.EVALUATION_NO_CONTENT_EX)).build();
			}
			return Response.status(200).entity(jsondata.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}

	}
	
	@Override
	public Response cancelOrder(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VNPostCancelOrderModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		String serverNo = VnPostTerm.getVNPostServerNo(input.getGovAgencyCode());
		VNPostServerConfigModel config = getServerConfig(groupId, serverNo, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
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
				
				MResult result = IOrder.cancelOrder(config.getApiCancelOrder(), token.getAccessToken(),
						token.getTokenType(), input.getCustomerCode(), input.getOrderNumber());
				
				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} catch (Exception e) {
				_log.error(e);
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
			}
		}
	}

}
