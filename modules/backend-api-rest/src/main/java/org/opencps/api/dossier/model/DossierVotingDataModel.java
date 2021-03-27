package org.opencps.api.dossier.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class DossierVotingDataModel.
 */
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
	"votingCode5",
	"votingCode6",
	"votingCode7",
	"votingCode8",
	"votingCode9",
	"votingName1",
	"votingName2",
	"votingName3",
	"votingName4",
	"votingName5",
	"votingName6",
	"votingName7",
	"votingName8",
	"votingName9",
	"resultVotingCode1",
	"resultVotingCode2",
	"resultVotingCode3",
	"resultVotingCode4",
	"resultVotingCode5",
	"resultVotingCode6",
	"resultVotingCode7",
	"resultVotingCode8",
	"resultVotingCode9",
	"dueDate",
	"extendDate",
	"releaseDate",
	"finishDate"
})
public class DossierVotingDataModel {
	
	/** The dossier id. */
	protected Integer dossierId;
    
    /** The group id. */
    protected Integer groupId;
    
    /** The receive date. */
    protected String receiveDate;
	
	/** The dossier name. */
	protected String dossierName;
    
    /** The dossier no. */
    protected String dossierNo;
    
    /** The service code. */
    protected String serviceCode;
    
    /** The service name. */
    protected String serviceName;
    
    /** The voting code 1.- tieu chi thoi gian (som han, dung han, tre han) */
    protected String votingCode1;
	
	/** The voting code 2.- la voting code 1 */
	protected String votingCode2;
	
	/** The voting code 3.- tieu chi thu nhat trong 3 cau hoi danh gia (ko co danh gia thi diem toi da) */
	protected String votingCode3;
	
	/** The voting code 4.- tieu chi thu hai trong 3 cau hoi danh gia (ko co danh gia thi diem toi da) */
	protected String votingCode4;
	
	/** The voting code 5.- default = 1*/
	protected String votingCode5;
	
	/** The voting code 6.- default = 2 */
	protected String votingCode6;
	
	/** The voting code 7.- tieu chi thu ba trong 3 cau hoi danh gia (ko co danh gia thi diem toi da) */
	protected String votingCode7;
	
	/** The voting code 8.- default = 2 */
	protected String votingCode8;
	
	/** The voting code 9.- default = 2 */
	protected String votingCode9;
	
	/** The voting name 1. */
	protected String votingName1;
	
	/** The voting name 2. */
	protected String votingName2;
	
	/** The voting name 3. */
	protected String votingName3;
	
	/** The voting name 4. */
	protected String votingName4;
	
	/** The voting name 5. */
	protected String votingName5;
	
	/** The voting name 6. */
	protected String votingName6;
	
	/** The voting name 7. */
	protected String votingName7;
	
	/** The voting name 8. */
	protected String votingName8;
	
	/** The voting name 9. */
	protected String votingName9;
	
	/** The result voting code 1. */
	protected Integer resultVotingCode1;
	
	/** The result voting code 2. */
	protected Integer resultVotingCode2;
	
	/** The result voting code 3. */
	protected Integer resultVotingCode3;
	
	/** The result voting code 4. */
	protected Integer resultVotingCode4;
	
	/** The result voting code 5. */
	protected Integer resultVotingCode5;
	
	/** The result voting code 6. */
	protected Integer resultVotingCode6;
	
	/** The result voting code 7. */
	protected Integer resultVotingCode7;
	
	/** The result voting code 8. */
	protected Integer resultVotingCode8;
	
	/** The result voting code 9. */
	protected Integer resultVotingCode9;
	
	/** The due date. */
	protected String dueDate;
	
	/** The extend date. */
	protected String extendDate;
	
	/** The release date. */
	protected String releaseDate;
	
	/** The finish date. */
	protected String finishDate;
	
	/**
	 * Gets the dossier id.
	 *
	 * @return the dossier id
	 */
	public Integer getDossierId() {
		return dossierId;
	}
	
	/**
	 * Sets the dossier id.
	 *
	 * @param dossierId the new dossier id
	 */
	public void setDossierId(Integer dossierId) {
		this.dossierId = dossierId;
	}
	
	/**
	 * Gets the group id.
	 *
	 * @return the group id
	 */
	public Integer getGroupId() {
		return groupId;
	}
	
