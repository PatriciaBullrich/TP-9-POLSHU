package ar.marto.tp9_polshu.utiles;
import android.util.Log;

import java.util.Arrays;

public final class CustomLog {
    public static final String CUSTOM_TAG ="Custom Tag";
    public static void log(String msj){
        Log.d(CUSTOM_TAG, msj);
    }
    public static void log(int num){
        Log.d(CUSTOM_TAG, String.valueOf(num));
    }
    public static void log(boolean msj){
        Log.d(CUSTOM_TAG, String.valueOf(msj));
    }
    public static void log(float msj){
        Log.d(CUSTOM_TAG, String.valueOf(msj));
    }
    public static void log(int[] nums){ Log.d(CUSTOM_TAG, Arrays.toString(nums)); }
    public static void logException(Exception e){
        log("errors: "+ e.getMessage());
    }
    public static void logObject(Object o){
        log("object recieved:" + o.toString());
    }
}