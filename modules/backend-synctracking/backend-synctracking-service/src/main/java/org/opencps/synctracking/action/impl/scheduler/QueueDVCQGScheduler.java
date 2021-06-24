package org.opencps.synctracking.action.impl.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.scheduler.PublishEventHSKMScheduler;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.synctracking.model.DossierTax;
import org.opencps.synctracking.service.DossierTaxLocalServiceUtil;
import org.opencps.synctracking.service.comparator.QueueDVCQGComparator;
import org.osgi.service.component.annotations.*;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@Component(immediate = true, service = QueueDVCQGScheduler.class)
public class QueueDVCQGScheduler extends BaseMessageListener {
    public static final int STATE_WAITING_SYNC = 1;
    public static final int STATE_DONE = 3;
    private static final Integer QUANTITY_JOB_DVCQG = 500;
    private volatile boolean isRunning = false;
    private static final String SERVER_CONFIG_NULL = "There is no server config frequency";
    private static final String PARSE_CONFIG_JSON_FAIL= "Create object json from config error";
    private static int timeSyncDossierDVCQG = Validator.isNotNull(PropsUtil.get("opencps.sync.dossiertax.time"))
            ? Integer.valueOf(PropsUtil.get("opencps.sync.dossiertax.time"))
            : 1440;
    private static final Boolean ENABLE_JOB = Validator.isNotNull(PropsUtil.get("org.opencps.dossiertax.enable"))
            ? Boolean.valueOf(PropsUtil.get("org.opencps.dossiertax.enable")) : false;

    //Start time config
    private static int HOUR_STATISTIC = Validator.isNotNull(PropsUtil.get("opencps.dossiertax.hour"))
            ? Integer.valueOf(PropsUtil.get("opencps.dossiertax.hour")) :-1;
    private static int MINUTE_STATISTIC = Validator.isNotNull(PropsUtil.get("opencps.dossiertax.minute"))
            ? Integer.valueOf(PropsUtil.get("opencps.dossiertax.minute")) :-1;
    private static int SECOND_STATISTIC = Validator.isNotNull(PropsUtil.get("opencps.dossiertax.second"))
            ? Integer.valueOf(PropsUtil.get("opencps.dossiertax.second")) :-1;
    static class Counter {
        private volatile static int count = 0;
        public static int getCount(){
            return count;
        }
        public static synchronized void decreaseCount(){
            count--;
        }

        public static synchronized void setCount(int countNew){
            count = countNew;
        }
    }
    private static volatile ThreadPoolExecutor threadPoolExecutor;

    private int corePoolSize    = 10;
    private int maximumPoolSize = 20;
    private int queueCapacity   = 5;
    private int keepAliveTime   = 10;
    public QueueDVCQGScheduler () {
        _log.info("Constructor QueueDVCQGScheduler");

        if(Validator.isNull(threadPoolExecutor)) {
            _log.info("Creating threadPoolExecutor first time...");
            threadPoolExecutor = new ThreadPoolExecutor(
                    corePoolSize, // Số thread mặc định được cấp để xử lý request
                    maximumPoolSize, //Số thread tối đa được dùng
                    keepAliveTime, //thời gian sống 1 thread nếu thread đang ko làm gì
                    java.util.concurrent.TimeUnit.SECONDS, //đơn vị thời gian
                    new ArrayBlockingQueue<>(queueCapacity), //Queue để lưu số lượng request chờ khi số thread trong
                    // corePoolSize được dùng hết, khi số lượng request = queueCapacity thì sẽ tạo 1 thread mới
                    new ThreadPoolExecutor.CallerRunsPolicy()); //Tự động xử lý exception khi số lượng request vượt quá queueCapacity
        }
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        _log.debug("=======START SEND : ====== : isRunning: "+ isRunning);
        if (!isRunning) {
            isRunning = true;
        } else {
            return;
        }

        if(!ENABLE_JOB) {
            return;
        }

        if(Counter.getCount() > 0) {
            return;
        }
        try {
            doProcess(message);
        }

        catch (Exception e) {
            _log.debug(e);
        }
        isRunning = false;
        _log.debug("------- END SEND : ------: isRunning: "+ isRunning);
    }

