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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DossierTax}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DossierTax
 * @generated
 */
@ProviderType
public class DossierTaxWrapper implements DossierTax, ModelWrapper<DossierTax> {
	public DossierTaxWrapper(DossierTax dossierTax) {
		_dossierTax = dossierTax;
	}

	@Override
	public Class<?> getModelClass() {
		return DossierTax.class;
	}

	@Override
	public String getModelClassName() {
		return DossierTax.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("taxId", getTaxId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("dossierNo", getDossierNo());
		attributes.put("maSoThue", getMaSoThue());
		attributes.put("soQuyetDinh", getSoQuyetDinh());
		attributes.put("ngayQuyetDinh", getNgayQuyetDinh());
		attributes.put("tenTieuMuc", getTenTieuMuc());
		attributes.put("soTien", getSoTien());
		attributes.put("hoTenNguoiNopTien", getHoTenNguoiNopTien());
		attributes.put("soCmtNguoiNopTien", getSoCmtNguoiNopTien());
		attributes.put("diaChiNguoiNopTien", getDiaChiNguoiNopTien());
		attributes.put("tinhNguoiNopTien", getTinhNguoiNopTien());
		attributes.put("huyenNguoiNopTien", getHuyenNguoiNopTien());
		attributes.put("xaNguoiNopTien", getXaNguoiNopTien());
		attributes.put("thoiGianThanhToan", getThoiGianThanhToan());
		attributes.put("soTienNop", getSoTienNop());
		attributes.put("noiDungThanhToan", getNoiDungThanhToan());
		attributes.put("trangThaiThanhToan", getTrangThaiThanhToan());
		attributes.put("fileChungTu", getFileChungTu());
		attributes.put("ngayThueTraThongBao", getNgayThueTraThongBao());
		attributes.put("ngayTraThongBao", getNgayTraThongBao());
		attributes.put("ngayNhanBienLai", getNgayNhanBienLai());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long taxId = (Long)attributes.get("taxId");

		if (taxId != null) {
			setTaxId(taxId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		String dossierNo = (String)attributes.get("dossierNo");

		if (dossierNo != null) {
			setDossierNo(dossierNo);
		}

		String maSoThue = (String)attributes.get("maSoThue");

		if (maSoThue != null) {
			setMaSoThue(maSoThue);
		}

		String soQuyetDinh = (String)attributes.get("soQuyetDinh");

		if (soQuyetDinh != null) {
			setSoQuyetDinh(soQuyetDinh);
		}

		Date ngayQuyetDinh = (Date)attributes.get("ngayQuyetDinh");

		if (ngayQuyetDinh != null) {
			setNgayQuyetDinh(ngayQuyetDinh);
		}

		String tenTieuMuc = (String)attributes.get("tenTieuMuc");

		if (tenTieuMuc != null) {
			setTenTieuMuc(tenTieuMuc);
		}

		Integer soTien = (Integer)attributes.get("soTien");

		if (soTien != null) {
			setSoTien(soTien);
		}

		String hoTenNguoiNopTien = (String)attributes.get("hoTenNguoiNopTien");

		if (hoTenNguoiNopTien != null) {
			setHoTenNguoiNopTien(hoTenNguoiNopTien);
		}

		Integer soCmtNguoiNopTien = (Integer)attributes.get("soCmtNguoiNopTien");

		if (soCmtNguoiNopTien != null) {
			setSoCmtNguoiNopTien(soCmtNguoiNopTien);
		}

		String diaChiNguoiNopTien = (String)attributes.get("diaChiNguoiNopTien");

		if (diaChiNguoiNopTien != null) {
			setDiaChiNguoiNopTien(diaChiNguoiNopTien);
		}

		String tinhNguoiNopTien = (String)attributes.get("tinhNguoiNopTien");

		if (tinhNguoiNopTien != null) {
			setTinhNguoiNopTien(tinhNguoiNopTien);
		}

		String huyenNguoiNopTien = (String)attributes.get("huyenNguoiNopTien");

		if (huyenNguoiNopTien != null) {
			setHuyenNguoiNopTien(huyenNguoiNopTien);
		}

		String xaNguoiNopTien = (String)attributes.get("xaNguoiNopTien");

		if (xaNguoiNopTien != null) {
			setXaNguoiNopTien(xaNguoiNopTien);
		}

		Date thoiGianThanhToan = (Date)attributes.get("thoiGianThanhToan");

		if (thoiGianThanhToan != null) {
			setThoiGianThanhToan(thoiGianThanhToan);
		}

		Integer soTienNop = (Integer)attributes.get("soTienNop");

		if (soTienNop != null) {
			setSoTienNop(soTienNop);
		}

		String noiDungThanhToan = (String)attributes.get("noiDungThanhToan");

		if (noiDungThanhToan != null) {
			setNoiDungThanhToan(noiDungThanhToan);
		}

		Integer trangThaiThanhToan = (Integer)attributes.get(
				"trangThaiThanhToan");

		if (trangThaiThanhToan != null) {
			setTrangThaiThanhToan(trangThaiThanhToan);
		}

		String fileChungTu = (String)attributes.get("fileChungTu");

		if (fileChungTu != null) {
			setFileChungTu(fileChungTu);
		}

		Date ngayThueTraThongBao = (Date)attributes.get("ngayThueTraThongBao");

		if (ngayThueTraThongBao != null) {
			setNgayThueTraThongBao(ngayThueTraThongBao);
		}

		Date ngayTraThongBao = (Date)attributes.get("ngayTraThongBao");

		if (ngayTraThongBao != null) {
			setNgayTraThongBao(ngayTraThongBao);
		}

		Date ngayNhanBienLai = (Date)attributes.get("ngayNhanBienLai");

		if (ngayNhanBienLai != null) {
			setNgayNhanBienLai(ngayNhanBienLai);
		}
	}

	@Override
	public Object clone() {
		return new DossierTaxWrapper((DossierTax)_dossierTax.clone());
	}

	@Override
	public int compareTo(DossierTax dossierTax) {
		return _dossierTax.compareTo(dossierTax);
	}

	/**
	* Returns the company ID of this dossier tax.
	*
	* @return the company ID of this dossier tax
	*/
	@Override
	public long getCompanyId() {
		return _dossierTax.getCompanyId();
	}

	/**
	* Returns the create date of this dossier tax.
	*
	* @return the create date of this dossier tax
	*/
	@Override
	public Date getCreateDate() {
		return _dossierTax.getCreateDate();
	}

	/**
	* Returns the dia chi nguoi nop tien of this dossier tax.
	*
	* @return the dia chi nguoi nop tien of this dossier tax
	*/
	@Override
	public String getDiaChiNguoiNopTien() {
		return _dossierTax.getDiaChiNguoiNopTien();
	}

	/**
	* Returns the dossier ID of this dossier tax.
	*
	* @return the dossier ID of this dossier tax
	*/
	@Override
	public long getDossierId() {
		return _dossierTax.getDossierId();
	}

	/**
	* Returns the dossier no of this dossier tax.
	*
	* @return the dossier no of this dossier tax
	*/
	@Override
	public String getDossierNo() {
		return _dossierTax.getDossierNo();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dossierTax.getExpandoBridge();
	}

	/**
	* Returns the file chung tu of this dossier tax.
	*
	* @return the file chung tu of this dossier tax
	*/
	@Override
	public String getFileChungTu() {
		return _dossierTax.getFileChungTu();
	}

	/**
	* Returns the group ID of this dossier tax.
	*
	* @return the group ID of this dossier tax
	*/
	@Override
	public long getGroupId() {
		return _dossierTax.getGroupId();
	}

	/**
	* Returns the ho ten nguoi nop tien of this dossier tax.
	*
	* @return the ho ten nguoi nop tien of this dossier tax
	*/
	@Override
	public String getHoTenNguoiNopTien() {
		return _dossierTax.getHoTenNguoiNopTien();
	}

	/**
	* Returns the huyen nguoi nop tien of this dossier tax.
	*
	* @return the huyen nguoi nop tien of this dossier tax
	*/
	@Override
	public String getHuyenNguoiNopTien() {
		return _dossierTax.getHuyenNguoiNopTien();
	}

	/**
	* Returns the ma so thue of this dossier tax.
	*
	* @return the ma so thue of this dossier tax
	*/
	@Override
	public String getMaSoThue() {
		return _dossierTax.getMaSoThue();
	}

	/**
	* Returns the modified date of this dossier tax.
	*
	* @return the modified date of this dossier tax
	*/
	@Override
	public Date getModifiedDate() {
		return _dossierTax.getModifiedDate();
	}

	/**
	* Returns the ngay nhan bien lai of this dossier tax.
	*
	* @return the ngay nhan bien lai of this dossier tax
	*/
	@Override
	public Date getNgayNhanBienLai() {
		return _dossierTax.getNgayNhanBienLai();
	}

	/**
	* Returns the ngay quyet dinh of this dossier tax.
	*
	* @return the ngay quyet dinh of this dossier tax
	*/
	@Override
	public Date getNgayQuyetDinh() {
		return _dossierTax.getNgayQuyetDinh();
	}

	/**
	* Returns the ngay thue tra thong bao of this dossier tax.
	*
	* @return the ngay thue tra thong bao of this dossier tax
	*/
	@Override
	public Date getNgayThueTraThongBao() {
		return _dossierTax.getNgayThueTraThongBao();
	}

	/**
	* Returns the ngay tra thong bao of this dossier tax.
	*
	* @return the ngay tra thong bao of this dossier tax
	*/
	@Override
	public Date getNgayTraThongBao() {
		return _dossierTax.getNgayTraThongBao();
	}

	/**
	* Returns the noi dung thanh toan of this dossier tax.
	*
	* @return the noi dung thanh toan of this dossier tax
	*/
	@Override
	public String getNoiDungThanhToan() {
		return _dossierTax.getNoiDungThanhToan();
	}

	/**
	* Returns the primary key of this dossier tax.
	*
	* @return the primary key of this dossier tax
	*/
	@Override
	public long getPrimaryKey() {
		return _dossierTax.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dossierTax.getPrimaryKeyObj();
	}

	/**
	* Returns the so cmt nguoi nop tien of this dossier tax.
	*
	* @return the so cmt nguoi nop tien of this dossier tax
	*/
	@Override
	public int getSoCmtNguoiNopTien() {
		return _dossierTax.getSoCmtNguoiNopTien();
	}

	/**
	* Returns the so quyet dinh of this dossier tax.
	*
	* @return the so quyet dinh of this dossier tax
	*/
	@Override
	public String getSoQuyetDinh() {
		return _dossierTax.getSoQuyetDinh();
	}

	/**
	* Returns the so tien of this dossier tax.
	*
	* @return the so tien of this dossier tax
	*/
	@Override
	public int getSoTien() {
		return _dossierTax.getSoTien();
	}

	/**
	* Returns the so tien nop of this dossier tax.
	*
	* @return the so tien nop of this dossier tax
	*/
	@Override
	public int getSoTienNop() {
		return _dossierTax.getSoTienNop();
	}

	/**
	* Returns the tax ID of this dossier tax.
	*
	* @return the tax ID of this dossier tax
	*/
	@Override
	public long getTaxId() {
		return _dossierTax.getTaxId();
	}

	/**
	* Returns the ten tieu muc of this dossier tax.
	*
	* @return the ten tieu muc of this dossier tax
	*/
	@Override
	public String getTenTieuMuc() {
		return _dossierTax.getTenTieuMuc();
	}

	/**
	* Returns the thoi gian thanh toan of this dossier tax.
	*
	* @return the thoi gian thanh toan of this dossier tax
	*/
	@Override
	public Date getThoiGianThanhToan() {
		return _dossierTax.getThoiGianThanhToan();
	}

	/**
	* Returns the tinh nguoi nop tien of this dossier tax.
	*
	* @return the tinh nguoi nop tien of this dossier tax
	*/
	@Override
	public String getTinhNguoiNopTien() {
		return _dossierTax.getTinhNguoiNopTien();
	}

	/**
	* Returns the trang thai thanh toan of this dossier tax.
	*
	* @return the trang thai thanh toan of this dossier tax
	*/
	@Override
	public int getTrangThaiThanhToan() {
		return _dossierTax.getTrangThaiThanhToan();
	}

	/**
	* Returns the user ID of this dossier tax.
	*
	* @return the user ID of this dossier tax
	*/
	@Override
	public long getUserId() {
		return _dossierTax.getUserId();
	}

	/**
	* Returns the user name of this dossier tax.
	*
	* @return the user name of this dossier tax
	*/
	@Override
	public String getUserName() {
		return _dossierTax.getUserName();
	}

	/**
	* Returns the user uuid of this dossier tax.
	*
	* @return the user uuid of this dossier tax
	*/
	@Override
	public String getUserUuid() {
		return _dossierTax.getUserUuid();
	}

	/**
	* Returns the uuid of this dossier tax.
	*
	* @return the uuid of this dossier tax
	*/
	@Override
	public String getUuid() {
		return _dossierTax.getUuid();
	}

	/**
	* Returns the xa nguoi nop tien of this dossier tax.
	*
	* @return the xa nguoi nop tien of this dossier tax
	*/
	@Override
	public String getXaNguoiNopTien() {
		return _dossierTax.getXaNguoiNopTien();
	}

	@Override
	public int hashCode() {
		return _dossierTax.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dossierTax.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dossierTax.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dossierTax.isNew();
	}

	@Override
	public void persist() {
		_dossierTax.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dossierTax.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this dossier tax.
	*
	* @param companyId the company ID of this dossier tax
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dossierTax.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this dossier tax.
	*
	* @param createDate the create date of this dossier tax
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_dossierTax.setCreateDate(createDate);
	}

	/**
	* Sets the dia chi nguoi nop tien of this dossier tax.
	*
	* @param diaChiNguoiNopTien the dia chi nguoi nop tien of this dossier tax
	*/
	@Override
	public void setDiaChiNguoiNopTien(String diaChiNguoiNopTien) {
		_dossierTax.setDiaChiNguoiNopTien(diaChiNguoiNopTien);
	}

	/**
	* Sets the dossier ID of this dossier tax.
	*
	* @param dossierId the dossier ID of this dossier tax
	*/
	@Override
	public void setDossierId(long dossierId) {
		_dossierTax.setDossierId(dossierId);
	}

	/**
	* Sets the dossier no of this dossier tax.
	*
	* @param dossierNo the dossier no of this dossier tax
	*/
	@Override
	public void setDossierNo(String dossierNo) {
		_dossierTax.setDossierNo(dossierNo);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_dossierTax.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_dossierTax.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_dossierTax.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file chung tu of this dossier tax.
	*
	* @param fileChungTu the file chung tu of this dossier tax
	*/
	@Override
	public void setFileChungTu(String fileChungTu) {
		_dossierTax.setFileChungTu(fileChungTu);
	}

	/**
	* Sets the group ID of this dossier tax.
	*
	* @param groupId the group ID of this dossier tax
	*/
	@Override
	public void setGroupId(long groupId) {
		_dossierTax.setGroupId(groupId);
	}

	/**
	* Sets the ho ten nguoi nop tien of this dossier tax.
	*
	* @param hoTenNguoiNopTien the ho ten nguoi nop tien of this dossier tax
	*/
	@Override
	public void setHoTenNguoiNopTien(String hoTenNguoiNopTien) {
		_dossierTax.setHoTenNguoiNopTien(hoTenNguoiNopTien);
	}

	/**
	* Sets the huyen nguoi nop tien of this dossier tax.
	*
	* @param huyenNguoiNopTien the huyen nguoi nop tien of this dossier tax
	*/
	@Override
	public void setHuyenNguoiNopTien(String huyenNguoiNopTien) {
		_dossierTax.setHuyenNguoiNopTien(huyenNguoiNopTien);
	}

	/**
	* Sets the ma so thue of this dossier tax.
	*
	* @param maSoThue the ma so thue of this dossier tax
	*/
	@Override
	public void setMaSoThue(String maSoThue) {
		_dossierTax.setMaSoThue(maSoThue);
	}

	/**
	* Sets the modified date of this dossier tax.
	*
	* @param modifiedDate the modified date of this dossier tax
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_dossierTax.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_dossierTax.setNew(n);
	}

	/**
	* Sets the ngay nhan bien lai of this dossier tax.
	*
	* @param ngayNhanBienLai the ngay nhan bien lai of this dossier tax
	*/
	@Override
	public void setNgayNhanBienLai(Date ngayNhanBienLai) {
		_dossierTax.setNgayNhanBienLai(ngayNhanBienLai);
	}

	/**
	* Sets the ngay quyet dinh of this dossier tax.
	*
	* @param ngayQuyetDinh the ngay quyet dinh of this dossier tax
	*/
	@Override
	public void setNgayQuyetDinh(Date ngayQuyetDinh) {
		_dossierTax.setNgayQuyetDinh(ngayQuyetDinh);
	}

	/**
	* Sets the ngay thue tra thong bao of this dossier tax.
	*
	* @param ngayThueTraThongBao the ngay thue tra thong bao of this dossier tax
	*/
	@Override
	public void setNgayThueTraThongBao(Date ngayThueTraThongBao) {
		_dossierTax.setNgayThueTraThongBao(ngayThueTraThongBao);
	}

	/**
	* Sets the ngay tra thong bao of this dossier tax.
	*
	* @param ngayTraThongBao the ngay tra thong bao of this dossier tax
	*/
	@Override
	public void setNgayTraThongBao(Date ngayTraThongBao) {
		_dossierTax.setNgayTraThongBao(ngayTraThongBao);
	}

	/**
	* Sets the noi dung thanh toan of this dossier tax.
	*
	* @param noiDungThanhToan the noi dung thanh toan of this dossier tax
	*/
	@Override
	public void setNoiDungThanhToan(String noiDungThanhToan) {
		_dossierTax.setNoiDungThanhToan(noiDungThanhToan);
	}

	/**
	* Sets the primary key of this dossier tax.
	*
	* @param primaryKey the primary key of this dossier tax
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dossierTax.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dossierTax.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the so cmt nguoi nop tien of this dossier tax.
	*
	* @param soCmtNguoiNopTien the so cmt nguoi nop tien of this dossier tax
	*/
	@Override
	public void setSoCmtNguoiNopTien(int soCmtNguoiNopTien) {
		_dossierTax.setSoCmtNguoiNopTien(soCmtNguoiNopTien);
	}

	/**
	* Sets the so quyet dinh of this dossier tax.
	*
	* @param soQuyetDinh the so quyet dinh of this dossier tax
	*/
	@Override
	public void setSoQuyetDinh(String soQuyetDinh) {
		_dossierTax.setSoQuyetDinh(soQuyetDinh);
	}

	/**
	* Sets the so tien of this dossier tax.
	*
	* @param soTien the so tien of this dossier tax
	*/
	@Override
	public void setSoTien(int soTien) {
		_dossierTax.setSoTien(soTien);
	}

	/**
	* Sets the so tien nop of this dossier tax.
	*
	* @param soTienNop the so tien nop of this dossier tax
	*/
	@Override
	public void setSoTienNop(int soTienNop) {
		_dossierTax.setSoTienNop(soTienNop);
	}

	/**
	* Sets the tax ID of this dossier tax.
	*
	* @param taxId the tax ID of this dossier tax
	*/
	@Override
	public void setTaxId(long taxId) {
		_dossierTax.setTaxId(taxId);
	}

	/**
	* Sets the ten tieu muc of this dossier tax.
	*
	* @param tenTieuMuc the ten tieu muc of this dossier tax
	*/
	@Override
	public void setTenTieuMuc(String tenTieuMuc) {
		_dossierTax.setTenTieuMuc(tenTieuMuc);
	}

	/**
	* Sets the thoi gian thanh toan of this dossier tax.
	*
	* @param thoiGianThanhToan the thoi gian thanh toan of this dossier tax
	*/
	@Override
	public void setThoiGianThanhToan(Date thoiGianThanhToan) {
		_dossierTax.setThoiGianThanhToan(thoiGianThanhToan);
	}

	/**
	* Sets the tinh nguoi nop tien of this dossier tax.
	*
	* @param tinhNguoiNopTien the tinh nguoi nop tien of this dossier tax
	*/
	@Override
	public void setTinhNguoiNopTien(String tinhNguoiNopTien) {
		_dossierTax.setTinhNguoiNopTien(tinhNguoiNopTien);
	}

	/**
	* Sets the trang thai thanh toan of this dossier tax.
	*
	* @param trangThaiThanhToan the trang thai thanh toan of this dossier tax
	*/
	@Override
	public void setTrangThaiThanhToan(int trangThaiThanhToan) {
		_dossierTax.setTrangThaiThanhToan(trangThaiThanhToan);
	}

	/**
	* Sets the user ID of this dossier tax.
	*
	* @param userId the user ID of this dossier tax
	*/
	@Override
	public void setUserId(long userId) {
		_dossierTax.setUserId(userId);
	}

	/**
	* Sets the user name of this dossier tax.
	*
	* @param userName the user name of this dossier tax
	*/
	@Override
	public void setUserName(String userName) {
		_dossierTax.setUserName(userName);
	}

	/**
	* Sets the user uuid of this dossier tax.
	*
	* @param userUuid the user uuid of this dossier tax
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_dossierTax.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this dossier tax.
	*
	* @param uuid the uuid of this dossier tax
	*/
	@Override
	public void setUuid(String uuid) {
		_dossierTax.setUuid(uuid);
	}

	/**
	* Sets the xa nguoi nop tien of this dossier tax.
	*
	* @param xaNguoiNopTien the xa nguoi nop tien of this dossier tax
	*/
	@Override
	public void setXaNguoiNopTien(String xaNguoiNopTien) {
		_dossierTax.setXaNguoiNopTien(xaNguoiNopTien);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DossierTax> toCacheModel() {
		return _dossierTax.toCacheModel();
	}

	@Override
	public DossierTax toEscapedModel() {
		return new DossierTaxWrapper(_dossierTax.toEscapedModel());
	}

	@Override
	public String toString() {
		return _dossierTax.toString();
	}

	@Override
	public DossierTax toUnescapedModel() {
		return new DossierTaxWrapper(_dossierTax.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _dossierTax.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DossierTaxWrapper)) {
			return false;
		}

		DossierTaxWrapper dossierTaxWrapper = (DossierTaxWrapper)obj;

		if (Objects.equals(_dossierTax, dossierTaxWrapper._dossierTax)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dossierTax.getStagedModelType();
	}

	@Override
	public DossierTax getWrappedModel() {
		return _dossierTax;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dossierTax.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dossierTax.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dossierTax.resetOriginalValues();
	}

	private final DossierTax _dossierTax;
}