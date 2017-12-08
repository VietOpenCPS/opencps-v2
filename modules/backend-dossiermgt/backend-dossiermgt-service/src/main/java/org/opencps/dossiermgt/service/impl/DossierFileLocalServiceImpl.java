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

package org.opencps.dossiermgt.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.exception.DuplicateDossierFileException;
import org.opencps.dossiermgt.exception.InvalidDossierStatusException;
import org.opencps.dossiermgt.exception.NoSuchDossierPartException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.impl.DossierFileImpl;
import org.opencps.dossiermgt.service.base.DossierFileLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierFileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierFileLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierFileLocalServiceUtil
 */
@ProviderType
public class DossierFileLocalServiceImpl extends DossierFileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierFileLocalServiceUtil} to access the
	 * dossier file local service.
	 */

	/**
	 * POST /dossiers/{id|referenceUid}/files
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile addDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo, String displayName, String sourceFileName, long fileSize,
			InputStream inputStream, String fileType, String isSync, ServiceContext serviceContext) throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		validateAddDossierFile(groupId, dossierId, referenceUid, dossierTemplateNo, dossierPartNo, fileTemplateNo);

		DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId, dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, inputStream, sourceFileName, fileType,
					fileSize, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		long dossierFileId = counterLocalService.increment(DossierFile.class.getName());

		DossierFile object = dossierFilePersistence.create(dossierFileId);

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		// Add other fields

		object.setDossierId(dossierId);
		if (Validator.isNull(referenceUid)) {
			referenceUid = PortalUUIDUtil.generate();
		}

		object.setReferenceUid(referenceUid);
		object.setDossierTemplateNo(dossierTemplateNo);
		object.setFileEntryId(fileEntryId);
		object.setDossierPartNo(dossierPartNo);
		object.setFileTemplateNo(fileTemplateNo);
		object.setDossierPartType(dossierPart.getPartType());
		
		if (Validator.isNull(displayName)) {
			displayName = sourceFileName;
		}
		
		if (Validator.isNotNull(dossierPart.getFormScript())) {
			object.setEForm(true);
			object.setFormScript(dossierPart.getFormScript());
		}
		
		if (Validator.isNotNull(dossierPart.getFormReport())) {
			object.setFormReport(dossierPart.getFormReport());
		}
		
		if (Validator.isNotNull(dossierPart.getSampleData())) {
			object.setFormData(dossierPart.getSampleData());
		}


		object.setDisplayName(displayName);
		object.setOriginal(true);
		
		if (Validator.isNotNull(isSync) && GetterUtil.getBoolean(isSync)) {
			object.setIsNew(false);
		} else {
			object.setIsNew(true);
		}
		

		return dossierFilePersistence.update(object);
	}
	
	

	/**
	 * POST /dossiers/{id}/files/copyfile
	 * 
	 * @param groupId
	 * @param dossierId
	 * @param dossierFileId
	 * @param dossierTemplateNo
	 * @param dossierPartNo
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile cloneDossierFile(long groupId, long dossierId, long dossierFileId, String dossierTemplateNo,
			String dossierPartNo, ServiceContext serviceContext) throws PortalException, SystemException {

		DossierFile sourceDossierFile = dossierFilePersistence.findByPrimaryKey(dossierFileId);

		User user = userPersistence.findByPrimaryKey(serviceContext.getUserId());

		DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(groupId, dossierTemplateNo, dossierPartNo);

		long fileEntryId = 0;

		if (sourceDossierFile.getFileEntryId() > 0) {
			try {
				FileEntry fileEntry = FileUploadUtils.cloneDossierFile(user.getPrimaryKey(), groupId,
						sourceDossierFile.getFileEntryId(), serviceContext);

				fileEntryId = fileEntry.getFileEntryId();
			} catch (Exception e) {
				throw new SystemException(e);
			}
		}

		
		long newDossierFileId = counterLocalService.increment(DossierFile.class.getName());
		
		DossierFile object = dossierFilePersistence.create(newDossierFileId);
		
		Date now = new Date();

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(user.getPrimaryKey());
		object.setUserName(user.getFullName());

		// Add other fields

		object.setDossierId(dossierId);
		object.setReferenceUid(PortalUUIDUtil.generate());
		object.setDossierTemplateNo(dossierTemplateNo);
		object.setFileEntryId(fileEntryId);
		object.setDossierPartNo(dossierPartNo);
		object.setFileTemplateNo(sourceDossierFile.getFileTemplateNo());
		object.setDossierPartType(dossierPart.getPartType());
		object.setDisplayName(sourceDossierFile.getDisplayName());
		object.setFormData(sourceDossierFile.getFormData());
		object.setOriginal(false);
		object.setIsNew(true);
		object.setFormScript(sourceDossierFile.getFormScript());
		object.setFormReport(sourceDossierFile.getFormReport());

		return dossierFilePersistence.update(object);
	}

	/**
	 * for POST /dossiers/{id}/cloning
	 * 
	 * @param groupId
	 * @param newDossierId
	 * @param oldDossierId
	 * @param dossierPartType
	 * @param serviceContext
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void cloneDossierFilesByDossierId(long groupId, long newDossierId, long oldDossierId, int dossierPartType,
			ServiceContext serviceContext) throws PortalException, SystemException {

		List<DossierFile> dossierFiles = dossierFileLocalService.getDossierFilesByD_DP(oldDossierId, dossierPartType);

		for (DossierFile dossierFile : dossierFiles) {
			cloneDossierFile(groupId, newDossierId, dossierFile.getDossierFileId(), dossierFile.getDossierTemplateNo(),
					dossierFile.getDossierPartNo(), serviceContext);
		}
	}

	/**
	 * POST /dossiers/{id|referenceUid}/files/{referenceUid}
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DossierFile updateDossierFile(long groupId, long dossierId, String referenceUid, String displayName,
			String sourceFileName, InputStream inputStream, ServiceContext serviceContext)
			throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);

		long fileEntryId = 0;

		try {
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, dossierFile.getFileEntryId(),
					inputStream, sourceFileName, null, 0, serviceContext);

			if (fileEntry != null) {
				fileEntryId = fileEntry.getFileEntryId();
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		// Add audit fields
		dossierFile.setModifiedDate(now);
		dossierFile.setUserId(userAction.getUserId());
		dossierFile.setUserName(userAction.getFullName());

		// Add other fields

		dossierFile.setDossierId(dossierId);
		if (Validator.isNull(referenceUid)) {
			referenceUid = PortalUUIDUtil.generate();
		}

		dossierFile.setFileEntryId(fileEntryId);
		if (Validator.isNull(displayName)) {
			displayName = sourceFileName;
		}

		dossierFile.setDisplayName(displayName);
		dossierFile.setOriginal(true);
		dossierFile.setIsNew(true);

		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierFile updateFormData(long groupId, long dossierId, String referenceUid, String formData,
			ServiceContext serviceContext) throws PortalException, SystemException {

		//User user = userPersistence.findByPrimaryKey(serviceContext.getUserId());
		System.out.println("GET DOSSIER FILE" + new Date());

		DossierFile dossierFile = dossierFilePersistence.findByDID_REF(dossierId, referenceUid);
		
		//dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);

		String jrxmlTemplate = dossierFile.getFormReport();

		if (Validator.isNull(jrxmlTemplate)) {
			DossierPart dossierPart = dossierPartLocalService.fetchByTemplatePartNo(groupId,
					dossierFile.getDossierTemplateNo(), dossierFile.getDossierPartNo());

			if (dossierPart == null) {
				throw new NoSuchDossierPartException();
			}

			jrxmlTemplate = dossierPart.getFormReport();

			dossierFile.setFormReport(jrxmlTemplate);
		}

		dossierFile.setFormData(formData);
		dossierFile.setIsNew(true);
		
		//String jrxmlTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"openCPSGenJasper\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" whenResourceMissingType=\"Empty\" uuid=\"26db35e2-8d3e-4abf-bcb8-57f44f660834\"><property name=\"net.sf.jasperreports.awt.ignore.missing.font\" value=\"true\"/><property name=\"ireport.zoom\" value=\"1.4641000000000006\"/><property name=\"ireport.x\" value=\"0\"/><property name=\"ireport.y\" value=\"288\"/><property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"One Empty Record\"/><property name=\"net.sf.jasperreports.awt.ignore.missing.font\" value=\"true\"/><property name=\"net.sf.jasperreports.json.date.pattern\" value=\"yyyy-MM-dd\"/><property name=\"net.sf.jasperreports.json.number.pattern\" value=\"#,##0.##\"/><property name=\"ireport.background.image.properties\" value=\"false,true,0.25,0,0,0,0,0,0\"/><style name=\"Sans_Normal\" isDefault=\"true\" fontName=\"DejaVu Sans\" fontSize=\"12\" isBold=\"false\" isItalic=\"false\" isUnderline=\"false\" isStrikeThrough=\"false\"/><style name=\"Sans_Bold\" fontName=\"DejaVu Sans\" fontSize=\"12\" isBold=\"true\" isItalic=\"false\" isUnderline=\"false\" isStrikeThrough=\"false\"/><style name=\"Sans_Italic\" fontName=\"DejaVu Sans\" fontSize=\"12\" isBold=\"false\" isItalic=\"true\" isUnderline=\"false\" isStrikeThrough=\"false\"/><queryString language=\"json\"><![CDATA[opencps]]></queryString><field name=\"tenCoQuanToChuc\" class=\"java.lang.String\"><fieldDescription><![CDATA[tenCoQuanToChuc]]></fieldDescription></field><field name=\"congHoaXaHoi\" class=\"java.lang.String\"><fieldDescription><![CDATA[congHoaXaHoi]]></fieldDescription></field><field name=\"toKhaiXoaDangKy\" class=\"java.lang.String\"><fieldDescription><![CDATA[toKhaiXoaDangKy]]></fieldDescription></field><field name=\"nguoiDeNghiLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[nguoiDeNghiLb]]></fieldDescription></field><field name=\"nguoiDeNghi\" class=\"java.lang.String\"><fieldDescription><![CDATA[nguoiDeNghi]]></fieldDescription></field><field name=\"quocTich\" class=\"java.lang.String\"><fieldDescription><![CDATA[quocTich]]></fieldDescription></field><field name=\"diaChi\" class=\"java.lang.String\"><fieldDescription><![CDATA[diaChi]]></fieldDescription></field><field name=\"dienThoai\" class=\"java.lang.String\"><fieldDescription><![CDATA[dienThoai]]></fieldDescription></field><field name=\"tauBayLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[tauBayLb]]></fieldDescription></field><field name=\"loaiTauBay\" class=\"java.lang.String\"><fieldDescription><![CDATA[loaiTauBay]]></fieldDescription></field><field name=\"kieuLoaiTauBay\" class=\"java.lang.String\"><fieldDescription><![CDATA[kieuLoaiTauBay]]></fieldDescription></field><field name=\"nhaSanXuat\" class=\"java.lang.String\"><fieldDescription><![CDATA[nhaSanXuat]]></fieldDescription></field><field name=\"soXuatXuong\" class=\"java.lang.String\"><fieldDescription><![CDATA[soXuatXuong]]></fieldDescription></field><field name=\"trongLuongToiDa\" class=\"java.lang.String\"><fieldDescription><![CDATA[trongLuongToiDa]]></fieldDescription></field><field name=\"namXuatXuong\" class=\"java.lang.String\"><fieldDescription><![CDATA[namXuatXuong]]></fieldDescription></field><field name=\"soLuongKieuDongCo\" class=\"java.lang.String\"><fieldDescription><![CDATA[soLuongKieuDongCo]]></fieldDescription></field><field name=\"dauHieuQuocTich\" class=\"java.lang.String\"><fieldDescription><![CDATA[dauHieuQuocTich]]></fieldDescription></field><field name=\"noiDungXoaDangKyLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[noiDungXoaDangKyLb]]></fieldDescription></field><field name=\"xoaDangKyTauBayLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[xoaDangKyTauBayLb]]></fieldDescription></field><field name=\"giayChungNhanDaCapLb1\" class=\"java.lang.String\"><fieldDescription><![CDATA[giayChungNhanDaCapLb1]]></fieldDescription></field><field name=\"soGiayChungNhanDaCap1\" class=\"java.lang.String\"><fieldDescription><![CDATA[soGiayChungNhanDaCap1]]></fieldDescription></field><field name=\"ngayCapGiayChungNhan1\" class=\"java.lang.String\"><fieldDescription><![CDATA[ngayCapGiayChungNhan1]]></fieldDescription></field><field name=\"xoaDangKyTauBayCheckbox\" class=\"java.lang.String\"><fieldDescription><![CDATA[xoaDangKyTauBayCheckbox]]></fieldDescription></field><field name=\"xoaDangKyTauBayTamThoiLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[xoaDangKyTauBayTamThoiLb]]></fieldDescription></field><field name=\"giayChungNhanDaCapLb2\" class=\"java.lang.String\"><fieldDescription><![CDATA[giayChungNhanDaCapLb2]]></fieldDescription></field><field name=\"soGiayChungNhanDaCap2\" class=\"java.lang.String\"><fieldDescription><![CDATA[soGiayChungNhanDaCap2]]></fieldDescription></field><field name=\"ngayCapGiayChungNhan2\" class=\"java.lang.String\"><fieldDescription><![CDATA[ngayCapGiayChungNhan2]]></fieldDescription></field><field name=\"xoaDangKyTauBayTamThoiCheckbox\" class=\"java.lang.String\"><fieldDescription><![CDATA[xoaDangKyTauBayTamThoiCheckbox]]></fieldDescription></field><field name=\"xoaDangKyQuyenSoHuuLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[xoaDangKyQuyenSoHuuLb]]></fieldDescription></field><field name=\"giayChungNhanDaCapLb3\" class=\"java.lang.String\"><fieldDescription><![CDATA[giayChungNhanDaCapLb3]]></fieldDescription></field><field name=\"soGiayChungNhanDaCap3\" class=\"java.lang.String\"><fieldDescription><![CDATA[soGiayChungNhanDaCap3]]></fieldDescription></field><field name=\"ngayCapGiayChungNhan3\" class=\"java.lang.String\"><fieldDescription><![CDATA[ngayCapGiayChungNhan3]]></fieldDescription></field><field name=\"xoaDangKyQuyenSoHuuCheckbox\" class=\"java.lang.String\"><fieldDescription><![CDATA[xoaDangKyQuyenSoHuuCheckbox]]></fieldDescription></field><field name=\"xoaDangKyQuyenChiemHuuLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[xoaDangKyQuyenChiemHuuLb]]></fieldDescription></field><field name=\"giayChungNhanDaCapLb4\" class=\"java.lang.String\"><fieldDescription><![CDATA[giayChungNhanDaCapLb4]]></fieldDescription></field><field name=\"soGiayChungNhanDaCap4\" class=\"java.lang.String\"><fieldDescription><![CDATA[soGiayChungNhanDaCap4]]></fieldDescription></field><field name=\"ngayCapGiayChungNhan4\" class=\"java.lang.String\"><fieldDescription><![CDATA[ngayCapGiayChungNhan4]]></fieldDescription></field><field name=\"xoaDangKyQuyenChiemHuuCheckbox\" class=\"java.lang.String\"><fieldDescription><![CDATA[xoaDangKyQuyenChiemHuuCheckbox]]></fieldDescription></field><field name=\"taiLieuKemTheoLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[taiLieuKemTheoLb]]></fieldDescription></field><field name=\"taiLieuKemTheo\" class=\"java.lang.String\"><fieldDescription><![CDATA[taiLieuKemTheo]]></fieldDescription></field><field name=\"ngayThangNam\" class=\"java.lang.String\"><fieldDescription><![CDATA[ngayThangNam]]></fieldDescription></field><field name=\"nguoiDeNghiLb1\" class=\"java.lang.String\"><fieldDescription><![CDATA[nguoiDeNghiLb1]]></fieldDescription></field><field name=\"kyDongDau\" class=\"java.lang.String\"><fieldDescription><![CDATA[kyDongDau]]></fieldDescription></field><field name=\"nguoiDeNghi1\" class=\"java.lang.String\"><fieldDescription><![CDATA[nguoiDeNghi1]]></fieldDescription></field><field name=\"camDoanLb\" class=\"java.lang.String\"><fieldDescription><![CDATA[camDoanLb]]></fieldDescription></field><detail><band height=\"213\"><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"130\" width=\"554\" height=\"22\" uuid=\"ff9d8f7b-e49b-4569-ad15-3dbb0c4e5fb6\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"16\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"TỜ KHAI\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"154\" width=\"554\" height=\"22\" uuid=\"757fe125-401b-4080-8d38-3f36cfeb6ac1\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"Xóa đăng ký tàu bay\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"176\" width=\"554\" height=\"22\" uuid=\"e4e25f4d-46f2-4d47-bc22-f8f04f76c9dd\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"Application for registration of aircraft\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"10\" width=\"239\" height=\"22\" uuid=\"a48da871-f252-4ddf-8c69-6bbf0567ceb6\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\" markup=\"html\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[$F{tenCoQuanToChuc}==null?\"...\":$F{tenCoQuanToChuc}.toUpperCase()]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"260\" y=\"10\" width=\"294\" height=\"22\" uuid=\"b64abcf3-ffc9-46b5-948d-a86716f2e62c\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"260\" y=\"32\" width=\"294\" height=\"22\" uuid=\"1e39fe13-f5e3-489f-8324-36a58d4ffe3e\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"Độc lập - Tự do - Hạnh phúc\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"260\" y=\"65\" width=\"294\" height=\"22\" uuid=\"97ae2631-31c9-47ba-aeab-3d7d1e36bab5\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"SOCIALIST REPUBLIC OF VIETNAM\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"260\" y=\"87\" width=\"294\" height=\"22\" uuid=\"583b8bd6-a6e1-455a-82a5-9e920f8e9854\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"Independence - Freedom - Happiness\"]]></textFieldExpression></textField><line><reportElement x=\"310\" y=\"54\" width=\"190\" height=\"1\" uuid=\"e1880e0e-1d63-4d98-8cd7-d0bc13b4f350\"/></line><line><reportElement x=\"312\" y=\"108\" width=\"190\" height=\"1\" uuid=\"fd2758c9-0c76-4b99-80bc-24f36dc07f27\"/></line><line><reportElement x=\"59\" y=\"32\" width=\"120\" height=\"1\" uuid=\"814bd788-93ae-4eef-9390-d1112e2a9f8b\"/></line></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"983f76b7-caa5-4313-a003-1e043ab44a03\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"I. NGƯỜI ĐỀ NGHỊ / APPLICANT\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"35754074-2347-464c-b8b7-2025d58e8cf9\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"1. Tên / Full Name: \" + ($F{nguoiDeNghi}==null?\"...\":$F{nguoiDeNghi})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"76711fe5-a1ac-4dfd-8f8a-97df5f681b28\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"2. Quốc tịch / Nationality: \" +($F{quocTich}==null?\"...\":$F{quocTich})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"cb0a9682-669a-4032-918a-dd2ed0f53664\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"3. Địa chỉ / Address: \" + ($F{diaChi}==null?\"...\":$F{diaChi})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"9219a683-1cb1-4b4d-9db1-88e49013bdce\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"4. Điện thoại / Tel: \" + ($F{dienThoai}==null?\"...\":$F{dienThoai})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"ba7371a8-0a9d-47b9-971b-790e7e7445fd\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"II. TÀU BAY / AIRCRAFT\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"fde996d8-4e5e-4749-986c-bba9739d84a2\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"1. Loại tầu bay / Type of aircraft: \" +($F{loaiTauBay}==null?\"...\":$F{loaiTauBay})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"670b3133-cbae-4daf-9c2c-b3214b13d0f5\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"2. Kiểu loại tầu bay / Designation of aircraft: \" +($F{kieuLoaiTauBay}==null?\"...\":$F{kieuLoaiTauBay})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"5138eb4a-da40-4d30-8358-4e6692826f4e\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"3. Nhà sản xuất / Manufacturer: \" +($F{nhaSanXuat}==null?\"...\":$F{nhaSanXuat})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"c5a2a2d3-8f26-44ab-9975-a48c10627bb5\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"4. Số xuất xưởng / Aircraft serial number: \" + ($F{soXuatXuong}==null?\"...\":$F{soXuatXuong})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"da6e3c9b-df67-4fc3-9387-d853d4cd6849\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"5. Trọng lượng cất cánh tối đa / MTOW: \" + ($F{trongLuongToiDa}==null?\"...\":$F{trongLuongToiDa})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"9ebe5aa4-1843-4874-b539-2743eb2a278c\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"6. Năm xuất xưởng / Year: \" +($F{namXuatXuong}==null?\"...\":$F{namXuatXuong})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"83fbb28a-8915-433b-8262-530a25898383\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"7. Số lượng và kiểu động cơ / Number and designation of engine: \" + ($F{soLuongKieuDongCo}==null?\"...\":$F{soLuongKieuDongCo})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"51f7a399-fbfd-4183-849c-cb47ddd25d2a\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[\"8. Dấu hiệu quốc tịch và dấu hiệu đăng ký / Nationality and registration mark: \" +($F{dauHieuQuocTich}==null?\"...\":$F{dauHieuQuocTich})]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"d6c748b8-80eb-403e-a2ed-ecac0bde550a\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"III. NỘI DUNG XÓA ĐĂNG KÝ/DEREGISTRATION INFORMATION\"]]></textFieldExpression></textField></band><band height=\"38\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"38\" uuid=\"5c65eb27-0ab7-4d9b-8554-3e64a5498b87\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Xóa đăng ký tàu bay mang quốc tịch Việt Nam/Deregistration of Vietnamese nationality of aircraft\"]]></textFieldExpression></textField><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"38\" uuid=\"a6849f78-fd1d-45c2-b533-e3c17971ab35\"/><box><topPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"85f08e68-9246-44dd-aa8f-9b55f9d9d3d4\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Giấy chứng nhận đã cấp/Issued certificate:\"]]></textFieldExpression></textField><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"21c7401a-9ead-465a-b39c-ba804f4d29c3\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{xoaDangKyTauBayCheckbox}.contains(\"true\")? \"☒\":\"☐\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"2374de25-e7ac-4cc5-bed0-5a4223d427d8\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Số/No.: \"+($F{xoaDangKyTauBayCheckbox}.contains(\"true\")?(($F{soGiayChungNhanDaCap1}==null?\"\":$F{soGiayChungNhanDaCap1})):\"...\")]]></textFieldExpression></textField><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"ed743a33-cdc8-4c45-9041-0ed44becbc6c\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"c298723b-5483-44d4-bd41-650ab3ea0ee9\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"- Ngày cấp/Date of issued: \"+($F{xoaDangKyTauBayCheckbox}.contains(\"true\")?(($F{ngayCapGiayChungNhan1}==null?\"\":$F{ngayCapGiayChungNhan1})):\"...\")]]></textFieldExpression></textField><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"4ee724f4-24f1-42c3-9d68-5ec3a4aeaab3\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField></band><band height=\"38\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"38\" uuid=\"620fa5ce-2be1-42c8-b231-ebe45c439db5\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Xóa đăng ký tàu bay tạm thời mang quốc tịch Việt Nam/Deregistration of temporary Vietnamese nationality of aircraft\"]]></textFieldExpression></textField><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"38\" uuid=\"fa9ef933-62a2-445d-88c3-a2e91078b4da\"/><box><topPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"ce671857-55bf-4d3a-ac54-6b89c562199d\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{xoaDangKyTauBayTamThoiCheckbox}.contains(\"true\")? \"☒\":\"☐\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"9c3da66b-9974-4266-a829-7efbfaab48c7\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Giấy chứng nhận đã cấp/Issued certificate:\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"825843d8-edf5-4fbe-bbe4-5c0a43c3a345\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"177bc697-1577-4a5e-900a-e01088ee9564\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Số/No.: \"+($F{xoaDangKyTauBayTamThoiCheckbox}.contains(\"true\")?(($F{soGiayChungNhanDaCap2}==null?\"\":$F{soGiayChungNhanDaCap2})):\"...\")]]></textFieldExpression></textField></band><band height=\"34\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"3a6e4d5a-3363-43ed-b0ce-2179675fad03\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"eb911c88-dafe-479f-b35d-24354ce463e6\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"- Ngày cấp/Date of issued: \"+($F{xoaDangKyTauBayTamThoiCheckbox}.contains(\"true\")?(($F{ngayCapGiayChungNhan2}==null?\"\":$F{ngayCapGiayChungNhan2})):\"...\")]]></textFieldExpression></textField><break><reportElement x=\"0\" y=\"29\" width=\"100\" height=\"1\" uuid=\"c01d6203-54b3-4474-a1b6-51700bccfa7a\"/></break></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"28585c91-54ef-498e-8464-e1e81a394292\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Xóa đăng ký quyền sở hữu tàu bay/Deregistration of aircraft ownership\"]]></textFieldExpression></textField><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"40b8b701-b060-4fbc-bce2-676d4f0e9a76\"/><box><topPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"5f09e8e9-1c69-48f3-970f-2daabfcecf59\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{xoaDangKyQuyenChiemHuuCheckbox}.contains(\"true\")? \"☒\":\"☐\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"4863d65b-dc87-4586-8ca6-811c9fa99c3b\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Giấy chứng nhận đã cấp/Issued certificate:\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"99720b34-3cee-40b8-978e-673f6258bc62\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"6c97bc10-d265-4a92-b40d-44a709d0fd19\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Số/No.: \"+($F{xoaDangKyQuyenChiemHuuCheckbox}.contains(\"true\")?(($F{soGiayChungNhanDaCap3}==null?\"...\":$F{soGiayChungNhanDaCap3})):\"...\")]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"e435d13d-cda6-4ccf-aa99-f23b51bea161\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"06b1e394-b0ad-448d-8aaf-073b8596f1d0\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"- Ngày cấp/Date of issued: \"+($F{xoaDangKyQuyenChiemHuuCheckbox}.contains(\"true\")?(($F{ngayCapGiayChungNhan3}==null?\"...\":$F{ngayCapGiayChungNhan3})):\"...\")]]></textFieldExpression></textField></band><band height=\"38\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"38\" uuid=\"af203c92-7b4e-4814-89d8-6bd9772dbb5e\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Xóa đăng ký quyền chiếm hữu tày bay/Deregistration of right to aircraft possession\"]]></textFieldExpression></textField><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"38\" uuid=\"adf35554-15b1-4329-8e29-38a457aae63e\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><topPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"05146a39-797d-46c8-afb2-78d48ce592ca\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[$F{xoaDangKyQuyenChiemHuuCheckbox}.contains(\"true\")? \"☒\":\"☐\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"7ffca180-7760-43d3-81ae-a913bd8860e3\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Giấy chứng nhận đã cấp/Issued certificate:\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"f01cd70e-5e81-49e5-8ae6-2d7370b8f4b6\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"4d26af97-0df5-45cd-97f3-b1aacb58b31f\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Số/No.: \"+($F{xoaDangKyQuyenChiemHuuCheckbox}.contains(\"true\")?(($F{soGiayChungNhanDaCap4}==null?\"...\":$F{soGiayChungNhanDaCap4})):\"...\")]]></textFieldExpression></textField></band><band height=\"29\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField><reportElement x=\"520\" y=\"0\" width=\"34\" height=\"22\" uuid=\"41947d66-06cf-471f-8698-991023dc4de5\"/><box><topPen lineWidth=\"0.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><bottomPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/><rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression><![CDATA[\"\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"520\" height=\"22\" uuid=\"17055a02-98ce-4a5f-a804-a6f269abe42a\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><box><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\" leftIndent=\"10\" rightIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"- Ngày cấp/Date of issued: \"+($F{xoaDangKyQuyenChiemHuuCheckbox}.contains(\"true\")?(($F{ngayCapGiayChungNhan4}==null?\"...\":$F{ngayCapGiayChungNhan4})):\"...\")]]></textFieldExpression></textField></band><band height=\"117\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"260\" height=\"22\" uuid=\"c7040324-8693-4645-8a61-2cb5e2cb20e6\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Left\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"20\"/></textElement><textFieldExpression><![CDATA[\"Tài liệu kèm theo đơn / Attachment:\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"22\" width=\"260\" height=\"22\" uuid=\"141cc23f-7bdc-48cc-9c2c-c39e880b237b\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Left\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"20\"/></textElement><textFieldExpression><![CDATA[($F{taiLieuKemTheo}==null?\"\":$F{taiLieuKemTheo})]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"312\" y=\"90\" width=\"242\" height=\"22\" uuid=\"294133b9-bf81-4fbf-976d-7103ab9cc0c5\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[($F{nguoiDeNghi}==null?\"...\":$F{nguoiDeNghi})]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"312\" y=\"44\" width=\"242\" height=\"22\" uuid=\"a9821316-20b1-46f5-9e7a-14f6072ae270\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isItalic=\"true\"/></textElement><textFieldExpression><![CDATA[\"(Ký, đóng dấu) / (Signature/seal)\"]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"312\" y=\"0\" width=\"242\" height=\"22\" uuid=\"27c8c58f-7c54-49d3-b917-af3f443d9583\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/></textElement><textFieldExpression><![CDATA[($F{ngayThangNam}==null?\"...\":(\"Ngày \"+$F{ngayThangNam}.split(\"/\")[0] + \" tháng \" +$F{ngayThangNam}.split(\"/\")[1] + \" năm \" +$F{ngayThangNam}.split(\"/\")[2]))]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"312\" y=\"22\" width=\"242\" height=\"22\" uuid=\"14fc66f5-618d-4f4b-8b9a-52d74c2aa6e4\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\" isBold=\"true\"/></textElement><textFieldExpression><![CDATA[\"Người đề nghị / Applicant\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"a8615703-20c4-4fb0-b838-22b2188ea00b\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"Tôi xin cam đoan các thông tin trong đơn là hoàn toàn chính xác và hoàn toàn chịu trách nhiệm về nội dung khai trong đơn này.\"]]></textFieldExpression></textField></band><band height=\"22\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/><textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\"><reportElement x=\"0\" y=\"0\" width=\"554\" height=\"22\" uuid=\"9616a4fc-15e8-420f-9692-4b43f5de0a5d\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/></reportElement><textElement textAlignment=\"Justified\" verticalAlignment=\"Middle\"><font fontName=\"DejaVu Sans\" size=\"12\"/><paragraph firstLineIndent=\"10\"/></textElement><textFieldExpression><![CDATA[\"I declare that all particulars supplied in this application are correct and complete and shall fully be responsible for the provided information.\"]]></textFieldExpression></textField></band></detail></jasperReport>";
//		String jrxmlTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"Blank_test\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"453\" leftMargin=\"85\" rightMargin=\"56\" topMargin=\"56\" bottomMargin=\"56\" uuid=\"4f5a9471-fbea-438e-98fc-68f847aafc2b\"> <property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"adad\"/> <subDataset name=\"openCPSSubDataSet\" uuid=\"a2b74605-31cc-4bf0-85cc-ce7ff244bd56\"> <property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"One Empty Record\"/> <queryString> <![CDATA[bang]]> </queryString> <field name=\"stt\" class=\"java.lang.String\"> <fieldDescription><![CDATA[stt]]></fieldDescription> </field> <field name=\"tenphepthu\" class=\"java.lang.String\"> <fieldDescription><![CDATA[tenphepthu]]></fieldDescription> </field> </subDataset> <queryString language=\"json\"> <![CDATA[opencps]]> </queryString> <field name=\"dinh_danh\" class=\"java.lang.String\"> <fieldDescription><![CDATA[dinh_danh]]></fieldDescription> </field> <field name=\"ngay\" class=\"java.lang.String\"> <fieldDescription><![CDATA[ngay]]></fieldDescription> </field> <field name=\"tendoanhnghiep\" class=\"java.lang.String\"> <fieldDescription><![CDATA[tendoanhnghiep]]></fieldDescription> </field> <field name=\"diachi\" class=\"java.lang.String\"> <fieldDescription><![CDATA[diachi]]></fieldDescription> </field> <field name=\"bang\" class=\"java.lang.String\"> <fieldDescription><![CDATA[bang]]></fieldDescription> </field> <detail> <band height=\"290\"> <property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/> <componentElement> <reportElement x=\"19\" y=\"150\" width=\"401\" height=\"30\" uuid=\"62cdbf2a-960b-47d5-b757-3ac5d01c66f7\"/> <jr:list xmlns:jr=\"http://jasperreports.sourceforge.net/jasperreports/components\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd\" printOrder=\"Vertical\"> <datasetRun subDataset=\"openCPSSubDataSet\" uuid=\"5031a5f0-9a25-4696-a19f-6cb844afe206\"> <dataSourceExpression><![CDATA[((net.sf.jasperreports.engine.data.JsonDataSource)$P{REPORT_DATA_SOURCE}).subDataSource(\"bang\")]]></dataSourceExpression> </datasetRun> <jr:listContents height=\"30\" width=\"401\"> <textField> <reportElement x=\"0\" y=\"0\" width=\"50\" height=\"30\" uuid=\"99ed1467-4cb1-4399-b806-1583e60b6cb1\"/> <box> <topPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <bottomPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> </box> <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/> <textFieldExpression><![CDATA[$F{stt}]]></textFieldExpression> </textField> <textField> <reportElement x=\"50\" y=\"0\" width=\"351\" height=\"30\" uuid=\"dc672f71-4258-4309-a40d-bb8db69133ae\"/> <box> <topPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <bottomPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> </box> <textElement verticalAlignment=\"Middle\"/> <textFieldExpression><![CDATA[$F{tenphepthu}]]></textFieldExpression> </textField> </jr:listContents> </jr:list> </componentElement> <textField> <reportElement x=\"0\" y=\"10\" width=\"100\" height=\"30\" uuid=\"5292847c-c01a-4bec-9a61-194747afcb2b\"/> <textFieldExpression><![CDATA[$F{tendoanhnghiep}]]></textFieldExpression> </textField> <textField> <reportElement x=\"210\" y=\"20\" width=\"240\" height=\"30\" uuid=\"09dbb2c9-fd46-4a34-bb19-aaaf984122a3\"/> <textFieldExpression><![CDATA[$F{dinh_danh} +\", ngày \"+$F{ngay}]]></textFieldExpression> </textField> <textField> <reportElement x=\"0\" y=\"70\" width=\"450\" height=\"30\" uuid=\"689e5fa7-d133-4cd7-be59-2d85dc989f64\"/> <textFieldExpression><![CDATA[\"Tên doanh nghiệp\" + $F{tendoanhnghiep}]]></textFieldExpression> </textField> <textField> <reportElement x=\"0\" y=\"100\" width=\"450\" height=\"30\" uuid=\"4c503b0e-89bf-4dc8-8e61-1ca02c65eb40\"/> <textFieldExpression><![CDATA[\"Địa chỉ\" + $F{diachi}]]></textFieldExpression> </textField> </band> </detail> </jasperReport>";
		//String jrxmlTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"Blank_test\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"453\" leftMargin=\"85\" rightMargin=\"56\" topMargin=\"56\" bottomMargin=\"56\" uuid=\"4f5a9471-fbea-438e-98fc-68f847aafc2b\"> <property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"adad\"/> <subDataset name=\"openCPSSubDataSet\" uuid=\"a2b74605-31cc-4bf0-85cc-ce7ff244bd56\"> <property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"One Empty Record\"/><queryString> <![CDATA[bang]]> </queryString> <field name=\"stt\" class=\"java.lang.String\"> <fieldDescription><![CDATA[stt]]></fieldDescription> </field> <field name=\"tenphepthu\" class=\"java.lang.String\"> <fieldDescription><![CDATA[tenphepthu]]></fieldDescription> </field> </subDataset> <queryString language=\"json\"> <![CDATA[opencps]]> </queryString> <field name=\"dinh_danh\" class=\"java.lang.String\"> <fieldDescription><![CDATA[dinh_danh]]></fieldDescription> </field> <field name=\"ngay\" class=\"java.lang.String\"> <fieldDescription><![CDATA[ngay]]></fieldDescription> </field> <field name=\"tendoanhnghiep\" class=\"java.lang.String\"> <fieldDescription><![CDATA[tendoanhnghiep]]></fieldDescription> </field> <field name=\"diachi\" class=\"java.lang.String\"> <fieldDescription><![CDATA[diachi]]></fieldDescription> </field> <field name=\"bang\" class=\"java.lang.String\"> <fieldDescription><![CDATA[bang]]></fieldDescription> </field> <detail> <band height=\"290\"> <property name=\"com.jaspersoft.studio.unit.height\" value=\"pixel\"/> <componentElement> <reportElement x=\"19\" y=\"150\" width=\"401\" height=\"30\" uuid=\"62cdbf2a-960b-47d5-b757-3ac5d01c66f7\"/> <jr:list xmlns:jr=\"http://jasperreports.sourceforge.net/jasperreports/components\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports/components http://jasperreports.sourceforge.net/xsd/components.xsd\" printOrder=\"Vertical\"> <datasetRun subDataset=\"openCPSSubDataSet\" uuid=\"5031a5f0-9a25-4696-a19f-6cb844afe206\"> <dataSourceExpression><![CDATA[((net.sf.jasperreports.engine.data.JsonDataSource)$P{REPORT_DATA_SOURCE}).subDataSource(\"bang\")]]></dataSourceExpression> </datasetRun> <jr:listContents height=\"30\" width=\"401\"> <textField> <reportElement x=\"0\" y=\"0\" width=\"50\" height=\"30\" uuid=\"99ed1467-4cb1-4399-b806-1583e60b6cb1\"/> <box> <topPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <bottomPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> </box> <textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/> <textFieldExpression><![CDATA[$F{stt}]]></textFieldExpression> </textField> <textField> <reportElement x=\"50\" y=\"0\" width=\"351\" height=\"30\" uuid=\"dc672f71-4258-4309-a40d-bb8db69133ae\"/> <box> <topPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <leftPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <bottomPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> <rightPen lineWidth=\"1.0\" lineStyle=\"Solid\" lineColor=\"#000000\"/> </box> <textElement verticalAlignment=\"Middle\"/> <textFieldExpression><![CDATA[$F{tenphepthu}]]></textFieldExpression> </textField> </jr:listContents> </jr:list> </componentElement> <textField> <reportElement x=\"0\" y=\"10\" width=\"100\" height=\"30\" uuid=\"5292847c-c01a-4bec-9a61-194747afcb2b\"/> <textFieldExpression><![CDATA[$F{tendoanhnghiep}]]></textFieldExpression> </textField> <textField> <reportElement x=\"210\" y=\"20\" width=\"240\" height=\"30\" uuid=\"09dbb2c9-fd46-4a34-bb19-aaaf984122a3\"/> <textFieldExpression><![CDATA[$F{dinh_danh} +\", ngày \"+$F{ngay}]]></textFieldExpression> </textField> <textField> <reportElement x=\"0\" y=\"70\" width=\"450\" height=\"30\" uuid=\"689e5fa7-d133-4cd7-be59-2d85dc989f64\"/> <textFieldExpression><![CDATA[\"Tên doanh nghiệp\" + $F{tendoanhnghiep}]]></textFieldExpression> </textField> <textField> <reportElement x=\"0\" y=\"100\" width=\"450\" height=\"30\" uuid=\"4c503b0e-89bf-4dc8-8e61-1ca02c65eb40\"/> <textElement> <font fontName=\"DejaVu Sans\" size=\"16\" isBold=\"true\" /> </textElement><textFieldExpression><![CDATA[\"Địa chỉ\" + $F{diachi}]]></textFieldExpression> </textField> </band> </detail> </jasperReport>";
		//formData = "{\"opencps\":{\"dinh_danh\":\"Hà Nội\", \"ngay\":\"07/12/2017\",\"diachi\":\"hà nội\",\"tendoanhnghiep\":\"ABC\",\"bang\":[{\"stt\":\"1\",\"tenphepthu\":\"phép thử 1\"}]}}";
		// Binhth add message bus to processing jasper file
		Message message = new Message();

		JSONObject msgData = JSONFactoryUtil.createJSONObject();
		msgData.put("className", DossierFile.class.getName());
		msgData.put("classPK", dossierFile.getDossierFileId());
		msgData.put("jrxmlTemplate", jrxmlTemplate );
		msgData.put("formData", formData);
		msgData.put("userId", serviceContext.getUserId());
		
		message.put("msgToEngine", msgData);
		MessageBusUtil.sendMessage("jasper/engine/out/destination", message);

		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(long dossierFileId) throws PortalException {
		DossierFile dossierFile = dossierFilePersistence.findByPrimaryKey(dossierFileId);

		return deleteDossierFile(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(long dossierId, String referenceUid) throws PortalException {
		DossierFile dossierFile = dossierFileLocalService.getDossierFileByReferenceUid(dossierId, referenceUid);
		return deleteDossierFile(dossierFile);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierFile resetDossierFile(long dossierFileId) {
		DossierFile dossierFile = dossierFilePersistence.fetchByPrimaryKey(dossierFileId);
		
		dossierFile.setIsNew(false);
		
		dossierFilePersistence.update(dossierFile);
		
		return dossierFile;
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deleteDossierFile(DossierFile dossierFile) throws PortalException {

		// TODO: validate remove delete dossier file
		validateDeleteDossierFile(dossierFile);

		dossierFile.setModifiedDate(new Date());
		dossierFile.setRemoved(true);

		return dossierFilePersistence.update(dossierFile);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierFile deletePermanentDossierFile(DossierFile dossierFile) {

		return dossierFilePersistence.remove(dossierFile);
	}

	public List<DossierFile> getDossierFilesByDossierId(long dossierId) {
		return dossierFilePersistence.findByDossierId(dossierId, false);
	}

	public List<DossierFile> getDossierFilesByD_DP(long dossierId, int dossierPartType) {
		return dossierFilePersistence.findByD_DPT(dossierId, dossierPartType, false);
	}

	// TODO: POST /dossiers/{id|referenceUid}/files/{referenceUid}

	public DossierFile getDossierFileByReferenceUid(long dossierId, String referenceUid) throws PortalException {

		return dossierFilePersistence.findByD_RUID(dossierId, referenceUid, false);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);
		
		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fileTemplateNo = GetterUtil.getString(params.get(DossierFileTerm.FILE_TEMPLATE_NO));
		String dossierPartType = GetterUtil.getString(params.get(DossierFileTerm.DOSSIER_PART_TYPE));
		String user_id = GetterUtil.getString(params.get(DossierFileTerm.USER_ID));
		String original = GetterUtil.getString(params.get(DossierFileTerm.ORIGINAL));
		String removed = GetterUtil.getString(params.get(DossierFileTerm.REMOVED));

		if (Validator.isNotNull(fileTemplateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

			query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierPartType)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierPartType);

			query.addFields(DossierFileTerm.DOSSIER_PART_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(user_id)) {
			MultiMatchQuery query = new MultiMatchQuery(user_id);

			query.addFields(DossierFileTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(original)) {
			MultiMatchQuery query = new MultiMatchQuery(original);

			query.addFields(DossierFileTerm.ORIGINAL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		if(Validator.isNotNull(removed)){
			MultiMatchQuery query = new MultiMatchQuery(removed);

			query.addFields(DossierFileTerm.REMOVED);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierFile> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierFile.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fileTemplateNo = GetterUtil.getString(params.get(DossierFileTerm.FILE_TEMPLATE_NO));
		String dossierPartType = GetterUtil.getString(params.get(DossierFileTerm.DOSSIER_PART_TYPE));
		String user_id = GetterUtil.getString(params.get(DossierFileTerm.USER_ID));
		String original = GetterUtil.getString(params.get(DossierFileTerm.ORIGINAL));
		String removed = GetterUtil.getString(params.get(DossierFileTerm.REMOVED));

		if (Validator.isNotNull(fileTemplateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

			query.addFields(DossierFileTerm.FILE_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierPartType)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierPartType);

			query.addFields(DossierFileTerm.DOSSIER_PART_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(user_id)) {
			MultiMatchQuery query = new MultiMatchQuery(user_id);

			query.addFields(DossierFileTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(original)) {
			MultiMatchQuery query = new MultiMatchQuery(original);

			query.addFields(DossierFileTerm.ORIGINAL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		if(Validator.isNotNull(removed)){
			MultiMatchQuery query = new MultiMatchQuery(removed);

			query.addFields(DossierFileTerm.REMOVED);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}
	
	public List<DossierFile> getByDossierIdAndIsNew(long dossierId, boolean isNew) {
		return dossierFilePersistence.findByDID_ISN(dossierId, isNew);
	}
	
	/**
	 * Deny if status of dossier not new or waiting
	 * 
	 * @param dossierFile
	 * @throws PortalException
	 */
	private void validateDeleteDossierFile(DossierFile dossierFile) throws PortalException {

		Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierFile.getDossierId());

		if (dossier != null) {
			if ( Validator.isNotNull(dossier.getDossierStatus()) && (!dossier.getDossierStatus().equalsIgnoreCase("new")
					|| !dossier.getDossierStatus().equalsIgnoreCase("waiting"))) {

				throw new InvalidDossierStatusException();
			}
		}
	}

	private void validateAddDossierFile(long groupId, long dossierId, String referenceUid, String dossierTemplateNo,
			String dossierPartNo, String fileTemplateNo) throws PortalException {

		// TODO add more logic here

		dossierPersistence.findByPrimaryKey(dossierId);

		if (Validator.isNotNull(referenceUid)) {
			DossierFile dossierFile = dossierFilePersistence.fetchByD_RUID(dossierId, referenceUid, false);

			if (dossierFile != null) {
				throw new DuplicateDossierFileException();
			}
		}
	}

	public static final String CLASS_NAME = DossierFile.class.getName();
}