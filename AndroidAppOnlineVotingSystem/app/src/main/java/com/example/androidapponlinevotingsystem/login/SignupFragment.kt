package com.example.androidapponlinevotingsystem.login

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.widget.doOnTextChanged
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.databinding.FragmentLoginBinding
import com.example.androidapponlinevotingsystem.databinding.FragmentSignupBinding
import java.io.Console

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.inputTextFirstName.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 25) {
                binding.textInputLayoutFirstName.error = "No More"
            }else if(text!!.length < 26 && text!!.length > 3){
                binding.textInputLayoutFirstName.helperText = "Good"
                binding.textInputLayoutFirstName.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
            }else{
                binding.textInputLayoutFirstName.error = null
                binding.textInputLayoutFirstName.helperText = "Required*"
                binding.textInputLayoutFirstName.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextLastName.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 25) {
                binding.textInputLayoutLastName.error = "No More"
            }else if(text!!.length < 26 && text!!.length > 3){
                binding.textInputLayoutLastName.helperText = "Good"
                binding.textInputLayoutLastName.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
            }else{
                binding.textInputLayoutLastName.error = null
                binding.textInputLayoutLastName.helperText = "Required*"
                binding.textInputLayoutLastName.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextCNP.doOnTextChanged { text, start, before, count ->
            if(text!!.length == 13) {

                var cnp: String = text!!.toString()
                val constCnp : String = "279146358279"
                var c: Int = 0


                 for (i in 0..11) {
                      c += (cnp[i].toString()).toInt() *  constCnp[i].digitToInt()
                 }

                  var rez:Int = c % 11
                  if(rez == 10){
                       rez = 1
                    }

                  if(rez!= (cnp[12].toString()).toInt() ){
                      binding.textInputLayoutCNP.error = "Invalid"
                  }else{
                      binding.textInputLayoutCNP.helperText = "Good"
                      binding.textInputLayoutCNP.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                      binding.textInputLayoutCNP.error = null
                  }

            }else {
                  binding.textInputLayoutCNP.error = null
                  binding.textInputLayoutCNP.helperText = "Required*"
                  binding.textInputLayoutCNP.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextPhone.doOnTextChanged { text, start, before, count ->
            if(text!!.length == 10) {

                var c:String = text!!.toString()

                if(c[0].digitToInt() == 0){

                binding.textInputLayoutPhone.helperText = "Good"
                binding.textInputLayoutPhone.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))

                }else{
                    binding.textInputLayoutPhone.error = "Invalid"
                }

            }else{
                binding.textInputLayoutPhone.helperText = "Required*"
                binding.textInputLayoutPhone.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }


        binding.inputTextEmail.doOnTextChanged { text, start, before, count ->

            if( text!!.length > 0){

                var emailText = binding.inputTextEmail.text.toString()
                if(Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
                    binding.textInputLayoutEmail.helperText = "Good"
                    binding.textInputLayoutEmail.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                }else{
                    binding.textInputLayoutEmail.error = "Invalid Email Adress"
                }

            } else {
                binding.textInputLayoutEmail.helperText = "Required*"
                binding.textInputLayoutEmail.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextUsername.doOnTextChanged { text, start, before, count ->

            if(text!!.length > 25) {

                binding.textInputLayoutUsername.error = "No More"

            }else if( text!!.length > 0){

                if(true){     //Verificare in baza de date
                    binding.textInputLayoutUsername.error = null
                    binding.textInputLayoutUsername.helperText = "Good"
                    binding.textInputLayoutUsername.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                }else{
                    binding.textInputLayoutUsername.error = "Username exists"
                }

            } else {
                binding.textInputLayoutUsername.error = null
                binding.textInputLayoutUsername.helperText = "Required*"
                binding.textInputLayoutUsername.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }


        binding.inputTextPassword.doOnTextChanged { text, start, before, count ->

            if(text!!.length >= 8){

                if(!text!!.matches(".*[A-Z].*".toRegex())){

                    binding.textInputLayoutPassword.helperText = "Must Contain 1 Upper-case Character"
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                }else if(!text!!.matches(".*[a-z].*".toRegex())){

                    binding.textInputLayoutPassword.helperText = "Must Contain 1 Lower-case Character"
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                } else if(!text!!.matches(".*[0-9].*".toRegex())){

                    binding.textInputLayoutPassword.helperText = "Must Contain 1 digit"
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                }else if(!text!!.matches(".*[@#\$%^&+=].*".toRegex())){

                    binding.textInputLayoutPassword.helperText = "Must Contain 1 special caracter: @#\$%^&+="
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                }else{
                    binding.textInputLayoutPassword.helperText = "Good"
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                }

            }else if( text!!.length < 8 && text!!.length > 0){
                binding.textInputLayoutPassword.helperText = "Minimum 8 Character Password"
                binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }else {
                binding.textInputLayoutPassword.helperText = "Required*"
                binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextConfirmPassword.doOnTextChanged { text, start, before, count ->

            if(text!!.length > 0){

                if(text!!.toString() == binding.inputTextPassword.text.toString()){
                    binding.textInputLayoutConfirmPassword.helperText = "Good"
                    binding.textInputLayoutConfirmPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                }else{
                    binding.textInputLayoutConfirmPassword.helperText = "Not Match"
                    binding.textInputLayoutConfirmPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                }
            }else{
                binding.textInputLayoutConfirmPassword.helperText = "Required*"
                binding.textInputLayoutConfirmPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }

        }

        return binding.root
    }



}