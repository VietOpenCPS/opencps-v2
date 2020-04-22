package org.opencps.esb.api.service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.inet.api.ClientConfiguration;
import com.inet.api.iws.auth.horae.HoraeCredentials;
import com.inet.api.iws.services.knobstick.KnobstickServiceAsync;
import com.inet.api.iws.services.knobstick.KnobstickServiceAsyncClient;

/**
 * KnobstickServiceAsyncProvider.
 *
 * @author: Dzung Nguyen
 * @version: $Id KnobstickServiceAsyncProvider 2013-09-18 12:19:30z nguyen_dv $
 * @since 1.0
 */
public class KnobstickServiceAsyncProvider implements Provider<KnobstickServiceAsync> {
  //~ class properties ========================================================
  private final KnobstickServiceAsync knobstickServiceAsync;

  //~ class members ===========================================================
  @Inject
  public KnobstickServiceAsyncProvider(HoraeCredentials credentials, ClientConfiguration configuration) {
    this.knobstickServiceAsync = new KnobstickServiceAsyncClient(credentials, configuration);
  }

  public KnobstickServiceAsync get() {
    return knobstickServiceAsync;
  }
}
