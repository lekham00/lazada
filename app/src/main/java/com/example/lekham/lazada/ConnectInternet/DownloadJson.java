package com.example.lekham.lazada.ConnectInternet;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Le Kham on 5/28/2017.
 */

public class DownloadJson extends AsyncTask<String, Void, String> {
    String mPath;
    List<HashMap<String, String>> mAttr;
    StringBuilder stringBuilderData;

    public DownloadJson(String path) {
        mPath = path;
    }

    public DownloadJson(String path, List<HashMap<String, String>> attr) {
        mPath = path;
        mAttr = attr;
    }

    @Override
    protected String doInBackground(String... params) {
        String data = "";
        try {
            URL url = new URL(mPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            data = methodGet(httpURLConnection);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    private String methodGet(HttpURLConnection httpURLConnection) {
        String data = "";
        try {
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            stringBuilderData = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilderData.append(line);
            }
            data = stringBuilderData.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

//    private String methodPost(HttpURLConnection httpURLConnection) {
//        String data = "";
//        try {
//            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setDoInput(true);
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
}
