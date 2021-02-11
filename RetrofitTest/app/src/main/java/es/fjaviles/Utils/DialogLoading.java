package es.fjaviles.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import es.fjaviles.R;

public class DialogLoading {

    private Activity activity;
    private AlertDialog dialog;

    public DialogLoading(Activity activity){
        this.activity = activity;
    }

    public void startLoadingDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_loading, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();

    }


    public void stopLoadingDialog(){ dialog.dismiss(); }

}
