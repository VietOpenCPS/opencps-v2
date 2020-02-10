
package org.opencps.sms.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;

import org.opencps.sms.service.dto.DossierRequest;
import org.opencps.sms.service.dto.DossierResponse;
import org.opencps.sms.service.util.Constants;
import org.opencps.sms.service.util.DossierServiceProps;
import org.opencps.sms.service.util.ServerConfigContants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;
import opencps.statistic.common.webservice.facade.OpencpsRestFacade;

public class DossierLookUpFacadeImpl extends OpencpsRestFacade<DossierRequest, DossierResponse>
    implements DossierLookUpFacade<DossierRequest, DossierResponse> {

    private static final Log _log = LogFactoryUtil.getLog(DossierLookUpFacadeImpl.class);

    @Override
    public DossierResponse callDossierRestService(DossierRequest payload)
        throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {

        return makeServiceCall(payload);
    }

    @Override
    protected DossierResponse makeServiceCall(DossierRequest payload)
        throws UpstreamServiceTimedOutException, UpstreamServiceFailedException {

        MultiValueMap<String, String> urlQueryParams = new LinkedMultiValueMap<>();

        urlQueryParams.add(Constants.DOSSIER_LOOKUP_KEY_START, Integer.toString(payload.getStart()));
        urlQueryParams.add(Constants.DOSSIER_LOOKUP_KEY_END, Integer.toString(payload.getEnd()));
        urlQueryParams.add(Constants.DOSSIER_LOOKUP_KEY_DOSSIER_NO, payload.getDossierNo());
        urlQueryParams.add(Constants.DOSSIER_LOOKUP_KEY_APPLICANT_ID_NO, payload.getApplicantIdNo());

        String endPoint = DossierServiceProps.get(Constants.OPENCPS_REST_ENDPOINT_DOSSIER);
        _log.info("=======SMS DOSSIER ENDPOINT=====" + endPoint);
        HashMap<String, String> urlPathSegments = new HashMap<>();
        String url = buildUrl(endPoint, urlPathSegments, urlQueryParams);

        HttpHeaders httpHeaders = new HttpHeaders();
		if (Validator.isNotNull(PropsUtil.get(ServerConfigContants.SERVER_SYNC_KEY))
				&& Validator.isNotNull(PropsUtil.get(ServerConfigContants.SERVER_SYNC_SECRET))) {
			setHttpHeadersAuthorization(httpHeaders, PropsUtil.get(ServerConfigContants.SERVER_SYNC_KEY), PropsUtil.get(ServerConfigContants.SERVER_SYNC_SECRET));
		}
		else {
	        httpHeaders = setHttpHeadersAuthorization(
	            httpHeaders, DossierServiceProps.get(Constants.OPENCPS_BACKEND_USERNAME),
	            DossierServiceProps.get(Constants.OPENCPS_BACKEND_SECRET));
		}
        httpHeaders.add(Field.GROUP_ID, DossierServiceProps.get(Constants.OPENCPS_GROUP_ID_CONFIG));
        
        return (DossierResponse) this.executeGenericRestCall(
            url, HttpMethod.GET, httpHeaders, payload, DossierResponse.class).getBody();
    }

}
