package com.example.hp.hireme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hp.hireme.AccuontActivity.Org;
import com.example.hp.hireme.AccuontActivity.listOrg;

import java.util.ArrayList;

/**
 * Created by Lama on 18/11/17.
 */

public class viewInfoOrg extends AppCompatActivity {
String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);

        Intent intent = getIntent();
        Id = intent.getStringExtra("org");


    }
    }
