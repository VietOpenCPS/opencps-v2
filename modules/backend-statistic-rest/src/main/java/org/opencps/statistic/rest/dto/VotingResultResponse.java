package org.opencps.statistic.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "VotingResultResponse")
public class VotingResultResponse {
	private int total;
	private String agency;
	@XmlElement(name = "data")
	private List<VotingResultStatisticData> data;

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<VotingResultStatisticData> getData() {
		return data;
	}
	public void setData(List<VotingResultStatisticData> data) {
		this.data = data;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}

}
