package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class profileCand extends AppCompatActivity implements View.OnClickListener {
private ImageButton editprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_cand);
        editprofile=(ImageButton)findViewById(R.id.editprofile);

        editprofile.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view==editprofile) {
            startActivity(new Intent(this, ProfileEditActivity2.class));

        }
    }


}
