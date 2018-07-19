package org.opencps.api.controller.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.v21.model.ActionConfigList;
import org.opencps.api.v21.model.DeliverableTypeList;
import org.opencps.api.v21.model.DictCollection;
import org.opencps.api.v21.model.DocumentTypeList;
import org.opencps.api.v21.model.DossierTemplate;
import org.opencps.api.v21.model.MenuConfigList;
import org.opencps.api.v21.model.NotificationTemplateList;
import org.opencps.api.v21.model.ObjectFactory;
import org.opencps.api.v21.model.PaymentConfigList;
import org.opencps.api.v21.model.ServerConfigList;
import org.opencps.api.v21.model.ServiceInfo;
import org.opencps.api.v21.model.ServiceProcess;
import org.opencps.api.v21.model.StepConfigList;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ReadXMLFileUtils {

	private static Log _log = LogFactoryUtil.getLog(ReadXMLFileUtils.class);

	public static String convertFiletoString(File fXmlFile) {
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(fXmlFile), "UTF8"));
			StringBuilder sb = new StringBuilder();
			String line = bufReader.readLine();
			while (line != null) {
				sb.append(line).append("\n");
				line = bufReader.readLine();
			}
			String xml2String = sb.toString();
			_log.info("XML to String using BufferedReader : ");
			_log.info(xml2String);

			return xml2String;
		} catch (Exception e) {
			_log.error(e);
		} finally {
			try {
				bufReader.close();
			} catch (IOException e1) {
				_log.error(e1);
			}
		}
		return StringPool.BLANK;
	}



	//LamTV_Process get list file of folder
	public static void listFilesForParentFolder(File fileList, long groupId, long userId,
			ServiceContext serviceContext) {
		try {
			File[] files = fileList.listFiles();
			String folderParentPath = fileList.getPath();
			if (files != null && files.length > 0) {
				for (File fileEntry : files) {
					if (fileEntry.isDirectory()) {
						listFilesForFolder(fileEntry, fileEntry.getPath(), folderParentPath, groupId, userId,
								serviceContext);
					} else {
						String fileName = fileEntry.getName();
						String subFileName = ImportZipFileUtils.getSubFileName(fileName);
						if (Validator.isNotNull(subFileName)) {
							String xmlString = convertFiletoString(fileEntry);
							compareParentFile(folderParentPath, fileName, xmlString, groupId, userId, serviceContext);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private static void listFilesForFolder(File fileEntry, String folderPath, String folderParentPath, long groupId,
			long userId, ServiceContext serviceContext) {
		_log.info("folderPath: "+folderPath);
		_log.info("folderParentPath: "+folderParentPath);
		if (Validator.isNotNull(folderPath)) {
			int index = folderPath.lastIndexOf(StringPool.FORWARD_SLASH);
			String subFolder = folderPath.substring(index);
			_log.info("subFolder: "+subFolder);
			if (Validator.isNotNull(subFolder)) {
				switch (subFolder) {
				case ConstantUtils.SOURCE_DICTS:
					processListFileDict(fileEntry, groupId, userId, serviceContext);
					break;
				case ConstantUtils.SOURCE_SERVICES:
					processListFileService(fileEntry, folderParentPath, groupId, userId, serviceContext);
					break;
				case ConstantUtils.SOURCE_TEMPLATES:
					processListFileTemplate(fileEntry, folderParentPath, groupId, userId, serviceContext);
					break;
				case ConstantUtils.SOURCE_PROCESSES:
					processListFileProcess(fileEntry, groupId, userId, serviceContext);
					break;
				default:
					break;
				}
			}
		}
	}

	private static void compareParentFile(String folderPath, String fileName, String xmlString, long groupId,
			long userId, ServiceContext serviceContext) {
		try{
		if (Validator.isNotNull(fileName)) {
			switch (fileName) {
			case ConstantUtils.XML_ACTION_CONFIG:
				ActionConfigList actList = convertXMLToActionConfig(xmlString);
				ProcessUpdateDBUtils.processUpdateActionConfig(actList, folderPath, groupId, userId, serviceContext);
				break;
			case ConstantUtils.XML_STEP_CONFIG:
				StepConfigList stepList = convertXMLToStepConfig(xmlString);
				ProcessUpdateDBUtils.processUpdateStepConfig(stepList, groupId, userId, serviceContext);
				break;
			case ConstantUtils.XML_MENU_CONFIG:
				MenuConfigList menuList = convertXMLToMenuConfig(xmlString);
				ProcessUpdateDBUtils.processUpdateMenuConfig(menuList, groupId, userId, serviceContext);
				break;
			case ConstantUtils.XML_DOCUMENT_TYPE:
				DocumentTypeList docList = convertXMLToDocumentType(xmlString);
				ProcessUpdateDBUtils.processUpdateDocumentType(docList, folderPath, groupId, userId, serviceContext);
				break;
			case ConstantUtils.XML_DELIVERABLE_TYPE:
				DeliverableTypeList deliTypeList = convertXMLToDeliverableType(xmlString);
				ProcessUpdateDBUtils.processUpdateDeliverableType(deliTypeList, folderPath, groupId, userId, serviceContext);
				break;
			case ConstantUtils.XML_PAYMENT_CONFIG:
				PaymentConfigList paymentList = convertXMLToPaymentConfig(xmlString);
				ProcessUpdateDBUtils.processUpdatePaymentConfig(paymentList, groupId, userId, serviceContext);
				break;
			case ConstantUtils.XML_SERVER_CONFIG:
				ServerConfigList serverList = convertXMLToServerConfig(xmlString);
				ProcessUpdateDBUtils.processUpdateServerConfig(serverList, groupId, userId, serviceContext);
				break;
			case ConstantUtils.XML_NOTIFICATION_TEMPLATE:
				NotificationTemplateList notiTempList = convertXMLToNotificationTemplate(xmlString);
				ProcessUpdateDBUtils.processUpdateNotificationTemplate(notiTempList, groupId, userId, serviceContext);
				break;
			default:
				break;
			}
		}
		}catch (Exception e) {
			_log.error(e);
		}
	}

	/** Process output object to DB - START */
	private static void processListFileDict(File fileEntry, long groupId, long userId, ServiceContext serviceContext) {
		File[] files = fileEntry.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				String xmlString = convertFiletoString(file);
				DictCollection dicts = convertXMLToDictCollection(xmlString);
				ProcessUpdateDBUtils.processUpdateDictCollection(dicts, groupId, userId, serviceContext);
			}
		}
	}

	private static void processListFileService(File fileEntry, String folderParentPath, long groupId, long userId,
			ServiceContext serviceContext) {
		File[] files = fileEntry.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				String fileName = file.getName();
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					String filePath = file.getPath();
					String xmlString = convertFiletoString(file);
					ServiceInfo service = convertXMLToServiceInfo(xmlString);
					ProcessUpdateDBUtils.processUpdateServiceInfo(service, filePath, folderParentPath, groupId, userId,
							serviceContext);
				}
			}
		}
	}

	private static void processListFileTemplate(File fileEntry, String folderParentPath, long groupId, long userId,
			ServiceContext serviceContext) {
		File[] files = fileEntry.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				String fileName = file.getName();
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					String filePath = file.getPath();
					String xmlString = convertFiletoString(file);
					DossierTemplate template = convertXMLToDossierTemplate(xmlString);
					ProcessUpdateDBUtils.processUpdateDossierTemplate(template, filePath, folderParentPath, groupId, userId,
							serviceContext);
				}
			}
		}
	}

	private static void processListFileProcess(File fileEntry, long groupId, long userId,
			ServiceContext serviceContext) {
		File[] files = fileEntry.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				String fileName = file.getName();
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					String filePath = file.getPath();
					String xmlString = convertFiletoString(file);
					ServiceProcess process = convertXMLToServiceProcess(xmlString);
					ProcessUpdateDBUtils.processUpdateServiceProcess(process, filePath, groupId, userId, serviceContext);
				}
			}
		}
	}
	/** Process output object to DB - END */

	/** Process convert xml to Object - START */
	// LamTV_ Process convert xml to Object ActionConfig
	private static ActionConfigList convertXMLToActionConfig(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			ActionConfigList objectElement = (ActionConfigList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			_log.info(e);
		}

		return null;
	}
	// LamTV_ Process convert xml to Object StepConfig
	private static StepConfigList convertXMLToStepConfig(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			StepConfigList objectElement = (StepConfigList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object MenuConfig
	private static MenuConfigList convertXMLToMenuConfig(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			MenuConfigList objectElement = (MenuConfigList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object DocumentType
	private static DocumentTypeList convertXMLToDocumentType(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			DocumentTypeList objectElement = (DocumentTypeList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object DeliverableType
	private static DeliverableTypeList convertXMLToDeliverableType(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			DeliverableTypeList objectElement = (DeliverableTypeList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object PaymentConfig
	private static PaymentConfigList convertXMLToPaymentConfig(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			PaymentConfigList objectElement = (PaymentConfigList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object ServerConfig
	private static ServerConfigList convertXMLToServerConfig(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			ServerConfigList objectElement = (ServerConfigList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object NotificationTemplate
	private static NotificationTemplateList convertXMLToNotificationTemplate(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			NotificationTemplateList objectElement = (NotificationTemplateList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object ServiceInfo
	private static ServiceInfo convertXMLToServiceInfo(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			ServiceInfo objectElement = (ServiceInfo) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object DossierTemplate
	private static DossierTemplate convertXMLToDossierTemplate(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			DossierTemplate objectElement = (DossierTemplate) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object ServiceProcess
	private static ServiceProcess convertXMLToServiceProcess(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			ServiceProcess objectElement = (ServiceProcess) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	// LamTV_ Process convert xml to Object ServiceProcess
	private static DictCollection convertXMLToDictCollection(String xmlString) {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			DictCollection objectElement = (DictCollection) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** Process convert xml to Object - END */

}
