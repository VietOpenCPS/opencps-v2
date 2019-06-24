package org.opencps.dossiermgt.input.model;

public class DossierMarkBatchModel {

	long dossierId; 
	String dossierPartNo;
	Integer fileMark;
	Integer fileCheck;
	String fileComment;
	String recordCount;

	public long getDossierId() {
		return dossierId;
	}
	public void setDossierId(long dossierId) {
		this.dossierId = dossierId;
	}
	public String getDossierPartNo() {
		return dossierPartNo;
	}
	public void setDossierPartNo(String dossierPartNo) {
		this.dossierPartNo = dossierPartNo;
	}
	public Integer getFileMark() {
		return fileMark;
	}
	public void setFileMark(Integer fileMark) {
		this.fileMark = fileMark;
	}
	public Integer getFileCheck() {
		return fileCheck;
	}
	public void setFileCheck(Integer fileCheck) {
		this.fileCheck = fileCheck;
	}
	public String getFileComment() {
		return fileComment;
	}
	public void setFileComment(String fileComment) {
		this.fileComment = fileComment;
	}
	public String getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

}
