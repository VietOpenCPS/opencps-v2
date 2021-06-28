package org.opencps.backend.statisticmgt.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
public class DossierStatisticMgtResponse {

	private int total;
	private String agency;
	@XmlElement(name = "data")
	private List<DossierStatisticMgtData> dossierStatisticMgtData;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public List<DossierStatisticMgtData> getDossierStatisticMgtData() {
		if (dossierStatisticMgtData == null) {
			dossierStatisticMgtData = new ArrayList<DossierStatisticMgtData>();
		}
		return this.dossierStatisticMgtData;
	}
	
	public void setDossierStatisticMgtData(List<DossierStatisticMgtData> dossierStatisticMgtData) {
		this.dossierStatisticMgtData = dossierStatisticMgtData;
	}
	
	
}
