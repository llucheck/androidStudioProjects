package com.lewisu.leonard.cs.bookrating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class BookRatingListAdapter extends RecyclerView.Adapter<BookRatingListAdapter.RatingHolder>{


    class RatingHolder extends RecyclerView.ViewHolder{
        private final TextView bookNameTextView;
        private final TextView bookRatingTextView;
        private final TextView authorTextView;

        RatingHolder(View itemView) {
            super(itemView);
            bookNameTextView = itemView.findViewById(R.id.bookNameTextView);
            bookRatingTextView = itemView.findViewById(R.id.bookRatingTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);

        }
    }

    private final LayoutInflater inflater;
    private List<BookRating> ratings;
    private Context context;

    BookRatingListAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.list_row, parent, false);
        return new RatingHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingHolder holder, int position) {
        if (ratings != null){
            BookRating ratingObject = ratings.get(position);

            holder.bookNameTextView.setText(ratingObject.getBookName());
            holder.authorTextView.setText(ratingObject.getAuthorName());

            int rating = ratingObject.getRating();
            String ratingString = context.getResources().getQuantityString(R.plurals.star_rating, rating, rating);
            holder.bookRatingTextView.setText(ratingString);
            holder.itemView.setTag(ratingObject.getId());
        }

    }

    @Override
    public int getItemCount() {
        if(ratings != null){
            return ratings.size();
        }
        return 0;
    }

    void setRatings(List<BookRating> ratings){
        this.ratings = ratings;
        notifyDataSetChanged();
    }
}
