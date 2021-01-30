package com.dreamernguyen.ClientDuAn.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatHang {
    @SerializedName("linkAnh")
    @Expose
    private List<String> linkAnh = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("hangMuc")
    @Expose
    private String hangMuc;
    @SerializedName("tieuDe")
    @Expose
    private String tieuDe;
    @SerializedName("noiDung")
    @Expose
    private String noiDung;
    @SerializedName("giaBan")
    @Expose
    private Integer giaBan;
    @SerializedName("diaChi")
    @Expose
    private String diaChi;
    @SerializedName("idNguoiDung")
    @Expose
    private NguoiDung idNguoiDung;
    @SerializedName("thoiGianTao")
    @Expose
    private String thoiGianTao;
    @SerializedName("thoiGianCapNhat")
    @Expose
    private String thoiGianCapNhat;

    //Đăng bán
    public MatHang(String hangMuc, String tieuDe, String noiDung, Integer giaBan,List<String> linkAnh,  String diaChi) {
        this.linkAnh = linkAnh;
        this.hangMuc = hangMuc;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.giaBan = giaBan;
        this.diaChi = diaChi;
    }

    public List<String> getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(List<String> linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(String hangMuc) {
        this.hangMuc = hangMuc;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Integer getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Integer giaBan) {
        this.giaBan = giaBan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public NguoiDung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(NguoiDung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(String thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(String thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }
}
