package com.example.androidapponlinevotingsystem.data

import retrofit2.Call
import retrofit2.http.*


interface UserApi {

    @POST("/user/save")
    fun save(@Body user: User):Call<User>

    @GET("/publickey")
    fun publicKey():Call<String>

    @GET("/checkUser/{username}")
    fun checkUser(@Path("username") username: String): Call<Boolean>

    @GET("/user/password/{username}")
    fun getPasswordForUsername(@Path(value="username") username: String) : Call<String>


}