package com.example.particlephoton;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;

public class PrincipalView extends BaseView {
    public PrincipalView(Context context) {
        super(context);

        Button shooterBtn = activity.findViewById(R.id.shooterBtn);
        shooterBtn.setOnClickListener(onClick -> {
            try {
                activity.shooter();
            } catch (ParticleCloudException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParticleDevice.FunctionDoesNotExistException e) {
                e.printStackTrace();
            }
        });

        activity.findViewById(R.id.cautionBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.caution();
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }
            }
        });

        activity.findViewById(R.id.medicalBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.medical();
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }
            }
        });

        activity.findViewById(R.id.logout).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        activity.findViewById(R.id.shooterBtnOff).setOnClickListener(onClick -> {
            try {
                activity.shooterOff();
            } catch (ParticleCloudException e) {
                e.printStackTrace();
            }
        });

        activity.findViewById(R.id.cautionBtnOff).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.cautionOff();
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }
            }
        });

        activity.findViewById(R.id.medicalBtnOff).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.medicalOff();
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
