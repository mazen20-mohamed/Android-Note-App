package com.example.noteapp.View.ui.home;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.noteapp.Model.Note;
import com.example.noteapp.ModelView.ItemClickListener;
import com.example.noteapp.ModelView.NoteAdapter;
import com.example.noteapp.ModelView.StartApi;
import com.example.noteapp.R;
import com.example.noteapp.View.MainScreen;
import com.example.noteapp.View.NewNote;
import com.example.noteapp.View.UpdateNote;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment  implements ItemClickListener {

    private FragmentHomeBinding binding;
    private List<Note> note;
    private HomeFragment a;
    private String token;
    NoteAdapter noteCardAdapter;
    View root;
    LinearLayoutManager linearLayoutManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        a = this;
        linearLayoutManager = new GridLayoutManager(a.getActivity(),2);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(a.getContext());
        token =sharedPreferences.getString("token","default");
        reload();
        FloatingActionButton add = root.findViewById(R.id.fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewNote.class);
                startActivity(intent);
            }
        });
        MaterialToolbar topAppBar = root.findViewById(R.id.topAppBar2);
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.delete2){
                    StartApi.getRetrofit().deleteAllNote(token).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    return true;
                }
                return false;
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        reload();
    }

    void reload(){
        StartApi.getRetrofit().getAllNote(token).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if(response.isSuccessful()){
                    note = response.body();
                    noteCardAdapter = new NoteAdapter(note,a);
                    RecyclerView recyclerView =root.findViewById(R.id.rv_note);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(noteCardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void itemClick(int position) {
        Note note = noteCardAdapter.getNotes().get(position);
        if(note!=null){
            Intent intent = new Intent(getActivity(), UpdateNote.class);
            intent.putExtra("title",note.getTitle());
            intent.putExtra("body",note.getBody());
            intent.putExtra("id",note.getId());
            startActivity(intent);
        }
    }
}