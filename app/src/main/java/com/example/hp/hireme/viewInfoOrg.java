package com.example.hp.hireme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.hireme.AccuontActivity.Org;
import com.example.hp.hireme.AccuontActivity.listOrg;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lama on 18/11/17.
 */

public class viewInfoOrg extends AppCompatActivity {
    private TextView name1;
    private TextView  loc;
    private TextView ca;

    TextView t;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);

        Intent intent=getIntent();
        final String cat=intent.getExtras().getString("cat","");
        final String location=intent.getExtras().getString("location","");
        final String name=intent.getExtras().getString("name","");
        final String Id=intent.getExtras().getString("id","");

        name1 = (TextView) findViewById(R.id.name1);
        loc =(TextView)findViewById(R.id.loc);
        ca =(TextView)findViewById(R.id.ca);
        imageView=(ImageView)findViewById(R.id.imageView);

        t=(TextView)findViewById(R.id.t);
        name1.setText(name);
        loc.setText(location);
        ca.setText(cat);

       // FirebaseStorage storage = FirebaseStorage.getInstance();
       // StorageReference storageRef = storage.getReference().child("Org").child(Id);

     // String m= storageRef.child("pic").getDownloadUrl().toString();
       // t.setText(m);
        //Picasso.with(viewInfoOrg.this).load(m).into(imageView);


    }
    }
