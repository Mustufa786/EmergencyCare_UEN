package edu.aku.dmu.uen_ec.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.dmu.uen_ec.contracts.FamilyMembersContract;
import edu.aku.dmu.uen_ec.contracts.FamilyMembersContract.familyMembers;
import edu.aku.dmu.uen_ec.contracts.FormsContract;
import edu.aku.dmu.uen_ec.contracts.FormsContract.FormsTable;
import edu.aku.dmu.uen_ec.contracts.OPDContract;
import edu.aku.dmu.uen_ec.contracts.OPDContract.singleOPD;
import edu.aku.dmu.uen_ec.contracts.TalukasContract;
import edu.aku.dmu.uen_ec.contracts.TalukasContract.singleTaluka;
import edu.aku.dmu.uen_ec.contracts.UCsContract;
import edu.aku.dmu.uen_ec.contracts.UCsContract.UCsTable;
import edu.aku.dmu.uen_ec.contracts.UsersContract;
import edu.aku.dmu.uen_ec.contracts.UsersContract.UsersTable;
import edu.aku.dmu.uen_ec.contracts.VillagesContract;
import edu.aku.dmu.uen_ec.contracts.VillagesContract.singleVillage;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CRF.db";
    public static final String PROJECT_NAME = "CRF";
    private static final int DATABASE_VERSION = 2;
    public static final String DB_NAME = DATABASE_NAME.replace(".", "_" + MainApp.versionName + "_" + DATABASE_VERSION + "_copy.");


    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.ROW_USERNAME + " TEXT,"
            + UsersTable.ROW_PASSWORD + " TEXT"
            + " ) ;";
    public static final String SQL_CREATE_UCS = "CREATE TABLE " + UCsTable.TABLE_NAME + "("
            + UCsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UCsTable.COLUMN_UCS_NAME + " TEXT,"
            + UCsTable.COLUMN_TALUKA_CODE + " TEXT,"
            + UCsTable.COLUMN_UCCODE + " TEXT"
            + " ) ;";
    public static final String SQL_CREATE_VILLAGES = "CREATE TABLE " + singleVillage.TABLE_NAME + "("
            + singleVillage._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleVillage.COLUMN_VILLAGE_NAME + " TEXT,"
            + singleVillage.COLUMN_VILLAGE_CODE + " TEXT,"
            + singleVillage.COLUMN_UC_CODE + " TEXT"
            + " ) ;";
    public static final String SQL_CREATE_TALUKAS = "CREATE TABLE " + singleTaluka.TABLE_NAME + "("
            + singleTaluka._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleTaluka.COLUMN_TALUKA_NAME + " TEXT,"
            + singleTaluka.COLUMN_TALUKA_CODE + " TEXT"
            + " ) ;";

    public static final String SQL_CREATE_OPD = "CREATE TABLE " + singleOPD.TABLE_NAME + "("
            + singleOPD._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleOPD.COLUMN_CRA01 + " TEXT,"
            + singleOPD.COLUMN_CRA02 + " TEXT,"
            + singleOPD.COLUMN_CRA04 + " TEXT,"
            + singleOPD.COLUMN_CRA05 + " TEXT,"
            + singleOPD.COLUMN_CRA06A + " TEXT,"
            + singleOPD.COLUMN_CRA06B + " TEXT,"
            + singleOPD.COLUMN_CRA06C + " TEXT,"
            + singleOPD.COLUMN_CRA06D + " TEXT,"
            + singleOPD.COLUMN_CRA06E + " TEXT,"
            + singleOPD.COLUMN_CRA07 + " TEXT"
            + " ) ;";


    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT," +
            FormsTable.COLUMN_UC_ID + " TEXT," +
            FormsTable.COLUMN_village_ID + " TEXT," +
            FormsTable.COLUMN_TALUKA_CODE + " TEXT," +
            FormsTable.COLUMN_FORMTYPE + " TEXT," +
            FormsTable.COLUMN_FORMDATE + " TEXT," +
            FormsTable.COLUMN_USER + " TEXT," +
            FormsTable.COLUMN_GPSELEV + " TEXT," +
            FormsTable.COLUMN_F1 + " TEXT," +
            FormsTable.COLUMN_CRFA + " TEXT," +
            FormsTable.COLUMN_studyid + " TEXT," +
            FormsTable.COLUMN_crfcstatus + " TEXT," +
            FormsTable.COLUMN_crfc21 + " TEXT," +
            FormsTable.COLUMN_crfc28 + " TEXT," +
            FormsTable.COLUMN_F2 + " TEXT," +
            FormsTable.COLUMN_F3 + " TEXT," +
            FormsTable.COLUMN_END_TIME + " TEXT," +
            FormsTable.COLUMN_ISTATUS + " TEXT," +
            FormsTable.COLUMN_ISTATUS88x + " TEXT," +
            FormsTable.COLUMN_GPSLAT + " TEXT," +
            FormsTable.COLUMN_GPSLNG + " TEXT," +
            FormsTable.COLUMN_GPSDATE + " TEXT," +
            FormsTable.COLUMN_GPSACC + " TEXT," +
            FormsTable.COLUMN_GPSTIME + " TEXT," +
            FormsTable.COLUMN_DEVICEID + " TEXT," +
            FormsTable.COLUMN_DEVICETAGID + " TEXT," +
            FormsTable.COLUMN_APP_VERSION + " TEXT," +
            FormsTable.COLUMN_SYNCED + " TEXT," +
            FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    private static final String SQL_CREATE_FAMILY_MEMEBERS = "CREATE TABLE "
            + familyMembers.TABLE_NAME + "("
            + familyMembers.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + familyMembers.COLUMN_PROJECT_NAME + " TEXT,"
            + familyMembers.COLUMN_UID + " TEXT UNIQUE," +
            familyMembers.COLUMN_UUID + " TEXT," +
            familyMembers.COLUMN_FORMDATE + " TEXT," +
            familyMembers.COLUMN_USER + " TEXT," +
            familyMembers.COLUMN_F1B + " TEXT," +
            familyMembers.COLUMN_DEVICEID + " TEXT," +
            familyMembers.COLUMN_DEVICETAGID + " TEXT," +
            familyMembers.COLUMN_APP_VERSION + " TEXT," +
            familyMembers.COLUMN_SYNCED + " TEXT," +
            familyMembers.COLUMN_SYNCED_DATE + " TEXT"
            + " );";


    private static final String SQL_DELETE_FORMS = "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;
    private static final String SQL_DELETE_TALUKA = "DROP TABLE IF EXISTS " + singleTaluka.TABLE_NAME;
    private static final String SQL_DELETE_FAMILYMEMBERS = "DROP TABLE IF EXISTS " + familyMembers.TABLE_NAME;
    private static final String SQL_DELETE_USER = "DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME;
    private static final String SQL_DELETE_UCS = "DROP TABLE IF EXISTS " + UCsTable.TABLE_NAME;
    private static final String SQL_DELETE_VILLAGES = "DROP TABLE IF EXISTS " + singleVillage.TABLE_NAME;
    private static final String SQL_DELETE_OPD = "DROP TABLE IF EXISTS " + singleOPD.TABLE_NAME;

    private final String TAG = "DatabaseHelper";
    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_FAMILY_MEMEBERS);
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_UCS);
        db.execSQL(SQL_CREATE_VILLAGES);
        db.execSQL(SQL_CREATE_TALUKAS);
        db.execSQL(SQL_CREATE_OPD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        /*db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_DELETE_FAMILYMEMBERS);
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_UCS);
        db.execSQL(SQL_DELETE_VILLAGES);
        db.execSQL(SQL_DELETE_TALUKA);
        db.execSQL(SQL_DELETE_OPD);*/

        switch (i) {
            case 1:
                db.execSQL(SQL_CREATE_OPD);

        }

    }

    public List<UCsContract> getUCsList() {
        List<UCsContract> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + UCsTable.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UCsContract fc = new UCsContract();
                fc.setUcs(c.getString(c.getColumnIndex(UCsTable.COLUMN_UCS_NAME)));
                fc.setUccode(c.getString(c.getColumnIndex(UCsTable.COLUMN_UCCODE)));
                formList.add(fc.hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public List<TalukasContract> getTalukaList() {
        List<TalukasContract> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + singleTaluka.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TalukasContract fc = new TalukasContract();
                formList.add(fc.hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public List<OPDContract> getOPDList() {
        List<OPDContract> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + singleOPD.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                OPDContract fc = new OPDContract();
                formList.add(fc.hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public FormsContract getsFormContract(String studyID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_UC_ID,
                FormsTable.COLUMN_village_ID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_F1,
                FormsTable.COLUMN_CRFA,
                FormsTable.COLUMN_studyid,
                FormsTable.COLUMN_crfcstatus,
                FormsTable.COLUMN_crfc21,
                FormsTable.COLUMN_crfc28,
                FormsTable.COLUMN_F2,
                FormsTable.COLUMN_F3,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_TALUKA_CODE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_GPSTIME,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
        };


        String whereClause = FormsTable.COLUMN_studyid + "=?";
        String[] whereArgs = new String[]{studyID};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        FormsContract allFC = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC = fc.Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public OPDContract getFroms(String studyID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleOPD._ID,
                singleOPD.COLUMN_CRA01,
                singleOPD.COLUMN_CRA02,
                singleOPD.COLUMN_CRA04,
                singleOPD.COLUMN_CRA05,
                singleOPD.COLUMN_CRA06A,
                singleOPD.COLUMN_CRA06B,
                singleOPD.COLUMN_CRA06C,
                singleOPD.COLUMN_CRA06D,
                singleOPD.COLUMN_CRA06E,
                singleOPD.COLUMN_CRA07,

        };


        String whereClause = singleOPD.COLUMN_CRA01 + "=?";
        String[] whereArgs = new String[]{studyID};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleOPD._ID + " ASC";

        OPDContract allFC = null;
        try {
            c = db.query(
                    singleOPD.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                OPDContract fc = new OPDContract();
                allFC = fc.hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public List<FormsContract> getsFormContractCRFC(String crfstatus) {


        List<FormsContract> list_form = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_UC_ID,
                FormsTable.COLUMN_village_ID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_F1,
                FormsTable.COLUMN_CRFA,
                FormsTable.COLUMN_studyid,
                FormsTable.COLUMN_crfcstatus,
                FormsTable.COLUMN_crfc21,
                FormsTable.COLUMN_crfc28,
                FormsTable.COLUMN_F2,
                FormsTable.COLUMN_F3,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_TALUKA_CODE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_GPSTIME,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
        };


        String whereClause = " f_Type='CRFA' and " + FormsTable.COLUMN_crfcstatus + "=?";
        String[] whereArgs = new String[]{crfstatus};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        FormsContract allFC = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                list_form.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return list_form;
    }


    public String getsFormcount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 0;
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_studyid,
        };


        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String name = "0";
        String orderBy = null;
        // FormsTable.COLUMN_studyid + " ASC";

        FormsContract allFC = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );


            while (c.moveToNext()) {
                // FormsContract fc = new FormsContract();
                // allFC = fc.Hydrate(c);
                name = c.getString(c.getColumnIndex(FormsTable.COLUMN_studyid));
                i++;

            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        if (name.equals("") || name.isEmpty()) {
            name = i + "";
        }
        return name;
    }

    public List<VillagesContract> getVillages(String id) {
        List<VillagesContract> formList = new ArrayList<>();

        String[] columns = {
                singleVillage.COLUMN_VILLAGE_NAME,
                singleVillage.COLUMN_VILLAGE_CODE
        };
        String selection = singleVillage.COLUMN_UC_CODE + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        String orderBy =
                singleVillage.COLUMN_VILLAGE_NAME + " COLLATE NOCASE ASC;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(
                singleVillage.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                orderBy);

        if (c.moveToFirst()) {
            do {
                VillagesContract fc = new VillagesContract();
//                fc.setHf_name(c.getString(c.getColumnIndex(singleHF.COLUMN_HF_NAME)));
//                fc.setHf_uen_code(c.getLong(c.getColumnIndex(singleHF.COLUMN_HF_UEN_CODE)));
                formList.add(fc.hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public List<UCsContract> getUcs(String id) {
        List<UCsContract> formList = new ArrayList<>();

        String[] columns = {
                UCsTable.COLUMN_UCS_NAME,
                UCsTable.COLUMN_UCCODE
        };
        String selection = UCsTable.COLUMN_TALUKA_CODE + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        String orderBy =
                UCsTable.COLUMN_UCS_NAME + " COLLATE NOCASE ASC;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(
                UCsTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                orderBy);

        if (c.moveToFirst()) {
            do {
                UCsContract fc = new UCsContract();
                formList.add(fc.hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public Long addForm(FormsContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECT_NAME, fc.getProjectName());
        values.put(FormsTable.COLUMN_UID, fc.getUID());
        values.put(FormsTable.COLUMN_UC_ID, fc.getUc());
        values.put(FormsTable.COLUMN_village_ID, fc.getVillage());
        values.put(FormsTable.COLUMN_FORMDATE, fc.getFormDate());
        values.put(FormsTable.COLUMN_USER, fc.getUser());
        values.put(FormsTable.COLUMN_TALUKA_CODE, fc.getTalukdaCode());
        values.put(FormsTable.COLUMN_FORMTYPE, fc.getFormType());
        values.put(FormsTable.COLUMN_F1, fc.getF1());
        values.put(FormsTable.COLUMN_CRFA, fc.getCRFA());
        values.put(FormsTable.COLUMN_studyid, fc.getstudyid());
        values.put(FormsTable.COLUMN_crfcstatus, fc.getcrfcstatus());
        values.put(FormsTable.COLUMN_crfc21, fc.getcrfc21());
        values.put(FormsTable.COLUMN_crfc28, fc.getcrfc28());
        values.put(FormsTable.COLUMN_F2, fc.getF2());
        values.put(FormsTable.COLUMN_F3, fc.getF3());
        values.put(FormsTable.COLUMN_GPSELEV, fc.getGpsElev());
        values.put(FormsTable.COLUMN_GPSTIME, fc.getGpsElev());
        values.put(FormsTable.COLUMN_ISTATUS, fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88x, fc.getIstatus88x());
        values.put(FormsTable.COLUMN_GPSLAT, fc.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, fc.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDATE, fc.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, fc.getGpsAcc());
        values.put(FormsTable.COLUMN_DEVICETAGID, fc.getTagID());
        values.put(FormsTable.COLUMN_DEVICEID, fc.getDeviceID());
        values.put(FormsTable.COLUMN_APP_VERSION, fc.getAppversion());
        values.put(FormsTable.COLUMN_SYNCED, fc.getSynced());
        values.put(FormsTable.COLUMN_SYNCED_DATE, fc.getSynced_date());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addFamilyMembers(FamilyMembersContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_PROJECT_NAME, fmc.getProjectName());
        values.put(familyMembers.COLUMN_UID, fmc.get_UID());
        values.put(familyMembers.COLUMN_UUID, fmc.get_UUID());
        values.put(familyMembers.COLUMN_FORMDATE, fmc.getFormDate());
        values.put(familyMembers.COLUMN_USER, fmc.getUser());
        values.put(familyMembers.COLUMN_DEVICETAGID, fmc.getDevicetagID());
        values.put(familyMembers.COLUMN_F1B, fmc.getF1b());
        values.put(familyMembers.COLUMN_DEVICEID, fmc.getDeviceId());
        values.put(familyMembers.COLUMN_SYNCED, fmc.getSynced());
        values.put(familyMembers.COLUMN_SYNCED_DATE, fmc.getSyncedDate());
        values.put(familyMembers.COLUMN_APP_VERSION, fmc.getApp_ver());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                familyMembers.TABLE_NAME,
                familyMembers.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Collection<FormsContract> getTodayForms() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_UC_ID,
                FormsTable.COLUMN_village_ID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_CRFA,
                FormsTable.COLUMN_studyid,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_TALUKA_CODE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_GPSTIME,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
        };

        String whereClause = FormsTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> formList = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                formList.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return formList;
    }

    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_UC_ID,
                FormsTable.COLUMN_village_ID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_END_TIME,
                FormsTable.COLUMN_F1,
                FormsTable.COLUMN_CRFA,
                FormsTable.COLUMN_studyid,
                FormsTable.COLUMN_crfcstatus,
                FormsTable.COLUMN_crfc21,
                FormsTable.COLUMN_crfc28,
                FormsTable.COLUMN_F2,
                FormsTable.COLUMN_F3,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_TALUKA_CODE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_GPSTIME,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,

        };
        String whereClause = FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    FormsContract fc = new FormsContract();
                    allFC.add(fc.Hydrate(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getUnsyncedFormsCRF(String formtype) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_UC_ID,
                FormsTable.COLUMN_village_ID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_END_TIME,
                //  FormsTable.COLUMN_F1,
                FormsTable.COLUMN_CRFA,
                FormsTable.COLUMN_studyid,
                FormsTable.COLUMN_crfcstatus,
                //   FormsTable.COLUMN_crfc21,
                //   FormsTable.COLUMN_crfc28,

                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_TALUKA_CODE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_GPSTIME,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,

        };
        String whereClause = "(" + FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " = '' ) and f_Type='" + formtype + "'";

        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    FormsContract fc = new FormsContract();
                    allFC.add(fc.HydrateCRFAB(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getUnsyncedFormsCRFC21() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_UC_ID,
                FormsTable.COLUMN_village_ID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_END_TIME,
                //    FormsTable.COLUMN_F1,
                FormsTable.COLUMN_CRFA,
                FormsTable.COLUMN_studyid,
                //   FormsTable.COLUMN_crfcstatus,
                //  FormsTable.COLUMN_crfc21,
                //  FormsTable.COLUMN_crfc28,

                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_TALUKA_CODE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_GPSTIME,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,

        };
        String whereClause = FormsTable.COLUMN_SYNCED + "='1' and f_Type='CRFA' and (crfc21 is not null or crfc21!='')";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    FormsContract fc = new FormsContract();
                    allFC.add(fc.Hydrate(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<FormsContract> getUnsyncedFormsCRFC28() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_UC_ID,
                FormsTable.COLUMN_village_ID,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_END_TIME,
                //  FormsTable.COLUMN_F1,
                //  FormsTable.COLUMN_CRFA,
                FormsTable.COLUMN_studyid,
                //   FormsTable.COLUMN_crfcstatus,
                //FormsTable.COLUMN_crfc21,
                FormsTable.COLUMN_crfc28,

                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_TALUKA_CODE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_GPSELEV,
                FormsTable.COLUMN_GPSTIME,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,

        };
        String whereClause = FormsTable.COLUMN_SYNCED + "='1' and f_Type='CRFA' and (crfc28 is not null or crfc28!='')";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    FormsContract fc = new FormsContract();
                    allFC.add(fc.Hydrate(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FamilyMembersContract> getUnsyncedFamilyMember() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                familyMembers._ID,
                familyMembers.COLUMN_UID,
                familyMembers.COLUMN_UUID,
                familyMembers.COLUMN_FORMDATE,
                familyMembers.COLUMN_USER,
                familyMembers.COLUMN_DEVICEID,
                familyMembers.COLUMN_DEVICETAGID,
                familyMembers.COLUMN_APP_VERSION,
                familyMembers.COLUMN_F1B,
                familyMembers.COLUMN_SYNCED,
                familyMembers.COLUMN_SYNCED_DATE,


        };
        String whereClause = familyMembers.COLUMN_SYNCED + " is null OR " + familyMembers.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FamilyMembersContract> allFC = new ArrayList<FamilyMembersContract>();
        try {
            c = db.query(
                    familyMembers.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    FamilyMembersContract fc = new FamilyMembersContract();
                    allFC.add(fc.Hydrate(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsContract.FormsTable._ID + " LIKE ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
        db.close();
    }

    public void updateSyncedFamilyMembers(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_SYNCED, true);
        values.put(familyMembers.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = familyMembers._ID + " LIKE ?";
        String[] whereArgs = {id};

        int count = db.update(
                familyMembers.TABLE_NAME,
                values,
                where,
                whereArgs);
        db.close();
    }

    public int updatesF1() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_F1, MainApp.fc.getF1());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updatesCRFA() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_CRFA, MainApp.fc.getCRFA());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updatesF2() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_F2, MainApp.fc.getF2());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updatesF3() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_F3, MainApp.fc.getF3());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updatecrf21(String studyid) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        //   values.put(FormsTable.COLUMN_crfc21, MainApp.fc.getcrfc21());
        values.put(FormsTable.COLUMN_crfcstatus, "1");

// Which row to update, based on the ID
        String selection = "f_type='CRFA' and " + FormsTable.COLUMN_studyid + " =? ";
        String[] selectionArgs = {studyid};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updatecrf28(String studyid) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        // values.put(FormsTable.COLUMN_crfc28, MainApp.fc.getcrfc28());
        values.put(FormsTable.COLUMN_crfcstatus, "2");

// Which row to update, based on the ID
        String selection = "f_type='CRFA' and " + FormsTable.COLUMN_studyid + " =? ";
        String[] selectionArgs = {studyid};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88x, MainApp.fc.getIstatus88x());
        values.put(FormsTable.COLUMN_END_TIME, MainApp.fc.getEndtime());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFormID(FormsContract fc) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, fc.getUID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =?";
        String[] selectionArgs = {String.valueOf(fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateFamilyMemID(FamilyMembersContract fmc) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(familyMembers.COLUMN_UID, fmc.get_UID());

// Which row to update, based on the ID
        String selection = familyMembers._ID + " =?";
        String[] selectionArgs = {String.valueOf(fmc.get_ID())};

        int count = db.update(familyMembers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public void syncUser(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract user = new UsersContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersContract.UsersTable.ROW_USERNAME, user.getUserName());
                values.put(UsersTable.ROW_PASSWORD, user.getPassword());
                db.insert(UsersTable.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public void syncUcs(JSONArray ucslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UCsTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = ucslist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UCsContract user = new UCsContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UCsTable.COLUMN_UCS_NAME, user.getUcsName());
                values.put(UCsTable.COLUMN_UCCODE, user.getUccode());
                values.put(UCsTable.COLUMN_TALUKA_CODE, user.getTaluka_code());

                db.insert(UCsTable.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public void syncVillages(JSONArray villages) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleVillage.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = villages;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                VillagesContract user = new VillagesContract();
                user.sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(singleVillage.COLUMN_VILLAGE_NAME, user.getVillageName());
                values.put(singleVillage.COLUMN_VILLAGE_CODE, user.getVillageCode());
                values.put(singleVillage.COLUMN_UC_CODE, user.getUcCode());
                db.insert(singleVillage.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public void syncTaluka(JSONArray villages) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleTaluka.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = villages;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                TalukasContract user = new TalukasContract();
                user.sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(singleTaluka.COLUMN_TALUKA_NAME, user.getDistrictName());
                values.put(singleTaluka.COLUMN_TALUKA_CODE, user.getDistrictCode());
                db.insert(singleTaluka.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public void syncOPD(JSONArray villages) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleOPD.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = villages;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                OPDContract user = new OPDContract();
                user.sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(singleOPD.COLUMN_CRA01, user.getcra01());
                values.put(singleOPD.COLUMN_CRA02, user.getcra02());
                values.put(singleOPD.COLUMN_CRA04, user.getcra04());
                values.put(singleOPD.COLUMN_CRA05, user.getcra05());
                values.put(singleOPD.COLUMN_CRA06A, user.getcra06a());
                values.put(singleOPD.COLUMN_CRA06B, user.getcra06b());
                values.put(singleOPD.COLUMN_CRA06C, user.getcra06c());
                values.put(singleOPD.COLUMN_CRA06D, user.getcra06d());
                values.put(singleOPD.COLUMN_CRA06E, user.getcra06e());
                values.put(singleOPD.COLUMN_CRA07, user.getcra07());

                db.insert(singleOPD.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public boolean Login(String username, String password) throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        String[] columns = {
                UsersTable._ID,
                UsersTable.ROW_USERNAME,
                UsersTable.ROW_PASSWORD
        };

// Which row to update, based on the ID
        String selection = UsersContract.UsersTable.ROW_USERNAME + " = ?" + " AND " + UsersContract.UsersTable.ROW_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UsersContract.UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        /*cursor.close();
        db.close();
        return cursorCount > 0;*/

        if (cursorCount > 0) {
            cursor.moveToFirst();
            MainApp.usersContract = new UsersContract().Hydrate(cursor);
            return true;
        }

        return false;
    }


}