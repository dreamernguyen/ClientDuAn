package com.dreamernguyen.ClientDuAn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamernguyen.ClientDuAn.Models.BaiViet;
import com.dreamernguyen.ClientDuAn.Models.BinhLuan;
import com.dreamernguyen.ClientDuAn.Models.NguoiDung;
import com.dreamernguyen.ClientDuAn.Models.ResponseData;
import com.dreamernguyen.ClientDuAn.Retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ApiInterface apiInterface;
    CustomCall customCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> linkAnh = null;
        textView = findViewById(R.id.tv);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Log.d("TAG", "onResponse: ");


        customCall = new CustomCall();
//     




    }

    public void test(View view) {
        Toast.makeText(this, "this", Toast.LENGTH_SHORT).show();
        customCall.danhSachBaiVietAn("600688a68df31f0984c97de7");

    }

}