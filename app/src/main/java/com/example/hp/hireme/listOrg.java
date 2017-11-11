package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by Lama on 11/11/17.
 */

public class listOrg extends AppCompatActivity {
   String cat;
private EditText h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        h=(EditText) findViewById(R.id.editText2);

        Intent intent=getIntent();
        cat=intent.getStringExtra("cat");
        h.setText(cat);

    }
}
