
package org.opencps.api.controller.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.DossierUserActions;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierUserActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.Validator;

public class ConvertDossierFromV1Dot9Utils {

	public ConvertDossierFromV1Dot9Utils() {
		// TODO Auto-generated constructor stub
	}

	static Log _log =
		LogFactoryUtil.getLog(ConvertDossierFromV1Dot9Utils.class);

	public static final String TEMP_UUID_ = "uuid_";
	public static final String TEMP_DOSSIERID = "dossierId";
	public static final String TEMP_GROUPID = "groupId";
	public static final String TEMP_COMPANYID = "companyId";
	public static final String TEMP_USERID = "userId";
	public static final String TEMP_USERNAME = "userName";
	public static final String TEMP_CREATEDATE = "createDate";
	public static final String TEMP_MODIFIEDDATE = "modifiedDate";
	public static final String TEMP_REFERENCEUID = "referenceUid";
	public static final String TEMP_COUNTER = "counter";
	public static final String TEMP_SERVICECODE = "serviceCode";
	public static final String TEMP_SERVICENAME = "serviceName";
	public static final String TEMP_GOVAGENCYCODE = "govAgencyCode";
	public static final String TEMP_GOVAGENCYNAME = "govAgencyName";
	public static final String TEMP_APPLICANTNAME = "applicantName";
	public static final String TEMP_APPLICANTIDTYPE = "applicantIdType";
	public static final String TEMP_APPLICANTIDNO = "applicantIdNo";
	public static final String TEMP_APPLICANTIDDATE = "applicantIdDate";
	public static final String TEMP_ADDRESS = "address";
	public static final String TEMP_CITYCODE = "cityCode";
	public static final String TEMP_CITYNAME = "cityName";
	public static final String TEMP_DISTRICTCODE = "districtCode";
	public static final String TEMP_DISTRICTNAME = "districtName";
	public static final String TEMP_WARDCODE = "wardCode";
	public static final String TEMP_WARDNAME = "wardName";
	public static final String TEMP_CONTACTNAME = "contactName";
	public static final String TEMP_CONTACTTELNO = "contactTelNo";
	public static final String TEMP_CONTACTEMAIL = "contactEmail";
	public static final String TEMP_DOSSIERTEMPLATENO = "dossierTemplateNo";
	public static final String TEMP_DOSSIERTEMPLATENAME = "dossierTemplateName";
	public static final String TEMP_DOSSIERNOTE = "dossierNote";
	public static final String TEMP_SUBMISSIONNOTE = "submissionNote";
	public static final String TEMP_APPLICANTNOTE = "applicantNote";
	public static final String TEMP_BRIEFNOTE = "briefNote";
	public static final String TEMP_DOSSIERNO = "dossierNo";
	public static final String TEMP_SUBMITTING = "submitting";
	public static final String TEMP_SUBMITDATE = "submitDate";
	public static final String TEMP_RECEIVEDATE = "receiveDate";
	public static final String TEMP_DUEDATE = "dueDate";
	public static final String TEMP_RELEASEDATE = "releaseDate";
	public static final String TEMP_FINISHDATE = "finishDate";
	public static final String TEMP_CANCELLINGDATE = "cancellingDate";
	public static final String TEMP_CORRECTTINGDATE = "correcttingDate";
	public static final String TEMP_DOSSIERSTATUS = "dossierStatus";
	public static final String TEMP_DOSSIERSTATUSTEXT = "dossierStatusText";
	public static final String TEMP_DOSSIERSUBSTATUS = "dossierSubStatus";
	public static final String TEMP_DOSSIERSUBSTATUSTEXT =
		"dossierSubStatusText";
	public static final String TEMP_FOLDERID = "folderId";
	public static final String TEMP_DOSSIERACTIONID = "dossierActionId";
	public static final String TEMP_VIAPOST = "viaPost";
	public static final String TEMP_POSTADDRESS = "postAddress";
	public static final String TEMP_PASSWORD_ = "password";
	public static final String TEMP_NOTIFICATION = "notification";
	public static final String TEMP_VIAPOSTAL = "viaPostal";
	public static final String TEMP_POSTALADDRESS = "postalAddress";
	public static final String TEMP_POSTALCITYCODE = "postalCityCode";
	public static final String TEMP_POSTALCITYNAME = "postalCityName";
	public static final String TEMP_POSTALTELNO = "postalTelNo";
	public static final String TEMP_ONLINE_ = "online_";
	public static final String TEMP_SERVERNO = "serverNo";
	public static final String TEMP_ENDORSEMENTDATE = "endorsementDate";
	public static final String TEMP_LOCKSTATE = "lockState";
	public static final String TEMP_DOSSIERREGISTER = "dossierRegister";
	public static final String TEMP_DELEGATENAME = "delegateName";
	public static final String TEMP_DELEGATEIDNO = "delegateIdNo";
	public static final String TEMP_DELEGATETELNO = "delegateTelNo";
	public static final String TEMP_DELEGATEEMAIL = "delegateEmail";
	public static final String TEMP_DELEGATEADDRESS = "delegateAddress";
	public static final String TEMP_DELEGATECITYCODE = "delegateCityCode";
	public static final String TEMP_DELEGATECITYNAME = "delegateCityName";
	public static final String TEMP_DELEGATEDISTRICTCODE =
		"delegateDistrictCode";
	public static final String TEMP_DELEGATEDISTRICTNAME =
		"delegateDistrictName";
	public static final String TEMP_DELEGATEWARDCODE = "delegateWardCode";
	public static final String TEMP_DELEGATEWARDNAME = "delegateWardName";
	public static final String TEMP_EXTENDDATE = "extendDate";
	public static final String TEMP_POSTALSERVICECODE = "postalServiceCode";
	public static final String TEMP_POSTALSERVICENAME = "postalServiceName";
	public static final String TEMP_POSTALDISTRICTCODE = "postalDistrictCode";
	public static final String TEMP_POSTALDISTRICTNAME = "postalDistrictName";
	public static final String TEMP_POSTALWARDCODE = "postalWardCode";
	public static final String TEMP_POSTALWARDNAME = "postalWardName";
	public static final String TEMP_ORIGINAL = "original";
	public static final String TEMP_REGISTERBOOKCODE = "registerBookCode";
	public static final String TEMP_REGISTERBOOKNAME = "registerBookName";
	public static final String TEMP_PROCESSNO = "processNo";
	public static final String TEMP_ORIGINALITY = "originality";
	public static final String TEMP_ORIGINDOSSIERID = "originDossierId";
	public static final String TEMP_PROCESSDATE = "processDate";
	public static final String TEMP_DURATIONCOUNT = "durationCount";
	public static final String TEMP_DURATIONUNIT = "durationUnit";
	public static final String TEMP_SAMPLECOUNT = "sampleCount";
	public static final String TEMP_DOSSIERNAME = "dossierName";
	public static final String TEMP_ORIGINDOSSIERNO = "originDossierNo";
	public static final String TEMP_GROUPDOSSIERID = "groupDossierId";
	public static final String TEMP_METADATA = "metaData";
	public static final String TEMP_DELEGATETYPE = "delegateType";
	public static final String TEMP_DOCUMENTNO = "documentNo";
	public static final String TEMP_DOCUMENTDATE = "documentDate";

