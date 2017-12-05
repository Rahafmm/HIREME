package com.example.hp.hireme.AccuontActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.hireme.Org;
import com.example.hp.hireme.R;
import com.example.hp.hireme.viewInfoOrg;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FavoriteActivity extends AppCompatActivity {


    ListView FavListView;
    DatabaseReference mDatabase;
    ArrayList<String> names;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        /*Intent intent = getIntent();
        cat = intent.getStringExtra("cat");

        names = new ArrayList<>();
        listFav = (ListView) findViewById(R.id.FavListView);
        lisFav1 = (TextView) findViewById(R.id.FavListView1);
        //listorg1.setText(cat);
        listFav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Org selOrg = names.get(i);

                //Intent intent =new Intent(listOrg.this, viewInfoOrg.class);

            }
        });*/
        firebaseAuth = FirebaseAuth.getInstance();
        String User_ID = firebaseAuth.getCurrentUser().getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("fav").child(User_ID);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String n=postSnapshot.getKey().toString();
                    Toast.makeText(FavoriteActivity.this, n, Toast.LENGTH_LONG).show();
                    names.add(n);
                }

                String[] uploads = new String[names.size()];

                for (int i = 0; i < uploads.length; i++) {

                    uploads[i] = names.get(i);

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



