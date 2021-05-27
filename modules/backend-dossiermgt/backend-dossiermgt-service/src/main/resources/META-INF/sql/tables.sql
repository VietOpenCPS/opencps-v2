create table opencps_accesstoken (
	accessTokenId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	token VARCHAR(255) null,
	expireDate DATE null,
	className VARCHAR(255) null
);

create table opencps_actionconfig (
	uuid_ VARCHAR(75) null,
	actionConfigId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	actionCode VARCHAR(255) null,
	actionName VARCHAR(512) null,
	extraForm BOOLEAN,
	formConfig TEXT null,
	sampleData TEXT null,
	insideProcess BOOLEAN,
	userNote INTEGER,
	syncType INTEGER,
	eventType INTEGER,
	infoType INTEGER,
	pending BOOLEAN,
	rollbackable BOOLEAN,
	notificationType VARCHAR(255) null,
	documentType VARCHAR(255) null,
	mappingAction VARCHAR(255) null,
	dateOption INTEGER
);

create table opencps_applicableInfo (
	uuid_ VARCHAR(75) null,
	applicableInfoId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceCode VARCHAR(75) null,
	govAgencyCode VARCHAR(75) null,
	serviceLevel INTEGER,
	serviceConfigMappingId LONG
);

create table opencps_booking (
	uuid_ VARCHAR(75) null,
	bookingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	className VARCHAR(255) null,
	classPK LONG,
	serviceCode VARCHAR(128) null,
	codeNumber VARCHAR(255) null,
	bookingName VARCHAR(512) null,
	checkinDate DATE null,
	gateNumber VARCHAR(255) null,
	state_ INTEGER,
	bookingDate DATE null,
	speaking BOOLEAN,
	serviceGroupCode VARCHAR(255) null,
	count INTEGER,
	online_ BOOLEAN,
	bookingInTime VARCHAR(255) null,
	telNo VARCHAR(128) null,
	govAgencyCode VARCHAR(75) null
);

create table opencps_configcounter (
	uuid_ VARCHAR(75) null,
	configCounterId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	counterCode VARCHAR(255) null,
	patternCode VARCHAR(255) null,
	startCounter INTEGER
);

create table opencps_deliverable (
	uuid_ VARCHAR(75) null,
	deliverableId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	deliverableCode VARCHAR(128) null,
	deliverableName VARCHAR(512) null,
	deliverableType VARCHAR(255) null,
	govAgencyCode VARCHAR(128) null,
	govAgencyName VARCHAR(512) null,
	applicantIdNo VARCHAR(128) null,
	applicantName VARCHAR(512) null,
	subject VARCHAR(512) null,
	formData TEXT null,
	formScript TEXT null,
	formReport TEXT null,
	formScriptFileId LONG,
	formReportFileId LONG,
	expireDate DATE null,
	issueDate DATE null,
	revalidate DATE null,
	deliverableState INTEGER,
	fileEntryId LONG,
	dossierId LONG,
	docSync INTEGER,
	fileAttachs VARCHAR(512) null
);

create table opencps_deliverablelog (
	uuid_ VARCHAR(75) null,
	deliverableLogId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	deliverableId LONG,
	dossierUid VARCHAR(255) null,
	author VARCHAR(255) null,
	content TEXT null,
	deliverableAction INTEGER,
	actionDate DATE null,
	payload TEXT null,
	fileEntryId LONG
);

create table opencps_deliverabletype (
	uuid_ VARCHAR(75) null,
	deliverableTypeId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	typeCode VARCHAR(128) null,
	typeName VARCHAR(575) null,
	formScript TEXT null,
	formReport TEXT null,
	formScriptFileId LONG,
	formReportFileId LONG,
	codePattern VARCHAR(275) null,
	dataConfig TEXT null,
	tableConfig TEXT null,
	counter LONG,
	mappingData TEXT null,
	docSync INTEGER,
	govAgencies VARCHAR(255) null
);

create table opencps_deliverabletyperole (
	uuid_ VARCHAR(75) null,
	deliverableTypeRoleId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	deliverableTypeId LONG,
	roleId LONG,
	moderator BOOLEAN
);

