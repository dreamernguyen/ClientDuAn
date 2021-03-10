package com.dreamernguyen.ClientDuAn;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.cloudinary.android.MediaManager;
import com.dreamernguyen.ClientDuAn.Activity.NhanTinActivity;
import com.dreamernguyen.ClientDuAn.Models.TinNhan;
import com.google.gson.Gson;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainApplication extends Application  {
    private String serverURL = "https://server-du-an.herokuapp.com/";
    private Socket mSocket;
    private String TAG = "Main Application";
    public static final String CHANNEL_ID = "CHANNEL_1";

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

        mSocket.connect();


        test();
    }



    private void test(){
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
                            NhanTinActivity.loadTinNhan();
                            Notification notification = new NotificationCompat.Builder(getApplicationContext(),MainApplication.CHANNEL_ID)
                                    .setSmallIcon(R.drawable.logo_main)
                                    .setContentTitle("Có tin nhắn mới từ "+tinNhan.getIdNguoiGui().getHoTen())
                                    .setColor(getResources().getColor(R.color.BlueViolet))
                                    .setContentText("Server thông báo qua socket : ")
                                    .setStyle(new NotificationCompat.BigTextStyle()
                                            .bigText(tinNhan.getNoiDung()))
                                    .build();
                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            notificationManager.notify(001, notification);
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
            CharSequence name = "Thông báo từ server";
            String description = "Thông báo tin nhắn mới";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }

        }
    }

}
