package com.dreamernguyen.ClientDuAn;

import android.Manifest;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.cloudinary.android.MediaManager;
import com.dreamernguyen.ClientDuAn.Activity.NhanTinActivity;
import com.dreamernguyen.ClientDuAn.Models.TinNhan;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainApplication extends Application  {
    private String serverURL = "https://server-du-an.herokuapp.com/";
    private Socket mSocket;
    private String TAG = "Main Application";
    public static final String CHANNEL_ID = "Tin nhắn";
    public static final String CHANNEL_ID2 = "Thông báo";

    {
        try {
            mSocket = IO.socket(serverURL);
        }catch (URISyntaxException e){
            throw new RuntimeException(e);

        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        MediaManager.init(this);
        LocalDataManager.init(getApplicationContext());
        createNotificationChannel();
        capQuyen();
//        mSocket.connect();

//        thongBaoTinNhan();

        Intent intent = new Intent(this, MyService.class);
        startService(intent);


    }


    private void capQuyen(){
        TedPermission.with(this).setPermissionListener(new PermissionListener() {
            @Override
            public void onPermissionGranted() {
//                Toast.makeText(MainApplication.this, "Ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainApplication.this, "Not Ok", Toast.LENGTH_SHORT).show();
            }
        }).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
                .setDeniedTitle("Cài đặt cho phép truy cập").setDeniedMessage("Vui lòng bật quyền truy cập tại [Cài đặt] > [Quyền]").check();
    }

    private void thongBaoTinNhan(){
        // Create an Intent for the activity you want to start

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "onCreate: "+mSocket.connected()+"\nid"+mSocket.id());
                if(mSocket.connected()){
                    Log.d("test", "onCreate: kết nối");
                }else {
                    Log.d("test", "onCreate: Chưa kết nối");
                }
                mSocket.emit("testab","abc");
                mSocket.on("tinNhan", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Gson gson = new Gson();
                            TinNhan tinNhan = gson.fromJson(args[0].toString(), TinNhan.class);
                            Log.d(TAG, "model: "+tinNhan.getNoiDung());

                        Log.d(TAG, "call: "+args[0]);
                        if(tinNhan.getIdNguoiNhan().getId().equals("600688a68df31f0984c97de7")){
                            Intent resultIntent = new Intent(getApplicationContext(), NhanTinActivity.class);
                            resultIntent.putExtra("chucNang","NhanTin");
                            resultIntent.putExtra("idNguoiDung", tinNhan.getIdNguoiNhan().getId());
                            resultIntent.putExtra("tenNguoiDung", tinNhan.getIdNguoiGui().getHoTen());
// Create the TaskStackBuilder and add the intent, which inflates the back stack
                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                            stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                            Notification notification = new NotificationCompat.Builder(getApplicationContext(),MainApplication.CHANNEL_ID)
                                    .setSmallIcon(R.drawable.logo_main)
                                    .setContentTitle("Có tin nhắn mới từ "+tinNhan.getIdNguoiGui().getHoTen())
                                    .setColor(getResources().getColor(R.color.BlueViolet))
                                    .setContentText("Server thông báo qua socket : ")
                                    .setContentIntent(resultPendingIntent)
                                    .setStyle(new NotificationCompat.BigTextStyle()
                                            .bigText(tinNhan.getNoiDung()))
                                    .build();
                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                            int id = (int) new Date().getTime();
                            notificationManager.notify(1, notification);
                        }


                    }
                });
            }
        });
    }
        private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Channel 1
            CharSequence name = "Thông báo từ server";
            String description = "Thông báo tin nhắn mới";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            //Channel 2
            CharSequence name2 = "Thông báo từ server 2";
            String description2 = "Thông báo các hoạt động";
            int importance2 = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID2, name2, importance2);
            channel2.setDescription(description2);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel2);
            }

        }
    }

}
