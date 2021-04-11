package org.opencps.api.einvoice.vnpt.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = {"key", "Invoice"})

@XmlRootElement(name = "Inv")
public class InvModel {
	
	protected String key;	
	protected InvoiceModel Invoice;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public InvoiceModel getInvoice() {
		return Invoice;
	}

	public void setInvoice(InvoiceModel invoice) {
		Invoice = invoice;
	}
	
	
}
