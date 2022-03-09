package com.example.androidapponlinevotingsystem.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.androidapponlinevotingsystem.R

class StartAplicationFragment(fragmentActivity: FragmentActivity, fragmentManager: FragmentManager) : Fragment() {


   private lateinit var pageView: ViewPager2
   private  var mfragmentActivity: FragmentActivity
   private var mfragemntManager: FragmentManager

   init {
      mfragmentActivity = fragmentActivity
       mfragemntManager = fragmentManager
  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view:View = inflater.inflate(R.layout.fragment_start_aplication, container, false)

        pageView = view.findViewById(R.id.viewPagerStartAplication)
        pageView.adapter = ViewPagerAdapter(mfragmentActivity,mfragemntManager)

       return view

    }

}