package com.faruk.retrofitshimmer.data;

import com.google.gson.annotations.SerializedName;

public class PetsData {

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("title")
    private String title;

    @SerializedName("content_url")
    private String content_url;

    @SerializedName("date_added")
    private String date_added;

    public PetsData(String image_url, String title, String content_url, String date_added) {
        this.image_url = image_url;
        this.title = title;
        this.content_url = content_url;
        this.date_added = date_added;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
}
