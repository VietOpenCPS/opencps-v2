package org.opencps.synchronization.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.constants.DictItemTerm;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class DictItemModel {
	private Date createDate;
	private Date modifiedDate;
	private String itemCode;
	private String itemName;
	private String itemNameEN;
	private String itemDescription;
	private DictItemModel parentItem;
	private int level;
	private int sibling;
	private String treeIndex;
	private List<DictGroupModel> groups;
	
	public static DictItemModel fromJSONObject(JSONObject obj) {
		DictItemModel model = new DictItemModel();
		
		if (obj.has(DictItemTerm.CREATE_DATE)) {
			model.setCreateDate(APIDateTimeUtils.convertStringToDate(obj.getString(DictItemTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP));
		}
		if (obj.has(DictItemTerm.MODIFIED_DATE)) {
			model.setCreateDate(APIDateTimeUtils.convertStringToDate(obj.getString(DictItemTerm.MODIFIED_DATE), APIDateTimeUtils._TIMESTAMP));
		}

		if (obj.has(DictItemTerm.ITEM_CODE)) {
			model.setItemCode(obj.getString(DictItemTerm.ITEM_CODE));
		}
		
		if (obj.has(DictItemTerm.ITEM_NAME)) {
			model.setItemCode(obj.getString(DictItemTerm.ITEM_NAME));
		}
		
		if (obj.has(DictItemTerm.ITEM_NAME_EN)) {
			model.setItemCode(obj.getString(DictItemTerm.ITEM_NAME_EN));
		}

		if (obj.has(DictItemTerm.ITEM_DESCRIPTION)) {
			model.setItemCode(obj.getString(DictItemTerm.ITEM_DESCRIPTION));
		}
		
		if (obj.has(DictItemTerm.LEVEL)) {
			model.setLevel(obj.getInt(DictItemTerm.LEVEL));
		}
		
		if (obj.has(DictItemTerm.SIBLING)) {
			model.setSibling(obj.getInt(DictItemTerm.SIBLING));
		}
		
		if (obj.has(DictItemTerm.TREE_INDEX)) {
			model.setTreeIndex(obj.getString(DictItemTerm.TREE_INDEX));
		}
		
		if (obj.has("parentItem") && Validator.isNotNull(obj.getString("parentItem"))) {
			DictItemModel parentModel = new DictItemModel();
			JSONObject parentObj = obj.getJSONObject("parentItem");
			parentModel.setItemCode(parentObj.getString(DictItemTerm.ITEM_CODE));
			parentModel.setItemName(parentObj.getString(DictItemTerm.ITEM_NAME));
			parentModel.setItemNameEN(parentObj.getString(DictItemTerm.ITEM_NAME_EN));
		}
		
		if (obj.has("groups") && Validator.isNotNull(obj.get("groups"))) {
			JSONArray groupArr = obj.getJSONArray("groups");
			
			if (groupArr != null) {
				List<DictGroupModel> lstGroups = new ArrayList<>();
				
				for (int i = 0; i < groupArr.length(); i++) {
					lstGroups.add(DictGroupModel.fromJSONObject(groupArr.getJSONObject(i)));				
				}				
			}
			
		}
		return model;
	}
	
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
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemNameEN() {
		return itemNameEN;
	}
	public void setItemNameEN(String itemNameEN) {
		this.itemNameEN = itemNameEN;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public DictItemModel getParentItem() {
		return parentItem;
	}
	public void setParentItem(DictItemModel parentItem) {
		this.parentItem = parentItem;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSibling() {
		return sibling;
	}
	public void setSibling(int sibling) {
		this.sibling = sibling;
	}
	public String getTreeIndex() {
		return treeIndex;
	}
	public void setTreeIndex(String treeIndex) {
		this.treeIndex = treeIndex;
	}
	public List<DictGroupModel> getGroups() {
		return groups;
	}
	public void setGroups(List<DictGroupModel> groups) {
		this.groups = groups;
	}
}
