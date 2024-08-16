package com.example.postandgetdataretrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @POST("/add")
    Call<DataModel> addData(@Body DataModel dataModel);

    @GET("/get")
    Call<List<DataModel>> getData();

    @DELETE("/delete/{id}")

    Call<ResponseBody> setDelete(@Path("id") int id);
}
