package com.example.hp.hireme.AccuontActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.hireme.R;
import com.example.hp.hireme.viewInfoOrg;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class FavoriteActivity extends AppCompatActivity {

    Button buttonViewFav;
    ArrayList<Org> fav;
    //ArrayList<String> favArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_cand);

        //favArray = new ArrayList<>();

        buttonViewFav = (Button) findViewById(R.id.buttonViewFav);

        buttonViewFav.setOnClickListener((View.OnClickListener) this);
    }

    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
            viewInfoOrg orga = postSnapshot.getValue(viewInfoOrg.class);
            Candidate c ;
            Candidate ca;
            //fav=ca.getfav();

    }

        String[] favArray = new String[fav.size()];

        for (int i = 0; i < favArray.length; i++) {

            favArray[i] = fav.get(i).getname();

        }

        //displaying it to list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, favArray);
        //favArray.etAdapter(adapter);
    }

        public void onClick(View view){
            if(view==buttonViewFav){
                viewFav();
            }

        }
        private void viewFav() {

            //String item;
            //boolean clicked=true;
            //for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                //Org orga = postSnapshot.getValue(Org.class);
                }


    }



/**
    String cat;
    ListView listFav;
    DatabaseReference mDatabase;
    ArrayList<Org> names;
    TextView lisFav1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        Intent intent=getIntent();
        cat=intent.getStringExtra("cat");

        names=new ArrayList<>();
        listFav=(ListView) findViewById(R.id.FavListView);
        lisFav1=(TextView)findViewById(R.id.FavListView1);
        //listorg1.setText(cat);
        listFav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Org selOrg = names.get(i);

                //Intent intent =new Intent(listOrg.this, viewInfoOrg.class);

            }
        });



        mDatabase= FirebaseDatabase.getInstance().getReference().child("Org");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Org orga = postSnapshot.getValue(Org.class);

                    names.add(orga);
                }

                String[] uploads = new String[names.size()];

                for (int i = 0; i < uploads.length; i++) {

                    uploads[i] = names.get(i).getname();

                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listFav.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
    */



