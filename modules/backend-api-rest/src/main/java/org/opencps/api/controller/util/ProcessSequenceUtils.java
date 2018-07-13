package org.opencps.api.controller.util;

import org.opencps.api.processsequence.model.ProcessSequenceOneOutputModel;
import org.opencps.dossiermgt.model.ProcessSequence;

public class ProcessSequenceUtils {
	public static ProcessSequenceOneOutputModel mappingDetail(ProcessSequence ps) {
		ProcessSequenceOneOutputModel model = new ProcessSequenceOneOutputModel();
		model.setDurationCount(ps.getDurationCount());
		model.setSequenceName(ps.getSequenceName());
		model.setSequenceNo(ps.getSequenceNo());
		model.setSequenceRole(ps.getSequenceRole());
		return model;
	}
}
