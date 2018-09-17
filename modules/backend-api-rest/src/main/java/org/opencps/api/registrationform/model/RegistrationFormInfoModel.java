package org.opencps.api.registrationform.model;

import com.liferay.petra.string.StringPool;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
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
 *         &lt;element name="_properties" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keyword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "_properties",
    "keyword",
    "start",
    "end",
    "sort",
    "order"
})
@XmlRootElement(name = "RegistrationFormInfoModel")
public class RegistrationFormInfoModel {

	@DefaultValue(StringPool.BLANK) @QueryParam(value = "_properties")
    private String _properties;
	@QueryParam(value = "keyword")
	private String keyword;
	@DefaultValue("0") @QueryParam(value = "start")
    protected Integer start;
	@DefaultValue("0") @QueryParam(value = "end")
    protected Integer end;
	@DefaultValue("modifiedDate") @QueryParam("sort")
    protected String sort;
	@DefaultValue(StringPool.BLANK) @QueryParam(value = "order")
    protected String order;

    /**
     * Gets the value of the keyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Sets the value of the keyword property.
     * 
     * @param keyword
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets the value of the _properties property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProperties() {
		return _properties;
	}

    /**
     * Sets the value of the _properties property.
     * 
     * @param service
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setProperties(String _properties) {
		this._properties = _properties;
	}


	/**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link int }
     *     
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param start
     *     allowed object is
     *     {@link int }
     *     
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link int }
     *     
     */
    public int getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param end
     *     allowed object is
     *     {@link int }
     *     
     */
    public void setEnd(int end) {
        this.end = end;
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
     * @param sort
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param order
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrder(String value) {
        this.order = value;
    }

}
