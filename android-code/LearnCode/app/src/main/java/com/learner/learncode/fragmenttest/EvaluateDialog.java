package com.learner.learncode.fragmenttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by liting on 2016/1/8.
 */
public class EvaluateDialog extends DialogFragment {

    public static  final String[] items = {"不错","一般","极好"};
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("评价")
//                .setMessage("不错就给个赏吧")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(which);
                    }
                })
                .create();
        return dialog;
    }

    private void setResult(int which) {
        if(getTargetFragment() !=null){
            Intent intent = new Intent();
            intent.putExtra("result",items[which]);
            getTargetFragment().onActivityResult(Fragment1.RESULT_EVALUTE, Activity.RESULT_OK,intent);
        }
    }
}
