//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.10.23 at 09:44:18 AM ICT 
//


package org.opencps.dossiermgt.input.model;

public class DossierInputModel {
    protected String referenceUid;
    protected String serviceCode;
    protected String govAgencyCode;
    protected String dossierTemplateNo;
    protected String applicantName;
    protected String applicantIdType;
    protected String applicantIdNo;
    protected String applicantIdDate;
    protected String address;
    protected String cityCode;
    protected String districtCode;
    protected String wardCode;
    protected String contactName;
    protected String contactTelNo;
    protected String contactEmail;
    protected String password;
    protected String online;
    protected String notification;
    protected String applicantNote;
	protected Integer viaPostal;
	protected String postalAddress;
	protected String postalCityCode;
	protected String postalTelNo;
	protected String originality;
	protected String dossierNo;
	protected String submitDate;
	protected String receiveDate;
	protected String dueDate;
	protected String dossierStatus;
	protected String dossierStatusText;
	protected String dossierSubStatus;
	protected String dossierSubStatusText;
	private boolean isSameAsApplicant;
	private String delegateName;
	private String delegateIdNo;
	private String delegateTelNo;
	private String delegateEmail;
	private String delegateAddress;
	private String delegateCityCode;
	private String delegateCityName;
	private String delegateDistrictCode;
	private String delegateDistrictName;
	private String delegateWardCode;
	private String delegateWardName;
	private Long sampleCount;
	private String briefNote;
	private String serviceName;
	private String dossierName;
	private String postalCityName;
	private String postalServiceCode;
	private String postalServiceName;
	private String postalDistrictCode;
	private String postalDistrictName;
	private String postalWardCode;
	private String postalWardName;
	private String originDossierNo;
	private String dossierMarkArr;
	private String dossierFileArr;
	private String dossiers;
	private String payment;
	private String serverNo;
	private String metaData;
	private Integer delegateType;
	private int systemId;
	private String dossierCounter;
	private Integer vnpostalStatus;
	private String vnpostalProfile;
	private Integer fromViaPostal;
	private String formMeta;

	public String getFormMeta() {
		return formMeta;
	}

	public void setFormMeta(String formMeta) {
		this.formMeta = formMeta;
	}

	public Integer getFromViaPostal() {
		return fromViaPostal;
	}

	public void setFromViaPostal(Integer fromViaPostal) {
		this.fromViaPostal = fromViaPostal;
	}

	public Integer getVnpostalStatus() {
		return vnpostalStatus;
	}

	public void setVnpostalStatus(Integer vnpostalStatus) {
		this.vnpostalStatus = vnpostalStatus;
	}

	public String getVnpostalProfile() {
		return vnpostalProfile;
	}

	public void setVnpostalProfile(String vnpostalProfile) {
		this.vnpostalProfile = vnpostalProfile;
	}

	public String getDossierCounter() {
		return dossierCounter;
	}

