package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.example.hp.hireme.Org;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by Lama on 11/11/17.
 */

public class listOrg extends AppCompatActivity {
   //String cat;
    ListView listView;
    DatabaseReference mDatabase;
    ArrayList<Org> names;
String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Intent intent=getIntent();
       final String cat=intent.getStringExtra("cat");

        names=new ArrayList<>();
        listView=(ListView) findViewById(R.id.listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                 final Org selOrg = names.get(i);
               //Intent intent =new Intent(listOrg.this, viewInfoOrg.class);
                Intent intent = new Intent(listOrg.this, viewInfoOrg.class);
                intent.putExtra("name",selOrg.getname());
                intent.putExtra("location",selOrg.getLocation());
                intent.putExtra("cat",selOrg.getcatgory());
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });




        mDatabase= FirebaseDatabase.getInstance().getReference().child("Org");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Org o = postSnapshot.getValue(Org.class);
                    id=postSnapshot.getKey();
                    if(o.getcatgory().toUpperCase().equals(cat.toUpperCase())){
                       names.add(o);}
                }

                String[] uploads = new String[names.size()];

                for (int i = 0; i < uploads.length; i++) {

                    uploads[i] = names.get(i).getname();

                }

                //disp laying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
