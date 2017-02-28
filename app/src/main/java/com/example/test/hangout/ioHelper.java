package com.example.test.hangout;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by pilespin on 2/6/17.
 */

public class ioHelper {

    public static void writeToFile(Context context, String filename, String content) {

        context.deleteFile(filename);

        FileOutputStream outputStream;
        File file = new File(context.getFilesDir(), filename);

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
//            Log.d("------ BASE ------ : ", "File not write");
//            e.printStackTrace();
        }
    }

    public static String readFile(Context context, String filename) {

        File f = new File(context.getFilesDir(), filename);
        int length = (int) f.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(bytes);
            in.close();
        } catch (Exception e) {
//            Log.d("------ BASE ------ : ", "File not found");
//            e.printStackTrace();
        }
        String content = new String(bytes);
        return (content);
    }
}
