package org.opencps.api.dossiervnpost.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "fullName", "phoneNumber", "deliverAddress", "cityCode", "cityName", "districtCode",
		"districtName", "wardCode", "wardName","dossierId" })
@XmlRootElement(name = "DossierVnpostModel")
public class DossierVnpostModel {
	@FormParam(value = "fullName")
	protected String fullName;
	@FormParam(value = "phoneNumber")
	protected String phoneNumber;
	@FormParam(value = "deliverAddress")
	protected String deliverAddress;
	@FormParam(value = "cityCode")
	protected String cityCode;
	@FormParam(value = "cityName")
	protected String cityName;
	@FormParam(value = "districtCode")
	protected String districtCode;
	@FormParam(value = "districtName")
	protected String districtName;
	@FormParam(value = "wardCode")
	protected String wardCode;
	@FormParam(value = "wardName")
	protected String wardName;
	@FormParam(value = "dossierId")
	protected long dossierId;

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the deliverAddress
	 */
	public String getDeliverAddress() {
		return deliverAddress;
	}

	/**
	 * @param deliverAddress
	 *            the deliverAddress to set
	 */
	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode
	 *            the districtCode to set
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName
	 *            the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * @return the wardCode
	 */
	public String getWardCode() {
		return wardCode;
	}

	/**
	 * @param wardCode
	 *            the wardCode to set
	 */
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	/**
	 * @return the wardName
	 */
	public String getWardName() {
		return wardName;
	}

	/**
	 * @param wardName
	 *            the wardName to set
	 */
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	/**
	 * @return the dossierId
	 */
	public long getDossierId() {
		return dossierId;
	}

	/**
	 * @param dossierId the dossierId to set
	 */
	public void setDossierId(long dossierId) {
		this.dossierId = dossierId;
	}

}
