package org.opencps.esb.api.service;

import com.google.inject.AbstractModule;
import com.inet.api.ClientConfiguration;
import com.inet.api.iws.auth.horae.HoraeCredentials;
import com.inet.api.iws.services.agency.AgencyServiceAsync;
import com.inet.api.iws.services.knobstick.KnobstickServiceAsync;
import com.inet.api.iws.services.slot.SlotServiceAsync;
import com.inet.api.iws.services.sts.SecurityTokenServiceAsync;

/**
 * CollaborationServiceModule.
 *
 * @author: Dzung Nguyen
 * @version: $Id CollaborationServiceModule 2013-09-18 12:04:30z nguyen_dv $
 * @since 1.0
 */
public class CollaborationServiceModule extends AbstractModule {
  //~ class members ===========================================================
  @Override
  protected void configure() {
    // bind credentials.
    bind(HoraeCredentials.class).toProvider(HoraeCredentialsProvider.class);

    // bind client configuration.
    bind(ClientConfiguration.class).toProvider(ClientConfigurationProvider.class);


    // bind agency service.
    bind(AgencyServiceAsync.class).toProvider(AgencyServiceAsyncProvider.class);

    // bind slot service.
    bind(SlotServiceAsync.class).toProvider(SlotServiceAsyncProvider.class);

    // bind knobstick service.
    bind(KnobstickServiceAsync.class).toProvider(KnobstickServiceAsyncProvider.class);

    // bind SecurityToken
    bind(SecurityTokenServiceAsync.class).toProvider(SecurityTokenServiceAsyncProvider.class);


  }
}
