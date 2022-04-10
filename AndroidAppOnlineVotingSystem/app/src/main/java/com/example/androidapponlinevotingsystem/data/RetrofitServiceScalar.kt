package com.example.androidapponlinevotingsystem.data

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitServiceScalar {

    private var retrofit: Retrofit

    init{
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8080")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    public  fun getRetrofit(): Retrofit {
        return retrofit
    }
}