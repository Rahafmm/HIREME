package com.example.hp.hireme;

/**
 * Created by Lama on 05/11/17.
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

    /**
     * Created by User on 2/8/2017.
     */

    public class ProfileEditActivity2 extends AppCompatActivity implements View.OnClickListener {

        public static final int REQ_CODE_PICK_IMAGE = 500;
        //add Firebase Database stuff
        private FirebaseDatabase mFirebaseDatabase;
        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener mAuthListener;
        private DatabaseReference myRef;
        private  String userID;
        private DatabaseReference mDatabase8;
        private FirebaseDatabase firebaseDatabase;
        private DatabaseReference databaseReference;
        private ProgressDialog progressDialog;
        Spinner gspinner;
        private String loca="";
        private String ca;
        private String n;
        //private EditText profile_name;
        private EditText profile_password;
        private EditText profile_location;
        DatabaseReference mDatabase;
        private Button save;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.edit);

           // profile_name = (EditText) findViewById(R.id.profile_name);
            profile_password = (EditText) findViewById(R.id.profile_password);
            profile_location = (EditText) findViewById(R.id.profile_location);
            save=(Button) findViewById(R.id.buttonSave);

            gspinner=(Spinner)findViewById(R.id.editcat);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                    createFromResource(this, R.array.catogary, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gspinner.setAdapter(adapter);

            firebaseDatabase=FirebaseDatabase.getInstance();

            progressDialog = new ProgressDialog(this);
            mAuth = FirebaseAuth.getInstance();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();
            mDatabase= FirebaseDatabase.getInstance().getReference();
            FirebaseUser user = mAuth.getCurrentUser();
            userID = user.getUid();
            mDatabase8= FirebaseDatabase.getInstance().getReference().child("Org");

            mDatabase8.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Org i = postSnapshot.getValue(Org.class);

                        if (i.getuid().toUpperCase().equals(userID.toUpperCase())) {
                            if(i.getLocation()!=null)
                                loca=i.getLocation();
                            if(i.getcatgory()!= null)
                                ca=i.getcatgory();
                            n=postSnapshot.getKey();
                        }


                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            profile_location.setText(loca);

            save.setOnClickListener(this);
/////// for image
            findViewById(R.id.profile_image).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * if do not have the permission for reading the external storage
                     * ask the user for the permission
                     * it will then go to "onRequestPermissionsResult"
                     * */

                    if (!PermissionUtil.checkPermission(ProfileEditActivity2.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        PermissionUtil.requestReadExternalStoragePermission(ProfileEditActivity2.this);

                    } else { // we already have the storage, pick the image
                        pickImage();
                    }
                }
            });

/////// end image
        }
/////// pic
        private void pickImage() {

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "Select picture"),
                    REQ_CODE_PICK_IMAGE);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               @NonNull String[] permissions,
                                               @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            /**
             * if the permission is granted, pick image for external storage
             * */



                pickImage();

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            /**
             * if we get the iamge and return back to this activity
             * update the user picture using showImage
             * edit user picture step 3
             * */
            if (requestCode == REQ_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();

                if (imageUri != null) {
                    showImage(imageUri);
                }
            }
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference().child("Org").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            storageRef.child("pic").putFile((Uri)(findViewById(R.id.profile_image)).getTag());

        }

        private void showImage(Uri imageUri) {
            ImageView imageView = (ImageView) findViewById(R.id.profile_image);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            // a tag use to identify views
            // this tag is used for storing the image uri
            imageView.setTag(imageUri);
            ImageUtil.loadImage(this, imageUri, imageView);
        }
        /////// end pic

        @Override
        public void onClick(View v) {
            String cat="0";

            if(!(TextUtils.isEmpty(profile_location.getText().toString().trim())))
            mDatabase.child("Org").child(n).child("location").setValue(profile_location.getText().toString());

            FirebaseStorage storage = FirebaseStorage.getInstance();
           // StorageReference storageRef = storage.getReference().child("Org").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
           // storageRef.child("pic").putFile((Uri)(findViewById(R.id.profile_image)).getTag());

           // mDatabase.child("Org").child(userID).child("Location").setValue((findViewById(R.id.profile_image)).getTag());

            cat =String.valueOf(gspinner.getSelectedItemId());

            if(!(cat.equals("0"))){
            String catgory="";
            switch (cat) {
                case "1":
                    catgory = "IT";
                    break;
                case "2":
                    catgory = "health";
                    break;
                case "3":
                    catgory = "industrial";
                    break;
                case "4":
                    catgory = "food";
                    break;
                case "5":
                    catgory = "Busines";
                    break;
                case "6":
                    catgory = "travel";
                    break;
            }
            mDatabase.child("Org").child(n).child("catgory").setValue(catgory);}

            if(!(TextUtils.isEmpty(profile_password.getText().toString().trim()))){
                FirebaseUser user1 =FirebaseAuth.getInstance().getCurrentUser();
                if(user1!=null)
                {
                    progressDialog.setMessage("يتم حفظ التغييرات، الرجاء الانتظار..");
                    progressDialog.show();
                    mDatabase= FirebaseDatabase.getInstance().getReference();
                    String pass=profile_password.getText().toString().trim();

                    user1.updatePassword(pass)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                String pass=profile_password.getText().toString().trim();
                                mDatabase.child("Org").child(n).child("pass").setValue(pass);

                                //display message to the user here
                                Toast.makeText(ProfileEditActivity2.this, "تمت العملية بنجاح", Toast.LENGTH_LONG).show();

                            } else {
                                //display some message here
                                Toast.makeText(ProfileEditActivity2.this, "هناك خلل.. ", Toast.LENGTH_LONG).show();

                            }
                            progressDialog.dismiss();
                        }
                    });

                }
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
