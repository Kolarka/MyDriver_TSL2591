package hr.unipu.mydriver_tsl2591;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay;
import com.google.android.things.contrib.driver.ht16k33.Ht16k33;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.I2cDevice;
import com.google.android.things.pio.PeripheralManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.jvm.internal.Intrinsics;



public class TSL2591SensorDriver extends Activity {


    Configuration con;
    Long lux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sensordriver);
        DatabaseReference dataBase;
        dataBase = FirebaseDatabase.getInstance().getReference("Luminosity");
        try {
            I2cDevice device = PeripheralManager.getInstance().openI2cDevice("I2C1", 0x29);
            Intrinsics.checkExpressionValueIsNotNull(device, "device");
            TSL2591 sensor = new TSL2591(device);
            sensor.powerOn();
            sensor.setTime(TSL2591.TSL2591_INTEGRATIONTIME_400MS);
            sensor.setGain(TSL2591.TSL2591_GAIN_HIGH);

            Context context = getApplicationContext();
            Observable<Float> o = sensor.getLux();
            Observer observer = new Observer() {
                @SuppressLint("ShowToast")
                @Override
                public void onSubscribe(Disposable d) {
                    Toast.makeText(context, "Subscribed", Toast.LENGTH_LONG);
                }

                @SuppressLint("ShowToast")
                @Override
                public void onNext(Object o) {
                    try {
                        Float f = (float) o;
                        //Toast.makeText(context,  String.valueOf(f), Toast.LENGTH_LONG ).show();
                        Log.i("Next", String.valueOf(f));
                        Configuration con = new Configuration(f);
                        dataBase.setValue(con);
                        Toast.makeText(context, "Jacina dodana", Toast.LENGTH_LONG).show();


                        sensor.powerOff();
                        sensor.close();

                    } catch (Exception e) {
                    }
                }


                @SuppressLint("ShowToast")
                @Override
                public void onError(Throwable e) {
                    Toast.makeText(context, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.i("MainActivity", "Error");
                }

                @SuppressLint("ShowToast")
                @Override
                public void onComplete() {
                    Toast.makeText(context, "Observable completed", Toast.LENGTH_LONG).show();
                    Log.i("MainActivity", " ");
                }
            };
            o.subscribe(observer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
        DatabaseReference hotelRef = reff.child("lux");
        reff = FirebaseDatabase.getInstance().getReference("Luminosity");
        reff.addValueEventListener(new ValueEventListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    lux = ds.getValue(Long.class);
                    try {
                        AlphanumericDisplay segment = RainbowHat.openDisplay();
                        segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
                        segment.display(lux);
                        segment.setEnabled(true);
                        segment.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Fall");
            }
        });
    }
}

