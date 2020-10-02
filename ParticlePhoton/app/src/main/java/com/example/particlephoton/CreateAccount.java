//Yoooo
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
    private EditText uniqueID;
    private String thisID = "ARJUNISLAZY";
    private String principalID = "ARJUNISCOOL";
    private boolean checkValid;

    public CreateAccount(Context context) {
        super(context);
        activity.setContentView(R.layout.signup);
        createUsername = activity.findViewById(R.id.createUsername);
        createPassword = activity.findViewById(R.id.createPassword);
        uniqueID = activity.findViewById(R.id.unique);

        Button createAccount = activity.findViewById(R.id.createAccount);
        createAccount.setOnClickListener( onClick -> {
            String username = createUsername.getText().toString();
            String password = createPassword.getText().toString();
            String id = uniqueID.getText().toString();
            checkValid = true;

            if(id.isEmpty() && !(thisID.equals(id))) {
                if(!principalID.equals(id)) {
                    uniqueID.setError("This is not a valid ID!");
                    checkValid = false;
                }
            }

            if(username.isEmpty()) {
                createUsername.setError("Please enter a username!");
                checkValid = false;
            }

            if(password.isEmpty()) {
                createPassword.setError("Please enter a password!");
                checkValid = false;
            }

            DatabaseReference ref = activity.getDatabase().getReference("Users");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot user: snapshot.getChildren()) {
                        if(user.child("username").getValue().equals(username))
                        {
                            createUsername.setError("Please enter a valid username!");
                            checkValid = false;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            if(checkValid) {
                User newUser = new User(username, password, activity.getDeviceID(), true);
                if(principalID.equals(id)) {
                    newUser.setPrincipal();
                }
                ref.child(username).setValue(newUser);
                activity.changeView(new Login(activity));
            }
        });
    }
}
