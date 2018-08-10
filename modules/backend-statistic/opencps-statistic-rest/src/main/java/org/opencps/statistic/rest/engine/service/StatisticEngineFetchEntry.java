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
import org.opencps.statistic.rest.util.DossierStatusContants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.Validator;

public class StatisticEngineFetchEntry {

	private final static Logger LOG = LoggerFactory.getLogger(StatisticEngineFetchEntry.class);
	
	
	/**
	 * Update to StatisticData
	 * 
	 * @param statisticData
	 * @param dossierData
	 */
	public void updateDossierStatisticData(DossierStatisticData statisticData, GetDossierData dossierData) {
		// check release date is null or
		
		
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		
		statisticData.setMonth(month);
		statisticData.setYear(year);
		statisticData.setGroupId(dossierData.getGroupId());

		Date dueDate = Validator.isNull(dossierData.getDueDate()) ? null
				: StatisticUtils.convertStringToDate(dossierData.getDueDate());
		Date extendDate = Validator.isNull(dossierData.getExtendDate()) ? null
				: StatisticUtils.convertStringToDate(dossierData.getExtendDate());
		Date releaseDate = Validator.isNull(dossierData.getReleaseDate()) ? null
				: StatisticUtils.convertStringToDate(dossierData.getReleaseDate());
		Date receviedDate = Validator.isNull(dossierData.getReceiveDate()) ? null
				: StatisticUtils.convertStringToDate(dossierData.getReceiveDate());

		statisticData.setTotalCount(statisticData.getTotalCount() + 1);

		/* DENIED */
		if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DENIED)) {
			statisticData.setDeniedCount(statisticData.getDeniedCount() + 1);

		} else if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_CANCELLED)) {
			statisticData.setCancelledCount(statisticData.getCancelledCount() + 1);

		} else if (Validator.isNull(receviedDate)) {
			statisticData.setReceivedCount(statisticData.getReceivedCount() + 1);
		} else {

			/* PROCESS */
			statisticData.setProcessCount(statisticData.getProcessCount() + 1);

			/* REVEICED */
			if (Validator.isNotNull(dossierData.getReceiveDate()) && receviedDate.after(getFirstDay())) {

				statisticData.setReceivedCount(statisticData.getReceivedCount());

				/* ONLINE */
				if (dossierData.getOnline()) {
					statisticData.setOnlineCount(statisticData.getOnlineCount());
				} else {

					statisticData.setOnegateCount(statisticData.getOnegateCount() + 1);
				}
			} else {
				/* REMAINING */
				statisticData.setRemainingCount(statisticData.getRemainingCount() + 1);

			}

			/* RELEASED */
			if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DONE)
					|| dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_RELEASING)
					|| dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_POSTING)
					|| dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_UNRESOLVED)) {

				statisticData.setReleaseCount(statisticData.getReleaseCount());

				/* DONE */
				if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DONE)
						|| dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_POSTING)) {

					statisticData.setDoneCount(statisticData.getDoneCount() + 1);
				}

				/* RELEASING */
				if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_RELEASING)) {
					statisticData.setReleasingCount(statisticData.getReleasingCount() + 1);
				}

				/* UNRESOLVED */
				if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_UNRESOLVED)) {
					statisticData.setUnresolvedCount(statisticData.getUnresolvedCount() + 1);
				}

				/* BETIME */
				if (Validator.isNotNull(dueDate) && Validator.isNotNull(extendDate) && extendDate.before(dueDate)) {

					statisticData.setBetimesCount(statisticData.getBetimesCount() + 1);

				} else {
					/* ONTIMECOUNT */
					if (Validator.isNull(dossierData.getDueDate())
							|| (Validator.isNotNull(dossierData.getReleaseDate()) && releaseDate.before(dueDate))) {
						// tinhs overtime

						statisticData.setOntimeCount(statisticData.getOntimeCount() + 1);

					} else {

						statisticData.setOvertimeCount(statisticData.getOvertimeCount() + 1);

						/* TODO :) Fixed */

						boolean isOvertimeInside = true;

						if (isOvertimeInside) {
							statisticData.setOvertimeInside(statisticData.getOvertimeInside() + 1);
						} else {
							statisticData.setOvertimeOutside(statisticData.getOvertimeOutside());
						}
					}
				}

			} else if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_WAITING)) {
				statisticData.setWaitingCount(statisticData.getWaitingCount() + 1);
			} else {
				/* PROCESSING */

				statisticData.setProcessingCount(statisticData.getProcessingCount() + 1);

				/* OUTSIDE COUNT */
				if (!dossierData.getDossierStatus().equals(DossierStatusContants.DOSSIER_STATUS_PROCESING)) {
					statisticData.setOutsideCount(statisticData.getOutsideCount() + 1);
				} else {
				}

				/* UNDUE */
				if (Validator.isNull(dueDate) || dueDate.after(new Date())) {
					statisticData.setUndueCount(statisticData.getUndueCount() + 1);
				} else {
					// OVERDUE
					statisticData.setOverdueCount(statisticData.getOverdueCount() + 1);
					/* INTEROPERATING */
					if (dossierData.getDossierStatus()
							.contentEquals(DossierStatusContants.DOSSIER_STATUS_INTEROPERATING)) {
						statisticData.setInteroperatingCount(statisticData.getInteroperatingCount() + 1);
					}
				}
			}

		}

	}

	private static Date getFirstDay() {
		LocalDateTime localDateTime = LocalDateTime.now();

		localDateTime.with(TemporalAdjusters.firstDayOfMonth());
		localDateTime.with(LocalTime.MIN);

		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

		return Date.from(instant);
	}

	private static Date getLastDay() {
		LocalDateTime localDateTime = LocalDateTime.now();

		localDateTime.with(TemporalAdjusters.lastDayOfMonth());
		localDateTime.with(LocalTime.MAX);

		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

		return Date.from(instant);
	}

}
