package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.RoleModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.v21.model.ActionConfigList;
import org.opencps.api.v21.model.ApplicantList;
import org.opencps.api.v21.model.BusinessList;
import org.opencps.api.v21.model.CitizenList;
import org.opencps.api.v21.model.DeliverableTypeList;
import org.opencps.api.v21.model.DictCollection;
import org.opencps.api.v21.model.DocumentTypeList;
import org.opencps.api.v21.model.DossierTemplate;
import org.opencps.api.v21.model.DynamicReportList;
import org.opencps.api.v21.model.HolidayList;
import org.opencps.api.v21.model.MenuConfigList;
import org.opencps.api.v21.model.NotificationTemplateList;
import org.opencps.api.v21.model.ObjectFactory;
import org.opencps.api.v21.model.PaymentConfigList;
import org.opencps.api.v21.model.ServerConfigList;
import org.opencps.api.v21.model.ServiceInfo;
import org.opencps.api.v21.model.ServiceProcess;
import org.opencps.api.v21.model.StepConfigList;
import org.opencps.api.v21.model.UserManagement;
import org.opencps.api.v21.model.UserManagement.Roles;
import org.opencps.api.v21.model.UserManagement.Users;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.model.PaymentConfig;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.api.v21.model.WorkingTimeList;
import org.opencps.api.v21.model.WorkingTimeList.WorkingTime;

public class ReadXMLFileUtils {

	private static Log _log = LogFactoryUtil.getLog(ReadXMLFileUtils.class);
	private static volatile String strError = StringPool.BLANK;

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
//			_log.info("XML to String using BufferedReader : ");
//			_log.info(xml2String);

