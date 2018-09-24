package org.opencps.api.vnpost.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VNPostGetOrderModel", propOrder = { "pageSize", "lastId" })
public class VNPostGetOrderModel {
	@FormParam(value = "pageSize")
	private Integer pageSize; // số bản ghi cần lấy (tương đương số lượng bản ghi lớn nhất sẽ trả về). Giá trị pagesize lớn nhất là 1000.
	
	@FormParam(value = "lastId")
	private String lastId; // id bản ghi mới nhất đã lấy được từ lần gọi trước đó. Lưu ý: trong lần gọi đầu tiên, đặt lastId = “000000000000000000000000”
	
	@FormParam(value = "orderNumber")
	private String orderNumber;
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
	
}
