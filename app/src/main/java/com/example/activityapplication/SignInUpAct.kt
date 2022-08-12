package com.example.activityapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.activityapplication.databinding.ActivitySignInUpBinding


class SignInUpAct : AppCompatActivity() {
    lateinit var binding: ActivitySignInUpBinding
    private var signState = "empty"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signState = intent.getStringExtra(Constance.SIGN_STATE)!!
        if (signState == Constance.SIGN_IN_STATE){
            binding.edName.visibility = View.GONE
            binding.edName2.visibility = View.GONE
            binding.edName3.visibility = View.GONE
            binding.bAvatar.visibility = View.INVISIBLE
        }
    }


    fun onClickDone(view: View){

        if (signState == Constance.SIGN_UP_STATE){
           val intent = Intent()
            intent.putExtra(Constance.LOGIN, binding.edLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, binding.edPassword.text.toString())
            intent.putExtra(Constance.NAME, binding.edName.text.toString())
            intent.putExtra(Constance.NAME2, binding.edName2.text.toString())
            intent.putExtra(Constance.NAME3, binding.edName3.text.toString())
            if (binding.imAvatar.isVisible)intent.putExtra(Constance.AVATAR_ID, R.drawable.andrey)
            setResult(RESULT_OK, intent)
            finish()
        }else if (signState == Constance.SIGN_IN_STATE){
            intent.putExtra(Constance.LOGIN, binding.edLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, binding.edPassword.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }


    fun onClickAvatar(view: View){
        binding.imAvatar.visibility = View.VISIBLE
    }
}