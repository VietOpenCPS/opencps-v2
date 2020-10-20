package org.opencps.api.serviceprocess.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessStepDetailModel", propOrder = {
        "stepCode",
        "processStepId",
        "stepName",
        "sequenceNo",
        "dossierStatus",
        "dossierStatusText",
        "dossierSubStatus",
        "dossierSubStatusText",
        "durationCount",
        "stepInstruction",
        "briefNote",
        "customProcessUrl",
        "editable",
        "lockState",
        "checkInput",
        "roleAsStep"
})
@XmlRootElement(name = "ProcessStepDetailModel")
public class ProcessStepDetailModel {

    protected String stepCode;
    protected long processStepId;
    protected String stepName;
    protected String sequenceNo;
    protected String dossierStatus;
    protected String dossierStatusText;
    protected String dossierSubStatus;
    protected String dossierSubStatusText;
    protected String durationCount;
    protected String stepInstruction;
    protected String customProcessUrl;
    protected String editable;
    protected String lockState;
    protected Integer checkInput;
    protected String roleAsStep;

    public String getRoleAsStep() {
        return roleAsStep;
    }

    public void setRoleAsStep(String roleAsStep) {
        this.roleAsStep = roleAsStep;
    }

    public Integer getCheckInput() {
        return checkInput;
    }

    public void setCheckInput(Integer checkInput) {
        this.checkInput = checkInput;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getBriefNote() {
        return briefNote;
    }

    public void setBriefNote(String briefNote) {
        this.briefNote = briefNote;
    }

    protected String briefNote;

    /**
     * Gets the value of the stepCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStepCode() {
        return stepCode;
    }

    /**
     * Sets the value of the stepCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStepCode(String value) {
        this.stepCode = value;
    }

    /**
     * Gets the value of the processStepId property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getProcessStepId() {
        return processStepId;
    }

    /**
     * Sets the value of the processStepId property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setProcessStepId(Long value) {
        this.processStepId = value;
    }

    /**
     * Gets the value of the stepName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStepName() {
        return stepName;
    }

    /**
     * Sets the value of the stepName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStepName(String value) {
        this.stepName = value;
    }

    /**
     * Gets the value of the sequenceNo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSequenceNo() {
        return sequenceNo;
    }

    /**
     * Sets the value of the sequenceNo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSequenceNo(String value) {
        this.sequenceNo = value;
    }

    /**
     * Gets the value of the dossierStatus property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDossierStatus() {
        return dossierStatus;
    }

    /**
     * Sets the value of the dossierStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDossierStatus(String value) {
        this.dossierStatus = value;
    }

    /**
     * Gets the value of the dossierStatusText property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDossierStatusText() {
        return dossierStatusText;
    }

    /**
     * Sets the value of the dossierStatusText property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDossierStatusText(String value) {
        this.dossierStatusText = value;
    }

    /**
     * Gets the value of the dossierSubStatus property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDossierSubStatus() {
        return dossierSubStatus;
    }

    /**
     * Sets the value of the dossierSubStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDossierSubStatus(String value) {
        this.dossierSubStatus = value;
    }

    /**
     * Gets the value of the dossierSubStatusText property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDossierSubStatusText() {
        return dossierSubStatusText;
    }

    /**
     * Sets the value of the dossierSubStatusText property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDossierSubStatusText(String value) {
        this.dossierSubStatusText = value;
    }

    /**
     * Gets the value of the durationCount property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDurationCount() {
        return durationCount;
    }

    /**
     * Sets the value of the durationCount property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDurationCount(String value) {
        this.durationCount = value;
    }

    /**
     * Gets the value of the instructionNote property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStepInstruction() {
        return stepInstruction;
    }

    /**
     * Sets the value of the instructionNote property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStepInstruction(String value) {
        this.stepInstruction = value;
    }

    /**
     * Gets the value of the customProcessUrl property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustomProcessUrl() {
        return customProcessUrl;
    }

    /**
     * Sets the value of the customProcessUrl property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustomProcessUrl(String value) {
        this.customProcessUrl = value;
    }

    /**
     * Gets the value of the editable property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEditable() {
        return editable;
    }

    /**
     * Sets the value of the editable property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEditable(String value) {
        this.editable = value;
    }

}
