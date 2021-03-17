package com.dreamernguyen.ClientDuAn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dreamernguyen.ClientDuAn.Adapter.AnhBaiVietAdapter;

public class AnhChiTietActivity extends AppCompatActivity {
    ViewPager vp;
    AnhBaiVietAdapter anhBaiVietAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anh_chi_tiet);
        vp = findViewById(R.id.vpgImage);
        Intent i = getIntent();
        anhBaiVietAdapter = new AnhBaiVietAdapter(i.getStringArrayListExtra("listAnh"),true);
        Log.d("pppp", "onCreate: "+i.getIntExtra("pos",0));

        vp.setAdapter(anhBaiVietAdapter);
        vp.setCurrentItem(i.getIntExtra("pos",0));
    }
}