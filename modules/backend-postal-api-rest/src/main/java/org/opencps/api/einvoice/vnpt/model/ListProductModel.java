package org.opencps.api.einvoice.vnpt.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType (XmlAccessType.FIELD)

@XmlRootElement(name = "Products")
public class ListProductModel {

	@XmlElement(name = "Product")
	protected List<ProductModel> listProductModels;

	public List<ProductModel> getListProductModels() {
		return listProductModels;
	}

	public void setListProductModels(List<ProductModel> listProductModels) {
		this.listProductModels = listProductModels;
	}
	
}
