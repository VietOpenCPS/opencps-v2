package org.opencps.api.einvoice.vnpt.model;
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
 *         &lt;element name="UpdateCusResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "updateCusResult"
})
@XmlRootElement(name = "UpdateCusResponse")
public class UpdateCusResponse {

    @XmlElement(name = "UpdateCusResult")
    protected int updateCusResult;

    /**
     * Gets the value of the updateCusResult property.
     * 
     */
    public int getUpdateCusResult() {
        return updateCusResult;
    }

    /**
     * Sets the value of the updateCusResult property.
     * 
     */
    public void setUpdateCusResult(int value) {
        this.updateCusResult = value;
    }

}
