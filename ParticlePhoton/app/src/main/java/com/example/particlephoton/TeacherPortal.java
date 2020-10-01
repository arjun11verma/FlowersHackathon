package com.example.particlephoton;

import android.content.Context;
import android.view.View;
import android.widget.Button;

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
                activity.stopShooter();
            } catch (ParticleCloudException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParticleDevice.FunctionDoesNotExistException e) {
                e.printStackTrace();
            }
        });
    }
}