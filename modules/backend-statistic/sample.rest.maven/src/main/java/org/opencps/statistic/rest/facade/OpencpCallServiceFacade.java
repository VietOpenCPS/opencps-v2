package org.opencps.statistic.rest.facade;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public interface OpencpCallServiceFacade<R1, R2> {
	R2 callWebPage(R1 payload) throws UpstreamServiceTimedOutException, UpstreamServiceFailedException;
}
