package org.opencps.api.deliverable.model;

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
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keyword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicant" type="{http://www.w3.org/2001/XMLSchema}String" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}String" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}String" minOccurs="0"/>
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
    "state",
    "agency",
    "keyword",
    "type",
    "applicant",
    "owner",
    "start",
    "end",
    "sort",
    "order"
})
@XmlRootElement(name = "DeliverableFileSearch")
public class DeliverableSearchModel {
	@QueryParam(value = "state")
    private String state;
	@DefaultValue(StringPool.BLANK) @QueryParam(value = "agency")
    private String agency;
	@QueryParam(value = "keyword")
	private String keyword;
	@QueryParam(value = "type")
    private String type;
	@QueryParam(value = "applicant")
    private String applicant;
	@QueryParam(value = "owner")
	private String owner;
	@QueryParam(value = "start")
	private int start;
	@QueryParam(value = "end")
	private int end;
	@DefaultValue("modifiedDate") @QueryParam("sort")
	private String sort;
	@QueryParam(value = "order")
	private String order;
	
	/**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the keyword property.
     * 
     * @param state
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String state) {
        this.state = state;
    }

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
		return type;
	}

    /**
     * Sets the value of the type property.
     * 
     * @param keyword
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setType(String type) {
		this.type = type;
	}

	/**
     * Gets the value of the applicant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicant() {
		return applicant;
	}

    /**
     * Sets the value of the applicant property.
     * 
     * @param service
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

    /**
     * Gets the value of the agency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getAgency() {
		return agency;
	}

    /**
     * Sets the value of the agency property.
     * 
     * @param agency
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setAgency(String agency) {
		this.agency = agency;
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

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getOwner() {
		return owner;
	}

    /**
     * Sets the value of the owner property.
     * 
     * @param order
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setOwner(String owner) {
		this.owner = owner;
	}

}
