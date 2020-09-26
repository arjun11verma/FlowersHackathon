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

public class CreateAccount extends BaseView {
    private EditText createUsername;
    private EditText createPassword;

    public CreateAccount(Context context) {
        super(context);

        Button createAccount = null;
        createAccount.setOnClickListener( onClick -> {
            String username = createUsername.getText().toString();
            String password = createPassword.getText().toString();

            DatabaseReference ref = activity.getDatabase().getReference("Users");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot user: snapshot.getChildren()) {
                        if(((User)user.getValue()).getUsername().equals(username))
                        {
                            createUsername.setError("This username has already been used!");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            if(username.isEmpty()) {
                createUsername.setError("Please enter a username!");
            }

            if(password.isEmpty()) {
                createPassword.setError("Please enter a password!");
            }

            ref.setValue(new User(username, password));
        });
    }
}
