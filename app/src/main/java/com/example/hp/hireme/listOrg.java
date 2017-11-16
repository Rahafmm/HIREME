package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by Lama on 11/11/17.
 */

public class listOrg extends AppCompatActivity {
   String cat;
    private ListView listorg;
    DatabaseReference mDatabase;
    ArrayList<String> names;
    DatabaseReference orgRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Intent intent=getIntent();
        cat=intent.getStringExtra("cat");

        names=new ArrayList<String>();
        LinearLayout orglist=(LinearLayout)findViewById(R.id.orglist);
        //listorg=(ListView) findViewById(R.id.listorg);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        orgRef=mDatabase.child("Org");
        listorg=(ListView) findViewById(R.id.listorg);

        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Org name =ds.getValue(Org.class);
                    names.add(name.getname());
                    String[] uploads = new String[names.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = names.get(i);
                    }
                    final ArrayAdapter<String> list;
                    list=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads);
                    listorg.setAdapter(list);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };



    }
}
