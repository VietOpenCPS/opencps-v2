package org.opencps.frontend.web.admin.constants;

public class FrontendWebAdminPortletConstants {

	public enum AdminMenuItemType {
		ACTIVITY("ActivityType"), DOCUMENT("DocumentType"), DICTCOLLECTION("DictCollection"), WORKINGUNIT(
				"WorkingUnit"), WORKSPACE("Workspace"), JOBPOS("JobPos"), CONTACT("Contact"), OFFICESITE(
						"OfficeSite"), LOCATION(
								"Location"), LABEL("Lable"), NOTIFICATIONTEMPLATE("NotificationTemplate");

		private String value;

		AdminMenuItemType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return this.getValue();
		}

		public static AdminMenuItemType getEnum(String value) {
			for (AdminMenuItemType v : values())
				if (v.getValue().equalsIgnoreCase(value))
					return v;
			throw new IllegalArgumentException();
		}
	}

	public static final String _ACTIVITY_CLASSNAME = "activity";
	public static final String _DOCUMENT_CLASSNAME = "docFile";
	public static final String _OFFICESITE_CLASSNAME = "officeSite";

}
