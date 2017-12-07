package com.example.hp.hireme.AccuontActivity;

import java.util.List;

/**
 * Created by Lama on 28/11/17.
 */

public class Position {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    private String des;


    public List<String> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<String> candidates) {
        this.candidates = candidates;
    }

    private List<String> candidates;

}
