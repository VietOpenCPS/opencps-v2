package org.opencps.dossiermgt.action.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;

import org.opencps.dossiermgt.action.NotarizationActions;
import org.opencps.dossiermgt.model.Notarization;
import org.opencps.dossiermgt.service.NotarizationLocalServiceUtil;

public class NotarizationActionsImpl implements NotarizationActions {

	@Override
	public Notarization createNotarization(long groupId, long dossierId, String fileName, int totalRecord,
			int totalPage, int totalCopy, long totalFee, long notarizationNo, int notarizationYear,
			Date notarizationDate, String signerName, String signerPosition, String statusCode,
			ServiceContext serviceContext) throws PortalException {
		return NotarizationLocalServiceUtil.addNotarization(groupId, dossierId, fileName, totalRecord, totalPage, totalCopy, totalFee, notarizationNo, notarizationYear, notarizationDate, signerName, signerPosition, statusCode, serviceContext);
	}

	@Override
	public Notarization updateNotarization(long groupId, long notarizationId, long dossierId, String fileName,
			int totalRecord, int totalPage, int totalCopy, long totalFee, long notarizationNo, int notarizationYear,
			Date notarizationDate, String signerName, String signerPosition, String statusCode,
			ServiceContext serviceContext) throws PortalException {
		return NotarizationLocalServiceUtil.updateNotarization(groupId, notarizationId, dossierId, fileName, totalRecord, totalPage, totalCopy, totalFee, notarizationNo, notarizationYear, notarizationDate, signerName, signerPosition, statusCode, serviceContext);
	}

}
