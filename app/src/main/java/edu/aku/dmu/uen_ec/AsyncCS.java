package edu.aku.dmu.uen_ec;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

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
    private ProgressBar progressBar;
    private Context context;

    public AsyncCS(Context context, Collection<?> lst, boolean flag) {
        this.lst = lst;
        this.flag = flag;
        lst_string = new ArrayList<>();
//        progressBar = new ProgressBar()
    }

    @Override
    protected List<JSONModelCRFA> doInBackground(Void... voids) {

        if (flag) {
            Collection<FormsContract> fm = (Collection<FormsContract>) lst;
            for (FormsContract fc : fm) {
                JSONModelCRFA crfa = JSONUtils.getModelFromJSON(fc.getCRFA(), JSONModelCRFA.class);
                if ((crfa.getCra12().equals("1") || crfa.getCra12().equals("2") || crfa.getCra12().equals("3")) && daysCheck(crfa))
                    lst_string.add(crfa);
            }
        } else {
            Collection<OPDContract> fm = (Collection<OPDContract>) lst;
            for (OPDContract fc : fm) {
                if ((fc.getcra12().equals("1") || fc.getcra12().equals("2") || fc.getcra12().equals("3")) && daysCheck(fc)) {
                    lst_string.add(new JSONModelCRFA(fc));
                }
            }
        }

        return lst_string;
    }

    private boolean daysCheck(JSONModelCRFA crfa) {
        String pDate = String.format("%02d", Integer.valueOf(crfa.getCra03a())) + "-" + String.format("%02d", Integer.valueOf(crfa.getCra03b())) + "-" + String.format("%02d", Integer.valueOf(crfa.getCra03c()));
        return DateUtils.ageInDaysByDOB(pDate) < 8;
    }

    private boolean daysCheck(OPDContract crfa) {
        String pDate = crfa.getcra03a() + "-" + crfa.getcra03b() + "-" + crfa.getcra03c();
        return DateUtils.ageInDaysByDOB(pDate) < 8;
    }
}
