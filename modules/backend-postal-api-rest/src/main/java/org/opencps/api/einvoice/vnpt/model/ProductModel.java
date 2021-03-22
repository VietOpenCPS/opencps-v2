package org.opencps.api.einvoice.vnpt.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.ws.rs.DefaultValue;
import javax.xml.bind.annotation.XmlAccessType;


@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(name="", propOrder = {"ProdName", "ProdUnit", "ProdQuantity",
		"ProdPrice", "Amount"})

@XmlRootElement(name = "Product")
public class ProductModel {
	
	protected String ProdName;	
	protected String ProdUnit;
	
	@DefaultValue("1")
	protected int ProdQuantity;
	
	@DefaultValue("1")
	protected int ProdPrice;
	
	protected int Amount;
	public String getProdName() {
		return ProdName;
	}
	public void setProdName(String prodName) {
		ProdName = prodName;
	}
	public String getProdUnit() {
		return ProdUnit;
	}
	public void setProdUnit(String prodUnit) {
		ProdUnit = prodUnit;
	}
	public int getProdQuantity() {
		return ProdQuantity;
	}
	public void setProdQuantity(int prodQuantity) {
		ProdQuantity = prodQuantity;
	}
	public int getProdPrice() {
		return ProdPrice;
	}
	public void setProdPrice(int prodPrice) {
		ProdPrice = prodPrice;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	

}
