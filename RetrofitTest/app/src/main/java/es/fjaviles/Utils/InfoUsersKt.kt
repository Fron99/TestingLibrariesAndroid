package es.fjaviles.Utils

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import es.fjaviles.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToast.Companion.darkColorToast

class InfoUsersKt {
    companion object {

        val TOAST_SUCCESS : String = "TOAST_SUCCESS";
        val TOAST_ERROR : String = "TOAST_ERROR";
        val TOAST_WARNING : String = "TOAST_WARNING";
        val TOAST_INFO : String = "TOAST_INFO";
        val TOAST_DELETE : String = "TOAST_DELETE";


        fun showMessageDarkColorToast(typeMessage: String, activity: Activity?, context: Context?) {
            var title = ""
            var message = ""
            var typeMessageFinish : String = typeMessage
            when (typeMessageFinish) {
                TOAST_SUCCESS -> {
                    title = "Successful"
                    message = "It has been realized correctly"
                }

                TOAST_DELETE -> {
                    title = "Warning"
                    message = "Be careful"
                }

                TOAST_WARNING -> {
                    title = "Deleted!"
                    message = "Deleted Completed successfully!"
                }

                TOAST_INFO -> {
                    title = "Saved"
                    message = "The person has been saved"
                }

                TOAST_ERROR -> {
                    title = "Error"
                    message = "It has been realized correctly"
                }
                else -> {
                    typeMessageFinish = TOAST_ERROR
                }

            }

            if (title == "" && typeMessageFinish == TOAST_ERROR )
            {
                title = "Error"
                message = "This typeMessage not found"

            }

            darkColorToast(activity!!,
                    title, message,
                    typeMessageFinish,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(context!!, R.font.helvetica_regular))

        }

    }
    
}