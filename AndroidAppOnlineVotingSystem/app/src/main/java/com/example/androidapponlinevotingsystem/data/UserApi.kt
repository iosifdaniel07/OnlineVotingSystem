package com.example.androidapponlinevotingsystem.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/users/save")
    fun save(@Body user: User):Call<User>
}