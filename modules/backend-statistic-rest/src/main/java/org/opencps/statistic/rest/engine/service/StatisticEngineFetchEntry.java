package org.opencps.statistic.rest.engine.service;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private static final int USED_POSTAL = 2;
//	private static final int NOT_USED_POSTAL = 1;
	protected Log _log = LogFactoryUtil.getLog(StatisticEngineFetchEntry.class);
		
	//Caculate dueDate by day
	private static final Boolean CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE = Validator.isNotNull(PropsUtil.get("opencps.statistic.dossier.dueDate.day.enable"))
			? Boolean.valueOf(PropsUtil.get("opencps.statistic.dossier.dueDate.day.enable")) : false;
			
	public void updateDossierStatisticData(DossierStatisticData statisticData, GetDossierData dossierData,
			Date fromStatisticDate, Date toStatisticDate, int reporting) {
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
		int viaPostal = dossierData.getViaPostal();
//		if (viaPostal != 0)
//			_log.info("VIA POSTAL STATISTIC: " + viaPostal);
		if (viaPostal == USED_POSTAL) {
			statisticData.setViaPostalCount(statisticData.getViaPostalCount() + 1);
		}
		else {
			
		}
		if (!dossierData.getOnline() && dossierData.getFromViaPostal() > 0) {
			statisticData.setFromViaPostalCount(statisticData.getFromViaPostalCount() + 1);
		}
		Calendar receivedStatistic = Calendar.getInstance();
		receivedStatistic.setTime(receviedDate);
		boolean isReceivedSaturday = (receivedStatistic.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
		Calendar finishStatistic = Calendar.getInstance();
		finishStatistic.setTime(receviedDate);
		boolean isFinishSaturday = (receivedStatistic.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
		if (isReceivedSaturday && isFinishSaturday) {
			statisticData.setSaturdayCount(statisticData.getSaturdayCount() + 1);
		}
		if (isReceivedSaturday) {
			statisticData.setReceiveDossierSatCount(statisticData.getReceiveDossierSatCount() + 1);
		}
		if (isFinishSaturday) {
			statisticData.setReleaseDossierSatCount(statisticData.getReleaseDossierSatCount() + 1);
		}
		int serviceLevel = dossierData.getServiceLevel();
		if (LEVEL_3 == serviceLevel) {
			statisticData.setDossierOnline3Count(statisticData.getDossierOnline3Count() + 1);
		}
		if (LEVEL_4 == serviceLevel) {
			statisticData.setDossierOnline4Count(statisticData.getDossierOnline4Count() + 1);
		}
		//tong so tiep nhan dau ky
		if (receviedDate != null && (releaseDate == null || releaseDate.after(fromStatisticDate))) {
			statisticData.setTotalCount(statisticData.getTotalCount() + 1);
		}
		if (dossierData.getDossierStatus().contentEquals(DossierStatusTerm.DENIED)) {
			statisticData.setDeniedCount(statisticData.getDeniedCount() + 1);				
		} else {
			// tiep nhan xu ly
			statisticData.setProcessCount(statisticData.getProcessCount() + 1);

			//if (receviedDate != null && receviedDate.after(getFirstDay(month, year))) {
			if (receviedDate != null && receviedDate.after(fromStatisticDate)
					&& receviedDate.before(toStatisticDate)) {
				// ho so tiep nhan trong ky:
				// ngay nhan thuoc from - to
				statisticData.setReceivedCount(statisticData.getReceivedCount() + 1);
				if (dossierData.getOnline()) {
					statisticData.setOnlineCount(statisticData.getOnlineCount() + 1);
				} else {
					statisticData.setOnegateCount(statisticData.getOnegateCount() + 1);
				}
			} else if (receviedDate != null && receviedDate.before(fromStatisticDate)
					&& ( releaseDate == null || releaseDate.after(fromStatisticDate))) {
				// ho so ton ky truoc:
				// ngay nhan truoc ngay from, ngay release sau ngay from hoac ko co ngay release
				statisticData.setRemainingCount(statisticData.getRemainingCount() + 1);
			}
			
			if (releaseDate == null || releaseDate.after(toStatisticDate)) {
				// hồ sơ đang xử lý 
				if (dossierData.getDossierStatus().contentEquals(DossierStatusTerm.WAITING) || 
						dossierData.getDossierStatus().contentEquals(DossierStatusTerm.RECEIVING)) {
					// dang tạm dừng chờ bổ sung
					statisticData.setWaitingCount(statisticData.getWaitingCount() + 1);
				} else {
					// đang xử lý
					statisticData.setProcessingCount(statisticData.getProcessingCount() + 1);
					if (!DossierStatusTerm.PROCESSING.equals(dossierData.getDossierStatus())) {
						// xử lý nội bộ
						statisticData.setOutsideCount(statisticData.getOutsideCount() + 1);
					} else {
						// xử lý ngoài cơ quan
						statisticData.setInsideCount(statisticData.getInsideCount() + 1);
					}

					Date now = new Date();
					// add by phuchn - caculate duedate by day
					if (CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE) {
						dueDate = Validator.isNull(dossierData.getDueDate())
								? null : StatisticUtils.convertStringToDate(dossierData.getDueDate(), DossierStatusTerm.DATE_FORMAT);
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						now = StatisticUtils.convertStringToDate(dateFormat.format(new Date()), DossierStatusTerm.DATE_FORMAT);
					}
					if (dueDate != null && !dueDate.after(now.before(toStatisticDate) ? now : toStatisticDate)) {
						// đang quá hạn
						statisticData.setOverdueCount(statisticData.getOverdueCount() + 1);
						if (!DossierStatusTerm.PROCESSING.equals(dossierData.getDossierStatus())) {
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
				if (dossierData.getDossierStatus().contentEquals(DossierStatusTerm.CANCELLED)) {
					// ho so da bi rut trong thang
					statisticData.setCancelledCount(statisticData.getCancelledCount() + 1);
				} else {
					// hồ sơ đã hoàn thành trong tháng	
					statisticData.setReleaseCount(statisticData.getReleaseCount() + 1);						
					if (dossierData.getDossierStatus().contentEquals(DossierStatusTerm.UNRESOLVED)) {
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

					// add by phuchn - caculate duedate by day
					if (CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE) {
						dueDate = Validator.isNull(dossierData.getDueDate())
								? null : StatisticUtils.convertStringToDate(dossierData.getDueDate(), DossierStatusTerm.DATE_FORMAT);
						releaseDate = Validator.isNull(dossierData.getReleaseDate())
								? null
								: StatisticUtils.convertStringToDate(dossierData.getReleaseDate(), DossierStatusTerm.DATE_FORMAT);
						finishDate = Validator.isNull(dossierData.getFinishDate())
								? null
								: StatisticUtils.convertStringToDate(dossierData.getFinishDate(), DossierStatusTerm.DATE_FORMAT);
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
			// tong so ho so dang xu ly, hoan thanh doi voi cac ho so tiep nhan trong ky
			if (receviedDate != null && receviedDate.after(fromStatisticDate)
					&& receviedDate.before(toStatisticDate)) {
				// tong so ho so dang xu ly
				if (releaseDate == null || releaseDate.after(toStatisticDate)) {
					if (!DossierStatusTerm.WAITING.equals(dossierData.getDossierStatus()) && 
							!DossierStatusTerm.RECEIVING.equals(dossierData.getDossierStatus())) {
						// set ho so dang xu ly
						statisticData.setProcessingInAPeriodCount(statisticData.getProcessingInAPeriodCount() + 1);
					}
				} // tong so ho so hoan thanh
				else {
					if (!DossierStatusTerm.CANCELLED.equals(dossierData.getDossierStatus())) {
						// set ho so hoan thanh
						statisticData.setReleaseInAPeriodCount(statisticData.getReleaseInAPeriodCount() + 1);
					}
				}	
			}
		}
	}

	private static final int LEVEL_3 = 3;
	private static final int LEVEL_4 = 4;
	
	public void updateSumDossierStatisticData(DossierStatisticData statisticData, GetDossierData dossierData,
			Date fromStatisticDate, Date toStatisticDate, int reporting) {
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
		//tong so tiep nhan dau ky
		if (receviedDate != null && (releaseDate == null || releaseDate.after(fromStatisticDate))) {
			statisticData.setTotalCount(statisticData.getTotalCount() + 1);
		}
		int viaPostalCount = dossierData.getViaPostal();
		if (viaPostalCount == USED_POSTAL) {
			statisticData.setViaPostalCount(statisticData.getViaPostalCount() + 1);
		}
		if (!dossierData.getOnline() && dossierData.getFromViaPostal() > 0) {
			statisticData.setFromViaPostalCount(statisticData.getFromViaPostalCount() + 1);
		}
		Calendar receivedStatistic = Calendar.getInstance();
		receivedStatistic.setTime(receviedDate);
		boolean isReceivedSaturday = (receivedStatistic.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
		Calendar finishStatistic = Calendar.getInstance();
		finishStatistic.setTime(receviedDate);
		boolean isFinishSaturday = (receivedStatistic.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
		if (isReceivedSaturday && isFinishSaturday) {
			statisticData.setSaturdayCount(statisticData.getSaturdayCount() + 1);
		}
		if (isReceivedSaturday) {
			statisticData.setReceiveDossierSatCount(statisticData.getReceiveDossierSatCount() + 1);
		}
		if (isFinishSaturday) {
			statisticData.setReleaseDossierSatCount(statisticData.getReleaseDossierSatCount() + 1);
		}
		int serviceLevel = dossierData.getServiceLevel();
		if (LEVEL_3 == serviceLevel) {
			statisticData.setDossierOnline3Count(statisticData.getDossierOnline3Count() + 1);
		}
		if (LEVEL_4 == serviceLevel) {
			statisticData.setDossierOnline4Count(statisticData.getDossierOnline4Count() + 1);
		}

		if (dossierData.getDossierStatus().contentEquals(DossierStatusTerm.DENIED)) {
			statisticData.setDeniedCount(statisticData.getDeniedCount() + 1);				
		} else {
			// tiep nhan xu ly
			statisticData.setProcessCount(statisticData.getProcessCount() + 1);

			//if (receviedDate != null && receviedDate.after(getFirstDay(month, year))) {
			if (receviedDate != null && receviedDate.after(fromStatisticDate) 
					&& receviedDate.before(toStatisticDate)) {
				// ho so tiep nhan trong ky:
				// ngay nhan thuoc from - to
				statisticData.setReceivedCount(statisticData.getReceivedCount() + 1);
				if (dossierData.getOnline()) {
					statisticData.setOnlineCount(statisticData.getOnlineCount() + 1);
				} else {
					statisticData.setOnegateCount(statisticData.getOnegateCount() + 1);
				}			
			} else if (receviedDate != null && receviedDate.before(fromStatisticDate)
					&& (releaseDate == null ||releaseDate.after(fromStatisticDate))) {
				// ho so ton ky truoc:
				// ngay nhan truoc ngay from, ngay release sau ngay from hoac ko co ngay release
				statisticData.setRemainingCount(statisticData.getRemainingCount() + 1);
			}
			
			if (releaseDate == null || releaseDate.after(toStatisticDate)) {
				// hồ sơ đang xử lý 
				if (dossierData.getDossierStatus().contentEquals(DossierStatusTerm.WAITING) || 
						dossierData.getDossierStatus().contentEquals(DossierStatusTerm.RECEIVING)) {
					// dang tạm dừng chờ bổ sung
					statisticData.setWaitingCount(statisticData.getWaitingCount() + 1);
				} else {
					// đang xử lý
					statisticData.setProcessingCount(statisticData.getProcessingCount() + 1);
					if (dossierData.getOnline()) {
						statisticData.setOnlineProcessingCount(statisticData.getOnlineProcessingCount() + 1);
					} else {
						statisticData.setOnegateProcessingCount(statisticData.getOnegateProcessingCount() + 1);
					}
					if (!DossierStatusTerm.PROCESSING.equals(dossierData.getDossierStatus())) {
						// xử lý nội bộ
						statisticData.setOutsideCount(statisticData.getOutsideCount() + 1);
					} else {
						// xử lý ngoài cơ quan
						statisticData.setInsideCount(statisticData.getInsideCount() + 1);
					}

					Date now = new Date();
					// add by phuchn - caculate duedate by day
					if (CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE) {
						dueDate = Validator.isNull(dossierData.getDueDate())
								? null : StatisticUtils.convertStringToDate(dossierData.getDueDate(), DossierStatusTerm.DATE_FORMAT);
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						now = StatisticUtils.convertStringToDate(dateFormat.format(new Date()), DossierStatusTerm.DATE_FORMAT);
					}
					if (dueDate != null && !dueDate.after(now.before(toStatisticDate) ? now : toStatisticDate)) {
						// đang quá hạn
						statisticData.setOverdueCount(statisticData.getOverdueCount() + 1);
						if (!DossierStatusTerm.PROCESSING.equals(dossierData.getDossierStatus())) {
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
				if (dossierData.getDossierStatus().contentEquals(DossierStatusTerm.CANCELLED)) {
					// ho so da bi rut trong thang
					statisticData.setCancelledCount(statisticData.getCancelledCount() + 1);
				} else {
					// hồ sơ đã hoàn thành trong tháng	
					if (fromStatisticDate.before(releaseDate) && toStatisticDate.after(releaseDate))
						statisticData.setReleaseCount(statisticData.getReleaseCount() + 1);						
					if (dossierData.getDossierStatus().contentEquals(DossierStatusTerm.UNRESOLVED)) {
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
						
						//int betimeCal = dueDate != null ? BetimeUtils.getValueCompareRelease(dossierData.getGroupId(), releaseDate, dueDate) : 3;
						// add by phuchn - caculate duedate by day
						if (CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE) {
							dueDate = Validator.isNull(dossierData.getDueDate())
									? null : StatisticUtils.convertStringToDate(dossierData.getDueDate(), DossierStatusTerm.DATE_FORMAT);
							releaseDate = Validator.isNull(dossierData.getReleaseDate())
									? null
									: StatisticUtils.convertStringToDate(dossierData.getReleaseDate(), DossierStatusTerm.DATE_FORMAT);
							finishDate = Validator.isNull(dossierData.getFinishDate())
									? null
									: StatisticUtils.convertStringToDate(dossierData.getFinishDate(), DossierStatusTerm.DATE_FORMAT);
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
//							_log.info("dossierData_No: "+dossierData.getDossierNo());
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
						/*if (betimeCal == 3) {
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
						}*/
						
						// truc tuyen
						if (dossierData.getOnline()) {
							if (overdue==0) {
								statisticData.setOnlineBetimesCount(statisticData.getOnlineBetimesCount() + 1);
								statisticData.setOnlineReleaseBetimesCount(statisticData.getOnlineReleaseBetimesCount() + 1);
							} else if (overdue==2) {
								statisticData.setOnlineOvertimeCount(statisticData.getOnlineOntimeCount() + 1);
								statisticData.setOnlineReleaseOvertimeCount(statisticData.getOnlineReleaseOvertimeCount() + 1);
							} else {
								statisticData.setOnlineOntimeCount(statisticData.getOnlineOntimeCount() + 1);
								statisticData.setOnlineReleaseOntimeCount(statisticData.getOnlineReleaseOntimeCount() + 1);
							}
						} // truc tiep
						else {
							if (overdue==0) {
								statisticData.setOnegateBetimesCount(statisticData.getOnegateBetimesCount() + 1);
								statisticData.setOnegateReleaseBetimesCount(statisticData.getOnegateReleaseBetimesCount() + 1);
							} else if (overdue==2) {
								statisticData.setOnegateOvertimeCount(statisticData.getOnegateOvertimeCount() + 1);
								statisticData.setOnegateReleaseOvertimeCount(statisticData.getOnegateReleaseOvertimeCount() + 1);
							} else {
								statisticData.setOnegateOntimeCount(statisticData.getOnegateOntimeCount() + 1);
								statisticData.setOnegateReleaseOntimeCount(statisticData.getOnegateReleaseOntimeCount() + 1);
							}
						}
					}
				}
			}
			// tong so ho so dang xu ly, hoan thanh doi voi cac ho so tiep nhan trong ky
			if (receviedDate != null && receviedDate.after(fromStatisticDate)
					&& receviedDate.before(toStatisticDate)) {
				// tong so ho so dang xu ly
				if (releaseDate == null || releaseDate.after(toStatisticDate)) {
					if (!DossierStatusTerm.WAITING.equals(dossierData.getDossierStatus()) && 
							!DossierStatusTerm.RECEIVING.equals(dossierData.getDossierStatus())) {
						// set ho so dang xu ly
						statisticData.setProcessingInAPeriodCount(statisticData.getProcessingInAPeriodCount() + 1);
					}
				} // tong so ho so hoan thanh
				else {
					if (!DossierStatusTerm.CANCELLED.equals(dossierData.getDossierStatus())) {
						// set ho so hoan thanh
						statisticData.setReleaseInAPeriodCount(statisticData.getReleaseInAPeriodCount() + 1);
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

class DossierStatusTerm {
	
	public static final String DENIED = "denied";
	public static final String WAITING = "waiting";
	public static final String RECEIVING = "receiving";
	public static final String PROCESSING = "processing";
	public static final String CANCELLED = "cancelled";
	public static final String UNRESOLVED = "unresolved";
	public static final String DATE_FORMAT = "dd/MM/yyyy";

}
