package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
import org.opencps.api.oai.model.oaipmh.*;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ModelKeysDeliverable;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.kernel.prop.PropValues;
import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.constants.JobPosTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.utils.APIDateTimeUtils;

public class OAIMethodUtils {

	private static final Log _log = LogFactoryUtil.getLog(OAIMethodUtils.class);

	public static String OAI = "oai";
	public static int PAGE_SIZE = 100;
	public static int PAGE_END = 10000;
	public static String OPS_USER = "OPS_USER";
	public static String OPS_ROLE = "OPS_ROLE";
	public static String OPS_DELI_TYPE = "OPS_DELI_TYPE";
	public static String OPS_DELI = "OPS_DELI";
	String domain = PropsUtil.get("company.default.web.id");
	public static String APPLICANT = "APPLICANT";
	public static String CONG_DAN = "Công dân";

	private String identifierBuider(String identifierCode, String identifierValue) {
		return OAI + StringPool.COLON + domain + StringPool.COLON + identifierCode + StringPool.FORWARD_SLASH
				+ identifierValue;
	}

	public OAIPMHtype calculateIdentify(RequestType query) {

		_log.debug(VerbType.IDENTIFY);
		return buildBadReq();
	}

	public OAIPMHtype calculateListMetadataFormats(RequestType query) {

		_log.debug(VerbType.LIST_METADATA_FORMATS);
		return buildBadReq();
	}

	public OAIPMHtype calculateListSets(RequestType query) {

		_log.debug(VerbType.LIST_SETS);
		return buildBadReq();
	}

	public OAIPMHtype calculateGetRecord(RequestType query) {

		_log.debug(VerbType.GET_RECORD);
		return buildBadReq();
	}

	public OAIPMHtype calculateListIdentifiers(RequestType query) {

		_log.debug(VerbType.LIST_IDENTIFIERS);
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
			Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);
			BooleanQuery booleanQuery = null;

			long companyIdValid = GetterUtil.getLong(companyId, 20099);
			int pageValid = GetterUtil.getInteger(page, 0);
			searchContext.setCompanyId(companyIdValid);
			searchContext.addFullQueryEntryClassName(User.class.getName());
			searchContext.setEntryClassNames(new String[] { User.class.getName() });
			searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
			searchContext.setLike(true);
			int start = pageValid * PAGE_SIZE;
			int end = PAGE_END;
			searchContext.setStart(start);
			searchContext.setEnd(end);
			searchContext.setAndSearch(true);
			searchContext.setSorts(sorts);

