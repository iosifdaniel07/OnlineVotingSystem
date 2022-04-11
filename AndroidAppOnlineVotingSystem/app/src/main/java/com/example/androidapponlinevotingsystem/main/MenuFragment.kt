package com.example.androidapponlinevotingsystem.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.MyIdentity
import com.example.androidapponlinevotingsystem.login.LogginActivity

class MenuFragment : Fragment() {

    private lateinit var logout : Button
    private lateinit var candidate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_menu, container, false)

        logout = view.findViewById(R.id.buttonLogout)
        logout.setOnClickListener{

            MyIdentity.id = -1
            val intent = Intent(activity,LogginActivity::class.java)
            startActivity(intent)
        }

        candidate = view.findViewById(R.id.buttonCandidate)
        candidate.setOnClickListener {

            val intent = Intent(activity,CandidateActivty::class.java)
            startActivity(intent)

        }

        return view
    }


}