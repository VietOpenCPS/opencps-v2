package org.opencps.kyso.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.viettel.signature.plugin.SignPdfFile;
import javax.servlet.http.HttpServletRequest;


public interface DigitalSignatureActions {

	public JSONObject createHashSignature(String emailUser, long fileEntryId, String typeSignature, String postStepCode, long groupId);

	public JSONObject completeSignature(String sign, String signFieldName, String fileName);
	
	public JSONObject hashFile(long fileEntryId, String certChainBase64, HttpServletRequest request);
	
	public JSONObject insertSignnature(String signatureBase64, String signFileName, SignPdfFile signPdfFile, long fileEntryId);
}