			booleanQuery = indexer.getFullQuery(searchContext);
			booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, User.class.getName());
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

			if (listDocument.size() == PAGE_SIZE && completeListSize > end) {

				ResumptionTokenType rusumptionToken = new ResumptionTokenType();
				rusumptionToken.setValue(String.valueOf(pageValid + 1));
				rusumptionToken.setCompleteListSize(new BigInteger(String.valueOf(completeListSize)));
				results.setResumptionToken(rusumptionToken);
			}
			OMEmployee oMEmployee = null;
			OMRoles oMRoles = null;
			OMRole oMRole = null;
			OMApplicant omApplicant = null;


			for (Document document : listDocument) {

				HeaderType headerType = new HeaderType();
				MetadataType metadataType = new MetadataType();
				RecordType recordType = new RecordType();
				Long userId = Long.valueOf(document.get(EmployeeTerm.USER_ID));

				Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);


				if (Validator.isNotNull(employee)) {

					oMEmployee = new OMEmployee();
					Long empId = employee.getEmployeeId();

					oMEmployee.setGroupId(employee.getGroupId());
					oMEmployee.setFullName(employee.getFullName());
					oMEmployee.setTelNo(employee.getTelNo());
					oMEmployee.setEmail(employee.getEmail());
					oMEmployee.setWorkingStatus(String.valueOf(employee.getWorkingStatus()));

					long mappingUserId = Validator.isNotNull(employee.getMappingUserId()) ? employee.getMappingUserId() : 0L;
					User user = UserLocalServiceUtil.fetchUser(mappingUserId);

					if (Validator.isNotNull(user)) {
						oMEmployee.setScreenName(user.getScreenName());
					}

					List<EmployeeJobPos> empJobPosList = EmployeeJobPosLocalServiceUtil
							.findByF_EmployeeId(empId);
					oMRoles = new OMRoles();
					for (EmployeeJobPos empJobPos : empJobPosList) {

						JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(empJobPos.getJobPostId());
						oMRole = new OMRole();
						if(Validator.isNotNull(jobPos)) {
							oMRole.setGroupId(jobPos.getGroupId() != 0 ? jobPos.getGroupId() : 0L);
							oMRole.setRoleId(jobPos.getJobPosId());
							oMRole.setRoleCode(jobPos.getJobPosCode());
							oMRole.setRoleTitle(jobPos.getTitle());

							oMRoles.getRole().add(oMRole);
						}
					}
					oMEmployee.setRoles(oMRoles);

					headerType.setIdentifier(identifierBuider(OPS_USER, employee.getEmployeeNo()));
					headerType.setDatestamp(Validator.isNotNull(employee.getModifiedDate())
							? APIDateTimeUtils._dateToString(employee.getModifiedDate(),
							APIDateTimeUtils.ISO8601)
							: StringPool.BLANK);

					metadataType.setUser(oMEmployee);
					recordType.setHeader(headerType);
					recordType.setMetadata(metadataType);
					results.getRecord().add(recordType);
				}else{
					Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(userId);
					if(Validator.isNull(applicant)){
						continue;
					}
					omApplicant = new OMApplicant();
					String modifiedDate = Validator.isNotNull(applicant.getModifiedDate()) ? APIDateTimeUtils
							._dateToString(applicant.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
							: StringPool.BLANK;
					omApplicant.setGroupId(applicant.getGroupId());
					omApplicant.setEmail(applicant.getContactEmail());
					omApplicant.setFullName(applicant.getApplicantName());
					omApplicant.setTelNo(applicant.getContactTelNo());
					omApplicant.setWorkingStatus(1L);

					User user = UserLocalServiceUtil.fetchUser(applicant.getMappingUserId());

					if (Validator.isNotNull(user)) {
						omApplicant.setScreenName(user.getScreenName());
					}

					oMRole = new OMRole();
					oMRoles = new OMRoles();
					oMRole.setGroupId(applicant.getGroupId());
					oMRole.setRoleCode(APPLICANT);
					oMRole.setRoleTitle(CONG_DAN);
					oMRoles.getRole().add(oMRole);
					omApplicant.setRoles(oMRoles);


					headerType.setIdentifier(
							identifierBuider(OPS_USER, String.valueOf(applicant.getApplicantId())));
					headerType.setDatestamp(modifiedDate);
					metadataType.setUserApp(omApplicant);
					recordType.setHeader(headerType);
					recordType.setMetadata(metadataType);

					results.getRecord().add(recordType);
				}
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
		long deliverableStateL = 1L;

		try {
			List<Deliverable> deliverables = DeliverableLocalServiceUtil.findDeliverableByCreateDate(from, until, deliverableType, deliverableStateL);

			_log.info("ListDeliverable: " + deliverables.size());
			if (!deliverables.isEmpty() && deliverables.size() > 0) {
			OMDeliverable oMDeliverable = null;

			for (Deliverable deliverable : deliverables) {

				Long deliverableId = deliverable.getDeliverableId();
				Long groupId = deliverable.getGroupId();

				oMDeliverable = new OMDeliverable();
				oMDeliverable.setGroupId(groupId);
				oMDeliverable.setDeliverableId(deliverableId);
				oMDeliverable.setModifiedDate(String.valueOf(deliverable.getModifiedDate()));
				oMDeliverable.setDeliverableCode(deliverable.getDeliverableCode());
				oMDeliverable.setDeliverableName(deliverable.getDeliverableName());
				oMDeliverable.setDeliverableType(deliverable.getDeliverableType());
				oMDeliverable.setGovAgencyCode(deliverable.getGovAgencyCode());
				oMDeliverable.setGovAgencyName(deliverable.getGovAgencyName());
				oMDeliverable.setApplicantIdNo(deliverable.getApplicantIdNo());
				oMDeliverable.setApplicantName(deliverable.getApplicantName());
				oMDeliverable.setSubject(deliverable.getSubject());
				oMDeliverable.setFormData(deliverable.getFormData());
				oMDeliverable.setFormScript(deliverable.getFormScript());
				oMDeliverable.setFormReport(deliverable.getFormReport());
				oMDeliverable.setIssueDate(String.valueOf(deliverable.getIssueDate()));
				long expire = GetterUtil.getLong(deliverable.getExpireDate());
				oMDeliverable.setExpireDate(String.valueOf(expire));
				oMDeliverable.setRevalidate(String.valueOf(deliverable.getRevalidate()));
				int deliverableState = GetterUtil.getInteger(deliverable.getDeliverableState());
				if (deliverableState == 1 && expire > 0 && new Date().getTime() > deliverableState) {

					deliverableState = 2;
				}
				oMDeliverable.setDeliverableState(String.valueOf(deliverableState));
				long dossierId = GetterUtil.getLong(deliverable.getDossierId());
				oMDeliverable.setDossierId(dossierId);
				oMDeliverable.setDomain(PropValues.PORTAL_DOMAIN);

				long fileEntryId = 0;
				if (Validator.isNotNull(deliverable.getFileAttachs())) {
					String fileEntrys = deliverable.getFileAttachs();
					String[] files = fileEntrys.split(StringPool.COMMA);
					fileEntryId = GetterUtil.getLong(files[files.length - 1]);
				} else {
					fileEntryId = GetterUtil.getLong(deliverable.getFileEntryId());
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
				headerType.setIdentifier(identifierBuider(OPS_DELI, String.valueOf(deliverable.getDeliverableId())));
				headerType.setDatestamp(Validator.isNotNull(deliverable.getModifiedDate())
						? APIDateTimeUtils._dateToString(deliverable.getModifiedDate(),
						APIDateTimeUtils.ISO8601)
						: StringPool.BLANK);

				MetadataType metadataType = new MetadataType();
				metadataType.setDeliverable(oMDeliverable);

				RecordType recordType = new RecordType();
				recordType.setHeader(headerType);
				recordType.setMetadata(metadataType);

				results.getRecord().add(recordType);
			}
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
