
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
 *         &lt;element ref="{http://benchresources.in/cdm/Player}DictCollectionModel" maxOccurs="unbounded" minOccurs="0"/>
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
    "dictCollectionModel"
})
@XmlRootElement(name = "DictCollectionList", namespace = "http://benchresources.in/cdm/Player")
public class DictCollectionList {

    @XmlElement(name = "DictCollectionModel", namespace = "http://benchresources.in/cdm/Player")
    protected List<DictCollectionModel> dictCollectionModel;

    /**
     * Gets the value of the dictCollectionModel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dictCollectionModel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDictCollectionModel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DictCollectionModel }
     * 
     * 
     */
    public List<DictCollectionModel> getDictCollectionModel() {
        if (dictCollectionModel == null) {
            dictCollectionModel = new ArrayList<DictCollectionModel>();
        }
        return this.dictCollectionModel;
    }

}