	/**
	 * Sets the group id.
	 *
	 * @param groupId the new group id
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * Gets the receive date.
	 *
	 * @return the receive date
	 */
	public String getReceiveDate() {
		return receiveDate;
	}
	
	/**
	 * Sets the receive date.
	 *
	 * @param receiveDate the new receive date
	 */
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	/**
	 * Gets the dossier name.
	 *
	 * @return the dossier name
	 */
	public String getDossierName() {
		return dossierName;
	}
	
	/**
	 * Sets the dossier name.
	 *
	 * @param dossierName the new dossier name
	 */
	public void setDossierName(String dossierName) {
		this.dossierName = dossierName;
	}
	
	/**
	 * Gets the dossier no.
	 *
	 * @return the dossier no
	 */
	public String getDossierNo() {
		return dossierNo;
	}
	
	/**
	 * Sets the dossier no.
	 *
	 * @param dossierNo the new dossier no
	 */
	public void setDossierNo(String dossierNo) {
		this.dossierNo = dossierNo;
	}
	
	/**
	 * Gets the service code.
	 *
	 * @return the service code
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	
	/**
	 * Sets the service code.
	 *
	 * @param serviceCode the new service code
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	/**
	 * Gets the service name.
	 *
	 * @return the service name
	 */
	public String getServiceName() {
		return serviceName;
	}
	
	/**
	 * Sets the service name.
	 *
	 * @param serviceName the new service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	/**
	 * Gets the voting code 1.
	 *
	 * @return the voting code 1
	 */
	public String getVotingCode1() {
		return votingCode1;
	}
	
	/**
	 * Sets the voting code 1.
	 *
	 * @param votingCode1 the new voting code 1
	 */
	public void setVotingCode1(String votingCode1) {
		this.votingCode1 = votingCode1;
	}
	
	/**
	 * Gets the voting code 2.
	 *
	 * @return the voting code 2
	 */
	public String getVotingCode2() {
		return votingCode2;
	}
	
	/**
	 * Sets the voting code 2.
	 *
	 * @param votingCode2 the new voting code 2
	 */
	public void setVotingCode2(String votingCode2) {
		this.votingCode2 = votingCode2;
	}
	
	/**
	 * Gets the voting code 3.
	 *
	 * @return the voting code 3
	 */
	public String getVotingCode3() {
		return votingCode3;
	}
	
	/**
	 * Sets the voting code 3.
	 *
	 * @param votingCode3 the new voting code 3
	 */
	public void setVotingCode3(String votingCode3) {
		this.votingCode3 = votingCode3;
	}
	
	/**
	 * Gets the voting code 4.
	 *
	 * @return the voting code 4
	 */
	public String getVotingCode4() {
		return votingCode4;
	}
	
	/**
	 * Sets the voting code 4.
	 *
	 * @param votingCode4 the new voting code 4
	 */
	public void setVotingCode4(String votingCode4) {
		this.votingCode4 = votingCode4;
	}
	
	/**
	 * Gets the voting name 1.
	 *
	 * @return the voting name 1
	 */
	public String getVotingName1() {
		return votingName1;
	}
	
	/**
	 * Sets the voting name 1.
	 *
	 * @param votingName1 the new voting name 1
	 */
	public void setVotingName1(String votingName1) {
		this.votingName1 = votingName1;
	}
	
	/**
	 * Gets the voting name 2.
	 *
	 * @return the voting name 2
	 */
	public String getVotingName2() {
		return votingName2;
	}
	
	/**
	 * Sets the voting name 2.
	 *
	 * @param votingName2 the new voting name 2
	 */
	public void setVotingName2(String votingName2) {
		this.votingName2 = votingName2;
	}
	
	/**
	 * Gets the voting name 3.
	 *
	 * @return the voting name 3
	 */
	public String getVotingName3() {
		return votingName3;
	}
	
	/**
	 * Sets the voting name 3.
	 *
	 * @param votingName3 the new voting name 3
	 */
	public void setVotingName3(String votingName3) {
		this.votingName3 = votingName3;
	}
	
	/**
	 * Gets the voting name 4.
	 *
	 * @return the voting name 4
	 */
	public String getVotingName4() {
		return votingName4;
	}
	
	/**
	 * Sets the voting name 4.
	 *
	 * @param votingName4 the new voting name 4
	 */
	public void setVotingName4(String votingName4) {
		this.votingName4 = votingName4;
	}
	
