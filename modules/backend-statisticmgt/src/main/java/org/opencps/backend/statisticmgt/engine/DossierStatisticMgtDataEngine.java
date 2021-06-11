package org.opencps.backend.statisticmgt.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.dto.DossierStatisticMgtData;
import org.opencps.backend.statisticmgt.util.ActionUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.statistic.service.OpencpsDossierStatisticMgtLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = DossierStatisticMgtDataEngine.class)
public class DossierStatisticMgtDataEngine extends BaseMessageListener {

	private volatile boolean isRunningDossier = false;
	private static boolean RECACULATOR_STATISTIC_LAST_YEAR = Validator
			.isNotNull(PropsUtil.get("org.opencps.statistic.recaculator"))
					? Boolean.valueOf(PropsUtil.get("org.opencps.statistic.recaculator"))
					: false;

	private static final Boolean CALCULATE_DOSSIER_STATISTIC_ENABLE = Validator.isNotNull(PropsUtil.get("org.opencps.statistic.enable"))
			? Boolean.valueOf(PropsUtil.get("org.opencps.statistic.enable")) : false;
	
	private static int TIME_STATISTIC = Validator.isNotNull(PropsUtil.get("opencps.statistic.dossier.time"))
			? Integer.valueOf(PropsUtil.get("opencps.statistic.dossier.time")) :45;
	
	//Start time config
	private static int HOUR_STATISTIC = Validator.isNotNull(PropsUtil.get("opencps.statistic.dossier.hour"))
			? Integer.valueOf(PropsUtil.get("opencps.statistic.dossier.hour")) :-1;
	private static int MINUTE_STATISTIC = Validator.isNotNull(PropsUtil.get("opencps.statistic.dossier.minute"))
			? Integer.valueOf(PropsUtil.get("opencps.statistic.dossier.minute")) :-1;
	private static int SECOND_STATISTIC = Validator.isNotNull(PropsUtil.get("opencps.statistic.dossier.second"))
			? Integer.valueOf(PropsUtil.get("opencps.statistic.dossier.second")) :-1;
	
	private static long GROUPID_DVC = Validator.isNotNull(PropsUtil.get("opencps.statistic.groupId.dvc"))
			? Integer.valueOf(PropsUtil.get("opencps.statistic.groupId.dvc")) :-1;
	private static int GROUP_TYPE_SITE = 1;

	protected Log _log = LogFactoryUtil.getLog(DossierStatisticMgtDataEngine.class);

