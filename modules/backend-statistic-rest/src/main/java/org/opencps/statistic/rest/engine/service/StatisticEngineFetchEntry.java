package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.util.Validator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetDossierData;
import org.opencps.statistic.rest.dto.GetVotingResultData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;

public class StatisticEngineFetchEntry {

	public void updateDossierStatisticData(DossierStatisticData statisticData, GetDossierData dossierData, int month) {
//		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		statisticData.setMonth(month);
		statisticData.setYear(year);
		statisticData.setGroupId(dossierData.getGroupId());
		Date dueDate = Validator.isNull(dossierData.getDueDate())
				? null
				: StatisticUtils.convertStringToDate(dossierData.getDueDate());
		Date extendDate = Validator.isNull(dossierData.getExtendDate())
				? null
				: StatisticUtils.convertStringToDate(dossierData.getExtendDate());
		Date releaseDate = Validator.isNull(dossierData.getReleaseDate())
				? null
				: StatisticUtils.convertStringToDate(dossierData.getReleaseDate());
		Date receviedDate = Validator.isNull(dossierData.getReceiveDate())
				? null
				: StatisticUtils.convertStringToDate(dossierData.getReceiveDate());
		Date finishDate = Validator.isNull(dossierData.getFinishDate())
				? null
				: StatisticUtils.convertStringToDate(dossierData.getFinishDate());
		//
		statisticData.setTotalCount(statisticData.getTotalCount() + 1);
		if (dossierData.getDossierStatus().contentEquals("denied")) {
			statisticData.setDeniedCount(statisticData.getDeniedCount() + 1);
		} else if (dossierData.getDossierStatus().contentEquals("cancelled")) {
			statisticData.setCancelledCount(statisticData.getCancelledCount() + 1);
		} else {
			statisticData.setProcessCount(statisticData.getProcessCount() + 1);
			//System.out.println("getFirstDay(month, year): "+getFirstDay(month, year));
			//System.out.println("receviedDate: "+receviedDate);
			//System.out.println("receviedDate.after(getFirstDay(month, year): "+receviedDate.after(getFirstDay(month, year)));
			if (receviedDate != null && receviedDate.after(getFirstDay(month, year))) {
				statisticData.setReceivedCount(statisticData.getReceivedCount() + 1);
				if (dossierData.getOnline()) {
					statisticData.setOnlineCount(statisticData.getOnlineCount() + 1);
				} else {
					statisticData.setOnegateCount(statisticData.getOnegateCount() + 1);
				}
			} else {
				statisticData.setRemainingCount(statisticData.getRemainingCount() + 1);
			}

			//if (releaseDate is null or releaseDate > toDate) {
			if (releaseDate == null || releaseDate.after(getLastDay(month, year))) {
				// hồ sơ đang xử lý trong tháng
				if (dossierData.getDossierStatus().contentEquals("waiting")) {
					// tạm dừng chờ bổ sung
					statisticData.setWaitingCount(statisticData.getWaitingCount() + 1);
				} else {
					// đang xử lý
					statisticData.setProcessingCount(statisticData.getProcessingCount() + 1);
					if (!"procesing".equals(dossierData.getDossierStatus())) {
						// xử lý nội bộ
						statisticData.setOutsideCount(statisticData.getOutsideCount() + 1);
					} else {
						// xử lý ngoài cơ quan
						statisticData.setInsideCount(statisticData.getInsideCount() + 1);
					}
					if (dueDate != null && !dueDate.after(getLastDay(month, year))) {
						// đang quá hạn
						statisticData.setOverdueCount(statisticData.getOverdueCount() + 1);
						if (!"procesing".equals(dossierData.getDossierStatus())) {
							// đang quá hạn và xử lý bên ngoài 
							statisticData.setInteroperatingCount(statisticData.getInteroperatingCount() + 1);
						}
					} else {
						// đang xử lý còn hạn
						statisticData.setUndueCount(statisticData.getUndueCount() + 1);
					}
				}
			} else {
				// hồ sơ đã hoàn thành trong tháng
				statisticData.setReleaseCount(statisticData.getReleaseCount() + 1);
				if (dossierData.getDossierStatus().contentEquals("unresolved")) {
					// từ chối giải quyết => không tính hạn xử lý
					statisticData.setUnresolvedCount(statisticData.getUnresolvedCount() + 1);
				} 
				
				// hồ sơ có kết quả tính hạn xử lý
				//if (finishDate not null ) {
				if (finishDate != null) {
					// số đã trả kết quả
					statisticData.setDoneCount(statisticData.getDoneCount() + 1);
				} else {
					statisticData.setReleasingCount(statisticData.getReleasingCount() + 1);
				}

				int overdue = 1; // 0: sớm hạn, 1: đúng hạn, 2: quá hạn
				// Check condition filter betimes
				if (dueDate != null) {
					//Check extendDate != null and releaseDate < dueDate
					if (releaseDate != null && releaseDate.before(dueDate) && extendDate != null) overdue = 0;
					//Or check finishDate < dueDate
					if (finishDate != null && finishDate.before(dueDate)) overdue = 0;
					//Check overTime condition releaseDate > dueDate
					if (releaseDate != null && releaseDate.after(dueDate)) overdue = 2;
				}

				if (overdue==0) {
					statisticData.setBetimesCount(statisticData.getBetimesCount() + 1);
				} else if (overdue==2) {
					statisticData.setOvertimeCount(statisticData.getOvertimeCount() + 1);
					boolean isOvertimeInside = true;
					if (isOvertimeInside) {
						statisticData.setOvertimeInside(statisticData.getOvertimeInside() + 1);
					} else {
						statisticData.setOvertimeOutside(statisticData.getOvertimeOutside() + 1);
					}
				} else {
					statisticData.setOntimeCount(statisticData.getOntimeCount() + 1);
				}
			}
		}
	}

	private static Date getFirstDay(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);
		//Set calendar first of month
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		return cal.getTime();
	}

	private static Date getLastDay(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);
		//Set calendar first of month
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		return cal.getTime();
	}

//	private static Date getLastDay() {
//		LocalDateTime localDateTime = LocalDateTime.now();
//		localDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
//		localDateTime = localDateTime.with(LocalTime.MAX);
//		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
//		return Date.from(instant);
//	}

	//LamTV_ Process Statistic follow GS.Tuan Anh
	public void calculateVotingStatisticData(VotingResultStatisticData statisticData, GetVotingResultData votingData, int month) {
		int year = LocalDate.now().getYear();
		statisticData.setMonth(month);
		statisticData.setYear(year);
		statisticData.setGroupId(votingData.getGroupId());
		// Get info date check statistic
		//Date createDate = Validator.isNotNull(votingData.getCreateDate())
		//		? StatisticUtils.convertStringToDate(votingData.getCreateDate()): null;
		//Date modifiedDate = Validator.isNotNull(votingData.getModifiedDate())
		//		? StatisticUtils.convertStringToDate(votingData.getModifiedDate()): null;
		//
		statisticData.setTotalCount(statisticData.getTotalCount() + 1);

		if (votingData.getSelected() == 0) {
			statisticData.setVeryGoodCount(statisticData.getVeryGoodCount() + 1);
		} else if (votingData.getSelected() == 1) {
			statisticData.setGoodCount(statisticData.getGoodCount() + 1);
		} else {
			statisticData.setBadCount(statisticData.getBadCount() + 1);
		}

	}
}
