package com.example.androidapponlinevotingsystem.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LoginSignupAdapter(suportFragmentManager: FragmentManager) : FragmentPagerAdapter(suportFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getCount(): Int {
       return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragement: Fragment, title: String){
       mFragmentList.add(fragement)
       mFragmentTitleList.add(title)
    }

}