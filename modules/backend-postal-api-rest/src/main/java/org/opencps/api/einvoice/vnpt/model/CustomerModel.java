package org.opencps.api.einvoice.vnpt.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = {"Name", "Code", "TaxCode", "Address", "BankAccountName", 
		"BankName", "BankNumber", "Email", "Fax", "Phone", "ContectPerson", "RepresentPerson",
		"CusType"})

@XmlRootElement(name = "Customer")
public class CustomerModel {

	@FormParam(value = "Name")
	protected String name;
	@FormParam(value = "Code")
	protected String code;
	@FormParam(value = "TaxCode")
	protected String taxCode;
	@FormParam(value = "Address")
	protected String address;
	@FormParam(value = "BankAccountName")
	protected String bankAccountName;
	@FormParam(value = "BankName")
	protected String bankName;
	@FormParam(value = "BankNumber")
	protected String bankNumber;
	@FormParam(value = "Email")
	protected String email;
	@FormParam(value = "Fax")
	protected String fax;
	@FormParam(value = "Phone")
	protected String phone;
	@FormParam(value = "ContectPerson")
	protected String contectPerson;
	@FormParam(value = "RepresentPerson")
	protected String representPerson;
	@FormParam(value = "CusType")
	protected int cusType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContectPerson() {
		return contectPerson;
	}
	public void setContectPerson(String contectPerson) {
		this.contectPerson = contectPerson;
	}
	public String getRepresentPerson() {
		return representPerson;
	}
	public void setRepresentPerson(String representPerson) {
		this.representPerson = representPerson;
	}
	public int getCusType() {
		return cusType;
	}
	public void setCusType(int cusType) {
		this.cusType = cusType;
	}
	
}
