package org.opencps.statistic.rest.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticKey;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;

import java.util.*;

public class DossierSyncStatisticUtil {

    private Log _log = LogFactoryUtil.getLog(DossierSyncStatisticUtil.class.getName());

    private DossierStatisticData processCalStatistic(long groupId, int month, int year, String govAgencyCode, String domainCode,
                                                     String groupGovAgencyCode, String system, int reporting,
                                                     List<OpencpsDossierStatistic> dossierStatisticList) {
        DossierStatisticData dossierStatistic = null;
        int totalCount = 0;
        int deniedCount = 0;
        int cancelledCount = 0;
        int processCount = 0;
        int remainingCount = 0;
        int receivedCount = 0;
        int onlineCount = 0;
        int fromViaPostalCount = 0;
        int releaseCount = 0;
        int betimesCount = 0;
        int ontimeCount = 0;
        int overtimeCount = 0;
        int doneCount = 0;
        int releasingCount = 0;
        int unresolvedCount = 0;
        int processingCount = 0;
        int undueCount = 0;
        int overdueCount = 0;
        int ontimePercentage = 100;
        int overtimeInside = 0;
        int overtimeOutside = 0;
        int interoperatingCount = 0;
        int waitingCount = 0;
        int onegateCount = 0;
        int outsideCount = 0;
        int insideCount = 0;
        int viaPostalCount = 0;
        int saturdayCount = 0;
        int dossierOnline3Count = 0;
        int dossierOnline4Count = 0;
        int receiveDossierSatCount = 0;
        int releaseDossierSatCount = 0;
        long companyId = 0;
        int processingInAPeriodCount = 0;
        int releaseInAPeriodCount = 0;
        if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
            dossierStatistic = new DossierStatisticData();
            for (OpencpsDossierStatistic opencpsDossierStatistic : dossierStatisticList) {
                //
                companyId = opencpsDossierStatistic.getCompanyId() > 0 ? opencpsDossierStatistic.getCompanyId() : 0;
                //
                totalCount += opencpsDossierStatistic.getTotalCount();
                deniedCount += opencpsDossierStatistic.getDeniedCount();
                cancelledCount += opencpsDossierStatistic.getCancelledCount();
                processCount += opencpsDossierStatistic.getProcessCount();
                remainingCount += opencpsDossierStatistic.getRemainingCount();
                receivedCount += opencpsDossierStatistic.getReceivedCount();
                onlineCount += opencpsDossierStatistic.getOnlineCount();
                fromViaPostalCount += opencpsDossierStatistic.getFromViaPostalCount();
                releaseCount += opencpsDossierStatistic.getReleaseCount();
                betimesCount += opencpsDossierStatistic.getBetimesCount();
                ontimeCount += opencpsDossierStatistic.getOntimeCount();
                overtimeCount += opencpsDossierStatistic.getOvertimeCount();
                doneCount += opencpsDossierStatistic.getDoneCount();
                releasingCount += opencpsDossierStatistic.getReleasingCount();
                unresolvedCount += opencpsDossierStatistic.getUnresolvedCount();
                processingCount += opencpsDossierStatistic.getProcessingCount();
                undueCount += opencpsDossierStatistic.getUndueCount();
                overdueCount += opencpsDossierStatistic.getOverdueCount();
                overtimeInside += opencpsDossierStatistic.getOvertimeInside();
                overtimeOutside += opencpsDossierStatistic.getOvertimeOutside();
                interoperatingCount += opencpsDossierStatistic.getInteroperatingCount();
                waitingCount += opencpsDossierStatistic.getWaitingCount();
                onegateCount += opencpsDossierStatistic.getOnegateCount();
                outsideCount += opencpsDossierStatistic.getOutsideCount();
                insideCount += opencpsDossierStatistic.getInsideCount();
                viaPostalCount += opencpsDossierStatistic.getViaPostalCount();
                saturdayCount += opencpsDossierStatistic.getSaturdayCount();
                dossierOnline3Count += opencpsDossierStatistic.getDossierOnline3Count();
                dossierOnline4Count += opencpsDossierStatistic.getDossierOnline4Count();
                receiveDossierSatCount += opencpsDossierStatistic.getReceiveDossierSatCount();
                releaseDossierSatCount += opencpsDossierStatistic.getReleaseDossierSatCount();
                processingInAPeriodCount += opencpsDossierStatistic.getProcessingInAPeriodCount();
                releaseInAPeriodCount += opencpsDossierStatistic.getReleaseInAPeriodCount();
            }
            //
            if (releaseCount > 0) {
                ontimePercentage = (betimesCount + ontimeCount) * 100 / releaseCount;
            }
            //
            dossierStatistic.setCompanyId(companyId);
            dossierStatistic.setGroupId(groupId);
            dossierStatistic.setMonth(month);
            dossierStatistic.setYear(year);
            dossierStatistic.setSystem(system);
            dossierStatistic.setTotalCount(totalCount);
            dossierStatistic.setDeniedCount(deniedCount);
            dossierStatistic.setCancelledCount(cancelledCount);
            dossierStatistic.setProcessCount(processCount);
            dossierStatistic.setRemainingCount(remainingCount);
            dossierStatistic.setReceivedCount(receivedCount);
            dossierStatistic.setOnlineCount(onlineCount);
            dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
            dossierStatistic.setReleaseCount(releaseCount);
            dossierStatistic.setBetimesCount(betimesCount);
            dossierStatistic.setOntimeCount(ontimeCount);
            dossierStatistic.setOvertimeCount(overtimeCount);
            dossierStatistic.setDoneCount(doneCount);
            dossierStatistic.setReleasingCount(releasingCount);
            dossierStatistic.setUnresolvedCount(unresolvedCount);
            dossierStatistic.setProcessingCount(processingCount);
            dossierStatistic.setUndueCount(undueCount);
            dossierStatistic.setOverdueCount(overdueCount);
            dossierStatistic.setOntimePercentage(ontimePercentage);
            dossierStatistic.setOvertimeInside(overtimeInside);
            dossierStatistic.setOvertimeOutside(overtimeOutside);
            dossierStatistic.setInteroperatingCount(interoperatingCount);
            dossierStatistic.setWaitingCount(waitingCount);
            dossierStatistic.setGovAgencyCode(govAgencyCode);
            dossierStatistic.setGovAgencyName(dossierStatisticList.get(0).getGovAgencyName());
            dossierStatistic.setDomainCode(domainCode);
            dossierStatistic.setDomainName(dossierStatisticList.get(0).getDomainName());
            dossierStatistic.setReporting(reporting);
            dossierStatistic.setOnegateCount(onegateCount);
            dossierStatistic.setOutsideCount(outsideCount);
            dossierStatistic.setInsideCount(insideCount);
            dossierStatistic.setViaPostalCount(viaPostalCount);
            dossierStatistic.setSaturdayCount(saturdayCount);
            dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
            dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
            dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
            dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);
            dossierStatistic.setGroupAgencyCode(groupGovAgencyCode);
            dossierStatistic.setProcessingInAPeriodCount(processingInAPeriodCount);
            dossierStatistic.setReleaseInAPeriodCount(releaseInAPeriodCount);
        }
        //
        return dossierStatistic;
    }

    public void processSyncStatistic()  {

        try {

            List<ServerConfig> configList = ServerConfigLocalServiceUtil.getByServerAndProtocol("SERVER_STATISTIC_DVC", DossierStatisticConstants.STATISTIC_PROTOCOL);

            ServerConfig config = null;
            if (configList != null && configList.size() > 0) {
                config = configList.get(0);
                if (config == null) {
                    return;
                }
            } else {
                return ;
            }

            JSONObject scObject = JSONFactoryUtil.createJSONObject(config.getConfigs());

            long groupId = 0;
            if (scObject != null) {
                if (scObject.has(Field.GROUP_ID)) {
                    groupId = scObject.getLong(Field.GROUP_ID) > 0 ? scObject.getLong(Field.GROUP_ID) : 35417;
                }
            }
            StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

            int[] reportArr = {0, 1};
            List<OpencpsDossierStatistic> statisticList = OpencpsDossierStatisticLocalServiceUtil.findByREPO_ARR(reportArr);

            Map<String, DossierStatisticKey> mapKey = null;
            DossierStatisticKey statisticKey = null;
            if (statisticList != null && statisticList.size() > 0) {
                mapKey = new HashMap<String, DossierStatisticKey>();
                for (OpencpsDossierStatistic opencpsDossierStatistic : statisticList) {
                    StringBuilder sb = new StringBuilder();
                    if (Validator.isNotNull(opencpsDossierStatistic.getGovAgencyCode())) {
                        sb.append(opencpsDossierStatistic.getGovAgencyCode());
                    } else {
                        sb.append("all");
                    }
                    if (Validator.isNotNull(opencpsDossierStatistic.getDomainCode())) {
                        sb.append(StringPool.AT);
                        sb.append(opencpsDossierStatistic.getDomainCode());
                    } else {
                        sb.append(StringPool.AT);
                        sb.append("all");
                    }
                    if (Validator.isNotNull(opencpsDossierStatistic.getSystem())) {
                        sb.append(StringPool.AT);
                        sb.append(opencpsDossierStatistic.getSystem());
                    } else {
                        sb.append(StringPool.AT);
                        sb.append("all");
                    }
                    if (Validator.isNotNull(opencpsDossierStatistic.getGroupAgencyCode())) {
                        sb.append(StringPool.AT);
                        sb.append(opencpsDossierStatistic.getGroupAgencyCode());
                    } else {
                        sb.append(StringPool.AT);
                        sb.append("all");
                    }
                    //month and year
                    sb.append(StringPool.AT);
                    sb.append(opencpsDossierStatistic.getMonth());
                    sb.append(StringPool.AT);
                    sb.append(opencpsDossierStatistic.getYear());

                    if (mapKey.isEmpty() || !mapKey.containsKey(sb.toString())
                            || (mapKey.containsKey(sb.toString()) && opencpsDossierStatistic.getReporting() == 0)) {
                        statisticKey = new DossierStatisticKey();
                        if (Validator.isNotNull(opencpsDossierStatistic.getGovAgencyCode())) {
                            statisticKey.setGovAgencyCode(opencpsDossierStatistic.getGovAgencyCode());
                        }
                        if (Validator.isNotNull(opencpsDossierStatistic.getDomainCode())) {
                            statisticKey.setDomainCode(opencpsDossierStatistic.getDomainCode());
                        }
                        if (Validator.isNotNull(opencpsDossierStatistic.getSystem())) {
                            statisticKey.setSystem(opencpsDossierStatistic.getSystem());
                        }
                        if (Validator.isNotNull(opencpsDossierStatistic.getGroupAgencyCode())) {
                            statisticKey.setGroupAgencyCode(opencpsDossierStatistic.getGroupAgencyCode());
                        }
                        if (Validator.isNotNull(opencpsDossierStatistic.getReporting())) {
                            statisticKey.setReporting(opencpsDossierStatistic.getReporting());
                        }
                        statisticKey.setMonth(opencpsDossierStatistic.getMonth());
                        statisticKey.setYear(opencpsDossierStatistic.getYear());
                        mapKey.put(sb.toString(), statisticKey);
                    }
                }
            }

            Map<String, DossierStatisticData> mapStatistic = new HashMap<>();
            for (Map.Entry<String, DossierStatisticKey> entry : mapKey.entrySet()) {

                DossierStatisticKey objectKey = entry.getValue();
                //lay d/s statistic dong bo tu mcdt(NOT_G) de tong hop lai
                List<OpencpsDossierStatistic> dossierStatisticList = OpencpsDossierStatisticLocalServiceUtil
                        .getByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, objectKey.getMonth(), objectKey.getYear(),
                                objectKey.getGovAgencyCode(), objectKey.getDomainCode(), objectKey.getGroupAgencyCode(),
                                objectKey.getSystem());

                //_log.info("mapKey.getKey: "+entry.getKey());
                //int size = dossierStatisticList != null ? dossierStatisticList.size() : -1;
                //_log.info("mapKey_dossierStatisticList: "+ size);
                //

                //hot fix by TrungNT
                // hs tu nsw do truc tiep vao DVC(ko dong bo tu mcdt sang), vi the khi tinh lai ban ghi co system = null phai cong them hs cua nsw vao(hs cua NSW co system=1)

                List<OpencpsDossierStatistic> temps = new ArrayList<OpencpsDossierStatistic>();

                if(dossierStatisticList != null) {
                    temps.addAll(dossierStatisticList);
                }

                if(Validator.isNull(objectKey.getSystem())) {

                    List<OpencpsDossierStatistic> dossierStatisticListTmp = OpencpsDossierStatisticLocalServiceUtil
                            .getByG_M_Y_GOV_DOM_GRO_SYS(groupId, objectKey.getMonth(), objectKey.getYear(),
                                    objectKey.getGovAgencyCode(), objectKey.getDomainCode(), objectKey.getGroupAgencyCode(),
                                    String.valueOf(1));

                    _log.info("DossierSyncStatisticScheduler key " + entry.getKey() + "|" + dossierStatisticListTmp != null ? dossierStatisticListTmp.size() : "dossierStatisticListTmp null");

                    if(dossierStatisticListTmp != null && !dossierStatisticListTmp.isEmpty()) {
                        temps.addAll(dossierStatisticListTmp);
                    }
                }

                DossierStatisticData dossierStatistic = processCalStatistic(groupId, objectKey.getMonth(), objectKey.getYear(), objectKey.getGovAgencyCode(), objectKey.getDomainCode(), objectKey.getGroupAgencyCode(),
                        objectKey.getSystem(), objectKey.getReporting(), temps);
                //
                if (dossierStatistic != null) {
                    mapStatistic.put(entry.getKey(), dossierStatistic);
                }
            }
            // Convert Map to List jsonObject
            StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
            List<JSONObject> lstDossierDataObjs = statisticEngineUpdate.convertStatisticDataList(mapStatistic);

            engineUpdateAction.updateStatistic(lstDossierDataObjs);


        }
        catch (Exception e) {
            _log.error(e);
        }

    }
}
