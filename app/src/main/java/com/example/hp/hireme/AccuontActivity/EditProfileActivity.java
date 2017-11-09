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

import com.example.hp.hireme.profileCand;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.hp.hireme.R;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText name;

    private EditText oldPass;

    private EditText newPass;

    private EditText conPass;

    private Button save;

    private Button cancel;



    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;

    private  String userID;

    private ProgressDialog progressDialog;
    //private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);



        name = (EditText) findViewById(R.id.name);

        newPass = (EditText) findViewById(R.id.newPass);


        save = (Button) findViewById(R.id.save);


        progressDialog = new ProgressDialog(this);


        firebaseDatabase=FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        save.setOnClickListener(this);


    }



    ///edit
    @Override
    public void onClick(View v) {

        if(!(TextUtils.isEmpty(name.getText().toString().trim())))
            mDatabase.child("candet").child(userID).child("name").setValue(name.getText().toString());







        if(!(TextUtils.isEmpty(newPass.getText().toString().trim()))){
            FirebaseUser user1 =FirebaseAuth.getInstance().getCurrentUser();
            if(user1!=null)
            {
                progressDialog.setMessage("changing Password , please wait!!");
                progressDialog.show();
                mDatabase= FirebaseDatabase.getInstance().getReference();
                String pass=newPass.getText().toString();


                user1.updatePassword(pass)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    mDatabase.child("candet").child(userID).child("Cpassword").setValue(newPass.getText().toString());

                                    //display message to the user here
                                    Toast.makeText(EditProfileActivity.this, "Successfully changed password", Toast.LENGTH_LONG).show();

                                } else {
                                    //display some message here
                                    Toast.makeText(EditProfileActivity.this, "There is error in change password ", Toast.LENGTH_LONG).show();

                                }
                                progressDialog.dismiss();
                            }
                        });

            }
        }

    }
    /////end edit






//        private void CanProfileEdit(){
//
//            String Name = name.getText().toString();
//            String OldPass = oldPass.getText().toString().trim();
//            String NewPass = newPass.getText().toString().trim();
//            String ConPass = conPass.getText().toString().trim();
//
//            // update the user profile information in Firebase database.
//            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(OldPass) || TextUtils.isEmpty(NewPass)
//                    || TextUtils.isEmpty(ConPass)) {
//
//                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();
//                return;
//            }
//        progressDialog.setMessage("Saving edits please wait");
//        progressDialog.show();
//
//
//
//    }
//
//    @Override
//    public void onClick(View view) {
//
//    }
}