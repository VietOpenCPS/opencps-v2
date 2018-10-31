package org.opencps.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * UsersUserItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-08T02:56:13.835Z")

public class FileTemplateMiniItem {
	@JsonProperty("fileTemplateNo")
	private String fileTemplateNo = null;

	@JsonProperty("templateName")
	private String templateName = null;

	public FileTemplateMiniItem() {
		// TODO Auto-generated constructor stub
	}

	public FileTemplateMiniItem(String fileTemplateNo, String templateName) {
		this.fileTemplateNo = fileTemplateNo;
		this.templateName = templateName;
	}
	
	public String getFileTemplateNo() {
		return fileTemplateNo;
	}

	public void setFileTemplateNo(String fileTemplateNo) {
		this.fileTemplateNo = fileTemplateNo;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
