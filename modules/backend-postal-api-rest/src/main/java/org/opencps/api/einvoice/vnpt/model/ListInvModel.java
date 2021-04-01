package org.opencps.api.einvoice.vnpt.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType (XmlAccessType.FIELD)

@XmlRootElement(name = "Invoices")
public class ListInvModel {
	
	@XmlElement(name = "Inv")
	protected List<InvModel> listInvoice;

	public List<InvModel> getListInvoice() {
		return listInvoice;
	}

	public void setListInvoice(List<InvModel> listInvoice) {
		this.listInvoice = listInvoice;
	}
	
	
}
