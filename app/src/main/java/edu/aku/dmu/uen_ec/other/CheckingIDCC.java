package edu.aku.dmu.uen_ec.other;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.aku.dmu.uen_ec.core.DatabaseHelper;

public final class CheckingIDCC {

    private static final String TAG = CheckingIDCC.class.getName();
    private static final String DirectoryName = Environment.getExternalStorageDirectory() + File.separator + DatabaseHelper.PROJECT_NAME;

    public static String accessingFile(Context mContext, String tagID, boolean increment) {
        try {
            String fName = "DONTDELETE";

            if (!creatingFile(mContext, fName)) return "";

            String fileName = DirectoryName
                    + File.separator
                    + fName;
            StringBuilder lineBuffer = new StringBuilder();
            File idFile = new File(fileName);
            FileInputStream fis = new FileInputStream(idFile);
            byte[] byteArray = new byte[(int) idFile.length()];
            fis.read(byteArray);
            String data = new String(byteArray);
            String[] stringArray = data.split("\n");

            if (stringArray.length == 0) {
                lineBuffer.append(tagID).append("-501\n");
                writeInFile(idFile, lineBuffer.toString());
            } else {
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        lineBuffer.append(line).append("\n");
                    }
                }
            }

            return incrementInFile(idFile, lineBuffer.toString(), increment);

        } catch (IOException e) {
            e.printStackTrace();

            return "";
        }
    }

    private static boolean creatingFile(Context mContext, String fName) {

        File folder = new File(DirectoryName);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {

            File idFile = new File(folder, fName);
            if (!idFile.exists()) {
                try {
                    writeInFile(idFile, "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return true;

        } else {
            Toast.makeText(mContext, "Can't create folder!!", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private static void writeInFile(File file, String line) throws IOException {
        FileWriter writer = new FileWriter(file);

        writer.write(line);

        writer.flush();
        writer.close();
    }

    private static String incrementInFile(File idFile, String lines, boolean flag) {
        String tarLine, combStr = "";
        StringBuilder lineBuffer = new StringBuilder();

        try {
            String[] indLines = lines.split("\n");

            for (int i = 0; i < indLines.length; i++) {
                tarLine = indLines[i];

                String[] idLength = tarLine.split("-");
                String id = idLength[idLength.length - 1];

                int lastCont = flag ? Integer.valueOf(id) + 1 : Integer.valueOf(id);

                String subStr = tarLine.substring(0, (tarLine.length() - idLength[idLength.length - 1].length()));
                combStr = subStr + lastCont;

                lineBuffer.append(combStr).append("\n");

            }

            writeInFile(idFile, lineBuffer.toString());

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return combStr;
    }


}
