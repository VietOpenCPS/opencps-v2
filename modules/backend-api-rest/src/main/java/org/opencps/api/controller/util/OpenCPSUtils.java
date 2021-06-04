package org.opencps.api.controller.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.adminconfig.model.ApiManager;
import org.opencps.adminconfig.model.SyncTracking;
import org.opencps.adminconfig.service.ApiManagerLocalServiceUtil;
import org.opencps.adminconfig.service.SyncTrackingLocalServiceUtil;
import org.opencps.api.adminconfig.model.DtoResponse;
import org.opencps.api.adminconfig.model.SyncTrackingQuery;
import org.opencps.api.adminconfig.model.SyncTrackingResponse;

public class OpenCPSUtils {
	
	private static Log _log = LogFactoryUtil.getLog(OpenCPSUtils.class);
	private static final Integer MAX_LIMIT = 1000;
	private static final String FORMAT_DATE_FRONT_END = "d/M/yyyy";
	public static final Integer STATE_SUCCESS = 1;
    public static final Integer STATE_ERROR = 2;
	
	public static void addSyncTracking(String apiCode, long userId, long groupId,
			String dossierNo, String referenceUid, String serviceCode, 
			int stateSync, String bodyRequest, String bodyResponse) {
		
		ApiManager apiManager = ApiManagerLocalServiceUtil.findByApiCode(apiCode);
		if (apiManager != null) {
			
			try {
				SyncTracking syncTracking = SyncTrackingLocalServiceUtil.updateSyncTracking(
						userId, groupId, 0l, dossierNo, referenceUid, 
						serviceCode, stateSync, apiManager.getApiCode(),
						JSONFactoryUtil.looseSerialize(bodyRequest),
						JSONFactoryUtil.looseSerialize(bodyResponse));
				
				_log.debug("SyncTracking : " + JSONFactoryUtil.looseSerialize(syncTracking));
			} catch (Exception e) {
				_log.error(e.getMessage());
			}
			
		}
	}
	
