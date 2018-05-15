package org.opencps.api.vnpost.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VotingInputModel", propOrder = { "govAgencyCode", "subject", "choices",
		"commentable"})
public class VotingModel {
	@FormParam(value = "govAgencyCode")
	private String govAgencyCode; 
	@FormParam(value = "subject")
	private String subject;
	@FormParam(value = "choices")
	private String[] choices; 
	@FormParam(value = "commentable")
	private Boolean commentable;
	/**
	 * @return the govAgencyCode
	 */
	public String getGovAgencyCode() {
		return govAgencyCode;
	}
	/**
	 * @param govAgencyCode the govAgencyCode to set
	 */
	public void setGovAgencyCode(String govAgencyCode) {
		this.govAgencyCode = govAgencyCode;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the choices
	 */
	public String[] getChoices() {
		return choices;
	}
	/**
	 * @param choices the choices to set
	 */
	public void setChoices(String[] choices) {
		this.choices = choices;
	}
	/**
	 * @return the senderProvince
	 */
	/**
	 * @return the commentable
	 */
	public Boolean getCommentable() {
		return commentable;
	}
	/**
	 * @param commentable the commentable to set
	 */
	public void setCommentable(Boolean commentable) {
		this.commentable = commentable;
	}
	
	
}
