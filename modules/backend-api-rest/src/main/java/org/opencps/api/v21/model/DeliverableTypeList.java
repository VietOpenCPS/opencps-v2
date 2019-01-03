//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.03 at 02:46:10 PM ICT 
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
 *         &lt;element name="DeliverableType" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="typeCode">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="typeName">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="formScriptFileId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="formReportFileId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="codePattern">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="counter" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="mappingData">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="1000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="docSync" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="dataConfig">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="1000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tableConfig">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="1000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="govAgencies">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="formScript">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="10000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="formReport">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="10000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="roleCode">
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
    "deliverableType"
})
@XmlRootElement(name = "DeliverableTypeList")
public class DeliverableTypeList {

    @XmlElement(name = "DeliverableType", required = true)
    protected List<DeliverableTypeList.DeliverableType> deliverableType;

    /**
     * Gets the value of the deliverableType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliverableType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliverableType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliverableTypeList.DeliverableType }
     * 
     * 
     */
    public List<DeliverableTypeList.DeliverableType> getDeliverableType() {
        if (deliverableType == null) {
            deliverableType = new ArrayList<DeliverableTypeList.DeliverableType>();
        }
        return this.deliverableType;
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
     *         &lt;element name="typeCode">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="typeName">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="formScriptFileId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="formReportFileId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="codePattern">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="counter" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="mappingData">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="1000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="docSync" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="dataConfig">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="1000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tableConfig">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="1000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="govAgencies">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="formScript">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="10000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="formReport">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="10000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="roleCode">
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
        "typeCode",
        "typeName",
        "formScriptFileId",
        "formReportFileId",
        "codePattern",
        "counter",
        "mappingData",
        "docSync",
        "dataConfig",
        "tableConfig",
        "govAgencies",
        "formScript",
        "formReport",
        "roleCode"
    })
    public static class DeliverableType {

        @XmlElement(required = true)
        protected String typeCode;
        @XmlElement(required = true)
        protected String typeName;
        protected Integer formScriptFileId;
        protected Integer formReportFileId;
        @XmlElement(required = true)
        protected String codePattern;
        protected Integer counter;
        @XmlElement(required = true)
        protected String mappingData;
        protected Integer docSync;
        @XmlElement(required = true)
        protected String dataConfig;
        @XmlElement(required = true)
        protected String tableConfig;
        @XmlElement(required = true)
        protected String govAgencies;
        @XmlElement(required = true)
        protected String formScript;
        @XmlElement(required = true)
        protected String formReport;
        @XmlElement(required = true)
        protected String roleCode;

        /**
         * Gets the value of the typeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTypeCode() {
            return typeCode;
        }

        /**
         * Sets the value of the typeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTypeCode(String value) {
            this.typeCode = value;
        }

        /**
         * Gets the value of the typeName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTypeName() {
            return typeName;
        }

        /**
         * Sets the value of the typeName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTypeName(String value) {
            this.typeName = value;
        }

        /**
         * Gets the value of the formScriptFileId property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getFormScriptFileId() {
            return formScriptFileId;
        }

        /**
         * Sets the value of the formScriptFileId property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setFormScriptFileId(Integer value) {
            this.formScriptFileId = value;
        }

        /**
         * Gets the value of the formReportFileId property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getFormReportFileId() {
            return formReportFileId;
        }

        /**
         * Sets the value of the formReportFileId property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setFormReportFileId(Integer value) {
            this.formReportFileId = value;
        }

        /**
         * Gets the value of the codePattern property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodePattern() {
            return codePattern;
        }

        /**
         * Sets the value of the codePattern property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodePattern(String value) {
            this.codePattern = value;
        }

        /**
         * Gets the value of the counter property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getCounter() {
            return counter;
        }

        /**
         * Sets the value of the counter property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setCounter(Integer value) {
            this.counter = value;
        }

        /**
         * Gets the value of the mappingData property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMappingData() {
            return mappingData;
        }

        /**
         * Sets the value of the mappingData property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMappingData(String value) {
            this.mappingData = value;
        }

        /**
         * Gets the value of the docSync property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getDocSync() {
            return docSync;
        }

        /**
         * Sets the value of the docSync property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setDocSync(Integer value) {
            this.docSync = value;
        }

        /**
         * Gets the value of the dataConfig property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDataConfig() {
            return dataConfig;
        }

        /**
         * Sets the value of the dataConfig property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDataConfig(String value) {
            this.dataConfig = value;
        }

        /**
         * Gets the value of the tableConfig property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTableConfig() {
            return tableConfig;
        }

        /**
         * Sets the value of the tableConfig property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTableConfig(String value) {
            this.tableConfig = value;
        }

        /**
         * Gets the value of the govAgencies property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGovAgencies() {
            return govAgencies;
        }

        /**
         * Sets the value of the govAgencies property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGovAgencies(String value) {
            this.govAgencies = value;
        }

        /**
         * Gets the value of the formScript property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFormScript() {
            return formScript;
        }

        /**
         * Sets the value of the formScript property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFormScript(String value) {
            this.formScript = value;
        }

        /**
         * Gets the value of the formReport property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFormReport() {
            return formReport;
        }

        /**
         * Sets the value of the formReport property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFormReport(String value) {
            this.formReport = value;
        }

        /**
         * Gets the value of the roleCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRoleCode() {
            return roleCode;
        }

        /**
         * Sets the value of the roleCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRoleCode(String value) {
            this.roleCode = value;
        }

    }

}
