package org.opencps.statistic.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetPersonResultResponse")
public class GetPersonResponse {
	private int total;
	
	@XmlElement(name = "data")
	private List<GetPersonData> data;

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<GetPersonData> getData() {
		return data;
	}
	public void setData(List<GetPersonData> data) {
		this.data = data;
	}
	
}
