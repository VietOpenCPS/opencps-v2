
package org.opencps.kernel.message;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.opencps.kernel.prop.PropValues;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
public class MBMessageEntry {

	private static Log _log = LogFactoryUtil.getLog(MBMessageEntry.class);

	public MBMessageEntry(ServiceContext serviceContext) {
		this.setUserId(serviceContext.getUserId());
		this.setGroupId(serviceContext.getScopeGroupId());
		this.setCompanyId(serviceContext.getCompanyId());
	}

	public MBMessageEntry(
		String fromName, long toUserId, String toEmail, String toName,
		ServiceContext serviceContext) {
		this.setUserId(serviceContext.getUserId());
		this.setGroupId(serviceContext.getScopeGroupId());
		this.setCompanyId(serviceContext.getCompanyId());

		List<Long> toUserIds = new ArrayList<>();
		toUserIds.add(toUserId);

		this.setToUserIds(toUserIds);

		this.setToAddress(initArrayInternetAddress(toEmail, toName));

//		this.setFrom(initInternetAddress("no-reply@fds.vn", fromName));
		this.setFrom(initInternetAddress(PropValues.MAIL_ADMIN_SERVER, fromName));

	}

	private InternetAddress initInternetAddress(String email, String name) {

		InternetAddress internetAddresses = null;
		try {
			internetAddresses = new InternetAddress(email, name);
		}
		catch (Exception e) {
			_log.error(e);
		}
		return internetAddresses;
	}

	private InternetAddress[] initArrayInternetAddress(String email, String name) {

		InternetAddress[] internetAddresses = null;
		try {
			internetAddresses = new InternetAddress[] {
				new InternetAddress(email, name)
			};
		}
		catch (Exception e) {
			_log.error(e);
		}
		return internetAddresses;
	}

	private long groupId;
	private long companyId;
	private long userId;

	private List<Long> toUserIds;

	private InternetAddress from;
	private InternetAddress[] toAddress;
	private InternetAddress[] ccAddress;
	private InternetAddress[] bccAddress;
	private InternetAddress[] bulkAddress;

	private Date createDate;
	private Date modifiedDate;
	private String notificationType;
	private String fromEmail;
	private String fromName;

	private boolean sendEmail;
	private boolean sendSMS;
	private boolean sendNotify;
	private boolean sendZalo;
	private String emailSubject;
	private String emailBody;
	private String textMessage;
	
	private String zaloAccessToken;
	private Map<Long, String> mappingZaloUid;
	
	public String getZaloAccessToken() {

		return zaloAccessToken;
	}

	public void setZaloAccessToken(String zaloAccessToken) {

		this.zaloAccessToken = zaloAccessToken;
	}

	public Map<Long, String> getMappingZaloUid() {

		return mappingZaloUid;
	}

	public void setMappingZaloUid(Map<Long, String> mappingZaloUid) {

		this.mappingZaloUid = mappingZaloUid;
	}
	
	public String getNotifyMessage() {
		return notifyMessage;
	}

