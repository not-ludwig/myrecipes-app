package com.example.finalproject;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class Recipe {
    public static ArrayList<Recipe> recipeArrayList = new ArrayList<>();
    private int id;
    private String name;
    private String description;
    private String image_url;
    private int user_id;

    public Recipe(int id, String name, String description, String image_url,
                  int user_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.user_id = user_id;
    }

    @NonNull
    @Override
    public String toString() {
    // return super.toString();
        return this.name;
    }

    public Recipe(String name, String description, String image_url, int
            user_id) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.user_id = user_id;
    }

    public Recipe(String string, String string1, String string2) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}