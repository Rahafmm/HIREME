package com.example.hp.hireme.AccuontActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.hireme.LoginActivity;
import com.example.hp.hireme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;

    private ProgressDialog progressDialog;

    //defining firebaseauth object

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPasword);

        buttonSignup = (Button) findViewById(R.id.buttonRgister);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);

        // to chow message
        progressDialog = new ProgressDialog(this);

    }


    @Override
    public void onClick(View v) {
        //calling register method on click
        registerUser();
    }

    private void registerUser() {
        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"ادخل بريدك الالكتروني",Toast.LENGTH_LONG).show();
            return; //stop the function execution
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"ادخل كلمة السر",Toast.LENGTH_LONG).show();
            return; //stop the function execution
        }

        //if the email and password are not empty
        //displaying a progress dialog
        progressDialog.setMessage("تسجيل، الرجاء الانتظار..");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display message to the user here
                            Toast.makeText(RegisterActivity.this,"تم التسجيل بنجاح",Toast.LENGTH_LONG).show();
                            //close this activity
                            finish();
                            //opening login activity
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"هناك خلل..",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}