	/**
	 * Gets the result voting code 1.
	 *
	 * @return the result voting code 1
	 */
	public Integer getResultVotingCode1() {
		return resultVotingCode1;
	}
	
	/**
	 * Sets the result voting code 1.
	 *
	 * @param resultVotingCode1 the new result voting code 1
	 */
	public void setResultVotingCode1(Integer resultVotingCode1) {
		this.resultVotingCode1 = resultVotingCode1;
	}
	
	/**
	 * Gets the result voting code 2.
	 *
	 * @return the result voting code 2
	 */
	public Integer getResultVotingCode2() {
		return resultVotingCode2;
	}
	
	/**
	 * Sets the result voting code 2.
	 *
	 * @param resultVotingCode2 the new result voting code 2
	 */
	public void setResultVotingCode2(Integer resultVotingCode2) {
		this.resultVotingCode2 = resultVotingCode2;
	}
	
	/**
	 * Gets the result voting code 3.
	 *
	 * @return the result voting code 3
	 */
	public Integer getResultVotingCode3() {
		return resultVotingCode3;
	}
	
	/**
	 * Sets the result voting code 3.
	 *
	 * @param resultVotingCode3 the new result voting code 3
	 */
	public void setResultVotingCode3(Integer resultVotingCode3) {
		this.resultVotingCode3 = resultVotingCode3;
	}
	
	/**
	 * Gets the result voting code 4.
	 *
	 * @return the result voting code 4
	 */
	public Integer getResultVotingCode4() {
		return resultVotingCode4;
	}
	
	/**
	 * Sets the result voting code 4.
	 *
	 * @param resultVotingCode4 the new result voting code 4
	 */
	public void setResultVotingCode4(Integer resultVotingCode4) {
		this.resultVotingCode4 = resultVotingCode4;
	}
	
	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public String getDueDate() {
		return dueDate;
	}
	
	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * Gets the extend date.
	 *
	 * @return the extend date
	 */
	public String getExtendDate() {
		return extendDate;
	}
	
	/**
	 * Sets the extend date.
	 *
	 * @param extendDate the new extend date
	 */
	public void setExtendDate(String extendDate) {
		this.extendDate = extendDate;
	}
	
	/**
	 * Gets the release date.
	 *
	 * @return the release date
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * Sets the release date.
	 *
	 * @param releaseDate the new release date
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	/**
	 * Gets the finish date.
	 *
	 * @return the finish date
	 */
	public String getFinishDate() {
		return finishDate;
	}
	
	/**
	 * Sets the finish date.
	 *
	 * @param finishDate the new finish date
	 */
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * Gets the voting code 5.
	 *
	 * @return the voting code 5
	 */
	public String getVotingCode5() {
		return votingCode5;
	}
	
	/**
	 * Sets the voting code 5.
	 *
	 * @param votingCode5 the new voting code 5
	 */
	public void setVotingCode5(String votingCode5) {
		this.votingCode5 = votingCode5;
	}
	
	/**
	 * Gets the voting code 6.
	 *
	 * @return the voting code 6
	 */
	public String getVotingCode6() {
		return votingCode6;
	}
	
	/**
	 * Sets the voting code 6.
	 *
	 * @param votingCode6 the new voting code 6
	 */
	public void setVotingCode6(String votingCode6) {
		this.votingCode6 = votingCode6;
	}
	
	/**
	 * Gets the voting code 7.
	 *
	 * @return the voting code 7
	 */
	public String getVotingCode7() {
		return votingCode7;
	}
	
	/**
	 * Sets the voting code 7.
	 *
	 * @param votingCode7 the new voting code 7
	 */
	public void setVotingCode7(String votingCode7) {
		this.votingCode7 = votingCode7;
	}
	
	/**
	 * Gets the voting code 8.
	 *
	 * @return the voting code 8
	 */
	public String getVotingCode8() {
		return votingCode8;
	}
	
	/**
	 * Sets the voting code 8.
	 *
	 * @param votingCode8 the new voting code 8
	 */
	public void setVotingCode8(String votingCode8) {
		this.votingCode8 = votingCode8;
	}
	
	/**
	 * Gets the voting code 9.
	 *
	 * @return the voting code 9
	 */
	public String getVotingCode9() {
		return votingCode9;
	}
	
