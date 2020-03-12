package edu.aku.dmu.uen_ec.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.dmu.uen_ec.MainActivity;
import edu.aku.dmu.uen_ec.R;
import edu.aku.dmu.uen_ec.core.DatabaseHelper;
import edu.aku.dmu.uen_ec.core.MainApp;
import edu.aku.dmu.uen_ec.databinding.ActivityEndingBinding;
import edu.aku.dmu.uen_ec.validation.ValidatorClass;

public class EndingActivity extends AppCompatActivity {

    private static final String TAG = EndingActivity.class.getSimpleName();

    ActivityEndingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ending);
        binding.setCallback(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            binding.istatusa.setEnabled(true);
            binding.istatusb.setEnabled(false);
            binding.istatusb.setEnabled(false);
            binding.istatusc.setEnabled(false);
            binding.istatusd.setEnabled(false);
            binding.istatuse.setEnabled(false);
            binding.istatus96.setEnabled(false);
        } else {
            binding.istatusa.setEnabled(false);
            binding.istatusb.setEnabled(true);
            binding.istatusc.setEnabled(true);
            binding.istatusd.setEnabled(true);
            binding.istatuse.setEnabled(true);
            binding.istatus96.setEnabled(true);
        }

        binding.istatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (binding.istatus96.isChecked()) {
                    binding.istatus96x.setVisibility(View.GONE);
                    //istatus88x.requestFocus();
                } else {
                    binding.istatus96x.setText(null);
                    binding.istatus96x.setVisibility(View.GONE);
                }
            }
        });

        if (MainApp.lst_U5.size() > 0) {
            MainApp.lst_U5.clear();
        }
        if (MainApp.lst_U2.size() > 0) {
            MainApp.lst_U2.clear();
        }
        MainApp.serial_no = 1;
        MainApp.Respondent_is_UnMarried = false;

    }

    public void BtnEnd() {

        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                Intent endSec = new Intent(this, MainActivity.class);
                startActivity(endSec);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() {


        MainApp.fc.setIstatus(
                binding.istatusa.isChecked() ? "1"
                        : binding.istatusb.isChecked() ? "2"
                        : binding.istatusc.isChecked() ? "3"
                        : binding.istatusd.isChecked() ? "4"
                        : binding.istatuse.isChecked() ? "5"
                        : binding.istatus96.isChecked() ? "96"
                        : "0");

        MainApp.fc.setIstatus88x(binding.istatus96x.getText().toString());
        MainApp.fc.setEndtime(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));


        //
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnding();

        if (updcount == 1) {

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyRadioButton(this, binding.istatus, binding.istatus96, binding.istatus96x, getString(R.string.istatus));
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }

}
