package com.example.hp.hireme;

import com.example.hp.hireme.AccuontActivity.Upload;

/**
 * Created by l8een on 11/21/2017.
 */

public class Candidate {
        int count=0;
        private String name;
        private String pass;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;
        //private String uid;

    public Upload getupload() {
        return upload;
    }

    public void setupload(Upload upload) {
        this.upload = upload;
    }

    private Upload upload;





   // ArrayList<Org> fav;


       /* public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }*/

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


