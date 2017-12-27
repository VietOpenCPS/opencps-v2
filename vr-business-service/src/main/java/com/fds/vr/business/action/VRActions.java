package com.fds.vr.business.action;

import com.liferay.portal.kernel.json.JSONObject;

public interface VRActions {
	public JSONObject getTechSpecByVehicleClass(long groupId, String module, long dossierId, long dossierFileId,
			String vehicleClass);

	public JSONObject getTechSpecLimit(long groupId, String module, long dossierId, long dossierFileId,
			String vehicleClass, String vehicleType, long fomulaType);

	public JSONObject getTechSpecByVehicleClassType(long groupId, String module, long dossierId, long dossierFileId,
			String vehicleClass, String vehicleType);

	public JSONObject getDictItem(long groupId, String dictCollectionCode, String dictCollectionType);

	public JSONObject getTechSpecByVehicleClassExt(long groupId, String module, long dossierId, long dossierFileId,
			String fileTemplateNo, String vehicleClass);
}
