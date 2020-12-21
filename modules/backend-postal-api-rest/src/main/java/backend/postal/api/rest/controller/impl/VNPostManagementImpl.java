package backend.postal.api.rest.controller.impl;

import backend.postal.api.rest.controller.LGSPService;
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
import java.util.*;

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
import org.springframework.http.MediaType;
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
				&& configObj.has(VnPostTerm.SERVER_APIGETORDERTRACKING) && configObj.has(VnPostTerm.SERVER_ACTIVE)) {
			VNPostServerConfigModel vnPostConfig = new VNPostServerConfigModel(configObj.getString(VnPostTerm.SERVER_APIPOSTORDER),
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

			if(configObj.has(VnPostTerm.SERVER_IS_THROUGH_LGSP)) {
				_log.info("server is though lgsp");
				//case integrate VNPOST through LGSP(not NGSP)
				vnPostConfig.setLGSP(true);
				if(configObj.has(VnPostTerm.SERVER_LGSP_TYPE) && !configObj.getString(VnPostTerm.SERVER_LGSP_TYPE).isEmpty()) {
					vnPostConfig.setLgspType(configObj.getString(VnPostTerm.SERVER_LGSP_TYPE));
				} else {
					vnPostConfig.setLgspType("");
				}
				//key token dong thap
				vnPostConfig.setKeyToken(configObj.getString(VnPostTerm.SERVER_KEY_TOKEN_LGSP));
			} else {
				_log.info("server is ngsp");
				vnPostConfig.setLGSP(false);
			}

			return vnPostConfig;
		} else {
			_log.info("Loi config");
			return null;
		}
	}

	public VNPostServerConfigModel getServerConfig(long groupId, String serverNo, String protocol) {
		VNPostServerConfigModel config = null;

		try {
			ServerConfig sc = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId, serverNo, protocol);
			_log.info("Thong tin server config");
			_log.info(sc.getConfigs());
			VNPostServerConfigModel check = fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
			// _log.info("check ============ " + JSONFactoryUtil.looseSerialize(check));
			if (check.getActive()) {
				config = check;
			}
			return config;
		} catch (Exception e) {
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
		_log.info("Info get server config: " + groupId + "|" + serverNo );
		VNPostServerConfigModel config = getServerConfig(groupId, serverNo, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
		if (config == null) {
			config = getServerConfig(groupId, VnPostTerm.SERVER_CONFIG_VIA_POSTAL_SERVERNO_D, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
		}

		if (config == null) {
			return null;
		} else {
			try {
				MToken token = this.getMToken(config);

				if(Validator.isNull(token)) {
					return null;
				}

				String senderName = Validator.isNotNull(config.getSenderName()) ? config.getSenderName()
						: input.getSenderName();
				_log.info("THONG TIN VNPOST: " + config);
				MOrder order = new MOrder(config.getCustomerCode(), input.getOrderNumber(), input.getCodAmount(),
						config.getSenderProvince(), config.getSenderDistrict(), config.getSenderAddress(), senderName,
						config.getSenderEmail(), config.getSenderTel(), input.getSenderDesc(), input.getDescription(),
						input.getReceiverName(), input.getReceiverAddress(), input.getReceiverTel(),
						input.getReceiverProvince(), input.getReceiverDistrict(), input.getReceiverEmail());
				_log.info("THONG TIN order: " + order);

				if(config.isLGSP() && config.getLgspType().equals("TTTT")) {
					LGSPService lgspService = new LGSPServiceImpl();
					JSONObject result = lgspService.postTTTT(config.getApiPostOrder(), token.getAccessToken(),
							this.parseMOrderToMap(order), "vnpost_post_order");
					return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
				}

				MResult result = IOrder.postOrder(config.getApiPostOrder(), token.getAccessToken(),
						token.getTokenType(), order);
				_log.info("THONG TIN result: " + result);
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

	private Map<String, Object> parseMOrderToMap(MOrder order) {
		Map<String, Object> body = new HashMap<>();
		body.put(VnPostTerm.CUSTOMER_CODE, order.getCustomerCode());
		body.put(VnPostTerm.ORDER_NUMBER, order.getOrderNumber());
		body.put(VnPostTerm.COD_AMOUNT, order.getCODAmount());
		body.put(VnPostTerm.SENDER_PROVINCE, order.getSenderProvince());
		body.put(VnPostTerm.SENDER_DISTRICT, order.getSenderDistrict());
		body.put(VnPostTerm.SENDER_ADDRESS, order.getSenderAddress());
		body.put(VnPostTerm.SENDER_NAME, order.getSenderName());
		body.put(VnPostTerm.SENDER_EMAIL, order.getSenderEmail());
		body.put(VnPostTerm.SENDER_TEL, order.getSenderTel());
		body.put(VnPostTerm.SENDER_DESC, order.getSenderDesc());
		body.put(VnPostTerm.DESCRIPTION, order.getDescription());
		body.put(VnPostTerm.RECEIVER_NAME, order.getReceiverName());
		body.put(VnPostTerm.RECEIVER_ADDRESS, order.getReceiverAddress());
		body.put(VnPostTerm.RECEIVER_TEL, order.getReceiverTel());
		body.put(VnPostTerm.RECEIVER_PROVINCE, order.getReceiverProvince());
		body.put(VnPostTerm.RECEIVER_DISTRICT, order.getReceiverDistrict());
		body.put(VnPostTerm.RECEIVER_EMAIL, order.getReceiverEmail());
		return body;
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
				MToken token = this.getMToken(config);

				if(Validator.isNull(token)) {
					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(null).build();
				}

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

				if(config.isLGSP() && config.getLgspType().equals("TTTT")) {
					LGSPService lgspService = new LGSPServiceImpl();
					JSONObject result = lgspService.postTTTT(config.getApiPostOrder(), token.getAccessToken(),
							this.parseMOrderToMap(order), "vnpost_post_order");
					return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
				}

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

	private JSONObject getOrder(VNPostServerConfigModel config,
			int pagesize, String lastId, String orderNumber) {
		JSONObject objreturn = JSONFactoryUtil.createJSONObject();
		String oId = "";
		try {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			MToken token = this.getMToken(config);

			if(Validator.isNull(token)) {
				return null;
			}

			MStatus status = IOrder.getOrderTracking(config.getApiGetOrderTracking(), token.getAccessToken(), token.getTokenType(),
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
					objreturn = getOrder(config, pagesize, oId, orderNumber);
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
			JSONObject jsondata = getOrder(config, input.getPageSize(), input.getLastId(), input.getOrderNumber());
			if(Validator.isNull(jsondata)) {
				return Response.status(204).entity(ConfigProps.get(ConfigConstants.EVALUATION_NO_CONTENT_EX)).build();
			}
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
				MToken token = this.getMToken(config);
				if(Validator.isNull(token)) {
					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(null).build();
				}
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

	private JSONObject getTokenLGSP(VNPostServerConfigModel config) {
		try {
			LGSPService lgspService = new LGSPServiceImpl();

			if(Validator.isNotNull(config.getLgspType()) && config.getLgspType().equals("TTTT")) {
				String customerKey = config.getCustomerKey();
				String secretKey   = config.getSecretKey();

				String keyLogin = "";
				if(!customerKey.isEmpty() && !secretKey.isEmpty()) {
					String keyBeforeEncode = customerKey + ":" + secretKey;
					keyLogin = Base64.getEncoder().encodeToString(keyBeforeEncode.getBytes());
				}

				if(keyLogin.isEmpty()) {
					throw new Exception("No keylogin");
				}

				_log.info("token: " + config.getApiGetToken() + ", key:" + keyLogin);
				return lgspService.getTokenTTTT(config.getApiGetToken(), keyLogin);
			} else {
				String urlGetToken = config.getApiGetToken();
				String keyToken    = config.getKeyToken();
				_log.info("token: " + urlGetToken + ", key:" + keyToken);
				return lgspService.getToken(urlGetToken, keyToken);
			}
		} catch (Exception e) {
			_log.error("Error when get token LGSP: " + e.getMessage());
			return null;
		}
	}

	private MToken getMToken(VNPostServerConfigModel config) {
		try {
			MToken token;
			if(config.isLGSP()) {
				token = new MToken();
				JSONObject jsonToken = this.getTokenLGSP(config);
				if(Validator.isNull(jsonToken)) {
					return null;
				}

				if(!jsonToken.has("access_token")) {
					return null;
				}

				if(!jsonToken.has("token_type")) {
					return null;
				}

				token.setAccessToken(jsonToken.getString("access_token"));
				token.setTokenType(jsonToken.getString("token_type"));
			} else {
				token = IToken.getToken(config.getApiGetToken(), config.getCustomerKey(), config.getSecretKey());
			}
			return token;
		} catch (Exception e) {
			_log.error(e.getMessage());
			return null;
		}
	}
}
