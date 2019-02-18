package org.opencps.dossiermgt.input.model;

public class DossierMarkBatchModel {
	long dossierId; 
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
	String dossierPartNo;
	Integer fileMark;
	Integer fileCheck;
	String fileComment;
}
