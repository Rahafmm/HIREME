package com.example.hp.hireme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Lama on 07/12/17.
 */

public class viewRAapplication extends AppCompatActivity implements View.OnClickListener {
    private Button appli;
    private ImageView accept;
    private ImageView reject;

    private String appn;
    private String idcan;
    private String nameog;
    private String ur;

    private String key;
    private String url;

    private DatabaseReference mDatabaseReference3;

    private application news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejectapp);
       appli=(Button)findViewById(R.id.appli);
        accept=(ImageView) findViewById(R.id.accept);
        reject=(ImageView)findViewById(R.id.reject);


        appli.setOnClickListener(this);
        accept.setOnClickListener(this);
        reject.setOnClickListener(this);

        Intent intent = getIntent();
        key = intent.getExtras().getString("app", "");
        url = intent.getExtras().getString("url", "");

       mDatabaseReference3 = FirebaseDatabase.getInstance().getReference().child("application");

        mDatabaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    application o = postSnapshot.getValue(application.class);
                    if(postSnapshot.getKey().toUpperCase().equals(key.toUpperCase())){
                        news=o;
                    appn=o.getAppname();
                    idcan=o.getIdCan();
                    nameog=o.getNameOrg();
                    ur=o.getUrl();}
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {

        if (view== appli) {
            Intent n = new Intent(Intent.ACTION_VIEW);
            n.setData(Uri.parse(url));
            startActivity(n);
        }
        if(view == accept){

           news.setIdCan(idcan);
            // ca.setIdOrg(idOrg);
            news.setNameOrg(nameog);
            news.setUrl(ur);
            news.setAppname(appn);
            news.setStatus("accept");

            mDatabaseReference3.child(news.getNameOrg()+" _ "+news.getIdCan()).setValue(news);
            Toast.makeText(viewRAapplication.this, "تم تغيير حالة الطلب", Toast.LENGTH_LONG).show();

        }
        if (view== reject) {
            news.setIdCan(idcan);
            // ca.setIdOrg(idOrg);
            news.setNameOrg(nameog);
            news.setUrl(ur);
            news.setAppname(appn);
            news.setStatus("reject");
            mDatabaseReference3.child(news.getNameOrg()+" _ "+news.getIdCan()).setValue(news);
            Toast.makeText(viewRAapplication.this, "تم تغيير حالة الطلب", Toast.LENGTH_LONG).show();

        }



    }
}
