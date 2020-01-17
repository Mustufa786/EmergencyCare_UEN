package edu.aku.dmu.uen_ec.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.aku.dmu.uen_ec.AsyncCS;
import edu.aku.dmu.uen_ec.JSONModels.JSONModelCRFA;
import edu.aku.dmu.uen_ec.R;
import edu.aku.dmu.uen_ec.contracts.FormsContract;
import edu.aku.dmu.uen_ec.contracts.OPDContract;
import edu.aku.dmu.uen_ec.core.DatabaseHelper;
import edu.aku.dmu.uen_ec.databinding.ActivityCBinding;

public class CRFCActivity extends AppCompatActivity {

    private static final String TAG = "CRFCActivity";
    public static ActivityCBinding bi;
    DatabaseHelper db;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public static boolean days_21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_c);
        bi.setCallback(this);
        db = new DatabaseHelper(this);

        days_21 = true;

        get_data_recylceview("0");


    }

    public void day21() {
        get_data_recylceview("0");
        days_21 = true;
    }

    public void day28() {
        get_data_recylceview("1");
        days_21 = false;
    }

    public void get_data_recylceview(String crfstatus) {
        // list here
        List<JSONModelCRFA> list = getdata(crfstatus);

        if (crfstatus.equals("0")) {
            bi.btn21.setText("21 Days Follow-Up" + " (" + list.size() + ")");
            bi.btn21.setBackgroundColor(getResources().getColor(R.color.colorPrimaryAlpha));
            bi.btn48.setBackgroundColor(getResources().getColor(R.color.colorPrimaryAlpha2));
        } else {
            bi.btn48.setText("28 Days Follow-Up" + " (" + list.size() + ")");
            bi.btn21.setBackgroundColor(getResources().getColor(R.color.colorPrimaryAlpha2));
            bi.btn48.setBackgroundColor(getResources().getColor(R.color.colorPrimaryAlpha));
        }

//        Collections.sort(list);
        mRecyclerView = findViewById(R.id.list_survey_completed);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SurveyCompletedCustomAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);

    }

    public List<JSONModelCRFA> getdata(String crfstatus) {
        List<JSONModelCRFA> lst_string = new ArrayList<>();
        Collection<FormsContract> lst = db.getsFormContractCRFC(crfstatus);
      /*  if (lst != null) {
            for (FormsContract fc : lst) {
                JSONModelCRFA crfa = JSONUtils.getModelFromJSON(fc.getCRFA(), JSONModelCRFA.class);
                String stringg;
                if (crfa.getCra12().equals("1") || crfa.getCra12().equals("2") || crfa.getCra12().equals("3")) {
                    stringg = crfa.getCra01() + "-" + crfa.getCra02() + "-" + crfa.getCra04() + "-" + crfa.getCra05() + "-" +
                            crfa.getCra03a() + "/" + crfa.getCra03b() + "/" + crfa.getCra03c();
                    lst_string.add(crfa);
                }
            }
        }*/

        Collection<OPDContract> opd = db.getAllForms();
        try {
            if (lst.size() > 0) lst_string.addAll(new AsyncCS(this, lst, true).execute().get());
            if (opd.size() > 0) lst_string.addAll(new AsyncCS(this, opd, false).execute().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        /*if (opd.size() > 0) {
            for (OPDContract fc : opd) {
                if (fc.getcra12().equals("1") || fc.getcra12().equals("2") || fc.getcra12().equals("3")) {
                    lst_string.add(new JSONModelCRFA(fc));
                }
            }
        }*/

        return lst_string;
    }

}

