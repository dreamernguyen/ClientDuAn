package com.dreamernguyen.ClientDuAn.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {

    @SerializedName("thongBao")
    @Expose
    String thongBao;
    @SerializedName("baiViet")
    @Expose
    BaiViet baiViet;

    @SerializedName("binhLuan")
    @Expose
    BinhLuan binhLuan;


    @SerializedName("matHang")
    @Expose
    MatHang matHang;

    @SerializedName("nguoiDung")
    @Expose
    NguoiDung nguoiDung;

    @SerializedName("danhSachBaiViet")
    @Expose
    List<BaiViet> baiVietList;

    @SerializedName("ListNguoiDung")
    @Expose
    List<NguoiDung> ListNguoiDung;



    @SerializedName("ListBinhLuan")
    @Expose
    List<BinhLuan> ListBinhLuan;

    public BinhLuan getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(BinhLuan binhLuan) {
        this.binhLuan = binhLuan;
    }

    public List<BinhLuan> getListBinhLuan() {
        return ListBinhLuan;
    }

    public void setListBinhLuan(List<BinhLuan> listBinhLuan) {
        ListBinhLuan = listBinhLuan;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public List<NguoiDung> getListNguoiDung() {
        return ListNguoiDung;
    }

    public void setListNguoiDung(List<NguoiDung> listNguoiDung) {
        ListNguoiDung = listNguoiDung;
    }

    public String getThongBao() {
        return thongBao;
    }

    public void setThongBao(String thongBao) {
        this.thongBao = thongBao;
    }

    public BaiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(BaiViet baiViet) {
        this.baiViet = baiViet;
    }

    public List<BaiViet> getBaiVietList() {
        return baiVietList;
    }

    public void setBaiVietList(List<BaiViet> baiVietList) {
        this.baiVietList = baiVietList;
    }
}
