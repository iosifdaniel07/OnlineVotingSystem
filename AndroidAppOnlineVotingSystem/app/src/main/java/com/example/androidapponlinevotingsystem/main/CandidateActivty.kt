package com.example.androidapponlinevotingsystem.main

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.*
import com.example.androidapponlinevotingsystem.serverProblemActivity.ImageConvertor
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.create
import javax.security.auth.callback.Callback


class CandidateActivty : AppCompatActivity() {

   private lateinit var uploadbtn : FloatingActionButton
   private lateinit var createBtn: Button
   private lateinit var imagecover : ImageView
   private lateinit var bitmapImage: Bitmap
   private lateinit var description: EditText
   var SELECT_PICTURE = 200
   private var checkDescription: Boolean = false
   private var checkImage: Boolean = false
   private var checkRol: Boolean = false
   private lateinit var radiobtn: RadioGroup
   private lateinit var btnBack:  FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_activty)

        uploadbtn = findViewById(R.id.btnUploadImage)
        imagecover = findViewById(R.id.imageCandidate)
        radiobtn  = findViewById(R.id.radioGroup)
        createBtn = findViewById(R.id.buttonCandidate)
        description = findViewById(R.id.description)
        btnBack = findViewById(R.id.backbtn)
        uploadbtn.setOnClickListener {

            val i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
        }

        createBtn.setOnClickListener {

            createCandidate()

        }

        btnBack.setOnClickListener {
            val intent = Intent(this@CandidateActivty, MainActivity::class.java)
            startActivity(intent)
        }

    }



   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {
                val selectedImageUri = data?.data
                if (null != selectedImageUri) {

                   bitmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri)
                   imagecover.setImageURI(selectedImageUri)
                   checkImage = true
                }
            }
        }
    }


    private fun createCandidate(){

        CoroutineScope(Dispatchers.Default).launch {

            var candidate: Candidate = Candidate()

            if(description.text.length > 10){
                checkDescription = true
            }

            if( radiobtn.getCheckedRadioButtonId() == R.id.presedinte){
                candidate.rol = "Presedinte"
                checkRol = true
            }else if(radiobtn.getCheckedRadioButtonId() == R.id.parlamentar){
                candidate.rol = "Parlamentar"
                checkRol = true
            }else if(radiobtn.getCheckedRadioButtonId() == R.id.euroParlamentar){
                candidate.rol = "EuroParlamentar"
                checkRol = true
            }

            if(checkImage  && checkRol  && checkDescription ){
                candidate.description = description.text.toString()
                candidate.idUser = MyIdentity.id.toString()
                candidate.image = ImageConvertor.getBitmapAsByteArray(bitmapImage)

                val retrofitService = RetrofitServiceScalar()
                val userApi = retrofitService.getRetrofit().create(UserApi::class.java)
                userApi.getname(MyIdentity.id).enqueue(object : retrofit2.Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {

                        if(response.code() == 200){
                            candidate.name = response.body().toString()

                            val retrofitServicea = RetrofitService()
                            val candidaApi = retrofitServicea.getRetrofit().create(UserApi::class.java)
                            candidaApi.saveCandidate(candidate).enqueue(object : retrofit2.Callback<Candidate> {
                                override fun onResponse(call: Call<Candidate>, response: Response<Candidate>) {


                                     val intent = Intent(this@CandidateActivty, MainActivity::class.java)
                                      startActivity(intent)
                                }

                                override fun onFailure(call: Call<Candidate>, t: Throwable) {

                                    if(response.code() == 200) {


                                          val intent = Intent(this@CandidateActivty, MainActivity::class.java)
                                          startActivity(intent)
                                    }
                                }
                            })

                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.e("creare candidat","eroare")
                    }
                })

            }

        }
    }
}