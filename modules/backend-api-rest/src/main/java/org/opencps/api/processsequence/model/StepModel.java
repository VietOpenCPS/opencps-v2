//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.18 at 02:47:16 PM ICT 
//


package org.opencps.api.processsequence.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StepModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StepModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fromStepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fromStepName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="durationCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="actions" type="{}ActionModel" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StepModel", propOrder = {
    "fromStepCode",
    "fromStepName",
    "groupName",
    "durationCount",
    "actions"
})
public class StepModel {

    protected String fromStepCode;
    protected String fromStepName;
    protected String groupName;
    protected Double durationCount;
    protected List<ActionModel> actions;

    /**
     * Gets the value of the fromStepCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromStepCode() {
        return fromStepCode;
    }

    /**
     * Sets the value of the fromStepCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromStepCode(String value) {
        this.fromStepCode = value;
    }

    /**
     * Gets the value of the fromStepName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromStepName() {
        return fromStepName;
    }

    /**
     * Sets the value of the fromStepName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromStepName(String value) {
        this.fromStepName = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the durationCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Double getDurationCount() {
        return durationCount;
    }

    /**
     * Sets the value of the durationCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDurationCount(Double value) {
        this.durationCount = value;
    }

    /**
     * Gets the value of the actions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActionModel }
     * 
     * 
     */
    public List<ActionModel> getActions() {
        if (actions == null) {
            actions = new ArrayList<ActionModel>();
        }
        return this.actions;
    }

}
