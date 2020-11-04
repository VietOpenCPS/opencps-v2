package backend.postal.api.rest.controller.impl;

import backend.postal.api.rest.controller.LGSPService;

import com.liferay.petra.string.StringPool;
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
		// _log.debug("configObj =============== " +
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
					configObj.getString(VnPostTerm.SERVER_SENDERNAME),
					configObj.getBoolean(VnPostTerm.LGSP_ACTIVE),
					configObj.getString(VnPostTerm.LGSP_USERNAME),
					configObj.getString(VnPostTerm.LGSP_PASSWORD),
					configObj.getString(VnPostTerm.LGSP_SERCUREKEY),
					configObj.getString(VnPostTerm.LGSP_CUSTOMERCODE),
					configObj.getString(VnPostTerm.LGSP_TOKENURL),
					configObj.getString(VnPostTerm.LGSP_VNPOSTURL),
					configObj.getString(VnPostTerm.LGSP_VNPOST_ORDERPOST),
					configObj.getString(VnPostTerm.LGSP_VNPOST_ORDERCANCEL),
					configObj.getString(VnPostTerm.LGSP_VNPOST_ORDERTRACKING),
					configObj.getString(VnPostTerm.LGSP_VNPOST_GETPOSTAGEVAS),
					configObj.getString(VnPostTerm.LGSP_VNPOST_GETINFOMATIONPOST),
					configObj.getString(VnPostTerm.LGSP_VNPOST_PRICEHCC),
					configObj.getString(VnPostTerm.LGSP_VNPOST_DOCUMENT),
					configObj.getString(VnPostTerm.LGSP_VNPOST_GETPRICE),
					configObj.getString(VnPostTerm.LGSP_CONSUMERKEY));

			if(configObj.has(VnPostTerm.SERVER_IS_THROUGH_LGSP)) {
				_log.debug("server is though lgsp");
				//case integrate VNPOST through LGSP(not NGSP)
				vnPostConfig.setLGSP(true);
				vnPostConfig.setKeyToken(configObj.getString(VnPostTerm.SERVER_KEY_TOKEN_LGSP));
			} else {
				_log.debug("server is ngsp");
				vnPostConfig.setLGSP(false);
			}

			return vnPostConfig;
		} else {
			_log.debug("Loi config");
			return null;
		}
	}

	public VNPostServerConfigModel getServerConfig(long groupId, String serverNo, String protocol) {
		VNPostServerConfigModel config = null;

		try {
			ServerConfig sc = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId, serverNo, protocol);
			_log.debug("Thong tin server config");
			_log.debug(sc.getConfigs());
			VNPostServerConfigModel check = fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
			// _log.debug("check ============ " + JSONFactoryUtil.looseSerialize(check));
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
		_log.debug("Info get server config: " + groupId + "|" + serverNo );
		VNPostServerConfigModel config = getServerConfig(groupId, serverNo, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
		if (config == null) {
			config = getServerConfig(groupId, VnPostTerm.SERVER_CONFIG_VIA_POSTAL_SERVERNO_D, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
		}
		//_log.debug("sendPostalRequest groupId ============= " + groupId);
		//_log.debug("sendPostalRequest lstsc ============= " + JSONFactoryUtil.looseSerialize(config));

		// String tokenUrl = "https://api.mitc.vn/token";
		// String consumer_key = "ddabyJ69AeQyzhFvlnXNROKQs7Ia";
		// String secret_key = "kFqNeYCoLtr4MMSnfRcXQwhIO3Aa";
		if (config == null) {
			return null;
		} else {
			try {
				MToken token = this.getMToken(config);

				if(Validator.isNull(token)) {
					return null;
				}
				// String apiUrl = "https://api.mitc.vn/apiVNPostNGSP/p1.0/order/post";

				// _log.debug("token ============= " + JSONFactoryUtil.looseSerialize(token));

				String senderName = Validator.isNotNull(config.getSenderName()) ? config.getSenderName()
						: input.getSenderName();
				_log.debug("THONG TIN VNPOST: " + config);
				MOrder order = new MOrder(config.getCustomerCode(), input.getOrderNumber(), input.getCodAmount(),
						config.getSenderProvince(), config.getSenderDistrict(), config.getSenderAddress(), senderName,
						config.getSenderEmail(), config.getSenderTel(), input.getSenderDesc(), input.getDescription(),
						input.getReceiverName(), input.getReceiverAddress(), input.getReceiverTel(),
						input.getReceiverProvince(), input.getReceiverDistrict(), input.getReceiverEmail());
				_log.debug("THONG TIN order: " + order);
				MResult result = IOrder.postOrder(config.getApiPostOrder(), token.getAccessToken(),
						token.getTokenType(), order);
				_log.debug("THONG TIN result: " + result);
				_log.debug(config.getCustomerCode() + "-----" + input.getOrderNumber() + "-----" + input.getCodAmount()
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
				MToken token = this.getMToken(config);

				if(Validator.isNull(token)) {
					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(null).build();
				}

				_log.debug(token);
				_log.debug("input============" + input);
				_log.debug("config============" + config);
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
				_log.debug(config.getCustomerCode() + "-----" + input.getOrderNumber() + "-----" + input.getCodAmount()
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
	
	@Override
	public Response getPostalPrice(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, VNPostInputModel input) {
		
		
		try {
			
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String serverNo = VnPostTerm.getVNPostServerNo(input.getGovAgencyCode());
			
			_log.debug("Info get server config: " + groupId + "|" + serverNo );
			VNPostServerConfigModel config = null;
			
			try {
				config = getServerConfig(groupId, serverNo, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
			}catch(Exception e) {
				
			}
			
			
			if (config == null) {
				
				try {
				
				config = getServerConfig(groupId, VnPostTerm.SERVER_CONFIG_VIA_POSTAL_SERVERNO_D, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);
				}catch(Exception e){
					
				}
			}
	
			if (config == null) {
				return null;
			} else {
				
					
				String result = StringPool.BLANK;
				
				int provinceCodeSender = input.getSenderProvince();
				int districtCodeSender = input.getSenderDistrict();
				int provinceCodeReceiver= input.getReceiverProvince();
				int districtCodeReceiver = input.getReceiverDistrict();
				
				_log.debug("+++provinceCodeSender:"+provinceCodeSender);
				_log.debug("+++districtCodeSender:"+districtCodeSender);
				
				_log.debug("+++provinceCodeReceiver:"+provinceCodeReceiver);
				_log.debug("+++districtCodeReceiver:"+districtCodeReceiver);
				
				if(provinceCodeSender > 0) {
					provinceCodeReceiver = config.getSenderProvince();
					districtCodeReceiver = config.getSenderDistrict();
				}else {
					provinceCodeSender = config.getSenderProvince();
					districtCodeSender =  config.getSenderDistrict();
				}
				

				JSONObject datas = createVNPOSTPriceData(provinceCodeSender, districtCodeSender,provinceCodeReceiver,districtCodeReceiver, input.getWeight());
				
				_log.debug("++datas:"+datas);
				
				boolean isLGSPActive = config.isLgspActive();
				
				if(isLGSPActive) {
				
					LGSPService lgspService = new LGSPServiceImpl();
					String access_token = lgspService.getToken(config.getLgspTokenUrl(), config.getLgspConsumerKey(), config.getLgspSercureKey());
					
					StringBuilder serviceURL = new StringBuilder();
					serviceURL.append(config.getLgspVnpostUrl()).append(StringPool.QUESTION).append("service").append(StringPool.EQUAL).append(config.getLgspVnpostGetPrice());

					result = lgspService.getVNPOSTPrice(serviceURL.toString(), access_token, datas);
				
				}
				
				if(Validator.isNotNull(result)) {
					return Response.status(200).entity(result).build();
				}else {
					return Response.status(417).entity(result).build();
				}
				
			}
			
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}
	
	private JSONObject createVNPOSTPriceData(int provinceCodeSender,int districtCodeSender,int provinceCodeReceiver,int districtCodeReceiver,int weight) {
		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("weight", weight);
		jsonObject.put("mainSrv", "HCC");
		jsonObject.put("isreturn", 1);
		
		JSONObject senderObject = JSONFactoryUtil.createJSONObject();
		senderObject.put("provCode", provinceCodeSender);
		senderObject.put("distCode", districtCodeSender);
		
		JSONObject receiverObject = JSONFactoryUtil.createJSONObject();
		receiverObject.put("provCode", provinceCodeReceiver);
		receiverObject.put("distCode", districtCodeReceiver);
		
		jsonObject.put("sender", senderObject);
		jsonObject.put("receiver", receiverObject);

		
		return jsonObject;
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

				//_log.debug("lstDoc ====== " + ToStringBuilder.reflectionToString(lstDoc));
				int i = 0;
				for (MRhDoc rHdoc : lstDoc) {
					// _log.debug("rHdoc ====== " + ToStringBuilder.reflectionToString(rHdoc));
					// String strRhDoc = rHdoc.getOrderNumber();
					// _log.debug("orderNumber ====== " + rHdoc.getOrderNumber());
					if (rHdoc.getOrderNumber().equals(orderNumber)) {
						obj.put(VnPostTerm.STATUS_CODE, rHdoc.getStatusCode());
						obj.put(VnPostTerm.STATUS_MESSAGE, rHdoc.getStatusMessage());
						// _log.debug("obj length ====== " + obj.length());
					}
					int j = i++;
					// _log.debug("lstDoc length ====== " + lstDoc.size());
					if (j == lstDoc.size() - 1) {
						if (obj.length() != 0) {
							//_log.debug("obj1 ====== " + obj.toJSONString());
							objreturn = obj;
							break;
						} else {
							oId = rHdoc.getId().getoid();
							//_log.debug("oId ====== " + oId);
						}
					}
				}
				//_log.debug("objreturn length  ====== " + objreturn.length());
				if (objreturn.length() == 0) {
					//_log.debug("check   ====== ");
					objreturn = getOrder(config, pagesize, oId, orderNumber);
				}
			}
			//_log.debug("OrderTrackingReturn ====== " + objreturn.toJSONString());
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
		// _log.debug("groupId = =========" + groupId);
		String serverNo = VnPostTerm.getVNPostServerNo(input.getGovAgencyCode());
		VNPostServerConfigModel config = getServerConfig(groupId, serverNo, VnPostTerm.SERVER_CONFIG_VIA_POSTAL);

		try {
			JSONObject jsondata = getOrder(config, input.getPageSize(), input.getLastId(), input.getOrderNumber());
			if(Validator.isNull(jsondata)) {
				return Response.status(204).entity(ConfigProps.get(ConfigConstants.EVALUATION_NO_CONTENT_EX)).build();
			}
			//_log.debug("OrderTrackingReturn VNPOST====== " + jsondata.toJSONString());

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
		// _log.debug("config ============= " + JSONFactoryUtil.looseSerialize(config));

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
			String urlGetToken = config.getApiGetToken();
			String keyToken    = config.getKeyToken();
			_log.debug("token: " + urlGetToken + ", key:" + keyToken);
			LGSPService lgspService = new LGSPServiceImpl();
			return lgspService.getToken(urlGetToken, keyToken);

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