	public static void setDossierFileObject(
		JSONObject j, ServiceContext serviceContext) {

		try {
			Dossier dossier = DossierLocalServiceUtil.getByDossierNo(
				j.getLong(TEMP_GROUPID), j.getString(TEMP_DOSSIERNO));

			if (dossier == null) {
				return;
			}

			DossierFileActions action = new DossierFileActionsImpl();

			String dossierTemplateNo = dossier.getDossierTemplateNo();

			long groupId = j.getLong("groupId");
			String dossierPartNo = j.getString("partNo");
			String referenceUid = dossier.getReferenceUid() +
				dossier.getDossierId() + dossierPartNo;
			String fileTemplateNo =
				dossier.getDossierTemplateNo() + "-" + dossierPartNo;
			String displayName = j.getString("partName");
			String fileName = j.getString("fileName");

			DossierFile oldDossierFile =
				DossierFileLocalServiceUtil.getByG_DID_PART_NAME(
					groupId, dossier.getDossierId(), dossierPartNo, 1,
					displayName);
			if (oldDossierFile == null) {
				oldDossierFile =
					DossierFileLocalServiceUtil.getByG_DID_PART_NAME(
						groupId, dossier.getDossierId(), dossierPartNo, 2,
						displayName);
			}
			if (oldDossierFile != null) {
				_log.info("__Start update file at:" + new Date());

//				DossierFile dossierFile = null;
//
//				System.out.println(oldDossierFile.getReferenceUid());
//				dossierFile = action.updateDossierFile(
//					groupId, dossier.getDossierId(),
//					oldDossierFile.getReferenceUid(), "sourceFileName", inputStream,
//					serviceContext);
				// _log.info("__End update file at:" + new Date());

//				dossierFile.setRemoved(false);
				// _log.info("__Start update dossier file at:" + new Date());
//				DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
				// _log.info("__End update dossier file at:" + new Date());
			}
			else {
				 _log.info("__Start add file at:" + new Date());
				DossierFile dossierFile = null;
				// _log.info(
				// groupId + "===" + dossier.getDossierId() + "===" +
				// dossierPartNo + "===" + displayName);

				InputStream inputStream = getFileFromDVCOld(j.getString("fileUrl"));

				if (dossier == null || inputStream == null) {
					return;
				}

				dossierFile = action.addDossierFile(
					groupId, dossier.getDossierId(), referenceUid,
					dossierTemplateNo, dossierPartNo, fileTemplateNo,
					displayName, "sourceFileName", 0l, inputStream, "", "false",
					serviceContext);
				// _log.info("__End add file at:" + new Date());
				dossierFile.setRemoved(false);
				// _log.info("__Start update dossier file at:" + new Date());
				DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

				// _log.info("__End update dossier file at:" + new Date());
			}
		}
		catch (Exception e) {

			System.out.println(j.getString("fileUrl"));
			_log.info(e);
		}
	}

