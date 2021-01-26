package com.dreamernguyen.ClientDuAn.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NguoiDung {

    @SerializedName("dangTheoDoi")
    @Expose
    private List<NguoiDung> dangTheoDoi = null;
    @SerializedName("duocTheoDoi")
    @Expose
    private List<NguoiDung> duocTheoDoi = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("matKhau")
    @Expose
    private String matKhau;
    @SerializedName("hoTen")
    @Expose
    private String hoTen;
    @SerializedName("thoiGianTao")
    @Expose
    private String thoiGianTao;
    @SerializedName("thoiGianCapNhat")
    @Expose
    private String thoiGianCapNhat;
    @SerializedName("sdt")
    @Expose
    private Integer sdt;
    @SerializedName("tuoi")
    @Expose
    private Integer tuoi;
    @SerializedName("diaChi")
    @Expose
    private String diaChi;

    public List<NguoiDung> getDangTheoDoi() {
        return dangTheoDoi;
    }

    public void setDangTheoDoi(List<NguoiDung> dangTheoDoi) {
        this.dangTheoDoi = dangTheoDoi;
    }

    public List<NguoiDung> getDuocTheoDoi() {
        return duocTheoDoi;
    }

    public void setDuocTheoDoi(List<NguoiDung> duocTheoDoi) {
        this.duocTheoDoi = duocTheoDoi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(String thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }
    public Integer getSdt() {
        return sdt;
    }

    public void setSdt(Integer sdt) {
        this.sdt = sdt;
    }

    public Integer getTuoi() {
        return tuoi;
    }

    public void setTuoi(Integer tuoi) {
        this.tuoi = tuoi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


    public String getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(String thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }
}
