package com.lewisu.leonard.cs.recipebox;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="recipe_title")
    private String recipeTitle;
    private String Author;
    private String Ingredients;
    private String Directions;

    @Ignore
    public Recipe(){
    }

    public Recipe(String recipeTitle, String Author, String Ingredients, String Directions) {
        this.recipeTitle = recipeTitle;
        this.Author = Author;
        this.Ingredients = Ingredients;
        this.Directions = Directions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getDirections() {
        return Directions;
    }

    public void setDirections(String directions) {
        Directions = directions;
    }

    protected Recipe(Parcel in){
        recipeTitle = in.readString();
        Author = in.readString();
        Ingredients = in.readString();
        Directions = in.readString();
    }

    @Override
    public int describeContents(){return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(recipeTitle);
        dest.writeString(Author);
        dest.writeString(Ingredients);
        dest.writeString(Directions);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>(){
        @Override
        public Recipe createFromParcel(Parcel in){return new Recipe(in);}

        @Override
        public Recipe[] newArray(int size){return new Recipe[size];}
    };
}