create table opencps_documenttype (
	uuid_ VARCHAR(75) null,
	DocumentTypeId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	typeCode VARCHAR(255) null,
	templateClass INTEGER,
	documentName VARCHAR(255) null,
	codePattern VARCHAR(255) null,
	documentScript TEXT null,
	docSync INTEGER
);

create table opencps_dossier (
	uuid_ VARCHAR(75) null,
	dossierId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	referenceUid VARCHAR(255) null,
	counter LONG,
	registerBookCode VARCHAR(100) null,
	registerBookName VARCHAR(1024) null,
	dossierRegister VARCHAR(255) null,
	processNo VARCHAR(128) null,
	serviceCode VARCHAR(128) null,
	serviceName TEXT null,
	govAgencyCode VARCHAR(128) null,
	govAgencyName VARCHAR(512) null,
	applicantName VARCHAR(500) null,
	applicantIdType VARCHAR(128) null,
	applicantIdNo VARCHAR(128) null,
	applicantIdDate DATE null,
	address TEXT null,
	cityCode VARCHAR(128) null,
	cityName VARCHAR(255) null,
	districtCode VARCHAR(128) null,
	districtName VARCHAR(512) null,
	wardCode VARCHAR(128) null,
	wardName VARCHAR(512) null,
	contactName TEXT null,
	contactTelNo VARCHAR(128) null,
	contactEmail VARCHAR(255) null,
	delegateType INTEGER,
	delegateName VARCHAR(512) null,
	delegateIdNo VARCHAR(128) null,
	delegateTelNo VARCHAR(128) null,
	delegateEmail VARCHAR(255) null,
	delegateAddress TEXT null,
	delegateCityCode VARCHAR(128) null,
	delegateCityName VARCHAR(512) null,
	delegateDistrictCode VARCHAR(128) null,
	delegateDistrictName VARCHAR(512) null,
	delegateWardCode VARCHAR(128) null,
	delegateWardName VARCHAR(512) null,
	documentNo VARCHAR(255) null,
	documentDate DATE null,
	dossierTemplateNo VARCHAR(128) null,
	dossierTemplateName TEXT null,
	dossierNote TEXT null,
	submissionNote TEXT null,
	applicantNote TEXT null,
	briefNote TEXT null,
	dossierNo VARCHAR(255) null,
	submitting BOOLEAN,
	processDate DATE null,
	submitDate DATE null,
	receiveDate DATE null,
	dueDate DATE null,
	extendDate DATE null,
	releaseDate DATE null,
	finishDate DATE null,
	cancellingDate DATE null,
	correcttingDate DATE null,
	dossierStatus VARCHAR(255) null,
	dossierStatusText TEXT null,
	dossierSubStatus VARCHAR(128) null,
	dossierSubStatusText TEXT null,
	folderId LONG,
	dossierActionId LONG,
	viaPostal INTEGER,
	postalServiceCode VARCHAR(255) null,
	postalServiceName VARCHAR(1024) null,
	postalAddress TEXT null,
	postalCityCode VARCHAR(255) null,
	postalCityName VARCHAR(512) null,
	postalDistrictCode VARCHAR(255) null,
	postalDistrictName VARCHAR(512) null,
	postalWardCode VARCHAR(255) null,
	postalWardName VARCHAR(512) null,
	postalTelNo VARCHAR(128) null,
	password_ VARCHAR(75) null,
	notification BOOLEAN,
	online_ BOOLEAN,
	original BOOLEAN,
	serverNo VARCHAR(255) null,
	endorsementDate DATE null,
	lockState VARCHAR(200) null,
	originality INTEGER,
	originDossierId LONG,
	sampleCount LONG,
	durationUnit INTEGER,
	durationCount DOUBLE,
	dossierName VARCHAR(1000) null,
	originDossierNo VARCHAR(255) null,
	groupDossierId VARCHAR(75) null,
	metaData TEXT null,
	systemId INTEGER,
	dossierCounter VARCHAR(128) null,
	vnpostalStatus INTEGER,
	vnpostalProfile TEXT null,
	fromViaPostal INTEGER,
	multipleCheck VARCHAR(75) null,
	postalCodeSend VARCHAR(75) null,
	postalCodeReceived VARCHAR(75) null,
	lastReceiveDate DATE null,
	lastSendDate DATE null
);

