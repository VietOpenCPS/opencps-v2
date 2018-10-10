package org.opencps.statistic.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDomainResponse {
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<ServiceDomainData> getData() {
		return data;
	}
	public void setData(List<ServiceDomainData> data) {
		this.data = data;
	}
	private int total;

	private List<ServiceDomainData> data;
}
