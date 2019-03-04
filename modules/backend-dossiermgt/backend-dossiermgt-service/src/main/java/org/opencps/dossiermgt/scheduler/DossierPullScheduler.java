package org.opencps.dossiermgt.scheduler;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.MultipartUtility;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierRequestUD;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

//@Component(immediate = true, service = DossierPullScheduler.class)
public class DossierPullScheduler extends BaseSchedulerEntryMessageListener {
	private final String serectKey = "OPENCPSV2";
	private static final int BUFFER_SIZE = 4096;

	@Override
	protected void doReceive(Message message) throws Exception {

		_log.info("OpenCPS PULL DOSSIERS IS STARTING : " + APIDateTimeUtils.convertDateToString(new Date()));

		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		User systemUser = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(),
				RESTFulConfiguration.SERVER_USER);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());
		serviceContext.setUserId(systemUser.getUserId());

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

		try {
			String endPointDossier = "dossiers?submitting=true&secetKey=" + serectKey;

			JSONObject resDossierSearch = rest.callAPI(0l, HttpMethods.GET, "application/json",
					RESTFulConfiguration.SERVER_PATH_BASE, endPointDossier, RESTFulConfiguration.SERVER_USER,
					RESTFulConfiguration.SERVER_PASS, properties, serviceContext);

			if (GetterUtil.getInteger(resDossierSearch.get(RESTFulConfiguration.STATUS)) != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ GetterUtil.getInteger(resDossierSearch.get(RESTFulConfiguration.STATUS)));
			}

			JSONObject jsData = JSONFactoryUtil
					.createJSONObject(resDossierSearch.getString(RESTFulConfiguration.MESSAGE));

			JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString("data"));

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				try {
					pullDossier(company, object, systemUser);
				} catch (Exception e) {
					_log.error(object.get(DossierTerm.DOSSIER_ID), e);
				}
			}

		} catch (Exception e) {
			_log.error(e);
//			e.printStackTrace();

		}
	}

	private void pullDossier(Company company, JSONObject object, User systemUser) throws PortalException, Exception {
		long dossierId = GetterUtil.getLong(object.get(DossierTerm.DOSSIER_ID));

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());
		serviceContext.setUserId(object.getLong(DossierTerm.USER_ID));

		long sourceGroupId = object.getLong(Field.GROUP_ID);
		String referenceUid = object.getString(DossierTerm.REFERENCE_UID);

		String serverno = object.getString(DossierTerm.SERVER_NO);

		List<ServiceProcess> processes = ServiceProcessLocalServiceUtil.getByServerNo(serverno);

		List<ServiceProcess> syncProcesses = new ArrayList<ServiceProcess>();

		String serviceInfoCode = object.getString(DossierTerm.SERVICE_CODE);
		String govAgencyCode = object.getString(DossierTerm.GOV_AGENCY_CODE);
		String dossierTemplateCode = object.getString(DossierTerm.DOSSIER_TEMPLATE_NO);

		DossierActions actions = new DossierActionsImpl();

		for (ServiceProcess process : processes) {

			long desGroupId = process.getGroupId();

			try {
				ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(desGroupId, serviceInfoCode,
						govAgencyCode);

				ProcessOption option = null;

				if (Validator.isNotNull(config)) {
					option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(desGroupId, dossierTemplateCode,
							config.getServiceConfigId());
				}

				if (Validator.isNotNull(option)) {

					ServiceProcess desServiceProcess = ServiceProcessLocalServiceUtil
							.getServiceProcess(option.getServiceProcessId());

					syncProcesses.add(desServiceProcess);

					break;
				}

			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
				_log.info("NOProcess");
			}

		}

		for (ServiceProcess syncServiceProcess : syncProcesses) {
			// Create DOSSIER for destination PROCESS
			// Get AutoEvent
			// Call nextAction

			Dossier desDossier = DossierLocalServiceUtil.getByRef(syncServiceProcess.getGroupId(),
					object.getString(DossierTerm.REFERENCE_UID));
			long userId = systemUser.getUserId();
			_log.info("userId: " + userId);

			if (Validator.isNull(desDossier)) {
				// Create DOSSIER

				_log.info("CREATE DOSSIER PULL");
				long desGroupId = syncServiceProcess.getGroupId();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

				Date submitDate = new Date();

				String strSubmitDate = object.getString(DossierTerm.SUBMIT_DATE);
				_log.info("strSubmitDate: " + strSubmitDate);
				try {
					submitDate = sdf.parse(object.getString(DossierTerm.SUBMIT_DATE));
					_log.info("submitDate: " + submitDate);

				} catch (Exception e) {
					_log.debug(e);
					//_log.error(e);
					_log.info("SUBMITDATE_NOT_VALID");
				}

				desDossier = DossierLocalServiceUtil.updateDossier(desGroupId, 0l, referenceUid,
						object.getInt(DossierTerm.COUNTER), object.getString(DossierTerm.SERVICE_CODE),
						object.getString(DossierTerm.SERVICE_NAME), govAgencyCode,
						object.getString(DossierTerm.GOV_AGENCY_NAME), object.getString(DossierTerm.APPLICANT_NAME),
						object.getString(DossierTerm.APPLICANT_ID_TYPE), object.getString(DossierTerm.APPLICANT_ID_NO),
						APIDateTimeUtils.convertStringToDate(object.getString(DossierTerm.APPLICANT_ID_DATE),
								APIDateTimeUtils._NORMAL_PARTTERN),
						object.getString(DossierTerm.ADDRESS), object.getString(DossierTerm.CITY_CODE),
						object.getString(DossierTerm.CITY_NAME), object.getString(DossierTerm.DISTRICT_CODE),
						object.getString(DossierTerm.DISTRICT_NAME), object.getString(DossierTerm.WARD_CODE),
						object.getString(DossierTerm.WARD_NAME), object.getString(DossierTerm.CONTACT_NAME),
						object.getString(DossierTerm.CONTACT_TEL_NO), object.getString(DossierTerm.CONTACT_EMAIL),
						object.getString(DossierTerm.DOSSIER_TEMPLATE_NO), object.getString(DossierTerm.DOSSIER_NOTE),
						object.getString(DossierTerm.SUBMISSION_NOTE), object.getString(DossierTerm.APPLICANT_NOTE),
						object.getString(DossierTerm.BRIEF_NOTE), object.getString(DossierTerm.DOSSIER_NO), false,
						APIDateTimeUtils.convertStringToDate(object.getString(DossierTerm.CORRECTING_DATE),
								APIDateTimeUtils._NORMAL_PARTTERN),
						object.getString(DossierTerm.DOSSIER_STATUS), object.getString(DossierTerm.DOSSIER_STATUS_TEXT),
						object.getString(DossierTerm.DOSSIER_SUB_STATUS),
						object.getString(DossierTerm.DOSSIER_SUB_STATUS_TEXT), 0l, 0l,
						object.getInt(DossierTerm.VIA_POSTAL), object.getString(DossierTerm.POSTAL_ADDRESS),
						object.getString(DossierTerm.POSTAL_CITY_CODE), object.getString(DossierTerm.POSTAL_CITY_NAME),
						object.getString(DossierTerm.POSTAL_TEL_NO), object.getString(DossierTerm.SECRET),
						object.getBoolean(DossierTerm.NOTIFICATION), object.getBoolean(DossierTerm.ONLINE),
						object.getString(DossierTerm.SERVER_NO), submitDate, serviceContext);

				// TODO add sync DOSSIERFILE and PAYMENTFILE

				List<JSONObject> lsFileSync = new ArrayList<>();

				// get the list of file of source dossier need to sync
				getDossierFiles(sourceGroupId, dossierId, lsFileSync);

				_log.info("START pull dossier File1: ");
				pullDossierFiles(userId, desDossier.getGroupId(), desDossier.getDossierId(), lsFileSync, sourceGroupId,
						dossierId, referenceUid, serviceContext);

				// get the list of payment file need to sync
				List<JSONObject> lsPaymentsFileSync = new ArrayList<>();

				getPaymentFiles(sourceGroupId, dossierId, lsPaymentsFileSync);

				// Do Pull paymentFile to client

				pullPaymentFile(sourceGroupId, dossierId, desDossier.getGroupId(), desDossier.getDossierId(),
						lsPaymentsFileSync, serviceContext);

				long desDossierId = desDossier.getPrimaryKey();

				// doAction in this case is an Applicant object
				String applicantNote = object.getString(DossierTerm.APPLICANT_NOTE);
				String applicantName = object.getString(DossierTerm.APPLICANT_NAME);

				// Process doAction (with autoEvent = SUBMIT)
				try {
					// DossierLocalServiceUtil.syncDossier(desDossier);

					ProcessAction processAction = ProcessActionLocalServiceUtil
							.fetchBySPI_PRESC_AEV(syncServiceProcess.getServiceProcessId(), StringPool.BLANK, "SUBMIT");
					
					
					//_log.info("GETPROCESSACTION************" + processAction.getActionName());

					long assignedUserId = processAction.getAssignUserId();

					actions.doAction(syncServiceProcess.getGroupId(), desDossierId, desDossier.getReferenceUid(),
							processAction.getActionCode(), processAction.getProcessActionId(), applicantName,
							applicantNote, assignedUserId, systemUser.getUserId(), StringPool.BLANK, serviceContext);

				} catch (Exception e) {
					_log.debug(e);
					//_log.error(e);
					_log.info("SyncDossierUnsuccessfuly" + desDossier.getReferenceUid());
				}

			} else {

				_log.error("UPDATE_____IN_CASE_HAS_DES_DOSSIER___________");
				String cancellingDate = object.getString(DossierTerm.CANCELLING_DATE);
				String correctingDate = object.getString(DossierTerm.CORRECTING_DATE);
				String endorsementDate = object.getString(DossierTerm.ENDORSEMENT_DATE);
				_log.error("CANCELLING DATE: "+cancellingDate);

				if (Validator.isNotNull(cancellingDate)) {
					// Update cancellingDate

					_log.error("UPDATE____CANCELLING_DATE");
					desDossier.setCancellingDate(APIDateTimeUtils.convertStringToDate(
							cancellingDate, APIDateTimeUtils._NORMAL_PARTTERN));

				}

				if (Validator.isNotNull(correctingDate)) {
					_log.error("UPDATE____CORRECTTING_DATE");
					// Update correctingDate
					desDossier.setCorrecttingDate(APIDateTimeUtils.convertStringToDate(
							correctingDate, APIDateTimeUtils._NORMAL_PARTTERN));
				}
				
				
				if (Validator.isNotNull(endorsementDate)) {
					_log.error("UPDATE____ENDOSEMENT_DATE");
					// Update correctingDate
					desDossier.setEndorsementDate(APIDateTimeUtils.convertStringToDate(
							endorsementDate, APIDateTimeUtils._NORMAL_PARTTERN));
				}
	
				// Update dossier

				DossierLocalServiceUtil.updateDossier(desDossier);

				// the resubmit case
				if (object.getBoolean(DossierTerm.SUBMITTING, false)) {
					// Check autoEvent
					ProcessAction processAction = null;
					DossierAction dossierAction = null;

					try {
						dossierAction = DossierActionLocalServiceUtil
								.getDossierAction(desDossier.getDossierActionId());

						processAction = ProcessActionLocalServiceUtil.fetchBySPI_PRESC_AEV(
								syncServiceProcess.getServiceProcessId(), dossierAction.getStepCode(), "SUBMIT");

						// _log.info(JSONFactoryUtil.looseSerialize(processAction));

					} catch (Exception e) {
						// TODO: handle exception
						_log.debug(e);
						//_log.error(e);
					}

					// TODO add sync DOSSIERFILE and PAYMENTFILE

					List<JSONObject> lsFileSync = new ArrayList<>();

					// get the list of file of source dossier need to sync
					getDossierFiles(sourceGroupId, dossierId, lsFileSync);

					_log.info("START pull dossier File2: ");
					pullDossierFiles(userId, desDossier.getGroupId(), desDossier.getDossierId(), lsFileSync,
							sourceGroupId, dossierId, referenceUid, serviceContext);

					// get the list of payment file need to sync
					List<JSONObject> lsPaymentsFileSync = new ArrayList<>();

					getPaymentFiles(sourceGroupId, dossierId, lsPaymentsFileSync);

					// Do Pull paymentFile to client

					pullPaymentFile(sourceGroupId, dossierId, desDossier.getGroupId(), desDossier.getDossierId(),
							lsPaymentsFileSync, serviceContext);
					
					serviceContext.setCompanyId(desDossier.getCompanyId());
					serviceContext.setScopeGroupId(desDossier.getGroupId());
					
					synDossierRequest(dossierId, desDossier.getDossierId(), serviceContext);

					if (Validator.isNotNull(processAction) && Validator.isNull(cancellingDate)) {
						// doAction
						// doAction in this case is an Applicant object
						String applicantNote = object.getString(DossierTerm.APPLICANT_NOTE);
						String applicantName = object.getString(DossierTerm.APPLICANT_NAME);
//						String actionNote = StringPool.BLANK;
						if (dossierAction != null) {
//							actionNote = dossierAction.getActionNote();
						}

						// String subUsers = StringPool.BLANK;
						if(processAction != null) {
							actions.doAction(syncServiceProcess.getGroupId(), desDossier.getDossierId(),
									desDossier.getReferenceUid(), processAction.getActionCode(),
									processAction.getProcessActionId(), applicantName, applicantNote,
	
									processAction.getAssignUserId(), systemUser.getUserId(), StringPool.BLANK,
									serviceContext);
						}
					} else {
						desDossier.setSubmitting(true);

					}

				}

			}

		}

		// ResetDossier
		resetDossier(sourceGroupId, referenceUid, true, serviceContext);

	}

	private void synDossierRequest(long srcDossierId, long desDossierId, ServiceContext context) {

		try {
			
			_log.info("****RUN SYN DOSSIER REQUEST****" +  srcDossierId );
			
			List<DossierRequestUD> dossierRequests = DossierRequestUDLocalServiceUtil.getDossierRequest(srcDossierId, 1);
			_log.info("****RUN SYN DOSSIER REQUEST**** 1" +dossierRequests.size() + " " + srcDossierId );

			for (DossierRequestUD dr : dossierRequests) {

				dr.setIsNew(0);

				DossierRequestUDLocalServiceUtil.updateDossierRequestUD(dr);
				
				context.setUserId(dr.getUserId());
				
				DossierRequestUDLocalServiceUtil.updateDossierRequest(0, desDossierId, dr.getReferenceUid(),
						dr.getRequestType(), dr.getComment(), 0, dr.getStatusReg(), context);

			}

		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
		}
	}

	private void getPaymentFiles(long srcGroupId, long srcDossierId, List<JSONObject> lsPaymentsFileSync) {

		try {

			InvokeREST rest = new InvokeREST();

			HashMap<String, String> properties = new HashMap<String, String>();
			properties.put("Content-Type", "application/x-www-form-urlencoded");

			String path = "dossiers/" + srcDossierId + "/payments";

			ServiceContext serviceContext = new ServiceContext();

			JSONObject resDossierFile = rest.callAPI(srcGroupId, HttpMethods.GET, "application/json",
					RESTFulConfiguration.SERVER_PATH_BASE, path, RESTFulConfiguration.SERVER_USER,
					RESTFulConfiguration.SERVER_PASS, properties, serviceContext);

			if (GetterUtil.getInteger(resDossierFile.get(RESTFulConfiguration.STATUS)) != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException(
						"Failed : HTTP error code : " + resDossierFile.get(RESTFulConfiguration.STATUS));
			} else {

				JSONObject jsData = JSONFactoryUtil
						.createJSONObject(resDossierFile.getString(RESTFulConfiguration.MESSAGE));

				JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString("data"));

				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);

					if (GetterUtil.getInteger(object.get("isNew")) == 1) {
						lsPaymentsFileSync.add(object);
					}

				}

			}

		} catch (Exception e) {
//			e.printStackTrace();
			_log.error(e);
		}
	}

	private void pullPaymentFile(long srcGroupId, long srcDossierId, long groupId, long dossierId,
			List<JSONObject> syncPaymentFiles, ServiceContext context) throws PortalException {
		for (JSONObject object : syncPaymentFiles) {
			// Add paymentFile to CLIENT

//			PaymentFileActions actions = new PaymentFileActionsImpl();

			// check paymentFile is exist
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossierId,
					object.getString("referenceUid"));

//			if (Validator.isNull(paymentFile)) {
//				paymentFile = actions.createPaymentFile(context.getUserId(), groupId, dossierId,
//						object.getString("referenceUid"), object.getString("govAgencyCode "),
//						object.getString("govAgencyName"), object.getString("applicantName"),
//						object.getString("applicantIdNo"), object.getString("paymentFee"),
//						object.getLong("paymentAmount"), object.getString("paymentNote"),
//						object.getString("epaymentProfile"), object.getString("bankInfo"), context);
//			}
			//
			// paymentFile.setPaymentStatus(object.getInt("paymentStatus"));
			paymentFile.setPaymentMethod(object.getString("paymentMethod"));

			PaymentFileLocalServiceUtil.updatePaymentFile(paymentFile);

			// Add fileConfirmId
			if (object.getLong("confirmFileEntryId") != 0) {
				// Download confirmFile form SERVER

				try {

					String fileRef = object.getString("referenceUid");

					// Get file from SERVER
					String path = "dossiers/" + srcDossierId + "/payments/" + fileRef + "/confirmfile";

					URL url = new URL(RESTFulConfiguration.SERVER_PATH_BASE + path);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					String authString = RESTFulConfiguration.SERVER_USER + ":" + RESTFulConfiguration.SERVER_PASS;

					String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

					conn.setRequestMethod(HttpMethods.GET);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("groupId", String.valueOf(srcGroupId));

					int responseCode = conn.getResponseCode();

					if (responseCode != 200) {
						if (responseCode != 204) {
							throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
						}

					} else {

						InputStream is = conn.getInputStream();

						String raw = conn.getHeaderField("Content-Disposition");
						// raw = "attachment; filename=abc.jpg"
						File tempFile = null;

						if (raw != null && raw.indexOf("=") != -1) {
							String fileName = raw.split("=")[1];

							fileName = StringUtil.replace(fileName, "\"", StringPool.BLANK);

							tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),
									StringPool.PERIOD + fileName);

						} else {
							tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),
									StringPool.PERIOD + "tmp");
						}

						FileOutputStream outStream = null;
						try {
							outStream = new FileOutputStream(tempFile);
	
							int bytesRead = -1;
							byte[] buffer = new byte[BUFFER_SIZE];
							while ((bytesRead = is.read(buffer)) != -1) {
								outStream.write(buffer, 0, bytesRead);
							}	
						}
						catch (Exception e) {
							_log.debug(e);
							//_log.error(e);
						}
						finally {
							if (outStream != null)
								outStream.close();
						}
						is.close();

						String requestURL = RESTFulConfiguration.CLIENT_PATH_BASE + "dossiers/" + dossierId
								+ "/payments/" + fileRef + "/confirm";

						String clientAuthString = new String(Base64.getEncoder().encodeToString(
								(RESTFulConfiguration.CLIENT_USER + StringPool.COLON + RESTFulConfiguration.CLIENT_PASS)
										.getBytes()));

						pullPaymentFile(requestURL, "UTF-8", groupId, dossierId, clientAuthString, tempFile,
								StringPool.BLANK, object.getString("paymentMethod"), object.getString("confirmPayload"),
								context);

					}

					conn.disconnect();

				} catch (MalformedURLException e) {
					_log.error(e);
//					e.printStackTrace();
				} catch (IOException e) {
					_log.error(e);
//					e.printStackTrace();

				}

				// Add conformFile to CLIENT
			} else {
				try {

					String fileRef = object.getString("referenceUid");

					// Get file from SERVER
					String path = "dossiers/" + srcDossierId + "/payments/" + fileRef + "/confirm/noattachment";

					URL url = new URL(RESTFulConfiguration.SERVER_PATH_BASE + path);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					String authString = RESTFulConfiguration.SERVER_USER + ":" + RESTFulConfiguration.SERVER_PASS;

					String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

					conn.setRequestMethod(HttpMethods.PUT);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("groupId", String.valueOf(srcGroupId));

					int responseCode = conn.getResponseCode();

					if (responseCode != 200) {
						if (responseCode != 204) {
							throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
						}

					} else {

						_log.info("PULL PAYMENT FILE" + dossierId + "fileReference" + fileRef);

						String requestURL = "dossiers/" + dossierId + "/payments/" + fileRef + "/confirm/noattachment";

						/*
						 * String clientAuthString = new
						 * String(Base64.getEncoder().encodeToString(
						 * (RESTFulConfiguration.CLIENT_USER + StringPool.COLON
						 * + RESTFulConfiguration.CLIENT_PASS) .getBytes()));
						 */

						InvokeREST callRest = new InvokeREST();

						HashMap<String, String> properties = new HashMap<String, String>();

						Map<String, Object> params = new HashMap<String, Object>();

						params.put("confirmNote", StringPool.BLANK);
						params.put("paymentMethod", object.getString("paymentMethod"));
						params.put("confirmPayload", object.getString("confirmPayload"));
						params.put("isSync", StringPool.FALSE);

						callRest.callPostAPI(groupId, HttpMethod.PUT, "application/json",
								RESTFulConfiguration.CLIENT_PATH_BASE, requestURL, RESTFulConfiguration.CLIENT_USER,
								RESTFulConfiguration.CLIENT_PASS, properties, params, context);

						/*
						 * pullPaymentFileNoAttach(requestURL, "UTF-8", groupId,
						 * dossierId, clientAuthString, StringPool.BLANK,
						 * object.getString("paymentMethod"),
						 * object.getString("confirmPayload"), context);
						 */

					}

					conn.disconnect();

				} catch (MalformedURLException e) {
					_log.error(e);
//					e.printStackTrace();
				} catch (IOException e) {
					_log.error(e);
//					e.printStackTrace();

				}

			}

		}
	}

	private void resetDossier(long groupId, String refId, boolean isServer, ServiceContext serviceContext) {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

		properties.put("Content-Type", "application/x-www-form-urlencoded");

		String path = "dossiers/" + refId + "/reset";

		JSONObject resReset = rest.callAPI(groupId, HttpMethods.GET, "application/json",
				isServer ? RESTFulConfiguration.SERVER_PATH_BASE : RESTFulConfiguration.CLIENT_PATH_BASE, path,
				isServer ? RESTFulConfiguration.SERVER_USER : RESTFulConfiguration.CLIENT_USER,
				isServer ? RESTFulConfiguration.SERVER_PASS : RESTFulConfiguration.CLIENT_PASS, properties,
				serviceContext);

		if (GetterUtil.getInteger(resReset.get(RESTFulConfiguration.STATUS)) != HttpURLConnection.HTTP_OK) {
			_log.info("Can't reset DOSSIER has groupId=" + groupId + " and ref=" + refId
					+ (isServer ? "in SERVER" : " in CLIENT"));
		}

	}

	private void getDossierFiles(long groupId, long dossierId, List<JSONObject> lsFileSync) {

		try {

			InvokeREST rest = new InvokeREST();

			HashMap<String, String> properties = new HashMap<String, String>();
			properties.put("Content-Type", "application/x-www-form-urlencoded");

			String path = "dossiers/" + dossierId + "/all/files";

			ServiceContext serviceContext = new ServiceContext();

			JSONObject resDossierFile = rest.callAPI(groupId, HttpMethods.GET, "application/json",
					RESTFulConfiguration.SERVER_PATH_BASE, path, RESTFulConfiguration.SERVER_USER,
					RESTFulConfiguration.SERVER_PASS, properties, serviceContext);

			if (GetterUtil.getInteger(resDossierFile.get(RESTFulConfiguration.STATUS)) != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException(
						"Failed : HTTP error code : " + resDossierFile.get(RESTFulConfiguration.STATUS));
			} else {

				JSONObject jsData = JSONFactoryUtil
						.createJSONObject(resDossierFile.getString(RESTFulConfiguration.MESSAGE));

				JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString("data"));

				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);

					if (GetterUtil.getBoolean(object.get("isNew"))) {

						lsFileSync.add(object);

						long dossierFileId = object.getLong("dossierFileId");

						if (dossierFileId != 0) {
							DossierFile file = DossierFileLocalServiceUtil
									.getDossierFile(object.getLong("dossierFileId"));
							file.setIsNew(false);
							DossierFileLocalServiceUtil.updateDossierFile(file);
						}

					}

				}

			}

		} catch (Exception e) {
//			e.printStackTrace();
			_log.error(e);
		}
	}

	private void pullDossierFiles(long userId, long desGroupId, long dossierId, List<JSONObject> lsFileSync,
			long srcGroupId, long srcDossierId, String dossierRef, ServiceContext serviceContext)
			throws PortalException {

		for (JSONObject ref : lsFileSync) {
			String fileRef = ref.getString("referenceUid");
			boolean isRemoved = ref.getBoolean("removed");

			_log.info("REMOVED_" + ref.getString("fileTemplateNo") + isRemoved);

			if (isRemoved) {

				// remove file in CLIENT
				_log.info("referenceUid" + fileRef);
				_log.info("dossierId" + dossierId);
				_log.info("srcDossierId" + srcDossierId);
				DossierFile file = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossierId, fileRef);
				if (file != null) {
					file.setRemoved(true);
					DossierFileLocalServiceUtil.updateDossierFile(file);
				}

			} else {

				try {
					// String fileRef = ref.getString("referenceUid");

					_log.info("START update dossier File not Removed: " + isRemoved);
					DossierFile srcDossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(srcDossierId,
							fileRef);

					srcDossierFile.setIsNew(false);

					DossierFileLocalServiceUtil.updateDossierFile(srcDossierFile);

					// Get file from SERVER
					String path = "dossiers/" + srcDossierId + "/files/" + fileRef;

					URL url = new URL(RESTFulConfiguration.SERVER_PATH_BASE + path);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					String authString = RESTFulConfiguration.SERVER_USER + ":" + RESTFulConfiguration.SERVER_PASS;

					String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

					conn.setRequestMethod(HttpMethods.GET);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("groupId", String.valueOf(srcGroupId));

					int responseCode = conn.getResponseCode();
					_log.info("responseCode: " + responseCode);

					if (responseCode != 200) {

						if (responseCode != 204) {
							throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
						} else {
							// Sync FormData

							_log.info("Sync FormData START ERROR:****** ");
							String dossierTemplateNo = ref.getString("dossierTemplateNo");
							String formData = ref.getString("formData");
							_log.info("formData: " + formData);

							DossierPart part = DossierPartLocalServiceUtil.getByFileTemplateNo(desGroupId,
									ref.getString("fileTemplateNo"));

							pullFormData(desGroupId, fileRef, dossierTemplateNo, dossierId, formData, part,
									serviceContext);
						}

					} else {

						_log.info("Sync FormData START NOTTTT ERROR:****** ");
						_log.info("dossierId: " + dossierId);
						_log.info("ReferenceUid fileRef: " + fileRef);
						DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossierId,
								fileRef);

						if (Validator.isNull(dossierFile)) {

							_log.info("dossierFile NULL:****** ");
							InputStream is = conn.getInputStream();

							File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),
									StringPool.PERIOD + ref.getString("fileType"));

							try (FileOutputStream outStream = new FileOutputStream(tempFile)) {

								int bytesRead = -1;
								byte[] buffer = new byte[BUFFER_SIZE];
								while ((bytesRead = is.read(buffer)) != -1) {
									outStream.write(buffer, 0, bytesRead);
								}
	
								outStream.close();
								is.close();
	
								String requestURL = RESTFulConfiguration.CLIENT_PATH_BASE + "dossiers/" + dossierId
										+ "/files";
	
								String clientAuthString = new String(
										Base64.getEncoder().encodeToString((RESTFulConfiguration.CLIENT_USER
												+ StringPool.COLON + RESTFulConfiguration.CLIENT_PASS).getBytes()));
	
								pullDossierFile(requestURL, "UTF-8", desGroupId, dossierId, clientAuthString, tempFile,
										ref.getString("dossierTemplateNo"), ref.getString("dossierPartNo"),
										ref.getString("fileTemplateNo"), ref.getString("displayName"),
										ref.getString("formData"), dossierRef, fileRef, serviceContext);
							}
						} else {
							// Sync FormData

							_log.info("Sync FormData START NOT ERROR:****** ");
							String dossierTemplateNo = ref.getString("dossierTemplateNo");
							String formData = ref.getString("formData");
							_log.info("formData: " + formData);

							DossierPart part = DossierPartLocalServiceUtil.getByFileTemplateNo(desGroupId,
									ref.getString("fileTemplateNo"));

							pullFormData(desGroupId, fileRef, dossierTemplateNo, dossierId, formData, part,
									serviceContext);

							// TODO: Write file upload sync
							InputStream is = conn.getInputStream();

							File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),
									StringPool.PERIOD + ref.getString("fileType"));

							try (FileOutputStream outStream = new FileOutputStream(tempFile)) {

								int bytesRead = -1;
								byte[] buffer = new byte[BUFFER_SIZE];
								while ((bytesRead = is.read(buffer)) != -1) {
									outStream.write(buffer, 0, bytesRead);
								}
	
								outStream.close();
								is.close();
								// Update file entry
								_log.info("START UPDATE FILE ENTRY");
								DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
										.fetchDLFileEntry(dossierFile.getFileEntryId());
	
								DLAppLocalServiceUtil.updateFileEntry(userId, dlFileEntry.getFileEntryId(),
										dlFileEntry.getTitle(), dlFileEntry.getMimeType(), dlFileEntry.getTitle(),
										dlFileEntry.getDescription(), StringPool.BLANK, true, tempFile, serviceContext);
								_log.info("END UPDATE FILE ENTRY");
							}
						}

					}

					conn.disconnect();

				} catch (MalformedURLException e) {
					_log.error(e);
//					e.printStackTrace();
				} catch (IOException e) {
					_log.error(e);
//					e.printStackTrace();

				}
			}
		}

	}

	private void pullPaymentFile(String requestURL, String charset, long desGroupId, long dossierId,
			String authStringEnc, File file, String confirmNote, String paymentMethod, String confirmPayload,
			ServiceContext serviceContext) {

		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset, desGroupId, authStringEnc,
					HttpMethod.PUT);
			// TODO; check logic here, if ref fileId in SERVER equal CLIENT

			multipart.addFilePart("file", file);
			multipart.addFormField("confirmNote", confirmNote);
			multipart.addFormField("paymentMethod", paymentMethod);
			multipart.addFormField("confirmPayload", confirmPayload);

