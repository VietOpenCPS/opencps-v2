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

package org.opencps.synctracking.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author duongnt
 * @generated
 */
@ProviderType
public class DossierTaxSoap implements Serializable {
	public static DossierTaxSoap toSoapModel(DossierTax model) {
		DossierTaxSoap soapModel = new DossierTaxSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTaxId(model.getTaxId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossierNo(model.getDossierNo());
		soapModel.setMaSoThue(model.getMaSoThue());
		soapModel.setSoQuyetDinh(model.getSoQuyetDinh());
		soapModel.setNgayQuyetDinh(model.getNgayQuyetDinh());
		soapModel.setTenTieuMuc(model.getTenTieuMuc());
		soapModel.setSoTien(model.getSoTien());
		soapModel.setHoTenNguoiNopTien(model.getHoTenNguoiNopTien());
		soapModel.setSoCmtNguoiNopTien(model.getSoCmtNguoiNopTien());
		soapModel.setDiaChiNguoiNopTien(model.getDiaChiNguoiNopTien());
		soapModel.setTinhNguoiNopTien(model.getTinhNguoiNopTien());
		soapModel.setHuyenNguoiNopTien(model.getHuyenNguoiNopTien());
		soapModel.setXaNguoiNopTien(model.getXaNguoiNopTien());
		soapModel.setThoiGianThanhToan(model.getThoiGianThanhToan());
		soapModel.setSoTienNop(model.getSoTienNop());
		soapModel.setNoiDungThanhToan(model.getNoiDungThanhToan());
		soapModel.setTrangThaiThanhToan(model.getTrangThaiThanhToan());
		soapModel.setFileChungTu(model.getFileChungTu());
		soapModel.setNgayThueTraThongBao(model.getNgayThueTraThongBao());
		soapModel.setNgayTraThongBao(model.getNgayTraThongBao());
		soapModel.setNgayNhanBienLai(model.getNgayNhanBienLai());
		soapModel.setStatusTBT(model.getStatusTBT());
		soapModel.setStatusCTT(model.getStatusCTT());

		return soapModel;
	}

	public static DossierTaxSoap[] toSoapModels(DossierTax[] models) {
		DossierTaxSoap[] soapModels = new DossierTaxSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DossierTaxSoap[][] toSoapModels(DossierTax[][] models) {
		DossierTaxSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DossierTaxSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DossierTaxSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DossierTaxSoap[] toSoapModels(List<DossierTax> models) {
		List<DossierTaxSoap> soapModels = new ArrayList<DossierTaxSoap>(models.size());

		for (DossierTax model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DossierTaxSoap[soapModels.size()]);
	}

	public DossierTaxSoap() {
	}

	public long getPrimaryKey() {
		return _taxId;
	}

	public void setPrimaryKey(long pk) {
		setTaxId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getTaxId() {
		return _taxId;
	}

	public void setTaxId(long taxId) {
		_taxId = taxId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getDossierNo() {
		return _dossierNo;
	}

	public void setDossierNo(String dossierNo) {
		_dossierNo = dossierNo;
	}

	public String getMaSoThue() {
		return _maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		_maSoThue = maSoThue;
	}

	public String getSoQuyetDinh() {
		return _soQuyetDinh;
	}

	public void setSoQuyetDinh(String soQuyetDinh) {
		_soQuyetDinh = soQuyetDinh;
	}

	public Date getNgayQuyetDinh() {
		return _ngayQuyetDinh;
	}

	public void setNgayQuyetDinh(Date ngayQuyetDinh) {
		_ngayQuyetDinh = ngayQuyetDinh;
	}

	public String getTenTieuMuc() {
		return _tenTieuMuc;
	}

	public void setTenTieuMuc(String tenTieuMuc) {
		_tenTieuMuc = tenTieuMuc;
	}

	public int getSoTien() {
		return _soTien;
	}

	public void setSoTien(int soTien) {
		_soTien = soTien;
	}

	public String getHoTenNguoiNopTien() {
		return _hoTenNguoiNopTien;
	}

	public void setHoTenNguoiNopTien(String hoTenNguoiNopTien) {
		_hoTenNguoiNopTien = hoTenNguoiNopTien;
	}

	public int getSoCmtNguoiNopTien() {
		return _soCmtNguoiNopTien;
	}

	public void setSoCmtNguoiNopTien(int soCmtNguoiNopTien) {
		_soCmtNguoiNopTien = soCmtNguoiNopTien;
	}

	public String getDiaChiNguoiNopTien() {
		return _diaChiNguoiNopTien;
	}

	public void setDiaChiNguoiNopTien(String diaChiNguoiNopTien) {
		_diaChiNguoiNopTien = diaChiNguoiNopTien;
	}

	public String getTinhNguoiNopTien() {
		return _tinhNguoiNopTien;
	}

	public void setTinhNguoiNopTien(String tinhNguoiNopTien) {
		_tinhNguoiNopTien = tinhNguoiNopTien;
	}

	public String getHuyenNguoiNopTien() {
		return _huyenNguoiNopTien;
	}

	public void setHuyenNguoiNopTien(String huyenNguoiNopTien) {
		_huyenNguoiNopTien = huyenNguoiNopTien;
	}

	public String getXaNguoiNopTien() {
		return _xaNguoiNopTien;
	}

	public void setXaNguoiNopTien(String xaNguoiNopTien) {
		_xaNguoiNopTien = xaNguoiNopTien;
	}

	public Date getThoiGianThanhToan() {
		return _thoiGianThanhToan;
	}

	public void setThoiGianThanhToan(Date thoiGianThanhToan) {
		_thoiGianThanhToan = thoiGianThanhToan;
	}

	public int getSoTienNop() {
		return _soTienNop;
	}

	public void setSoTienNop(int soTienNop) {
		_soTienNop = soTienNop;
	}

	public String getNoiDungThanhToan() {
		return _noiDungThanhToan;
	}

	public void setNoiDungThanhToan(String noiDungThanhToan) {
		_noiDungThanhToan = noiDungThanhToan;
	}

	public int getTrangThaiThanhToan() {
		return _trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(int trangThaiThanhToan) {
		_trangThaiThanhToan = trangThaiThanhToan;
	}

	public String getFileChungTu() {
		return _fileChungTu;
	}

	public void setFileChungTu(String fileChungTu) {
		_fileChungTu = fileChungTu;
	}

	public Date getNgayThueTraThongBao() {
		return _ngayThueTraThongBao;
	}

	public void setNgayThueTraThongBao(Date ngayThueTraThongBao) {
		_ngayThueTraThongBao = ngayThueTraThongBao;
	}

	public Date getNgayTraThongBao() {
		return _ngayTraThongBao;
	}

	public void setNgayTraThongBao(Date ngayTraThongBao) {
		_ngayTraThongBao = ngayTraThongBao;
	}

	public Date getNgayNhanBienLai() {
		return _ngayNhanBienLai;
	}

	public void setNgayNhanBienLai(Date ngayNhanBienLai) {
		_ngayNhanBienLai = ngayNhanBienLai;
	}

	public int getStatusTBT() {
		return _statusTBT;
	}

	public void setStatusTBT(int statusTBT) {
		_statusTBT = statusTBT;
	}

	public int getStatusCTT() {
		return _statusCTT;
	}

	public void setStatusCTT(int statusCTT) {
		_statusCTT = statusCTT;
	}

	private String _uuid;
	private long _taxId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _dossierNo;
	private String _maSoThue;
	private String _soQuyetDinh;
	private Date _ngayQuyetDinh;
	private String _tenTieuMuc;
	private int _soTien;
	private String _hoTenNguoiNopTien;
	private int _soCmtNguoiNopTien;
	private String _diaChiNguoiNopTien;
	private String _tinhNguoiNopTien;
	private String _huyenNguoiNopTien;
	private String _xaNguoiNopTien;
	private Date _thoiGianThanhToan;
	private int _soTienNop;
	private String _noiDungThanhToan;
	private int _trangThaiThanhToan;
	private String _fileChungTu;
	private Date _ngayThueTraThongBao;
	private Date _ngayTraThongBao;
	private Date _ngayNhanBienLai;
	private int _statusTBT;
	private int _statusCTT;
}