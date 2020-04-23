package org.opencps.esb.api.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.inet.api.iws.services.agency.AgencyServiceAsync;
import com.inet.api.iws.services.knobstick.KnobstickServiceAsync;
import com.inet.api.iws.services.slot.SlotServiceAsync;
import com.inet.api.iws.services.sts.SecurityTokenServiceAsync;

/**
 * CollaborationServices.
 *
 * @author: Dzung Nguyen
 * @version: $Id CollaborationServices 2013-09-18 12:13:30z nguyen_dv $
 * @since 1.0
 */
public class CollaborationServices {
  //~ class properties =========================================================
  /* the injector to lookup the target object. */
  private static final Injector COLLAB_INJECTOR = Guice.createInjector(new CollaborationServiceModule());

  //~ class members ============================================================
  private CollaborationServices() {
    throw new AssertionError("The CollaborationServices must not be initiated.");
  }

  /**
   * @return the agency service.
   */
  public static AgencyServiceAsync getAgencyService() {
    return COLLAB_INJECTOR.getInstance(AgencyServiceAsync.class);
  }

  /**
   * @return the slot service.
   */
  public static SlotServiceAsync getSlotService() {
    return COLLAB_INJECTOR.getInstance(SlotServiceAsync.class);
  }

  /**
   * @return the agency service.
   */
  public static KnobstickServiceAsync getKnobstickService() {
    return COLLAB_INJECTOR.getInstance(KnobstickServiceAsync.class);
  }

  public static SecurityTokenServiceAsync getSecurityTokenService() {
    return COLLAB_INJECTOR.getInstance(SecurityTokenServiceAsync.class);
  }

}
