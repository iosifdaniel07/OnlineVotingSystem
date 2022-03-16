package com.example.androidapponlinevotingsystem.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.databinding.FragmentLoginBinding
import com.example.androidapponlinevotingsystem.main.MainActivity

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var text: TextView
    private lateinit var loginBtn: Button
    private lateinit var forgotPasswordBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        forgotPasswordBtn = binding.root.findViewById(R.id.forgotPassword)
        loginBtn = binding.root.findViewById(R.id.btnLoggin)
        text = binding.root.findViewById(R.id.textViewError)
        text.visibility = View.INVISIBLE

        loginBtn.setOnClickListener {
            text.visibility = View.VISIBLE
        }

        forgotPasswordBtn.setOnClickListener{
            val intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}