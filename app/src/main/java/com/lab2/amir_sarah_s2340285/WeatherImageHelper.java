package com.lab2.amir_sarah_s2340285;

//
// Name                 Sarah Amir
// Student ID           S2340285
// Programme of Study   BSc Computing
//

import android.content.Context;
import android.graphics.drawable.Drawable;

public class WeatherImageHelper {

    public static Drawable getWeatherImage(Context context, String weatherCondition) {
        switch (weatherCondition.toLowerCase()) {
            case "sunny":
                return context.getDrawable(R.drawable.day_clear);
            case "clear":
                return context.getDrawable(R.drawable.day_clear);
            case "sunny intervals":
                return context.getDrawable(R.drawable.day_partial_cloud);
            case "cloudy":
                return context.getDrawable(R.drawable.cloudy);
            case "light cloud":
                return context.getDrawable(R.drawable.day_partial_cloud);
            case "partial cloud":
                return context.getDrawable(R.drawable.day_partial_cloud);
            case "partly cloudy":
                return context.getDrawable(R.drawable.day_partial_cloud);
            case "light rain":
                return context.getDrawable(R.drawable.day_rain);
            case "light rain showers":
                return context.getDrawable(R.drawable.day_rain);
            case "rainy":
                return context.getDrawable(R.drawable.rain);
            case "heavy rain":
                return context.getDrawable(R.drawable.rain);
            case "rain thunder":
                return context.getDrawable(R.drawable.day_rain_thunder);
            case "thunder":
                return context.getDrawable(R.drawable.thunder);
            case "sleet":
                return context.getDrawable(R.drawable.day_sleet);
            case "snow thunder":
                return context.getDrawable(R.drawable.day_snow_thunder);
            case "snow":
                return context.getDrawable(R.drawable.day_snow);
            case "snowy":
                return context.getDrawable(R.drawable.snow);
            case "fog":
                return context.getDrawable(R.drawable.fog);
            case "mist":
                return context.getDrawable(R.drawable.mist);
            case "overcast":
                return context.getDrawable(R.drawable.overcast);
            case "drizzle":
                return context.getDrawable(R.drawable.drizzle);
            default:
                return context.getDrawable(R.drawable.day_clear); // Default image
        }
    }
}
