package com.example.androidapponlinevotingsystem.preview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidapponlinevotingsystem.preview.page1
import com.example.androidapponlinevotingsystem.preview.page2
import com.example.androidapponlinevotingsystem.preview.page3

class ViewPagerAdapter(fragmentActivity:FragmentActivity,fm:FragmentManager) : FragmentStateAdapter(fragmentActivity){

    private var mfm: FragmentManager

    init {
        mfm = fm
    }

    override fun getItemCount(): Int {
       return 3;
    }

    override fun createFragment(position: Int): Fragment {
        when(position){

            0 -> {
               return page1(mfm)
            }
            1 -> {
                return page2(mfm)
            }
            else ->{
                return page3(mfm)
            }
        }
    }

}