package com.lewisu.leonard.cs.recipebox;

import android.app.Application;

import java.util.List;

public class RecipeRespository {
    private RecipeDao recipeDao;

    public RecipeRespository(Application application) {
        RecipeDatabase db = RecipeDatabase.getDatabase(application);
        recipeDao = db.recipeDao();
    }

    List<Recipe> getAllRecipes(){
        return recipeDao.getAllRecipes();
    }

    Recipe getRecipes(int id){
        return recipeDao.getRecipe(id);
    }

    void deleteRecipes(Recipe recipe) {
        recipeDao.deleteRecipes(recipe);
    }

    void insert(Recipe recipe){
        recipeDao.insert(recipe);
    }

    void update(Recipe recipe){
        recipeDao.UpdateRecipes(recipe);
    }

    void initialize(){
        recipeDao.deleteAll();
    }
}

