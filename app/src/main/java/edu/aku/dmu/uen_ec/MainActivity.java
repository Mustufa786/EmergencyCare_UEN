package edu.aku.dmu.uen_ec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Collection;

import edu.aku.dmu.uen_ec.contracts.FormsContract;
import edu.aku.dmu.uen_ec.core.AndroidDatabaseManager;
import edu.aku.dmu.uen_ec.core.DatabaseHelper;
import edu.aku.dmu.uen_ec.core.MainApp;
import edu.aku.dmu.uen_ec.databinding.ActivityMainBinding;
import edu.aku.dmu.uen_ec.other.DiseaseCode;
import edu.aku.dmu.uen_ec.ui.CRFAActivity;
import edu.aku.dmu.uen_ec.ui.CRFBActivity;
import edu.aku.dmu.uen_ec.ui.CRFCActivity;
import edu.aku.dmu.uen_ec.ui.DesingActivity;
import edu.aku.dmu.uen_ec.ui.SyncActivity;
import edu.aku.dmu.uen_ec.util.Util;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding bi;
    SharedPreferences sharedPref;
    DatabaseHelper db;
    private String rSumText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);


        diseaseCode();

        sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        if (MainApp.admin) {
            bi.databaseBtn.setVisibility(View.VISIBLE);
        } else {
            bi.databaseBtn.setVisibility(View.GONE);
        }

        if (Integer.valueOf(MainApp.versionName.split("\\.")[0]) > 0) {
            bi.testing.setVisibility(View.GONE);
        } else {
            bi.testing.setVisibility(View.VISIBLE);
        }


        db = new DatabaseHelper(this);
        Collection<FormsContract> todaysForms = db.getTodayForms();
        Collection<FormsContract> unsyncedForms = db.getUnsyncedForms();

        rSumText += "TODAY'S RECORDS SUMMARY\r\n";
        rSumText += "=======================\r\n";
        rSumText += "\r\n";
        rSumText += "Total Forms Today: " + todaysForms.size() + "\r\n";
        rSumText += "Unsynced Forms: " + unsyncedForms.size() + "\r\n";
        rSumText += "\r\n";
        if (todaysForms.size() > 0) {
            rSumText += "\tFORMS' LIST: \r\n";
            String iStatus;
            rSumText += "--------------------------------------------------\r\n";
            rSumText += "[ FORM_ID ] \t[Form Status] \t[Sync Status]----------\r\n";
            rSumText += "--------------------------------------------------\r\n";

            for (FormsContract fc : todaysForms) {
                if (fc.getIstatus() != null) {
                    switch (fc.getIstatus()) {
                        case "1":
                            iStatus = "\tComplete";
                            break;
                        case "2":
                            iStatus = "\tIncomplete";
                            break;
                        case "3":
                        case "4":
                            iStatus = "\tRefused";
                            break;
                        default:
                            iStatus = "\tN/A";
                    }
                } else {
                    iStatus = "\tN/A";
                }

                rSumText += fc.get_ID();

                rSumText += " " + iStatus + " ";

                rSumText += (fc.getSynced() == null ? "\t\tNot Synced" : "\t\tSynced");
                rSumText += "\r\n";
                rSumText += "--------------------------------------------------\r\n";
            }
        }
        if (MainApp.admin) {
            bi.databaseBtn.setVisibility(View.VISIBLE);
            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            rSumText += "Last Data Download: \t" + syncPref.getString("LastDownSyncServer", "Never Updated");
            rSumText += "\r\n";
            rSumText += "Last Data Upload: \t" + syncPref.getString("LastUpSyncServer", "Never Synced");
            rSumText += "\r\n";
            rSumText += "\r\n";
            rSumText += "\r\n";

        }
        bi.recordSummary.setText(rSumText);

    }

    public void diseaseCode() {
        DiseaseCode.HmDiseaseCode.put("HIV/AIDS", "1");
        DiseaseCode.HmDiseaseCode.put("HIV-AIDS and Tuberculosis", "2");
        DiseaseCode.HmDiseaseCode.put("HIV/AIDS - Multidrug-resistant Tuberculosis without extensive drug resistance", "3");
        DiseaseCode.HmDiseaseCode.put("HIV/AIDS - Extensively drug-resistant Tuberculosis", "4");
        DiseaseCode.HmDiseaseCode.put("HIV/AIDS resulting in other diseases", "5");
        DiseaseCode.HmDiseaseCode.put("Syphilis", "6");
        DiseaseCode.HmDiseaseCode.put("Chlamydial infection", "7");
        DiseaseCode.HmDiseaseCode.put("Gonococcal infection", "8");
        DiseaseCode.HmDiseaseCode.put("Other sexually transmitted diseases", "9");
        DiseaseCode.HmDiseaseCode.put("Tuberculosis", "10");
        DiseaseCode.HmDiseaseCode.put("Drug-susceptible tuberculosis", "11");
        DiseaseCode.HmDiseaseCode.put("Multidrug-resistant tuberculosis without extensive drug resistance", "12");
        DiseaseCode.HmDiseaseCode.put("Extensively drug-resistant tuberculosis", "13");
        DiseaseCode.HmDiseaseCode.put("Lower respiratory infections (LRI, LRTI)", "14");
        DiseaseCode.HmDiseaseCode.put("Pneumonia", "15");
        DiseaseCode.HmDiseaseCode.put("Influenza", "16");
        DiseaseCode.HmDiseaseCode.put("Pneumococcal pneumonia", "17");
        DiseaseCode.HmDiseaseCode.put("H influenzae type B pneumonia", "18");
        DiseaseCode.HmDiseaseCode.put("Respiratory syncytial virus pneumonia", "19");
        DiseaseCode.HmDiseaseCode.put("Upper respiratory infections (URI)", "20");
        DiseaseCode.HmDiseaseCode.put("Otitis media", "21");
        DiseaseCode.HmDiseaseCode.put("Enteric infections", "22");
        DiseaseCode.HmDiseaseCode.put("Diarrheal diseases (AGE)", "23");
        DiseaseCode.HmDiseaseCode.put("Cholera", "24");
        DiseaseCode.HmDiseaseCode.put("Other salmonella infections", "25");
        DiseaseCode.HmDiseaseCode.put("Shigellosis", "26");
        DiseaseCode.HmDiseaseCode.put("Enteropathogenic E coli infection", "27");
        DiseaseCode.HmDiseaseCode.put("Enterotoxigenic E coli infection", "28");
        DiseaseCode.HmDiseaseCode.put("Campylobacter enteritis", "29");
        DiseaseCode.HmDiseaseCode.put("Amoebiasis", "30");
        DiseaseCode.HmDiseaseCode.put("Cryptosporidiosis", "31");
        DiseaseCode.HmDiseaseCode.put("Rotavirus enteritis", "32");
        DiseaseCode.HmDiseaseCode.put("Aeromonas", "33");
        DiseaseCode.HmDiseaseCode.put("Clostridium difficile", "34");
        DiseaseCode.HmDiseaseCode.put("Norovirus", "35");
        DiseaseCode.HmDiseaseCode.put("Adenovirus", "36");
        DiseaseCode.HmDiseaseCode.put("Other bacterial foodborne diarrhea", "37");
        DiseaseCode.HmDiseaseCode.put("Other diarrheal diseases", "38");
        DiseaseCode.HmDiseaseCode.put("Typhoid fever", "39");
        DiseaseCode.HmDiseaseCode.put("Paratyphoid fever", "40");
        DiseaseCode.HmDiseaseCode.put("Invasive Non-typhoidal Salmonella (iNTS)", "41");
        DiseaseCode.HmDiseaseCode.put("Poliomyelitis", "42");
        DiseaseCode.HmDiseaseCode.put("Other intestinal infectious diseases", "43");
        DiseaseCode.HmDiseaseCode.put("Malaria", "44");
        DiseaseCode.HmDiseaseCode.put("Sepsis", "45");
        DiseaseCode.HmDiseaseCode.put("Leprosy", "46");
        DiseaseCode.HmDiseaseCode.put("Chagas disease", "47");
        DiseaseCode.HmDiseaseCode.put("Leishmaniasis", "48");
        DiseaseCode.HmDiseaseCode.put("Visceral leishmaniasis", "49");
        DiseaseCode.HmDiseaseCode.put("Schistosomiasis", "50");
        DiseaseCode.HmDiseaseCode.put("Cysticercosis", "51");
        DiseaseCode.HmDiseaseCode.put("Cystic echinococcosis", "52");
        DiseaseCode.HmDiseaseCode.put("Dengue", "53");
        DiseaseCode.HmDiseaseCode.put("Yellow fever", "54");
        DiseaseCode.HmDiseaseCode.put("Rabies", "55");
        DiseaseCode.HmDiseaseCode.put("Intestinal nematode infections", "56");
        DiseaseCode.HmDiseaseCode.put("Meningitis", "57");
        DiseaseCode.HmDiseaseCode.put("Pneumococcal meningitis", "58");
        DiseaseCode.HmDiseaseCode.put("H influenzae type B meningitis", "59");
        DiseaseCode.HmDiseaseCode.put("Meningococcal meningitis", "60");
        DiseaseCode.HmDiseaseCode.put("Other meningitis", "61");
        DiseaseCode.HmDiseaseCode.put("Encephalitis", "62");
        DiseaseCode.HmDiseaseCode.put("Diphtheria", "63");
        DiseaseCode.HmDiseaseCode.put("Whooping cough", "64");
        DiseaseCode.HmDiseaseCode.put("Tetanus", "65");
        DiseaseCode.HmDiseaseCode.put("Measles", "66");
        DiseaseCode.HmDiseaseCode.put("Varicella and herpes zoster", "67");
        DiseaseCode.HmDiseaseCode.put("Acute hepatitis", "68");
        DiseaseCode.HmDiseaseCode.put("Acute hepatitis A", "69");
        DiseaseCode.HmDiseaseCode.put("Acute hepatitis B", "70");
        DiseaseCode.HmDiseaseCode.put("Acute hepatitis C", "71");
        DiseaseCode.HmDiseaseCode.put("Acute hepatitis E", "72");
        DiseaseCode.HmDiseaseCode.put("Other unspecified infectious diseases", "73");
        DiseaseCode.HmDiseaseCode.put("Maternal hemorrhage", "74");
        DiseaseCode.HmDiseaseCode.put("Maternal sepsis and other maternal infections", "75");
        DiseaseCode.HmDiseaseCode.put("Maternal hypertensive disorders", "76");
        DiseaseCode.HmDiseaseCode.put("Maternal obstructed labor and uterine rupture", "77");
        DiseaseCode.HmDiseaseCode.put("Maternal abortion and miscarriage", "78");
        DiseaseCode.HmDiseaseCode.put("Ectopic pregnancy", "79");
        DiseaseCode.HmDiseaseCode.put("Other maternal disorders", "80");
        DiseaseCode.HmDiseaseCode.put("Neonatal preterm birth", "81");
        DiseaseCode.HmDiseaseCode.put("Neonatal encephalopathy due to birth asphyxia and trauma", "82");
        DiseaseCode.HmDiseaseCode.put("Neonatal sepsis and other neonatal infections", "83");
        DiseaseCode.HmDiseaseCode.put("Hemolytic disease and other neonatal jaundice", "84");
        DiseaseCode.HmDiseaseCode.put("Other neonatal disorders", "85");
        DiseaseCode.HmDiseaseCode.put("Severe acute malnutrition (SAM)", "86");
        DiseaseCode.HmDiseaseCode.put("Kwashiorkor", "87");
        DiseaseCode.HmDiseaseCode.put("Protein-energy malnutrition", "88");
        DiseaseCode.HmDiseaseCode.put("Iodine deficiency", "89");
        DiseaseCode.HmDiseaseCode.put("Iron-deficiency anemia", "90");
        DiseaseCode.HmDiseaseCode.put("Other nutritional deficiencies", "91");
        DiseaseCode.HmDiseaseCode.put("Leukemia", "92");
        DiseaseCode.HmDiseaseCode.put("Lymphoma", "93");
        DiseaseCode.HmDiseaseCode.put("Brain cancer", "94");
        DiseaseCode.HmDiseaseCode.put("Wilm's tumour", "95");
        DiseaseCode.HmDiseaseCode.put("Neuroblastoma", "96");
        DiseaseCode.HmDiseaseCode.put("Other cancer", "97");
        DiseaseCode.HmDiseaseCode.put("Rheumatic heart disease", "98");
        DiseaseCode.HmDiseaseCode.put("Ischemic heart disease", "99");
        DiseaseCode.HmDiseaseCode.put("Other heart disease", "100");
        DiseaseCode.HmDiseaseCode.put("Stroke", "101");
        DiseaseCode.HmDiseaseCode.put("Intracranial hemorrhage", "102");
        DiseaseCode.HmDiseaseCode.put("Myocarditis", "103");
        DiseaseCode.HmDiseaseCode.put("Other cardiomyopathy", "104");
        DiseaseCode.HmDiseaseCode.put("Endocarditis", "105");
        DiseaseCode.HmDiseaseCode.put("Asthma", "106");
        DiseaseCode.HmDiseaseCode.put("Chronic respiratory disease", "107");
        DiseaseCode.HmDiseaseCode.put("Interstitial lung disease", "108");
        DiseaseCode.HmDiseaseCode.put("Chronic liver disease", "109");
        DiseaseCode.HmDiseaseCode.put("Peptic ulcer disease", "110");
        DiseaseCode.HmDiseaseCode.put("Gastritis", "111");
        DiseaseCode.HmDiseaseCode.put("Appendicitis", "112");
        DiseaseCode.HmDiseaseCode.put("Hernia", "113");
        DiseaseCode.HmDiseaseCode.put("Intussusception", "114");
        DiseaseCode.HmDiseaseCode.put("Volvulus", "115");
        DiseaseCode.HmDiseaseCode.put("Bowel obstruction", "116");
        DiseaseCode.HmDiseaseCode.put("Inflammatory bowel disease", "117");
        DiseaseCode.HmDiseaseCode.put("Intestinal ileus", "118");
        DiseaseCode.HmDiseaseCode.put("Pancreatitis", "119");
        DiseaseCode.HmDiseaseCode.put("Epilepsy", "120");
        DiseaseCode.HmDiseaseCode.put("Migraine", "121");
        DiseaseCode.HmDiseaseCode.put("Diabetes mellitus", "122");
        DiseaseCode.HmDiseaseCode.put("Diabetes mellitus type 1", "123");
        DiseaseCode.HmDiseaseCode.put("Diabetes mellitus type 2", "124");
        DiseaseCode.HmDiseaseCode.put("Acute glomerulonephritis", "125");
        DiseaseCode.HmDiseaseCode.put("Skin and other subcutanous disease", "126");
        DiseaseCode.HmDiseaseCode.put("Cellulitis", "127");
        DiseaseCode.HmDiseaseCode.put("Atopic dermatitis (eczema)", "128");
        DiseaseCode.HmDiseaseCode.put("Other dermatitis", "129");
        DiseaseCode.HmDiseaseCode.put("Viral skin disease", "130");
        DiseaseCode.HmDiseaseCode.put("Fungal skin disease", "131");
        DiseaseCode.HmDiseaseCode.put("Pyoderma", "132");
        DiseaseCode.HmDiseaseCode.put("Urticaria", "133");
        DiseaseCode.HmDiseaseCode.put("Scabies", "134");
        DiseaseCode.HmDiseaseCode.put("Rheumatoid arthritis", "135");
        DiseaseCode.HmDiseaseCode.put("Congenital birth defects", "136");
        DiseaseCode.HmDiseaseCode.put("Myelomeningocele", "137");
        DiseaseCode.HmDiseaseCode.put("Cleft lip, cleft palate", "138");
        DiseaseCode.HmDiseaseCode.put("Down syndrome", "139");
        DiseaseCode.HmDiseaseCode.put("Urinary tract infections (UTI)", "140");
        DiseaseCode.HmDiseaseCode.put("Polycystic ovarian syndrome", "141");
        DiseaseCode.HmDiseaseCode.put("Endometriosis", "142");
        DiseaseCode.HmDiseaseCode.put("Genital prolapse", "143");
        DiseaseCode.HmDiseaseCode.put("Thalassemias", "144");
        DiseaseCode.HmDiseaseCode.put("Sickle cell disorders", "145");
        DiseaseCode.HmDiseaseCode.put("G6PD deficiency", "146");
        DiseaseCode.HmDiseaseCode.put("Hemolytic anemia", "147");
        DiseaseCode.HmDiseaseCode.put("Transport injuries", "148");
        DiseaseCode.HmDiseaseCode.put("Pedestrian road injuries", "149");
        DiseaseCode.HmDiseaseCode.put("Cyclist road injuries", "150");
        DiseaseCode.HmDiseaseCode.put("Motorcyclist road injuries", "151");
        DiseaseCode.HmDiseaseCode.put("Motor vehicle road injuries", "152");
        DiseaseCode.HmDiseaseCode.put("Other road injuries", "153");
        DiseaseCode.HmDiseaseCode.put("Other transport injuries", "154");
        DiseaseCode.HmDiseaseCode.put("Falls", "155");
        DiseaseCode.HmDiseaseCode.put("Drowning", "156");
        DiseaseCode.HmDiseaseCode.put("Burns", "157");
        DiseaseCode.HmDiseaseCode.put("Carbon monoxide poisoning", "158");
        DiseaseCode.HmDiseaseCode.put("Other poisoning", "159");
        DiseaseCode.HmDiseaseCode.put("Unintentional firearm injuries", "160");
        DiseaseCode.HmDiseaseCode.put("Snakebite", "161");
        DiseaseCode.HmDiseaseCode.put("Foreign body in airway", "162");
        DiseaseCode.HmDiseaseCode.put("Foreign body in other body part", "163");
        DiseaseCode.HmDiseaseCode.put("Self-harm (suicide attempt)", "164");
    }

    public void onSyncServer() {

        startActivity(new Intent(this, SyncActivity.class));
//        ConnectivityManager connMgr = (ConnectivityManager)
//                getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//
//            Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();
//            new SyncAllData(
//                    this,
//                    "Forms",
//                    "updateSyncedForms",
//                    FormsContract.class,
//                    MainApp._HOST_URL + FormsContract.FormsTable._URL,
//                    db.getUnsyncedForms()
//            ).execute();
//            Toast.makeText(getApplicationContext(), "Syncing Family Members", Toast.LENGTH_SHORT).show();
//            new SyncAllData(
//                    this,
//                    "Family Members",
//                    "updateSyncedFamilyMembers",
//                    FormsContract.class,
//                    MainApp._HOST_URL + FamilyMembersContract.familyMembers._URL,
//                    db.getUnsyncedFamilyMember()
//            ).execute();
//        } else {
//            Toast.makeText(this, "Internet is not available", Toast.LENGTH_SHORT).show();
//        }
    }

    public void openForm(int type) {

        Class cc;

        if (type == 1) {
            cc = CRFAActivity.class;
        } else if (type == 2) {
            cc = CRFBActivity.class;
        } else {
            cc = CRFCActivity.class;
        }
        if (sharedPref.getString("tagName", null) != "" && sharedPref.getString("tagName", null) != null) {
            startActivity(new Intent(MainActivity.this, cc).putExtra(MainApp.formType, selectFormType(type)));
        } else {
            Util.showTagDialog(this);
        }
    }

    public  void Desing()
    {


        Intent intt =new Intent(this,DesingActivity.class);
        startActivity(intt);

    }


    private String selectFormType(int type) {
        switch (type) {
            case 1:
                return "f1";
            case 2:
                return "f2";
            case 3:
                return "f3";
            default:
                return "";
        }
    }


    public void openDatabaseManager() {
        startActivity(new Intent(this, AndroidDatabaseManager.class));
    }
}
