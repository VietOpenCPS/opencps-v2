//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.11 at 02:28:15 PM ICT 
//


package org.opencps.thirdparty.system.nsw.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Thongtin_Loi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Thongtin_Loi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ma_loi">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="250"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ten_loi">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5000"/>
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
@XmlType(name = "Thongtin_Loi", propOrder = {
    "maLoi",
    "tenLoi"
})
public class ThongtinLoi {

    @XmlElement(name = "ma_loi", required = true)
    protected String maLoi;
    @XmlElement(name = "ten_loi", required = true)
    protected String tenLoi;

    /**
     * Gets the value of the maLoi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaLoi() {
        return maLoi;
    }

    /**
     * Sets the value of the maLoi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaLoi(String value) {
        this.maLoi = value;
    }

    /**
     * Gets the value of the tenLoi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenLoi() {
        return tenLoi;
    }

    /**
     * Sets the value of the tenLoi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenLoi(String value) {
        this.tenLoi = value;
    }

}
