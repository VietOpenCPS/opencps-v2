package org.fds.opencps.paygate.integration.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.File;

/**
 * @author trungnt
 *
 */
public interface PayGateIntegrationAction {
	public File genneralQRCode(User user, long groupId, long dossierId, ServiceContext serviceContext);

	public JSONObject doConfirm(User user, ServiceContext serviceContext, String billcode, String merchant_code,
			String order_id, String check_sum);
}
