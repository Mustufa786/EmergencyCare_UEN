package edu.aku.dmu.uen_ec.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.aku.dmu.uen_ec.JSONModels.JSONModelCRFA;
import edu.aku.dmu.uen_ec.R;
import edu.aku.dmu.uen_ec.contracts.FormsContract;
import edu.aku.dmu.uen_ec.core.DatabaseHelper;
import edu.aku.dmu.uen_ec.core.MainApp;
import edu.aku.dmu.uen_ec.util.Util;

import static android.content.Context.MODE_PRIVATE;
import static edu.aku.dmu.uen_ec.core.MainApp.fc;

class SurveyCompletedCustomAdapter extends RecyclerView.Adapter {

    private static final String TAG = "CustomAdapter";
    private final int mSize;
    Context mContext;
    List<JSONModelCRFA> mList;

    public SurveyCompletedCustomAdapter(Context context, List<JSONModelCRFA> list) {
        mContext = context;
        mList = list;
        mSize = list.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crfcitems, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ViewHolder vh = (ViewHolder) holder;

        //rejected items..
        // if(mList.get(position).contains("-00")) {
        //   vh.itemView.setBackgroundColor(Color.parseColor("#FFB7BC"));
        // }

        JSONModelCRFA crf = mList.get(position);

        vh.studyid.setText(crf.getCra01().toUpperCase());

        vh.opdnum.setText(crf.getCra02().equals("") ? "n/a" : crf.getCra02());
        vh.name.setText(crf.getCra04().equals("") ? "n/a" : crf.getCra04().toUpperCase());
        vh.fname.setText(crf.getCra05().equals("") ? "n/a" : crf.getCra05().toUpperCase());
        vh.district.setText(crf.getCra06a().equals("") ? "n/a" : crf.getCra06a().toUpperCase());
        vh.tehsil.setText(crf.getCra06e().equals("") ? "n/a" : crf.getCra06e().toUpperCase());
        vh.uc.setText(crf.getCra06b().equals("") ? "n/a" : crf.getCra06b().toUpperCase());
        vh.village.setText(crf.getCra06c().equals("") ? "n/a" : crf.getCra06c().toUpperCase());
        vh.address.setText(crf.getCra06d().equals("") ? "n/a" : crf.getCra06d().toUpperCase());

        vh.serial.setText(String.valueOf(position + 1));
        String Pdate = crf.getCra03a() + "/" + crf.getCra03b() + "/" + crf.getCra03c();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = null;

        Calendar c = Calendar.getInstance();


        try {
            strDate = sdf.parse(Pdate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.setTime(strDate);


        if (CRFCActivity.days_21) {
            vh.txtdate.setText("Due-Date to Notify LHS");


            c.add(Calendar.DATE, 23);


            Pdate = sdf.format(c.getTime());

            vh.date.setText(Pdate);

        } else {
            vh.txtdate.setText("Due-Date for 28-Days Follow up");

            c.add(Calendar.DATE, 31);

            Pdate = sdf.format(c.getTime());
            vh.date.setText(Pdate);

        }

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (CRFCActivity.days_21 == true) {
                    String studyid = vh.studyid.getText().toString();

                    String datetotnotify = vh.date.getText().toString();

                    dialog21days(studyid, datetotnotify);
                } else {
                    String studyid = vh.studyid.getText().toString();

                    String datetotnotify = vh.date.getText().toString();

                    dialog28days(studyid, datetotnotify);
                }
            }
        });


    }

    public void dialog21days(final String studyid, final String datetotnotify) {
        // 21 days
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);

        final Dialog dialog = new Dialog(mContext);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.crfc21d);

        final EditText day21 = dialog.findViewById(R.id.day21);
        final EditText month21 = dialog.findViewById(R.id.month21);
        final EditText year21 = dialog.findViewById(R.id.year21);
        final EditText lhscode = dialog.findViewById(R.id.lhscode);

        final Button btncancel = dialog.findViewById(R.id.btn_End);
        final Button btnok = dialog.findViewById(R.id.btn_Continue);


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // String studid=vh.
                if (

                        day21.getText().length() == 0 ||
                                month21.getText().length() == 0 ||
                                lhscode.getText().length() == 0 ||
                                year21.getText().length() == 0) {

                    Toast.makeText(mContext, "Please enter the data", Toast.LENGTH_LONG).show();
                    return;
                }
                Date date_master = null;

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date datetonotify = null;

                Calendar c = Calendar.getInstance();


                String currentdate =
                        day21.getText().toString() + "/"
                                + month21.getText().toString() + "/"
                                + year21.getText().toString();

                try {

                    date_master = sdf.parse(datetotnotify);
                    datetonotify = sdf.parse(datetotnotify);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                c.setTime(datetonotify);


                c.add(Calendar.DATE, 2);

                String bigdata = sdf.format(c.getTime());

                c.add(Calendar.DATE, -4);

                String smalldate = sdf.format(c.getTime());


                c.add(Calendar.DATE, 1);
                String small2 = sdf.format(c.getTime());


                c.add(Calendar.DATE, 2);
                String bigdata2 = sdf.format(c.getTime());


                Date DateSmall = null;
                Date Datebig = null;

                Date DateSmall2 = null;
                Date Datebig2 = null;
                Date dateCurrent = null;


                try {
                    DateSmall = sdf.parse(smalldate);
                    Datebig = sdf.parse(bigdata);

                    DateSmall2 = sdf.parse(small2);
                    Datebig2 = sdf.parse(bigdata2);


                    dateCurrent = sdf.parse(currentdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (dateCurrent.getTime() == DateSmall.getTime() || dateCurrent.getTime() == Datebig.getTime()

                        || dateCurrent.getTime() == date_master.getTime()

                        || dateCurrent.getTime() == DateSmall2.getTime()
                        || dateCurrent.getTime() == Datebig2.getTime()

                ) {
                } else {
                    Toast.makeText(mContext, "should be equals to  Notificaiton Date", Toast.LENGTH_LONG).show();
                    return;
                }


                fc = new FormsContract();
                SharedPreferences sharedPref = mContext.getSharedPreferences("tagName", MODE_PRIVATE);
                fc.setTagID(sharedPref.getString("tagName", null));
                fc.setFormDate((DateFormat.format("dd-MM-yyyy HH:mm", new Date())).toString());
                fc.setDeviceID(MainApp.deviceId);
                fc.setUser(MainApp.userName);

                fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);

                JSONObject f1 = new JSONObject();
                Util.setGPS(mContext);
                fc.setF1(String.valueOf(f1));
                try {


                    JSONObject CRFC = new JSONObject();

                    CRFC.put("crc08", lhscode.getText().toString());
                    CRFC.put("crc09a", day21.getText().toString());
                    CRFC.put("crc09b", month21.getText().toString());
                    CRFC.put("crc09c", year21.getText().toString());
                    CRFC.put("crc10", datetotnotify);

                    CRFC.put("crc10", datetotnotify);
                    fc.setFormType("CRFC21");


                    fc.setCRFA(String.valueOf(CRFC));
                    fc.setstudyid(studyid);

                    if (UpdateDBCRF21(studyid)) {

                        Toast.makeText(mContext, "Updated", Toast.LENGTH_SHORT).show();


                        CRFCActivity.bi.btn21.performClick();

                        dialog.dismiss();

                    } else {
                        Toast.makeText(mContext, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        dialog.show();

    }


    private boolean UpdateDBCRF21(String studyid) {


        DatabaseHelper db = new DatabaseHelper(mContext);



        /*
        if (updcount == 1) {

            return true;
        } else {
            Toast.makeText(mContext, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

        */

        // new things


        // 2. insert form
        Long rowId;
        rowId = db.addForm(fc);
        if (rowId > 0) {
            fc.set_ID(String.valueOf(rowId));
            fc.setUID((fc.getDeviceID() + fc.get_ID()));
            db.updateFormID(fc);

            int updcount = db.updatecrf21(studyid);

            return true;
        } else {
            Toast.makeText(mContext, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;

        }

    }

    private boolean UpdateDBCRF28(String studyid) {
        DatabaseHelper db = new DatabaseHelper(mContext);


        Long rowId;
        rowId = db.addForm(fc);
        if (rowId > 0) {
            fc.set_ID(String.valueOf(rowId));
            fc.setUID((fc.getDeviceID() + fc.get_ID()));
            db.updateFormID(fc);

            int updcount = db.updatecrf28(studyid);

            return true;
        } else {
            Toast.makeText(mContext, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;

        }

    }


    public void dialog28days(final String studyid, final String date28days) {
        // 28 days
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);

        final Dialog dialog = new Dialog(mContext);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.crfc28d);

        final RadioButton crfc12a, crfc12b, crfc12c, crfc12d, crfc13a, crfc13b;

        crfc12a = dialog.findViewById(R.id.crfc12a);
        crfc12b = dialog.findViewById(R.id.crfc12b);
        crfc12c = dialog.findViewById(R.id.crfc12c);
        crfc12d = dialog.findViewById(R.id.crfc12d);

        crfc13a = dialog.findViewById(R.id.crfc13a);
        crfc13b = dialog.findViewById(R.id.crfc13b);


        Button btncancel = dialog.findViewById(R.id.btn_End);
        Button btnok = dialog.findViewById(R.id.btn_Continue);


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // String studid=vh.
                if (
                        !crfc12a.isChecked() &
                                !crfc12b.isChecked() &
                                !crfc12c.isChecked() &
                                !crfc12d.isChecked()
                ) {

                    Toast.makeText(mContext, "Please select  the Discharge", Toast.LENGTH_LONG).show();
                    return;
                }

                if (

                        !crfc13a.isChecked() &
                                !crfc13b.isChecked()) {

                    Toast.makeText(mContext, "Please select status ", Toast.LENGTH_LONG).show();
                    return;
                }


                fc = new FormsContract();
                SharedPreferences sharedPref = mContext.getSharedPreferences("tagName", MODE_PRIVATE);
                fc.setTagID(sharedPref.getString("tagName", null));
                fc.setFormDate((DateFormat.format("dd-MM-yyyy HH:mm", new Date())).toString());
                fc.setDeviceID(MainApp.deviceId);
                fc.setUser(MainApp.userName);

                fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);

                JSONObject f1 = new JSONObject();
                Util.setGPS(mContext);
                fc.setF1(String.valueOf(f1));

                try {


                    JSONObject CRFC = new JSONObject();


                    String crf12 = "0";
                    if (crfc12a.isChecked()) {
                        crf12 = "1";
                    } else if (crfc12b.isChecked()) {
                        crf12 = "2";
                    } else if (crfc12c.isChecked()) {
                        crf12 = "3";
                    } else if (crfc12d.isChecked()) {
                        crf12 = "4";
                    } else {
                        crf12 = "0";

                    }


                    String crf13 = "0";
                    if (crfc13a.isChecked()) {
                        crf13 = "1";
                    } else if (crfc13b.isChecked()) {
                        crf13 = "2";
                    }


                    CRFC.put("crc12", crf12);
                    CRFC.put("crc13", crf13);
                    CRFC.put("crc11", date28days);

                    fc.setFormType("CRFC28");
                    fc.setstudyid(studyid);


                    fc.setCRFA(String.valueOf(CRFC));

                    if (UpdateDBCRF28(studyid)) {

                        Toast.makeText(mContext, "Updated", Toast.LENGTH_SHORT).show();

                        CRFCActivity.bi.btn48.performClick();
                        dialog.dismiss();

                    } else {
                        Toast.makeText(mContext, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        dialog.show();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView studyid, serial, name,
                fname, date, txtdate, district, uc, tehsil, address, village, opdnum;

        public ViewHolder(View v) {
            super(v);

            studyid = v.findViewById(R.id.studyid);
            serial = v.findViewById(R.id.serial);
            name = v.findViewById(R.id.name);
            fname = v.findViewById(R.id.fname);
            txtdate = v.findViewById(R.id.txtdate);
            date = v.findViewById(R.id.date);
            uc = v.findViewById(R.id.uc);
            district = v.findViewById(R.id.district);
            tehsil = v.findViewById(R.id.tehsil);
            address = v.findViewById(R.id.address);
            village = v.findViewById(R.id.village);

            opdnum = v.findViewById(R.id.opdn);

        }
    }
}
