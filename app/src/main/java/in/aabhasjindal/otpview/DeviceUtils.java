package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import java.io.File;

public class DeviceUtils {
    public static boolean isDeviceRooted(Context context) {
        // Check for most common root binaries
        String[] paths = {"/system/bin/su",
                "/system/xbin/su",
                "/sbin/su",
                "/system/xbin/which su",
                "/system/bin/which su",
                "which su"};
        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }

        File file = new File("/system/app/Superuser.apk");
        if (file.exists()) {
            return true;
        }

        // Check for system app that indicates the device is rooted
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo("com.noshufou.android.su", PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            // Do nothing
        }

        return false;
    }


    /**
     * Check Developer option
     * @return
     */
    public static int checkDeveloperOption(Context context){
        return Settings.Secure.getInt(context.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED , 0);
        // if developer options are enabled the value will be 1, otherwise it will be 0
    }

}