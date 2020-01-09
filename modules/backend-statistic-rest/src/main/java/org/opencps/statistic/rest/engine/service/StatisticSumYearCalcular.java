package org.opencps.statistic.rest.engine.service;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.statistic.rest.dto.DomainResponse;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.service.DossierStatisticFinderService;
import org.opencps.statistic.rest.service.DossierStatisticFinderServiceImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.StatisticDataUtil;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class StatisticSumYearCalcular {
	
	private static Log _log = LogFactoryUtil.getLog(StatisticSumYearCalcular.class);
	
	private DossierStatisticFinderService dossierStatisticFinderService = new DossierStatisticFinderServiceImpl();

	private DossierStatisticUpdateService<DossierStatisticData> updateGovService = new DossierStatisticUpdateServiceImpl();

	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	/* Tính theo năm */
	public void filterSumYear(long companyId, long groupId, int year, boolean isDomain, boolean isAgency, boolean isSystem, List<String> lstGroupGovs)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {

		DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();
		List<ServerConfig> lstScs =  ServerConfigLocalServiceUtil.getByProtocol(groupId, DossierStatisticConstants.STATISTIC_PROTOCOL);
		
		dossierStatisticRequest.setMonth(-1);
		dossierStatisticRequest.setYear(year);
		dossierStatisticRequest.setGroupId(groupId);
		dossierStatisticRequest.setStart(QueryUtil.ALL_POS);
		dossierStatisticRequest.setEnd(QueryUtil.ALL_POS);
		StringBuilder groupAgencyCode = new StringBuilder();
		for (String gc : lstGroupGovs) {
			if (!"".contentEquals(groupAgencyCode.toString())) {
				groupAgencyCode.append(StringPool.COMMA);
			}
			groupAgencyCode.append(gc);
		}
		/** #1 */
		/* case domain = null && agency = null && system = null */
		if (!isDomain && !isAgency && !isSystem) {
			/* statistic by all */

				try {
					dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);
					
					//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
							.finderDossierStatistics(dossierStatisticRequest);

					if (dossierStatisticResponse != null) {
						Optional<List<DossierStatisticData>> dossierStatisticData = Optional
								.ofNullable(dossierStatisticResponse.getDossierStatisticData());

						dossierStatisticData.ifPresent(source -> {
							if (source.size() > 0) {
								
								//LOG.info("***DATA****" + source.size());
								DossierStatisticData latestMonthStatisticData = source.get(0);

								try {
									getDetailData(companyId, groupId, 0, year, null, null, null, null, null, null, source,
											latestMonthStatisticData);
								} catch (SystemException e) {
									_log.error(e);
								} catch (PortalException e) {
									_log.error(e);
								}
							}
						});
					}

				} catch (Exception e) {
					_log.error(e);
				}


		}

		/** #2 */
		/* case domain = null && agency = null  && system != null*/
		if (!isDomain && !isAgency && isSystem) {
			/* statistic by all */

			String[] systemArr = DossierStatisticConstants.ALL_SYSTEM.split(StringPool.COMMA);

			for (String strSystem : systemArr) {

				try {
					dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setSystem(strSystem);
					
					//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
							.finderDossierStatistics(dossierStatisticRequest);

					if (dossierStatisticResponse != null) {
						Optional<List<DossierStatisticData>> dossierStatisticData = Optional
								.ofNullable(dossierStatisticResponse.getDossierStatisticData());
	
						dossierStatisticData.ifPresent(source -> {
							if (source.size() > 0) {
								
								//LOG.info("***DATA****" + source.size());
								DossierStatisticData latestMonthStatisticData = source.get(0);
	
								try {
									getDetailData(companyId, groupId, 0, year, null, null, null, null, strSystem, null, source,
											latestMonthStatisticData);
								} catch (SystemException e) {
									_log.error(e);
								} catch (PortalException e) {
									_log.error(e);
								}
							}
						});
					}

				} catch (Exception e) {
					_log.error(e);
				}
			}

		}

		/** #3 */
		/* case domain = null && agency != null  && system = null*/
		if (!isDomain && isAgency && !isSystem) {
			/* statistic by all */

			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject;
					try {
						scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							agencyRequest.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}						
					} catch (JSONException e) {
						_log.error(e);
					}
				}
			}

			GovAgencyResponse agencyResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				agencyResponse = callService.callRestService(agencyRequest);
			}
			else {
				agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
			}

			Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

			govDataList.ifPresent(source -> {
				for (GovAgencyData data : source) {
					dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setGovAgencyCode(data.getItemCode());
					dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);

					DossierStatisticResponse dossierStatisticResponse;

					try {
						dossierStatisticResponse = dossierStatisticFinderService
								.finderDossierStatistics(dossierStatisticRequest);

						if (dossierStatisticResponse != null) {
							Optional<List<DossierStatisticData>> dossierStatisticData = Optional
									.ofNullable(dossierStatisticResponse.getDossierStatisticData());

							dossierStatisticData.ifPresent(source2 -> {
								if (dossierStatisticData.get().size() > 0) {

									DossierStatisticData latestMonthStatisticData = source2.get(0);

									try {
										getDetailData(companyId, groupId, 0, year, null, null, data.getItemCode(),
												data.getItemName(), null, null, source2, latestMonthStatisticData);
									} catch (SystemException e) {
										_log.error(e);
									} catch (PortalException e) {
										_log.error(e);
									}
								}

							});
						}

					} catch (PortalException e) {
						_log.error(e);
					}

				}
			});

		}

		/** #4 */
		/* case domain != null && agency = null && system = null*/
		if (isDomain && !isAgency && !isSystem) {
			/* statistic by all */

			List<DomainResponse> domainResponses = getDomain(groupId, -1, year);
			
			//DossierStatisticUtils.logAsFormattedJson(LOG, domainResponses);

			for (DomainResponse domainResponse : domainResponses) {

				try {
					dossierStatisticRequest.setDomain(domainResponse.getItemCode());
					dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);
					
					//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
							.finderDossierStatistics(dossierStatisticRequest);

					if (dossierStatisticResponse != null) {
						Optional<List<DossierStatisticData>> dossierStatisticData = Optional
								.ofNullable(dossierStatisticResponse.getDossierStatisticData());

						dossierStatisticData.ifPresent(source -> {
							if (source.size() > 0) {
								
								//LOG.info("***DATA****" + source.size());
								DossierStatisticData latestMonthStatisticData = source.get(0);

								try {
									getDetailData(companyId, groupId, 0, year, domainResponse.getItemCode(),
											domainResponse.getItemName(), null, null, null, null, source,
											latestMonthStatisticData);
								} catch (SystemException e) {
									_log.error(e);
								} catch (PortalException e) {
									_log.error(e);
								}
							}
						});
					}

				} catch (Exception e) {
					_log.error(e);
				}

			}

		}

		/** #5 */
		/* case domain = null && agency != null &&  system != null */
		if (!isDomain && isAgency && isSystem) {
			/* statistic by all */
			String[] systemArr = DossierStatisticConstants.ALL_SYSTEM.split(StringPool.COMMA);
			//
			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject;
					try {
						scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							agencyRequest.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}						
					} catch (JSONException e) {
						_log.error(e);
					}
				}
			}

			GovAgencyResponse agencyResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				agencyResponse = callService.callRestService(agencyRequest);
			}
			else {
				agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
			}
			//
			Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

			for (String strSystem : systemArr) {
				dossierStatisticRequest.setSystem(strSystem);
				
				govDataList.ifPresent(source -> {
					for (GovAgencyData data : source) {
						dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
						dossierStatisticRequest.setGovAgencyCode(data.getItemCode());

						DossierStatisticResponse dossierStatisticResponse;

						try {
							dossierStatisticResponse = dossierStatisticFinderService
									.finderDossierStatistics(dossierStatisticRequest);

							if (dossierStatisticResponse != null) {
								Optional<List<DossierStatisticData>> dossierStatisticData = Optional
										.ofNullable(dossierStatisticResponse.getDossierStatisticData());

								dossierStatisticData.ifPresent(source2 -> {
									if (dossierStatisticData.get().size() > 0) {

										DossierStatisticData latestMonthStatisticData = source2.get(0);

										try {
											getDetailData(companyId, groupId, 0, year, null, null, data.getItemCode(),
													data.getItemName(), strSystem, null, source2, latestMonthStatisticData);
										} catch (SystemException e) {
											_log.error(e);
										} catch (PortalException e) {
											_log.error(e);
										}
									}

								});
							}

						} catch (PortalException e) {
							_log.error(e);
						}

					}
				});
			}

		}

		/** #6 */
		/* case domain != null && agency = null  && system != null */
		if (isDomain && !isAgency && isSystem) {
			
			String[] systemArr = DossierStatisticConstants.ALL_SYSTEM.split(StringPool.COMMA);
			//
			List<DomainResponse> domainResponses = getDomain(groupId, -1, year);
			//
			for (String strSystem : systemArr) {
				dossierStatisticRequest.setSystem(strSystem);
				dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);

				if (!domainResponses.isEmpty()) {

					for (DomainResponse domainResponse : domainResponses) {
						dossierStatisticRequest.setDomain(domainResponse.getItemCode());

						DossierStatisticResponse dossierStatisticResponse;

						try {
							dossierStatisticResponse = dossierStatisticFinderService
									.finderDossierStatistics(dossierStatisticRequest);

							if (dossierStatisticResponse != null) {
								Optional<List<DossierStatisticData>> dossierStatisticData = Optional
										.ofNullable(dossierStatisticResponse.getDossierStatisticData());

								dossierStatisticData.ifPresent(source2 -> {
									if (dossierStatisticData.get().size() > 0) {
										DossierStatisticData latestMonthStatisticData = source2.get(0);

										try {
											getDetailData(companyId, groupId, 0, year, domainResponse.getItemCode(),
													domainResponse.getItemName(), null, null, strSystem, null, source2,
													latestMonthStatisticData);
										} catch (SystemException e) {
											_log.error(e);
										} catch (PortalException e) {
											_log.error(e);
										}
									}

								});
							}

						} catch (PortalException e) {
							_log.error(e);
						}
					}
				}
			}

		}

		/** #7 */
		/* case domain != null && agency != null */
		if (isDomain && isAgency && !isSystem) {
			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject;
					try {
						scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							agencyRequest.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}						
					} catch (JSONException e) {
						_log.error(e);
					}
				}
			}

			GovAgencyResponse agencyResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				agencyResponse = callService.callRestService(agencyRequest);
			}
			else {
				agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
			}

			Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

			List<DomainResponse> domainResponses = getDomain(groupId, -1, year);

			govDataList.ifPresent(source -> {
				for (GovAgencyData data : source) {
					dossierStatisticRequest.setGovAgencyCode(data.getItemCode());

					if (!domainResponses.isEmpty()) {

						for (DomainResponse domainResponse : domainResponses) {
							dossierStatisticRequest.setDomain(domainResponse.getItemCode());
							dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);

							DossierStatisticResponse dossierStatisticResponse;

							try {
								dossierStatisticResponse = dossierStatisticFinderService
										.finderDossierStatistics(dossierStatisticRequest);

								if (dossierStatisticResponse != null) {
									Optional<List<DossierStatisticData>> dossierStatisticData = Optional
											.ofNullable(dossierStatisticResponse.getDossierStatisticData());

									dossierStatisticData.ifPresent(source2 -> {
										if (dossierStatisticData.get().size() > 0) {
											DossierStatisticData latestMonthStatisticData = source2.get(0);

											try {
												getDetailData(companyId, groupId, 0, year, domainResponse.getItemCode(),
														domainResponse.getItemName(), data.getItemCode(),
														data.getItemName(), null, null, source2, latestMonthStatisticData);
											} catch (SystemException e) {
												_log.error(e);
											} catch (PortalException e) {
												_log.error(e);
											}
										}

									});
								}

							} catch (PortalException e) {
								_log.error(e);
							}
						}
					}

				}
			});

		}

		/** #8 */
		/* case domain != null && agency != null && system != null */
		if (isDomain && isAgency && isSystem) {
			//
			String[] systemArr = DossierStatisticConstants.ALL_SYSTEM.split(StringPool.COMMA);
			//
			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject;
					try {
						scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							agencyRequest.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}						
					} catch (JSONException e) {
						_log.error(e);
					}
				}
			}

			GovAgencyResponse agencyResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				agencyResponse = callService.callRestService(agencyRequest);
			}
			else {
				agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
			}

			Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

			List<DomainResponse> domainResponses = getDomain(groupId, -1, year);

			for (String strSystem : systemArr) {
				dossierStatisticRequest.setSystem(strSystem);
				
				govDataList.ifPresent(source -> {
					for (GovAgencyData data : source) {
						dossierStatisticRequest.setGovAgencyCode(data.getItemCode());

						if (!domainResponses.isEmpty()) {

							for (DomainResponse domainResponse : domainResponses) {
								dossierStatisticRequest.setDomain(domainResponse.getItemCode());

								DossierStatisticResponse dossierStatisticResponse;

								try {
									dossierStatisticResponse = dossierStatisticFinderService
											.finderDossierStatistics(dossierStatisticRequest);

									if (dossierStatisticResponse != null) {
										Optional<List<DossierStatisticData>> dossierStatisticData = Optional
												.ofNullable(dossierStatisticResponse.getDossierStatisticData());

										dossierStatisticData.ifPresent(source2 -> {
											if (dossierStatisticData.get().size() > 0) {
												DossierStatisticData latestMonthStatisticData = source2.get(0);

												try {
													getDetailData(companyId, groupId, 0, year, domainResponse.getItemCode(),
															domainResponse.getItemName(), data.getItemCode(),
															data.getItemName(), strSystem, null, source2, latestMonthStatisticData);
												} catch (SystemException e) {
													_log.error(e);
												} catch (PortalException e) {
													_log.error(e);
												}
											}

										});
									}

								} catch (PortalException e) {
									_log.error(e);
								}
							}
						}
					}
				});
			}

		}

			try {
				dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
				dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
				dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);
				dossierStatisticRequest.setGroupAgencyCode(groupAgencyCode.toString());
				
				//DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

				DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
						.finderDossierStatistics(dossierStatisticRequest);
				
				if (dossierStatisticResponse != null) {
					Optional<List<DossierStatisticData>> dossierStatisticData = Optional
							.ofNullable(dossierStatisticResponse.getDossierStatisticData());

					dossierStatisticData.ifPresent(source -> {
						if (source.size() > 0) {
							
							//LOG.info("***DATA****" + source.size());
							DossierStatisticData latestMonthStatisticData = source.get(0);

							try {
								getDetailData(companyId, groupId, 0, year, null, null, null, null, null, groupAgencyCode.toString(), source,
										latestMonthStatisticData);
							} catch (SystemException e) {
								_log.error(e);
							} catch (PortalException e) {
								_log.error(e);
							}
						}
					});
				}

			} catch (Exception e) {
				_log.error(e);
			}
					
	}

	/* Caculate all year */
	public void filterSumAllYear(long companyId, long groupId, int month, boolean isDomain, boolean isAgency,
			boolean isSystem, List<String> lstGroupGovs) throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {
		DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(groupId,
				DossierStatisticConstants.STATISTIC_PROTOCOL);

		dossierStatisticRequest.setMonth(0);
		dossierStatisticRequest.setYear(-1);
		dossierStatisticRequest.setGroupId(groupId);
		dossierStatisticRequest.setStart(QueryUtil.ALL_POS);
		dossierStatisticRequest.setEnd(QueryUtil.ALL_POS);

		/** #1 */
		/* case domain = null && agency = null && system = null */
		if (!isDomain && !isAgency && !isSystem) {
			/* statistic by all */
			_log.info("Case 1111111");

			try {
				dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
				dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
				dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);

				// DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

				DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
						.finderDossierStatistics(dossierStatisticRequest);
				
//				_log.info("dossierStatisticResponse2222: "+dossierStatisticResponse);
				//_log.info("dossierStatisticResponse111: "+JSONFactoryUtil.looseSerialize(dossierStatisticResponse));

				if (dossierStatisticResponse != null) {
					Optional<List<DossierStatisticData>> dossierStatisticData = Optional
							.ofNullable(dossierStatisticResponse.getDossierStatisticData());

					dossierStatisticData.ifPresent(source -> {
						if (source.size() > 0) {

							// LOG.info("***DATA****" + source.size());
							DossierStatisticData latestMonthStatisticData = source.get(0);

							try {
								getDetailData(companyId, groupId, 0, 0, null, null, null, null, null, null, source,
										latestMonthStatisticData);
							} catch (SystemException e) {
								_log.error(e);
							} catch (PortalException e) {
								_log.error(e);
							}
						}
					});
				}

			} catch (Exception e) {
				_log.error(e);
			}

		}

		/** #2 */
		/* case domain = null && agency = null && system != null */
		if (!isDomain && !isAgency && isSystem) {
			/* statistic by all */
			_log.info("Case 2222222");

			String[] systemArr = DossierStatisticConstants.ALL_SYSTEM.split(StringPool.COMMA);

			for (String strSystem : systemArr) {

				try {
					dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setSystem(strSystem);

					// DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
							.finderDossierStatistics(dossierStatisticRequest);

					if (dossierStatisticResponse != null) {
						Optional<List<DossierStatisticData>> dossierStatisticData = Optional
								.ofNullable(dossierStatisticResponse.getDossierStatisticData());

						dossierStatisticData.ifPresent(source -> {
							if (source.size() > 0) {

								// LOG.info("***DATA****" + source.size());
								DossierStatisticData latestMonthStatisticData = source.get(0);
								_log.info("latestMonthStatisticData: "+latestMonthStatisticData);

								try {
									getDetailData(companyId, groupId, 0, 0, null, null, null, null, strSystem, null, source,
											latestMonthStatisticData);
								} catch (SystemException e) {
									_log.error(e);
								} catch (PortalException e) {
									_log.error(e);
								}
							}
						});
					}

				} catch (Exception e) {
					_log.error(e);
				}
			}

		}

		/** #3 */
		/* case domain = null && agency != null && system = null */
		if (!isDomain && isAgency && !isSystem) {
			/* statistic by all */

			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject;
					try {
						scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							agencyRequest
									.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}
					} catch (JSONException e) {
						_log.error(e);
					}
				}
			}

			GovAgencyResponse agencyResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				agencyResponse = callService.callRestService(agencyRequest);
			} else {
				agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
			}

			Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

			govDataList.ifPresent(source -> {
				for (GovAgencyData data : source) {
					dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setGovAgencyCode(data.getItemCode());
					dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);

					DossierStatisticResponse dossierStatisticResponse;

					try {
						dossierStatisticResponse = dossierStatisticFinderService
								.finderDossierStatistics(dossierStatisticRequest);

						if (dossierStatisticResponse != null) {
							Optional<List<DossierStatisticData>> dossierStatisticData = Optional
									.ofNullable(dossierStatisticResponse.getDossierStatisticData());

							dossierStatisticData.ifPresent(source2 -> {
								if (dossierStatisticData.get().size() > 0) {

									DossierStatisticData latestMonthStatisticData = source2.get(0);

									try {
										getDetailData(companyId, groupId, 0, 0, null, null, data.getItemCode(),
												data.getItemName(), null, null, source2, latestMonthStatisticData);
									} catch (SystemException e) {
										_log.error(e);
									} catch (PortalException e) {
										_log.error(e);
									}
								}

							});
						}

					} catch (PortalException e) {
						_log.error(e);
					}

				}
			});

		}

		/** #4 */
		/* case domain != null && agency = null && system = null */
		if (isDomain && !isAgency && !isSystem) {
			/* statistic by all */

			List<DomainResponse> domainResponses = getDomain(groupId, month, -1);

			// DossierStatisticUtils.logAsFormattedJson(LOG, domainResponses);

			for (DomainResponse domainResponse : domainResponses) {

				try {
					dossierStatisticRequest.setDomain(domainResponse.getItemCode());
					dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
					dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);

					// DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

					DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
							.finderDossierStatistics(dossierStatisticRequest);

					if (dossierStatisticResponse != null) {
						Optional<List<DossierStatisticData>> dossierStatisticData = Optional
								.ofNullable(dossierStatisticResponse.getDossierStatisticData());

						dossierStatisticData.ifPresent(source -> {
							if (source.size() > 0) {

								// LOG.info("***DATA****" + source.size());
								DossierStatisticData latestMonthStatisticData = source.get(0);

								try {
									getDetailData(companyId, groupId, 0, 0, domainResponse.getItemCode(),
											domainResponse.getItemName(), null, null, null, null, source,
											latestMonthStatisticData);
								} catch (SystemException e) {
									_log.error(e);
								} catch (PortalException e) {
									_log.error(e);
								}
							}
						});
					}

				} catch (Exception e) {
					_log.error(e);
				}

			}

		}

		/** #5 */
		/* case domain = null && agency != null && system != null */
		if (!isDomain && !isAgency) {
			/* statistic by all */
			String[] systemArr = DossierStatisticConstants.ALL_SYSTEM.split(StringPool.COMMA);
			//
			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject;
					try {
						scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							agencyRequest
									.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}
					} catch (JSONException e) {
						_log.error(e);
					}
				}
			}

			GovAgencyResponse agencyResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				agencyResponse = callService.callRestService(agencyRequest);
			} else {
				agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
			}
			//
			Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

			for (String strSystem : systemArr) {
				dossierStatisticRequest.setSystem(strSystem);

				govDataList.ifPresent(source -> {
					for (GovAgencyData data : source) {
						dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
						dossierStatisticRequest.setGovAgencyCode(data.getItemCode());

						DossierStatisticResponse dossierStatisticResponse;

						try {
							dossierStatisticResponse = dossierStatisticFinderService
									.finderDossierStatistics(dossierStatisticRequest);

							if (dossierStatisticResponse != null) {
								Optional<List<DossierStatisticData>> dossierStatisticData = Optional
										.ofNullable(dossierStatisticResponse.getDossierStatisticData());

								dossierStatisticData.ifPresent(source2 -> {
									if (dossierStatisticData.get().size() > 0) {

										DossierStatisticData latestMonthStatisticData = source2.get(0);

										try {
											getDetailData(companyId, groupId, 0, 0, null, null, data.getItemCode(),
													data.getItemName(), strSystem, null, source2, latestMonthStatisticData);
										} catch (SystemException e) {
											_log.error(e);
										} catch (PortalException e) {
											_log.error(e);
										}
									}

								});
							}

						} catch (PortalException e) {
							_log.error(e);
						}

					}
				});
			}

		}

		/** #6 */
		/* case domain != null && agency = null && system != null */
		if (isDomain && !isAgency && isSystem) {

			String[] systemArr = DossierStatisticConstants.ALL_SYSTEM.split(StringPool.COMMA);
			//
			List<DomainResponse> domainResponses = getDomain(groupId, month, -1);
			//
			for (String strSystem : systemArr) {
				dossierStatisticRequest.setSystem(strSystem);
				dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);

				if (!domainResponses.isEmpty()) {

					for (DomainResponse domainResponse : domainResponses) {
						dossierStatisticRequest.setDomain(domainResponse.getItemCode());

						DossierStatisticResponse dossierStatisticResponse;

						try {
							dossierStatisticResponse = dossierStatisticFinderService
									.finderDossierStatistics(dossierStatisticRequest);

							if (dossierStatisticResponse != null) {
								Optional<List<DossierStatisticData>> dossierStatisticData = Optional
										.ofNullable(dossierStatisticResponse.getDossierStatisticData());

								dossierStatisticData.ifPresent(source2 -> {
									if (dossierStatisticData.get().size() > 0) {
										DossierStatisticData latestMonthStatisticData = source2.get(0);

										try {
											getDetailData(companyId, groupId, 0, 0, domainResponse.getItemCode(),
													domainResponse.getItemName(), null, null, strSystem, null, source2,
													latestMonthStatisticData);
										} catch (SystemException e) {
											_log.error(e);
										} catch (PortalException e) {
											_log.error(e);
										}
									}

								});
							}

						} catch (PortalException e) {
							_log.error(e);
						}
					}
				}
			}

		}

		/** #7 */
		/* case domain != null && agency != null */
		if (isDomain && isAgency && !isSystem) {
			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject;
					try {
						scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							agencyRequest
									.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}
					} catch (JSONException e) {
						_log.error(e);
					}
				}
			}

			GovAgencyResponse agencyResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				agencyResponse = callService.callRestService(agencyRequest);
			} else {
				agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
			}

			Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

			List<DomainResponse> domainResponses = getDomain(groupId, month, -1);

			govDataList.ifPresent(source -> {
				for (GovAgencyData data : source) {
					dossierStatisticRequest.setGovAgencyCode(data.getItemCode());

					if (!domainResponses.isEmpty()) {

						for (DomainResponse domainResponse : domainResponses) {
							dossierStatisticRequest.setDomain(domainResponse.getItemCode());
							dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);

							DossierStatisticResponse dossierStatisticResponse;

							try {
								dossierStatisticResponse = dossierStatisticFinderService
										.finderDossierStatistics(dossierStatisticRequest);

								if (dossierStatisticResponse != null) {
									Optional<List<DossierStatisticData>> dossierStatisticData = Optional
											.ofNullable(dossierStatisticResponse.getDossierStatisticData());

									dossierStatisticData.ifPresent(source2 -> {
										if (dossierStatisticData.get().size() > 0) {
											DossierStatisticData latestMonthStatisticData = source2.get(0);

											try {
												getDetailData(companyId, groupId, 0, 0, domainResponse.getItemCode(),
														domainResponse.getItemName(), data.getItemCode(),
														data.getItemName(), null, null, source2, latestMonthStatisticData);
											} catch (SystemException e) {
												_log.error(e);
											} catch (PortalException e) {
												_log.error(e);
											}
										}

									});
								}

							} catch (PortalException e) {
								_log.error(e);
							}
						}
					}

				}
			});

		}

		/** #8 */
		/* case domain != null && agency != null && system != null */
		if (isDomain && isAgency && isSystem) {
			//
			String[] systemArr = DossierStatisticConstants.ALL_SYSTEM.split(StringPool.COMMA);
			//
			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject;
					try {
						scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
						if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
							agencyRequest.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
						}
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							agencyRequest.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							agencyRequest
									.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}
					} catch (JSONException e) {
						_log.error(e);
					}
				}
			}

			GovAgencyResponse agencyResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				agencyResponse = callService.callRestService(agencyRequest);
			} else {
				agencyResponse = StatisticDataUtil.getLocalGovAgency(agencyRequest);
			}

			Optional<List<GovAgencyData>> govDataList = Optional.ofNullable(agencyResponse.getData());

			List<DomainResponse> domainResponses = getDomain(groupId, month, -1);

			for (String strSystem : systemArr) {
				dossierStatisticRequest.setSystem(strSystem);

				govDataList.ifPresent(source -> {
					for (GovAgencyData data : source) {
						dossierStatisticRequest.setGovAgencyCode(data.getItemCode());

						if (!domainResponses.isEmpty()) {

							for (DomainResponse domainResponse : domainResponses) {
								dossierStatisticRequest.setDomain(domainResponse.getItemCode());
								dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);

								DossierStatisticResponse dossierStatisticResponse;

								try {
									dossierStatisticResponse = dossierStatisticFinderService
											.finderDossierStatistics(dossierStatisticRequest);

									if (dossierStatisticResponse != null) {
										Optional<List<DossierStatisticData>> dossierStatisticData = Optional
												.ofNullable(dossierStatisticResponse.getDossierStatisticData());

										dossierStatisticData.ifPresent(source2 -> {
											if (dossierStatisticData.get().size() > 0) {
												DossierStatisticData latestMonthStatisticData = source2.get(0);

												try {
													getDetailData(companyId, groupId, 0, 0, domainResponse.getItemCode(),
															domainResponse.getItemName(), data.getItemCode(),
															data.getItemName(), strSystem, null, source2,
															latestMonthStatisticData);
												} catch (SystemException e) {
													_log.error(e);
												} catch (PortalException e) {
													_log.error(e);
												}
											}
										});
									}
								} catch (PortalException e) {
									_log.error(e);
								}
							}
						}
					}
				});
			}
		}
		
		//Each group gov agency
		StringBuilder groupAgencyCode = new StringBuilder();
		for (String gc : lstGroupGovs) {
			if (!"".contentEquals(groupAgencyCode.toString())) {
				groupAgencyCode.append(StringPool.COMMA);
			}
			groupAgencyCode.append(gc);
		}
			try {
				dossierStatisticRequest.setDomain(DossierStatisticConstants.TOTAL);
				dossierStatisticRequest.setGovAgencyCode(DossierStatisticConstants.TOTAL);
				dossierStatisticRequest.setSystem(DossierStatisticConstants.TOTAL);
				dossierStatisticRequest.setGroupAgencyCode(groupAgencyCode.toString());
				
				// DossierStatisticUtils.logAsFormattedJson(LOG, dossierStatisticRequest);

				DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
						.finderDossierStatistics(dossierStatisticRequest);
				
//				_log.info("dossierStatisticResponse2222: "+dossierStatisticResponse);
				//_log.info("dossierStatisticResponse111: "+JSONFactoryUtil.looseSerialize(dossierStatisticResponse));

				if (dossierStatisticResponse != null) {
					Optional<List<DossierStatisticData>> dossierStatisticData = Optional
							.ofNullable(dossierStatisticResponse.getDossierStatisticData());

					dossierStatisticData.ifPresent(source -> {
						if (source.size() > 0) {

							// LOG.info("***DATA****" + source.size());
							DossierStatisticData latestMonthStatisticData = source.get(0);

							try {
								getDetailData(companyId, groupId, 0, 0, null, null, null, null, null, null, source,
										latestMonthStatisticData);
							} catch (SystemException e) {
								_log.error(e);
							} catch (PortalException e) {
								_log.error(e);
							}
						}
					});
				}

			} catch (Exception e) {
				_log.error(e);
			}					
	}

	/* Tìm kiếm domain theo tháng và năm */
	private List<DomainResponse> getDomain(long groupId, int month, int year) {
		List<DomainResponse> domainResponses = new ArrayList<DomainResponse>();

		DossierStatisticRequest dossierStatisticRequest = new DossierStatisticRequest();

		dossierStatisticRequest.setMonth(month);
		dossierStatisticRequest.setYear(year);
		dossierStatisticRequest.setGroupId(groupId);
		dossierStatisticRequest.setStart(QueryUtil.ALL_POS);
		dossierStatisticRequest.setEnd(QueryUtil.ALL_POS);


		try {
			DossierStatisticResponse dossierStatisticResponse = dossierStatisticFinderService
					.finderDossierStatisticSystem(dossierStatisticRequest);

			Optional<List<DossierStatisticData>> optional = Optional
					.ofNullable(dossierStatisticResponse.getDossierStatisticData());

			optional.ifPresent(source -> {
				for (DossierStatisticData dossierStatisticData : source) {
					DomainResponse domainResponse = new DomainResponse();
					if (Validator.isNotNull(dossierStatisticData.getDomainCode())
							&& Validator.isNotNull(dossierStatisticData.getDomainName())) {

						domainResponse.setItemCode(dossierStatisticData.getDomainCode());
						domainResponse.setItemName(dossierStatisticData.getDomainName());

						domainResponses.add(domainResponse);
					}

				}
			});

		} catch (PortalException e) {
			_log.error(e);
		}

		/* remove duplicate */
		Set<DomainResponse> hs = new HashSet<>();
		hs.addAll(domainResponses);
		domainResponses.clear();
		domainResponses.addAll(hs);

		return domainResponses;
	}

	private void getDetailData(long companyId, long groupId, int month, int year, String domainCode, String domainName,
			String govAgencyCode, String govAgencyName, String system, String groupAgencyCode, List<DossierStatisticData> source,
			DossierStatisticData latest) throws SystemException, PortalException {
		DossierStatisticData dossierStatisticData = new DossierStatisticData();

		int totalCount = 0;
		int deniedCount = 0;
		int cancelledCount = 0;
		int processCount = 0;
		int remainingCount = 0;
		int receivedCount = 0;
		int onlineCount = 0;
		int releaseCount = 0;
		int betimesCount = 0;
		int ontimeCount = 0;
		int overtimeCount = 0;
		int overtimeInside = 0;
		int overtimeOutside = 0;
		int doneCount = 0;
		int releasingCount = 0;
		int unresolvedCount = 0;
		int processingCount = 0;
		int undueCount = 0;
		int overdueCount = 0;
		int interoperatingCount = 0;
		int waitingCount = 0;
		int ontimePercentage = 0;
		int onegateCount = 0;
		int viaPostalCount = 0;
		int saturdayCount = 0;
		int dossierOnline3Count = 0;
		int dossierOnline4Count = 0;
		int receiveDossierSatCount = 0;
		int releaseDossierSatCount = 0;
		
		if (month == 0 && year == 0) {
			for (DossierStatisticData data : source) {

				deniedCount = deniedCount + data.getDeniedCount();
				cancelledCount = cancelledCount + data.getCancelledCount();
				receivedCount = receivedCount + data.getReceivedCount();
				onlineCount = onlineCount + data.getOnlineCount();
				releaseCount = releaseCount + data.getReleaseCount();
				unresolvedCount = unresolvedCount + data.getUnresolvedCount();

				betimesCount = betimesCount + data.getBetimesCount();
				ontimeCount = ontimeCount + data.getOntimeCount();
				overtimeCount = overtimeCount + data.getOvertimeCount();
				overtimeOutside = overtimeOutside + data.getOvertimeOutside();
				overtimeInside = overtimeInside + data.getOvertimeInside();
				onegateCount = onegateCount + data.getOnegateCount();
				//
				processingCount = processingCount + data.getProcessingCount();
				undueCount = undueCount + data.getUndueCount();
				overdueCount = overdueCount+ data.getOverdueCount();
				interoperatingCount = interoperatingCount + data.getInteroperatingCount();
				waitingCount = waitingCount + data.getWaitingCount();
				viaPostalCount = viaPostalCount + data.getViaPostalCount();
				saturdayCount = saturdayCount + data.getSaturdayCount();
				dossierOnline3Count = dossierOnline3Count + data.getDossierOnline3Count();
				dossierOnline4Count = dossierOnline4Count + data.getDossierOnline4Count();
				receiveDossierSatCount = receiveDossierSatCount + data.getReceiveDossierSatCount();
				releaseDossierSatCount = releaseDossierSatCount + data.getReleaseDossierSatCount();
			}
		} else {
			for (DossierStatisticData data : source) {

				deniedCount = deniedCount + data.getDeniedCount();
				cancelledCount = cancelledCount + data.getCancelledCount();
				receivedCount = receivedCount + data.getReceivedCount();
				onlineCount = onlineCount + data.getOnlineCount();
				releaseCount = releaseCount + data.getReleaseCount();
				unresolvedCount = unresolvedCount + data.getUnresolvedCount();

				betimesCount = betimesCount + data.getBetimesCount();
				ontimeCount = ontimeCount + data.getOntimeCount();
				overtimeCount = overtimeCount + data.getOvertimeCount();
				overtimeOutside = overtimeOutside + data.getOvertimeOutside();
				overtimeInside = overtimeInside + data.getOvertimeInside();
				onegateCount = onegateCount + data.getOnegateCount();
				viaPostalCount = viaPostalCount + data.getViaPostalCount();
				saturdayCount = saturdayCount + data.getSaturdayCount();
				dossierOnline3Count = dossierOnline3Count + data.getDossierOnline3Count();
				dossierOnline4Count = dossierOnline4Count + data.getDossierOnline4Count();
				receiveDossierSatCount = receiveDossierSatCount + data.getReceiveDossierSatCount();
				releaseDossierSatCount = releaseDossierSatCount + data.getReleaseDossierSatCount();
			}
			//
			processingCount = latest.getProcessingCount();
			undueCount = latest.getUndueCount();
			overdueCount = latest.getOverdueCount();
			interoperatingCount = latest.getInteroperatingCount();
			waitingCount = latest.getWaitingCount();
		}
		
		processCount = releaseCount + processingCount + waitingCount + cancelledCount;
		totalCount = processCount + deniedCount;
		remainingCount = processCount >= receivedCount ? processCount - receivedCount : 0;
		doneCount = releaseCount >= (releasingCount + unresolvedCount) ? releaseCount - (releasingCount + unresolvedCount) : 0;
		
		/* value get in the latest month */
		if (releaseCount > 0) {
			ontimePercentage = Math.round(((betimesCount + ontimeCount)*100)/releaseCount);
		}
		
		dossierStatisticData.setTotalCount(totalCount);
		dossierStatisticData.setDeniedCount(deniedCount);
		dossierStatisticData.setCancelledCount(cancelledCount);
		dossierStatisticData.setProcessCount(processCount);
		dossierStatisticData.setReceivedCount(receivedCount);
		dossierStatisticData.setOnlineCount(onlineCount);
		dossierStatisticData.setReleaseCount(releaseCount);
		dossierStatisticData.setBetimesCount(betimesCount);
		dossierStatisticData.setOntimeCount(ontimeCount);
		dossierStatisticData.setOvertimeCount(overtimeCount);
		dossierStatisticData.setOvertimeInside(overtimeInside);
		dossierStatisticData.setOvertimeOutside(overtimeOutside);
		dossierStatisticData.setReleaseCount(releaseCount);
		dossierStatisticData.setProcessingCount(processingCount);
		dossierStatisticData.setUnresolvedCount(unresolvedCount);
		dossierStatisticData.setUndueCount(undueCount);
		dossierStatisticData.setOverdueCount(overdueCount);
		dossierStatisticData.setInteroperatingCount(interoperatingCount);
		dossierStatisticData.setWaitingCount(waitingCount);
		dossierStatisticData.setOntimePercentage(ontimePercentage);
		dossierStatisticData.setMonth(month);
		dossierStatisticData.setYear(year);
		dossierStatisticData.setDoneCount(doneCount);
		dossierStatisticData.setRemainingCount(remainingCount);
		dossierStatisticData.setOnegateCount(onegateCount);
		dossierStatisticData.setOvertimeInside(overtimeInside);
		dossierStatisticData.setOvertimeOutside(overtimeOutside);
		dossierStatisticData.setDomainCode(domainCode);
		dossierStatisticData.setDomainName(domainName);
		dossierStatisticData.setGovAgencyCode(govAgencyCode);
		dossierStatisticData.setGovAgencyName(govAgencyName);
		dossierStatisticData.setSystem(system);
		dossierStatisticData.setCompanyId(companyId);
		dossierStatisticData.setGroupId(groupId);
		dossierStatisticData.setViaPostalCount(viaPostalCount);
		dossierStatisticData.setSaturdayCount(saturdayCount);
		dossierStatisticData.setDossierOnline3Count(dossierOnline3Count);
		dossierStatisticData.setDossierOnline4Count(dossierOnline4Count);
		dossierStatisticData.setReceiveDossierSatCount(receiveDossierSatCount);
		dossierStatisticData.setReleaseDossierSatCount(releaseDossierSatCount);
		dossierStatisticData.setGroupAgencyCode(groupAgencyCode);
		
		updateGovService.updateDossierStatistic(dossierStatisticData);
	}

}
