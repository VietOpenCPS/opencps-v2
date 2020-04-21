package org.opencps.esb.api.service;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.inet.api.ClientConfiguration;
import com.inet.api.iws.auth.horae.HoraeCredentials;
import com.inet.api.iws.services.slot.SlotServiceAsync;
import com.inet.api.iws.services.slot.SlotServiceAsyncClient;

/**
 * SlotServiceAsyncProvider.
 *
 * @author: Dzung Nguyen
 * @version: $Id SlotServiceAsyncProvider 2013-09-18 12:18:30z nguyen_dv $
 * @since 1.0
 */
public class SlotServiceAsyncProvider implements Provider<SlotServiceAsync> {
  //~ class properties ========================================================
  private final SlotServiceAsync slotServiceAsync;

  //~ class members ===========================================================
  @Inject
  public SlotServiceAsyncProvider(HoraeCredentials credentials, ClientConfiguration configuration) {
    this.slotServiceAsync = new SlotServiceAsyncClient(credentials, configuration);
  }

  public SlotServiceAsync get() {
    return slotServiceAsync;
  }
}
