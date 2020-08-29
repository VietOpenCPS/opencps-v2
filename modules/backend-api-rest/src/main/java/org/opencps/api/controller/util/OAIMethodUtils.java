package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
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
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.oai.model.oaipmh.HeaderType;
import org.opencps.api.oai.model.oaipmh.ListRecordsType;
import org.opencps.api.oai.model.oaipmh.MetadataType;
import org.opencps.api.oai.model.oaipmh.OAIPMHerrorType;
import org.opencps.api.oai.model.oaipmh.OAIPMHerrorcodeType;
import org.opencps.api.oai.model.oaipmh.OAIPMHtype;
import org.opencps.api.oai.model.oaipmh.OMDeliverable;
import org.opencps.api.oai.model.oaipmh.OMDeliverableType;
import org.opencps.api.oai.model.oaipmh.OMEmployee;
import org.opencps.api.oai.model.oaipmh.OMRole;
import org.opencps.api.oai.model.oaipmh.OMRoles;
import org.opencps.api.oai.model.oaipmh.RecordType;
import org.opencps.api.oai.model.oaipmh.RequestType;
import org.opencps.api.oai.model.oaipmh.ResumptionTokenType;
import org.opencps.api.oai.model.oaipmh.VerbType;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ModelKeysDeliverable;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.kernel.prop.PropValues;
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
	public static int PAGE_SIZE = 100;
	public static String OPS_USER = "OPS_USER";
	public static String OPS_ROLE = "OPS_ROLE";
	public static String OPS_DELI_TYPE = "OPS_DELI_TYPE";
	public static String OPS_DELI = "OPS_DELI";
	String domain = PropsUtil.get("company.default.web.id");

	private String identifierBuider(String identifierCode, String identifierValue) {
		return OAI + StringPool.COLON + domain + StringPool.COLON + identifierCode + StringPool.FORWARD_SLASH
				+ identifierValue;
	}

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

		ListRecordsType listRecordsType = buildRecords(query.getSet(), query.getMetadataPrefix(),
				query.getResumptionToken(), query.getFrom(), query.getUntil(), query.getDeliverableType());
		results.setListRecords(listRecordsType);

		return results;
	}

	private ListRecordsType buildRecords(String set, String companyId, String pageNum, String from, String until,
			String deliverableType) {
		ListRecordsType results = new ListRecordsType();

		if (OPS_USER.equals(set)) {

			_log.debug("RUN getUserListRecords");
			return getUserListRecords(companyId, pageNum, from, until);

		} else if (OPS_ROLE.equals(set)) {

			_log.debug("RUN getRoleListRecords");
			return getRoleListRecords(companyId, pageNum, from, until);

		} else if (OPS_DELI_TYPE.equals(set)) {

			_log.debug("RUN getDeliverableTypeListRecords");
			return getDeliverableTypeListRecords(companyId, pageNum, from, until);
		} else if (OPS_DELI.equals(set)) {

			_log.debug("RUN getDeliverableListRecords");
			return getDeliverableListRecords(companyId, pageNum, from, until, deliverableType);
		}

		return results;
	}

	private ListRecordsType getUserListRecords(String companyId, String page, String from, String until) {

		ListRecordsType results = new ListRecordsType();

		try {

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(Field.MODIFIED_DATE, Sort.LONG_TYPE, true) };
			SearchContext searchContext = new SearchContext();
			Indexer<Employee> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Employee.class);
			BooleanQuery booleanQuery = null;

			long companyIdValid = GetterUtil.getLong(companyId, 20099);
			int pageValid = GetterUtil.getInteger(page, 0);
			searchContext.setCompanyId(companyIdValid);
			searchContext.addFullQueryEntryClassName(Employee.class.getName());
			searchContext.setEntryClassNames(new String[] { Employee.class.getName() });
			searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
			searchContext.setLike(true);
			int start = pageValid * PAGE_SIZE;
			int end = start + PAGE_SIZE;
			searchContext.setStart(start);
			searchContext.setEnd(end);
			searchContext.setAndSearch(true);
			searchContext.setSorts(sorts);

			booleanQuery = indexer.getFullQuery(searchContext);
			booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Employee.class.getName());
			if (Validator.isNotNull(from) && Validator.isNotNull(until)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						APIDateTimeUtils.timeZone2Lucene(from), APIDateTimeUtils.timeZone2Lucene(until), true, true);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else if (Validator.isNotNull(from)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						APIDateTimeUtils.timeZone2Lucene(from), null, true, false);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else if (Validator.isNotNull(until)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						null, APIDateTimeUtils.timeZone2Lucene(until), false, true);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}

			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			long completeListSize = IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
			List<Document> listDocument = hits.toList();

			_log.debug("listDocument.size=" + listDocument.size());
			if (listDocument.size() == PAGE_SIZE && completeListSize > end) {

				ResumptionTokenType rusumptionToken = new ResumptionTokenType();
				rusumptionToken.setValue(String.valueOf(pageValid + 1));
				rusumptionToken.setCompleteListSize(new BigInteger(String.valueOf(completeListSize)));
				results.setResumptionToken(rusumptionToken);
			}
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
				headerType.setIdentifier(identifierBuider(OPS_USER, document.get(EmployeeTerm.EMPLOYEE_NO)));
				headerType.setDatestamp(Validator.isNotNull(document.getDate(Field.MODIFIED_DATE))
						? APIDateTimeUtils._dateToString(document.getDate(Field.MODIFIED_DATE),
								APIDateTimeUtils.ISO8601)
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

	private ListRecordsType getRoleListRecords(String companyId, String page, String from, String until) {

		ListRecordsType results = new ListRecordsType();

		try {

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(Field.MODIFIED_DATE, Sort.LONG_TYPE, true) };
			SearchContext searchContext = new SearchContext();
			Indexer<JobPos> indexer = IndexerRegistryUtil.nullSafeGetIndexer(JobPos.class);
			BooleanQuery booleanQuery = null;

			long companyIdValid = GetterUtil.getLong(companyId, 20099);
			int pageValid = GetterUtil.getInteger(page, 0);
			searchContext.setCompanyId(companyIdValid);
			searchContext.addFullQueryEntryClassName(JobPos.class.getName());
			searchContext.setEntryClassNames(new String[] { JobPos.class.getName() });
			searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
			searchContext.setLike(true);
			int start = pageValid * PAGE_SIZE;
			int end = start + PAGE_SIZE;
			searchContext.setStart(start);
			searchContext.setEnd(end);
			searchContext.setAndSearch(true);
			searchContext.setSorts(sorts);

			booleanQuery = indexer.getFullQuery(searchContext);
			booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, JobPos.class.getName());
			if (Validator.isNotNull(from) && Validator.isNotNull(until)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						APIDateTimeUtils.timeZone2Lucene(from), APIDateTimeUtils.timeZone2Lucene(until), true, true);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else if (Validator.isNotNull(from)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						APIDateTimeUtils.timeZone2Lucene(from), null, true, false);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else if (Validator.isNotNull(until)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						null, APIDateTimeUtils.timeZone2Lucene(until), false, true);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}

			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			List<Document> listDocument = hits.toList();
			long completeListSize = IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

			_log.debug("listDocument.size=" + listDocument.size());
			if (listDocument.size() == PAGE_SIZE && completeListSize > end) {

				ResumptionTokenType rusumptionToken = new ResumptionTokenType();
				rusumptionToken.setValue(String.valueOf(pageValid + 1));
				rusumptionToken.setCompleteListSize(new BigInteger(String.valueOf(completeListSize)));
				results.setResumptionToken(rusumptionToken);
			}
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
				headerType.setIdentifier(identifierBuider(OPS_ROLE, document.get(JobPosTerm.JOBPOS_ID)));
				headerType.setDatestamp(Validator.isNotNull(document.getDate(Field.MODIFIED_DATE))
						? APIDateTimeUtils._dateToString(document.getDate(Field.MODIFIED_DATE),
								APIDateTimeUtils.ISO8601)
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

	private ListRecordsType getDeliverableListRecords(String companyId, String page, String from, String until,
			String deliverableType) {

		ListRecordsType results = new ListRecordsType();

		try {

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(Field.MODIFIED_DATE, Sort.LONG_TYPE, true) };
			SearchContext searchContext = new SearchContext();
			Indexer<Deliverable> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);
			BooleanQuery booleanQuery = null;

			long companyIdValid = GetterUtil.getLong(companyId, 20099);
			int pageValid = GetterUtil.getInteger(page, 0);
			searchContext.setCompanyId(companyIdValid);
			searchContext.addFullQueryEntryClassName(Deliverable.class.getName());
			searchContext.setEntryClassNames(new String[] { Deliverable.class.getName() });
			searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
			searchContext.setLike(true);
			int start = pageValid * PAGE_SIZE;
			int end = start + PAGE_SIZE;
			searchContext.setStart(start);
			searchContext.setEnd(end);
			searchContext.setAndSearch(true);
			searchContext.setSorts(sorts);

			booleanQuery = indexer.getFullQuery(searchContext);
			booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Deliverable.class.getName());
			if (Validator.isNotNull(from) && Validator.isNotNull(until)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						APIDateTimeUtils.timeZone2Lucene(from), APIDateTimeUtils.timeZone2Lucene(until), true, true);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else if (Validator.isNotNull(from)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						APIDateTimeUtils.timeZone2Lucene(from), null, true, false);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else if (Validator.isNotNull(until)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						null, APIDateTimeUtils.timeZone2Lucene(until), false, true);
				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
			if (Validator.isNotNull(deliverableType)) {
				MultiMatchQuery query = new MultiMatchQuery(deliverableType);
				query.addFields(DeliverableTerm.DELIVERABLE_TYPE);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}

			Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
			List<Document> listDocument = hits.toList();
			long completeListSize = IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

			_log.debug("listDocument.size=" + listDocument.size());
			if (listDocument.size() == PAGE_SIZE && completeListSize > end) {

				ResumptionTokenType rusumptionToken = new ResumptionTokenType();
				rusumptionToken.setValue(String.valueOf(pageValid + 1));
				rusumptionToken.setCompleteListSize(new BigInteger(String.valueOf(completeListSize)));
				results.setResumptionToken(rusumptionToken);
			}
			OMDeliverable oMDeliverable = null;

			for (Document document : listDocument) {

				String deliverableId = document.get(DeliverableTerm.DELIVERABLE_ID);
				String groupId = document.get(Field.GROUP_ID);

				oMDeliverable = new OMDeliverable();
				oMDeliverable.setGroupId(Long.parseLong(groupId));
				oMDeliverable.setDeliverableId(Long.parseLong(deliverableId));
				oMDeliverable.setModifiedDate(String.valueOf(document.getDate(Field.MODIFIED_DATE).getTime()));
				oMDeliverable.setDeliverableCode(document.get(DeliverableTerm.DELIVERABLE_CODE));
				oMDeliverable.setDeliverableName(document.get(DeliverableTerm.DELIVERABLE_NAME));
				oMDeliverable.setDeliverableType(document.get(DeliverableTerm.DELIVERABLE_TYPE));
				oMDeliverable.setGovAgencyCode(document.get(DeliverableTerm.GOV_AGENCY_CODE));
				oMDeliverable.setGovAgencyName(document.get(DeliverableTerm.GOV_AGENCY_NAME));
				oMDeliverable.setApplicantIdNo(document.get(DeliverableTerm.APPLICANT_ID_NO));
				oMDeliverable.setApplicantName(document.get(DeliverableTerm.APPLICANT_NAME));
				oMDeliverable.setSubject(document.get(DeliverableTerm.SUBJECT));
				oMDeliverable.setFormData(document.get(DeliverableTerm.FORM_DATA));
				oMDeliverable.setFormScript(document.get(DeliverableTerm.FORM_SCRIPT));
				oMDeliverable.setFormReport(document.get(DeliverableTerm.FORM_REPORT));
				oMDeliverable.setIssueDate(document.get(DeliverableTerm.ISSUE_DATE));
				long expire = GetterUtil.getLong(document.get(DeliverableTerm.EXPIRE_DATE));
				oMDeliverable.setExpireDate(String.valueOf(expire));
				oMDeliverable.setRevalidate(document.get(DeliverableTerm.REVALIDATE));
				int deliverableState = GetterUtil.getInteger(document.get(DeliverableTerm.DELIVERABLE_STATE));
				if (deliverableState == 1 && expire > 0 && new Date().getTime() > deliverableState) {

					deliverableState = 2;
				}
				oMDeliverable.setDeliverableState(String.valueOf(deliverableState));
				long dossierId = GetterUtil.getLong(document.get(DossierTerm.DOSSIER_ID));
				oMDeliverable.setDossierId(dossierId);
				oMDeliverable.setDomain(PropValues.PORTAL_DOMAIN);

				long fileEntryId = 0;
				if (Validator.isNotNull(document.get(DeliverableTerm.FILE_ATTACHS))) {
					String fileEntrys = document.get(DeliverableTerm.FILE_ATTACHS);
					String[] files = fileEntrys.split(StringPool.COMMA);
					fileEntryId = GetterUtil.getLong(files[files.length - 1]);
				} else {
					fileEntryId = GetterUtil.getLong(document.get(ModelKeysDeliverable.FILEENTRYID));
				}
				if (Validator.isNotNull(fileEntryId)) {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
					String path = "documents/" + fileEntry.getGroupId() + StringPool.FORWARD_SLASH
							+ fileEntry.getFolderId() + StringPool.FORWARD_SLASH + fileEntry.getTitle();
					oMDeliverable.setFilePath(path);
				} else {
					oMDeliverable.setFilePath(StringPool.BLANK);
				}

				oMDeliverable.setReferenceUid(StringPool.BLANK);
				if (dossierId > 0) {

					Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
					if (Validator.isNotNull(dossier)) {
						oMDeliverable.setReferenceUid(dossier.getReferenceUid());
					}
				}

				HeaderType headerType = new HeaderType();
				headerType.setIdentifier(identifierBuider(OPS_DELI, document.get(DeliverableTerm.DELIVERABLE_ID)));
				headerType.setDatestamp(Validator.isNotNull(document.getDate(Field.MODIFIED_DATE))
						? APIDateTimeUtils._dateToString(document.getDate(Field.MODIFIED_DATE),
								APIDateTimeUtils.ISO8601)
						: StringPool.BLANK);

				MetadataType metadataType = new MetadataType();
				metadataType.setDeliverable(oMDeliverable);

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

	private ListRecordsType getDeliverableTypeListRecords(String companyId, String page, String from, String until) {

		ListRecordsType results = new ListRecordsType();

		try {

			long companyIdValid = GetterUtil.getLong(companyId, 20099);
//			long pageValid = GetterUtil.getInteger(page, 0);
			List<DeliverableType> deliverableTypes = DeliverableTypeLocalServiceUtil
					.getAllDeliverableTypes(companyIdValid);
			OMDeliverableType oMDeliverableType = null;

			_log.debug("listDocument.size=" + deliverableTypes.size());
			for (DeliverableType deliverableType : deliverableTypes) {

				String createDate = Validator.isNotNull(deliverableType.getCreateDate()) ? APIDateTimeUtils
						._dateToString(deliverableType.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK;
				String modifiedDate = Validator.isNotNull(deliverableType.getModifiedDate()) ? APIDateTimeUtils
						._dateToString(deliverableType.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
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
				headerType.setIdentifier(
						identifierBuider(OPS_DELI_TYPE, oMDeliverableType.getDeliverableTypeId().toString()));
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
