package edu.aku.dmu.uen_ec.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

//import edu.aku.dmu.nns_2018.contracts.dummy.A1Model;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {


    private final String projectName = "CRF";
    //private final String surveyType = "SN";
    private String _ID = "";
    private String _UID = "";
    private String uc = "";
    private String village = "";


    private String tagID = "";


    private String formType = "";


    private String talukdaCode = "";
    private String formDate = ""; // Date
    private String userr = ""; // Interviewer


    private String istatus = ""; // Interview Status
    private String istatus88x = ""; // Interview Status
    private String istatusHH = ""; // HH Status

    private String sA1 = "";     // Info Section
    private String sA4 = ""; // sA4
    private String sA402 = ""; // sA4
    private String sA7 = ""; //
    private String f1 = ""; //

    private String CRFA = ""; //
    private String studyid = ""; //
    private String crfcstatus = ""; //
    private String crfc21 = ""; //
    private String crfc28 = ""; //


    private String f2 = ""; //
    private String f3 = ""; //
    private String endtime = "";
    private String count = "";
    private String respLineNo = "";
    private String clusterNo = "";
    private String hhNo = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String gpsElev = "";


    private String gpsTime = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion;
    private String sA5 = ""; //

    public FormsContract() {
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getTalukdaCode() {
        return talukdaCode;
    }

    public void setTalukdaCode(String talukdaCode) {
        this.talukdaCode = talukdaCode;
    }

    public FormsContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsTable._ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.formDate = jsonObject.getString(FormsTable.COLUMN_FORMDATE);
        this.userr = jsonObject.getString(FormsTable.COLUMN_USER);

        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus88x = jsonObject.getString(FormsTable.COLUMN_ISTATUS88x);
        this.istatusHH = jsonObject.getString(FormsTable.COLUMN_ISTATUSHH);
        this.gpsElev = jsonObject.getString(FormsTable.COLUMN_GPSELEV);
        this.f1 = jsonObject.getString(FormsTable.COLUMN_F1);

        this.CRFA = jsonObject.getString(FormsTable.COLUMN_CRFA);
        this.studyid = jsonObject.getString(FormsTable.COLUMN_studyid);
        this.crfcstatus = jsonObject.getString(FormsTable.COLUMN_crfcstatus);


        this.f2 = jsonObject.getString(FormsTable.COLUMN_F2);
        this.f3 = jsonObject.getString(FormsTable.COLUMN_F3);
        this.formType = jsonObject.getString(FormsTable.COLUMN_FORMTYPE);
        this.talukdaCode = jsonObject.getString(FormsTable.COLUMN_TALUKA_CODE);
        this.f3 = jsonObject.getString(FormsTable.COLUMN_F3);
        this.endtime = jsonObject.getString(FormsTable.COLUMN_END_TIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.gpsTime = jsonObject.getString(FormsTable.COLUMN_GPSTIME);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_APP_VERSION);

        return this;

    }

    public FormsContract HydrateCRFAB(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable._ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.uc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UC_ID));
        this.village = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_village_ID));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));
        this.userr = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS88x));
        this.gpsElev = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSELEV));
       // this.f1 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1));

        this.CRFA = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CRFA));
        this.studyid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_studyid));
        this.crfcstatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfcstatus));
     //   this.crfc21 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfc21));
       // this.crfc28 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfc28));

     //   this.f2 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F2));
      //  this.f3 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F3));
        this.formType = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMTYPE));
        this.talukdaCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_TALUKA_CODE));
        this.endtime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_END_TIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.gpsTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSTIME));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.synced = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED_DATE));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APP_VERSION));

        // TODO:

        return this;

    }

    public FormsContract HydrateCRF21(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable._ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.uc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UC_ID));
        this.village = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_village_ID));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));
        this.userr = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS88x));
        this.gpsElev = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSELEV));
       // this.f1 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1));

        //this.CRFA = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CRFA));
        this.studyid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_studyid));
        //this.crfcstatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfcstatus));
       // this.crfc21 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfc21));
       //  this.crfc28 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfc28));

