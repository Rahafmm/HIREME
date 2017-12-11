package com.example.hp.hireme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.hp.hireme.AccuontActivity.Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUploadsActivity extends AppCompatActivity implements View.OnClickListener {

    //the listview
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] uploads;
    String[] uploads1;
    final Context context = this;
    boolean press;
    ImageButton undo;
    private FirebaseAuth firebaseAuth;
    String User_id;
    //database reference to get uploads data
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseReference1;
    //list to store uploads data
    List<Upload> uploadList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);

        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        undo=(ImageButton)findViewById(R.id.undo);
        undo.setOnClickListener(this);

        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //getting the upload
                final Upload upload = uploadList.get(i);

                //Opening the upload file in browser using the upload url
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(upload.getUrl()));
                startActivity(intent);


            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        User_id = firebaseAuth.getCurrentUser().getUid();
        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("candet").child(User_id).child("upload");

        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    Upload upload = dataSnapshot.getValue(Upload.class);
                    uploadList.add(upload);


                 uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getName();
                }

                //displaying it to list
                 adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                removeItemFromList(position);
                return true;


            }

        });

    }
    protected void removeItemFromList(int position) {
        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(
                ViewUploadsActivity.this);

        alert.setTitle("حذف");
        alert.setMessage("*إذا كنت تريد تعديل الملف قم برفع ملف جديد سيتم حذف الملف السابق,,هل تريد تعديل الملف ؟");
        alert.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TOD O Auto-generated method stub

                        // main code on after clicking yes
                        uploadList.remove(deletePosition);
                        Upload upload = new Upload("لايوجد سيرة ذاتية", "", User_id);
                        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference().child("candet").child(User_id).child("upload");
                        mDatabaseReference1.setValue(upload);

//                Intent in=new Intent(context, addCV.class);

                        uploads = new String[uploadList.size()];

                        for (int i = 0; i < uploads.length; i++) {
                            uploads[i] = uploadList.get(i).getName();
                        }

                        //displaying it to list
                        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                        listView.setAdapter(adapter);
                        Intent in = new Intent(context, addCV.class);
                        context.startActivity(in);
                        startActivity(new Intent(getApplicationContext(), addCV.class));
                    }


//                Intent in=new Intent(context, addCV.class);
//                context.startActivity(in);

        });
        alert.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

        alert.show();

    }
    public void onClick(View view) {

        if(view ==undo){
            startActivity(new Intent(this, profileCand.class));
        }
    }}





