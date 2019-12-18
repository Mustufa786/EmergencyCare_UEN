package edu.aku.dmu.uen_ec.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.aku.dmu.uen_ec.R;
import edu.aku.dmu.uen_ec.contracts.FormsContract;
import edu.aku.dmu.uen_ec.core.DatabaseHelper;
import edu.aku.dmu.uen_ec.core.MainApp;
import edu.aku.dmu.uen_ec.databinding.ActivityABinding;
import edu.aku.dmu.uen_ec.other.DiseaseCode;
import edu.aku.dmu.uen_ec.util.Util;
import edu.aku.dmu.uen_ec.validation.UIirfan;
import edu.aku.dmu.uen_ec.validation.ValidatorClass;

import static edu.aku.dmu.uen_ec.core.MainApp.fc;

public class CRFAActivity extends AppCompatActivity {

    ActivityABinding bi;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bi = DataBindingUtil.setContentView(this, R.layout.activity_a);
        bi.setCallback(this);

//        setTitle(R.string.f9aHeading);
        List<String> Dieascodelist = new ArrayList<>(DiseaseCode.HmDiseaseCode.keySet());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, Dieascodelist);
        bi.cra11.setThreshold(1); //will start working from first character
        bi.cra11.setAdapter(adapter);

        bi.cra11b.setThreshold(1); //will start working from first character
        bi.cra11b.setAdapter(adapter);


        setupViews();

        setDesing();


        db = new DatabaseHelper(this);
        int study_id = Integer.parseInt(db.getsFormcount());
        study_id = study_id + 1;
        if (study_id < 10) {
            bi.cra01.setText("0" + study_id + "");
        } else {
            bi.cra01.setText("00" + study_id + "");
        }
        bi.cra01.setEnabled(false);

    }

    private void setupViews() {

    }

    public void BtnContinue() {
        if (formValidation()) {





            /*
            if(bi.cra08c.getText().equals("14"))
            {
                if(!bi.cra08a.getText().equals("0") || !bi.cra08b.getText().equals("0"))
                {
                    bi.cra08c.setError("Age can't be more then 14 years");
                    bi.cra08c.requestFocus();
                    return;
                }

            }
*/


            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date strDate = null;
            try {
                strDate = sdf.parse(bi.cra03a.getText() + "/" + bi.cra03b.getText() + "/" + bi.cra03c.getText());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() < strDate.getTime()) {

                bi.cra03a.setError("Can not be greater then current date");
                bi.cra03a.requestFocus();
                return;
            }


            try {
                SaveDraft();
                if (UpdateDB()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), EndingActivity.class).putExtra("complete", true));
                } else {
                    Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        // 2. insert form
        Long rowId;
        rowId = db.addForm(fc);
        if (rowId > 0) {
            fc.set_ID(String.valueOf(rowId));
            fc.setUID((fc.getDeviceID() + fc.get_ID()));
            db.updateFormID(fc);
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;

        }
    }

    private void SaveDraft() throws JSONException {

        fc = new FormsContract();
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        fc.setTagID(sharedPref.getString("tagName", null));
        fc.setFormDate((DateFormat.format("dd-MM-yyyy HH:mm", new Date())).toString());
        fc.setDeviceID(MainApp.deviceId);
        fc.setUser(MainApp.userName);

        fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);

        JSONObject f1 = new JSONObject();
        Util.setGPS(this);
        JSONObject CRFA = new JSONObject();

        CRFA.put("cra01", bi.cra01.getText().toString());

        CRFA.put("cra02", bi.cra02.getText().toString());

        CRFA.put("cra03a", bi.cra03a.getText().toString());
        CRFA.put("cra03b", bi.cra03b.getText().toString());
        CRFA.put("cra03c", bi.cra03c.getText().toString());

        CRFA.put("cra04", bi.cra04.getText().toString());

        CRFA.put("cra05", bi.cra05.getText().toString());

        CRFA.put("cra06a", bi.cra06a.getText().toString());
        CRFA.put("cra06b", bi.cra06b.getText().toString());
        CRFA.put("cra06c", bi.cra06c.getText().toString());
        CRFA.put("cra06d", bi.cra06d.getText().toString());
        CRFA.put("cra06e", bi.cra06d.getText().toString());

        CRFA.put("cra07", bi.cra07.getText().toString());


        CRFA.put("cra08b", "0");
        CRFA.put("cra08c", "0");



        CRFA.put("cra08a",
                bi.cra08a.isChecked() ? "1"
                        : bi.cra08b.isChecked() ? "2"
                        : bi.cra08c.isChecked() ? "3"
                        : bi.cra08d.isChecked() ? "4"
                        : bi.cra08e.isChecked() ? "5"
                        : "0");

        CRFA.put("cra09",
                bi.cra09a.isChecked() ? "1"
                        : bi.cra09b.isChecked() ? "2"
                        : "0");

        CRFA.put("cra10", bi.cra10.getText().toString());

        CRFA.put("cra11", DiseaseCode.HmDiseaseCode.get(bi.cra11.getText().toString()));

        CRFA.put("cra11b", DiseaseCode.HmDiseaseCode.get(bi.cra11b.getText().toString()));


        CRFA.put("cra12",
                bi.cra12a.isChecked() ? "1"
                        : bi.cra12b.isChecked() ? "2"
                        : bi.cra12c.isChecked() ? "3"
                        : bi.cra12d.isChecked() ? "4"
                        : bi.cra12e.isChecked() ? "5"
                        : "0");
        fc.setCRFA(String.valueOf(CRFA));
        fc.setFormType("CRFA");
        fc.setstudyid(bi.cra01.getText().toString());

        fc.setcrfcstatus("0");


    }

    private boolean formValidation() {

        return ValidatorClass.EmptyCheckingContainer(this, bi.GrpCRFA);


    }

    private void setDesing() {


        UIirfan.findViews(bi.GrpCRFA, this);


    }


    public void BtnEnd() {


        this.finish();
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();
    }
}
