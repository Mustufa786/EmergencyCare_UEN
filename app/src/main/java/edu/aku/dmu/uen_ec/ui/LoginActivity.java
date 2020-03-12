package edu.aku.dmu.uen_ec.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import edu.aku.dmu.uen_ec.MainActivity;
import edu.aku.dmu.uen_ec.R;
import edu.aku.dmu.uen_ec.core.DatabaseHelper;
import edu.aku.dmu.uen_ec.core.MainApp;
import edu.aku.dmu.uen_ec.databinding.ActivityLoginBinding;
import edu.aku.dmu.uen_ec.util.Util;

import static java.lang.Thread.sleep;

public class LoginActivity extends AppCompatActivity {


    ActivityLoginBinding bi;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "test1234:test1234",
            "1:1",
            "testS12345:testS12345", "bar@example.com:world"
    };
    DatabaseHelper db;
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_login);
        bi.setCallback(this);
    //    bi.userName.setText("dmu@aku");
     //  bi.passowrd.setText("aku?dmu");
        db = new DatabaseHelper(this);

        String packageName = getPackageName();
        try {
            long installedOn = this
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .lastUpdateTime;
            Integer versionCode = this
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .versionCode;
            String versionName = this
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .versionName;
            bi.txtinstalldate.setText("Ver. " + versionName + "." + versionCode + " \r\n( Last Updated: " + new SimpleDateFormat("dd MMM. yyyy").format(new Date(installedOn)) + " )");

            MainApp.versionCode = versionCode;
            MainApp.versionName = versionName;


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        Target viewTarget = new ViewTarget(bi.syncBtn.getId(), this);

        new ShowcaseView.Builder(this)
                .setTarget(viewTarget)
                .setStyle(R.style.CustomShowcaseTheme)
                .setContentText("\n\nPlease Sync Data before login...")
                .singleShot(42)
                .build();



        bi.showpassowrd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    bi.passowrd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    bi.passowrd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        bi.userName.setError(null);
        bi.passowrd.setError(null);
        String email = bi.userName.getText().toString();
        String password = bi.passowrd.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !Util.isPasswordValid(password)) {
            bi.passowrd.setError(getString(R.string.error_invalid_password));
            focusView = bi.passowrd;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            bi.userName.setError(getString(R.string.error_field_required));
            focusView = bi.userName;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }


    public void syncData() {
//        ConnectivityManager connMgr = (ConnectivityManager)
//                getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            new GetAllData(this, "users", MainApp._HOST_URL + UsersContract.UsersTable._URI).execute();
////            new GetAllData(this, "ucs", MainApp._HOST_URL + UCsContract.UCsTable._URI).execute();
//            new GetAllData(this, "villages", MainApp._HOST_URL + VillagesContract.singleVillage._URI).execute();
//        } else {
//            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
//        }

        startActivity(new Intent(this, SyncActivity.class));
    }

    private void showProgress(boolean b) {
        if (b) {
            bi.loginBtn.setVisibility(View.GONE);
            bi.progressBar.setVisibility(View.VISIBLE);
        } else {
            bi.loginBtn.setVisibility(View.VISIBLE);
            bi.progressBar.setVisibility(View.GONE);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Simulate network access.
                sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            HashMap<String, String> tagValues = MainApp.getTagValues(LoginActivity.this);
            LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    || (tagValues.get("org") == null || tagValues.get("org").equals("5"))) {
                DatabaseHelper db = new DatabaseHelper(LoginActivity.this);
                if ((mEmail.equals("dmu@aku") && mPassword.equals("aku?dmu")) || db.Login(mEmail, mPassword)
                        || (mEmail.equals("test1234") && mPassword.equals("test1234"))) {
                    MainApp.userName = mEmail;
                    MainApp.admin = mEmail.contains("@");

                    Intent iLogin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(iLogin);
                    showProgress(false);

                } else {
                    bi.passowrd.setError(getString(R.string.error_incorrect_password));
                    bi.passowrd.requestFocus();
                    Toast.makeText(LoginActivity.this, mEmail + " " + mPassword, Toast.LENGTH_SHORT).show();
                    showProgress(false);
                }
            } else {
                showProgress(false);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        LoginActivity.this);
                alertDialogBuilder
                        .setMessage("GPS is disabled in your device. Enable it?")
                        .setCancelable(false)
                        .setPositiveButton("Enable GPS",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Intent callGPSSettingIntent = new Intent(
                                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        startActivity(callGPSSettingIntent);
                                    }
                                });
                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        }


        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
