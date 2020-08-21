
package org.opencps.api.test.model;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="serverNo" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="serverName" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="serverName11">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="collapse"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="serverName22">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;whiteSpace value="preserve"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="serverName33">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[^\s]+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="serverId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "serverNo",
    "serverName",
    "serverName11",
    "serverName22",
    "serverName33",
    "serverId"
})
@XmlRootElement(name = "TestConfigModel")
public class TestConfigModel {

    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    @QueryParam("serverNo")
    protected String serverNo;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    @QueryParam("serverNo")
    protected String serverName;
    @XmlElement(required = true)
    @QueryParam("serverName11")
    protected String serverName11;
    @QueryParam("serverName22")
    @XmlElement(required = true)
    protected String serverName22;
    @QueryParam("serverName33")
    @XmlElement(required = true)
    protected String serverName33;
    @QueryParam("serverId")
    protected Integer serverId;

    /**
     * Gets the value of the serverNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerNo() {
        return serverNo;
    }

    /**
     * Sets the value of the serverNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerNo(String value) {
        this.serverNo = value;
    }

    /**
     * Gets the value of the serverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the value of the serverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerName(String value) {
        this.serverName = value;
    }

    /**
     * Gets the value of the serverName11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerName11() {
        return serverName11;
    }

    /**
     * Sets the value of the serverName11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerName11(String value) {
        this.serverName11 = value;
    }

    /**
     * Gets the value of the serverName22 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerName22() {
        return serverName22;
    }

    /**
     * Sets the value of the serverName22 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerName22(String value) {
        this.serverName22 = value;
    }

    /**
     * Gets the value of the serverName33 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerName33() {
        return serverName33;
    }

    /**
     * Sets the value of the serverName33 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerName33(String value) {
        this.serverName33 = value;
    }

    /**
     * Gets the value of the serverId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServerId() {
        return serverId;
    }

    /**
     * Sets the value of the serverId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServerId(Integer value) {
        this.serverId = value;
    }

}
