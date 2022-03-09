package com.example.androidapponlinevotingsystem.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.login.login_signinTabs
import com.google.android.material.floatingactionbutton.FloatingActionButton


class page3(fm:FragmentManager) : Fragment() {

    private lateinit var mButton: FloatingActionButton
    private var mfm: FragmentManager

    init {
     mfm = fm
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view:View =  inflater.inflate(R.layout.fragment_page3, container, false)


        mButton = view.findViewById(R.id.floatingActionButtonNext)
        mButton.setOnClickListener{

            val fragmentTransaction:FragmentTransaction = mfm.beginTransaction()
           fragmentTransaction.replace(R.id.frameLayout, login_signinTabs(mfm))
            fragmentTransaction.commit()
        }

        return view
    }

}