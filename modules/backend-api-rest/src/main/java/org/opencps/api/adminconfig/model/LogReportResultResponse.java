package org.opencps.api.adminconfig.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.opencps.dossiermgt.action.util.ConstantUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	    "total",
	    "data"
	})
@XmlRootElement(name = "data")
public class LogReportResultResponse {
	
	private int total;	
	private List<LogReportStatisticData> data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<LogReportStatisticData> getData() {
		if (data == null) {
			data = new ArrayList<LogReportStatisticData>();
		}
		return this.data;
	}

}
