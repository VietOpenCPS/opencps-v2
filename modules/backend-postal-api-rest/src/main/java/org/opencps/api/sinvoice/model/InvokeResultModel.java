package org.opencps.api.sinvoice.model;

import com.liferay.portal.kernel.json.JSONArray;

/**
 * @author trungnt
 *
 */
public class InvokeResultModel {
	private int status;
	private String errorCode;
	private String description;

	private String supplierTaxCode;
	private String invoiceNo;
	private String transactionID;
	private String reservationCode;
	private String fileName;
	private String fileToBytes;
	private JSONArray invoices;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupplierTaxCode() {
		return supplierTaxCode;
	}

	public void setSupplierTaxCode(String supplierTaxCode) {
		this.supplierTaxCode = supplierTaxCode;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileToBytes() {
		return fileToBytes;
	}

	public void setFileToBytes(String fileToBytes) {
		this.fileToBytes = fileToBytes;
	}

	public JSONArray getInvoices() {
		return invoices;
	}

	public void setInvoices(JSONArray invoices) {
		this.invoices = invoices;
	}

}
