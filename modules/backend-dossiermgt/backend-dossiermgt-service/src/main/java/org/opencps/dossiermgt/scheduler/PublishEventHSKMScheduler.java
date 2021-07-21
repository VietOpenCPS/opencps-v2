package org.opencps.dossiermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.PublishQueueComparator;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@Component(immediate = true, service = PublishEventHSKMScheduler.class)
public class PublishEventHSKMScheduler extends BaseMessageListener {
	static class Counter {
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
		return PublishEventHSKMScheduler.Counter.getCount();
	}

	public static int resetCount(){
		PublishEventHSKMScheduler.Counter.count = 0;
		return getCount();
	}

	private volatile boolean isRunning = false;
	private static final Boolean ENABLE_JOB = Validator.isNotNull(PropsUtil.get("org.opencps.synchskm.enable"))
			? Boolean.valueOf(PropsUtil.get("org.opencps.synchskm.enable")) : false;
	private static final Integer QUANTITY_JOB_DVCQG = 100;
	private static final String startLine1 = "==========";
	private static final String startLine2 = "==================";
	private static int timeSyncDossierDVCQG = Validator.isNotNull(PropsUtil.get("opencps.sync.dvcqg.time"))
			? Integer.valueOf(PropsUtil.get("opencps.sync.dvcqg.time"))
			: 20;

	private static volatile ThreadPoolExecutor threadPoolExecutor;

	private int corePoolSize    = 15;
	private int maximumPoolSize = 60;
	private int queueCapacity   = 10;
	private int keepAliveTime   = 10;

