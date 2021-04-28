package org.opencps.statistic.rest.engine.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.OpencpsPersonStatistic;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.statistic.service.OpencpsPersonStatisticLocalServiceUtil;
import org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil;
import org.springframework.expression.spel.ast.OpNE;

public class StatisticEngineUpdateAction {

	private static Log _log = LogFactoryUtil.getLog(StatisticEngineUpdateAction.class);

	public OpencpsDossierStatistic updateStatistic(DossierStatisticData payload) {
		if (Validator.isNull(payload.getDomainCode())) {
			payload.setDomainCode((String) null);
		}

		if (Validator.isNull(payload.getGovAgencyCode())) {
			payload.setGovAgencyCode((String) null);
		}
		if (Validator.isNull(payload.getSystem())) {
			payload.setSystem((String) null);
		}
//		long dossierStatisticId = 0L;
//
//		try {
//			OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsit(
//					payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(),
//					payload.getDomainCode());
//			if (Validator.isNotNull(dossierStatistic)) {
//				dossierStatisticId = dossierStatistic.getDossierStatisticId();
//			}
//		} catch (Exception e) {
//			_log.error(e);
//		}
//
//		byte pausingCount = 0;
//
//		try {
//			return OpencpsDossierStatisticLocalServiceUtil.updateStatistic(dossierStatisticId, payload.getCompanyId(),
//					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
//					payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
//					payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
//					payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
//					payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
//					payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
//					payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
//					payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
//					payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
//					payload.getDomainName(), payload.isReporting(), payload.getOnegateCount(), payload.getOutsideCount(),
//					payload.getInsideCount());
//		} catch (PortalException | SystemException e) {
//			_log.error(e);
//			return null;
//		}

		byte pausingCount = 0;
	
		try {
			return OpencpsDossierStatisticLocalServiceUtil.createOrUpdateStatistic(payload.getCompanyId(),
					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getSystem(),
					payload.getTotalCount(), payload.getDeniedCount(), payload.getCancelledCount(),
					payload.getProcessCount(), payload.getRemainingCount(), payload.getReceivedCount(),
					payload.getOnlineCount(), payload.getReleaseCount(), payload.getBetimesCount(),
					payload.getOntimeCount(), payload.getOvertimeCount(), payload.getDoneCount(),
					payload.getReleasingCount(), payload.getUnresolvedCount(), payload.getProcessingCount(),
					payload.getUndueCount(), payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(),
					payload.getOvertimeInside(), payload.getOvertimeOutside(), payload.getInteroperatingCount(),
					payload.getWaitingCount(), payload.getGovAgencyCode(), payload.getGovAgencyName(),
					payload.getDomainCode(), payload.getDomainName(), payload.getGroupAgencyCode(), payload.getReporting(), payload.getOnegateCount(),
					payload.getOutsideCount(), payload.getInsideCount(), payload.getViaPostalCount(), payload.getSaturdayCount(), payload.getDossierOnline3Count(),
					payload.getDossierOnline4Count(), payload.getReceiveDossierSatCount(), payload.getReleaseDossierSatCount(),
					payload.getFromViaPostalCount());
		} catch (PortalException | SystemException e) {
			_log.error(e);
			return null;
		}
	}
	
	public void updateStatistic(List<JSONObject> dossierDataObjs) {
		try {
			OpencpsDossierStatisticLocalServiceUtil.updateBatchStatistic(dossierDataObjs);
		} catch (SystemException e) {
			_log.debug(e);
		} catch (PortalException e) {
			_log.debug(e);
		}
	}
	
	public void removeDossierStatisticByD_M_Y(long groupId, String domainCode, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByD_M_Y(groupId, domainCode, month, year);
	}

	public void removeDossierStatisticByMonthYear(long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByMonthYear(groupId, month, year);
	}

	public void removeDossierStatisticByYear(long companyId, long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByYear(companyId, groupId, month, year);
	}

	//Get list dossierStatistic by groupId, month, year
	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYear(long groupId, int month, int year) {
		return OpencpsDossierStatisticLocalServiceUtil.getDossierStatisticByMonthYear(groupId, month, year);
	}

	//Get list dossierStatistic by groupId, month, year and reporting
	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYearAndReport(long groupId, int month, int year, int reporting) {
		return OpencpsDossierStatisticLocalServiceUtil.getDossierStatisticByMonthYearAndReport(groupId, month, year, reporting);
	}

