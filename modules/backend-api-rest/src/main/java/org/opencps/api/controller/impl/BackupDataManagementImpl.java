package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.io.IOUtils;
import org.opencps.api.backupdata.model.DataInputModel;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.BackupDataManagement;
import org.opencps.api.controller.util.ImportZipFileUtils;
import org.opencps.api.controller.util.ReadXMLFileUtils;
import org.opencps.api.v21.model.CitizenList;
import org.opencps.api.v21.model.CitizenList.Citizen;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class BackupDataManagementImpl implements BackupDataManagement{
	private static Log _log = LogFactoryUtil.getLog(BackupDataManagementImpl.class);

	@Override
	public Response exportDataToXML(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataInputModel input) {
		
		_log.info("export DB to XML");
		BackendAuth auth = new BackendAuthImpl();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();

		//long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		//long userId = user.getUserId();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, "admin")) {
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity("User not permission process!").build();
			}

			String dataCode = input.getDataCode();
			String dataType = input.getDataType();
			if (Validator.isNotNull(dataCode)) {
				if (dataCode.equals("applicant")) {
					if ("citizen".equals(dataType)) {
						List<Applicant> appList = ApplicantLocalServiceUtil.getApplicantByType(0l, dataCode);
						if (appList != null && appList.size() > 0) {
							CitizenList citizenList = new CitizenList();
							for (Applicant applicant : appList) {
								Citizen citizen = new Citizen();
								if (Validator.isNotNull(applicant.getApplicantIdNo())
										&& applicant.getApplicantIdNo().length() < 12) {
									citizen.setLoaiThe(0);
								} else {
									citizen.setLoaiThe(1);
								}
								citizen.setSoCMND(applicant.getApplicantIdNo());
								citizen.setSoDinhDanh(1);
								citizen.setHoVaTen(applicant.getApplicantName());
								citizen.setThuDienTu(applicant.getContactEmail());
								citizen.setGioiTinh(0);
								citizen.setDanToc("Không rõ");
								citizen.setSoDienThoaiBan(StringPool.BLANK);
								citizen.setSoDienThoai(applicant.getContactTelNo());
								citizen.setTonGiao(StringPool.BLANK);
								citizen.setTinhTrangHonNhan(0);
								citizen.setNhomMau("00");
								citizen.setNgayThangNamSinh(StringPool.BLANK);
								citizen.setNoiDangKyKhaiSinh(StringPool.BLANK);
								citizen.setQueQuan(applicant.getCityName());
								citizen.setThuongTru(applicant.getCityName());
								String noiohientai = applicant.getAddress() + StringPool.DASH + applicant.getDistrictName() + StringPool.DASH +
										applicant.getCityName();
								citizen.setNoiOHienTai(noiohientai);
								citizen.setQuocTich("Viet Nam");
								citizen.setCha(StringPool.BLANK);
								citizen.setMe(StringPool.BLANK);
								citizen.setVoChong(StringPool.BLANK);
								citizen.setNguoiDaiDien(StringPool.BLANK);
								citizen.setChuHo(applicant.getApplicantName());
								citizen.setTrangThai(1);
								//
								citizenList.getCitizen().add(citizen);
							}
							//Method which uses JAXB to convert object to XML
							File file = ReadXMLFileUtils.convertCitizenToXML(citizenList);
							//
							ResponseBuilder responseBuilder = Response.ok((Object) file);

							responseBuilder.header("Content-Disposition",
									"attachment; filename=\"" + file.getName() + "\"");
							responseBuilder.header("Content-Type", "application/xml");

							return responseBuilder.build();
						}
					}
					
				}
			}

			//String result = StringPool.BLANK;
			//
//			List<Group> groupList = GroupLocalServiceUtil.getActiveGroups(company.getCompanyId(), true);
//			String strGroupId = StringPool.BLANK;
//			if (groupList != null && groupList.size() > 0) {
//				List<String> groupIdList = new ArrayList<>();
//				for (Group group : groupList) {
//					if (group.isSite()) {
//						groupIdList.add(String.valueOf(group.getGroupId()));
//					}
//				}
//				if (groupIdList != null && groupIdList.size() > 0) {
//					strGroupId = String.join(StringPool.COMMA, groupIdList);
//				}
//			}
//			//Check group
//			if (!strGroupId.contains(String.valueOf(groupId))) {
//				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("GroupId not exits!").build();
//			}
//			
//			//Process FILE
//			fileInputStream = dataHandle.getInputStream();
//			String fileName = dataHandle.getName();
//			String extFile = ImportZipFileUtils.getExtendFileName(fileName);
//			_log.info("extFile: "+extFile);
//			if (Validator.isNotNull(extFile)) {
//				if ("zip".equals(extFile.toLowerCase())) {
//					String pathFolder = ImportZipFileUtils.getFolderPath(fileName, ConstantUtils.DEST_DIRECTORY);
////					//delete folder if exits
//					File fileOld = new File(pathFolder);
//					_log.info("fileOld: "+fileOld);
//					if (fileOld.exists()) {
//						boolean flag = ReadXMLFileUtils.deleteFilesForParentFolder(fileOld);
//						_log.info("LamTV_Delete DONE: "+flag);
//					}
////					_log.info("LamTV_pathFolder: "+pathFolder);
//					ImportZipFileUtils.unzip(fileInputStream, ConstantUtils.DEST_DIRECTORY);
//					File fileList = new File(pathFolder);
////					//Validate xml
//					String strError = ReadXMLFileUtils.validateXML(fileList, true);
//					_log.info("strError: "+strError);
//					if (Validator.isNotNull(strError)) {
//						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(strError).build();
//					}
//
////					String errorCheck = ReadXMLFileUtils.getStrError();
////					_log.info("errorCheck: "+errorCheck);
////					if (Validator.isNotNull(errorCheck)) {
////						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(errorCheck).build();
////					}
//					result = ReadXMLFileUtils.listFilesForParentFolder(fileList, groupId, userId, serviceContext);
//					if (Validator.isNull(result)) {
//						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("Folder is not structure").build();
//					}
//					_log.info("LamTV_IMPORT DONE_ZIP");
//				} else if ("xml".equals(extFile.toLowerCase())) {
//					String pathFile = ConstantUtils.DEST_DIRECTORY + StringPool.SLASH + fileName;
////					//delete folder if exits
//					File fileOld = new File(pathFile);
//					_log.info("fileOld: "+fileOld.getAbsolutePath());
//					if (fileOld.exists()) {
//						boolean flag = ReadXMLFileUtils.deleteFilesForParentFolder(fileOld);
//						_log.info("LamTV_Delete DONE: "+flag);
//					}
//					_log.info("LamTV_pathFolder: "+pathFile);
//					File fileList = new File(pathFile);
//					FileOutputStream out = new FileOutputStream(fileList);
//					IOUtils.copy(fileInputStream, out);
////					FileUtils.copyInputStreamToFile(fileInputStream, fileList);
//					_log.info("fileList: "+fileList);
////					_log.info("LamTV_fileList: "+fileList.getPath());
//					String subFileName = ImportZipFileUtils.getSubFileName(fileName);
//					if (Validator.isNotNull(subFileName)) {
//						String strError = ReadXMLFileUtils.validateXML(fileList, false);
//						if (Validator.isNotNull(strError)) {
//							return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(strError).build();
//						}
//						String xmlString = ReadXMLFileUtils.convertFiletoString(fileList);
//						result = ReadXMLFileUtils.compareParentFile(ConstantUtils.DEST_DIRECTORY, fileName, xmlString, groupId, userId, serviceContext);
//					}
//					_log.info("LamTV_IMPORT DONE_FILE");
//				}
//			}
//
//			return Response.status(200).entity(result).build();
//
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
		
		return null;
	}

}
