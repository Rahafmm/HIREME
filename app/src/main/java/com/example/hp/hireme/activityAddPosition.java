package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activityAddPosition extends AppCompatActivity implements View.OnClickListener{
    private EditText posdes;
    private EditText posname;
    private TextView nameempty;
    private TextView posdesempty;
    private Button buttonAddPosition;
    private  DatabaseReference mDatabases;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
   public int inc=0;
    DatabaseReference mDatabase1;
    ArrayList<position> pos;
    position[] w;
    private Org os;
    List<position> s;
    List<position> s1;

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
        final String uid = firebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Org").child(uid);
//DatabaseReference currentU=mDatabase.child(uid);

      // mDatabase.child(namepos).child("name").setValue(namepos);
      // mDatabase.child(namepos).child("des").setValue(despos);
        mDatabase1= FirebaseDatabase.getInstance().getReference().child("Org");

        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Org o = postSnapshot.getValue(Org.class);
                    if (o.getuid().toUpperCase().equals(uid.toUpperCase())) {
                        os = o;
String k =os.getname();
                        position po = new position();
                        po.setName(namepos);
                        po.setDes(despos);
                        po.setNamorg(k);
                         mDatabases= FirebaseDatabase.getInstance().getReference().child("position");
                        mDatabases.child(os.getname()+"_"+namepos).setValue(po);
                        Toast.makeText(activityAddPosition.this, "تمت اضافه الوظيفه بنجاح", Toast.LENGTH_LONG).show();

                    }
                }

            }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





        // org.setposition(po);


       // Toast.makeText(activityAddPosition.this, "تمت الاضافة ", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
        }




    else{
        Toast.makeText(activityAddPosition.this, "لم تتم الاضافه", Toast.LENGTH_LONG).show();
    }
    progressDialog.dismiss();
}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.home:
            startActivity(new Intent(this, ProfileActivity.class) );
            return(true);


    }
        return(super.onOptionsItemSelected(item));
    }


}



