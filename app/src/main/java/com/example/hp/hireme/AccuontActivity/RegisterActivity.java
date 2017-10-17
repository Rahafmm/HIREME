package com.example.hp.hireme.AccuontActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.hireme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRgister;
    private EditText editTextName;
    private  EditText textEmailAddress;
    private  EditText editTextPasword;
    private  EditText editTextCPasword;
    private TextView  textViewLogIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("candet");
        firebaseAuth= FirebaseAuth.getInstance();
                progressDialog= new ProgressDialog(this);

        buttonRgister = (Button) findViewById(R.id.buttonRgister);
        editTextName = (EditText) findViewById(R.id.editTextName);
        textEmailAddress = (EditText) findViewById(R.id.editTextEmail);
        editTextPasword = (EditText) findViewById(R.id.editTextPasword);
        editTextCPasword = (EditText) findViewById(R.id.editTextCPasword);
        textViewLogIn = (TextView) findViewById(R.id.textviewLogIn);

        buttonRgister.setOnClickListener(this);
        textViewLogIn.setOnClickListener(this);
    }

    private  void registerUser(){
        String Email = textEmailAddress.getText().toString().trim();
        String password = editTextPasword.getText().toString().trim();
        String Cpassword = editTextCPasword.getText().toString().trim();
        String Name = editTextName.getText().toString().trim();


        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"plase enter email", Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"plase enter password", Toast.LENGTH_SHORT).show();
            return;
        }


        if(TextUtils.isEmpty(Cpassword)){
            Toast.makeText(this,"plase enter confirom password ", Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(Name)){
            Toast.makeText(this,"plase enter name", Toast.LENGTH_SHORT).show();
            return;

        }

        progressDialog.setMessage("Regisreing USER ....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "could not register , please register again", Toast.LENGTH_SHORT).show();
                }
                }
            });
        }

    public void onClick(View view){

        if(view == buttonRgister ){
            registerUser();
        }
        if(view == textViewLogIn ) {



        }

    }
}
