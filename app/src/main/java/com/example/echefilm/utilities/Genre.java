package com.example.echefilm.utilities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Set;

public class Genre implements Serializable {
    int id;
    String genreName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }



}
