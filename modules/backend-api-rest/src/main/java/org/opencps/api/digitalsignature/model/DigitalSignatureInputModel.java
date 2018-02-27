//
//This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
//See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
//Any modifications to this file will be lost upon recompilation of the source schema. 
//Generated on: 2017.12.07 at 10:58:13 AM ICT 
//

package org.opencps.api.digitalsignature.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
* <p>Java class for DeliverableTypeInputModel complex type.
* 
* <p>The following schema fragment specifies the expected content contained within this class.
* 
* <pre>
* &lt;complexType name="DeliverableTypeInputModel">
*   &lt;complexContent>
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*       &lt;sequence>
*         &lt;element name="sign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
*         &lt;element name="signFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
*         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
* 
* 
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DigitalSignatureInputModel", propOrder = {
 "sign",
 "signFieldName",
 "fileName",
 "strIdArr",
 "fileEntryId",
 "actionCode",
 "actionUser",
 "actionNote",
 "assignUserId",
 "subUsers",
 "postStepCode"
})
public class DigitalSignatureInputModel {

	@FormParam(value = "sign")
	protected String sign;
	@FormParam(value = "signFieldName")
	protected String signFieldName;
	@FormParam(value = "fileName")
	protected String fileName;
	@FormParam(value = "strIdArr")
	protected String strIdArr;
	@FormParam(value = "fileEntryId")
	protected String fileEntryId;
	@FormParam(value = "actionCode")
	protected String actionCode;
	@FormParam(value = "actionUser")
	protected String actionUser;
	@FormParam(value = "actionNote")
	protected String actionNote;
	@FormParam(value = "assignUserId")
	protected String assignUserId;
	@FormParam(value = "subUsers")
	protected String subUsers;
	@FormParam(value = "postStepCode")
	protected String postStepCode;
	 /**
	  * Gets the value of the deliverableType property.
	  * 
	  * @return
	  *     possible object is
	  *     {@link String }
	  *     
	  */
	public String getSign() {
		return sign;
	}
	
	/**
	 * Sets the value of the deliverableType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**
	 * Gets the value of the deliverableType property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getSignFieldName() {
		return signFieldName;
	}
	
	/**
	 * Sets the value of the deliverableType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setSignFieldName(String signFieldName) {
		this.signFieldName = signFieldName;
	}
	
	/**
	 * Gets the value of the deliverableType property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Sets the value of the deliverableType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStrIdArr() {
		return strIdArr;
	}

	public void setStrIdArr(String strIdArr) {
		this.strIdArr = strIdArr;
	}

	public String getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(String fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionUser() {
		return actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}

	public String getActionNote() {
		return actionNote;
	}

	public void setActionNote(String actionNote) {
		this.actionNote = actionNote;
	}

	public String getAssignUserId() {
		return assignUserId;
	}

	public void setAssignUserId(String assignUserId) {
		this.assignUserId = assignUserId;
	}

	public String getSubUsers() {
		return subUsers;
	}

	public void setSubUsers(String subUsers) {
		this.subUsers = subUsers;
	}

	public String getPostStepCode() {
		return postStepCode;
	}

	public void setPostStepCode(String postStepCode) {
		this.postStepCode = postStepCode;
	}

}
