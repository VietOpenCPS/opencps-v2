package org.opencps.kyso.action;

import com.liferay.portal.kernel.json.JSONObject;

public interface DigitalSignatureActions {

	public JSONObject createHashSignature(String emailUser, long fileEntryId, String typeSignature, String postStepCode, long groupId);

	public JSONObject completeSignature(String sign, String signFieldName, String fileName);
}
