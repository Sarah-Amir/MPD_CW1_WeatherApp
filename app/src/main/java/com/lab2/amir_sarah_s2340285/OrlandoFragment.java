package com.lab2.amir_sarah_s2340285;

//
// Name                 Sarah Amir
// Student ID           S2340285
// Programme of Study   BSc Computing
//

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrlandoFragment extends Fragment implements OnMapReadyCallback {


    private String result;
    private String urlSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/4167147"; // 3-day Forecast
    private String urlSource1 = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/4167147"; //Current Forecast
    private String location="Orlando";

    //Initialise UserInterface ids
    //Current Forecast ids
    // 'c' stands for 'current' forecast
    private TextView cityName, cDay, cWeather,
            cTemperature, cMinimumTemperature,
            cMaximumTemperature, cDate, cTime, cfirstDaySunrise,
            cfirstDaySunset, cWindSpeed, cPressure,
            cHumidity, cVisibility;

    //Three Day Forecast ids
    // 'f' stands for 'first' day forecast
    private TextView fDay, fDayWeather, fDayMinTemperature, fDayMaxTemperature,
            fDaySunrise, fDaySunset, fDayWindSpeed, fDayPressure, fDayHumidity,
            fDayVisibility, fDayUVRisk, fDayPollution;

    // 's' stands for 'second' day forecast
    private TextView sDay, sDayWeather, sDayMinTemperature, sDayMaxTemperature,
            sDaySunrise, sDaySunset, sDayWindSpeed, sDayPressure, sDayHumidity,
            sDayVisibility, sDayUVRisk, sDayPollution;

    // 't' stands for 'third' day forecast
    private TextView tDay, tDayWeather, tDayMinTemperature, tDayMaxTemperature,
            tDaySunrise, tDaySunset, tDayWindSpeed, tDayPressure, tDayHumidity,
            tDayVisibility, tDayUVRisk, tDayPollution;
    private Layout layoutcolor;
    private ImageView cimgWeatherCondition;

    //Initialise detailed view for first, second and third days forecasts card views and layouts
    private CardView firstDayForecastCardView;
    private LinearLayout firstDayForecastDetails;
    private ImageView downArrowfirstDay;

    private CardView secondDayForecastCardView;
    private LinearLayout secondDayForecastDetails;
    private ImageView downArrowSecondDay;

    private CardView thirdDayForecastCardView;
    private LinearLayout thirdDayForecastDetails;
    private ImageView downArrowThirdDay;

    //initialise Google Map, view and associated variables "Latitude" and "Longitude"
    private MapView mMapView;
    private GoogleMap mMap;
    private Double latitude = 0.0;
    private Double longitude = 0.0;

    List<ForecastItem> listItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_orlando, container, false);

        // Set up the links to the graphical components
        cityName = (TextView) v.findViewById(R.id.cityName);
        //Current Weather Widgets
        cDay = (TextView) v.findViewById(R.id.currentDay);
        cWeather = (TextView) v.findViewById(R.id.currentWeather);
        cTemperature = (TextView) v.findViewById(R.id.currentTemperature);
        cMinimumTemperature = (TextView) v.findViewById(R.id.currentMinimumTemperature);
        cMaximumTemperature = (TextView) v.findViewById(R.id.currentMaximumTemperature);
        cDate = (TextView) v.findViewById(R.id.cDate);
        cTime = (TextView) v.findViewById(R.id.cTime);
        cfirstDaySunrise = (TextView) v.findViewById(R.id.currentDaySunrise);
        cfirstDaySunset = (TextView) v.findViewById(R.id.currentDaySunset);
        cWindSpeed = (TextView) v.findViewById(R.id.currentWindSpeed);
        cPressure = (TextView) v.findViewById(R.id.currentPressure);
        cHumidity = (TextView) v.findViewById(R.id.currentHumidity);
        cVisibility = (TextView) v.findViewById(R.id.currentVisibility);
        cimgWeatherCondition = (ImageView) v.findViewById(R.id.imgCurrentWeather);
        //First Day Widgets
        fDay = (TextView) v.findViewById(R.id.firstDay);
        fDayWeather = (TextView) v.findViewById(R.id.firstDayWeather);
        fDayMinTemperature = (TextView) v.findViewById(R.id.firstDayMinTemperature);
        fDayMaxTemperature = (TextView) v.findViewById(R.id.firstDayMaxTemperature);
        fDaySunrise = (TextView) v.findViewById(R.id.firstDaySunrise);
        fDaySunset = (TextView) v.findViewById(R.id.firstDaySunset);
        fDayWindSpeed = (TextView) v.findViewById(R.id.firstDayWindSpeed);
        fDayPressure = (TextView) v.findViewById(R.id.firstDayPressure);
        fDayHumidity = (TextView) v.findViewById(R.id.firstDayHumidity);
        fDayVisibility = (TextView) v.findViewById(R.id.firstDayVisibility);
        fDayUVRisk = (TextView) v.findViewById(R.id.firstDayUVRisk);
        fDayPollution = (TextView) v.findViewById(R.id.firstDayPollution);
        //Second Day Widgets
        sDay = (TextView) v.findViewById(R.id.secondDay);
        sDayWeather = (TextView) v.findViewById(R.id.secondDayWeather);
        sDayMinTemperature = (TextView) v.findViewById(R.id.secondDayMinTemperature);
        sDayMaxTemperature = (TextView) v.findViewById(R.id.secondDayMaxTemperature);
        sDaySunrise = (TextView) v.findViewById(R.id.secondDaySunrise);
        sDaySunset = (TextView) v.findViewById(R.id.secondDaySunset);
        sDayWindSpeed = (TextView) v.findViewById(R.id.secondDayWindSpeed);
        sDayPressure = (TextView) v.findViewById(R.id.secondDayPressure);
        sDayHumidity = (TextView) v.findViewById(R.id.secondDayHumidity);
        sDayVisibility = (TextView) v.findViewById(R.id.secondDayVisibility);
        sDayUVRisk = (TextView) v.findViewById(R.id.secondDayUVRisk);
        sDayPollution = (TextView) v.findViewById(R.id.secondDayPollution);
        //Third Day Widgets
        tDay = (TextView) v.findViewById(R.id.thirdDay);
        tDayWeather = (TextView) v.findViewById(R.id.thirdDayWeather);
        tDayMinTemperature = (TextView) v.findViewById(R.id.thirdDayMinTemperature);
        tDayMaxTemperature = (TextView) v.findViewById(R.id.thirdDayMaxTemperature);
        tDaySunrise = (TextView) v.findViewById(R.id.thirdDaySunrise);
        tDaySunset = (TextView) v.findViewById(R.id.thirdDaySunset);
        tDayWindSpeed = (TextView) v.findViewById(R.id.thirdDayWindSpeed);
        tDayPressure = (TextView) v.findViewById(R.id.thirdDayPressure);
        tDayHumidity = (TextView) v.findViewById(R.id.thirdDayHumidity);
        tDayVisibility = (TextView) v.findViewById(R.id.thirdDayVisibility);
        tDayUVRisk = (TextView) v.findViewById(R.id.thirdDayUVRisk);
        tDayPollution = (TextView) v.findViewById(R.id.thirdDayPollution);
        //Widgets for separate 3 Day Forecast
        firstDayForecastCardView = (CardView) v.findViewById(R.id.firstDayForecast);
        firstDayForecastDetails = (LinearLayout) v.findViewById(R.id.firstDayForecastDetails);
        downArrowfirstDay = (ImageView) v.findViewById(R.id.downArrowFirstDay);
        // Set OnClickListener on the CardView
        firstDayForecastCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of detailed information
                if (firstDayForecastDetails.getVisibility() == View.VISIBLE) {
                    firstDayForecastDetails.setVisibility(View.GONE);
                    downArrowfirstDay.setVisibility(View.VISIBLE);
                } else {
                    firstDayForecastDetails.setVisibility(View.VISIBLE);
                    downArrowfirstDay.setVisibility(View.GONE);
                }
            }
        });

        secondDayForecastCardView = (CardView) v.findViewById(R.id.secondDayForecast);
        secondDayForecastDetails = (LinearLayout) v.findViewById(R.id.secondDayForecastDetails);
        downArrowSecondDay = (ImageView) v.findViewById(R.id.downArrowSecondDay);
        // Set OnClickListener on the CardView
        secondDayForecastCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of detailed information
                if (secondDayForecastDetails.getVisibility() == View.VISIBLE) {
                    secondDayForecastDetails.setVisibility(View.GONE);
                    downArrowSecondDay.setVisibility(View.VISIBLE);
                } else {
                    secondDayForecastDetails.setVisibility(View.VISIBLE);
                    downArrowSecondDay.setVisibility(View.GONE);
                }
            }
        });

        thirdDayForecastCardView = (CardView) v.findViewById(R.id.thirdDayForecast);
        thirdDayForecastDetails = (LinearLayout) v.findViewById(R.id.thirdDayForecastDetails);
        downArrowThirdDay = (ImageView) v.findViewById(R.id.downArrowThirdDay);
        // Set OnClickListener on the CardView
        thirdDayForecastCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of detailed information
                if (thirdDayForecastDetails.getVisibility() == View.VISIBLE) {
                    thirdDayForecastDetails.setVisibility(View.GONE);
                    downArrowThirdDay.setVisibility(View.VISIBLE);
                } else {
                    thirdDayForecastDetails.setVisibility(View.VISIBLE);
                    downArrowThirdDay.setVisibility(View.GONE);
                }
            }
        });
        //Define Widgets for Google Map
        mMapView = v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        startProgress();

        return v;
    }

    // Method to receive latitude and longitude from outside the activity

    public void startProgress() {
        // Run network access on a separate thread;
        //new Thread(new Task(urlSource)).start();
        // Approach 1:
        Thread thread1 = new Thread(new OrlandoFragment.Task(urlSource));
        Thread thread2 = new Thread(new OrlandoFragment.Task(urlSource1));
        thread1.start(); // Start the thread
        try {
            thread1.join(); // Wait for thread1 to finish
            System.out.println("Thread 1 has finished execution.");
            System.out.println(listItems.toString());
            System.out.println("List size after thread1:" + listItems.size());

            thread2.start();
            thread2.join(); // Wait for thread2 to finish
            System.out.println("Thread 2 has finished execution.");
            System.out.println(listItems.toString());
            System.out.println("List size after thread2:" + listItems.size());

            //listDataDisplay.setText(listItems.toString()); //After show list on screen

            //Extract Data for three day forecast

            //First Day
            String firstDay = String.valueOf(listItems.get(0));
            System.out.println("First Day");
            //Removing "Title: " from firstDay
            firstDay = firstDay.substring(firstDay.indexOf(":") + 2);
            //Remove "Description: "
            firstDay = firstDay.replace("Description: ", "");
            System.out.println(firstDay);
            // Split the data into key-value pairs
            Map<String, String> keyValuePairs = new HashMap<>();
            String[] pairs = firstDay.split(", ");
            for (String pair : pairs) {
                String[] keyValue = pair.split(": ", 2);
                if (keyValue.length == 2) {
                    keyValuePairs.put(keyValue[0], keyValue[1]);
                }
            }
            //Print all the values
            for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }


            //Extract required data from KeyValuePairs for the First Day
            String first_Day = null;
            String firstDayWeather = null;
            String firstDayMinTemperature = null;
            String firstDayMaxTemperature = null;
            String firstDaySunrise = null;
            String firstDaySunset = null;
            String firstDayVisibility = null;
            String firstDayPressure = null;
            String firstDayWindSpeed = null;
            String firstDayHumidity = null;
            String firstDayUVRisk = null;
            String firstDayPollution = null;

            for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {
                if ("Today".equals(entry.getKey()) || "Tonight".equals(entry.getKey())) {
                    first_Day = entry.getKey();
                    firstDayWeather = entry.getValue();
                    System.out.println(first_Day + " Weather = " + firstDayWeather);
                }
                if ("Minimum Temperature".equals(entry.getKey())) {
                    firstDayMinTemperature = entry.getValue();
                    //Extract Celsius Temperature
                    firstDayMinTemperature = firstDayMinTemperature.substring(0, firstDayMinTemperature.indexOf("(") - 1);
                    System.out.println("Minimum Temperature = " + firstDayMinTemperature);
                }

                if ("Maximum Temperature".equals(entry.getKey())) {
                    firstDayMaxTemperature = entry.getValue();
                    //Extract Celsius Temperature
                    firstDayMaxTemperature = firstDayMaxTemperature.substring(0, firstDayMaxTemperature.indexOf("(") - 1);
                    System.out.println("Maximum Temperature = " + firstDayMaxTemperature);
                }

                if ("Sunrise".equals(entry.getKey())) {
                    firstDaySunrise = entry.getValue();
                    System.out.println("Sunrise = " + firstDaySunrise);
                }

                if ("Sunset".equals(entry.getKey())) {
                    firstDaySunset = entry.getValue();
                    System.out.println("Sunset = " + firstDaySunset);
                }
                if ("Pressure".equals(entry.getKey())) {
                    firstDayPressure = entry.getValue();
                    System.out.println("Pressure = " + firstDayPressure);
                }
                if ("Wind Speed".equals(entry.getKey())) {
                    firstDayWindSpeed = entry.getValue();
                    System.out.println("Wind Speed = " + firstDayWindSpeed);
                }
                if ("Humidity".equals(entry.getKey())) {
                    firstDayHumidity = entry.getValue();
                    System.out.println("Humidity = " + firstDayHumidity);
                }
                if ("UV Risk".equals(entry.getKey())) {
                    firstDayUVRisk = entry.getValue();
                    System.out.println("UV Risk = " + firstDayUVRisk);
                }
                if ("Pollution".equals(entry.getKey())) {
                    firstDayPollution = entry.getValue();
                    System.out.println("Pollution = " + firstDayPollution);
                }
                if ("Visibility".equals(entry.getKey())) {
                    firstDayVisibility = entry.getValue();
                    System.out.println("Visibility = " + firstDayVisibility);
                }
                if ("GeoRSS Point".equals(entry.getKey())) {
                    String georssPoint = entry.getValue();
                    //Extract Latitude and Longitude
                    String[] parts = georssPoint.split(" ");

                    String lat = parts[0];
                    String lng = parts[1];

                    //pass the values of Latitude and longitude for the Google Maps
                    latitude = Double.parseDouble(lat);
                    longitude = Double.parseDouble(lng);

                    System.out.println("Latitude: " + lat);
                    System.out.println("Longitude: " + lng);
                }
            }

            //Second Day
            String secondDay = String.valueOf(listItems.get(1));
            System.out.println("Second Day");
            System.out.println(secondDay);
            //Removing "Title: " from secondDay
            secondDay = secondDay.substring(secondDay.indexOf(":") + 2);
            //Remove "Description: "
            secondDay = secondDay.replace("Description: ", "");
            System.out.println(secondDay);
            // Split the data into key-value pairs
            Map<String, String> keyValuePairs2 = new HashMap<>();
            String[] pairs2 = secondDay.split(", ");
            for (String pair : pairs2) {
                String[] keyValue = pair.split(": ", 2);
                if (keyValue.length == 2) {
                    keyValuePairs2.put(keyValue[0], keyValue[1]);
                }
            }
            //Extract required data from KeyValuePairs2 for the Second Day
            String second_Day = null;
            String secondDayWeather = null;
            String secondDayMinTemperature = null;
            String secondDayMaxTemperature = null;
            String secondDaySunrise = null;
            String secondDaySunset = null;
            String secondDayVisibility = null;
            String secondDayPressure = null;
            String secondDayWindSpeed = null;
            String secondDayHumidity = null;
            String secondDayUVRisk = null;
            String secondDayPollution = null;
            for (Map.Entry<String, String> entry : keyValuePairs2.entrySet()) {
                if ("Monday".equals(entry.getKey()) || "Tuesday".equals(entry.getKey()) ||
                        "Wednesday".equals(entry.getKey()) || "Thursday".equals(entry.getKey()) || "Friday".equals(entry.getKey()) || "Saturday".equals(entry.getKey()) || "Sunday".equals(entry.getKey())) {
                    second_Day = entry.getKey();
                    secondDayWeather = entry.getValue();
                    System.out.println(second_Day + " Weather = " + secondDayWeather);
                }

                if ("Minimum Temperature".equals(entry.getKey())) {
                    secondDayMinTemperature = entry.getValue();
                    //Extract Celsius Temperature
                    secondDayMinTemperature = secondDayMinTemperature.substring(0, secondDayMinTemperature.indexOf("(") - 1);
                    System.out.println("Minimum Temperature = " + secondDayMinTemperature);
                }

                if ("Maximum Temperature".equals(entry.getKey())) {
                    secondDayMaxTemperature = entry.getValue();
                    //Extract Celsius Temperature
                    secondDayMaxTemperature = secondDayMaxTemperature.substring(0, secondDayMaxTemperature.indexOf("(") - 1);
                    System.out.println("Maximum Temperature = " + secondDayMaxTemperature);
                }

                if ("Sunrise".equals(entry.getKey())) {
                    secondDaySunrise = entry.getValue();
                    System.out.println("Sunrise = " + secondDaySunrise);
                }

                if ("Sunset".equals(entry.getKey())) {
                    secondDaySunset = entry.getValue();
                    System.out.println("Sunset = " + secondDaySunset);
                }
                if ("Pressure".equals(entry.getKey())) {
                    secondDayPressure = entry.getValue();
                    System.out.println("Pressure = " + secondDayPressure);
                }
                if ("Wind Speed".equals(entry.getKey())) {
                    secondDayWindSpeed = entry.getValue();
                    System.out.println("Wind Speed = " + secondDayWindSpeed);
                }
                if ("Humidity".equals(entry.getKey())) {
                    secondDayHumidity = entry.getValue();
                    System.out.println("Humidity = " + secondDayHumidity);
                }
                if ("UV Risk".equals(entry.getKey())) {
                    secondDayUVRisk = entry.getValue();
                    System.out.println("UV Risk = " + secondDayUVRisk);
                }
                if ("Pollution".equals(entry.getKey())) {
                    secondDayPollution = entry.getValue();
                    System.out.println("Pollution = " + secondDayPollution);
                }
                if ("Visibility".equals(entry.getKey())) {
                    secondDayVisibility = entry.getValue();
                    System.out.println("Visibility = " + secondDayVisibility);
                }
            }

            //Third Day
            String thirdDay = String.valueOf(listItems.get(2));
            System.out.println("Third Day");
            System.out.println(thirdDay);
            //Removing "Title: " from thirdDay
            thirdDay = thirdDay.substring(thirdDay.indexOf(":") + 2);
            //Remove "Description: "
            thirdDay = thirdDay.replace("Description: ", "");
            System.out.println(thirdDay);
            // Split the data into key-value pairs
            Map<String, String> keyValuePairs3 = new HashMap<>();
            String[] pairs3 = thirdDay.split(", ");
            for (String pair : pairs3) {
                String[] keyValue = pair.split(": ", 2);
                if (keyValue.length == 2) {
                    keyValuePairs3.put(keyValue[0], keyValue[1]);
                }
            }
            //Extract required data from KeyValuePairs3 for the Third Day
            String third_Day = null;
            String thirdDayWeather = null;
            String thirdDayMinTemperature = null;
            String thirdDayMaxTemperature = null;
            String thirdDaySunrise = null;
            String thirdDaySunset = null;
            String thirdDayVisibility = null;
            String thirdDayPressure = null;
            String thirdDayWindSpeed = null;
            String thirdDayHumidity = null;
            String thirdDayUVRisk = null;
            String thirdDayPollution = null;
            for (Map.Entry<String, String> entry : keyValuePairs3.entrySet()) {
                if ("Monday".equals(entry.getKey()) || "Tuesday".equals(entry.getKey()) ||
                        "Wednesday".equals(entry.getKey()) || "Thursday".equals(entry.getKey()) || "Friday".equals(entry.getKey()) || "Saturday".equals(entry.getKey()) || "Sunday".equals(entry.getKey())) {
                    third_Day = entry.getKey();
                    thirdDayWeather = entry.getValue();
                    System.out.println(third_Day + " Weather = " + thirdDayWeather);
                }

                if ("Minimum Temperature".equals(entry.getKey())) {
                    thirdDayMinTemperature = entry.getValue();
                    //Extract Celsius Temperature
                    thirdDayMinTemperature = thirdDayMinTemperature.substring(0, thirdDayMinTemperature.indexOf("(") - 1);
                    System.out.println("Minimum Temperature = " + thirdDayMinTemperature);
                }

                if ("Maximum Temperature".equals(entry.getKey())) {
                    thirdDayMaxTemperature = entry.getValue();
                    //Extract Celsius Temperature
                    thirdDayMaxTemperature = thirdDayMaxTemperature.substring(0, thirdDayMaxTemperature.indexOf("(") - 1);
                    System.out.println("Maximum Temperature = " + thirdDayMaxTemperature);
                }
                if ("Sunrise".equals(entry.getKey())) {
                    thirdDaySunrise = entry.getValue();
                    System.out.println("Sunrise = " + thirdDaySunrise);
                }

                if ("Sunset".equals(entry.getKey())) {
                    thirdDaySunset = entry.getValue();
                    System.out.println("Sunset = " + thirdDaySunset);
                }
                if ("Pressure".equals(entry.getKey())) {
                    thirdDayPressure = entry.getValue();
                    System.out.println("Pressure = " + thirdDayPressure);
                }
                if ("Wind Speed".equals(entry.getKey())) {
                    thirdDayWindSpeed = entry.getValue();
                    System.out.println("Wind Speed = " + thirdDayWindSpeed);
                }
                if ("Humidity".equals(entry.getKey())) {
                    thirdDayHumidity = entry.getValue();
                    System.out.println("Humidity = " + thirdDayHumidity);
                }
                if ("UV Risk".equals(entry.getKey())) {
                    thirdDayUVRisk = entry.getValue();
                    System.out.println("UV Risk = " + thirdDayUVRisk);
                }
                if ("Pollution".equals(entry.getKey())) {
                    thirdDayPollution = entry.getValue();
                    System.out.println("Pollution = " + thirdDayPollution);
                }
                if ("Visibility".equals(entry.getKey())) {
                    thirdDayVisibility = entry.getValue();
                    System.out.println("Visibility = " + thirdDayVisibility);
                }
            }

            //Data Extraction for three day forecast Ends here

            //Extract Data for latest forecast
            String currentDay = String.valueOf(listItems.get(3));
            System.out.println(currentDay);

            String current_Day = currentDay.substring(currentDay.indexOf(":") + 2, currentDay.indexOf("-") - 1);
            System.out.println("Current Day= " + current_Day);
            String currentTime = currentDay.substring(currentDay.indexOf("-") + 2, currentDay.indexOf("T:") + 1);
            System.out.println("Current Time= " + currentTime);
            String currentWeather = currentDay.substring(currentDay.indexOf("T:") + 3, currentDay.indexOf(","));
            System.out.println("Current Weather= " + currentWeather);
            String currentTemperature = currentDay.substring(currentDay.indexOf("Temperature:") + 13, currentDay.indexOf(", Wind Direction:"));
            //Extract Celsius Temperature
            currentTemperature = currentTemperature.substring(0, currentTemperature.indexOf("(") - 1);
            System.out.println("Current Temperature= " + currentTemperature);
            String currentWindDirection = currentDay.substring(currentDay.indexOf("Wind Direction:") + 16, currentDay.indexOf(", Wind Speed:"));
            System.out.println("Current Wind Direction= " + currentWindDirection);
            String currentWindSpeed = currentDay.substring(currentDay.indexOf("Wind Speed:") + 12, currentDay.indexOf(", Humidity:"));
            System.out.println("Current Wind Speed= " + currentWindSpeed);
            String currentHumidity = currentDay.substring(currentDay.indexOf("Humidity:") + 10, currentDay.indexOf(", Pressure:"));
            System.out.println("Current Humidity= " + currentHumidity);
            String currentPressure = currentDay.substring(currentDay.indexOf("Pressure:") + 10, currentDay.indexOf(", Visibility:"));
            System.out.println("Current Pressure= " + currentPressure);
            String currentVisibility = currentDay.substring(currentDay.indexOf("Visibility:") + 11, currentDay.indexOf(", Link:"));
            System.out.println("Current Visibility= " + currentVisibility);
            String currentLink = currentDay.substring(currentDay.indexOf("Link:") + 6, currentDay.indexOf(", PubDate:"));
            System.out.println("Current Link= " + currentLink);
            String currentPubDate = currentDay.substring(currentDay.indexOf("PubDate:") + 9, currentDay.indexOf(", GeoRSS Point:"));
            System.out.println("Current Pub Date= " + currentPubDate);
            String currentDate = currentDay.substring(currentDay.indexOf("PubDate:") + 9, currentDay.indexOf("PubDate:") + 25);
            System.out.println("Current Date= " + currentDate);

            //Data Extraction for latest forecast Ends here

            //Update Screen for Current Forecast
            cityName.setText(location);
            cDay.setText(current_Day);
            if ("Not available".equals(currentWeather)) {
                currentWeather = firstDayWeather;
            }
            cWeather.setText(currentWeather);


            //Set Weather image as Current Weather condition.
            //Using WeatherImageHelper class
            Drawable weatherImage = WeatherImageHelper.getWeatherImage(getContext(), currentWeather);
            cimgWeatherCondition.setImageDrawable(weatherImage);

            cTemperature.setText(currentTemperature);
            cMinimumTemperature.setText(firstDayMinTemperature);
            if (firstDayMaxTemperature == null) {
                cMaximumTemperature.setText(currentTemperature);
            } else {
                cMaximumTemperature.setText(firstDayMaxTemperature);
            }
            cDate.setText(currentDate);
            cTime.setText(currentTime);
            cfirstDaySunrise.setText(firstDaySunrise);
            cfirstDaySunset.setText(firstDaySunset);
            if (currentWindSpeed.replaceAll("\\s+", "").equals("--mph")) {
                cWindSpeed.setText(firstDayWindSpeed);
            } else {
                cWindSpeed.setText(currentWindSpeed);
            }
            //cWindSpeed.setText(currentWindSpeed);
            if (currentPressure.replaceAll("\\s+", "").equals("--mb,Notavailable")) {
                cPressure.setText(firstDayPressure);
            } else {
                cPressure.setText(currentPressure);
            }
            //cPressure.setText(currentPressure);
            cHumidity.setText(currentHumidity);
            if (currentVisibility.replaceAll("\\s+", "").equals("--")) {
                cVisibility.setText(firstDayVisibility);
            } else {
                cVisibility.setText(currentVisibility);
            }

            //Update Screen for First Day Forecast
            fDay.setText(first_Day);
            fDayWeather.setText(firstDayWeather);
            fDayMinTemperature.setText(firstDayMinTemperature);
            //fDayMaxTemperature.setText(firstDayMaxTemperature);
            if(firstDayMaxTemperature==null){
                fDayMaxTemperature.setText(currentTemperature);
            }else{
                fDayMaxTemperature.setText(firstDayMaxTemperature);
            }
            fDaySunrise.setText(firstDaySunrise);
            fDaySunset.setText(firstDaySunset);
            fDayWindSpeed.setText(firstDayWindSpeed);
            fDayPressure.setText(firstDayPressure);
            fDayHumidity.setText(firstDayHumidity);
            fDayVisibility.setText(firstDayVisibility);
            fDayUVRisk.setText(firstDayUVRisk);
            fDayPollution.setText(firstDayPollution);

            //Update Screen for Second Day Forecast
            sDay.setText(second_Day);
            sDayWeather.setText(secondDayWeather);
            sDayMinTemperature.setText(secondDayMinTemperature);
            sDayMaxTemperature.setText(secondDayMaxTemperature);
            sDaySunrise.setText(secondDaySunrise);
            sDaySunset.setText(secondDaySunset);
            sDayWindSpeed.setText(secondDayWindSpeed);
            sDayPressure.setText(secondDayPressure);
            sDayHumidity.setText(secondDayHumidity);
            sDayVisibility.setText(secondDayVisibility);
            sDayUVRisk.setText(secondDayUVRisk);
            sDayPollution.setText(secondDayPollution);

            //Update Screen for Third Day Forecast
            tDay.setText(third_Day);
            tDayWeather.setText(thirdDayWeather);
            tDayMinTemperature.setText(thirdDayMinTemperature);
            tDayMaxTemperature.setText(thirdDayMaxTemperature);
            tDaySunrise.setText(thirdDaySunrise);
            tDaySunset.setText(thirdDaySunset);
            tDayWindSpeed.setText(thirdDayWindSpeed);
            tDayPressure.setText(thirdDayPressure);
            tDayHumidity.setText(thirdDayHumidity);
            tDayVisibility.setText(thirdDayVisibility);
            tDayUVRisk.setText(thirdDayUVRisk);
            tDayPollution.setText(thirdDayPollution);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //set the zoom level for Google Map
        float zoomLevel=10.0f;
        // Add a marker in current location and move the camera
        LatLng cityLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(cityLocation).title(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLocation,zoomLevel));
    }


    private class Task implements Runnable {
        private String url;

        public Task(String aurl) {
            url = aurl;
        }

        @Override
        public void run() {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";


            Log.e("MyTag", "in run");

            try {
                Log.e("MyTag", "in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                while ((inputLine = in.readLine()) != null) {
                    result = result + inputLine;
                    Log.e("MyTag", inputLine);

                }
                in.close();
            } catch (IOException ae) {
                Log.e("MyTag", "ioexception");
            }

            //Get rid of the first tag <?xml version="1.0" encoding="utf-8"?>
            int i = result.indexOf("?>") + 1;
            result = result.substring(i + 1);
            Log.e("Check result after first line", result);
            //Remove all tags except data within <item> tags and keep rss tag for namespace declaration
            result = result.substring(result.indexOf("<"), result.indexOf(">") + 1) + result.substring(result.indexOf("<item>"));
            Log.e("Check result after second line", result);
            i = result.indexOf("</channel>");
            result = result.substring(0, i) + result.substring(i + 10);
            // Remove the colon from <georss:point> tag
            result = result.replaceAll("<georss:point>", "<georss_point>")
                    .replaceAll("</georss:point>", "</georss_point>");

            Log.e("MyTag - cleaned", result);


            //
            // Cleaned xml data to be parsed
            //
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new StringReader(result));
                ForecastItem aItem = new ForecastItem();
                int eventType = xpp.getEventType();
                int count = 0;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) // Found a start tag
                    {   // Check which start Tag we have as we'd do different things
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            Log.d("MyTag", "New Item found!");
                            aItem = new ForecastItem();
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            aItem.setTitle(temp);

                            Log.d("MyTag", "title is " + temp);
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            aItem.setLink(temp);
                            Log.d("MyTag", "link is " + temp);
                        } else if (xpp.getName().equalsIgnoreCase("description")) {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            aItem.setDescription(temp);
                            Log.d("MyTag", "description is " + temp);
                        } else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            aItem.setPubDate(temp);
                            Log.d("MyTag", "Pub Date is " + temp);
                        } else if (xpp.getName().equalsIgnoreCase("georss_point")) {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            aItem.setGeorssPoint(temp);
                            Log.d("MyTag", "Geo Points " + temp);
                        }
                    } else if (eventType == XmlPullParser.END_TAG) // Found an end tag
                    {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            Log.d("MyTag", "Item parsing completed!");
                            Log.d("aItem", "Item is " + aItem);
                            listItems.add(aItem);
                        }
                    }
                    eventType = xpp.next(); // Get the next event  before looping again

                } // End of while
                Log.d("ListItems", listItems.toString());

            } catch (XmlPullParserException ae1) {
                Log.e("MyTag", "Parsing error " + ae1.toString());
            } catch (IOException ae1) {
                Log.e("MyTag", "IO error during parsing");
            }



        }

    }
}