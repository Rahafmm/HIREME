package com.example.hp.hireme.AccuontActivity;

import java.util.ArrayList;

/**
 * Created by l8een on 11/21/2017.
 */

public class Candidate {
        int count=0;
        private String name;
        private String pass;
        private String uid;

    public ArrayList<Org> getFav() {
        return fav;
    }

    public void setFav(Org g) {
       fav.set(count, g);
        count++;
    }

    ArrayList<Org> fav;


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


        public String getname() {
            return name;
        }

        public void setname(String nname) {
            this.name = nname;
        }
    }


