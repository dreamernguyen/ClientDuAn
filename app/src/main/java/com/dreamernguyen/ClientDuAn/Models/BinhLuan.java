package com.dreamernguyen.ClientDuAn.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BinhLuan {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("idNguoiDung")
    @Expose
    private String idNguoiDung;
    @SerializedName("noiDung")
    @Expose
    private String noiDung;
    @SerializedName("thoiGianTao")
    @Expose
    private String thoiGianTao;
    @SerializedName("thoiGianCapNhat")
    @Expose
    private String thoiGianCapNhat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
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
}
