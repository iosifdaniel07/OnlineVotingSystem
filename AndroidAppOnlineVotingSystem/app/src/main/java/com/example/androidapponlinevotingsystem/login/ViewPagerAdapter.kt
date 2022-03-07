package com.example.androidapponlinevotingsystem.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity:FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int {
       return 3;
    }

    override fun createFragment(position: Int): Fragment {
        when(position){

            0 -> {
               return page1()
            }
            1 -> {
                return page2()
            }
            else ->{
                return page3()
            }
        }
    }

}