package org.opencps.datamgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HolidayCheckUtils {

//	private static Log _log = LogFactoryUtil.getLog(HolidayCheckUtils.class);

	/**
	 * @param startDate
	 * @param endDate
	 * @param daysDuration
	 * @return
	 */
//	public OutDateStatus checkActionDateOver(Date startDate, Date endDate,
//			String daysDuration) {
//
//		OutDateStatus outDateStatus = new OutDateStatus();
//
//		long timeOver = 0;
//
//		if (daysDuration.trim().length() > 0) {
//
//			Calendar endDayCal = Calendar.getInstance();
//
//			endDayCal.setTime(endDate);
//
//			Calendar endDateMax = HolidayUtils.getEndDate(startDate,
//					daysDuration);
//
//			long endDay = endDayCal.getTimeInMillis();
//			long endDayMax = endDateMax.getTimeInMillis();
//
//			timeOver = endDayMax - endDay;
//
//			if (timeOver > 0) {
//				outDateStatus.setIsOutDate(false);
//				outDateStatus.setTimeOutDate(timeOver);
//				return outDateStatus;
//			} else if (timeOver < 0) {
//				outDateStatus.setIsOutDate(true);
//				outDateStatus.setTimeOutDate(timeOver);
//				return outDateStatus;
//			}
//		}
//		return outDateStatus;
//	}

	public static int calculatorDateUntilDealine(Date startDate, Date endDate,
			int daysDuration) {

		int dateOverNumbers = 0;

		if (daysDuration > 0) {

			Calendar endayCal = Calendar.getInstance();
			endayCal.setTime(endDate);

			Calendar dealineCal = Calendar.getInstance();
			dealineCal.setTime(startDate);

			for (int i = 0; i < daysDuration; i++) {

				dealineCal.add(Calendar.DATE, 1);
			}

			long endDay = endayCal.getTimeInMillis();
			long deadline = dealineCal.getTimeInMillis();
			long result = 0;

			result = deadline - endDay;

			result = DateTimeUtils.convertTimemilisecondsToDays(result);

			dateOverNumbers = (int) result;
		}
		return dateOverNumbers;
	}

	public static String calculatorDateUntilDealineReturnFormart(
			Date startDate, Date endDate, int daysDuration, Locale locale) {

		String dateOverNumbers = StringPool.BLANK;

		if (daysDuration > 0 && Validator.isNotNull(startDate)
				&& Validator.isNotNull(endDate)) {

			Calendar endayCal = Calendar.getInstance();
			endayCal.setTime(endDate);

			Calendar dealineCal = Calendar.getInstance();
			dealineCal.setTime(startDate);

			for (int i = 0; i < daysDuration; i++) {

				dealineCal.add(Calendar.DATE, 1);
			}

			long endDay = endayCal.getTimeInMillis();
			long deadline = dealineCal.getTimeInMillis();
			long result = 0;

			result = deadline - endDay;

			dateOverNumbers = DateTimeUtils.convertTimemilisecondsToFormat(
					result, locale);
		}
		return dateOverNumbers;
	}

	/**
	 * @param processOrderId
	 * @param processWorkflowId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
//	public OutDateStatus getOutDateStatus(long processOrderId,
//			long processWorkflowId) {
//
//		ActionHistory actionHistoryNewest = null;
//		List<ActionHistory> actionHistoryList = new ArrayList<ActionHistory>();
//
//		ProcessWorkflow processWorkflow = null;
//
//		ProcessStep processStep = null;
//
//		OutDateStatus outDateStatus = null;
//
//		try {
//			if (processOrderId > 0 && processWorkflowId > 0) {
//
//				actionHistoryList = ActionHistoryLocalServiceUtil
//						.getActionHistoryByProcessOrderId(processOrderId, 0,
//								1, false);
//
//				if (actionHistoryList.size() > 0) {
//
//					actionHistoryNewest = actionHistoryList.get(0);
//				}
//
//				try {
//					processWorkflow = ProcessWorkflowLocalServiceUtil
//							.getProcessWorkflow(processWorkflowId);
//				} catch (NoSuchProcessWorkflowException e) {
//
//				}
//
//				if (Validator.isNotNull(processWorkflow)) {
//
//					if (processWorkflow.getPostProcessStepId() > 0) {
//
//						try {
//							processStep = ProcessStepLocalServiceUtil
//									.getProcessStep(processWorkflow
//											.getPostProcessStepId());
//						} catch (NoSuchProcessStepException e) {
//
//						}
//
//						if (Validator.isNotNull(processStep)) {
//
//							if (Validator.isNotNull(actionHistoryNewest)
//									&& Validator.isNotNull(actionHistoryNewest
//											.getCreateDate())) {
//								outDateStatus = checkActionDateOver(
//										actionHistoryNewest.getCreateDate(),
//										new Date(),
//										processStep.getDaysDuration());
//							} else {
//								outDateStatus = checkActionDateOver(new Date(),
//										new Date(),
//										processStep.getDaysDuration());
//							}
//
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			_log.error(e);
//		}
//
//		return outDateStatus;
//
//	}
	
	public static Date getEndDate(Date baseDate, String pattern) {
//		Calendar estimateDate = null;
//		estimateDate = HolidayUtils.getEndDate(baseDate, pattern);
//		return estimateDate.getTime();
		return null;
	}

}