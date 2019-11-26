package edu.aku.dmu.uen_ec.ui;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;

import edu.aku.dmu.uen_ec.R;
import edu.aku.dmu.uen_ec.databinding.ActivityABinding;
import edu.aku.dmu.uen_ec.databinding.ActivityUiSettingBinding;

public class DesingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ActivityUiSettingBinding bi;

    String MY_PREFS_NAME="desing";
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bi = DataBindingUtil.setContentView(this, R.layout.activity_ui_setting);
        bi.setCallback(this);

        events();

        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();


    }

    public  void btnclic()
    {
        this.finish();

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();
    }

    public  void events()
    {

        bi.rbSmall.setOnCheckedChangeListener(this);
        bi.rbNormal.setOnCheckedChangeListener(this);
        bi.rbLarage.setOnCheckedChangeListener(this);
        bi.rbXlarage.setOnCheckedChangeListener(this);



        bi.rbRbSmall.setOnCheckedChangeListener(this);
        bi.rbRbNormal.setOnCheckedChangeListener(this);
        bi.rbRbLarage.setOnCheckedChangeListener(this);
        bi.rbRbXlarage.setOnCheckedChangeListener(this);



        bi.rbCbSmall.setOnCheckedChangeListener(this);
        bi.rbCbNormal.setOnCheckedChangeListener(this);
        bi.rbCbLarage.setOnCheckedChangeListener(this);
        bi.rbCbXlarage.setOnCheckedChangeListener(this);

        bi.rbEdSmall.setOnCheckedChangeListener(this);
        bi.rbEdNormal.setOnCheckedChangeListener(this);
        bi.rbEdLarage.setOnCheckedChangeListener(this);
        bi.rbEdXlarage.setOnCheckedChangeListener(this);

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        // label quesitons
        if(buttonView==bi.rbSmall && bi.rbSmall.isChecked())
        {

            bi.labqexp.setTextSize(16);
            bi.labqexp.setText("Question Lable Size is Small");

            editor.putInt("qtxt", 16);
        }
        else if(buttonView==bi.rbLarage && bi.rbLarage.isChecked())
        {

            bi.labqexp.setTextSize(24);

            bi.labqexp.setText("Question Lable Size is Large");
            editor.putInt("qtxt", 24);
        }
        else if(buttonView==bi.rbNormal && bi.rbNormal.isChecked())
        {

            bi.labqexp.setTextSize(20);

            editor.putInt("qtxt", 20);



            bi.labqexp.setText("Question Lable Size is Normal");
        }

        else if(buttonView==bi.rbXlarage && bi.rbXlarage.isChecked())
        {


            bi.labqexp.setTextSize(26);

            editor.putInt("qtxt", 26);



            bi.labqexp.setText("Question Lable Size is Extra Large");
        }



        // txt label ends

        if(buttonView==bi.rbRbSmall && bi.rbRbSmall.isChecked())
        {

            bi.rbexp.setTextSize(16);
            bi.rbexp.setText("Question Lable Size is Small");

            editor.putInt("rbtxt", 16);
        }
        else if(buttonView==bi.rbRbLarage && bi.rbRbLarage.isChecked())
        {

            bi.rbexp.setTextSize(24);

            bi.rbexp.setText("Question Lable Size is Large");
            editor.putInt("rbtxt", 24);
        }
        else if(buttonView==bi.rbRbNormal && bi.rbRbNormal.isChecked())
        {

            bi.rbexp.setTextSize(18);

            editor.putInt("rbtxt", 18);



            bi.rbexp.setText("Question Lable Size is Normal");
        }

        else if(buttonView==bi.rbRbXlarage && bi.rbRbXlarage.isChecked())
        {


            bi.rbexp.setTextSize(26);

            editor.putInt("rbtxt", 26);



            bi.rbexp.setText("Question Lable Size is Extra Large");
        }





        if(buttonView==bi.rbCbSmall && bi.rbCbSmall.isChecked())
        {

            bi.cbexp.setTextSize(16);
            bi.cbexp.setText("Checkbox Lable Size is Small");

            editor.putInt("cbtxt", 16);
        }
        else if(buttonView==bi.rbCbLarage && bi.rbCbLarage.isChecked())
        {

            bi.cbexp.setTextSize(24);

            bi.cbexp.setText("Checkbox Lable Size is Large");
            editor.putInt("cbtxt", 24);
        }
        else if(buttonView==bi.rbCbNormal && bi.rbCbNormal.isChecked())
        {

            bi.cbexp.setTextSize(20);

            editor.putInt("cbtxt", 20);



            bi.cbexp.setText("Checkbox Lable Size is Normal");
        }

        else if(buttonView==bi.rbCbXlarage && bi.rbCbXlarage.isChecked())
        {


            bi.cbexp.setTextSize(26);

            editor.putInt("cbtxt", 26);



            bi.cbexp.setText("Checkbox Lable Size is Extra Large");
        }


        else if(buttonView==bi.rbEdSmall && bi.rbEdSmall.isChecked())
        {


            bi.edexp.setTextSize(16);

            editor.putInt("edtxt", 16);



            bi.edexp.setText("Editext Size Small");
        }

        else if(buttonView==bi.rbEdNormal && bi.rbEdNormal.isChecked())
        {


            bi.edexp.setTextSize(20);

            editor.putInt("edtxt", 20);



            bi.edexp.setText("Editext Size Normal");
        }

        else if(buttonView==bi.rbEdLarage && bi.rbEdLarage.isChecked())
        {


            bi.edexp.setTextSize(24);

            editor.putInt("edtxt", 24);



            bi.edexp.setText("Editext Size large");
        }


        else if(buttonView==bi.rbEdXlarage && bi.rbEdXlarage.isChecked())
        {


            bi.edexp.setTextSize(26);

            editor.putInt("edtxt", 26);



            bi.edexp.setText("Editext Size extra large");
        }
// txt label ends






        editor.apply();

    }
}
