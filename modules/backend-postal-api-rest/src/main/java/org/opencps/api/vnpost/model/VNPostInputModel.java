package org.opencps.api.vnpost.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VNPostInputModel", propOrder = { "customerCode", "orderNumber", "codAmount",
		"senderProvince", "senderDistrict", "senderAddress", "senderName", "senderEmail", "senderTel", "senderDesc",
		"description", "receiverName", "receiverAddress", "receiverTel", "receiverProvince", "receiverDistrict",
		"receiverEmail" })
public class VNPostInputModel {
	@FormParam(value = "customerCode")
	private String customerCode; // Có Mã khách hàng do VNPost cung cấp
	
	@FormParam(value = "orderNumber")
	private String orderNumber; // Có Mã hồ sơ (do đơn vị tiếp nhận hồ sơ cung cấp)
	@FormParam(value = "codAmount")
	private double codAmount; // Không Số tiền nhờ thu
	@FormParam(value = "senderProvince")
	private int senderProvince; // Có Mã tỉnh gửi (mã bưu điện cấp tỉnh do
								// VNPost cung cấp)
	@FormParam(value = "senderDistrict")
	private int senderDistrict;
	@FormParam(value = "senderAddress")
	private String senderAddress; // Có Địa chỉ người / cơ quan gửi
	@FormParam(value = "senderName")
	private String senderName; // Có Tên người / cơ quan gửi
	@FormParam(value = "senderEmail")
	private String senderEmail; // Không Email người / cơ quan gửi
	@FormParam(value = "senderTel")
	private String senderTel; // Có Số điện thoại người / cơ quan gửi
	@FormParam(value = "senderDesc")
	private String senderDesc; // Không Thành phần hồ sơ
	@FormParam(value = "description")
	private String description; // Không Ghi chú
	@FormParam(value = "receiverName")
	private String receiverName; // Có Tên người / cơ quan nhận
	@FormParam(value = "receiverAddress")
	private String receiverAddress; // Có Địa chỉ người / cơ quan nhận
	@FormParam(value = "receiverTel")
	private String receiverTel; // Có Điện thoại người / cơ quan nhận
	@FormParam(value = "receiverProvince")
	private int receiverProvince; // Có Mã tỉnh nhận (mã bưu điện cấp tỉnh do
	// VNPost cung cấp)
	@FormParam(value = "receiverDistrict")
	private int receiverDistrict; // Không Mã huyện nhận (mã bưu điện cấp huyện
									// do VNPost cung cấp)
	@FormParam(value = "receiverEmail")
	private String receiverEmail; // Không Email người / cơ quan nhận

	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * @param customerCode
	 *            the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the codAmount
	 */
	public double getCodAmount() {
		return codAmount;
	}

	/**
	 * @param codAmount
	 *            the codAmount to set
	 */
	public void setCodAmount(double codAmount) {
		this.codAmount = codAmount;
	}

	/**
	 * @return the senderProvince
	 */
	public int getSenderProvince() {
		return senderProvince;
	}

	/**
	 * @param senderProvince
	 *            the senderProvince to set
	 */
	public void setSenderProvince(int senderProvince) {
		this.senderProvince = senderProvince;
	}

	/**
	 * @return the senderDistrict
	 */
	public int getSenderDistrict() {
		return senderDistrict;
	}

	/**
	 * @param senderDistrict
	 *            the senderDistrict to set
	 */
	public void setSenderDistrict(int senderDistrict) {
		this.senderDistrict = senderDistrict;
	}

	/**
	 * @return the senderAddress
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * @param senderAddress
	 *            the senderAddress to set
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName
	 *            the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the senderEmail
	 */
	public String getSenderEmail() {
		return senderEmail;
	}

	/**
	 * @param senderEmail
	 *            the senderEmail to set
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	/**
	 * @return the senderTel
	 */
	public String getSenderTel() {
		return senderTel;
	}

	/**
	 * @param senderTel
	 *            the senderTel to set
	 */
	public void setSenderTel(String senderTel) {
		this.senderTel = senderTel;
	}

	/**
	 * @return the senderDesc
	 */
	public String getSenderDesc() {
		return senderDesc;
	}

	/**
	 * @param senderDesc
	 *            the senderDesc to set
	 */
	public void setSenderDesc(String senderDesc) {
		this.senderDesc = senderDesc;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * @param receiverName
	 *            the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * @return the receiverAddress
	 */
	public String getReceiverAddress() {
		return receiverAddress;
	}

	/**
	 * @param receiverAddress
	 *            the receiverAddress to set
	 */
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	/**
	 * @return the receiverTel
	 */
	public String getReceiverTel() {
		return receiverTel;
	}

	/**
	 * @param receiverTel
	 *            the receiverTel to set
	 */
	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	/**
	 * @return the receiverProvince
	 */
	public int getReceiverProvince() {
		return receiverProvince;
	}

	/**
	 * @param receiverProvince
	 *            the receiverProvince to set
	 */
	public void setReceiverProvince(int receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	/**
	 * @return the receiverDistrict
	 */
	public int getReceiverDistrict() {
		return receiverDistrict;
	}

	/**
	 * @param receiverDistrict
	 *            the receiverDistrict to set
	 */
	public void setReceiverDistrict(int receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	/**
	 * @return the receiverEmail
	 */
	public String getReceiverEmail() {
		return receiverEmail;
	}

	/**
	 * @param receiverEmail
	 *            the receiverEmail to set
	 */
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

}