create table opencps_dossieraction (
	uuid_ VARCHAR(75) null,
	dossierActionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	serviceProcessId LONG,
	previousActionId LONG,
	fromStepCode VARCHAR(255) null,
	fromStepName VARCHAR(512) null,
	fromSequenceNo VARCHAR(255) null,
	actionCode VARCHAR(100) null,
	actionUser VARCHAR(255) null,
	actionName VARCHAR(512) null,
	actionNote TEXT null,
	actionOverdue INTEGER,
	syncActionCode VARCHAR(100) null,
	pending BOOLEAN,
	rollbackable BOOLEAN,
	stepCode VARCHAR(255) null,
	stepName VARCHAR(512) null,
	sequenceNo VARCHAR(255) null,
	dueDate DATE null,
	nextActionId LONG,
	payload TEXT null,
	stepInstruction VARCHAR(500) null,
	state_ INTEGER,
	eventStatus INTEGER
);

create table opencps_dossieractionsync (
	uuid_ VARCHAR(75) null,
	dossierActionSyncId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	dossierActionId LONG,
	createDossier BOOLEAN,
	referenceUid VARCHAR(75) null,
	actionCode VARCHAR(255) null,
	actionUser VARCHAR(500) null,
	actionNote TEXT null
);

create table opencps_dossieractionuser (
	uuid_ VARCHAR(75) null,
	dossierActionId LONG not null,
	userId LONG not null,
	dossierId LONG,
	stepCode VARCHAR(255) null,
	moderator INTEGER,
	assigned INTEGER,
	visited BOOLEAN,
	roleId LONG,
	delegacy INTEGER,
	primary key (dossierActionId, userId)
);

create table opencps_dossierdocument (
	uuid_ VARCHAR(75) null,
	DossierDocumentId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	referenceUid VARCHAR(75) null,
	dossierActionId LONG,
	documentType VARCHAR(255) null,
	documentName VARCHAR(512) null,
	documentCode VARCHAR(100) null,
	documentFileId LONG,
	docSync INTEGER
);

create table opencps_dossierfile (
	uuid_ VARCHAR(75) null,
	dossierFileId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	referenceUid VARCHAR(75) null,
	dossierTemplateNo VARCHAR(255) null,
	dossierPartNo VARCHAR(255) null,
	dossierPartType INTEGER,
	fileTemplateNo VARCHAR(255) null,
	displayName VARCHAR(500) null,
	formData TEXT null,
	fileEntryId LONG,
	original BOOLEAN,
	eForm BOOLEAN,
	isNew BOOLEAN,
	removed BOOLEAN,
	signCheck INTEGER,
	signInfo TEXT null,
	formScript TEXT null,
	formReport TEXT null,
	formSchema TEXT null,
	deliverableCode VARCHAR(255) null
);

create table opencps_dossierlog (
	uuid_ VARCHAR(75) null,
	dossierLogId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	author VARCHAR(255) null,
	content TEXT null,
	notificationType VARCHAR(200) null,
	payload TEXT null
);

create table opencps_dossiermark (
	uuid_ VARCHAR(75) null,
	dossierMarkId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	dossierPartNo VARCHAR(255) null,
	fileCheck INTEGER,
	fileMark INTEGER,
	fileComment VARCHAR(512) null,
	recordCount VARCHAR(512) null
);

create table opencps_dossierpart (
	uuid_ VARCHAR(75) null,
	dossierPartId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	templateNo VARCHAR(255) null,
	partNo VARCHAR(255) null,
	partName VARCHAR(500) null,
	partTip TEXT null,
	partType INTEGER,
	multiple BOOLEAN,
	formScript TEXT null,
	formReport TEXT null,
	sampleData TEXT null,
	required BOOLEAN,
	fileTemplateNo VARCHAR(255) null,
	eSign BOOLEAN,
	deliverableType VARCHAR(500) null,
	deliverableAction INTEGER,
	eForm BOOLEAN,
	fileMark INTEGER,
	partNameTitle VARCHAR(500) null
);

