package com.example.androidapponlinevotingsystem.main

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.*
import com.example.androidapponlinevotingsystem.serverProblemActivity.ImageConvertor
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class VoteActivity : AppCompatActivity() {

    private lateinit var btnBack : FloatingActionButton
    private lateinit var imageCandidate : ImageView
    private lateinit var name: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var vote : TextView
    private lateinit var btnVote : Button

    private lateinit var candidat: CandidateGet

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote)


        var b: Bundle? = intent.extras
        var idUser = b!!.getString("idUser")
        Log.e("user",idUser!!)

        getCandidate(idUser!!)


        imageCandidate = findViewById(R.id.imageCandidateVote)
         name = findViewById(R.id.numeVote)
         descriptionTextView = findViewById(R.id.descriereVot)


        btnBack = findViewById(R.id.backbtnVote)
        btnBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        vote = findViewById(R.id.textVoted)
        vote.visibility = View.INVISIBLE

        btnVote = findViewById(R.id.buttonVote)
        btnVote.setOnClickListener {

            val retrofitService =  RetrofitService()
            val userApi = retrofitService.getRetrofit() .create(UserApi::class.java)
            userApi.getMyUser(MyIdentity.id).enqueue(object : retrofit2.Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {

                    if(response.code() == 200){

                        if(candidat.rol == "Presedinte"){

                            if(response.body()!!.president == 0){

                                val retrofitServiceScalar =  RetrofitService()
                                val userApi = retrofitServiceScalar.getRetrofit() .create(UserApi::class.java)
                                userApi.vote(candidat.idUser).enqueue(object : retrofit2.Callback<CandidateGet> {


                                    override fun onResponse(call: Call<CandidateGet>, response: Response<CandidateGet>) {

                                         if(response.code() == 200){
                                             vote.text = "Ai votat"
                                             vote.visibility = View.VISIBLE
                                         }else{
                                             Log.e("error","vote" + response.code())
                                         }
                                    }

                                    override fun onFailure(call: Call<CandidateGet>, t: Throwable) {
                                      Log.e("error","vote")
                                    }
                                })

                            }else{
                                vote.visibility = View.VISIBLE
                            }

                        }else if (candidat.rol == "Parlamentar"){

                            if(response.body()!!.parliament == 0){


                            }else{
                                vote.visibility = View.VISIBLE
                            }

                        }else if( candidat.rol == "EuroParlamentar"){

                            if(response.body()!!.europarliament == 0){


                            }else{
                                vote.visibility = View.VISIBLE
                            }

                        }

                    }else{
                        Log.e("cod",response.code().toString())
                    }

                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                   Log.e("error","get user errror")
                }
            })
        }

    }

    fun getCandidate(usernameId: String) {

        CoroutineScope(Dispatchers.Default).launch {

            val retrofitService =  RetrofitService()
            val userApi = retrofitService.getRetrofit() .create(UserApi::class.java)
            userApi.getCandidateForUsernameId(usernameId).enqueue(object : retrofit2.Callback<CandidateGet> {

                override fun onFailure(call: Call<CandidateGet>, t: Throwable) {
                    Log.e("eroare","obtinere detalii candidat")
                }

                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<CandidateGet>, response: Response<CandidateGet>) {

                    if(response.code() == 200){
                        Log.e("ok","ok")

                        candidat = response.body()!!
                        name.setText(candidat.name)
                        descriptionTextView.setText(candidat.description)
                        imageCandidate.setImageBitmap(ImageConvertor.getBitmapImage(java.util.Base64.getDecoder().decode(candidat.image.toByteArray())))


                    }else{
                        Log.e("eroare","obtinere detalii candidat" + response.code())
                    }
                }
            })

        }
    }
}