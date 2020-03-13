package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.configcounter.model.ConfigCounterDetailModel;
import org.opencps.api.configcounter.model.ConfigCounterModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.model.ConfigCounter;

public class ConfigCounterUtils {

	
	public static List<ConfigCounterModel> mappingToListConfig(List<ConfigCounter> configList) {
		if (configList == null) {
			return null;
		}
		List<ConfigCounterModel> configModelList = new ArrayList<ConfigCounterModel>();
		for (ConfigCounter config : configList) {
			ConfigCounterModel model = new ConfigCounterModel();

			model.setConfigCounterId(config.getConfigCounterId());
			model.setCounterCode(config.getCounterCode());
			model.setPatternCode(config.getPatternCode());
			model.setStartCounter(config.getStartCounter());
			model.setCreateDate(
					APIDateTimeUtils.convertDateToString(config.getCreateDate(), APIDateTimeUtils._NORMAL_DATE_TIME));
			model.setModifiedDate(
					APIDateTimeUtils.convertDateToString(config.getModifiedDate(), APIDateTimeUtils._NORMAL_DATE_TIME));
			//
			configModelList.add(model);
		}
		return configModelList;
	}

	public static ConfigCounterDetailModel mappingToConfigCounterModel(ConfigCounter config) {

		if (config == null) {
			return null;
		}
		ConfigCounterDetailModel model = new ConfigCounterDetailModel();

		model.setConfigCounterId(config.getConfigCounterId());
		model.setCounterCode(config.getCounterCode());
		model.setPatternCode(config.getPatternCode());
		model.setStartCounter(config.getStartCounter());
		model.setCreateDate(
				APIDateTimeUtils.convertDateToString(config.getCreateDate(), APIDateTimeUtils._NORMAL_DATE_TIME));
		model.setModifiedDate(
				APIDateTimeUtils.convertDateToString(config.getModifiedDate(), APIDateTimeUtils._NORMAL_DATE_TIME));

		return model;
	}
}
