package com.example.hp.hireme;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.hp.hireme.AccuontActivity.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lama on 03/11/17.
 */

public class Org   {

    private String name;
    private String Location;
    private String pass;
    private String cat;
    private String uid;
    private List<Position> position;

    public List<Position> getposition() {
        return position;
    }

    public void setposition(List<Position> position) {
        this.position = position;
    }


   // private int p;





   /* public Position[] getposition() {
        return position;
    }

    public void setposition(Position pos) {
        position[p++] = pos;
    }*/


    public String getuid() {
        return uid;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setpass(String pass) {
        this.pass = pass;
    }


    public String getname() {
        return name;
    }

    public void setname(String nname) {
        this.name = nname;
    }

    public String getcatgory() {
        return cat;
    }

    public void setcatgory(String catt) {
        this.cat = catt;
    }


    public String getLocation() {
        return Location;
    }

    public void setLocation(String loc) {
        this.Location = loc;
    }



}