    private void doProcess(Message message) {
        _log.info("TASK SEND DVCQG time: " + timeSyncDossierDVCQG);
        _log.info("isRunning: " + isRunning + ", jobEnable: " + ENABLE_JOB + ", counting: " + Counter.getCount());
        // Lấy danh sách thông báo thuế và chứng từ thuế
        List<DossierTax> lstQueueTBT = DossierTaxLocalServiceUtil.getByStatusTBT(new int[] {
                        STATE_WAITING_SYNC }, 0, QUANTITY_JOB_DVCQG,
                new QueueDVCQGComparator(true, Field.CREATE_DATE, Date.class));

        List<DossierTax> lstQueueCTT = DossierTaxLocalServiceUtil.getByStatusCTT(STATE_DONE,
                        STATE_WAITING_SYNC , 0, QUANTITY_JOB_DVCQG,
                new QueueDVCQGComparator(true, Field.CREATE_DATE, Date.class));

        //Remove duplicated dossierId
        Set<Long> listDossierIdTBT = new HashSet<>();
        Set<Long> listDossierIdCTT = new HashSet<>();
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        if(lstQueueTBT !=null && lstQueueTBT.size()> 0) {
            for (DossierTax queue : lstQueueTBT) {
                // distinct thông báo thuế
                listDossierIdTBT.add(queue.getDossierId());

            }
        }
        if(lstQueueCTT !=null && lstQueueCTT.size()> 0) {
            for (DossierTax queue : lstQueueCTT) {
                // distinct chứng từ thuế
                listDossierIdCTT.add(queue.getDossierId());
            }
        }

        if(lstQueueTBT !=null && lstQueueTBT.size() >0){
            for(Long dossierId : listDossierIdTBT) {
                // Thông báo thuế
                JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
                jsonObject.put("dossierId", dossierId);
                jsonObject.put("type", false);
                jsonArray.put(jsonObject);
            }
        }

        if(listDossierIdCTT !=null && listDossierIdCTT.size() >0){
            for(Long dossierId : listDossierIdCTT) {
                // Chứng từ thuế
                JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
                jsonObject.put("dossierId", dossierId);
                jsonObject.put("type", true);
                jsonArray.put(jsonObject);
            }
        }
        if(Validator.isNull(jsonArray)) {
            _log.warn(  "Not found queue dvcqg with dossierId still running...");
            return;
        }
        try {
            // Thông báo thuế && Chứng từ thuế
            if(jsonArray !=null){
                Counter.setCount(jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    threadPoolExecutor.execute(() -> mainProcess(object));
                }
            }

        }catch (Exception e){
            e.getMessage();
        }
    }