	@Override
	protected void doReceive(Message message) throws Exception {

		_log.debug("START STATISTIC MGT DOSSIER: " + isRunningDossier);
		if (!isRunningDossier && CALCULATE_DOSSIER_STATISTIC_ENABLE && GROUPID_DVC != -1) {
			isRunningDossier = true;
		} else {
			return;
		}
		long startTime = System.currentTimeMillis();
		Date nowLog = new Date();
		try {

			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

			List<Group> groups = GroupLocalServiceUtil.getCompanyGroups(company.getCompanyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			List<Group> sites = new ArrayList<Group>();
			
			// Danh sách du lieu thong ke theo cac thang
			Map<Integer, Map<Integer, Map<String, DossierStatisticMgtData>>> calculateDataDomainsByMonth = new HashMap<>();
			Map<Integer, Map<Integer, Map<String, DossierStatisticMgtData>>> calculateDataGovsByMonth = new HashMap<>();

			// Danh sach du lieu thong ke theo nam
			Map<Integer, Map<Integer, Map<String, DossierStatisticMgtData>>> calculateDataDomainsByYear = new HashMap<>();
			Map<Integer, Map<Integer, Map<String, DossierStatisticMgtData>>> calculateDataGovsByYear = new HashMap<>();

			for (Group group : groups) {
				if (group.getType() == GROUP_TYPE_SITE && group.isSite() && group.getGroupId() != GROUPID_DVC) {
					sites.add(group);
				}
			}
			_log.debug("List size: " + JSONFactoryUtil.looseSerialize(sites));
			

			for (Group site : sites) {

				int monthCurrent = LocalDate.now().getMonthValue();
				int yearCurrent = LocalDate.now().getYear();
				Map<Integer, Boolean> mapFlagCurrent = new HashMap<>();
				
				/** START : Tổng hợp thống kê dữ liệu theo tháng và update vào db **/
				// Du lieu thong ke theo thang : linh vuc, don vi nam hien tai
				Map<Integer, Map<String, DossierStatisticMgtData>> calculateDataDomainByMonth = new HashMap<>();
				Map<Integer, Map<String, DossierStatisticMgtData>> calculateDataGovByMonth = new HashMap<>();
				
				for (int month = 1; month <= monthCurrent; month++) {
					boolean flagStatistic = true;
					if (month <= monthCurrent) {
						if (flagStatistic) {
							try {								
								// thong ke ho so thang 
								processDossierDataByMonth(site.getGroupId(), month, yearCurrent, calculateDataDomainByMonth, calculateDataGovByMonth);								
								calculateDataDomainsByMonth.put(yearCurrent, calculateDataDomainByMonth);
								calculateDataGovsByMonth.put(yearCurrent, calculateDataGovByMonth);
							} catch (Exception e) {
								_log.debug(e);
							}
						}
						mapFlagCurrent.put(month, flagStatistic);
					}
				}

				int lastYear = LocalDate.now().getYear() - 1;
				boolean flagLastYear = false;
				Map<Integer, Boolean> mapFlagPrev = new HashMap<>();
				
				// Du lieu thong ke theo thang: nam truoc
				Map<Integer, Map<String, DossierStatisticMgtData>> calculateLastDataDomainByMonth = new HashMap<>();
				Map<Integer, Map<String, DossierStatisticMgtData>> calculateLastDataGovByMonth = new HashMap<>();
				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
					if (RECACULATOR_STATISTIC_LAST_YEAR) {
						flagLastYear = true;
					}
					if (flagLastYear) {
						try {
							// Du lieu tong hop theo thang
							processDossierDataByMonth(site.getGroupId(), lastMonth, lastYear, calculateLastDataDomainByMonth, calculateLastDataGovByMonth);
							calculateDataDomainsByMonth.put(lastYear, calculateLastDataDomainByMonth);
							calculateDataGovsByMonth.put(lastYear, calculateLastDataGovByMonth);							
						} catch (Exception e) {
							_log.debug(e);
						}
					}
					mapFlagPrev.put(lastMonth, flagLastYear);
				}

				// Tong hop cho nam hien tai
				List<JSONObject> lstDossierDataObjs = new ArrayList<JSONObject>();
				for (int month = 1; month <= monthCurrent; month++) {
					if (mapFlagCurrent.get(month)) {
						if (calculateDataDomainsByMonth.get(yearCurrent) != null
								&& calculateDataDomainsByMonth.get(yearCurrent).get(month) != null) {
							lstDossierDataObjs.addAll(convertStatisticDataList(calculateDataDomainByMonth.get(month)));
						}
					}
				}
				for (int month = 1; month <= monthCurrent; month++) {
					if (mapFlagCurrent.get(month)) {
						if (calculateDataGovsByMonth.get(yearCurrent) != null
								&& calculateDataGovsByMonth.get(yearCurrent).get(month) != null) {
							lstDossierDataObjs.addAll(convertStatisticDataList(calculateDataGovByMonth.get(month)));
						}
					}
				}				
				
				// Tong hop cho nam cu
				if(RECACULATOR_STATISTIC_LAST_YEAR) {
					for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
						if (mapFlagPrev.get(lastMonth)) {
							if (calculateDataDomainsByMonth.get(lastYear) != null
									&& calculateDataDomainsByMonth.get(lastYear).get(lastMonth) != null) {
								lstDossierDataObjs
										.addAll(convertStatisticDataList(calculateDataDomainsByMonth.get(lastYear).get(lastMonth)));
							}
						}
					}
					for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
						if (mapFlagPrev.get(lastMonth)) {
							if (calculateDataGovsByMonth.get(lastYear) != null
									&& calculateDataGovsByMonth.get(lastYear).get(lastMonth) != null) {
								lstDossierDataObjs
										.addAll(convertStatisticDataList(calculateDataGovsByMonth.get(lastYear).get(lastMonth)));
							}
						}
					}
				}
				
				// update du lieu cac thang vao db
				if(Validator.isNotNull(lstDossierDataObjs) && lstDossierDataObjs.size() > 0) {
					_log.debug("Statistic data month :" + JSONFactoryUtil.looseSerialize(lstDossierDataObjs));
					updateStatistic(lstDossierDataObjs);
				}
				
				/** END : Tổng hợp thống kê dữ liệu theo tháng và update vào db **/
				
				/*****************************************************************/
				
				/** START : Tổng hợp dữ liệu thống kê theo nam va update vào db **/	
				
				Map<Integer, Map<String, DossierStatisticMgtData>> calculateDataDomainByYear = new HashMap<>();
				Map<Integer, Map<String, DossierStatisticMgtData>> calculateDataGovByYear = new HashMap<>();
				Map<Integer, Map<String, DossierStatisticMgtData>> calculateLastDataDomainByYear = new HashMap<>();
				Map<Integer, Map<String, DossierStatisticMgtData>> calculateLastDataGovByYear = new HashMap<>();
				
				// tong hop du lieu ho so nam
				
				// Du lieu nam hien tai
				processDossierDataByYear(site.getGroupId(), yearCurrent, calculateDataDomainByYear, calculateDataGovByYear);								
				calculateDataDomainsByYear.put(yearCurrent, calculateDataDomainByYear);
				calculateDataGovsByYear.put(yearCurrent, calculateDataGovByYear);
				
				// Du lieu nam truoc
				processDossierDataByYear(site.getGroupId(), lastYear, calculateLastDataDomainByYear, calculateLastDataGovByYear);
				calculateDataDomainsByYear.put(lastYear, calculateLastDataDomainByYear);
				calculateDataGovsByYear.put(lastYear, calculateLastDataGovByYear);		
				
				List<JSONObject> lstDossierDataObjYears = new ArrayList<JSONObject>();
				
				// Tong hop cho nam hien tai				
				if (calculateDataDomainsByYear.get(yearCurrent) != null
						&& calculateDataDomainsByYear.get(yearCurrent).get(0) != null) {
					lstDossierDataObjYears.addAll(convertStatisticDataList(calculateDataDomainsByYear.get(yearCurrent).get(0)));
				}
				
				if (calculateDataGovsByYear.get(yearCurrent) != null
						&& calculateDataGovsByYear.get(yearCurrent).get(0) != null) {
					lstDossierDataObjYears.addAll(convertStatisticDataList(calculateDataGovsByYear.get(yearCurrent).get(0)));
				}
				
				// Tong hop cho nam cu				
				if (calculateDataDomainsByYear.get(lastYear) != null
						&& calculateDataDomainsByYear.get(lastYear).get(0) != null) {
					lstDossierDataObjYears.addAll(convertStatisticDataList(calculateDataDomainsByYear.get(lastYear).get(0)));
				}
				
				if (calculateDataGovsByYear.get(lastYear) != null
						&& calculateDataGovsByYear.get(lastYear).get(0) != null) {
					lstDossierDataObjYears.addAll(convertStatisticDataList(calculateDataGovsByYear.get(lastYear).get(0)));
				}

				// tinh ban ghi nam
				if(Validator.isNotNull(lstDossierDataObjYears) && lstDossierDataObjYears.size() > 0) {
					_log.debug("Statistic Data year :" + JSONFactoryUtil.looseSerialize(lstDossierDataObjYears));
					updateStatistic(lstDossierDataObjYears);
				}
				
				/** END : Tổng hợp dữ liệu thống kê theo nam va update vào db **/	
			}

		} catch (Exception e) {
			_log.error(e);
		}
		_log.info("END TRACE LOG STATISTICS MGT TIME: " + nowLog);
		_log.info("STATISTICS MGT END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;

		isRunningDossier = false;

	}

	private void processDossierDataByMonth(long groupId, int month, int year,
			Map<Integer, Map<String, DossierStatisticMgtData>> calculateDataDomain,
			Map<Integer, Map<String, DossierStatisticMgtData>> calculateDataGov) {

		Date firstDay = getFirstDay(month, year);
		Date lastDay = getLastDay(month, year);
		long fromDate = firstDay.getTime();
		long toDate = lastDay.getTime();
		Map<String, DossierStatisticMgtData> statisticDataByDomainCode = new HashMap<String, DossierStatisticMgtData>();
		Map<String, DossierStatisticMgtData> statisticDataByGovAgencyCode = new HashMap<String, DossierStatisticMgtData>();

		JSONObject jsonDataByDomainCode = ActionUtil.getDossierStatistic(groupId, -1, fromDate, toDate, null, null, null,
				null, null, null, "domainCode", null, null, 20, Constants.GROUP_COUNT);
		_log.debug("Du lieu thong ke thang theo linh vuc :" + JSONFactoryUtil.looseSerialize(jsonDataByDomainCode));
		if (jsonDataByDomainCode != null && jsonDataByDomainCode.getInt(Constants.TOTAL) > 0) {
			fetchStatisticData(groupId, statisticDataByDomainCode, jsonDataByDomainCode, month, year, false);
			calculateDataDomain.put(month, statisticDataByDomainCode);
		}

		JSONObject jsonDataByGovAgencyCode = ActionUtil.getDossierStatistic(groupId, -1, fromDate, toDate, null, null, null,
				null, null, null, "govAgencyCode", null, null, 20, Constants.GROUP_COUNT);
		_log.debug("Du lieu thong ke thang theo don vi :" + JSONFactoryUtil.looseSerialize(jsonDataByGovAgencyCode));
		if (jsonDataByGovAgencyCode != null && jsonDataByGovAgencyCode.getInt(Constants.TOTAL) > 0) {
			fetchStatisticData(groupId, statisticDataByGovAgencyCode, jsonDataByGovAgencyCode, month, year, true);
			calculateDataGov.put(month, statisticDataByGovAgencyCode);
		}

	}
	
	private void processDossierDataByYear(long groupId, int year,
			Map<Integer, Map<String, DossierStatisticMgtData>> calculateDataDomain,
			Map<Integer, Map<String, DossierStatisticMgtData>> calculateDataGov) {

		Date firstDay = getFirstDay(1, year);
		Date lastDay = getLastDay(12, year);
		long fromDate = firstDay.getTime();
		long toDate = lastDay.getTime();
		Map<String, DossierStatisticMgtData> statisticDataByDomainCode = new HashMap<String, DossierStatisticMgtData>();
		Map<String, DossierStatisticMgtData> statisticDataByGovAgencyCode = new HashMap<String, DossierStatisticMgtData>();

		JSONObject jsonDataByDomainCode = ActionUtil.getDossierStatistic(groupId, -1, fromDate, toDate, null, null, null,
				null, null, null, "domainCode", null, null, 20, Constants.GROUP_COUNT);
		_log.debug("Du lieu thong ke nam theo linh vuc :" + JSONFactoryUtil.looseSerialize(jsonDataByDomainCode));
		if (jsonDataByDomainCode != null && jsonDataByDomainCode.getInt(Constants.TOTAL) > 0) {
			fetchStatisticData(groupId, statisticDataByDomainCode, jsonDataByDomainCode, 0, year, false);
			calculateDataDomain.put(0, statisticDataByDomainCode);
		}

		JSONObject jsonDataByGovAgencyCode = ActionUtil.getDossierStatistic(groupId, -1, fromDate, toDate, null, null, null,
				null, null, null, "govAgencyCode", null, null, 20, Constants.GROUP_COUNT);
		_log.debug("Du lieu thong ke nam theo don vi :" + JSONFactoryUtil.looseSerialize(jsonDataByGovAgencyCode));
		if (jsonDataByGovAgencyCode != null && jsonDataByGovAgencyCode.getInt(Constants.TOTAL) > 0) {
			fetchStatisticData(groupId, statisticDataByGovAgencyCode, jsonDataByGovAgencyCode, 0, year, true);
			calculateDataGov.put(0, statisticDataByGovAgencyCode);
		}

	}

	private static Date getFirstDay(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);
		// Set calendar first of month
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
		// Set calendar first of month
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	private void fetchStatisticData(long groupId, Map<String, DossierStatisticMgtData> statisticData,
			JSONObject jsonObjectData, int month, int year, boolean isCaculateByGov) {

		JSONArray data = jsonObjectData.getJSONArray(Constants.DATA);
		if (isCaculateByGov) {
			if (data != null && data.length() > 0) {
				for (int i = 0; i < data.length(); i++) {
					JSONObject jsonObject = data.getJSONObject(i);

					String govAgencyCode = jsonObject.getString("govAgencyCode");
					String govAgencyName = jsonObject.getString("govAgencyName");
					String domainCode = jsonObject.getString("domainCode");
					String domainName = jsonObject.getString("domainName");
					String serviceCode = jsonObject.getString("serviceCode");
					String serviceName = jsonObject.getString("serviceName");

					int betimesCount = jsonObject.getInt("betimesCount");
					int doneCount = jsonObject.getInt("doneCount");
					int onegateCount = jsonObject.getInt("onegateCount");
					int onlineCount = jsonObject.getInt("onlineCount");
					int ontimeCount = jsonObject.getInt("ontimeCount");
					int ontimePercentage = jsonObject.getInt("ontimePercentage");
					int overdueCount = jsonObject.getInt("overdueCount");
					int overtimeCount = jsonObject.getInt("overtimeCount");
					// DuanTV: process (tong xu ly) = total
					int processCount = jsonObject.getInt("totalCount");
					int processingCount = jsonObject.getInt("processingCount");
					int receivedCount = jsonObject.getInt("receivedCount");
					int releaseCount = jsonObject.getInt("releaseCount");
					int releasingCount = jsonObject.getInt("releasingCount");
					int remainingCount = jsonObject.getInt("remainingCount");
					int totalCount = jsonObject.getInt("totalCount");
					int undueCount = jsonObject.getInt("undueCount");
					int waitingCount = jsonObject.getInt("waitingCount");
					
					// DuanTV :Thêm vào trước, hiện để mặc định là 0, khi nào cần thì xử lý lại
					int unresolvedCount = 0;
					int cancelledCount = 0;

					String type1 = groupId + "@all";
					DossierStatisticMgtData dataType1 = new DossierStatisticMgtData();

					if (statisticData.containsKey(type1)) {
						dataType1 = statisticData.get(type1);
					}

					String type2 = groupId + "@" + govAgencyCode;
					DossierStatisticMgtData dataType2 = new DossierStatisticMgtData();

					if (statisticData.containsKey(type2)) {
						dataType2 = statisticData.get(type2);
					}

					if (Validator.isNull(govAgencyCode)) {

						dataType1.setGroupId(groupId);
						dataType1.setGovAgencyCode(StringPool.BLANK);
						dataType1.setGovAgencyName(StringPool.BLANK);
						dataType1.setBetimesCount(betimesCount);
						dataType1.setDomainCode(domainCode);
						dataType1.setDomainName(domainName);
						dataType1.setDoneCount(doneCount);
						dataType1.setMonth(month);
						dataType1.setYear(year);
						dataType1.setOnegateCount(onegateCount);
						dataType1.setOnlineCount(onlineCount);
						dataType1.setOntimeCount(ontimeCount);
						dataType1.setOntimePercentage(ontimePercentage);
						dataType1.setOverdueCount(overdueCount);
						dataType1.setOvertimeCount(overtimeCount);
						dataType1.setProcessCount(processCount);
						dataType1.setProcessingCount(processingCount);
						dataType1.setReceivedCount(receivedCount);
						dataType1.setReleaseCount(releaseCount);
						dataType1.setReleasingCount(releasingCount);
						dataType1.setRemainingCount(remainingCount);
						dataType1.setServiceCode(serviceCode);
						dataType1.setServiceName(serviceName);
						dataType1.setTotalCount(totalCount);
						dataType1.setUndueCount(undueCount);
						dataType1.setWaitingCount(waitingCount);
						dataType1.setGroupBy(1);
						dataType1.setUnresolvedCount(unresolvedCount);
						dataType1.setCancelledCount(cancelledCount);

						statisticData.put(type1, dataType1);
					} else {

						dataType2.setGroupId(groupId);
						dataType2.setGovAgencyCode(govAgencyCode);
						dataType2.setGovAgencyName(govAgencyName);
						dataType2.setBetimesCount(betimesCount);
						dataType2.setDomainCode(domainCode);
						dataType2.setDomainName(domainName);
						dataType2.setDoneCount(doneCount);
						dataType2.setMonth(month);
						dataType2.setYear(year);
						dataType2.setOnegateCount(onegateCount);
						dataType2.setOnlineCount(onlineCount);
						dataType2.setOntimeCount(ontimeCount);
						dataType2.setOntimePercentage(ontimePercentage);
						dataType2.setOverdueCount(overdueCount);
						dataType2.setOvertimeCount(overtimeCount);
						dataType2.setProcessCount(processCount);
						dataType2.setProcessingCount(processingCount);
						dataType2.setReceivedCount(receivedCount);
						dataType2.setReleaseCount(releaseCount);
						dataType2.setReleasingCount(releasingCount);
						dataType2.setRemainingCount(remainingCount);
						dataType2.setServiceCode(serviceCode);
						dataType2.setServiceName(serviceName);
						dataType2.setTotalCount(totalCount);
						dataType2.setUndueCount(undueCount);
						dataType2.setWaitingCount(waitingCount);
						dataType2.setGroupBy(1);
						dataType2.setUnresolvedCount(unresolvedCount);
						dataType2.setCancelledCount(cancelledCount);

						statisticData.put(type2, dataType2);
					}
				}

			}

		}else {
			
			if (data != null && data.length() > 0) {
				for (int i = 0; i < data.length(); i++) {
					JSONObject jsonObject = data.getJSONObject(i);

					String govAgencyCode = jsonObject.getString("govAgencyCode");
					String govAgencyName = jsonObject.getString("govAgencyName");
					String domainCode = jsonObject.getString("domainCode");
					String domainName = jsonObject.getString("domainName");
					String serviceCode = jsonObject.getString("serviceCode");
					String serviceName = jsonObject.getString("serviceName");

					int betimesCount = jsonObject.getInt("betimesCount");
					int doneCount = jsonObject.getInt("doneCount");
					int onegateCount = jsonObject.getInt("onegateCount");
					int onlineCount = jsonObject.getInt("onlineCount");
					int ontimeCount = jsonObject.getInt("ontimeCount");
					int ontimePercentage = jsonObject.getInt("ontimePercentage");
					int overdueCount = jsonObject.getInt("overdueCount");
					int overtimeCount = jsonObject.getInt("overtimeCount");
					
					//process = total
					int processCount = jsonObject.getInt("processCount");
					
					int processingCount = jsonObject.getInt("processingCount");
					int receivedCount = jsonObject.getInt("receivedCount");
					int releaseCount = jsonObject.getInt("releaseCount");
					int releasingCount = jsonObject.getInt("releasingCount");
					int remainingCount = jsonObject.getInt("remainingCount");
					int totalCount = jsonObject.getInt("totalCount");
					int undueCount = jsonObject.getInt("undueCount");
					int waitingCount = jsonObject.getInt("waitingCount");
					
					// DuanTV :Thêm vào trước, hiện để mặc định là 0, khi nào cần thì xử lý lại
					int unresolvedCount = 0;
					int cancelledCount = 0;

					String type3 = "all@" + groupId;
					DossierStatisticMgtData dataType3 = new DossierStatisticMgtData();

					if (statisticData.containsKey(type3)) {
						dataType3 = statisticData.get(type3);
					}

					String type4 = domainCode + "@" + groupId;
					DossierStatisticMgtData dataType4 = new DossierStatisticMgtData();

					if (statisticData.containsKey(type4)) {
						dataType4 = statisticData.get(type4);
					}

					if (Validator.isNull(domainCode)) {

						dataType3.setGroupId(groupId);
						dataType3.setGovAgencyCode(govAgencyCode);
						dataType3.setGovAgencyName(govAgencyName);
						dataType3.setBetimesCount(betimesCount);
						dataType3.setDomainCode(StringPool.BLANK);
						dataType3.setDomainName(StringPool.BLANK);
						dataType3.setDoneCount(doneCount);
						dataType3.setMonth(month);
						dataType3.setYear(year);
						dataType3.setOnegateCount(onegateCount);
						dataType3.setOnlineCount(onlineCount);
						dataType3.setOntimeCount(ontimeCount);
						dataType3.setOntimePercentage(ontimePercentage);
						dataType3.setOverdueCount(overdueCount);
						dataType3.setOvertimeCount(overtimeCount);
						dataType3.setProcessCount(processCount);
						dataType3.setProcessingCount(processingCount);
						dataType3.setReceivedCount(receivedCount);
						dataType3.setReleaseCount(releaseCount);
						dataType3.setReleasingCount(releasingCount);
						dataType3.setRemainingCount(remainingCount);
						dataType3.setServiceCode(serviceCode);
						dataType3.setServiceName(serviceName);
						dataType3.setTotalCount(totalCount);
						dataType3.setUndueCount(undueCount);
						dataType3.setWaitingCount(waitingCount);
						dataType3.setGroupBy(2);						
						dataType3.setUnresolvedCount(unresolvedCount);
						dataType3.setCancelledCount(cancelledCount);						

						statisticData.put(type3, dataType3);
					} else {

						dataType4.setGroupId(groupId);
						dataType4.setGovAgencyCode(govAgencyCode);
						dataType4.setGovAgencyName(govAgencyName);
						dataType4.setBetimesCount(betimesCount);
						dataType4.setDomainCode(domainCode);
						dataType4.setDomainName(domainName);
						dataType4.setDoneCount(doneCount);
						dataType4.setMonth(month);
						dataType4.setYear(year);
						dataType4.setOnegateCount(onegateCount);
						dataType4.setOnlineCount(onlineCount);
						dataType4.setOntimeCount(ontimeCount);
						dataType4.setOntimePercentage(ontimePercentage);
						dataType4.setOverdueCount(overdueCount);
						dataType4.setOvertimeCount(overtimeCount);
						dataType4.setProcessCount(processCount);
						dataType4.setProcessingCount(processingCount);
						dataType4.setReceivedCount(receivedCount);
						dataType4.setReleaseCount(releaseCount);
						dataType4.setReleasingCount(releasingCount);
						dataType4.setRemainingCount(remainingCount);
						dataType4.setServiceCode(serviceCode);
						dataType4.setServiceName(serviceName);
						dataType4.setTotalCount(totalCount);
						dataType4.setUndueCount(undueCount);
						dataType4.setWaitingCount(waitingCount);
						dataType4.setGroupBy(2);
						dataType4.setCancelledCount(cancelledCount);
						dataType4.setUnresolvedCount(unresolvedCount);

						statisticData.put(type4, dataType4);
					}
				}

			}
		}
	}
	
	private List<JSONObject> convertStatisticDataList(Map<String, DossierStatisticMgtData> statisticData) {
		List<JSONObject> lstDossierDataObjs = new ArrayList<JSONObject>();
		for (Map.Entry<String, DossierStatisticMgtData> me : statisticData.entrySet()) {

			DossierStatisticMgtData payload = (DossierStatisticMgtData) me.getValue();
			ObjectMapper mapper = new ObjectMapper();
			try {
				JSONObject dossierDataObj = JSONFactoryUtil.createJSONObject(mapper.writeValueAsString(payload));
				lstDossierDataObjs.add(dossierDataObj);
			} catch (Exception e) {
				_log.error(e);
			}
		}
		return lstDossierDataObjs;
	}

	
	
	private void updateStatistic(List<JSONObject> dossierDataObjs) {
		try {
			OpencpsDossierStatisticMgtLocalServiceUtil.updateBatchStatisticMgt(dossierDataObjs);
		} catch (SystemException e) {
			_log.debug(e);
		} catch (PortalException e) {
			_log.debug(e);
		}
	}
	

	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws SchedulerException {
		String listenerClass = getClass().getName();
		// Create startDate
		Calendar cal = Calendar.getInstance();
		LocalDate now = LocalDate.now();
		int year =  now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		if (HOUR_STATISTIC != -1 && MINUTE_STATISTIC != -1 && SECOND_STATISTIC != -1) {
			cal.set(year, month-1, day, HOUR_STATISTIC, MINUTE_STATISTIC, SECOND_STATISTIC);
		}else {
			cal.set(year, month-1, day);
		}
		Date startDate = cal.getTime();

		Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, startDate, null,
				TIME_STATISTIC, TimeUnit.MINUTE);

		_schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
		_schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);

//		_schedulerEntryImpl.setTrigger(jobTrigger);

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

}
