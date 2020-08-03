package org.opencps.statistic.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opencps.dossiermgt.action.util.ConstantUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = ConstantUtils.DATA)
public class DossierStatisticManualResponse {
	private int total;
	private String agency;
	@XmlElement(name = ConstantUtils.DATA)
	private List<DossierStatisticManualData> dossierStatisticData;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DossierStatisticManualData> getDossierStatisticManualData() {
		return dossierStatisticData;
	}

	public void setDossierStatisticData(List<DossierStatisticManualData> dossierStatisticData) {
		this.dossierStatisticData = dossierStatisticData;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

}
