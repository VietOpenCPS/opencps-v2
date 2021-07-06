package org.opencps.dossiermgt.scheduler;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.TTTTIntegrationAction;
import org.opencps.dossiermgt.action.impl.TTTTIntegrationImpl;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.lgsp.model.MResult;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.PaymentFileInputModel;
import org.opencps.dossiermgt.rest.utils.LGSPRestClient;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.PublishQueueComparator;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = PublishEventScheduler.class)
public class PublishEventScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	
	static class CounterPublishEvent {
		private volatile static int count = 0;
		public static int getCount(){
			return count;
		}
		public static synchronized void decreaseCount(){
			count--;
		}

		public static synchronized void setCount(int countNew){
			count = countNew;
		}
	}

	public static int getCount(){
		return PublishEventScheduler.CounterPublishEvent.getCount();
	}

	public static int resetCount(){
		PublishEventScheduler.CounterPublishEvent.count = 0;
		return getCount();
	}

	public static int PublishEventSchedulerCount(){
		return CounterPublishEvent.getCount();
	}

	public static int ResetPublishEventSchedulerCount(){
		CounterPublishEvent.setCount(0);
		return PublishEventSchedulerCount();
	}
	
	public PublishEventScheduler () {
		_log.debug("Constructor PublishEventScheduler");

		if(Validator.isNull(threadPoolExecutor)) {
			_log.debug("Creating threadPoolExecutor first time...");
			threadPoolExecutor = new ThreadPoolExecutor(
					corePoolSize, // Số thread mặc định được cấp để xử lý request
					maximumPoolSize, //Số thread tối đa được dùng
					keepAliveTime, //thời gian sống 1 thread nếu thread đang ko làm gì
					java.util.concurrent.TimeUnit.SECONDS, //đơn vị thời gian
					new ArrayBlockingQueue<>(queueCapacity), //Queue để lưu số lượng request chờ khi số thread trong
					// corePoolSize được dùng hết, khi số lượng request = queueCapacity thì sẽ tạo 1 thread mới
					new ThreadPoolExecutor.CallerRunsPolicy()); //Tự động xử lý exception khi số lượng request vượt quá queueCapacity
		}
	}
	
	private static final String startLine1 = "==========";
	private static final String startLine2 = "==================";

	private static volatile ThreadPoolExecutor threadPoolExecutor;

	private int corePoolSize    = 5;
	private int maximumPoolSize = 20;
	private int queueCapacity   = 10;
	private int keepAliveTime   = 10;

	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("====PublishEventScheduler isRunning: " + isRunning + ", counting: " + CounterPublishEvent.getCount());
		if (isRunning) {
			_log.info("Job publish event is running");
			return;
		}

		if(CounterPublishEvent.getCount() > 0) {
			_log.info("Job publish event before still has count :" + CounterPublishEvent.getCount());
			return;
		}

		isRunning = true;

		try {

			_log.info("OpenCPS PUBLISH EVENT IS  : " + APIDateTimeUtils.convertDateToString(new Date()));

			List<PublishQueue> lstPqs = PublishQueueLocalServiceUtil.getByStatusesAndNotServerNo(new int[] {
							PublishQueueTerm.STATE_WAITING_SYNC},
					ServerConfigTerm.DVCQG_INTEGRATION, 0, 60);

			_log.info("LstPqs queue in publish event : " + lstPqs.size());

			if(Validator.isNull(lstPqs) || lstPqs.size() == 0) {
				_log.debug("No queue found with status " + PublishQueueTerm.STATE_WAITING_SYNC);
				isRunning = false;
				return;
			}

			//Remove duplicated dossierId
			Set<Long> listDossierId = new HashSet<>();
			for(PublishQueue queue : lstPqs) {
				listDossierId.add(queue.getDossierId());
			}
			int sizeDossierId = listDossierId.size();
			CounterPublishEvent.setCount(sizeDossierId);
			//Core function
			for(Long dossierId : listDossierId) {
				threadPoolExecutor.execute(() -> mainProcess(dossierId, sizeDossierId));
				_log.debug("Number thread active publishEvent: " + threadPoolExecutor.getActiveCount());
			}

		}
		catch (Exception e) {
			_log.debug(e);
		}
		isRunning = false;
	}
	
	private void mainProcess(long dossierId, int sizeDossierId) {
		
		_log.debug(startLine1 + "Start thread publish event for dossierId " + dossierId);
		if(CounterPublishEvent.getCount() == sizeDossierId) {
			_log.debug("Publish Event time start: " + APIDateTimeUtils.convertDateToString(new Date()));
		}
		
		List<PublishQueue> listQueueByDossierId = PublishQueueLocalServiceUtil.getByDossierIdAndNotServerNo(
				dossierId, ServerConfigTerm.DVCQG_INTEGRATION, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new PublishQueueComparator(true, Field.CREATE_DATE, Date.class));

		if(Validator.isNull(listQueueByDossierId) || listQueueByDossierId.size() == 0) {
			_log.info(startLine2 + "Not found publish queue with dossierId " + dossierId + ", still running...");
			return;
		}
		
		int queueStatus;
		long queueIdError = 0;
		long queueIdCurrent;
		String serverNo;
		boolean easySend;

		for (PublishQueue oneQueue : listQueueByDossierId) {
			queueIdCurrent = oneQueue.getPublishQueueId();
			queueStatus    = oneQueue.getStatus();
			serverNo       = oneQueue.getServerNo();
			easySend       = serverNo.equals("SERVER_PUBLISH");

			try {
				if(queueStatus == PublishQueueTerm.STATE_RECEIVED_ACK) {
					continue;
				}

				if(queueStatus == PublishQueueTerm.STATE_ACK_ERROR) {
					if(!easySend) {
						queueIdError = queueIdCurrent;
					}
					continue;
				}

				if (queueIdError != 0) {
					_log.info("easySend: " + easySend);
					oneQueue.setStatus(PublishQueueTerm.STATE_ACK_ERROR);
					PublishQueueLocalServiceUtil.updatePublishQueue(oneQueue);
					continue;
				}

				if(queueStatus == PublishQueueTerm.STATE_ALREADY_SENT) {
					continue;
				}

				if(queueStatus == PublishQueueTerm.STATE_WAITING_SYNC) {
					//start sync
					oneQueue.setStatus(PublishQueueTerm.STATE_ALREADY_SENT);
					oneQueue = PublishQueueLocalServiceUtil.updatePublishQueue(oneQueue);

					boolean result = processPublish(oneQueue);

					if(result) {
						oneQueue.setStatus(PublishQueueTerm.STATE_RECEIVED_ACK);
					} else {
						oneQueue.setStatus(PublishQueueTerm.STATE_ACK_ERROR);
						queueIdError = queueIdCurrent;
					}
					
					oneQueue.setModifiedDate(new Date());
					PublishQueueLocalServiceUtil.updatePublishQueue(oneQueue);
					_log.info("Publish event done for dossier: " + dossierId);
				}
			} catch (Exception e) {
				_log.warn("================== Publish event Error when submit queue: " + queueIdCurrent + ", status: " + queueStatus);
				_log.warn(e);
				_log.warn("================== Publish event Still running...");
			}
		}

		CounterPublishEvent.decreaseCount();
		_log.info("============Publish event counting remain: " + CounterPublishEvent.getCount());
		if(CounterPublishEvent.getCount() == 0) {
			_log.debug("Time end: " + APIDateTimeUtils.convertDateToString(new Date()));
		}
		
	}

	private boolean processPublish(PublishQueue pq) {
		long dossierId = pq.getDossierId();
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (Validator.isNotNull(dossier) && Validator.isNotNull(dossier.getOriginDossierId())
				&& (dossier.getOriginDossierId() != 0 || Validator.isNotNull(dossier.getOriginDossierNo()))) {
			return true;
		}
		_log.debug("pq: "+JSONFactoryUtil.looseSerialize(pq));
		long groupId = pq.getGroupId();
		ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, pq.getServerNo());
		
		if(Validator.isNull(sc)) {
			return false;
		}
		
		if (ServerConfigTerm.PUBLISH_PROTOCOL.equals(sc.getProtocol())) {
			try {
				if (dossier != null && dossier.getOriginality() > 0) {
					OpenCPSRestClient client = OpenCPSRestClient
							.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
					DossierDetailModel result = client.publishDossier(OpenCPSConverter.convertDossierPublish(
							DossierMgtUtils.convertDossierToJSON(dossier, dossier.getDossierActionId())));

					PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
					if (result.getDossierId() != null && paymentFile != null && (paymentFile.getPaymentStatus() == 2
							|| paymentFile.getPaymentStatus() == 1)) { // ||
																	// paymentFile.getPaymentStatus()
																	// ==
																	// 5

						PaymentFileInputModel pfiModel = new PaymentFileInputModel();
						pfiModel.setApplicantIdNo(dossier.getApplicantIdNo());
						pfiModel.setApplicantName(dossier.getApplicantName());
						pfiModel.setBankInfo(paymentFile.getBankInfo());
						pfiModel.setEpaymentProfile(paymentFile.getEpaymentProfile());
						pfiModel.setGovAgencyCode(dossier.getGovAgencyCode());
						pfiModel.setGovAgencyName(dossier.getGovAgencyName());
						pfiModel.setPaymentAmount(paymentFile.getPaymentAmount());
						pfiModel.setPaymentFee(paymentFile.getPaymentFee());
						pfiModel.setPaymentNote(paymentFile.getPaymentNote());
						pfiModel.setReferenceUid(paymentFile.getReferenceUid());
						if (Validator.isNotNull(dossier.getOriginDossierNo())) {
							if (pfiModel.getReferenceUid().contains(DossierTerm.PREFIX_UUID)) {
								String newRef = pfiModel.getReferenceUid().substring(DossierTerm.PREFIX_UUID.length());
								pfiModel.setReferenceUid(newRef);
							}
						}

						pfiModel.setFeeAmount(paymentFile.getFeeAmount());
						pfiModel.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
						pfiModel.setPaymentStatus(paymentFile.getPaymentStatus());
						pfiModel.setAdvanceAmount(paymentFile.getAdvanceAmount());
						pfiModel.setServiceAmount(paymentFile.getServiceAmount());
						pfiModel.setShipAmount(paymentFile.getShipAmount());
						
						//_log.debug("SONDT PAYMENT PFIMODEL SYNC INFORM ======================== " + JSONFactoryUtil.looseSerialize(pfiModel));
						client.postPaymentFiles(pfiModel.getReferenceUid(), pfiModel);
					}

					// Add by TrungNT 16/11/2020
					// sync dossierFile

					// StringBuilder messageText = new StringBuilder();
					// StringBuilder acknowlegement = new StringBuilder();

					String payload = pq.getPublishData();
					_log.debug("dossier Inform: " + dossier.getOriginDossierNo());
					_log.debug("payload Inform: " + payload);
					JSONObject payloadObj = JSONFactoryUtil.createJSONObject(payload);

					// Sync file HSLT
					_log.debug("payloadObj LT: " + payloadObj);
					if (payloadObj.has(DossierSyncTerm.PAYLOAD_SYNC_FILES)) {
						JSONArray fileArrs = payloadObj.getJSONArray(DossierSyncTerm.PAYLOAD_SYNC_FILES);
						for (int i = 0; i < fileArrs.length(); i++) {
							JSONObject fileObj = fileArrs.getJSONObject(i);
							if (fileObj.has(DossierFileTerm.REFERENCE_UID)) {
								DossierFile df = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(
										dossier.getDossierId(), fileObj.getString(DossierFileTerm.REFERENCE_UID));
								if (df != null) {
									if (df.getFileEntryId() > 0) {
										FileEntry fileEntry;
										try {
											fileEntry = DLAppLocalServiceUtil.getFileEntry(df.getFileEntryId());
											File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(),
													fileEntry.getVersion(), true);
											DossierFileModel dfModel = new DossierFileModel();
											dfModel.setReferenceUid(df.getReferenceUid());
											if (Validator.isNotNull(dossier.getOriginDossierNo())) {
												if (df.getReferenceUid().contains(DossierTerm.PREFIX_UUID)) {
													String newRef = df.getReferenceUid()
															.substring(DossierTerm.PREFIX_UUID.length());
													dfModel.setReferenceUid(newRef);
												}
											}
											dfModel.setModifiedDate(String.valueOf(df.getModifiedDate().getTime()));
											dfModel.setDossierPartNo(df.getDossierPartNo());
											dfModel.setDisplayName(df.getDisplayName());
											dfModel.setDossierTemplateNo(df.getDossierTemplateNo());
											dfModel.setFileTemplateNo(df.getFileTemplateNo());
											dfModel.setFormData(df.getFormData());
											dfModel.setFileType(fileEntry.getMimeType());
											dfModel.setRemoved(df.getRemoved());
											dfModel.setEForm(df.getEForm());
											client.postDossierFileInform(file, dossier,
													dossier.getReferenceUid(), dfModel);
										} catch (PortalException e) {
											_log.error(e);
										}

									}

								}
							}
						}
					}

//					if (client.isWriteLog()) {
//						String messageText = DossierMgtUtils.convertDossierToJSON(dossier, dossier.getDossierActionId())
//								.toJSONString();
//						String acknowlegement = JSONFactoryUtil.looseSerialize(result);
//						pq.setMessageText(messageText);
//						pq.setAcknowlegement(acknowlegement);
//						PublishQueueLocalServiceUtil.updatePublishQueue(pq);
//					}
					if (result.getDossierId() != null) {
						return true;
					} else {
						return false;
					}

				} else {
					return true;
				}
			} catch (JSONException e) {
				_log.error(e);
			}
		} else if (ServerConfigTerm.LGSP_PROTOCOL.equals(sc.getProtocol())) {
			try {
				if (dossier != null && dossier.getOriginality() > 0) {
					LGSPRestClient client = LGSPRestClient
							.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
					Mtoken token = client.getToken();
					if (Validator.isNotNull(token.getAccessToken())) {
						JSONObject dossierObj = DossierMgtUtils.convertDossierToJSON(dossier,
								dossier.getDossierActionId());
						MResult result = client.publishDossier(token.getAccessToken(),
								OpenCPSConverter.convertDossierPublish(
										DossierMgtUtils.convertDossierToJSON(dossier, dossier.getDossierActionId())));
						if (client.isWriteLog()) {
							JSONObject messageObj = JSONFactoryUtil.createJSONObject();
							messageObj.put("token", token.getAccessToken());
							messageObj.put("MSyncDocument", JSONFactoryUtil.looseSerialize(OpenCPSConverter
									.convertDossierToLGSPJSON(OpenCPSConverter.convertDossierPublish(dossierObj))));
							String messageText = messageObj.toJSONString();
							String acknowlegement = JSONFactoryUtil.looseSerialize(result);
							pq.setMessageText(messageText);
							pq.setAcknowlegement(acknowlegement);
							pq.setPublishType(1);
							PublishQueueLocalServiceUtil.updatePublishQueue(pq);
						}
						if (result.getStatus() != 200) {
							return false;
						} else {
							ServiceContext context = new ServiceContext();
							MResult result2 = client.postDocumentTrace(token.getAccessToken(),
									dossierObj.getLong(DossierTerm.DOSSIER_ID));
							JSONObject messageObj = JSONFactoryUtil.createJSONObject();
							messageObj.put("token", token.getAccessToken());
							JSONObject lgspObj = OpenCPSConverter.convertToDocumentTraces(dossierId);
							messageObj.put("MDocumentTraces", lgspObj.toJSONString());
							String messageText = messageObj.toJSONString();
							String acknowlegement = JSONFactoryUtil.looseSerialize(result2);
							PublishQueueLocalServiceUtil.updatePublishQueue(sc.getGroupId(), 0l, 2, 0l,
									sc.getServerNo(), StringPool.BLANK, PublishQueueTerm.STATE_RECEIVED_ACK, 0,
									messageText, acknowlegement, context);

							if (result2.getStatus() != 200) {
								return false;
							} else {
								return true;
							}
						}
					} else {
						return false;
					}
				} else {
					return true;
				}
			} catch (JSONException e) {
				_log.error(e);
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		else if (ServerConfigTerm.TTTT_INTEGRATION.equals(sc.getProtocol())) {
			_log.info("Integrating dossier to TTTT...");
			try {
				TTTTIntegrationAction integrationAction = new TTTTIntegrationImpl();
				return integrationAction.syncDoActionDossierUsingHttpConnection(dossier);
			} catch (Exception e) {
				_log.error(e);
				return false;
			}
		}

		return true;
	}
	
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  
		  _log.info("====PublishEvent Scheduler Active====");
		  String listenerClass = getClass().getName();
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 45, TimeUnit.SECOND);

		  _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
		  _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);
		  