	/**
	 * Sets the voting code 9.
	 *
	 * @param votingCode9 the new voting code 9
	 */
	public void setVotingCode9(String votingCode9) {
		this.votingCode9 = votingCode9;
	}
	
	/**
	 * Gets the voting name 5.
	 *
	 * @return the voting name 5
	 */
	public String getVotingName5() {
		return votingName5;
	}
	
	/**
	 * Sets the voting name 5.
	 *
	 * @param votingName5 the new voting name 5
	 */
	public void setVotingName5(String votingName5) {
		this.votingName5 = votingName5;
	}
	
	/**
	 * Gets the voting name 6.
	 *
	 * @return the voting name 6
	 */
	public String getVotingName6() {
		return votingName6;
	}
	
	/**
	 * Sets the voting name 6.
	 *
	 * @param votingName6 the new voting name 6
	 */
	public void setVotingName6(String votingName6) {
		this.votingName6 = votingName6;
	}
	
	/**
	 * Gets the voting name 7.
	 *
	 * @return the voting name 7
	 */
	public String getVotingName7() {
		return votingName7;
	}
	
	/**
	 * Sets the voting name 7.
	 *
	 * @param votingName7 the new voting name 7
	 */
	public void setVotingName7(String votingName7) {
		this.votingName7 = votingName7;
	}
	
	/**
	 * Gets the voting name 8.
	 *
	 * @return the voting name 8
	 */
	public String getVotingName8() {
		return votingName8;
	}
	
	/**
	 * Sets the voting name 8.
	 *
	 * @param votingName8 the new voting name 8
	 */
	public void setVotingName8(String votingName8) {
		this.votingName8 = votingName8;
	}
	
	/**
	 * Gets the voting name 9.
	 *
	 * @return the voting name 9
	 */
	public String getVotingName9() {
		return votingName9;
	}
	
	/**
	 * Sets the voting name 9.
	 *
	 * @param votingName9 the new voting name 9
	 */
	public void setVotingName9(String votingName9) {
		this.votingName9 = votingName9;
	}
	
	/**
	 * Gets the result voting code 5.
	 *
	 * @return the result voting code 5
	 */
	public Integer getResultVotingCode5() {
		return resultVotingCode5;
	}
	
	/**
	 * Sets the result voting code 5.
	 *
	 * @param resultVotingCode5 the new result voting code 5
	 */
	public void setResultVotingCode5(Integer resultVotingCode5) {
		this.resultVotingCode5 = resultVotingCode5;
	}
	
	/**
	 * Gets the result voting code 6.
	 *
	 * @return the result voting code 6
	 */
	public Integer getResultVotingCode6() {
		return resultVotingCode6;
	}
	
	/**
	 * Sets the result voting code 6.
	 *
	 * @param resultVotingCode6 the new result voting code 6
	 */
	public void setResultVotingCode6(Integer resultVotingCode6) {
		this.resultVotingCode6 = resultVotingCode6;
	}
	
	/**
	 * Gets the result voting code 7.
	 *
	 * @return the result voting code 7
	 */
	public Integer getResultVotingCode7() {
		return resultVotingCode7;
	}
	
	/**
	 * Sets the result voting code 7.
	 *
	 * @param resultVotingCode7 the new result voting code 7
	 */
	public void setResultVotingCode7(Integer resultVotingCode7) {
		this.resultVotingCode7 = resultVotingCode7;
	}
	
	/**
	 * Gets the result voting code 8.
	 *
	 * @return the result voting code 8
	 */
	public Integer getResultVotingCode8() {
		return resultVotingCode8;
	}
	
	/**
	 * Sets the result voting code 8.
	 *
	 * @param resultVotingCode8 the new result voting code 8
	 */
	public void setResultVotingCode8(Integer resultVotingCode8) {
		this.resultVotingCode8 = resultVotingCode8;
	}
	
	/**
	 * Gets the result voting code 9.
	 *
	 * @return the result voting code 9
	 */
	public Integer getResultVotingCode9() {
		return resultVotingCode9;
	}
	
	/**
	 * Sets the result voting code 9.
	 *
	 * @param resultVotingCode9 the new result voting code 9
	 */
	public void setResultVotingCode9(Integer resultVotingCode9) {
		this.resultVotingCode9 = resultVotingCode9;
	}
	
}
