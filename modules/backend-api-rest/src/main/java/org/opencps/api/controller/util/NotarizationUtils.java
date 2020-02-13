package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.notarization.model.NotarizationDetailModel;
import org.opencps.api.notarization.model.NotarizationModel;
import org.opencps.dossiermgt.model.Notarization;

public class NotarizationUtils {
	public static NotarizationDetailModel mappingToNotarizationDetailModel(Notarization notarization) {
		NotarizationDetailModel model = new NotarizationDetailModel();
		model.setDossierId(notarization.getDossierId());
		model.setFileName(notarization.getFileName());
		model.setTotalRecord(notarization.getTotalRecord());
		model.setTotalCopy(notarization.getTotalCopy());
		model.setTotalFee(notarization.getTotalFee());
		model.setTotalPage(notarization.getTotalPage());
		model.setNotarizationNo(notarization.getNotarizationNo());
		model.setNotarizationYear(notarization.getNotarizationYear());
		model.setNotarizationDate(notarization.getNotarizationDate().getTime());
		model.setSignerName(notarization.getSignerName());
		model.setSignerPosition(notarization.getSignerPosition());
		model.setStatusCode(notarization.getStatusCode());
		return model;
	}

	public static NotarizationModel mappingToNotarizationModel(Notarization notarization) {
		NotarizationModel model = new NotarizationModel();
		model.setDossierId(notarization.getDossierId());
		model.setFileName(notarization.getFileName());
		model.setTotalRecord(notarization.getTotalRecord());
		model.setTotalCopy(notarization.getTotalCopy());
		model.setTotalFee(notarization.getTotalFee());
		model.setTotalPage(notarization.getTotalPage());
		model.setNotarizationNo(notarization.getNotarizationNo());
		model.setNotarizationYear(notarization.getNotarizationYear());
		model.setNotarizationDate(notarization.getNotarizationDate().getTime());
		model.setSignerName(notarization.getSignerName());
		model.setSignerPosition(notarization.getSignerPosition());
		model.setStatusCode(notarization.getStatusCode());
		return model;
	}
	
	public static List<NotarizationModel> mappingToNotarizationResults(List<Notarization> lstNotarizations) {
		List<NotarizationModel> results = new ArrayList<NotarizationModel>();
		for (Notarization n : lstNotarizations) {
			NotarizationModel model = mappingToNotarizationModel(n);
			results.add(model);
		}
		return results;
	}
}
