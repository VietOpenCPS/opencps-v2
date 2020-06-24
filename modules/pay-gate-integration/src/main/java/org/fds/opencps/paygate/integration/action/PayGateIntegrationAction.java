package org.fds.opencps.paygate.integration.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

/**
 * @author trungnt
 *
 */
public interface PayGateIntegrationAction {
	public File genneralQRCode(User user, long groupId, long dossierId, ServiceContext serviceContext);

	public JSONObject doConfirm(User user, ServiceContext serviceContext, String billcode, String merchant_code,
			String order_id, String check_sum);

	public JSONObject receiveResult(User user, ServiceContext serviceContext, String billcode, String cust_msisdn,
			String error_code, String merchant_code, String order_id, int payment_status, long trans_amount,
			String vt_transaction_id, String check_sum);

	public JSONObject searchResult(User user, ServiceContext serviceContext, String order_id, String billcode, String cust_msisdn, long trans_amount);

	public JSONObject mcDoConfirm(User user, ServiceContext serviceContext, String billcode, String merchant_code,
			String order_id, String check_sum);

	public JSONObject mcReceiveResult(User user, ServiceContext serviceContext, String billcode, String cust_msisdn,
			String error_code, String merchant_code, String order_id, int payment_status, long trans_amount,
			String vt_transaction_id, String check_sum);

	public JSONObject mcSearchResult(User user, ServiceContext serviceContext, String order_id, String billcode, String cust_msisdn, long trans_amount);

	public JSONObject dvcReceiveResult(User user, ServiceContext serviceContext, String url, long groupId, String actionCode, String order_id, String username, String pwd);
	
	public JSONObject kpCallBack(User user, ServiceContext serviceContext, String body);
	
	public String kpCreateTransaction(User user, long groupId, long dossierId, ServiceContext serviceContext);
	
	public JSONObject kpViewDetailTransaction(User user, long groupId, long dossierId, ServiceContext serviceContext);
	
	public String ppInitTransaction(User user, long groupId, long dossierId, ServiceContext serviceContext, HttpServletRequest request);
	
	public JSONObject ppConfirmTransaction(User user, ServiceContext serviceContext, String body);
	
	public String ppGetReceipt(User user, long groupId, long dosssierId, ServiceContext serviceContext);
}
