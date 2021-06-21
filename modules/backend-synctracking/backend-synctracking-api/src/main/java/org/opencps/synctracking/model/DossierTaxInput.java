package org.opencps.synctracking.model;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DossierTaxInput")
public class DossierTaxInput {
    public Long groupId;
    public Long dossierId;
    public String dossierNo;
    public String maSoThue;
    public String soQuyetDinh;
    public String ngayQuyetDinh;
    public String tenTieuMuc;
    public Integer soTien;

    public String hoTenNguoiNopTien;
    public String soCmtNguoiNopTien;
    public String diaChiNguoiNopTien;
    public String tinhNguoiNopTien;
    public String huyenNguoiNopTien;
    public String xaNguoiNopTien;
    public String thoiGianThanhToan;
    public String soTienNop;
    public String noiDungThanhToan;
    public Integer trangThaiThanhToan;
    public String fileChungTu;
    public String ngayThueTraThongBao;
    public String ngayTraThongBao;
    public String ngayNhanBienLai;

}
