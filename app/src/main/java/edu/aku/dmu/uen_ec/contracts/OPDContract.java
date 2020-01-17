package edu.aku.dmu.uen_ec.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class OPDContract {

    private static String TAG = "";

    private String cra01;
    private String cra02;
    private String cra04;
    private String cra05;
    private String cra06a;
    private String cra06b;
    private String cra06c;
    private String cra06d;
    private String cra06e;
    private String cra07;
    private String cra03a;
    private String cra03b;
    private String cra03c;
    private String cra12;


    public OPDContract() {
    }

    public OPDContract sync(JSONObject jsonObject) throws JSONException {

        this.cra01 = jsonObject.getString(singleOPD.COLUMN_CRA01);
        this.cra02 = jsonObject.getString(singleOPD.COLUMN_CRA02);
        this.cra04 = jsonObject.getString(singleOPD.COLUMN_CRA04);
        this.cra05 = jsonObject.getString(singleOPD.COLUMN_CRA05);
        this.cra06a = jsonObject.getString(singleOPD.COLUMN_CRA06A);
        this.cra06b = jsonObject.getString(singleOPD.COLUMN_CRA06B);
        this.cra06c = jsonObject.getString(singleOPD.COLUMN_CRA06C);
        this.cra06d = jsonObject.getString(singleOPD.COLUMN_CRA06D);
        this.cra06e = jsonObject.getString(singleOPD.COLUMN_CRA06E);
        this.cra07 = jsonObject.getString(singleOPD.COLUMN_CRA07);
        this.cra03a = jsonObject.getString(singleOPD.COLUMN_CRA03a);
        this.cra03b = jsonObject.getString(singleOPD.COLUMN_CRA03b);
        this.cra03c = jsonObject.getString(singleOPD.COLUMN_CRA03c);
        this.cra12 = jsonObject.getString(singleOPD.COLUMN_CRA12);

        return this;
    }

    public OPDContract hydrate(Cursor cursor) {

        this.cra01 = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA01));
        this.cra02 = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA02));
        this.cra04 = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA04));
        this.cra05 = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA05));
        this.cra06a = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA06A));
        this.cra06b = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA06B));
        this.cra06c = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA06C));
        this.cra06d = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA06D));
        this.cra06e = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA06E));
        this.cra07 = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA07));
        this.cra03a = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA03a));
        this.cra03b = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA03b));
        this.cra03c = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA03c));
        this.cra12 = cursor.getString(cursor.getColumnIndex(singleOPD.COLUMN_CRA12));


        return this;
    }

    public String getcra01() {
        return cra01;
    }

    public void setcra01(String cra01) {
        this.cra01 = cra01;
    }

    public String getcra02() {
        return cra02;
    }

    public void setcra02(String cra02) {
        this.cra02 = cra02;
    }

    public String getcra04() {
        return cra04;
    }

    public void setcra04(String cra04) {
        this.cra04 = cra04;
    }

    public String getcra05() {
        return cra05;
    }

    public void setcra05(String cra05) {
        this.cra05 = cra05;
    }

    public String getcra06a() {
        return cra06a;
    }

    public void setcra06a(String cra06a) {
        this.cra06a = cra06a;
    }

    public String getcra06b() {
        return cra06b;
    }

    public void setcra06b(String cra06b) {
        this.cra06b = cra06b;
    }

    public String getcra06c() {
        return cra06c;
    }

    public void setcra06c(String cra06c) {
        this.cra06c = cra06c;
    }

    public String getcra06d() {
        return cra06d;
    }

    public void setcra06d(String cra06d) {
        this.cra06d = cra06d;
    }

    public String getcra06e() {
        return cra06e;
    }

    public void setcra06e(String cra06e) {
        this.cra06e = cra06e;
    }

    public String getcra07() {
        return cra07;
    }

    public void setcra07(String cra07) {
        this.cra07 = cra07;
    }

    public String getcra03a() {
        return cra03a;
    }

    public void setcra03a(String cra03a) {
        this.cra03a = cra03a;
    }

    public String getcra03b() {
        return cra03b;
    }

    public void setcra03b(String cra03b) {
        this.cra03b = cra03b;
    }

    public String getcra03c() {
        return cra03c;
    }

    public void setcra03c(String cra03c) {
        this.cra03c = cra03c;
    }

    public String getcra12() {
        return cra12;
    }

    public void setcra12(String cra12) {
        this.cra12 = cra12;
    }

    public static abstract class singleOPD implements BaseColumns {

        public static final String TABLE_NAME = "OPD_table";
        public static final String _ID = "_ID";

        public static final String COLUMN_CRA01 = "cra01";
        public static final String COLUMN_CRA02 = "cra02";
        public static final String COLUMN_CRA04 = "cra04";
        public static final String COLUMN_CRA05 = "cra05";
        public static final String COLUMN_CRA06A = "cra06a";
        public static final String COLUMN_CRA06B = "cra06b";
        public static final String COLUMN_CRA06C = "cra06c";
        public static final String COLUMN_CRA06D = "cra06d";
        public static final String COLUMN_CRA06E = "cra06e";
        public static final String COLUMN_CRA07 = "cra07";
        public static final String COLUMN_CRA03a = "cra03a";
        public static final String COLUMN_CRA03b = "cra03b";
        public static final String COLUMN_CRA03c = "cra03c";
        public static final String COLUMN_CRA12 = "cra12";

        public static final String _URI = "getforms.php";
    }

}