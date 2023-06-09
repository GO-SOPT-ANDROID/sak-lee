package org.android.go.sopt.ui.join

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.App
import org.android.go.sopt.R
import org.android.go.sopt.data.model.UserInfo
import org.android.go.sopt.data.model.sign.RequestSignUpDto
import org.android.go.sopt.databinding.ActivityJoinBinding
import org.android.go.sopt.util.Constants.INPUT_SUCCESS
import org.android.go.sopt.util.afterTextChanged
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.toast

@AndroidEntryPoint
class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding
    private val viewModel by viewModels<JoinViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)
        setContentView(binding.root)
        initViews()
        setupMsg()
        clickJoin()
        signUpResult()
    }

    private fun setupMsg() {
        viewModel.sign_Intro_Msg.observe(this) {
            binding.tvIntroMsg.text = it
            // 입력 제대로 안하면 버튼 금지
            if (it == INPUT_SUCCESS) binding.btnJoin.isEnabled = true
        }
    }

    /**
     * user가 올바르게 회원가입 입력하고 있는지 알려주려고 만듬
     * */
    private fun initViews() {
        with(binding) {
            etvId.afterTextChanged { dataChanged() }
            etvPwd.afterTextChanged { dataChanged() }
            etvPwdCheck.afterTextChanged { dataChanged() }
            etvName.afterTextChanged { dataChanged() }
            etvSpecialty.afterTextChanged { dataChanged() }
            loJoin.setOnClickListener {
                hideKeyboard()
            }
            vm = viewModel
        }
    }

    private fun dataChanged() {
        viewModel.loginDataChanged(
            id = binding.etvId.text.toString(),
            pwd = binding.etvPwd.text.toString(),
            pwdCheck = binding.etvPwdCheck.text.toString(),
            name = binding.etvName.text.toString(),
            specialty = binding.etvSpecialty.text.toString()
        )
    }

    private fun clickJoin() {
        binding.btnJoin.setOnClickListener {
            //test version
            viewModel.signUp(
                RequestSignUpDto(
                    binding.etvId.text.toString(),
                    binding.etvPwd.text.toString(),
                    binding.etvName.text.toString(),
                    binding.etvSpecialty.text.toString()
                )
            )
        }
    }

    private fun signUpResult() {
        viewModel.signUpResult.observe(this) {
            if (it) {
                setUser()
                val intent = Intent().apply {
                    putExtra("id", binding.etvId.text.toString())
                    putExtra("password", binding.etvPwdCheck.text.toString())
                }
                setResult(RESULT_OK, intent)
                finish()
            } else toast("회원가입 실패", Toast.LENGTH_SHORT)
        }
    }

    /**
     * 회원가입 정보 저장
     * 회원 여러명일떈 생각안해봤음
     * 로그인시 아디 비번 맞추고,마이페이지에서 세팅할떄 사용 -> user정보 있으면 자동로그인
     * */
    private fun setUser() {
        with(binding) {
            App.prefs.saveUserInfo(
                UserInfo(
                    etvId.text.toString(),
                    etvPwd.text.toString(),
                    etvName.text.toString(),
                    etvSpecialty.text.toString()
                )
            )
        }
    }
}