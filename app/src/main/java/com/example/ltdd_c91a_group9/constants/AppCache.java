package com.example.ltdd_c91a_group9.constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ltdd_c91a_group9.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AppCache {

    public static boolean checkFileExists(Context context) {
        try {
            InputStream inputStream = context.openFileInput("cache.txt");
            if (inputStream != null) {
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
           // Log.e("Exception", "File not found: " + e.toString());
            return false;
        }
    }

    public static void createFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("cache.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public static boolean deleteFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("cache.txt", Context.MODE_PRIVATE);
            File f = new File(System.getProperty("user.dir"), "cache.txt");
            f.delete();
            fos.close();
            return true;
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            return false;
        }
    }
    public static void updateFile(Context context , String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("cache.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFile(Context context) {
        String result = "";
        try {
            InputStream inputStream = context.openFileInput("cache.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                result = stringBuilder.toString();
            } else {
                return result;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return result;
    }

}
