package com.example.androidapponlinevotingsystem.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.androidapponlinevotingsystem.R

class LogginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggin)



       //2
      //  val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
      //  fragmentTransaction.replace(R.id.frameLayout,login_signinTabs(supportFragmentManager))
       // fragmentTransaction.commit()

        //1
        val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,StartAplicationFragment(this,supportFragmentManager))
        fragmentTransaction.commit()
    }

}