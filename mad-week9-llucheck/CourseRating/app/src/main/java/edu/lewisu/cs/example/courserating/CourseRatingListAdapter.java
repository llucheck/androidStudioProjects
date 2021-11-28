package edu.lewisu.cs.example.courserating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CourseRatingListAdapter extends RecyclerView.Adapter<CourseRatingListAdapter.RatingHolder>{


    class RatingHolder extends RecyclerView.ViewHolder{
        private final TextView courseNameTextView;
        private final TextView courseRatingTextView;
        private final TextView instructorTextView;

        RatingHolder(View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.courseNameTextView);
            courseRatingTextView = itemView.findViewById(R.id.courseRatingTextView);
            instructorTextView = itemView.findViewById(R.id.courseInstructorTextView);

        }
    }

    private final LayoutInflater inflater;
    private List<CourseRating> ratings;
    private Context context;

    CourseRatingListAdapter(Context context){
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
            CourseRating ratingObject = ratings.get(position);

            holder.courseNameTextView.setText(ratingObject.getCourseName());
            holder.instructorTextView.setText(ratingObject.getInstructorName());

            int rating = ratingObject.getRating();
            String ratingString = context.getResources().getQuantityString(R.plurals.star_rating, rating, rating);
            holder.courseRatingTextView.setText(ratingString);
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

    void setRatings(List<CourseRating> ratings){
        this.ratings = ratings;
        notifyDataSetChanged();
    }
}
