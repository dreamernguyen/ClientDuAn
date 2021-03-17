package com.dreamernguyen.ClientDuAn.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dreamernguyen.ClientDuAn.Activity.DangMatHangActivity;
import com.dreamernguyen.ClientDuAn.R;

public class DangMatHangThongTinFragment extends Fragment {


    TextView tv, tvBack;
    ListView lvThongTin;

    ArrayAdapter<String> adapter;
    String[] DanhMuc = {"bat dong san", "xe co", "do dien tu", "thu cung", "Tu lanh, may lanh, may giat", "do gia dung, noi that", "thoi trang", "Giai tri"};
    String[] DanhMucBatDongSan = {"can ho", "chung cu", "nha o", "dat", "van phong, mat bang kinh doanh", "phong tro"};
    String[] DanhMucXeCo = {"o to", "xe may", "xe tai, xe ben", "xe dien", "xe dap", "phu tung", "Phuong tien khac"};
    String[] DanhMucDoDienTu = {"dien thoai", "may tinh bang", "laptop", "may tinh de ban", "may anh", "tivi", "thiet bi deo thong minh"};
    String[] DanhMucThuCung = {"ga", "cho", "chim", "meo", "khac"};
    String[] DanhMucTuLanh = {"tuLanh ", "mayLanh,dieu hoa", "may Giat"};

    String[] ThanhPho = {"Đà Nẵng", "Hà Nội", "Hồ Chí Minh"};
    String[] QuanDaNang = {"Cẩm Lệ", "Hải Châu", " Liên Chiểu", "Ngũ Hành Sơn", "Sơn Trà", "Thanh Khê", "Hòa Vang"};
    String[] QuanHaNoi = {"ba dinh", "bac tu liem", "cau giay", "dong da", "Ha dong", "Hai ba trung", "hoan kiem", "hoang Mai", "Long bien", "Nam tu liem", "tay ho", "thanh xuan", "ba vi"};
    String[] QuanHoChiMinh = {"Quan 1", "Quan 2", "Quan 3", "Quan 4", "Quan 5", "Quan 6", "Quan 7", "Quan 8", "Quan 9", "Quan 10", "Quan 11", "Quan 12"};

    String[] PhuongCamLe = {"hoa an", "hoa phat", "hoa tho dong", "hoa tho tay", "hoa xuan", "khue trung"};
    String[] PhuongHaiChau = {"binh hien", "binh thuan", "hai chau 1", "hai chau 2", "hoa cuong bac", "hoa cuong nam", "hoa thuan tay", "hoa thuan dong", "nam duong", "phuoc ninh", "thạch thang", "Thanh bình", "thuan phuoc"};
    String[] PhuongLienChieu = {"hoa hiep bac", "hoa hiep nam", "hoa khanh bac", "hoa khanh nam", "hoa minh"};
    String[] PhuongBaDinh = {"Cong vi", "dien bien", "doi can", "giang vo", "kim ma", "lieu giai", "ngoc ha", "ngoc khanh", "nguyen trung truc"};
    String[] PhuongBacTuLiem = {" co nhue 1", "co nhue 2", "dong ngac", "duc thang", "lien mac", "Minh Khai", "Phu dien", ""};

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_mat_hang_thong_tin, container, false);

        tv = view.findViewById(R.id.tv);
        tvBack = view.findViewById(R.id.tvBack);
        lvThongTin = view.findViewById(R.id.lvThongTin);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri,false);
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri,false);

            }
        });
        themThongTin(DangMatHangActivity.viTri);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void themThongTin(int a) {
        switch (a) {

            case 0:
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri);

                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, DanhMuc);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.DanhMuc = lvThongTin.getItemAtPosition(position).toString();
                        themDanhMucCon(DangMatHangActivity.DanhMuc);
                    }
                });
                break;
            case 2:
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri);

                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ThanhPho);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.ThanhPho=lvThongTin.getItemAtPosition(position).toString();
                        Toast.makeText(getActivity(), DangMatHangActivity.ThanhPho, Toast.LENGTH_SHORT).show();
                        themQuan(DangMatHangActivity.ThanhPho);
                    }
                });
                break;
        }

    }

    public void themDanhMucCon(String a) {

        switch (a) {
            case "bat dong san":
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themThongTin(0);
                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, DanhMucBatDongSan);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri);
                        DangMatHangActivity.DanhMucCon = lvThongTin.getItemAtPosition(position).toString();
                    }
                });
                break;
            case "xe co":
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themThongTin(0);
                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, DanhMucXeCo);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri);
                        DangMatHangActivity.DanhMucCon = lvThongTin.getItemAtPosition(position).toString();

                    }
                });
                break;
            case "do dien tu":
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themThongTin(0);
                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, DanhMucDoDienTu);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri);
                        DangMatHangActivity.DanhMucCon = lvThongTin.getItemAtPosition(position).toString();

                    }
                });
                break;
            case "thu cung":
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themThongTin(0);
                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, DanhMucThuCung);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri);
                        DangMatHangActivity.DanhMucCon = lvThongTin.getItemAtPosition(position).toString();

                    }
                });
                break;
            case "Tu lanh, may lanh, may giat":
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themThongTin(0);
                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, DanhMucTuLanh);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri);
                        DangMatHangActivity.DanhMucCon = lvThongTin.getItemAtPosition(position).toString();

                    }
                });
                break;
        }

    }

    public void themQuan(String a){
        switch (a){
            case "Đà Nẵng":
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themThongTin(2);
                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, QuanDaNang);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.QuanHuyen=lvThongTin.getItemAtPosition(position).toString();
                        themPhuong(DangMatHangActivity.QuanHuyen);
                    }
                });

        }
    }
    public void themPhuong(String a){
        switch (a){
            case "Cẩm Lệ":
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themQuan(DangMatHangActivity.QuanHuyen);
                    }
                });
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, PhuongCamLe);
                lvThongTin.setAdapter(adapter);
                lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DangMatHangActivity.PhuongXa=lvThongTin.getItemAtPosition(position).toString();
                        DangMatHangActivity.vp2DangMatHang.setCurrentItem(DangMatHangActivity.viTri);

                    }
                });
        }
    }
}
