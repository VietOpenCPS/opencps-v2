package org.opencps.dossiermgt.scheduler;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.service.AccessTokenLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(immediate = true, service = GarbageDossierPublishQueueScheduler.class)
public class GarbageDossierPublishQueueScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	private static final Boolean ENABLE_JOB = Validator
			.isNotNull(PropsUtil.get("org.opencps.schedule.remove.publish.queue.enable.job"))
					? Boolean.valueOf(PropsUtil.get("org.opencps.schedule.remove.publish.queue.enable.job"))
					: true;

	private static String cronExpression = Validator
			.isNotNull(PropsUtil.get("org.opencps.schedule.remove.publish.queue.cron.expression"))
					? String.valueOf(PropsUtil.get("org.opencps.schedule.remove.publish.queue.cron.expression"))
					: "0 0 2 1/1 * ? *";
					
	public static String pathTemp = Validator.isNotNull(System.getProperty("java.io.tmpdir"))
			? String.valueOf(System.getProperty("java.io.tmpdir"))
			: null;

	@Override
	protected void doReceive(Message message) throws Exception {
		if (isRunning) {
			return;
		}

		if (!ENABLE_JOB) {
			return;
		}

		// Start scheduler
		isRunning = true;
		try {

			Date now = new Date();

			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.DATE, -30);
			Date thirtyDayAgo = cal.getTime();
			thirtyDayAgo.setHours(0);
			thirtyDayAgo.setMinutes(0);
			thirtyDayAgo.setSeconds(0);

//			Action 1 : 1. Dossier (dossierStatus is null, từ 1 tháng về trước)

			_log.info("OpenCPS REMOVE DOSSIER SCHEDULER IS : " + APIDateTimeUtils.convertDateToString(new Date()));

			List<Dossier> lstDossier = DossierLocalServiceUtil
					.findDossierBeforeDateAndDossierStatusisNull(thirtyDayAgo);

			if (Validator.isNull(lstDossier) || lstDossier.size() == 0) {
				_log.info("list Dossier before " + APIDateTimeUtils.convertDateToString(thirtyDayAgo)
						+ " and Status is Null return list: null");
			} else {
				_log.info("OpenCPS REMOVE DOSSIER COUNT: " + lstDossier.size());

				for (Dossier dossier : lstDossier) {
					try {
						DossierLocalServiceUtil.removeDossier(dossier.getGroupId(), dossier.getDossierId(),
								dossier.getReferenceUid());
						
					} catch (Exception pe) {
						_log.error(pe);
					}
				}
				try {
					DossierLocalServiceUtil.removeDossierByF_OG_DS(9, StringPool.BLANK);
				} catch (Exception e) {
					_log.error(e);
				}
			}

//			Action 4. 4. opencps_publish_queue (Xóa những bản ghi có serverNo = 'DVCQG' từ cách 30 ngày về trước và status =3 (10000), còn những trường hợp còn lại thì xóa hết những bản ghi status = 3 (10000))

			_log.info("OpenCPS REMOVE PUBLISH QUEUE SCHEDULER IS : " + APIDateTimeUtils.convertDateToString(new Date()));

			List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByStatusesAndServerNo(
					new int[] { PublishQueueTerm.STATE_RECEIVED_ACK }, ServerConfigTerm.DVCQG_INTEGRATION, 0, 10000, null);

			if (Validator.isNull(lstQueues) || lstQueues.size() == 0) {
				
				_log.info("list PublishQueue (serverNo = 'DVCQG' and Status = 3  return list: null");
				
			} else {
				
				_log.info("OpenCPS REMOVE PUBLISH WITH SERVERNO QUEUE COUNT: " + lstQueues.size());
				
				for (PublishQueue publishQueue : lstQueues) {
					if (thirtyDayAgo.compareTo(publishQueue.getCreateDate()) > 0) {
						
						PublishQueueLocalServiceUtil.removePublishQueue(publishQueue.getPublishQueueId());
						
					}
					
				}
			}

			List<PublishQueue> lstPqs = PublishQueueLocalServiceUtil.getByStatusesAndNotServerNo(
					new int[] { PublishQueueTerm.STATE_RECEIVED_ACK }, ServerConfigTerm.DVCQG_INTEGRATION, 0, 10000);

			if (Validator.isNull(lstPqs) || lstPqs.size() == 0) {
				_log.info("OpenCPS REMOVE PUBLISH WITH NOT SERVERNO QUEUE COUNT : null");
			} else {
				
				_log.info("OpenCPS REMOVE PUBLISH WITH NOT SERVERNO QUEUE COUNT: " + lstPqs.size());
				
				for (PublishQueue publishQueue : lstPqs) {
			
					PublishQueueLocalServiceUtil.removePublishQueue(publishQueue.getPublishQueueId());
					
				}
			}

			_log.info("OpenCPS REMOVE PUBLISH WITH NOT SERVERNO QUEUE HAS BEEN DONE: "
					+ APIDateTimeUtils.convertDateToString(new Date()));

			AccessTokenLocalServiceUtil.garbageToken();
			
//			Action 6. 6. File Temp (In tomcat, xóa hết)

			_log.info("OpenCPS REMOVE FILE TEMP IS: " + APIDateTimeUtils.convertDateToString(new Date()));
			
			
			if(Validator.isNotNull(pathTemp)) {
				
				if(pathTemp.contains("/tomcat") && pathTemp.contains("/temp") ) {
					_log.info(pathTemp);
					deleteDirectoryJava7(pathTemp);
					Files.createDirectories(Paths.get(pathTemp));
				}
				else {
					_log.info("Path " + pathTemp + " is Not Avaible!");
				}
				
				
			} else {
				_log.info("Path file Temp is Null! Please configurate attribute (org.opencps.path.temp) in properties");
			}

			_log.info("OpenCPS REMOVE FILE TEMP HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));
			
//			Action 7. opencps_dossiersync (Xóa những bản ghi có state_ = 3 cách 30 ngày về trước)
			
			_log.info("OpenCPS REMOVE DOSSIER SYNC IS : " + APIDateTimeUtils.convertDateToString(new Date()));
			
			List<DossierSync> lstDossierSync = DossierSyncLocalServiceUtil.getDossierSyncBeforeDateAndState(thirtyDayAgo, DossierSyncTerm.STATE_RECEIVED_ACK, 0, 10000);
			
			if (Validator.isNull(lstDossierSync) || lstDossierSync.size() == 0) {
				_log.info("OpenCPS REMOVE DOSSIER SYNC COUNT : null");
			} else {
				_log.info("OpenCPS REMOVE DOSSIER SYNC COUNT: " + lstDossierSync.size());
				
				for (DossierSync dossierSync : lstDossierSync) {
					DossierSyncLocalServiceUtil.removeByDossierId(dossierSync.getGroupId(), dossierSync.getDossierId());
				}
				
			}
			
			_log.info("OpenCPS REMOVE DOSSIER SYNC HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));
		
		} catch (Exception e) {
			_log.error(e);
		}

		isRunning = false;
		
		_log.info("OpenCPS REMOVE SCHEDULER HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));

	}

	public static void deleteDirectoryJava7(String filePath) throws IOException {

		Path path = Paths.get(filePath);

		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

			// delete directories or folders
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				Files.delete(dir);
				System.out.printf("Directory is deleted : %s%n", dir);
				return FileVisitResult.CONTINUE;
			}

			// delete files
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				System.out.printf("File is deleted : %s%n", file);
				return FileVisitResult.CONTINUE;
			}
		}

		);

	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws SchedulerException {
		String listenerClass = getClass().getName();
		Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null,
				cronExpression);

		_schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
		_schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.PERSISTED);

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

	private Log _log = LogFactoryUtil.getLog(GarbageDossierPublishQueueScheduler.class);
}
