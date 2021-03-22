package org.opencps.api.einvoice.vnpt.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = {"Name", "Code", "TaxCode", "Address", "BankAccountName", 
		"BankName", "BankNumber", "Email", "Fax", "Phone", "ContactPerson", "RepresentPerson",
		"CusType"})

@XmlRootElement(name = "Customer")
public class CustomerModel {

	@FormParam(value = "Name")
	protected String Name;
	@FormParam(value = "Code")
	protected String Code;
	@FormParam(value = "TaxCode")
	protected String TaxCode;
	@FormParam(value = "Address")
	protected String Address;
	@FormParam(value = "BankAccountName")
	protected String BankAccountName;
	@FormParam(value = "BankName")
	protected String BankName;
	@FormParam(value = "BankNumber")
	protected String BankNumber;
	@FormParam(value = "Email")
	protected String Email;
	@FormParam(value = "Fax")
	protected String Fax;
	@FormParam(value = "Phone")
	protected String Phone;
	@FormParam(value = "ContactPerson")
	protected String ContactPerson;
	@FormParam(value = "RepresentPerson")
	protected String RepresentPerson;
	@FormParam(value = "CusType")
	protected int CusType;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getTaxCode() {
		return TaxCode;
	}
	public void setTaxCode(String taxCode) {
		TaxCode = taxCode;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getBankAccountName() {
		return BankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		BankAccountName = bankAccountName;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getBankNumber() {
		return BankNumber;
	}
	public void setBankNumber(String bankNumber) {
		BankNumber = bankNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	public String getRepresentPerson() {
		return RepresentPerson;
	}
	public void setRepresentPerson(String representPerson) {
		RepresentPerson = representPerson;
	}
	public int getCusType() {
		return CusType;
	}
	public void setCusType(int cusType) {
		CusType = cusType;
	}
	
	
}
