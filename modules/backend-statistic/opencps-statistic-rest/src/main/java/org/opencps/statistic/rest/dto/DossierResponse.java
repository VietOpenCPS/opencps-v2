package org.opencps.statistic.rest.dto;

import java.util.List;

import org.opencps.statistic.model.OpencpsDossier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierResponse {
	private int total;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<OpencpsDossier> getData() {
		return data;
	}
	public void setData(List<OpencpsDossier> data) {
		this.data = data;
	}
	private List<OpencpsDossier> data;
}
