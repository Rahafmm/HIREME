package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hp.hireme.AccuontActivity.EditProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileCand extends AppCompatActivity implements View.OnClickListener {


    private BottomNavigationView bottomNavigationView;

    private ImageButton industrial;
    private ImageButton IT;
    private ImageButton food;
    private ImageButton Busines;
    private ImageButton travel;
    private ImageButton Health;
    private Button buttonViewFav;

    public String category;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_cand);

        industrial=(ImageButton)findViewById(R.id.industrial);
        IT=(ImageButton)findViewById(R.id.IT);
        food=(ImageButton)findViewById(R.id.food);
        Busines=(ImageButton)findViewById(R.id.Busines);
        travel=(ImageButton)findViewById(R.id.travel);
        Health=(ImageButton)findViewById(R.id.Health);
       // buttonViewFav=(Button)findViewById(R.id.buttonViewFav);

        Health.setOnClickListener(this);
        IT.setOnClickListener(this);
        food.setOnClickListener(this);
        Busines.setOnClickListener(this);
        travel.setOnClickListener(this);
        industrial.setOnClickListener(this);
        //buttonViewFav.setOnClickListener(this);

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.nav);
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user= firebaseAuth.getCurrentUser();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                                                                     @Override
                                                                     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                                         if(item.getItemId()== R.id.Logout){
                                                                             firebaseAuth.signOut();
                                                                             finish();
                                                                             startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                                         }else if(item.getItemId()== R.id.edit){
                                                                             startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
                                                                         }else if(item.getItemId()== R.id.addCV){
                                                                             startActivity(new Intent(getApplicationContext(), addCV.class));
                                                                         }else if(item.getItemId()==R.id.fav){
                                                                             startActivity(new Intent(getApplicationContext(),FavoriteActivity.class));
                                                                         }


                                                                         return false;
                                                                     }
                                                                 }
        );



    }

    @Override
    public void onClick(View view) {
        try {
            if (view == industrial) {
                category = "industrial";
            }
            if (view == IT) {
                category = "IT";
            }
            if (view == food) {
                category = "food";
            }
            if (view == Busines) {
                category = "Busines";
            }
            if (view == travel) {
                category = "travel";
            }
            if (view == Health) {
                category = "Health";
            }

            Intent intent = new Intent(profileCand.this, listOrg.class);
            intent.putExtra("cat", category);
            startActivity(intent);

        } catch (Exception e) {
            System.out.print("there are no organizations on this category");
        }

    }
}
