//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.11 at 01:51:54 PM ICT 
//


package org.opencps.thirdparty.system.nsw.vietnameselao.model;

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
 *         &lt;element name="Official" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Individual" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Business" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Others" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "official",
    "individual",
    "business",
    "others"
})
@XmlRootElement(name = "PurposeOfDeparture")
public class PurposeOfDeparture {

    @XmlElement(name = "Official", required = true)
    protected String official;
    @XmlElement(name = "Individual", required = true)
    protected String individual;
    @XmlElement(name = "Business", required = true)
    protected String business;
    @XmlElement(name = "Others", required = true)
    protected String others;

    /**
     * Gets the value of the official property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficial() {
        return official;
    }

    /**
     * Sets the value of the official property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficial(String value) {
        this.official = value;
    }

    /**
     * Gets the value of the individual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividual() {
        return individual;
    }

    /**
     * Sets the value of the individual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividual(String value) {
        this.individual = value;
    }

    /**
     * Gets the value of the business property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiness() {
        return business;
    }

    /**
     * Sets the value of the business property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiness(String value) {
        this.business = value;
    }

    /**
     * Gets the value of the others property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthers() {
        return others;
    }

    /**
     * Sets the value of the others property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthers(String value) {
        this.others = value;
    }

}
