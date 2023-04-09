package org.android.go.sopt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.ui.login.LoginActivity
import org.android.go.sopt.ui.mypage.MyPageActivity
import org.android.go.sopt.util.User
import org.android.go.sopt.util.User.isLoggedIn

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUser()
        initClick()
    }

    private fun initClick() {
        binding.tv1.setOnClickListener {
            if (isLoggedIn.value == true)
                startActivity(Intent(this, MyPageActivity::class.java))
            else
                startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.tv2.setOnClickListener {
            User.logout()
        }
    }

    private fun setUser() {
        if (App.prefs.isLogin) User.login(App.prefs.getUserInfo())
    }
}