create table opencps_dossierrequests (
	uuid_ VARCHAR(75) null,
	dossierRequestId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	referenceUid VARCHAR(75) null,
	requestType VARCHAR(128) null,
	comment_ TEXT null,
	isNew INTEGER,
	statusReg INTEGER
);

create table opencps_dossierstatistic (
	uuid_ VARCHAR(75) null,
	dossierStatisticId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	month INTEGER,
	year INTEGER,
	totalCount INTEGER,
	deniedCount INTEGER,
	cancelledCount INTEGER,
	processCount INTEGER,
	remainingCount INTEGER,
	receivedCount INTEGER,
	onlineCount INTEGER,
	releaseCount INTEGER,
	betimesCount INTEGER,
	ontimeCount INTEGER,
	overtimeCount INTEGER,
	doneCount INTEGER,
	releasingCount INTEGER,
	unresolvedCount INTEGER,
	processingCount INTEGER,
	undueCount INTEGER,
	overdueCount INTEGER,
	pausingCount INTEGER,
	ontimePercentage INTEGER,
	govAgencyCode VARCHAR(255) null,
	govAgencyName VARCHAR(255) null,
	domainCode VARCHAR(255) null,
	domainName VARCHAR(255) null,
	reporting BOOLEAN
);

create table opencps_dossierstatusmapping (
	dossierStatusMappingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	statusCode VARCHAR(255) null,
	statusCodeDVCQG VARCHAR(128) null,
	subStatusCode VARCHAR(128) null
);

create table opencps_dossiersync (
	uuid_ VARCHAR(75) null,
	DossierSyncId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	dossierRefUid VARCHAR(128) null,
	syncRefUid VARCHAR(255) null,
	dossierActionId LONG,
	actionCode VARCHAR(128) null,
	actionName VARCHAR(512) null,
	actionUser VARCHAR(255) null,
	actionNote TEXT null,
	syncType INTEGER,
	infoType INTEGER,
	payload TEXT null,
	serverNo VARCHAR(128) null,
	state_ INTEGER,
	retry INTEGER,
	messageText TEXT null,
	acknowlegement TEXT null
);

create table opencps_dossiertemplate (
	uuid_ VARCHAR(75) null,
	dossierTemplateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	templateName STRING null,
	description TEXT null,
	templateNo VARCHAR(255) null,
	newFormScript TEXT null,
	formMeta TEXT null
);

create table opencps_dossieruser (
	uuid_ VARCHAR(75) null,
	dossierId LONG not null,
	userId LONG not null,
	moderator INTEGER,
	visited BOOLEAN,
	roleId LONG,
	primary key (dossierId, userId)
);

create table opencps_eform (
	uuid_ VARCHAR(75) null,
	eFormId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	eFormNo VARCHAR(128) null,
	serviceCode VARCHAR(128) null,
	fileTemplateNo VARCHAR(128) null,
	eFormName VARCHAR(512) null,
	formScriptFileId LONG,
	formReportFileId LONG,
	eFormData TEXT null,
	email VARCHAR(255) null,
	secret VARCHAR(75) null,
	govAgencyCode VARCHAR(75) null
);

create table opencps_menuconfig (
	uuid_ VARCHAR(75) null,
	menuConfigId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	menuGroup VARCHAR(255) null,
	menuName VARCHAR(1024) null,
	order_ INTEGER,
	menuType INTEGER,
	queryParams VARCHAR(500) null,
	tableConfig TEXT null,
	buttonConfig TEXT null,
	icon VARCHAR(255) null,
	viewScript TEXT null
);

create table opencps_menurole (
	uuid_ VARCHAR(75) null,
	menuRoleId LONG not null primary key,
	menuConfigId LONG,
	roleId LONG
);

create table opencps_newsboard (
	uuid_ VARCHAR(75) null,
	newsBoardId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	newsTitle VARCHAR(75) null,
	newsContent VARCHAR(75) null,
	newsStatus INTEGER
);

create table opencps_notarization (
	notarizationId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	fileName VARCHAR(1024) null,
	totalRecord INTEGER,
	totalPage INTEGER,
	totalCopy INTEGER,
	totalFee LONG,
	notarizationNo LONG,
	notarizationYear INTEGER,
	notarizationDate DATE null,
	signerName VARCHAR(512) null,
	signerPosition VARCHAR(512) null,
	statusCode VARCHAR(75) null
);

