package org.opencps.statistic.rest.util;

import java.util.Locale;

public interface DossierStatisticConstants {
	String DOMAIN_TOTAL = "total";
	String AGENCY_TOTAL = "total";
	String DOMAIN_AGENCY = "total";
	int REPORTING_TYPE_0 = 0;
	int REPORTING_TYPE_1 = 1;
	
	String GOV_AGENCY_GROUP_CODE = "GOVERNMENT_AGENCY";
	
	long GROUP_ID_PROCESS_PORTAL = 55301;
	long GROUP_ID_RECEIVED_PORTAL = 55217;
	
	String GOV_AGENCY_ENDPOINT = "opencps.rest.enpoind.govagency";
	String SERVICE_DOMAIN_ENDPOINT = "opencps.rest.enpoind.servicedomain";
	
	String DOSSIER_ENDPOINT = "opencps.rest.enpoind.dossier";
	
	String SERVICE_INFO_ENDPOINT = "opencps.rest.enpoint.service.info";
	
	String OPENCPS_AUTHENCATION = "opencps.rest.authentication";
	
	String OPENCPS_GROUP_CONFIG = "opencps.group.id.config";
	
	String GROUP_ID = "groupId";
	
	int START_YEARS = 1990;
	
	Locale VIETNAM = new Locale("vi", "VN");
	
	long STATISTIC_USER_ID = -1;
	String STATISTIC_USER_NAME = "ADM";

}
