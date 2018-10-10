package org.opencps.statistic.rest.engine.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetDossierData;

import com.liferay.portal.kernel.util.Validator;

public class StatisticEngineFetchEntry {
//	private static final Logger _log = LoggerFactory.getLogger(StatisticEngineFetchEntry.class);

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
		statisticData.setTotalCount(statisticData.getTotalCount() + 1);
		if (dossierData.getDossierStatus().contentEquals("denied")) {
			statisticData.setDeniedCount(statisticData.getDeniedCount() + 1);
		} else if (dossierData.getDossierStatus().contentEquals("cancelled")) {
			statisticData.setCancelledCount(statisticData.getCancelledCount() + 1);
		} else {
			statisticData.setProcessCount(statisticData.getProcessCount() + 1);
			if (receviedDate != null && receviedDate.after(getFirstDay())) {
				statisticData.setReceivedCount(statisticData.getReceivedCount() + 1);
				if (dossierData.getOnline()) {
					statisticData.setOnlineCount(statisticData.getOnlineCount() + 1);
				} else {
					statisticData.setOnegateCount(statisticData.getOnegateCount() + 1);
				}
			} else {
				statisticData.setRemainingCount(statisticData.getRemainingCount() + 1);
			}

			if (!dossierData.getDossierStatus().contentEquals("done")
					&& !dossierData.getDossierStatus().contentEquals("releasing")
					&& !dossierData.getDossierStatus().contentEquals("posting")
					&& !dossierData.getDossierStatus().contentEquals("unresolved")) {
				if (dossierData.getDossierStatus().contentEquals("waiting")) {
					statisticData.setWaitingCount(statisticData.getWaitingCount() + 1);
				} else {
					statisticData.setProcessingCount(statisticData.getProcessingCount() + 1);
					if (!"procesing".equals(dossierData.getDossierStatus())) {
						statisticData.setOutsideCount(statisticData.getOutsideCount() + 1);
					} else {
						statisticData.setInsideCount(statisticData.getInsideCount() + 1);
					}

					if (dueDate != null && !dueDate.after(new Date())) {
						statisticData.setOverdueCount(statisticData.getOverdueCount() + 1);
						if (dossierData.getDossierStatus().contentEquals("interoperating")) {
							statisticData.setInteroperatingCount(statisticData.getInteroperatingCount() + 1);
						}
					} else {
						statisticData.setUndueCount(statisticData.getUndueCount() + 1);
					}
				}
			} else {
				statisticData.setReleaseCount(statisticData.getReleaseCount() + 1);
				if (dossierData.getDossierStatus().contentEquals("done")
						|| dossierData.getDossierStatus().contentEquals("posting")) {
					statisticData.setDoneCount(statisticData.getDoneCount() + 1);
				}

				if (dossierData.getDossierStatus().contentEquals("releasing")) {
					statisticData.setReleasingCount(statisticData.getReleasingCount() + 1);
				}

				if (dossierData.getDossierStatus().contentEquals("unresolved")) {
					statisticData.setUnresolvedCount(statisticData.getUnresolvedCount() + 1);
				}

				if (dueDate != null && extendDate != null && extendDate.before(dueDate)) {
					statisticData.setBetimesCount(statisticData.getBetimesCount() + 1);
				} else if (!Validator.isNull(dossierData.getDueDate())
						&& (!Validator.isNotNull(dossierData.getReleaseDate())
								|| (releaseDate != null && !releaseDate.before(dueDate)))) {
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

	private static Date getFirstDay() {
		LocalDateTime localDateTime = LocalDateTime.now();
		localDateTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
		localDateTime = localDateTime.with(LocalTime.MIN);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

//	private static Date getLastDay() {
//		LocalDateTime localDateTime = LocalDateTime.now();
//		localDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
//		localDateTime = localDateTime.with(LocalTime.MAX);
//		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
//		return Date.from(instant);
//	}
}
