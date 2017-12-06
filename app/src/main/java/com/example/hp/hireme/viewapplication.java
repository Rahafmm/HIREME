package com.example.hp.hireme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewapplication extends AppCompatActivity {
 ListView lisapp;
    private FirebaseAuth firebaseAuth;
    String User_id;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseReference1;
    //List<application> app;
    ArrayList<application> names;
    String nameOrg;
    String S;
    String [] arr;
    int i=0;
    //Intent intent = getIntent();
   // final String name = intent.getExtras().getString("name", "");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewapplication);
        lisapp=(ListView)findViewById(R.id.lisapp);
        firebaseAuth = FirebaseAuth.getInstance();
        arr=new String[100];
        names=new ArrayList<>();
        lisapp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //getting the upload
                final application upload = names.get(i);

                //Opening the upload file in browser using the upload url
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(upload.getUrl()));
                startActivity(intent);


            }
        });
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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseReference1= FirebaseDatabase.getInstance().getReference().child("application");
        mDatabaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    application o = postSnapshot.getValue(application.class);
                    if(o.getNameOrg().toUpperCase().equals(nameOrg.toUpperCase()))
                        names.add(o);
                       // Toast.makeText(viewapplication.this, S, Toast.LENGTH_SHORT).show();
                }

                String[] uploads = new String[names.size()];

                for (int i = 0; i < uploads.length; i++) {

                    uploads[i] = names.get(i).getAppname();

                }

                //disp laying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
              lisapp.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