	public PublishEventHSKMScheduler () {
		_log.info("Constructor PublishEventHSKMScheduler");

		if(Validator.isNull(threadPoolExecutor)) {
			_log.info("Creating threadPoolExecutor first time...");
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

	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("TASK SEND HSKM to DVCQG: " + timeSyncDossierDVCQG);
		_log.info("isRunning: " + isRunning + ", jobEnable: " + ENABLE_JOB + ", counting: " + Counter.getCount());

		if(isRunning) {
			return;
		}

		if(!ENABLE_JOB) {
			return;
		}

		if(Counter.getCount() > 0) {
			return;
		}

		//Start scheduler
		isRunning = true;

		try {
			//Get max 100 queue
			List<PublishQueue> lstPqs = PublishQueueLocalServiceUtil.getByStatusesAndServerNo(new int[] {
					PublishQueueTerm.STATE_WAITING_SYNC },
					ServerConfigTerm.DVCQG_INTEGRATION, 0, QUANTITY_JOB_DVCQG,
					new PublishQueueComparator(true, Field.CREATE_DATE, Date.class));

			_log.info("lstPqs  : " + lstPqs.size());

			if(Validator.isNull(lstPqs) || lstPqs.size() == 0) {
				_log.info("No queue found with status " + PublishQueueTerm.STATE_WAITING_SYNC);
				isRunning = false;
				return;
			}

			//Remove duplicated dossierId
			Set<Long> listDossierId = new HashSet<>();
			for(PublishQueue queue : lstPqs) {
				listDossierId.add(queue.getDossierId());
			}
			int sizeDossierId = listDossierId.size();
			Counter.setCount(sizeDossierId);
			//Core function
			for(Long dossierId : listDossierId) {
				threadPoolExecutor.execute(() -> mainProcess(dossierId, sizeDossierId));
				_log.info("Number thread active: " + threadPoolExecutor.getActiveCount());
			}
		}
		catch (Exception e) {
			_log.error("Error when running scheduler DVCQG " + e.getMessage());
			e.printStackTrace();
		}
		isRunning = false;
	}

	private void mainProcess(long dossierId, int sizeDossierId) {
		_log.info(startLine1 + "Start thread for dossierId " + dossierId);
		if(Counter.getCount() == sizeDossierId) {
			_log.info("Time start: " + APIDateTimeUtils.convertDateToString(new Date()));
		}

		List<PublishQueue> listQueueByDossierId = PublishQueueLocalServiceUtil.getByDossierIdAndServerNo(
				dossierId, ServerConfigTerm.DVCQG_INTEGRATION, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new PublishQueueComparator(true, Field.CREATE_DATE, Date.class));

		if(Validator.isNull(listQueueByDossierId) || listQueueByDossierId.size() == 0) {
			_log.warn(startLine2 + "Not found queue dvcqg with dossierId " + dossierId + ", still running...");
			return;
		}

		int queueStatus;
		long queueIdError = 0;
		long queueIdCurrent;

		for(PublishQueue oneQueue: listQueueByDossierId) {
			queueIdCurrent = oneQueue.getPublishQueueId();
			queueStatus    = oneQueue.getStatus();

			try {
				if(queueStatus == PublishQueueTerm.STATE_RECEIVED_ACK) {
					continue;
				}

				if(queueStatus == PublishQueueTerm.STATE_ACK_ERROR) {
					queueIdError = queueIdCurrent;
					continue;
				}

				if (queueIdError != 0) {
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
					PublishQueueLocalServiceUtil.updatePublishQueue(oneQueue);
					_log.info("Done for dossier: " + dossierId);
				}
			} catch (Exception e) {
				_log.warn(startLine2 + "Error when submit queue: " + queueIdCurrent + ", status: " + queueStatus
						+ ": " + e.getMessage());
				_log.warn(startLine2 + "Still running...");
				oneQueue.setStatus(PublishQueueTerm.STATE_ACK_ERROR);
				PublishQueueLocalServiceUtil.updatePublishQueue(oneQueue);
				queueIdError = queueIdCurrent;
			}
		}

		Counter.decreaseCount();
		_log.info(startLine1 + "Counting remain: " + Counter.getCount());
		if(Counter.getCount() == 0) {
			_log.info("Time end: " + APIDateTimeUtils.convertDateToString(new Date()));
		}
	}
	
	private boolean processPublish(PublishQueue pq) {
		long dossierId = pq.getDossierId();

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (Validator.isNotNull(dossier)
				&& Validator.isNotNull(dossier.getOriginDossierId())
				&& (dossier.getOriginDossierId() != 0
				|| Validator.isNotNull(dossier.getOriginDossierNo()))) {
			return true;
		}

		long groupId = pq.getGroupId();
		ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, pq.getServerNo());

		try {
			DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();

			JSONObject result = actionImpl.syncDossierAndDossierStatus(groupId, dossier, null);

			if(Validator.isNull(result)) {
				return false;
			}

			_log.info("result DVCQG with process in thread for queueId  " + pq.getPublishQueueId() + ": " + result);
			if(result.has("error_code") && "0".equals(result.getString("error_code"))) {
				PublishQueueLocalServiceUtil.updatePublishQueue(
						sc.getGroupId(), pq.getPublishQueueId(), 2, dossier.getDossierId(),
						sc.getServerNo(), StringPool.BLANK, PublishQueueTerm.STATE_RECEIVED_ACK, 0,
						String.valueOf(dossier.getDossierNo()), result.toJSONString(),
						new ServiceContext());
				return true;
			}
		} catch (Exception e) {
			_log.error(e);
		}
		return false;
	}
	
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  String listenerClass = getClass().getName();
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, timeSyncDossierDVCQG, TimeUnit.SECOND);

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
	 * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
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
	   * OSGi will start a component once all of it's dependencies are satisfied.  However, there
	   * are times where you want to hold off until the portal is completely ready to go.
	   * 
	   * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
	   * component which will not be available until, surprise surprise, the portal has finished
	   * initializing.
	   * 
	   * With this reference, this component activation waits until portal initialization has completed.
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

	private Log _log = LogFactoryUtil.getLog(PublishEventHSKMScheduler.class);
	
}
