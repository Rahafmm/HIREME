package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HIREME extends AppCompatActivity implements View.OnClickListener {



    //defining view objects
    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPassword;
    private  EditText editTextCPasword;
    private Button buttonSignup;
    private TextView  textViewLogIn;
private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private TextView regorg;

    //defining firebaseauth object

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("candet");
        //initializing views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPasword);
        editTextCPasword = (EditText) findViewById(R.id.editTextCPasword);
        textViewLogIn = (TextView) findViewById(R.id.textviewLogIn);
        regorg = (TextView) findViewById(R.id.regorg);
        buttonSignup = (Button) findViewById(R.id.buttonRgister);

        //attaching listener to button
        buttonSignup.setOnClickListener((View.OnClickListener) this);
        textViewLogIn.setOnClickListener(this);
        regorg.setOnClickListener(this);
        // to chow message
        progressDialog = new ProgressDialog(this);

    }


    @Override
    public void onClick(View view) {
        if(view == buttonSignup ){
        //calling register method on click
        registerUser();}
        if(view == textViewLogIn ) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        }
        if(view == textViewLogIn ) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

    private void registerUser() {
        //getting email and password from edit texts
        final String Cpassword = editTextCPasword.getText().toString().trim();
        final String Name = editTextName.getText().toString().trim();

        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return; //stop the function execution
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return; //stop the function execution
        }

        //if the email and password are not empty
        //displaying a progress dialog
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            String User_ID=firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference Cureent_User_db= mDatabase.child(User_ID);
                            Cureent_User_db.child("name").setValue(Name);
                            Cureent_User_db.child("Cpassword").setValue(Cpassword);
                            //display message to the user here
                            Toast.makeText(HIREME.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            //close this activity
                            finish();
                            //opening login activity
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(HIREME.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}

