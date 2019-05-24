package com.faruk.retrofitshimmer.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PetsDataList {

    @SerializedName("pets")
    private ArrayList<PetsData> petList;

    public ArrayList<PetsData> getPetsArrayList() {
        return petList;
    }

    public void setPetsArrayList(ArrayList<PetsData> weatherArrayList) {
        this.petList = weatherArrayList;
    }
}
