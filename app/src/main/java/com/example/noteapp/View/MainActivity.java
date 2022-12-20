package com.example.noteapp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.noteapp.Model.User;
import com.example.noteapp.ModelView.StartApi;
import com.example.noteapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextInputEditText email;
    private TextInputEditText password;
    public String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);
        Button login = findViewById(R.id.login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    void login(){
        StartApi.getRetrofit().login(new User(email.getText().toString(),password.getText().toString()))
                .enqueue(new Callback<User>(){
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull retrofit2.Response<User> response) {
                        if(response.isSuccessful()){
//                            Toast.makeText(getApplicationContext(),"Welcome Back",Toast.LENGTH_SHORT).show();
                            token ="Bearer ";
                            token+=response.body().getToken();
                            editor.putString("token",token);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"email or password is incorrect",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(),"Bad connection",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
