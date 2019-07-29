package org.opencps.api.dictcollection.model;

import java.util.Comparator;

public class DictItemCompareModel {

	protected String itemCode;
	protected String itemName;
	protected int sibling;

	public DictItemCompareModel(String itemCode, String itemName, int sibling) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.sibling = sibling;
	}

	/* Comparator for sorting the list by Student Name */
	public static final Comparator<DictItemCompareModel> SiblingComparator = new Comparator<DictItemCompareModel>() {

		public int compare(DictItemCompareModel item1, DictItemCompareModel item2) {
			int sibling1 = item1.getSibling();
			int sibling2 = item2.getSibling();

			// ascending order
			return sibling1 - sibling2;

			// descending order
			// return StudentName2.compareTo(StudentName1);
		}
	};

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String value) {
		this.itemCode = value;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String value) {
		this.itemName = value;
	}

	public int getSibling() {
		return sibling;
	}

	public void setSibling(int value) {
		this.sibling = value;
	}

}
