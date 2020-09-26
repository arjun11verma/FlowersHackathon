package com.example.particlephoton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParticleCloudSDK.init(this);

        try {
            ParticleCloudSDK.getCloud().logIn("averma332@gatech.edu", "Timeline123#");
        } catch (ParticleCloudException e) {
            e.printStackTrace();
        }

        try {
            childDevice = ParticleCloudSDK.getCloud().getDevice("38005f000e504b464d323520");
        } catch (ParticleCloudException e) {
            e.printStackTrace();
        }

        changeView(new Login(this));
    }

    public int getButtonNumber() throws ParticleCloudException, ParticleDevice.VariableDoesNotExistException, IOException {
        return childDevice.getIntVariable("button");
    }

    public void sendOnTheWay() throws ParticleCloudException, IOException, ParticleDevice.FunctionDoesNotExistException {
        childDevice.callFunction("setFlashingLight");
    }

    public void sendCheckUp() throws ParticleCloudException, IOException, ParticleDevice.FunctionDoesNotExistException {
        childDevice.callFunction("setSolidLight");
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