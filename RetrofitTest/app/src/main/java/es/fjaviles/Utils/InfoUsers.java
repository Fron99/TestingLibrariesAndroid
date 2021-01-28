package es.fjaviles.Utils;

import android.app.Activity;
import android.content.Context;

import androidx.core.content.res.ResourcesCompat;

import es.fjaviles.R;
import www.sanju.motiontoast.MotionToast;

public class InfoUsers {

    public static void showMessage(TypeMessage typeMessage, Activity activity, Context context){

        String title = "", message = "";

        switch (typeMessage){

            case TOAST_SUCCESS: title = "Successful"; message = "It has been realized correctly";break;
            case TOAST_WARNING: title = "Warning"; message = "Be careful";break;
            case TOAST_DELETE: title = "Deleted!"; message = "Deleted Completed successfully!";break;
            case TOAST_INFO: title = "Saved"; message = "The person has been saved";break;
            case TOAST_ERROR: title = "Error"; message = "It has been realized correctly";break;

        }

        MotionToast.Companion.darkColorToast(activity,title,message,
                String.valueOf(typeMessage),
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(context, R.font.helvetica_regular));

    }


}
