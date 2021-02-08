package es.fjaviles.Utils;

import android.app.Activity;
import android.content.Context;

import androidx.core.content.res.ResourcesCompat;

import es.fjaviles.R;
import www.sanju.motiontoast.MotionToast;

public class InfoUsers {

    public static final String TOAST_SUCCESS = "SUCCESS";
    public static final String  TOAST_ERROR = "FAILED";
    public static final String  TOAST_WARNING = "WARNING";
    public static final String  TOAST_INFO = "INFO";
    public static final String  TOAST_DELETE = "DELETE";
    public static final String  TOAST_NO_INTERNET = "NO INTERNET";

    public static void showMessageDarkColorToast(Activity activity, Context context, String typeMessage, String title, String message){

        String typeMessageFinish = "";

        switch (typeMessage){

            case TOAST_SUCCESS:
            case TOAST_WARNING:
            case TOAST_DELETE:
            case TOAST_INFO:
            case TOAST_ERROR:
            case TOAST_NO_INTERNET:
                break;

            default: typeMessageFinish = TOAST_ERROR; break;

        }


        if (typeMessageFinish.equals(TOAST_ERROR) )
        {
            title = "Error";
            message = "This typeMessage not found";

        }

        MotionToast.Companion.darkColorToast(activity,
                title, message,
                typeMessageFinish,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(context, R.font.helvetica_regular));

    }

    public static void showMessageDarkToast(Activity activity, Context context, String typeMessage, String title, String message){

        String typeMessageFinish = "";

        switch (typeMessage){

            case TOAST_SUCCESS:
            case TOAST_WARNING:
            case TOAST_DELETE:
            case TOAST_INFO:
            case TOAST_ERROR:
            case TOAST_NO_INTERNET:
                break;

            default: typeMessageFinish = TOAST_ERROR; break;

        }


        if (typeMessageFinish.equals(TOAST_ERROR) )
        {
            title = "Error";
            message = "This typeMessage not found";

        }

        MotionToast.Companion.darkToast(activity,
                title, message,
                typeMessageFinish,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(context, R.font.helvetica_regular));

    }

    public static void showMessageCreateToast(Activity activity, Context context, String typeMessage, String title, String message){

        String typeMessageFinish = "";

        switch (typeMessage){

            case TOAST_SUCCESS:
            case TOAST_WARNING:
            case TOAST_DELETE:
            case TOAST_INFO:
            case TOAST_ERROR:
            case TOAST_NO_INTERNET:
                break;

            default: typeMessageFinish = TOAST_ERROR; break;

        }


        if (typeMessageFinish.equals(TOAST_ERROR) )
        {
            title = "Error";
            message = "This typeMessage not found";

        }

        MotionToast.Companion.createToast(activity,
                title, message,
                typeMessageFinish,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(context, R.font.helvetica_regular));

    }

    public static void showMessageCreateColorToast(Activity activity, Context context, String typeMessage, String title, String message){

        String typeMessageFinish = "";

        switch (typeMessage){

            case TOAST_SUCCESS:
            case TOAST_WARNING:
            case TOAST_DELETE:
            case TOAST_INFO:
            case TOAST_ERROR:
            case TOAST_NO_INTERNET:
                break;

            default: typeMessageFinish = TOAST_ERROR; break;

        }


        if (typeMessageFinish.equals(TOAST_ERROR) )
        {
            title = "Error";
            message = "This typeMessage not found";

        }

        MotionToast.Companion.createColorToast(activity,
                title, message,
                typeMessageFinish,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(context, R.font.helvetica_regular));

    }


}