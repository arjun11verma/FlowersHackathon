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
    private boolean isValidUser = false;
    private boolean isValidPassword = false;

    public Login(Context context) {
        super(context);
        activity.setContentView(R.layout.activity_main);

        enterUsername = activity.findViewById(R.id.enterUsername);
        enterPassword = activity.findViewById(R.id.enterPassword);

        Button login = activity.findViewById(R.id.loginButton);
        login.setOnClickListener( onClick -> {
            String username = enterUsername.getText().toString();
            String password = enterPassword.getText().toString();
            isValidUser = false;
            isValidPassword = false;

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
                        if((user.child("username").getValue().equals(username)))
                        {
                            isValidUser = true;
                            if((user.child("password").getValue().equals(password)))
                            {
                                isValidPassword = true;
                                activity.setThisUser(new User(username, password));
                                activity.changeView(new TeacherPortal(activity));
                            }
                        }
                    }
                    if(!isValidUser) {
                        enterUsername.setError("Please enter a valid username!");
                    }
                    if(!isValidPassword) {
                        enterPassword.setError("Please enter a valid password!");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        Button createAccount = activity.findViewById(R.id.createAccountButton);
        createAccount.setOnClickListener(onClick -> {
            activity.changeView(new CreateAccount(activity));
        });
    }
}
