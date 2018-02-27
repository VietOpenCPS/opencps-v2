package org.opencps.dossiermgt.action;

public interface VNPostAction {

	String postOrder(String fullName, String phoneNumber, String deliverAddress, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, long dossierId, long groupId)
			throws Exception;
	
}
