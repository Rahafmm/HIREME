package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.hireme.AccuontActivity.Candidate;
import com.example.hp.hireme.AccuontActivity.Org;
import com.example.hp.hireme.AccuontActivity.listOrg;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lama on 18/11/17.
 */

public class viewInfoOrg extends AppCompatActivity{

    private TextView name1;
    private TextView loc;
    private TextView ca;
    private TextView m;
    private Button FavButton;
    private ProgressDialog progressDialog;
    DatabaseReference mDatabase;
    DatabaseReference mDatabase1;
    ArrayList<Org> favArray;
    Org g;
    String Id;
    Org org;

    //TextView t;
    private ImageView imageView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);

        firebaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        final String cat = intent.getExtras().getString("cat", "");
        final String location = intent.getExtras().getString("location", "");
        final String name = intent.getExtras().getString("name", "");
        Id = intent.getExtras().getString("id", "");

        name1 = (TextView) findViewById(R.id.name1);
        loc = (TextView) findViewById(R.id.loc);
        ca = (TextView) findViewById(R.id.ca);
        FavButton = (Button) findViewById(R.id.FavButton);
        m = (TextView) findViewById(R.id.m);
        //imageView=(ImageView)findViewById(R.id.imageView);

        // t=(TextView)findViewById(R.id.t);
        name1.setText(name);
        loc.setText(location);
        ca.setText(cat);
        //Fav Button
        FavButton.setOnClickListener(new View.OnClickListener() {


            // FirebaseStorage storage = FirebaseStorage.getInstance();
            // StorageReference storageRef = storage.getReference().child("Org").child(Id);

            // String m= storageRef.child("pic").getDownloadUrl().toString();
            // t.setText(m);
            //Picasso.with(viewInfoOrg.this).load(m).into(imageView);


            @Override
            public void onClick(View view) {
                try {


                    //m.setText("bnnnnn");
                    if (view == FavButton) {
                        //FavButton.setText("dee");
                        final String User_ID = firebaseAuth.getCurrentUser().getUid();

                        mDatabase = FirebaseDatabase.getInstance().getReference().child("candet");
                        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("Org");

                        mDatabase1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    g = postSnapshot.getValue(Org.class);
                                    if (g != null)
                                        if (g.getUid().equals(Id)) {

                                            org = postSnapshot.getValue(Org.class);
                                        }

                                }
                                //FavButton.setText("ll");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        mDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    Candidate can = postSnapshot.getValue(Candidate.class);
                                    if (can != null)
                                        if (can.getUid().equals(User_ID)) {
//m.setText("b");

                                            can.setFav(org);
                                            mDatabase.child(User_ID).child("fav").setValue(org);
                                        }

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                }catch (Exception e){System.out.print("error");}
            }
        });
    }

}