package com.example.androidapponlinevotingsystem.login

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.widget.doOnTextChanged
import com.example.androidapponlinevotingsystem.R
import com.example.androidapponlinevotingsystem.data.RetrofitService
import com.example.androidapponlinevotingsystem.data.User
import com.example.androidapponlinevotingsystem.data.UserApi
import com.example.androidapponlinevotingsystem.databinding.FragmentLoginBinding
import com.example.androidapponlinevotingsystem.databinding.FragmentSignupBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Console

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private var checkfName: Boolean = false
    private var checklName: Boolean = false
    private var checkUsername: Boolean = false
    private var checkCnp: Boolean = false
    private var checkPhone: Boolean = false
    private var checkEmail: Boolean = false
    private var checkPassword: Boolean = false
    private var checkConfirmPassword: Boolean = false
    private lateinit var newUser: User
    private lateinit var btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newUser = User()

        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.inputTextFirstName.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 25) {
                binding.textInputLayoutFirstName.error = "No More"
                checkfName = false
            }else if(text!!.length < 26 && text!!.length > 3){
                checkfName = true
                newUser.fname = text!!.toString()
                binding.textInputLayoutFirstName.helperText = "Good"
                binding.textInputLayoutFirstName.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
            }else{
                checkfName = false
                binding.textInputLayoutFirstName.error = null
                binding.textInputLayoutFirstName.helperText = "Required*"
                binding.textInputLayoutFirstName.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextLastName.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 25) {
                binding.textInputLayoutLastName.error = "No More"
                checklName = false
            }else if(text!!.length < 26 && text!!.length > 3){
                checklName = true
                newUser.lname = text!!.toString()
                binding.textInputLayoutLastName.helperText = "Good"
                binding.textInputLayoutLastName.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
            }else{
                checklName = false
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
                      checkCnp = false
                  }else{
                      binding.textInputLayoutCNP.helperText = "Good"
                      binding.textInputLayoutCNP.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                      binding.textInputLayoutCNP.error = null
                      checkCnp = true
                      newUser.cnp = text!!.toString()
                  }

            }else {
                  checkCnp = false
                  binding.textInputLayoutCNP.error = null
                  binding.textInputLayoutCNP.helperText = "Required*"
                  binding.textInputLayoutCNP.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextPhone.doOnTextChanged { text, start, before, count ->
            if(text!!.length == 10) {

                var c:String = text!!.toString()

                if(c[0].digitToInt() == 0){
                checkPhone = true
                newUser.phone = text!!.toString()
                binding.textInputLayoutPhone.helperText = "Good"
                binding.textInputLayoutPhone.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))

                }else{
                    binding.textInputLayoutPhone.error = "Invalid"
                    checkPhone = false
                }

            }else{
                checkPhone = false
                binding.textInputLayoutPhone.helperText = "Required*"
                binding.textInputLayoutPhone.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }


        binding.inputTextEmail.doOnTextChanged { text, start, before, count ->

            if( text!!.length > 0){

                var emailText = binding.inputTextEmail.text.toString()
                if(Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
                    checkEmail = true
                    newUser.email = text!!.toString()
                    binding.textInputLayoutEmail.helperText = "Good"
                    binding.textInputLayoutEmail.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                }else{
                    binding.textInputLayoutEmail.error = "Invalid Email Adress"
                    checkEmail = false
                }

            } else {
                checkEmail = false
                binding.textInputLayoutEmail.helperText = "Required*"
                binding.textInputLayoutEmail.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextUsername.doOnTextChanged { text, start, before, count ->

            if(text!!.length > 25) {

                binding.textInputLayoutUsername.error = "No More"
                checkUsername = false

            }else if( text!!.length > 0){

                if(true){     //Verificare in baza de date
                    checkUsername = true
                    newUser.username = text!!.toString()
                    binding.textInputLayoutUsername.error = null
                    binding.textInputLayoutUsername.helperText = "Good"
                    binding.textInputLayoutUsername.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                }else{
                    binding.textInputLayoutUsername.error = "Username exists"
                    checkUsername = false
                }

            } else {
                checkUsername = false
                binding.textInputLayoutUsername.error = null
                binding.textInputLayoutUsername.helperText = "Required*"
                binding.textInputLayoutUsername.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }


        binding.inputTextPassword.doOnTextChanged { text, start, before, count ->

            if(text!!.length >= 8){

                if(!text!!.matches(".*[A-Z].*".toRegex())){

                    checkPassword = false
                    binding.textInputLayoutPassword.helperText = "Must Contain 1 Upper-case Character"
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                }else if(!text!!.matches(".*[a-z].*".toRegex())){

                    checkPassword = false
                    binding.textInputLayoutPassword.helperText = "Must Contain 1 Lower-case Character"
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                } else if(!text!!.matches(".*[0-9].*".toRegex())){

                    checkPassword = false
                    binding.textInputLayoutPassword.helperText = "Must Contain 1 digit"
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                }else if(!text!!.matches(".*[@#\$%^&+=].*".toRegex())){

                    checkPassword = false
                    binding.textInputLayoutPassword.helperText = "Must Contain 1 special caracter: @#\$%^&+="
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                }else{
                    checkPassword = true
                    binding.textInputLayoutPassword.helperText = "Good"
                    binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                }

            }else if( text!!.length < 8 && text!!.length > 0){
                checkPassword = false
                binding.textInputLayoutPassword.helperText = "Minimum 8 Character Password"
                binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }else {
                checkPassword = false
                binding.textInputLayoutPassword.helperText = "Required*"
                binding.textInputLayoutPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }
        }

        binding.inputTextConfirmPassword.doOnTextChanged { text, start, before, count ->

            if(text!!.length > 0){

                if(text!!.toString() == binding.inputTextPassword.text.toString()){
                    checkConfirmPassword = true
                    newUser.password = text!!.toString()
                    binding.textInputLayoutConfirmPassword.helperText = "Good"
                    binding.textInputLayoutConfirmPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#00FF00")))
                }else{
                    checkConfirmPassword = false
                    binding.textInputLayoutConfirmPassword.helperText = "Not Match"
                    binding.textInputLayoutConfirmPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
                }
            }else{
                checkConfirmPassword = false
                binding.textInputLayoutConfirmPassword.helperText = "Required*"
                binding.textInputLayoutConfirmPassword.setHelperTextColor(ColorStateList.valueOf(Color.parseColor("#FF0000")))
            }

        }

        btn = binding.root.findViewById(R.id.signupBtn)
        btn.setOnClickListener {

          //  if(checkUsername && checkfName && checklName && checkCnp && checkEmail && checkPhone && checkConfirmPassword && checkPassword){

                val retrofitService =  RetrofitService()
                val userApi = retrofitService.getRetrofit() .create(UserApi::class.java)

                val user = User()

                user.phone="02311232133"
                user.cnp="555555"
                user.email="dsaadsds@ddd.dsd"
                user.password="sssss"
                user.fname="sssss"
                user.lname="ewqqweqwe"
                user.username="euuuuu0515"

                userApi.save(user).enqueue(object : Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(activity,"fail",Toast.LENGTH_SHORT).show()
                    }
                })

           // }

        }

        return binding.root
    }


}