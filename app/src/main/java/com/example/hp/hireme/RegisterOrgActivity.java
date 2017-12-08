package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private TextView  tv;
    private TextView textpasswordempty;
    private TextView textnameempty;
    private TextView textpasswordmatch;
    private TextView logCan;
    private TextView regCan;
    private Spinner cat;
    Org org1;
    String record="nothing";
    String cata[]={"IT","health","industrial","food","Busines","travel","Other"};
    ArrayAdapter<String> adapter;


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
        textpasswordempty =(TextView)findViewById(R.id.textpasswordempty);
        tv = (TextView)findViewById(R.id.textemailempty);
        textpasswordmatch = (TextView)findViewById(R.id.textpasswordmatch);
        textnameempty = (TextView)findViewById(R.id.textnameempty);
        cat=(Spinner)findViewById(R.id.cat);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cata);
        cat.setAdapter(adapter);
        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                                          @Override
                                          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                              switch (position) {
                                                  case 0:
                                                      record = "IT";
                                                      break;
                                                  case 1:
                                                      record = "health";
                                                      break;
                                                  case 2:
                                                      record = "industrial";
                                                      break;
                                                  case 3:
                                                      record = "food";
                                                      break;
                                                  case 4:
                                                      record = "Busines";
                                                      break;
                                                  case 5:
                                                      record = "travel";
                                                      break;
                                                  case 6:
                                                      record = "Other";
                                                      break;
                                              }
                                          }

                                          @Override
                                          public void onNothingSelected(AdapterView<?> parent) {

                                          }

                                      });


        regCan = (TextView) findViewById(R.id.regCan);
        logCan = (TextView) findViewById(R.id.logCan);
        //attaching listener to button
        buttonRgister.setOnClickListener((View.OnClickListener) this);
        textViewLogIn.setOnClickListener(this);
        regCan.setOnClickListener(this);
        logCan.setOnClickListener(this);

        // to chow message
        progressDialog = new ProgressDialog(this);

    }

    @Override
    public void onClick(View view) {
        if(view == buttonRgister ){
            //calling register method on click
            registerUser();}
        if(view == textViewLogIn ) {
            startActivity(new Intent(this, LoginOrgActivity.class) ); //profile=login

        }
        if(view == regCan ) {
            startActivity(new Intent(this, HIREME.class) );

        }
        if(view == logCan ) {
            startActivity(new Intent(this, LoginActivity.class) );

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
        if(TextUtils.isEmpty(Name)) {
            textnameempty.setText("* حقل مطلوب ");
        }else{
            textnameempty.setText("");
        }

        if(TextUtils.isEmpty(email)) {
            tv.setText("* حقل مطلوب ");
        }else{
            tv.setText("");
        }

        if(TextUtils.isEmpty(password)){
            textpasswordempty.setText("* حقل مطلوب");
        }else{
            textpasswordempty.setText("");
            password  = editTextPassword.getText().toString().trim();
        }

        if(password.equals(Cpassword)) {
            textpasswordmatch.setText("");
        }else{
            textpasswordmatch.setText("* كلمة السر غير متطابقة");
        }

        if(textpasswordmatch.getText().toString().trim().isEmpty()&&textpasswordempty.getText().toString().trim().isEmpty()&&tv.getText().toString().trim().isEmpty()&&textnameempty.getText().toString().trim().isEmpty()) {
            //if the email and password and Location are not empty
            //displaying a progress dialog
            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                String User_ID = firebaseAuth.getCurrentUser().getUid();
                                //DatabaseReference Cureent_User_db = mDatabase.child(User_ID);

                               // Cureent_User_db.child("Cpassword").setValue(Cpassword);
                                //Cureent_User_db.child("Location").setValue(Location);
                                //Cureent_User_db.child("catgory").setValue(record);

                                //Cureent_User_db.child("name").setValue(Name);
                                final String uid = firebaseAuth.getInstance().getCurrentUser().getUid();
                                Toast.makeText(RegisterOrgActivity.this, uid, Toast.LENGTH_LONG).show();



                                org1=new Org();

                                org1.setname(editTextName.getText().toString());
                                org1.setLocation(editTextLocation.getText().toString());

                                org1.setuid(User_ID);
                                org1.setpass(editTextPassword.getText().toString());
                                org1.setcatgory(record);

                                mDatabase.child(editTextName.getText().toString()).setValue(org1);
                               // Intent intent=new Intent(RegisterOrgActivity.this, ProfileActivity.class);
                               // intent.putExtra("org", (Serializable) org1);
                                //display message to the user here
                                Toast.makeText(RegisterOrgActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                                //close this activity
                                finish();
                                //opening login activity
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            } else {
                                //display some message here
                                Toast.makeText(RegisterOrgActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}

