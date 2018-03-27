package org.opencps.synchronization.rest.model;

import org.opencps.datamgt.constants.DictGroupTerm;

import com.liferay.portal.kernel.json.JSONObject;

public class DictGroupModel {
	private String groupCode;
	private String groupName;
	private String groupNameEN;
	
	public static DictGroupModel fromJSONObject(JSONObject obj) {
		DictGroupModel model = new DictGroupModel();
		
		if (obj.has(DictGroupTerm.GROUP_CODE)) {
			model.setGroupCode(obj.getString(DictGroupTerm.GROUP_CODE));
		}
		if (obj.has(DictGroupTerm.GROUP_NAME)) {
			model.setGroupName(obj.getString(DictGroupTerm.GROUP_NAME));
		}
		if (obj.has(DictGroupTerm.GROUP_NAME_EN)) {
			model.setGroupNameEN(obj.getString(DictGroupTerm.GROUP_NAME_EN));
		}
		
		return model;
	}
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupNameEN() {
		return groupNameEN;
	}
	public void setGroupNameEN(String groupNameEN) {
		this.groupNameEN = groupNameEN;
	}
}
