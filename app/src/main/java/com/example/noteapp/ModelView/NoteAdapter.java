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

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteCardViewHolder>
{
    private List<Note> note;
    private ItemClickListener clickListener;

    public class NoteCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public TextView description;
        public TextView date;
        public NoteCardViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description =itemView.findViewById(R.id.description);
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

    public NoteAdapter(List<Note> notes,ItemClickListener itemClickListener) {
        this.note = notes;
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.note_card,parent,false);
        return new NoteCardViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteCardViewHolder holder, int position) {
        holder.title.setText(note.get(position).getTitle());
        holder.description.setText(note.get(position).getBody());
        holder.date.setText(String.valueOf(note.get(position).getDateModified()));
    }
    @Override
    public int getItemCount() {
        return note.size();
    }

    public List<Note> getNotes() {
        return note;
    }
}
