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
@XmlRootElement(name = "data") public class DocumentTypeInputModel  {
  
  @ApiModelProperty(example = "1110", required = true, value = "")
  private String typeCode = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer templateClass = null;
  @ApiModelProperty(example = "Name of document", value = "")
  private String documentName = null;
  @ApiModelProperty(example = "Partten of code", value = "")
  private String codePattern = null;
  @ApiModelProperty(example = "Script of document", value = "")
  private String documentScript = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer docSync = null;

 /**
   * Get typeCode
   * @return typeCode
  **/
  @NotNull
  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public DocumentTypeInputModel typeCode(String typeCode) {
    this.typeCode = typeCode;
    return this;
  }

 /**
   * Get templateClass
   * @return templateClass
  **/
  public Integer getTemplateClass() {
    return templateClass;
  }

  public void setTemplateClass(Integer templateClass) {
    this.templateClass = templateClass;
  }

  public DocumentTypeInputModel templateClass(Integer templateClass) {
    this.templateClass = templateClass;
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

  public DocumentTypeInputModel documentName(String documentName) {
    this.documentName = documentName;
    return this;
  }

 /**
   * Get codePattern
   * @return codePattern
  **/
  public String getCodePattern() {
    return codePattern;
  }

  public void setCodePattern(String codePattern) {
    this.codePattern = codePattern;
  }

  public DocumentTypeInputModel codePattern(String codePattern) {
    this.codePattern = codePattern;
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

  public DocumentTypeInputModel documentScript(String documentScript) {
    this.documentScript = documentScript;
    return this;
  }

 /**
   * Get docSync
   * @return docSync
  **/
  public Integer getDocSync() {
    return docSync;
  }

  public void setDocSync(Integer docSync) {
    this.docSync = docSync;
  }

  public DocumentTypeInputModel docSync(Integer docSync) {
    this.docSync = docSync;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentTypeInputModel {\n");
    
    sb.append("    typeCode: ").append(toIndentedString(typeCode)).append("\n");
    sb.append("    templateClass: ").append(toIndentedString(templateClass)).append("\n");
    sb.append("    documentName: ").append(toIndentedString(documentName)).append("\n");
    sb.append("    codePattern: ").append(toIndentedString(codePattern)).append("\n");
    sb.append("    documentScript: ").append(toIndentedString(documentScript)).append("\n");
    sb.append("    docSync: ").append(toIndentedString(docSync)).append("\n");
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

