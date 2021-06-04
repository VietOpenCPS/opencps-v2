package org.opencps.backend.statisticmgt.engine;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.util.ActionUtil;
import org.opencps.statistic.rest.engine.service.StatisticUtils;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = DossierStatisticDataEngine.class)
public class DossierStatisticDataEngine extends BaseMessageListener{

	private volatile boolean isRunningDossier = false;

	protected Log _log = LogFactoryUtil.getLog(DossierStatisticDataEngine.class);

	@Override
	protected void doReceive(Message message) throws Exception {
		
		_log.debug("START STATISTIC DOSSIER: " + isRunningDossier);
		if (!isRunningDossier) {
			isRunningDossier = true;
		}
		else {
			return;
		}
		long startTime = System.currentTimeMillis();
		Date nowLog = new Date();
		try {
		
			int monthCurrent = LocalDate.now().getMonthValue();
			int yearCurrent = LocalDate.now().getYear();
			Map<Integer, Boolean> mapFlagCurrent = new HashMap<>();
			for (int month = 1; month <= monthCurrent; month ++) {
				boolean flagStatistic = true;
				if (month <= monthCurrent) {
					_log.info("STATISTICS CALCULATE ONE MONTH SITE: " + month + ", " + site.getGroupId() + ", " + site.getName(Locale.getDefault()) + " " + (System.currentTimeMillis() - startTime) + " ms");
					if (flagStatistic) {
						try {
							updateData(groupId, userId, monthCurrent, year, originalities, 
									domainCode, govAgencyCode, serviceCode, dossierStatus, day, 
									groupBy, start, end, type, subType);
							calculateDatas.put(yearCurrent, calculateData);
						} catch (Exception e) {
							_log.debug(e);
						}						
					}
					mapFlagCurrent.put(month, flagStatistic);
				}
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void updateData(long groupId, long userId, int month, int year, String originalities,
			String domainCode, String govAgencyCode, String serviceCode, String dossierStatus, Integer day,
			String groupBy, Integer start, Integer end, int type, String subType) {
		
		Date firstDay = getFirstDay(month, year);
		Date lastDay = getLastDay(month, year);
		long fromDate = firstDay.getTime();
		long toDate = lastDay.getTime();
		
		JSONObject result = ActionUtil.getDossierStatistic(groupId, userId, fromDate, toDate, originalities, domainCode,
				govAgencyCode, serviceCode, dossierStatus, day, groupBy, start, end, type, Constants.GROUP_COUNT);

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
	
}
