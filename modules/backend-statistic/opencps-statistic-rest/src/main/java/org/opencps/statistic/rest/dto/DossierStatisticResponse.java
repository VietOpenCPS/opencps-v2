package org.opencps.statistic.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DossierStatisticResponse {
	private int total;
	@JsonProperty("data")
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
