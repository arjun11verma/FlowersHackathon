package com.example.particlephoton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

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
    private boolean ifShot[];
    private int temp;
    private ParticleCloudSDK particle;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        particle.init(this);
        ifShot = new boolean[]{true, false, false, false};

        Async.executeAsync(particle.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {

            @Override
            public Object callApi(ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                particleCloud.logIn("tsa0662@gmail.com", "something");
                childDevice = particleCloud.getDevice("38005f000e504b464d323520");
                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                ifShot[0] = false;
            }

            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });

        changeView(new Login(this));
    }

    public void shooter() throws ParticleCloudException, IOException, ParticleDevice.FunctionDoesNotExistException {
        if(ifShot[0] == false) {
            Async.executeAsync(childDevice, new Async.ApiWork<ParticleDevice, Integer>() {
                @Override
                public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                    try {
                        temp = childDevice.callFunction("Danger");
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
            ifShot[0] = true;
        }
    }

    public void fire() throws ParticleCloudException, IOException, ParticleDevice.FunctionDoesNotExistException {
        if(ifShot[1] == false) {
            childDevice.callFunction("fire");
            ifShot[1] = true;
        }
    }

    public void medical() throws ParticleCloudException, IOException, ParticleDevice.FunctionDoesNotExistException {
        if(ifShot[2] == false) {
            childDevice.callFunction("medical");
            ifShot[2] = true;
        }
    }

    public void general() throws ParticleCloudException, IOException, ParticleDevice.FunctionDoesNotExistException {
        if (ifShot[3] == false) {
            childDevice.callFunction("general", Collections.singletonList("1"));
            ifShot[3] = true;
        }
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