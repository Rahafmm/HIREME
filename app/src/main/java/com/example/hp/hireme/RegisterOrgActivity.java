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

public class RegisterOrgActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPassword;
    private  EditText editTextCPasword;
    private Button buttonRgister;
    private DatabaseReference mDatabase;
    private EditText editTextLocation ;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView textViewLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerorg);


        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() !=null){
            //prf activity
            finish();
        }
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Org");
        //initializing views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPasword);
        editTextCPasword = (EditText) findViewById(R.id.editTextCPasword);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        buttonRgister = (Button) findViewById(R.id.buttonRgister);
        textViewLogIn = (TextView) findViewById(R.id.textviewLogIn);

        //attaching listener to button
        buttonRgister.setOnClickListener((View.OnClickListener) this);
        textViewLogIn.setOnClickListener(this);

        // to chow message
        progressDialog = new ProgressDialog(this);

    }

    @Override
    public void onClick(View view) {
        if(view == buttonRgister ){
            //calling register method on click
            registerUser();}
        if(view == textViewLogIn ) {
            startActivity(new Intent(this, LoginActivity.class) ); //profile=login

        }
    }


    private void registerUser() {
        //getting email and password from edit texts
        final String Cpassword = editTextCPasword.getText().toString().trim();
        final String Name = editTextName.getText().toString().trim();
        final String Location =editTextLocation.getText().toString().trim();


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

        if(TextUtils.isEmpty(Location)){
            Toast.makeText(this,"Please enter Location",Toast.LENGTH_LONG).show();
            return; //stop the function execution
        }

        //if the email and password and Location are not empty
        //displaying a progress dialog
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            String User_ID=firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference Cureent_User_db= mDatabase.child(User_ID);

                            Cureent_User_db.child("Cpassword").setValue(Cpassword);
                            Cureent_User_db.child("Location").setValue(Location);

                            Cureent_User_db.child("name").setValue(Name);
                            //display message to the user here
                            Toast.makeText(RegisterOrgActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            //close this activity
                            finish();
                            //opening login activity
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(RegisterOrgActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}

