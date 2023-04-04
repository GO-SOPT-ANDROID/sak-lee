package org.android.go.sopt.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.App
import org.android.go.sopt.MainActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.ui.join.JoinActivity
import org.android.go.sopt.util.User
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResultSignUp()
        initClick()
    }

    private fun initClick() {
        binding.loLogin.setOnClickListener {
            this@LoginActivity.hideKeyboard()
        }

        binding.btnLogin.setOnClickListener {
            val isLoginSuccessful = checkLogin(binding.etvId.text.toString(), binding.etvPwdCheck.text.toString())
            toast(if (isLoginSuccessful) "로그인 성공." else "로그인 실패")
            if (isLoginSuccessful) {
                App.prefs.saveBoolean(isLoginSuccessful)
                User.login(App.prefs.getUserInfo())
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        binding.btnJoin.setOnClickListener {
            activityResultLauncher.launch(Intent(this, JoinActivity::class.java))
        }
    }

    private fun checkLogin(etvId:String, etvPwd:String) = etvId==App.prefs.getId() && etvPwd==App.prefs.getPwd()

    private fun setResultSignUp() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    it.data?.run {
                        binding.etvId.setText(getStringExtra("id"))
                        binding.etvPwdCheck.setText(getStringExtra("password"))
                        makeSnackBar("회원가입 완료")
                    }
                }
            }
    }

    private fun makeSnackBar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}