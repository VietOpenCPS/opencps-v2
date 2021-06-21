package org.opencps.backend.statisticmgt.dto;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"month", "year", "domainCode", "govAgencyCode", "groupBy", "start", "end", "groupId"})
@XmlRootElement(name = "DossierStatisticMgtSearchModel")
public class DossierStatisticMgtSearchModel {

	@QueryParam("month")
	@DefaultValue("-1")
	protected int month;
	@QueryParam("year")
	protected int year;
	@QueryParam("domainCode")
	protected  String domainCode;
	@QueryParam("govAgencyCode") 
	protected String govAgencyCode;
	@QueryParam("groupBy") 
	protected int groupBy;
	@QueryParam("start") 
	protected int start;
	@QueryParam("end")
	protected int end;
	@QueryParam("groupId")
	protected long groupId;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	public String getGovAgencyCode() {
		return govAgencyCode;
	}
	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}
	public int getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(int groupBy) {
		this.groupBy = groupBy;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
}
