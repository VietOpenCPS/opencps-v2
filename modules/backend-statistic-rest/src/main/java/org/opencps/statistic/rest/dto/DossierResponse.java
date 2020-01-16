package org.opencps.statistic.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opencps.dossiermgt.action.util.ConstantUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = ConstantUtils.DATA)
public class DossierResponse {
	private int total;
	
	@XmlElement(name = ConstantUtils.DATA)
	private List<DossierData> data;

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<DossierData> getData() {
		return data;
	}
	public void setData(List<DossierData> data) {
		this.data = data;
	}
	
}