	//Get list dossierStatistic by groupId, month, year and not reporting
	public List<OpencpsDossierStatistic> getByMonthYearAndNotReport(long groupId, int month, int year, int reporting) {
		return OpencpsDossierStatisticLocalServiceUtil.getByMonthYearAndNotReport(groupId, month, year, reporting);
	}

	//Get list dossierStatistic by groupId, month, year and not reporting
	public OpencpsDossierStatistic getStatisticByMonthYearAndNotReport(long groupId, int month, int year, int reporting) {
		return OpencpsDossierStatisticLocalServiceUtil.getInfoByMonthYearAndNotReport(groupId, month, year, reporting);
	}

	//Remove record by domain and govAgencyCode
	public void removeDossierStatisticByG_M_Y_G_D(long groupId, int month, int year, String agency, String domainCode)
			throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatisticLocalServiceUtil.removeByG_M_Y_G_D(groupId, month, year, agency, domainCode);
	}

	//Get list statistic by year
	public List<OpencpsDossierStatistic> getDossierStatisticByYear(long companyId, long groupId, int month, int year) {
		return OpencpsDossierStatisticLocalServiceUtil.getDossierStatisticByYear(companyId, groupId, month, year);
	}

	//Process Voting
	public OpencpsVotingStatistic updateVotingStatistic(VotingResultStatisticData payload) {

		if (Validator.isNull(payload.getGovAgencyCode())) {
			payload.setGovAgencyCode((String) null);
		}
		if (Validator.isNull(payload.getDomain())) {
			payload.setDomain((String) null);
		}
		if (Validator.isNull(payload.getVotingCode())) {
			payload.setVotingCode((String) null);
		}

		long votingStatisticId = 0L;

		try {
			OpencpsVotingStatistic votingStatistic = OpencpsVotingStatisticLocalServiceUtil.checkExsit(
					payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(),
					payload.getDomain(), payload.getVotingCode());
			//System.out.println("votingStatistic: "+votingStatistic);
			if (Validator.isNotNull(votingStatistic)) {
				votingStatisticId = votingStatistic.getVotingStatisticId();
			}
		} catch (Exception e) {
			_log.error(e);
		}

		//System.out.println("votingStatisticId: "+votingStatisticId);
		try {
			return OpencpsVotingStatisticLocalServiceUtil.updateVotingStatistic(votingStatisticId,
					payload.getCompanyId(), payload.getGroupId(), -1L, "VDM", payload.getMonth(), payload.getYear(),
					payload.getVotingSubject(), payload.getTotalVoted(), payload.getVeryGoodCount(),
					payload.getGoodCount(), payload.getBadCount(), payload.getPercentVeryGood(),
					payload.getPercentGood(), payload.getPercentBad(), payload.getGovAgencyCode(),
					payload.getGovAgencyName(), payload.getDomain(), payload.getDomainName(), payload.getVotingCode(),
					0);
		} catch (SystemException e) {
			_log.error(e);
			return null;
		}
	}

	public void removeVotingStatisticByD_M_Y(long groupId, String domainCode, int month, int year) {
		OpencpsVotingStatisticLocalServiceUtil.removeVotingStatisticByD_M_Y(groupId, domainCode, month, year);
	}

	public void removeVotingStatisticByMonthYear(long groupId, int month, int year) {
		OpencpsVotingStatisticLocalServiceUtil.removeVotingStatisticByMonthYear(groupId, month, year);
	}

	public void removeVotingStatisticByYear(long companyId, long groupId, int month, int year) {
		OpencpsVotingStatisticLocalServiceUtil.removeVotingStatisticByYear(companyId, groupId, month, year);
	}

	//Process Person Voting
	public OpencpsPersonStatistic updatePersonStatistic(PersonStatisticData payload) {

		if (Validator.isNull(payload.getGovAgencyCode())) {
			payload.setGovAgencyCode((String) null);
		}
		if (Validator.isNull(payload.getEmployeeId())) {
			payload.setEmployeeId(0);
		}
		if (Validator.isNull(payload.getVotingCode())) {
			payload.setVotingCode((String) null);
		}

		long personStatisticId = 0L;

		try {
			OpencpsPersonStatistic personStatistic = OpencpsPersonStatisticLocalServiceUtil.checkExsit(
					payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(),
					payload.getEmployeeId(), payload.getVotingCode());
			//System.out.println("votingStatistic: "+votingStatistic);
			if (Validator.isNotNull(personStatistic)) {
				personStatisticId = personStatistic.getPersonStatisticId();
			}
		} catch (Exception e) {
			_log.error(e);
		}

		//System.out.println("votingStatisticId: "+votingStatisticId);
		try {
			return OpencpsPersonStatisticLocalServiceUtil.updatePersonStatistic(personStatisticId,
					payload.getCompanyId(), payload.getGroupId(), -1L, "VDM", payload.getMonth(), payload.getYear(),
					payload.getVotingSubject(), payload.getTotalVoted(),
					payload.getVeryGoodCount(), payload.getGoodCount(), payload.getBadCount(),
					payload.getPercentVeryGood(),
					payload.getPercentGood(), payload.getPercentBad(), payload.getGovAgencyCode(),
					payload.getGovAgencyName(), payload.getEmployeeId(), payload.getVotingCode(), 0);
		} catch (SystemException e) {
			_log.error(e);
		}

		return null;
	}