//		  _schedulerEntryImpl.setTrigger(jobTrigger);

		if (_initialized) {
			deactivate();
		}

		_schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
		_initialized = true;
	}

	@Deactivate
	protected void deactivate() {
		if (_initialized) {
			try {
				_schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType());
			} catch (SchedulerException se) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to unschedule trigger", se);
				}
			}

			_schedulerEngineHelper.unregister(this);
		}
		_initialized = false;
		isRunning = false;
	}

	/**
	 * getStorageType: Utility method to get the storage type from the scheduler
	 * entry wrapper.
	 * 
	 * @return StorageType The storage type to use.
	 */
	protected StorageType getStorageType() {
		if (_schedulerEntryImpl instanceof StorageTypeAware) {
			return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
		}

		return StorageType.PERSISTED;
	}

	/**
	 * setModuleServiceLifecycle: So this requires some explanation...
	 * 
	 * OSGi will start a component once all of it's dependencies are satisfied.
	 * However, there are times where you want to hold off until the portal is
	 * completely ready to go.
	 * 
	 * This reference declaration is waiting for the ModuleServiceLifecycle's
	 * PORTAL_INITIALIZED component which will not be available until, surprise
	 * surprise, the portal has finished initializing.
	 * 
	 * With this reference, this component activation waits until portal
	 * initialization has completed.
	 * 
	 * @param moduleServiceLifecycle
	 */
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
		_schedulerEngineHelper = schedulerEngineHelper;
	}

	private SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private volatile boolean _initialized;
	private SchedulerEntryImpl _schedulerEntryImpl = null;

	private Log _log = LogFactoryUtil.getLog(PublishEventScheduler.class);

}
