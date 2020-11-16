package org.opencps.dossiermgt.input.model;

import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessAction;

public class FrequencyDoAction {
    private DossierAction dossierAction;
    private ProcessAction processAction;

    public DossierAction getDossierAction() {
        return dossierAction;
    }

    public void setDossierAction(DossierAction dossierAction) {
        this.dossierAction = dossierAction;
    }

    public ProcessAction getProcessAction() {
        return processAction;
    }

    public void setProcessAction(ProcessAction processAction) {
        this.processAction = processAction;
    }
}
