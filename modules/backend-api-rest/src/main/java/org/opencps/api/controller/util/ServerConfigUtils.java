package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.serverconfig.model.ServerConfigDataModel;
import org.opencps.api.serverconfig.model.ServerConfigDetailModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;

public class ServerConfigUtils {

	public static List<ServerConfigDataModel> mappingTOData(List<ServerConfig> configs) {

		List<ServerConfigDataModel> outputs = new ArrayList<ServerConfigDataModel>();

		for (ServerConfig cf : configs) {
			ServerConfigDataModel model = new ServerConfigDataModel();

			model.setServerConfigId(cf.getPrimaryKey());
			model.setCreateDate(
					APIDateTimeUtils.convertDateToString(cf.getCreateDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			model.setModifiedDate(
					APIDateTimeUtils.convertDateToString(cf.getModifiedDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			model.setServerNo(cf.getServerNo());
			model.setServerName(cf.getServerName());
			model.setProtocol(cf.getProtocol());
			model.setLastSync(
					APIDateTimeUtils.convertDateToString(cf.getLastSync(), APIDateTimeUtils._NORMAL_PARTTERN));
			model.setConfigs(cf.getConfigs());
			
			outputs.add(model);
		}

		return outputs;
	}

	public static ServerConfigDetailModel mappingToDetailModel(ServerConfig cf) {
		ServerConfigDetailModel model = new ServerConfigDetailModel();

		model.setServerConfigId(cf.getPrimaryKey());
		model.setCreateDate(
				APIDateTimeUtils.convertDateToString(cf.getCreateDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setModifiedDate(
				APIDateTimeUtils.convertDateToString(cf.getModifiedDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setServerNo(cf.getServerNo());
		model.setServerName(cf.getServerName());
		model.setProtocol(cf.getProtocol());
		model.setLastSync(APIDateTimeUtils.convertDateToString(cf.getLastSync(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setConfigs(cf.getConfigs());
		model.setGroupId(cf.getGroupId());

		return model;
	}
}
