package org.opencps.statistic.rest.engine.service;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.dto.PersonRequest;
import org.opencps.statistic.rest.dto.PersonResponse;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.VotingResponse;
import org.opencps.statistic.rest.dto.VotingResultRequest;
import org.opencps.statistic.rest.dto.VotingResultResponse;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.service.PersonStatisticFinderService;
import org.opencps.statistic.rest.service.PersonStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class PersonStatisticSumYearCalcular {
	
	private static Log _log = LogFactoryUtil.getLog(PersonStatisticSumYearCalcular.class);
	
	private PersonStatisticFinderService personStatisticFinderService = new PersonStatisticFinderServiceImpl();
	private PersonStatisticUpdateService<PersonStatisticData> updateGovService = new PersonStatisticUpdateServiceImpl();
	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	public void filterPersonSumYear(long companyId, long groupId, int year, boolean isVoting, boolean isEmployee,
			boolean isAgency) throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {

		PersonRequest personResultRequest = new PersonRequest();

		personResultRequest.setMonth(-1);
		personResultRequest.setYear(year);
		personResultRequest.setGroupId(groupId);
		personResultRequest.setStart(QueryUtil.ALL_POS);
		personResultRequest.setEnd(QueryUtil.ALL_POS);

		/** case votingCode = null && employeeId = null && agency = null **/
		if (!isVoting && !isEmployee && !isAgency) {
			/* statistic by all */

			try {
				
				personResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);
				personResultRequest.setEmployeeId(0l);
				personResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
				
				PersonResponse personResponse = personStatisticFinderService
						.finderPersonStatisticList(personResultRequest);

				if (personResponse != null) {
					List<PersonStatisticData> personStatisticList = personResponse.getData();
					if (personStatisticList != null && personStatisticList.size() > 0) {
						PersonStatisticData personData = personStatisticList.get(0);
						if (personData != null) {
							updateDetailData(companyId, groupId, 0, year, null, null, null, null, null,
									personStatisticList, personData);
						}
					}
				}

			} catch (Exception e) {
				_log.error(e);
			}

		}

		/** case votingCode != null && domain == null && agency == null **/
		if (isVoting && !isEmployee && !isAgency) {
			/* statistic by all */

			List<VotingResponse> votingList = getVotingCode(groupId, year);
			if (votingList != null && votingList.size() > 0) {
				for (VotingResponse voting : votingList) {

					try {
						personResultRequest.setVotingCode(voting.getItemCode());
						personResultRequest.setEmployeeId(0l);
						personResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
						
						//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

						PersonResponse personResponse = personStatisticFinderService
								.finderPersonStatisticList(personResultRequest);

						if (personResponse != null) {
							List<PersonStatisticData> personStatisticList = personResponse.getData();
							if (personStatisticList != null && personStatisticList.size() > 0) {
								PersonStatisticData personData = personStatisticList.get(0);
								if (personData != null) {
									updateDetailData(companyId, groupId, 0, year, voting.getItemCode(),
											voting.getItemName(), null, null, null, personStatisticList, personData);
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
		if (!isVoting && isEmployee && !isAgency) {
			/* statistic by all */

			String[] employeeArr = getEmployee(groupId, year);
			for (String employeeId : employeeArr) {

				try {
					personResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);
					personResultRequest.setEmployeeId(GetterUtil.getLong(employeeId));
					personResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					
					//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					PersonResponse personResponse = personStatisticFinderService
							.finderPersonStatisticList(personResultRequest);

					if (personResponse != null) {
						List<PersonStatisticData> personStatisticList = personResponse.getData();
						if (personStatisticList != null && personStatisticList.size() > 0) {
							PersonStatisticData personData = personStatisticList.get(0);
							if (personData != null) {
								updateDetailData(companyId, groupId, 0, year, null, null,
										GetterUtil.getLong(employeeId), null, null, personStatisticList, personData);
							}
						}
					}
				} catch (Exception e) {
					_log.error(e);
				}
			}
		}

		/** case votingCode != null && domain != null && agency == null **/
		if (isVoting && isEmployee && !isAgency) {
			/* statistic by all */

			String[] employeeArr = getEmployee(groupId, year);
			List<VotingResponse> votingList = getVotingCode(groupId, year);
			for (String employeeId : employeeArr) {
				personResultRequest.setEmployeeId(GetterUtil.getLong(employeeId));
				if (votingList != null && votingList.size() > 0) {
					for (VotingResponse voting : votingList) {
						personResultRequest.setVotingCode(voting.getItemCode());

						try {

							personResultRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);

							// DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

							PersonResponse personResponse = personStatisticFinderService
									.finderPersonStatisticList(personResultRequest);

							if (personResponse != null) {
								List<PersonStatisticData> personStatisticList = personResponse.getData();
								if (personStatisticList != null && personStatisticList.size() > 0) {
									PersonStatisticData personData = personStatisticList.get(0);
									if (personData != null) {
										updateDetailData(companyId, groupId, 0, year, voting.getItemCode(),
												voting.getItemName(), GetterUtil.getLong(employeeId), null, null,
												personStatisticList, personData);
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
		if (!isVoting && !isEmployee && isAgency) {
			/* statistic by all */

			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);

			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {

						try {
							personResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);
							personResultRequest.setEmployeeId(0l);
							personResultRequest.setGovAgencyCode(agency.getItemCode());
							
							//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

							PersonResponse personResponse = personStatisticFinderService
									.finderPersonStatisticList(personResultRequest);

							if (personResponse != null) {
								List<PersonStatisticData> personStatisticList = personResponse.getData();
								if (personStatisticList != null && personStatisticList.size() > 0) {
									PersonStatisticData personData = personStatisticList.get(0);
									if (personData != null) {
										updateDetailData(companyId, groupId, 0, year, null, null, null,
												agency.getItemCode(), agency.getItemName(), personStatisticList,
												personData);
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
		if (isVoting && !isEmployee && isAgency) {
			/* statistic by all */
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				List<VotingResponse> votingList = getVotingCode(groupId, year);
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {
						personResultRequest.setGovAgencyCode(agency.getItemCode());
						if (votingList != null && votingList.size() > 0) {
							for (VotingResponse voting : votingList) {
								personResultRequest.setVotingCode(voting.getItemCode());

								try {
									personResultRequest.setEmployeeId(0l);

									PersonResponse personResponse = personStatisticFinderService
											.finderPersonStatisticList(personResultRequest);

									if (personResponse != null) {
										List<PersonStatisticData> personStatisticList = personResponse.getData();
										if (personStatisticList != null && personStatisticList.size() > 0) {
											PersonStatisticData personData = personStatisticList.get(0);
											if (personData != null) {
												updateDetailData(companyId, groupId, 0, year, voting.getItemCode(),
														voting.getItemName(), null, agency.getItemCode(),
														agency.getItemName(), personStatisticList, personData);
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
		if (!isVoting && isEmployee && isAgency) {
			/* statistic by all */
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				String[] employeeArr = getEmployee(groupId, year);
				if (agencyList != null && agencyList.size() > 0) {
					for (GovAgencyData agency : agencyList) {
						personResultRequest.setGovAgencyCode(agency.getItemCode());
						if (employeeArr != null) {
							for (String employeeId : employeeArr) {
								personResultRequest.setEmployeeId(GetterUtil.getLong(employeeId));

								try {
									personResultRequest.setVotingCode(DossierStatisticConstants.TOTAL);

									PersonResponse personResponse = personStatisticFinderService
											.finderPersonStatisticList(personResultRequest);

									if (personResponse != null) {
										List<PersonStatisticData> personStatisticList = personResponse.getData();
										if (personStatisticList != null && personStatisticList.size() > 0) {
											PersonStatisticData personData = personStatisticList.get(0);
											if (personData != null) {
												updateDetailData(companyId, groupId, 0, year, null, null,
														GetterUtil.getLong(employeeId), agency.getItemCode(),
														agency.getItemName(), personStatisticList, personData);
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
		if (isVoting && isEmployee && isAgency) {
			/* statistic by all */
			//System.out.println("STRART");
			GovAgencyRequest agencyRequest = new GovAgencyRequest();
			agencyRequest.setGroupId(groupId);
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (agencyResponse != null) {
				List<GovAgencyData> agencyList = agencyResponse.getData();
				String[] employeeArr = getEmployee(groupId, year);
				List<VotingResponse> votingList = getVotingCode(groupId, year);
				//System.out.println("groupId: "+groupId);
				if (agencyList != null && agencyList.size() > 0) {
					//System.out.println("agencyList: "+ agencyList.size());
					for (GovAgencyData agency : agencyList) {
						personResultRequest.setGovAgencyCode(agency.getItemCode());
						//System.out.println("agency.getItemCode(): "+ agency.getItemCode());
						if (employeeArr != null) {
							for (String employeeId : employeeArr) {
								personResultRequest.setEmployeeId(GetterUtil.getLong(employeeId));
								//System.out.println("domain.getItemCode(): "+ domain.getItemCode());
								//System.out.println("votingList.size(): "+ votingList.size());
								if (votingList != null && votingList.size() > 0) {
									for (VotingResponse voting : votingList) {
										personResultRequest.setVotingCode(voting.getItemCode());
										//System.out.println("voting.getItemCode(): "+ voting.getItemCode());
										try {
											PersonResponse personResponse = personStatisticFinderService
													.finderPersonStatisticList(personResultRequest);

											if (personResponse != null) {
												List<PersonStatisticData> personStatisticList = personResponse.getData();
												if (personStatisticList != null && personStatisticList.size() > 0) {
													PersonStatisticData personData = personStatisticList.get(0);
													if (personData != null) {
														updateDetailData(companyId, groupId, 0, year,
																voting.getItemCode(), voting.getItemName(),
																GetterUtil.getLong(employeeId),
																agency.getItemCode(), agency.getItemName(),
																personStatisticList, personData);
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

	private List<VotingResponse> getVotingCode(long groupId, int year) {
		List<VotingResponse> votingList = new ArrayList<VotingResponse>();

		PersonRequest votingRequest = new PersonRequest();
		votingRequest.setMonth(-1);
		votingRequest.setYear(year);
		votingRequest.setGroupId(groupId);
		votingRequest.setStart(QueryUtil.ALL_POS);
		votingRequest.setEnd(QueryUtil.ALL_POS);

		try {
			PersonResponse votingResponse = personStatisticFinderService.finderPersonStatistic(votingRequest);
			if (votingResponse != null) {
				List<PersonStatisticData> votingDataList = votingResponse.getData();
				VotingResponse result = null;
				Map<String, VotingResponse> map = new HashMap<>();
				if (votingDataList != null && votingDataList.size() > 0) {
					for (PersonStatisticData votingData : votingDataList) {
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

	private String[] getEmployee(long groupId, int year) {
		
		PersonRequest votingRequest = new PersonRequest();
		votingRequest.setMonth(-1);
		if (year > 0) {
			votingRequest.setYear(year);
		} else {
			votingRequest.setYear(LocalDate.now().getYear());
		}
		votingRequest.setGroupId(groupId);
		votingRequest.setStart(QueryUtil.ALL_POS);
		votingRequest.setEnd(QueryUtil.ALL_POS);

		try {
			PersonResponse votingResponse = personStatisticFinderService.finderPersonStatistic(votingRequest);

			if (votingResponse != null) {
				List<PersonStatisticData> votingResultList = votingResponse.getData();
				if (votingResultList != null && votingResultList.size() > 0) {
					StringBuilder sb = new StringBuilder();
					for (PersonStatisticData votingData : votingResultList) {
						String strEmployeeId = sb.toString();
						if (Validator.isNotNull(votingData.getEmployeeId()) && votingData.getEmployeeId() > 0) {
							if (Validator.isNotNull(sb.toString())) {
								if (!strEmployeeId.contains(String.valueOf(votingData.getEmployeeId()))) {
									sb.append(StringPool.COMMA);
									sb.append(votingData.getEmployeeId());
								}
							} else {
								sb.append(votingData.getEmployeeId());
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
			String votingSubject, Long employeeId, String govAgencyCode, String govAgencyName,
			List<PersonStatisticData> dataList, PersonStatisticData personData)
			throws SystemException, PortalException {
		PersonStatisticData votingData = new PersonStatisticData();

		int totalVoted = 0;
		int veryGoodCount = 0;
		int goodCount = 0;

		for (PersonStatisticData data : dataList) {
			totalVoted += data.getTotalVoted();
			veryGoodCount += data.getVeryGoodCount();
			goodCount += data.getGoodCount();
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
		votingData.setEmployeeId(employeeId);
		votingData.setGovAgencyCode(govAgencyCode);
		votingData.setGovAgencyName(govAgencyName);
		votingData.setCompanyId(companyId);
		votingData.setGroupId(groupId);

		updateGovService.updatePersonStatistic(votingData);
	}
}
