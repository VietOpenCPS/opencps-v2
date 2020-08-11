package service;

import com.liferay.portal.kernel.service.ServiceContext;

public interface ImportFile {
    public void importExcelFile(String xmlString, long groupId, long userId, ServiceContext serviceContext);
}
