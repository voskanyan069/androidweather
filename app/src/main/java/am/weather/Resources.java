package am.weather;

import android.text.Editable;

public class Resources {
    static Editable city;
    static double[] temp = new double[40];
    static double[] feelLike = new double[40];
    static double[] tempMin = new double[40];
    static double[] tempMax = new double[40];
    static String[] mode = new String[40];
    static String[] modeDesc = new String[40];
    static double[] windSpeed = new double[40];
    static int[] windDeg = new int[40];
    static String[] dateTime = new String[40];
    static int len;

    //    final static String urlApi = "https://api.openweathermap.org/data/2.5/weather?q=" + "erevan" + "&appid=" + api;

    final static String api = "c47002378b7f12e3192c162d8ee06280";
    static String urlApi;
}
