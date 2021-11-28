package edu.lewisu.cs.example.courseratingfragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class CourseRatingAdapter extends FirebaseRecyclerAdapter<CourseRating, CourseRatingAdapter.RatingHolder> {

    class RatingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView courseNameTextView;


        RatingHolder(View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.courseNameTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(adapterPosition);
        }
    }



    private final TodoListAdapterOnClickHandler clickHandler;


    public interface TodoListAdapterOnClickHandler {
        void onClick(int position);
    }


    public CourseRatingAdapter(@NonNull FirebaseRecyclerOptions<CourseRating> options, TodoListAdapterOnClickHandler clickHandler) {
        super(options);
        this.clickHandler = clickHandler;

    }

    @Override
    public RatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_row, parent, false);
        return new CourseRatingAdapter.RatingHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull RatingHolder holder, int position, @NonNull CourseRating model) {
        holder.courseNameTextView.setText(model.getCourseName());

    }

}
