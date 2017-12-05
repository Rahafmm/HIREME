package com.example.hp.hireme.AccuontActivity;

/**
 * Created by HP on 11/15/2017.
 */

public class Upload {

    public String name;
    public String url;

    public String getId() {
        return id;
    }



    private String id;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url,String id) {
        this.id=id;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
