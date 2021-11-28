package com.lewisu.leonard.cs.recipebox;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {
    private Recipe recipe;
    private RecipeRespository respository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail_activity);

        EditText recipeTitleField = findViewById(R.id.recipe_title);
        recipeTitleField.addTextChangedListener(new TitleListener());

        EditText authorTitleField = findViewById(R.id.author_text);
        authorTitleField.addTextChangedListener(new AuthorListener());

        EditText ingredientsField = findViewById(R.id.ingredient_text_view);
        ingredientsField.addTextChangedListener(new IngredientListener());

        final EditText directionsField = findViewById(R.id.direction_text_view);
        directionsField.addTextChangedListener(new DirectionListener());

        Button addEditButton = findViewById(R.id.save_button);

        respository = new RecipeRespository(getApplication());
        int id = getIntent().getIntExtra("id", -1);

        if(id != 0){
            recipe = respository.getRecipes(id);
        }else if(id == 0){
            recipe = getIntent().getParcelableExtra("recipe");

        }

        if(recipe != null) {
            recipeTitleField.setText(recipe.getRecipeTitle());
            authorTitleField.setText(recipe.getAuthor());
            ingredientsField.setText(recipe.getIngredients());
            Log.d("Ingredients", recipe.getIngredients());
            directionsField.setText(recipe.getDirections());
            if(id != 0){
                addEditButton.setText(R.string.update);
                addEditButton.setOnClickListener(new OnUpdateButtonClick());
            }
            else{
                addEditButton.setOnClickListener(new OnAddButtonClick());
            }
        }else{
            recipe = new Recipe();
            addEditButton.setOnClickListener(new OnAddButtonClick());
        }

    }

    private class TitleListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            recipe.setRecipeTitle(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class AuthorListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            recipe.setAuthor(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    private class IngredientListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            recipe.setIngredients(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    private class DirectionListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            recipe.setDirections(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class OnAddButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v){
            respository.insert(recipe);
            finish();
        }
    }
    private class OnUpdateButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            respository.update(recipe);
            finish();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch(id){
            case R.id.delete:
                respository.deleteRecipes(recipe);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}