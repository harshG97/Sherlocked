package com.example.harsh.sherlocked.extra;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.harsh.sherlocked.R;

import java.util.HashMap;

/**
 * Created by Harsh on 01-03-2017.
 */

public class dialog {
    public void showDialog(Context activity, HashMap<String,String> msg, String ssn){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);

        TextView name = (TextView) dialog.findViewById(R.id.dia_episode);
        name.setText(msg.get("name"));

        TextView season = (TextView) dialog.findViewById(R.id.diaSsn);
        season.setText(ssn);

        TextView date = (TextView) dialog.findViewById(R.id.diaDate);
        date.setText(msg.get("date"));

        TextView rate = (TextView) dialog.findViewById(R.id.diaRating);
        rate.setText(msg.get("rating"));

        TextView plot = (TextView) dialog.findViewById(R.id.diaPlot);
        plot.setText(msg.get("plot"));

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
