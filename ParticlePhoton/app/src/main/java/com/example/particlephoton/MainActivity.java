package com.example.particlephoton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.Collections;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.utils.Async;

public class MainActivity extends AppCompatActivity {
    //Firebase Account: Cheese Jenkins, cheesejenkins123@gmail.com, Pudding123 is the password
    private Database database = new Database();
    private ParticleDevice childDevice;
    private User thisUser;
    private BaseView thisView;
    private int temp = 1;
    private ParticleCloudSDK particle;
    private String deviceID;
    private MainActivity activity = this;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        particle.init(this);

        Async.executeAsync(particle.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                particleCloud.logIn("tsa0662@gmail.com", "something");
                childDevice = particleCloud.getDevice("38005f000e504b464d323520");
                return -1;
            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });

        deviceID = getIMEI();

        database.getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean temper = true;
                for(DataSnapshot child: snapshot.getChildren()) {
                    if(((String)child.child("deviceID").getValue()).equals(deviceID)) {
                        if((boolean)(child.child("in")).getValue()) {
                            changeView(new TeacherPortal(activity));
                            temper = false;
                            break;
                        }
                    }
                }
                if(temper) { changeView(new Login(activity)); }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getIMEI() {
        String IMEI = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return IMEI;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void shooter() throws ParticleCloudException, IOException, ParticleDevice.FunctionDoesNotExistException {
        Async.executeAsync(childDevice, new Async.ApiWork<ParticleDevice, Integer>() {
            @Override
            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                try {
                    temp = childDevice.callFunction("DangerOn");
                    temp = childDevice.callFunction("NormalOff");
                    database.getReference("state", "shot").setValue(true);
                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                    e.printStackTrace();
                }
                return temp;
            }
            @Override
            public void onSuccess(Integer integer) {

            }
            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });
    }

    public void shooterOff() throws ParticleCloudException {
        Async.executeAsync(childDevice, new Async.ApiWork<ParticleDevice, Integer>() {
            @Override
            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                try {
                    temp = childDevice.callFunction("DangerOff");
                    database.getReference("state", "shot").setValue(false);
                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                    e.printStackTrace();
                }
                return temp;
            }
            @Override
            public void onSuccess(Integer integer) {

            }
            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });
    }

    public void caution() throws ParticleCloudException {
        Async.executeAsync(childDevice, new Async.ApiWork<ParticleDevice, Integer>() {
            @Override
            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                try {
                    temp = childDevice.callFunction("CautionOn");
                    temp = childDevice.callFunction("NormalOff");
                    database.getReference("state", "caution").setValue(true);
                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                    e.printStackTrace();
                }
                return temp;
            }
            @Override
            public void onSuccess(Integer integer) {

            }
            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });
    }

    public void cautionOff() throws ParticleCloudException {
        Async.executeAsync(childDevice, new Async.ApiWork<ParticleDevice, Integer>() {
            @Override
            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                try {
                    temp = childDevice.callFunction("CautionOff");
                    database.getReference("state", "caution").setValue(false);
                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                    e.printStackTrace();
                }
                return temp;
            }
            @Override
            public void onSuccess(Integer integer) {

            }
            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });
    }

    public void medical() throws ParticleCloudException {
        Async.executeAsync(childDevice, new Async.ApiWork<ParticleDevice, Integer>() {
            @Override
            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                try {
                    temp = childDevice.callFunction("MedicalOn");
                    temp = childDevice.callFunction("NormalOff");
                    database.getReference("state", "medical").setValue(true);
                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                    e.printStackTrace();
                }
                return temp;
            }
            @Override
            public void onSuccess(Integer integer) {

            }
            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });
    }

    public void medicalOff() throws ParticleCloudException {
        Async.executeAsync(childDevice, new Async.ApiWork<ParticleDevice, Integer>() {
            @Override
            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                try {
                    temp = childDevice.callFunction("MedicalOff");
                    database.getReference("state", "medical").setValue(false);
                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                    e.printStackTrace();
                }
                return temp;
            }
            @Override
            public void onSuccess(Integer integer) {

            }
            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });
    }

    public void normal() throws ParticleCloudException {
        database.getReference("state").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean temper = true;
                for(DataSnapshot child: snapshot.getChildren()) {
                    if(((boolean)child.getValue())) {
                        temper = false;
                        break;
                    }
                }
                if(temper) {
                    try {
                        Async.executeAsync(childDevice, new Async.ApiWork<ParticleDevice, Integer>() {
                            @Override
                            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                                try {
                                    temp = childDevice.callFunction("NormalOn");
                                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                                    e.printStackTrace();
                                }
                                return temp;
                            }
                            @Override
                            public void onSuccess(Integer integer) {

                            }
                            @Override
                            public void onFailure(ParticleCloudException exception) {

                            }
                        });
                    } catch (ParticleCloudException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Database getDatabase() {
        return database;
    }

    public void changeView(BaseView view) {
        thisView = view;
    }

    public User getThisUser() {
        return thisUser;
    }

    public void setThisUser(User user) {
        thisUser = user;
    }
}