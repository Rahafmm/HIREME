package com.example.hp.hireme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class viewapplication extends AppCompatActivity {
private ListView lisapp;
    private FirebaseAuth firebaseAuth;
    String User_id;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseReference1;
    List<application> listapp;
    String nameOrg;
    //Intent intent = getIntent();
   // final String name = intent.getExtras().getString("name", "");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewapplication);
        lisapp=(ListView)findViewById(R.id.lisapp);
        firebaseAuth = FirebaseAuth.getInstance();
        User_id = firebaseAuth.getCurrentUser().getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Org");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Org o = postSnapshot.getValue(Org.class);
                    if(o.getuid().toUpperCase().equals(User_id.toUpperCase())){
                        nameOrg=o.getname();}
                }
                Toast.makeText(viewapplication.this, nameOrg, Toast.LENGTH_LONG).show();
               /* String[] uploads = new String[listapp.size()];

                for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = listapp.get(i).getAppname();


                }

                //disp laying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                lisapp.setAdapter(adapter);*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
