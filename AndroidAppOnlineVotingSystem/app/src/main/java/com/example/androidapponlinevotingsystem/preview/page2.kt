package com.example.androidapponlinevotingsystem.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.login.login_signinTabs


class page2(fm: FragmentManager) : Fragment() {

    private var mfm: FragmentManager
    private lateinit var mSkipButton: Button

    init {
        mfm = fm
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_page2, container, false)

        mSkipButton = view.findViewById(R.id.skipbutton2)
        mSkipButton.setOnClickListener{

            val fragmentTransaction: FragmentTransaction = mfm.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, login_signinTabs(mfm))
            fragmentTransaction.commit()
        }

        return view
    }

}