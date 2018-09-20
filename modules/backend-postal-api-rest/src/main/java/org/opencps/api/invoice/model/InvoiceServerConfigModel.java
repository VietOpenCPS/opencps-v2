package org.opencps.api.invoice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VNPostServerConfigModel", propOrder = { "endPointUrl", "active" })

public class InvoiceServerConfigModel {
	private String endPointUrl;

	private Boolean active;

	public String getEndPointUrl() {
		return endPointUrl;
	}

	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public InvoiceServerConfigModel(String endPointUrl, Boolean active) {
		super();
		this.endPointUrl = endPointUrl;
		this.active = active;
	}
	
	
}
