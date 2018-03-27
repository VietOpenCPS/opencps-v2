package org.opencps.synchronization.rest.model;

import java.util.Date;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.constants.DictCollectionTerm;

import com.liferay.portal.kernel.json.JSONObject;

public class DictCollectionModel {
	private Date createDate;
	private Date modifiedDate;
	private String collectionCode;
	private String collectionName;
	private String collectionNameEN;
	private String description;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public String getCollectionNameEN() {
		return collectionNameEN;
	}
	public void setCollectionNameEN(String collectionNameEN) {
		this.collectionNameEN = collectionNameEN;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static DictCollectionModel fromJSONObject(JSONObject obj) {
		DictCollectionModel model = new DictCollectionModel();
		
		if (obj.has(DictCollectionTerm.CREATE_DATE)) {
			model.setCreateDate(APIDateTimeUtils.convertStringToDate(obj.getString(DictCollectionTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP));
		}
		if (obj.has(DictCollectionTerm.MODIFIED_DATE)) {
			model.setCreateDate(APIDateTimeUtils.convertStringToDate(obj.getString(DictCollectionTerm.MODIFIED_DATE), APIDateTimeUtils._TIMESTAMP));
		}
		
		if (obj.has(DictCollectionTerm.COLLECTION_CODE)) {
			model.setCollectionCode(obj.getString(DictCollectionTerm.COLLECTION_CODE));
		}
		
		if (obj.has(DictCollectionTerm.COLLECTION_NAME)) {
			model.setCollectionName(obj.getString(DictCollectionTerm.COLLECTION_NAME));
		}
		
		if (obj.has(DictCollectionTerm.COLLECTION_NAME_EN)) {
			model.setCollectionName(obj.getString(DictCollectionTerm.COLLECTION_NAME_EN));
		}

		if (obj.has(DictCollectionTerm.DESCRIPTION)) {
			model.setCollectionName(obj.getString(DictCollectionTerm.DESCRIPTION));
		}
		
		return model;
	}
}
