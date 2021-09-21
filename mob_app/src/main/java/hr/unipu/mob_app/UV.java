package hr.unipu.mob_app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONObject;

import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class UV extends Fragment {


    final String APP_ID = "24211101488e0e32f5fc5689bdef0f2c";
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;

    String Location_Provider = LocationManager.GPS_PROVIDER;

    private TextView NameofCity;
    private TextView weatherState;
    private TextView temperature;
    private ImageView weatherIcon;

    LocationManager mLocationManager;
    LocationListener mLocationListener;


    Long lux;

    public UV() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_uv, container, false);


        weatherState = (TextView) v.findViewById(R.id.weathercondition);
        temperature = (TextView) v.findViewById(R.id.temperature);
        weatherIcon = (ImageView) v.findViewById(R.id.weathericon);
        NameofCity = (TextView) v.findViewById(R.id.city_name);


        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
        DatabaseReference hotelRef = reff.child("lux");


        reff = FirebaseDatabase.getInstance().getReference("Luminosity");
        reff.addValueEventListener(new ValueEventListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    lux = ds.getValue(Long.class);
                    Integer rezultat = getArguments().getInt("Option");


                    TextView UV = (TextView) v.findViewById(R.id.uv_txt);
                    TextView add = (TextView) v.findViewById(R.id.id_naocale);
                    TextView spf = (TextView) v.findViewById(R.id.zastit_fakt);
                    Button back = (Button) v.findViewById(R.id.back_button);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Fragment fra = new Main();
                            FragmentManager fma = getFragmentManager();
                            FragmentTransaction fta = fma.beginTransaction();
                            Bundle args = new Bundle();
                            fra.setArguments(args);
                            fta.replace(R.id.FrameContainer, fra);
                            fta.commit();
                        }
                    });
                    if ((lux >= 0 && lux <= 50) && rezultat == 1) {
                        UV.setText(" " + 1);
                        add.setText("Naočale");
                        spf.setText("30 SPF");

                    }
                    if ((lux >= 0 && lux <= 50) && rezultat == 2) {
                        UV.setText(" " + 1);
                        add.setText("Naočale");
                        spf.setText("15 SPF");

                    }
                    if ((lux >= 0 && lux <= 50) && rezultat == 3) {
                        UV.setText(" " + 1);
                        add.setText("Naočale");
                        spf.setText("15 SPF");

                    }
                    if ((lux >= 0 && lux <= 50) && rezultat == 4) {
                        UV.setText(" " + 1);
                        add.setText("Naočale");
                        spf.setText("8-14 SPF");

                    }
                    if ((lux >= 0 && lux <= 50) && rezultat == 5) {
                        UV.setText("" + 1);
                        add.setText("Naočale");
                        spf.setText("8-14 SPF");

                    }


                    if ((lux >= 51 && lux <= 100) && rezultat == 1) {
                        UV.setText(" " + 2);
                        add.setText("Naočale i šešir");
                        spf.setText("30 SPF");

                    }
                    if ((lux >= 51 && lux <= 100) && rezultat == 2) {
                        UV.setText(" " + 2);
                        add.setText("Naočale i šešir");
                        spf.setText("30 SPF");

                    }
                    if ((lux >= 51 && lux <= 100) && rezultat == 3) {
                        UV.setText(" " + 2);
                        add.setText("Naočale i šešir");
                        spf.setText("30 SPF");

                    }
                    if ((lux >= 51 && lux <= 100) && rezultat == 4) {
                        UV.setText(" " + 2);
                        add.setText("Naočale i šešir");
                        spf.setText("15 SPF");

                    }
                    if ((lux >= 51 && lux <= 100) && rezultat == 5) {
                        UV.setText(" " + 2);
                        add.setText("Naočale i šešir");
                        spf.setText("8-14 SPF");

                    }

                    if ((lux >= 101 && lux <= 150) && rezultat == 1) {
                        UV.setText(" " + 3);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("50+ SPF");

                    }
                    if ((lux >= 101 && lux <= 150) && rezultat == 2) {
                        UV.setText(" " + 3);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("50+ SPF");

                    }
                    if ((lux >= 101 && lux <= 150) && rezultat == 3) {
                        UV.setText(" " + 3);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("30 SPF");

                    }
                    if ((lux >= 101 && lux <= 150) && rezultat == 4) {
                        UV.setText(" " + 3);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("15 SPF");

                    }
                    if ((lux >= 101 && lux <= 150) && rezultat == 5) {
                        UV.setText(" " + 3);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("15 SPF");

                    }

                    if ((lux >= 151 && lux <= 200) && rezultat == 1) {
                        UV.setText(" " + 4);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("50-100 SPF");

                    }
                    if ((lux >= 151 && lux <= 200) && rezultat == 2) {
                        UV.setText(" " + 4);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("50+ SPF");

                    }

                    if ((lux >= 151 && lux <= 200) && rezultat == 3) {
                        UV.setText(" " + 4);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("30 SPF");

                    }
                    if ((lux >= 151 && lux <= 200) && rezultat == 4) {
                        UV.setText(" " + 4);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("30 SPF");

                    }
                    if ((lux >= 151 && lux <= 200) && rezultat == 5) {
                        UV.setText(" " + 4);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("15 SPF");

                    }

                    if ((lux >= 201 && lux <= 250) && (rezultat == 1 || rezultat == 2 || rezultat == 3)) {
                        UV.setText(" " + 5);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("50-100 SPF");

                    }
                    if ((lux >= 251 && lux <= 300) && rezultat == 4) {
                        UV.setText(" " + 6);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("50+ SPF");

                    }

                    if ((lux >= 251 && lux <= 300) && rezultat == 5) {
                        UV.setText(" " + 7);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("30 SPF");

                    }

                    if (lux >= 301 && lux <= 350) {
                        UV.setText(" " + 8);
                        add.setText("Naočale, šešir, držati se sjene");
                        spf.setText("50-100 SPF");

                    }
                    if (lux >= 351) {
                        UV.setText(" " + 9);
                        add.setText("Naočale, šešir, držati se sjene, izbjegavati izlaziti između 10:00 i 16:00 sati");
                        spf.setText("100 SPF");

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Fall");
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getWeatherForCurrentLocation();
    }

    private void getWeatherForCurrentLocation() {
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String latitude = String.valueOf(location.getLatitude());
                String longitude = String.valueOf(location.getLongitude());

                RequestParams params = new RequestParams();
                params.put("lat", latitude);
                params.put("lon", longitude);
                params.put("appid", APP_ID);
                connecting(params);
            }
        };

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);

            return;
        }
            mLocationManager.requestLocationUpdates(Location_Provider, MIN_TIME, MIN_DISTANCE, mLocationListener);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
             //   Toast.makeText(UV.this, "Location got successfully", Toast.LENGTH_SHORT).show();
                getWeatherForCurrentLocation();
            }
            else {
                //user denied the permission
            }

        }
    }
    private void connecting(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            //    Toast.makeText(UV.this, "Data got successfully", Toast.LENGTH_SHORT).show();

                WeatherData weatherData = WeatherData.fromJson(response);
                updateUI(weatherData);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });

    }

    private void updateUI(WeatherData weatherData) {


        temperature.setText(weatherData.getTemperature());
        NameofCity.setText(weatherData.getCity());
        weatherState.setText(weatherData.getWeatherType());
        int resourceID=getResources().getIdentifier(weatherData.getIcon(),"drawable", getActivity().getPackageName());
        weatherIcon.setImageResource(resourceID);


    }

    @Override
    public void onPause() {
        super.onPause();
        if(mLocationManager!=null)
        {
            mLocationManager.removeUpdates(mLocationListener);
        }
    }



}