    private void mainProcess(JSONObject object) {
        Long dossierId = object.getLong("dossierId");
        boolean type = object.getBoolean("type");
        _log.info("DossierId : " + dossierId);
        _log.info("type : " + type);
        Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

        if (type) {
            List<DossierTax> lstCTT = DossierTaxLocalServiceUtil.getByDossierIdAndStatusCTT(dossierId, 1);

            if (lstCTT == null && lstCTT.size() == 0) {
                return;
            }
            if (Validator.isNotNull(dossier)) {
                JSONObject result = doAction(dossier, type);
                for (DossierTax dossierTax : lstCTT) {
                    if (dossierTax.getStatusCTT() == 3) {
                        continue;
                    } else if (dossierTax.getStatusCTT() == 1) {
                        if (Validator.isNotNull(result) && Validator.isNotNull(result.getString("dossierActionId"))) {
                            dossierTax.setStatusCTT(3);
                            DossierTaxLocalServiceUtil.updateDossierTax(dossierTax);
                        } else {
                            dossierTax.setStatusCTT(4);
                            DossierTaxLocalServiceUtil.updateDossierTax(dossierTax);
                        }
                    }
                }
            }else{
                for (DossierTax dossierTax : lstCTT) {
                    if (dossierTax.getStatusCTT() == 3) {
                        continue;
                    } else if (dossierTax.getStatusCTT() == 1) {
                        dossierTax.setStatusCTT(4);
                        DossierTaxLocalServiceUtil.updateDossierTax(dossierTax);
                    }
                }
            }
        } else {

            List<DossierTax> lstTTB = DossierTaxLocalServiceUtil.getByDossierIdAndStatusTBT(dossierId, 1);
            if (lstTTB == null && lstTTB.size() == 0) {
                return;
            }
            if (Validator.isNotNull(dossier)) {
                JSONObject result = doAction(dossier, type);
                for (DossierTax dossierTax : lstTTB) {
                    if (dossierTax.getStatusTBT() == 3) {
                        continue;
                    } else if (dossierTax.getStatusCTT() == 1) {
                        if (Validator.isNotNull(result) && Validator.isNotNull(result.getString("dossierActionId"))) {
                            dossierTax.setStatusTBT(3);
                            DossierTaxLocalServiceUtil.updateDossierTax(dossierTax);
                        } else {
                            dossierTax.setStatusTBT(4);
                            DossierTaxLocalServiceUtil.updateDossierTax(dossierTax);
                        }
                    }
                }
            }else{
                for (DossierTax dossierTax : lstTTB) {
                    if (dossierTax.getStatusTBT() == 3) {
                        continue;
                    } else if (dossierTax.getStatusTBT() == 1) {
                        dossierTax.setStatusTBT(4);
                        DossierTaxLocalServiceUtil.updateDossierTax(dossierTax);
                    }
                }
            }

        }
        Counter.decreaseCount();
        _log.info("Counting remain: " + Counter.getCount());
        if (Counter.getCount() == 0) {
            _log.info("Time end: " + APIDateTimeUtils.convertDateToString(new Date()));
        }
    }

    public JSONObject getServerConfigByServerNo(String protocol, String serverNo) {
        // Get ServerConfig
        List<ServerConfig> listConfig = new ArrayList<>();
        if(Validator.isNotNull(protocol)) {
            listConfig = ServerConfigLocalServiceUtil.getByProtocol(protocol);
        }else if(Validator.isNotNull(serverNo)){
            listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(serverNo, "API_SYNC");
        }
        JSONObject configJson = JSONFactoryUtil.createJSONObject();
        try {
            ServerConfig serverConfig = listConfig.get(0);
            if (Validator.isNull(serverConfig.getConfigs())
                    || serverConfig.getConfigs().isEmpty()) {
                throw new Exception(SERVER_CONFIG_NULL);
            }
            configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

            if (Validator.isNull(configJson)) {
                throw new Exception(PARSE_CONFIG_JSON_FAIL);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return configJson;
    }
    private JSONObject doAction(Dossier dossier, boolean actionCTTD) {
        JSONObject resPostDossier = JSONFactoryUtil.createJSONObject();
        try {

            JSONObject action;
            JSONObject config = getServerConfigByServerNo(DossierTerm.DVCQG_THANH_TOAN_THUE,"");

            if (dossier.isOnline()) {
                // TODO: call api doaction to DVC
                action = config.getJSONObject(DossierTerm.ACTION_IS_ONLINE);

                String actionCode = action.getString(DossierTerm.ACTION_CODE);

                //url doAction
                // tạo file trc mới doAction tạo trên DVC
                // Hồ sơ trực tiếp sẽ lấy groupId bên DVC
                String url = action.getString(DossierTerm.URL);

                String username = action.getString(DossierTerm.USERNAME);

                String pwd = action.getString(DossierTerm.PWD);


                HashMap<String, String> properties = new HashMap<String, String>();

                properties.put(Field.GROUP_ID, action.getString(Field.GROUP_ID));

                String endPoint = DossierTerm.buildPathDoAction(url, dossier.getReferenceUid());
                resPostDossier.put("urlAction", endPoint);
                Map<String, Object> params = new HashMap<String, Object>();

                params.put(DossierTerm.ACTION_CODE, actionCode);

                _log.info("params============" + params);
                resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
                        properties, params, username, pwd);

                _log.info("=====resPostDossier=========" + resPostDossier);

                return resPostDossier;
            } else {//
                // tạo file trc mới doAction tạo trên DVC
                // Hs trực tuyến lấy groupId theo SERVER_ + GovAgencyCode của hồ sơ trong ServerConfig

                action = config.getJSONObject(DossierTerm.ACTION_IS_NOT_ONLINE);
                HashMap<String, String> properties = new HashMap<String, String>();
                if(Validator.isNotNull(dossier.getGovAgencyCode())){
                    JSONObject serverConfig = getServerConfigByServerNo("", "SERVER_" + dossier.getGovAgencyCode());
                    properties.put(Field.GROUP_ID, serverConfig.getString(Field.GROUP_ID)); //124302
                }

                properties.put(Field.GROUP_ID, action.getString(Field.GROUP_ID)); //124302

                String endPoint = DossierTerm.buildPathDoAction(action.getString(DossierTerm.URL),
                        dossier.getReferenceUid());

                resPostDossier.put("urlAction", endPoint);

                Map<String, Object> params = new HashMap<String, Object>();

                if(actionCTTD){
                    params.put(DossierTerm.ACTION_CODE, action.get(DossierTerm.ACTION_CODE_CHUNG_TU));
                }else {
                    params.put(DossierTerm.ACTION_CODE, action.get(DossierTerm.ACTION_CODE));
                }
                _log.info(endPoint);
                resPostDossier = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPoint,
                        properties, params, action.getString(DossierTerm.USERNAME), action.getString(DossierTerm.PWD));

                _log.info("=====resPostDossier=========" + resPostDossier);
                return resPostDossier;
            }

        } catch (Exception e) {
            _log.error(e);

        }
        return resPostDossier;
    }