	public static void main(String[] args) {

		getFileFromDVCOld(
			"http://dvc.mt.gov.vn/documents/20182/3850206/_1487395415897.txt");

	}

	public static InputStream getFileFromDVCOld(String url) {

		URL oracle;
		BufferedReader br = null;
		InputStream result = null;
		try {
			oracle = new URL(url);

			result = oracle.openStream();
			_log.info(url);
//			if (url.indexOf("txt") > 0) {
//				br = new BufferedReader(new InputStreamReader(result));
//
//				String line = null;
//
//				while ((line = br.readLine()) != null) {
//					if (line.equalsIgnoreCase("quit")) {
//						break;
//					}
//					System.out.println("Line entered : " + line);
//				}
//			}

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static JSONObject setDossierObject(JSONObject j) {

		JSONObject objectData = JSONFactoryUtil.createJSONObject();

		// objectData.put(TEMP_UUID_, "bf7ca69d-c381-df77-4ab3-041f861f325f");
		// objectData.put(TEMP_DOSSIERID, 0l);
		// objectData.put(TEMP_GROUPID, groupId);
		// objectData.put(TEMP_COMPANYID, 20099l);
		// objectData.put(TEMP_USERID, userId);
		// objectData.put(TEMP_USERNAME, "Phạm Thế Dương");
		// objectData.put(TEMP_CREATEDATE, "2019-08-20 10:58:53");
		// objectData.put(TEMP_MODIFIEDDATE, "2019-08-20 11:21:24");
		// objectData.put(
		// TEMP_REFERENCEUID, "e265ee9b-31a0-4761-8d63-614630b4ed56");
		// objectData.put(TEMP_COUNTER, 1);
		// objectData.put(TEMP_SERVICECODE, "BGTVT-285800");
		// objectData.put(
		// TEMP_SERVICENAME,
		// "Gia hạn giấy phép xây dựng công trình thiết yếu trong phạm vi đất
		// dành cho đường sắt");
		// objectData.put(TEMP_GOVAGENCYCODE, "CDSVN");
		// objectData.put(TEMP_GOVAGENCYNAME, "Cục Đường sắt Việt Nam");
		// objectData.put(TEMP_APPLICANTNAME, "NGUYỄN THỊ PHƯƠNG");
		// objectData.put(TEMP_APPLICANTIDTYPE, "citizen");
		// objectData.put(TEMP_APPLICANTIDNO, "1183009539");
		// objectData.put(TEMP_APPLICANTIDDATE, 0l);
		// objectData.put(TEMP_ADDRESS, "Cổ Điển");
		// objectData.put(TEMP_CITYCODE, "01");
		// objectData.put(TEMP_CITYNAME, "Hà Nội");
		// objectData.put(TEMP_DISTRICTCODE, "17");
		// objectData.put(TEMP_DISTRICTNAME, "Huyện Đông Anh");
		// objectData.put(TEMP_WARDCODE, "508");
		// objectData.put(TEMP_WARDNAME, "Xã Hải Bối");
		// objectData.put(TEMP_CONTACTNAME, "NGUYỄN THỊ PHƯƠNG");
		// objectData.put(TEMP_CONTACTTELNO, "0978266524");
		// objectData.put(TEMP_CONTACTEMAIL, "phuongnt@yopmail.com");
		// objectData.put(TEMP_DOSSIERTEMPLATENO, "MAU_BGTVT-285800");
		// objectData.put(
		// TEMP_DOSSIERTEMPLATENAME,
		// "Gia hạn giấy phép xây dựng công trình thiết yếu trong phạm vi đất
		// dành cho đường sắt");
		// objectData.put(TEMP_DOSSIERNOTE, "");
		// objectData.put(TEMP_SUBMISSIONNOTE, "");
		// objectData.put(TEMP_APPLICANTNOTE, "");
		// objectData.put(TEMP_BRIEFNOTE, "");
		// objectData.put(
		// TEMP_DOSSIERNO, Validator.isNull(dossierNo)
		// ? "000.00.20.G04.111111.1113" : dossierNo);
		// objectData.put(TEMP_SUBMITTING, "0");
		// objectData.put(TEMP_SUBMITDATE, "2019-08-20 10:58:55");
		// objectData.put(TEMP_RECEIVEDATE, "2019-08-20 10:58:40");
		// objectData.put(TEMP_DUEDATE, "2019-08-20 19:30:00");
		// objectData.put(TEMP_RELEASEDATE, "2019-08-20 11:21:16");
		// objectData.put(TEMP_FINISHDATE, "2019-08-20 11:21:24");
		// objectData.put(TEMP_CANCELLINGDATE, 0l);
		// objectData.put(TEMP_CORRECTTINGDATE, 0l);
		// objectData.put(TEMP_DOSSIERSTATUS, "releasing");
		// objectData.put(TEMP_DOSSIERSTATUSTEXT, "Chờ trả kết quả");
		// objectData.put(TEMP_DOSSIERSUBSTATUS, "releasing_0");
		// objectData.put(TEMP_DOSSIERSUBSTATUSTEXT, "Trả kết quả tại một cửa");
		// objectData.put(TEMP_FOLDERID, 0l);
		// objectData.put(TEMP_DOSSIERACTIONID, 0l);
		// objectData.put(TEMP_VIAPOST, 0);
		// objectData.put(TEMP_POSTADDRESS, "");
		// objectData.put(TEMP_PASSWORD_, "1111");
		// objectData.put(TEMP_NOTIFICATION, "0");
		// objectData.put(TEMP_VIAPOSTAL, 1);
		// objectData.put(TEMP_POSTALADDRESS, "");
		// objectData.put(TEMP_POSTALCITYCODE, "");
		// objectData.put(TEMP_POSTALCITYNAME, "");
		// objectData.put(TEMP_POSTALTELNO, "");
		// objectData.put(TEMP_ONLINE_, "0");
		// objectData.put(TEMP_SERVERNO, "SERVER_DVC");
		// objectData.put(TEMP_ENDORSEMENTDATE, 0l);
		// objectData.put(TEMP_LOCKSTATE, "");
		// objectData.put(TEMP_DOSSIERREGISTER, "");
		// objectData.put(TEMP_DELEGATENAME, "NGUYỄN THỊ PHƯƠNG");
		// objectData.put(TEMP_DELEGATEIDNO, "1183009539");
		// objectData.put(TEMP_DELEGATETELNO, "0978266524");
		// objectData.put(TEMP_DELEGATEEMAIL, "phuongnt@yopmail.com");
		// objectData.put(TEMP_DELEGATEADDRESS, "Cổ Điển");
		// objectData.put(TEMP_DELEGATECITYCODE, "1");
		// objectData.put(TEMP_DELEGATECITYNAME, "Hà Nội");
		// objectData.put(TEMP_DELEGATEDISTRICTCODE, "17");
		// objectData.put(TEMP_DELEGATEDISTRICTNAME, "Huyện Đông Anh");
		// objectData.put(TEMP_DELEGATEWARDCODE, "508");
		// objectData.put(TEMP_DELEGATEWARDNAME, "Xã Hải Bối");
		// objectData.put(TEMP_EXTENDDATE, 0l);
		// objectData.put(TEMP_POSTALSERVICECODE, "");
		// objectData.put(TEMP_POSTALSERVICENAME, "");
		// objectData.put(TEMP_POSTALDISTRICTCODE, "");
		// objectData.put(TEMP_POSTALDISTRICTNAME, "");
		// objectData.put(TEMP_POSTALWARDCODE, "");
		// objectData.put(TEMP_POSTALWARDNAME, "");
		// objectData.put(TEMP_ORIGINAL, false); // boolean
		// objectData.put(TEMP_REGISTERBOOKCODE, "REGISTER_01");
		// objectData.put(TEMP_REGISTERBOOKNAME, "Sổ theo dõi");
		// objectData.put(TEMP_PROCESSNO, "QT_BGTVT-285800");
		// objectData.put(TEMP_ORIGINALITY, 3);
		// objectData.put(TEMP_ORIGINDOSSIERID, 0l);
		// objectData.put(TEMP_PROCESSDATE, 0l);
		// objectData.put(TEMP_DURATIONCOUNT, 1.0d);
		// objectData.put(TEMP_DURATIONUNIT, 0);
		// objectData.put(TEMP_SAMPLECOUNT, 0l);
		// objectData.put(
		// TEMP_DOSSIERNAME,
		// "Gia hạn giấy phép xây dựng công trình thiết yếu trong phạm vi đất
		// dành cho đường sắt");
		// objectData.put(TEMP_ORIGINDOSSIERNO, "");
		// objectData.put(TEMP_GROUPDOSSIERID, 0l);
		// objectData.put(TEMP_METADATA, "");
		// objectData.put(TEMP_DELEGATETYPE, 0);
		// objectData.put(TEMP_DOCUMENTNO, "");
		// objectData.put(TEMP_DOCUMENTDATE, 0l);

		objectData = j;

		Dossier dossier = DossierLocalServiceUtil.getByDossierNo(
			objectData.getLong(TEMP_GROUPID),
			objectData.getString(TEMP_DOSSIERNO));

		if (dossier != null) {
			System.out.println(
				"updata dossier ====== ====== ===== " +
					objectData.getString(TEMP_DOSSIERNO));
			objectData.put(TEMP_DOSSIERID, dossier.getDossierId());
			dossier = DossierLocalServiceUtil.adminProcessData(objectData);
		}
		else {
			System.out.println(
				"insert dossier ====== ====== ===== " +
					objectData.getString(TEMP_DOSSIERNO));
			dossier = DossierLocalServiceUtil.adminProcessData(objectData);
		}

		System.out.println(dossier.getDossierId());

		objectData.put(TEMP_DOSSIERID, dossier.getDossierId());
		return objectData;
	}

	public static void insertUserDossier(long groupId, long dossierId) {

		List<Employee> employees = EmployeeLocalServiceUtil.findByG(groupId);
		System.out.println(
			groupId + "start import dossier user..." + dossierId);
		for (Employee e : employees) {
			if (e.getMappingUserId() > 0) {

				DossierUserActions duActions = new DossierUserActionsImpl();;
				duActions.addDossierUser(
					groupId, dossierId, e.getMappingUserId(), 1, true);
			}
		}
		System.out.println(
			groupId + "start import dossier user done..." + dossierId);
	}
	
	public static void insertUserDossierDvc(long mappingID, long dossierId) {

		Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(mappingID);
		if (Validator.isNotNull(applicant)) {

			DossierUserActions duActions = new DossierUserActionsImpl();;
			duActions.addDossierUser(
				applicant.getGroupId(), dossierId, applicant.getMappingUserId(), 1, true);
		}
		System.out.println(
			mappingID + "start import dossier user done..." + dossierId);
	}

	public static JSONObject callActionDoneDossier(
		String pathBase, long groupId, long id, String actionCode,
		ServiceContext serviceContext)
		throws PortalException {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

		String endPoint = "dossiers/" + id + "/actions";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("payment", "");
		params.put("assignUsers", "");
		params.put("actionNote", "");
		params.put(
			"actionCode", Validator.isNull(actionCode) ? "4000" : actionCode);
		params.put("payload", "{\"dossierNote\":\"\"}");

		JSONObject resPostDossier = rest.callPostAPI(
			groupId, HttpMethods.POST, "application/json", pathBase, endPoint,
			RESTFulConfiguration.SERVER_USER, RESTFulConfiguration.SERVER_PASS,
			properties, params, serviceContext);

		_log.info(resPostDossier);

		return resPostDossier;

	}

	public static JSONArray readExcelFileWithHeaderConfig(
		InputStream excelInputStream) {

		Workbook workbook = null;
		JSONArray results = JSONFactoryUtil.createJSONArray();

		try {

			workbook = new XSSFWorkbook(excelInputStream);
			//
			Sheet datatypeSheetOne = workbook.getSheetAt(0);
			int nOfRows = datatypeSheetOne.getPhysicalNumberOfRows();
			int nOfColumns = 1000;
			_log.debug("nOfRows: " + nOfRows);

			if (nOfRows > 1) {

				JSONObject headerFormat = JSONFactoryUtil.createJSONObject();
				for (int i = 0; i < nOfColumns; i++) {
					Cell celli = datatypeSheetOne.getRow(0).getCell(i);
					if (Validator.isNotNull(celli) &&
						Validator.isNotNull(celli.getStringCellValue())) {
						headerFormat.put(
							String.valueOf(i),
							datatypeSheetOne.getRow(0).getCell(
								i).getStringCellValue());
					}
					else {
						nOfColumns = i - 1;
						break;
					}
				}
				_log.debug("====headerFormat==" + headerFormat);
				_log.debug("====nOfColumns===" + nOfColumns);
				for (int i = 1; i < nOfRows; i++) {
					Row currentRow = datatypeSheetOne.getRow(i);
					if (currentRow != null) {

						// todo convert
						JSONObject data = convertRowToJSONObject(
							currentRow, nOfColumns, headerFormat);
						if (Validator.isNotNull(data)) {

							results.put(data);
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (workbook != null) {
				try {
					workbook.close();
				}
				catch (IOException e) {
					e.printStackTrace();
					// _log.debug(e);
				}
			}
		}
		return results;
	}

	public static JSONObject convertRowToJSONObject(
		Row currentRow, int nOfColumns, JSONObject headerFormat) {

		JSONObject dataRow = JSONFactoryUtil.createJSONObject();

		try {
			for (int i = 0; i <= nOfColumns; i++) {

				dataRow.put(
					headerFormat.getString(String.valueOf(i)),
					getCellValue(currentRow.getCell(i)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// _log.error(e);
			return null;
		}

		return dataRow;
	}

	public static Object getCellValue(Cell cell) {

		if (cell == null) {

			return null;
		}
		else if (CellType.STRING == cell.getCellType()) {

			String result = cell.getStringCellValue();

			if (result.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {

				try {

					return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(
						result).getTime();
				}
				catch (ParseException e) {

					return null;
				}
			}

			return cell.getStringCellValue();
		}
		else if (CellType.BOOLEAN == cell.getCellType()) {

			return cell.getBooleanCellValue();
		}
		else if (CellType.ERROR == cell.getCellType()) {

			return cell.getErrorCellValue();
		}
		else if (CellType.NUMERIC == cell.getCellType()) {

			return cell.getNumericCellValue();
		}
		else if (DateUtil.isCellDateFormatted(cell)) {

			return new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE).format(
				cell.getDateCellValue());
		}
		else {

			return null;
		}
	}

	public static JSONObject buildDossierJSONObject(ResultSet rs) {

		JSONObject objectData = JSONFactoryUtil.createJSONObject();

		try {
			objectData.put(TEMP_UUID_, rs.getString(TEMP_UUID_));

			// objectData.put(TEMP_DOSSIERID, rs.getString(TEMP_DOSSIERID));
			objectData.put(TEMP_GROUPID, rs.getString(TEMP_GROUPID));
			objectData.put(TEMP_COMPANYID, rs.getString(TEMP_COMPANYID));
			objectData.put(TEMP_USERID, rs.getString(TEMP_USERID));
			objectData.put(TEMP_USERNAME, rs.getString(TEMP_USERNAME));
			objectData.put(
				TEMP_CREATEDATE,
				convertStringToDate(rs.getString(TEMP_CREATEDATE)));
			objectData.put(
				TEMP_MODIFIEDDATE,
				convertStringToDate(rs.getString(TEMP_MODIFIEDDATE)));
			objectData.put(TEMP_REFERENCEUID, rs.getString(TEMP_REFERENCEUID));
			objectData.put(TEMP_COUNTER, rs.getString(TEMP_COUNTER));
			objectData.put(TEMP_SERVICECODE, rs.getString(TEMP_SERVICECODE));
			objectData.put(TEMP_SERVICENAME, rs.getString(TEMP_SERVICENAME));
			objectData.put(
				TEMP_GOVAGENCYCODE, rs.getString(TEMP_GOVAGENCYCODE));
			objectData.put(
				TEMP_GOVAGENCYNAME, rs.getString(TEMP_GOVAGENCYNAME));
			objectData.put(
				TEMP_APPLICANTNAME, rs.getString(TEMP_APPLICANTNAME));
			objectData.put(
				TEMP_APPLICANTIDTYPE, rs.getString(TEMP_APPLICANTIDTYPE));
			objectData.put(
				TEMP_APPLICANTIDNO, rs.getString(TEMP_APPLICANTIDNO));
			objectData.put(
				TEMP_APPLICANTIDDATE,
				convertStringToDate(rs.getString(TEMP_APPLICANTIDDATE)));
			objectData.put(TEMP_ADDRESS, rs.getString(TEMP_ADDRESS));
			objectData.put(TEMP_CITYCODE, rs.getString(TEMP_CITYCODE));
			objectData.put(TEMP_CITYNAME, rs.getString(TEMP_CITYNAME));
			objectData.put(TEMP_DISTRICTCODE, rs.getString(TEMP_DISTRICTCODE));
			objectData.put(TEMP_DISTRICTNAME, rs.getString(TEMP_DISTRICTNAME));
			objectData.put(TEMP_WARDCODE, rs.getString(TEMP_WARDCODE));
			objectData.put(TEMP_WARDNAME, rs.getString(TEMP_WARDNAME));
			objectData.put(TEMP_CONTACTNAME, rs.getString(TEMP_CONTACTNAME));
			objectData.put(TEMP_CONTACTTELNO, rs.getString(TEMP_CONTACTTELNO));
			objectData.put(TEMP_CONTACTEMAIL, rs.getString(TEMP_CONTACTEMAIL));
			objectData.put(
				TEMP_DOSSIERTEMPLATENO, rs.getString(TEMP_DOSSIERTEMPLATENO));
			objectData.put(
				TEMP_DOSSIERTEMPLATENAME,
				rs.getString(TEMP_DOSSIERTEMPLATENAME));
			objectData.put(TEMP_DOSSIERNOTE, rs.getString(TEMP_DOSSIERNOTE));
			objectData.put(
				TEMP_SUBMISSIONNOTE, rs.getString(TEMP_SUBMISSIONNOTE));
			objectData.put(
				TEMP_APPLICANTNOTE, rs.getString(TEMP_APPLICANTNOTE));
			objectData.put(TEMP_BRIEFNOTE, rs.getString(TEMP_BRIEFNOTE));
			objectData.put(TEMP_DOSSIERNO, rs.getString(TEMP_DOSSIERNO));
			objectData.put(TEMP_SUBMITTING, rs.getString(TEMP_SUBMITTING));
			_log.info(rs.getString(TEMP_SUBMITDATE));
			objectData.put(
				TEMP_SUBMITDATE,
				convertStringToDate(rs.getString(TEMP_SUBMITDATE)));
			objectData.put(
				TEMP_RECEIVEDATE,
				convertStringToDate(rs.getString(TEMP_RECEIVEDATE)));
			objectData.put(
				TEMP_DUEDATE, convertStringToDate(rs.getString(TEMP_DUEDATE)));
			objectData.put(
				TEMP_RELEASEDATE,
				convertStringToDate(rs.getString(TEMP_RELEASEDATE)));
			objectData.put(
				TEMP_FINISHDATE,
				convertStringToDate(rs.getString(TEMP_FINISHDATE)));
			objectData.put(
				TEMP_CANCELLINGDATE,
				convertStringToDate(rs.getString(TEMP_CANCELLINGDATE)));
			objectData.put(
				TEMP_CORRECTTINGDATE,
				convertStringToDate(rs.getString(TEMP_CORRECTTINGDATE)));
			objectData.put(
				TEMP_DOSSIERSTATUS, rs.getString(TEMP_DOSSIERSTATUS));
			objectData.put(
				TEMP_DOSSIERSTATUSTEXT, rs.getString(TEMP_DOSSIERSTATUSTEXT));
			objectData.put(
				TEMP_DOSSIERSUBSTATUS, rs.getString(TEMP_DOSSIERSUBSTATUS));
			objectData.put(
				TEMP_DOSSIERSUBSTATUSTEXT,
				rs.getString(TEMP_DOSSIERSUBSTATUSTEXT));
			objectData.put(TEMP_FOLDERID, rs.getString(TEMP_FOLDERID));
			objectData.put(
				TEMP_DOSSIERACTIONID, rs.getString(TEMP_DOSSIERACTIONID));
			objectData.put(TEMP_VIAPOST, rs.getString(TEMP_VIAPOST));
			objectData.put(TEMP_POSTADDRESS, rs.getString(TEMP_POSTADDRESS));
			objectData.put(TEMP_PASSWORD_, rs.getString("password_"));
			objectData.put(TEMP_NOTIFICATION, rs.getString(TEMP_NOTIFICATION));
			objectData.put(TEMP_VIAPOSTAL, rs.getString(TEMP_VIAPOSTAL));
			objectData.put(
				TEMP_POSTALADDRESS, rs.getString(TEMP_POSTALADDRESS));
			objectData.put(
				TEMP_POSTALCITYCODE, rs.getString(TEMP_POSTALCITYCODE));
			objectData.put(
				TEMP_POSTALCITYNAME, rs.getString(TEMP_POSTALCITYNAME));
			objectData.put(TEMP_POSTALTELNO, rs.getString(TEMP_POSTALTELNO));
			objectData.put(TEMP_ONLINE_, rs.getString(TEMP_ONLINE_));
			objectData.put(TEMP_ONLINE_, true);
			objectData.put("online", true);
			objectData.put(TEMP_SERVERNO, rs.getString(TEMP_SERVERNO));
			objectData.put(
				TEMP_ENDORSEMENTDATE,
				convertStringToDate(rs.getString(TEMP_ENDORSEMENTDATE)));
			objectData.put(TEMP_LOCKSTATE, rs.getString(TEMP_LOCKSTATE));
			objectData.put(
				TEMP_DOSSIERREGISTER, rs.getString(TEMP_DOSSIERREGISTER));
			objectData.put(TEMP_DELEGATENAME, rs.getString(TEMP_DELEGATENAME));
			objectData.put(TEMP_DELEGATEIDNO, rs.getString(TEMP_DELEGATEIDNO));
			objectData.put(
				TEMP_DELEGATETELNO, rs.getString(TEMP_DELEGATETELNO));
			objectData.put(
				TEMP_DELEGATEEMAIL, rs.getString(TEMP_DELEGATEEMAIL));
			objectData.put(
				TEMP_DELEGATEADDRESS, rs.getString(TEMP_DELEGATEADDRESS));
			objectData.put(
				TEMP_DELEGATECITYCODE, rs.getString(TEMP_DELEGATECITYCODE));
			objectData.put(
				TEMP_DELEGATECITYNAME, rs.getString(TEMP_DELEGATECITYNAME));
			objectData.put(
				TEMP_DELEGATEDISTRICTCODE,
				rs.getString(TEMP_DELEGATEDISTRICTCODE));
			objectData.put(
				TEMP_DELEGATEDISTRICTNAME,
				rs.getString(TEMP_DELEGATEDISTRICTNAME));
			objectData.put(
				TEMP_DELEGATEWARDCODE, rs.getString(TEMP_DELEGATEWARDCODE));
			objectData.put(
				TEMP_DELEGATEWARDNAME, rs.getString(TEMP_DELEGATEWARDNAME));
			objectData.put(
				TEMP_EXTENDDATE,
				convertStringToDate(rs.getString(TEMP_EXTENDDATE)));
			objectData.put(
				TEMP_POSTALSERVICECODE, rs.getString(TEMP_POSTALSERVICECODE));
			objectData.put(
				TEMP_POSTALSERVICENAME, rs.getString(TEMP_POSTALSERVICENAME));
			objectData.put(
				TEMP_POSTALDISTRICTCODE, rs.getString(TEMP_POSTALDISTRICTCODE));
			objectData.put(
				TEMP_POSTALDISTRICTNAME, rs.getString(TEMP_POSTALDISTRICTNAME));
			objectData.put(
				TEMP_POSTALWARDCODE, rs.getString(TEMP_POSTALWARDCODE));
			objectData.put(
				TEMP_POSTALWARDNAME, rs.getString(TEMP_POSTALWARDNAME));
			objectData.put(TEMP_ORIGINAL, false); // boolean
			objectData.put(
				TEMP_REGISTERBOOKCODE, rs.getString(TEMP_REGISTERBOOKCODE));
			objectData.put(
				TEMP_REGISTERBOOKNAME, rs.getString(TEMP_REGISTERBOOKNAME));
			objectData.put(TEMP_PROCESSNO, rs.getString(TEMP_PROCESSNO));
			objectData.put(TEMP_ORIGINALITY, rs.getString(TEMP_ORIGINALITY));
			objectData.put(
				TEMP_ORIGINDOSSIERID, rs.getString(TEMP_ORIGINDOSSIERID));
			objectData.put(TEMP_PROCESSDATE, rs.getString(TEMP_PROCESSDATE));
			objectData.put(
				TEMP_DURATIONCOUNT, rs.getString(TEMP_DURATIONCOUNT));
			objectData.put(TEMP_DURATIONUNIT, rs.getString(TEMP_DURATIONUNIT));
			objectData.put(TEMP_SAMPLECOUNT, rs.getString(TEMP_SAMPLECOUNT));
			objectData.put(TEMP_DOSSIERNAME, rs.getString(TEMP_DOSSIERNAME));
			objectData.put(
				TEMP_ORIGINDOSSIERNO, rs.getString(TEMP_ORIGINDOSSIERNO));
			objectData.put(
				TEMP_GROUPDOSSIERID, rs.getString(TEMP_GROUPDOSSIERID));
			objectData.put(TEMP_METADATA, rs.getString(TEMP_METADATA));
			objectData.put(TEMP_DELEGATETYPE, rs.getString(TEMP_DELEGATETYPE));
			objectData.put(TEMP_DOCUMENTNO, rs.getString(TEMP_DOCUMENTNO));
			objectData.put(TEMP_DOCUMENTDATE, rs.getString(TEMP_DOCUMENTDATE));
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

		return objectData;

	}

	public static JSONObject buildDossierFileJSONObject(ResultSet rs) {

		JSONObject objectData = JSONFactoryUtil.createJSONObject();

		try {
			objectData.put("groupId", rs.getString("groupId"));
			objectData.put("dossierNo", rs.getString("dossierNo"));
			objectData.put("partNo", rs.getString("partNo"));
			objectData.put("partName", rs.getString("partName"));
			objectData.put("fileName", rs.getString("fileName"));
			objectData.put("dossierPartType", rs.getString("dossierPartType"));
			objectData.put("fileUrl", rs.getString("fileUrl"));

		}
		catch (SQLException e) {

			e.printStackTrace();
		}

		return objectData;

	}

	static Long convertStringToDate(String result) {

		if (result != null &&
			result.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {

			try {

				return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(
					result).getTime();
			}
			catch (ParseException e) {

				return null;
			}
		}

		return null;
	}

}
