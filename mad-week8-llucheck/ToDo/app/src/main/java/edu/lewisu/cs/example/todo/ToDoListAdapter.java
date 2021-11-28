package edu.lewisu.cs.example.todo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoHolder> {

    class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView titleTextView;

        ToDoHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            ToDo toDo = toDos.get(adapterPosition);
            clickHandler.onClick(toDo);
        }
    }

    private final LayoutInflater inflater;
    private List<ToDo> toDos;

    private final ToDoListAdapterOnClickHandler clickHandler;

    public interface ToDoListAdapterOnClickHandler {
        void onClick(ToDo toDO);
    }

    public ToDoListAdapter(Context context, ToDoListAdapterOnClickHandler clickHandler) {
        inflater = LayoutInflater.from(context);
        this.clickHandler = clickHandler;
    }

    @Override
    public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_row,parent,false);
        return new ToDoHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoHolder holder, int position) {

        if (toDos != null) {
            ToDo current = toDos.get(position);
            holder.titleTextView.setText(current.getTitle());
            holder.itemView.setTag(current.getId());
        }
    }

    @Override
    public int getItemCount() {

        if(toDos != null){
            return toDos.size();
        }
        return 0;
    }

    void setToDos(List<ToDo> toDos){
        this.toDos = toDos;
        notifyDataSetChanged();
    }
}
