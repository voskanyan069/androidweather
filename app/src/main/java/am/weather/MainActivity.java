package am.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputCity;
    private Button btnCheck;
    private ImageButton imgBtnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        checkWeather();
    }

    public void init () {
        inputCity = findViewById(R.id.city_input);
        btnCheck = findViewById(R.id.check_weather);
        imgBtnSettings = findViewById(R.id.settings_imgBtn);

        toSettings();
    }

    public void toSettings() {
        imgBtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("am.weather.SettingsActivity"));
            }
        });
    }

    public void checkWeather() {
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources.city = inputCity.getText();
                try {
                    Resources.urlApi = "https://api.openweathermap.org/data/2.5/forecast?q=" + Resources.city.toString() + "&appid=" + Resources.api + "&units=metric";
                } catch (Exception e) {
                    Resources.urlApi = "https://api.openweathermap.org/data/2.5/forecast?q=" + "vanadzor" + "&appid=" + Resources.api + "&units=metric";
                }
                startActivity(new Intent("am.weather.WeatherActivity"));
            }
        });
    }
}