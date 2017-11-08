package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hp.hireme.AccuontActivity.EditProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileCand extends AppCompatActivity implements View.OnClickListener {
private ImageButton editprofile;
    private Button buttonLogout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_cand);
        editprofile=(ImageButton)findViewById(R.id.editprofile);
        buttonLogout=(Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);

        editprofile.setOnClickListener(this);
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user= firebaseAuth.getCurrentUser();

    }

    @Override
    public void onClick(View view) {

        if (view==editprofile) {
            startActivity(new Intent(this, EditProfileActivity.class));

        }
        if (view==buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


}
