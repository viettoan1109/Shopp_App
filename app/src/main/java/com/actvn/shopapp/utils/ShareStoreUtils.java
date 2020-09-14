package com.actvn.shopapp.utils;

import android.content.Context;

public class ShareStoreUtils {
    private static final String FILE_NAME = "SHARE"; // taoj file

    // luu token vo file
    public static void saveToken(Context context, String token) {
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit()
                .putString(ConstApp.TOKEN, token)
                .apply();
    }

    // lay token
    public static String getToken(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
                .getString(ConstApp.TOKEN, null);
    }
}
