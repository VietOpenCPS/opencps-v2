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
import java.util.Optional;
import java.util.Set;

import org.opencps.statistic.rest.dto.DomainResponse;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
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
									null, null, votingStatisticList, votingResultData);
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

			String[] votingCodeArr = getVotingCode(groupId);
			for (String votingCode : votingCodeArr) {

				try {
					votingResultRequest.setVotingCode(votingCode);
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
								updateDetailData(companyId, groupId, 0, year, null, domain.getItemCode(),
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
		if (!isVoting && isDomain && !isAgency) {
			/* statistic by all */

			List<DomainResponse> domainList = getDomain(groupId);
			String[] votingCodeArr = getVotingCode(groupId);
			for (DomainResponse domain : domainList) {
				votingResultRequest.setDomain(domain.getItemCode());
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
										updateDetailData(companyId, groupId, 0, year, votingCode, domain.getItemCode(),
												domain.getItemName(), null, null, votingStatisticList,
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

		/** case votingCode != null && domain == null && agency != null **/
		if (isVoting && !isDomain && isAgency) {
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
									votingResultRequest.setDomain(DossierStatisticConstants.TOTAL);

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

		/** case votingCode == null && domain != null && agency != null **/
		if (isVoting && !isDomain && isAgency) {
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
												updateDetailData(companyId, groupId, 0, year, null,
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
		if (isVoting && !isDomain && isAgency) {
			/* statistic by all */
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				List<DomainResponse> domainList = getDomain(groupId);
				String[] votingCodeArr = getVotingCode(groupId);
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {
						votingResultRequest.setGovAgencyCode(agency.getItemCode());
						if (domainList != null) {
							for (DomainResponse domain : domainList) {
								votingResultRequest.setDomain(domain.getItemCode());
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
				if (votingDataList != null && votingDataList.size() > 0) {
					for (VotingResultStatisticData votingData : votingDataList) {
						if (Validator.isNotNull(votingData.getDomain())
								&& Validator.isNotNull(votingData.getDomainName())) {
							domainResponse = new DomainResponse();
							domainResponse.setItemCode(votingData.getDomain());
							domainResponse.setItemName(votingData.getDomainName());

							domainList.add(domainResponse);
						}
					}
				}
			}

		} catch (PortalException e) {
			_log.error(e);
		}

		/* remove duplicate */
		Set<DomainResponse> hs = new HashSet<>();
		hs.addAll(domainList);
		domainList.clear();
		domainList.addAll(hs);

		return domainList;
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

	private void updateDetailData(long companyId, long groupId, int month, int year, String votingCode, String domain,
			String domainName, String govAgencyCode, String govAgencyName, List<VotingResultStatisticData> dataList,
			VotingResultStatisticData votingResultData) throws SystemException, PortalException {
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
		//votingData.setTotalCount(totalCount);
		votingData.setMonth(month);
		votingData.setYear(year);
		votingData.setVotingCode(votingCode);
		votingData.setDomain(domain);
		votingData.setDomainName(domainName);
		votingData.setGovAgencyCode(govAgencyCode);
		votingData.setGovAgencyName(govAgencyName);
		votingData.setCompanyId(companyId);
		votingData.setGroupId(groupId);

		updateGovService.updateVotingStatistic(votingData);
	}
}
