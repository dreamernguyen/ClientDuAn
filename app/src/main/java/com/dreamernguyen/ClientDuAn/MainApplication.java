package com.dreamernguyen.ClientDuAn;

import android.app.Application;

import com.cloudinary.android.MediaManager;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MediaManager.init(this);
    }

}
