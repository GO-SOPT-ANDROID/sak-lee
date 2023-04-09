package org.android.go.sopt.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.databinding.ActivityMyPageBinding
import org.android.go.sopt.model.UserInfo
import org.android.go.sopt.util.User

/**
 * set User Page
 * **/

class MyPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.user= User.user!!.value
    }
}