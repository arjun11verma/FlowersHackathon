package com.example.particlephoton;

import android.content.Context;
import android.view.View;

public class BaseView extends View {
    protected MainActivity activity;

    public BaseView(Context context) {
        super(context);
        activity = (MainActivity)context;
    }
}
