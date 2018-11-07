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
@XmlRootElement(name = "data") public class DossierSyncModel  {
  
  @ApiModelProperty(example = "0", required = true, value = "")
  private Long dossierSyncId = null;
  @ApiModelProperty(example = "0", value = "")
  private Long userId = null;
  @ApiModelProperty(example = "2016-04-16T16:06:05Z", value = "")
  private java.util.Date createDate = null;
  @ApiModelProperty(example = "2016-04-16T16:06:05Z", value = "")
  private java.util.Date modifiedDate = null;
  @ApiModelProperty(example = "referenceUid of dossier", value = "")
  private String dossierRefUid = null;
  @ApiModelProperty(example = "referenceUid of dossierSync", value = "")
  private String syncRefUid = null;
  @ApiModelProperty(example = "code of dossierSync", value = "")
  private String actionCode = null;
  @ApiModelProperty(example = "Name of dossierSync", value = "")
  private String actionName = null;
  @ApiModelProperty(example = "action of user process", value = "")
  private String actionUser = null;
  @ApiModelProperty(example = "note of process action", value = "")
  private String actionNote = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer syncType = null;
  @ApiModelProperty(example = "payload info", value = "")
  private String payload = null;

 /**
   * Get dossierSyncId
   * @return dossierSyncId
  **/
  @NotNull
  public Long getDossierSyncId() {
    return dossierSyncId;
  }

  public void setDossierSyncId(Long dossierSyncId) {
    this.dossierSyncId = dossierSyncId;
  }

  public DossierSyncModel dossierSyncId(Long dossierSyncId) {
    this.dossierSyncId = dossierSyncId;
    return this;
  }

 /**
   * Get userId
   * @return userId
  **/
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public DossierSyncModel userId(Long userId) {
    this.userId = userId;
    return this;
  }

 /**
   * Get createDate
   * @return createDate
  **/
  public java.util.Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.util.Date createDate) {
    this.createDate = createDate;
  }

  public DossierSyncModel createDate(java.util.Date createDate) {
    this.createDate = createDate;
    return this;
  }

 /**
   * Get modifiedDate
   * @return modifiedDate
  **/
  public java.util.Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(java.util.Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public DossierSyncModel modifiedDate(java.util.Date modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

 /**
   * Get dossierRefUid
   * @return dossierRefUid
  **/
  public String getDossierRefUid() {
    return dossierRefUid;
  }

  public void setDossierRefUid(String dossierRefUid) {
    this.dossierRefUid = dossierRefUid;
  }

  public DossierSyncModel dossierRefUid(String dossierRefUid) {
    this.dossierRefUid = dossierRefUid;
    return this;
  }

 /**
   * Get syncRefUid
   * @return syncRefUid
  **/
  public String getSyncRefUid() {
    return syncRefUid;
  }

  public void setSyncRefUid(String syncRefUid) {
    this.syncRefUid = syncRefUid;
  }

  public DossierSyncModel syncRefUid(String syncRefUid) {
    this.syncRefUid = syncRefUid;
    return this;
  }

 /**
   * Get actionCode
   * @return actionCode
  **/
  public String getActionCode() {
    return actionCode;
  }

  public void setActionCode(String actionCode) {
    this.actionCode = actionCode;
  }

  public DossierSyncModel actionCode(String actionCode) {
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

  public DossierSyncModel actionName(String actionName) {
    this.actionName = actionName;
    return this;
  }

 /**
   * Get actionUser
   * @return actionUser
  **/
  public String getActionUser() {
    return actionUser;
  }

  public void setActionUser(String actionUser) {
    this.actionUser = actionUser;
  }

  public DossierSyncModel actionUser(String actionUser) {
    this.actionUser = actionUser;
    return this;
  }

 /**
   * Get actionNote
   * @return actionNote
  **/
  public String getActionNote() {
    return actionNote;
  }

  public void setActionNote(String actionNote) {
    this.actionNote = actionNote;
  }

  public DossierSyncModel actionNote(String actionNote) {
    this.actionNote = actionNote;
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

  public DossierSyncModel syncType(Integer syncType) {
    this.syncType = syncType;
    return this;
  }

 /**
   * Get payload
   * @return payload
  **/
  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public DossierSyncModel payload(String payload) {
    this.payload = payload;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DossierSyncModel {\n");
    
    sb.append("    dossierSyncId: ").append(toIndentedString(dossierSyncId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    modifiedDate: ").append(toIndentedString(modifiedDate)).append("\n");
    sb.append("    dossierRefUid: ").append(toIndentedString(dossierRefUid)).append("\n");
    sb.append("    syncRefUid: ").append(toIndentedString(syncRefUid)).append("\n");
    sb.append("    actionCode: ").append(toIndentedString(actionCode)).append("\n");
    sb.append("    actionName: ").append(toIndentedString(actionName)).append("\n");
    sb.append("    actionUser: ").append(toIndentedString(actionUser)).append("\n");
    sb.append("    actionNote: ").append(toIndentedString(actionNote)).append("\n");
    sb.append("    syncType: ").append(toIndentedString(syncType)).append("\n");
    sb.append("    payload: ").append(toIndentedString(payload)).append("\n");
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

