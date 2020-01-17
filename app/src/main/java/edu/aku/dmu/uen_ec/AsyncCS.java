package edu.aku.dmu.uen_ec;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.aku.dmu.uen_ec.JSONModels.JSONModelCRFA;
import edu.aku.dmu.uen_ec.JsonUtils.JSONUtils;
import edu.aku.dmu.uen_ec.contracts.FormsContract;
import edu.aku.dmu.uen_ec.contracts.OPDContract;
import edu.aku.dmu.uen_ec.util.DateUtils;

public class AsyncCS extends AsyncTask<Void, Void, List<JSONModelCRFA>> {

    private Collection<?> lst;
    private boolean flag;
    private List<JSONModelCRFA> lst_string;
    private Snackbar snackbar;
    private Activity activity;
    private int daysValue;

    public AsyncCS(Activity activity, Collection<?> lst, boolean flag, int daysValue) {
        this.lst = lst;
        this.flag = flag;
        this.daysValue = daysValue;
        lst_string = new ArrayList<>();
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        snackbar = Snackbar.make(viewGroup, "Loading data..", Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimaryAlpha));
        TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(activity.getResources().getColor(R.color.white));
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        snackbar.show();
    }

    @Override
    protected List<JSONModelCRFA> doInBackground(Void... voids) {

        if (flag) {
            Collection<FormsContract> fm = (Collection<FormsContract>) lst;
            for (FormsContract fc : fm) {
                JSONModelCRFA crfa = JSONUtils.getModelFromJSON(fc.getCRFA(), JSONModelCRFA.class);
                if ((crfa.getCra12().equals("1") || crfa.getCra12().equals("2") || crfa.getCra12().equals("3")) && daysCheck(crfa, daysValue)) {
                    lst_string.add(crfa);
                }
            }
        } else {
            Collection<OPDContract> fm = (Collection<OPDContract>) lst;
            for (OPDContract fc : fm) {
                if ((fc.getcra12().equals("1") || fc.getcra12().equals("2") || fc.getcra12().equals("3")) && daysCheck(fc, daysValue)) {
                    lst_string.add(new JSONModelCRFA(fc));
                }
            }
        }
        return lst_string;
    }

    @Override
    protected void onPostExecute(List<JSONModelCRFA> jsonModelCRFAS) {
        super.onPostExecute(jsonModelCRFAS);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                snackbar.dismiss();
            }
        }, 2000);
    }

    private boolean daysCheck(JSONModelCRFA crfa, int daysValue) {
        String pDate = String.format("%02d", Integer.valueOf(crfa.getCra03a())) + "-" + String.format("%02d", Integer.valueOf(crfa.getCra03b())) + "-" + String.format("%02d", Integer.valueOf(crfa.getCra03c()));
        return DateUtils.ageInDaysByDOB(pDate) < daysValue;
    }

    private boolean daysCheck(OPDContract crfa, int daysValue) {
        String pDate = crfa.getcra03a() + "-" + crfa.getcra03b() + "-" + crfa.getcra03c();
        return DateUtils.ageInDaysByDOB(pDate) < daysValue;
    }
}
