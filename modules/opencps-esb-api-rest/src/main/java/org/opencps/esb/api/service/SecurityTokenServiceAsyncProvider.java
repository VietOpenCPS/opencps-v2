package org.opencps.esb.api.service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.inet.api.ClientConfiguration;
import com.inet.api.iws.auth.horae.HoraeCredentials;
import com.inet.api.iws.services.sts.SecurityTokenServiceAsync;
import com.inet.api.iws.services.sts.SecurityTokenServiceAsyncClient;

/**
 * SecurityTokenServiceAsyncProvider.
 *
 * @author thoangtran
 * @version SecurityTokenServiceAsyncProvider 2018 12-08 11:25
 */
public class SecurityTokenServiceAsyncProvider implements Provider<SecurityTokenServiceAsync> {
  //~ class properties ========================================================
  private final SecurityTokenServiceAsync securityTokenService;

  //~ class members ===========================================================
  @Inject
  public SecurityTokenServiceAsyncProvider(HoraeCredentials credentials, ClientConfiguration configuration) {
    this.securityTokenService = new SecurityTokenServiceAsyncClient(credentials, configuration);
  }

  public SecurityTokenServiceAsync get() {
    return securityTokenService;
  }
}
