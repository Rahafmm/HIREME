package com.example.hp.hireme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
    private DatabaseReference mDatabase8;
    private DatabaseReference mDatabase1;
    private ArrayList<Org> favArray;
    private ArrayList<position> s;

    private Org g;
    private String Id;
    private Org org;
    private String id;
    private String name;
   // private  Position i;

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
        name = intent.getExtras().getString("name", "");
        Id = intent.getExtras().getString("id", "");
s=new ArrayList<position>();
        name1 = (TextView) findViewById(R.id.name1);
        loc = (TextView) findViewById(R.id.loc);
        ca = (TextView) findViewById(R.id.ca);
        FavButton = (Button) findViewById(R.id.FavButton);

        textView5 = (TextView) findViewById(R.id.textView5);
        listView2=(ListView) findViewById(R.id.listView2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                final position selOrg = s.get(i);



                //Intent intent =new Intent(listOrg.this, viewInfoOrg.class);
                Intent intent = new Intent(viewInfoOrg.this, viewInfoPos.class);
                intent.putExtra("name",selOrg.getName());
                intent.putExtra("des",selOrg.getDes());
                intent.putExtra("id",id);

                    intent.putExtra("nameOrg",name);
                intent.putExtra("Id",name);
                    intent.putExtra("location",location);
                    intent.putExtra("idOrg",Id);
                    intent.putExtra("cat",cat);
                startActivity(intent);
            }
        });
        //imageView=(ImageView)findViewById(R.id.imageView);

        // t=(TextView)findViewById(R.id.t);
        name1.setText(name);
        loc.setText(location);
        ca.setText(cat);

        mDatabase8= FirebaseDatabase.getInstance().getReference().child("position");

      mDatabase8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    position i = postSnapshot.getValue(position.class);

                if (i.getNamorg().toUpperCase().equals(name.toUpperCase())) {
                        s.add(i);
                   }

                }
                String[] uplads = new String[s.size()];

                for (int i = 0; i < uplads.length; i++) {

                    uplads[i] = s.get(i).getName();


                }

                //disp laying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uplads);
                listView2.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





       //Fav Button
       FavButton.setOnClickListener(new View.OnClickListener() {



            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {

                String User_ID = firebaseAuth.getCurrentUser().getUid();


                mDatabase1 = FirebaseDatabase.getInstance().getReference().child("fav");
                mDatabase1.child(User_ID).child(name).setValue("1");
                Toast.makeText(viewInfoOrg.this, "تمت الإضافه بنجاح", Toast.LENGTH_LONG).show();

               //start

//                NotificationManager not=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//                Notification notif=new Notification.Builder(this)
//                .setSmallIcon(R.drawable.a1)
//                        .setContentTitle("Notification")
//                        .setContentText("you have added the Org to your fav list")
//                        .build();
//                not.notify(0,notif);
                //end
                // startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                //notify

//                NotificationCompat.Builder mBuilder =
//                        new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.a1)
//                        .setContentTitle("Notification")
//                        .setContentText("you have added the Org to your fav list");
//
//                NotificationManager mNotificationManger =
//                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//                NotificationManager.notify().
//                        mNotificationManager.notify(001,mBuilder.build());
//                sendNotification(this);
                getnotiy(view);

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void getnotiy(View view){
                NotificationManager not=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notif=new Notification.Builder(viewInfoOrg.this)
                .setSmallIcon(R.drawable.a1)
                        .setContentTitle("تنبية")
                        .setContentText("لقد قمت بإضافة شركة الى مفضلتك بنجاح")
                        .build();
                not.notify(0,notif);

            }


//           public void sendNotification(View.OnClickListener view){
//                               NotificationCompat.Builder mBuilder =
//                                       (NotificationCompat.Builder) new NotificationCompat.Builder((Context) view)
//                                       .setSmallIcon(R.drawable.a1)
//                                       .setContentTitle("Notification")
//                                       .setContentText("you have added the Org to your fav list");
//
//                NotificationManager mNotificationManger =
//                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//                NotificationManager.notify()
//                        .mNotificationManager.notify(001,mBuilder.build());
//
//            }




        });
    }


}