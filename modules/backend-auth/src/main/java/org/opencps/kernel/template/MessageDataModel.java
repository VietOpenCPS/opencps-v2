
package org.opencps.kernel.template;

import java.util.Date;

/**
 * @author trungnt
 */
/**
 * @author trungnt
 */
/**
 * @author trungnt
 *
 */
/**
 * @author trungnt
 */
public class MessageDataModel {

	private long groupId;
	private long userId;
	private Date createDate;
	private Date modifiedDate;
	private String notificationType;
	private String className;
	private String classPK;
	// private String payload;
	private String fromUsername;
	private String toUsername;
	private long toUserId;
	private String toEmail;
	private String toTelNo;
	private Date publicationDate;
	private Date expireDate;
	private boolean sendSMS;
	private boolean sendEmail;
	private Object payload;
	private String baseUrl;
	private String guestBaseUrl;
	private String security;
	private String token;
	private String subActiveUrl;

	public long getGroupId() {

		return groupId;
	}

	public long getUserId() {

		return userId;
	}

	public Date getCreateDate() {

		return createDate;
	}

	public Date getModifiedDate() {

		return modifiedDate;
	}

	public String getNotificationType() {

		return notificationType;
	}

	public String getClassName() {

		return className;
	}

	public String getClassPK() {

		return classPK;
	}

	public String getFromUsername() {

		return fromUsername;
	}

	public String getToUsername() {

		return toUsername;
	}

	public long getToUserId() {

		return toUserId;
	}

	public String getToEmail() {

		return toEmail;
	}

	public String getToTelNo() {

		return toTelNo;
	}

	public Date getPublicationDate() {

		return publicationDate;
	}

	public Date getExpireDate() {

		return expireDate;
	}

	public boolean isSendSMS() {

		return sendSMS;
	}

	public boolean isSendEmail() {

		return sendEmail;
	}

	public Object getPayload() {

		return payload;
	}

	public void setGroupId(long groupId) {

		this.groupId = groupId;
	}

	public void setUserId(long userId) {

		this.userId = userId;
	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;
	}

	public void setModifiedDate(Date modifiedDate) {

		this.modifiedDate = modifiedDate;
	}

	public void setNotificationType(String notificationType) {

		this.notificationType = notificationType;
	}

	public void setClassName(String className) {

		this.className = className;
	}

	public void setClassPK(String classPK) {

		this.classPK = classPK;
	}

	public void setFromUsername(String fromUsername) {

		this.fromUsername = fromUsername;
	}

	public void setToUsername(String toUsername) {

		this.toUsername = toUsername;
	}

	public void setToUserId(long toUserId) {

		this.toUserId = toUserId;
	}

	public void setToEmail(String toEmail) {

		this.toEmail = toEmail;
	}

	public void setToTelNo(String toTelNo) {

		this.toTelNo = toTelNo;
	}

	public void setPublicationDate(Date publicationDate) {

		this.publicationDate = publicationDate;
	}

	public void setExpireDate(Date expireDate) {

		this.expireDate = expireDate;
	}

	public void setSendSMS(boolean sendSMS) {

		this.sendSMS = sendSMS;
	}

	public void setSendEmail(boolean sendEmail) {

		this.sendEmail = sendEmail;
	}

	public void setPayload(Object data) {

		this.payload = data;
	}

	public String getBaseUrl() {

		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {

		this.baseUrl = baseUrl;
	}

	public String getGuestBaseUrl() {

		return guestBaseUrl;
	}

	public void setGuestBaseUrl(String guestBaseUrl) {

		this.guestBaseUrl = guestBaseUrl;
	}

	public String getSecurity() {

		return security;
	}

	public void setSecurity(String security) {

		this.security = security;
	}

	public String getToken() {

		return token;
	}

	public void setToken(String token) {

		this.token = token;
	}

	public String getSubActiveUrl() {
		return subActiveUrl;
	}

	public void setSubActiveUrl(String subActiveUrl) {
		this.subActiveUrl = subActiveUrl;
	}

}
