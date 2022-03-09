package com.example.androidapponlinevotingsystem.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.androidapponlinevotingsystem.R
import com.google.android.material.tabs.TabLayout


class login_signinTabs(fm:FragmentManager) : Fragment() {

    private lateinit var pageViewLogin: ViewPager
    private lateinit var tabLoginSignUp: TabLayout
    private var fm: FragmentManager

    init {
        this.fm = fm
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view:View = inflater.inflate(R.layout.fragment_login_signin_tabs, container, false)

      setUpTabLoginSignUp(view)

        return view
    }


    private fun setUpTabLoginSignUp(view:View){

        var madapter : LoginSignupAdapter = LoginSignupAdapter(fm)
        madapter.addFragment(LoginTabFragment(),"Login")
        madapter.addFragment(SignupTabFragment(),"SignUp")
        pageViewLogin = view.findViewById(R.id.viewPagerFragmentSignupLogin)
        pageViewLogin.adapter = madapter
        tabLoginSignUp = view.findViewById(R.id.tabLayout )
        tabLoginSignUp.setupWithViewPager(pageViewLogin)

    }
}