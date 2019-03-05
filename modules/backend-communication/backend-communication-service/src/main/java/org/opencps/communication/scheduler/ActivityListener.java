package org.opencps.communication.scheduler;


/**
 * @author trungnt
 */
//@Component(immediate = true, service = ModelListener.class)
//public class ActivityListener extends BaseModelListener<Activity> {
//
//	Locale defaultLocale = LocaleUtil.getDefault();
//
//	@Override
//	public void onAfterCreate(Activity model)
//		throws ModelListenerException {
//
//		_log.info("onAfterCreate Activity" + model);
//
//		/*ServiceContext serviceContext =
//			MBServiceContextFactoryUtil.createWidthSignedState(
//				model.getCompanyId(), model.getGroupId(), model.getUserId(),
//				true);*/
//
//		// Move to ActivityBusinessImpl
//		/*try {
//		
//			long hosterId = 0;
//		
//			if (model.getHostingId() > 0) {
//		
//				WorkingUnit workingUnit =
//					_workingUnitLocalService.fetchWorkingUnit(
//						model.getHostingId());
//		
//				hosterId = workingUnit != null ? workingUnit.getLeaderId() : 0;
//		
//			}
//		
//			
//		
//			VisibilityBusinessFactoryUtil.addVisibility(
//				model.getUserId(), model.getGroupId(), Activity.class.getName(),
//				String.valueOf(model.getActivityId()), model.getActivityType(),
//				model.getSubject(),
//				MBPermissionConstant.Visibilities.PRIVATE.getValue(),
//				VisibilityUtil.autoSeek(
//					model.getGroupId(), Activity.class.getName(),
//					model.getActivityType(), 0),
//				model.getManagerId(), hosterId, model.getLeaderId(),
//				model.getStartDate(), null, model.getEndDate(), 0,
//				StringPool.BLANK, 0, 0, null, StringPool.BLANK,
//				MBPermissionConstant.VisibilityEditable.DRAFT.getValue(),
//				serviceContext);
//		
//			
//		
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}*/
//
//		//ActivityBusinessFactoryUtil.updatingResources(model, serviceContext);
//
//		try {
//			ChangeLogBusinessFactoryUtil.writeChangeLog(
//				model.getUserId(), model.getCompanyId(), model.getGroupId(),
//				Activity.class.getName(), String.valueOf(model.getActivityId()),
//				model.getUserName(),
//				ActivityBusinessFactoryUtil.getChangeLogContent(
//					ChangeLogTerm.CREATE, defaultLocale, model),
//				ChangeLogTerm.CREATE,
//				ActivityBusinessFactoryUtil.getChangeLogPayload(
//					ChangeLogTerm.CREATE, defaultLocale, model));
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//	}
//
//	@Override
//	public void onAfterUpdate(Activity model)
//		throws ModelListenerException {
//
//		_log.info("onAfterUpdate Activity" + model);
//
//		/*try {
//		
//			long hosterId = 0;
//		
//			// long leaderId = model.getLeaderId();
//		
//			if (model.getHostingId() > 0) {
//				WorkingUnit workingUnit =
//					_workingUnitLocalService.fetchWorkingUnit(
//						model.getHostingId());
//				hosterId = workingUnit != null ? workingUnit.getLeaderId() : 0;
//			}
//		
//			Visibility visibility =
//				_visibilityLocalService.fetchByF_className_classPK(
//					Activity.class.getName(),
//					String.valueOf(model.getActivityId()));
//		
//			
//		
//			if (visibility != null) {
//		
//				User user = UserLocalServiceUtil.fetchUser(model.getUserId());
//		
//				if (user != null && !user.isDefaultUser()) {
//		
//					VisibilityBusinessFactoryUtil.updateVisibility(
//						Activity.class.getName(),
//						String.valueOf(model.getActivityId()),
//						model.getActivityType(), model.getSubject(),
//						visibility.getVisible(), visibility.getState(),
//						model.getManagerId(), hosterId, model.getLeaderId(),
//						model.getStartDate(), visibility.getFinishDate(),
//						model.getEndDate(), visibility.getOverdue(),
//						visibility.getPayload(), visibility.getProgress(),
//						visibility.getSecurity(), StringPool.BLANK,
//						visibility.getDoerId(), visibility.getLastDate(),
//						visibility.getProcessNo(), visibility.getEditable(),
//						serviceContext);
//				}
//			}
//			
//			else {
//				VisibilityBusinessFactoryUtil.addVisibility(
//					model.getUserId(), model.getGroupId(),
//					Activity.class.getName(),
//					String.valueOf(model.getActivityId()),
//					model.getActivityType(), model.getSubject(),
//					MBPermissionConstant.Visibilities.PRIVATE.getValue(),
//					VisibilityUtil.autoSeek(
//						model.getGroupId(), Activity.class.getName(),
//						model.getActivityType(), 0),
//					model.getManagerId(), hosterId, model.getLeaderId(),
//					model.getStartDate(), null, model.getEndDate(), 0,
//					StringPool.BLANK, 0, 0, null, StringPool.BLANK,
//					MBPermissionConstant.VisibilityEditable.DRAFT.getValue(),
//					serviceContext);
//			}
//		
//			
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}*/
//
//		/*if (model.getHostingId() != getHostingId() ||
//			model.getJobPosId() != getJobPosId() ||
//			model.getManagerId() != getManagerId()) {
//
//			ServiceContext serviceContext =
//				MBServiceContextFactoryUtil.createWidthSignedState(
//					model.getCompanyId(), model.getGroupId(), model.getUserId(),
//					true);
//			ActivityBusinessFactoryUtil.updatingResources(
//				model, serviceContext);
//		}*/
//
//		try {
//			ChangeLogBusinessFactoryUtil.writeChangeLog(
//				model.getUserId(), model.getCompanyId(), model.getGroupId(),
//				Activity.class.getName(), String.valueOf(model.getActivityId()),
//				model.getUserName(),
//				ActivityBusinessFactoryUtil.getChangeLogContent(
//					ChangeLogTerm.UPDATE, defaultLocale, model),
//				ChangeLogTerm.UPDATE,
//				ActivityBusinessFactoryUtil.getChangeLogPayload(
//					ChangeLogTerm.UPDATE, defaultLocale, model));
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//
//		doAddMessageQueue(model);
//	}
//
//	@Override
//	public void onAfterRemove(Activity model)
//		throws ModelListenerException {
//
//		/*
//		 * _log.info("onAfterRemove Activity" + model); try {
//		 * VisibilityBusinessFactoryUtil.deleteVisibility(
//		 * Activity.class.getName(), String.valueOf(model.getActivityId())); }
//		 * catch (Exception e) { _log.equals(e); }
//		 */
//
//		try {
//			ChangeLogBusinessFactoryUtil.writeChangeLog(
//				model.getUserId(), model.getCompanyId(), model.getGroupId(),
//				Activity.class.getName(), String.valueOf(model.getActivityId()),
//				model.getUserName(),
//				ActivityBusinessFactoryUtil.getChangeLogContent(
//					ChangeLogTerm.DELETE, defaultLocale, model),
//				ChangeLogTerm.DELETE,
//				ActivityBusinessFactoryUtil.getChangeLogPayload(
//					ChangeLogTerm.DELETE, defaultLocale, model));
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//	}
//
//	@Override
//	public void onBeforeCreate(Activity model)
//		throws ModelListenerException {
//
//		super.onBeforeCreate(model);
//	}
//
//	@Override
//	public void onBeforeUpdate(Activity model)
//		throws ModelListenerException {
//
//		setHostingId(model.getHostingId());
//		setManagerId(model.getManagerId());
//		setJobPosId(model.getJobPosId());
//	}
//
//	protected void _doAddMessageQueue(
//		Activity model, List<String[]> lstInfo, String code) {
//
//		JSONObject payload = JSONFactoryUtil.createJSONObject();
//
//		try {
//			payload.put(
//				"Activity", JSONFactoryUtil.createJSONObject(
//					JSONFactoryUtil.looseSerialize(model)));
//		}
//		catch (JSONException parse) {
//			_log.error(parse);
//		}
//
//		if (lstInfo != null && model != null) {
//			for (String[] info : lstInfo) {
//				try {
//					NotificationQueueBusinessFactoryUtil.create(
//						model.getUserId(), model.getGroupId(), code,
//						Activity.class.getName(),
//						String.valueOf(model.getActivityId()),
//						payload.toJSONString(), model.getUserName(), info[1],
//						GetterUtil.getLong(info[3]), info[0], info[2], null,
//						null,
//						MBServiceContextFactoryUtil.create(
//							model.getCompanyId(), model.getGroupId(),
//							model.getUserId()));
//				}
//				catch (Exception e) {
//					// Nothing todo
//				}
//			}
//		}
//	}
//
//	protected void doAddMessageQueue(Activity model) {
//
//		List<String[]> lstInfo =
//			ResourceBusinessFactoryUtil.getJoinResourceInfo(
//				model.getGroupId(), Activity.class.getName(),
//				String.valueOf(model.getActivityId()));
//
//		_doAddMessageQueue(
//			model, lstInfo,
//			NotificationUtil.NotificationType.ACTIVITY.getCode());
//
//	}
//
//	private long hostingId;
//	private long managerId;
//	private long jobPosId;
//
//	public long getHostingId() {
//
//		return hostingId;
//	}
//
//	public long getManagerId() {
//
//		return managerId;
//	}
//
//	public long getJobPosId() {
//
//		return jobPosId;
//	}
//
//	public void setHostingId(long hostingId) {
//
//		this.hostingId = hostingId;
//	}
//
//	public void setManagerId(long managerId) {
//
//		this.managerId = managerId;
//	}
//
//	public void setJobPosId(long jobPosId) {
//
//		this.jobPosId = jobPosId;
//	}
//
//	@Reference
//	private WorkingUnitLocalService _workingUnitLocalService;
//
//	@Reference
//	private VisibilityLocalService _visibilityLocalService;
//
//	private Log _log = LogFactoryUtil.getLog(ActivityListener.class);
//
//}
