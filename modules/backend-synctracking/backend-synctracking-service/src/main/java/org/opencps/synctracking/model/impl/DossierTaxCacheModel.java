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

package org.opencps.synctracking.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.synctracking.model.DossierTax;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DossierTax in entity cache.
 *
 * @author duongnt
 * @see DossierTax
 * @generated
 */
@ProviderType
public class DossierTaxCacheModel implements CacheModel<DossierTax>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierTaxCacheModel)) {
			return false;
		}

		DossierTaxCacheModel dossierTaxCacheModel = (DossierTaxCacheModel)obj;

		if (taxId == dossierTaxCacheModel.taxId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, taxId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(61);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", taxId=");
		sb.append(taxId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", dossierNo=");
		sb.append(dossierNo);
		sb.append(", maSoThue=");
		sb.append(maSoThue);
		sb.append(", soQuyetDinh=");
		sb.append(soQuyetDinh);
		sb.append(", ngayQuyetDinh=");
		sb.append(ngayQuyetDinh);
		sb.append(", tenTieuMuc=");
		sb.append(tenTieuMuc);
		sb.append(", soTien=");
		sb.append(soTien);
		sb.append(", hoTenNguoiNopTien=");
		sb.append(hoTenNguoiNopTien);
		sb.append(", soCmtNguoiNopTien=");
		sb.append(soCmtNguoiNopTien);
		sb.append(", diaChiNguoiNopTien=");
		sb.append(diaChiNguoiNopTien);
		sb.append(", tinhNguoiNopTien=");
		sb.append(tinhNguoiNopTien);
		sb.append(", huyenNguoiNopTien=");
		sb.append(huyenNguoiNopTien);
		sb.append(", xaNguoiNopTien=");
		sb.append(xaNguoiNopTien);
		sb.append(", thoiGianThanhToan=");
		sb.append(thoiGianThanhToan);
		sb.append(", soTienNop=");
		sb.append(soTienNop);
		sb.append(", noiDungThanhToan=");
		sb.append(noiDungThanhToan);
		sb.append(", trangThaiThanhToan=");
		sb.append(trangThaiThanhToan);
		sb.append(", fileChungTu=");
		sb.append(fileChungTu);
		sb.append(", ngayThueTraThongBao=");
		sb.append(ngayThueTraThongBao);
		sb.append(", ngayTraThongBao=");
		sb.append(ngayTraThongBao);
		sb.append(", ngayNhanBienLai=");
		sb.append(ngayNhanBienLai);
		sb.append(", statusTBT=");
		sb.append(statusTBT);
		sb.append(", statusCTT=");
		sb.append(statusCTT);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DossierTax toEntityModel() {
		DossierTaxImpl dossierTaxImpl = new DossierTaxImpl();

		if (uuid == null) {
			dossierTaxImpl.setUuid("");
		}
		else {
			dossierTaxImpl.setUuid(uuid);
		}

		dossierTaxImpl.setTaxId(taxId);
		dossierTaxImpl.setCompanyId(companyId);
		dossierTaxImpl.setGroupId(groupId);
		dossierTaxImpl.setUserId(userId);

		if (userName == null) {
			dossierTaxImpl.setUserName("");
		}
		else {
			dossierTaxImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dossierTaxImpl.setCreateDate(null);
		}
		else {
			dossierTaxImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dossierTaxImpl.setModifiedDate(null);
		}
		else {
			dossierTaxImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (dossierNo == null) {
			dossierTaxImpl.setDossierNo("");
		}
		else {
			dossierTaxImpl.setDossierNo(dossierNo);
		}

		if (maSoThue == null) {
			dossierTaxImpl.setMaSoThue("");
		}
		else {
			dossierTaxImpl.setMaSoThue(maSoThue);
		}

		if (soQuyetDinh == null) {
			dossierTaxImpl.setSoQuyetDinh("");
		}
		else {
			dossierTaxImpl.setSoQuyetDinh(soQuyetDinh);
		}

		if (ngayQuyetDinh == Long.MIN_VALUE) {
			dossierTaxImpl.setNgayQuyetDinh(null);
		}
		else {
			dossierTaxImpl.setNgayQuyetDinh(new Date(ngayQuyetDinh));
		}

		if (tenTieuMuc == null) {
			dossierTaxImpl.setTenTieuMuc("");
		}
		else {
			dossierTaxImpl.setTenTieuMuc(tenTieuMuc);
		}

		dossierTaxImpl.setSoTien(soTien);

		if (hoTenNguoiNopTien == null) {
			dossierTaxImpl.setHoTenNguoiNopTien("");
		}
		else {
			dossierTaxImpl.setHoTenNguoiNopTien(hoTenNguoiNopTien);
		}

		dossierTaxImpl.setSoCmtNguoiNopTien(soCmtNguoiNopTien);

		if (diaChiNguoiNopTien == null) {
			dossierTaxImpl.setDiaChiNguoiNopTien("");
		}
		else {
			dossierTaxImpl.setDiaChiNguoiNopTien(diaChiNguoiNopTien);
		}

		if (tinhNguoiNopTien == null) {
			dossierTaxImpl.setTinhNguoiNopTien("");
		}
		else {
			dossierTaxImpl.setTinhNguoiNopTien(tinhNguoiNopTien);
		}

		if (huyenNguoiNopTien == null) {
			dossierTaxImpl.setHuyenNguoiNopTien("");
		}
		else {
			dossierTaxImpl.setHuyenNguoiNopTien(huyenNguoiNopTien);
		}

		if (xaNguoiNopTien == null) {
			dossierTaxImpl.setXaNguoiNopTien("");
		}
		else {
			dossierTaxImpl.setXaNguoiNopTien(xaNguoiNopTien);
		}

		if (thoiGianThanhToan == Long.MIN_VALUE) {
			dossierTaxImpl.setThoiGianThanhToan(null);
		}
		else {
			dossierTaxImpl.setThoiGianThanhToan(new Date(thoiGianThanhToan));
		}

		dossierTaxImpl.setSoTienNop(soTienNop);

		if (noiDungThanhToan == null) {
			dossierTaxImpl.setNoiDungThanhToan("");
		}
		else {
			dossierTaxImpl.setNoiDungThanhToan(noiDungThanhToan);
		}

		dossierTaxImpl.setTrangThaiThanhToan(trangThaiThanhToan);

		if (fileChungTu == null) {
			dossierTaxImpl.setFileChungTu("");
		}
		else {
			dossierTaxImpl.setFileChungTu(fileChungTu);
		}

		if (ngayThueTraThongBao == Long.MIN_VALUE) {
			dossierTaxImpl.setNgayThueTraThongBao(null);
		}
		else {
			dossierTaxImpl.setNgayThueTraThongBao(new Date(ngayThueTraThongBao));
		}

		if (ngayTraThongBao == Long.MIN_VALUE) {
			dossierTaxImpl.setNgayTraThongBao(null);
		}
		else {
			dossierTaxImpl.setNgayTraThongBao(new Date(ngayTraThongBao));
		}

		if (ngayNhanBienLai == Long.MIN_VALUE) {
			dossierTaxImpl.setNgayNhanBienLai(null);
		}
		else {
			dossierTaxImpl.setNgayNhanBienLai(new Date(ngayNhanBienLai));
		}

		dossierTaxImpl.setStatusTBT(statusTBT);
		dossierTaxImpl.setStatusCTT(statusCTT);

		dossierTaxImpl.resetOriginalValues();

		return dossierTaxImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		taxId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		dossierNo = objectInput.readUTF();
		maSoThue = objectInput.readUTF();
		soQuyetDinh = objectInput.readUTF();
		ngayQuyetDinh = objectInput.readLong();
		tenTieuMuc = objectInput.readUTF();

		soTien = objectInput.readInt();
		hoTenNguoiNopTien = objectInput.readUTF();

		soCmtNguoiNopTien = objectInput.readInt();
		diaChiNguoiNopTien = objectInput.readUTF();
		tinhNguoiNopTien = objectInput.readUTF();
		huyenNguoiNopTien = objectInput.readUTF();
		xaNguoiNopTien = objectInput.readUTF();
		thoiGianThanhToan = objectInput.readLong();

		soTienNop = objectInput.readInt();
		noiDungThanhToan = objectInput.readUTF();

		trangThaiThanhToan = objectInput.readInt();
		fileChungTu = objectInput.readUTF();
		ngayThueTraThongBao = objectInput.readLong();
		ngayTraThongBao = objectInput.readLong();
		ngayNhanBienLai = objectInput.readLong();

		statusTBT = objectInput.readInt();

		statusCTT = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(taxId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (dossierNo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dossierNo);
		}

		if (maSoThue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(maSoThue);
		}

		if (soQuyetDinh == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(soQuyetDinh);
		}

		objectOutput.writeLong(ngayQuyetDinh);

		if (tenTieuMuc == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tenTieuMuc);
		}

		objectOutput.writeInt(soTien);

		if (hoTenNguoiNopTien == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hoTenNguoiNopTien);
		}

		objectOutput.writeInt(soCmtNguoiNopTien);

		if (diaChiNguoiNopTien == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(diaChiNguoiNopTien);
		}

		if (tinhNguoiNopTien == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tinhNguoiNopTien);
		}

		if (huyenNguoiNopTien == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(huyenNguoiNopTien);
		}

		if (xaNguoiNopTien == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(xaNguoiNopTien);
		}

		objectOutput.writeLong(thoiGianThanhToan);

		objectOutput.writeInt(soTienNop);

		if (noiDungThanhToan == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(noiDungThanhToan);
		}

		objectOutput.writeInt(trangThaiThanhToan);

		if (fileChungTu == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileChungTu);
		}

		objectOutput.writeLong(ngayThueTraThongBao);
		objectOutput.writeLong(ngayTraThongBao);
		objectOutput.writeLong(ngayNhanBienLai);

		objectOutput.writeInt(statusTBT);

		objectOutput.writeInt(statusCTT);
	}

	public String uuid;
	public long taxId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String dossierNo;
	public String maSoThue;
	public String soQuyetDinh;
	public long ngayQuyetDinh;
	public String tenTieuMuc;
	public int soTien;
	public String hoTenNguoiNopTien;
	public int soCmtNguoiNopTien;
	public String diaChiNguoiNopTien;
	public String tinhNguoiNopTien;
	public String huyenNguoiNopTien;
	public String xaNguoiNopTien;
	public long thoiGianThanhToan;
	public int soTienNop;
	public String noiDungThanhToan;
	public int trangThaiThanhToan;
	public String fileChungTu;
	public long ngayThueTraThongBao;
	public long ngayTraThongBao;
	public long ngayNhanBienLai;
	public int statusTBT;
	public int statusCTT;
}