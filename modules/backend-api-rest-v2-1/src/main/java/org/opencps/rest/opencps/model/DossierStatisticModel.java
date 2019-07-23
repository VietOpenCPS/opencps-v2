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
@XmlRootElement(name = "data") public class DossierStatisticModel  {
  
  @ApiModelProperty(example = "stepCode of stepConfig", required = true, value = "")
  private String stepCode = null;
  @ApiModelProperty(example = "referenceUid of dossierSync", value = "")
  private String stepName = null;
  @ApiModelProperty(example = "code of dossierSync", value = "")
  private String dossierStatus = null;
  @ApiModelProperty(example = "Name of dossierSync", value = "")
  private String dossierSubStatus = null;
  @ApiModelProperty(example = "action of user process", value = "")
  private Long totalCount = null;

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

  public DossierStatisticModel stepCode(String stepCode) {
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

  public DossierStatisticModel stepName(String stepName) {
    this.stepName = stepName;
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

  public DossierStatisticModel dossierStatus(String dossierStatus) {
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

  public DossierStatisticModel dossierSubStatus(String dossierSubStatus) {
    this.dossierSubStatus = dossierSubStatus;
    return this;
  }

 /**
   * Get totalCount
   * @return totalCount
  **/
  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }

  public DossierStatisticModel totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DossierStatisticModel {\n");
    
    sb.append("    stepCode: ").append(toIndentedString(stepCode)).append("\n");
    sb.append("    stepName: ").append(toIndentedString(stepName)).append("\n");
    sb.append("    dossierStatus: ").append(toIndentedString(dossierStatus)).append("\n");
    sb.append("    dossierSubStatus: ").append(toIndentedString(dossierSubStatus)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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

