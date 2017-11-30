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
import android.widget.Toast;

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

public class viewInfoPos extends AppCompatActivity implements View.OnClickListener {


        private TextView name1;
        private TextView de;
        private Button FavButtonn;
        private TextView textView3;
        private TextView textView2;
        private FirebaseAuth firebaseAuth;
        DatabaseReference mDatabase;
    private String des;
    private String Id1;
    private  String nameOrg;
    private String idOrg;
    private String User_ID;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.viewpos);

            firebaseAuth = FirebaseAuth.getInstance();
            Intent intent = getIntent();
            des = intent.getExtras().getString("des", "");
            final String namey = intent.getExtras().getString("name", "");
              Id1 = intent.getExtras().getString("id", "");
             nameOrg = intent.getExtras().getString("name", "");
             idOrg = intent.getExtras().getString("Id", "");

            User_ID = firebaseAuth.getCurrentUser().getUid();


            name1 = (TextView) findViewById(R.id.name1);
            de = (TextView) findViewById(R.id.de);
            FavButtonn = (Button) findViewById(R.id.FavButtonn);
            textView3 = (TextView) findViewById(R.id.textView3);
            textView2 = (TextView) findViewById(R.id.textView2);

            name1.setText(namey);
            de.setText(des);

            FavButtonn.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {


        application ca =new application();
        ca.setIdCan(User_ID);
       // ca.setIdOrg(idOrg);
        ca.setNameOrg(Id1);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("application");
        String pu=Id1+"_"+nameOrg;

        mDatabase.child(pu).setValue(ca);
        Toast.makeText(viewInfoPos.this, "تم رفع طلبك بنجاح", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, profileCand.class));
    }
}
