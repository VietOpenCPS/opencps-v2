package org.opencps.statistic.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
public class DossierStatisticResponse {
	private int total;
	private String agency;
	@XmlElement(name = "data")
	private List<DossierStatisticData> dossierStatisticData;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DossierStatisticData> getDossierStatisticData() {
		return dossierStatisticData;
	}

	public void setDossierStatisticData(List<DossierStatisticData> dossierStatisticData) {
		this.dossierStatisticData = dossierStatisticData;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

}
