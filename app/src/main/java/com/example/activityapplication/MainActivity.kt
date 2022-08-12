package com.example.activityapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.activityapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var name2: String = "empty"
    private var name3: String = "empty"
    private var avatarImageId: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constance.REQUEST_CODE_SIGN_IN){
            val l = data?.getStringExtra(Constance.LOGIN)
            val p = data?.getStringExtra(Constance.PASSWORD)
            if (login == l && password == p) {

                binding.imAvatar.visibility = View.VISIBLE
                binding.imAvatar.setImageResource(avatarImageId)
                val textInfo = "$name $name2 $name3"
                binding.tvInfo.text = textInfo
                binding.bHide.visibility = View.GONE
                binding.bExit.text = "Выйти"

            }else{
                binding.imAvatar.visibility = View.VISIBLE
                binding.imAvatar.setImageResource(R.drawable.dula)
                binding.tvInfo.text = "Такого аккаунта не существует!"

            }
        }else if (requestCode == Constance.REQUEST_CODE_SIGN_UP){

            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data.getStringExtra(Constance.PASSWORD)!!
            name = data.getStringExtra(Constance.NAME)!!
            name2 = data.getStringExtra(Constance.NAME2)!!
            name3 = data.getStringExtra(Constance.NAME3)!!
            avatarImageId = data.getIntExtra(Constance.AVATAR_ID, 0)
            binding.imAvatar.visibility = View.VISIBLE
            binding.imAvatar.setImageResource(avatarImageId)
            val textInfo = "$name $name2 $name3"
            binding.tvInfo.text = textInfo
            binding.bHide.visibility = View.GONE
            binding.bExit.text = "Выйти"

        }

    }

    fun onClickSignIn(view: View){
        if (binding.imAvatar.isVisible && binding.tvInfo.text.toString() != "Такого аккаунта не существует!"){

            binding.imAvatar.visibility = View.INVISIBLE
            binding.tvInfo.text = ""
            binding.bHide.visibility = View.VISIBLE
            binding.bExit.text = getString(R.string.sign_in)

        } else {

            val intent = Intent(this, SignInUpAct::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)
        }
    }
    fun onClickSignUp(view: View){
        val intent = Intent(this, SignInUpAct::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)
    }
}
