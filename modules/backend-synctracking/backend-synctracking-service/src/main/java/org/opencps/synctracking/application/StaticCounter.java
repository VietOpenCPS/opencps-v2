package org.opencps.synctracking.application;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class StaticCounter {
    private final static Log _log = LogFactoryUtil.getLog(StaticCounter.class);
    public synchronized static void increaseCounter(String pattern) {
        try {
            _log.info("Cache count 111:" + SyncTrackingApplication.CounterSyncTracking.getCount());

            Counter currentCounter = CounterLocalServiceUtil.fetchCounter(pattern);
            long currentCounterInDb = currentCounter.getCurrentId();
            int i = 0;
            _log.info("Current in db1111:" + currentCounterInDb);

//            while (currentCounterInDb <= CounterSyncTracking.getCount() && i<=9) {
//                Thread.sleep(500);
//                _log.info("Sleep 0.5s");
//                i++;
//            }

            long counterNumber = currentCounterInDb + 1;

            currentCounter.setCurrentId(counterNumber);
//            Counter newCounter = CounterLocalServiceUtil.updateCounter(currentCounter);
//            SyncTrackingApplication.CounterSyncTracking.setCount(counterNumber);
//            _log.info("Current in db after update:" + newCounter.getCurrentId());
        } catch (Exception e) {
            _log.error(e);
        }
    }

}
