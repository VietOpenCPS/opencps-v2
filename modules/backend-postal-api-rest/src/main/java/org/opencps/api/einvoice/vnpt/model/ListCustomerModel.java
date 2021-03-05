package org.opencps.api.einvoice.vnpt.model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "Customers")
@XmlAccessorType (XmlAccessType.FIELD)
public class ListCustomerModel {

	@XmlElement(name= "Customer")
	protected List<CustomerModel> listCustomerModels;

	public List<CustomerModel> getListCustomerModels() {
		return listCustomerModels;
	}

	public void setListCustomerModels(List<CustomerModel> listCustomerModels) {
		this.listCustomerModels = listCustomerModels;
	}
	
	
}
