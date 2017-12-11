package com.example.hp.hireme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewrequset extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private String User_id;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseReference1;
    private ListView reject;
    private ListView accecpt;
    private ArrayList<application> all;
    private ArrayList<application> rej;
    private  ArrayList<application> accp;
    private String r="reject";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrequset);
        firebaseAuth = FirebaseAuth.getInstance();
        reject=(ListView)findViewById(R.id.reject);
       accecpt=(ListView)findViewById(R.id.accecpt);
        rej=new ArrayList<application>();
        all=new ArrayList<application>();
        accp=new ArrayList<application>();
        User_id = firebaseAuth.getCurrentUser().getUid();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("application");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    application ap = postSnapshot.getValue(application.class);
                    if(ap.getIdCan().toUpperCase().equals(User_id.toUpperCase())){
                        if(ap.getStatus().equals(r))
                        all.add(ap);


                    }
                    String[] re = new String[all.size()];
                    String[] ac = new String[all.size()];
                    // j = new String[names.size()];

                    for (int i = 0; i < all.size(); i++) {
                       if(all.get(i).getStatus().equals(r))
                           re[i] = all.get(i).getNameOrg() + " _ " + all.get(i).getAppname();

//                    }
//                        else {
//                            ac[i] = all.get(i).getAppname();
//                        }

                        // j[i]=key.get(i);
                    }

                    //disp laying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, re);
                    reject.setAdapter(adapter);

                    //disp laying it to list
//                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ac);
//                    accecpt.setAdapter(adapter1);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference().child("application");

        mDatabaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    application ap1 = postSnapshot.getValue(application.class);
                    if(ap1.getIdCan().toUpperCase().equals(User_id.toUpperCase())){
                        if(ap1.getStatus().equals("accept"))
                            accp.add(ap1);


                    }
                    //String[] re = new String[all.size()];
                    String[] ac = new String[accp.size()];
                    // j = new String[names.size()];

                    for (int i = 0; i < accp.size(); i++) {
                        //  if(all.get(i).getStatus().equals(r)){
                        ac[i] = accp.get(i).getNameOrg()+" _ "+accp.get(i).getAppname();

//                    }
//                        else {
//                            ac[i] = all.get(i).getAppname();
//                        }

                        // j[i]=key.get(i);
                    }

                    //disp laying it to list
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ac);
                    accecpt.setAdapter(adapter1);

                    //disp laying it to list
//                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ac);
//                    accecpt.setAdapter(adapter1);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
