package com.example.hp.hireme.AccuontActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.hireme.Org;
import com.example.hp.hireme.R;
import com.example.hp.hireme.listOrg;
import com.example.hp.hireme.viewInfoOrg;
import com.example.hp.hireme.viewapplication;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class FavoriteActivity extends AppCompatActivity{


    ListView FavListView;
    DatabaseReference mDatabase;
    DatabaseReference mDatabase1;
    private ArrayList<String> nameO;
    private String User_ID;
    private FirebaseAuth firebaseAuth;
    private String selOrg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);
nameO=new ArrayList<String>();
        FavListView = (ListView) findViewById(R.id.FavListView);
        /*Intent intent = getIntent();
        cat = intent.getStringExtra("cat");

        names = new ArrayList<>();
        listFav = (ListView) findViewById(R.id.FavListView);
        lisFav1 = (TextView) findViewById(R.id.FavListView1);
        //listorg1.setText(cat);*/
        FavListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selOrg = nameO.get(i);
                mDatabase1 = FirebaseDatabase.getInstance().getReference().child("Org").child(selOrg);

                mDatabase1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                       // for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            String m = dataSnapshot.child("name").getValue().toString();
                        String m1 = dataSnapshot.child("location").getValue().toString();
                        String m2 = dataSnapshot.child("catgory").getValue().toString();
                        String m3 = dataSnapshot.child("uid").getValue().toString();
                        Intent intent = new Intent(FavoriteActivity.this, viewInfoOrg.class);
                        intent.putExtra("name",m);
                        intent.putExtra("location",m1);
                        intent.putExtra("cat",m2);
                        intent.putExtra("id",m3);
                        startActivity(intent);



                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                //Intent intent =new Intent(listOrg.this, viewInfoOrg.class);


            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        User_ID = firebaseAuth.getCurrentUser().getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("fav").child(User_ID);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String n = postSnapshot.getKey().toString();


                    nameO.add(n);
                }

              String[] uploads = new String[nameO.size()];

                for (int i = 0; i < uploads.length; i++) {

                    uploads[i] = nameO.get(i);

                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                FavListView.setAdapter(adapter);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}