//        this.f2 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F2));
  //      this.f3 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F3));
        this.formType = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMTYPE));
        this.talukdaCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_TALUKA_CODE));
        this.endtime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_END_TIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.gpsTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSTIME));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.synced = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED_DATE));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APP_VERSION));

        // TODO:

        return this;

    }

    public FormsContract HydrateCRF28(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable._ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.uc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UC_ID));
        this.village = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_village_ID));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));
        this.userr = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS88x));
        this.gpsElev = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSELEV));
 //       this.f1 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1));

        //this.CRFA = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CRFA));
        this.studyid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_studyid));
        //this.crfcstatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfcstatus));
       // this.crfc21 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfc21));
         this.crfc28 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfc28));

      //  this.f2 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F2));
       // this.f3 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F3));
        this.formType = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMTYPE));
        this.talukdaCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_TALUKA_CODE));
        this.endtime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_END_TIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.gpsTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSTIME));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.synced = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED_DATE));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APP_VERSION));

        // TODO:

        return this;

    }

    public FormsContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable._ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.uc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UC_ID));
        this.village = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_village_ID));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));
        this.userr = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS88x));
        this.gpsElev = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSELEV));
      //  this.f1 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1));

        this.CRFA = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CRFA));
        this.studyid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_studyid));
//        this.crfcstatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfcstatus));
//        this.crfc21 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfc21));
//        this.crfc28 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_crfc28));

      //  this.f2 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F2));
       // this.f3 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F3));
        this.formType = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMTYPE));
        this.talukdaCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_TALUKA_CODE));
        this.endtime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_END_TIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.gpsTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSTIME));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.synced = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED_DATE));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APP_VERSION));

        // TODO:

        return this;

    }


    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String getRespLineNo() {
        return respLineNo;
    }

    public void setRespLineNo(String respLineNo) {
        this.respLineNo = respLineNo;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public String getUID() {
        return _UID;
    }

    public void setUID(String _UID) {
        this._UID = _UID;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getUser() {
        return userr;
    }

    public void setUser(String user) {
        this.userr = user;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }


    public String getIstatus88x() {
        return istatus88x;
    }

    public void setIstatus88x(String istatus88x) {
        this.istatus88x = istatus88x;
    }


    public String getsA1() {
        return sA1;
    }

    public void setsA1(String sA1) {
        this.sA1 = sA1;
    }


    public String getGpsElev() {
        return gpsElev;
    }

    public void setGpsElev(String gpsElev) {
        this.gpsElev = gpsElev;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getsA4() {
        return sA4;
    }

    public void setsA4(String sA4) {
        this.sA4 = sA4;
    }

    public String getsA5() {

        return sA5;
    }

    public void setsA5(String sA5) {
        this.sA5 = sA5;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getClusterNo() {
        return clusterNo;
    }

    public void setClusterNo(String clusterNo) {
        this.clusterNo = clusterNo;
    }

    public String getHhNo() {
        return hhNo;
    }

    public void setHhNo(String hhNo) {
        this.hhNo = hhNo;
    }

    public String getIstatusHH() {
        return istatusHH;
    }

    public void setIstatusHH(String istatusHH) {
        this.istatusHH = istatusHH;
    }

    public String getsA402() {
        return sA402;
    }

    public void setsA402(String sA402) {
        this.sA402 = sA402;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getCRFA() {
        return CRFA;
    }

    public void setCRFA(String CRFA) {
        this.CRFA = CRFA;
    }


    public String getstudyid() {
        return studyid;
    }
    public String getcrfcstatus() {
        return crfcstatus;
    }

    public String getcrfc21() {
        return crfc21;
    }
    public String getcrfc28() {
        return crfc28;
    }

    public void setstudyid(String studyid) {
        this.studyid = studyid;
    }

    public void setcrfcstatus(String crfcstatus) {
        this.crfcstatus = crfcstatus;
    }

    public void setcrfc21(String crfc21) {
        this.crfc21 = crfc21;
    }

    public void setcrfc28(String crfc28) {
        this.crfc28 = crfc28;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsTable._ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(FormsTable.COLUMN_UC_ID, this.uc == null ? JSONObject.NULL : this.uc);
        json.put(FormsTable.COLUMN_village_ID, this.village == null ? JSONObject.NULL : this.village);
        json.put(FormsTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(FormsTable.COLUMN_FORMTYPE, this.formType == null ? JSONObject.NULL : this.formType);
        json.put(FormsTable.COLUMN_TALUKA_CODE, this.talukdaCode == null ? JSONObject.NULL : this.talukdaCode);
        json.put(FormsTable.COLUMN_USER, this.userr == null ? JSONObject.NULL : this.userr);
        json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(FormsTable.COLUMN_ISTATUS88x, this.istatus88x == null ? JSONObject.NULL : this.istatus88x);
        json.put(FormsTable.COLUMN_ISTATUSHH, this.istatusHH == null ? JSONObject.NULL : this.istatusHH);
        json.put(FormsTable.COLUMN_GPSELEV, this.gpsElev == null ? JSONObject.NULL : this.gpsElev);
        json.put(FormsTable.COLUMN_studyid, this.studyid == null ? JSONObject.NULL : this.studyid);
        json.put(FormsTable.COLUMN_crfcstatus, this.crfcstatus == null ? JSONObject.NULL : this.crfcstatus);
        


        if (!this.crfc21.equals("")) {

            json.put(FormsTable.COLUMN_crfc21, this.crfc21.equals("") ? JSONObject.NULL : new JSONObject(this.crfc21));
        }

        if (!this.crfc28.equals("")) {

            json.put(FormsTable.COLUMN_crfc21, this.crfc28.equals("") ? JSONObject.NULL : new JSONObject(this.crfc28));
        }

        // when need to conver to json
        if (!this.f1.equals("")) {

            json.put(FormsTable.COLUMN_F1, this.f1.equals("") ? JSONObject.NULL : new JSONObject(this.f1));
        }


        if (!this.CRFA.equals("")) {

            json.put(FormsTable.COLUMN_CRFA, this.CRFA.equals("") ? JSONObject.NULL : new JSONObject(this.CRFA));
        }


        if (!this.f2.equals("")) {

            json.put(FormsTable.COLUMN_F2, this.f2.equals("") ? JSONObject.NULL : new JSONObject(this.f2));
        }
        if (!this.f3.equals("")) {

            json.put(FormsTable.COLUMN_F3, this.f3.equals("") ? JSONObject.NULL : new JSONObject(this.f3));
        }
        json.put(FormsTable.COLUMN_END_TIME, this.endtime == null ? JSONObject.NULL : this.endtime);
        json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(FormsTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(FormsTable.COLUMN_GPSTIME, this.gpsTime == null ? JSONObject.NULL : this.gpsTime);
        json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        /*json.put(FormsTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(FormsTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);*/
        json.put(FormsTable.COLUMN_APP_VERSION, this.appversion == null ? JSONObject.NULL : this.appversion);


        return json;
    }

    public String getsA7() {
        return sA7;
    }

    public void setsA7(String sA7) {
        this.sA7 = sA7;
    }

    public static abstract class FormsTable implements BaseColumns {

        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectname";
        public static final String _ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "userr";
        public static final String COLUMN_UC_ID = "uc_id";
        public static final String COLUMN_village_ID = "village_id";
        public static final String COLUMN_TALUKA_CODE = "taluka_code";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS88x = "istatus88x";
        public static final String COLUMN_ISTATUSHH = "istatusHH";
        public static final String COLUMN_END_TIME = "endtime";
        public static final String COLUMN_F1 = "f1";

        public static final String COLUMN_CRFA = "crfa";
        public static final String COLUMN_studyid = "studyid";
        public static final String COLUMN_crfcstatus = "crfcstatus";
        public static final String COLUMN_crfc21 = "crfc21";
        public static final String COLUMN_crfc28 = "crfc28";

        public static final String COLUMN_F2 = "f2";
        public static final String COLUMN_F3 = "f3";
        public static final String COLUMN_FORMTYPE = "f_Type";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDATE = "gpsdate";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_GPSELEV = "gpselev";
        public static final String COLUMN_GPSTIME = "gpstime";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APP_VERSION = "appversion";

      //  public static String _URL = "screenings.php";

        public static String _URL = "forms.php";

        public static String _URL2 = "forms2.php";
        public static String _URL3 = "forms3.php";
        public static String _URL4 = "forms4.php";
    }
}
