package com.example.hp.hireme.AccuontActivity;

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
import com.example.hp.hireme.R;

public class EditProfileActivity extends AppCompatActivity {


    private EditText name;

    private EditText oldPass;

    private EditText newPass;

    private EditText conPass;

    private Button save;

    private Button cancel;

    private ProgressDialog progressDialog;
    //private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setTitle("Edit Account Information");

        name = (EditText) findViewById(R.id.name);
        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        conPass = (EditText) findViewById(R.id.conPass);

        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);

        progressDialog = new ProgressDialog(this);}


        private void CanProfileEdit(){

            String Name = name.getText().toString();
            String OldPass = oldPass.getText().toString().trim();
            String NewPass = newPass.getText().toString().trim();
            String ConPass = conPass.getText().toString().trim();

            // update the user profile information in Firebase database.
            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(OldPass) || TextUtils.isEmpty(NewPass)
                    || TextUtils.isEmpty(ConPass)) {

                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();
                return;
            }
        progressDialog.setMessage("Saving edits please wait");
        progressDialog.show();



    }

}