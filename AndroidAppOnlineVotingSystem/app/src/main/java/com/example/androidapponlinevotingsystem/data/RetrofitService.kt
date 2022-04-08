package com.example.androidapponlinevotingsystem.data

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private var retrofit: Retrofit

    init{
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8080")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    public  fun getRetrofit(): Retrofit{
        return retrofit
    }
}