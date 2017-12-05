package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
private ImageButton imageButton8;
    private ImageButton addpos;
    private ImageButton app;
    DatabaseReference mDatabaseReference1;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//hi
        firebaseAuth= FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user= firebaseAuth.getCurrentUser();


        buttonLogout=(Button) findViewById(R.id.buttonLogout);
        app=(ImageButton)findViewById(R.id.app);
        imageButton8=(ImageButton)findViewById(R.id.imageButton8);
        addpos=(ImageButton)findViewById(R.id.addpos);
        addpos.setOnClickListener(this);
        imageButton8.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
        app.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        if (view==buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(view==imageButton8){

            startActivity(new Intent(this, ProfileEditActivity2.class));
            //startActivity(new Intent(this, EditProfileActivity.class));
        }
        if(view==addpos){
            //Intent  i = getIntent();
            //Org org = (Org)i.getSerializableExtra("org");
           // Intent intent=new Intent(ProfileActivity.this, activityAddPosition.class);
           // intent.putExtra("org",org);
            startActivity(new Intent(this, activityAddPosition.class));
        }
        if(view==app){
            //Intent  i = getIntent();
            //Org org = (Org)i.getSerializableExtra("org");
            // Intent intent=new Intent(ProfileActivity.this, activityAddPosition.class);
            // intent.putExtra("org",org);mDatabaseReference1=FirebaseDatabase.getInstance().getReference().child("Org").child(User_id).child("name");

            startActivity(new Intent(this, viewapplication.class));
        }
    }
}
