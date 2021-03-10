package com.dreamernguyen.ClientDuAn;

import android.content.Context;

public class LocalDataManager {
    private static final String PREF_FIRST_INSTALL ="PREF_FIRST_INSTALL" ;
    private static LocalDataManager instance;
    private ConfigSharedPreferences configPref;
    public static void init(Context context){
        instance = new LocalDataManager();
        instance.configPref= new ConfigSharedPreferences(context);

    }
    public static LocalDataManager getInstance(){
        if (instance==null){
            instance= new LocalDataManager();

        }
        return instance;
    }

    public static void setFirstInstalled(boolean isFirst){
        LocalDataManager.getInstance().configPref.putBooleanValue(PREF_FIRST_INSTALL,isFirst);
    }
    public static boolean getFirstInstalled(){
        return LocalDataManager.getInstance().configPref.getBooleanValue(PREF_FIRST_INSTALL);
    }
    public static void setDanhMuc(String value){
        LocalDataManager.getInstance().configPref.putStringValue("DanhMuc",value);
    }
    public static String getDanhMuc(){
        return LocalDataManager.getInstance().configPref.getStringValue("DanhMuc");
    }
    public static void setIdNguoiDung(String value){
        LocalDataManager.getInstance().configPref.putStringValue("idNguoiDung",value);
    }
    public static String getIdNguoiDung(){
        return LocalDataManager.getInstance().configPref.getStringValue("idNguoiDung");
    }
}
