package com.example.postandgetdataretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.postandgetdataretrofit.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String BASE_URL = "http://192.168.0.110:3000";
    ActivityMainBinding actMain;
    Retrofit retrofit;
    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actMain = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(actMain.getRoot());

        actMain.btnDataAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DataActivity.class));
            }
        });

         actMain.btnPost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 name = actMain.etName.getText().toString();
                 email = actMain.etEmail.getText().toString();
                 if (name.isEmpty() || email.isEmpty()) return;

                 parseRetrofit();

                 RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                 Call<DataModel> listCall = retrofitService.addData(new DataModel(name,email,0));
                 listCall.enqueue(new Callback<DataModel>() {
                     @Override
                     public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                         Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                     }
                     @Override
                     public void onFailure(Call<DataModel> call, Throwable throwable) {

                     }
                 });
             }
         });

    }

    private void parseRetrofit() {
         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}