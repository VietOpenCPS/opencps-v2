package org.opencps.statistic.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DossierStatisticResponse")
public class DossierStatisticResponse {
	private int total;
	@XmlElement(name = "data")
	private DossierStatisticData dossierStatisticData;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DossierStatisticData getDossierStatisticData() {
		return dossierStatisticData;
	}

	public void setDossierStatisticData(DossierStatisticData dossierStatisticData) {
		this.dossierStatisticData = dossierStatisticData;
	}

}
