package com.example.valera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.Locale;


public class Main2Activity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private SelectCountryFragment selectCountryFragment;
    private SavedCountriesFragment savedCountriesFragment;
    private CountryInfoFragment countryInfoFragment;
    private AuthorFragment authorFragment;
    private Context ctx;
    private FusedLocationProviderClient mFusedLocationClient;
    public static String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String logintext = getIntent().getExtras().getString("login");
        setTitle(logintext);

        selectCountryFragment = new SelectCountryFragment();
        savedCountriesFragment = new SavedCountriesFragment();
        countryInfoFragment = new CountryInfoFragment();
        authorFragment = new AuthorFragment();

        ctx = this;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(ctx);

        if (ActivityCompat.checkSelfPermission(ctx,
                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new
                                String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
            else {
                getLocation();
            }
        }
        getLocation();

        if (savedInstanceState == null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, countryInfoFragment)
                    .commit();
            setTitle(R.string.f_count_inf);
        }

        drawerLayout = findViewById(R.id.my_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();

                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                        switch (menuItem.getItemId()){
                            case R.id.select_country:
                                fragmentTransaction.replace(R.id.content, selectCountryFragment);
                                setTitle(R.string.f_select_country);
                                break;
                            case R.id.saved_countries:
                                fragmentTransaction.replace(R.id.content, savedCountriesFragment);
                                setTitle(R.string.f_saved_countries);
                                break;
                            case R.id.country_info:
                                fragmentTransaction.replace(R.id.content, countryInfoFragment);
                                setTitle(R.string.f_count_inf);
                                break;
                            case R.id.author_info:
                                fragmentTransaction.replace(R.id.content, authorFragment);
                                setTitle(R.string.f_auth);
                                break;
                        }

                        fragmentTransaction.addToBackStack(null).commit();

                        return true;

                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[]
                                                   grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            Log.i("Location permission", "Access denied!");
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new
                        OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    Geocoder geocoder = new Geocoder(ctx,
                                            Locale.getDefault());
                                    try {
                                        Address address =
                                                geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
                                        city = address.getLocality();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
    }

}
