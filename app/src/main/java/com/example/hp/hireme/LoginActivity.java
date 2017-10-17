package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewregister;

   private FirebaseAuth m;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPasword);
       buttonLogIn=(Button) findViewById(R.id.buttonLogin);
        textViewregister=(TextView)findViewById(R.id.textViewregister);

        progressDialog=new ProgressDialog(this);

        buttonLogIn.setOnClickListener(this);
        textViewregister.setOnClickListener(this);

    }

    private void CandidateLogIn(){

        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        //checking if email and password are empty
        if (TextUtils.isEmpty(email) ){
            Toast.makeText(this,"please enter your email",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password) ){
            Toast.makeText(this,"please enter your password",Toast.LENGTH_LONG).show();
            return;
        }
        //if email and password are not empty
        //display progress
        progressDialog.setMessage("Loging In please wait");
        progressDialog.show();
    }

    @Override
    public void onClick(View view){
        if (view==buttonLogIn){
            CandidateLogIn();
        }
        if (view==textViewregister){
            finish();
            startActivity(new Intent(this,HIREME.class));
        }


    }

}
