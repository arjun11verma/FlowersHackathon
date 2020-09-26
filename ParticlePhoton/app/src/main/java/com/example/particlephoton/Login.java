package com.example.particlephoton;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Login extends BaseView {
    private EditText enterUsername;
    private EditText enterPassword;
    private boolean isValid = false;

    public Login(Context context) {
        super(context);
        activity.setContentView(R.layout.activity_main);

        Button login = null;
        login.setOnClickListener( onClick -> {
            String username = enterUsername.getText().toString();
            String password = enterPassword.getText().toString();

            DatabaseReference ref = activity.getDatabase().getReference("Users");

            if(username.isEmpty()) {
                enterUsername.setError("Please enter a username!");
            }

            if(password.isEmpty()) {
                enterPassword.setError("Please enter a password!");
            }

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot user: snapshot.getChildren()) {
                        if(((User)user.getValue()).getUsername().equals(username))
                        {
                            if(((User)user.getValue()).getPassword().equals(password))
                            {
                                activity.setThisUser((User)user.getValue());
                                activity.changeView(new ParentInterface(context));
                                isValid = true;
                            }
                        }
                    }
                    if(!isValid) {
                        enterUsername.setError("Please enter a username!");
                        enterPassword.setError("Please enter a password!");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }
}
