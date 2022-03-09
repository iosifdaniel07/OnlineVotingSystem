package com.example.androidapponlinevotingsystem.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.preview.StartAplicationFragment

class LogginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggin)

        val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,
            StartAplicationFragment(this,supportFragmentManager)
        )
        fragmentTransaction.commit()

    }

}