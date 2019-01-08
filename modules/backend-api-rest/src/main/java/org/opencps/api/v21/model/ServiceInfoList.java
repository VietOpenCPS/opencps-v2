//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.08 at 04:57:33 PM ICT 
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
 *         &lt;element name="ServiceInfo" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ProceduresCode">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ProceduresName">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Status">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="FieldCode">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="BranchCode">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Dossier">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Fee">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ProcessingDuration">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="OutCome">
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
    "serviceInfo"
})
@XmlRootElement(name = "ServiceInfoList")
public class ServiceInfoList {

    @XmlElement(name = "ServiceInfo", required = true)
    protected List<ServiceInfoList.ServiceInfo> serviceInfo;

    /**
     * Gets the value of the serviceInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceInfoList.ServiceInfo }
     * 
     * 
     */
    public List<ServiceInfoList.ServiceInfo> getServiceInfo() {
        if (serviceInfo == null) {
            serviceInfo = new ArrayList<ServiceInfoList.ServiceInfo>();
        }
        return this.serviceInfo;
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
     *         &lt;element name="ProceduresCode">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ProceduresName">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Status">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="FieldCode">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="BranchCode">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Dossier">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Fee">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ProcessingDuration">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="OutCome">
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
        "proceduresCode",
        "proceduresName",
        "status",
        "fieldCode",
        "branchCode",
        "dossier",
        "fee",
        "processingDuration",
        "outCome"
    })
    public static class ServiceInfo {

        @XmlElement(name = "ProceduresCode", required = true)
        protected String proceduresCode;
        @XmlElement(name = "ProceduresName", required = true)
        protected String proceduresName;
        @XmlElement(name = "Status", required = true)
        protected String status;
        @XmlElement(name = "FieldCode", required = true)
        protected String fieldCode;
        @XmlElement(name = "BranchCode", required = true)
        protected String branchCode;
        @XmlElement(name = "Dossier", required = true)
        protected String dossier;
        @XmlElement(name = "Fee", required = true)
        protected String fee;
        @XmlElement(name = "ProcessingDuration", required = true)
        protected String processingDuration;
        @XmlElement(name = "OutCome", required = true)
        protected String outCome;

        /**
         * Gets the value of the proceduresCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProceduresCode() {
            return proceduresCode;
        }

        /**
         * Sets the value of the proceduresCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProceduresCode(String value) {
            this.proceduresCode = value;
        }

        /**
         * Gets the value of the proceduresName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProceduresName() {
            return proceduresName;
        }

        /**
         * Sets the value of the proceduresName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProceduresName(String value) {
            this.proceduresName = value;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatus(String value) {
            this.status = value;
        }

        /**
         * Gets the value of the fieldCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFieldCode() {
            return fieldCode;
        }

        /**
         * Sets the value of the fieldCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFieldCode(String value) {
            this.fieldCode = value;
        }

        /**
         * Gets the value of the branchCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBranchCode() {
            return branchCode;
        }

        /**
         * Sets the value of the branchCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBranchCode(String value) {
            this.branchCode = value;
        }

        /**
         * Gets the value of the dossier property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDossier() {
            return dossier;
        }

        /**
         * Sets the value of the dossier property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDossier(String value) {
            this.dossier = value;
        }

        /**
         * Gets the value of the fee property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFee() {
            return fee;
        }

        /**
         * Sets the value of the fee property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFee(String value) {
            this.fee = value;
        }

        /**
         * Gets the value of the processingDuration property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProcessingDuration() {
            return processingDuration;
        }

        /**
         * Sets the value of the processingDuration property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProcessingDuration(String value) {
            this.processingDuration = value;
        }

        /**
         * Gets the value of the outCome property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOutCome() {
            return outCome;
        }

        /**
         * Sets the value of the outCome property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOutCome(String value) {
            this.outCome = value;
        }

    }

}