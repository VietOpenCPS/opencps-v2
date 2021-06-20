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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the DossierTax service. Represents a row in the &quot;opencps_dossier_tax&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.opencps.synctracking.model.impl.DossierTaxModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.opencps.synctracking.model.impl.DossierTaxImpl}.
 * </p>
 *
 * @author duongnt
 * @see DossierTax
 * @see org.opencps.synctracking.model.impl.DossierTaxImpl
 * @see org.opencps.synctracking.model.impl.DossierTaxModelImpl
 * @generated
 */
@ProviderType
public interface DossierTaxModel extends BaseModel<DossierTax>, GroupedModel,
	ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a dossier tax model instance should use the {@link DossierTax} interface instead.
	 */

	/**
	 * Returns the primary key of this dossier tax.
	 *
	 * @return the primary key of this dossier tax
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this dossier tax.
	 *
	 * @param primaryKey the primary key of this dossier tax
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this dossier tax.
	 *
	 * @return the uuid of this dossier tax
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this dossier tax.
	 *
	 * @param uuid the uuid of this dossier tax
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the tax ID of this dossier tax.
	 *
	 * @return the tax ID of this dossier tax
	 */
	public long getTaxId();

	/**
	 * Sets the tax ID of this dossier tax.
	 *
	 * @param taxId the tax ID of this dossier tax
	 */
	public void setTaxId(long taxId);

	/**
	 * Returns the company ID of this dossier tax.
	 *
	 * @return the company ID of this dossier tax
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this dossier tax.
	 *
	 * @param companyId the company ID of this dossier tax
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the group ID of this dossier tax.
	 *
	 * @return the group ID of this dossier tax
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this dossier tax.
	 *
	 * @param groupId the group ID of this dossier tax
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the user ID of this dossier tax.
	 *
	 * @return the user ID of this dossier tax
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this dossier tax.
	 *
	 * @param userId the user ID of this dossier tax
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this dossier tax.
	 *
	 * @return the user uuid of this dossier tax
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this dossier tax.
	 *
	 * @param userUuid the user uuid of this dossier tax
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this dossier tax.
	 *
	 * @return the user name of this dossier tax
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this dossier tax.
	 *
	 * @param userName the user name of this dossier tax
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this dossier tax.
	 *
	 * @return the create date of this dossier tax
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this dossier tax.
	 *
	 * @param createDate the create date of this dossier tax
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this dossier tax.
	 *
	 * @return the modified date of this dossier tax
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this dossier tax.
	 *
	 * @param modifiedDate the modified date of this dossier tax
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the dossier ID of this dossier tax.
	 *
	 * @return the dossier ID of this dossier tax
	 */
	public long getDossierId();

	/**
	 * Sets the dossier ID of this dossier tax.
	 *
	 * @param dossierId the dossier ID of this dossier tax
	 */
	public void setDossierId(long dossierId);

	/**
	 * Returns the dossier no of this dossier tax.
	 *
	 * @return the dossier no of this dossier tax
	 */
	@AutoEscape
	public String getDossierNo();

	/**
	 * Sets the dossier no of this dossier tax.
	 *
	 * @param dossierNo the dossier no of this dossier tax
	 */
	public void setDossierNo(String dossierNo);

	/**
	 * Returns the ma so thue of this dossier tax.
	 *
	 * @return the ma so thue of this dossier tax
	 */
	@AutoEscape
	public String getMaSoThue();

	/**
	 * Sets the ma so thue of this dossier tax.
	 *
	 * @param maSoThue the ma so thue of this dossier tax
	 */
	public void setMaSoThue(String maSoThue);

	/**
	 * Returns the so quyet dinh of this dossier tax.
	 *
	 * @return the so quyet dinh of this dossier tax
	 */
	@AutoEscape
	public String getSoQuyetDinh();

	/**
	 * Sets the so quyet dinh of this dossier tax.
	 *
	 * @param soQuyetDinh the so quyet dinh of this dossier tax
	 */
	public void setSoQuyetDinh(String soQuyetDinh);

	/**
	 * Returns the ngay quyet dinh of this dossier tax.
	 *
	 * @return the ngay quyet dinh of this dossier tax
	 */
	public Date getNgayQuyetDinh();

	/**
	 * Sets the ngay quyet dinh of this dossier tax.
	 *
	 * @param ngayQuyetDinh the ngay quyet dinh of this dossier tax
	 */
	public void setNgayQuyetDinh(Date ngayQuyetDinh);

	/**
	 * Returns the ten tieu muc of this dossier tax.
	 *
	 * @return the ten tieu muc of this dossier tax
	 */
	@AutoEscape
	public String getTenTieuMuc();

	/**
	 * Sets the ten tieu muc of this dossier tax.
	 *
	 * @param tenTieuMuc the ten tieu muc of this dossier tax
	 */
	public void setTenTieuMuc(String tenTieuMuc);

	/**
	 * Returns the so tien of this dossier tax.
	 *
	 * @return the so tien of this dossier tax
	 */
	public int getSoTien();

	/**
	 * Sets the so tien of this dossier tax.
	 *
	 * @param soTien the so tien of this dossier tax
	 */
	public void setSoTien(int soTien);

	/**
	 * Returns the ho ten nguoi nop tien of this dossier tax.
	 *
	 * @return the ho ten nguoi nop tien of this dossier tax
	 */
	@AutoEscape
	public String getHoTenNguoiNopTien();

	/**
	 * Sets the ho ten nguoi nop tien of this dossier tax.
	 *
	 * @param hoTenNguoiNopTien the ho ten nguoi nop tien of this dossier tax
	 */
	public void setHoTenNguoiNopTien(String hoTenNguoiNopTien);

	/**
	 * Returns the so cmt nguoi nop tien of this dossier tax.
	 *
	 * @return the so cmt nguoi nop tien of this dossier tax
	 */
	public int getSoCmtNguoiNopTien();

	/**
	 * Sets the so cmt nguoi nop tien of this dossier tax.
	 *
	 * @param soCmtNguoiNopTien the so cmt nguoi nop tien of this dossier tax
	 */
	public void setSoCmtNguoiNopTien(int soCmtNguoiNopTien);

	/**
	 * Returns the dia chi nguoi nop tien of this dossier tax.
	 *
	 * @return the dia chi nguoi nop tien of this dossier tax
	 */
	@AutoEscape
	public String getDiaChiNguoiNopTien();

	/**
	 * Sets the dia chi nguoi nop tien of this dossier tax.
	 *
	 * @param diaChiNguoiNopTien the dia chi nguoi nop tien of this dossier tax
	 */
	public void setDiaChiNguoiNopTien(String diaChiNguoiNopTien);

	/**
	 * Returns the tinh nguoi nop tien of this dossier tax.
	 *
	 * @return the tinh nguoi nop tien of this dossier tax
	 */
	@AutoEscape
	public String getTinhNguoiNopTien();

	/**
	 * Sets the tinh nguoi nop tien of this dossier tax.
	 *
	 * @param tinhNguoiNopTien the tinh nguoi nop tien of this dossier tax
	 */
	public void setTinhNguoiNopTien(String tinhNguoiNopTien);

	/**
	 * Returns the huyen nguoi nop tien of this dossier tax.
	 *
	 * @return the huyen nguoi nop tien of this dossier tax
	 */
	@AutoEscape
	public String getHuyenNguoiNopTien();

	/**
	 * Sets the huyen nguoi nop tien of this dossier tax.
	 *
	 * @param huyenNguoiNopTien the huyen nguoi nop tien of this dossier tax
	 */
	public void setHuyenNguoiNopTien(String huyenNguoiNopTien);

	/**
	 * Returns the xa nguoi nop tien of this dossier tax.
	 *
	 * @return the xa nguoi nop tien of this dossier tax
	 */
	@AutoEscape
	public String getXaNguoiNopTien();

	/**
	 * Sets the xa nguoi nop tien of this dossier tax.
	 *
	 * @param xaNguoiNopTien the xa nguoi nop tien of this dossier tax
	 */
	public void setXaNguoiNopTien(String xaNguoiNopTien);

	/**
	 * Returns the thoi gian thanh toan of this dossier tax.
	 *
	 * @return the thoi gian thanh toan of this dossier tax
	 */
	public Date getThoiGianThanhToan();

	/**
	 * Sets the thoi gian thanh toan of this dossier tax.
	 *
	 * @param thoiGianThanhToan the thoi gian thanh toan of this dossier tax
	 */
	public void setThoiGianThanhToan(Date thoiGianThanhToan);

	/**
	 * Returns the so tien nop of this dossier tax.
	 *
	 * @return the so tien nop of this dossier tax
	 */
	public int getSoTienNop();

	/**
	 * Sets the so tien nop of this dossier tax.
	 *
	 * @param soTienNop the so tien nop of this dossier tax
	 */
	public void setSoTienNop(int soTienNop);

	/**
	 * Returns the noi dung thanh toan of this dossier tax.
	 *
	 * @return the noi dung thanh toan of this dossier tax
	 */
	@AutoEscape
	public String getNoiDungThanhToan();

	/**
	 * Sets the noi dung thanh toan of this dossier tax.
	 *
	 * @param noiDungThanhToan the noi dung thanh toan of this dossier tax
	 */
	public void setNoiDungThanhToan(String noiDungThanhToan);

	/**
	 * Returns the trang thai thanh toan of this dossier tax.
	 *
	 * @return the trang thai thanh toan of this dossier tax
	 */
	public int getTrangThaiThanhToan();

	/**
	 * Sets the trang thai thanh toan of this dossier tax.
	 *
	 * @param trangThaiThanhToan the trang thai thanh toan of this dossier tax
	 */
	public void setTrangThaiThanhToan(int trangThaiThanhToan);

	/**
	 * Returns the file chung tu of this dossier tax.
	 *
	 * @return the file chung tu of this dossier tax
	 */
	@AutoEscape
	public String getFileChungTu();

	/**
	 * Sets the file chung tu of this dossier tax.
	 *
	 * @param fileChungTu the file chung tu of this dossier tax
	 */
	public void setFileChungTu(String fileChungTu);

	/**
	 * Returns the ngay thue tra thong bao of this dossier tax.
	 *
	 * @return the ngay thue tra thong bao of this dossier tax
	 */
	public Date getNgayThueTraThongBao();

	/**
	 * Sets the ngay thue tra thong bao of this dossier tax.
	 *
	 * @param ngayThueTraThongBao the ngay thue tra thong bao of this dossier tax
	 */
	public void setNgayThueTraThongBao(Date ngayThueTraThongBao);

	/**
	 * Returns the ngay tra thong bao of this dossier tax.
	 *
	 * @return the ngay tra thong bao of this dossier tax
	 */
	public Date getNgayTraThongBao();

	/**
	 * Sets the ngay tra thong bao of this dossier tax.
	 *
	 * @param ngayTraThongBao the ngay tra thong bao of this dossier tax
	 */
	public void setNgayTraThongBao(Date ngayTraThongBao);

	/**
	 * Returns the ngay nhan bien lai of this dossier tax.
	 *
	 * @return the ngay nhan bien lai of this dossier tax
	 */
	public Date getNgayNhanBienLai();

	/**
	 * Sets the ngay nhan bien lai of this dossier tax.
	 *
	 * @param ngayNhanBienLai the ngay nhan bien lai of this dossier tax
	 */
	public void setNgayNhanBienLai(Date ngayNhanBienLai);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(DossierTax dossierTax);

	@Override
	public int hashCode();

	@Override
	public CacheModel<DossierTax> toCacheModel();

	@Override
	public DossierTax toEscapedModel();

	@Override
	public DossierTax toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}