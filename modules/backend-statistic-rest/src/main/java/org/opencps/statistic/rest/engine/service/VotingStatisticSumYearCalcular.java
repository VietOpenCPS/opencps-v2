package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.statistic.rest.dto.DomainResponse;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.dto.VotingResponse;
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
	
	private static Log _log = LogFactoryUtil.getLog(VotingStatisticSumYearCalcular.class);
	
	private VotingStatisticFinderService votingStatisticFinderService = new VotingStatisticFinderServiceImpl();
	private VotingStatisticUpdateService<VotingResultStatisticData> updateGovService = new VotingStatisticUpdateServiceImpl();
	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	public void filterVotingSumYear(long companyId, long groupId, int year, boolean isVoting, boolean isDomain,
			boolean isAgency) throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {

		VotingResultRequest votingResultRequest = new VotingResultRequest();

		votingResultRequest.setMonth(-1);
		votingResultRequest.setYear(year);
		votingResultRequest.setGroupId(groupId);
		votingResultRequest.setStart(QueryUtil.ALL_POS);
		votingResultRequest.setEnd(QueryUtil.ALL_POS);

		/** case votingCode = null && domain = null && agency = null **/
		if (!isVoting && !isDomain && !isAgency) {
			/* statistic by all */

			try {
				
				votingResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);
				votingResultRequest.setDomain(DossierStatisticConstants.TOTAL);
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
									null, null, null, votingStatisticList, votingResultData);
						}
					}
				}

			} catch (Exception e) {
				_log.error(e);
			}

		}

		/** case votingCode != null && domain == null && agency == null **/
		if (isVoting && !isDomain && !isAgency) {
			/* statistic by all */

			List<VotingResponse> votingList = getVotingCode(groupId, year);
			if (votingList != null && votingList.size() > 0) {
				for (VotingResponse voting : votingList) {

					try {
						votingResultRequest.setVotingCode(voting.getItemCode());
						votingResultRequest.setDomain(DossierStatisticConstants.TOTAL);
						votingResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
						
						//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

						VotingResultResponse votingResultResponse = votingStatisticFinderService
								.finderVotingStatisticList(votingResultRequest);

						if (votingResultResponse != null) {
							List<VotingResultStatisticData> votingStatisticList = votingResultResponse.getData();
							if (votingStatisticList != null && votingStatisticList.size() > 0) {
								VotingResultStatisticData votingResultData = votingStatisticList.get(0);
								if (votingResultData != null) {
									updateDetailData(companyId, groupId, 0, year, voting.getItemCode(),
											voting.getItemName(), null, null, null, null, votingStatisticList,
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

		/** case votingCode == null && domain != null && agency == null **/
		if (!isVoting && isDomain && !isAgency) {
			/* statistic by all */

			List<DomainResponse> domainList = getDomain(groupId);
			for (DomainResponse domain : domainList) {

				try {
					votingResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);
					votingResultRequest.setDomain(domain.getItemCode());
					votingResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					
					//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					VotingResultResponse votingResultResponse = votingStatisticFinderService
							.finderVotingStatisticList(votingResultRequest);

					if (votingResultResponse != null) {
						List<VotingResultStatisticData> votingStatisticList = votingResultResponse.getData();
						if (votingStatisticList != null && votingStatisticList.size() > 0) {
							VotingResultStatisticData votingResultData = votingStatisticList.get(0);
							if (votingResultData != null) {
								updateDetailData(companyId, groupId, 0, year, null, null, domain.getItemCode(),
										domain.getItemName(), null, null, votingStatisticList, votingResultData);
							}
						}
					}
				} catch (Exception e) {
					_log.error(e);
				}
			}
		}

		/** case votingCode != null && domain != null && agency == null **/
		if (isVoting && isDomain && !isAgency) {
			/* statistic by all */

			List<DomainResponse> domainList = getDomain(groupId);
			List<VotingResponse> votingList = getVotingCode(groupId, year);
			for (DomainResponse domain : domainList) {
				votingResultRequest.setDomain(domain.getItemCode());
				if (votingList != null && votingList.size() > 0) {
					for (VotingResponse voting : votingList) {
						votingResultRequest.setVotingCode(voting.getItemCode());

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
										updateDetailData(companyId, groupId, 0, year, voting.getItemCode(),
												voting.getItemName(), domain.getItemCode(), domain.getItemName(), null,
												null, votingStatisticList, votingResultData);
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

		/** case votingCode == null && domain == null && agency != null **/
		if (!isVoting && !isDomain && isAgency) {
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
							votingResultRequest.setDomain(DossierStatisticConstants.TOTAL);
							votingResultRequest.setGovAgencyCode(agency.getItemCode());
							
							//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

							VotingResultResponse votingResultResponse = votingStatisticFinderService
									.finderVotingStatisticList(votingResultRequest);

							if (votingResultResponse != null) {
								List<VotingResultStatisticData> votingStatisticList = votingResultResponse.getData();
								if (votingStatisticList != null && votingStatisticList.size() > 0) {
									VotingResultStatisticData votingResultData = votingStatisticList.get(0);
									if (votingResultData != null) {
										updateDetailData(companyId, groupId, 0, year, null, null, null, null,
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

		/** case votingCode != null && domain == null && agency != null **/
		if (isVoting && !isDomain && isAgency) {
			/* statistic by all */
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				List<VotingResponse> votingList = getVotingCode(groupId, year);
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {
						votingResultRequest.setGovAgencyCode(agency.getItemCode());
						if (votingList != null && votingList.size() > 0) {
							for (VotingResponse voting : votingList) {
								votingResultRequest.setVotingCode(voting.getItemCode());

								try {
									votingResultRequest.setDomain(DossierStatisticConstants.TOTAL);

									VotingResultResponse votingResultResponse = votingStatisticFinderService
											.finderVotingStatisticList(votingResultRequest);

									if (votingResultResponse != null) {
										List<VotingResultStatisticData> votingStatisticList = votingResultResponse
												.getData();
										if (votingStatisticList != null && votingStatisticList.size() > 0) {
											VotingResultStatisticData votingResultData = votingStatisticList.get(0);
											if (votingResultData != null) {
												updateDetailData(companyId, groupId, 0, year, voting.getItemCode(),
														voting.getItemName(), null, null, agency.getItemCode(),
														agency.getItemName(), votingStatisticList, votingResultData);
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

		/** case votingCode == null && domain != null && agency != null **/
		if (!isVoting && isDomain && isAgency) {
			/* statistic by all */
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				List<DomainResponse> domainList = getDomain(groupId);
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {
						votingResultRequest.setGovAgencyCode(agency.getItemCode());
						if (domainList != null) {
							for (DomainResponse domain : domainList) {
								votingResultRequest.setDomain(domain.getItemCode());

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
												updateDetailData(companyId, groupId, 0, year, null, null,
														domain.getItemCode(), domain.getItemName(),
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

		/** case votingCode != null && domain != null && agency != null **/
		if (isVoting && isDomain && isAgency) {
			/* statistic by all */
			//System.out.println("STRART");
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				List<DomainResponse> domainList = getDomain(groupId);
				List<VotingResponse> votingList = getVotingCode(groupId, year);
				//System.out.println("groupId: "+groupId);
				if (agencyList != null && agencyList.size() > 0) {
					//System.out.println("agencyList: "+ agencyList.size());
					for (GovAgencyData agency : agencyList) {
						votingResultRequest.setGovAgencyCode(agency.getItemCode());
						//System.out.println("agency.getItemCode(): "+ agency.getItemCode());
						if (domainList != null) {
							for (DomainResponse domain : domainList) {
								votingResultRequest.setDomain(domain.getItemCode());
								//System.out.println("domain.getItemCode(): "+ domain.getItemCode());
								//System.out.println("votingList.size(): "+ votingList.size());
								if (votingList != null && votingList.size() > 0) {
									for (VotingResponse voting : votingList) {
										votingResultRequest.setVotingCode(voting.getItemCode());
										//System.out.println("voting.getItemCode(): "+ voting.getItemCode());
										try {
											VotingResultResponse votingResultResponse = votingStatisticFinderService
													.finderVotingStatisticList(votingResultRequest);

											if (votingResultResponse != null) {
												List<VotingResultStatisticData> votingStatisticList = votingResultResponse
														.getData();
												if (votingStatisticList != null && votingStatisticList.size() > 0) {
													VotingResultStatisticData votingResultData = votingStatisticList.get(0);
													if (votingResultData != null) {
														updateDetailData(companyId, groupId, 0, year,
																voting.getItemCode(), voting.getItemName(),
																domain.getItemCode(), domain.getItemName(),
																agency.getItemCode(), agency.getItemName(),
																votingStatisticList, votingResultData);
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

	private List<DomainResponse> getDomain(long groupId) {
		List<DomainResponse> domainList = new ArrayList<DomainResponse>();

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
				DomainResponse domainResponse = null;
				Map<String, DomainResponse> map = new HashMap<>();
				if (votingDataList != null && votingDataList.size() > 0) {
					for (VotingResultStatisticData votingData : votingDataList) {
						if (Validator.isNotNull(votingData.getDomain())
								&& Validator.isNotNull(votingData.getDomainName())) {
							domainResponse = new DomainResponse();
							domainResponse.setItemCode(votingData.getDomain());
							domainResponse.setItemName(votingData.getDomainName());
							//
							map.put(votingData.getDomain(), domainResponse);
						}
					}
				}
				//
				if (map != null && !map.isEmpty()) {
					for (Map.Entry<String, DomainResponse> entry : map.entrySet()) {
						domainList.add(entry.getValue());
					}
				}
			}

		} catch (PortalException e) {
			_log.error(e);
		}

		return domainList;
	}

	private List<VotingResponse> getVotingCode(long groupId, int year) {
		List<VotingResponse> votingList = new ArrayList<VotingResponse>();

		VotingResultRequest votingRequest = new VotingResultRequest();
		votingRequest.setMonth(-1);
		votingRequest.setYear(year);
		votingRequest.setGroupId(groupId);
		votingRequest.setStart(QueryUtil.ALL_POS);
		votingRequest.setEnd(QueryUtil.ALL_POS);

		try {
			VotingResultResponse votingResponse = votingStatisticFinderService.finderVotingStatistic(votingRequest);
			if (votingResponse != null) {
				List<VotingResultStatisticData> votingDataList = votingResponse.getData();
				VotingResponse result = null;
				Map<String, VotingResponse> map = new HashMap<>();
				if (votingDataList != null && votingDataList.size() > 0) {
					for (VotingResultStatisticData votingData : votingDataList) {
						if (Validator.isNotNull(votingData.getVotingCode())
								&& Validator.isNotNull(votingData.getVotingSubject())) {
							result = new VotingResponse();
							result.setItemCode(votingData.getVotingCode());
							result.setItemName(votingData.getVotingSubject());
							//
							map.put(votingData.getVotingCode(), result);
						}
					}
				}
				//
				if (map != null && !map.isEmpty()) {
					for (Map.Entry<String, VotingResponse> entry : map.entrySet()) {
						votingList.add(entry.getValue());
					}
				}
			}
		} catch (PortalException e) {
			_log.error(e);
		}

		return votingList;
	}

	private void updateDetailData(long companyId, long groupId, int month, int year, String votingCode,
			String votingSubject, String domain, String domainName, String govAgencyCode, String govAgencyName,
			List<VotingResultStatisticData> dataList, VotingResultStatisticData votingResultData)
			throws SystemException, PortalException {
		VotingResultStatisticData votingData = new VotingResultStatisticData();

		int totalVoted = 0;
		int veryGoodCount = 0;
		int goodCount = 0;
		//int badCount = 0;
		//int totalCount = 0;

		for (VotingResultStatisticData data : dataList) {
			totalVoted += data.getTotalVoted();
			//totalCount += data.getTotalCount();
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
		votingData.setVeryGoodCount(veryGoodCount);
		votingData.setGoodCount(goodCount);
		votingData.setBadCount(totalVoted - (veryGoodCount + goodCount));
		votingData.setMonth(month);
		votingData.setYear(year);
		votingData.setVotingCode(votingCode);
		votingData.setVotingSubject(votingSubject);
		votingData.setDomain(domain);
		votingData.setDomainName(domainName);
		votingData.setGovAgencyCode(govAgencyCode);
		votingData.setGovAgencyName(govAgencyName);
		votingData.setCompanyId(companyId);
		votingData.setGroupId(groupId);

		updateGovService.updateVotingStatistic(votingData);
	}
}
