/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.vr.business.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.Registration;

import com.fds.vr.business.model.VRVehicleTypeCertificate;
import com.fds.vr.business.service.base.VRVehicleTypeCertificateLocalServiceBaseImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * The implementation of the vr vehicle type certificate local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fds.vr.business.service.VRVehicleTypeCertificateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author LamTV
 * @see VRVehicleTypeCertificateLocalServiceBaseImpl
 * @see com.fds.vr.business.service.VRVehicleTypeCertificateLocalServiceUtil
 */
@ProviderType
public class VRVehicleTypeCertificateLocalServiceImpl
	extends VRVehicleTypeCertificateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.fds.vr.business.service.VRVehicleTypeCertificateLocalServiceUtil} to access the vr vehicle type certificate local service.
	 */

	private Log _log = LogFactoryUtil.getLog(VRVehicleTypeCertificateLocalServiceImpl.class);

	public VRVehicleTypeCertificate updateVehicleTypeCertificate(LinkedHashMap<String, String> mapValues,
			Date modifiedDate, Registration registration, DossierFile dossierFile) {
		
		Date now = new Date();

		long vrVehicleTypeCertificateId = counterLocalService.increment(VRVehicleTypeCertificate.class.getName());

		VRVehicleTypeCertificate object = vrVehicleTypeCertificatePersistence.create(vrVehicleTypeCertificateId);

		/// Add audit fields
		object.setSyncDate(now);
		_log.info("Vao KHONG syncDateList:"+object.getSyncDate());

		// Add other fields
		object.setMtCore(1);
		if (dossierFile != null) {
			object.setDossierId(dossierFile.getDossierId());
		}
		object.setDossierType(mapValues.get("bien_ban@hinh_thuc_cap_giay"));
		object.setDossierNo(mapValues.get("so_ho_so"));
		object.setConvertassembleId(0);
		if (registration != null) {
			object.setApplicantIdNo(Long.valueOf(registration.getApplicantIdNo()));
			object.setApplicantIdDate(registration.getApplicantIdDate());
			object.setApplicantName(registration.getApplicantName());
		}
		//
//		object.setApplicantAddress(mapValues.get(""));
//		object.setApplicantRepresentative(mapValues.get(""));
//		object.setApplicantRepresentativeTitle(mapValues.get(""));
//		object.setApplicantEmail(mapValues.get(""));
//		object.setApplicantPhone(mapValues.get(""));
//		object.setApplicantFax(mapValues.get(""));
//		object.setApplicantContactName(mapValues.get(""));
//		object.setApplicantContactEmail(mapValues.get(""));
//		object.setApplicantcontactPhone(mapValues.get(""));
//		object.setManufacturerForeignCode(mapValues.get(""));
//		object.setManufacturerName(mapValues.get(""));
//		object.setManufacturerAddress(mapValues.get(""));
//		object.setManufacturerRepresentative(mapValues.get(""));
//		object.setManufacturerRepresentativeTitle(mapValues.get(""));
//		object.setManufacturerEmail(mapValues.get(""));
//		object.setManufacturerPhone(mapValues.get(""));
//		object.setManufacturerFax(mapValues.get(""));
//		object.setProductionPlantCode(mapValues.get(""));
//		object.setProductionPlantName(mapValues.get(""));
//		object.setProductionPlantAddress(mapValues.get(""));
//		object.setProductionPlantRepresentative(mapValues.get(""));
//		object.setProductionPlantRepresentativeTitle(mapValues.get(""));
//		//copreportno
//		object.setCopReportNo(mapValues.get(""));
////		object.setCopReportDate(DATEEEEEEE);
////		object.setCopReportExpireDate(DATEEEEEEE);
//		object.setDesignerCode(mapValues.get(""));
//		object.setDesignerName(mapValues.get(""));
//		object.setDesignerAddress(mapValues.get(""));
//		object.setDesignerRepresentative(mapValues.get(""));
//		object.setDesignerRepresentativeTitle(mapValues.get(""));
//		object.setDesignerEmail(mapValues.get(""));
//		object.setDesignerPhone(mapValues.get(""));
//		object.setDesignerFax(mapValues.get(""));
//		object.setVerificationCertificateNo(mapValues.get(""));
////		object.setVerificationCertificateDate(DATEEEEEEE);
//		object.setVerificationRefNo(mapValues.get(""));
////		object.setVerificationRefDate(DATEEEEE);
//		object.setTypeApprovalCertificateNo(mapValues.get(""));
////		object.setTypeApprovalCertificateDate(DATEEEEEEEE);
//		object.setDesignModelCode(mapValues.get(""));
//		object.setDesignModelDescription(mapValues.get(""));
//		object.setDesignSymbol(mapValues.get(""));
//		object.setRegisteredNumber(mapValues.get(""));
////		object.setInspectorReceiveDate(mapValues.get(""));
////		object.setInspectorSubmitDate(mapValues.get(""));
////		object.setInspectorendorSementDate(DATE     );
////		object.setInspectorDeadline(DATEEEEEEEE);
////		object.setInspectorFinishDate(DATEEEEEEE);
////		object.setInspectorCancelDate(DATEEEEEEEE);
//		object.setInspectorOrganization(mapValues.get(""));
//		object.setInspectorDivision(mapValues.get(""));
//		object.setInspectorSignName(mapValues.get(""));
//		object.setInspectorSignTitle(mapValues.get(""));
//		object.setInspectorSignPlace(mapValues.get(""));
//		object.setCertificateType(mapValues.get(""));
//		object.setReferenceCertificateNo(mapValues.get(""));
////		object.setReferenceCertificateDate(DATEEEEEEEE);
//		object.setCertificateRecordNo(mapValues.get(""));
//		object.setCertificateSignName(mapValues.get(""));
//		object.setCertificateSignTitle(mapValues.get(""));
//		object.setCertificateSignPlace(mapValues.get(""));
////		object.setCertificateRecordDate(DATEEEEEEE);
////		object.setCertificateRecordExpireDate(DATEEEEEEEE);
//		object.setExpiredStatus(mapValues.get(""));
//		object.setCertificateRecordStatus(mapValues.get(""));
//		object.setDigitalIssueStatus(mapValues.get(""));
//		object.setVehicleClass(mapValues.get(""));
//		object.setCertifiedVehicleType(mapValues.get(""));
//		object.setCertifiedVehicleTypeDescription(mapValues.get(""));
//		object.setCertifiedTrademark(mapValues.get(""));
//		object.setCertifiedCommercialName(mapValues.get(""));
//		object.setCertifiedModelCode(mapValues.get(""));
//		object.setCertifiedAssemblyType(mapValues.get(""));
//		object.setCertifiedAssemblyTypeDescription(mapValues.get(""));
//		object.setCertifiedVINNo(mapValues.get(""));
//		object.setCertifiedVINPosition(mapValues.get(""));
//		object.setCertifiedFrameNo(mapValues.get(""));
//		object.setCertifiedFrameAttachPlace(mapValues.get(""));
//		object.setCertifiedFramePosition(mapValues.get(""));
//		object.setCertifiedEngineNo(mapValues.get(""));
//		object.setCertifiedEngineAttachPlace(mapValues.get(""));
//		object.setCertifiedEnginePosition(mapValues.get(""));
//		object.setSafetyTestReportNo(mapValues.get(""));
////		object.setSafetyTestReportDate(DATEEEEEEEEE);
//		object.setEmissionTestReportNo(mapValues.get(""));
////		object.setEmissionTestReportDate(DATEEEEEEE);
//		object.setCommonSafetyStandard(mapValues.get(""));
//		object.setEmissionStandard(mapValues.get(""));
//		object.setOtherTestReportNo(mapValues.get(""));
////		object.setOtherTestReportDate(DATEEEEEEE);
//		object.setSampleFrameNo(mapValues.get(""));
//		object.setSampleVINNo(mapValues.get(""));
//		object.setSampleEngineNo(mapValues.get(""));
//		object.setSampleVehicleType(mapValues.get(""));
//		object.setSampleVehicleTypeDescription(mapValues.get(""));
//		object.setSampleTrademark(mapValues.get(""));
//		object.setSampleTrademarkName(mapValues.get(""));
//		object.setSampleCommercialName(mapValues.get(""));
//		object.setSampleModelCode(mapValues.get(""));
//		object.setCustomsDeclarationNo(mapValues.get(""));
////		object.setCustomsDeclarationDate(DATEEEEEE);
//		object.setProductionCountry(mapValues.get(""));
//		object.setImporterQuantity(Long.valueOf(mapValues.get("")));
//		object.setInspectionRecordNo(mapValues.get(""));
////		object.setInspectionDate(DATEEEEEE);
//		object.setInspectionSite(mapValues.get(""));
//		object.setInspectionDistrictCode(mapValues.get(""));
//		object.setInspectionDistrictName(mapValues.get(""));
//		object.setInspectionProvinceCode(mapValues.get(""));
//		object.setInspectionProvinceName(mapValues.get(""));
//		object.setCorporationId(mapValues.get(""));
//		object.setInspectorId(Long.valueOf(mapValues.get("")));
//		object.setRemarks(mapValues.get(""));
//		object.setInspectionNote(mapValues.get(""));
//		object.setCertificateNote(mapValues.get(""));
//		object.setModule(mapValues.get(""));
//		object.setModifyDate(modifiedDate);

		return vrVehicleTypeCertificatePersistence.update(object);
	}
}