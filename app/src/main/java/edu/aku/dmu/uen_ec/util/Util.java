package edu.aku.dmu.uen_ec.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.aku.dmu.uen_ec.R;
import edu.aku.dmu.uen_ec.core.MainApp;

import static android.content.Context.MODE_PRIVATE;

public class Util {

    public static boolean isPasswordValid(String password) {
        return password.length() >= 7;
    }

    public static void showTagDialog(Context context) {

        SharedPreferences sharedPref;
        final SharedPreferences.Editor editor;

        sharedPref = context.getSharedPreferences("tagName", MODE_PRIVATE);
        editor = sharedPref.edit();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        View view = LayoutInflater.from(context).inflate(R.layout.tagid_dialog, null);
        final EditText tagID = view.findViewById(R.id.tagIDet);
        Button submitBtn = view.findViewById(R.id.submitTag);
        Button cancel = view.findViewById(R.id.cancel);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(tagID.getText().toString())) {
                    editor.putString("tagName", tagID.getText().toString());
                    editor.apply();
                    dialog.dismiss();
                } else {
                    tagID.setError("This field is required");
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void setGPS(Context context) {
        SharedPreferences GPSPref = context.getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);

//        String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String dt = GPSPref.getString("Time", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(context, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.fc.setGpsLat(GPSPref.getString("Latitude", "0"));
            MainApp.fc.setGpsLng(GPSPref.getString("Longitude", "0"));
            MainApp.fc.setGpsAcc(GPSPref.getString("Accuracy", "0"));
//            AppMain.fc.setGpsTime(GPSPref.getString(date, "0")); // Timestamp is converted to date above
            MainApp.fc.setGpsTime(date); // Timestamp is converted to date above

            // Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

        }

    }


}
