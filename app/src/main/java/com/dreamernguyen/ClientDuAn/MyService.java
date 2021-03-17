package com.dreamernguyen.ClientDuAn;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.dreamernguyen.ClientDuAn.Activity.NhanTinActivity;
import com.dreamernguyen.ClientDuAn.Models.TinNhan;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.Date;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MyService extends Service {
    private String serverURL = "http://192.168.31.47:5000/";
    private Socket mSocket;

    {
        try {
            mSocket = IO.socket(serverURL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);

        }
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        // The service is being created

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSocket.connect();
        thongBaoTinNhan();
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), MainApplication.CHANNEL_ID2)
                .setSmallIcon(R.drawable.logo_main)
                .setContentTitle("App Rao Vặt đang chạy ngầm ")
                .setColor(getResources().getColor(R.color.BlueViolet))
                .setContentText("Đang nhận thông báo ")
                .build();
        startForeground(123, notification);
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void thongBaoTinNhan() {
        // Create an Intent for the activity you want to start

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("Service", "onCreate: " + mSocket.connected() + "\nid" + mSocket.id());
                if (mSocket.connected()) {
                    Log.d("test", "onCreate: kết nối");
                } else {
                    Log.d("test", "onCreate: Chưa kết nối");
                }
                mSocket.emit("testab", "MayThat");
                mSocket.on("tinNhan", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Gson gson = new Gson();
                        TinNhan tinNhan = gson.fromJson(args[0].toString(), TinNhan.class);
                        Log.d("Service", "model: " + tinNhan.getNoiDung());

                        Log.d("Service", "call: " + args[0]);
                        if (tinNhan.getIdNguoiNhan().getId().equals("600688a68df31f0984c97de7")) {
                            Intent resultIntent = new Intent(getApplicationContext(), NhanTinActivity.class);
                            resultIntent.putExtra("chucNang", "NhanTin");
                            resultIntent.putExtra("idNguoiDung", tinNhan.getIdNguoiNhan().getId());
                            resultIntent.putExtra("tenNguoiDung", tinNhan.getIdNguoiGui().getHoTen());

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                            stackBuilder.addNextIntentWithParentStack(resultIntent);
                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                            Notification notification = new NotificationCompat.Builder(getApplicationContext(), MainApplication.CHANNEL_ID)
                                    .setSmallIcon(R.drawable.logo_main)
                                    .setContentTitle("Có tin nhắn mới từ " + tinNhan.getIdNguoiGui().getHoTen())
                                    .setColor(getResources().getColor(R.color.BlueViolet))
                                    .setContentText("Server thông báo qua socket : ")
                                    .setContentIntent(resultPendingIntent)
                                    .setGroup(tinNhan.getIdNguoiNhan().getId())
                                    .setStyle(new NotificationCompat.BigTextStyle()
                                            .bigText(tinNhan.getNoiDung()))
                                    .build();
                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            int id = (int) new Date().getTime();
                            notificationManager.notify(id, notification);
                        }


                    }
                });
            }
        });
    }
}