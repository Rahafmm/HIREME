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


/*public class LoginActivity extends AppCompatActivity{

    private EditText mLoginEmailFiled;
    private EditText mLoginPasswordFiled;
    private Button mLoginBtn;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mAuth = FirebaseAuth.getInstance();

    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

    mLoginEmailFiled = (EditText) findViewById(R.id.loginEmailFiled);
    mLoginPasswordFiled = (EditText) findViewById(R.id.loginPasswordFiled);

    mLoginBtn = (Button) findViewById(R.id.loginBtn);

    mLoginBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){

            checkLogin();
        }
    });

}

    private void checkLogin() {

        String email = mLoginEmailFiled.getText().toString().trim();
        String password = mLoginPasswordFiled.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

           mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   if(task.isSuccessful()){

                       checkUserExist();

                   }else{

                       Toast.makeText(LoginActivity.this,"Error Login",Toast.LENGTH_LONG).show();
                   }

               }
           });



        }
    }

    private void checkUserExist() {

        final String user_id = mAuth.getCurrentUser().getUid();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user_id)){

                  Intent HIREMEIntent = new Intent(LoginActivity.this,HIREME.class) ;
                    HIREMEIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(HIREMEIntent);


                }else{

                    Toast.makeText(LoginActivity.this,"You need to setup your account.",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}*/



public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogIn;
    private EditText editTextEmail;
    private EditText editTextPasword;
    private TextView textViewregister;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView textViewregisterorg;
    private TextView textViewloginorg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

       if (firebaseAuth.getCurrentUser() != null) {

            //profile activity here
         finish();
          startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
       }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPasword = (EditText) findViewById(R.id.editTextPasword);
        buttonLogIn = (Button) findViewById(R.id.buttonLogin);
        textViewregister = (TextView) findViewById(R.id.textViewregister);
        textViewregisterorg = (TextView) findViewById(R.id.textViewregisterorg);
        textViewloginorg = (TextView) findViewById(R.id.textViewloginorg);

        progressDialog = new ProgressDialog(this);

        buttonLogIn.setOnClickListener(this);
        textViewregister.setOnClickListener(this);
        textViewregisterorg.setOnClickListener(this);
        textViewloginorg.setOnClickListener(this);


    }
    private void CandidateLogIn(){

        String email=editTextEmail.getText().toString().trim();
        String password=editTextPasword.getText().toString().trim();

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

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //start profile activity

                            finish();
                            startActivity(new Intent(getApplicationContext(), profileCand.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "The email OR password are incorrect", Toast.LENGTH_LONG).show();
                        }

                    }
                });

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
        if (view==textViewregisterorg){
            finish();
            startActivity(new Intent(this,RegisterOrgActivity.class));
        }
        if (view==textViewloginorg){
            finish();
            startActivity(new Intent(this,LoginOrgActivity.class));
        }


    }

}
