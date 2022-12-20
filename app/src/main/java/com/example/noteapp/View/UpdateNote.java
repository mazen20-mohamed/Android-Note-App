package com.example.noteapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapp.Model.Note;
import com.example.noteapp.ModelView.StartApi;
import com.example.noteapp.R;
import com.google.android.material.appbar.MaterialToolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        Intent intent=  getIntent();
        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");

        EditText editText = findViewById(R.id.title_update);
        EditText des = findViewById(R.id.description_update);
        editText.setText(title);
        String id = intent.getStringExtra("id");
        des.setText(body);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token =sharedPreferences.getString("token","default");
        Button btn = findViewById(R.id.elevatedButton2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note(editText.getText().toString(),des.getText().toString(),id);
                StartApi.getRetrofit().updateNote(token,note).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Note Updated",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Something Wrong",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                    }
                });
                finish();
            }
        });
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.delete){
                    StartApi.getRetrofit().deleteNote(token,id).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();

                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
                finish();
                return false;
            }
        });
    }
}
