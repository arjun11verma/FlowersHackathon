package com.example.particlephoton;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;

public class TeacherPortal extends BaseView {
    public TeacherPortal(Context context) {
        super(context);
        activity.setContentView(R.layout.teacher_portal);

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
                activity.getDatabase().getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot child: snapshot.getChildren()) {
                            if(((String)child.child("deviceID").getValue()).equals(activity.getDeviceID())) {
                                activity.getDatabase().getReference("Users").child((String)child.child("username").getValue()).child("in").setValue(false);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                activity.changeView(new Login(activity));
            }
        });
    }
}