package com.example.hp.hireme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.hireme.AccuontActivity.Candidate;
import com.example.hp.hireme.AccuontActivity.Position;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lama on 18/11/17.
 */

public class viewInfoOrg extends AppCompatActivity{

    private TextView name1;
    private TextView loc;
    private TextView ca;
    private TextView m;
    private Button FavButton;
    private TextView textView5;
    private ListView listView2;
    private ProgressDialog progressDialog;
    DatabaseReference mDatabase;
    DatabaseReference mDatabase1;
    ArrayList<Org> favArray;
    List<Position> s;
    Org g;
    String Id;
    Org org;
    String id;

    //TextView t;
    private ImageView imageView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);

        firebaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        final String cat = intent.getExtras().getString("cat", "");
        final String location = intent.getExtras().getString("location", "");
        final String name = intent.getExtras().getString("name", "");
        Id = intent.getExtras().getString("id", "");

        name1 = (TextView) findViewById(R.id.name1);
        loc = (TextView) findViewById(R.id.loc);
        ca = (TextView) findViewById(R.id.ca);
        FavButton = (Button) findViewById(R.id.FavButton);

        textView5 = (TextView) findViewById(R.id.textView5);
        listView2=(ListView) findViewById(R.id.listView2);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                final Position selOrg = s.get(i);
                if (!s.get(i).getName().equals("none")) {


                //Intent intent =new Intent(listOrg.this, viewInfoOrg.class);
                Intent intent = new Intent(viewInfoOrg.this, viewInfoPos.class);
                intent.putExtra("name",selOrg.getName());
                intent.putExtra("des",selOrg.getDes());
                intent.putExtra("id",id);
                    intent.putExtra("nameOrg",name);
                    intent.putExtra("location",location);
                    intent.putExtra("idOrg",Id);
                    intent.putExtra("cat",cat);
                startActivity(intent);}
            }
        });
        //imageView=(ImageView)findViewById(R.id.imageView);

        // t=(TextView)findViewById(R.id.t);
        name1.setText(name);
        loc.setText(location);
        ca.setText(cat);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Org");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Org o = postSnapshot.getValue(Org.class);
                    id=postSnapshot.getKey();
                    if(o.getname().toUpperCase().equals(name.toUpperCase())){
                        s=o.getposition();}
                }

                String[] uploads = new String[s.size()];

                for (int i = 0; i < uploads.length; i++) {
if(!s.get(i).getName().equals("none"))
                    uploads[i] = s.get(i).getName();
                    else {
    uploads[i]="";
                    }

                }

                //disp laying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView2.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Fav Button
      /*  FavButton.setOnClickListener(new View.OnClickListener() {


            // FirebaseStorage storage = FirebaseStorage.getInstance();
            // StorageReference storageRef = storage.getReference().child("Org").child(Id);

            // String m= storageRef.child("pic").getDownloadUrl().toString();
            // t.setText(m);
            //Picasso.with(viewInfoOrg.this).load(m).into(imageView);


            @Override
            public void onClick(View view) {
                try {


                    //m.setText("bnnnnn");
                    if (view == FavButton) {
                        //FavButton.setText("dee");
                        final String User_ID = firebaseAuth.getCurrentUser().getUid();

                        mDatabase = FirebaseDatabase.getInstance().getReference().child("candet");
                        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("Org");

                        mDatabase1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    g = postSnapshot.getValue(Org.class);
                                    if (g != null)
                                        if (g.getuid().equals(Id)) {

                                            org = postSnapshot.getValue(Org.class);
                                        }

                                }
                                //FavButton.setText("ll");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        mDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    Candidate can = postSnapshot.getValue(Candidate.class);
                                    if (can != null)
                                        if (can.getUid().equals(User_ID)) {
//m.setText("b");

                                            can.setFav(org);
                                            mDatabase.child(User_ID).child("fav").setValue(org);
                                        }

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                }catch (Exception e){System.out.print("error");}
            }
        });*/
    }

}