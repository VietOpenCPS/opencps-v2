package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.opencps.api.oai.model.oaipmh.HeaderType;
import org.opencps.api.oai.model.oaipmh.ListRecordsType;
import org.opencps.api.oai.model.oaipmh.MetadataType;
import org.opencps.api.oai.model.oaipmh.OAIPMHerrorType;
import org.opencps.api.oai.model.oaipmh.OAIPMHerrorcodeType;
import org.opencps.api.oai.model.oaipmh.OAIPMHtype;
import org.opencps.api.oai.model.oaipmh.OMDeliverableType;
import org.opencps.api.oai.model.oaipmh.OMEmployee;
import org.opencps.api.oai.model.oaipmh.OMRole;
import org.opencps.api.oai.model.oaipmh.OMRoles;
import org.opencps.api.oai.model.oaipmh.RecordType;
import org.opencps.api.oai.model.oaipmh.RequestType;
import org.opencps.api.oai.model.oaipmh.VerbType;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.constants.JobPosTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.utils.APIDateTimeUtils;

public class OAIMethodUtils {

	private static final Log _log = LogFactoryUtil.getLog(OAIMethodUtils.class);

	public static String OAI = "oai";
	public static String OPS_USER = "OPS_USER";
	public static String OPS_ROLE = "OPS_ROLE";
	public static String OPS_DELI_TYPE = "OPS_DELI_TYPE";
	String domain = PropsUtil.get("company.default.web.id");

	public OAIPMHtype calculateIdentify(RequestType query) {

		System.out.println(VerbType.IDENTIFY);
		return buildBadReq();
	}

	public OAIPMHtype calculateListMetadataFormats(RequestType query) {

		System.out.println(VerbType.LIST_METADATA_FORMATS);
		return buildBadReq();
	}

	public OAIPMHtype calculateListSets(RequestType query) {

		System.out.println(VerbType.LIST_SETS);
		return buildBadReq();
	}

	public OAIPMHtype calculateGetRecord(RequestType query) {

		System.out.println(VerbType.GET_RECORD);
		return buildBadReq();
	}

	public OAIPMHtype calculateListIdentifiers(RequestType query) {

		System.out.println(VerbType.LIST_IDENTIFIERS);
		return buildBadReq();
	}

	public OAIPMHtype calculateListRecords(RequestType query) {

		_log.debug("RUN buildListRecords");
		return buildListRecords(query);
	}

	private OAIPMHtype buildBadReq() {
		OAIPMHtype results = new OAIPMHtype();

		LocalDate localDate = LocalDate.of(2019, 4, 25);

		XMLGregorianCalendar xmlGregorianCalendar = null;
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
		} catch (DatatypeConfigurationException e) {
			_log.error(e);
		}
		results.setResponseDate(xmlGregorianCalendar);

		OAIPMHerrorType oAIPMHerrorType = new OAIPMHerrorType();
		oAIPMHerrorType.setCode(OAIPMHerrorcodeType.BAD_ARGUMENT);
		oAIPMHerrorType.setValue(OAIPMHerrorcodeType.BAD_ARGUMENT.value());
		results.getError().add(oAIPMHerrorType);

