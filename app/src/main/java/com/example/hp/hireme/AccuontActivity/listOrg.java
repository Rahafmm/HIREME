package com.example.hp.hireme.AccuontActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hp.hireme.R;
import com.example.hp.hireme.profileCand;
import com.example.hp.hireme.viewInfoOrg;
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
    ListView listorg;
    DatabaseReference mDatabase;
    ArrayList<Org> names;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Intent intent=getIntent();
        cat=intent.getStringExtra("cat");

        names=new ArrayList<>();
        listorg=(ListView) findViewById(R.id.listorg);
      listorg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Org selOrg = names.get(i);

               Intent intent =new Intent(listOrg.this, viewInfoOrg.class);
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.putExtra("org",selOrg.getUid());
                startActivity(intent);
            }
        });

        //listorg=(ListView) findViewById(R.id.listorg);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Org");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Org orga = postSnapshot.getValue(Org.class);
                   // if(orga.getname().equals(cat))
                    names.add(orga);
                }

                String[] uploads = new String[names.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = names.get(i).getname();

                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listorg.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
