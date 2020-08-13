package service.dependencies;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.util.ReadXMLFileUtils;
import service.ImportFile;

public class ImportFileImpl implements ImportFile {
    private static final Log _log = LogFactoryUtil.getLog(ImportFileImpl.class);
    @Override
    public void importExcelFile(String xmlString, long groupId, long userId, ServiceContext serviceContext) {
        try {
            _log.info("----Thread importing file...");

            ReadXMLFileUtils.compareParentFile(
                    "", ConstantUtils.XML_USERS, xmlString,
                    groupId, userId, serviceContext);
            _log.info("Import file excel succeed!");

        } catch (Exception e) {
            _log.error(e.getMessage());
        }
    }
}
