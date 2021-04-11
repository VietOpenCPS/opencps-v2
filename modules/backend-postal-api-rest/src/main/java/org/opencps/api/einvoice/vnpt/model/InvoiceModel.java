package org.opencps.api.einvoice.vnpt.model;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = {"CusCode", "Buyer", "CusName", "CusAddress", "CusPhone", 
		"CusTaxCode", "PaymentMethod", "KindOfService", "Extra", "Products", "DiscountAmount", 
		"Total", "VATRate", "VATAmount", "Amount", "AmountInWords","ArisingDate", "Email"})

@XmlRootElement(name = "Invoice")
public class InvoiceModel {
	
	protected String CusCode;	
	protected String Buyer;	
	protected String CusName;
	protected String CusAddress;
	protected String CusPhone;	
	protected String CusTaxCode;	
	protected String PaymentMethod;	
	protected String KindOfService;
	
	protected String Extra;
	protected ListProductModel Products; 
	
	@DefaultValue("")
	protected int DiscountAmount;
	
	protected int Total;
	
	@DefaultValue("")
	protected int VATRate;
	
	@DefaultValue("")
	protected int VATAmount;
	
	protected int Amount;
	protected String AmountInWords;
	protected String ArisingDate;
	protected String Email;
	public String getCusCode() {
		return CusCode;
	}
	public void setCusCode(String cusCode) {
		CusCode = cusCode;
	}
	public String getBuyer() {
		return Buyer;
	}
	public void setBuyer(String buyer) {
		Buyer = buyer;
	}
	public String getCusName() {
		return CusName;
	}
	public void setCusName(String cusName) {
		CusName = cusName;
	}
	public String getCusAddress() {
		return CusAddress;
	}
	public void setCusAddress(String cusAddress) {
		CusAddress = cusAddress;
	}
	public String getCusPhone() {
		return CusPhone;
	}
	public void setCusPhone(String cusPhone) {
		CusPhone = cusPhone;
	}
	public String getCusTaxCode() {
		return CusTaxCode;
	}
	public void setCusTaxCode(String cusTaxCode) {
		CusTaxCode = cusTaxCode;
	}
	public String getPaymentMethod() {
		return PaymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		PaymentMethod = paymentMethod;
	}
	public String getKindOfService() {
		return KindOfService;
	}
	public void setKindOfService(String kindOfService) {
		KindOfService = kindOfService;
	}
	public String getExtra() {
		return Extra;
	}
	public void setExtra(String extra) {
		Extra = extra;
	}
	
	public ListProductModel getProducts() {
		return Products;
	}
	public void setProducts(ListProductModel products) {
		Products = products;
	}
	public int getDiscountAmount() {
		return DiscountAmount;
	}
	public void setDiscountAmount(int discountAmount) {
		DiscountAmount = discountAmount;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}	
	public int getVATRate() {
		return VATRate;
	}
	public void setVATRate(int vATRate) {
		VATRate = vATRate;
	}
	public int getVATAmount() {
		return VATAmount;
	}
	public void setVATAmount(int vATAmount) {
		VATAmount = vATAmount;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public String getAmountInWords() {
		return AmountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		AmountInWords = amountInWords;
	}	
	public String getArisingDate() {
		return ArisingDate;
	}
	public void setArisingDate(String arisingDate) {
		ArisingDate = arisingDate;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

	
}