    public JSONObject callPostAPI(String httpMethod, String accept, String urlPath, HashMap<String, String> properties,
                                  Map<String, Object> params, String username, String password) {

        JSONObject response = JSONFactoryUtil.createJSONObject();

        HttpURLConnection conn = null;

        BufferedReader br = null;

        try {

            URL url = new URL(urlPath);

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
            conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());
            conn.setRequestMethod(httpMethod);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty(ConstantUtils.VALUE_ACCEPT, accept);

            if (Validator.isNotNull(username) && Validator.isNotNull(password)) {
                String authString = username + StringPool.COLON + password;

                String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

                conn.setRequestProperty(ConstantUtils.VALUE_AUTHORIZATION, ConstantUtils.VALUE_BASIC + authStringEnc);
            }

            if (!properties.isEmpty()) {
                for (Map.Entry m : properties.entrySet()) {
                    conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
                }
            }

            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0)
                    postData.append(StringPool.AMPERSAND.charAt(0));
                postData.append(java.net.URLEncoder.encode(param.getKey(), ConstantUtils.UTF_8));
                postData.append(StringPool.EQUAL.charAt(0));
                postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), ConstantUtils.UTF_8));
            }

            byte[] postDataBytes = postData.toString().getBytes(ConstantUtils.UTF_8);

            conn.setRequestProperty(ConstantUtils.CONTENT_LENGTH, String.valueOf(postDataBytes.length));

            conn.getOutputStream().write(postDataBytes);

            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;

            StringBuilder sb = new StringBuilder();

            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            response = JSONFactoryUtil.createJSONObject(sb.toString());

            conn.disconnect();

        } catch (MalformedURLException e) {
            _log.error("Can't invoke api " + urlPath);
            _log.error(e);
        } catch (IOException e) {
            _log.error("Can't invoke api " + urlPath);
            _log.error(e);
        } catch (JSONException e) {
            _log.error("Can't invoke api " + urlPath);
            _log.error(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    _log.error(e);
                }
            }

        }

        return response;
    }
    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) throws SchedulerException {
        String listenerClass = getClass().getName();
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

        Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, startDate, null, timeSyncDossierDVCQG, TimeUnit.MINUTE);

        _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
        _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);


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
        isRunning = false;
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

    private Log _log = LogFactoryUtil.getLog(QueueDVCQGScheduler.class);
}
