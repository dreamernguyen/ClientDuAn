package com.dreamernguyen.ClientDuAn.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dreamernguyen.ClientDuAn.Fragment.KhamPhaFragment;
import com.dreamernguyen.ClientDuAn.Fragment.TheoDoiFragment;

public class TabLayoutAdapter extends FragmentStatePagerAdapter {

    public TabLayoutAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TheoDoiFragment();
            case 1:
                return new KhamPhaFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Theo Dõi";
            case 1:
                return "Khám Phá";
            default:
                return "Theo Dõi";
        }
    }
}
