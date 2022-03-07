package com.example.androidapponlinevotingsystem.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.androidapponlinevotingsystem.R
import com.google.android.material.tabs.TabLayout

class LogginActivity : AppCompatActivity() {

    lateinit var pageView: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggin)

        pageView = findViewById(R.id.viewPager)
        pageView.adapter = ViewPagerAdapter(this)
    }
}