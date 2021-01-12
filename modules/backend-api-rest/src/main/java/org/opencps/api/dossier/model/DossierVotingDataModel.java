package org.opencps.api.dossier.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"dossierId",
	"groupId",
	"receiveDate",
	"dossierName",
	"dossierNo",
	"serviceCode",
	"serviceName",
	"votingCode1",
	"votingCode2",
	"votingCode3",
	"votingCode4",
	"votingName1",
	"votingName2",
	"votingName3",
	"votingName4",
	"resultVotingCode1",
	"resultVotingCode2",
	"resultVotingCode3",
	"resultVotingCode4",
	"dueDate",
	"extendDate",
	"releaseDate",
	"finishDate"
})
public class DossierVotingDataModel {
	protected Integer dossierId;
    protected Integer groupId;
    protected String receiveDate;
	protected String dossierName;
    protected String dossierNo;
    protected String serviceCode;
    protected String serviceName;
    protected String votingCode1;
	protected String votingCode2;
	protected String votingCode3;
	protected String votingCode4;
	protected String votingName1;
	protected String votingName2;
	protected String votingName3;
	protected String votingName4;
	protected Integer resultVotingCode1;
	protected Integer resultVotingCode2;
	protected Integer resultVotingCode3;
	protected Integer resultVotingCode4;
	protected String dueDate;
	protected String extendDate;
	protected String releaseDate;
	protected String finishDate;
	public Integer getDossierId() {
		return dossierId;
	}
	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getDossierName() {
		return dossierName;
	}
	public void setDossierName(String dossierName) {
		this.dossierName = dossierName;
	}
	public String getDossierNo() {
		return dossierNo;
	}
	public void setDossierNo(String dossierNo) {
		this.dossierNo = dossierNo;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getVotingCode1() {
		return votingCode1;
	}
	public void setVotingCode1(String votingCode1) {
		this.votingCode1 = votingCode1;
	}
	public String getVotingCode2() {
		return votingCode2;
	}
	public void setVotingCode2(String votingCode2) {
		this.votingCode2 = votingCode2;
	}
	public String getVotingCode3() {
		return votingCode3;
	}
	public void setVotingCode3(String votingCode3) {
		this.votingCode3 = votingCode3;
	}
	public String getVotingCode4() {
		return votingCode4;
	}
	public void setVotingCode4(String votingCode4) {
		this.votingCode4 = votingCode4;
	}
	public String getVotingName1() {
		return votingName1;
	}
	public void setVotingName1(String votingName1) {
		this.votingName1 = votingName1;
	}
	public String getVotingName2() {
		return votingName2;
	}
	public void setVotingName2(String votingName2) {
		this.votingName2 = votingName2;
	}
	public String getVotingName3() {
		return votingName3;
	}
	public void setVotingName3(String votingName3) {
		this.votingName3 = votingName3;
	}
	public String getVotingName4() {
		return votingName4;
	}
	public void setVotingName4(String votingName4) {
		this.votingName4 = votingName4;
	}
	public Integer getResultVotingCode1() {
		return resultVotingCode1;
	}
	public void setResultVotingCode1(Integer resultVotingCode1) {
		this.resultVotingCode1 = resultVotingCode1;
	}
	public Integer getResultVotingCode2() {
		return resultVotingCode2;
	}
	public void setResultVotingCode2(Integer resultVotingCode2) {
		this.resultVotingCode2 = resultVotingCode2;
	}
	public Integer getResultVotingCode3() {
		return resultVotingCode3;
	}
	public void setResultVotingCode3(Integer resultVotingCode3) {
		this.resultVotingCode3 = resultVotingCode3;
	}
	public Integer getResultVotingCode4() {
		return resultVotingCode4;
	}
	public void setResultVotingCode4(Integer resultVotingCode4) {
		this.resultVotingCode4 = resultVotingCode4;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getExtendDate() {
		return extendDate;
	}
	public void setExtendDate(String extendDate) {
		this.extendDate = extendDate;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
}
