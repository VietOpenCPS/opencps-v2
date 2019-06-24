//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.21 at 04:27:41 PM ICT 
//


package org.opencps.api.v21.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessAction" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="actionCode">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="actionName">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="preStepCode">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="postStepCode">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="autoEvent">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="preCondition">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="allowAssignUser" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="assignUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="assignUserName">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="requestPayment" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="paymentFee">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="createDossierFiles">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="returnDossierFiles">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="eSignature" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="signatureType">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="createDossiers">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "processAction"
})
@XmlRootElement(name = "actions")
public class Actions {

    @XmlElement(name = "ProcessAction", required = true)
    protected List<Actions.ProcessAction> processAction;

    /**
     * Gets the value of the processAction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processAction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Actions.ProcessAction }
     * 
     * 
     */
    public List<Actions.ProcessAction> getProcessAction() {
        if (processAction == null) {
            processAction = new ArrayList<Actions.ProcessAction>();
        }
        return this.processAction;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="actionCode">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="actionName">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="preStepCode">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="postStepCode">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="autoEvent">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="preCondition">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="allowAssignUser" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="assignUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="assignUserName">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="requestPayment" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="paymentFee">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="createDossierFiles">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="returnDossierFiles">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="eSignature" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="signatureType">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="createDossiers">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "actionCode",
        "actionName",
        "preStepCode",
        "postStepCode",
        "autoEvent",
        "preCondition",
        "allowAssignUser",
        "assignUserId",
        "assignUserName",
        "requestPayment",
        "paymentFee",
        "createDossierFiles",
        "returnDossierFiles",
        "eSignature",
        "signatureType",
        "createDossiers"
    })
    public static class ProcessAction {

        @XmlElement(required = true)
        protected String actionCode;
        @XmlElement(required = true)
        protected String actionName;
        @XmlElement(required = true)
        protected String preStepCode;
        @XmlElement(required = true)
        protected String postStepCode;
        @XmlElement(required = true)
        protected String autoEvent;
        @XmlElement(required = true)
        protected String preCondition;
        protected int allowAssignUser;
        protected int assignUserId;
        @XmlElement(required = true)
        protected String assignUserName;
        protected int requestPayment;
        @XmlElement(required = true)
        protected String paymentFee;
        @XmlElement(required = true)
        protected String createDossierFiles;
        @XmlElement(required = true)
        protected String returnDossierFiles;
        protected boolean eSignature;
        @XmlElement(required = true)
        protected String signatureType;
        @XmlElement(required = true)
        protected String createDossiers;

        /**
         * Gets the value of the actionCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getActionCode() {
            return actionCode;
        }

        /**
         * Sets the value of the actionCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setActionCode(String value) {
            this.actionCode = value;
        }

        /**
         * Gets the value of the actionName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getActionName() {
            return actionName;
        }

        /**
         * Sets the value of the actionName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setActionName(String value) {
            this.actionName = value;
        }

        /**
         * Gets the value of the preStepCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPreStepCode() {
            return preStepCode;
        }

        /**
         * Sets the value of the preStepCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPreStepCode(String value) {
            this.preStepCode = value;
        }

        /**
         * Gets the value of the postStepCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPostStepCode() {
            return postStepCode;
        }

        /**
         * Sets the value of the postStepCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPostStepCode(String value) {
            this.postStepCode = value;
        }

        /**
         * Gets the value of the autoEvent property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAutoEvent() {
            return autoEvent;
        }

        /**
         * Sets the value of the autoEvent property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAutoEvent(String value) {
            this.autoEvent = value;
        }

        /**
         * Gets the value of the preCondition property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPreCondition() {
            return preCondition;
        }

        /**
         * Sets the value of the preCondition property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPreCondition(String value) {
            this.preCondition = value;
        }

        /**
         * Gets the value of the allowAssignUser property.
         * 
         */
        public int getAllowAssignUser() {
            return allowAssignUser;
        }

        /**
         * Sets the value of the allowAssignUser property.
         * 
         */
        public void setAllowAssignUser(int value) {
            this.allowAssignUser = value;
        }

        /**
         * Gets the value of the assignUserId property.
         * 
         */
        public int getAssignUserId() {
            return assignUserId;
        }

        /**
         * Sets the value of the assignUserId property.
         * 
         */
        public void setAssignUserId(int value) {
            this.assignUserId = value;
        }

        /**
         * Gets the value of the assignUserName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAssignUserName() {
            return assignUserName;
        }

        /**
         * Sets the value of the assignUserName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAssignUserName(String value) {
            this.assignUserName = value;
        }

        /**
         * Gets the value of the requestPayment property.
         * 
         */
        public int getRequestPayment() {
            return requestPayment;
        }

        /**
         * Sets the value of the requestPayment property.
         * 
         */
        public void setRequestPayment(int value) {
            this.requestPayment = value;
        }

        /**
         * Gets the value of the paymentFee property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPaymentFee() {
            return paymentFee;
        }

        /**
         * Sets the value of the paymentFee property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPaymentFee(String value) {
            this.paymentFee = value;
        }

        /**
         * Gets the value of the createDossierFiles property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreateDossierFiles() {
            return createDossierFiles;
        }

        /**
         * Sets the value of the createDossierFiles property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreateDossierFiles(String value) {
            this.createDossierFiles = value;
        }

        /**
         * Gets the value of the returnDossierFiles property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReturnDossierFiles() {
            return returnDossierFiles;
        }

        /**
         * Sets the value of the returnDossierFiles property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReturnDossierFiles(String value) {
            this.returnDossierFiles = value;
        }

        /**
         * Gets the value of the eSignature property.
         * 
         */
        public boolean isESignature() {
            return eSignature;
        }

        /**
         * Sets the value of the eSignature property.
         * 
         */
        public void setESignature(boolean value) {
            this.eSignature = value;
        }

        /**
         * Gets the value of the signatureType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSignatureType() {
            return signatureType;
        }

        /**
         * Sets the value of the signatureType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSignatureType(String value) {
            this.signatureType = value;
        }

        /**
         * Gets the value of the createDossiers property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreateDossiers() {
            return createDossiers;
        }

        /**
         * Sets the value of the createDossiers property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreateDossiers(String value) {
            this.createDossiers = value;
        }

    }

}
