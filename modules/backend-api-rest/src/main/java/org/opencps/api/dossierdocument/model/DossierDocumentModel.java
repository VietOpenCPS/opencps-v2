package org.opencps.api.dossierdocument.model;

import javax.validation.constraints.*;

import io.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement public class DossierDocumentModel  {
  
  @ApiModelProperty(example = "0", required = true, value = "")
  private Long dossierDocumentId = null;
  @ApiModelProperty(example = "2016-04-16T16:06:05Z", value = "")
  private java.util.Date createDate = null;
  @ApiModelProperty(example = "2016-04-16T16:06:05Z", value = "")
  private java.util.Date modifiedDate = null;
  @ApiModelProperty(example = "0", value = "")
  private Long dossierId = null;
  @ApiModelProperty(example = "0", value = "")
  private Long dossierActionId = null;
  @ApiModelProperty(example = "type of document", value = "")
  private String documentType = null;
  @ApiModelProperty(example = "Name of document", value = "")
  private String documentName = null;
  @ApiModelProperty(example = "code of document", value = "")
  private String documentCode = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer docSync = null;
  @ApiModelProperty(example = "10", value = "")
  private Long fileSize = null;
  @ApiModelProperty(example = "type of file", value = "")
  private String fileType = null;
  @ApiModelProperty(example = "0", value = "")
  private Long documentFileId = null;

 /**
   * Get dossierDocumentId
   * @return dossierDocumentId
  **/
  @NotNull
  public Long getDossierDocumentId() {
    return dossierDocumentId;
  }

  public void setDossierDocumentId(Long dossierDocumentId) {
    this.dossierDocumentId = dossierDocumentId;
  }

  public DossierDocumentModel dossierDocumentId(Long dossierDocumentId) {
    this.dossierDocumentId = dossierDocumentId;
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

  public DossierDocumentModel createDate(java.util.Date createDate) {
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

  public DossierDocumentModel modifiedDate(java.util.Date modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

 /**
   * Get dossierId
   * @return dossierId
  **/
  public Long getDossierId() {
    return dossierId;
  }

  public void setDossierId(Long dossierId) {
    this.dossierId = dossierId;
  }

  public DossierDocumentModel dossierId(Long dossierId) {
    this.dossierId = dossierId;
    return this;
  }

 /**
   * Get dossierActionId
   * @return dossierActionId
  **/
  public Long getDossierActionId() {
    return dossierActionId;
  }

  public void setDossierActionId(Long dossierActionId) {
    this.dossierActionId = dossierActionId;
  }

  public DossierDocumentModel dossierActionId(Long dossierActionId) {
    this.dossierActionId = dossierActionId;
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

  public DossierDocumentModel documentType(String documentType) {
    this.documentType = documentType;
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

  public DossierDocumentModel documentName(String documentName) {
    this.documentName = documentName;
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

  public DossierDocumentModel documentCode(String documentCode) {
    this.documentCode = documentCode;
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

  public DossierDocumentModel docSync(Integer docSync) {
    this.docSync = docSync;
    return this;
  }

 /**
   * Get fileSize
   * @return fileSize
  **/
  public Long getFileSize() {
    return fileSize;
  }

  public void setFileSize(Long fileSize) {
    this.fileSize = fileSize;
  }

  public DossierDocumentModel fileSize(Long fileSize) {
    this.fileSize = fileSize;
    return this;
  }

 /**
   * Get fileType
   * @return fileType
  **/
  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public DossierDocumentModel fileType(String fileType) {
    this.fileType = fileType;
    return this;
  }

 /**
   * Get documentFileId
   * @return documentFileId
  **/
  public Long getDocumentFileId() {
    return documentFileId;
  }

  public void setDocumentFileId(Long documentFileId) {
    this.documentFileId = documentFileId;
  }

  public DossierDocumentModel documentFileId(Long documentFileId) {
    this.documentFileId = documentFileId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DossierDocumentModel {\n");
    
    sb.append("    dossierDocumentId: ").append(toIndentedString(dossierDocumentId)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    modifiedDate: ").append(toIndentedString(modifiedDate)).append("\n");
    sb.append("    dossierId: ").append(toIndentedString(dossierId)).append("\n");
    sb.append("    dossierActionId: ").append(toIndentedString(dossierActionId)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
    sb.append("    documentName: ").append(toIndentedString(documentName)).append("\n");
    sb.append("    documentCode: ").append(toIndentedString(documentCode)).append("\n");
    sb.append("    docSync: ").append(toIndentedString(docSync)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
    sb.append("    documentFileId: ").append(toIndentedString(documentFileId)).append("\n");
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

