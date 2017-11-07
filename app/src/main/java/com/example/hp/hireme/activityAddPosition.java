package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activityAddPosition extends AppCompatActivity {
    private EditText posdes;
    private EditText posname;
    private TextView nameempty;
    private TextView posdesempty;
    private Button buttonAddPosition;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_position);
        posdes = (EditText) findViewById(R.id.posdes);
        posname = (EditText) findViewById(R.id.posname);
        nameempty = (TextView) findViewById(R.id.nameempty);
        posdesempty = (TextView) findViewById(R.id.posdesempty);
        buttonAddPosition = (Button) findViewById(R.id.buttonAddPosition);
        buttonAddPosition.setOnClickListener((View.OnClickListener) this);

        progressDialog = new ProgressDialog(this);

    }

    public void onClick(View view) {
        if (view == buttonAddPosition) {
            AddPos();
        }
    }

    public void AddPos() {
        final String namepos = posname.getText().toString().trim();
        final String despos = posdes.getText().toString().trim();


        //checking if name and desc are empty
        if(TextUtils.isEmpty(namepos)) {
            nameempty.setText("* حقل مطلوب ");
        }else{
            nameempty.setText("");
        }

        if(TextUtils.isEmpty(despos)) {
            posdesempty.setText("* حقل مطلوب ");
        }else{
            posdesempty.setText("");
        }

        //displaying a progress dialog
        progressDialog.setMessage("الرجاء الانتظار...");
        progressDialog.show();


    }




}



