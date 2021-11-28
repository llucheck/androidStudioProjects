package com.lewisu.leonard.cs.recipebox;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insert(Recipe... recipe);

    @Update
    void UpdateRecipes(Recipe... recipe);

    @Delete
    void deleteRecipes(Recipe... recipe);

    @Query("DELETE FROM recipe_table")
    void deleteAll();

    @Query("SELECT * FROM recipe_table WHERE id= :id")
    Recipe getRecipe(int id);

    @Query("SELECT * FROM recipe_table ORDER BY recipe_title ASC")
    List<Recipe> getAllRecipes();
}


