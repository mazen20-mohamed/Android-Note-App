package com.example.noteapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.noteapp.Model.Note;
import com.example.noteapp.ModelView.StartApi;
import com.example.noteapp.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateToDo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do);

        Button btn = findViewById(R.id.save);
        TextInputEditText editText = findViewById(R.id.title_new2);
        TextInputEditText des = findViewById(R.id.alarm_date);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token =sharedPreferences.getString("token","default");
        CreateToDo a = this;
        MaterialToolbar topAppBar = findViewById(R.id.topTo);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note(editText.getText().toString());
                note.setAlarmDate(des.getText().toString());
                StartApi.getRetrofit().addNote(token,note).enqueue(
                        new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(a.getApplicationContext(),"To Do Added",Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        }
                );
                finish();
            }
        });
    }
}
