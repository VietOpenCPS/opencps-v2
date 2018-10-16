package org.opencps.rest.application.model;

import javax.validation.constraints.*;

import io.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "data") public class ActionConfigItem  {
  
  @ApiModelProperty(example = "null", value = "")
  private Long actionConfigId = null;
  @ApiModelProperty(example = "1100", required = true, value = "")
  private String actionCode = null;
  @ApiModelProperty(example = "Nhận hồ sơ tại một cửa", value = "")
  private String actionName = null;
  @ApiModelProperty(example = "true", value = "")
  private Boolean extraForm = null;
  @ApiModelProperty(example = "{&quot;schema&quot;: {&quot;title&quot;: &quot;Example ?&quot;,&quot;type&quot;: &quot;object&quot;,&quot;properties&quot;: {&quot;name&quot;: {&quot;type&quot;: &quot;string&quot;,&quot;title&quot;: &quot;Name&quot;}}}}", value = "")
  private String formScript = null;
  @ApiModelProperty(example = "{&quot;name&quot;: &quot;example&quot;}", value = "")
  private String sampleData = null;
  @ApiModelProperty(example = "true", value = "")
  private Boolean insideProcess = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer syncType = null;
  @ApiModelProperty(example = "true", value = "")
  private Boolean pending = null;
  @ApiModelProperty(example = "true", value = "")
  private Boolean rollbackable = null;
  @ApiModelProperty(example = "Mã tạo thông báo email/SMS request: gửi cán bộ một cửa inform: gửi người làm thủ tục", value = "")
  private String notificationType = null;
  @ApiModelProperty(example = "0001", value = "")
  private String documentType = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer userNote = null;
  @ApiModelProperty(example = "1200", value = "")
  private String mappingAction = null;

 /**
   * Get actionConfigId
   * @return actionConfigId
  **/
  public Long getActionConfigId() {
    return actionConfigId;
  }

  public void setActionConfigId(Long actionConfigId) {
    this.actionConfigId = actionConfigId;
  }

  public ActionConfigItem actionConfigId(Long actionConfigId) {
    this.actionConfigId = actionConfigId;
    return this;
  }

 /**
   * Get actionCode
   * @return actionCode
  **/
  @NotNull
  public String getActionCode() {
    return actionCode;
  }

  public void setActionCode(String actionCode) {
    this.actionCode = actionCode;
  }

  public ActionConfigItem actionCode(String actionCode) {
    this.actionCode = actionCode;
    return this;
  }

 /**
   * Get actionName
   * @return actionName
  **/
  public String getActionName() {
    return actionName;
  }

  public void setActionName(String actionName) {
    this.actionName = actionName;
  }

  public ActionConfigItem actionName(String actionName) {
    this.actionName = actionName;
    return this;
  }

 /**
   * Get extraForm
   * @return extraForm
  **/
  public Boolean getExtraForm() {
    return extraForm;
  }

  public void setExtraForm(Boolean extraForm) {
    this.extraForm = extraForm;
  }

  public ActionConfigItem extraForm(Boolean extraForm) {
    this.extraForm = extraForm;
    return this;
  }

 /**
   * Get formScript
   * @return formScript
  **/
  public String getFormScript() {
    return formScript;
  }

  public void setFormScript(String formScript) {
    this.formScript = formScript;
  }

  public ActionConfigItem formScript(String formScript) {
    this.formScript = formScript;
    return this;
  }

 /**
   * Get sampleData
   * @return sampleData
  **/
  public String getSampleData() {
    return sampleData;
  }

  public void setSampleData(String sampleData) {
    this.sampleData = sampleData;
  }

  public ActionConfigItem sampleData(String sampleData) {
    this.sampleData = sampleData;
    return this;
  }

 /**
   * Get insideProcess
   * @return insideProcess
  **/
  public Boolean getInsideProcess() {
    return insideProcess;
  }

  public void setInsideProcess(Boolean insideProcess) {
    this.insideProcess = insideProcess;
  }

  public ActionConfigItem insideProcess(Boolean insideProcess) {
    this.insideProcess = insideProcess;
    return this;
  }

 /**
   * Get syncType
   * @return syncType
  **/
  public Integer getSyncType() {
    return syncType;
  }

  public void setSyncType(Integer syncType) {
    this.syncType = syncType;
  }

  public ActionConfigItem syncType(Integer syncType) {
    this.syncType = syncType;
    return this;
  }

 /**
   * Get pending
   * @return pending
  **/
  public Boolean getPending() {
    return pending;
  }

  public void setPending(Boolean pending) {
    this.pending = pending;
  }

  public ActionConfigItem pending(Boolean pending) {
    this.pending = pending;
    return this;
  }

 /**
   * Get rollbackable
   * @return rollbackable
  **/
  public Boolean getRollbackable() {
    return rollbackable;
  }

  public void setRollbackable(Boolean rollbackable) {
    this.rollbackable = rollbackable;
  }

  public ActionConfigItem rollbackable(Boolean rollbackable) {
    this.rollbackable = rollbackable;
    return this;
  }

 /**
   * Get notificationType
   * @return notificationType
  **/
  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public ActionConfigItem notificationType(String notificationType) {
    this.notificationType = notificationType;
    return this;
  }

 /**
   * Get documentType
   * @return documentType
  **/
  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public ActionConfigItem documentType(String documentType) {
    this.documentType = documentType;
    return this;
  }

 /**
   * Get userNote
   * @return userNote
  **/
  public Integer getUserNote() {
    return userNote;
  }

  public void setUserNote(Integer userNote) {
    this.userNote = userNote;
  }

  public ActionConfigItem userNote(Integer userNote) {
    this.userNote = userNote;
    return this;
  }

 /**
   * Get mappingAction
   * @return mappingAction
  **/
  public String getMappingAction() {
    return mappingAction;
  }

  public void setMappingAction(String mappingAction) {
    this.mappingAction = mappingAction;
  }

  public ActionConfigItem mappingAction(String mappingAction) {
    this.mappingAction = mappingAction;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActionConfigItem {\n");
    
    sb.append("    actionConfigId: ").append(toIndentedString(actionConfigId)).append("\n");
    sb.append("    actionCode: ").append(toIndentedString(actionCode)).append("\n");
    sb.append("    actionName: ").append(toIndentedString(actionName)).append("\n");
    sb.append("    extraForm: ").append(toIndentedString(extraForm)).append("\n");
    sb.append("    formScript: ").append(toIndentedString(formScript)).append("\n");
    sb.append("    sampleData: ").append(toIndentedString(sampleData)).append("\n");
    sb.append("    insideProcess: ").append(toIndentedString(insideProcess)).append("\n");
    sb.append("    syncType: ").append(toIndentedString(syncType)).append("\n");
    sb.append("    pending: ").append(toIndentedString(pending)).append("\n");
    sb.append("    rollbackable: ").append(toIndentedString(rollbackable)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
    sb.append("    userNote: ").append(toIndentedString(userNote)).append("\n");
    sb.append("    mappingAction: ").append(toIndentedString(mappingAction)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

