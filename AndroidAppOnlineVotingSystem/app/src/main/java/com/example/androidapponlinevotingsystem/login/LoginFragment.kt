package com.example.androidapponlinevotingsystem.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.databinding.FragmentLoginBinding
import com.example.androidapponlinevotingsystem.databinding.FragmentSignupBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var text: TextView
    private lateinit var loginBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginBtn = binding.root.findViewById(R.id.btnLoggin)
        text = binding.root.findViewById(R.id.textViewError)
        text.visibility = View.INVISIBLE

        loginBtn.setOnClickListener {
            text.visibility = View.VISIBLE
        }

        return binding.root
    }

}