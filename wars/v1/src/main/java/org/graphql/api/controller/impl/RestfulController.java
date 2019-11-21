package org.graphql.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.graphql.api.controller.utils.CaptchaServiceSingleton;
import org.graphql.api.controller.utils.CheckFileUtils;
import org.graphql.api.controller.utils.WebKeys;
import org.graphql.api.errors.OpenCPSNotFoundException;
import org.graphql.api.model.FileTemplateMiniItem;
import org.graphql.api.model.UsersUserItem;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.FileAttachLocalServiceUtil;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;
import org.opencps.usermgt.action.impl.UserActions;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import backend.admin.config.whiteboard.BundleLoader;
import backend.utils.FileUploadUtils;
import io.swagger.annotations.ApiParam;

/**
 * Rest Controller
 *
 * @author binhth
 */
@RestController
public class RestfulController {

	@RequestMapping(value = "/user/{id}/deactive", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void deactiveAccount(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") long id,
			@RequestBody String body) {

		try {

			JSONObject bodyData = JSONFactoryUtil.createJSONObject(body);

			User user = UserLocalServiceUtil.getUser(id);

			boolean locked = bodyData.getBoolean(ConstantUtils.LOCKED);

			if (locked) {
				user.setStatus(WorkflowConstants.STATUS_INACTIVE);
			} else {
				user.setStatus(WorkflowConstants.STATUS_APPROVED);
			}

			UserLocalServiceUtil.updateUser(user);

			Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

			indexer.reindex(user);

		} catch (Exception e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/user/{id}/changepass", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void changePassWordUser(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") long id, @RequestBody String body) {

		try {

			JSONObject bodyData = JSONFactoryUtil.createJSONObject(body);

			String password = bodyData.getString(DossierTerm.SECRET);

			User user = UserLocalServiceUtil.updatePassword(id, password, password, Boolean.FALSE);

			Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

			indexer.reindex(user);

		} catch (Exception e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getUserId(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") long id) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put(ConstantUtils.VALUE_EMAIL, StringPool.BLANK);
		result.put(ConstantUtils.VALUE_SCREEN_NAME, StringPool.BLANK);
		result.put(ConstantUtils.DEACTIVE_ACCOUNT, 0);

		try {

			User user = UserLocalServiceUtil.fetchUser(id);

			result.put(ConstantUtils.VALUE_EMAIL, user.getEmailAddress());
			result.put(ConstantUtils.VALUE_SCREEN_NAME, user.getScreenName());
			result.put(ConstantUtils.DEACTIVE_ACCOUNT, user.getStatus());

		} catch (Exception e) {
			_log.debug(e);
		}

		return result.toJSONString();
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getUserLoginInfo(HttpServletRequest request, HttpServletResponse response) {

		JSONArray dataUser = JSONFactoryUtil.createJSONArray();

		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put(ConstantUtils.VALUE_EMAIL, StringPool.BLANK);
		result.put(ConstantUtils.VALUE_ROLE, StringPool.BLANK);
		result.put(ConstantUtils.DEACTIVE_ACCOUNT, 0);
		try {

			long userId = 0;
			if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
				userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());

				User user = UserLocalServiceUtil.fetchUser(userId);

				List<Role> roles = user.getRoles();

				String roleName = StringPool.BLANK;

				for (Role role : roles) {

					if (ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN).equals(role.getName())) {
						roleName = ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN);
						break;
					}

					if (ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN_DATA).equals(role.getName())) {
						roleName = ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN_DATA);
						break;
					}

				}

				result.put(ConstantUtils.VALUE_EMAIL, user.getEmailAddress());
				result.put(ConstantUtils.VALUE_ROLE, roleName);
				result.put(ConstantUtils.DEACTIVE_ACCOUNT, user.getStatus());

			}

		} catch (Exception e) {
			_log.debug(e);
		}

		dataUser.put(result);

		return dataUser.toJSONString();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		long checkUserId = -1;
		String emailAddress = StringPool.BLANK;

		try {

			Enumeration<String> headerNames = request.getHeaderNames();

			String strBasic = StringPool.BLANK;

			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String key = (String) headerNames.nextElement();
					String value = request.getHeader(key);
					if (key.trim().equalsIgnoreCase(WebKeys.AUTHORIZATION)) {
						strBasic = value;
						break;
					}
				}
			}

			// Get encoded user and password, comes after "BASIC "
			String userpassEncoded = strBasic.substring(6);
			String decodetoken = new String(Base64.decode(userpassEncoded), StringPool.UTF8);

			String account[] = decodetoken.split(StringPool.COLON);

			String email = account[0];
			String password = account[1];
			emailAddress = email;

			long userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(request, email, password,
					CompanyConstants.AUTH_TYPE_EA);
			if (userId > 0) {
				checkUserId = userId;
				// Remember me false
				AuthenticatedSessionManagerUtil.login(request, response, email, password, false,
						CompanyConstants.AUTH_TYPE_EA);

				Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);

				User user = UserLocalServiceUtil.fetchUser(userId);
				if (Validator.isNotNull(employee)) {

					if (user != null && user.getStatus() == WorkflowConstants.STATUS_PENDING
							&& employee.getWorkingStatus() == 0) {
						return DossierActionTerm.PENDING;
					} else {
						return ConstantUtils.HOME_URL;
					}
				}
			}

		} catch (AuthException ae) {
			System.out.println("AUTH EXCEPTION: " + checkUserId);
			_log.debug(ae);
			if (checkUserId != -1) {
				User checkUser = UserLocalServiceUtil.fetchUser(checkUserId);

				if (checkUser != null && checkUser.getFailedLoginAttempts() >= 5) {
					ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
					String jCaptchaResponse = request
							.getParameter(ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA_RESPONSE));
					String captchaId = request.getSession().getId();
					try {
						boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
						if (!isResponseCorrect)
							return ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA);
					} catch (CaptchaServiceException e) {
						_log.debug(e);
						return ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA);
					}
				} else {
					return ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA);
				}
			} else {
				try {
					Company company = CompanyLocalServiceUtil
							.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
					User checkUser = UserLocalServiceUtil.fetchUserByEmailAddress(company.getCompanyId(), emailAddress);

					if (checkUser != null && checkUser.getFailedLoginAttempts() >= 5) {
						ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
						String jCaptchaResponse = request
								.getParameter(ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA_RESPONSE));
						String captchaId = request.getSession().getId();
						try {
							boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
							if (!isResponseCorrect)
								return ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA);
						} catch (CaptchaServiceException e) {
							_log.debug(e);
							return ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA);
						}
					}
				} catch (PortalException e) {
					_log.debug(e);
				}
			}
		} catch (PortalException pe) {
			System.out.println("PORTAL EXCEPTION: " + emailAddress);
			_log.debug(pe);
			try {
				Company company = CompanyLocalServiceUtil
						.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
				User checkUser = UserLocalServiceUtil.fetchUserByEmailAddress(company.getCompanyId(), emailAddress);

				if (checkUser != null && checkUser.getFailedLoginAttempts() >= 5) {
					ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
					String jCaptchaResponse = request
							.getParameter(ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA_RESPONSE));
					String captchaId = request.getSession().getId();
					try {
						boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
						if (!isResponseCorrect)
							return ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA);
					} catch (CaptchaServiceException e) {
						_log.debug(e);
						return ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA);
					}
				}
			} catch (PortalException e) {
				_log.debug(e);
			}

		} catch (Exception e) {
			System.out.println("EXCEPTION");
			_log.debug(e);
		}

		return "";
	}

	@RequestMapping(value = "/users/avatar/{className}/{pk}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getAttachment(HttpServletRequest request, @PathVariable("className") String className,
			@PathVariable("pk") String pk) {

		String result = StringPool.BLANK;

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(Field.GROUP_ID))) {
			groupId = Long.valueOf(request.getHeader(Field.GROUP_ID));
		}

		List<FileAttach> fileAttachs = FileAttachLocalServiceUtil.findByF_className_classPK(groupId, className, pk);

		if (Validator.isNotNull(fileAttachs) && fileAttachs.size() > 0) {

			FileAttach fileAttach = fileAttachs.get(fileAttachs.size() - 1);

			try {

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileAttach.getFileEntryId());

				result = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(),
						(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY), StringPool.BLANK);
			} catch (PortalException e) {
				_log.debug(e);
			}

		}

		return result;
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "File Attachment incorrect format")
	public class ResourceNotFoundException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}

	@ResponseStatus(value = HttpStatus.OK, reason = "Success")
	public class SucessException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}

	@RequestMapping(value = "/users/upload/{code}/{className}/{pk}", method = RequestMethod.POST)
	public void uploadAttachment(MultipartHttpServletRequest request, @PathVariable("code") String code,
			@PathVariable("className") String className, @PathVariable("pk") String pk) {

		CommonsMultipartFile multipartFile = null;

		Iterator<String> iterator = request.getFileNames();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			// create multipartFile array if you upload multiple files
			multipartFile = (CommonsMultipartFile) request.getFile(key);
		}

		boolean flagCheck = CheckFileUtils.checkFileUpload(multipartFile);

		if (!flagCheck) {
//			return Response.status(403)
//					.entity("File Attachment incorrect format!").build();
			throw new ResourceNotFoundException();
		}

		long userId = 0;
		if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
			userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());
		}
		long groupId = 0;
		if (Validator.isNotNull(request.getHeader(Field.GROUP_ID))) {
			groupId = Long.valueOf(request.getHeader(Field.GROUP_ID));
		}
		long companyId = CompanyThreadLocal.getCompanyId();
		String desc = ReadFilePropertiesUtils.get(ConstantUtils.FILE_ATTACH_DESC);
		String destination = ReadFilePropertiesUtils.get(ConstantUtils.FILE_ATTACH_DESTINATION);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);

		try {
			if (multipartFile != null) {
				FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId,
						multipartFile.getInputStream(),
						UUID.randomUUID() + StringPool.UNDERLINE + multipartFile.getOriginalFilename(),
						multipartFile.getOriginalFilename()
								.substring(multipartFile.getOriginalFilename().lastIndexOf(StringPool.PERIOD) + 1),
						multipartFile.getSize(), destination, desc, serviceContext);

				if (ReadFilePropertiesUtils.get(ConstantUtils.CLASS_ADMINCONFIG).equals(code)) {

					ServiceFileTemplateLocalServiceUtil.addServiceFileTemplate(Long.valueOf(pk),
							fileEntry.getFileEntryId() + StringPool.BLANK, multipartFile.getOriginalFilename(),
							fileEntry.getFileEntryId(), serviceContext);

				} else {

					User user = UserLocalServiceUtil.fetchUser(userId);

					FileAttach fileAttach = FileAttachLocalServiceUtil.addFileAttach(userId, groupId, className, pk,
							user.getFullName(), user.getEmailAddress(), fileEntry.getFileEntryId(), StringPool.BLANK,
							StringPool.BLANK, 0, fileEntry.getFileName(), serviceContext);

					if (ReadFilePropertiesUtils.get(ConstantUtils.CLASS_EMPLOYEE).equals(code)) {
						Employee employee = EmployeeLocalServiceUtil.fetchEmployee(Long.valueOf(pk));
						employee.setPhotoFileEntryId(fileAttach.getFileEntryId());
						EmployeeLocalServiceUtil.updateEmployee(employee);
					} else if (ReadFilePropertiesUtils.get(ConstantUtils.CLASS_DELIVERABLE_TYPE).equals(code)) {

						DeliverableType openCPSDeliverableType = DeliverableTypeLocalServiceUtil
								.fetchDeliverableType(Long.valueOf(pk));

						if (className.endsWith(ConstantUtils.FORM_UPCASE)) {
							openCPSDeliverableType.setFormScriptFileId(fileAttach.getFileEntryId());
						} else if (className.endsWith(ConstantUtils.JASPER_UPCASE)) {
							openCPSDeliverableType.setFormReportFileId(fileAttach.getFileEntryId());
						}

						DeliverableTypeLocalServiceUtil.updateDeliverableType(openCPSDeliverableType);

					} else if (ReadFilePropertiesUtils.get(ConstantUtils.CLASS_APPLICANT).equals(code)) {

						System.out.println("RestfulController.uploadAttachment()" + Long.valueOf(pk));
						Employee employee = EmployeeLocalServiceUtil.fetchEmployee(Long.valueOf(pk));
						System.out.println("RestfulController.uploadAttachment(className)" + className);
						File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(),
								fileEntry.getVersion(), true);
						if (ReadFilePropertiesUtils.get(ConstantUtils.CLASS_APPLICANT_ESIGN).equals(className)) {
							String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH
									+ ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATH_CER) + employee.getEmail()
									+ StringPool.PERIOD + ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_PNG);
							File targetFile = new File(buildFileName);
							employee.setFileSignId(fileAttach.getFileEntryId());
							Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} else {
							String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH
									+ ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATH_CER) + employee.getEmail()
									+ StringPool.PERIOD + ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_CER);
							File targetFile = new File(buildFileName);
							employee.setFileCertId(fileAttach.getFileEntryId());
							Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						}

						EmployeeLocalServiceUtil.updateEmployee(employee);

					} else if (ReadFilePropertiesUtils.get(ConstantUtils.CLASS_DELIVERABLE).equals(code)) {

						Deliverable openCPSDeliverable = DeliverableLocalServiceUtil.fetchDeliverable(Long.valueOf(pk));

						openCPSDeliverable.setFileEntryId(fileAttach.getFileEntryId());
						//
						String formData = openCPSDeliverable.getFormData();
						if (Validator.isNotNull(formData)) {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
							jsonData.put(DossierFileTerm.FILE_ATTACH, true);
							openCPSDeliverable.setFormData(jsonData.toJSONString());
						}

						DeliverableLocalServiceUtil.updateDeliverable(openCPSDeliverable);

					}

				}
			}
		} catch (Exception e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/users/upload/{code}/{className}/{serviceInfoId}/{fileTemplateNo}", method = RequestMethod.POST)
	public void uploadServiceFileAttachment(MultipartHttpServletRequest request, @PathVariable("code") String code,
			@PathVariable("className") String className, @PathVariable("serviceInfoId") String serviceInfoId,
			@PathVariable("fileTemplateNo") String fileTemplateNo) {

		CommonsMultipartFile multipartFile = null;

		Iterator<String> iterator = request.getFileNames();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			// create multipartFile array if you upload multiple files
			multipartFile = (CommonsMultipartFile) request.getFile(key);
		}

		boolean flagCheck = CheckFileUtils.checkFileUpload(multipartFile);

		if (!flagCheck) {
//			return Response.status(403)
//					.entity("File Attachment incorrect format!").build();
			throw new ResourceNotFoundException();
		}

		long userId = 0;
		if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
			userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());
		}
		long groupId = 0;
		if (Validator.isNotNull(request.getHeader(Field.GROUP_ID))) {
			groupId = Long.valueOf(request.getHeader(Field.GROUP_ID));
		}
		long companyId = CompanyThreadLocal.getCompanyId();
		String desc = ReadFilePropertiesUtils.get(ConstantUtils.FILE_ATTACH_DESC);
		String destination = ReadFilePropertiesUtils.get(ConstantUtils.FILE_ATTACH_DESTINATION);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);

		try {
			if (multipartFile != null) {
				FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId,
						multipartFile.getInputStream(),
						UUID.randomUUID() + StringPool.UNDERLINE + multipartFile.getOriginalFilename(),
						multipartFile.getOriginalFilename()
								.substring(multipartFile.getOriginalFilename().lastIndexOf(StringPool.PERIOD) + 1),
						multipartFile.getSize(), destination, desc, serviceContext);

				User user = UserLocalServiceUtil.fetchUser(userId);

				FileAttach fileAttach = FileAttachLocalServiceUtil.addFileAttach(userId, groupId, className,
						serviceInfoId + StringPool.UNDERLINE + fileTemplateNo, user.getFullName(),
						user.getEmailAddress(), fileEntry.getFileEntryId(), StringPool.BLANK, StringPool.BLANK, 0,
						fileEntry.getFileName(), serviceContext);

				if (ReadFilePropertiesUtils.get(ConstantUtils.CLASS_FILE_TEMPLATE).equals(code)) {

					ServiceFileTemplate fileTemplate = ServiceFileTemplateLocalServiceUtil
							.fetchByF_serviceInfoId_fileTemplateNo(Long.valueOf(serviceInfoId), fileTemplateNo);

					if (className.endsWith(ConstantUtils.FORM_UPCASE)) {
						fileTemplate.setFormScriptFileId(fileAttach.getFileEntryId());
					} else if (className.endsWith(ConstantUtils.JASPER_UPCASE)) {
						fileTemplate.setFormReportFileId(fileAttach.getFileEntryId());
					}

					ServiceFileTemplateLocalServiceUtil.updateServiceFileTemplate(fileTemplate);
				}
			}

		} catch (Exception e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/fileattach/{className}/{pk}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getAttachFileData(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("className") String className, @PathVariable("pk") String pk) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray resultArray = JSONFactoryUtil.createJSONArray();

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(Field.GROUP_ID))) {
			groupId = Long.valueOf(request.getHeader(Field.GROUP_ID));
		}

		List<FileAttach> fileAttachs = FileAttachLocalServiceUtil.findByF_className_classPK(groupId, className, pk);

		result.put(ConstantUtils.TOTAL, fileAttachs.size());

		JSONObject object = null;
		for (FileAttach ett : fileAttachs) {

			try {

				String newName = ett.getFileName();

				if (newName.indexOf(StringPool.UNDERLINE) > 0) {

					ett.setFileName(newName.substring(newName.indexOf(StringPool.UNDERLINE) + 1));

				}

				object = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(ett));

				resultArray.put(object);

			} catch (Exception e) {
				_log.debug(e);
			}

		}
		result.put(ConstantUtils.DATA, resultArray);

		return result.toJSONString();

	}

	@RequestMapping(value = "/users/upload/delete/{code}/{className}/{pk}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String deleteAttachFileData(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("className") String className, @PathVariable("pk") String pk,
			@PathVariable("code") String code) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(Field.GROUP_ID))) {
			groupId = Long.valueOf(request.getHeader(Field.GROUP_ID));
		}

		List<FileAttach> fileAttachs = FileAttachLocalServiceUtil.findByF_className_classPK(groupId, className, pk);

		for (FileAttach ett : fileAttachs) {

			FileAttachLocalServiceUtil.deleteFileAttach(ett);

		}

		if (ReadFilePropertiesUtils.get(ConstantUtils.CLASS_DELIVERABLE).equals(code)) {
			Deliverable openCPSDeliverable = DeliverableLocalServiceUtil.fetchDeliverable(Long.valueOf(pk));

			openCPSDeliverable.setFileEntryId(0);

			DeliverableLocalServiceUtil.updateDeliverable(openCPSDeliverable);
		}

		return result.toJSONString();

	}

	@RequestMapping(value = "/filetemplate/{serviceInfoId}/{fileTemplateNo}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeServiceFileTemplate(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("serviceInfoId") long serviceInfoId, @PathVariable("fileTemplateNo") String fileTemplateNo) {

		try {
			ServiceFileTemplate serviceFileTemplate = ServiceFileTemplateLocalServiceUtil
					.fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId, fileTemplateNo);

			long fileEntryId = serviceFileTemplate.getFileEntryId();

			ServiceFileTemplateLocalServiceUtil.deleteServiceFileTemplate(serviceFileTemplate);
			DLAppLocalServiceUtil.deleteFileEntry(fileEntryId);

		} catch (Exception e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/filetemplate/{serviceInfoId}/{fileTemplateNo}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void upDateServiceFileTemplate(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("serviceInfoId") long serviceInfoId, @PathVariable("fileTemplateNo") String fileTemplateNo,
			@RequestBody FileTemplateMiniItem fileTemplateMiniItem) {

		ServiceFileTemplate serviceFileTemplate = ServiceFileTemplateLocalServiceUtil
				.fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId, fileTemplateNo);

		ServiceFileTemplatePK serviceFileTemplatePK = new ServiceFileTemplatePK(serviceInfoId, fileTemplateNo);

		ServiceFileTemplate serviceFileTemplateNew;
		try {
			serviceFileTemplateNew = ServiceFileTemplateLocalServiceUtil.getServiceFileTemplate(serviceFileTemplatePK);

			ServiceFileTemplateLocalServiceUtil.deleteServiceFileTemplate(serviceFileTemplate);

			if (Validator.isNotNull(serviceFileTemplateNew)) {

				if (Validator.isNotNull(fileTemplateMiniItem.getFileTemplateNo())) {
					serviceFileTemplateNew.setFileTemplateNo(fileTemplateMiniItem.getFileTemplateNo());
				}
				if (Validator.isNotNull(fileTemplateMiniItem.getTemplateName())) {
					serviceFileTemplateNew.setTemplateName(fileTemplateMiniItem.getTemplateName());
				}

				ServiceFileTemplateLocalServiceUtil.updateServiceFileTemplate(serviceFileTemplateNew);
			}
		} catch (PortalException e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/users/{id}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	public ResponseEntity<UsersUserItem> getUserById(
			@ApiParam(value = "id của user", required = true) @PathVariable("id") String id) {

		if (Validator.isNull(id)) {

			throw new OpenCPSNotFoundException(User.class.getName());

		} else {

			UserActions actions = new UserActions();

			String userData = actions.getUserById(Long.valueOf(id));

			if (Validator.isNull(userData)) {
				throw new OpenCPSNotFoundException(User.class.getName());
			}

			return new ResponseEntity<UsersUserItem>(JSONFactoryUtil.looseDeserialize(userData, UsersUserItem.class),
					HttpStatus.OK);

		}

	}

	@RequestMapping(value = "/fileattach/{id}/text", produces = {
			"text/plain; charset=utf-8" }, method = RequestMethod.GET)
	public @ResponseBody String getTextFromFileEntryId(HttpServletResponse response,
			@ApiParam(value = "id của user", required = true) @PathVariable("id") Long id) {

		String result = StringPool.BLANK;
		response.setCharacterEncoding(StringPool.UTF8);

		InputStream is = null;

		try {

			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(id);

			is = dlFileEntry.getContentStream();

			result = IOUtils.toString(is, StandardCharsets.UTF_8);

		} catch (Exception e) {
			_log.debug(e);
			result = StringPool.BLANK;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}

		return result.toString();

	}

	@RequestMapping(value = "/deliverable/{id}/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getDeliverableById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id) {

		try {
			Deliverable deliverable = DeliverableLocalServiceUtil.fetchDeliverable(id);
			if (deliverable != null) {
				return JSONFactoryUtil.looseSerialize(deliverable);
			}

		} catch (Exception e) {
			_log.debug(e);
		}

		return StringPool.BLANK;
	}

	@RequestMapping(value = "/site/name", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getSiteName(HttpServletRequest request, HttpServletResponse response) {

		String result = StringPool.BLANK;

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader(Field.GROUP_ID))) {
			groupId = Long.valueOf(request.getHeader(Field.GROUP_ID));
		}

		Group group = GroupLocalServiceUtil.fetchGroup(groupId);

		if (Validator.isNotNull(group)) {

			result = group.getGroupKey();

		}

		return result.toUpperCase();

	}

	@RequestMapping(value = "/users/login/jcaptcha", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resource> getJCaptcha(HttpServletRequest request, HttpServletResponse response) {
		try {
			ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();

			String captchaId = request.getSession().getId();
			File destDir = new File(ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA));
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			File file = new File(ReadFilePropertiesUtils.get(ConstantUtils.CAPTCHA) + StringPool.SLASH + captchaId
					+ StringPool.PERIOD + ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_PNG));
			if (!file.exists()) {
				file.createNewFile();
			}

			if (file.exists()) {
				BufferedImage challengeImage = instance.getImageChallengeForID(captchaId, Locale.US);
				try {
					ImageIO.write(challengeImage, ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_PNG), file);

				} catch (IOException e) {
					_log.debug(e);
				}
			}

			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

			return ResponseEntity.ok().headers(new HttpHeaders()).contentLength(file.length()).body(resource);
		} catch (Exception e) {
			_log.debug(e);
			return null;
		}

	}

	public static final Log _log = LogFactoryUtil.getLog(RestfulController.class);
}
