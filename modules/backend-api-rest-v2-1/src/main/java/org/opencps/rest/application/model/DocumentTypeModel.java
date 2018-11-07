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
@XmlRootElement(name = "data") public class DocumentTypeModel  {
  
  @ApiModelProperty(example = "0", required = true, value = "")
  private Long documentTypeId = null;
  @ApiModelProperty(example = "0", value = "")
  private Long groupId = null;
  @ApiModelProperty(example = "0", value = "")
  private Long userId = null;
  @ApiModelProperty(example = "2016-04-16T16:06:05Z", value = "")
  private java.util.Date createDate = null;
  @ApiModelProperty(example = "2016-04-16T16:06:05Z", value = "")
  private java.util.Date modifiedDate = null;
  @ApiModelProperty(example = "1100", value = "")
  private String typeCode = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer templateClass = null;
  @ApiModelProperty(example = "Thông tin tài khoản", value = "")
  private String documentName = null;
  @ApiModelProperty(example = "&lt;jasper&gt;&lt;/jasper&gt;", value = "")
  private String documentScript = null;
  @ApiModelProperty(example = "Ma hoa tai lieu", value = "")
  private String codePattern = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer docSync = null;

 /**
   * Get documentTypeId
   * @return documentTypeId
  **/
  @NotNull
  public Long getDocumentTypeId() {
    return documentTypeId;
  }

  public void setDocumentTypeId(Long documentTypeId) {
    this.documentTypeId = documentTypeId;
  }

  public DocumentTypeModel documentTypeId(Long documentTypeId) {
    this.documentTypeId = documentTypeId;
    return this;
  }

 /**
   * Get groupId
   * @return groupId
  **/
  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public DocumentTypeModel groupId(Long groupId) {
    this.groupId = groupId;
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

  public DocumentTypeModel userId(Long userId) {
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

  public DocumentTypeModel createDate(java.util.Date createDate) {
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

  public DocumentTypeModel modifiedDate(java.util.Date modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

 /**
   * Get typeCode
   * @return typeCode
  **/
  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public DocumentTypeModel typeCode(String typeCode) {
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

  public DocumentTypeModel templateClass(Integer templateClass) {
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

  public DocumentTypeModel documentName(String documentName) {
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

  public DocumentTypeModel documentScript(String documentScript) {
    this.documentScript = documentScript;
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

  public DocumentTypeModel codePattern(String codePattern) {
    this.codePattern = codePattern;
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

  public DocumentTypeModel docSync(Integer docSync) {
    this.docSync = docSync;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentTypeModel {\n");
    
    sb.append("    documentTypeId: ").append(toIndentedString(documentTypeId)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    modifiedDate: ").append(toIndentedString(modifiedDate)).append("\n");
    sb.append("    typeCode: ").append(toIndentedString(typeCode)).append("\n");
    sb.append("    templateClass: ").append(toIndentedString(templateClass)).append("\n");
    sb.append("    documentName: ").append(toIndentedString(documentName)).append("\n");
    sb.append("    documentScript: ").append(toIndentedString(documentScript)).append("\n");
    sb.append("    codePattern: ").append(toIndentedString(codePattern)).append("\n");
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