	public void setDossierCounter(String dossierCounter) {
		this.dossierCounter = dossierCounter;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public Integer getDelegateType() {
		return delegateType;
	}

	public void setDelegateType(Integer delegateType) {
		this.delegateType = delegateType;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public Long getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Long documentDate) {
		this.documentDate = documentDate;
	}

	private String documentNo;
	private Long documentDate;
	
	public Integer getViaPostal() {
		return viaPostal;
	}

	public void setViaPostal(Integer viaPostal) {
		this.viaPostal = viaPostal;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPostalCityCode() {
		return postalCityCode;
	}

	public void setPostalCityCode(String postalCityCode) {
		this.postalCityCode = postalCityCode;
	}

	public String getPostalTelNo() {
		return postalTelNo;
	}

	public void setPostalTelNo(String postalTelNo) {
		this.postalTelNo = postalTelNo;
	}

	public String getApplicantNote() {
		return applicantNote;
	}

	public void setApplicantNote(String applicantNote) {
		this.applicantNote = applicantNote;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	/**
     * Gets the value of the referenceUid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceUid() {
        return referenceUid;
    }

    /**
     * Sets the value of the referenceUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceUid(String value) {
        this.referenceUid = value;
    }

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the govAgencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovAgencyCode() {
        return govAgencyCode;
    }

    /**
     * Sets the value of the govAgencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovAgencyCode(String value) {
        this.govAgencyCode = value;
    }

    /**
     * Gets the value of the dossierTemplateNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDossierTemplateNo() {
        return dossierTemplateNo;
    }

    /**
     * Sets the value of the dossierTemplateNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDossierTemplateNo(String value) {
        this.dossierTemplateNo = value;
    }

    /**
     * Gets the value of the applicantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * Sets the value of the applicantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicantName(String value) {
        this.applicantName = value;
    }

    /**
     * Gets the value of the applicantIdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicantIdType() {
        return applicantIdType;
    }

    /**
     * Sets the value of the applicantIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicantIdType(String value) {
        this.applicantIdType = value;
    }

    /**
     * Gets the value of the applicantIdNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicantIdNo() {
        return applicantIdNo;
    }

    /**
     * Sets the value of the applicantIdNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicantIdNo(String value) {
        this.applicantIdNo = value;
    }

    /**
     * Gets the value of the applicantIdDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicantIdDate() {
        return applicantIdDate;
    }

    /**
     * Sets the value of the applicantIdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicantIdDate(String value) {
        this.applicantIdDate = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the cityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Sets the value of the cityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityCode(String value) {
        this.cityCode = value;
    }

    /**
     * Gets the value of the districtCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * Sets the value of the districtCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictCode(String value) {
        this.districtCode = value;
    }

    /**
     * Gets the value of the wardCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWardCode() {
        return wardCode;
    }

    /**
     * Sets the value of the wardCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWardCode(String value) {
        this.wardCode = value;
    }

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the contactTelNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactTelNo() {
        return contactTelNo;
    }

    /**
     * Sets the value of the contactTelNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactTelNo(String value) {
        this.contactTelNo = value;
    }

    /**
     * Gets the value of the contactEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the value of the contactEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactEmail(String value) {
        this.contactEmail = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the online property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnline() {
        return online;
    }

    /**
     * Sets the value of the online property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnline(String value) {
        this.online = value;
    }

    /**
     * Gets the value of the originality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginality() {
        return originality;
    }

    /**
     * Sets the value of the originality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginality(String value) {
        this.originality = value;
    } 

    /**
     * Gets the value of the dossierNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDossierNo() {
        return dossierNo;
    }

    /**
     * Sets the value of the dossierNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDossierNo(String value) {
        this.dossierNo = value;
    } 

    /**
     * Gets the value of the submitDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitDate() {
        return submitDate;
    }

    /**
     * Sets the value of the submitDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitDate(String value) {
        this.submitDate = value;
    } 

    /**
     * Gets the value of the receiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveDate() {
        return receiveDate;
    }

    /**
     * Sets the value of the receiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveDate(String value) {
        this.receiveDate = value;
    } 

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDueDate(String value) {
        this.dueDate = value;
    }

	public String getDossierStatus() {
		return dossierStatus;
	}

	public void setDossierStatus(String dossierStatus) {
		this.dossierStatus = dossierStatus;
	}

	public String getDossierStatusText() {
		return dossierStatusText;
	}

	public void setDossierStatusText(String dossierStatusText) {
		this.dossierStatusText = dossierStatusText;
	}

	public String getDossierSubStatus() {
		return dossierSubStatus;
	}

	public void setDossierSubStatus(String dossierSubStatus) {
		this.dossierSubStatus = dossierSubStatus;
	}

	public String getDossierSubStatusText() {
		return dossierSubStatusText;
	}

	public void setDossierSubStatusText(String dossierSubStatusText) {
		this.dossierSubStatusText = dossierSubStatusText;
	}

	public boolean isSameAsApplicant() {
		return isSameAsApplicant;
	}

	public void setSameAsApplicant(boolean isSameAsApplicant) {
		this.isSameAsApplicant = isSameAsApplicant;
	}

	public String getDelegateName() {
		return delegateName;
	}

	public void setDelegateName(String delegateName) {
		this.delegateName = delegateName;
	}

	public String getDelegateIdNo() {
		return delegateIdNo;
	}

	public void setDelegateIdNo(String delegateIdNo) {
		this.delegateIdNo = delegateIdNo;
	}

	public String getDelegateTelNo() {
		return delegateTelNo;
	}

	public void setDelegateTelNo(String delegateTelNo) {
		this.delegateTelNo = delegateTelNo;
	}

	public String getDelegateEmail() {
		return delegateEmail;
	}

	public void setDelegateEmail(String delegateEmail) {
		this.delegateEmail = delegateEmail;
	}

	public String getDelegateAddress() {
		return delegateAddress;
	}

	public void setDelegateAddress(String delegateAddress) {
		this.delegateAddress = delegateAddress;
	}

	public String getDelegateCityCode() {
		return delegateCityCode;
	}

	public void setDelegateCityCode(String delegateCityCode) {
		this.delegateCityCode = delegateCityCode;
	}

	public String getDelegateCityName() {
		return delegateCityName;
	}

	public void setDelegateCityName(String delegateCityName) {
		this.delegateCityName = delegateCityName;
	}
	
	public String getDelegateDistrictCode() {
		return delegateDistrictCode;
	}

	public void setDelegateDistrictCode(String delegateDistrictCode) {
		this.delegateDistrictCode = delegateDistrictCode;
	}

	public String getDelegateDistrictName() {
		return delegateDistrictName;
	}

	public void setDelegateDistrictName(String delegateDistrictName) {
		this.delegateDistrictName = delegateDistrictName;
	}
	
	public String getDelegateWardCode() {
		return delegateWardCode;
	}

	public void setDelegateWardCode(String delegateWardCode) {
		this.delegateWardCode = delegateWardCode;
	}

	public String getDelegateWardName() {
		return delegateWardName;
	}

	public void setDelegateWardName(String delegateWardName) {
		this.delegateWardName = delegateWardName;
	}
	
	public Long getSampleCount() {
		return sampleCount;
	}

	public void setSampleCount(Long sampleCount) {
		this.sampleCount = sampleCount;
	} 

	public String getBriefNote() {
		return briefNote;
	}
	
	public void setBriefNote(String value) {
		this.briefNote = value;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDossierName() {
		return dossierName;
	}

	public void setDossierName(String dossierName) {
		this.dossierName = dossierName;
	}

	public String getPostalCityName() {
		return postalCityName;
	}
	
	public void setPostalCityName(String value) {
		this.postalCityName = value;
	}

	public String getPostalServiceCode() {
		return postalServiceCode;
	}
	
	public void setPostalServiceCode(String value) {
		this.postalServiceCode = value;
	}

	public String getPostalServiceName() {
		return postalServiceName;
	}
	
	public void setPostalServiceName(String value) {
		this.postalServiceName = value;
	}

	public String getPostalDistrictCode() {
		return postalDistrictCode;
	}
	
	public void setPostalDistrictCode(String value) {
		this.postalDistrictCode = value;
	}

	public String getPostalDistrictName() {
		return postalDistrictName;
	}
	
	public void setPostalDistrictName(String value) {
		this.postalDistrictName = value;
	}

	public String getPostalWardCode() {
		return postalWardCode;
	}
	
	public void setPostalWardCode(String value) {
		this.postalWardCode = value;
	}

	public String getPostalWardName() {
		return postalWardName;
	}
	
	public void setPostalWardName(String value) {
		this.postalWardName = value;
	}

	public String getOriginDossierNo() {
		return originDossierNo;
	}
	
	public void setOriginDossierNo(String value) {
		this.originDossierNo = value;
	}

	public String getDossierMarkArr() {
		return dossierMarkArr;
	}

	public void setDossierMarkArr(String dossierMarkArr) {
		this.dossierMarkArr = dossierMarkArr;
	}

	public String getDossierFileArr() {
		return dossierFileArr;
	}

	public void setDossierFileArr(String dossierFileArr) {
		this.dossierFileArr = dossierFileArr;
	}

	public String getDossiers() {
		return dossiers;
	}

	public void setDossiers(String dossiers) {
		this.dossiers = dossiers;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getServerNo() {
		return serverNo;
	}

	public void setServerNo(String serverNo) {
		this.serverNo = serverNo;
	}
	
	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

}
