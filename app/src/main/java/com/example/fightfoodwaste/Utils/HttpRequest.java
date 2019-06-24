package com.example.fightfoodwaste.Utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

class HttpRequest extends AsyncTask<String, Void, String> {

    private static final int URL = 0;
    private static final int METHOD = 1;
    private static final int BODY = 2;
    private static final String CHARSET = "UTF-8";
    private final WeakReference<HttpClient.Listeners> callback;
    private int responseCode = 0;

    public HttpRequest(HttpClient.Listeners callback){
        this.callback = new WeakReference<>(callback);
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection;

        Log.d("HTTP", params[URL]);
        Log.d("HTTP", params[METHOD]);
        Log.d("HTTP", params[BODY]);
        try {
            URL url = new URL(params[URL]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod(params[METHOD]);
            urlConnection.setRequestProperty("Accept-Charset", CHARSET);
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=" + CHARSET);

            if(!params[BODY].equals("")) {
                urlConnection.setDoOutput(true);

                try (OutputStream output = urlConnection.getOutputStream()) {
                    output.write(params[BODY].getBytes());
                }
            }

            this.setResponseCode(urlConnection.getResponseCode());

            if(this.responseCode < 300) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);
            }

            return Integer.toString(this.getResponseCode());
        }
        catch (SocketTimeoutException e) {
            Log.e("HTTP", "Timeout");
            e.printStackTrace();
        }
        catch (MalformedURLException e){
            Log.e("HTTP", "Malformed URL");
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.e("HTTP", "IOException");
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        this.callback.get().onPostExecute(result);
    }

    private static String readStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}