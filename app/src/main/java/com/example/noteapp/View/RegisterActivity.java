package com.example.noteapp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.noteapp.Model.User;
import com.example.noteapp.ModelView.StartApi;
import com.example.noteapp.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText email;
    private TextInputEditText password,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerr = findViewById(R.id.Register);
        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        name = findViewById(R.id.name);
        registerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
                finish();
            }
        });
    }
    void register(){
        StartApi.getRetrofit().register(new User(name.getText().toString(),email.getText().toString(),password.getText().toString()))
                .enqueue(new Callback<User>(){
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull retrofit2.Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Register successfully",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Registered before",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(),"Bad connection",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}