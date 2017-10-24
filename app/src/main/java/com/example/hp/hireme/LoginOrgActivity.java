package com.example.hp.hireme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;





    public class LoginOrgActivity extends AppCompatActivity implements View.OnClickListener{

        private Button buttonLogIn;
        private EditText editTextEmail;
        private EditText editTextPasword;
        private TextView textViewregister;
        private FirebaseAuth firebaseAuth;
        private ProgressDialog progressDialog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_org);

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

            progressDialog = new ProgressDialog(this);

            buttonLogIn.setOnClickListener(this);
            textViewregister.setOnClickListener(this);


        }
        private void OrgLogIn(){

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
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            }
                        }
                    });

        }

        @Override
        public void onClick(View view){
            if (view==buttonLogIn){
                OrgLogIn();
            }
            if (view==textViewregister){
                finish();
                startActivity(new Intent(this,RegisterOrgActivity.class));
            }


        }

    }
