package io.swagger.model;

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
@XmlRootElement public class ActionConfigItem  {
  
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
  @ApiModelProperty(example = "NTF-023", value = "")
  private String notificationType = null;
  @ApiModelProperty(example = "true", value = "")
  private Boolean createDocument = null;
  @ApiModelProperty(example = "Thông tin tài khoản", value = "")
  private String documentName = null;
  @ApiModelProperty(example = "&lt;jasper&gt;&lt;/jasper&gt;", value = "")
  private String documentScript = null;
  @ApiModelProperty(example = "THONG_TIN_TAI_KHOAN", value = "")
  private String documentCode = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer sendDocument = null;

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
   * Get createDocument
   * @return createDocument
  **/
  public Boolean getCreateDocument() {
    return createDocument;
  }

  public void setCreateDocument(Boolean createDocument) {
    this.createDocument = createDocument;
  }

  public ActionConfigItem createDocument(Boolean createDocument) {
    this.createDocument = createDocument;
    return this;
  }

 /**
   * Get documentName
   * @return documentName
  **/
  public String getDocumentName() {
    return documentName;
  }

  public void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  public ActionConfigItem documentName(String documentName) {
    this.documentName = documentName;
    return this;
  }

 /**
   * Get documentScript
   * @return documentScript
  **/
  public String getDocumentScript() {
    return documentScript;
  }

  public void setDocumentScript(String documentScript) {
    this.documentScript = documentScript;
  }

  public ActionConfigItem documentScript(String documentScript) {
    this.documentScript = documentScript;
    return this;
  }

 /**
   * Get documentCode
   * @return documentCode
  **/
  public String getDocumentCode() {
    return documentCode;
  }

  public void setDocumentCode(String documentCode) {
    this.documentCode = documentCode;
  }

  public ActionConfigItem documentCode(String documentCode) {
    this.documentCode = documentCode;
    return this;
  }

 /**
   * Get sendDocument
   * @return sendDocument
  **/
  public Integer getSendDocument() {
    return sendDocument;
  }

  public void setSendDocument(Integer sendDocument) {
    this.sendDocument = sendDocument;
  }

  public ActionConfigItem sendDocument(Integer sendDocument) {
    this.sendDocument = sendDocument;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActionConfigItem {\n");
    
    sb.append("    actionCode: ").append(toIndentedString(actionCode)).append("\n");
    sb.append("    actionName: ").append(toIndentedString(actionName)).append("\n");
    sb.append("    extraForm: ").append(toIndentedString(extraForm)).append("\n");
    sb.append("    formScript: ").append(toIndentedString(formScript)).append("\n");
    sb.append("    sampleData: ").append(toIndentedString(sampleData)).append("\n");
    sb.append("    insideProcess: ").append(toIndentedString(insideProcess)).append("\n");
    sb.append("    syncType: ").append(toIndentedString(syncType)).append("\n");
    sb.append("    pending: ").append(toIndentedString(pending)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    createDocument: ").append(toIndentedString(createDocument)).append("\n");
    sb.append("    documentName: ").append(toIndentedString(documentName)).append("\n");
    sb.append("    documentScript: ").append(toIndentedString(documentScript)).append("\n");
    sb.append("    documentCode: ").append(toIndentedString(documentCode)).append("\n");
    sb.append("    sendDocument: ").append(toIndentedString(sendDocument)).append("\n");
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

