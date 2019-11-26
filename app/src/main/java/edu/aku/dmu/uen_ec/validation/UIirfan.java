package edu.aku.dmu.uen_ec.validation;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * created by irfan syed 11/11/2019

 */

public abstract class UIirfan {

    public  static SharedPreferences prefs;


    public static void findViews(View v,Context mcn) {

        prefs=mcn.getSharedPreferences("desing", MODE_PRIVATE);

        int Qlabel = prefs.getInt("qtxt", 18); //0 is the default value.

        int rblabel = prefs.getInt("rbtxt", 18); //0 is the default value.

        int cblable = prefs.getInt("cbtxt", 18); //0 is the default value.
        int edlable = prefs.getInt("edtxt", 18); //0 is the default value.
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    // recursively call this method
                    findViews(child,mcn);
                }
            }

            else if(v instanceof RadioButton)
            {
                RadioButton rb = (RadioButton) v;
                rb.setTextSize(rblabel);
            }

            else if(v instanceof CheckBox)
            {
                CheckBox cb = (CheckBox) v;
                cb.setTextSize(cblable);
            }

            else if(v instanceof EditText)
            {
                EditText ed = (EditText) v;
                ed.setTextSize(edlable);
            }
                else if (v instanceof TextView)
            {
                //do whatever you want ...

                TextView tx = (TextView) v;
                tx.setTextSize(Qlabel);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}