//	public void removePersonStatisticByD_M_Y(long groupId, String domainCode, int month, int year) {
//		OpencpsPersonStatisticLocalServiceUtil.removeVotingStatisticByD_M_Y(groupId, domainCode, month, year);
//	}

	public void removePersonStatisticByMonthYear(long groupId, int month, int year) {
		OpencpsPersonStatisticLocalServiceUtil.removePersonStatisticByMonthYear(groupId, month, year);
	}

	public void removePersonStatisticByYear(long companyId, long groupId, int month, int year) {
		OpencpsPersonStatisticLocalServiceUtil.removePersonStatisticByYear(companyId, groupId, month, year);
	}
	
	public OpencpsDossierStatistic createStatistic(DossierStatisticData payload) {
		byte pausingCount = 0;

		try {
			return OpencpsDossierStatisticLocalServiceUtil.createOnlyStatistic(payload.getCompanyId(),
					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
					payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
					payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
					payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
					payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
					payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
					payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
					payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
					payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
					payload.getDomainName(), payload.getReporting(), payload.getOnegateCount(), payload.getOutsideCount(),
					payload.getInsideCount(), payload.getViaPostalCount(), payload.getSaturdayCount(), payload.getDossierOnline3Count(), payload.getDossierOnline4Count(), payload.getReceiveDossierSatCount(), payload.getReleaseDossierSatCount(),
					payload.getFromViaPostalCount());
		} catch (PortalException | SystemException e) {
			_log.error(e);
			return null;
		}
	}
	
	
	public OpencpsDossierStatistic updateOnlyStatistic(OpencpsDossierStatistic statistic, DossierStatisticData payload) {
		byte pausingCount = 0;

		try {
			return OpencpsDossierStatisticLocalServiceUtil.updateOnlyStatistic(statistic, payload.getCompanyId(),
					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
					payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
					payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
					payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
					payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
					payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
					payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
					payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
					payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
					payload.getDomainName(), payload.getReporting(), payload.getOnegateCount(), payload.getOutsideCount(),
					payload.getInsideCount(), payload.getViaPostalCount(), payload.getSaturdayCount(), payload.getDossierOnline3Count(), payload.getDossierOnline4Count(), payload.getReceiveDossierSatCount(), payload.getReleaseDossierSatCount(),
					payload.getFromViaPostalCount());
		} catch (PortalException | SystemException e) {
			_log.error(e);
			return null;
		}
	}
	
	public JSONArray calculateStatisticByGovAgencyCode(JSONArray result ,long groupId,int year) throws Exception {
		
		OpencpsDossierStatistic opencpsDossierStatisticYearTotal = null;
		OpencpsDossierStatistic opencpsDossierStatisticYearTotalNonSystem = null;
		
		List<OpencpsDossierStatistic> govAgencys = OpencpsDossierStatisticLocalServiceUtil.getGovAgencyCodes();
		
		for(OpencpsDossierStatistic govAgencyCode:govAgencys) {
			
			List<OpencpsDossierStatistic> opencpsDossierStatisticYearTotals= new ArrayList<OpencpsDossierStatistic>();
			
			/*Tìm bản ghi duy nhất  theo mã đơn vị, tháng 0(bản ghi cả năm),năm,tổng hợp của MCĐT,NSW,Phần mềm nghiệp vụ khác
			 * */
			 opencpsDossierStatisticYearTotalNonSystem = OpencpsDossierStatisticLocalServiceUtil
					.createUniqueByGovMonthYearNonSystem(20199,groupId, govAgencyCode.getGovAgencyCode(),govAgencyCode.getGovAgencyName(), 0, year);
			
			_log.debug("+++opencpsDossierStatisticYearTotalNonSystem:"+opencpsDossierStatisticYearTotalNonSystem);
			
			for(int system= 0 ; system <=2 ; system++) {
				
				_log.debug("++++system:"+system);
				opencpsDossierStatisticYearTotal = null;
				
				try {
					/*Tìm bản ghi duy nhất  theo mã đơn vị, tháng 0(bản ghi cả năm),năm,system(MCĐT,NSW,Phần mềm nghiệp vụ khác)
					 * */
					opencpsDossierStatisticYearTotal = OpencpsDossierStatisticLocalServiceUtil
							.createUniqueByGovMonthYearSystem(20199, groupId, govAgencyCode.getGovAgencyCode(),
									govAgencyCode.getGovAgencyName(), 0, year, String.valueOf(system));
					
					_log.debug("+++opencpsDossierStatisticYearTotal:"+opencpsDossierStatisticYearTotal);
					
					for(int month = 1;month <= 12; month++  ) {
						
						_log.debug("===month:"+month);
						
						List<OpencpsDossierStatistic> dossierStatistics = OpencpsDossierStatisticLocalServiceUtil
								.searchDossierStatisticSystem(groupId, month,
										year, "total",govAgencyCode.getGovAgencyCode(),  String.valueOf(system), StringPool.BLANK,
										QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						
						_log.debug("----list1----"+dossierStatistics.size());
						
							for(OpencpsDossierStatistic opencpsDossierStatistic:dossierStatistics) {
								
								_log.debug("++++opencpsDossierStatistic.getDossierStatisticId():"+opencpsDossierStatistic.getDossierStatisticId());
								_log.debug("++opencpsDossierStatistic.getGovAgencyCode():"+opencpsDossierStatistic.getGovAgencyCode());
								_log.debug("++opencpsDossierStatistic.getSystem():"+opencpsDossierStatistic.getSystem());
								_log.debug("++opencpsDossierStatistic.getDomainCode():"+opencpsDossierStatistic.getDomainCode());
								_log.debug("++opencpsDossierStatistic.getMonth():"+opencpsDossierStatistic.getMonth());
								_log.debug("++opencpsDossierStatistic.getYear():"+opencpsDossierStatistic.getYear());
								
								if(Validator.isNotNull(opencpsDossierStatistic.getGovAgencyCode())) {
									opencpsDossierStatisticYearTotal = processCalAllStatistic(opencpsDossierStatisticYearTotal, opencpsDossierStatistic, month);
								}

							}

					}
					_log.debug("+++opencpsDossierStatisticYearTotal(2):"+opencpsDossierStatisticYearTotal);
					OpencpsDossierStatisticLocalServiceUtil.updateDossierStatistic(opencpsDossierStatisticYearTotal);
					opencpsDossierStatisticYearTotals.add(opencpsDossierStatisticYearTotal);
					
					JSONObject jsonStatistic = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(opencpsDossierStatisticYearTotal));
					
					result.put(jsonStatistic);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					_log.error(e);
				}
			}
			
			/*Tổng hợp số liệu các bản ghi có system vào bản ghi tổng hợp các đơn vị không có system
			 * */
			opencpsDossierStatisticYearTotalNonSystem = processCalAllStatistic(opencpsDossierStatisticYearTotalNonSystem, opencpsDossierStatisticYearTotals);
			OpencpsDossierStatisticLocalServiceUtil.updateDossierStatistic(opencpsDossierStatisticYearTotalNonSystem);
		}
		return result;
	}
	
	public JSONArray calculateStatisticByDomainCode(JSONArray result ,long groupId,int year) throws Exception {
		
		OpencpsDossierStatistic opencpsDossierStatisticYearTotal = null;
		OpencpsDossierStatistic opencpsDossierStatisticYearTotalNonSystem = null;
		
		List<OpencpsDossierStatistic> domainCodes = OpencpsDossierStatisticLocalServiceUtil.getDomainCodes();
		
		for(OpencpsDossierStatistic domainCode:domainCodes) {
			
			List<OpencpsDossierStatistic> opencpsDossierStatisticYearTotals= new ArrayList<OpencpsDossierStatistic>();
			
			/*Tìm bản ghi duy nhất  theo lĩnh vực, tháng 0(bản ghi cả năm),năm,tổng hợp của MCĐT,NSW,Phần mềm nghiệp vụ khác
			 * */
			 opencpsDossierStatisticYearTotalNonSystem = OpencpsDossierStatisticLocalServiceUtil
					.createUniqueByMonthYearDomainNonSystem(20199,groupId,  0, year,domainCode.getDomainCode(),domainCode.getDomainName());
			
			_log.debug("+++opencpsDossierStatisticYearTotalNonSystem:"+opencpsDossierStatisticYearTotalNonSystem);
			
			for(int system= 0 ; system <=2 ; system++) {
				
				_log.debug("++++system:"+system);
				opencpsDossierStatisticYearTotal = null;
				
				try {
					/*Tìm bản ghi duy nhất  theo lĩnh vực, tháng 0(bản ghi cả năm),năm,system(MCĐT,NSW,Phần mềm nghiệp vụ khác)
					 * */
					opencpsDossierStatisticYearTotal = OpencpsDossierStatisticLocalServiceUtil
							.createUniqueByMonthYearDomainSystem(20199, groupId, 0, year, domainCode.getDomainCode(),
									domainCode.getDomainName(), String.valueOf(system));
					
					_log.debug("+++opencpsDossierStatisticYearTotal:"+opencpsDossierStatisticYearTotal);
					
					for(int month = 1;month <= 12; month++  ) {
						
						_log.debug("===month:"+month);
						
						List<OpencpsDossierStatistic> dossierStatistics = OpencpsDossierStatisticLocalServiceUtil
								.searchDossierStatisticSystem(groupId, month,
										year, domainCode.getDomainCode(),"total",  String.valueOf(system), StringPool.BLANK,
										QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						
						_log.debug("----list1----"+dossierStatistics.size());
						
							for(OpencpsDossierStatistic opencpsDossierStatistic:dossierStatistics) {
								
								_log.debug("++++opencpsDossierStatistic.getDossierStatisticId():"+opencpsDossierStatistic.getDossierStatisticId());
								_log.debug("++opencpsDossierStatistic.getGovAgencyCode():"+opencpsDossierStatistic.getGovAgencyCode());
								_log.debug("++opencpsDossierStatistic.getSystem():"+opencpsDossierStatistic.getSystem());
								_log.debug("++opencpsDossierStatistic.getDomainCode():"+opencpsDossierStatistic.getDomainCode());
								_log.debug("++opencpsDossierStatistic.getMonth():"+opencpsDossierStatistic.getMonth());
								_log.debug("++opencpsDossierStatistic.getYear():"+opencpsDossierStatistic.getYear());
								
								if(Validator.isNotNull(opencpsDossierStatistic.getDomainCode())) {
									opencpsDossierStatisticYearTotal = processCalAllStatistic(opencpsDossierStatisticYearTotal, opencpsDossierStatistic, month);
								}

							}

					}
					_log.debug("+++opencpsDossierStatisticYearTotal(2):"+opencpsDossierStatisticYearTotal);
					OpencpsDossierStatisticLocalServiceUtil.updateDossierStatistic(opencpsDossierStatisticYearTotal);
					opencpsDossierStatisticYearTotals.add(opencpsDossierStatisticYearTotal);
					
					JSONObject jsonStatistic = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(opencpsDossierStatisticYearTotal));
					
					result.put(jsonStatistic);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					_log.error(e);
				}
			}
			
			/*Tổng hợp số liệu các bản ghi có system vào bản ghi tổng hợp các đơn vị không có system
			 * */
			opencpsDossierStatisticYearTotalNonSystem = processCalAllStatistic(opencpsDossierStatisticYearTotalNonSystem, opencpsDossierStatisticYearTotals);
			OpencpsDossierStatisticLocalServiceUtil.updateDossierStatistic(opencpsDossierStatisticYearTotalNonSystem);
		}
		return result;
	}
	
	public JSONArray calculateStatistic(long groupId,int year) throws Exception {
		
		JSONArray result = JSONFactoryUtil.createJSONArray();
		
		result = calculateStatisticByGovAgencyCode(result, groupId, year);
		
		result = calculateStatisticByDomainCode(result, groupId, year);
		
		return result;
	}
	
	private  OpencpsDossierStatistic processCalAllStatistic(OpencpsDossierStatistic opencpsDossierStatisticYearTotal ,List<OpencpsDossierStatistic> opencpsDossierStatistics) {
		
		for(int i=0;i < opencpsDossierStatistics.size();i++) {
			
			OpencpsDossierStatistic opencpsDossierStatistic = opencpsDossierStatistics.get(i);
			
			opencpsDossierStatisticYearTotal = processCalAllStatistic(opencpsDossierStatisticYearTotal, opencpsDossierStatistic, i == 0 ? 1:0);
		}
		
		return opencpsDossierStatisticYearTotal;
	}
	
	private  OpencpsDossierStatistic processCalAllStatistic(OpencpsDossierStatistic opencpsDossierStatisticYearTotal ,OpencpsDossierStatistic opencpsDossierStatistic,int month) {
		
		opencpsDossierStatisticYearTotal.setModifiedDate(new Date());
		
		if(month == 1) {
			
			/*
			 * Ghi đè giá trị cũ trong trường hợp opencpsDossierStatisticYearTotal đã có dữ liệu
			 * */
			
			opencpsDossierStatisticYearTotal.setProcessingCount(opencpsDossierStatistic.getProcessingCount());
			opencpsDossierStatisticYearTotal.setProcessingInAPeriodCount(opencpsDossierStatistic.getProcessingInAPeriodCount());
			opencpsDossierStatisticYearTotal.setReleaseInAPeriodCount(opencpsDossierStatistic.getReleaseInAPeriodCount());
			opencpsDossierStatisticYearTotal.setWaitingCount(opencpsDossierStatistic.getWaitingCount());

			opencpsDossierStatisticYearTotal.setTotalCount(opencpsDossierStatistic.getTotalCount());
			opencpsDossierStatisticYearTotal.setProcessCount(opencpsDossierStatistic.getProcessCount());
			opencpsDossierStatisticYearTotal.setRemainingCount(opencpsDossierStatistic.getRemainingCount());
			opencpsDossierStatisticYearTotal.setReceivedCount(opencpsDossierStatistic.getReceivedCount());
			opencpsDossierStatisticYearTotal.setOnlineCount(opencpsDossierStatistic.getOnlineCount());
			opencpsDossierStatisticYearTotal.setOnegateCount(opencpsDossierStatistic.getOnegateCount());
			opencpsDossierStatisticYearTotal.setReleaseCount(opencpsDossierStatistic.getReceivedCount());
			opencpsDossierStatisticYearTotal.setBetimesCount(opencpsDossierStatistic.getBetimesCount());
			opencpsDossierStatisticYearTotal.setOntimeCount(opencpsDossierStatistic.getOntimeCount());
			opencpsDossierStatisticYearTotal.setOvertimeCount(opencpsDossierStatistic.getOvertimeCount());
			opencpsDossierStatisticYearTotal.setDoneCount(opencpsDossierStatistic.getDoneCount());
			opencpsDossierStatisticYearTotal.setReleasingCount(opencpsDossierStatistic.getReleasingCount());
			opencpsDossierStatisticYearTotal.setUnresolvedCount(opencpsDossierStatistic.getUnresolvedCount());
			opencpsDossierStatisticYearTotal.setUndueCount(opencpsDossierStatistic.getUndueCount());
			opencpsDossierStatisticYearTotal.setOverdueCount(opencpsDossierStatistic.getOverdueCount());
			opencpsDossierStatisticYearTotal.setPausingCount(opencpsDossierStatistic.getPausingCount());
			opencpsDossierStatisticYearTotal.setOvertimeInside(opencpsDossierStatistic.getOvertimeInside());
			opencpsDossierStatisticYearTotal.setOvertimeOutside(opencpsDossierStatistic.getOvertimeOutside());
			opencpsDossierStatisticYearTotal.setInsideCount(opencpsDossierStatistic.getInsideCount());
			opencpsDossierStatisticYearTotal.setOutsideCount(opencpsDossierStatistic.getOutsideCount());
			opencpsDossierStatisticYearTotal.setInteroperatingCount(opencpsDossierStatistic.getInteroperatingCount());
			opencpsDossierStatisticYearTotal.setViaPostalCount(opencpsDossierStatistic.getViaPostalCount());
			opencpsDossierStatisticYearTotal.setFromViaPostalCount(opencpsDossierStatistic.getFromViaPostalCount());
			opencpsDossierStatisticYearTotal.setSaturdayCount(opencpsDossierStatistic.getSaturdayCount());
			opencpsDossierStatisticYearTotal.setNotViaPostalCount(opencpsDossierStatistic.getNotViaPostalCount());
			opencpsDossierStatisticYearTotal.setDossierOnline3Count(opencpsDossierStatistic.getDossierOnline3Count());
			opencpsDossierStatisticYearTotal.setDossierOnline4Count(opencpsDossierStatistic.getDossierOnline4Count());
			opencpsDossierStatisticYearTotal.setReceiveDossierSatCount(opencpsDossierStatistic.getReceiveDossierSatCount());
			opencpsDossierStatisticYearTotal.setReleaseDossierSatCount(opencpsDossierStatistic.getReleaseDossierSatCount());
			
		}else {
		
			if(month == 0) {
				
				/*
				 * Số liệu cộng tổng
				 * */
				opencpsDossierStatisticYearTotal.setProcessingCount(opencpsDossierStatisticYearTotal.getProcessingCount()+opencpsDossierStatistic.getProcessingCount());
				opencpsDossierStatisticYearTotal.setProcessingInAPeriodCount(opencpsDossierStatisticYearTotal.getProcessingInAPeriodCount()+opencpsDossierStatistic.getProcessingInAPeriodCount());
				opencpsDossierStatisticYearTotal.setReleaseInAPeriodCount(opencpsDossierStatisticYearTotal.getReleaseInAPeriodCount()+opencpsDossierStatistic.getReleaseInAPeriodCount());
				opencpsDossierStatisticYearTotal.setWaitingCount(opencpsDossierStatisticYearTotal.getWaitingCount()+opencpsDossierStatistic.getWaitingCount());
			}else {
				
				/*
				 * Số liệu chỉ lấy tháng cuối cùng
				 * */
				opencpsDossierStatisticYearTotal.setProcessingCount(opencpsDossierStatistic.getProcessingCount());
				opencpsDossierStatisticYearTotal.setProcessingInAPeriodCount(opencpsDossierStatistic.getProcessingInAPeriodCount());
				opencpsDossierStatisticYearTotal.setReleaseInAPeriodCount(opencpsDossierStatistic.getReleaseInAPeriodCount());
				opencpsDossierStatisticYearTotal.setWaitingCount(opencpsDossierStatistic.getWaitingCount());
				
			}
			
			
			/*
			 * Số liệu cộng tổng
			 * */
			opencpsDossierStatisticYearTotal.setTotalCount(opencpsDossierStatisticYearTotal.getTotalCount() + opencpsDossierStatistic.getTotalCount());
			opencpsDossierStatisticYearTotal.setProcessCount(opencpsDossierStatisticYearTotal.getProcessCount() + opencpsDossierStatistic.getProcessCount());
			opencpsDossierStatisticYearTotal.setRemainingCount(opencpsDossierStatisticYearTotal.getRemainingCount()+opencpsDossierStatistic.getRemainingCount());
			opencpsDossierStatisticYearTotal.setReceivedCount(opencpsDossierStatisticYearTotal.getReceivedCount()+opencpsDossierStatistic.getReceivedCount());
			opencpsDossierStatisticYearTotal.setOnlineCount(opencpsDossierStatisticYearTotal.getOnlineCount()+opencpsDossierStatistic.getOnlineCount());
			opencpsDossierStatisticYearTotal.setOnegateCount(opencpsDossierStatisticYearTotal.getOnegateCount()+opencpsDossierStatistic.getOnegateCount());
			opencpsDossierStatisticYearTotal.setReleaseCount(opencpsDossierStatisticYearTotal.getReleaseCount()+opencpsDossierStatistic.getReceivedCount());
			opencpsDossierStatisticYearTotal.setBetimesCount(opencpsDossierStatisticYearTotal.getBetimesCount()+opencpsDossierStatistic.getBetimesCount());
			opencpsDossierStatisticYearTotal.setOntimeCount(opencpsDossierStatisticYearTotal.getOntimeCount()+opencpsDossierStatistic.getOntimeCount());
			opencpsDossierStatisticYearTotal.setOvertimeCount(opencpsDossierStatisticYearTotal.getOvertimeCount()+opencpsDossierStatistic.getOvertimeCount());
			opencpsDossierStatisticYearTotal.setDoneCount(opencpsDossierStatisticYearTotal.getDoneCount()+opencpsDossierStatistic.getDoneCount());
			opencpsDossierStatisticYearTotal.setReleasingCount(opencpsDossierStatisticYearTotal.getReleasingCount()+opencpsDossierStatistic.getReleasingCount());
			opencpsDossierStatisticYearTotal.setUnresolvedCount(opencpsDossierStatisticYearTotal.getUnresolvedCount()+opencpsDossierStatistic.getUnresolvedCount());
			opencpsDossierStatisticYearTotal.setUndueCount(opencpsDossierStatisticYearTotal.getUndueCount()+opencpsDossierStatistic.getUndueCount());
			opencpsDossierStatisticYearTotal.setOverdueCount(opencpsDossierStatisticYearTotal.getOverdueCount()+opencpsDossierStatistic.getOverdueCount());
			opencpsDossierStatisticYearTotal.setPausingCount(opencpsDossierStatisticYearTotal.getPausingCount()+opencpsDossierStatistic.getPausingCount());
			opencpsDossierStatisticYearTotal.setOvertimeInside(opencpsDossierStatisticYearTotal.getOvertimeInside()+opencpsDossierStatistic.getOvertimeInside());
			opencpsDossierStatisticYearTotal.setOvertimeOutside(opencpsDossierStatisticYearTotal.getOvertimeOutside()+opencpsDossierStatistic.getOvertimeOutside());
			opencpsDossierStatisticYearTotal.setInsideCount(opencpsDossierStatisticYearTotal.getInsideCount()+opencpsDossierStatistic.getInsideCount());
			opencpsDossierStatisticYearTotal.setOutsideCount(opencpsDossierStatisticYearTotal.getOutsideCount()+opencpsDossierStatistic.getOutsideCount());
			opencpsDossierStatisticYearTotal.setInteroperatingCount(opencpsDossierStatisticYearTotal.getInteroperatingCount()+opencpsDossierStatistic.getInteroperatingCount());
			
			opencpsDossierStatisticYearTotal.setViaPostalCount(opencpsDossierStatisticYearTotal.getViaPostalCount()+opencpsDossierStatistic.getViaPostalCount());
			opencpsDossierStatisticYearTotal.setFromViaPostalCount(opencpsDossierStatisticYearTotal.getFromViaPostalCount()+opencpsDossierStatistic.getFromViaPostalCount());
			opencpsDossierStatisticYearTotal.setSaturdayCount(opencpsDossierStatisticYearTotal.getSaturdayCount()+opencpsDossierStatistic.getSaturdayCount());
			opencpsDossierStatisticYearTotal.setNotViaPostalCount(opencpsDossierStatisticYearTotal.getNotViaPostalCount()+opencpsDossierStatistic.getNotViaPostalCount());
			opencpsDossierStatisticYearTotal.setDossierOnline3Count(opencpsDossierStatisticYearTotal.getDossierOnline3Count()+opencpsDossierStatistic.getDossierOnline3Count());
			opencpsDossierStatisticYearTotal.setDossierOnline4Count(opencpsDossierStatisticYearTotal.getDossierOnline4Count()+opencpsDossierStatistic.getDossierOnline4Count());
			opencpsDossierStatisticYearTotal.setReceiveDossierSatCount(opencpsDossierStatisticYearTotal.getReceiveDossierSatCount()+opencpsDossierStatistic.getReceiveDossierSatCount());
			opencpsDossierStatisticYearTotal.setReleaseDossierSatCount(opencpsDossierStatisticYearTotal.getReleaseDossierSatCount()+opencpsDossierStatistic.getReleaseDossierSatCount());
		
		}
		
		
		return opencpsDossierStatisticYearTotal;
	}
	
}