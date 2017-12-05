package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    DatabaseReference mDatabase1;
    private String des;
    private String Id1;
    private  String nameOrg;
    private String idOrg;
    private String User_ID;
    private String url;


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

        mDatabase1= FirebaseDatabase.getInstance().getReference().child("candet").child(User_ID).child("upload").child("url");
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                url=dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        ca.setUrl(url);
        ca.setAppname(nameOrg);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("application");
        String pu=Id1;

        mDatabase.child(pu).setValue(ca);
        Toast.makeText(viewInfoPos.this, "تم رفع طلبك بنجاح", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, profileCand.class));
    }
}
