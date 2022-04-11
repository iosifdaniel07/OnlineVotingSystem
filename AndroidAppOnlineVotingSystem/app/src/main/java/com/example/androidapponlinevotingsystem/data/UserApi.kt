package com.example.androidapponlinevotingsystem.data

import retrofit2.Call
import retrofit2.Callback
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

    @GET("/user/id/{username}")
    fun getIdForUsername(@Path(value="username") username: String) : Call<Long>

    @GET("/user/numeprenume/{id}")
    fun getname(@Path(value="id") id: Long) : Call<String>

    @POST("/candidate")
    fun saveCandidate(@Body candidate: Candidate):Call<Candidate>

    @GET("/candidate/president")
    fun getCandidatesPresident() : Call<List<Candidate>>

    @POST("/user/sessionkey/{username}/{sessionkey}")
    fun sendSessionKey(@Path(value="username") username: String, @Path(value="sessionkey") sessionkey: String) : Call<Boolean>
}