create table opencps_paymentconfig (
	uuid_ VARCHAR(75) null,
	paymentConfigId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	govAgencyCode VARCHAR(128) null,
	govAgencyName TEXT null,
	govAgencyTaxNo VARCHAR(75) null,
	invoiceTemplateNo TEXT null,
	invoiceIssueNo TEXT null,
	invoiceLastNo TEXT null,
	invoiceForm TEXT null,
	bankInfo STRING null,
	epaymentConfig TEXT null
);

create table opencps_paymentfeeinfo (
	uuid_ VARCHAR(75) null,
	paymentFeeInfoId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	type_ VARCHAR(75) null,
	paymentFeeCode VARCHAR(75) null,
	paymentFeeName VARCHAR(75) null,
	amount VARCHAR(75) null,
	serviceConfigMappingId LONG
);

create table opencps_paymentfile (
	uuid_ VARCHAR(75) null,
	paymentFileId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	referenceUid VARCHAR(75) null,
	govAgencyCode VARCHAR(128) null,
	govAgencyName VARCHAR(500) null,
	paymentFee VARCHAR(500) null,
	advanceAmount LONG,
	feeAmount LONG,
	serviceAmount LONG,
	shipAmount LONG,
	paymentAmount LONG,
	paymentNote VARCHAR(500) null,
	epaymentProfile STRING null,
	bankInfo STRING null,
	paymentStatus INTEGER,
	paymentMethod VARCHAR(255) null,
	confirmDatetime DATE null,
	confirmPayload STRING null,
	confirmFileEntryId LONG,
	confirmNote VARCHAR(75) null,
	approveDatetime DATE null,
	accountUserName VARCHAR(500) null,
	govAgencyTaxNo VARCHAR(500) null,
	invoiceTemplateNo VARCHAR(500) null,
	invoiceIssueNo VARCHAR(500) null,
	invoiceNo VARCHAR(500) null,
	invoicePayload STRING null,
	einvoice STRING null,
	invoiceFileEntryId LONG
);

create table opencps_postconnect (
	uuid_ VARCHAR(75) null,
	postConnectId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	postService INTEGER,
	postType INTEGER,
	orderNumber VARCHAR(75) null,
	postStatus INTEGER,
	metadata VARCHAR(75) null,
	syncState INTEGER,
	retry INTEGER
);

create table opencps_process_plugin (
	uuid_ VARCHAR(75) null,
	processPluginId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	stepCode VARCHAR(128) null,
	serviceProcessId LONG,
	pluginName VARCHAR(500) null,
	pluginType INTEGER,
	sequenceNo VARCHAR(128) null,
	pluginForm TEXT null,
	sampleData STRING null,
	autoRun BOOLEAN
);

create table opencps_processaction (
	uuid_ VARCHAR(75) null,
	processActionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceProcessId LONG,
	preStepCode VARCHAR(255) null,
	postStepCode VARCHAR(255) null,
	autoEvent VARCHAR(255) null,
	preCondition STRING null,
	actionCode VARCHAR(255) null,
	actionName VARCHAR(255) null,
	allowAssignUser INTEGER,
	assignUserId LONG,
	requestPayment INTEGER,
	paymentFee VARCHAR(500) null,
	createDossierFiles STRING null,
	returnDossierFiles STRING null,
	makeBriefNote VARCHAR(500) null,
	syncActionCode VARCHAR(128) null,
	rollbackable BOOLEAN,
	createDossierNo BOOLEAN,
	eSignature BOOLEAN,
	configNote TEXT null,
	dossierTemplateNo VARCHAR(255) null,
	signatureType VARCHAR(255) null,
	createDossiers VARCHAR(255) null,
	checkInput INTEGER,
	postAction VARCHAR(128) null
);

create table opencps_processoption (
	uuid_ VARCHAR(75) null,
	processOptionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceConfigId LONG,
	optionOrder INTEGER,
	optionName VARCHAR(500) null,
	autoSelect VARCHAR(500) null,
	dossierTemplateId LONG,
	serviceProcessId LONG,
	instructionNote TEXT null,
	submissionNote TEXT null,
	sampleCount LONG,
	registerBookCode VARCHAR(100) null,
	forCitizen BOOLEAN,
	forBusiness BOOLEAN
);

