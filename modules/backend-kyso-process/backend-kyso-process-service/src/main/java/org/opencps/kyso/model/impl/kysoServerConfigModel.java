package org.opencps.kyso.model.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "kysoServerConfigModel", propOrder = { "type_kyso", "type_dongdau", "active" })
public class kysoServerConfigModel {

	private String type_kyso;
	
	private String type_dongdau;
	
	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public kysoServerConfigModel(String type_kyso, String type_dongdau, Boolean active) {
		super();
		this.type_kyso = type_kyso;
		this.type_dongdau = type_dongdau;
		this.active = active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getType_kyso() {
		return type_kyso;
	}

	public void setType_kyso(String type_kyso) {
		this.type_kyso = type_kyso;
	}

	public String getType_dongdau() {
		return type_dongdau;
	}

	public void setType_dongdau(String type_dongdau) {
		this.type_dongdau = type_dongdau;
	}
	
	
}
