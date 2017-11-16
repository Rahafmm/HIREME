package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activityAddPosition extends AppCompatActivity implements View.OnClickListener{
    private EditText posdes;
    private EditText posname;
    private TextView nameempty;
    private TextView posdesempty;
    private Button buttonAddPosition;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_position);

        posdes = (EditText) findViewById(R.id.posdes);
        posname = (EditText) findViewById(R.id.posname);
        nameempty = (TextView) findViewById(R.id.nameempty);
        posdesempty = (TextView) findViewById(R.id.posdesempty);
        buttonAddPosition = (Button) findViewById(R.id.buttonAddPosition);
        buttonAddPosition.setOnClickListener(this);


        progressDialog = new ProgressDialog(this);
    }


    public void onClick(View view) {
        if (view == buttonAddPosition) {
            AddPos();
        }
    }

    public void AddPos() {
        final String namepos = posname.getText().toString().trim();
        final String despos = posdes.getText().toString().trim();
        String uid = null;


        //checking if name and desc are empty
        if(TextUtils.isEmpty(namepos)) {
            nameempty.setText("* حقل مطلوب ");
        }else{
            nameempty.setText("");
        }

        if(TextUtils.isEmpty(despos)) {
            posdesempty.setText("* حقل مطلوب ");
        }else{
            posdesempty.setText("");
        }

        //displaying a progress dialog


if(firebaseAuth.getInstance().getCurrentUser()==null){
    startActivity(new Intent(this, LoginActivity.class));
}
else {
    progressDialog.setMessage("الرجاء الانتظار...");
    progressDialog.show();
    if(posdesempty.getText().toString().trim().isEmpty()&&nameempty.getText().toString().trim().isEmpty()) {
        uid = firebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Org");
//DatabaseReference currentU=mDatabase.child(uid);
        mDatabase.child(uid).child("posisiton").child("namepos").setValue(namepos);
        mDatabase.child(uid).child("posisiton").child("despos").setValue(despos);

        Toast.makeText(activityAddPosition.this, "تمت الاضافة ", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }
    else{
        Toast.makeText(activityAddPosition.this, "لم تتم الاضافه", Toast.LENGTH_LONG).show();
    }
    progressDialog.dismiss();
}

    }




}



