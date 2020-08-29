package org.opencps.api.controller.impl;

import backend.auth.api.exception.ErrorMsgModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.backend.dossiermgt.logistic.ViettelPostManagement;
import org.opencps.backend.dossiermgt.logistic.ViettelPostManagementImpl;
import org.opencps.backend.dossiermgt.logistic.ViettelPostTerm;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.VNPostManagement;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.api.dossier.model.DossierInputModel;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.rest.model.ViettelPostUpdateOrder;
import org.opencps.dossiermgt.rest.model.ViettlePostResponse;
import org.opencps.dossiermgt.service.*;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class VNPostManagementImpl implements VNPostManagement
{
	private static final Log _log = LogFactoryUtil.getLog(VNPostManagementImpl.class);
	private static final String NO_SERVER_CONFIG_MESSAGE = "No config for vietel client";
	private static final String MESS_TOKEN_INVALID = "Token invalid";
	private static final String MESS_SYSTEM_ERROR = "System error";
	private static final String MESS_NO_ORDER_FOUND = "No order found";
	private static final String MESS_UPDATE_SUCCESS = "Update succeed";
	private static final String HEADER_TOKEN = "token";

	@Override
	public Response testPostBill(HttpServletRequest request, HttpHeaders header,Locale locale) {
//		ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(544833, ViettelPostTerm.SERVER_NO,
//				ViettelPostTerm.PROTOCOL);
		try{
//			ViettelPostManagement viettelPostManagement = new ViettelPostManagementImpl(serverConfig);
//			String token = viettelPostManagement.getToken();
//			JSONObject dossier = JSONFactoryUtil.createJSONObject();
//			dossier.put("dossierId", 2121);
//			dossier.put("postalTelNo", "0987654567");
//
//			viettelPostManagement.postBill(token, dossier);
			Message message = new Message();
			JSONObject msgData = JSONFactoryUtil.createJSONObject();

			message.put(ConstantUtils.MSG_ENG, msgData);
			message.put(DossierTerm.CONSTANT_DOSSIER, DossierMgtUtils
					.convertDossierToJSON(DossierLocalServiceUtil.getDossier(96204), 0));

			MessageBusUtil.sendMessage(DossierTerm.VNPOST_DOSSIER_DESTINATION, message);

			return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
		}catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response testViettlePost(HttpServletRequest request, HttpHeaders header,Locale locale) {
		try {
			_log.info("Viettel post schedule at : " + APIDateTimeUtils.convertDateToString(new Date()));
			List<PostConnect> listPostConnect = PostConnectLocalServiceUtil.getBySyncState(PublishQueueTerm.STATE_WAITING_SYNC);

			if(Validator.isNull(listPostConnect) || listPostConnect.isEmpty()) {
				return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
			}
			long dossierId;
			long groupId;
			long userId;
			long companyId;
			LinkedHashMap<String, Object> params = null;
			DossierActions actions = new DossierActionsImpl();
			JSONArray jsonData;
			String preCondition;
			Integer postStatus;
			Integer postStatusInPre = null;
			String actionCode;
			Dossier dossier;
			boolean updateStatus;
			ServiceContext serviceContext;
			User user;
			Company company;

			for(PostConnect postConnect : listPostConnect) {
				updateStatus = false;
				dossierId  = postConnect.getDossierId();
				groupId    = postConnect.getGroupId();
				//todo change this code
				userId     = postConnect.getUserId();
				companyId  = postConnect.getCompanyId();
				postStatus = postConnect.getPostStatus();
				dossier    = DossierLocalServiceUtil.getDossier(dossierId);

				user = UserLocalServiceUtil.getUser(userId);
				if(Validator.isNull(dossier)) {
					_log.error("Not found dossierID: " + dossierId);
					postConnect.setSyncState(PublishQueueTerm.STATE_ACK_ERROR);
					PostConnectLocalServiceUtil.updatePostConnect(postConnect);
					continue;
				}

				serviceContext = MBServiceContextFactoryUtil.create(companyId, groupId, userId);

				params = new LinkedHashMap<>();
				params.put(Field.GROUP_ID, String.valueOf(groupId));
				params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));
				jsonData = actions.getNextActionList(
						userId, companyId, groupId, params,
						null, 0, 10, serviceContext);

				if(Validator.isNull(jsonData) || jsonData.length() == 0) {
					_log.error("List next action null for dossierId: " + dossierId);
//					postConnect.setSyncState(PublishQueueTerm.STATE_ACK_ERROR);
//					PostConnectLocalServiceUtil.updatePostConnect(postConnect);
					continue;
				}

				for (int i = 0; i < jsonData.length(); i++) {
					//todo remove hard code
					preCondition = jsonData.getJSONObject(i).getString(ProcessActionTerm.PRE_CONDITION)+ ",postStatus=2";
					if (preCondition.contains(DossierTerm.CONTAIN_POST_STATUS)) {
						String[] splitCodes = preCondition.split(StringPool.COMMA);
						for(String oneSplitComma : splitCodes) {
							if(oneSplitComma.contains(DossierTerm.CONTAIN_POST_STATUS)) {
								String[] keyValuePostStatus = oneSplitComma.split(StringPool.EQUAL);
								if (splitCodes.length == 2) {
									postStatusInPre = Validator.isNotNull(keyValuePostStatus[1]) ? Integer.valueOf(keyValuePostStatus[1]) : null;
								}
							}
						}

						if(Validator.isNull(postStatusInPre)) {
							_log.error("PostStatus In Precondition is null with dossierId: " + dossierId);
//							postConnect.setSyncState(PublishQueueTerm.STATE_ACK_ERROR);
//							PostConnectLocalServiceUtil.updatePostConnect(postConnect);
							continue;
						}
						_log.info("PostStatus In Precondition : " + postStatusInPre);
						if(postStatus.equals(postStatusInPre)) {
							actionCode = jsonData.getJSONObject(i).getString(ProcessActionTerm.ACTION_CODE);
							//call do action
							if(Validator.isNotNull(actionCode)){
								updateStatus = doAction(actionCode, groupId, dossier, user, null, serviceContext);
								break;
							}
						}
					}
				}

//				if (updateStatus) {
//					postConnect.setSyncState(PublishQueueTerm.STATE_RECEIVED_ACK);
//				} else {
//					postConnect.setSyncState(PublishQueueTerm.STATE_ACK_ERROR);
//				}
//				PostConnectLocalServiceUtil.updatePostConnect(postConnect);
			}
			_log.info("End Viettel post schedule!!!");
		} catch (Exception e){
			_log.error(e);
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
	}

	private boolean doAction(String actionCode, long groupId, Dossier dossier, User user
			, Company company, ServiceContext serviceContext) {
		try {
			_log.info("----VIETTEL POST: doing action");
			DossierActions actions = new DossierActionsImpl();
			ErrorMsgModel errorModel = new ErrorMsgModel();
			DossierAction dossierResult = null;

			ActionConfig actionConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
			String serviceCode = dossier.getServiceCode();
			String govAgencyCode = dossier.getGovAgencyCode();
			String dossierTempNo = dossier.getDossierTemplateNo();

			//case action config null
			if(Validator.isNull(actionConfig)) {
				_log.info("----VIETTEL POST: case action config null");
				ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceCode, govAgencyCode);
				if (Validator.isNull(config)) {
					throw new Exception("Server config not found for serviceCode "
							+ serviceCode + ", govAgencyCode " + govAgencyCode);
				}
				ProcessOption option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTempNo,
						config.getServiceConfigId());

				if (Validator.isNull(option)) {
					throw new Exception("ProcessOption not found for dossierTempNo " + dossierTempNo
							+ ", ServiceConfigId " + config.getServiceConfigId());
				}
				long serviceProcessId = option.getServiceProcessId();
				ProcessAction proAction = getProcessAction(user, groupId, dossier, actionCode, serviceProcessId);
				if(Validator.isNull(proAction)) {
					throw new Exception("ProcessAction not found for actionCode " + actionCode
							+ ", serviceProcessId " + serviceProcessId);
				}

				dossierResult = actions.doAction(
						groupId, user.getUserId(), dossier, option, proAction,
						actionCode, "" ,
						"", "",
						"", "",
						0, serviceContext, errorModel);
				return Validator.isNotNull(dossierResult);
			}

			//case action config not null
			_log.info("----VIETTEL POST: case action config not null");
			boolean insideProcess = actionConfig.getInsideProcess();
			ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceCode, govAgencyCode);
			if(Validator.isNull(config)){
				throw new Exception("Server config not found for serviceCode "
						+ serviceCode + ", govAgencyCode " + govAgencyCode);
			}
			ProcessOption option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTempNo,
					config.getServiceConfigId());

			if(!insideProcess) {
				dossierResult = actions.doAction(
						groupId, user.getUserId(), dossier, option, null,
						actionCode, "", "",
						"", "",
						"", actionConfig.getSyncType(),
						serviceContext, errorModel);
				return Validator.isNotNull(dossierResult);
			}

			if (dossier.getDossierActionId() == 0) {
				if (Validator.isNull(option)) {
					throw new Exception("ProcessOption not found for dossierTempNo " + dossierTempNo
							+ ", ServiceConfigId " + config.getServiceConfigId());
				}
				long serviceProcessId = option.getServiceProcessId();
				ProcessAction proAction = getProcessAction(user, groupId, dossier, actionCode, serviceProcessId);
				if(Validator.isNull(proAction)) {
					throw new Exception("ProcessAction not found for actionCode " + actionCode
							+ ", serviceProcessId " + serviceProcessId);
				}
				dossierResult = actions.doAction(
						groupId, user.getUserId(), dossier, option, proAction,
						actionCode, "" ,
						"", "",
						"", "",
						0, serviceContext, errorModel);
				return Validator.isNotNull(dossierResult);
			}

			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(
					dossier.getDossierActionId());

			if(Validator.isNull(dossierAction)) {
				throw new Exception("Not found dossierAction");
			}

			long serviceProcessId = dossierAction.getServiceProcessId();
			DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(
					groupId,
					dossier.getDossierTemplateNo());

			ProcessOption oldOption = ProcessOptionLocalServiceUtil.fetchBySP_DT(
					serviceProcessId,
					dossierTemplate.getDossierTemplateId());

			ProcessAction proAction = getProcessAction(user, groupId, dossier, actionCode, serviceProcessId);
			if(Validator.isNull(proAction)) {
				throw new Exception("ProcessAction not found for actionCode " + actionCode
						+ ", serviceProcessId " + serviceProcessId);
			}

			dossierResult = actions.doAction(
					groupId, user.getUserId(), dossier, oldOption, proAction,
					actionCode, "" ,
					"", "",
					"", "",
					actionConfig.getSyncType(), serviceContext, errorModel);
			_log.info("----VIETTEL POST: End do action for dossier " + dossier.getDossierId());
			return Validator.isNotNull(dossierResult);
		} catch (Exception e) {
			_log.debug(e);
			return false;
		}
	}

	private ProcessAction getProcessAction(User user, long groupId, Dossier dossier, String actionCode,
										   long serviceProcessId) throws Exception{
		_log.info("----VIETTEL POST: getting process action");
		try {
			ProcessAction action = null;
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());

			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
					serviceProcessId);
			_log.debug("GET PROCESS ACTION____" + groupId + "," + actionCode + "," + serviceProcessId);
			String dossierStatus = dossier.getDossierStatus();
			String dossierSubStatus = dossier.getDossierSubStatus();
			String preStepCode;
			String curStepCode = StringPool.BLANK;
			if (dossier.getDossierActionId() > 0) {
				DossierAction curAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
				if (curAction != null) {
					curStepCode = curAction.getStepCode();
				}
			}
			for (ProcessAction act : actions) {

				preStepCode = act.getPreStepCode();
				_log.debug("LamTV_preStepCode: "+preStepCode);
				if (Validator.isNotNull(curStepCode) && !preStepCode.contentEquals(curStepCode)) continue;

				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);

				if (Validator.isNull(step) && dossierAction == null) {
					action = act;
					break;
				} else {
					String stepStatus = step != null ? step.getDossierStatus() : StringPool.BLANK;
					String stepSubStatus = step != null ?  step.getDossierSubStatus() : StringPool.BLANK;
					boolean flagCheck = false;

					if (dossierAction != null) {
						if (act.getPreStepCode().equals(dossierAction.getStepCode())) {
							flagCheck = true;
						}
					}
					else {
						flagCheck = true;
					}
					_log.debug("LamTV_preStepCode: "+stepStatus + "," + stepSubStatus + "," + dossierStatus + "," + dossierSubStatus + "," + act.getPreCondition() + "," + flagCheck);
					if (stepStatus.contentEquals(dossierStatus)
							&& StringUtil.containsIgnoreCase(stepSubStatus, dossierSubStatus)
							&& flagCheck) {
						if (Validator.isNotNull(act.getPreCondition()) && DossierMgtUtils.checkPreCondition(act.getPreCondition().split(StringPool.COMMA), dossier, user)) {
							action = act;
							break;
						}
						else if (Validator.isNull(act.getPreCondition())) {
							action = act;
							break;
						}
					}
				}
			}
			_log.info("----VIETTEL POST: End get process action");

			return action;
		} catch (Exception e) {
			_log.debug(e);
			return null;
		}
	}

	@Override
	public Response updateOrder(HttpHeaders header, ViettelPostUpdateOrder updateInfo) {
		ViettlePostResponse response;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			//todo change to dynamic token
			String tokenFDS = "iyJhbGciOiJFUzI1NiJ9.eutVc2VyewciOjgxNTcwdsadb3VyY2UiOweqIlRva2VuIjoiRj";
			String tokenHeader = header.getHeaderString(HEADER_TOKEN);

			//Check token valid
			if(!tokenFDS.equals(tokenHeader)) {
				response = new ViettlePostResponse(ViettelPostTerm.TOKEN_INVALID,
						true, MESS_TOKEN_INVALID, null);

				return Response.status(HttpURLConnection.HTTP_OK)
						.entity(objectMapper.writeValueAsString(response)).build();
			}

			//Check config vietel exists in FDS DB
			List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(ViettelPostTerm.SERVER_NO,
					ViettelPostTerm.PROTOCOL);

			if(Validator.isNull(listConfig) || listConfig.isEmpty()) {
				throw new Exception(NO_SERVER_CONFIG_MESSAGE);
			}

			ServerConfig serverConfig = listConfig.get(0);
			if(Validator.isNull(serverConfig)) {
				throw new Exception(NO_SERVER_CONFIG_MESSAGE);
			}

			//Update bill order
			ViettelPostManagement viettelPostManagement = new ViettelPostManagementImpl(serverConfig);
			boolean saveSuccess = viettelPostManagement.updateBill(updateInfo);
			if (!saveSuccess) {
				response = new ViettlePostResponse(ViettelPostTerm.NO_ORDER_FOUND,
						true, MESS_NO_ORDER_FOUND, null);

				return Response.status(HttpURLConnection.HTTP_OK)
						.entity(objectMapper.writeValueAsString(response)).build();
			}

			response = new ViettlePostResponse(ViettelPostTerm.SUCCESS,
					false, MESS_UPDATE_SUCCESS, null);

			return Response.status(HttpURLConnection.HTTP_OK).entity(objectMapper.writeValueAsString(response)).build();
		} catch (Exception e) {
			_log.error("Update viettle post bill error with info " + updateInfo.toString());
			_log.debug(e);
			response = new ViettlePostResponse(ViettelPostTerm.SYSTEM_ERROR,
					true, MESS_SYSTEM_ERROR, null);
			try {
				return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(objectMapper.writeValueAsString(response)).build();
			} catch (JsonProcessingException jsonProcessingException) {
				_log.debug(jsonProcessingException);
				return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
			}
		}
	}
	@Override
	public Response getDossierDetailByBarcode(HttpServletRequest request,HttpHeaders header,ServiceContext serviceContext,
		String receiptCode)
	{
		String [] receiptCodeSplit = receiptCode.split(StringPool.DASH);
		String checkKey = receiptCodeSplit[0];
		//String securityCode = StringPool.BLANK;
		long dossierId ;
		long dossierCounter ;
		//long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		if (Validator.isNotNull(receiptCode) && receiptCodeSplit.length==4 && ("D").equals(checkKey))
		{
			String securityCode = receiptCodeSplit[1];
			dossierId = Long.parseLong(receiptCodeSplit[2]);
			dossierCounter = Long.parseLong(receiptCodeSplit[3]);
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (Validator.isNotNull(dossier))
			{
				String password = dossier.getPassword();
				long counter = dossier.getCounter();
				if (Validator.isNotNull(password) && securityCode.equals(password) && Validator.isNotNull(counter) && dossierCounter==counter)
				{
					DossierDetailModel result = DossierUtils.mappingForGetDetail(dossier,0);
					return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
				}
			}
		}
		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

	@Override public Response updateDossierByBarcode(HttpServletRequest request,HttpHeaders header,
		ServiceContext serviceContext,String receiptCode,DossierInputModel dossierInputModel, String ma_bien_nhan)
	{
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		int viaPostal = dossierInputModel.getViaPostal();
		String postalServiceCode = dossierInputModel.getPostalServiceCode();
		String postalServiceName = dossierInputModel.getPostalServiceName();
		String postalAddress = dossierInputModel.getPostalAddress();
		String postalCityCode = dossierInputModel.getPostalCityCode();
		String postalCityName  = dossierInputModel.getPostalCityName();
		String postalDistrictCode = dossierInputModel.getPostalDistrictCode();
		String postalDistrictName = dossierInputModel.getPostalDistrictName();
		String postalWardCode = dossierInputModel.getPostalWardCode();
		String postalWardName = dossierInputModel.getPostalWardName();
		String postalTelNo = dossierInputModel.getPostalTelNo();

		DossierActions dossierActions = new DossierActionsImpl();
		Dossier dossier = dossierActions.updateDossierVNPost(groupId , ma_bien_nhan,viaPostal, postalServiceCode,
			postalServiceName, postalAddress, postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalWardCode, postalWardName, postalTelNo);
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		if (Validator.isNotNull(dossier) && Validator.isNotNull(dossier.getDossierId()))
			jsonObject.put("status", "succeed");
		else
			jsonObject.put("status","failed");

		return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject.toString()).build();
	}
}