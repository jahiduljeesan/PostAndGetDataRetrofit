package com.example.postandgetdataretrofit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.postandgetdataretrofit.databinding.ActivityDataBinding;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataActivity extends AppCompatActivity implements DataListAdapter.ButtonHandeller{

    ActivityDataBinding actData;
    DataListAdapter dataListAdapter;
    List<DataModel> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actData = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(actData.getRoot());


        Call<List<DataModel>> listCall = RetrofitHandeler.getRetrofit().getData();

        listCall.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                dataList = response.body();
                dataListAdapter = new DataListAdapter(DataActivity.this,dataList,DataActivity.this);
                actData.dataList.setLayoutManager(new LinearLayoutManager(DataActivity.this));
                actData.dataList.setAdapter(dataListAdapter);
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable throwable) {

            }
        });


    }


    @Override
    public void onDeleteClick(int position) {
        Call<ResponseBody> call = RetrofitHandeler.getRetrofit().setDelete(dataList.get(position).getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(DataActivity.this, "Item is deleted", Toast.LENGTH_SHORT).show();
                dataListAdapter.dataList.remove(position);
                dataListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}