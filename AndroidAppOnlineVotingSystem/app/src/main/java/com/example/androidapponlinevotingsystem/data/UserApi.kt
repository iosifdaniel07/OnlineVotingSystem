package com.example.androidapponlinevotingsystem.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import java.util.ArrayList


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
    fun getCandidatesPresident() : Call<List<CandidateGet>>

    @GET("/candidate/parliamentary")
    fun getCandidatesParliamentary() : Call<List<CandidateGet>>

    @GET("/candidate/europarliamentary")
    fun getCandidatesEuroParliamentary() : Call<List<CandidateGet>>

    @POST("/user/{username}")
    fun sendSessionKey(@Path(value="username") username: String, @Body sessionkey: String) : Call<Boolean>

    @GET("candidate/{id}")
    fun getCandidateForUsernameId(@Path(value="id") id: String) : Call<CandidateGet>

    @GET("/user/{id}")
    fun getMyUser(@Path(value = "id") id: Long): Call<User>

    @POST("candidate/vote/{id}")
    fun vote(@Path("id") idCandidate: String) : Call<CandidateGet>
}