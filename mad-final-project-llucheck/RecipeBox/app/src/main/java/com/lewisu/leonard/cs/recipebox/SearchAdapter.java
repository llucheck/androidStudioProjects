package com.lewisu.leonard.cs.recipebox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    class SearchHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView titleTextView;

        SearchHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recipe_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Recipe recipe = recipes.get(adapterPosition);
            clickHandler.onClick(recipe);
        }
    }

    private final LayoutInflater inflater;
    private List<Recipe> recipes;

    private final SearchAdapterOnClickHandler clickHandler;

    public interface SearchAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }

    public SearchAdapter(Context context, SearchAdapterOnClickHandler clickHandler) {
        inflater = LayoutInflater.from(context);
        this.clickHandler = clickHandler;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_row,parent,false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {

        if (recipes != null) {
            Recipe current = recipes.get(position);
            holder.titleTextView.setText(current.getRecipeTitle());
            holder.itemView.setTag(current.getId());
        }
    }

    @Override
    public int getItemCount() {

        if(recipes != null){
            return recipes.size();
        }
        return 0;
    }

    void setRecipes(List<Recipe> recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public void setRecipeData(List<Recipe> recipesData){
        recipes = recipesData;
        notifyDataSetChanged();
    }
}