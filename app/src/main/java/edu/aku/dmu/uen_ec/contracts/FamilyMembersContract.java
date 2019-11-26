package edu.aku.dmu.uen_ec.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FamilyMembersContract implements Serializable {

    private final String projectName = "SRC-2";
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String formDate = "";
    private String deviceId = "";
    private String f1b = "";
    private String devicetagID = "";
    private String user = "";
    private String app_ver = "";
    private String serialNo = "";
    private String synced = "";
    private String syncedDate = "";


    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
        this.app_ver = app_ver;
    }

    public String getF1b() {
        return f1b;
    }


    public void setF1b(String f1b) {
        this.f1b = f1b;
    }


    public FamilyMembersContract() {
    }

    public FamilyMembersContract(FamilyMembersContract fmc) {
        this.serialNo = fmc.serialNo;

    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String get_UUID() {
        return _UUID;
    }

    public void set_UUID(String _UUID) {
        this._UUID = _UUID;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSyncedDate() {
        return syncedDate;
    }

    public void setSyncedDate(String syncedDate) {
        this.syncedDate = syncedDate;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }


    public FamilyMembersContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(familyMembers.COLUMN_ID);
        this._UID = jsonObject.getString(familyMembers.COLUMN_UID);
        this._UUID = jsonObject.getString(familyMembers.COLUMN_UUID);
        this.formDate = jsonObject.getString(familyMembers.COLUMN_FORMDATE);
        this.deviceId = jsonObject.getString(familyMembers.COLUMN_DEVICEID);
        this.user = jsonObject.getString(familyMembers.COLUMN_USER);
        this.app_ver = jsonObject.getString(familyMembers.COLUMN_APP_VERSION);
        this.devicetagID = jsonObject.getString(familyMembers.COLUMN_DEVICETAGID);

        return this;

    }

    public FamilyMembersContract Hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_UUID));
        this.formDate = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_FORMDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DEVICEID));
        this.user = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_USER));
        this.app_ver = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_APP_VERSION));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_DEVICETAGID));
        this.f1b = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_F1B));
        this.synced = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SYNCED));
        this.syncedDate = cursor.getString(cursor.getColumnIndex(familyMembers.COLUMN_SYNCED_DATE));

        return this;

    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(familyMembers.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(familyMembers.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(familyMembers.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(familyMembers.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(familyMembers.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(familyMembers.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(familyMembers.COLUMN_APP_VERSION, this.app_ver == null ? JSONObject.NULL : this.app_ver);
//        json.put(familyMembers.COLUMN_KISH_SELECTED_ADOLESCENT, this.kishAdolsSelected == null ? JSONObject.NULL : this.kishAdolsSelected);
        if (this.f1b != null && !this.f1b.equals("")) {
            json.put(familyMembers.COLUMN_F1B, this.f1b.equals("") ? JSONObject.NULL : new JSONObject(this.f1b));
        }

        json.put(familyMembers.COLUMN_PROJECT_NAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(familyMembers.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);


        return json;
    }

    public static abstract class familyMembers implements BaseColumns {

        public static final String TABLE_NAME = "familymembers";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECT_NAME = "project_name";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_APP_VERSION = "app_ver";
        public static final String COLUMN_F1B = "f1b";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "sync_date";
        public static String _URL = "familymembers.php";
        public static String _URI = "getfamilymembers.php";
    }
}
