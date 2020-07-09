package am.weather;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class WeatherActivity extends AppCompatActivity {

    private TextView cityName;
    private ListView listOfElem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new ParseTask().execute();
        (new Handler()).postDelayed(this::afterLoading, 3333);
    }

    public void init() {
        cityName = findViewById(R.id.city_name);
        listOfElem = findViewById(R.id.listOfElem);

        cityName.setText(Resources.city.toString().toUpperCase());
    }

    public void afterLoading() {
        setContentView(R.layout.activity_weather);
        init();
        onPostExecute();
    }

    protected void onPostExecute() {
        String[] from = {"name_item"};
        int[] to = {R.id.name_item};

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
        try {
            HashMap<String, String> hashMapWeather = new HashMap<String, String>();

            for (int i = 0; i < Resources.len; ++i) {
                hashing(hashMapWeather, Resources.temp[i], Resources.feelLike[i], Resources.tempMin[i],
                        Resources.tempMax[i], Resources.mode[i], Resources.modeDesc[i],
                        Resources.windSpeed[i], Resources.windDeg[i], Resources.dateTime[i], arrayList);
            }

            final SimpleAdapter adapter = new SimpleAdapter(WeatherActivity.this, arrayList, R.layout.list_item, from, to);
            listOfElem.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hashing(HashMap<String, String> hashMap, double celsius, double feelsLike,
                        double min, double max, String mode, String modeDes, double windSpeed,
                        int windDeg, String date, ArrayList<HashMap<String, String>> arrayList) {
        hashMap = new HashMap<String, String>();
        hashMap.put("name_item", date + "\n" + "Celsius:  " + celsius + "\n" + "Feels like:  " + feelsLike + "\n" +
                    "Max:  " + max + "\n" + "Min:  " + min + "\n" + "Mode:  " + mode + "\n" + "Mode Description:  " + modeDes + "\n" +
                    "Wind Speed:  " + windSpeed + "\n" + "Wind Deg:  " + windDeg);
        arrayList.add(hashMap);
    }
}
