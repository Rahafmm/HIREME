package com.example.hp.hireme.AccuontActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.hp.hireme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;



public class FavoriteActivity extends AppCompatActivity {

    private Button buttonViewFav;
    private Button buttonAddfav;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    ListView listView;
    //list to store favorites
    List<Upload> favList;
    //////////////view fav
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        buttonViewFav = (Button) findViewById(R.id.buttonViewFav);
        buttonViewFav.setOnClickListener((View.OnClickListener) this);





        favList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
//////////////////add fav
        final Button buttonAddfav=(Button)findViewById(R.id.buttonAddfav);
        buttonAddfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==buttonAddfav){
                    addFav();
                }
            }
            public void addFav(){
//displaying it to list
               // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, favList);
               // listView.setAdapter(adapter);

            }
        });
    }

    }