create table opencps_processsequence (
	uuid_ VARCHAR(75) null,
	processSequenceId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceProcessId LONG,
	sequenceNo VARCHAR(255) null,
	sequenceName VARCHAR(512) null,
	sequenceRole VARCHAR(255) null,
	durationCount DOUBLE
);

create table opencps_processstep (
	uuid_ VARCHAR(75) null,
	processStepId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	stepCode VARCHAR(255) null,
	serviceProcessId LONG,
	stepName VARCHAR(500) null,
	sequenceNo VARCHAR(255) null,
	dossierStatus VARCHAR(255) null,
	dossierSubStatus VARCHAR(255) null,
	durationCount DOUBLE,
	customProcessUrl VARCHAR(255) null,
	stepInstruction TEXT null,
	briefNote TEXT null,
	editable BOOLEAN,
	restrictDossier VARCHAR(255) null,
	lockState VARCHAR(255) null,
	groupName VARCHAR(255) null,
	roleAsStep VARCHAR(255) null,
	checkInput INTEGER
);

create table opencps_processsteprole (
	uuid_ VARCHAR(75) null,
	processStepId LONG not null,
	roleId LONG not null,
	roleCode VARCHAR(255) null,
	roleName VARCHAR(255) null,
	moderator BOOLEAN,
	condition_ TEXT null,
	primary key (processStepId, roleId)
);

create table opencps_publish_queue (
	uuid_ VARCHAR(75) null,
	publishQueueId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	dossierId LONG,
	serverNo VARCHAR(255) null,
	status INTEGER,
	retry INTEGER,
	publishType INTEGER,
	publishData TEXT null,
	messageText TEXT null,
	acknowlegement TEXT null
);

create table opencps_registration (
	uuid_ VARCHAR(75) null,
	registrationId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	applicantName VARCHAR(275) null,
	applicantIdType VARCHAR(128) null,
	applicantIdNo VARCHAR(128) null,
	applicantIdDate DATE null,
	address VARCHAR(575) null,
	cityCode VARCHAR(128) null,
	cityName VARCHAR(275) null,
	districtCode VARCHAR(128) null,
	districtName VARCHAR(275) null,
	wardCode VARCHAR(128) null,
	wardName VARCHAR(275) null,
	contactName VARCHAR(275) null,
	contactTelNo VARCHAR(275) null,
	contactEmail VARCHAR(275) null,
	govAgencyCode VARCHAR(255) null,
	govAgencyName VARCHAR(275) null,
	registrationState INTEGER,
	registrationClass VARCHAR(255) null,
	submitting BOOLEAN,
	representativeEnterprise VARCHAR(255) null
);

create table opencps_registrationform (
	uuid_ VARCHAR(75) null,
	registrationFormId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	registrationId LONG,
	referenceUid VARCHAR(75) null,
	formNo VARCHAR(128) null,
	formName VARCHAR(275) null,
	formData TEXT null,
	formScript TEXT null,
	formReport TEXT null,
	fileEntryId LONG,
	isNew BOOLEAN,
	removed BOOLEAN
);

create table opencps_registrationlog (
	uuid_ VARCHAR(75) null,
	registrationLogId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	registrationId LONG,
	author TEXT null,
	content TEXT null,
	payload TEXT null
);

create table opencps_registrationtemplate (
	uuid_ VARCHAR(75) null,
	registrationTemplateId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	govAgencyCode VARCHAR(128) null,
	govAgencyName VARCHAR(255) null,
	formNo VARCHAR(128) null,
	formName VARCHAR(512) null,
	multiple BOOLEAN,
	formScript TEXT null,
	formReport TEXT null,
	sampleData TEXT null
);

create table opencps_reportlandtax (
	uuid_ VARCHAR(75) null,
	reportId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossierNo VARCHAR(75) null,
	bodyRequest VARCHAR(75) null,
	response VARCHAR(75) null
);