	public DtoResponse getLogReports(SyncTrackingQuery syncTrackingQuery) throws Exception {
		try {
            DtoResponse response = new DtoResponse();
            if(Validator.isNull(syncTrackingQuery)) {
                throw new Exception("No query param was found");
            }


            if(Validator.isNull(syncTrackingQuery.start)) {
                throw new Exception("No start index was found");
            }

            if(Validator.isNull(syncTrackingQuery.end)) {
                throw new Exception("No end index was found");
            }

            if(syncTrackingQuery.start >= syncTrackingQuery.end) {
                throw new Exception("Start is not smaller than end");
            }

            if(syncTrackingQuery.end >= MAX_LIMIT) {
                syncTrackingQuery.end = MAX_LIMIT;
                if(syncTrackingQuery.end < syncTrackingQuery.start) {
                    syncTrackingQuery.start = syncTrackingQuery.end - 10;
                }
            }

            String fromDateString = Validator.isNotNull(syncTrackingQuery.fromDate)
                    && !syncTrackingQuery.fromDate.isEmpty()
                    ? syncTrackingQuery.fromDate : "1/10/2020";

            String toDateString = Validator.isNotNull(syncTrackingQuery.toDate)
                    && !syncTrackingQuery.toDate.isEmpty()
                    ? syncTrackingQuery.toDate : "1/10/2100";

            Date fromDateParsed = OpenCPSUtils.stringToDate(fromDateString, FORMAT_DATE_FRONT_END);
            Date toDateParsed   = OpenCPSUtils.stringToDate(toDateString, FORMAT_DATE_FRONT_END);

            boolean hasDossierNo = false;
            boolean hasServiceCode = false;
            boolean hasApi = false;

            if(Validator.isNotNull(syncTrackingQuery.dossierNo) && !syncTrackingQuery.dossierNo.isEmpty()) {
                SyncTracking syncTracking;
                syncTracking = SyncTrackingLocalServiceUtil.getByDossierNo(syncTrackingQuery.dossierNo);

                if(Validator.isNull(syncTracking)) {
                    syncTracking = SyncTrackingLocalServiceUtil.getByReferenceUid(syncTrackingQuery.dossierNo);
                }

                if(Validator.isNotNull(syncTracking)) {
                    syncTrackingQuery.dossierNo = syncTracking.getReferenceUid();
                }
                hasDossierNo = true;
            }

            if(Validator.isNotNull(syncTrackingQuery.serviceCode) && !syncTrackingQuery.serviceCode.isEmpty()) {
                hasServiceCode = true;
            }
            
            if(Validator.isNotNull(syncTrackingQuery.api) && !syncTrackingQuery.api.isEmpty()) {
                hasApi = true;
            }
            
            List<SyncTracking> syncTrackingList;
            List<SyncTracking> syncTrackingPaginate;
            
            _log.info("33333");
            // 1-1-1| 1-1-0| 1-0-0| 1-0-1| 0-1-1| 0-1-0| 0-0-1| 0-0-0
            _log.info("hasApi :" + hasApi);
            _log.info("hasDossierNo :" + hasDossierNo);
            _log.info("hasServiceCode :" + hasServiceCode);
            _log.info("syncTrackingQuery :" + JSONFactoryUtil.looseSerialize(syncTrackingQuery));
            
            if(hasApi && hasDossierNo && hasServiceCode) {
            	_log.info("1111111");
            	syncTrackingList = SyncTrackingLocalServiceUtil.getByApiAndServiceCodeAndDossierNoAndDate(
            			syncTrackingQuery.api, syncTrackingQuery.dossierNo, syncTrackingQuery.serviceCode,
            			fromDateParsed, toDateParsed, 0, MAX_LIMIT);
            	
            	syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByApiAndServiceCodeAndDossierNoAndDate(
            			syncTrackingQuery.api, syncTrackingQuery.dossierNo, syncTrackingQuery.serviceCode,
            			fromDateParsed, toDateParsed, syncTrackingQuery.start, syncTrackingQuery.end);
            	
            } else if (hasApi && hasDossierNo && !hasServiceCode) {
            	_log.info("2222222");
            	syncTrackingList = SyncTrackingLocalServiceUtil.getByApiAndDossierNoAndDate(
            			syncTrackingQuery.api, syncTrackingQuery.dossierNo, 
            			fromDateParsed, toDateParsed, 0, MAX_LIMIT);
            	
            	syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByApiAndDossierNoAndDate(
            			syncTrackingQuery.api, syncTrackingQuery.dossierNo, 
            			fromDateParsed, toDateParsed, syncTrackingQuery.start, syncTrackingQuery.end);
            	
            } else if (hasApi && !hasDossierNo && !hasServiceCode) {
            	_log.info("3333333");
            	syncTrackingList = SyncTrackingLocalServiceUtil.getByApiAndDate(
            			syncTrackingQuery.api, fromDateParsed, toDateParsed, 0, MAX_LIMIT);
            	
            	syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByApiAndDate(syncTrackingQuery.api, fromDateParsed, toDateParsed, 
            			syncTrackingQuery.start, syncTrackingQuery.end);

            } else if (hasApi && !hasDossierNo && hasServiceCode) {
            	_log.info("4444444");
            	syncTrackingList = SyncTrackingLocalServiceUtil.getByApiAndServiceCodeAndDate(
            			syncTrackingQuery.api, syncTrackingQuery.serviceCode, 
            			fromDateParsed, toDateParsed, 0, MAX_LIMIT);
            	
            	syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByApiAndServiceCodeAndDate(
            			syncTrackingQuery.api, syncTrackingQuery.serviceCode, 
            			fromDateParsed, toDateParsed, syncTrackingQuery.start, syncTrackingQuery.end);

            } else if (!hasApi && hasDossierNo && hasServiceCode) {
            	_log.info("5555555");
            	syncTrackingList = SyncTrackingLocalServiceUtil.getByDossierNoAndServiceCodeAndDate(
                        syncTrackingQuery.dossierNo, syncTrackingQuery.serviceCode,
                        fromDateParsed, toDateParsed,
                        0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByDossierNoAndServiceCodeAndDate(
                        syncTrackingQuery.dossierNo, syncTrackingQuery.serviceCode,
                        fromDateParsed, toDateParsed,
                        syncTrackingQuery.start, syncTrackingQuery.end
                );
            	
            } else if (!hasApi && hasDossierNo && !hasServiceCode) {
            	_log.info("6666666");
            	syncTrackingList = SyncTrackingLocalServiceUtil.getByReferenceUidAndDate(
                        syncTrackingQuery.dossierNo, fromDateParsed, toDateParsed,
                        0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByReferenceUidAndDate(
                        syncTrackingQuery.dossierNo, fromDateParsed, toDateParsed,
                        syncTrackingQuery.start, syncTrackingQuery.end
                );
                
            } else if (!hasApi && !hasDossierNo && hasServiceCode) {
            	_log.info("7777777");
            	syncTrackingList = SyncTrackingLocalServiceUtil.getByServiceCodeAndDate(
                        syncTrackingQuery.serviceCode, fromDateParsed, toDateParsed,
                        0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByServiceCodeAndDate(
                        syncTrackingQuery.serviceCode, fromDateParsed, toDateParsed,
                        syncTrackingQuery.start, syncTrackingQuery.end
                );
                
            } else {
            	_log.info("8888888");
            	
            	syncTrackingList = SyncTrackingLocalServiceUtil.getByDate(
                        fromDateParsed, toDateParsed,0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByDate(
                        fromDateParsed, toDateParsed,syncTrackingQuery.start, syncTrackingQuery.end
                );
                
            } 

            if(Validator.isNull(syncTrackingList) || syncTrackingList.size() == 0) {
                response.setTotal(0);
                response.getData().addAll(new ArrayList<>());
                return response;
            }

            List<SyncTrackingResponse> syncTrackingResponse = this.transForm(syncTrackingPaginate);
            response.setTotal(syncTrackingList.size());
            response.getData().addAll(syncTrackingResponse);
            return response;
        } catch (Exception e) {
        	e.printStackTrace();
            _log.error(e.getMessage());
        }
		return null;
	}
	
	private static Date stringToDate(String dateTime, String formatDate) {
        try {
            DateFormat inputFormatter = new SimpleDateFormat(formatDate);
            return inputFormatter.parse(dateTime);
        } catch (Exception e) {
            System.out.println("Error when transform string => date");
            return null;
        }
    }
	
	private List<SyncTrackingResponse> transForm(List<SyncTracking> listTracking) throws Exception{
        try {
            List<SyncTrackingResponse> listTrackingTransform = new ArrayList<>();
            SyncTrackingResponse oneTrackingTransform;
            for(SyncTracking syncTracking : listTracking) {
                oneTrackingTransform = new SyncTrackingResponse();
                oneTrackingTransform.api = syncTracking.getApi();
                ApiManager apiManager = ApiManagerLocalServiceUtil.findByApiCode(syncTracking.getApi());
                if (Validator.isNotNull(apiManager)) {
                	oneTrackingTransform.api = apiManager.getApiName();
                }
                oneTrackingTransform.bodyRequest = Validator.isNotNull(syncTracking.getBodyRequest())
                        ? syncTracking.getBodyRequest() : "";
                oneTrackingTransform.bodyResponse = Validator.isNotNull(syncTracking.getResponse())
                        ? syncTracking.getResponse() : "";
                oneTrackingTransform.createDate = Validator.isNotNull(syncTracking.getCreateDate())
                        ? syncTracking.getCreateDate().getTime() : 0;
                oneTrackingTransform.dossierNo = Validator.isNotNull(syncTracking.getDossierNo())
                        ? syncTracking.getDossierNo(): "";
                oneTrackingTransform.serviceCode = Validator.isNotNull(syncTracking.getServiceCode())
                        ? syncTracking.getServiceCode(): "";
                oneTrackingTransform.fromUnit = Validator.isNotNull(syncTracking.getFromUnit())
                        ? syncTracking.getFromUnit() : "";
                oneTrackingTransform.toUnit = Validator.isNotNull(syncTracking.getToUnit())
                        ? syncTracking.getToUnit() : "";
                oneTrackingTransform.stateSync = Validator.isNotNull(syncTracking.getStateSync())
                        ? syncTracking.getStateSync() : OpenCPSUtils.STATE_ERROR;
                oneTrackingTransform.trackingId = Validator.isNotNull(syncTracking.getTrackingId())
                        ? syncTracking.getTrackingId() : 0;
                oneTrackingTransform.groupId = Validator.isNotNull(syncTracking.getGroupId())
                		? syncTracking.getGroupId() : 0;
                listTrackingTransform.add(oneTrackingTransform);
            }
            return listTrackingTransform;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