			return xml2String;
		} catch (Exception e) {
			_log.error(e);
		} finally {
			try {
				if (bufReader != null)
					bufReader.close();
			} catch (IOException e1) {
				_log.debug(e1);
				//_log.error(e);
			}
		}
		return StringPool.BLANK;
	}

	//LamTV_Process delete list file of folder
	public static boolean deleteFilesForParentFolder(File fileList) {
		File[] filesParent = fileList.listFiles();
		if (filesParent != null && filesParent.length > 0) {
			for (File fileEntry : filesParent) {
				if (fileEntry.isDirectory()) {
					File[] files = fileEntry.listFiles();
					for (File file : files) {
						if (!file.delete()) {
							return false;
						}
					}
					if (!fileEntry.delete()) {
						return false;
					}
				} else {
					if (!fileEntry.delete()) {
						return false;
					}
				}
			}
			if (!fileList.delete()) {
				return false;
			}
		}
		return true;
	}

	//LamTV_Process get list file of folder
	public static String listFilesForParentFolder(File fileList, long groupId, long userId,
			ServiceContext serviceContext) throws Exception {
		StringBuilder strFileSucess = new StringBuilder();
		File[] files = fileList.listFiles();
		String folderParentPath = fileList.getPath();
		if (files != null && files.length > 0) {
			for (File fileEntry : files) {
				if (fileEntry.isDirectory()) {
					String strChidFile = listFilesForFolder(fileEntry, fileEntry.getPath(), folderParentPath, groupId, userId,
							serviceContext);
					//
					strFileSucess.append(strChidFile);
					strFileSucess.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					String fileName = fileEntry.getName();
					String subFileName = ImportZipFileUtils.getSubFileName(fileName);
					if (Validator.isNotNull(subFileName)) {
						String xmlString = convertFiletoString(fileEntry);
						String strParentFile = compareParentFile(folderParentPath, fileName, xmlString, groupId, userId, serviceContext);
						//
						strFileSucess.append(strParentFile);
						strFileSucess.append(ConstantUtils.HTML_NEW_LINE);
					}
				}
			}
		}
		_log.info("strFileSucess: "+strFileSucess.toString());
		return strFileSucess.toString();
	}

	private static String listFilesForFolder(File fileEntry, String folderPath, String folderParentPath, long groupId,
			long userId, ServiceContext serviceContext) throws Exception {
		_log.info("folderPath: "+folderPath);
		_log.info("folderParentPath: "+folderParentPath);
		String strFile = StringPool.BLANK;
		if (Validator.isNotNull(folderPath)) {
			int index = folderPath.lastIndexOf(StringPool.FORWARD_SLASH);
			String subFolder = folderPath.substring(index);
			_log.info("subFolder: "+subFolder);
			if (Validator.isNotNull(subFolder)) {
				switch (subFolder) {
				case ConstantUtils.SOURCE_DICTS:
					strFile = processListFileDict(fileEntry, groupId, userId, serviceContext);
					break;
				case ConstantUtils.SOURCE_SERVICES:
					strFile = processListFileService(fileEntry, folderParentPath, groupId, userId, serviceContext);
					break;
				case ConstantUtils.SOURCE_TEMPLATES:
					strFile =processListFileTemplate(fileEntry, folderParentPath, groupId, userId, serviceContext);
					break;
				case ConstantUtils.SOURCE_PROCESSES:
					strFile = processListFileProcess(fileEntry, groupId, userId, serviceContext);
					break;
				default:
					break;
				}
			}
		}
		return strFile;
	}

	public static String compareParentFile(String folderPath, String fileName, String xmlString, long groupId,
			long userId, ServiceContext serviceContext) throws PortalException, JAXBException {

		StringBuilder sbParentFile = new StringBuilder();
		boolean flag = false;
		if (Validator.isNotNull(fileName)) {
			switch (fileName) {
			case ConstantUtils.XML_ACTION_CONFIG:
				ActionConfigList actList = convertXMLToActionConfig(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateActionConfig(actList, folderPath, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE); 
				} else {
					strError = ConstantUtils.XML_ACTION_CONFIG;
				}
				break;
			case ConstantUtils.XML_STEP_CONFIG:
				StepConfigList stepList = convertXMLToStepConfig(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateStepConfig(stepList, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					strError = ConstantUtils.XML_STEP_CONFIG;
				}
				break;
			case ConstantUtils.XML_MENU_CONFIG:
				MenuConfigList menuList = convertXMLToMenuConfig(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateMenuConfig(menuList, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					strError = ConstantUtils.XML_MENU_CONFIG;
				}
				break;
			case ConstantUtils.XML_DOCUMENT_TYPE:
				DocumentTypeList docList = convertXMLToDocumentType(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateDocumentType(docList, folderPath, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					strError = ConstantUtils.XML_DOCUMENT_TYPE;
				}
				break;
			case ConstantUtils.XML_DELIVERABLE_TYPE:
				DeliverableTypeList deliTypeList = convertXMLToDeliverableType(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateDeliverableType(deliTypeList, folderPath, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					strError = ConstantUtils.XML_DELIVERABLE_TYPE;
				}
				break;
			case ConstantUtils.XML_PAYMENT_CONFIG:
				PaymentConfigList paymentList = convertXMLToPaymentConfig(xmlString);
				flag = ProcessUpdateDBUtils.processUpdatePaymentConfig(paymentList, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					strError = ConstantUtils.XML_PAYMENT_CONFIG;
				}
				break;
			case ConstantUtils.XML_SERVER_CONFIG:
				ServerConfigList serverList = convertXMLToServerConfig(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateServerConfig(serverList, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					strError = ConstantUtils.XML_SERVER_CONFIG;
				}
				break;
			case ConstantUtils.XML_NOTIFICATION_TEMPLATE:
				NotificationTemplateList notiTempList = convertXMLToNotificationTemplate(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateNotificationTemplate(notiTempList, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					strError = ConstantUtils.XML_NOTIFICATION_TEMPLATE;
				}
				break;
			case ConstantUtils.XML_USERS:
				UserManagement userManagement = convertXMLToUser(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateUser(userManagement, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE);
				} else {
					strError = ConstantUtils.XML_USERS;
				}
				break;
			case ConstantUtils.XML_DYNAMIC_REPORT:
				DynamicReportList reportList = convertXMLToDynamicReport(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateDynamicReport(reportList, folderPath, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE); 
				} else {
					strError = ConstantUtils.XML_DYNAMIC_REPORT;
				}
				break;
			case ConstantUtils.XML_HOLIDAY:
				HolidayList holidayList = convertXMLToHoliday(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateHoliday(holidayList, folderPath, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE); 
				} else {
					strError = ConstantUtils.XML_HOLIDAY;
				}
				break;
			case ConstantUtils.XML_APPLICANT:
				ApplicantList applicantList = convertXMLToApplicant(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateApplicant(applicantList, folderPath, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE); 
				} else {
					strError = ConstantUtils.XML_HOLIDAY;
				}
				break;
			case ConstantUtils.XML_WORKING_TIME:
				WorkingTimeList workingTimeList = convertXMLToWorkingTime(xmlString);
				flag = ProcessUpdateDBUtils.processUpdateWorkingTime(workingTimeList, folderPath, groupId, userId, serviceContext);
				if (flag) {
					sbParentFile.append(fileName);
					sbParentFile.append(ConstantUtils.HTML_NEW_LINE); 
				} else {
					strError = ConstantUtils.XML_HOLIDAY;
				}
				break;
			default:
				break;
			}
		}
		return sbParentFile.toString();
	}

	/** Process output object to DB - START 
	 * @throws NoSuchUserException 
	 * @throws JAXBException */
	private static String processListFileDict(File fileEntry, long groupId, long userId, ServiceContext serviceContext)
			throws NoSuchUserException, JAXBException {
		StringBuilder sbDictFile = new StringBuilder();
		File[] files = fileEntry.listFiles();
		if (files != null && files.length > 0) {
			sbDictFile.append(fileEntry.getName());
			sbDictFile.append(ConstantUtils.HTML_NEW_LINE);
			for (File file : files) {
				String fileName = file.getName();
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					String xmlString = convertFiletoString(file);
					DictCollection dicts = convertXMLToDictCollection(xmlString);
					boolean flagDict = ProcessUpdateDBUtils.processUpdateDictCollection(dicts, groupId, userId, serviceContext);
					if (flagDict) {
						//Append file success
						sbDictFile.append(ConstantUtils.HTML_FOUR_SPACE);
						sbDictFile.append(file.getName());
						sbDictFile.append(ConstantUtils.HTML_NEW_LINE);
					} else {
						strError = fileEntry.getName() + StringPool.SLASH + fileName;
					}
					
				}
			}
		}
		return sbDictFile.toString();
	}

	private static String processListFileService(File fileEntry, String folderParentPath, long groupId, long userId,
			ServiceContext serviceContext) throws Exception {
		StringBuilder sbServiceFile = new StringBuilder();
		File[] files = fileEntry.listFiles();
		if (files != null && files.length > 0) {
			sbServiceFile.append(fileEntry.getName());
			sbServiceFile.append(ConstantUtils.HTML_NEW_LINE);
			for (File file : files) {
				String fileName = file.getName();
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					String filePath = file.getPath();
					String xmlString = convertFiletoString(file);
					ServiceInfo service = convertXMLToServiceInfo(xmlString);
					boolean flag = ProcessUpdateDBUtils.processUpdateServiceInfo(service, filePath, folderParentPath, groupId, userId,
							serviceContext);
					if (flag) {
						//Append file success
						sbServiceFile.append(ConstantUtils.HTML_FOUR_SPACE);
						sbServiceFile.append(file.getName());
						sbServiceFile.append(ConstantUtils.HTML_NEW_LINE);
					} else {
						strError = fileEntry.getName() + StringPool.SLASH + fileName;
					}
				}
			}
		}
		return sbServiceFile.toString();
	}

	private static String processListFileTemplate(File fileEntry, String folderParentPath, long groupId, long userId,
			ServiceContext serviceContext) throws PortalException, JAXBException {
		StringBuilder sbTemplateFile = new StringBuilder();
		File[] files = fileEntry.listFiles();
		if (files != null && files.length > 0) {
			sbTemplateFile.append(fileEntry.getName());
			sbTemplateFile.append(ConstantUtils.HTML_NEW_LINE);
			for (File file : files) {
				String fileName = file.getName();
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					String filePath = file.getPath();
					String xmlString = convertFiletoString(file);
					DossierTemplate template = convertXMLToDossierTemplate(xmlString);
					boolean flag = ProcessUpdateDBUtils.processUpdateDossierTemplate(template, filePath, folderParentPath, groupId, userId,
							serviceContext);
					if (flag) {
						//Append file success
						sbTemplateFile.append(ConstantUtils.HTML_FOUR_SPACE);
						sbTemplateFile.append(file.getName());
						sbTemplateFile.append(ConstantUtils.HTML_NEW_LINE);
					} else {
						strError = fileEntry.getName() + StringPool.SLASH + fileName;
					}
				}
			}
		}
		return sbTemplateFile.toString();
	}

	private static String processListFileProcess(File fileEntry, long groupId, long userId,
			ServiceContext serviceContext) throws PortalException, JAXBException {
		StringBuilder sbProcessFile = new StringBuilder();
		File[] files = fileEntry.listFiles();
		if (files != null && files.length > 0) {
			sbProcessFile.append(fileEntry.getName());
			sbProcessFile.append(ConstantUtils.HTML_NEW_LINE);
			for (File file : files) {
				String fileName = file.getName();
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					String filePath = file.getPath();
					String xmlString = convertFiletoString(file);
					ServiceProcess process = convertXMLToServiceProcess(xmlString);
					boolean flag = ProcessUpdateDBUtils.processUpdateServiceProcess(process, filePath, groupId, userId, serviceContext);
					if (flag) {
						//Append file success
						sbProcessFile.append(ConstantUtils.HTML_FOUR_SPACE);
						sbProcessFile.append(file.getName());
						sbProcessFile.append(ConstantUtils.HTML_NEW_LINE);
					} else {
						strError = fileEntry.getName() + StringPool.SLASH + fileName;
					}
				}
			}
		}
		return sbProcessFile.toString();
	}
	/** Process output object to DB - END */

	/** Process convert xml to Object - START 
	 * @throws JAXBException */
	// LamTV_ Process convert xml to Object ActionConfig
	private static ActionConfigList convertXMLToActionConfig(String xmlString) throws JAXBException {

			JAXBContext jaxbContext = null;
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			ActionConfigList objectElement = (ActionConfigList) jaxbUnmarshaller.unmarshal(reader);

			return objectElement;
	}
	// LamTV_ Process convert xml to Object StepConfig
	private static StepConfigList convertXMLToStepConfig(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			StepConfigList objectElement = (StepConfigList) jaxbUnmarshaller.unmarshal(reader);
			return objectElement;
	}

	// LamTV_ Process convert xml to Object MenuConfig
	private static MenuConfigList convertXMLToMenuConfig(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		MenuConfigList objectElement = (MenuConfigList) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object DocumentType
	private static DocumentTypeList convertXMLToDocumentType(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		DocumentTypeList objectElement = (DocumentTypeList) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object DeliverableType
	private static DeliverableTypeList convertXMLToDeliverableType(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		DeliverableTypeList objectElement = (DeliverableTypeList) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object PaymentConfig
	private static PaymentConfigList convertXMLToPaymentConfig(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		PaymentConfigList objectElement = (PaymentConfigList) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object ServerConfig
	private static ServerConfigList convertXMLToServerConfig(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		ServerConfigList objectElement = (ServerConfigList) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object NotificationTemplate
	private static NotificationTemplateList convertXMLToNotificationTemplate(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		NotificationTemplateList objectElement = (NotificationTemplateList) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object User
	private static UserManagement convertXMLToUser(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		UserManagement objectElement = (UserManagement) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object DynamicReport
	private static DynamicReportList convertXMLToDynamicReport(String xmlString) throws JAXBException {

			JAXBContext jaxbContext = null;
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			DynamicReportList objectElement = (DynamicReportList) jaxbUnmarshaller.unmarshal(reader);

			return objectElement;
	}

	// LamTV_ Process convert xml to Object DynamicReport
	private static HolidayList convertXMLToHoliday(String xmlString) throws JAXBException {

			JAXBContext jaxbContext = null;
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			HolidayList objectElement = (HolidayList) jaxbUnmarshaller.unmarshal(reader);

			return objectElement;
	}

	// LamTV_ Process convert xml to Object WorkingTime
	private static WorkingTimeList convertXMLToWorkingTime(String xmlString) throws JAXBException {

			JAXBContext jaxbContext = null;
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			WorkingTimeList objectElement = (WorkingTimeList) jaxbUnmarshaller.unmarshal(reader);

			return objectElement;
	}

	// LamTV_ Process convert xml to Object Applicant
	private static ApplicantList convertXMLToApplicant(String xmlString) throws JAXBException {

			JAXBContext jaxbContext = null;
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			ApplicantList objectElement = (ApplicantList) jaxbUnmarshaller.unmarshal(reader);

			return objectElement;
	}

	// LamTV_ Process convert xml to Object ServiceInfo
	private static ServiceInfo convertXMLToServiceInfo(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		ServiceInfo objectElement = (ServiceInfo) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object DossierTemplate
	private static DossierTemplate convertXMLToDossierTemplate(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		DossierTemplate objectElement = (DossierTemplate) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object ServiceProcess
	private static ServiceProcess convertXMLToServiceProcess(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		ServiceProcess objectElement = (ServiceProcess) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;
	}

	// LamTV_ Process convert xml to Object ServiceProcess
	private static DictCollection convertXMLToDictCollection(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = null;

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		DictCollection objectElement = (DictCollection) jaxbUnmarshaller.unmarshal(reader);
		return objectElement;

	}
	/** Process convert xml to Object - END  */

	/** Process Convert Object to xml - START **/
	// LamTV_ Process convert xml to Object ServiceProcess
	public static File convertCitizenToXML(CitizenList citizenList) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(CitizenList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File
		File file = new File("Citizen.xml");

		// Writes XML file to file-system
		jaxbMarshaller.marshal(citizenList, file);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return file;
	}

	// LamTV_ Process convert xml to Object ServiceProcess
	public static File convertBusinessToXML(BusinessList businessList) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(BusinessList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File
		File file = new File("Business.xml");

		// Writes XML file to file-system
		jaxbMarshaller.marshal(businessList, file);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return file;
	}

	// LamTV_ Process convert xml to Object ServiceProcess
	public static File convertDictCollectionToXML(DictCollection dictCollection, String collectionCode) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(DictCollection.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File
		File file = new File(collectionCode + ConstantUtils.EXTENTION_XML);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(dictCollection, file);
		//out log in server
		//jaxbMarshaller.marshal(dictCollection, System.out);

		return file;
	}

	// LamTV_ Process convert xml to Object ServiceProcess
	public static void convertServiceInfoToXML(ServiceInfo serviceInfo, String serviceCode, String pathFolder)
			throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(ServiceInfo.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File
		File file = new File(pathFolder + StringPool.FORWARD_SLASH + serviceCode + ConstantUtils.EXTENTION_XML);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(serviceInfo, file);
		//out log in server
		//jaxbMarshaller.marshal(dictCollection, System.out);
	}

	// LamTV_ Process convert xml to Object ActionConfig
	public static File convertActionConfigToXML(ActionConfigList actConfigList) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(ActionConfigList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File
		File file = new File("ActionConfig.xml");

		// Writes XML file to file-system
		jaxbMarshaller.marshal(actConfigList, file);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return file;
	}

	// LamTV_ Process convert xml to Object StepConfig
	public static File convertStepConfigToXML(StepConfigList stepConfigList) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(StepConfigList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File
		File file = new File("StepConfig.xml");

		// Writes XML file to file-system
		jaxbMarshaller.marshal(stepConfigList, file);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return file;
	}

	// LamTV_ Process convert xml to Object StepConfig
	public static File convertMenuConfigToXML(MenuConfigList meuConfigList) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(MenuConfigList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File
		File file = new File("MenuConfig.xml");

		// Writes XML file to file-system
		jaxbMarshaller.marshal(meuConfigList, file);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return file;
	}

	/** Process Convert Object to xml - END **/

	//Process validate xml
	public static String validateXML(File xmlFile, boolean flagList) {

		if (flagList) {
			File[] files = xmlFile.listFiles();
			if (files != null && files.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (File fileEntry : files) {
					if (fileEntry.isDirectory()) {
						String folderPath = fileEntry.getPath();
						int index = folderPath.lastIndexOf(StringPool.FORWARD_SLASH);
						String subFolder = folderPath.substring(index + 1);
						if (ConstantUtils.SOURCE_VALIDATE.contains(subFolder)) {
							File[] fileChid = fileEntry.listFiles();
							for (File file : fileChid) {
								String fileName = file.getName();
								String subFileName = ImportZipFileUtils.getSubFileName(fileName);
								if (Validator.isNotNull(subFileName)) {
									try {
									DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
									builderFactory.setNamespaceAware(true);

									DocumentBuilder parser = builderFactory.newDocumentBuilder();
									parser.parse(file);
									} catch (Exception e) {
										_log.debug(e);
										//_log.error(e);
										sb.append(subFolder);
										sb.append(StringPool.SLASH);
										sb.append(xmlFile.getName());
										sb.append(StringPool.COLON + StringPool.SPACE);
										sb.append(e.getMessage());
										sb.append(ConstantUtils.HTML_NEW_LINE);
									}
								}
							}
						}
					} else {
						String fileName = fileEntry.getName();
						String subFileName = ImportZipFileUtils.getSubFileName(fileName);
						if (Validator.isNotNull(subFileName)) {
							try {
								DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
								builderFactory.setNamespaceAware(true);
	
								DocumentBuilder parser = builderFactory.newDocumentBuilder();
								parser.parse(fileEntry);
							} catch (Exception e) {
								_log.debug(e);
								//_log.error(e);
								sb.append(fileName);
								sb.append(StringPool.COLON + StringPool.SPACE);
								sb.append(e.getMessage());
								sb.append(ConstantUtils.HTML_NEW_LINE);
							}
						}
					}
				}
				return sb.toString();
			}
		} else {
			// parse an XML document into a DOM tree
			String strError = StringPool.BLANK;
			try {
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				builderFactory.setNamespaceAware(true);

				DocumentBuilder parser = builderFactory.newDocumentBuilder();
				parser.parse(xmlFile);
			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
				strError = xmlFile.getName() + StringPool.COLON + e.getMessage(); 
			}
			return strError;
		}

		return StringPool.BLANK;
	}

	public static String getStrError() {
		return strError;
	}

	public static void setStrError(String strError) {
		ReadXMLFileUtils.strError = strError;
	}
	
	public static ByteArrayOutputStream exportActionConfigToXMLStream(long groupId) throws JAXBException {
		List<ActionConfig> actConfigList = ActionConfigLocalServiceUtil.getByGroupId(groupId);
		ByteArrayOutputStream actionConfigXMLStream = new ByteArrayOutputStream();
		
		if (actConfigList != null && actConfigList.size() > 0) {
			ActionConfigList configList = new ActionConfigList();
			for (ActionConfig act : actConfigList) {
				org.opencps.api.v21.model.ActionConfigList.ActionConfig config = new org.opencps.api.v21.model.ActionConfigList.ActionConfig();
				if (Validator.isNotNull(act.getActionCode())) {
					config.setActionCode(act.getActionCode());
					config.setActionName(act.getActionName());
					config.setExtraForm(act.getExtraForm());
					config.setFormConfig(act.getFormConfig());
					config.setSampleData(act.getSampleData());
					config.setInsideProcess(act.getInsideProcess());
					config.setUserNote(act.getUserNote());
					config.setSyncType(act.getSyncType());
					config.setEventType(act.getEventType());
					config.setInfoType(act.getInfoType());
					config.setRollbackable(act.getRollbackable());
					config.setNotificationType(act.getNotificationType());
					config.setDocumentType(act.getDocumentType());
					config.setMappingAction(act.getMappingAction());
					config.setDateOption(act.getDateOption());
					//
					configList.getActionConfig().add(config);
				}
			}
			actionConfigXMLStream = ReadXMLFileUtils.convertActionConfigToXMLStream(configList);
		}	
		
		return actionConfigXMLStream;
	}
	public static ByteArrayOutputStream convertActionConfigToXMLStream(ActionConfigList actConfigList) throws JAXBException {
		ByteArrayOutputStream actionConfigXMLStream = new ByteArrayOutputStream();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ActionConfigList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(actConfigList, actionConfigXMLStream);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return actionConfigXMLStream;
	}
	
	public static ByteArrayOutputStream convertStepConfigToXMLStream(StepConfigList stepConfigList) throws JAXBException {
		ByteArrayOutputStream stepConfigXMLStream = new ByteArrayOutputStream();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(StepConfigList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(stepConfigList, stepConfigXMLStream);

		return stepConfigXMLStream;
	}

	// LamTV_ Process convert xml to Object StepConfig
	public static ByteArrayOutputStream convertMenuConfigToXMLStream(MenuConfigList meuConfigList) throws JAXBException {
		ByteArrayOutputStream menuConfigXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(MenuConfigList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(meuConfigList, menuConfigXMLStream);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return menuConfigXMLStream;
	}
	
	public static ByteArrayOutputStream convertDocumentTypeToXMLStream(DocumentTypeList documentTypeList) throws JAXBException {
		ByteArrayOutputStream documentTypeConfigXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(DocumentTypeList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(documentTypeList, documentTypeConfigXMLStream);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return documentTypeConfigXMLStream;
	}

	public static ByteArrayOutputStream convertDeliverableTypeToXMLStream(DeliverableTypeList deliverableTypeList) throws JAXBException {
		ByteArrayOutputStream deliverableTypeConfigXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(DeliverableTypeList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(deliverableTypeList, deliverableTypeConfigXMLStream);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return deliverableTypeConfigXMLStream;
	}
	
	public static ByteArrayOutputStream convertPaymentConfigToXMLStream(PaymentConfigList paymentConfigList) throws JAXBException {
		ByteArrayOutputStream paymentConfigXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(PaymentConfigList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(paymentConfigList, paymentConfigXMLStream);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return paymentConfigXMLStream;
	}

	public static ByteArrayOutputStream convertServerConfigToXMLStream(ServerConfigList serverConfigList) throws JAXBException {
		ByteArrayOutputStream serverConfigXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(ServerConfigList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(serverConfigList, serverConfigXMLStream);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return serverConfigXMLStream;
	}
	
	public static ByteArrayOutputStream convertNotificationTemplateToXMLStream(NotificationTemplateList notificationTemplateList) throws JAXBException {
		ByteArrayOutputStream notificationTemplateXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(NotificationTemplateList.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Writes XML file to file-system
		jaxbMarshaller.marshal(notificationTemplateList, notificationTemplateXMLStream);
		//jaxbMarshaller.marshal(citizenList, System.out);

		return notificationTemplateXMLStream;
	}

	public static ByteArrayOutputStream convertServiceInfoToXMLStream(ServiceInfo serviceInfo)
			throws JAXBException {
		ByteArrayOutputStream serviceInfoXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(ServiceInfo.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File

		jaxbMarshaller.marshal(serviceInfo, serviceInfoXMLStream);
		//out log in server
		//jaxbMarshaller.marshal(dictCollection, System.out);
		return serviceInfoXMLStream;
	}

	public static ByteArrayOutputStream convertDictCollectionToXMLStream(DictCollection dictCollection) throws JAXBException {
		ByteArrayOutputStream dictCollectionXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(DictCollection.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File

		// Writes XML file to file-system
		jaxbMarshaller.marshal(dictCollection, dictCollectionXMLStream);
		//out log in server
		//jaxbMarshaller.marshal(dictCollection, System.out);

		return dictCollectionXMLStream;
	}
	
	public static ByteArrayOutputStream convertDossierTemplateToXMLStream(DossierTemplate dossierTemplate)
			throws JAXBException {
		ByteArrayOutputStream dossierTemplateXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(DossierTemplate.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File

		jaxbMarshaller.marshal(dossierTemplate, dossierTemplateXMLStream);
		//out log in server
		//jaxbMarshaller.marshal(dictCollection, System.out);
		return dossierTemplateXMLStream;
	}
	
	public static ByteArrayOutputStream convertServiceProcessToXMLStream(ServiceProcess serviceProcess)
			throws JAXBException {
		ByteArrayOutputStream serviceProcessXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(ServiceProcess.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File

		jaxbMarshaller.marshal(serviceProcess, serviceProcessXMLStream);
		//out log in server
		//jaxbMarshaller.marshal(dictCollection, System.out);
		return serviceProcessXMLStream;
	}

	public static ByteArrayOutputStream convertUserManagementToXMLStream(UserManagement userManagement)
			throws JAXBException {
		ByteArrayOutputStream userManagementXMLStream = new ByteArrayOutputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(UserManagement.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//for pretty-print XML in JAXB
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//Store XML to File

		jaxbMarshaller.marshal(userManagement, userManagementXMLStream);
		//out log in server
		//jaxbMarshaller.marshal(dictCollection, System.out);
		return userManagementXMLStream;
	}
	
	public static ByteArrayOutputStream exportStepConfigToXMLStream(long groupId) throws JAXBException {
		ByteArrayOutputStream stepConfigXMLStream = new ByteArrayOutputStream();
		
		List<StepConfig> stepConfigList = StepConfigLocalServiceUtil.getStepByGroupId(groupId);
		if (stepConfigList != null && stepConfigList.size() > 0) {
			StepConfigList configList = new StepConfigList();
			for (StepConfig step : stepConfigList) {
				org.opencps.api.v21.model.StepConfigList.StepConfig config = new org.opencps.api.v21.model.StepConfigList.StepConfig();
				if (Validator.isNotNull(step.getStepCode())) {
					config.setStepCode(step.getStepCode());
					config.setStepName(step.getStepName());
					config.setStepType(step.getStepType());
					config.setDossierStatus(step.getDossierStatus());
					config.setDossierSubStatus(step.getDossierSubStatus());
					config.setMenuGroup(step.getMenuGroup());
					config.setMenuStepName(step.getMenuStepName());
					config.setButtonConfig(step.getButtonConfig());
					//
					configList.getStepConfig().add(config);
				}
				stepConfigXMLStream = ReadXMLFileUtils.convertStepConfigToXMLStream(configList);
			}
		}	
		
		return stepConfigXMLStream;
	}	

	public static ByteArrayOutputStream exportMenuConfigToXMLStream(long groupId) throws JAXBException {
		ByteArrayOutputStream menuConfigXMLStream = new ByteArrayOutputStream();
		List<MenuConfig> menuConfigList = MenuConfigLocalServiceUtil.getByGroupId(groupId);
		if (menuConfigList != null && menuConfigList.size() > 0) {
			MenuConfigList configList = new MenuConfigList();
			for (MenuConfig menu : menuConfigList) {
				org.opencps.api.v21.model.MenuConfigList.MenuConfig config = new org.opencps.api.v21.model.MenuConfigList.MenuConfig();
				if (Validator.isNotNull(menu.getMenuGroup())) {
					config.setMenuGroup(menu.getMenuGroup());
					config.setMenuName(menu.getMenuName());
					config.setOrder(menu.getOrder());
					config.setMenuType(menu.getMenuType());
					config.setQueryParams(menu.getQueryParams());
					config.setTableConfig(menu.getTableConfig());
					config.setButtonConfig(menu.getButtonConfig());
					//
					long menuConfigId = menu.getMenuConfigId();
					if (menuConfigId > 0) {
						List<MenuRole> roleList = MenuRoleLocalServiceUtil.getByMenuConfig(menuConfigId);
						if (roleList != null && roleList.size() > 0) {
							long[] roleArr = new long[roleList.size()];
							for (int i = 0; i < roleList.size(); i++) {
								roleArr[i] = roleList.get(i).getRoleId();
							}
							//
							List<JobPos> jobPosList = JobPosLocalServiceUtil.findByF_mappingRoleIds(groupId, roleArr);
							StringBuilder sb = new StringBuilder();
							if (jobPosList != null && jobPosList.size() > 0) {
								for (int i = 0; i < jobPosList.size(); i++) {
									if (i == 0) {
										sb.append(jobPosList.get(i).getJobPosCode());
									} else {
										sb.append(StringPool.COMMA);
										sb.append(jobPosList.get(i).getJobPosCode());
									}
								}
							}
							config.setRoles(sb.toString());
						}
					}
					
					//
					configList.getMenuConfig().add(config);
				}
				menuConfigXMLStream = ReadXMLFileUtils.convertMenuConfigToXMLStream(configList);
			}	
		}
		
		return menuConfigXMLStream;
	}
	
	public static ByteArrayOutputStream exportDocumentTypeToXMLStream(long groupId) throws JAXBException {
		ByteArrayOutputStream documentTypeXMLStream = new ByteArrayOutputStream();
		
		List<DocumentType> documentTypeList = DocumentTypeLocalServiceUtil.findByG(groupId);
		
		if (documentTypeList != null && documentTypeList.size() > 0) {
			DocumentTypeList typeList = new DocumentTypeList();
			for (DocumentType type : documentTypeList) {
				org.opencps.api.v21.model.DocumentTypeList.DocumentType documentType = new org.opencps.api.v21.model.DocumentTypeList.DocumentType();
				documentType.setCodePattern(type.getCodePattern());
				documentType.setDocSync(type.getDocSync());
				documentType.setDocumentName(type.getDocumentName());
				documentType.setTemplateClass(type.getTemplateClass());
				documentType.setTypeCode(type.getTypeCode());
				
				typeList.getDocumentType().add(documentType);
			}
			documentTypeXMLStream = ReadXMLFileUtils.convertDocumentTypeToXMLStream(typeList);
		}	
		
		return documentTypeXMLStream;
	}
	
	public static ByteArrayOutputStream exportDeliverableTypeToXMLStream(long groupId) throws JAXBException {
		ByteArrayOutputStream deliverableTypeXMLStream = new ByteArrayOutputStream();
		
		List<DeliverableType> deliverableTypeList = DeliverableTypeLocalServiceUtil.findByG(groupId);
		
		if (deliverableTypeList != null && deliverableTypeList.size() > 0) {
			DeliverableTypeList typeList = new DeliverableTypeList();
			for (DeliverableType type : deliverableTypeList) {
				org.opencps.api.v21.model.DeliverableTypeList.DeliverableType deliverableType = new org.opencps.api.v21.model.DeliverableTypeList.DeliverableType();
				deliverableType.setCodePattern(type.getCodePattern());
				deliverableType.setCounter((int)type.getCounter());
				deliverableType.setDataConfig(type.getDataConfig());
				deliverableType.setDocSync(type.getDocSync());
				deliverableType.setFormReport(type.getFormReport());
				deliverableType.setFormReportFileId((int)type.getFormReportFileId());
				deliverableType.setFormScript(type.getFormScript());
				deliverableType.setFormScriptFileId((int)type.getFormScriptFileId());
				deliverableType.setGovAgencies(type.getGovAgencies());
				deliverableType.setMappingData(type.getMappingData());
				deliverableType.setTableConfig(type.getTableConfig());
				deliverableType.setTypeCode(type.getTypeCode());
				deliverableType.setTypeName(type.getTypeName());
				
				typeList.getDeliverableType().add(deliverableType);
			}
			deliverableTypeXMLStream = ReadXMLFileUtils.convertDeliverableTypeToXMLStream(typeList);
		}	
		
		return deliverableTypeXMLStream;
	}	
	
	public static ByteArrayOutputStream exportPaymentConfigToXMLStream(long groupId) throws JAXBException {
		ByteArrayOutputStream paymentConfigXMLStream = new ByteArrayOutputStream();
		
		List<PaymentConfig> paymentConfigList = PaymentConfigLocalServiceUtil.findByG(groupId);
		
		if (paymentConfigList != null && paymentConfigList.size() > 0) {
			PaymentConfigList typeList = new PaymentConfigList();
			for (PaymentConfig type : paymentConfigList) {
				org.opencps.api.v21.model.PaymentConfigList.PaymentConfig paymentConfig = new org.opencps.api.v21.model.PaymentConfigList.PaymentConfig();
				paymentConfig.setBankInfo(type.getBankInfo());
				paymentConfig.setEpaymentConfig(type.getEpaymentConfig());
				paymentConfig.setGovAgencyCode(type.getGovAgencyCode());
				paymentConfig.setGovAgencyName(type.getGovAgencyName());
				paymentConfig.setGovAgencyTaxNo(type.getGovAgencyTaxNo());
				paymentConfig.setInvoiceIssueNo(type.getInvoiceIssueNo());
				paymentConfig.setInvoiceLastNo(type.getInvoiceLastNo());
				paymentConfig.setInvoiceTemplateNo(type.getInvoiceTemplateNo());
				typeList.getPaymentConfig().add(paymentConfig);
			}
			paymentConfigXMLStream = ReadXMLFileUtils.convertPaymentConfigToXMLStream(typeList);
		}	
		
		return paymentConfigXMLStream;
	}	
	
	public static ByteArrayOutputStream exportServerConfigToXMLStream(long groupId) throws JAXBException {
		ByteArrayOutputStream serverConfigXMLStream = new ByteArrayOutputStream();
		
		List<ServerConfig> serverConfigList = ServerConfigLocalServiceUtil.getGroupId(groupId);
		
		if (serverConfigList != null && serverConfigList.size() > 0) {
			ServerConfigList typeList = new ServerConfigList();
			for (ServerConfig type : serverConfigList) {
				org.opencps.api.v21.model.ServerConfigList.ServerConfig serverConfig = new org.opencps.api.v21.model.ServerConfigList.ServerConfig();
				serverConfig.setConfigs(type.getConfigs());
				serverConfig.setGovAgencyCode(type.getGovAgencyCode());
				serverConfig.setProtocol(type.getProtocol());
				serverConfig.setServerName(type.getServerName());
				serverConfig.setServerNo(type.getServerNo());
				
				typeList.getServerConfig().add(serverConfig);
			}
			serverConfigXMLStream = ReadXMLFileUtils.convertServerConfigToXMLStream(typeList);
		}	
		
		return serverConfigXMLStream;
	}	
	
	public static ByteArrayOutputStream exportNotificationTemplateToXMLStream(long groupId) throws JAXBException {
		ByteArrayOutputStream notificationTemplateXMLStream = new ByteArrayOutputStream();
		
		List<Notificationtemplate> notiTemplateList = NotificationtemplateLocalServiceUtil.findByF_NotificationtemplateByGroup(groupId);
		
		if (notiTemplateList != null && notiTemplateList.size() > 0) {
			NotificationTemplateList typeList = new NotificationTemplateList();
			for (Notificationtemplate type : notiTemplateList) {
				org.opencps.api.v21.model.NotificationTemplateList.NotificationTemplate notiConfig = new org.opencps.api.v21.model.NotificationTemplateList.NotificationTemplate();
				notiConfig.setEmailBody(type.getEmailBody());
				notiConfig.setEmailSubject(type.getEmailSubject());
				notiConfig.setExpireDuration(type.getExpireDuration());
				notiConfig.setInterval(type.getInterval());
				notiConfig.setNotificationType(type.getNotificationType());
				notiConfig.setSendEmail(type.getSendEmail());
				notiConfig.setSendSMS(type.getSendSMS());
				notiConfig.setTextMessage(type.getTextMessage());
				typeList.getNotificationTemplate().add(notiConfig);
			}
			notificationTemplateXMLStream = ReadXMLFileUtils.convertNotificationTemplateToXMLStream(typeList);
		}	
		
		return notificationTemplateXMLStream;
	}	
	
	public static ByteArrayOutputStream exportUserManagementToXMLStream(long groupId) throws JAXBException {
		ByteArrayOutputStream userManagementXMLStream = new ByteArrayOutputStream();
		
		List<Employee> employeeList = EmployeeLocalServiceUtil.findByG(groupId);
		
		if (employeeList != null && employeeList.size() > 0) {
			UserManagement userManagement = new UserManagement();
			Users users = new Users();
			for (Employee employee : employeeList) {
				if (Validator.isNotNull(employee.getMappingUserId())) {
					org.opencps.api.v21.model.UserManagement.Users.Employee emp = new org.opencps.api.v21.model.UserManagement.Users.Employee();
					emp.setEmployeeNo(employee.getEmployeeNo());
					emp.setFullname(employee.getFullName());
					emp.setTitle(employee.getTitle());
					emp.setGender(employee.getGender());
					emp.setBirthdate(String.valueOf(employee.getBirthdate()));
					emp.setTelNo(employee.getTelNo());
					emp.setEmail(employee.getEmail());
					emp.setWorkingStatus(employee.getWorkingStatus());
					JobPos mainJob = JobPosLocalServiceUtil.fetchJobPos(employee.getMainJobPostId());
					if (mainJob != null) {
						emp.setJobTitle(mainJob.getTitle());
					}
					StringBuilder roles = new StringBuilder();
					List<EmployeeJobPos> lstEmps = EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(employee.getEmployeeId());
					for (EmployeeJobPos ejp : lstEmps) {
						JobPos jp = JobPosLocalServiceUtil.fetchJobPos(ejp.getJobPostId());
						if (jp != null) {
							if (roles.length() == 0) {
								roles.append(jp.getJobPosCode());
							}
							else {
								roles.append(",");
								roles.append(jp.getJobPosCode());
							}
						}
					}
					emp.setRoles(roles.toString());
					
					users.getEmployee().add(emp);
				}
			}
			userManagement.setUsers(users);
			List<JobPos> lstJobs = JobPosLocalServiceUtil.findByG(groupId);
			Roles roles = new Roles();
			for (JobPos jp : lstJobs) {
				org.opencps.api.v21.model.UserManagement.Roles.JobPos jobPos = new org.opencps.api.v21.model.UserManagement.Roles.JobPos();
				jobPos.setCode(jp.getJobPosCode());
				jobPos.setTitle(jp.getTitle());
				jobPos.setDescription(jp.getDescription());
				
				roles.getJobPos().add(jobPos);
			}
			
			userManagement.setRoles(roles);
			
			userManagementXMLStream = ReadXMLFileUtils.convertUserManagementToXMLStream(userManagement);
		}	
		
		return userManagementXMLStream;
	}		
}
