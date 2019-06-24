package org.opencps.rest.opencps.model;

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
@XmlRootElement(name = "data") public class StepConfigItem  {
  
  @ApiModelProperty(example = "null", value = "")
  private Long stepConfigId = null;
  @ApiModelProperty(example = "2018-06-11T08:04:12.129Z", value = "")
  private String createDate = null;
  @ApiModelProperty(example = "001", required = true, value = "")
  private String stepCode = null;
  @ApiModelProperty(example = "step name 001", value = "")
  private String stepName = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer stepType = null;
  @ApiModelProperty(example = "dossierStatus 001", value = "")
  private String dossierStatus = null;
  @ApiModelProperty(example = "dossierSubStatus 001", value = "")
  private String dossierSubStatus = null;
  @ApiModelProperty(example = "menuGroup 001", value = "")
  private String menuGroup = null;
  @ApiModelProperty(example = "menuStepName 001", value = "")
  private String menuStepName = null;
  @ApiModelProperty(example = "0001", value = "")
  private String buttonConfig = null;

 /**
   * Get stepConfigId
   * @return stepConfigId
  **/
  public Long getStepConfigId() {
    return stepConfigId;
  }

  public void setStepConfigId(Long stepConfigId) {
    this.stepConfigId = stepConfigId;
  }

  public StepConfigItem stepConfigId(Long stepConfigId) {
    this.stepConfigId = stepConfigId;
    return this;
  }

 /**
   * Get createDate
   * @return createDate
  **/
  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public StepConfigItem createDate(String createDate) {
    this.createDate = createDate;
    return this;
  }

 /**
   * Get stepCode
   * @return stepCode
  **/
  @NotNull
  public String getStepCode() {
    return stepCode;
  }

  public void setStepCode(String stepCode) {
    this.stepCode = stepCode;
  }

  public StepConfigItem stepCode(String stepCode) {
    this.stepCode = stepCode;
    return this;
  }

 /**
   * Get stepName
   * @return stepName
  **/
  public String getStepName() {
    return stepName;
  }

  public void setStepName(String stepName) {
    this.stepName = stepName;
  }

  public StepConfigItem stepName(String stepName) {
    this.stepName = stepName;
    return this;
  }

 /**
   * Get stepType
   * @return stepType
  **/
  public Integer getStepType() {
    return stepType;
  }

  public void setStepType(Integer stepType) {
    this.stepType = stepType;
  }

  public StepConfigItem stepType(Integer stepType) {
    this.stepType = stepType;
    return this;
  }

 /**
   * Get dossierStatus
   * @return dossierStatus
  **/
  public String getDossierStatus() {
    return dossierStatus;
  }

  public void setDossierStatus(String dossierStatus) {
    this.dossierStatus = dossierStatus;
  }

  public StepConfigItem dossierStatus(String dossierStatus) {
    this.dossierStatus = dossierStatus;
    return this;
  }

 /**
   * Get dossierSubStatus
   * @return dossierSubStatus
  **/
  public String getDossierSubStatus() {
    return dossierSubStatus;
  }

  public void setDossierSubStatus(String dossierSubStatus) {
    this.dossierSubStatus = dossierSubStatus;
  }

  public StepConfigItem dossierSubStatus(String dossierSubStatus) {
    this.dossierSubStatus = dossierSubStatus;
    return this;
  }

 /**
   * Get menuGroup
   * @return menuGroup
  **/
  public String getMenuGroup() {
    return menuGroup;
  }

  public void setMenuGroup(String menuGroup) {
    this.menuGroup = menuGroup;
  }

  public StepConfigItem menuGroup(String menuGroup) {
    this.menuGroup = menuGroup;
    return this;
  }

 /**
   * Get menuStepName
   * @return menuStepName
  **/
  public String getMenuStepName() {
    return menuStepName;
  }

  public void setMenuStepName(String menuStepName) {
    this.menuStepName = menuStepName;
  }

  public StepConfigItem menuStepName(String menuStepName) {
    this.menuStepName = menuStepName;
    return this;
  }

 /**
   * Get buttonConfig
   * @return buttonConfig
  **/
  public String getButtonConfig() {
    return buttonConfig;
  }

  public void setButtonConfig(String buttonConfig) {
    this.buttonConfig = buttonConfig;
  }

  public StepConfigItem buttonConfig(String buttonConfig) {
    this.buttonConfig = buttonConfig;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StepConfigItem {\n");
    
    sb.append("    stepConfigId: ").append(toIndentedString(stepConfigId)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    stepCode: ").append(toIndentedString(stepCode)).append("\n");
    sb.append("    stepName: ").append(toIndentedString(stepName)).append("\n");
    sb.append("    stepType: ").append(toIndentedString(stepType)).append("\n");
    sb.append("    dossierStatus: ").append(toIndentedString(dossierStatus)).append("\n");
    sb.append("    dossierSubStatus: ").append(toIndentedString(dossierSubStatus)).append("\n");
    sb.append("    menuGroup: ").append(toIndentedString(menuGroup)).append("\n");
    sb.append("    menuStepName: ").append(toIndentedString(menuStepName)).append("\n");
    sb.append("    buttonConfig: ").append(toIndentedString(buttonConfig)).append("\n");
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

