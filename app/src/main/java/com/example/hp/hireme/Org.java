package com.example.hp.hireme;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lama on 03/11/17.
 */

public class Org  {
    private String Email;
    private String name;
    private String Location;
    public Uri imgurl;
    private String pass;
    private String cat;
    private String uid;




    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +

                ", Location='" + Location + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", email='" + Email + '\'' +
                ", uid='" + uid + '\'' +
                ", cat='" + cat + '\'' +
                '}';
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setpass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getname() {
        return name;
    }

    public void setname(String nname) {
        this.name = nname;
    }

    public String getcat() {
        return cat;
    }

    public void setcat(String catt) {
        this.cat = catt;
    }


    public String getLocation() {
        return Location;
    }

    public void setLocation(String loc) {
        this.Location = loc;
    }



}
