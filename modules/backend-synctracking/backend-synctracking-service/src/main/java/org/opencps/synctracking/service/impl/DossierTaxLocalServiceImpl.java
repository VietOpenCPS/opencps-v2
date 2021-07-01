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

package org.opencps.synctracking.service.impl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.synctracking.model.DossierTax;
import org.opencps.synctracking.model.DossierTaxInput;
import org.opencps.synctracking.service.base.DossierTaxLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the dossier tax local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synctracking.service.DossierTaxLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DossierTaxLocalServiceBaseImpl
 * @see org.opencps.synctracking.service.DossierTaxLocalServiceUtil
 */
public class DossierTaxLocalServiceImpl extends DossierTaxLocalServiceBaseImpl {
	@Override
	public DossierTax createDossierTaxManual(DossierTaxInput dossierTaxInput) {
		long taxId = counterLocalService.increment(DossierTax.class.getName());
		Date now = new Date();
		DossierTax dossierTax  = dossierTaxLocalService.createDossierTax(taxId);
		dossierTax.setCreateDate(now);
		dossierTax.setModifiedDate(now);
		dossierTax.setGroupId(dossierTaxInput.groupId);

		if(Validator.isNotNull(dossierTaxInput.dossierId) && dossierTaxInput.dossierId >0) {
			dossierTax.setDossierId(dossierTaxInput.dossierId);
		}

		if(Validator.isNotNull(dossierTaxInput.dossierNo)) {
			dossierTax.setDossierNo(dossierTaxInput.dossierNo);
		}

		if(Validator.isNotNull(dossierTaxInput.maSoThue)) {
			dossierTax.setMaSoThue(dossierTaxInput.maSoThue);
		}

		if(Validator.isNotNull(dossierTaxInput.soQuyetDinh)) {
			dossierTax.setSoQuyetDinh(dossierTaxInput.soQuyetDinh);
		}

		if(Validator.isNotNull(dossierTaxInput.ngayQuyetDinh)) {
			dossierTax.setNgayQuyetDinh(APIDateTimeUtils
					.convertStringToDate(dossierTaxInput.ngayQuyetDinh, APIDateTimeUtils._NORMAL_PARTTERN));
		}

		if(Validator.isNotNull(dossierTaxInput.tenTieuMuc)) {
			dossierTax.setTenTieuMuc(dossierTaxInput.tenTieuMuc);
		}

		if(Validator.isNotNull(dossierTaxInput.soTien) && dossierTaxInput.soTien > 0) {
			dossierTax.setSoTien(dossierTaxInput.soTien);
		}

		if(Validator.isNotNull(dossierTaxInput.hoTenNguoiNopTien)) {
			dossierTax.setHoTenNguoiNopTien(dossierTaxInput.hoTenNguoiNopTien);
		}

		if(Validator.isNotNull(dossierTaxInput.soCmtNguoiNopTien)) {
			dossierTax.setSoCmtNguoiNopTien(Integer.valueOf(dossierTaxInput.soCmtNguoiNopTien));
		}

		if(Validator.isNotNull(dossierTaxInput.diaChiNguoiNopTien)) {
			dossierTax.setDiaChiNguoiNopTien(dossierTaxInput.diaChiNguoiNopTien);
		}

		if(Validator.isNotNull(dossierTaxInput.tinhNguoiNopTien)) {
			dossierTax.setTinhNguoiNopTien(dossierTaxInput.tinhNguoiNopTien);
		}

		if(Validator.isNotNull(dossierTaxInput.huyenNguoiNopTien)) {
			dossierTax.setHuyenNguoiNopTien(dossierTaxInput.huyenNguoiNopTien);
		}

		if(Validator.isNotNull(dossierTaxInput.xaNguoiNopTien)) {
			dossierTax.setXaNguoiNopTien(dossierTaxInput.xaNguoiNopTien);
		}

		if(Validator.isNotNull(dossierTaxInput.xaNguoiNopTien)) {
			dossierTax.setThoiGianThanhToan(APIDateTimeUtils.convertStringToDate(dossierTaxInput.thoiGianThanhToan, APIDateTimeUtils._NORMAL_DATE));
		}

		if(Validator.isNotNull(dossierTaxInput.xaNguoiNopTien)) {
			dossierTax.setNgayNhanBienLai(APIDateTimeUtils.convertStringToDate(dossierTaxInput.ngayNhanBienLai, APIDateTimeUtils._NORMAL_DATE));
		}

		if(Validator.isNotNull(dossierTaxInput.xaNguoiNopTien)) {
			dossierTax.setNgayThueTraThongBao(APIDateTimeUtils.convertStringToDate(dossierTaxInput.ngayThueTraThongBao, APIDateTimeUtils._NORMAL_DATE));
		}

		if(Validator.isNotNull(dossierTaxInput.xaNguoiNopTien)) {
			dossierTax.setNgayTraThongBao(APIDateTimeUtils.convertStringToDate(dossierTaxInput.ngayTraThongBao, APIDateTimeUtils._NORMAL_DATE));
		}

		if(Validator.isNotNull(dossierTaxInput.xaNguoiNopTien)) {
			dossierTax.setSoTienNop(Integer.valueOf(dossierTaxInput.soTienNop));
		}

		if(Validator.isNotNull(dossierTaxInput.xaNguoiNopTien)) {
			dossierTax.setNoiDungThanhToan(dossierTaxInput.noiDungThanhToan);
		}

		if(Validator.isNotNull(dossierTaxInput.xaNguoiNopTien)) {
			dossierTax.setTrangThaiThanhToan(dossierTaxInput.trangThaiThanhToan);
		}

		if(Validator.isNotNull(dossierTaxInput.statusTBT)) {
			dossierTax.setStatusTBT(Integer.valueOf(dossierTaxInput.statusTBT));
		}
		if(Validator.isNotNull(dossierTaxInput.statusCTT)) {
			dossierTax.setStatusCTT(Integer.valueOf(dossierTaxInput.statusCTT));
		}

		dossierTaxPersistence.update(dossierTax);
		return dossierTax;
	}

	@Override
	public List<DossierTax> getByStatusTBT(int[] statuses, int start, int end, OrderByComparator<DossierTax> orderByComparator) {
		return dossierTaxPersistence.findByF_STATUS_TBT(statuses, start, end, orderByComparator);
	}

	@Override
	public List<DossierTax> getByStatusCTT(int statusTBT, int statusCTT, int start, int end, OrderByComparator<DossierTax> orderByComparator) {
		return dossierTaxPersistence.findByF_STATUS_CTT(statusTBT, statusCTT, start, end, orderByComparator);
	}

	@Override
	public List<DossierTax> getByDossierIdAndStatusTBT(long dossierId, int statuses) {
		return dossierTaxPersistence.findByF_DOSSIER_TBT(dossierId, statuses);
	}

	@Override
	public List<DossierTax> getByDossierIdAndStatusCTT(long dossierId, int statuses) {
		return dossierTaxPersistence.findByF_DOSSIER_CTT(dossierId, statuses);
	}

	@Override
	public DossierTax fetchDossierTaxByDMS(String dossierNo, String maSoThue, String soQuyetDinh) {
		return dossierTaxPersistence.fetchByF_DMS(dossierNo, maSoThue, soQuyetDinh);
	}
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synctracking.service.DossierTaxLocalServiceUtil} to access the dossier tax local service.
	 */
}