create table opencps_serviceconfig (
	uuid_ VARCHAR(75) null,
	serviceConfigId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceInfoId LONG,
	govAgencyCode VARCHAR(128) null,
	govAgencyName STRING null,
	serviceInstruction TEXT null,
	serviceLevel INTEGER,
	serviceUrl VARCHAR(500) null,
	forCitizen BOOLEAN,
	forBusiness BOOLEAN,
	postService BOOLEAN,
	registration BOOLEAN
);

create table opencps_serviceconfigmapping (
	uuid_ VARCHAR(75) null,
	serviceConfigMappingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceConfigCode VARCHAR(75) null,
	serviceConfigName VARCHAR(75) null,
	serviceCode VARCHAR(75) null,
	serviceName VARCHAR(75) null,
	govAgencyName VARCHAR(75) null,
	domainName VARCHAR(75) null
);

create table opencps_serviceinfo (
	uuid_ VARCHAR(75) null,
	serviceInfoId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceCode VARCHAR(128) null,
	serviceName TEXT null,
	processText TEXT null,
	methodText TEXT null,
	dossierText TEXT null,
	conditionText TEXT null,
	durationText TEXT null,
	applicantText TEXT null,
	resultText TEXT null,
	regularText TEXT null,
	feeText TEXT null,
	administrationCode VARCHAR(128) null,
	administrationName VARCHAR(500) null,
	administrationIndex VARCHAR(128) null,
	domainCode VARCHAR(128) null,
	domainName TEXT null,
	domainIndex VARCHAR(128) null,
	maxLevel INTEGER,
	public_ BOOLEAN,
	govAgencyText TEXT null,
	isNotarization BOOLEAN,
	serviceNameTitle VARCHAR(500) null,
	tagCode VARCHAR(75) null,
	tagName VARCHAR(75) null
);

create table opencps_serviceinfomapping (
	serviceInfoMappingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	serviceCode VARCHAR(128) null,
	serviceCodeDVCQG VARCHAR(128) null,
	serviceNameDVCQG TEXT null,
	synced INTEGER
);

create table opencps_serviceprocess (
	uuid_ VARCHAR(75) null,
	serviceProcessId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(255) null,
	createDate DATE null,
	modifiedDate DATE null,
	processNo VARCHAR(255) null,
	processName STRING null,
	description TEXT null,
	durationCount DOUBLE,
	durationUnit INTEGER,
	counter LONG,
	generateDossierNo BOOLEAN,
	dossierNoPattern TEXT null,
	generateDueDate BOOLEAN,
	dueDatePattern VARCHAR(500) null,
	generatePassword BOOLEAN,
	directNotification BOOLEAN,
	serverNo VARCHAR(255) null,
	serverName TEXT null,
	requestPayment BOOLEAN,
	paymentFee VARCHAR(255) null,
	dossierGroupPattern VARCHAR(128) null,
	counterCode VARCHAR(128) null
);

create table opencps_serviceprocessrole (
	uuid_ VARCHAR(75) null,
	serviceProcessId LONG not null,
	roleId LONG not null,
	roleCode VARCHAR(255) null,
	roleName VARCHAR(255) null,
	moderator BOOLEAN,
	condition_ VARCHAR(255) null,
	primary key (serviceProcessId, roleId)
);

create table opencps_services_filetemplates (
	uuid_ VARCHAR(75) null,
	serviceInfoId LONG not null,
	fileTemplateNo VARCHAR(128) not null,
	templateName VARCHAR(1000) null,
	fileEntryId LONG,
	eForm BOOLEAN,
	formScriptFileId LONG,
	formReportFileId LONG,
	eFormNoPattern VARCHAR(255) null,
	eFormNamePattern VARCHAR(255) null,
	primary key (serviceInfoId, fileTemplateNo)
);

create table opencps_stepconfig (
	uuid_ VARCHAR(75) null,
	stepConfigId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	stepCode VARCHAR(128) null,
	stepName VARCHAR(512) null,
	stepType INTEGER,
	dossierStatus VARCHAR(128) null,
	dossierSubStatus VARCHAR(128) null,
	menuGroup VARCHAR(128) null,
	menuStepName VARCHAR(512) null,
	buttonConfig TEXT null
);

create table opencps_userinfolog (
	uuid_ VARCHAR(75) null,
	userLogId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	payload TEXT null
);