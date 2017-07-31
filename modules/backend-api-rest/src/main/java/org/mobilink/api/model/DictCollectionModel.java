
package org.mobilink.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="dictCollectionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="companyId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="modifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="collectionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="collectionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "dictCollectionId",
    "companyId",
    "groupId",
    "userId",
    "createDate",
    "modifiedDate",
    "collectionCode",
    "collectionName",
    "description"
})
@XmlRootElement(name = "DictCollectionModel", namespace = "http://benchresources.in/cdm/Player")
public class DictCollectionModel {

    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long dictCollectionId;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long companyId;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long groupId;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long userId;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifiedDate;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    protected String collectionCode;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    protected String collectionName;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    protected String description;

    /**
     * Gets the value of the dictCollectionId property.
     * 
     */
    public long getDictCollectionId() {
        return dictCollectionId;
    }

    /**
     * Sets the value of the dictCollectionId property.
     * 
     */
    public void setDictCollectionId(long value) {
        this.dictCollectionId = value;
    }

    /**
     * Gets the value of the companyId property.
     * 
     */
    public long getCompanyId() {
        return companyId;
    }

    /**
     * Sets the value of the companyId property.
     * 
     */
    public void setCompanyId(long value) {
        this.companyId = value;
    }

    /**
     * Gets the value of the groupId property.
     * 
     */
    public long getGroupId() {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * 
     */
    public void setGroupId(long value) {
        this.groupId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the modifiedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the value of the modifiedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifiedDate(XMLGregorianCalendar value) {
        this.modifiedDate = value;
    }

    /**
     * Gets the value of the collectionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectionCode() {
        return collectionCode;
    }

    /**
     * Sets the value of the collectionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectionCode(String value) {
        this.collectionCode = value;
    }

    /**
     * Gets the value of the collectionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * Sets the value of the collectionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectionName(String value) {
        this.collectionName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
