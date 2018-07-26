/*
 * Copyright © GM Global Technology Operations LLC. All rights reserved.
 * This information is confidential and proprietary to GM Global Technology
 * Operations LLC and may not be used, modified, copied or distributed.
 */
package com.gm.gcc.cei.ole.support.dealership.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class DealershipResponse {

	private String bacCode;
	private String dealerCode;
	private String name;
	private String dealerType;
	private String dealerStreet1;
	private String dealerStreet2;
	private String dealerState;
	private String dealerCity;
	private String dealerZip;
	private String dealerPhone;
	
	private Double latitude;
	private Double longitude;
	public String getBacCode() {
		return bacCode;
	}
	public void setBacCode(String bacCode) {
		this.bacCode = bacCode;
	}
	public String getDealerCode() {
		return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDealerType() {
		return dealerType;
	}
	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}
	public String getDealerStreet1() {
		return dealerStreet1;
	}
	public void setDealerStreet1(String dealerStreet1) {
		this.dealerStreet1 = dealerStreet1;
	}
	public String getDealerStreet2() {
		return dealerStreet2;
	}
	public void setDealerStreet2(String dealerStreet2) {
		this.dealerStreet2 = dealerStreet2;
	}
	public String getDealerState() {
		return dealerState;
	}
	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}
	public String getDealerCity() {
		return dealerCity;
	}
	public void setDealerCity(String dealerCity) {
		this.dealerCity = dealerCity;
	}
	public String getDealerZip() {
		return dealerZip;
	}
	public void setDealerZip(String dealerZip) {
		this.dealerZip = dealerZip;
	}
	public String getDealerPhone() {
		return dealerPhone;
	}
	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
