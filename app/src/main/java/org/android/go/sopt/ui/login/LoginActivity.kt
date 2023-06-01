package org.android.go.sopt.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.App
import org.android.go.sopt.MainActivity
import org.android.go.sopt.R
import org.android.go.sopt.data.model.sign.RequestSignInDto
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.ui.join.JoinActivity
import org.android.go.sopt.util.User
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.toast

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        setContentView(binding.root)
        setResultSignUp()
        initClick()
        signInResult()
    }

    private fun initClick() {
        binding.loLogin.setOnClickListener {
            hideKeyboard()
        }

        binding.btnLogin.setOnClickListener {
            viewModel.signIn(
                RequestSignInDto(
                    binding.etvId.text.toString(),
                    binding.etvPwdCheck.text.toString()
                )
            )
        }

        binding.btnJoin.setOnClickListener {
            activityResultLauncher.launch(Intent(this, JoinActivity::class.java))
        }
    }

    private fun signInResult() {
        viewModel.signInResult.observe(this) {
            if (it) {
                App.prefs.isLogin = it
                User.login(App.prefs.getUserInfo())
                Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(this)
                }
            } else toast("로그인 실패", Toast.LENGTH_SHORT)
        }
    }

    private fun setResultSignUp() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    it.data?.run {
                        binding.etvId.setText(getStringExtra("id"))
                        binding.etvPwdCheck.setText(getStringExtra("password"))
                        makeSnackBar(getString(R.string.join_success))
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