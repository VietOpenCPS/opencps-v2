package org.opencps.dossiermgt.activator;

import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class DossierActionsActivator implements BundleActivator {
    private ServiceRegistration registration;
    
    @Override
    public void start(BundleContext context) throws Exception { 
        registration = context.registerService(DossierActions.class.getName(), new DossierActionsImpl(), null);
    }
 
    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
    }
}
