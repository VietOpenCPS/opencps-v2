//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.22 at 03:30:55 PM ICT 
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
 *         &lt;element name="Business" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Enterprise_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Msdn">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Enterprise_Code">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Enterprise_Gdt_Code">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Enterprise_type_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Name">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Short_Name">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Name_F">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Founding_Date">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Enterprise_Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Legal_Name">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Site_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Subunit_Parent_Ent_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="HOAddress">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="BusinessActivity">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Email">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Website">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Mobile">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Phone">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Fax">
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
    "business"
})
@XmlRootElement(name = "BusinessList")
public class BusinessList {

    @XmlElement(name = "Business", required = true)
    protected List<BusinessList.Business> business;

    /**
     * Gets the value of the business property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the business property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusiness().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessList.Business }
     * 
     * 
     */
    public List<BusinessList.Business> getBusiness() {
        if (business == null) {
            business = new ArrayList<BusinessList.Business>();
        }
        return this.business;
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
     *         &lt;element name="Enterprise_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Msdn">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Enterprise_Code">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Enterprise_Gdt_Code">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Enterprise_type_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Name">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Short_Name">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Name_F">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Founding_Date">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Enterprise_Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Legal_Name">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Site_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Subunit_Parent_Ent_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="HOAddress">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="BusinessActivity">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Email">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Website">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Mobile">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Phone">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Fax">
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
        "enterpriseId",
        "msdn",
        "enterpriseCode",
        "enterpriseGdtCode",
        "enterpriseTypeId",
        "name",
        "shortName",
        "nameF",
        "foundingDate",
        "enterpriseStatus",
        "legalName",
        "siteId",
        "subunitParentEntId",
        "hoAddress",
        "businessActivity",
        "email",
        "website",
        "mobile",
        "phone",
        "fax"
    })
    public static class Business {

        @XmlElement(name = "Enterprise_Id")
        protected int enterpriseId;
        @XmlElement(name = "Msdn", required = true)
        protected String msdn;
        @XmlElement(name = "Enterprise_Code", required = true)
        protected String enterpriseCode;
        @XmlElement(name = "Enterprise_Gdt_Code", required = true)
        protected String enterpriseGdtCode;
        @XmlElement(name = "Enterprise_type_id")
        protected int enterpriseTypeId;
        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "Short_Name", required = true)
        protected String shortName;
        @XmlElement(name = "Name_F", required = true)
        protected String nameF;
        @XmlElement(name = "Founding_Date", required = true)
        protected String foundingDate;
        @XmlElement(name = "Enterprise_Status")
        protected int enterpriseStatus;
        @XmlElement(name = "Legal_Name", required = true)
        protected String legalName;
        @XmlElement(name = "Site_Id")
        protected int siteId;
        @XmlElement(name = "Subunit_Parent_Ent_Id")
        protected int subunitParentEntId;
        @XmlElement(name = "HOAddress", required = true)
        protected String hoAddress;
        @XmlElement(name = "BusinessActivity", required = true)
        protected String businessActivity;
        @XmlElement(name = "Email", required = true)
        protected String email;
        @XmlElement(name = "Website", required = true)
        protected String website;
        @XmlElement(name = "Mobile", required = true)
        protected String mobile;
        @XmlElement(name = "Phone", required = true)
        protected String phone;
        @XmlElement(name = "Fax", required = true)
        protected String fax;

        /**
         * Gets the value of the enterpriseId property.
         * 
         */
        public int getEnterpriseId() {
            return enterpriseId;
        }

        /**
         * Sets the value of the enterpriseId property.
         * 
         */
        public void setEnterpriseId(int value) {
            this.enterpriseId = value;
        }

        /**
         * Gets the value of the msdn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMsdn() {
            return msdn;
        }

        /**
         * Sets the value of the msdn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMsdn(String value) {
            this.msdn = value;
        }

        /**
         * Gets the value of the enterpriseCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEnterpriseCode() {
            return enterpriseCode;
        }

        /**
         * Sets the value of the enterpriseCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEnterpriseCode(String value) {
            this.enterpriseCode = value;
        }

        /**
         * Gets the value of the enterpriseGdtCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEnterpriseGdtCode() {
            return enterpriseGdtCode;
        }

        /**
         * Sets the value of the enterpriseGdtCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEnterpriseGdtCode(String value) {
            this.enterpriseGdtCode = value;
        }

        /**
         * Gets the value of the enterpriseTypeId property.
         * 
         */
        public int getEnterpriseTypeId() {
            return enterpriseTypeId;
        }

        /**
         * Sets the value of the enterpriseTypeId property.
         * 
         */
        public void setEnterpriseTypeId(int value) {
            this.enterpriseTypeId = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the shortName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShortName() {
            return shortName;
        }

        /**
         * Sets the value of the shortName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShortName(String value) {
            this.shortName = value;
        }

        /**
         * Gets the value of the nameF property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNameF() {
            return nameF;
        }

        /**
         * Sets the value of the nameF property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNameF(String value) {
            this.nameF = value;
        }

        /**
         * Gets the value of the foundingDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFoundingDate() {
            return foundingDate;
        }

        /**
         * Sets the value of the foundingDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFoundingDate(String value) {
            this.foundingDate = value;
        }

        /**
         * Gets the value of the enterpriseStatus property.
         * 
         */
        public int getEnterpriseStatus() {
            return enterpriseStatus;
        }

        /**
         * Sets the value of the enterpriseStatus property.
         * 
         */
        public void setEnterpriseStatus(int value) {
            this.enterpriseStatus = value;
        }

        /**
         * Gets the value of the legalName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLegalName() {
            return legalName;
        }

        /**
         * Sets the value of the legalName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLegalName(String value) {
            this.legalName = value;
        }

        /**
         * Gets the value of the siteId property.
         * 
         */
        public int getSiteId() {
            return siteId;
        }

        /**
         * Sets the value of the siteId property.
         * 
         */
        public void setSiteId(int value) {
            this.siteId = value;
        }

        /**
         * Gets the value of the subunitParentEntId property.
         * 
         */
        public int getSubunitParentEntId() {
            return subunitParentEntId;
        }

        /**
         * Sets the value of the subunitParentEntId property.
         * 
         */
        public void setSubunitParentEntId(int value) {
            this.subunitParentEntId = value;
        }

        /**
         * Gets the value of the hoAddress property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHOAddress() {
            return hoAddress;
        }

        /**
         * Sets the value of the hoAddress property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHOAddress(String value) {
            this.hoAddress = value;
        }

        /**
         * Gets the value of the businessActivity property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBusinessActivity() {
            return businessActivity;
        }

        /**
         * Sets the value of the businessActivity property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBusinessActivity(String value) {
            this.businessActivity = value;
        }

        /**
         * Gets the value of the email property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmail() {
            return email;
        }

        /**
         * Sets the value of the email property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmail(String value) {
            this.email = value;
        }

        /**
         * Gets the value of the website property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWebsite() {
            return website;
        }

        /**
         * Sets the value of the website property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWebsite(String value) {
            this.website = value;
        }

        /**
         * Gets the value of the mobile property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMobile() {
            return mobile;
        }

        /**
         * Sets the value of the mobile property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMobile(String value) {
            this.mobile = value;
        }

        /**
         * Gets the value of the phone property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPhone() {
            return phone;
        }

        /**
         * Sets the value of the phone property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPhone(String value) {
            this.phone = value;
        }

        /**
         * Gets the value of the fax property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFax() {
            return fax;
        }

        /**
         * Sets the value of the fax property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFax(String value) {
            this.fax = value;
        }

    }

}
