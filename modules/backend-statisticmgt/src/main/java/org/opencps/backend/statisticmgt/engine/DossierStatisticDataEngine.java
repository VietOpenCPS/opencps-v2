package org.opencps.backend.statisticmgt.engine;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = DossierStatisticDataEngine.class)
public class DossierStatisticDataEngine extends BaseMessageListener{

	private volatile boolean isRunningDossier = false;

	protected Log _log = LogFactoryUtil.getLog(DossierStatisticDataEngine.class);

	@Override
	protected void doReceive(Message message) throws Exception {
		
		_log.debug("START STATISTIC DOSSIER: " + isRunningDossier);
		if (!isRunningDossier) {
			isRunningDossier = true;
		}
		else {
			return;
		}
		long startTime = System.currentTimeMillis();
		Date nowLog = new Date();
		try {
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
