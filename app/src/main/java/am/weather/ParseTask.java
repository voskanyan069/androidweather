package am.weather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// JSON FROM URL
public class ParseTask extends AsyncTask<Void, Void, String> {
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJSON = "";

    @Override
    protected String doInBackground(Void ... params) {
        try {
            String url_json = Resources.urlApi;
            URL url = new URL(url_json);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJSON = buffer.toString();
            Log.d("FOR_LOG", resultJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJSON;
    }

    //        FIND RATE FOR THIS VALUTE AND ADD TO LIST
    protected void onPostExecute(String strJSON) {
        super.onPostExecute(strJSON);

        try {
            JSONObject json = new JSONObject(strJSON);

            JSONArray jsonArray_list = json.getJSONArray("list");
            Resources.len = jsonArray_list.length();
            for (int i = 0; i < Resources.len; ++i) {
                JSONObject jsonArray_index = jsonArray_list.getJSONObject(i);

                JSONObject json_main = jsonArray_index.getJSONObject("main");
                JSONArray json_weather = jsonArray_index.getJSONArray("weather");
                JSONObject json_weather_main = json_weather.getJSONObject(0);
                JSONObject json_wind = jsonArray_index.getJSONObject("wind");

                Resources.temp[i] = json_main.getDouble("temp");
                Resources.feelLike[i] = json_main.getDouble("feels_like");
                Resources.tempMin[i] = json_main.getDouble("temp_min");
                Resources.tempMax[i] = json_main.getDouble("temp_max");
                Resources.mode[i] = json_weather_main.getString("main");
                Resources.modeDesc[i] = json_weather_main.getString("description");
                Resources.windSpeed[i] = json_wind.getDouble("speed");
                Resources.windDeg[i] = json_wind.getInt("deg");
                Resources.dateTime[i] = jsonArray_index.getString("dt_txt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
