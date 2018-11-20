package org.opencps.sms.service;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public interface DossierLookUpFacade<R1, R2> {
    R2 callDossierRestService(R1 payload) throws UpstreamServiceTimedOutException, UpstreamServiceFailedException;
}
