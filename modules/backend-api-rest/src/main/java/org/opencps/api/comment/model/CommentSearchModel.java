//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.17 at 11:22:25 AM ICT 
//


package org.opencps.api.comment.model;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="classPK" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "keywords",
    "sort",
    "order",
    "start",
    "end",
    "className",
    "classPK",
    "email",
    "opinion"
})
@XmlRootElement(name = "CommentSearchModel")
public class CommentSearchModel {

	@FormParam(value = "keywords")
    protected String keywords;
	@FormParam(value = "sort")
    protected String sort;
	@FormParam(value = "order")
    protected boolean order;
	@FormParam(value = "start")
	@DefaultValue(value="-1")
    protected int start;
	@FormParam(value = "end")
	@DefaultValue(value="-1")
    protected int end;
	@FormParam(value = "className")
    protected String className;
	@FormParam(value = "classPK")
    protected long classPK;
	@FormParam(value = "email")
    protected String email;
	@FormParam(value = "opinion")
    protected String opinion;
    

    /**
     * Gets the value of the keywords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of the keywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeywords(String value) {
        this.keywords = value;
    }

    /**
     * Gets the value of the sort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSort() {
        return sort;
    }

    /**
     * Sets the value of the sort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSort(String value) {
        this.sort = value;
    }

    /**
     * Gets the value of the order property.
     * 
     */
    public boolean isOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     */
    public void setOrder(boolean value) {
        this.order = value;
    }

    /**
     * Gets the value of the start property.
     * 
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     */
    public void setStart(int value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     * 
     */
    public int getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     */
    public void setEnd(int value) {
        this.end = value;
    }

    /**
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Gets the value of the classPK property.
     * 
     */
    public long getClassPK() {
        return classPK;
    }

    /**
     * Sets the value of the classPK property.
     * 
     */
    public void setClassPK(long value) {
        this.classPK = value;
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
     * Gets the value of the opinion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * Sets the value of the opinion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpinion(String value) {
        this.opinion = value;
    }
    
}
