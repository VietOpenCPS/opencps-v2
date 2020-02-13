package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;

import org.opencps.dossiermgt.model.Notarization;

public interface NotarizationActions {
	public Notarization createNotarization(long groupId, long dossierId, String fileName,
			int totalRecord, int totalPage, int totalCopy,
			long totalFee, long notarizationNo, int notarizationYear,
			Date notarizationDate, String signerName, String signerPosition,
			String statusCode,
			ServiceContext serviceContext) throws PortalException;
	public Notarization updateNotarization(long groupId, long notarizationId, long dossierId, String fileName,
			int totalRecord, int totalPage, int totalCopy,
			long totalFee, long notarizationNo, int notarizationYear,
			Date notarizationDate, String signerName, String signerPosition,
			String statusCode,
			ServiceContext serviceContext) throws PortalException;
}
