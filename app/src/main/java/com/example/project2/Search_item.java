package com.example.project2;


public class Search_item {
    String name_item;
    int ImageUrl;

    public Search_item(String name_item, int imageUrl) {
        this.name_item = name_item;
        ImageUrl = imageUrl;
    }

    public String getName_item() {
        return name_item;
    }

    public void setName_item(String name) {
        this.name_item = name_item;
    }

    public int getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(int imageUrl) {
        ImageUrl = imageUrl;
    }


}
