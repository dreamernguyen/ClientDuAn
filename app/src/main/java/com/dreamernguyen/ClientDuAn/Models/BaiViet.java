package com.dreamernguyen.ClientDuAn.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaiViet {


    @SerializedName("linkAnh")
    @Expose
    private List<String> linkAnh = null;
    @SerializedName("luotThich")
    @Expose
    private List<NguoiDung> luotThich = null;
    @SerializedName("trangThai")
    @Expose
    private Boolean trangThai;
    @SerializedName("idNguoiDung")
    @Expose
    private NguoiDung idNguoiDung;
    @SerializedName("noiDung")
    @Expose
    private String noiDung;
    @SerializedName("thoiGianTao")
    @Expose
    private String thoiGianTao;
    @SerializedName("thoiGianCapNhat")
    @Expose
    private String thoiGianCapNhat;

    public List<String> getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(List<String> linkAnh) {
        this.linkAnh = linkAnh;
    }

    public List<NguoiDung> getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(List<NguoiDung> luotThich) {
        this.luotThich = luotThich;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public NguoiDung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(NguoiDung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
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


    public BaiViet(List<String> linkAnh, String noiDung) {
        this.linkAnh = linkAnh;
        this.noiDung = noiDung;
    }
}

