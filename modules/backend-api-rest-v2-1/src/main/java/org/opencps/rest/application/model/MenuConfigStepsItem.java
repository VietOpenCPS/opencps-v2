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
@XmlRootElement(name = "data") public class MenuConfigStepsItem  {
  
  @ApiModelProperty(example = "", value = "")
  private String stepCode = null;
  @ApiModelProperty(example = "001", value = "")
  private String stepName = null;
  @ApiModelProperty(example = "step name 001", value = "")
  private String menuStepName = null;
  @ApiModelProperty(example = "null", value = "")
  private String dossierStatus = null;
  @ApiModelProperty(example = "null", value = "")
  private String dossierSubStatus = null;
  @ApiModelProperty(example = "step name 001", value = "")
  private String buttonConfig = null;

 /**
   * Get stepCode
   * @return stepCode
  **/
  public String getStepCode() {
    return stepCode;
  }

  public void setStepCode(String stepCode) {
    this.stepCode = stepCode;
  }

  public MenuConfigStepsItem stepCode(String stepCode) {
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

  public MenuConfigStepsItem stepName(String stepName) {
    this.stepName = stepName;
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

  public MenuConfigStepsItem menuStepName(String menuStepName) {
    this.menuStepName = menuStepName;
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

  public MenuConfigStepsItem dossierStatus(String dossierStatus) {
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

  public MenuConfigStepsItem dossierSubStatus(String dossierSubStatus) {
    this.dossierSubStatus = dossierSubStatus;
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

  public MenuConfigStepsItem buttonConfig(String buttonConfig) {
    this.buttonConfig = buttonConfig;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuConfigStepsItem {\n");
    
    sb.append("    stepCode: ").append(toIndentedString(stepCode)).append("\n");
    sb.append("    stepName: ").append(toIndentedString(stepName)).append("\n");
    sb.append("    menuStepName: ").append(toIndentedString(menuStepName)).append("\n");
    sb.append("    dossierStatus: ").append(toIndentedString(dossierStatus)).append("\n");
    sb.append("    dossierSubStatus: ").append(toIndentedString(dossierSubStatus)).append("\n");
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

