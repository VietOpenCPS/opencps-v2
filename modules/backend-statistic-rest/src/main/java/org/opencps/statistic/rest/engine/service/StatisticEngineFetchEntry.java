package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.opencps.datamgt.util.BetimeUtils;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetDossierData;
import org.opencps.statistic.rest.dto.GetPersonData;
import org.opencps.statistic.rest.dto.GetVotingResultData;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;

public class StatisticEngineFetchEntry {

	public void updateDossierStatisticData(DossierStatisticData statisticData, GetDossierData dossierData,
			Date fromStatisticDate, Date toStatisticDate, boolean reporting) {
//		int month = LocalDate.now().getMonthValue();
		//int year = LocalDate.now().getYear();
		Calendar dateStatistic = Calendar.getInstance();
		dateStatistic.setTime(fromStatisticDate);
		
		statisticData.setMonth(dateStatistic.get(Calendar.MONTH) + 1);
		statisticData.setYear(dateStatistic.get(Calendar.YEAR));
		statisticData.setGroupId(dossierData.getGroupId());
		statisticData.setReporting(reporting);
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
		} else {
			// tiep nhan xu ly
			statisticData.setProcessCount(statisticData.getProcessCount() + 1);

			//if (receviedDate != null && receviedDate.after(getFirstDay(month, year))) {
			if (receviedDate != null && receviedDate.after(fromStatisticDate)) {
				// trong ky
				statisticData.setReceivedCount(statisticData.getReceivedCount() + 1);
				if (dossierData.getOnline()) {
					statisticData.setOnlineCount(statisticData.getOnlineCount() + 1);
				} else {
					statisticData.setOnegateCount(statisticData.getOnegateCount() + 1);
				}
			} else {
				// ton ky truoc
				statisticData.setRemainingCount(statisticData.getRemainingCount() + 1);
			}
			
			if (releaseDate == null || releaseDate.after(toStatisticDate)) {
				// hồ sơ đang xử lý 
				if (dossierData.getDossierStatus().contentEquals("waiting") || 
						dossierData.getDossierStatus().contentEquals("receiving")) {
					// dang tạm dừng chờ bổ sung
					statisticData.setWaitingCount(statisticData.getWaitingCount() + 1);
				} else {
					// đang xử lý
					statisticData.setProcessingCount(statisticData.getProcessingCount() + 1);
					if (!"processing".equals(dossierData.getDossierStatus())) {
						// xử lý nội bộ
						statisticData.setOutsideCount(statisticData.getOutsideCount() + 1);
					} else {
						// xử lý ngoài cơ quan
						statisticData.setInsideCount(statisticData.getInsideCount() + 1);
					}

					Date now = new Date();
					if (dueDate != null && !dueDate.after(now.before(toStatisticDate) ? now : toStatisticDate)) {
						// đang quá hạn
						statisticData.setOverdueCount(statisticData.getOverdueCount() + 1);
						if (!"processing".equals(dossierData.getDossierStatus())) {
							// đang quá hạn và xử lý bên ngoài 
							statisticData.setInteroperatingCount(statisticData.getInteroperatingCount() + 1);
						}
					} else {
						// đang xử lý còn hạn
						statisticData.setUndueCount(statisticData.getUndueCount() + 1);
					}
				}
			} else {
				// ho so da ket thuc trong thang	
				if (dossierData.getDossierStatus().contentEquals("cancelled")) {
					// ho so da bi rut trong thang
					statisticData.setCancelledCount(statisticData.getCancelledCount() + 1);
				} else {
					// hồ sơ đã hoàn thành trong tháng	
					statisticData.setReleaseCount(statisticData.getReleaseCount() + 1);						
					if (dossierData.getDossierStatus().contentEquals("unresolved")) {
						// từ chối giải quyết => không tính hạn xử lý
						statisticData.setUnresolvedCount(statisticData.getUnresolvedCount() + 1);
					} else { 
						if (finishDate != null) {
							// số đã trả kết quả
							statisticData.setDoneCount(statisticData.getDoneCount() + 1);								
						} else {
							statisticData.setReleasingCount(statisticData.getReleasingCount() + 1);
						}
					}

					// hồ sơ có kết quả hoặc từ chối tính hạn xử lý
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
	}

	public void updateSumDossierStatisticData(DossierStatisticData statisticData, GetDossierData dossierData,
			Date fromStatisticDate, Date toStatisticDate, boolean reporting) {
//		int month = LocalDate.now().getMonthValue();
		//int year = LocalDate.now().getYear();
		Calendar dateStatistic = Calendar.getInstance();
		dateStatistic.setTime(fromStatisticDate);
		
		statisticData.setMonth(dateStatistic.get(Calendar.MONTH) + 1);
		statisticData.setYear(dateStatistic.get(Calendar.YEAR));
		statisticData.setGroupId(dossierData.getGroupId());
		statisticData.setReporting(reporting);
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
		} else {
			// tiep nhan xu ly
			statisticData.setProcessCount(statisticData.getProcessCount() + 1);

			//if (receviedDate != null && receviedDate.after(getFirstDay(month, year))) {
			if (receviedDate != null && receviedDate.after(fromStatisticDate)) {
				// trong ky
				statisticData.setReceivedCount(statisticData.getReceivedCount() + 1);
				if (dossierData.getOnline()) {
					statisticData.setOnlineCount(statisticData.getOnlineCount() + 1);
				} else {
					statisticData.setOnegateCount(statisticData.getOnegateCount() + 1);
				}
			} else {
				// ton ky truoc
				statisticData.setRemainingCount(statisticData.getRemainingCount() + 1);
			}
			
			if (releaseDate == null || releaseDate.after(toStatisticDate)) {
				// hồ sơ đang xử lý 
				if (dossierData.getDossierStatus().contentEquals("waiting") || 
						dossierData.getDossierStatus().contentEquals("receiving")) {
					// dang tạm dừng chờ bổ sung
					statisticData.setWaitingCount(statisticData.getWaitingCount() + 1);
				} else {
					// đang xử lý
					statisticData.setProcessingCount(statisticData.getProcessingCount() + 1);
					if (!"processing".equals(dossierData.getDossierStatus())) {
						// xử lý nội bộ
						statisticData.setOutsideCount(statisticData.getOutsideCount() + 1);
					} else {
						// xử lý ngoài cơ quan
						statisticData.setInsideCount(statisticData.getInsideCount() + 1);
					}

					Date now = new Date();
					if (dueDate != null && !dueDate.after(now.before(toStatisticDate) ? now : toStatisticDate)) {
						// đang quá hạn
						statisticData.setOverdueCount(statisticData.getOverdueCount() + 1);
						if (!"processing".equals(dossierData.getDossierStatus())) {
							// đang quá hạn và xử lý bên ngoài 
							statisticData.setInteroperatingCount(statisticData.getInteroperatingCount() + 1);
						}
					} else {
						// đang xử lý còn hạn
						statisticData.setUndueCount(statisticData.getUndueCount() + 1);
					}
				}
			} else {
				// ho so da ket thuc trong thang	
				if (dossierData.getDossierStatus().contentEquals("cancelled")) {
					// ho so da bi rut trong thang
					statisticData.setCancelledCount(statisticData.getCancelledCount() + 1);
				} else {
					// hồ sơ đã hoàn thành trong tháng	
					if (fromStatisticDate.before(releaseDate) && toStatisticDate.after(releaseDate))
						statisticData.setReleaseCount(statisticData.getReleaseCount() + 1);						
					if (dossierData.getDossierStatus().contentEquals("unresolved")) {
						// từ chối giải quyết => không tính hạn xử lý
						statisticData.setUnresolvedCount(statisticData.getUnresolvedCount() + 1);
					} else { 
						if (finishDate != null
								&& fromStatisticDate.before(finishDate) && toStatisticDate.after(finishDate)) {
							// số đã trả kết quả
							statisticData.setDoneCount(statisticData.getDoneCount() + 1);								
						} else {
							statisticData.setReleasingCount(statisticData.getReleasingCount() + 1);
						}
					}

					if (fromStatisticDate.before(releaseDate) && toStatisticDate.after(releaseDate)) {
						/*
						// hồ sơ có kết quả hoặc từ chối tính hạn xử lý
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
						*/
						
						int betimeCal = dueDate != null ? BetimeUtils.getValueCompareRelease(dossierData.getGroupId(), releaseDate, dueDate) : 3;
						
						if (betimeCal == 3) {
							statisticData.setBetimesCount(statisticData.getBetimesCount() + 1);
						} else if (betimeCal == 1) {
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
		}
	}
	
//	private static Date getLastDay() {
//		LocalDateTime localDateTime = LocalDateTime.now();
//		localDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
//		localDateTime = localDateTime.with(LocalTime.MAX);
//		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
//		return Date.from(instant);
//	}

	//LamTV_ Process Statistic follow GS.Tuan Anh
	public void calculateVotingStatisticData(VotingResultStatisticData statisticData, GetVotingResultData votingData,
			Date fromCalDate, Date toCalDate) {

		// Get info date check statistic
		Calendar dateStatistic = Calendar.getInstance();
		dateStatistic.setTime(fromCalDate);
		
		statisticData.setMonth(dateStatistic.get(Calendar.MONTH) + 1);
		statisticData.setYear(dateStatistic.get(Calendar.YEAR));
		statisticData.setGroupId(votingData.getGroupId());
		
		statisticData.setTotalVoted(statisticData.getTotalVoted() + 1);

		if (votingData.getSelected() == 1) {
			statisticData.setVeryGoodCount(statisticData.getVeryGoodCount() + 1);
		} else if (votingData.getSelected() == 2) {
			statisticData.setGoodCount(statisticData.getGoodCount() + 1);
		} else if (votingData.getSelected() == 3){
			statisticData.setBadCount(statisticData.getBadCount() + 1);
		}

		//System.out.println("statisticData"+ JSONFactoryUtil.looseSerialize(statisticData));
	}

	public void calculatePersonStatisticData(PersonStatisticData statisticData, GetPersonData personData,
			Date fromStatisticDate, Date toStatisticDate) {

		Calendar dateStatistic = Calendar.getInstance();
		dateStatistic.setTime(fromStatisticDate);

		statisticData.setMonth(dateStatistic.get(Calendar.MONTH) + 1);
		statisticData.setYear(dateStatistic.get(Calendar.YEAR));
		statisticData.setGroupId(personData.getGroupId());
		// Get info date check statistic
		statisticData.setTotalVoted(statisticData.getTotalVoted() + 1);
		//System.out.println("votingData.getSelected()"+ votingData.getSelected());
		//System.out.println("votingData.getGroupId()"+ votingData.getGroupId());

		if (personData.getSelected() == 1) {
			statisticData.setVeryGoodCount(statisticData.getVeryGoodCount() + 1);
		} else if (personData.getSelected() == 2) {
			statisticData.setGoodCount(statisticData.getGoodCount() + 1);
		} else if (personData.getSelected() == 3){
			statisticData.setBadCount(statisticData.getBadCount() + 1);
		}
	}
}
