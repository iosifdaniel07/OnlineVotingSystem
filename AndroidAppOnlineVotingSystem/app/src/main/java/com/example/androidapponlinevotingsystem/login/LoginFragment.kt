package com.example.androidapponlinevotingsystem.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.*
import com.example.androidapponlinevotingsystem.databinding.FragmentLoginBinding
import com.example.androidapponlinevotingsystem.main.MainActivity
import com.example.androidapponlinevotingsystem.serverProblemActivity.PublicKey_SessionKey
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*
import java.util.concurrent.CompletableFuture

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var text: TextView
    private lateinit var loginBtn: Button
    private lateinit var forgotPasswordBtn: Button
    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        forgotPasswordBtn = binding.root.findViewById(R.id.forgotPassword)
        loginBtn = binding.root.findViewById(R.id.btnLoggin)
        text = binding.root.findViewById(R.id.textViewError)
        text.visibility = View.INVISIBLE


        username = binding.root.findViewById(R.id.username)
        password = binding.root.findViewById(R.id.password)

        loginBtn.setOnClickListener {

            val retrofitService =  RetrofitServiceScalar()
            val userApi = retrofitService.getRetrofit() .create(UserApi::class.java)

            userApi.checkUser(username!!.text.toString()).enqueue(object :Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                   if(response.code() == 200){
                       if(response.body() == true) {

                           userApi.getPasswordForUsername(username.text.toString()).enqueue(object : Callback<String>{
                               override fun onResponse(call: Call<String>, response: Response<String>) {

                                   if(response.code() == 200){
                                       val sha256hex: String = DigestUtils.sha256Hex(password.text.toString())

                                       if(sha256hex == response.body()){
                                           Toast.makeText(activity,"AUTENTIFICAT",Toast.LENGTH_SHORT).show()

                                           val retrofitServiceScalar = RetrofitServiceScalar()
                                           val userApiScalar = retrofitServiceScalar.getRetrofit().create(UserApi::class.java)
                                           userApiScalar.getIdForUsername(username.text.toString()).enqueue(object : Callback<Long>{

                                               @RequiresApi(Build.VERSION_CODES.O)
                                               override fun onResponse(call: Call<Long>, response: Response<Long>) {

                                                   /*
                                                  PublicKey_SessionKey.generateSessionKey()
                                                   userApiScalar.sendSessionKey(username.text.toString(),PublicKey_SessionKey.publicEncryptSessionKey()).enqueue(object : Callback<Boolean>{

                                                       override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                                                           Log.e("response",response.code().toString())
                                                           if(response.code() == 200){

                                                               if(response.body() == true){
                                                                   val intent = Intent(activity,MainActivity::class.java)
                                                                   startActivity(intent)
                                                                   Log.e("session key",  Base64.getEncoder().encodeToString(PublicKey_SessionKey.sessionKey!!.encoded))
                                                               }
                                                           }


                                                       }

                                                       override fun onFailure(call: Call<Boolean>, t: Throwable) {
                                                          Log.e("session key", "failed send session key")
                                                       }
                                                   })
                                                 */
                                                   MyIdentity.id = response.body()!!
                                                   val intent = Intent(activity,MainActivity::class.java)
                                                   startActivity(intent)
                                               }

                                               override fun onFailure(call: Call<Long>, t: Throwable) {
                                                   Toast.makeText(activity,"fail connect: get id",Toast.LENGTH_SHORT).show()
                                               }

                                           })


                                       }else{
                                           text.visibility = View.VISIBLE
                                       }

                                   }else{
                                       text.visibility = View.VISIBLE
                                   }
                               }

                               override fun onFailure(call: Call<String>, t: Throwable) {
                                   Toast.makeText(activity,"fail connect",Toast.LENGTH_SHORT).show()
                               }
                           })

                       }else{
                           text.visibility = View.VISIBLE
                       }
                   }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.e("login","eroare")
                }
            })


        }

        forgotPasswordBtn.setOnClickListener{
           //resetare parola
        }

        return binding.root
    }

}