		return results;
	}

	private OAIPMHtype buildListRecords(RequestType query) {
		OAIPMHtype results = new OAIPMHtype();

		LocalDate localDate = LocalDate.of(2019, 4, 25);

		XMLGregorianCalendar xmlGregorianCalendar = null;
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
		} catch (DatatypeConfigurationException e) {
			_log.error(e);
		}
		results.setResponseDate(xmlGregorianCalendar);

		ListRecordsType listRecordsType = buildRecords(query.getSet(), query.getMetadataPrefix());
		results.setListRecords(listRecordsType);

		return results;
	}

	private ListRecordsType buildRecords(String set, String metadataPrefix) {
		ListRecordsType results = new ListRecordsType();

		if (OPS_USER.equals(set)) {

			_log.debug("RUN getUserListRecords");
			return getUserListRecords(metadataPrefix);

		} else if (OPS_ROLE.equals(set)) {

			_log.debug("RUN getRoleListRecords");
			return getRoleListRecords(metadataPrefix);

		} else if (OPS_DELI_TYPE.equals(set)) {

			_log.debug("RUN getDeliverableTypeListRecords");
			return getDeliverableTypeListRecords(metadataPrefix);
		}

		return results;
	}

	private ListRecordsType getUserListRecords(String metadataPrefix) {

		ListRecordsType results = new ListRecordsType();

		try {

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(EmployeeTerm.FULL_NAME_SORTABLE, Sort.STRING_TYPE, true) };
			SearchContext searchContext = new SearchContext();
			Indexer<Employee> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Employee.class);
			BooleanQuery booleanQuery = null;

			long companyId = Validator.isNotNull(metadataPrefix) ? Long.parseLong(metadataPrefix) : 20099;
			searchContext.setCompanyId(companyId);
			searchContext.addFullQueryEntryClassName(Employee.class.getName());
			searchContext.setEntryClassNames(new String[] { Employee.class.getName() });
			searchContext.setAttribute("paginationType", "regular");
			searchContext.setLike(true);
			searchContext.setStart(QueryUtil.ALL_POS);
			searchContext.setEnd(QueryUtil.ALL_POS);
			searchContext.setAndSearch(true);
			searchContext.setSorts(sorts);

			booleanQuery = indexer.getFullQuery(searchContext);
			booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Employee.class.getName());

			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			List<Document> listDocument = hits.toList();

			_log.debug("listDocument.size=" + listDocument.size());
			OMEmployee oMEmployee = null;
			OMRoles oMRoles = null;
			OMRole oMRole = null;

			for (Document document : listDocument) {

				oMEmployee = new OMEmployee();

				String empId = document.get(EmployeeTerm.EMPLOYEE_ID);
				String groupId = document.get(Field.GROUP_ID);

				oMEmployee.setGroupId(Long.parseLong(groupId));
				oMEmployee.setFullName(document.get(EmployeeTerm.FULL_NAME));
				oMEmployee.setTelNo(document.get(EmployeeTerm.TELNO));
				oMEmployee.setEmail(document.get(EmployeeTerm.EMAIL));
				oMEmployee.setWorkingStatus(document.get(EmployeeTerm.WORKING_STATUS));

				long mappingUserId = GetterUtil.get(document.get(EmployeeTerm.MAPPING_USER_ID), 0);
				User user = UserLocalServiceUtil.fetchUser(mappingUserId);

				if (Validator.isNotNull(user)) {
					oMEmployee.setScreenName(user.getScreenName());
				}

				List<EmployeeJobPos> empJobPosList = EmployeeJobPosLocalServiceUtil
						.findByF_EmployeeId(Long.parseLong(empId));

				oMRoles = new OMRoles();
				for (EmployeeJobPos empJobPos : empJobPosList) {

					JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(empJobPos.getJobPostId());

					oMRole = new OMRole();
					oMRole.setGroupId(jobPos.getGroupId());
					oMRole.setRoleId(jobPos.getJobPosId());
					oMRole.setRoleCode(jobPos.getJobPosCode());
					oMRole.setRoleTitle(jobPos.getTitle());

					oMRoles.getRole().add(oMRole);
				}
				oMEmployee.setRoles(oMRoles);

				HeaderType headerType = new HeaderType();
				headerType.setIdentifier(OAI + StringPool.COLON + domain + StringPool.COLON + OPS_USER + "/"
						+ document.get(EmployeeTerm.EMPLOYEE_NO));
				headerType.setDatestamp(Validator.isNotNull(document.getDate(Field.MODIFIED_DATE))
						? APIDateTimeUtils.convertDateToString(document.getDate(Field.MODIFIED_DATE),
								APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);

				MetadataType metadataType = new MetadataType();
				metadataType.setUser(oMEmployee);

				RecordType recordType = new RecordType();
				recordType.setHeader(headerType);
				recordType.setMetadata(metadataType);

				results.getRecord().add(recordType);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	private ListRecordsType getRoleListRecords(String metadataPrefix) {

		ListRecordsType results = new ListRecordsType();

		try {

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(JobPosTerm.JOBPOS_CODE_SORTABLE, Sort.STRING_TYPE, true) };
			SearchContext searchContext = new SearchContext();
			Indexer<JobPos> indexer = IndexerRegistryUtil.nullSafeGetIndexer(JobPos.class);
			BooleanQuery booleanQuery = null;

			long companyId = Validator.isNotNull(metadataPrefix) ? Long.parseLong(metadataPrefix) : 20099;
			searchContext.setCompanyId(companyId);
			searchContext.addFullQueryEntryClassName(JobPos.class.getName());
			searchContext.setEntryClassNames(new String[] { JobPos.class.getName() });
			searchContext.setAttribute("paginationType", "regular");
			searchContext.setLike(true);
			searchContext.setStart(QueryUtil.ALL_POS);
			searchContext.setEnd(QueryUtil.ALL_POS);
			searchContext.setAndSearch(true);
			searchContext.setSorts(sorts);

			booleanQuery = indexer.getFullQuery(searchContext);
			booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, JobPos.class.getName());

			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			List<Document> listDocument = hits.toList();

			_log.debug("listDocument.size=" + listDocument.size());
			OMRole oMRole = null;

			for (Document document : listDocument) {

//				oMRole = new OMRole();

				String jobPosId = document.get(JobPosTerm.JOBPOS_ID);
				String groupId = document.get(Field.GROUP_ID);

				oMRole = new OMRole();
				oMRole.setGroupId(Long.parseLong(groupId));
				oMRole.setRoleId(Long.parseLong(jobPosId));
				oMRole.setRoleCode(document.get(JobPosTerm.JOBPOS_CODE));
				oMRole.setRoleTitle(document.get(JobPosTerm.TITLE));

				HeaderType headerType = new HeaderType();
				headerType.setIdentifier(OAI + StringPool.COLON + domain + StringPool.COLON + OPS_ROLE + "/"
						+ document.get(JobPosTerm.JOBPOS_ID));
				headerType.setDatestamp(Validator.isNotNull(document.getDate(Field.MODIFIED_DATE))
						? APIDateTimeUtils.convertDateToString(document.getDate(Field.MODIFIED_DATE),
								APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);

				MetadataType metadataType = new MetadataType();
				metadataType.setRole(oMRole);

				RecordType recordType = new RecordType();
				recordType.setHeader(headerType);
				recordType.setMetadata(metadataType);

				results.getRecord().add(recordType);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	private ListRecordsType getDeliverableTypeListRecords(String metadataPrefix) {

		ListRecordsType results = new ListRecordsType();

		try {

			long companyId = Validator.isNotNull(metadataPrefix) ? Long.parseLong(metadataPrefix) : 20099;
			List<DeliverableType> deliverableTypes = DeliverableTypeLocalServiceUtil.getAllDeliverableTypes(companyId);
			OMDeliverableType oMDeliverableType = null;

			_log.debug("listDocument.size=" + deliverableTypes.size());
			for (DeliverableType deliverableType : deliverableTypes) {

				String createDate = Validator.isNotNull(deliverableType.getCreateDate()) ? APIDateTimeUtils
						.convertDateToString(deliverableType.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK;
				String modifiedDate = Validator.isNotNull(deliverableType.getModifiedDate()) ? APIDateTimeUtils
						.convertDateToString(deliverableType.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK;

				oMDeliverableType = new OMDeliverableType(deliverableType.getDeliverableTypeId(),
						deliverableType.getCompanyId(), deliverableType.getGroupId(), deliverableType.getUserId(),
						deliverableType.getUserName(), createDate, modifiedDate, deliverableType.getTypeCode(),
						deliverableType.getTypeName(), deliverableType.getFormScript(), deliverableType.getFormReport(),
						deliverableType.getFormScriptFileId(), deliverableType.getFormReportFileId(),
						deliverableType.getCodePattern(), deliverableType.getDataConfig(),
						deliverableType.getTableConfig(), deliverableType.getCounter(),
						deliverableType.getMappingData(), deliverableType.getDocSync(),
						deliverableType.getGovAgencies());

				HeaderType headerType = new HeaderType();
				headerType.setIdentifier(OAI + StringPool.COLON + domain + StringPool.COLON + OPS_DELI_TYPE + "/"
						+ oMDeliverableType.getDeliverableTypeId());
				headerType.setDatestamp(modifiedDate);

				MetadataType metadataType = new MetadataType();
				metadataType.setDeliverableType(oMDeliverableType);

				RecordType recordType = new RecordType();
				recordType.setHeader(headerType);
				recordType.setMetadata(metadataType);

				results.getRecord().add(recordType);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

}
