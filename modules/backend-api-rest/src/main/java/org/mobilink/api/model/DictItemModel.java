
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
 *         &lt;element name="dictItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="companyId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="modifiedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dictCollectionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="itemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parentItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="treeIndex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="issueStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dictVersionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sibling" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "dictItemId",
    "companyId",
    "groupId",
    "userId",
    "createDate",
    "modifiedDate",
    "dictCollectionId",
    "itemCode",
    "itemName",
    "itemDescription",
    "parentItemId",
    "treeIndex",
    "issueStatus",
    "dictVersionId",
    "sibling",
    "level"
})
@XmlRootElement(name = "DictItemModel", namespace = "http://benchresources.in/cdm/Player")
public class DictItemModel {

    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long dictItemId;
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
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long dictCollectionId;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    protected String itemCode;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    protected String itemName;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    protected String itemDescription;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long parentItemId;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player", required = true)
    protected String treeIndex;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected int issueStatus;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long dictVersionId;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected long sibling;
    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected int level;

    /**
     * Gets the value of the dictItemId property.
     * 
     */
    public long getDictItemId() {
        return dictItemId;
    }

    /**
     * Sets the value of the dictItemId property.
     * 
     */
    public void setDictItemId(long value) {
        this.dictItemId = value;
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
     * Gets the value of the itemCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the value of the itemCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemCode(String value) {
        this.itemCode = value;
    }

    /**
     * Gets the value of the itemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the value of the itemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * Gets the value of the itemDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the value of the itemDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemDescription(String value) {
        this.itemDescription = value;
    }

    /**
     * Gets the value of the parentItemId property.
     * 
     */
    public long getParentItemId() {
        return parentItemId;
    }

    /**
     * Sets the value of the parentItemId property.
     * 
     */
    public void setParentItemId(long value) {
        this.parentItemId = value;
    }

    /**
     * Gets the value of the treeIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreeIndex() {
        return treeIndex;
    }

    /**
     * Sets the value of the treeIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreeIndex(String value) {
        this.treeIndex = value;
    }

    /**
     * Gets the value of the issueStatus property.
     * 
     */
    public int getIssueStatus() {
        return issueStatus;
    }

    /**
     * Sets the value of the issueStatus property.
     * 
     */
    public void setIssueStatus(int value) {
        this.issueStatus = value;
    }

    /**
     * Gets the value of the dictVersionId property.
     * 
     */
    public long getDictVersionId() {
        return dictVersionId;
    }

    /**
     * Sets the value of the dictVersionId property.
     * 
     */
    public void setDictVersionId(long value) {
        this.dictVersionId = value;
    }

    /**
     * Gets the value of the sibling property.
     * 
     */
    public long getSibling() {
        return sibling;
    }

    /**
     * Sets the value of the sibling property.
     * 
     */
    public void setSibling(long value) {
        this.sibling = value;
    }

    /**
     * Gets the value of the level property.
     * 
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     */
    public void setLevel(int value) {
        this.level = value;
    }

}
