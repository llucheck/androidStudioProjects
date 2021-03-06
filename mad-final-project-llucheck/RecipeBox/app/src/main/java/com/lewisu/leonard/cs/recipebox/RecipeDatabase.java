package com.lewisu.leonard.cs.recipebox;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();

    private static RecipeDatabase INSTANCE;

    static RecipeDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (RecipeDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RecipeDatabase.class, "recipe_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
