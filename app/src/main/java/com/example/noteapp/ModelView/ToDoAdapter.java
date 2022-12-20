package com.example.noteapp.ModelView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.Model.Note;
import com.example.noteapp.R;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoCardViewHolder>
{
    private List<Note> note;
    private ItemClickListener clickListener;

    public class ToDoCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public TextView date;
        public ToDoCardViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2);
            date = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int v =getAdapterPosition();
            if(v!=RecyclerView.NO_POSITION){
                clickListener.itemClick(v);
            }
        }
    }

    public ToDoAdapter(List<Note> notes,ItemClickListener itemClickListener) {
        this.note = notes;
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ToDoAdapter.ToDoCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.to_do_card,parent,false);
        return new ToDoCardViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.ToDoCardViewHolder holder, int position) {
        holder.title.setText(note.get(position).getTitle());
        holder.date.setText(String.valueOf(note.get(position).getAlarmDate()));
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public List<Note> getNotes() {
        return note;
    }
}
