package org.opencps.api.sinvoice.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author trungnt
 *
 */
@XmlRootElement(name = "SearchInputModel")
public class SearchInputModel {
	@ApiModelProperty(example = "AA/16E0000001", required = true, value = "")
	private String invoiceNo = null;
	@ApiModelProperty(example = "20190420153000", required = true, value = "")
	private String startDate = null;
	@ApiModelProperty(example = "20190420153000", required = true, value = "")
	private String endDate = null;
	@ApiModelProperty(example = "01GTKT", required = false, value = "")
	private String invoiceType = null;
	@ApiModelProperty(example = "10", required = true, value = "")
	private Integer rowPerPage = null;
	@ApiModelProperty(example = "1", required = true, value = "")
	private Integer pageNum = null;
	@ApiModelProperty(example = "1000", required = true, value = "")
	private Long contractNo = null;
	@ApiModelProperty(example = "2041", required = false, value = "")
	private Long contractId = null;
	@ApiModelProperty(example = "204155", required = false, value = "")
	private Long customerId = null;
	@ApiModelProperty(example = "20414564434", required = false, value = "")
	private Long buyerTaxCode = null;
	@ApiModelProperty(example = "78GSH", required = false, value = "")
	private String buyerIdNo = null;
	@ApiModelProperty(example = "18HGD", required = false, value = "")
	private String templateCode = null;
	@ApiModelProperty(example = "JDG897", required = false, value = "")
	private String invoiceSeri = null;
	@ApiModelProperty(example = "false", required = false, value = "")
	private Boolean getAll = null;

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Integer getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(Integer rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Long getContractNo() {
		return contractNo;
	}

	public void setContractNo(Long contractNo) {
		this.contractNo = contractNo;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getBuyerTaxCode() {
		return buyerTaxCode;
	}

	public void setBuyerTaxCode(Long buyerTaxCode) {
		this.buyerTaxCode = buyerTaxCode;
	}

	public String getBuyerIdNo() {
		return buyerIdNo;
	}

	public void setBuyerIdNo(String buyerIdNo) {
		this.buyerIdNo = buyerIdNo;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getInvoiceSeri() {
		return invoiceSeri;
	}

	public void setInvoiceSeri(String invoiceSeri) {
		this.invoiceSeri = invoiceSeri;
	}

	public Boolean getGetAll() {
		return getAll;
	}

	public void setGetAll(Boolean getAll) {
		this.getAll = getAll;
	}

}
