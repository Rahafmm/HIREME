package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.hireme.AccuontActivity.Position;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lama on 28/11/17.
 */

public class viewInfoPos extends AppCompatActivity {


        private TextView name1;
        private TextView de;
        private Button FavButton;
        private TextView textView3;
        private TextView textView2;
        private FirebaseAuth firebaseAuth;
        DatabaseReference mDatabase;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.viewpos);

            firebaseAuth = FirebaseAuth.getInstance();
            Intent intent = getIntent();
            final String des = intent.getExtras().getString("des", "");
            final String name = intent.getExtras().getString("name", "");
            final String Id = intent.getExtras().getString("id", "");

            name1 = (TextView) findViewById(R.id.name1);
            de = (TextView) findViewById(R.id.de);
            FavButton = (Button) findViewById(R.id.FavButton);
            textView3 = (TextView) findViewById(R.id.textView3);
            textView2 = (TextView) findViewById(R.id.textView2);

            name1.setText(name);
            de.setText(des);



    }
}
