package afpa.learning.moneyconverter;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Afpa on 08/02/2017.
 */

public class Data extends AsyncTask<String, Void, Map<String, Double>> {

    private final String link = "http://api.fixer.io/latest";

    @Override
    protected Map<String, Double> doInBackground(String... params) {
        Map<String, Double> monies = new HashMap<>();
        try {
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "MoneyConverter-v0.1");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                System.out.println("OK");
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                java.util.Scanner scn = new java.util.Scanner(in).useDelimiter("\\A");
                if (scn.hasNext()) {
                    String s = scn.next();
                    JSONObject json = new JSONObject(s);
                    monies.put(json.getString("base"), 1d);
                    JSONObject rates = json.getJSONObject("rates");
                    Iterator<String> keys = rates.keys();
                    while (keys.hasNext()) {
                        String name = keys.next();
                        double rate = rates.getDouble(name);
                        monies.put(name, rate);
                    }
                }
                in.close();
            } else {
                System.out.println("PAS OK");
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monies;
    }
}
