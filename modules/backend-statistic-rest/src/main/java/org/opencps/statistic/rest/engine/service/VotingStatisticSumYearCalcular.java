package org.opencps.statistic.rest.engine.service;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.dto.ServiceResponse;
import org.opencps.statistic.rest.dto.VotingResultRequest;
import org.opencps.statistic.rest.dto.VotingResultResponse;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.service.VotingStatisticFinderService;
import org.opencps.statistic.rest.service.VotingStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class VotingStatisticSumYearCalcular {
	
	private static Log _log = LogFactoryUtil.getLog(StatisticSumYearCalcular.class);
	
	private VotingStatisticFinderService votingStatisticFinderService = new VotingStatisticFinderServiceImpl();
	private VotingStatisticUpdateService<VotingResultStatisticData> updateGovService = new VotingStatisticUpdateServiceImpl();
	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	public void filterVotingSumYear(long companyId, long groupId, int year, boolean isVoting, boolean isService,
			boolean isAgency) throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {

		VotingResultRequest votingResultRequest = new VotingResultRequest();

		votingResultRequest.setMonth(-1);
		votingResultRequest.setYear(year);
		votingResultRequest.setGroupId(groupId);
		votingResultRequest.setStart(QueryUtil.ALL_POS);
		votingResultRequest.setEnd(QueryUtil.ALL_POS);

		/** case votingCode = null && service = null && agency = null **/
		if (!isVoting && !isService && !isAgency) {
			/* statistic by all */

			try {
				
				votingResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);
				votingResultRequest.setServiceCode(DossierStatisticConstants.TOTAL);
				votingResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
				
				VotingResultResponse votingResultResponse = votingStatisticFinderService
						.finderVotingStatisticList(votingResultRequest);

				//Optional<List<DossierStatisticData>> dossierStatisticData = Optional
				//		.ofNullable(votingResultResponse.getData());
				if (votingResultResponse != null) {
					List<VotingResultStatisticData> votingStatisticList = votingResultResponse.getData();
					if (votingStatisticList != null && votingStatisticList.size() > 0) {
						VotingResultStatisticData votingResultData = votingStatisticList.get(0);
						if (votingResultData != null) {
							updateDetailData(companyId, groupId, 0, year, null, null, null,
									null, null, votingStatisticList, votingResultData);
						}
					}
				}

			} catch (Exception e) {
				_log.error(e);
			}

		}

		/** case votingCode != null && service == null && agency == null **/
		if (isVoting && !isService && !isAgency) {
			/* statistic by all */

			String[] votingCodeArr = getVotingCode(groupId);
			for (String votingCode : votingCodeArr) {

				try {
					votingResultRequest.setVotingCode(votingCode);
					votingResultRequest.setServiceCode(DossierStatisticConstants.TOTAL);
					votingResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					
					//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					VotingResultResponse votingResultResponse = votingStatisticFinderService
							.finderVotingStatisticList(votingResultRequest);

					if (votingResultResponse != null) {
						List<VotingResultStatisticData> votingStatisticList = votingResultResponse.getData();
						if (votingStatisticList != null && votingStatisticList.size() > 0) {
							VotingResultStatisticData votingResultData = votingStatisticList.get(0);
							if (votingResultData != null) {
								updateDetailData(companyId, groupId, 0, year, votingCode, null, null,
										null, null, votingStatisticList, votingResultData);
							}
						}
					}
				} catch (Exception e) {
					_log.error(e);
				}
			}
		}

		/** case votingCode == null && service != null && agency == null **/
		if (!isVoting && isService && !isAgency) {
			/* statistic by all */

			List<ServiceResponse> serviceList = getService(groupId);
			for (ServiceResponse service : serviceList) {

				try {
					votingResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);
					votingResultRequest.setServiceCode(service.getItemCode());
					votingResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					
					//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					VotingResultResponse votingResultResponse = votingStatisticFinderService
							.finderVotingStatisticList(votingResultRequest);

					if (votingResultResponse != null) {
						List<VotingResultStatisticData> votingStatisticList = votingResultResponse.getData();
						if (votingStatisticList != null && votingStatisticList.size() > 0) {
							VotingResultStatisticData votingResultData = votingStatisticList.get(0);
							if (votingResultData != null) {
								updateDetailData(companyId, groupId, 0, year, null, service.getItemCode(),
										service.getItemName(), null, null, votingStatisticList, votingResultData);
							}
						}
					}
				} catch (Exception e) {
					_log.error(e);
				}
			}
		}

		/** case votingCode != null && service != null && agency == null **/
		if (!isVoting && isService && !isAgency) {
			/* statistic by all */

			List<ServiceResponse> serviceList = getService(groupId);
			String[] votingCodeArr = getVotingCode(groupId);
			for (ServiceResponse service : serviceList) {
				votingResultRequest.setServiceCode(service.getItemCode());
				if (votingCodeArr != null) {
					for (String votingCode : votingCodeArr) {
						votingResultRequest.setVotingCode(votingCode);

						try {

							votingResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);

							// DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

							VotingResultResponse votingResultResponse = votingStatisticFinderService
									.finderVotingStatisticList(votingResultRequest);

							if (votingResultResponse != null) {
								List<VotingResultStatisticData> votingStatisticList = votingResultResponse.getData();
								if (votingStatisticList != null && votingStatisticList.size() > 0) {
									VotingResultStatisticData votingResultData = votingStatisticList.get(0);
									if (votingResultData != null) {
										updateDetailData(companyId, groupId, 0, year, votingCode, service.getItemCode(),
												service.getItemName(), null, null, votingStatisticList,
												votingResultData);
									}
								}
							}
						} catch (Exception e) {
							_log.error(e);
						}
					}
				}
			}
		}

		/** case votingCode == null && service == null && agency != null **/
		if (!isVoting && !isService && isAgency) {
			/* statistic by all */

			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);

			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {

						try {
							votingResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);
							votingResultRequest.setServiceCode(DossierStatisticConstants.TOTAL);
							votingResultRequest.setGovAgencyCode(agency.getItemCode());
							
							//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

							VotingResultResponse votingResultResponse = votingStatisticFinderService
									.finderVotingStatisticList(votingResultRequest);

							if (votingResultResponse != null) {
								List<VotingResultStatisticData> votingStatisticList = votingResultResponse.getData();
								if (votingStatisticList != null && votingStatisticList.size() > 0) {
									VotingResultStatisticData votingResultData = votingStatisticList.get(0);
									if (votingResultData != null) {
										updateDetailData(companyId, groupId, 0, year, null, null, null,
												agency.getItemCode(), agency.getItemName(), votingStatisticList,
												votingResultData);
									}
								}
							}
						} catch (Exception e) {
							_log.error(e);
						}
					}
				}
			}
		}

		/** case votingCode != null && service == null && agency != null **/
		if (isVoting && !isService && isAgency) {
			/* statistic by all */
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				String[] votingCodeArr = getVotingCode(groupId);
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {
						votingResultRequest.setGovAgencyCode(agency.getItemCode());
						if (votingCodeArr != null) {
							for (String votingCode : votingCodeArr) {
								votingResultRequest.setVotingCode(votingCode);

								try {
									votingResultRequest.setServiceCode(DossierStatisticConstants.TOTAL);

									VotingResultResponse votingResultResponse = votingStatisticFinderService
											.finderVotingStatisticList(votingResultRequest);

									if (votingResultResponse != null) {
										List<VotingResultStatisticData> votingStatisticList = votingResultResponse
												.getData();
										if (votingStatisticList != null && votingStatisticList.size() > 0) {
											VotingResultStatisticData votingResultData = votingStatisticList.get(0);
											if (votingResultData != null) {
												updateDetailData(companyId, groupId, 0, year, votingCode, null, null,
														agency.getItemCode(), agency.getItemName(), votingStatisticList,
														votingResultData);
											}
										}
									}
								} catch (Exception e) {
									_log.error(e);
								}
							}
						}
					}
				}
			}
		}

		/** case votingCode == null && service != null && agency != null **/
		if (isVoting && !isService && isAgency) {
			/* statistic by all */
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				List<ServiceResponse> serviceList = getService(groupId);
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {
						votingResultRequest.setGovAgencyCode(agency.getItemCode());
						if (serviceList != null) {
							for (ServiceResponse service : serviceList) {
								votingResultRequest.setServiceCode(service.getItemCode());

								try {
									votingResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);

									VotingResultResponse votingResultResponse = votingStatisticFinderService
											.finderVotingStatisticList(votingResultRequest);

									if (votingResultResponse != null) {
										List<VotingResultStatisticData> votingStatisticList = votingResultResponse
												.getData();
										if (votingStatisticList != null && votingStatisticList.size() > 0) {
											VotingResultStatisticData votingResultData = votingStatisticList.get(0);
											if (votingResultData != null) {
												updateDetailData(companyId, groupId, 0, year, null,
														service.getItemCode(), service.getItemName(),
														agency.getItemCode(), agency.getItemName(), votingStatisticList,
														votingResultData);
											}
										}
									}
								} catch (Exception e) {
									_log.error(e);
								}
							}
						}
					}
				}
			}
		}

		/** case votingCode != null && service != null && agency != null **/
		if (isVoting && !isService && isAgency) {
			/* statistic by all */
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				List<ServiceResponse> serviceList = getService(groupId);
				String[] votingCodeArr = getVotingCode(groupId);
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {
						votingResultRequest.setGovAgencyCode(agency.getItemCode());
						if (serviceList != null) {
							for (ServiceResponse service : serviceList) {
								votingResultRequest.setServiceCode(service.getItemCode());
								if (votingCodeArr != null) {
									for (String votingCode : votingCodeArr) {
										votingResultRequest.setVotingCode(votingCode);
										try {
											VotingResultResponse votingResultResponse = votingStatisticFinderService
													.finderVotingStatisticList(votingResultRequest);

											if (votingResultResponse != null) {
												List<VotingResultStatisticData> votingStatisticList = votingResultResponse
														.getData();
												if (votingStatisticList != null && votingStatisticList.size() > 0) {
													VotingResultStatisticData votingResultData = votingStatisticList.get(0);
													if (votingResultData != null) {
														updateDetailData(companyId, groupId, 0, year, null,
																service.getItemCode(), service.getItemName(),
																agency.getItemCode(), agency.getItemName(), votingStatisticList,
																votingResultData);
													}
												}
											}
										} catch (Exception e) {
											_log.error(e);
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

	private List<ServiceResponse> getService(long groupId) {
		List<ServiceResponse> serviceList = new ArrayList<>();

		DossierStatisticRequest dossierRequest = new DossierStatisticRequest();

		dossierRequest.setMonth(-1);
		dossierRequest.setYear(LocalDate.now().getYear());
		dossierRequest.setGroupId(groupId);
		dossierRequest.setStart(QueryUtil.ALL_POS);
		dossierRequest.setEnd(QueryUtil.ALL_POS);
		
		VotingResultRequest serviceRequest = new VotingResultRequest();

		serviceRequest.setMonth(-1);
		serviceRequest.setYear(LocalDate.now().getYear());
		serviceRequest.setGroupId(groupId);
		serviceRequest.setStart(QueryUtil.ALL_POS);
		serviceRequest.setEnd(QueryUtil.ALL_POS);

		try {
			VotingResultResponse votingResponse = votingStatisticFinderService.finderVotingStatistic(serviceRequest);

			if (votingResponse != null) {
				List<VotingResultStatisticData> votingDataList = votingResponse.getData();
				ServiceResponse serviceResponse = null;
				if (votingDataList != null && votingDataList.size() > 0) {
					for (VotingResultStatisticData votingData : votingDataList) {
						if (Validator.isNotNull(votingData.getServiceCode())
								&& Validator.isNotNull(votingData.getServiceName())) {
							serviceResponse = new ServiceResponse();
							serviceResponse.setItemCode(votingData.getServiceCode());
							serviceResponse.setItemName(votingData.getServiceName());

							serviceList.add(serviceResponse);
						}
					}
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		/* remove duplicate */
		Set<ServiceResponse> hs = new HashSet<>();
		hs.addAll(serviceList);
		serviceList.clear();
		serviceList.addAll(hs);

		return serviceList;
	}

	private String[] getVotingCode(long groupId) {

		VotingResultRequest votingRequest = new VotingResultRequest();
		votingRequest.setMonth(-1);
		votingRequest.setYear(LocalDate.now().getYear());
		votingRequest.setGroupId(groupId);
		votingRequest.setStart(QueryUtil.ALL_POS);
		votingRequest.setEnd(QueryUtil.ALL_POS);

		try {
			VotingResultResponse votingResponse = votingStatisticFinderService.finderVotingStatistic(votingRequest);

			if (votingResponse != null) {
				List<VotingResultStatisticData> votingResultList = votingResponse.getData();
				if (votingResultList != null && votingResultList.size() > 0) {
					StringBuilder sb = new StringBuilder();
					for (VotingResultStatisticData votingData : votingResultList) {
						String strVotingCode = sb.toString();
						if (Validator.isNotNull(votingData.getVotingCode())) {
							if (Validator.isNotNull(sb.toString())) {
								if (!strVotingCode.contains(votingData.getVotingCode())) {
									sb.append(StringPool.COMMA);
									sb.append(votingData.getVotingCode());
								}
							} else {
								sb.append(votingData.getVotingCode());
							}
						}
					}
					if (Validator.isNotNull(sb.toString())) {
						return StringUtil.split(sb.toString());
					}
				}
			}
		} catch (PortalException e) {
			_log.error(e);
		}

		return null;
	}

	private void updateDetailData(long companyId, long groupId, int month, int year, String votingCode,
			String serviceCode, String serviceName, String govAgencyCode, String govAgencyName,
			List<VotingResultStatisticData> dataList, VotingResultStatisticData votingResultData) throws SystemException, PortalException {
		VotingResultStatisticData votingData = new VotingResultStatisticData();

		int totalVoted = 0;
		int veryGoodCount = 0;
		int goodCount = 0;
		//int badCount = 0;
		int totalCount = 0;

		for (VotingResultStatisticData data : dataList) {
			totalVoted += data.getTotalCount();
			totalCount += data.getTotalCount();
			veryGoodCount += data.getVeryGoodCount();
			goodCount += data.getGoodCount();
			//badCount += data.getBadCount();
		}
		//Calculate percent option answer
		int percentVeryGood = veryGoodCount * 100 / totalVoted;
		int percentGood = goodCount * 100 / totalVoted;
		int percentBad = 100 - (percentVeryGood + percentGood);
		// set value in object
		votingData.setPercentVeryGood(percentVeryGood);
		votingData.setPercentGood(percentGood);
		votingData.setPercentBad(percentBad);
		votingData.setTotalVoted(totalVoted);
		votingData.setTotalCount(totalCount);
		votingData.setMonth(month);
		votingData.setYear(year);
		votingData.setVotingCode(votingCode);
		votingData.setServiceCode(serviceCode);
		votingData.setServiceName(serviceName);
		votingData.setGovAgencyCode(govAgencyCode);
		votingData.setGovAgencyName(govAgencyName);
		votingData.setCompanyId(companyId);
		votingData.setGroupId(groupId);

		updateGovService.updateVotingStatistic(votingData);
	}
}
