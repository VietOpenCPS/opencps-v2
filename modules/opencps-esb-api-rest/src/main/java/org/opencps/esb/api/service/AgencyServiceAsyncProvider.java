package org.opencps.esb.api.service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.inet.api.ClientConfiguration;
import com.inet.api.iws.auth.horae.HoraeCredentials;
import com.inet.api.iws.services.agency.AgencyServiceAsync;
import com.inet.api.iws.services.agency.AgencyServiceAsyncClient;

/**
 * AgencyServiceAsyncProvider.
 *
 * @author: Dzung Nguyen
 * @version: $Id AgencyServiceAsyncProvider 2013-09-18 12:10:30z nguyen_dv $
 * @since 1.0
 */
public class AgencyServiceAsyncProvider implements Provider<AgencyServiceAsync> {
  //~ class properties ========================================================
  private final AgencyServiceAsync agencyService;

  //~ class members ===========================================================
  @Inject
  public AgencyServiceAsyncProvider(HoraeCredentials credentials, ClientConfiguration configuration) {
    this.agencyService = new AgencyServiceAsyncClient(credentials, configuration);
  }

  public AgencyServiceAsync get() {
    return agencyService;
  }
}
