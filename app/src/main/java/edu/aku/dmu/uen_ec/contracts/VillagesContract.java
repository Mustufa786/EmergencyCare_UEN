package edu.aku.dmu.uen_ec.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class VillagesContract {

    private String villageCode;
    private String villageName;
    private String districtCode;
    private String talukaCode;
    private String ucCode;

    public String getTalukaCode() {
        return talukaCode;
    }

    public void setTalukaCode(String talukaCode) {
        this.talukaCode = talukaCode;
    }


    public VillagesContract() {
    }

    public VillagesContract sync(JSONObject jsonObject) throws JSONException {
        this.villageCode = jsonObject.getString(singleVillage.COLUMN_VILLAGE_CODE);
        this.villageName = jsonObject.getString(singleVillage.COLUMN_VILLAGE_NAME);
        this.ucCode = jsonObject.getString(singleVillage.COLUMN_UC_CODE);


        return this;
    }

    public VillagesContract hydrate(Cursor cursor) {
        this.villageCode = cursor.getString(cursor.getColumnIndex(singleVillage.COLUMN_VILLAGE_CODE));
        this.villageName = cursor.getString(cursor.getColumnIndex(singleVillage.COLUMN_VILLAGE_NAME));
//        this.districtCode = cursor.getString(cursor.getColumnIndex(singleVillage.COLUMN_TALUKA_CODE));
//        this.ucCode = cursor.getString(cursor.getColumnIndex(singleVillage.COLUMN_UC_CODE));

        return this;
    }


    public String getUcCode() {
        return ucCode;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public static abstract class singleVillage implements BaseColumns {

        public static final String TABLE_NAME = "villages";
        public static final String COLUMN_NAME_NULLABLE = "nullColumnHack";
        public static final String _ID = "_ID";
        public static final String COLUMN_VILLAGE_CODE = "village_code";
        public static final String COLUMN_VILLAGE_NAME = "village_name";
        public static final String COLUMN_UC_CODE = "uc_code";

        public static final String _URI = "villages.php";

    }
}