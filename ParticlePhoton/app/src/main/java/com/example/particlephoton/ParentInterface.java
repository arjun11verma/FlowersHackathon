package com.example.particlephoton;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;

public class ParentInterface extends BaseView {
    private TextView status;

    public ParentInterface(Context context) {
        super(context);

        Button checkStatus = null;
        checkStatus.setOnClickListener( onClick -> {
            try {
                changeStatus(activity.getButtonNumber());
            } catch (ParticleCloudException e) {
                e.printStackTrace();
            } catch (ParticleDevice.VariableDoesNotExistException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button sendCheck = null;
        sendCheck.setOnClickListener( onClick -> {
            try {
                activity.sendCheckUp();
            } catch (ParticleCloudException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParticleDevice.FunctionDoesNotExistException e) {
                e.printStackTrace();
            }
        });

        Button sendOnWay = null;
        sendOnWay.setOnClickListener( onClick -> {
            try {
                activity.sendOnTheWay();
            } catch (ParticleCloudException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParticleDevice.FunctionDoesNotExistException e) {
                e.printStackTrace();
            }
        });
    }

    private void changeStatus(int n) {
        if(n == 0) {
            status.setText("No Status");
        }
        else if(n == 1) {
            status.setText("I'm okay! Don't worry about me!");
        }
        else if(n == 2) {
            status.setText("I'm not so good, I don't feel comfortable.");
        }
        else if(n == 3) {
            status.setText("Please help me, I'm in danger!");
        }
    }
}
