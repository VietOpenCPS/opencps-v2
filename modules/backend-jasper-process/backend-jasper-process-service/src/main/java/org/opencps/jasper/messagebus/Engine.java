package org.opencps.jasper.messagebus;

import java.io.File;
import java.io.IOException;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.jasper.utils.JRReportUtil;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class Engine implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		// TODO Auto-generated method stub
		try {
			_doReceiveJasperRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	private void _doReceiveJasperRequest(Message message) {
		// TODO Auto-generated method stub
		_log.info("Jasper processing .............................");
		JSONObject msgData = (JSONObject) message.get("msgToEngine");

		File file = FileUtil.createTempFile(JRReportUtil.DocType.PDF.toString());

		String sourceFileName = System.currentTimeMillis() + StringPool.PERIOD + JRReportUtil.DocType.PDF.toString();

		try {

			long userId = msgData.getLong("userId");

			long classPK = msgData.getLong("classPK");

			DossierFile dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(classPK);

			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setUserId(userId);

			String fileExport = JRReportUtil.createReportFile(msgData.getString("jrxmlTemplate"),
					msgData.getString("formData"), null, file.getCanonicalPath());

			if (Validator.isNotNull(fileExport)) {
				FileEntry fileEntry;

				try {

					fileEntry = FileUploadUtils.uploadDossierFile(msgData.getLong("userId"), dossierFile.getGroupId(),
							file, sourceFileName, serviceContext);

					_log.info("Jasper exporting success : " + file.getCanonicalPath() );
					
					long fileEntryId = fileEntry.getFileEntryId();

					dossierFile.setFileEntryId(fileEntryId);

					dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

					Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);

					indexer.reindex(dossierFile);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			_log.info(fileExport + " <---------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Log _log = LogFactoryUtil.getLog(Engine.class);
}