	public void setNotifyMessage(String notifyMessage) {
		this.notifyMessage = notifyMessage;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	private String notifyMessage;
	private String data;
	private String userUrl;
	private String guestUrl;

	// private String userUrlPattern;
	// private String guestUrlPattern;

	private int expireDuration;
	private int interval;
	private int grouping;
	private File attachement;
	private String attachementName;
	private String className;
	private String toTelNo;

	public long getGroupId() {

		return groupId;
	}

	public long getCompanyId() {

		return companyId;
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

	public String getFromEmail() {

		return fromEmail;
	}

	public String getFromName() {

		return fromName;
	}

	public boolean isSendEmail() {

		return sendEmail;
	}

	public boolean isSendSMS() {

		return sendSMS;
	}

	public String getEmailSubject() {

		return emailSubject;
	}

	public String getEmailBody() {

		return emailBody;
	}

	public String getTextMessage() {

		return textMessage;
	}

	/*
	 * public String getUserUrlPattern() { return userUrlPattern; } public
	 * String getGuestUrlPattern() { return guestUrlPattern; }
	 */

	public int getExpireDuration() {

		return expireDuration;
	}

	public int getInterval() {

		return interval;
	}

	public int getGrouping() {

		return grouping;
	}

	public File getAttachement() {

		return attachement;
	}

	public String getAttachementName() {

		return attachementName;
	}

	public void setGroupId(long groupId) {

		this.groupId = groupId;
	}

	public void setCompanyId(long companyId) {

		this.companyId = companyId;
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

	public void setFromEmail(String fromEmail) {

		this.fromEmail = fromEmail;
	}

	public void setFromName(String fromName) {

		this.fromName = fromName;
	}

	public void setSendEmail(boolean sendEmail) {

		this.sendEmail = sendEmail;
	}

	public void setSendSMS(boolean sendSMS) {

		this.sendSMS = sendSMS;
	}

	public void setEmailSubject(String emailSubject) {

		this.emailSubject = emailSubject;
	}

	public void setEmailBody(String emailBody) {

		this.emailBody = emailBody;
	}

	public void setTextMessage(String textMessage) {

		this.textMessage = textMessage;
	}

	/*
	 * public void setUserUrlPattern(String userUrlPattern) {
	 * this.userUrlPattern = userUrlPattern; } public void
	 * setGuestUrlPattern(String guestUrlPattern) { this.guestUrlPattern =
	 * guestUrlPattern; }
	 */

	public void setExpireDuration(int expireDuration) {

		this.expireDuration = expireDuration;
	}

	public void setInterval(int interval) {

		this.interval = interval;
	}

	public void setGrouping(int grouping) {

		this.grouping = grouping;
	}

	public void setAttachement(File attachement) {

		this.attachement = attachement;
	}

	public void setAttachementName(String attachementName) {

		this.attachementName = attachementName;
	}

	public List<Long> getToUserIds() {

		return toUserIds;
	}

	public void setToUserIds(List<Long> toUserIds) {

		this.toUserIds = toUserIds;
	}

	public InternetAddress[] getToAddress() {

		return toAddress;
	}

	public InternetAddress[] getCcAddress() {

		return ccAddress;
	}

	public InternetAddress[] getBccAddress() {

		return bccAddress;
	}

	public InternetAddress[] getBulkAddress() {

		return bulkAddress;
	}

	public void setToAddress(InternetAddress[] toAddress) {

		this.toAddress = toAddress;
	}

	public void setCcAddress(InternetAddress[] ccAddress) {

		this.ccAddress = ccAddress;
	}

	public void setBccAddress(InternetAddress[] bccAddress) {

		this.bccAddress = bccAddress;
	}

	public void setBulkAddress(InternetAddress[] bulkAddress) {

		this.bulkAddress = bulkAddress;
	}

	public boolean isSendNotify() {

		return sendNotify;
	}

	public void setSendNotify(boolean sendNotify) {

		this.sendNotify = sendNotify;
	}

	public InternetAddress getFrom() {

		return from;
	}

	public void setFrom(InternetAddress from) {

		this.from = from;
	}

	
	public String getClassName() {
	
		return className;
	}

	
	public void setClassName(String className) {
	
		this.className = className;
	}

	
	public String getUserUrl() {
	
		return userUrl;
	}

	
	public String getGuestUrl() {
	
		return guestUrl;
	}

	
	public void setUserUrl(String userUrl) {
	
		this.userUrl = userUrl;
	}

	
	public void setGuestUrl(String guestUrl) {
	
		this.guestUrl = guestUrl;
	}

	public String getToTelNo() {
		return toTelNo;
	}

	public void setToTelNo(String toTelNo) {
		this.toTelNo = toTelNo;
	}
	
	public boolean isSendZalo() {

		return sendZalo;
	}

	public void setSendZalo(boolean sendZalo) {

		this.sendZalo = sendZalo;
	}

}