//			JSONObject object = JSONFactoryUtil.createJSONObject();

//			List<String> response = multipart.finish();

			// resetDossier(desGroupId, dossierRef, false, serviceContext);

		} catch (Exception e) {
			_log.error(e);
		}

	}

//	private void pullPaymentFileNoAttach(String requestURL, String charset, long desGroupId, long dossierId,
//			String authStringEnc, String confirmNote, String paymentMethod, String confirmPayload,
//			ServiceContext serviceContext) {
//
//		try {
//
//			MultipartUtility multipart = new MultipartUtility(requestURL, charset, desGroupId, authStringEnc,
//					HttpMethod.PUT);
//			// TODO; check logic here, if ref fileId in SERVER equal CLIENT
//
//			multipart.addFormField("confirmNote", confirmNote);
//			multipart.addFormField("paymentMethod", paymentMethod);
//			multipart.addFormField("confirmPayload", confirmPayload);
//
////			JSONObject object = JSONFactoryUtil.createJSONObject();
//
////			List<String> response = multipart.finish();
//
//			// resetDossier(desGroupId, dossierRef, false, serviceContext);
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
//
//	}

	private void pullDossierFile(String requestURL, String charset, long desGroupId, long dossierId,
			String authStringEnc, File file, String dossierTemplateNo, String dossierPartNo, String fileTemplateNo,
			String displayName, String formData, String dossierRef, String fileRef, ServiceContext serviceContext) {

		_log.info("START update FORMDATA*****");
		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset, desGroupId, authStringEnc);
			// TODO; check logic here, if ref fileId in SERVER equal CLIENT

			multipart.addFilePart("file", file);
			multipart.addFormField("referenceUid", fileRef);
			multipart.addFormField("dossierTemplateNo", dossierTemplateNo);
			multipart.addFormField("dossierPartNo", dossierPartNo);
			multipart.addFormField("fileTemplateNo", fileTemplateNo);
			multipart.addFormField("displayName", displayName);
			multipart.addFormField("fileType", StringPool.BLANK);
			multipart.addFormField("isSync", StringPool.BLANK);
			multipart.addFormField("formData", formData);

			_log.info("START update FORMDATA*****referenceUid"+fileRef);
			_log.info("START update FORMDATA*****dossierTemplateNo"+dossierTemplateNo);
			_log.info("START update FORMDATA*****dossierPartNo"+dossierPartNo);
			_log.info("START update FORMDATA*****fileTemplateNo"+fileTemplateNo);
			_log.info("START update FORMDATA*****displayName"+displayName);
			_log.info("START update FORMDATA*****formData"+formData);

			multipart.finish();
			resetDossier(desGroupId, dossierRef, false, serviceContext);

		} catch (Exception e) {
			_log.error(e);
		}

	}

	private void pullPaymentFiles(long srcGroupId, long desDossierId) throws PortalException {
		// get all paymentFile need to sync

		//
	}

	private void pullFormData(long desGroupId, String fileRef, String dossierTemplateNo, long dossierId,
			String formData, DossierPart part, ServiceContext serviceContext) {
		try {
			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossierId, fileRef);

			if (Validator.isNull(dossierFile)) {
				// create dossierFile
				dossierFile = DossierFileLocalServiceUtil.addDossierFile(desGroupId, dossierId,
						PortalUUIDUtil.generate(), dossierTemplateNo, part.getPartNo(), part.getFileTemplateNo(),
						part.getPartName(), StringPool.BLANK, 0, null, StringPool.BLANK, StringPool.FALSE,
						serviceContext);
			}

			dossierFile.setFormData(formData);

			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

		} catch (Exception e) {
			_log.error(e);
		}
	}

	private void updateFormData(long desGroupId, List<String> response, long dossierId, String formData,
			ServiceContext serviceContext) throws PortalException {

		StringBuilder sb = new StringBuilder();

		for (String line : response) {
			sb.append(line);
		}

		JSONObject jsDossierFile = JSONFactoryUtil.createJSONObject(sb.toString());

		String fileRef = jsDossierFile.getString("referenceUid");

		// Update formData

		DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossierId, fileRef);

		if (Validator.isNotNull(dossierFile)) {
			dossierFile.setFormData(formData);

			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
		}
		// DossierFileLocalServiceUtil.updateFormData(desGroupId, dossierId,
		// fileRef, formData, serviceContext);

	}

	public void pullRequestDossiers(long dossierId, long desDossierId) {
		// TODO need update use API not use LocalService

		try {
			List<DossierRequestUD> lsDossierRequest = DossierRequestUDLocalServiceUtil.getDossierRequest(dossierId, 1);

			ServiceContext context = new ServiceContext();

			context.setUserId(0);
			context.setScopeGroupId(55301);

			for (DossierRequestUD dr : lsDossierRequest) {
				// update to client

				DossierRequestUDLocalServiceUtil.updateDossierRequest(0, desDossierId, dr.getReferenceUid(),
						dr.getRequestType(), dr.getComment(), 0, dr.getStatusReg(), context);
			}

		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),
				45, TimeUnit.SECOND));
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(DossierPullScheduler.class);
}
