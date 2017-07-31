
package org.mobilink.api.model;

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
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{http://benchresources.in/cdm/Player}DictItemModel" maxOccurs="unbounded" minOccurs="0"/>
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
    "total",
    "dictItemModel"
})
@XmlRootElement(name = "DictItemResults", namespace = "http://benchresources.in/cdm/Player")
public class DictItemResults {

    @XmlElement(namespace = "http://benchresources.in/cdm/Player")
    protected int total;
    @XmlElement(name = "DictItemModel", namespace = "http://benchresources.in/cdm/Player")
    protected List<DictItemModel> dictItemModel;

    /**
     * Gets the value of the total property.
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

    /**
     * Gets the value of the dictItemModel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dictItemModel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDictItemModel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DictItemModel }
     * 
     * 
     */
    public List<DictItemModel> getDictItemModel() {
        if (dictItemModel == null) {
            dictItemModel = new ArrayList<DictItemModel>();
        }
        return this.dictItemModel;
    }

}
