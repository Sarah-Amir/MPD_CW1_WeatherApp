package com.lab2.amir_sarah_s2340285;

//
// Name                 Sarah Amir
// Student ID           S2340285
// Programme of Study   BSc Computing
//

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerLocationSelector;
    private GlasgowFragment glasgowFragment;
    private LondonFragment londonFragment;
    private NewyorkFragment newyorkFragment;
    private OrlandoFragment orlandoFragment;
    private MississaugaFragment mississaugaFragment;
    private AmsterdamFragment amsterdamFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check for Internet Connection
        if (checkConnection()){
            Toast.makeText(getApplicationContext(),"Connected to Internet",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Not Connected to Internet",Toast.LENGTH_SHORT).show();
        }

        //load the list of locations defined in array.xml into an ArrayAdapter
        ArrayAdapter adapter;
        adapter=ArrayAdapter.createFromResource(this,R.array.locations, android.R.layout.simple_spinner_dropdown_item);
        spinnerLocationSelector=(Spinner) findViewById(R.id.spinnerSelectLocation);
        spinnerLocationSelector.setAdapter(adapter);
        spinnerLocationSelector.setOnItemSelectedListener(this);

    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null)
            return false;
        if (networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            //connection is a wifi connection
            Toast.makeText(this,"WIFI connection",Toast.LENGTH_SHORT).show();
        }
        if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            //connection is a mobile network connection
            Toast.makeText(this,"Mobile Data connection",Toast.LENGTH_SHORT).show();
        }
        return networkInfo.isConnected();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent==spinnerLocationSelector){
            String text=(String) spinnerLocationSelector.getSelectedItem();
            if(text.equals("GCU Glasgow")){
                //Show glasgow fragment
                glasgowFragment=new GlasgowFragment();
                //Replace fragmentContainerView in activity_main.xml with glasgowFragment
                FragmentManager glasgowManager=getSupportFragmentManager();
                FragmentTransaction glasgowTransaction=glasgowManager.beginTransaction();
                glasgowTransaction.replace(R.id.fragmentContainerView,glasgowFragment);
                glasgowTransaction.commit();

            }else if (text.equals("GCU London")){
                //Show london fragment
                londonFragment=new LondonFragment();
                //Replace fragmentContainerView in activity_main.xml with londonFragment
                FragmentManager londonManager=getSupportFragmentManager();
                FragmentTransaction londonTransaction=londonManager.beginTransaction();
                londonTransaction.replace(R.id.fragmentContainerView,londonFragment);
                londonTransaction.commit();
            }else if (text.equals("GCNYC New York")){
                //Show newyork fragment
                newyorkFragment=new NewyorkFragment();
                //Replace fragmentContainerView in activity_main.xml with newyorkFragment
                FragmentManager newyorkManager=getSupportFragmentManager();
                FragmentTransaction newyorkTransaction=newyorkManager.beginTransaction();
                newyorkTransaction.replace(R.id.fragmentContainerView,newyorkFragment);
                newyorkTransaction.commit();
            } else if (text.equals("Orlando")) {
                //Show orlando fragment
                orlandoFragment=new OrlandoFragment();
                //Replace fragmentContainerView in activity_main.xml with newyorkFragment
                FragmentManager orlandoManager=getSupportFragmentManager();
                FragmentTransaction orlandoTransaction=orlandoManager.beginTransaction();
                orlandoTransaction.replace(R.id.fragmentContainerView,orlandoFragment);
                orlandoTransaction.commit();

            } else if (text.equals("Mississauga")) {
                //Show mississauga fragment
                mississaugaFragment=new MississaugaFragment();
                //Replace fragmentContainerView in activity_main.xml with mississaugaFragment
                FragmentManager mississaugaManager=getSupportFragmentManager();
                FragmentTransaction mississaugaTransaction=mississaugaManager.beginTransaction();
                mississaugaTransaction.replace(R.id.fragmentContainerView,mississaugaFragment);
                mississaugaTransaction.commit();
            } else if (text.equals("Amsterdam")) {
                //Show amsterdam fragment
                amsterdamFragment=new AmsterdamFragment();
                //Replace fragmentContainerView in activity_main.xml with mississaugaFragment
                FragmentManager amsterdamManager=getSupportFragmentManager();
                FragmentTransaction amsterdamTransaction=amsterdamManager.beginTransaction();
                amsterdamTransaction.replace(R.id.fragmentContainerView,amsterdamFragment);
                amsterdamTransaction.commit();
            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}