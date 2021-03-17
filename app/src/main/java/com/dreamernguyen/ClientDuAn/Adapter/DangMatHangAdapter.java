package com.dreamernguyen.ClientDuAn.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dreamernguyen.ClientDuAn.Fragment.DangMatHangDanhMucFragment;
import com.dreamernguyen.ClientDuAn.Fragment.DangMatHangDiaChiFragment;
import com.dreamernguyen.ClientDuAn.Fragment.DangMatHangFragment;
import com.dreamernguyen.ClientDuAn.Fragment.DangMatHangHinhAnhFragment;
import com.dreamernguyen.ClientDuAn.Fragment.DangMatHangKhacFragment;
import com.dreamernguyen.ClientDuAn.Fragment.DangMatHangThongTinFragment;

public class DangMatHangAdapter extends FragmentStateAdapter {
    public DangMatHangAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DangMatHangDanhMucFragment();
            case 1:
                return new DangMatHangHinhAnhFragment();
            case 2:
                return new DangMatHangDiaChiFragment();
            case 3:
                return new DangMatHangKhacFragment();
            case 4:
                return new DangMatHangFragment();
            case 5:
                return new DangMatHangThongTinFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
