package com.example.particlephoton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {
    private FirebaseDatabase database;

    public Database() {
        database = FirebaseDatabase.getInstance();
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public DatabaseReference getReference(String... nodes) {
        DatabaseReference temp = database.getReference();
        for(String node: nodes)
        {
            temp = temp.child(node);
        }
        return temp;
    }

    public DatabaseReference getReference() {
        return database.getReference();
    }

    public void setValue(Object val, String... nodes)
    {
        DatabaseReference ref = getReference(nodes);
        ref.setValue(val);
    }
}
