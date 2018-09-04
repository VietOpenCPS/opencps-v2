package org.opencps.statistic.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GovAgencyResponse {
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<GovAgencyData> getData() {
		return data;
	}
	public void setData(List<GovAgencyData> data) {
		this.data = data;
	}
	private int total;

	private List<GovAgencyData> data;
}
