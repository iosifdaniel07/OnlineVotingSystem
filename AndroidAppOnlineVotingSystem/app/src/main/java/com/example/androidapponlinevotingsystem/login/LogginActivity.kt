package com.example.androidapponlinevotingsystem.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.RetrofitServiceScalar
import com.example.androidapponlinevotingsystem.data.UserApi
import com.example.androidapponlinevotingsystem.preview.StartAplicationFragment
import com.example.androidapponlinevotingsystem.serverProblemActivity.PublicKey_SessionKey
import com.example.androidapponlinevotingsystem.serverProblemActivity.ServerIsDownActivity
import org.apache.commons.codec.binary.Hex
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyGenerator


class LogginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggin)

        //Verificare server si obtinere cheie publica
//////////////////////////////////////////////////////////////////////////////////////////////////////

        val retrofitService =  RetrofitServiceScalar()
        val service = retrofitService.getRetrofit() .create(UserApi::class.java)

        service.publicKey().enqueue(object : Callback<String> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Toast.makeText(this@LogginActivity,"responde code: ${response.code()}", Toast.LENGTH_SHORT).show()

                if(response.code() == 200){

                   PublicKey_SessionKey.convertTopublicKey(response.body().toString())

                }else{
                    val intent = Intent(this@LogginActivity, ServerIsDownActivity::class.java)
                    startActivity(intent)
                }

            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(this@LogginActivity,"fail connect +", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LogginActivity, ServerIsDownActivity::class.java)
                startActivity(intent)
            }
        })
 ///////////////////////////////////////////////////////////////////////////////////////////////////

        val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,
            StartAplicationFragment(this,supportFragmentManager)
        )
        fragmentTransaction.commit()

    }

}